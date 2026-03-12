package ai.aurora;

import javax.tools.*;

import ai.aurora.sandbox.SandboxClassLoader;

import java.io.*;
import java.net.URI;
import java.util.*;

public class InMemoryCompiler {
	
    public static void main(String[] args) throws Exception {
        String className = "Teste";
        String code = "public class Teste { public void run() { System.exit(0); } }";

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();

        // 1. Define the input "file" (from String)
        JavaFileObject file = new SimpleJavaFileObject(URI.create("string:///" + className + ".java"), 
                JavaFileObject.Kind.SOURCE) {
            @Override
            public CharSequence getCharContent(boolean ignoreEncodingErrors) { return code; }
        };

        // 2. Define the output "file" (to Byte Array)
        Map<String, ByteArrayOutputStream> classBytes = new HashMap<>();
        StandardJavaFileManager stdFileManager = compiler.getStandardFileManager(diagnostics, null, null);
        
        JavaFileManager fileManager = new ForwardingJavaFileManager<JavaFileManager>(stdFileManager) {
            @Override
            public JavaFileObject getJavaFileForOutput(Location location, String className, 
                    JavaFileObject.Kind kind, FileObject sibling) {
                return new SimpleJavaFileObject(URI.create("mem:///" + className + ".class"), kind) {
                    @Override
                    public OutputStream openOutputStream() {
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        classBytes.put(className, baos);
                        return baos;
                    }
                };
            }
        };

        // 3. Compile
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, null, null, Arrays.asList(file));
        
        if (task.call()) {
            // 4. Load the class from memory
            SandboxClassLoader loader = new SandboxClassLoader() {
                @Override
                protected Class<?> findClass(String name) throws ClassNotFoundException {
                    byte[] b = classBytes.get(name).toByteArray();
                    return defineClass(name, b, 0, b.length);
                }
            };

            Class<?> clazz = loader.loadClass(className);
            Object instance = clazz.getDeclaredConstructor().newInstance();
            clazz.getMethod("run").invoke(instance);
        } else {
            diagnostics.getDiagnostics().forEach(System.out::println);
        }
    }
}

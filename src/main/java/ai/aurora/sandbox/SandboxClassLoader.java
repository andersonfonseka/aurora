package ai.aurora.sandbox;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SandboxClassLoader extends ClassLoader {
    private final Set<String> allowedClasses = new HashSet<>(Arrays.asList(
        "java.lang.String", "java.lang.Integer", "java.lang.Math", "java.util.Map", "java.lang.Object"
    ));

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        // Only allow core logic and your framework's BotAction interface
        if (name.startsWith("java.") && !allowedClasses.contains(name)) {
            throw new SecurityException("Access to " + name + " is blocked in Aurora Bot.");
        }
        return super.loadClass(name, resolve);
    }
}

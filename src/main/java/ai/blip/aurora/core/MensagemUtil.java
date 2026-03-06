package ai.blip.aurora.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MensagemUtil {
	
	private Properties properties = new Properties();
	
	private static MensagemUtil INSTANCE;

	
	public static MensagemUtil obterInstancia() {
		
		if (INSTANCE == null) {
			INSTANCE = new MensagemUtil();
		}

		return INSTANCE;
	}
	
	private MensagemUtil() {
		
		InputStream is = MensagemUtil.class.getResourceAsStream("/aurora.properties");

		try {
			properties.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	public String obter(String chave){
		return this.properties.getProperty(chave);
	}
	
}

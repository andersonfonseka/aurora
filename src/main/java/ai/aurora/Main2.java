package ai.aurora;

import java.io.FileNotFoundException;
import java.util.Random;

import ai.aurora.core.Bot;
import ai.aurora.xml.AuroraXmlParser;

public class Main2 {

	public static void main(String[] args) throws FileNotFoundException, Exception {

		
		Bot auroraRHBot = new AuroraXmlParser().carregar(Main2.class.getResourceAsStream("/bot/tutor.xml"), new Random().nextLong(999999));
		
		auroraRHBot.run();
		
	}
	
}

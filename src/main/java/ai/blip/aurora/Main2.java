package ai.blip.aurora;

import java.io.FileNotFoundException;
import java.util.Random;
import java.util.UUID;

import ai.blip.aurora.core.Bot;
import ai.blip.aurora.xml.AuroraXmlParser;

public class Main2 {

	public static void main(String[] args) throws FileNotFoundException, Exception {

		//= new AuroraRHBot();
		
		Bot auroraRHBot = new AuroraXmlParser().carregar(Main2.class.getResourceAsStream("/bot/marketplace.xml"), new Random().nextLong(999999));
				//new AuroraXmlParser().carregar(Main.class.getResourceAsStream("/bot/pizza_delivery.xml")); 
				//new AuroraXmlParser().carregar(Main.class.getResourceAsStream("/bot/AuroraRHBot.xml"));
		
		auroraRHBot.run();
		
	}
	
}

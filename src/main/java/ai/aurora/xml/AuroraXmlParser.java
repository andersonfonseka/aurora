package ai.aurora.xml;

import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import ai.aurora.core.Bot;

public class AuroraXmlParser {

    public Bot carregar(InputStream xmlInput, long chatId) throws Exception {
     
    	SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        
        AuroraXmlHandler handler = new AuroraXmlHandler();
        saxParser.parse(xmlInput, handler);
        
        Bot bot = handler.getBot();
        	bot.setChatId(chatId);
        
        return bot;
    }
}
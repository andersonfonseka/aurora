package ai.blip.aurora.xml;

import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import ai.blip.aurora.core.Bot;

public class AuroraXmlParser {

    public Bot carregar(InputStream xmlInput) throws Exception {
     
    	SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        
        AuroraXmlHandler handler = new AuroraXmlHandler();
        saxParser.parse(xmlInput, handler);
        
        return handler.getBot();
    }
}
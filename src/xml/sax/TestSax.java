package xml.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.ext.DefaultHandler2;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * @author Monster
 */
public class TestSax extends DefaultHandler2 {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        TestSax testSax = new TestSax();
        testSax.test(Paths.get("src", "xml", "web.xml").toFile());
        System.out.println("--------------------------------------------------------------------");
        testSax.test(Paths.get("src", "xml", "server.xml").toFile());
    }

    public void test(File f) throws ParserConfigurationException, IOException, SAXException {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        saxParser.parse(new FileInputStream(f), this);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.println("标签:" + qName);
        for (int i = 0; i < attributes.getLength(); i++) {
            String name = attributes.getQName(i);
            String value = attributes.getValue(i);
            System.out.println("[属性:" + name + " 属性值:" + value + "]");
        }
        super.startElement(uri, localName, qName, attributes);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String trim = new String(ch, start, length).trim();
        trim = trim.replaceAll("\n", "");
        if (trim.length() > 0) {
            System.out.println("标签值:" + trim);
        }
        super.characters(ch, start, length);
    }
}

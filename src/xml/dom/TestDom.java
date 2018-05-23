package xml.dom;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * @author Monster
 */
public class TestDom {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        TestDom testDom = new TestDom();
        testDom.test(Paths.get("src", "xml", "web.xml").toFile());
        System.out.println("--------------------------------------------------------------------");
        testDom.test(Paths.get("src", "xml", "server.xml").toFile());
    }

    public void test(File f) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(f);
        System.out.println("InputEncoding：" + document.getInputEncoding());
        System.out.println("XmlStandalone：" + document.getXmlStandalone());
        System.out.println("XmlVersion：" + document.getXmlVersion());

        System.out.println("LocalName：" + document.getLocalName());
        System.out.println("TextContent：" + document.getTextContent());

        System.out.println("Prefix：" + document.getPrefix());
        System.out.println("NodeName：" + document.getNodeName());
        /**
         * 节点类型
         * @see org.w3c.dom.Node
         */
        System.out.println("NodeType：" + document.getNodeType());
        System.out.println("NodeValue：" + document.getNodeValue());

        System.out.println("BaseURI：" + document.getBaseURI());
        System.out.println("NamespaceURI：" + document.getNamespaceURI());
        System.out.println("DocumentURI：" + document.getDocumentURI());

        System.out.println("-------------------------------DOMConfiguration start-------------------------------------");
        DOMConfiguration domConfig = document.getDomConfig();
        DOMStringList domStringList = domConfig.getParameterNames();
        for(int i = 0 ; i < domStringList.getLength(); i++) {
            System.out.println(domStringList.item(i));
        }
        System.out.println("--------------------------------DOMConfiguration end------------------------------------");

        Element element = document.getDocumentElement();
        recursive(element);
    }

    public void recursive(Element element) {
        if(element.hasChildNodes()) {
            System.out.println("Tag:" + element.getTagName());
        } else {
            System.out.println("Tag:" + element.getTagName() + " TagValue:" + element.getTextContent());
        }

        if(element.hasAttributes()) {
            NamedNodeMap namedNodeMap = element.getAttributes();
            for (int i = 0; i < namedNodeMap.getLength(); i++) {
                Node n = namedNodeMap.item(i);
                System.out.println("[Attribute:" + n.getNodeName() + " AttributeValue:" + n.getNodeValue() + "]");
            }
        }
        if(element.hasChildNodes()) {
            NodeList nodeList = element.getChildNodes();
            for(int i = 0 ; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if(Node.ELEMENT_NODE == node.getNodeType()) {
                    recursive((Element) node);
                } else if(Node.COMMENT_NODE == node.getNodeType()) {
//                    System.out.println(node.getTextContent());
                }
            }
        }
    }
}

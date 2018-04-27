package xml;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;

public class XmlTester {

    public static void main(String[] args) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            XmlAccess acc = XmlAccess.getInstance();
            acc.loadXml();
            Document doc = acc.getPartOfTree("Action");
            test(doc);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void test(Document doc) throws Exception{
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(System.out);
        transformer.transform(source, result);
    }

}

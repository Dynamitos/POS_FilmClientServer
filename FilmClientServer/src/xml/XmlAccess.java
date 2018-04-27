package xml;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlAccess {

    DocumentBuilderFactory factory;
    DocumentBuilder builder;
    Document doc;

    private XmlAccess() {
        try {
            factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
            loadXml();
        } catch (Exception e) {
            System.err.println("XmlAccess-Error:\n" + e.getStackTrace());
        }
    }

    public static XmlAccess getInstance() {
        return XmlAccessHolder.INSTANCE;
    }

    private static class XmlAccessHolder {

        private static final XmlAccess INSTANCE = new XmlAccess();
    }

    public void loadXml() throws Exception {
        doc = builder.parse(new File("D:src\\resource\\film.xml"));
    }

    public Document getPartOfTree(String category) {
        Document selection = builder.newDocument();
        selection.appendChild(selection.createElement("workaroundTag"));
        Element root = doc.getDocumentElement();
        NodeList nodes = root.getElementsByTagName("categoryList");
        for (int i = 0; i < nodes.getLength(); i++) {
            Node n = nodes.item(i);
            if (n.getNodeType() == Node.ELEMENT_NODE) {
                NodeList children = n.getChildNodes();
                for (int j = 0; j < children.getLength(); j++) {
                    Node n2 = children.item(j);
                    if (n2.getNodeType() == Node.ELEMENT_NODE) {
                        if (n2.getNodeName() == "name") {
                            if (n2.getTextContent().equals(category)) {
                                Node newNode = selection.importNode(n, true);
                                selection.getDocumentElement().appendChild(newNode);
                            }
                        }
                    }
                }
            }
        }
        return selection;
    }

}

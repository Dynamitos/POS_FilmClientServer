package server;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.text.JTextComponent;
import org.w3c.dom.Document;
import utilityServer.BasicServer;
import xml.XmlAccess;

public class FilmServer extends BasicServer {

    private XmlAccess xml;

    public FilmServer(int portnr, JTextComponent logPane) {
        super(portnr, logPane);
        xml = XmlAccess.getInstance();
    }

    private List<String> loadCategoriesFromFile() throws Exception {
        return Files.lines(Paths.get("D:src\\resource\\categories.txt"))
                .collect(Collectors.toList());
    }

    private Document generateByCategory(String filmCategory) {
        return xml.getPartOfTree(filmCategory);
    }

    @Override
    public Object performRequest(Object request) throws Exception {
        String[] cmd = ((String) request).split("#");
        switch(cmd[0]) {
            case "KATEGORIEN":
                return loadCategoriesFromFile();
            case "KATEGORIE":
                return generateByCategory(cmd[1]);
            default:
                return "FEHLER#cmd nicht erkannt";
        }
    }

}

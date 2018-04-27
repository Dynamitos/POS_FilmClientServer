package client;

import client.utilityClient.ClientHelper;
import java.util.List;
import org.w3c.dom.Document;

public class DocumentClient {
    
    public static List<String> getKategorien() throws Exception {
        Object o = ClientHelper.sendAndReceiveObject("127.0.0.1", 9999, "KATEGORIEN");
        if(!(o instanceof List)) {
            throw new Exception("Error in getKategorien()");
        }
        return (List<String>) o;
    }
    
    public static Document getDom(String filmCategory) throws Exception {
        Object o = ClientHelper.sendAndReceiveObject("127.0.0.1", 9999, "KATEGORIE#"+filmCategory);
        if(!(o instanceof Document)) {
            throw new Exception("Error in getDom()");
        }
        return (Document) o;
    }
    
}

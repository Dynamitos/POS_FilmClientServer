package client.utilityClient;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import javax.swing.text.JTextComponent;

public class ClientHelper {

    public static JTextComponent logPane = null;

    /**
     * Universelle Send-and-receive Methode
     *
     * @param ip
     * @param port
     * @param request: beliebiges Object
     * @return: Object, muss auf gesendeten Typ gecasted werden
     * @throws Exception
     */
    public static Object sendAndReceiveObject(String ip, int port,
            Object request)
            throws Exception {
        //Anfrage an Server und wenn erfolgreich, dann Verbindung akzeptiert
        Socket client = new Socket(ip, port);

        //Datenaustausch: request senden
        OutputStream os = client.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(os);
        log("Client Request: " + request);
        oos.writeObject(request);

        //response empfangen
        ObjectInputStream ois = new ObjectInputStream(
                client.getInputStream());
        Object response = ois.readObject();
        log("Client Response: " + response);

        return response;
    }

    /**
     * Send-and-receive Methode für String-Objekte.
     *
     * @param ip
     * @param port
     * @param request: String
     * @return: Object wird auf gesendeten String gecasted
     * @throws Exception
     */
    public static String sendAndReceiveLine(String ip, int port,
            String request)
            throws Exception {
        Object objectResponse = sendAndReceiveObject(ip, port, request);

        if (!(objectResponse instanceof String)) {
            throw new Exception("Falscher Response-Type: String erwartet!");
        }
        String response = (String) objectResponse;
        return response;
    }

    public static void log(String logMessage) {
        if (logPane != null) {
            logPane.setText(logPane.getText() + logMessage + "\n");
        } else {
            System.out.println(logMessage);
        }
    }
}

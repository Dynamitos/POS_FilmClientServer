package server.utilityServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import javax.swing.text.JTextComponent;

public abstract class BasicServer {

    private ServerThread st;
    private JTextComponent logPane;
    private final int portnr;

    public BasicServer() {
        portnr = 9999;
    }

    public BasicServer(int portnr) {
        this.portnr = portnr;
    }

    public BasicServer(int portnr, JTextComponent logPane) {
        this(portnr);
        this.logPane = logPane;
    }

    public void startServer() {
        if (st == null || !st.isAlive()) {
            st = new ServerThread();
            st.start();
        }
    }

    public void stopServer() {
        if (st != null && st.isAlive()) {
            st.interrupt();
        }
    }

    protected void log(String logMessage) {
        if (logPane != null) {
            logPane.setText(logPane.getText() + logMessage + "\n");
        } else {
            System.out.println(logMessage);
        }
    }

    class ServerThread extends Thread {

        ServerSocket server;

        public ServerThread() {
            try {
                Thread.currentThread().setPriority(Thread.MIN_PRIORITY + 2);
                server = new ServerSocket(portnr);
                server.setSoTimeout(250);
                log("Server waiting on port " + portnr + " ...");
            } catch (IOException ex) {
                log("Error while starting server: " + ex.toString());
            }
        }

        @Override
        public void run() {
            while (!isInterrupted()) {
                try {
                    Socket socket = server.accept();
                    log("server connected to: " + socket.getRemoteSocketAddress());
                    new Thread(new ClientCommunicationThread(socket)).start();
                } catch (SocketTimeoutException ex) {
//          log("Socket timeout");
                } catch (IOException ex) {
                    log("Error during server connect");
                }
            }
            try {
                server.close();
                log("Server (portnr " + portnr + ") closed");
            } catch (IOException ex) {
                log("Error while closing server");
            }
        }
    }

    class ClientCommunicationThread implements Runnable {

        private Socket socket;

        public ClientCommunicationThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            ObjectInputStream ois = null;
            ObjectOutputStream oos = null;
            try {
                InputStream is = socket.getInputStream();
                is.available();
                ois = new ObjectInputStream(is);
                OutputStream os = socket.getOutputStream();
                oos = new ObjectOutputStream(os);

                Object request = ois.readObject();
                log("Server Request: " + request);
                Object response = performRequest(request);
                oos.writeObject(response);
                log("Server Response: " + response);

            } catch (Exception ex) {
                log("Error during client communication " + ex.toString());
            } finally {
                try {
                    ois.close();
                    oos.close();
                    socket.close();
                } catch (IOException ex) {
                    log("Error during client communication " + ex.toString());
                }
            }
        }
    }

    public abstract Object performRequest(Object request) throws Exception;

    public static void main(String[] args) throws InterruptedException {
        BasicServer bs = new BasicServer() {
            @Override
            public Object performRequest(Object request) {
                return request;
            }
        };
        bs.startServer();
        Thread.sleep(5000);
        bs.stopServer();
        System.out.println("finished");
    }
}

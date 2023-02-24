package fr.univ_amu.iut.tools;

import javafx.scene.control.Alert;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class containing the client used for the communications with the server simulating a linux console
 */
public class ClientTerminal {

    private static final String[] protocols = new String[]{"TLSv1.3"};
    private static final String[] cipher_suites = new String[]{"TLS_AES_128_GCM_SHA256"};
    private String hostname;
    private int port;
    private SSLSocket socketClient;
    private BufferedWriter out;
    private BufferedReader in;
    private List<String> txtReceived;

    /**
     * Constructor of the console used in the "play" scenes
     *
     * @param hostname
     * @param port
     */
    public ClientTerminal(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    /**
     * Method used to initialize the connection between the client and the server
     *
     * @throws UnknownHostException
     * @throws IOException
     */
    public void connect() {
        try {
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            socketClient = (SSLSocket) factory.createSocket(hostname,port);

            socketClient.setEnabledProtocols(protocols);
            socketClient.setEnabledCipherSuites(cipher_suites);
            socketClient.startHandshake();

            out = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
        } catch (IOException e) {
            Alert serverNotOnline = new Alert(Alert.AlertType.ERROR, "Connexion avec le serveur inexistante \nContactez le support");
            serverNotOnline.show();
            throw new RuntimeException(e);
        }
    }

    /**
     * Method used to send a message
     *
     * @param msg
     */
    public void send(String msg){
        try {
            out.write(msg);
            out.newLine();
            out.flush();
            Thread.sleep(1000);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method used to listen continuously to all the responses sent from the server
     */
    public void listen(){
        txtReceived = new ArrayList<String>();

        Thread receive = new Thread(new Runnable() {
            @Override
            public void run() {
                String bufReceived = "";
                try {
                    bufReceived = in.readLine();

                    while(bufReceived!=null){
                        //System.out.println(bufReceived);
                        txtReceived.add(bufReceived);
                        bufReceived = in.readLine();
                    }
                    System.out.println("Server out of service");
                    out.close();
                    socketClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        receive.start();
    }

    /**
     * Method used to return the buffer received from the server
     *
     * @return the buffer received from the server
     */
    public List<String> getBufferReceived(){
        List<String> buffReceived = new ArrayList<String>(txtReceived);
        txtReceived = new ArrayList<String>();
        return buffReceived;
    }
}

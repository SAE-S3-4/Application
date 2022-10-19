package sae_s3.findthebreach.Connection;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class ClientTerminal {

    private String hostname;
    private int port;
    private Socket socketClient;
    private BufferedWriter out;
    private BufferedReader in;
    private List<String> txtReceived;

    public ClientTerminal(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    public void connect() throws UnknownHostException, IOException {
        socketClient = new Socket(hostname, port);
        out = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
        in = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
    }

    public void send(String msg){
        try {
            out.write(msg);
            // rajouter un saut de ligne pour pouvoir utiliser readline c�t� serveur
            out.newLine();
            // vider le buffer
            out.flush();
            Thread.sleep(100);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

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

    public List<String> getBufferReceived(){
        List<String> buffReceived = new ArrayList<String>(txtReceived);
        txtReceived = new ArrayList<String>();
        return buffReceived;
    }
}

package fr.univ_amu.iut.Connection;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTerminal {
    private int port;
    private int nbClients;

    public ServerTerminal(int port, int nbClients) {
        this.port = port;
        this.nbClients = nbClients;
    }

    public void launch() throws IOException {

        ServerSocket server = new ServerSocket(port);

        System.out.println("Bash server launched on port : " + port);

        for (int i = 1; i <= nbClients; i++) {

            Socket client = server.accept();

            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

            Thread receive = new Thread(new Runnable() {
                String msg;

                @Override
                public void run() {
                    try {
                        while ((msg = in.readLine()) != null) {
                            try {
                                Process process = Runtime.getRuntime().exec(msg);

                                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                                String line = "";

                                while ((line = reader.readLine()) != null) {
                                    out.write(line);
                                    out.newLine();
                                    out.flush();
                                    System.out.println(line);
                                }
                            }catch (IOException | NullPointerException | IllegalArgumentException e) {
                                out.write("Command does not exist");
                                out.newLine();
                                out.flush();
                            }
                        }
                        //sortir de la boucle si le client a déconecté
                        System.out.println("Client disconnected");
                        //fermer le flux et la session socket
                        out.close();
                        client.close();
                        //server.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            receive.start();
        }
        server.close();
    }

    public static void main(String[] args) throws IOException {

        ServerTerminal server = new ServerTerminal(10007,1000);
        server.launch();

    }
}

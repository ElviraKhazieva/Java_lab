package ru.itis.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Connection implements Runnable {

    private Socket client;
    private Thread thread;
    private Server server;

    public Connection(Server server, Socket client) {
        this.client = client;
        this.server = server;
        thread = new Thread(this);
        thread.start();
    }

    public Socket getClient() {
        return client;
    }

    public Server getServer() {
        return server;
    }

    public void run() {
        try {
            BufferedReader fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            ArrayList<Connection> connections = server.getConnections();
            String messageFromClient = fromClient.readLine();
            while (messageFromClient != null) {
                for (int i = 0; i < connections.size(); i++) {
                    PrintWriter toClients = new PrintWriter(connections.get(i).getClient().getOutputStream(), true);
                    toClients.println(messageFromClient);
                }
                messageFromClient = fromClient.readLine();
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

}

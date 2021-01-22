package ru.itis.chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    private int port;
    private ArrayList<Connection> connections;

    public Server(int port) {
        this.port = port;
        connections = new ArrayList<>();
        start();
    }

    public ArrayList<Connection> getConnections() {
        return connections;
    }

    public void sendMessageToAllClients(String message) {
        PrintWriter toClients = null;
        for (Connection connection : connections) {
            try {
                toClients = new PrintWriter(connection.getClient().getOutputStream(), true);
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
            toClients.println(message);
        }
    }

    public void start() {
        try {
            ServerSocket server = new ServerSocket(port);
            while (true) {
                Socket client = server.accept();
                connections.add(new Connection(this, client));
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

}

package com.chatroom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server implements Runnable {

    private ArrayList<ConnectionHandler> connections;

    @Override
    public void run() {
        try {
            ServerSocket server = new ServerSocket(9999);
            Socket client = server.accept();
            ConnectionHandler handler = new ConnectionHandler(client);
            connections.add(handler);
        } catch (IOException e) {
            //TODO: handle function
        }

    }

    //handles clients connection
    class ConnectionHandler implements Runnable {
        private Socket client;
        private BufferedReader in; //gets the stream from socket
        private PrintWriter out; // writes to client
        private String name;

        //CTOR
        public ConnectionHandler(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            try {
                //initialize read and write stream
                out = new PrintWriter(client.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                out.println("Enter your name: ");
                name = in.readLine();
                System.out.println(name + " connected!");

            } catch (IOException e) {
                //TODO: handle function
            }
        }

        public void sendMessage(String message) {
            out.println(message);

        }
    }
}

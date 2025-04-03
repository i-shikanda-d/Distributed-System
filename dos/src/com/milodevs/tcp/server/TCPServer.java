package com.milodevs.tcp.server;

import java.io.*;
import java.net.*;

public class TCPServer {
    // Declare the server socket and port number
    private static ServerSocket serverSock;
    private static final int PORT = 5154;

    public static void main(String[] args) {
        System.out.println("Opening server on port:" + PORT);

        try {
            // Initialize the server socket
            serverSock = new ServerSocket(PORT);
            System.out.println("Server opened on port:" + PORT);

            // Continuously listen for client connections
            do {
                runServer();
            } while (true);
        } catch (IOException e) {
            // Handle exceptions related to server socket
            e.printStackTrace();
        } finally {
            try {
                // Close the server socket if it is not null
                if (serverSock != null) {
                    serverSock.close();
                    System.out.println("Server closed.");
                }
            } catch (IOException e) {
                // Handle errors during server socket closure
                System.out.println("Error closing server socket.");
            }
        }
    }

    private static void runServer() {
        Socket link = null; // Socket for client connection
        try {
            // Accept a client connection
            link = serverSock.accept();
            System.out.println("Connection opened.");

            // Set up input and output streams for communication
            BufferedReader input = new BufferedReader(new InputStreamReader(link.getInputStream()));
            PrintWriter output = new PrintWriter(link.getOutputStream(), true);

            String in = input.readLine(); // Read the first message from the client
            int numMsg = 0; // Counter for the number of messages received

            // Process messages until the client sends "exit"
            while (!in.equals("exit")) {
                System.out.println("Message received " + in); // Log the received message
                numMsg++; // Increment the message counter
                output.println("Message " + numMsg + ": " + in); // Send a response to the client
                in = input.readLine(); // Read the next message
            }
            System.out.println("Closing connection.");
        } catch (IOException e) {
            // Handle I/O exceptions
            e.printStackTrace();
        } finally {
            try {
                // Close the client connection
                if (link != null) {
                    link.close();
                    System.out.println("Connection closed.");
                }
            } catch (IOException e) {
                // Handle errors during client socket closure
                System.out.println("Error closing client socket.");
            }
        }
    }
}
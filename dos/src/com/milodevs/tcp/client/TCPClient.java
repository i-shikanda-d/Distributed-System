package com.milodevs.tcp.client;
import java.io.*;
import java.net.*;

public class TCPClient {
    // Declare the host and port for the TCP connection
    private static InetAddress host;
    private static final int PORT = 5154;

    public static void main(String[] args) {
        try {
            // Get the local host address
            host = InetAddress.getLocalHost();
            // Start the client
            runClient();
        } catch (UnknownHostException e) {
            // Handle case where the host is not found
            System.out.println("Host not found");
        }
    }

    private static void runClient() {
        Socket link = null; // Socket for the connection
        try {
            // Establish a connection to the server
            link = new Socket(host, PORT);

            // Set up input and output streams for communication
            BufferedReader input = new BufferedReader(new InputStreamReader(link.getInputStream()));
            PrintWriter output = new PrintWriter(link.getOutputStream(), true);
            BufferedReader userInp = new BufferedReader(new InputStreamReader(System.in));

            String msg, resp;
            do {
                // Prompt the user for a message
                System.out.print("Enter message: ");
                msg = userInp.readLine(); // Read user input
                output.println(msg); // Send the message to the server

                // Read and display the server's response
                resp = input.readLine();
                System.out.println("\nSERVER> " + resp);
            } while (!msg.equals("exit")); // Exit the loop if the user types "exit"
        } catch (IOException e) {
            // Handle I/O exceptions
            e.printStackTrace();
        } finally {
            try {
                // Close the connection
                System.out.println("Closing connection.");
                link.close();
            } catch (IOException e) {
                // Handle errors during disconnection
                System.out.println("Unable to disconnect.");
                System.exit(1);
            }
        }
    }
}
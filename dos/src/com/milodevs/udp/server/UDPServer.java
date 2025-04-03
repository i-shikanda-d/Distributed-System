package com.milodevs.udp.server;

import java.io.IOException;
import java.net.*;

public class UDPServer {
    // Declare the DatagramSocket, packets, port number, and buffer
    private static DatagramSocket dgramSocket;
    private static DatagramPacket inPacket, outPacket;
    private static final int PORT = 1066;
    private static byte[] buffer = new byte[256];

    public static void main(String[] args) {
        System.err.println("Starting server on PORT " + PORT);
        try {
            // Initialize the DatagramSocket to listen on the specified port
            dgramSocket = new DatagramSocket(PORT);
        } catch (IOException e) {
            // Handle exceptions during socket initialization
            System.err.println("Unable to start server on PORT " + PORT);
            System.exit(1);
        }

        System.err.println("Server started on PORT " + PORT);

        int numMessages = 0; // Counter for the number of messages received
        do {
            try {
                // Reset the buffer for each message
                buffer = new byte[256];

                // Prepare a DatagramPacket to receive data from the client
                inPacket = new DatagramPacket(buffer, buffer.length);
                dgramSocket.receive(inPacket); // Receive the packet

                // Extract client address, port, and message
                InetAddress clientAddress = inPacket.getAddress();
                int clientPort = inPacket.getPort();
                String messageIn = new String(inPacket.getData(), 0, inPacket.getLength());
                System.out.println("Message received from " + clientAddress + ":" + clientPort + ": " + messageIn);

                // Increment the message counter
                numMessages++;

                // Prepare a response message
                String messageOut = "Your message is " + messageIn + " msg num " + numMessages;

                // Create a DatagramPacket to send the response to the client
                outPacket = new DatagramPacket(messageOut.getBytes(), messageOut.length(), clientAddress, clientPort);
                dgramSocket.send(outPacket); // Send the response
            } catch (Exception e) {
                // Handle exceptions during communication
                e.printStackTrace();
            }
        } while (true); // Keep running indefinitely
    }
}

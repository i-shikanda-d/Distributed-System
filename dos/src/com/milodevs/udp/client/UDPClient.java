package com.milodevs.udp.client;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class UDPClient {
    // Declare the DatagramSocket, port number, buffer, and scanner
    private static DatagramSocket dgramSocket;
    private static final int PORT = 1066;
    private static byte[] buffer = new byte[256];
    private static Scanner scanner;

    public static void main(String[] args) {
        System.err.println("Starting Client");
        try {
            // Initialize the DatagramSocket
            dgramSocket = new DatagramSocket();
        } catch (IOException e) {
            // Handle exceptions during socket initialization
            e.printStackTrace();
        }

        do {
            try {
                // Reset the buffer for each message
                buffer = new byte[256];
                scanner = new Scanner(System.in);

                // Prompt the user for a message
                System.out.print("Enter message: ");
                String message = scanner.nextLine();

                // Exit the loop if the user types "q"
                if (message.equals("q")) {
                    System.out.println("Exiting...");
                    break;
                }

                // Get the server address (localhost in this case)
                InetAddress serverAddress = InetAddress.getByName("localhost");

                // Create a DatagramPacket to send the message to the server
                DatagramPacket outPacket = new DatagramPacket(message.getBytes(), message.length(), serverAddress, PORT);
                dgramSocket.send(outPacket); // Send the packet

                // Prepare a DatagramPacket to receive the server's response
                DatagramPacket inPacket = new DatagramPacket(buffer, buffer.length);
                dgramSocket.receive(inPacket); // Receive the response

                // Convert the response to a string and display it
                String response = new String(inPacket.getData(), 0, inPacket.getLength());
                System.out.println("Server says: " + response);
            } catch (Exception e) {
                // Handle exceptions during communication
                e.printStackTrace();
            }
        } while (true); // Keep running until the user exits
    }
}

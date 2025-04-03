package com.milodevs.rmi.alt.client;

import com.milodevs.rmi.alt.MessengerService;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class RMIClient {
    public static Registry registry;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Connecting to remote server...");
        try {
            // Connect to the RMI registry on the default port (1099)
            registry = LocateRegistry.getRegistry();
        } catch (Exception e) {
            // Handle connection failure
            System.err.println("Could not connect to remote server..Closing");
            System.exit(1);
        }

        do {
            try {
                // Look up the remote object in the RMI registry
                MessengerService server = (MessengerService) registry.lookup("MessengerService");

                // Prompt the user for a message to send to the server
                System.out.println("Enter a message to send to the server: ");
                String message = scanner.nextLine();

                // Send the message to the server and receive the response
                String response = server.sendMessage(message);

                // Display the server's response
                System.out.println("Response: " + response);
            } catch (Exception e) {
                // Handle exceptions during communication
                System.err.println("Could not send message.. Closing");
                System.exit(1);
            }
        } while (true); // Keep running until the user exits
    }
}

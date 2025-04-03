package com.milodevs.rmi.alt.server;

import com.milodevs.rmi.alt.MessengerService;
import com.milodevs.rmi.alt.MessengerServiceImpl;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIServer {
    // Declare the MessengerService and its stub
    private static MessengerService messengerService, stub;
    // Define the port number for the RMI registry
    private static final int PORT = 1099;

    public static void main(String[] args) {
        System.err.println("Starting RMI Server...");
        try {
            // Initialize the MessengerService implementation
            messengerService = new MessengerServiceImpl();
            // Export the MessengerService object to make it available for remote calls
            stub = (MessengerService) UnicastRemoteObject.exportObject((MessengerService) messengerService, 0);
            // Create the RMI registry on the specified port
            Registry registry = LocateRegistry.createRegistry(PORT);
            // Bind the stub to the name "MessengerService" in the registry
            registry.rebind("MessengerService", stub);
            System.out.println("RMI Server Started");
        } catch (Exception e) {
            // Print stack trace in case of an exception
            e.printStackTrace();
        }
    }
}

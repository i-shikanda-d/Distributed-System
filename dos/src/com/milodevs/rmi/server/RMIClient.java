package com.milodevs.rmi.server;

import com.milodevs.rmi.Search;
import com.milodevs.rmi.SearchQuery;
import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.*;

public class RMIClient {
    public static void main(String[] args) {
        try {
            // Create an instance of the SearchQuery implementation
            Search obj = new SearchQuery();

            // Start the RMI registry on port 1900
            LocateRegistry.createRegistry(1900);

            // Bind the remote object to a name in the RMI registry
            Naming.rebind("rmi://localhost:1900" + "/hello", obj);
        } catch (MalformedURLException | RemoteException e) {
            // Handle exceptions related to RMI setup
            System.out.println(e);
        }
    }
}
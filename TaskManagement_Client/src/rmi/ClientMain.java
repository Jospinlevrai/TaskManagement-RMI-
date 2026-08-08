/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rmi;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;

/**
 *
 * @author lukog
 */
public class ClientMain  {
     private static ServiceInt service;

    public static ServiceInt getService() throws Exception {
        if (service == null) {
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1099);
            service = (ServiceInt) registry.lookup("Project Server");
        }
        return service;
    }
    
  
    public static void main(String[] args){
        
       
    try{
        
        ServiceInt project = getService();
        
        System.out.println("Connected to server successfully!");
     
    }catch(Exception e){
        e.printStackTrace();
        }
    
    }
    
}

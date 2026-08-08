/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rmi;

import database.DBOperationImpl;
import java.rmi.RemoteException;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
/**
 *
 * @author lukog
 */
public class ServerMain {
    public static void main(String[] args){
      try{

         ServiceInt project = new DBOperationImpl();  
         
     
         Registry registry = LocateRegistry.createRegistry(1099);
         
         registry.rebind("Project Server", project);
         System.out.println("===================================================");
         System.out.println("Server Available                                   ");
         System.out.println("===================================================");
      }catch(RemoteException e) {
            System.out.println("Server failed to start: " + e.getMessage());
        }
      
   
    
    
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.rmi.RemoteException;



/**
 *
 * @author lukog
 */
public interface DBOpro {
     public void updatepro(int task_id,String status)throws RemoteException;
}

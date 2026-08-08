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
public interface DBDeletetask {
    public void deletetask(String task_id)throws RemoteException;
}

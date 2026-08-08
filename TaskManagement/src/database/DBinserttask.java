/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;
import java.rmi.RemoteException;
import model.Task;
import java.time.*;
/**
 *
 * @author lukog
 */
public interface DBinserttask {
    public void inserttask(Task task)throws RemoteException;
}

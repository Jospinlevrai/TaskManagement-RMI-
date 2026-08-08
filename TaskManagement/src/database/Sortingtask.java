/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.rmi.RemoteException;
import model.Task;
import java.util.List;

/**
 *
 * @author lukogo
 */
public interface Sortingtask {
 public List<Task> sortTasksByEmployee(int employeeId)throws RemoteException;   
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.rmi.RemoteException;
import model.Employee;
import java.time.LocalDateTime;

/**
 *
 * @author lukog
 */
public interface DBinsert {
    public void insertoperation(Employee employee)throws RemoteException;
}

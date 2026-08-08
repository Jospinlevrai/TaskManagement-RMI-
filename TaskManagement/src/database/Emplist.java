/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.rmi.RemoteException;
import model.Employee;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author lukog
 */
public interface Emplist {
 public List<Employee> getAllEmployees()throws RemoteException;   
}

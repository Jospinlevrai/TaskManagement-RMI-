/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;


import java.rmi.RemoteException;
import model.Employee;
import java.sql.*;
/**
 *
 * @author lukog
 */
public interface Logincheck {
    public String[] checklogin(String email,String password)throws RemoteException;
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;
import java.sql.*;

/**
 *
 * @author lukog
 */
public class DBConnection {
      Connection con ;
    String url = "jdbc:postgresql://localhost:5432/employeedb";
    String user = "postgres";
    String password = "JOSpin2006";
     public DBConnection() {
        con = getConnection();
    }
    public Connection getConnection(){
        Connection connection=null;
        // Step 1: Load Driver
        try{
            Class.forName("org.postgresql.Driver");
            System.out.println("Driver loaded successfully");
        }catch(ClassNotFoundException cnfe){
            System.out.println("Driver loading failed: "+cnfe.getMessage());
        }
        // Step 2: Establish Connection
        try{
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Database connected successfully");
        }catch(SQLException sqlee){
            System.out.println("Connection failed: "+sqlee.getMessage());
        }
          return connection;
        
    }
}

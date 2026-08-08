/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;


import rmi.ServiceInt;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import model.Task;
import java.sql.*;
import java.time.*;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import model.Employee;
/**
 *
 * @author lukog
 */
public class TaskImpl extends UnicastRemoteObject implements ServiceInt {
    
    
    public TaskImpl() throws RemoteException {
       
    }
    
    @Override
 public void inserttask(Task task)throws RemoteException{ 
       DBConnection dbc = new DBConnection();
       try{
           String query= "INSERT INTO task(title,description,assigned_to,status,due_date,created_at,updated_at) VALUES(?,?,?,?,?,?,?)";
           
           PreparedStatement pst = dbc.con.prepareStatement(query);
           
            
            pst.setString(1, task.getTitle());
            pst.setString(2, task.getDescription());
            pst.setInt(3, task.getAssignedTo());
            pst.setString(4, task.getStatus());
            pst.setDate(5, Date.valueOf(task.getDueDate()));
            pst.setTimestamp(6, Timestamp.valueOf(task.getCreatedAt()));
            pst.setTimestamp(7, Timestamp.valueOf(task.getUpdatedAt()));
            pst.execute();
            
          
           
          
         
           
           
           dbc.con.close();
       }catch(SQLException sqlee){
           System.out.println("Failed Operation:" +sqlee.getMessage());
       }
       
       
   }
   @Override
    public void deletetask(String task_id){
        DBConnection dbc = new DBConnection();
       try{
        String query="DELETE  FROM task where task_id=?";
        PreparedStatement pst = dbc.con.prepareStatement(query);
        pst.setString(1,task_id);
      
          pst.executeUpdate();
         
        dbc.con.close();
       
        
    }catch(SQLException sqlee) {
        
       System.out.println("Delete failed: " + sqlee.getMessage());
    }
}


   @Override
public void updatetask(int task_id, Task task) {
    DBConnection dbc = new DBConnection();
    try {
        String query = "UPDATE task SET title=?, description=?, assigned_to=?, status=?, due_date=?, created_at=?, updated_at=? WHERE task_id=?";

        PreparedStatement pst = dbc.con.prepareStatement(query);

        pst.setString(1, task.getTitle());
        pst.setString(2, task.getDescription());
        pst.setInt(3, task.getAssignedTo());
        pst.setString(4, task.getStatus());
        pst.setDate(5, Date.valueOf(task.getDueDate()));
        pst.setTimestamp(6, Timestamp.valueOf(task.getCreatedAt()));
        pst.setTimestamp(7, Timestamp.valueOf(task.getUpdatedAt()));
        pst.setInt(8, task_id);

        pst.executeUpdate();
        dbc.con.close();

    } catch (SQLException sqlee) {
        System.out.println("Update failed: " + sqlee.getMessage());
    }
}
    
   @Override
     public void selecttask(int task_id){
    DBConnection dbc = new DBConnection();
       try{
           String query = "SELECT * FROM task WHERE task_id=?";
           PreparedStatement pst= dbc.con.prepareStatement(query);
           
           
          pst.setInt(1, task_id);
          
          ResultSet rs = pst.executeQuery();
          
          
          
          while(rs.next()){

                System.out.println("Task ID: "+ rs.getInt("task_id"));

                System.out.println("Title: "+ rs.getString("title"));

                System.out.println("Description: "+ rs.getString("description"));

                System.out.println("Assigned To: "+ rs.getString("assigned_to"));

                System.out.println("Status: "+ rs.getString("status"));

                System.out.println("Due Date: "+ rs.getDate("due_date"));
            }
          dbc.con.close();
          
          
       }catch(SQLException sqlee) {

        System.out.println("Select failed: " + sqlee.getMessage());   
        
    }
        
       
    }
    @Override
     public void updatepro(int task_id,String status){
    DBConnection dbc = new DBConnection();
       try{
           String query = "Update task SET status=?,updated_at=? WHERE task_id=?";
           PreparedStatement pst= dbc.con.prepareStatement(query);
           
           
           
           pst.setString(1,status);
          
           pst.setTimestamp(2,Timestamp.valueOf(LocalDateTime.now()));
           pst.setInt(3,task_id);
           
           pst.executeUpdate();
         
          
          dbc.con.close();
          
          
       }catch(SQLException sqlee) {

        System.out.println("Select failed: " + sqlee.getMessage());   
        
    }
        
       
    }
    @Override
     public List<Task> getAllTasks(){
     
         DBConnection dbc = new DBConnection();
         List<Task> Tasks =  new ArrayList<>();
         
      try {
        String query = "SELECT * FROM task";
        PreparedStatement pst = dbc.con.prepareStatement(query);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            Task task = new Task(
                    rs.getInt("task_id"), rs.getString("title"),
                rs.getString("description"),rs.getInt("assigned_to"),
                rs.getString("status"),
                rs.getDate("due_date").toLocalDate(),
                rs.getTimestamp("created_at").toLocalDateTime(),
                rs.getTimestamp("updated_at").toLocalDateTime()
            );  
            Tasks.add(task);
       
     }
        dbc.con.close();
      } catch (SQLException sqlee) {
        System.out.println("Failed to load tasks: " + sqlee.getMessage());
    }
    return Tasks;
    
}
    @Override
     public List<Task> sortTasksByEmployee(int employeeId) {

    List<Task> tasks = new ArrayList<>();
    DBConnection dbc = new DBConnection();

    try {

        String query = "SELECT * FROM task WHERE assigned_to = ?";

        PreparedStatement pst = dbc.con.prepareStatement(query);
        pst.setInt(1, employeeId);

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {

            Task task = new Task(
                    rs.getInt("task_id"), rs.getString("title"),
                rs.getString("description"), rs.getInt("assigned_to"),
                rs.getString("status"),
                rs.getDate("due_date").toLocalDate(),
                rs.getTimestamp("created_at").toLocalDateTime(),
                rs.getTimestamp("updated_at").toLocalDateTime()
            );

            tasks.add(task);
        }

    } catch (SQLException sqlee) {
        System.out.println("Failed to load tasks: " + sqlee.getMessage());
    }

    return tasks;
}
    @Override
     public int countAllTasks() {
    DBConnection dbc = new DBConnection();
    int count = 0;
    try {
        String query = "SELECT COUNT(*) FROM task";
        PreparedStatement pst = dbc.con.prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            count = rs.getInt(1);
        }
        dbc.con.close();
    } catch (SQLException sqlee) {
        System.out.println("Count failed: " + sqlee.getMessage());
    }
    return count;
}

    @Override
    public int countByStatus(String status) {
    DBConnection dbc = new DBConnection();
    int count = 0;
    try {
        String query = "SELECT COUNT(*) FROM task WHERE status=?";
        PreparedStatement pst = dbc.con.prepareStatement(query);
        pst.setString(1, status);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            count = rs.getInt(1);
        }
        dbc.con.close();
    } catch (SQLException sqlee) {
        System.out.println("Count failed: " + sqlee.getMessage());
    }
    return count;
}

    @Override
    public int countAllEmployees() {
    DBConnection dbc = new DBConnection();
    int count = 0;
    try {
        String query = "SELECT COUNT(*) FROM employee WHERE role='EMPLOYEE'";
        PreparedStatement pst = dbc.con.prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            count = rs.getInt(1);
        }
        dbc.con.close();
    } catch (SQLException sqlee) {
        System.out.println("Count failed: " + sqlee.getMessage());
    }
    return count;
    
}
    @Override
    public int countBytaskEmployee(int empId) {
    DBConnection dbc = new DBConnection();
    int count = 0;
    try {
        String query = "SELECT COUNT(*) FROM task WHERE assigned_to=?";
        PreparedStatement pst = dbc.con.prepareStatement(query);
        pst.setInt(1, empId);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) count = rs.getInt(1);
        dbc.con.close();
    } catch (SQLException sqlee) {
        System.out.println("Count failed: " + sqlee.getMessage());
    }
    return count;
}
    @Override
    public int countBystat(int empId, String status) {
    DBConnection dbc = new DBConnection();
    int count = 0;
    try {
        String query = "SELECT COUNT(*) FROM task WHERE assigned_to=? AND status=?";
        PreparedStatement pst = dbc.con.prepareStatement(query);
        pst.setInt(1, empId);
        pst.setString(2, status);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) count = rs.getInt(1);
        dbc.con.close();
    } catch (SQLException sqlee) {
        System.out.println("Count failed: " + sqlee.getMessage());
    }
    return count;
}

    @Override
    public void insertoperation(Employee employee) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void updateOperation(int ID, Employee employee) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteoperation(int ID) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void selectOperation(int ID) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Employee checklogin(String email, String password) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Employee> getAllEmployees() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
     
     
     
     
     
   
     
    
    
}

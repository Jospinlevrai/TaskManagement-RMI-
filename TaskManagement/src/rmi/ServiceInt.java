/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rmi;
import model.Task;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.*;
import model.Employee;
/**
 *
 * @author lukog
 */
public interface ServiceInt extends Remote {
    public void inserttask(Task task)throws RemoteException;
    
    public void deletetask(String task_id)throws RemoteException;
    
    public void updatetask(int task_id, Task task)throws RemoteException;
    
    public void selecttask(int task_id)throws RemoteException;
    
    public List<Task> getAllTasks()throws RemoteException;
    
     public void updatepro(int task_id,String status)throws RemoteException;
     

    public List<Task> sortTasksByEmployee(int employeeId)throws RemoteException;
    
    public int countAllTasks() throws RemoteException;
    
    //For Employee table
    public int countByStatus(String status)throws RemoteException;
    

    public int countAllEmployees() throws RemoteException;
    
    public int countBytaskEmployee(int empId)throws RemoteException;
    
    public int countBystat(int empId, String status)throws RemoteException; 
    
    
     public void insertoperation(Employee employee) throws RemoteException;

    public void updateOperation(int ID, Employee employee) throws RemoteException;
    
    public void deleteoperation(int ID) throws RemoteException;


    public void selectOperation(int ID) throws RemoteException;
        public Employee checklogin(String email,String password)throws RemoteException;
        

    public List<Employee> getAllEmployees() throws RemoteException;
}


package database;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import model.Employee;
import java.sql.*;
import java.time.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import model.Task;
import rmi.ServiceInt;



public class DBOperationImpl extends UnicastRemoteObject implements ServiceInt {
   
    public DBOperationImpl() throws RemoteException {
    
}
    @Override
   public void insertoperation(Employee employee) throws RemoteException {
    DBConnection dbc = new DBConnection();
    try {
        String query = "INSERT INTO employee(full_name, email, age, created_at, password, role, is_active) "
                     + "VALUES(?,?,?,?,?,?,?)";

        PreparedStatement pst = dbc.con.prepareStatement(query);

        pst.setString(1, employee.getFullName());
        pst.setString(2, employee.getEmail());
        pst.setInt(3, employee.getAge());
        pst.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
        pst.setString(5, employee.getPassword());
        pst.setString(6, employee.getRole());
        pst.setBoolean(7, employee.isActive());
        
        pst.execute();  
           
       
           dbc.con.close();
       }catch(SQLException sqlee){
           System.out.println("Failed Operation:" +sqlee.getMessage());
       }
       
       
   }
   @Override
    public void deleteoperation(int ID)throws RemoteException{
        DBConnection dbc = new DBConnection();
       try{
        String query="DELETE  FROM employee where employee_id=?";
        PreparedStatement pst = dbc.con.prepareStatement(query);
        pst.setInt(1,ID);
      
          pst.executeUpdate();
         
        dbc.con.close();
       
        
    }catch(SQLException sqlee) {
        
       System.out.println("Delete failed: " + sqlee.getMessage());
    }
}


   @Override
    public void updateOperation(int ID,Employee employee)throws RemoteException{
    DBConnection dbc = new DBConnection();
       try{
           String query = "Update employee SET full_name=?, email=?, age=?, created_at=?,password=?,role=?,is_active=? WHERE employee_id=?";
           PreparedStatement pst= dbc.con.prepareStatement(query);
           
        
        pst.setString(1, employee.getFullName());
        pst.setString(2, employee.getEmail());
        pst.setInt(3, employee.getAge());
        pst.setTimestamp(4, Timestamp.valueOf(employee.getCreatedAt()));
        pst.setString(5, employee.getPassword());
        pst.setString(6, employee.getRole());
        pst.setBoolean(7, employee.isActive());
        pst.setInt(8, ID);
        
          
           pst.setInt(8,ID);
          pst.executeUpdate();
         
          
          dbc.con.close();
          
          
       }catch(SQLException sqlee) {

        System.out.println("update failed: " + sqlee.getMessage());   
        
    }
        
       
    }
    
   @Override
     public void selectOperation(int ID)throws RemoteException{
    DBConnection dbc = new DBConnection();
       try{
           String query = "SELECT * FROM employee WHERE employee_id=?";
           PreparedStatement pst= dbc.con.prepareStatement(query);
           
           
          pst.setInt(1, ID);
          ResultSet rs = pst.executeQuery();

        if (rs.next()) {

            System.out.println(
                rs.getInt("employee_id") + " "
                + rs.getString("full_name") + " "
                + rs.getString("role")
            );
        } else {
            System.out.println("Employee not found");
        }

        dbc.con.close();

    } catch (SQLException sqlee) {

        System.out.println("Select failed: " + sqlee.getMessage());
    }
}
        
       
     
   @Override
      public Employee checklogin(String email,String password)throws RemoteException{
    DBConnection dbc = new DBConnection();
    Employee employee = null;
       try{
           String query = "SELECT * FROM employee WHERE email=? AND password=?";

           PreparedStatement pst = dbc.con.prepareStatement(query);
           pst.setString(1, email);
           pst.setString(2, password);
           ResultSet rs = pst.executeQuery();
           
           if(rs.next()){
           employee = new Employee(
           rs.getInt("employee_id"),
           rs.getString("full_name"),
           rs.getString("email"),
           rs.getInt("age"),
           rs.getString("password"),
           rs.getString("role"),
           rs.getBoolean("is_active"),
           rs.getTimestamp("created_at").toLocalDateTime()
           );
           
           }
        
          dbc.con.close();
          
          
       }catch(SQLException sqlee) {

        System.out.println("Select failed: " + sqlee.getMessage());   
        
    }
        return employee;
       
    }
     public List<Employee> getAllEmployees() throws RemoteException{
    DBConnection dbc = new DBConnection();
    List<Employee> employees = new ArrayList<>();
    try {
        String query = "SELECT * FROM employee WHERE role = 'EMPLOYEE'";
        PreparedStatement pst = dbc.con.prepareStatement(query);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            Employee emp = new Employee(
                rs.getInt("employee_id"),
                rs.getString("full_name"),
                rs.getString("email"),
                rs.getInt("age"),
                rs.getString("password"),
                rs.getString("role"),
                rs.getBoolean("is_active"),
                rs.getTimestamp("created_at").toLocalDateTime()
            );
            employees.add(emp);
        }
        dbc.con.close();
    } catch (SQLException sqlee) {
        System.out.println("Failed to load employees: " + sqlee.getMessage());
    }
    return employees;
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
    public void deletetask(String task_id)throws RemoteException{
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
public void updatetask(int task_id, Task task)throws RemoteException {
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
     public void selecttask(int task_id)throws RemoteException{
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
     public void updatepro(int task_id,String status)throws RemoteException{
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
     public List<Task> getAllTasks()throws RemoteException{
     
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
     public List<Task> sortTasksByEmployee(int employeeId)throws RemoteException {

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
     public int countAllTasks() throws RemoteException{
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
    public int countByStatus(String status) throws RemoteException{
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
    public int countAllEmployees()throws RemoteException {
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
    public int countBytaskEmployee(int empId)throws RemoteException {
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
    public int countBystat(int empId, String status) throws RemoteException{
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
     

}
  


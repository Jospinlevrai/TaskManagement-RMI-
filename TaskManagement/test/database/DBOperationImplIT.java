/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package database;

import java.util.List;
import model.Employee;
import model.Task;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author lukog
 */
public class DBOperationImplIT {
    
    public DBOperationImplIT() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of insertoperation method, of class DBOperationImpl.
     */
    @Test
    public void testInsertoperation() throws Exception {
        System.out.println("insertoperation");
        Employee employee = null;
        DBOperationImpl instance = new DBOperationImpl();
        instance.insertoperation(employee);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteoperation method, of class DBOperationImpl.
     */
    @Test
    public void testDeleteoperation() throws Exception {
        System.out.println("deleteoperation");
        int ID = 0;
        DBOperationImpl instance = new DBOperationImpl();
        instance.deleteoperation(ID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateOperation method, of class DBOperationImpl.
     */
    @Test
    public void testUpdateOperation() throws Exception {
        System.out.println("updateOperation");
        int ID = 0;
        Employee employee = null;
        DBOperationImpl instance = new DBOperationImpl();
        instance.updateOperation(ID, employee);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of selectOperation method, of class DBOperationImpl.
     */
    @Test
    public void testSelectOperation() throws Exception {
        System.out.println("selectOperation");
        int ID = 0;
        DBOperationImpl instance = new DBOperationImpl();
        instance.selectOperation(ID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checklogin method, of class DBOperationImpl.
     */
    @Test
    public void testChecklogin() throws Exception {
        System.out.println("checklogin");
        String email = "";
        String password = "";
        DBOperationImpl instance = new DBOperationImpl();
        Employee expResult = null;
        Employee result = instance.checklogin(email, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllEmployees method, of class DBOperationImpl.
     */
    @Test
    public void testGetAllEmployees() throws Exception {
        System.out.println("getAllEmployees");
        DBOperationImpl instance = new DBOperationImpl();
        List<Employee> expResult = null;
        List<Employee> result = instance.getAllEmployees();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of inserttask method, of class DBOperationImpl.
     */
    @Test
    public void testInserttask() throws Exception {
        System.out.println("inserttask");
        Task task = null;
        DBOperationImpl instance = new DBOperationImpl();
        instance.inserttask(task);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deletetask method, of class DBOperationImpl.
     */
    @Test
    public void testDeletetask() throws Exception {
        System.out.println("deletetask");
        String task_id = "";
        DBOperationImpl instance = new DBOperationImpl();
        instance.deletetask(task_id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updatetask method, of class DBOperationImpl.
     */
    @Test
    public void testUpdatetask() throws Exception {
        System.out.println("updatetask");
        int task_id = 0;
        Task task = null;
        DBOperationImpl instance = new DBOperationImpl();
        instance.updatetask(task_id, task);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of selecttask method, of class DBOperationImpl.
     */
    @Test
    public void testSelecttask() throws Exception {
        System.out.println("selecttask");
        int task_id = 0;
        DBOperationImpl instance = new DBOperationImpl();
        instance.selecttask(task_id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updatepro method, of class DBOperationImpl.
     */
    @Test
    public void testUpdatepro() throws Exception {
        System.out.println("updatepro");
        int task_id = 0;
        String status = "";
        DBOperationImpl instance = new DBOperationImpl();
        instance.updatepro(task_id, status);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllTasks method, of class DBOperationImpl.
     */
    @Test
    public void testGetAllTasks() throws Exception {
        System.out.println("getAllTasks");
        DBOperationImpl instance = new DBOperationImpl();
        List<Task> expResult = null;
        List<Task> result = instance.getAllTasks();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sortTasksByEmployee method, of class DBOperationImpl.
     */
    @Test
    public void testSortTasksByEmployee() throws Exception {
        System.out.println("sortTasksByEmployee");
        int employeeId = 0;
        DBOperationImpl instance = new DBOperationImpl();
        List<Task> expResult = null;
        List<Task> result = instance.sortTasksByEmployee(employeeId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of countAllTasks method, of class DBOperationImpl.
     */
    @Test
    public void testCountAllTasks() throws Exception {
        System.out.println("countAllTasks");
        DBOperationImpl instance = new DBOperationImpl();
        int expResult = 0;
        int result = instance.countAllTasks();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of countByStatus method, of class DBOperationImpl.
     */
    @Test
    public void testCountByStatus() throws Exception {
        System.out.println("countByStatus");
        String status = "";
        DBOperationImpl instance = new DBOperationImpl();
        int expResult = 0;
        int result = instance.countByStatus(status);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of countAllEmployees method, of class DBOperationImpl.
     */
    @Test
    public void testCountAllEmployees() throws Exception {
        System.out.println("countAllEmployees");
        DBOperationImpl instance = new DBOperationImpl();
        int expResult = 0;
        int result = instance.countAllEmployees();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of countBytaskEmployee method, of class DBOperationImpl.
     */
    @Test
    public void testCountBytaskEmployee() throws Exception {
        System.out.println("countBytaskEmployee");
        int empId = 0;
        DBOperationImpl instance = new DBOperationImpl();
        int expResult = 0;
        int result = instance.countBytaskEmployee(empId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of countBystat method, of class DBOperationImpl.
     */
    @Test
    public void testCountBystat() throws Exception {
        System.out.println("countBystat");
        int empId = 0;
        String status = "";
        DBOperationImpl instance = new DBOperationImpl();
        int expResult = 0;
        int result = instance.countBystat(empId, status);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

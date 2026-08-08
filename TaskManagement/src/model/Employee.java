// NEW VERSION
package model;
import java.time.LocalDateTime;
import java.io.Serializable;

public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    private int employeeId;
    private String fullName;
    private String email;
    private int age;
    private String password;
    private String role;
    private boolean isActive;
    private LocalDateTime createdAt;

    public Employee(int employeeId, String fullName, String email, int age,
                    String password, String role, boolean isActive, LocalDateTime createdAt) {
        this.employeeId = employeeId;
        this.fullName = fullName;
        this.email = email;
        this.age = age;
        this.password = password;
        this.role = role;
        this.isActive = isActive;
        this.createdAt = createdAt;
    }

    public int getEmployeeId() { 
        return employeeId;
    }
    public void setEmployeeId(int employeeId) { 
        this.employeeId = employeeId;
    }

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) { 
        this.fullName = fullName; 
    }

    public String getEmail() { 
        return email;
    }
    public void setEmail(String email) { 
        this.email = email;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { 
        return role; 
    }
    public void setRole(String role) { this.role = role; }

    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean isActive) { 
        this.isActive = isActive; 
    }

    public LocalDateTime getCreatedAt() { 
        return createdAt; 
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
package model;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;

public class Task implements Serializable {
    private static final long serialVersionUID = 1L;
    private int taskId;
    private String title;
    private String description;
    private int assignedTo;
    private String status;
    private LocalDate dueDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Task(int taskId, String title, String description, int assignedTo,
                String status, LocalDate dueDate, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.taskId      = taskId;
        this.title       = title;
        this.description = description;
        this.assignedTo  = assignedTo; 
        this.status      = status;
        this.dueDate     = dueDate;
        this.createdAt   = createdAt;
        this.updatedAt   = updatedAt;
    }

    public int getTaskId(){ 
        return taskId; }
    public void setTaskId(int taskId){ 
        this.taskId = taskId; }
    
    public String getTitle(){ 
        return title; }
    
    public void setTitle(String title){
        this.title = title; }
    
    public String getDescription(){
        return description; }
    
    public void setDescription(String description) {
        this.description = description; }
    
    public int getAssignedTo()  { 
        return assignedTo; }
    
    public void setAssignedTo(int assignedTo){
        this.assignedTo = assignedTo; }
   
    public String getStatus() {
        return status; }
    
    public void setStatus(String status){
        this.status = status; }
    public LocalDate getDueDate(){ 
        return dueDate; }
    
   
    public void setDueDate(LocalDate dueDate)
    { this.dueDate = dueDate; }
   
    public LocalDateTime getCreatedAt(){ 
        return createdAt; }
   
    public void setCreatedAt(LocalDateTime c)
    { this.createdAt = c; }
   
    public LocalDateTime getUpdatedAt()  {
   
        return updatedAt; }
   
    public void setUpdatedAt(LocalDateTime u) {
        this.updatedAt = u; }
}
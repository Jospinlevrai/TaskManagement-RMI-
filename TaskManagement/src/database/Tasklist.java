


package database;

import java.rmi.RemoteException;
import model.Task;
import java.util.List;
import java.util.ArrayList;

public interface Tasklist  {
    public List<Task> getAllTasks()throws RemoteException;
}
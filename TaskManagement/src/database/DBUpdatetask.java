
package database;
import java.rmi.RemoteException;
import model.Task;
import java.time.*;

public interface DBUpdatetask {
    public void updatetask(int task_id, Task task)throws RemoteException;
}

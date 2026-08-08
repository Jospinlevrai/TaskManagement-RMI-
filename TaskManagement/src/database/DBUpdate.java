
package database;

import java.rmi.RemoteException;
import model.Employee;
import java.time.LocalDateTime;

public interface DBUpdate {
    public void updateOperation(int ID, Employee employee)throws RemoteException;
}
   


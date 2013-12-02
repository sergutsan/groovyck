import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RmiService extends Remote {
    public String echo(String s) throws RemoteException;
    public Car giveCar() throws RemoteException;
}
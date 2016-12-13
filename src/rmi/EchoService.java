import java.rmi.Remote;
import java.rmi.RemoteException;

// See also RmiService.java for an old version of this service
public interface EchoService extends Remote {
    public String echo(String s) throws RemoteException;
}

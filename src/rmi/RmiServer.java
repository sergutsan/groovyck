import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RmiServer extends UnicastRemoteObject implements RmiService {

    public RmiServer() throws RemoteException {
	  // IMPORTANT to specify
    }

    @Override
    public String echo(String s) {
	  System.out.println("Replied to some client saying '" + s + "'");
	  return s;
    }

    @Override
    public Car giveCar() throws RemoteException {
	  return new Car();
    }
}
import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
import java.net.*;

public class RmiLauncher {

    public static void main(String args[]) throws RemoteException {
	  RmiLauncher rl = new RmiLauncher();
	  rl.launch();
    }

    private void launch() {
	  //final String hostname = "193.61.29.207";
	  final String hostname = "127.0.0.1";
	  if (System.getSecurityManager() == null && false) {
		System.setSecurityManager(new RMISecurityManager());
		System.out.println("Launched a new security manager.");
	  }
	  try {
		LocateRegistry.createRegistry(1099);
		System.out.println("Registry created.");
		RmiServer server = new RmiServer();
		System.out.println("Server instantiated.");
		Naming.rebind("//" + hostname + "/RmiServer", server);
		System.out.println("Service bound.");
	  } catch (MalformedURLException ex) {
		ex.printStackTrace();
	  } catch (RemoteException ex) {
		ex.printStackTrace();
	  }
    }
}

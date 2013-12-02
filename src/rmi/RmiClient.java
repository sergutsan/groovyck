import java.rmi.*;
import java.net.*;

public class RmiClient {
    public static void main(String[] args) {
	  if (System.getSecurityManager() == null) {
		System.setSecurityManager(new RMISecurityManager());
		System.out.println("New security manager... ready!");
	  }
	  RmiClient rc = new RmiClient();
	  String msg = "Hello Server!";
	  if (args.length > 0) {
		msg = args[0];
	  }
	  rc.runEcho(msg);
    }
    
    private void runEcho(String s) {
	  try {
		RmiService service = (RmiService) Naming.lookup("//127.0.0.1:1099/RmiServer"); // what if name is wrong?
		String echo = service.echo(s);
		System.out.println(echo);
		service.giveCar();
	  } catch (MalformedURLException e) {
		e.printStackTrace();
	  } catch (RemoteException e) {
		e.printStackTrace();
	  } catch (NotBoundException e) {
		System.out.println("Sorry, service is not available.");
	  }
    }
}
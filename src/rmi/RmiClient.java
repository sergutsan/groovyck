import java.rmi.*;
import java.net.*;

public class RmiClient {

    private static final String DEFAULT_MSG = "Hello Server!";
    private static final String DEFAULT_IP  = "127.0.0.1";
    
    public static void main(String[] args) {
	  RmiClient rc = new RmiClient();
	  if (args.length != 2) {
		rc.showUsage();
		return;
	  }
	  if (System.getSecurityManager() == null && false) {
		System.setSecurityManager(new RMISecurityManager());
		System.out.println("New security manager... ready!");
	  }
	  String message = args[1];
	  String address = args[0];
	  rc.runEcho(message, address);
    }
    
    private void runEcho(String msg, final String server) {
	  try {
		// final String server = "127.0.0.1"; // localhost
		// final String server = "193.61.29.207";
		// System.out.println("Starting client... No message provided, using '" + DEFAULT_MSG + "'");
		String lookupAddress = "//" + server + ":1099/RmiServer"; // what if name is wrong?
		RmiService service = (RmiService) Naming.lookup(lookupAddress);
		String echo = service.echo(msg);
		System.out.println("Server (" + server + ") replied: '" + echo + "'");
		service.giveCar();
	  } catch (MalformedURLException e) {
		e.printStackTrace();
	  } catch (RemoteException e) {
		e.printStackTrace();
	  } catch (NotBoundException e) {
		System.out.println("Sorry, service is not available.");
	  }
    }

    private void showUsage() {
	  System.out.println("USAGE: client <address> <message>");
	  System.out.println("(example: client 123.12.45.87 'Hello world!'");
    }
    
}

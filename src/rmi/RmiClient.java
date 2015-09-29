import java.rmi.*;
import java.net.*;

public class RmiClient {
    private static final String DEFAULT_MSG = "Hello Server!";
    public static void main(String[] args) {
	  if (System.getSecurityManager() == null) {
		System.setSecurityManager(new RMISecurityManager());
		System.out.println("New security manager... ready!");
	  }
	  RmiClient rc = new RmiClient();
	  String msg = DEFAULT_MSG;
	  if (args.length > 0) {
		msg = args[0];
	  }
	  rc.runEcho(msg);
    }
    
    private void runEcho(String s) {
	  try {
		// final String server = "127.0.0.1"; // localhost
		final String server = "193.61.29.207";
		System.out.println("Starting client... No message provided, using '" + DEFAULT_MSG + "'");
		RmiService service = (RmiService) Naming.lookup("//" + server + ":1099/RmiServer"); // what if name is wrong?
		String echo = service.echo(s);
		System.out.println("Server replied: '" + echo + "'");
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

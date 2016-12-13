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
	  String message = args[1];
	  String address = args[0];
	  rc.runEcho(message, address);
    }
    
    private void runEcho(String msg, final String server) {
	  try {
		String genericAddress = "//" + server + ":1099/";
		String echoServiceAddress = genericAddress + "EchoServer"; // what if name is wrong?
		String boxServiceAddress = genericAddress + "BoxServer"; // what if name is wrong?
		EchoService service = (EchoService) Naming.lookup(echoServiceAddress);
		System.out.println("Sending message to server: " + msg);
		String echo = service.echo(msg);
		System.out.println("Server (" + server + ") replied: '" + echo + "'");
		System.out.print("Getting talking box from server...");
		TalkingBoxService boxService = (TalkingBoxService) Naming.lookup(boxServiceAddress);
		TalkingBox box = boxService.getTalkingBox();
		System.out.println("done.");
		System.out.println("The box says: '" + box.talk() + "'.");
	  } catch (MalformedURLException e) {
		e.printStackTrace();
	  } catch (RemoteException e) {
		e.printStackTrace();
		System.out.println("ERROR: could not execute remote method (incorrect address?).");
	  } catch (NotBoundException e) {
		e.printStackTrace();
		System.out.println("ERROR: service is not available (incorrect name?).");
	  }
    }

    private void showUsage() {
	  System.out.println("USAGE: client <address> <message>");
	  System.out.println("(example: client 123.12.45.87 'Hello world!'");
    }
    
}

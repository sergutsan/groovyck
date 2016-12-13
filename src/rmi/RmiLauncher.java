import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
import java.net.*;

public class RmiLauncher {

    private static final String HOSTNAME = "127.0.0.1";
    
    public static void main(String args[]) throws RemoteException {
	  if (args.length != 1) {
		System.out.println("USAGE: server <text>");
		System.out.println("(example: server 'I am a talking box!')'");
		return;
	  }
	  RmiLauncher rl = new RmiLauncher();
	  rl.launch(args[0]);
    }

    private void launch(String boxMsg) {
	  try {
		LocateRegistry.createRegistry(1099);
		System.out.println("Registry created.");
		EchoServer echoServer = new EchoServer();
		TalkingBoxServer boxServer = new TalkingBoxServer(boxMsg);
		System.out.println("Servers instantiated.");
		String genericAddress = "//" + HOSTNAME + "/";
		String echoServerAddress = genericAddress + "EchoServer";
		Naming.rebind(echoServerAddress, echoServer);
		System.out.println("Echo service bound at " + echoServerAddress);
		String boxServerAddress = genericAddress + "BoxServer";
		Naming.rebind(boxServerAddress, boxServer);
		System.out.println("Talking box service bound at " + boxServerAddress);
	  } catch (MalformedURLException ex) {
		ex.printStackTrace();
	  } catch (RemoteException ex) {
		ex.printStackTrace();
	  }
    }
}

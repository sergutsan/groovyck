import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class TalkingBoxServer extends UnicastRemoteObject implements TalkingBoxService {

    private String msg = null;
    
    public TalkingBoxServer(String msg) throws RemoteException {
	  this.msg = msg;
    }

    @Override
    public TalkingBox getTalkingBox() {
	  TalkingBox result = new TalkingBox(msg);
	  System.out.println("Sent talking box to some client (saying '" + result.talk() + "')...");
	  return result;
    }

    public void changeBoxTalk(String newMsg) {
	  msg = newMsg;
    }

}

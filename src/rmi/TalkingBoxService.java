import java.rmi.Remote;
import java.rmi.RemoteException;

// See also RmiService.java for an old version of this service
public interface TalkingBoxService extends Remote {
    public TalkingBox getTalkingBox() throws RemoteException;
}

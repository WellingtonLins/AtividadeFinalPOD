package ag.ifpb.eventbus.shared.sender;

import ag.ifpb.eventbus.shared.MensagemResultado;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Observer extends Remote{
	void notify(MensagemResultado result) throws RemoteException;
}

package ag.ifpb.eventbus.shared.sender;

import ag.ifpb.eventbus.shared.Mensagem;
import ag.ifpb.eventbus.shared.MensagemResultado;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IReceiver extends Remote {
	void delivery(Mensagem msg) throws RemoteException;
	   MensagemResultado result(String id) throws RemoteException;
}

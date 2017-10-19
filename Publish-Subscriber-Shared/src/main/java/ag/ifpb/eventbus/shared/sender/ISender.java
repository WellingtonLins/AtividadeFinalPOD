package ag.ifpb.eventbus.shared.sender;

import ag.ifpb.eventbus.shared.Mensagem;
import ag.ifpb.eventbus.shared.MensagemResultado;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ISender extends Remote{
	void sendMessage(Mensagem dto) throws RemoteException;
	   MensagemResultado getMessage(String id) throws RemoteException;
}

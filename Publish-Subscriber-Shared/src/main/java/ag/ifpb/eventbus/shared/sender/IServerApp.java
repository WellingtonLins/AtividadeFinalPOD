package ag.ifpb.eventbus.shared.sender;

import ag.ifpb.eventbus.shared.Mensagem;
import ag.ifpb.eventbus.shared.MensagemResultado;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServerApp extends Remote{
	   MensagemResultado print(Mensagem msg) throws RemoteException;
}

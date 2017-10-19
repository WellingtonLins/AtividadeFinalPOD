package ag.ifpb.eventbus.shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface EventBus extends Remote {
    
	void on(String eventName, Listener listener) throws RemoteException;
	void remove(String eventName, Listener listener) throws RemoteException;
	void fire(Mensagem event) throws RemoteException;
}

package ag.ifpb.eventbus.shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Representa o Barramento de Eventos e seu
 * comportamento primario que é de
 * registrar um listener [.on()] e o de
 * notificar o listener para a ocorr�ncia 
 * de um determinado evento [fire()]
 * 
 * @author arigarcia
 *
 */
public interface EventBus extends Remote {
    
	void on(String eventName, Listener listener) throws RemoteException;
	void fire(Mensagem event) throws RemoteException;
}

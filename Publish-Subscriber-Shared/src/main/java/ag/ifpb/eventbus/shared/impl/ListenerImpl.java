package ag.ifpb.eventbus.shared.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import ag.ifpb.eventbus.shared.Listener;
import ag.ifpb.eventbus.shared.Mensagem;

@SuppressWarnings("serial")
public abstract class ListenerImpl extends UnicastRemoteObject implements Listener {

	public ListenerImpl() throws RemoteException {}

	//É abastrato porque a implementação final é quem
	//vai dizer o que este métodos vai fazer
	@Override
	public abstract void onEvent(Mensagem mensagem) throws RemoteException;

}

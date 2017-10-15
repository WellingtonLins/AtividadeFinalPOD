package ag.ifpb.eventbus.server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {
//Esse cara esta dizendo : - "Registry sou um cantidato na porta 10999 , e se caso me procurarem
//    pelo no de EventBus pode me chamar que saberei tratar a requisição
	public static void main(String[] args) throws RemoteException, AlreadyBoundException {
		//
		System.out.println("Iniciando o EventBus - server");
		//
		Registry registry = LocateRegistry.createRegistry(10999);
		registry.bind("EventBus", new EventBusServer());
	}
}

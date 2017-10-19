package ag.ifpb.eventbus.teste;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import ag.ifpb.eventbus.shared.EventBus;
import ag.ifpb.eventbus.shared.Listener;
import ag.ifpb.eventbus.shared.Mensagem;

public class EventBusClient implements EventBus {

    private final Registry registry;
    private final EventBus eventBus;

    public EventBusClient() throws RemoteException, NotBoundException {
        this.registry = LocateRegistry.getRegistry(10990);
        eventBus = (EventBus) registry.lookup("Sender");
    }

    @Override
    public void on(String eventName, Listener listener) throws RemoteException {

        eventBus.on(eventName, listener);

    }

    @Override
    public void remove(String eventName, Listener listener) throws RemoteException {

        eventBus.remove(eventName, listener);

    }

    @Override
    public void fire(Mensagem mensagem) throws RemoteException {

        eventBus.fire(mensagem);

    }

}
package ag.ifpb.eventbus.shared.impl;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import ag.ifpb.eventbus.shared.EventBus;
import ag.ifpb.eventbus.shared.Listener;
import ag.ifpb.eventbus.shared.Mensagem;

public class EventBusClient implements EventBus {

    /* Do lado do cliente, este metodo delega o seu comportamento para uma
     * referência remota do eventBus */
    //Aqui passamos como o parametros o nome do evento e o  Listener(Uma interface) que tem
    //um metodo que recebe um Event
    @Override 
    public void on(String eventName, Listener listener) throws RemoteException {
        //recuperando uma instância do registro
        Registry registry = LocateRegistry.getRegistry(10999);

        try {
            //recuperar uma referência para o EventBusServer
            EventBus eventBus = (EventBus) registry.lookup("EventBus");
            eventBus.on(eventName, listener);//faz o trabalho real

        } catch (NotBoundException | AccessException e) {
            throw new RemoteException("Falha ao "
                    + "tentar vincular o listener ao evento", e);
        }

    }

    /**
     * Do lado do cliente, este método delega o seu comportamento para uma
     * referência remota do eventBus
     */
    @Override
    public void fire(Mensagem mensagem) throws RemoteException {
        //recuperando uma instância do registro
        Registry registry = LocateRegistry.getRegistry(10999);
        try {
            //recuperar uma referência para o EventBusServer
            EventBus eventBus = (EventBus) registry.lookup("EventBus");
            eventBus.fire(mensagem);
            
        } catch (NotBoundException | AccessException e) {
           
            throw new RemoteException("Falha ao disparar um evento", e);
        }
        
    }
    
    }

package ag.ifpb.eventbus.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ag.ifpb.eventbus.shared.EventBus;
import ag.ifpb.eventbus.shared.Listener;
import ag.ifpb.eventbus.shared.Mensagem;
import ag.ifpb.eventbus.shared.MensagemResultado;
import ag.ifpb.eventbus.shared.sender.IServerApp;
import java.util.UUID;

@SuppressWarnings("serial")
public class EventBusServer extends UnicastRemoteObject implements EventBus, IServerApp  {
//Esse Map representa para cada um de seus elementos basicamente um TOPICO e sua lista 
//de inscritos 

    private final Map<String, List<Listener>> listeners = new HashMap<>();

    public EventBusServer() throws RemoteException {
    }//obrigatário para RMI

    @Override
    public void on(String eventName, Listener listener) throws RemoteException {
        //Criando uma lista de Listeners apartir de um nome de Evento passado como parametro
        List<Listener> ls = listeners.get(eventName);
        if (ls == null) {//na primeira vez não há listeners
            ls = new ArrayList<>();
            listeners.put(eventName, ls);
        }
        //
        ls.add(listener);
    }

    @Override
    public void remove(String eventName, Listener listener) throws RemoteException {
        //Criando uma lista de Listeners apartir de um nome de Evento passado como parametro
        List<Listener> ls = listeners.get(eventName);
        if (ls == null) {//na primeira vez não há listeners
            ls = new ArrayList<>();
            listeners.put(eventName, ls);
        }
        //
        ls.remove(listener);
    }

    @Override
    public void fire(Mensagem mensagem) throws RemoteException {
        //Criando uma lista de Listeners apartir de um Evento passado como parametro
        List<Listener> list = listeners.get(mensagem.getGrupo().toString());
        //
        if (list == null) {
            throw new RemoteException("Nenhum listener para este evento");
        }
        //
        for (Listener listener : list) {
            listener.onEvent(mensagem);
        }
    }
 @Override
    public MensagemResultado print(Mensagem msg) throws RemoteException {

        String id = String.valueOf(UUID.randomUUID());
        MensagemResultado result = new MensagemResultado(msg.getId(), id, msg.getConteudo(),msg.getOrigem(),msg.getGrupo());
        //
        System.out.println(msg);
        //
        return result;

    }
}

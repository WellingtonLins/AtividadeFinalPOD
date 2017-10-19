package ifpb.receiver.pull;

import ag.ifpb.eventbus.shared.EventBus;
import ag.ifpb.eventbus.shared.EventBusClient;
import ag.ifpb.eventbus.shared.Mensagem;
import ag.ifpb.eventbus.shared.MensagemResultado;
import ag.ifpb.eventbus.shared.sender.IReceiver;
import ag.ifpb.eventbus.shared.sender.IServerApp;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Receiver extends UnicastRemoteObject implements IReceiver {

    private final ResponseMessageRepository repository;

    protected Receiver(ResponseMessageRepository repository) throws RemoteException {
        this.repository = repository;
    }

    @Override
    public void delivery(Mensagem msg) throws RemoteException {
        //
        System.out.println("Receiver ==> Recebendo mensagem ,encaminhando para o server.");
        //
        Registry registry = LocateRegistry.getRegistry(10992);//servidr final
        try {
            IServerApp serverApp = (IServerApp) registry.lookup("EventBus");
            
            MensagemResultado result = serverApp.print(msg);
            GerenciadorDeMensagem.encaminhar(result.getGrupo());
            EventBus e = new EventBusClient();
            e.fire(msg);
            repository.add(result);
            
        } catch (NotBoundException | AccessException e) {
            e.printStackTrace();
            System.out.println("Problemas no Receiver");
            throw new RuntimeException("Foi mal!!");
        }

    }

    @Override
    public MensagemResultado result(String id) throws RemoteException {
        MensagemResultado messageResult = repository.get(id);

        repository.remove(messageResult);

        return messageResult;
    }

}

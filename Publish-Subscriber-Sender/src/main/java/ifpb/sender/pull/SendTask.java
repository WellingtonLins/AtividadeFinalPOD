package ifpb.sender.pull;


import ag.ifpb.eventbus.shared.Mensagem;
import ag.ifpb.eventbus.shared.sender.IReceiver;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.TimerTask;

import ifpb.sender.pull.repositories.MessageRepository;
import ifpb.sender.pull.repositories.SendedMessageRepository;

public class SendTask extends TimerTask {

    private final MessageRepository repository;
    private final SendedMessageRepository sendedRepository;

    public SendTask(MessageRepository repository, SendedMessageRepository sendedRepository) {
        this.repository = repository;
        this.sendedRepository = sendedRepository;
    }

    @Override
    public void run() {
        //
        try {
            //localizar as mensagens para serem enviadas
            List<Mensagem> list = repository.list();
            //checar se existe mensagem
            if (list.size() > 0) {
                Registry registry = LocateRegistry.getRegistry(10991);
                //fazer o lookup
                IReceiver receiver = (IReceiver) registry.lookup("Receiver");
                //
                for (int i = 0; i < list.size(); i++) {
                    //
                    Mensagem m = list.get(i);
                    //
                    try {
                        receiver.delivery(m);
                        repository.remove(m);
                        sendedRepository.add(m);
                        break;
                    } catch (RemoteException e) {
                      System.out.println("SendTask ==> Erro no loop For,Servidor remoto mestre não esta Operando");
                    }
                }
            }
        } catch (RemoteException | NotBoundException e) {
            System.err.println("Erro no SendTask Servidor Receiver não esta Operando");
        }

    }

}

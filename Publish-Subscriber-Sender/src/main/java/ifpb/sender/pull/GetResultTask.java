package ifpb.sender.pull;

import ag.ifpb.eventbus.shared.Mensagem;
import ag.ifpb.eventbus.shared.MensagemResultado;
import ag.ifpb.eventbus.shared.sender.IReceiver;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import ifpb.sender.pull.repositories.MessageResultRepository;
import ifpb.sender.pull.repositories.SendedMessageRepository;

public class GetResultTask extends TimerTask {

    private final SendedMessageRepository repository;
    private final MessageResultRepository resultRepository;

    public GetResultTask(SendedMessageRepository rep, MessageResultRepository result) {
        this.resultRepository = result;
        this.repository = rep;
    }

    @Override
    public void run() {
        //
        System.out.println("GetResultTask ==> Executando tarefa de obter o resultado.");
        try {
            //
            List<Mensagem> messages = repository.list();
            //
            IReceiver receiver;
            if (messages.size() > 0) {
                Registry registry = LocateRegistry.getRegistry(10991);
                receiver = (IReceiver) registry.lookup("Receiver");
            } else {
                return;
            }
            //
            List<Mensagem> messagesCopy = new ArrayList<>(messages.size());
            messagesCopy.addAll(messages);
            //
            for (int i = 0; i < messagesCopy.size(); i++) {
                Mensagem msg = messagesCopy.get(i);
                MensagemResultado result = receiver.result(msg.getId());
                if (result != null) {
                    resultRepository.add(result);
                    repository.remove(msg);
                }
            }
        } catch (NotBoundException | IOException e) {
            System.out.println("Erro no TaskManager" + e);
        }

    }

}

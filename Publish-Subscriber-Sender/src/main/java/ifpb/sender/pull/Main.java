package ifpb.sender.pull;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import ifpb.sender.pull.repositories.MessageRepository;
import ifpb.sender.pull.repositories.MessageResultRepository;
import ifpb.sender.pull.repositories.SendedMessageRepository;
import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

public class Main {

    public static void main(String[] args) throws AccessException, RemoteException, AlreadyBoundException {
       
        System.out.println("Inicializado o serviço de Sender");
        //inicializar o repositorio
        MessageRepository repository = new MessageRepository();
        SendedMessageRepository sendedMessageRepository = new SendedMessageRepository();
        MessageResultRepository resultRepository = new MessageResultRepository();
        //inicializar o gerenciador de tarefas
        TaskManager.runTask(repository, sendedMessageRepository, resultRepository);
        //inicializar o serviçoo para client app
        Registry registry = LocateRegistry.createRegistry(10990);
        registry.bind("Sender", new SenderImpl(repository, resultRepository));
    }
}

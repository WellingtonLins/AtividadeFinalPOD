package ifpb.sender.pull;

import java.util.Timer;

import ifpb.sender.pull.repositories.MessageRepository;
import ifpb.sender.pull.repositories.MessageResultRepository;
import ifpb.sender.pull.repositories.SendedMessageRepository;

public class TaskManager {

    public static void runTask(MessageRepository repository, SendedMessageRepository sendedRepository,
            MessageResultRepository resultRepository) {
        Timer timer = new Timer();
        timer.schedule(new SendTask(repository, sendedRepository), 3000, 10000);
        timer.schedule(new GetResultTask(sendedRepository, resultRepository), 2000, 2000);
    }

}

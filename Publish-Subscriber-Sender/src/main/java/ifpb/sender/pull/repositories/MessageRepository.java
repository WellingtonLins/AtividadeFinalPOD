package ifpb.sender.pull.repositories;

import ag.ifpb.eventbus.shared.Mensagem;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MessageRepository {

    private List<Mensagem> list = new ArrayList<>();

    public void add(Mensagem msg) {
        System.out.println("MessageRepository ==> Adicionando uma mensagem: " + msg);
        list.add(msg);
    }

    public void remove(Mensagem msg) {
        System.out.println("MessageRepository Removendo uma mensagem: " + msg.getId());
        for (int i = 0; i < list.size(); i++) {
            Mensagem message = list.get(i);
            if (msg.getId().equals(message.getId())) {
                list.remove(i);
                break;
            }
        }
    }

    public List<Mensagem> list() {
        return Collections.unmodifiableList(list);
    }
}

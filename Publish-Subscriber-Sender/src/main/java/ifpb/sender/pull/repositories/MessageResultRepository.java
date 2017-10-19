package ifpb.sender.pull.repositories;

import ag.ifpb.eventbus.shared.MensagemResultado;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MessageResultRepository {

    private List<MensagemResultado> list = new ArrayList<>();

    public void add(MensagemResultado msg) {
        System.out.println("MessageResultRepository ==> Adicionando uma mensagem de resultado: " + msg.getHash());
        list.add(msg);
    }

    public void remove(MensagemResultado msg) {
        System.out.println("MessageResultRepository ==> Removendo uma mensagem de resultado: " + msg.getHash());
        for (int i = 0; i < list.size(); i++) {
            MensagemResultado message = list.get(i);
            if (msg.getHash().equals(message.getHash())) {
                list.remove(i);
                break;
            }
        }
    }

    public List<MensagemResultado> list() {
        return Collections.unmodifiableList(list);
    }

    public MensagemResultado get(String id) {
        for (int i = 0; i < list().size(); i++) {
            MensagemResultado message = list.get(i);
            if (id.equals(message.getId())) {
                return message;
            }
        }
        return null;
    }
}

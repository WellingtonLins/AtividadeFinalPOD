package ifpb.receiver.pull;

import ag.ifpb.eventbus.shared.MensagemResultado;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ResponseMessageRepository {
	private volatile List<MensagemResultado> list = new ArrayList<>();
	
	public void add(MensagemResultado msg) {
		System.out.println("ResponseMessageRepository ==> Adicionando uma mensagem de resposta: " + msg.getId());
		list.add(msg);	
	}
	
	public void remove(MensagemResultado msg){
		System.out.println("ResponseMessageRepository ==> Removendo uma mensagem de resposta: " + msg.getId());
		for (int i = 0; i < list.size(); i++) {
			MensagemResultado message = list.get(i);
			if (msg.getId().equals(message.getId())) {
				list.remove(i); 
				break;
			}
		}
	}

	public List<MensagemResultado> list() {
		return Collections.unmodifiableList(list);
	}
	
	public MensagemResultado get(String id){
		for (MensagemResultado messageResult : list) {
			if (messageResult.getId().equals(id)){
				return messageResult;
			}
		}
		return null;
	}
}

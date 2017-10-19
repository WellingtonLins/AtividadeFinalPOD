package ifpb.sender.pull;

import ag.ifpb.eventbus.shared.Mensagem;
import ag.ifpb.eventbus.shared.MensagemResultado;
import ag.ifpb.eventbus.shared.sender.ISender;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import ifpb.sender.pull.repositories.MessageRepository;
import ifpb.sender.pull.repositories.MessageResultRepository;

@SuppressWarnings("serial")
public class SenderImpl extends UnicastRemoteObject implements ISender {
	private final MessageRepository repository;
	private final MessageResultRepository resultRepository;
	
	public SenderImpl(MessageRepository rep, MessageResultRepository resultRep) throws RemoteException{
		this.repository = rep;
		this.resultRepository = resultRep;
	}

	@Override
	public void sendMessage(Mensagem dto) throws RemoteException{
		//armazenar temporariamente a mensagem
		repository.add(dto);
	}
	
	@Override
	public MensagemResultado getMessage(String id) throws RemoteException {
		//recuperar a mensagem no repositório
		MensagemResultado result = resultRepository.get(id);
		if (result != null) {
                    resultRepository.remove(result);
                }
		return result;
	}

}

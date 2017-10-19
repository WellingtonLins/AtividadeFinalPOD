package ifpb.receiver.pull;

import ag.ifpb.eventbus.shared.EventBusClient;
import ag.ifpb.eventbus.shared.EventBus;
import ag.ifpb.eventbus.shared.Grupo;
import ag.ifpb.eventbus.shared.Mensagem;
import ag.ifpb.eventbus.shared.Usuario;
import ag.ifpb.eventbus.shared.impl.ListenerImpl;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * @Date  11/10/2017 @Time 11:27:31
 * @author Wellington Lins Claudino Duarte   
 * @mail wellingtonlins2013@gmail.com
 */ 
public class GerenciadorDeMensagem {

     public static void encaminhar(Grupo grupo) throws RemoteException, NotBoundException{

        EventBus client = new EventBusClient();
        client.on(grupo.toString(), new ListenerImpl() {
            @Override
            public void onEvent(Mensagem mensagem) throws RemoteException {
                System.out.println(mensagem);
               }
            
        });
    }
    
}
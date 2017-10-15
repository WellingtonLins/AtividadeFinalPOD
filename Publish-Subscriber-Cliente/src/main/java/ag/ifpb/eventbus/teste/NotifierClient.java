package ag.ifpb.eventbus.teste;

import ag.ifpb.eventbus.shared.Grupo;
import java.rmi.RemoteException;

import ag.ifpb.eventbus.shared.Mensagem;
import ag.ifpb.eventbus.shared.Usuario;
import ag.ifpb.eventbus.shared.impl.EventBusClient;
import java.util.Scanner;

public class NotifierClient {

    public static void main(String[] args) throws RemoteException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Bem vindo!\nLogin\nDigite seu nome");
        String nome = scanner.nextLine();
        System.out.println("Digite sua senha");
        String Senha = scanner.nextLine();
        UsuariosDAOFake dao = new UsuariosDAOFake();

        System.out.println("Notificando o cliente!");
        
        Usuario usuario1 = dao.logar(nome, Senha);
        if(usuario1==null){
            System.err.println("Usuario não existe");
            System.exit(0);
        }
         String nomeGrupo = "";
         boolean bandeira =true;
        do {
            System.out.println("Escolha um Grupo:\n"
                    + "Grupo1 - 1\n"
                    + "Grupo2 - 2\n"
                    + "Grupo3 - 3\n");
            int  opcao = scanner.nextInt();
           
            switch (opcao) {
                case 1:
                    nomeGrupo = Grupo.GRUPO1.toString();
                     bandeira =false;
                    break;
                case 2:
                    nomeGrupo = Grupo.GRUPO2.toString();
                     bandeira =false;
                    break;
                case 3:
                    nomeGrupo = Grupo.GRUPO3.toString();
                     bandeira =false;
                    break;
                default:
                    System.out.println("Voce escolheu um opcao que não existe!");
                    break;
            }
            

        } while (bandeira);
        
        GerenciadorDeMensagem.encaminhar(nomeGrupo);
        String msn = "";

        System.out.println("Digite a mensagem");
        do {
            msn = scanner.nextLine();
            Mensagem mensagem = new Mensagem(msn, usuario1, nomeGrupo);
            EventBusClient client = new EventBusClient();
            client.fire(mensagem);

        } while (!msn.equals("end-chat"));
        if (msn.equals("end-chat")) {
            System.out.println("Voce saiu da aplicação");
        }
    }
}

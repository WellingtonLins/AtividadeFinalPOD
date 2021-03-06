package ag.ifpb.eventbus.teste;

import ag.ifpb.eventbus.shared.Grupo;
import java.rmi.RemoteException;

import ag.ifpb.eventbus.shared.Mensagem;
import ag.ifpb.eventbus.shared.Usuario;
import ag.ifpb.eventbus.shared.impl.EventBusClient;
import java.rmi.NotBoundException;
import java.util.Scanner;
import java.util.UUID;

public class NotifierClient {

    public static void main(String[] args) throws RemoteException, NotBoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bem vindo!\nLogin\nDigite seu nome");
        String nome = scanner.nextLine();
        System.out.println("Digite sua senha");
        String Senha = scanner.nextLine();
        UsuariosDAOFake dao = new UsuariosDAOFake();

        System.out.println("Notificando o cliente!");

        Usuario usuario1 = dao.logar(nome, Senha);
        usuario1.setLogado(Boolean.TRUE);
       
        if (usuario1 == null) {
            System.err.println("Usuario não existe");
            System.exit(0);
        }

        Grupo grupo = null;
        boolean bandeira = true;
        do {
            System.out.println("Escolha um Grupo:\n"
                    + "Grupo1 - 1\n"
                    + "Grupo2 - 2\n"
                    + "Grupo3 - 3\n");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    grupo = Grupo.GRUPO1;
                    bandeira = false;
                    break;
                case 2:
                    grupo = Grupo.GRUPO2;
                    bandeira = false;
                    break;
                case 3:
                    grupo = Grupo.GRUPO3;
                    bandeira = false;
                    break;
                default:
                    System.out.println("Voce escolheu um opcao que não existe!");
                    break;
            }

        } while (bandeira);

        GerenciadorDeMensagem.encaminhar(grupo, usuario1);
        String msn = "";

        System.out.println("Digite a mensagem");
        do {
            msn = scanner.nextLine();
//            if (msn.equals("end")) {
//                System.out.println("Voce saiu da aplicação");
//                System.exit(0);
//            }
            if (msn.equals("end")) {

                usuario1.setLogado(false);
            }
                String uuid = String.valueOf(UUID.randomUUID());
           
            Mensagem mensagem = new Mensagem(uuid, msn, usuario1, grupo);
            EventBusClient client = new EventBusClient();
            client.fire(mensagem);
            
        } while (!msn.equals("end"));
        if (msn.equals("end")) {
            
            System.out.println("Voce saiu da aplicação");
        }
    }
}

package ag.ifpb.eventbus.teste;

import ag.ifpb.eventbus.shared.Usuario;
import java.util.Set;
import java.util.TreeSet;

/**
 * @Date 09/10/2017 @Time 14:48:41
 * @author Wellington Lins Claudino Duarte
 * @mail wellingtonlins2013@gmail.com
 */
public class UsuariosDAOFake {

    private Set<Usuario> usariosCadastrados;

    public UsuariosDAOFake() {
        usariosCadastrados = new TreeSet<>();

        Usuario kiko = new Usuario(1, "Kiko", "123");
        Usuario chaves = new Usuario(2, "Chaves", "123");
        Usuario chiquinha = new Usuario(3, "Chiquinha", "123");
        Usuario ionhonho = new Usuario(4, "Ionhonho", "123");
        Usuario paty = new Usuario(5, "Paty", "123");
        Usuario pops = new Usuario(6, "Pops", "123");
        usariosCadastrados.add(chaves);
        usariosCadastrados.add(kiko);
        usariosCadastrados.add(chiquinha);
        usariosCadastrados.add(ionhonho);
        usariosCadastrados.add(paty);
        usariosCadastrados.add(pops);
    }

    public Set<Usuario> todos() {
       return usariosCadastrados;
    }

    public Usuario buscar(Usuario u) {

        if (usariosCadastrados.contains(u)) {
            return u;
        } else {
            return null;
        }
    }

    public Usuario logar(String nome, String senha) {
        boolean flag = false;
        Usuario buscado = null;
        for (Usuario usuario : usariosCadastrados) {

            if ((usuario.getNome().equals(nome)) && (usuario.getSenha().equals(senha))) {
                buscado = usuario;
                flag = true;
                break;
            }
        }

        if (flag == true) {
            return buscado;
        } else {
            return buscado;
        }
    }

    public boolean cadastrar(Usuario usuario) {
        if (usariosCadastrados.add(usuario)) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public long controleId() {
        return (long) usariosCadastrados.size() + 1;
    }
}

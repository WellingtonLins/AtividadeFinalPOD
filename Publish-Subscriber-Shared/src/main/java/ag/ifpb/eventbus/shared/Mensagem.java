package ag.ifpb.eventbus.shared;

import java.io.Serializable;

/**
 * @Date  09/10/2017 @Time 13:46:24
 * @author Wellington Lins Claudino Duarte   
 * @mail wellingtonlins2013@gmail.com
 */ 
public class Mensagem implements Serializable{

    private String id;
    private String conteudo;
    private Usuario origem;
    private Grupo canal;

    public Mensagem() {
    }

    public Mensagem(String id, String conteudo, Usuario origem, Grupo canal) {
        this.id = id;
        this.conteudo = conteudo;
        this.origem = origem;
        this.canal = canal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Usuario getOrigem() {
        return origem;
    }

    public void setOrigem(Usuario origem) {
        this.origem = origem;
    }

    public Grupo getGrupo() {
        return canal;
    }

    public void setGrupo(Grupo canal) {
        this.canal = canal;
    }

  

//    @Override
//    public String toString() {
//        return conteudo + " :: " + origem.getNome() + " ==> " + canal ;
//    }
    @Override
    public String toString() {
        return origem.getNome() + " ==> " + canal + " :: " + conteudo ;
    }
    
}
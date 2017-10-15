package ag.ifpb.eventbus.shared;

import java.io.Serializable;

/**
 * @Date  09/10/2017 @Time 13:46:24
 * @author Wellington Lins Claudino Duarte   
 * @mail wellingtonlins2013@gmail.com
 */ 
public class Mensagem implements Serializable{

    private String conteudo;
    private Usuario origem;
    private String canal;

    public Mensagem() {
    }

    public Mensagem(String conteudo, Usuario origem, String canal) {
        this.conteudo = conteudo;
        this.origem = origem;
        this.canal = canal;
    }

    public String getDestino() {
        return canal;
    }

    public String getConteudo() {
        return conteudo;
    }


    public Usuario getOrigem() {
        return origem;
    }

    @Override
    public String toString() {
        return conteudo + " :: " + origem.getNome() + " ==> " + canal ;
    } 
    
}
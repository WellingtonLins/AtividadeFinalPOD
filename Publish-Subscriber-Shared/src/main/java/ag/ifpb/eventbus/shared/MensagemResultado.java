package ag.ifpb.eventbus.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class MensagemResultado implements Serializable {

    private String id;
    private String hash;
    private String conteudo;
    private Usuario origem;
    private String canal;
    
    public MensagemResultado() {
    }

    public MensagemResultado(String id, String hash, String conteudo, Usuario origem, String canal) {
        this.id = id;
        this.hash = hash;
        this.conteudo = conteudo;
        this.origem = origem;
        this.canal = canal;
    }

 


    public String getId() {
        return id;
    }

    public String getHash() {
        return hash;
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

    public String getCanal() {
        return canal;
    }

    public void setCanal(String canal) {
        this.canal = canal;
    }

    @Override
    public String toString() {
        return "MessageResult{" + "id=" + id + ", hash=" + hash + ", conteudo=" + conteudo + ", origem=" + origem + ", canal=" + canal + '}';
    }


}

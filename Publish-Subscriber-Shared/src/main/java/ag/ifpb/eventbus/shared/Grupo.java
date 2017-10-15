/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ag.ifpb.eventbus.shared;

/**
 *
 * @author wellington
 */
public enum Grupo {
    GRUPO1("Grupo1"),
    GRUPO2("Grupo2"),
    GRUPO3("Grupo3");

    private final String nome;

    private Grupo(String nome) {
        this.nome = nome;
    }

    public boolean equalsNome(String outroNome) {
        return nome.equals(outroNome);
    }

    public String toString() {
        return this.nome;
    }
}

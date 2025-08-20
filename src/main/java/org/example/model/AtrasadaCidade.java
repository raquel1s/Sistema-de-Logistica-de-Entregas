package org.example.model;

public class AtrasadaCidade {

    private String cidade;
    private int atrasadas;

    public AtrasadaCidade(String cidade, int atrasadas) {
        this.cidade = cidade;
        this.atrasadas = atrasadas;
    }

    @Override
    public String toString() {
        return cidade + ": " + atrasadas + " entregas atrasadas.";
    }
}

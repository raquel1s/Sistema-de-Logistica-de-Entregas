package org.example.model;

public class PendentesEstado {

    private String estado;
    private int pendentes;

    public PendentesEstado(String estado, int pendentes) {
        this.estado = estado;
        this.pendentes = pendentes;
    }

    @Override
    public String toString() {
        return estado + ": " + pendentes + " pedidos pendentes.";
    }
}

package org.example.model;

public class ClienteVolume {

    private String cliente;
    private int volumeTotal;

    public ClienteVolume(String cliente, int volumeTotal) {
        this.cliente = cliente;
        this.volumeTotal = volumeTotal;
    }

    public String getCliente() {
        return cliente;
    }

    public int getVolumeTotal() {
        return volumeTotal;
    }

    @Override
    public String toString() {
        return cliente + ": " + volumeTotal + " caixas.";
    }
}

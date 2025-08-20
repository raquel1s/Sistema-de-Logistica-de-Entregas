package org.example.model;

public class EntregasMotorista {

    private String motorista;
    private int totalEntregas;

    public EntregasMotorista(String motorista, int totalEntregas) {
        this.motorista = motorista;
        this.totalEntregas = totalEntregas;
    }

    public String getMotorista() {
        return motorista;
    }

    public int getTotalEntregas() {
        return totalEntregas;
    }

    @Override
    public String toString() {
        return motorista + ": " + totalEntregas + " entregas";
    }
}

package org.example.model;

import java.time.LocalDate;

public class EntregasLista {

    private int id;
    private String cliente;
    private String motorista;
    private LocalDate dataSaida;
    private StatusEntrega status;

    public EntregasLista(int id, String cliente, String motorista, LocalDate dataSaida, StatusEntrega status) {
        this.id = id;
        this.cliente = cliente;
        this.motorista = motorista;
        this.dataSaida = dataSaida;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Id da entrega: " + id +
                "\nCliente: " + cliente +
                "\nMotorista: " + motorista +
                "\nData da Saída: " + dataSaida +
                "\nStatus da entrega: " + status +
                '\n';
    }
}

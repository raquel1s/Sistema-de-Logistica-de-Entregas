package org.example.model;

import java.time.LocalDate;

public class EntregasLista {

    private int id;
    private String cliente;
    private String motorista;
    private LocalDate dataSaida;
    private LocalDate dataEntrega;
    private StatusEntrega status;

    public EntregasLista(int id, String cliente, String motorista, LocalDate dataSaida, LocalDate dataEntrega, StatusEntrega status) {
        this.id = id;
        this.cliente = cliente;
        this.motorista = motorista;
        this.dataSaida = dataSaida;
        this.dataEntrega = dataEntrega;
        this.status = status;
    }

    public EntregasLista(int id, String cliente, String motorista, LocalDate dataSaida, StatusEntrega status) {
        this.id = id;
        this.cliente = cliente;
        this.motorista = motorista;
        this.dataSaida = dataSaida;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getCliente() {
        return cliente;
    }

    public String getMotorista() {
        return motorista;
    }

    public LocalDate getDataSaida() {
        return dataSaida;
    }

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public StatusEntrega getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Id da entrega: " + id +
                "\nCliente: " + cliente +
                "\nMotorista: " + motorista +
                "\nData da Saída: " + dataSaida +
                "\nData da Entrega: " + dataEntrega +
                "\nStatus da entrega: " + status +
                '\n';
    }
}

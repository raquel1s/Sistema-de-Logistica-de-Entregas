package org.example.model;

import java.time.LocalDate;

public class Pedido {

    private int id;
    private int clienteId;
    private LocalDate dataPedido;
    private int volumeM3;
    private double pesoKg;
    private StatusPedido status;

    public Pedido(int id, int clienteId, LocalDate dataPedido, int volumeM3, double pesoKg, StatusPedido status) {
        this.id = id;
        this.clienteId = clienteId;
        this.dataPedido = dataPedido;
        this.volumeM3 = volumeM3;
        this.pesoKg = pesoKg;
        this.status = status;
    }

    public Pedido(int clienteId, LocalDate dataPedido, int volumeM3, double pesoKg, StatusPedido status) {
        this.clienteId = clienteId;
        this.dataPedido = dataPedido;
        this.volumeM3 = volumeM3;
        this.pesoKg = pesoKg;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getClienteId() {
        return clienteId;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public int getVolumeM3() {
        return volumeM3;
    }

    public double getPesoKg() {
        return pesoKg;
    }

    public StatusPedido getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Id do Pedido: " + id +
                "\nId do cliente: " + clienteId +
                "\nData do Pedido: " + dataPedido +
                "\nVolume: " + volumeM3 +
                "\nPeso em Kg: " + pesoKg +
                "\nStatus: " + status +
                '\n';
    }
}

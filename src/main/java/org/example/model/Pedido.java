package org.example.model;

import java.time.LocalDate;

public class Pedido {

    private int id;
    private int pedidoId;
    private LocalDate dataPedido;
    private int volumeM3;
    private double pesoKg;
    private StatusPedido status;

    public Pedido(int id, int pedidoId, LocalDate dataPedido, int volumeM3, double pesoKg, StatusPedido status) {
        this.id = id;
        this.pedidoId = pedidoId;
        this.dataPedido = dataPedido;
        this.volumeM3 = volumeM3;
        this.pesoKg = pesoKg;
        this.status = status;
    }

    public Pedido(int pedidoId, LocalDate dataPedido, int volumeM3, double pesoKg, StatusPedido status) {
        this.pedidoId = pedidoId;
        this.dataPedido = dataPedido;
        this.volumeM3 = volumeM3;
        this.pesoKg = pesoKg;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getPedidoId() {
        return pedidoId;
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
}

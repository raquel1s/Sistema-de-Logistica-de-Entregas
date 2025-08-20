package org.example.model;

import java.time.LocalDate;

public class Entrega {

    private int id;
    private int pedidoId;
    private int motoristaId;
    private LocalDate dataSaida;
    private LocalDate dataEntrega;
    private StatusEntrega status;

    public Entrega(int pedidoId, int motoristaId, LocalDate dataSaida, StatusEntrega status) {
        this.pedidoId = pedidoId;
        this.motoristaId = motoristaId;
        this.dataSaida = dataSaida;
        this.status = status;
    }

    public Entrega(int id, int pedidoId, int motoristaId, StatusEntrega status) {
        this.id = id;
        this.pedidoId = pedidoId;
        this.motoristaId = motoristaId;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getPedidoId() {
        return pedidoId;
    }

    public int getMotoristaId() {
        return motoristaId;
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
}

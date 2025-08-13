package org.example.model;

import java.time.LocalDate;

public class HistoricoEntrega {

    private int id;
    private int entregaId;
    private LocalDate data_evento;
    private String descricao;

    public HistoricoEntrega(int id, int entregaId, LocalDate data_evento, String descricao) {
        this.id = id;
        this.entregaId = entregaId;
        this.data_evento = data_evento;
        this.descricao = descricao;
    }

    public HistoricoEntrega(int entregaId, LocalDate data_evento, String descricao) {
        this.entregaId = entregaId;
        this.data_evento = data_evento;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public int getEntregaId() {
        return entregaId;
    }

    public LocalDate getData_evento() {
        return data_evento;
    }

    public String getDescricao() {
        return descricao;
    }
}

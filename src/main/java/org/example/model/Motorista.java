package org.example.model;

public class Motorista {

    private int id;
    private String nome;
    private String cnh;
    private String veiculo;
    private String cidadeBase;

    public Motorista(int id, String nome, String cnh, String veiculo, String cidadeBase) {
        this.id = id;
        this.nome = nome;
        this.cnh = cnh;
        this.veiculo = veiculo;
        this.cidadeBase = cidadeBase;
    }

    public Motorista(String nome, String cnh, String veiculo, String cidadeBase) {
        this.nome = nome;
        this.cnh = cnh;
        this.veiculo = veiculo;
        this.cidadeBase = cidadeBase;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCnh() {
        return cnh;
    }

    public String getVeiculo() {
        return veiculo;
    }

    public String getCidadeBase() {
        return cidadeBase;
    }
}

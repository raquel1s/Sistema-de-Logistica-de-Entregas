package org.example.model;

public class Cliente {

    private int id;
    private String nome;
    private String cpfCnpj;
    private String endereco;
    private String cidade;
    private String estado;

    public Cliente(int id, String nome, String cpfCnpj, String endereco, String cidade, String estado) {
        this.id = id;
        this.nome = nome;
        this.cpfCnpj = cpfCnpj;
        this.endereco = endereco;
        this.cidade = cidade;
        this.estado = estado;
    }

    public Cliente(String nome, String cpfCnpj, String endereco, String cidade, String estado) {
        this.nome = nome;
        this.cpfCnpj = cpfCnpj;
        this.endereco = endereco;
        this.cidade = cidade;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf_cnpj() {
        return cpfCnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    @Override
    public String toString() {
        return "";
    }
}

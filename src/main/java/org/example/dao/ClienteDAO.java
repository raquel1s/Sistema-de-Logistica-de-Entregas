package org.example.dao;

import org.example.model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteDAO {

    public static Cliente buscarClienteCpgCnpj(String cpfCnpj){
        if(cpfCnpj == null || cpfCnpj.isEmpty() || cpfCnpj.isBlank()){
            return null;
        }

        String sql = "SELECT id, nome, cpf_cnpj, endereco, cidade, estado " +
                "FROM cliente " +
                "WHERE cpf_cnpj = ?";

        int id = 0;
        String nome = "";
        String cpfCnpjNovo = "";
        String endereco = "";
        String cidade = "";
        String estado = "";

        try(Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, cpfCnpj);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                id = rs.getInt("id");
                nome = rs.getString("nome");
                cpfCnpjNovo = rs.getString("cpf_cnpj");
                endereco = rs.getString("endereco");
                cidade = rs.getString("cidade");
                estado = rs.getString("estado");
            }else{
                System.out.println("Usuário não encontrado!");
                return null;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return new Cliente(id, nome, cpfCnpjNovo, endereco, cidade, estado);
    }

    public static void cadastrarCliente(Cliente cliente){
        String sql = "INSERT INTO cliente (nome, cpf_cnpj, endereco, cidade, estado) VALUES (?, ?, ?, ?, ?)";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf_cnpj());
            stmt.setString(3, cliente.getEndereco());
            stmt.setString(4, cliente.getCidade());
            stmt.setString(5, cliente.getEstado());
            stmt.executeUpdate();

            System.out.println("Usuário cadastrado com sucesso!");

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static ArrayList<Cliente> listarClientes() {
        ArrayList<Cliente> clientes = new ArrayList<>();

        String sql = "SELECT id, nome, cpf_cnpj, endereco, cidade, estado FROM cliente";

        try(Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(sql)){

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String cpfCnpj = rs.getString("cpf_cnpj");
                String endereco = rs.getString("endereco");
                String cidade = rs.getString("cidade");
                String estado = rs.getString("estado");

                Cliente cliente = new Cliente(id, nome, cpfCnpj, endereco, cidade, estado);
                clientes.add(cliente);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return clientes;
    }
}

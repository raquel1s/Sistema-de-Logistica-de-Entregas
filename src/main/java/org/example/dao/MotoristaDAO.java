package org.example.dao;

import org.example.model.Motorista;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MotoristaDAO {

    public static void cadastrarMotorista(Motorista motorista){
        String sql = "INSERT INTO motorista (nome, cnh, veiculo, cidade_base) VALUES (?, ?, ?, ?)";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, motorista.getNome());
            stmt.setString(2, motorista.getCnh());
            stmt.setString(3, motorista.getVeiculo());
            stmt.setString(4, motorista.getCidadeBase());
            stmt.executeUpdate();

            System.out.println("Motorista cadastrado com sucesso!");

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static ArrayList<Motorista> listarMotoristas() {
        ArrayList<Motorista> motoristas = new ArrayList<>();

        String sql = "SELECT id, nome, cnh, veiculo, cidade_base FROM motorista";

        try(Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(sql)){

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String cnh = rs.getString("cnh");
                String veiculo = rs.getString("veiculo");
                String cidade_base = rs.getString("cidade_base");

                Motorista motorista = new Motorista(id, nome, cnh, veiculo, cidade_base);
                motoristas.add(motorista);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return motoristas;
    }
}

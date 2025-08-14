package org.example.dao;

import org.example.model.Pedido;
import org.example.model.StatusPedido;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class PedidoDAO {

    public static void criarPedido(Pedido pedido){
        String sql = "INSERT INTO pedido (cliente_id, data_pedido, volume_m3, peso_kg, status_pedido) VALUES (?, ?, ?, ?, ?)";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, pedido.getClienteId());
            stmt.setDate(2, Date.valueOf(pedido.getDataPedido()));
            stmt.setInt(3, pedido.getVolumeM3());
            stmt.setDouble(4, pedido.getPesoKg());
            stmt.setString(5, pedido.getStatus().name());
            stmt.executeUpdate();

            System.out.println("Pedido criado com sucesso!");

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static ArrayList<Pedido> listarPedido(){
        ArrayList<Pedido> pedidos = new ArrayList<>();

        String sql = "SELECT id, cliente_id, data_pedido, volume_m3, peso_kg, status_pedido FROM pedido";

        try(Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(sql)){

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                int clienteId = rs.getInt("cliente_id");
                LocalDate dataPedido = rs.getDate("data_pedido").toLocalDate();
                int volumeM3 = rs.getInt("volume_m3");
                double pesoKg = rs.getDouble("peso_kg");
                StatusPedido status = StatusPedido.valueOf(rs.getString("status_pedido"));

                Pedido pedido = new Pedido(id, clienteId, dataPedido, volumeM3, pesoKg, status);
                pedidos.add(pedido);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return pedidos;
    }
}

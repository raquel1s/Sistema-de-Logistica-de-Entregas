package org.example.dao;

import org.example.model.Entrega;
import org.example.model.EntregasLista;
import org.example.model.StatusEntrega;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class EntregaDAO {

    public static void atribuirPedido(Entrega entrega){
        String sql = "INSERT INTO entrega (pedido_id, motorista_id, data_saida) VALUES (?, ?, ?)";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, entrega.getPedidoId());
            stmt.setInt(2, entrega.getMotoristaId());
            stmt.setDate(3, Date.valueOf(entrega.getDataSaida()));
            stmt.executeUpdate();

            System.out.println("Pedido atribuido ao motorista!");

        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public static void atualizarStatusEntrega(int id, StatusEntrega status){
        String sql = "UPDATE entrega SET status_entrega = ? WHERE id = ?";

        try(Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, status.name());
            stmt.setInt(2, id);
            stmt.executeUpdate();

            System.out.println("Status de entrega atualizado com sucesso!");

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static ArrayList<EntregasLista> listarEntregas(){
        ArrayList<EntregasLista> entregas = new ArrayList<>();

        String sql = "Select e.id" +
                ", c.nome as cliente" +
                ", m.nome as motorista" +
                ", e.data_saida" +
                ", e.status_entrega " +
                "from entrega e " +
                "left join motorista m " +
                "on e.motorista_id = m.id " +
                "left join pedido p " +
                "on e.pedido_id = p.id " +
                "left join cliente c " +
                "on p.cliente_id = c.id";

        try(Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(sql)){

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String cliente = rs.getString("cliente");
                String motorista = rs.getString("motorista");
                LocalDate dataSaida = rs.getDate("data_saida").toLocalDate();
                StatusEntrega status = StatusEntrega.valueOf(rs.getString("status_entrega"));

                EntregasLista entrega = new EntregasLista(id, cliente, motorista, dataSaida, status);
                entregas.add(entrega);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

        return entregas;
    }
}

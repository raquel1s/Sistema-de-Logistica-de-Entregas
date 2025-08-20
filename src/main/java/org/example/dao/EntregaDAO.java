package org.example.dao;

import org.example.model.*;

import java.awt.image.AreaAveragingScaleFilter;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class EntregaDAO {

    public static ArrayList<EntregasMotorista> totalEntregasMotorista(){
        ArrayList<EntregasMotorista> entregasMotoristas = new ArrayList<>();

        String sql = "SELECT m.nome as motorista " +
                ", COUNT(e.id) as entregas " +
                "FROM entrega e " +
                "LEFT JOIN motorista m " +
                "ON e.motorista_id = m.id " +
                "GROUP BY m.id " +
                "ORDER BY entregas DESC";

        try(Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(sql)){

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                String motorista = rs.getString("motorista");
                int totalEntregas = rs.getInt("entregas");

                EntregasMotorista em = new EntregasMotorista(motorista, totalEntregas);
                entregasMotoristas.add(em);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return  entregasMotoristas;
    }

    public static void atribuirPedido(Entrega entrega){
        String sql = "INSERT INTO entrega (pedido_id, motorista_id, data_saida, status_entrega) VALUES (?, ?, ?, ?)";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, entrega.getPedidoId());
            stmt.setInt(2, entrega.getMotoristaId());
            stmt.setDate(3, Date.valueOf(entrega.getDataSaida()));
            stmt.setString(4, entrega.getStatus().name());
            stmt.executeUpdate();

            System.out.println("Pedido atribuido ao motorista!");

        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public static void atualizarStatusEntrega(int id, StatusEntrega status, LocalDate dataEntrega){
        String sql = "UPDATE entrega SET status_entrega = ?, data_entrega = ? WHERE id = ?";

        try(Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, status.name());
            stmt.setDate(2, Date.valueOf(dataEntrega));
            stmt.setInt(3, id);
            stmt.executeUpdate();

            System.out.println("Status de entrega atualizado com sucesso!");

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static Entrega buscarEntregaId(int id){
        String sql = "SELECT id, pedido_id, motorista_id, status_entrega FROM entrega WHERE id = ?";

        int idPedido = 0;
        int pedido = 0;
        int motorista = 0;
        StatusEntrega statusEntrega = null;

        try(Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                idPedido = rs.getInt("id");
                pedido = rs.getInt("pedido_id");
                motorista = rs.getInt("motorista_id");
                statusEntrega = StatusEntrega.valueOf(rs.getString("status_entrega"));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return new Entrega(idPedido, pedido, motorista, statusEntrega);
    }

    public static ArrayList<EntregasLista> listarEntregas(){
        ArrayList<EntregasLista> entregas = new ArrayList<>();

        String sql = "Select e.id" +
                ", c.nome as cliente" +
                ", m.nome as motorista" +
                ", e.data_saida" +
                ", e.data_entrega" +
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

                EntregasLista entrega = null;
                if(rs.getDate("data_entrega") != null){
                    LocalDate dataEntrega = rs.getDate("data_entrega").toLocalDate();
                    entrega = new EntregasLista(id, cliente, motorista, dataSaida, dataEntrega, status);
                }else{
                    entrega = new EntregasLista(id, cliente, motorista, dataSaida, status);
                }

                entregas.add(entrega);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

        return entregas;
    }


    public static ArrayList<AtrasadaCidade> entregasAtrasadasCidade() {
        ArrayList<AtrasadaCidade> atrasadaCidades = new ArrayList<>();

        String sql = "SELECT c.cidade as cidade " +
                ", COUNT(e.id) as entregas " +
                "FROM entrega e " +
                "LEFT JOIN pedido p " +
                "ON e.pedido_id = p.id " +
                "LEFT JOIN cliente c " +
                "ON p.cliente_id = c.id " +
                "WHERE e.status_entrega = 'ATRASADA'" +
                "GROUP BY c.cidade " +
                "ORDER BY entregas DESC";

        try(Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(sql)){

            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                String cidade = rs.getString("cidade");
                int entregas = rs.getInt("entregas");

                AtrasadaCidade ac = new AtrasadaCidade(cidade, entregas);
                atrasadaCidades.add(ac);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return atrasadaCidades;
    }
}

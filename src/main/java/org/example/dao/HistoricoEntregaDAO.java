package org.example.dao;

import org.example.model.HistoricoEntrega;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class HistoricoEntregaDAO {

    public static void registrarEventoEntrega(HistoricoEntrega historicoEntrega){
        String sql = "INSERT INTO historico_entrega (entrega_id, data_evento, descricao) VALUES (?, ?, ?)";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, historicoEntrega.getEntregaId());
            stmt.setDate(2, Date.valueOf(LocalDate.now()));
            stmt.setString(3, historicoEntrega.getDescricao());
            stmt.executeUpdate();

            System.out.println("Evento de entrega registrado!");

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}

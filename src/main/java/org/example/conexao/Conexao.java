package org.example.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

        private static final String URL = "jdbc:mysql://localhost:3306/logistica_entregas?useSSL=false&serverTimezone=UTC";
        private static final String USUARIO = "root";
        private static final String SENHA = "mysqlPW";

        public static Connection conectar() throws SQLException {
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        }
}

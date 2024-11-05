package com.biblioteca.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Conexao {
    public static Connection conect() throws IOException, SQLException {
        String url = "jdbc:mysql://localhost:3306/biblioteca";
        String user = "root";
        String password = "marcelo81";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexao = DriverManager.getConnection(url, user, password);
            return conexao;
        } catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC não encontrado.");
            throw new SQLException("Driver JDBC não encontrado.");
        } catch (SQLException e) {
            System.out.println("Erro de conexão com o banco de dados.");
            throw e;
        }
    }
}

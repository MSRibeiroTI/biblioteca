package com.biblioteca.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.oracle.wls.shaded.org.apache.regexp.recompile;

public class Emprestimos {
    private int id;
    private int id_livro;
    private int id_cliente;
    private String cliente;
    private String titulo;
    private String data_emprestimo;
    private String data_devolucao;
    private String status;
    private Double multa;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_livro() {
        return id_livro;
    }

    public void setId_livro(int id_livro) {
        this.id_livro = id_livro;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getData_emprestimo() {
        return data_emprestimo;
    }

    public void setData_emprestimo(String data_emprestimo) {
        this.data_emprestimo = data_emprestimo;
    }

    public String getData_devolucao() {
        return data_devolucao;
    }

    public void setData_devolucao(String data_devolucao) {
        this.data_devolucao = data_devolucao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setMulta(double multa) {
        this.multa = multa;

    }

    public static List<Emprestimos> listarTodos() throws SQLException, IOException {
        List<Emprestimos> emprestimosList = new ArrayList<>();
        Connection connection = Conexao.conect();
        try {
            String sql = "SELECT e.*, c.nome as cliente, b.titulo FROM emprestimo e JOIN client c on e.id_cliente = c.id_cliente JOIN books b on e.id_livro = b.id_livro WHERE status = 1 ORDER BY id_emprestimo DESC";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Emprestimos emprestimos = new Emprestimos();
                emprestimos.setId(rs.getInt("id_emprestimo"));
                emprestimos.setId_livro(rs.getInt("id_livro"));
                emprestimos.setTitulo(rs.getString("titulo"));
                emprestimos.setId_cliente(rs.getInt("id_cliente"));
                emprestimos.setCliente(rs.getString("cliente"));
                emprestimos.setData_emprestimo(rs.getString("data_emprestimo"));
                emprestimos.setData_devolucao(rs.getString("data_devolucao"));
                emprestimos.setStatus(rs.getString("status"));
                emprestimosList.add(emprestimos);
            }
        } catch (SQLException e) {
            LogGenerator.generateLog(e.getMessage());
        } finally {
            connection.close();
        }
        return emprestimosList;
    }

    public boolean salvar() throws IOException, SQLException {
        System.out.println("id_livro: " + id_livro);
        System.out.println("id_cliente: " + id_cliente);
        System.out.println("data_emprestimo: " + data_emprestimo);
        System.out.println("data_devolucao: " + data_devolucao);
        Connection connection = Conexao.conect();
        try {
            String sql = "INSERT INTO emprestimo (id_livro, id_cliente, data_emprestimo, data_devolucao, status) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id_livro);
            preparedStatement.setInt(2, id_cliente);
            preparedStatement.setDate(3, Date.valueOf(data_emprestimo));
            preparedStatement.setDate(4, Date.valueOf(data_devolucao));
            preparedStatement.setInt(5, 1);
            preparedStatement.executeUpdate();

            String sql2 = "UPDATE books SET disponibilidade = 0 WHERE id_livro = ?";
            PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
            preparedStatement2.setInt(1, id_livro);
            preparedStatement2.executeUpdate();
            return true;
        } catch (SQLException e) {
            LogGenerator.generateLog(e.getMessage());
            return false;
        } finally {
            connection.close();
        }
    }

    public void devolver(int id, int id_livro, double multa, int id_cliente) throws IOException, SQLException {
        Connection connection = Conexao.conect();
        if(multa > 0) {
            Date vencimento = new Date(System.currentTimeMillis());
            String sql = "INSERT INTO multa (id_emprestimo, id_client, valor, vencimento, status) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, id_cliente);
            preparedStatement.setDouble(3, multa);
            preparedStatement.setDate(4, vencimento);
            preparedStatement.setInt(5, 1);
            preparedStatement.executeUpdate();
        }
        try {
            String sql = "UPDATE emprestimo SET status = 0 WHERE id_emprestimo = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            String sql2 = "UPDATE books SET disponibilidade = 1 WHERE id_livro = ?";
            PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
            preparedStatement2.setInt(1, id_livro);
            preparedStatement2.executeUpdate();
        } catch (SQLException e) {
            LogGenerator.generateLog(e.getMessage());
        } finally {
            connection.close();
        }
    }

    public static List<Emprestimos> listarLivrosAtrasados() throws SQLException, IOException {
        List<Emprestimos> livrosEncontrados = new ArrayList<Emprestimos>();
        Connection connection = Conexao.conect();
        try {
            String sql = "SELECT e.*, b.titulo, c.nome, DATE_FORMAT(e.data_devolucao, '%d/%m/%Y') AS data_devolucao_formatada "
                    +
                    "FROM emprestimo e " +
                    "JOIN books b ON e.id_livro = b.id_livro " +
                    "JOIN client c ON e.id_cliente = c.id_cliente " +
                    "WHERE status = 1 AND data_devolucao < CURDATE()";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Emprestimos emprestimo = new Emprestimos();
                emprestimo.setId(rs.getInt("id_emprestimo"));
                emprestimo.setId_livro(rs.getInt("id_livro"));
                emprestimo.setTitulo(rs.getString("titulo"));
                emprestimo.setId_cliente(rs.getInt("id_cliente"));
                emprestimo.setCliente(rs.getString("nome"));
                emprestimo.setData_emprestimo(rs.getString("data_emprestimo"));
                emprestimo.setData_devolucao(rs.getString("data_devolucao_formatada")); // Usar a data formatada
                emprestimo.setStatus(rs.getString("status"));
                livrosEncontrados.add(emprestimo);
            }
        } catch (SQLException e) {
            LogGenerator.generateLog(e.getMessage());
        } finally {
            connection.close();
        }
        return livrosEncontrados;
    }
    
    public static List<Emprestimos> listarHistorico(int id) throws SQLException, IOException{
    	List<Emprestimos> historicoEmprestimos = new ArrayList<Emprestimos>();
    	Connection connection = Conexao.conect();
    	try {
    		String sql = "SELECT e.*, c.nome as cliente, b.titulo FROM emprestimo e JOIN client c on e.id_cliente = c.id_cliente JOIN books b on e.id_livro = b.id_livro WHERE c.id_cliente = ? ORDER BY data_devolucao DESC";
    		
    		PreparedStatement preparedStatement = connection.prepareStatement(sql);
    		preparedStatement.setInt(1, id);
    		ResultSet rs = preparedStatement.executeQuery();
    		while (rs.next()) {
    			Emprestimos emprestimo= new Emprestimos();
    			emprestimo.setTitulo(rs.getString("titulo"));
    			emprestimo.setData_devolucao(rs.getString("data_devolucao"));
    			emprestimo.setStatus(rs.getString("status"));
    			historicoEmprestimos.add(emprestimo);
    		}
    	} catch (SQLException e) {
            LogGenerator.generateLog(e.getMessage());
        } finally {
            connection.close();
        }
    	
    	return historicoEmprestimos; 
    }
    
}






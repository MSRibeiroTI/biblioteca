package com.biblioteca.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Multas {
	private int id;
	private int id_emprestimo;
	private int id_cliente;
	private int id_livro;
	private String valor;
	private String vencimento;
	private String cliente;
	private String titulo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_emprestimo() {
		return id_emprestimo;
	}

	public void setId_emprestimo(int id_emprestimo) {
		this.id_emprestimo = id_emprestimo;
	}

	public int getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valorFormatado) {
		this.valor = valorFormatado;
	}

	public String getVencimento() {
		return vencimento;
	}

	public void setVencimento(String vencimento) {
		this.vencimento = vencimento;
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

	public int getId_livro() {
		return id_livro;
	}

	public void setId_livro(int id_livro) {
		this.id_livro = id_livro;
	}

	public static List<Multas> listarMultas() throws SQLException, IOException {
		List<Multas> multasList = new ArrayList<Multas>();
		Connection connection = Conexao.conect();
		try {
			String sql = "SELECT m.*, c.nome, titulo, b.id_livro FROM multa m JOIN client c on m.id_client = c.id_cliente JOIN emprestimo e on e.id_emprestimo = m.id_emprestimo JOIN books b on b.id_livro = e.id_livro WHERE m.status = 1 ORDER BY vencimento DESC";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Multas multas = new Multas();
				multas.setId(rs.getInt("id"));
				multas.setId_emprestimo(rs.getInt("id_emprestimo"));
				multas.setId_cliente(rs.getInt("id_client"));
				multas.setCliente(rs.getString("nome"));
				NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
				String valorFormatado = nf.format(rs.getDouble("valor"));
				multas.setValor(valorFormatado);
				multas.setVencimento(rs.getString("vencimento"));
				multas.setTitulo(rs.getString("titulo"));
				multas.setId_livro(rs.getInt("id_livro"));
				multasList.add(multas);

			}
		} catch (SQLException e) {
			LogGenerator.generateLog(e.getMessage());
		} finally {
			connection.close();
		}

		return multasList;
	}

	public static String receberMulta(int id) throws SQLException, IOException {
		Connection connection = Conexao.conect();
		try {
			String sql = "UPDATE multa SET status = 0 WHERE id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			LogGenerator.generateLog("Multa paga com sucesso! - " + id);
		} catch (SQLException e) {
			LogGenerator.generateLog(id + e.getMessage());
		} finally {
			connection.close();
		}
		return "Multa paga com sucesso!";
	}

}

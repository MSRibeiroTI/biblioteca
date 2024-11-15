package com.biblioteca.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Books {
	private int id_livro;
	private String titulo;
	private String autor;
	private String editora;
	private String ano_publicacao;
	private String isbn;
	private String genero;
	private String localizacao;
	private int disponivel;

	public int getId_livro() {
		return id_livro;
	}

	public void setId_livro(int id_livro) {
		this.id_livro = id_livro;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public String getAno_publicacao() {
		return ano_publicacao;
	}

	public void setAno_publicacao(String ano_publicacao) {
		this.ano_publicacao = ano_publicacao;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public int getDisponivel() {
		return disponivel;
	}

	public void setDisponivel(int disponivel) {
		this.disponivel = disponivel;
	}

	public static List<Books> listarTodos() throws SQLException, IOException {
		List<Books> booksList = new ArrayList<>();
		Connection connection = Conexao.conect();
		try {
			String sql = "SELECT * FROM books order by titulo ASC";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Books book = new Books();
				book.setId_livro(rs.getInt("id_livro"));
				book.setTitulo(rs.getString("titulo"));
				book.setAutor(rs.getString("autor"));
				book.setEditora(rs.getString("editora"));
				book.setAno_publicacao(rs.getString("ano_publicacao"));
				book.setIsbn(rs.getString("isbn"));
				book.setGenero(rs.getString("genero"));
				book.setLocalizacao(rs.getString("localizacao"));
				book.setDisponivel(rs.getInt("disponibilidade"));
				booksList.add(book);
			}
		} catch (SQLException e) {
			LogGenerator.generateLog(e.getMessage());
		} finally {
			connection.close();
		}
		return booksList;
	}

	public static List<Books> buscarPorNomeOuId(String nomeOuId) throws SQLException, IOException {
		List<Books> livrosEncontrados = new ArrayList<>();
		Connection connection = Conexao.conect();
		try {
			String sql = "SELECT * FROM books WHERE (id_livro = ? OR titulo LIKE ?) AND disponibilidade = 1";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, nomeOuId);
			preparedStatement.setString(2, "%" + nomeOuId + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Books book = new Books();
				book.setId_livro(rs.getInt("id_livro"));
				book.setTitulo(rs.getString("titulo"));
				book.setAutor(rs.getString("autor"));
				book.setEditora(rs.getString("editora"));
				book.setAno_publicacao(rs.getString("ano_publicacao"));
				book.setIsbn(rs.getString("isbn"));
				book.setGenero(rs.getString("genero"));
				book.setLocalizacao(rs.getString("localizacao"));
				book.setDisponivel(rs.getInt("disponibilidade"));
				livrosEncontrados.add(book);
			}
		} catch (SQLException e) {
			LogGenerator.generateLog(e.getMessage());
		} finally {
			connection.close();
		}
		return livrosEncontrados;
	}

	public static void cadastrarLivro(String titulo, String autor, String editora, String genero, String ano_publicacao, String isbn,
			 String localizacao, int quantidade) throws SQLException, IOException {
		Connection connection = Conexao.conect();
		try {
			for (int i = 0; i < quantidade; i++) {
				String sql = "INSERT INTO books (titulo, autor, editora, ano_publicacao, isbn, genero, localizacao, disponibilidade, quantidade) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, titulo);
				preparedStatement.setString(2, autor);
				preparedStatement.setString(3, editora);
				preparedStatement.setString(4, ano_publicacao);
				preparedStatement.setString(5, isbn);
				preparedStatement.setString(6, genero);
				preparedStatement.setString(7, localizacao);
				preparedStatement.setInt(8, 1);
				preparedStatement.setInt(9, 1);
				preparedStatement.executeUpdate();
				LogGenerator.generateLog("Livro cadastrado com sucesso! - " + titulo);
			}
		} catch (SQLException e) {
			LogGenerator.generateLog(e.getMessage());
		} finally {
			connection.close();
		}

	}

	

}

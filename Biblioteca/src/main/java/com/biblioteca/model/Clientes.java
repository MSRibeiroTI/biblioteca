package com.biblioteca.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Clientes {

	private int id_cliente;
	private String nome;
	private String cpf;
	private String telefone;
	private String referencias;
	private String nascimento;
	private int id_endereco;
	private String rua;
	private String numero;
	private String bairro;
	private String cep;
	private String estado;
	private String cidade;

	public int getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}

	public int getId_endereco() {
		return id_endereco;
	}

	public void setId_endereco(int id_endereco) {
		this.id_endereco = id_endereco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getReferencias() {
		return referencias;
	}

	public void setReferencias(String referencia) {
		this.referencias = referencia;
	}

	public String getNascimento() {
		return nascimento;
	}

	public void setNascimento(String nascimento) {
		this.nascimento = nascimento;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public static void deletar(int id) throws SQLException, IOException {
		Connection connection = Conexao.conect();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("DELETE FROM client WHERE id_cliente = ?");
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			LogGenerator.generateLog("Cliente deletado com sucesso! - " + id);
		} catch (SQLException e) {
			LogGenerator.generateLog(id + e.getMessage());
			System.out.println(e);
		} finally {
			connection.close();
		}
	}

	public static List<Clientes> listarTodos() throws SQLException, IOException {
		List<Clientes> clientesList = new ArrayList<>();
		Connection connection = Conexao.conect();

		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM client");

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Clientes cliente = new Clientes();
				cliente.setId_cliente(rs.getInt("id_cliente"));
				cliente.setNome(rs.getString("nome"));
				cliente.setTelefone(rs.getString("telefone"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setReferencias(rs.getString("referencias"));
				clientesList.add(cliente);
			}

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			connection.close();
		}

		return clientesList;
	}
	
	public static List<Clientes> Niver() throws SQLException, IOException {
		Calendar calendar = Calendar.getInstance();
		int mes = calendar.get(Calendar.MONTH) + 1;
		int dia = calendar.get(Calendar.DAY_OF_MONTH);
	
		List<Clientes> clientesList = new ArrayList<>();
	
		Connection connection = Conexao.conect();
	
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM client WHERE MONTH(data_nascimento) = ? AND DAY(data_nascimento) = ?");
			preparedStatement.setInt(1, mes);
			preparedStatement.setInt(2, dia);
	
			ResultSet rs = preparedStatement.executeQuery();
	
			while (rs.next()) {
				Clientes cliente = new Clientes();
				cliente.setNome(rs.getString("nome"));
				clientesList.add(cliente);
			}
	
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			connection.close();
		}
	
		return clientesList;
	}

	public static void cadastrar(String nome, String cpf, String telefone, String referencias, String nascimento,
			String rua, String numero, String cep, String bairro, String cidade, String estado)
			throws SQLException, IOException {
		Connection connection = Conexao.conect();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT id_cliente FROM client WHERE cpf = ?");
			preparedStatement.setString(1, cpf);
			ResultSet rs = preparedStatement.executeQuery();

			if (!rs.next()) {
				preparedStatement = connection
						.prepareStatement(
								"SELECT id_address FROM address WHERE rua = ? AND numero = ? AND cep = ? AND bairro = ? AND cidade = ? AND estado = ?");
				preparedStatement.setString(1, rua);
				preparedStatement.setString(2, numero);
				preparedStatement.setString(3, cep);
				preparedStatement.setString(4, bairro);
				preparedStatement.setString(5, cidade);
				preparedStatement.setString(6, estado);
				ResultSet rs2 = preparedStatement.executeQuery();

				int id_address = 0;
				if (rs2.next()) {
					id_address = rs2.getInt(1);
				} else {

					preparedStatement = connection
							.prepareStatement(
									"INSERT INTO address (rua, numero, cep, bairro, cidade, estado) VALUES (?, ?, ?, ?, ?, ?)");
					preparedStatement.setString(1, rua);
					preparedStatement.setString(2, numero);
					preparedStatement.setString(3, cep);
					preparedStatement.setString(4, bairro);
					preparedStatement.setString(5, cidade);
					preparedStatement.setString(6, estado);
					preparedStatement.executeUpdate();
					ResultSet rs3 = preparedStatement.executeQuery("SELECT MAX(id_address) FROM address");
					if (rs3.next()) {
						id_address = rs3.getInt(1);
					}
				}

				preparedStatement = connection
						.prepareStatement(
								"INSERT INTO client (nome, cpf, telefone, referencias, id_endereco, data_nascimento) VALUES (?, ?, ?, ?, ?, ?)");
				preparedStatement.setString(1, nome);
				preparedStatement.setString(2, cpf);
				preparedStatement.setString(3, telefone);
				preparedStatement.setString(4, referencias);
				preparedStatement.setInt(5, id_address);
				preparedStatement.setString(6, nascimento);
				preparedStatement.executeUpdate();
				LogGenerator.generateLog("Cliente cadastrado com sucesso! - " + nome);
			} else {
				LogGenerator.generateLog("CPF já cadastrado!");
			}
		} catch (SQLException e) {
			LogGenerator.generateLog("SQLException: " + e.getMessage());
			System.out.println(e);
		} finally {
			connection.close();
		}
	}

	public static Clientes getClienteById(int clienteId) throws IOException {
		try {
			Connection connection = Conexao.conect();
			PreparedStatement preparedStatement = connection
					.prepareStatement(
							"SELECT c.*, a.* FROM client c join address a on c.id_endereco = a.id_address WHERE c.id_cliente = ?");
			preparedStatement.setInt(1, clienteId);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				Clientes cliente = new Clientes();
				cliente.setId_cliente(rs.getInt("id_cliente"));
				cliente.setId_endereco(rs.getInt("id_endereco"));
				cliente.setNome(rs.getString("nome"));
				cliente.setTelefone(rs.getString("telefone"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setReferencias(rs.getString("referencias"));
				cliente.setNascimento(rs.getString("data_nascimento"));
				cliente.setRua(rs.getString("rua"));
				cliente.setNumero(rs.getString("numero"));
				cliente.setCep(rs.getString("cep"));
				cliente.setBairro(rs.getString("bairro"));
				cliente.setCidade(rs.getString("cidade"));
				cliente.setEstado(rs.getString("estado"));
				return cliente;
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return null;
	}

	public static void updateCliente(int id_cliente, String nome, String telefone, int id_endereco,
			String referencias, String nascimento,
			String rua, String numero, String cep, String bairro, String cidade, String estado)
			throws SQLException, IOException {
		Connection connection = Conexao.conect();
		try {
			PreparedStatement preparedStatement1 = connection
					.prepareStatement(
							"UPDATE client SET nome = ?, telefone = ?, referencias = ?, data_nascimento = ? WHERE id_cliente = ?");
			preparedStatement1.setString(1, nome);
			preparedStatement1.setString(2, telefone);
			preparedStatement1.setString(3, referencias);
			preparedStatement1.setString(4, nascimento);
			preparedStatement1.setInt(5, id_cliente);
			int linhasAfetadas = preparedStatement1.executeUpdate();
			if (linhasAfetadas == 0) {
				LogGenerator.generateLog("Cliente não encontrado para atualizar! - " + nome);
			} else {
				LogGenerator.generateLog("Cliente atualizado com sucesso! - " + nome);
			}

			PreparedStatement preparedStatement2 = connection
					.prepareStatement(
							"UPDATE address SET rua = ?, numero = ?, cep = ?, bairro = ?, cidade = ?, estado = ? WHERE id_address = ?");
			preparedStatement2.setString(1, rua);
			preparedStatement2.setString(2, numero);
			preparedStatement2.setString(3, cep);
			preparedStatement2.setString(4, bairro);
			preparedStatement2.setString(5, cidade);
			preparedStatement2.setString(6, estado);
			preparedStatement2.setInt(7, id_endereco);
			linhasAfetadas = preparedStatement2.executeUpdate();
			if (linhasAfetadas == 0) {
				LogGenerator.generateLog("Endereço do cliente não encontrado para atualizar! - " + rua);
			} else {
				LogGenerator.generateLog("Endereço do cliente atualizado com sucesso! - " + rua);
			}
		}

		catch (SQLException e) {
			LogGenerator.generateLog("SQLException: " + e.getMessage());
			System.out.println(e);
		} finally {
			connection.close();
		}
	}
}

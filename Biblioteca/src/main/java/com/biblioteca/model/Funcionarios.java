package com.biblioteca.model;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.biblioteca.controller.funcServlet;
import com.biblioteca.model.Conexao;
import com.mysql.cj.protocol.a.authentication.Sha256PasswordPlugin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;

public class Funcionarios {

    private int id_funcionario;
    private String nome;
    private String cpf;
    private String telefone;
    private int id_address;
    private String senha;
    private String funcao;
    private String nascimento;
    private String admissao;
    private String rua;
    private String numero;
    private String bairro;
    private String cep;
    private String estado;
    private String cidade;

    public int getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(int id_funcionario) {
        this.id_funcionario = id_funcionario;
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

    public int getId_address() {
        return id_address;
    }

    public void setId_address(int id_address) {
        this.id_address = id_address;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public String getAdmissao() {
        return admissao;
    }

    public void setAdmissao(String admissao) {
        this.admissao = admissao;
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

    public static Funcionarios login(String cpf, String senha)
            throws IOException, SQLException, NoSuchAlgorithmException {
        MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
        byte[] hash = algorithm.digest(senha.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        senha = sb.toString();
        Connection connection = Conexao.conect();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM funcionario WHERE cpf = ? AND senha = ?");
            preparedStatement.setString(1, cpf);
            preparedStatement.setString(2, senha);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                Funcionarios funcionario = new Funcionarios();
                funcionario.setId_funcionario(rs.getInt("id_funcionario"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setSenha(rs.getString("senha"));

                return funcionario;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            connection.close();
        }
        return null;
    }

    public static List<Funcionarios> listarTodos() throws SQLException, IOException {
        List<Funcionarios> funcionariosList = new ArrayList<>();
        Connection connection = Conexao.conect();

        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM funcionario");

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Funcionarios funcionario = new Funcionarios();
                funcionario.setId_funcionario(rs.getInt("id_funcionario"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setTelefone(rs.getString("telefone"));
                funcionario.setId_address(rs.getInt("id_endereco"));
                funcionario.setSenha(rs.getString("senha"));
                funcionario.setFuncao(rs.getString("funcao"));
                funcionario.setNascimento(rs.getString("data_nascimento"));
                funcionario.setAdmissao(rs.getString("data_admissao"));

                funcionariosList.add(funcionario);
            }

        } catch (SQLException e) {
            LogGenerator.generateLog(e.getMessage());
            System.out.println(e);
        } finally {
            connection.close();
        }

        return funcionariosList;
    }

    public static List<Funcionarios> Niver() throws SQLException, IOException {
        Calendar calendar = Calendar.getInstance();
        int mes = calendar.get(Calendar.MONTH) + 1;
        int dia = calendar.get(Calendar.DAY_OF_MONTH);

        List<Funcionarios> funcionariosList = new ArrayList<>();
        Connection connection = Conexao.conect();

        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(
                            "SELECT * FROM funcionario WHERE MONTH(data_nascimento) = ? AND DAY(data_nascimento) = ?");
            preparedStatement.setInt(1, mes);
            preparedStatement.setInt(2, dia);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Funcionarios funcionario = new Funcionarios();
                funcionario.setNome(rs.getString("nome"));
                funcionariosList.add(funcionario);
            }

        } catch (SQLException e) {
            LogGenerator.generateLog(e.getMessage());
            System.out.println(e);
        } finally {
            connection.close();
        }

        return funcionariosList;
    }

    public static Funcionarios getFuncionarioById(int funcionarioId) throws IOException {
        try {
            Connection connection = Conexao.conect();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(
                            "SELECT f.*, a.* FROM funcionario f join address a on f.id_endereco = a.id_address WHERE f.id_funcionario = ?");
            preparedStatement.setInt(1, funcionarioId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                Funcionarios funcionario = new Funcionarios();
                funcionario.setId_funcionario(rs.getInt("id_funcionario"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setTelefone(rs.getString("telefone"));
                funcionario.setId_address(rs.getInt("id_endereco"));
                funcionario.setSenha(rs.getString("senha"));
                funcionario.setFuncao(rs.getString("funcao"));
                funcionario.setNascimento(rs.getString("data_nascimento"));
                funcionario.setAdmissao(rs.getString("data_admissao"));
                funcionario.setRua(rs.getString("rua"));
                funcionario.setNumero(rs.getString("numero"));
                funcionario.setCep(rs.getString("cep"));
                funcionario.setBairro(rs.getString("bairro"));
                funcionario.setCidade(rs.getString("cidade"));
                funcionario.setEstado(rs.getString("estado"));

                return funcionario;
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;

    }

    public static String cadastrar(String nome, String cpf, String telefone, String funcao, String nascimento,
            String admissao, String senha, String rua, String numero, String cep, String bairro, String cidade,
            String estado)
            throws SQLException, IOException, NoSuchAlgorithmException {
        MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
        byte[] hash = algorithm.digest(senha.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        senha = sb.toString();

        Connection connection = Conexao.conect();

        try {
            PreparedStatement preparedStatement0 = connection
                    .prepareStatement("SELECT cpf FROM funcionario WHERE cpf = ?");
            preparedStatement0.setString(1, cpf);
            ResultSet rs = preparedStatement0.executeQuery();
            if (rs.next()) {
                LogGenerator.generateLog("CPF de funcionário já cadastrado.");

                return ("CPF de funcionário já cadastrado.");
            } else {

                PreparedStatement preparedStatement = connection
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
                int id_address = 0;
                if (rs3.next()) {
                    id_address = rs3.getInt(1);
                }

                PreparedStatement preparedStatement2 = connection
                        .prepareStatement(
                                "INSERT INTO funcionario (nome, cpf, telefone, funcao, data_nascimento, id_endereco, data_admissao, senha) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
                preparedStatement2.setString(1, nome);
                preparedStatement2.setString(2, cpf);
                preparedStatement2.setString(3, telefone);
                preparedStatement2.setString(4, funcao);
                preparedStatement2.setString(5, nascimento);
                preparedStatement2.setInt(6, id_address);
                preparedStatement2.setString(7, admissao);
                preparedStatement2.setString(8, senha);
                preparedStatement2.executeUpdate();
                LogGenerator.generateLog("Funcionário cadastrado com sucesso! :" + nome);
            }
        } catch (SQLException e) {
            System.out.println(e);
            LogGenerator.generateLog("Erro: " + e);
        } finally {
            connection.close();
        }
        return ("Cadastrado com sucesso! : " + nome);
    }

    public static String updateFunc(int id, String nome, String telefone, String funcao, String nascimento,
            String admissao, String senha, String rua, String numero, String cep, String bairro, String cidade,
            String estado, int id_endereco) throws IOException, SQLException {

        Connection connection = Conexao.conect();

        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(
                            "UPDATE funcionario SET nome = ?, telefone = ?, funcao = ?, data_nascimento = ?, data_admissao = ?, senha = ?, id_endereco = ? WHERE id_funcionario = ?");

                            
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, telefone);
            preparedStatement.setString(3, funcao);
            preparedStatement.setString(4, nascimento);
            preparedStatement.setString(5, admissao);
            preparedStatement.setString(6, senha);
            preparedStatement.setInt(7, id_endereco);
            preparedStatement.setInt(8, id);
            
            preparedStatement.executeUpdate();
            LogGenerator.generateLog("Funcionário atualizado com sucesso!" + nome);

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
            preparedStatement2.executeUpdate();
            LogGenerator.generateLog("Endereço atualizado com sucesso!");
        } catch (SQLException e) {
            System.out.println(e);
            LogGenerator.generateLog("Erro: " + e);
            return ("Erro: " + e);
        }

        return ("Funcionário atualizado com sucesso!");
    }

}

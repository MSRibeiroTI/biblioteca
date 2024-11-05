package com.biblioteca.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.biblioteca.model.Clientes;
import com.biblioteca.model.LogGenerator;

public class ClientesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			List<Clientes> lista = Clientes.listarTodos();
			request.setAttribute("lista", lista);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/clientes.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			LogGenerator.generateLog("Erro ao listar clientes: " + e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nomeString = request.getParameter("nome");
		String telefoneString = request.getParameter("telefone");
		String cpfString = request.getParameter("cpf");
		String referenciasString = request.getParameter("referencias");
		String nascimentoString = request.getParameter("nascimento");
		String ruaString = request.getParameter("rua");
		String numeroString = request.getParameter("numero");
		String cepString = request.getParameter("cep");
		String bairroString = request.getParameter("bairro");
		String estadoString = request.getParameter("uf");
		String cidadeString = request.getParameter("cidade");

		try {
			Clientes.cadastrar(nomeString, cpfString, telefoneString, referenciasString, nascimentoString, ruaString, numeroString,
					cepString, bairroString, cidadeString, estadoString);
			doGet(request, response);
		} catch (SQLException e) {
			// tratar o erro
		}

	}}

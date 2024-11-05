package com.biblioteca.controller;

import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.biblioteca.model.Clientes;
import com.biblioteca.model.LogGenerator;

@WebServlet("/cliente")
public class NovoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (ServletException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LogGenerator.generateLog(e.getMessage());
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (ServletException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LogGenerator.generateLog(e.getMessage());
		}
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		String action = request.getParameter("action");
		String page = "";

		try {
			switch (action) {
				case "novo":
					page = "/WEB-INF/views/clientes/novo.jsp";
					break;

				case "editar_cliente":
					int clienteId = Integer.parseInt(request.getParameter("id_cliente"));
					Clientes cliente = Clientes.getClienteById(clienteId);
					request.setAttribute("cliente", cliente);
					page = "/WEB-INF/views/clientes/editar_cliente.jsp";
					break;

				case "atualizar_cliente":
					int id_cliente = Integer.parseInt(request.getParameter("id_cliente"));
					String nome = request.getParameter("nome");
					String telefone = request.getParameter("telefone");
					String referencias = request.getParameter("referencias");
					String nascimento = request.getParameter("nascimento");
					String rua = request.getParameter("rua");
					String numero = request.getParameter("numero");
					String cep = request.getParameter("cep");
					String bairro = request.getParameter("bairro");
					String estado = request.getParameter("uf");
					String cidade = request.getParameter("cidade");
					int id_endereco = Integer.parseInt(request.getParameter("id_endereco"));
					Clientes.updateCliente(id_cliente, nome, telefone, id_endereco, referencias, nascimento, rua,
							numero, cep, bairro, cidade, estado);
					request.setAttribute("mensagem", "Cliente atualizado com sucesso!");
					page = "clientes";
					break;

				case "deletar":
					int id = Integer.parseInt(request.getParameter("id_cliente"));
					Clientes.deletar(id);
					request.setAttribute("mensagem", "Cliente deletado com sucesso!");
					page = "clientes";
					break;

				default:
					page = "/WEB-INF/views/clientes/error.jsp";
					break;
			}
		} catch (NumberFormatException e) {
			LogGenerator.generateLog("NumberFormatException: " + e.getMessage());
			page = "/WEB-INF/views/clientes/error.jsp";
		} catch (SQLException e) {
			LogGenerator.generateLog("SQLException: " + e.getMessage());
			page = "/WEB-INF/views/clientes/error.jsp";
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
}

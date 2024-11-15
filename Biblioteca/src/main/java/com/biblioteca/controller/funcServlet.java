package com.biblioteca.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.biblioteca.model.Funcionarios;
import com.biblioteca.model.LogGenerator;

@WebServlet("/funcionarios")
public class funcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (ServletException | IOException | SQLException e) {
			LogGenerator.generateLog(e.getMessage());
			throw new ServletException(e);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (ServletException | IOException | SQLException e) {
			LogGenerator.generateLog(e.getMessage());
			throw new ServletException(e);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException, NoSuchAlgorithmException {

		String action = request.getParameter("action");

		if (action == null || action.trim().isEmpty()) {
			response.sendRedirect("funcionarios");
			return;
		}

		String page = null;

		try {
			switch (action) {

				case "listar":
					List<Funcionarios> lista = Funcionarios.listarTodos();
					request.setAttribute("lista", lista);
					page = "/WEB-INF/views/funcionarios/funcionarios.jsp";
					break;

				case "novo":
					page = "/WEB-INF/views/funcionarios/novo.jsp";
					break;

				case "cadastrar":
					String nome = request.getParameter("nome");
					String cpf = request.getParameter("cpf");
					String telefone = request.getParameter("telefone");
					String cargo = request.getParameter("cargo");
					String senha = request.getParameter("password");
					String nascimento = request.getParameter("nascimento");
					String admissao = request.getParameter("admissao");
					String rua = request.getParameter("rua");
					String bairro = request.getParameter("bairro");
					String cep = request.getParameter("cep");
					String estado = request.getParameter("uf");
					String cidade = request.getParameter("cidade");
					String numero = request.getParameter("numero");

					if (nome.isEmpty() || cpf.isEmpty() || telefone.isEmpty() || cargo.isEmpty() || senha.isEmpty()
							|| nascimento.isEmpty() || admissao.isEmpty() || rua.isEmpty() || bairro.isEmpty()
							|| cep.isEmpty() || estado.isEmpty() || cidade.isEmpty() || numero.isEmpty()) {
						request.setAttribute("mensagem", "Todos os campos devem ser preenchidos!");
						page = "/WEB-INF/views/funcionarios/novo.jsp";
						break;
					}

					String mensagem = Funcionarios.cadastrar(nome, cpf, telefone, cargo, nascimento, admissao, senha,
							rua, numero, cep, bairro, cidade, estado);

					request.setAttribute("mensagem", mensagem);
					page = "/WEB-INF/views/funcionarios/novo.jsp";
					break;

				case "editar":
					Integer funcId = Integer.parseInt(request.getParameter("id_funcionario"));
					Funcionarios funcionarios = Funcionarios.getFuncionarioById(funcId);
					request.setAttribute("funcionarios", funcionarios);
					page = "/WEB-INF/views/funcionarios/editar.jsp";
					break;

				case "deletar":
					
					int funcIdInteger = Integer.parseInt(request.getParameter("id_funcionario"));
					Funcionarios.deletar(funcIdInteger);
					request.setAttribute("mensagem", "Funcionário deletado com sucesso!");
					List<Funcionarios> lista1 = Funcionarios.listarTodos();
					request.setAttribute("lista", lista1);
					page = "/WEB-INF/views/funcionarios/funcionarios.jsp";
					break;

				case "atualizar":
					String nome1 = request.getParameter("nome");
					String telefone1 = request.getParameter("telefone");
					String cargo1 = request.getParameter("funcao");
					String senha1 = request.getParameter("password");
					String nascimento1 = request.getParameter("nascimento");
					String admissao1 = request.getParameter("admissao");
					String rua1 = request.getParameter("rua");
					String bairro1 = request.getParameter("bairro");
					String cep1 = request.getParameter("cep");
					String estado1 = request.getParameter("uf");
					String cidade1 = request.getParameter("cidade");
					String numero1 = request.getParameter("numero");

					Integer id = Integer.parseInt(request.getParameter("id_funcionario"));
					Integer id2 = Integer.parseInt(request.getParameter("id_endereco"));
					
					if (nome1.isEmpty() || telefone1.isEmpty() || cargo1.isEmpty() || senha1.isEmpty()
							|| nascimento1.isEmpty() || admissao1.isEmpty() || rua1.isEmpty() || bairro1.isEmpty()
							|| cep1.isEmpty() || estado1.isEmpty() || cidade1.isEmpty() || numero1.isEmpty()) {
						request.setAttribute("mensagem", "Todos os campos devem ser preenchidos!");
						page = "/WEB-INF/views/clientes/error.jsp";
						break;
					}
					String mensagem1 = Funcionarios.updateFunc(id, nome1, telefone1, cargo1, nascimento1,
							admissao1, senha1, rua1, numero1, cep1, bairro1,
							cidade1, estado1, id2);

					request.setAttribute("mensagem", mensagem1);

					page = "/WEB-INF/views/funcionarios/editar.jsp";
					break;

				default:
					page = "/WEB-INF/views/clientes/error.jsp";
					break;
			}
		} catch (NumberFormatException e) {
			LogGenerator.generateLog("NumberFormatException: " + e.getMessage());
			page = "/WEB-INF/views/clientes/error.jsp";
		}

		if (page == null) {
			page = "/WEB-INF/views/clientes/error.jsp";
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
}

package com.biblioteca.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.biblioteca.model.Books;
import com.biblioteca.model.Clientes;
import com.biblioteca.model.Emprestimos;
import com.biblioteca.model.LogGenerator;
import com.biblioteca.model.Multas;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/emprestimos")
public class EmpServlet extends HttpServlet {
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
			response.sendRedirect("livros");
			return;
		}

		String page = null;

		try {
			switch (action) {

				case "listar":
					List<Emprestimos> lista = Emprestimos.listarTodos();
					Date today = new Date(System.currentTimeMillis());
					Map<Integer, Double> multas = new HashMap<>();

					for (Emprestimos e : lista) {
						Date data_devolucao = Date.valueOf(e.getData_devolucao());
						if (data_devolucao.before(today)) {
							long dias_atraso = ChronoUnit.DAYS.between(data_devolucao.toLocalDate(),
									today.toLocalDate());
							double multa = dias_atraso * 0.5;
							e.setMulta(multa);
							multas.put(e.getId(), multa);
						} else {
							multas.put(e.getId(), 0.0);
						}
					}

					request.setAttribute("lista", lista);
					request.setAttribute("multas", multas);

					page = "/WEB-INF/views/emprestimos/emprestimos.jsp";
					break;

				case "novo":
					List<Clientes> clientes = Clientes.getClientes();
					request.setAttribute("clientes", clientes);

					page = "/WEB-INF/views/emprestimos/novo.jsp";
					break;

				case "salvar_emprestimo":
					try {

						// Ler o corpo da requisição JSON
						BufferedReader reader = request.getReader();
						StringBuilder sb = new StringBuilder();
						String line;
						while ((line = reader.readLine()) != null) {
							sb.append(line);
						}
						String json = sb.toString();
						System.out.println("JSON recebido: " + json);

						// Converter JSON para objeto EmprestimoRequest
						ObjectMapper mapper = new ObjectMapper();
						EmprestimoRequest emprestimoRequest = mapper.readValue(json, EmprestimoRequest.class);

						// Extrair os dados da requisição
						String clienteId = emprestimoRequest.getCliente();
						String dataEmprestimo = emprestimoRequest.getDataEmprestimo();
						String dataDevolucao = emprestimoRequest.getDataDevolucao();
						List<String> livros = emprestimoRequest.getLivros();

						// Criar um novo objeto Emprestimos
						for (String livroId : livros) {
							Emprestimos emprestimo = new Emprestimos();
							emprestimo.setId_cliente(Integer.parseInt(clienteId));
							emprestimo.setData_emprestimo(dataEmprestimo);
							emprestimo.setData_devolucao(dataDevolucao);
							emprestimo.setId_livro(Integer.parseInt(livroId));

							// Salvar o empréstimo no banco de dados
							boolean sucesso = emprestimo.salvar();

							// Se o empréstimo foi salvo com sucesso, redirecionar para a lista de
							// empréstimos
							if (sucesso) {
								response.setStatus(HttpServletResponse.SC_OK);
								response.getWriter().write("{\"message\": \"Empréstimo salvo com sucesso.\"}");
							} else {
								response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
								response.getWriter().write("{\"message\": \"Erro ao salvar empréstimo.\"}");
							}
						}
					} catch (IOException e) {
						response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
						response.getWriter().write("{\"message\": \"Erro ao processar a requisição.\"}");
						LogGenerator.generateLog(e.getMessage());
						throw new ServletException(e);
					}
					break;

				case "devolver":
					int id = Integer.parseInt(request.getParameter("id_emprestimo"));
					int id_livro = Integer.parseInt(request.getParameter("id_livro"));
					double multa = Double.parseDouble(request.getParameter("multa"));
					int id_cliente = Integer.parseInt(request.getParameter("cliente"));
					Emprestimos emprestimo = new Emprestimos();
					emprestimo.setId(id);
					emprestimo.devolver(id, id_livro, multa, id_cliente);
					
					List<Emprestimos> lista1 = Emprestimos.listarTodos();
					Date today1 = new Date(System.currentTimeMillis());
					Map<Integer, Double> multas1 = new HashMap<>();

					for (Emprestimos e : lista1) {
						Date data_devolucao = Date.valueOf(e.getData_devolucao());
						if (data_devolucao.before(today1)) {
							long dias_atraso = ChronoUnit.DAYS.between(data_devolucao.toLocalDate(),
									today1.toLocalDate());
							double multa1 = dias_atraso * 0.5;
							e.setMulta(multa1);
							multas1.put(e.getId(), multa1);
						} else {
							multas1.put(e.getId(), 0.0);
						}
					}

					request.setAttribute("lista", lista1);
					request.setAttribute("multas", multas1);
					request.setAttribute("mensagem", "Livro devolvido com sucesso!");
					page = "/WEB-INF/views/emprestimos/emprestimos.jsp";
					break;

				case "receber_multa":
					List<Multas> listaMultas = Multas.listarMultas();
					request.setAttribute("listaMultas", listaMultas);

					page = "/WEB-INF/views/emprestimos/receber_multa.jsp";
					break;

				case "receber":
					int id_multa = Integer.parseInt(request.getParameter("id_multa"));
					Multas multa1 = new Multas();
					multa1.setId(id_multa);
					String mensagem = Multas.receberMulta(id_multa);
					List<Multas> listaMultas1 = Multas.listarMultas();
					request.setAttribute("listaMultas", listaMultas1);
					request.setAttribute("mensagem", mensagem);
					page = "/WEB-INF/views/emprestimos/receber_multa.jsp";

					break;

				default:
					page = "/WEB-INF/views/clientes/error.jsp";
					break;
			}
		} catch (Exception e) {
			LogGenerator.generateLog(e.getMessage());
			throw new ServletException(e);
		}

		if (page != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		}

	}
}

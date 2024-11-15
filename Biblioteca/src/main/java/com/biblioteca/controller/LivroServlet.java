package com.biblioteca.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.json.JSONObject;
import org.json.JSONArray;

import com.biblioteca.model.Books;
import com.biblioteca.model.LogGenerator;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/livros")
public class LivroServlet extends HttpServlet {
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
					List<Books> lista = Books.listarTodos();
					request.setAttribute("lista", lista);
					page = "/WEB-INF/views/livros/livros.jsp";
					break;

				case "novo":
					page = "/WEB-INF/views/livros/novo.jsp";
					break;

				case "cadastrar":
					String nome = request.getParameter("titulo");
					String autor = request.getParameter("autor");
					String editora = request.getParameter("editora");
					String genero = request.getParameter("genero");
					String ano_publicacao = request.getParameter("ano");
					String isbn = request.getParameter("isbn");
					String localizacao = request.getParameter("local");
					int quantidade = Integer.parseInt(request.getParameter("quantidade"));

					if (nome != null && autor != null && editora != null && genero != null && ano_publicacao != null &&
							isbn != null && localizacao != null) {
						Books.cadastrarLivro(nome, autor, editora, genero, ano_publicacao, isbn, localizacao, quantidade);
						request.setAttribute("mensagem", "Livro cadastrado com sucesso!");
					} else {
						request.setAttribute("mensagem", "Dados do livro incompletos!");
					}
					page = "/WEB-INF/views/livros/novo.jsp";
					break;

				case "nomeOuId":
					String nomeOuId = request.getParameter("nomeOuId");
					List<Books> livrosEncontrados = Books.buscarPorNomeOuId(nomeOuId);
					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					PrintWriter out = response.getWriter();
				if (!livrosEncontrados.isEmpty()) {
					JSONArray jsonArray = new JSONArray();
					for (Books livro : livrosEncontrados) {
						JSONObject jsonObject = new JSONObject();
						jsonObject.put("id", livro.getId_livro());
						jsonObject.put("titulo", livro.getTitulo());
						jsonObject.put("autor", livro.getAutor()); // Adicione outros campos conforme necessário
						jsonArray.put(jsonObject);
					 } 
				out.print(jsonArray.toString());
				} else { JSONObject jsonObject = new JSONObject(); jsonObject.put("mensagem", "Livro não encontrado!");
				
				out.print(jsonObject.toString());
			}
				out.flush(); break;
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



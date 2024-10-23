package com.biblioteca.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.biblioteca.model.Sistema;

@WebServlet(name = "SistemaServlet", urlPatterns = "/sistemas")
public class SistemaServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Sistema> sistemas = new ArrayList<>();
		
		sistemas.add(new Sistema("Barbosa e Gomes", "Sistema de acompanhamento de obra - Construção Civil - Engenharia", "http://localhost/bg/"));
		sistemas.add(new Sistema("Perfil do Instagram", "Sistema de gerenciamento de postagens pagas no instagram.", "http://localhost/arcoindica/"));
		sistemas.add(new Sistema("SISCERT", "Sistema de gerenciamento e geração de certificados da pós graduação.", "http://localhost/certificados/"));
		
		request.setAttribute("sistemas", sistemas);
		
		
		
		 RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/sistemas.jsp");
	        dispatcher.forward(request, response);
	}
	

}

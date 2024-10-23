package com.biblioteca.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.biblioteca.model.Servico;

@WebServlet(name = "ServicoServlet", urlPatterns = "/servicos")
public class ServicoServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Servico> servicos = new ArrayList<>();

        servicos.add(new Servico("Formatação", "Formatação do sistema opercional, instalação dos drivers, programas básicos, suite LibreOffice", 80.00));
        servicos.add(new Servico("Sistema", "Desenvolvimento de sistemas. (Entre em contato para consultar valores) ", 0.00));
        servicos.add(new Servico("Reset Epson", "Reset da mensagem das almofadas em impressoras Epson Ecotank", 100.00));
        servicos.add(new Servico("Bingo", "Geração de cartelas de bingo personalizadas. 500 unidades/Consulte valores para outras quantidades.", 200.00));
       
        

        request.setAttribute("servicos", servicos);
        

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/servicos.jsp");
        dispatcher.forward(request, response);
    }
}
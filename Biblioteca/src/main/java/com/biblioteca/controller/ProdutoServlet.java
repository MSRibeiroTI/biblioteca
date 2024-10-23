package com.biblioteca.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.biblioteca.model.Produto;

public class ProdutoServlet extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Produto> produtos = new ArrayList<>();

		produtos.add(new Produto(1, "Notebook i5 15ª Gen", "Infomática", 4350.00));
		produtos.add(new Produto(2, "Smartphone S20+", "Telefonia", 3200.00));
		produtos.add(new Produto(3, "Smartwatch", "Eletrônicos", 899.00));
		produtos.add(new Produto(4, "Tablet", "Eletrônicos", 800.00));

		request.setAttribute("produtos", produtos);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/produtos.jsp");
		dispatcher.forward(request, response);
	}

}

package com.biblioteca.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.biblioteca.model.Clientes;
import com.biblioteca.model.Funcionarios;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	List<Clientes> niver_cli = null;
		try {
			niver_cli = Clientes.Niver();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Funcionarios> niver_func = null;
		try {
			niver_func = Funcionarios.Niver();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        request.setAttribute("niver_func", niver_func);
        request.setAttribute("niver_cli", niver_cli);
        request.setAttribute("data",
                java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy")));
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/home.jsp");
        dispatcher.forward(request, response);
    }

}

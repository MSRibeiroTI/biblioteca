package com.biblioteca.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
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
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String usuario = request.getParameter("username");
        String senha = request.getParameter("password");

        try {
            Funcionarios funcionario = Funcionarios.login(usuario, senha);
            if (funcionario != null) {
                HttpSession session = request.getSession();
                session.setAttribute("usuario", funcionario.getNome());
                session.setAttribute("id_func", funcionario.getId_funcionario());
                session.setAttribute("funcao", funcionario.getFuncao());
                request.setAttribute("mensagem", "Bem-vindo " + funcionario.getNome());
                List<Clientes> niver_cli = Clientes.Niver();
                request.setAttribute("niver_cli", niver_cli);
                List<Funcionarios> niver_func = Funcionarios.Niver();
                request.setAttribute("niver_func", niver_func);
                request.setAttribute("data",
                        java.time.LocalDate.now()
                                .format(java.time.format.DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy")));
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/home.jsp");
                dispatcher.forward(request, response);
            } else {
                request.setAttribute("mensagem", "Usuário ou senha inválidos.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/");
                dispatcher.forward(request, response);
            }
        } catch (NoSuchAlgorithmException | IOException | SQLException | ServletException e) {
            e.printStackTrace();
        }
    }
}

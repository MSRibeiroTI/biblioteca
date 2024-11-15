package com.biblioteca.controller;

import java.io.IOException;

import com.biblioteca.model.LogGenerator;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	        HttpSession session = request.getSession(false);
        if (session != null) {
            LogGenerator.generateLog("Usuário fez logout: " + session.getAttribute("usuario"));
            session.invalidate();
        }
        
        response.sendRedirect(request.getContextPath() + "/");
    }

}

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

        <%@ include file="/WEB-INF/views/header.jsp" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Empréstimos</title>
                <link rel="stylesheet" type="text/css" href="css/style.css">
            </head>

            <body>
                <h1>Empréstimos</h1>
                <c:if test="${not empty mensagem}">
                    <div class="alert alert-success"
                        style="background-color: #dff0d8; border: 1px solid #d6e9c6; color: #3c763d; padding: 10px; border-radius: 4px;">
                        ${mensagem}
                    </div>
                </c:if>
                <div class="btn-index">
                    <a class="button" href="home">Voltar</a>
                    <a class="button" href="emprestimos?action=novo">Novo Empréstimo</a>
                </div>
                <div class="tb">
                    <table border="1">
                        <tr>
                            <th>Id</th>
                            <th>Código</th>
                            <th>Título</th>
                            <th>Id Cliente</th>
                            <th>Cliente</th>
                            <th>Data Empréstimo</th>
                            <th>Devolução</th>
                            <th>Multa</th>
                            <th>Ações</th>

                        </tr>
                       <c:forEach var="emprestimo" items="${lista}" varStatus="status">
					    <tr>
					        <td>${emprestimo.id}</td>
					        <td>${emprestimo.id_livro}</td>
					        <td>${emprestimo.titulo}</td>
					        <td>${emprestimo.id_cliente}</td>
					        <td>${emprestimo.cliente}</td>
					        <td>${emprestimo.data_emprestimo}</td>
					        <td>${emprestimo.data_devolucao}</td>
					        <td style="color: ${multas[emprestimo.id] > 0 ? 'red' : ''}">R$ ${multas[emprestimo.id]}</td>
					        <td>
					            <a href="emprestimos?action=devolver&id_emprestimo=${emprestimo.id}&id_livro=${emprestimo.id_livro}&multa=${multas[emprestimo.id]}&cliente=${emprestimo.id_cliente}"
					                onclick="return confirm('Deseja devolver este livro?');">Devolver</a>
					        </td>
					    </tr>
					</c:forEach>


                    </table>
                </div>


                <footer>
                    <p>© 2024 Biblioteca. Todos os direitos reservados. Desenvolvido por Marcelo S Ribeiro -
                        marcelocaricatus@hotmail.com</p>
                </footer>
            </body>

            </html>
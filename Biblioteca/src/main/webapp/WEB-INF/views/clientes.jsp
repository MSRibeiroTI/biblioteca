<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ include file="header.jsp" %>

        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Clientes</title>
            <link rel="stylesheet" type="text/css" href="css/style.css">
        </head>

        <body>
            <h1>Clientes</h1>
            <c:if test="${not empty mensagem}">
                <div class="alert alert-success"
                    style="background-color: #dff0d8; border: 1px solid #d6e9c6; color: #3c763d; padding: 10px; border-radius: 4px;">
                    ${mensagem}
                </div>
            </c:if>
            <div class="btn-index">
                <a class="button" href="home">Voltar</a>
                <a class="button" href="cliente?action=novo">Cadastrar</a>
            </div>
            <div class="tb">
                <table border="1">
                    <tr>
                        <th>ID</th>
                        <th>Nome</th>
                        <th>Telefone</th>
                        <th>Referências</th>
                        <th>Opções</th>
                    </tr>
                    <c:forEach items="${lista}" var="cliente">
                        <tr>
                            <td>${cliente.id_cliente}</td>
                            <td>${cliente.nome}</td>
                            <td>${cliente.telefone}</td>
                            <td>${cliente.referencias}</td>
                            <td>
                                <a href="cliente?action=editar_cliente&id_cliente=${cliente.id_cliente}">Editar</a>
                                <a href="cliente?action=deletar&id_cliente=${cliente.id_cliente}">Excluir</a>
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
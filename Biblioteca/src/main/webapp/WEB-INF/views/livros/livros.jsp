<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

        <%@ include file="/WEB-INF/views/header.jsp" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Livros</title>
                <link rel="stylesheet" type="text/css" href="css/style.css">
            </head>

            <body>
                <h1>Livros</h1>
                <c:if test="${not empty mensagem}">
                    <div class="alert alert-success"
                        style="background-color: #dff0d8; border: 1px solid #d6e9c6; color: #3c763d; padding: 10px; border-radius: 4px;">
                        ${mensagem}
                    </div>
                </c:if>
                <div class="btn-index">
                    <a class="button" href="home">Voltar</a>
                    <a class="button" href="livros?action=novo">Cadastrar</a>
                    <a class="button" href="emprestimos?action=novo">Novo Empréstimo</a>
                </div>
                <div class="tb">
                    <table border="1">
                        <tr>
                            <th>ID</th>
                            <th>Título</th>
                            <th>Autor</th>
                            <th>Editora</th>
                            <th>Opções</th>
                            <th>Disponível</th>
                        </tr>
                        <c:forEach items="${lista}" var="livros">
                            <tr>
                                <td>${livros.id_livro}</td>
                                <td>${livros.titulo}</td>
                                <td>${livros.autor}</td>
                                <td>${livros.editora}</td>
                                <td>
                                    <a
                                        href="livros?action=editar&id_livro=${livros.id_livro}">Editar</a>
                                    <c:if test="${sessionScope.funcao eq 'Gerente'}">
                                        <a href="livros?action=deletar&id_livro=${livros.id_livro}"
                                            onclick="return confirm('Tem certeza que deseja excluir este livro?');">Excluir</a>
                                    </c:if>
                                </td>
                                
                                <c:if test="${livros.disponivel eq '1'}">
								    <td style="color: green;">Sim</td>
								</c:if>
								
								<c:if test="${livros.disponivel eq '0'}">
								    <td style="color: red;">Não</td>
								</c:if>

                                
                        </c:forEach>

                    </table>
                </div>


                <footer>
                    <p>© 2024 Biblioteca. Todos os direitos reservados. Desenvolvido por Marcelo S Ribeiro -
                        marcelocaricatus@hotmail.com</p>
                </footer>
            </body>

            </html>
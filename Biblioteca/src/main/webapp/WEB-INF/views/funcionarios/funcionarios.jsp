<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

        <%@ include file="/WEB-INF/views/header.jsp" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UTF-8">
			<meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Funcionários</title>
                <link rel="stylesheet" type="text/css" href="css/style.css">
            </head>

            <body>
                <h1>Funcionários</h1>
                <c:if test="${not empty mensagem}">
                    <div class="alert alert-success"
                        style="background-color: #dff0d8; border: 1px solid #d6e9c6; color: #3c763d; padding: 10px; border-radius: 4px;">
                        ${mensagem}
                    </div>
                </c:if>
                <div class="btn-index">
                    <a class="button" href="home">Voltar</a>
                    <a class="button" href="funcionarios?action=novo">Cadastrar</a>
                </div>
                <div class="tb">
                    <table border="1">
                        <tr>
                            <th>Nome</th>
                            <th>Telefone</th>
                            <th>Função</th>
                            <th>Opções</th>
                        </tr>
                        <c:forEach items="${lista}" var="funcionarios">
                            <tr>
                                <td>${funcionarios.nome}</td>
                                <td>${funcionarios.telefone}</td>
                                <td>${funcionarios.funcao}</td>
                                <td>
                                    <a
                                        href="funcionarios?action=editar&id_funcionario=${funcionarios.id_funcionario}">Editar</a>
                                    <c:if test="${sessionScope.funcao eq 'Gerente'}">
                                        <a
                                            href="funcionarios?action=deletar&id_funcionario=${funcionarios.id_funcionario}"
                                            onclick="return confirm('Tem certeza que deseja excluir este funcionário?');">Excluir</a>
                                    </c:if>
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
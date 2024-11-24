<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

        <%@ include file="/WEB-INF/views/header.jsp" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Receber Multa</title>
                <link rel="stylesheet" type="text/css" href="css/style.css">
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.min.js"></script>
            <script
                src="https://cdnjs.cloudflare.com/ajax/libs/jquery-maskmoney/3.0.2/jquery.maskMoney.min.js"></script>
            <script src="js/script.js"></script>
            </head>

            <body>
                <h1>Multas</h1>
                <c:if test="${not empty mensagem}">
                    <div class="alert alert-success"
                        style="background-color: #dff0d8; border: 1px solid #d6e9c6; color: #3c763d; padding: 10px; border-radius: 4px;">
                        ${mensagem}
                    </div>
                </c:if>
                <div class="btn-index">
                    <a class="button" href="home">Voltar</a>
                </div>
                <div class="tb">
                    <table border="1">
                        <tr>
                            <th>Empréstimo</th>
                            <th>Cliente</th>
                            <th>Título</th>	
                            <th>Valor</th>
                            <th>Vencimento</th>
                            <th>Opções</th>
                        </tr>
                       <c:forEach var="multas" items="${listaMultas}" varStatus="status">
					    <tr>
					        <td>${multas.id_emprestimo}</td>
					        <td>${multas.cliente}</td>
					        <td>${multas.titulo}</td>
					        <td id="valor">${multas.valor}</td>
					        <td id="vencimento">${multas.vencimento}</td>
					        <td>
					            <a href="emprestimos?action=receber&id_multa=${multas.id}"
					                onclick="return confirm('Receber esta multa?');">Receber</a>
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

    
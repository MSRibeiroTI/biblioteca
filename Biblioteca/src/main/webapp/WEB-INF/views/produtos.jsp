<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Produtos</title>
            <link rel="stylesheet" type="text/css" href="css/style.css">
        </head>

        <body>
            <h1>Produtos</h1>
            <div class="tb">
            <table border="1">
                <tr>
                    <th>ID</th>
                    <th>Nome</th>
                    <th>Categoria</th>
                    <th>Preço</th>
                </tr>
                <c:forEach items="${produtos}" var="p">
                    <tr>
                        <td>${p.id}</td>
                        <td>${p.name}</td>
                        <td>${p.category}</td>
                        <td>R$ ${p.price}</td>
                    </tr>
                </c:forEach>

            </table>
            </div>	
            
           <div class="btn-index">
            <a class="button" href="/biblioteca">Voltar</a>
            </div>
       <footer>
	<p>© 2024 Biblioteca. Todos os direitos reservados. Desenvolvido por Marcelo S Ribeiro - marcelocaricatus@hotmail.com</p>
</footer>
        </body>

        </html>

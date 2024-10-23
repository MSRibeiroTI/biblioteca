<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Serviços</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>

<body>
	<h1>Serviços</h1>
	<div class="tb">
	<table border="1">
		<tr>
			<th>Serviço</th>
			<th>Descrição</th>
			<th>Preço</th>
		</tr>
		<c:forEach items="${servicos}" var="s">
			<tr>
				<td>${s.name}</td>
				<td>${s.description}</td>
				<td>R$ ${s.price}</td>
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

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sistemas</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>

<body>
	<h1>Sistemas</h1>
	<div class="tb">
	<table border="1">
	<tr>
			<th>Sistema</th>
			<th>Descrição</th>
			<th>link</th>
		</tr>
		<c:forEach items="${sistemas}" var="s">
		<tr>
			<td>${s.name}</td>
			<td>${s.description}</td>
			<td><a href="${s.link}">link</a></td>
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
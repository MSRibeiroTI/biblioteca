<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
	<title>Login - Biblioteca</title>
	<link rel="stylesheet" type="text/css" href="css/login.css">
</head>

<body>

	<div class="container">
		
		
		<form action="login" method="post" class="form-login">
		<h1 class="title">Biblioteca Odete Almeida Roitman</h1>
		<h3>Faça Login:</h3>
		<div class="login">
			<div>
				<label for="username">CPF:</label>
				<input type="text" id="username" name="username" required>
			</div>
			<div>
				<label for="password">Senha:</label>
				<input type="password" id="password" name="password" required>
			</div>
		</div>
			<c:if test="${not empty mensagem}">
                <div class="alert alert-success"
                    style="background-color: #dff0d8; border: 1px solid #d6e9c6; color: red; padding: 10px; border-radius: 4px;">
                    ${mensagem}
                </div>
            </c:if>
			<input class="button" type="submit" value="Entrar">
		</form>
		
	</div>
	<footer>
		<p>Â© 2024 Biblioteca. Todos os direitos reservados. Desenvolvido por Marcelo S Ribeiro -
			marcelocaricatus@hotmail.com</p>
	</footer>
</body>

</html>
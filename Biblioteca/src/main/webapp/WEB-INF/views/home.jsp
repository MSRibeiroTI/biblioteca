<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<%@ include file="/WEB-INF/views/header.jsp" %>
			<!DOCTYPE html>
			<html>

			<head>
				<title>Biblioteca</title>
				<link rel="stylesheet" type="text/css" href="css/style.css">
			</head>

			<body>

				<div class="container">
					<div class="img">
						<img src="img/imagem.jpg">
					</div>
					<h1 class="title">Bem-vindo! Biblioteca Odete Almeida Roitman</h1>
					<p class="description"> ## Sistema de Controle ##</p>
					<c:if test="${not empty mensagem}">
						<div class="alert alert-success"
							style="background-color: #dff0d8; border: 1px solid #d6e9c6; color: #3c763d; padding: 10px; border-radius: 4px;">
							${mensagem}
						</div>
					</c:if>

					<div class="btn-index">
						<a class="button" href="clientes">Clientes</a>
						<a class="button" href="funcionarios?action=listar">Funcionários</a>
						<a class="button" href="servicos">Livros</a>
						<a class="button" href="emprestar">Empréstimos</a>
						<a class="button" href="logout">Sair</a>
					</div>
				</div>
				<hr>
				<div class="container">
					<div class="painel">
						<div class="aniversario">

							<h3>Aniversariantes de hoje - ${data}: </h3>

							<h4>Clientes:</h4>
							<c:forEach items="${niver_cli}" var="niver">
								<p>${niver.nome}</p>

							</c:forEach>
							<h4>Funcionários:</h4>
							<c:forEach items="${niver_func}" var="niver">
								<p>${niver.nome}</p>
							</c:forEach>
							<c:if test="${not empty niver_cli or not empty niver_func}">
								<img class="balao-img" src="img/baloes.png">
							</c:if>
						</div>
						<div class="livros">
							<h3>Livros Atrasados:</h3>
						</div>
					</div>
				</div>
				<footer>
					<p>© 2024 Biblioteca. Todos os direitos reservados. Desenvolvido por Marcelo S Ribeiro -
						marcelocaricatus@hotmail.com</p>
				</footer>
			</body>

			</html>
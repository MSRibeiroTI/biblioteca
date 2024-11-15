<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ include file="/WEB-INF/views/header.jsp" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
			<meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Cadastrar Livro</title>
            <link rel="stylesheet" type="text/css" href="css/style.css">
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.min.js"></script>
            <script
                src="https://cdnjs.cloudflare.com/ajax/libs/jquery-maskmoney/3.0.2/jquery.maskMoney.min.js"></script>
            <script src="js/script.js"></script>
        </head>

        <body>
            <c:if test="${not empty mensagem}">
                <div class="alert alert-success"
                    style="background-color: #dff0d8; border: 1px solid #d6e9c6; color: #3c763d; padding: 10px; border-radius: 4px;">
                    ${mensagem}
                </div>
                </c:if>
            <form action="livros?action=cadastrar" method="post" style="display: flex; flex-direction: column; align-items: center;">
               <h1>Cadastrar Novo Livro</h1>
                <div class="form-group">
                    <label for="titulo">Título:</label> <input type="text" id="titulo" name="titulo" required>
                </div>
                <div class="form-cpf">
                    <div>
                        <label for="autor">Autor:</label><br>
                        <input type="text" id="autor" name="autor" required>
                    </div>
                    <div>
                        <label for="editora">Editora:</label><br>
                        <input type="text" id="editora" name="editora" required><br>
                    </div>
                    <div>
                        <label for="nascimento">Ano de Publicação:</label><br> <input type="text" id="ano" name="ano" required>
                    </div>
                </div>
                <div class="form-cep">
                    <div>
                        <label>ISBN: <input name="isbn" type="text" id="isbn" placeholder="Apenas números"></label>
                    </div>
                    <div>
                        <label>Localização:</label> <input name="local" type="text" id="local">
                    </div>
                    <div>
                        <label for="quantidade">Quantidade:</label> <input type="number" id="quantidade" name="quantidade" required>
                    </div>
                </div>
                
                <div class="form-cep">
                    <div>
                        <label for="genero">Gênero:</label>
                        <select id="genero" name="genero" required>
                            <option value="" selected="selected">Selecione</option>
                            <option value="Ação">Ação</option>
                            <option value="Ficção Histórica">Ficção Histórica</option>
                            <option value="Terror">Terror</option>
                            <option value="Ficção Científica">Ficção Científica</option>
                            <option value="Programação">Programação</option>
                            <option value="Conto">Conto</option> 
                        </select>
                    </div>
                   
                </div>
			<div class="btn-index">
                <input class="button" type="submit" id="cadastrar-botao" value="Cadastrar" style="width: 100%; text-align: center">
            </div>
            </form>

            <div class="btn-index">
                <a class="button" href="livros?action=listar">Voltar</a>
            </div>
            <footer>
                <p>© 2024 Biblioteca. Todos os direitos reservados. Desenvolvido por Marcelo S Ribeiro -
                    marcelocaricatus@hotmail.com</p>
            </footer>
        </body>
        <br><br>

        </html>
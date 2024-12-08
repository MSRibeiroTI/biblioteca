<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ include file="/WEB-INF/views/header.jsp" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
			<meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Editar Cliente</title>
            <link rel="stylesheet" type="text/css" href="css/style.css">
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.min.js"></script>
            <script
                src="https://cdnjs.cloudflare.com/ajax/libs/jquery-maskmoney/3.0.2/jquery.maskMoney.min.js"></script>
            <script src="js/script.js"></script>
        </head>

        <body>
            
            <form action="cliente?action=atualizar_cliente" method="post"
                style="display: flex; flex-direction: column; align-items: center;">
                <h1>Cliente</h1>
                <div class="form-group">
                    <input type="hidden" name="id_cliente" value="${cliente.id_cliente}">
                    <input type="hidden" name="id_endereco" value="${cliente.id_endereco}">
                    <label for="nome">Nome:</label> <input type="text" id="nome" name="nome" value="${cliente.nome}"
                        disabled>
                </div>
                <div class="form-cpf">
               		<div>
	                    <label for="telefone">Telefone:</label><br>
	                    <input type="text" id="telefone" name="telefone" value="${cliente.telefone}" disabled>
                    </div>
                    <div>         
                    <label for="cpf">CPF:</label><br>
                    <input type="text" id="cpf_edit" name="cpf" maxlength="11" value="${cliente.cpf}"
                        onkeyup="validarCPF(this.value);" disabled required> <br>
                    <span id="cpf-invalido" style="display: none; color: red;">CPF inválido</span>
                    <span id="cpf-valido" style="display: none; color: green;">CPF válido</span>
                </div>
                <div>
                    <label for="nascimento">Data de Nascimento:</label><br> <input type="date" value="${cliente.nascimento}"
                        id="nascimento" name="nascimento" disabled>
                </div>
                </div>
                <div class="form-cep">
                    <div>
                    	<label>Cep: <input name="cep" type="text" id="cep" value="${cliente.cep}" size="10" maxlength="9"
                            onblur="pesquisacep(this.value);" disabled></label><br />
                   	</div>
                   	<div>
                    	<label>Rua: </label><input name="rua" type="text" value="${cliente.rua}" id="rua" size="60" disabled><br />
	                 </div>
	                 <div>
	                    <label for="numero">Número:</label> <input type="text" value="${cliente.numero}" id="numero"
	                        name="numero" disabled>
                    </div>
                </div>
                <div class="form-cep">
                   <div>
	                    <label for="bairro">Bairro:</label> 
	                    <input type="text" id="bairro" value="${cliente.bairro}" name="bairro" disabled>
                   </div>
	                <div>
	                    <label for="cidade">Cidade:</label>
	                     <input type="text" id="cidade" name="cidade" value="${cliente.cidade}" disabled>
                   </div>
                   <div>
	                    <label>Estado:</label>
	                    <input name="uf" type="text" id="uf" size="2" value="${cliente.estado}" maxlength="2" disabled> <br />
                	</div>
                </div>
                <div class="form-group">
                    <label for="referencias">Referências:</label>
                    <textarea id="referencias" name="referencias" disabled>${cliente.referencias}</textarea>
                </div>
               
            </form>

            <div class="btn-index">
                <a class="button" href="clientes">Voltar</a>
            </div>
            <div class="container">
                <h2>Histórico de Empréstimos</h2>
                <table id="historico" class="tb">
                    <thead>
                        <tr>
                            <th>Livros</th>
                            <th>Devolução</th>
                            <th>Devolvido</th>
                        </tr>
                        <c:forEach items="${historico}" var="historico">
                        <tr>
                            <td>${historico.titulo}</td>
                            <td>${historico.data_devolucao}</td>
                            <td style="color: ${historico.status == 1 ? 'red' : 'green'}">${historico.status == 1 ? 'não' : 'sim'}</td>
                        </tr>
                        </c:forEach>
                    </thead>
                   	 	
                </table>
            </div>
            <footer>
                <p>© 2024 Biblioteca. Todos os direitos reservados. Desenvolvido por Marcelo S Ribeiro -
                    marcelocaricatus@hotmail.com</p>
            </footer>
        </body>
        <br><br>

        </html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ include file="/WEB-INF/views/header.jsp" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Cadastrar Funcionário</title>
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
            <form action="funcionarios?action=cadastrar" method="post" style="display: flex; flex-direction: column; align-items: center;">
               <h1>Cadastrar Funcionário</h1>
                <div class="form-group">
                    <label for="nome">Nome:</label> <input type="text" id="nome" name="nome" required>
                </div>
                <div class="form-cpf">
                    <div>
                        <label for="telefone">Telefone:</label><br>
                        <input type="text" id="telefone" name="telefone" maxlength="11" required>
                    </div>
                    <div>
                        <label for="cpf">CPF:</label><br>
                        <input type="text" id="cpf" name="cpf" maxlength="11" onkeyup="validarCPF(this.value);"
                            required><br>
                        <span id="cpf-invalido" style="display: none; color: red;">CPF inválido</span>
                        <span id="cpf-valido" style="display: none; color: green;">CPF válido</span>
                    </div>
                    <div>
                        <label for="nascimento">Data de Nascimento:</label><br> <input type="date" id="nascimento"
                            name="nascimento" required>
                    </div>
                </div>
                <div class="form-cep">
                    <div>
                        <label>Cep: <input name="cep" type="text" id="cep" value="" size="10" maxlength="9"
                                onblur="pesquisacep(this.value);" /></label>
                    </div>
                    <div>
                        <label>Rua:</label> <input name="rua" type="text" id="rua" size="60">
                    </div>
                    <div>
                        <label for="numero">Número:</label> <input type="text" id="numero" name="numero" required>
                    </div>
                </div>
                <div class="form-cep">
                    <div>
                        <label for="bairro">Bairro:</label> <input type="text" id="bairro" name="bairro" required>
                    </div>
                    <div>
                        <label for="cidade">Cidade:</label> <input type="text" id="cidade" name="cidade" required>
                    </div>
                    <div>
                        <label>Estado:</label><br> <input name="uf" type="text" id="uf" size="2" maxlength="2">
                    </div>
                </div>
                <div class="form-cep">
                    <div>
                        <label for="cargo">Função:</label>
                        <select id="cargo" name="cargo" required>
                            <option value="" selected="selected">Selecione</option>
                            <option value="Gerente">Gerente</option>
                            <option value="Atendente">Atendente</option>
                            <option value="Recepção">Recepção</option>
                            <option value="Caixa">Caixa</option>
                            <option value="Bibliotecário">Bibliotecário</option>
                        </select>
                    </div>
                    <div>
                        <label for="admissao">Admissão:</label> <input type="date" id="admissao" name="admissao"
                            required>
                    </div>
                    <div>
                        <label>Senha:</label><br> <input name="password" type="password" id="password">
                    </div>
                </div>

                <span id="cadastrar" style="display: none;"><input class="button" type="submit" id="cadastrar-botao"
                        value="Cadastrar" style="width: 100%; text-align: center"></span>
            </form>

            <div class="btn-index">
                <a class="button" href="funcionarios?action=listar">Voltar</a>
            </div>
            <footer>
                <p>© 2024 Biblioteca. Todos os direitos reservados. Desenvolvido por Marcelo S Ribeiro -
                    marcelocaricatus@hotmail.com</p>
            </footer>
        </body>
        <br><br>

        </html>
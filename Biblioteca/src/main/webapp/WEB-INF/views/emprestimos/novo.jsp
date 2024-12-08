<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

        <%@ include file="/WEB-INF/views/header.jsp" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UTF-8">
                <title>Novo Empréstimo</title>
                <link rel="stylesheet" type="text/css" href="css/style.css">
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
                <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
                <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.min.js"></script>
                <script
                    src="https://cdnjs.cloudflare.com/ajax/libs/jquery-maskmoney/3.0.2/jquery.maskMoney.min.js"></script>
                <script src="js/script.js"></script>
            </head>

            <body>
                <h1>Novo Empréstimo</h1>
                <c:if test="${not empty mensagem}">
                    <div class="alert alert-success"
                        style="background-color: #dff0d8; border: 1px solid #d6e9c6; color: #3c763d; padding: 10px; border-radius: 4px;">
                        ${mensagem} </div>
                </c:if>


                <form id="emprestimoForm" action="emprestimos" method="post">
                    <input type="hidden" name="action" value="salvar">
                    <label for="livro">Livro(s):</label>
                    <input type="text" name="livro" id="livro" oninput="buscar()"
                        placeholder="Digite o nome ou id do livro">
                    <input type="hidden" name="livroId" value="" id="livroId">
                    <input id="livroNome" type="text" value="" disabled>
                    <div class="btn-index">
                        <button type="button" class="button" id="btnAdicionarLivro">Adicionar</button>
                    </div><br>
                    <div id="livrosAdicionados"></div>
                    <div id="livrosAdicionadosId"></div>
                    <br>
                    <label for="cliente">Cliente:</label>
                    <select name="cliente" id="cliente">
                        <option value="">Selecione um cliente</option>
                        <c:forEach items="${clientes}" var="cliente">
                            <option value="${cliente.id_cliente}">${cliente.nome}</option>
                        </c:forEach>
                    </select>
                    <br><br>
                    <div class="data">
                        <label for="dataEmprestimo">Data de Emprestimo:</label><br>
                        <input type="date" name="dataEmprestimo" id="dataEmprestimo"
                            value="<%= java.time.LocalDate.now() %>" required>
                        <br>
                        <label for="dataDevolucao">Data de Devolucao:</label><br>
                        <input type="date" name="dataDevolucao" id="dataDevolucao"
                            value="<%= java.time.LocalDate.now().plusDays(3) %>" required>
                    </div>
                    <br>

                    <div class="btn-index">
                        <input type="submit" id="submit" value="Salvar">
                    </div>
                </form>
                <div class="btn-index"> <a class="button" href="emprestimos?action=listar">Voltar</a> </div>
                <script>
                    let livrosAdicionados = [];
                    let livrosAdicionadosId = [];

                    function buscar() {
                        var livro = document.getElementById("livro").value;
                        if (livro === "") {
                            console.log("Digite o nome ou id do livro!");
                            alert("Digite o nome ou id do livro!");
                        } else {
                            fetch("livros?action=nomeOuId&nomeOuId=" + livro)
                                .then(response => response.json())
                                .then(data => {
                                    if (data.length > 0) {
                                        var livroNomeDiv = document.getElementById("livroNome");
                                        var livroId = document.getElementById("livroId");
                                        livroNomeDiv.value = data[0].titulo;
                                        livroId.value = data[0].id;
                                    } else {
                                        console.log("Livro não encontrado ou não disponível!");
                                        alert("Livro não encontrado ou não disponível!");
                                    }
                                })
                                .catch(error => {
                                    console.error('Fetch error:', error);
                                    alert('Erro ao buscar dados do servidor.');
                                });
                        }
                    }

                    document.getElementById("btnAdicionarLivro").addEventListener("click", function () {
                        var livro = document.getElementById("livroNome").value;
                        var livroId = document.getElementById("livroId").value;
                        if (livro === "") {
                            console.log("Digite o nome ou id do livro!");
                            alert("Digite o nome ou id do livro!");
                        } else {
                            livrosAdicionados.push(livro);
                            livrosAdicionadosId.push(livroId);
                            var div = document.createElement("div");
                            var divId = document.createElement("div");

                            div.innerHTML = livro;

                            document.getElementById("livrosAdicionados").appendChild(div);
                            document.getElementById("livrosAdicionadosId").appendChild(divId);
                            document.getElementById("livroNome").value = "";
                            document.getElementById("livroId").value = "";

                        }
                    });

                    document.getElementById("emprestimoForm").addEventListener("submit", function (event) {
                        event.preventDefault();
                        var cliente = document.getElementById("cliente").value;
                        var dataEmprestimo = document.getElementById("dataEmprestimo").value;
                        var dataDevolucao = document.getElementById("dataDevolucao").value;
                        var livroId = document.getElementById("livroId");
                        livroId.value = document.getElementById("livroNome").value.split(" - ")[0];

                        if (cliente === "" || dataEmprestimo === "" || dataDevolucao === "") {
                            alert("Por favor, preencha todos os campos obrigatórios.");
                            return;
                        }

                        var emprestimo = {
                            cliente: cliente,
                            dataEmprestimo: dataEmprestimo,
                            dataDevolucao: dataDevolucao,
                            livros: livrosAdicionadosId
                        };

                        fetch("emprestimos?action=salvar_emprestimo", {
                            method: "POST",
                            headers: {
                                "Content-Type": "application/json"
                            },
                            body: JSON.stringify(emprestimo)
                        })
                            .then(response => {
                                if (!response.ok) {
                                    throw new Error('Erro ao salvar empréstimo.');
                                }
                                return response.json();
                            })
                            .then(data => {
                                console.log(data);
                                alert("Empréstimo salvo com sucesso!");
                                // Limpar o formulário e a lista de livros adicionados
                                document.getElementById("emprestimoForm").reset();
                                document.getElementById("livrosAdicionados").innerHTML = "";
                                livrosAdicionadosId = [];
                                livrosAdicionados = [];
                            })
                            .catch(error => {
                                console.error('Erro ao salvar empréstimo:', error);
                                alert('Erro ao salvar empréstimo.');
                            });
                    });
                </script>
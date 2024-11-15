package com.biblioteca.controller;

import java.util.List;

public class EmprestimoRequest {
    private String cliente;
    private String dataEmprestimo;
    private String dataDevolucao;
    private List<String> livros;

    // Getters e setters
    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(String dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public String getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(String dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public List<String> getLivros() {
        return livros;
    }

    public void setLivros(List<String> livros) {
        this.livros = livros;
    }
}

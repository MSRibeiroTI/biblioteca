package com.biblioteca.model;

public class Loan {
    private int id;
    private int bookId;
    private int clientId;
    private String loanDate;
    private String returnDate;
    private String status;

    public Loan(int bookId, int clientId, String loanDate, String returnDate, String status) {
        this.bookId = bookId;
        this.clientId = clientId;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
        this.status = status != null ? status : "emprestado"; // Default status
    }

    public int getId() {
        return id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(String loanDate) {
        this.loanDate = loanDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

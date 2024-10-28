package com.biblioteca.model;

public class Employee {
    private int id;
    private String name;
    private String cpf;
    private String phone;
    private int addressId;
    private String birthDate;
    private String role;
    private String hireDate;
    private String password;

    public Employee(String name, String cpf, String phone, int addressId, String birthDate, String role, String hireDate, String password) {
        this.name = name;
        this.cpf = cpf;
        this.phone = phone;
        this.addressId = addressId;
        this.birthDate = birthDate;
        this.role = role;
        this.hireDate = hireDate;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
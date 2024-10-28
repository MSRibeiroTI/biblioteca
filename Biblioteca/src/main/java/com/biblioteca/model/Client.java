package com.biblioteca.model;

public class Client {
    private int id;
    private String name;
    private String cpf;
    private String phone;
    private int addressId;
    private String birthDate;
    private String preferences;

    public Client(String name, String cpf, String phone, int addressId, String birthDate, String preferences) {
        this.name = name;
        this.cpf = cpf;
        this.phone = phone;
        this.addressId = addressId;
        this.birthDate = birthDate;
        this.preferences = preferences;
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

    public String getPreferences() {
        return preferences;
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }
}

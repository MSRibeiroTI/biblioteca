package com.biblioteca.model;

public class Produto {
	private int id;
	private String name;
	private String category;
	private double price;

	public Produto(int id, String name, String category, double price) {
		this.setId(id);
		this.setName(name);
		this.setCategory(category);
		this.setPrice(price);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}



}

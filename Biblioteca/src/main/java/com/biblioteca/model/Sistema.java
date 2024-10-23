package com.biblioteca.model;

public class Sistema {
	private String name;
	private String description;
	private String link;
	
	public Sistema(String name, String description, String link) {
		this.setName(name);
		this.setDescription(description);
		this.setLink(link);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

}

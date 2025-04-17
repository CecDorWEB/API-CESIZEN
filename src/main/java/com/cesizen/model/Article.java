package com.cesizen.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Article extends Ressource {

	@OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Paragraph> listOfParagraphs;

	public Article() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<Paragraph> getListOfParagraphs() {
		return listOfParagraphs;
	}

	public void setListOfParagraphs(List<Paragraph> listOfParagraphs) {
		this.listOfParagraphs = listOfParagraphs;
	}

}

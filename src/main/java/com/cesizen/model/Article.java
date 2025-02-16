package com.cesizen.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Article extends Ressource {

	@OneToMany(mappedBy = "article")
	private List<Paragraph> listOfParagraphs;

	public Article() {
		super();
		// TODO Auto-generated constructor stub
	}
}

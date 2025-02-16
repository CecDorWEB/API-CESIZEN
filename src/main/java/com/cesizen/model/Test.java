package com.cesizen.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Test extends Ressource {

	@OneToMany(mappedBy = "test")
	private List<Question> listOfQuestions;

	public Test() {
		super();
		// TODO Auto-generated constructor stub
	}

}

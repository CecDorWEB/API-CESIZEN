package com.cesizen.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Test extends Ressource {

	@OneToMany(mappedBy = "test", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Question> listOfQuestions;

	@OneToMany(mappedBy = "test")
	private List<UserTestResult> UserTestResult;

	public Test() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<Question> getListOfQuestions() {
		return listOfQuestions;
	}

	public void setListOfQuestions(List<Question> listOfQuestions) {
		this.listOfQuestions = listOfQuestions;
	}

	public List<UserTestResult> getUserTestResult() {
		return UserTestResult;
	}

	public void setUserTestResult(List<UserTestResult> userTestResult) {
		UserTestResult = userTestResult;
	}

}

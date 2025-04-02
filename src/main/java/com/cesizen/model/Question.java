package com.cesizen.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "question")
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 555, nullable = true)
	private String rule;

	@Column(length = 555, nullable = false)
	private String question;

	@Column(nullable = true)
	private Integer number_expected_answers;

	@ManyToOne
	private Test test;

	@OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Response> listOfResponses;

	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Question(String rule, String question, Integer number_expected_answers, Test test,
			List<Response> listOfResponses) {
		super();
		this.rule = rule;
		this.question = question;
		this.number_expected_answers = number_expected_answers;
		this.test = test;
		this.listOfResponses = listOfResponses;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Integer getNumber_expected_answers() {
		return number_expected_answers;
	}

	public void setNumber_expected_answers(Integer number_expected_answers) {
		this.number_expected_answers = number_expected_answers;
	}

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public List<Response> getListOfResponses() {
		return listOfResponses;
	}

	public void setListOfResponses(List<Response> listOfResponses) {
		this.listOfResponses = listOfResponses;
	}

}

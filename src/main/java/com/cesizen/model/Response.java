package com.cesizen.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "response")
public class Response {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 255, nullable = false)
	private String title;

	@Column(nullable = false)
	private Integer point;

	@Column(nullable = false)
	private boolean multiplied = false;

	@ManyToOne
	private Question question;

	public Response() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Response(String title, Integer point, boolean multiplied, Question question) {
		super();
		this.title = title;
		this.point = point;
		this.multiplied = multiplied;
		this.question = question;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public boolean isMultiplied() {
		return multiplied;
	}

	public void setMultiplied(boolean multiplied) {
		this.multiplied = multiplied;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

}

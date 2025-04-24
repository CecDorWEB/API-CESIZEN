package com.cesizen.model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_test_result")
public class UserTestResult {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Date realizeDate = Date.valueOf(LocalDate.now());

	@Column(nullable = false)
	private Integer score;
	
	@Column(nullable = false)
	private String textResult;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private Member user;

	@ManyToOne
	@JoinColumn(name = "test_id", nullable = false)
	private Test test;

	public UserTestResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserTestResult(Date realizeDate, Integer score, String textResult, Member user, Test test) {
		super();
		this.realizeDate = realizeDate;
		this.score = score;
		this.textResult = textResult;
		this.user = user;
		this.test = test;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getRealizeDate() {
		return realizeDate;
	}

	public void setRealizeDate(Date realizeDate) {
		this.realizeDate = realizeDate;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getTextResult() {
		return textResult;
	}

	public void setTextResult(String textResult) {
		this.textResult = textResult;
	}

	public Member getUser() {
		return user;
	}

	public void setUser(Member user) {
		this.user = user;
	}

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

}

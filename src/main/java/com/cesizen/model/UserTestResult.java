package com.cesizen.model;

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
	private LocalDateTime date_user_test;

	@Column(nullable = false)
	private Integer score;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "test_id", nullable = false)
	private Test test;

	public UserTestResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserTestResult(LocalDateTime date_user_test, Integer score, User user, Test test) {
		super();
		this.date_user_test = date_user_test;
		this.score = score;
		this.user = user;
		this.test = test;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDate_user_test() {
		return date_user_test;
	}

	public void setDate_user_test(LocalDateTime date_user_test) {
		this.date_user_test = date_user_test;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

}

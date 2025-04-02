package com.cesizen.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "test_result_text")
public class TestResultText {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 255, nullable = true)
	private String title;

	@Column(length = 555, nullable = false)
	private String content;

	@Column(nullable = false)
	private Integer minScore;

	@Column(nullable = false)
	private Integer maxScore;

	@OneToMany
	@JoinColumn(name = "test_result_text_id")
	private List<UserTestResult> UserTestResult;

	public TestResultText() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getMinScore() {
		return minScore;
	}

	public void setMinScore(Integer minScore) {
		this.minScore = minScore;
	}

	public Integer getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(Integer maxScore) {
		this.maxScore = maxScore;
	}

	public List<UserTestResult> getUserTestResult() {
		return UserTestResult;
	}

	public void setUserTestResult(List<UserTestResult> userTestResult) {
		UserTestResult = userTestResult;
	}

}

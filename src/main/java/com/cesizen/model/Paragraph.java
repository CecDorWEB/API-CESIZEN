package com.cesizen.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "paragraph")
public class Paragraph {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Integer paragraphOrder;

	@Column(length = 255, nullable = true)
	private String title;

	@Column(length = 2000, nullable = false)
	private String body;

	@Column(length = 555, nullable = true)
	private String visualSupport;

	@Column(length = 255, nullable = true)
	private String altVisualSupport;

	@ManyToOne
	private Article article;

	public Paragraph() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Paragraph(Integer paragraphOrder, String title, String body, String visualSupport, Article article) {
		super();
		this.paragraphOrder = paragraphOrder;
		this.title = title;
		this.body = body;
		this.visualSupport = visualSupport;
		this.article = article;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getParagraphOrder() {
		return paragraphOrder;
	}

	public void setParagraphOrder(Integer paragraphOrder) {
		this.paragraphOrder = paragraphOrder;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getVisualSupport() {
		return visualSupport;
	}

	public void setVisualSupport(String visualSupport) {
		this.visualSupport = visualSupport;
	}

	public String getAltVisualSupport() {
		return altVisualSupport;
	}

	public void setAltVisualSupport(String altVisualSupport) {
		this.altVisualSupport = altVisualSupport;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

}

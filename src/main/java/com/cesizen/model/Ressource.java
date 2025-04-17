package com.cesizen.model;

import java.sql.Date;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) // Je vais créer 2 tables: article et test
public abstract class Ressource {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(length = 255, nullable = false)
	private String title;

	@Column(length = 255, nullable = true)
	private String headerImage;
	
	@Column(length = 255, nullable = true)
	private String altHeaderImage;

	@Lob
	@Column(length = 555, nullable = false)
	private String headerIntroduction;

	@Column(nullable = false)
	private Date publicationDate = Date.valueOf(LocalDate.now());

	@Column(nullable = true)
	private Date updateDate = null;

	@Column(nullable = false)
	private boolean statut = false;

	@ManyToOne
	@JoinColumn(name = "type_id", nullable = false)
	private TypeRessource typeRessource;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	public Ressource() {
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

	public String getHeaderImage() {
		return headerImage;
	}

	public void setHeaderImage(String headerImage) {
		this.headerImage = headerImage;
	}

	public String getAltHeaderImage() {
		return altHeaderImage;
	}

	public void setAltHeaderImage(String altHeaderImage) {
		this.altHeaderImage = altHeaderImage;
	}

	public String getHeaderIntroduction() {
		return headerIntroduction;
	}

	public void setHeaderIntroduction(String headerIntroduction) {
		this.headerIntroduction = headerIntroduction;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public boolean getStatut() {
		return statut;
	}

	public void setStatut(boolean statut) {
		this.statut = statut;
	}

	public TypeRessource getTypeRessource() {
		return typeRessource;
	}

	public void setTypeRessource(TypeRessource typeRessource) {
		this.typeRessource = typeRessource;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}

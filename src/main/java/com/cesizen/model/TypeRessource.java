package com.cesizen.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "RessourceType")
public class TypeRessource {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Id;

	private String name;

	@Lob
	@Column(columnDefinition = "LONGBLOB")
	private byte[] logo;

	public TypeRessource() {
		super();
	}

	public TypeRessource(String name, byte[] logo) {
		super();
		this.name = name;
		this.logo = logo;
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getLogo() {
		return logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}
}

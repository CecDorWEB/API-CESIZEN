package com.cesizen.model;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name="user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length= 80, nullable = false)
	private String firstname;
	
	@Column(length= 80, nullable = false)
	private String lastname;
	
	@Column(length= 80, nullable = false)
	private String email;
	
	@Column(length= 255, nullable = false)
	private String password;
	
	@Column(nullable = false)
	private boolean statut;
	
	@Column(nullable = false)
	private LocalDate adhesionDate;
	
	public User() {}
	
	/*
	public User(String firstname, String lastname, String email, String password,boolean statut, LocalDate adhesionDate)
	{
		this.setFirstname(firstname);
		this.setLastname(lastname);
		this.email = email;
		this.password= password;
		this.statut=true;
		this.adhesionDate=LocalDate.now();
	}
	*/
	
	public Integer getId() {return id;}
	
	public String getFirstname() { return firstname; }
	public void setFirstname(String firstname) { this.firstname = firstname;}

	public String getLastname() { return lastname;}
	public void setLastname(String lastname) {this.lastname = lastname;}
	
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	
	public String getPassword() {return password;}
	public void setPassword(String password) {this.password = password;}
	
	public boolean getStatut() {return statut;}
	public void setStatut(boolean statut) {this.statut=statut;}
	
	public LocalDate getAdhesionDate() { return adhesionDate;}
	public void setAdhesionDate(LocalDate adhesionDate) { this.adhesionDate = adhesionDate;}

	
}

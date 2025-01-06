package com.cesizen.model;


import jakarta.persistence.*;

@Entity
@Table(name="role")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;
	
	/*
	@OneToMany (mappedBy = "role")
	private List<User> users;
	
	public List<User> getUsers() {return users;}
	public void setUsers(List<User> users) {this.users = users;}
	*/
	
	public Integer getId() {return id;}
	public void setId(Integer id) {this.id=id;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	
	public Role() {}
	
	public Role(String name) {
		this.name=name;
	}
	
	
}
package com.cesizen.DTO;

import java.time.LocalDate;

import com.cesizen.model.User;

import lombok.Data;

public class UserDTO {
    private Integer id;
    private String firstname;
    private String lastname;
    private String password;
    private String email;
    private boolean statut;
    private LocalDate adhesionDate;

    // Constructeur sans argument
    public UserDTO() {}

    // Constructeur avec un User en paramètre
    public UserDTO(User user) {
        this.id = user.getId();
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.password=user.getPassword();
        this.email = user.getEmail();
        this.statut = user.getStatut();
        this.adhesionDate = user.getAdhesionDate();
    }

    // Getters et setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    
    public String getPassword() {return password;}
	public void setPassword(String password) {this.password = password;}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isStatut() {
        return statut;
    }

    public void setStatut(boolean statut) {
        this.statut = statut;
    }

    public LocalDate getAdhesionDate() {
        return adhesionDate;
    }

    public void setAdhesionDate(LocalDate adhesionDate) {
        this.adhesionDate = adhesionDate;
    }

    // Méthode toString pour le débogage
    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", statut=" + statut +
                ", adhesionDate=" + adhesionDate +
                '}';
    }
}

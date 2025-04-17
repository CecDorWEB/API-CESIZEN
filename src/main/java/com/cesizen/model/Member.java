package com.cesizen.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("MEMBER")
public class Member extends User {

	// @Column(length = 80, nullable = true)
	// private String nickname;

}

package com.cesizen.model;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("MEMBER")
public class Member extends User {

}

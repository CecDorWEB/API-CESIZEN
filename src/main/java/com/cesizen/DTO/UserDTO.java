package com.cesizen.DTO;

import java.util.Date;

import com.cesizen.model.Role;
import com.cesizen.model.User;

public record UserDTO (
	Integer id,
    String firstname,
    String lastname,
    String password,
    String email,
    Integer role_id
		) {
}

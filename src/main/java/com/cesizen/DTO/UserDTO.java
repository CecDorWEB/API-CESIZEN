package com.cesizen.DTO;

public record UserDTO (
	Integer id,
	String type,
    String firstname,
    String lastname,
    String password,
    String email,
    Integer role_id
		) {
}

package com.cesizen.DTO;

import java.sql.Date;

public record UserDTO(Integer id, String type, String firstname, String lastname, String password, String email,
		Integer role_id, Boolean statut, Date adhesionDate) {
}

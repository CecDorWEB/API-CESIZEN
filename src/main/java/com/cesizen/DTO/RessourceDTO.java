package com.cesizen.DTO;

import java.sql.Date;

public record RessourceDTO(Long id, String title, String headerImage, String headerIntroduction, Date publicationDate,
		boolean statut) {
}

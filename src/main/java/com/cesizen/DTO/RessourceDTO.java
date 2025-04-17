package com.cesizen.DTO;

import java.sql.Date;

public record RessourceDTO(Long id, String title, String headerImage,String altHeaderImage, String headerIntroduction, Date publicationDate,
		Date updateDate, boolean statut, Long type_id, Integer user_id) {
}

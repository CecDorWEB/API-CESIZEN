package com.cesizen.DTO;

import java.sql.Date;

public record UserTestResultDTO (long id, Date realizeDate, Integer score,String textResult, String title
		) {
}

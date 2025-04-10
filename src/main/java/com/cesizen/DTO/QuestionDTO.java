package com.cesizen.DTO;

import java.util.List;

public record QuestionDTO(Long id, String question, String rule, Integer number_expected_answers,
		List<AnswerDTO> listOfAnswers) {

}

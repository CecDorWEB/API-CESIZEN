package com.cesizen.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cesizen.DTO.QuestionDTO;
import com.cesizen.model.Question;
import com.cesizen.services.QuestionServices;

@RestController
@RequestMapping("/ressource/test")
public class QuestionController {

	@Autowired
	private QuestionServices questionService;

	@GetMapping("/{testId}/question")
	public List<QuestionDTO> getQuestionByRessourceId(@PathVariable Long testId) {
		return questionService.getQuestionByRessourceId(testId).stream().map(questionService::toDTO)
				.collect(Collectors.toList());
	}

	// Ajouter une question
	@PostMapping("/{testId}/question")
	public ResponseEntity<QuestionDTO> createQuestion(@PathVariable long testId, @RequestBody Question question) {

		Question createdQuestion = questionService.createQuestion(testId, question);

		QuestionDTO dto = questionService.toDTO(createdQuestion);
		return ResponseEntity.ok(dto);
	}
}

package com.cesizen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cesizen.DTO.AnswerDTO;
import com.cesizen.model.Answer;
import com.cesizen.services.AnswerServices;

@RestController
@RequestMapping("/ressource/test/question")
public class AnswerController {

	@Autowired
	private AnswerServices answerService;

	// Ajouter une réponse
	@PostMapping("/{questionId}/answer")
	public ResponseEntity<AnswerDTO> createAnswer(@PathVariable long questionId, @RequestBody Answer answer) {

		Answer createdAnswer = answerService.createAnswer(questionId, answer);

		AnswerDTO dto = answerService.toDTO(createdAnswer);
		return ResponseEntity.ok(dto);
	}

	// Supprimer une réponse
	@DeleteMapping("/answer/{answerId}")
	public ResponseEntity<String> deleteAnswer(@PathVariable Long answerId) {
		boolean deleted = answerService.deleteAnswer(answerId);
		if (deleted) {
			return ResponseEntity.ok("Réponse supprimée avec succès !");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Réponse introuvable !");
		}
	}
}

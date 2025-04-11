package com.cesizen.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cesizen.DTO.AnswerDTO;
import com.cesizen.model.Answer;
import com.cesizen.model.Question;
import com.cesizen.repository.AnswerRepository;
import com.cesizen.repository.QuestionRepository;

import jakarta.transaction.Transactional;

@Service
public class AnswerServices {

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private AnswerRepository answerRepository;

	public AnswerDTO toDTO(Answer answer) {
		return new AnswerDTO(answer.getId(), answer.getTitle(), answer.getPoint(), answer.isMultiplied());
	}

	/* Création Answer */
	public Answer createAnswer(Long questionId, Answer answer) {

		Question question = questionRepository.findById(questionId)
				.orElseThrow(() -> new RuntimeException("The provided question is not found"));

		if (question == null) {
			throw new RuntimeException("The provided question is not find");
		}

		// Très important : utiliser la question récupérée de la base
		answer.setQuestion(question);

		// Ajouter la réponse à la liste (si elle est null, on l'initialise)
		if (question.getListOfAnswers() == null) {
			question.setListOfAnswers(new ArrayList<>());
		}
		question.getListOfAnswers().add(answer);

		// Sauvegarde
		return answerRepository.save(answer);
	}

	/* Supprimer une réponse */
	@Transactional
	public boolean deleteAnswer(Long answerId) {
		Optional<Answer> optionalAnswer = answerRepository.findById(answerId);
		if (optionalAnswer.isPresent()) {
			answerRepository.deleteById(answerId);
			return true;
		}
		return false;
	}
}

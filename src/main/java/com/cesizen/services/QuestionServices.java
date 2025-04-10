package com.cesizen.services;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cesizen.DTO.QuestionDTO;
import com.cesizen.model.Question;
import com.cesizen.model.Ressource;
import com.cesizen.model.Test;
import com.cesizen.repository.QuestionRepository;
import com.cesizen.repository.RessourceRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class QuestionServices {

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private RessourceRepository ressourceRepository;

	public List<Question> getQuestionByRessourceId(Long testId) {
		return questionRepository.findQuestionByTestId(testId);
	}

	public QuestionDTO toDTO(Question question) {
		return new QuestionDTO(question.getId(), question.getQuestion(), question.getRule(),
				question.getNumber_expected_answers());
	}

	/* Création Question */
	public Question createQuestion(Long testId, Question question) {

		Ressource ressource = ressourceRepository.findTestByTestId(testId);

		if (ressource == null) {
			throw new RuntimeException("The provided resource is not find");
		}

		Test test = (Test) ressource;
		question.setTest(test);
		return questionRepository.save(question);
	}

	/* Modifier question */
	public Question updateQuestion(Question question, Long questionId) {
		Question questDB = questionRepository.findById(questionId)
				.orElseThrow(() -> new EntityNotFoundException("Question non trouvée avec l'ID : " + questionId));
		;

		if (Objects.nonNull(question.getQuestion())) {
			questDB.setQuestion(question.getQuestion());
		}

		if (Objects.nonNull(question.getRule())) {
			questDB.setRule(question.getRule());
		}

		if (Objects.nonNull(question.getNumber_expected_answers())) {
			questDB.setNumber_expected_answers(question.getNumber_expected_answers());
		}

		return questionRepository.save(questDB);
	}

	/* Supprimer une question */
	public boolean deleteQuestion(Long questionId) {
		if (questionRepository.existsById(questionId)) {
			questionRepository.deleteById(questionId);
			return true;
		}
		return false;
	}

}

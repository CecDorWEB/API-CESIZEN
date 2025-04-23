package com.cesizen.services;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cesizen.DTO.ParagraphDTO;
import com.cesizen.DTO.ResultDTO;
import com.cesizen.model.Article;
import com.cesizen.model.Paragraph;
import com.cesizen.model.Ressource;
import com.cesizen.model.Result;
import com.cesizen.model.Test;
import com.cesizen.repository.RessourceRepository;
import com.cesizen.repository.ResultRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ResultServices {

	@Autowired
	private ResultRepository resultRepository;
	
	@Autowired
	private RessourceRepository ressourceRepository;
	
	//Pour renvoyer le résultat conformément au DTO
	public ResultDTO toDTO(Result result) {
		return new ResultDTO(result.getId(), result.getTitle(), result.getContent(),
				result.getMinScore(), result.getMaxScore());
	}
	
	//Tous les résultats par ressource
	 public  List<Result> getAllResultByRessourceId(Long ressourceId) {
		 return resultRepository.findAllResultByRessourceId(ressourceId);
	    }
	
	 //Résultat en fonction du score de l'utilisateur
	 public Result findResultByScore(Long ressourceId, Integer score) {
	       return resultRepository.findResultTestByScore(ressourceId, score);
	 }
	 
	//Création ou modification d'un résultat
		public Result createResult(Long ressourceId, Result result) {

			if (result.getId() == null) {
				//Dans ce cas on créer le résultat
				Ressource ressource = ressourceRepository.findTestByTestId(ressourceId);
				
				if (ressource == null) {
					throw new RuntimeException("The provided resource is not an Article");
				}
				
				Test test = (Test) ressource;
				result.setTest(test);
				return resultRepository.save(result);
				
			} else {
				//Modification
				Result resultDB = resultRepository.findById(result.getId())
			            .orElseThrow(() -> new EntityNotFoundException("Résultat non trouvé"));
				
				if (Objects.nonNull(result.getMinScore())) {
					resultDB.setMinScore(result.getMinScore());
				}
				
				if (Objects.nonNull(result.getMaxScore())) {
					resultDB.setMaxScore(result.getMaxScore());
				}
				
				if (Objects.nonNull(result.getTitle())) {
					resultDB.setTitle(result.getTitle());
				}
				
				if (Objects.nonNull(result.getContent())) {
					resultDB.setContent(result.getContent());
				}
				
				return resultRepository.save(resultDB);
			}

		}
	 
		/* Supprimer paragraphe */
		public boolean deleteResult(Long resultId) {
			if (resultRepository.existsById(resultId)) {
				resultRepository.deleteById(resultId);
				return true;
			}
			return false;
		}
}
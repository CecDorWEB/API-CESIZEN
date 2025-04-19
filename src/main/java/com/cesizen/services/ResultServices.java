package com.cesizen.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cesizen.DTO.ParagraphDTO;
import com.cesizen.DTO.ResultDTO;
import com.cesizen.model.Paragraph;
import com.cesizen.model.Result;
import com.cesizen.repository.ResultRepository;

@Service
public class ResultServices {

	@Autowired
	private ResultRepository resultRepository;
	
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
	 
		/* Supprimer paragraphe */
		public boolean deleteResult(Long resultId) {
			if (resultRepository.existsById(resultId)) {
				resultRepository.deleteById(resultId);
				return true;
			}
			return false;
		}
}
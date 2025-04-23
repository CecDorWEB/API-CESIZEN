package com.cesizen.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cesizen.DTO.ParagraphDTO;
import com.cesizen.DTO.ResultDTO;
import com.cesizen.model.Paragraph;
import com.cesizen.model.Result;
import com.cesizen.services.ResultServices;

@RestController
@RequestMapping("/results")
public class ResultController {
	
	@Autowired
	private ResultServices resultService;
	
	//Récupérer tous les textes de résultat d'une ressource
	@GetMapping("/all/{ressourceId}")
	public List<ResultDTO> getResultByRessourceId(@PathVariable Long ressourceId) {
	    return resultService.getAllResultByRessourceId(ressourceId).stream().map(resultService::toDTO)
				.collect(Collectors.toList());
	}
	
	//Récupérer les textes de résultat en fonction du score de l'utilisateur
		@GetMapping("/search")
		public ResponseEntity<ResultDTO> getResultByQuery(
		        @RequestParam Long ressourceId,
		        @RequestParam Integer score) {
		    Result result = resultService.findResultByScore(ressourceId, score);
		    
		    if (result == null) {
				return ResponseEntity.notFound().build();
			}
		    
		    ResultDTO resultDTO = resultService.toDTO(result);
		    return ResponseEntity.ok(resultDTO);
		}
		
		// Ajouter ou modifier un résultat
		@PostMapping("/{ressourceId}/create")
		public ResponseEntity<ResultDTO> createResult(@PathVariable long ressourceId,
				@RequestBody Result result) {
			
			System.out.println("------------------------------Reçu dans le backend : " + result);
			
			Result createdResult = resultService.createResult(ressourceId, result);

			ResultDTO dto = resultService.toDTO(createdResult);
			return ResponseEntity.ok(dto);
		}
		
	// Supprimer un résultat
		@DeleteMapping("/delete/{resultId}")
		public ResponseEntity<String> deleteResult(@PathVariable Long resultId) {
			boolean deleted = resultService.deleteResult(resultId);
			if (deleted) {
				return ResponseEntity.ok("Resultat supprimé avec succès !");
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Résultat introuvable !");
			}
		}
}

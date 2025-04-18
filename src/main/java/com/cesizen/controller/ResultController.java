package com.cesizen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cesizen.DTO.ResultDTO;
import com.cesizen.model.Result;
import com.cesizen.services.ResultServices;

@RestController
@RequestMapping("/results")
public class ResultController {
	
	@Autowired
	private ResultServices resultService;
	
	//Récupérer les textes de résultat en fonction du score de l'utilisateur
	@GetMapping("/search")
	public ResponseEntity<ResultDTO> getResultByQuery(
	        @RequestParam Long ressourceId,
	        @RequestParam Integer score) {
	    ResultDTO result = resultService.findResultByScore(ressourceId, score);
	    if (result != null) {
	        return ResponseEntity.ok(result);
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	    }
	}
	
}

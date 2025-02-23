package com.cesizen.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cesizen.DTO.RessourceDTO;
import com.cesizen.model.Ressource;
import com.cesizen.repository.RessourceRepository;
import com.cesizen.services.RessourceServices;

@RestController
@CrossOrigin
@RequestMapping("/ressource")
public class RessourceController {

	@Autowired
	private RessourceServices ressourceServices;

	@Autowired
	private RessourceRepository ressourceRepository;

	// Récupérer toutes les ressources
	@GetMapping
	public List<RessourceDTO> getAllRessources() {
		return ressourceServices.getAllRessources().stream().map(ressourceServices::toDTO).collect(Collectors.toList());
	}

	// Récupérer uniquement les Articles
	@GetMapping("/article")
	public List<RessourceDTO> getAllArticles() {
		return ressourceServices.getAllArticles().stream().map(ressourceServices::toDTO).collect(Collectors.toList());
	}

	// Récupérer l'article avec l'id de mon choix
	@GetMapping("/article/{articleId}")
	public List<RessourceDTO> getArticleById(@PathVariable Long articleId) {
		return ressourceServices.getArticleById(articleId).stream().map(ressourceServices::toDTO)
				.collect(Collectors.toList());
	}

	// Récupérer uniquement les tests
	@GetMapping("/test")
	public List<RessourceDTO> getAllTests() {
		return ressourceServices.getAllTests().stream().map(ressourceServices::toDTO).collect(Collectors.toList());
	}

	// Créer une nouvelle ressource
	@PostMapping
	public RessourceDTO createRessource(@RequestBody RessourceDTO ressourceDTO) {
		Ressource ressource = ressourceServices.toEntity(ressourceDTO);
		Ressource savedRessource = ressourceServices.saveRessource(ressource);

		return ressourceServices.toDTO(savedRessource);
	}

}

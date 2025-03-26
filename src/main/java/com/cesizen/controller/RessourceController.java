package com.cesizen.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cesizen.DTO.RessourceDTO;
import com.cesizen.model.Article;
import com.cesizen.model.Ressource;
import com.cesizen.model.Test;
import com.cesizen.repository.RessourceRepository;
import com.cesizen.services.RessourceServices;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin
@RequestMapping("/ressource")
public class RessourceController {

	@Autowired
	private RessourceServices ressourceServices;

	@Autowired
	private RessourceRepository ressourceRepository;

	@Autowired
	private ObjectMapper objectMapper;

	// Récupérer toutes les ressources
	@GetMapping
	public List<RessourceDTO> getAllRessources() {
		return ressourceServices.getAllRessources().stream().map(ressourceServices::toDTO).collect(Collectors.toList());
	}

	// Récupérer une ressource en fonction de son id
	@GetMapping("/{ressourceId}")
	public ResponseEntity<RessourceDTO> getRessourcebyId(@PathVariable Long ressourceId) {
		Ressource ressource = ressourceServices.getRessourceById(ressourceId);

		if (ressource == null) {
			return ResponseEntity.notFound().build();
		}

		RessourceDTO ressourceDTO = ressourceServices.toDTO(ressource);
		return ResponseEntity.ok(ressourceDTO);
	}

	// Récupérer uniquement les Articles
	@GetMapping("/article")
	public List<RessourceDTO> getAllArticles() {
		return ressourceServices.getAllArticles().stream().map(ressourceServices::toDTO).collect(Collectors.toList());
	}

	// Récupérer uniquement les ressources autorisés par type
	@GetMapping("/all")
	public ResponseEntity<List<RessourceDTO>> getAllRessourcesAllowedByType(@RequestParam Long ressourceTypeId) {
		List<RessourceDTO> ressources = ressourceServices.getAllRessourcesAllowedByType(ressourceTypeId).stream()
				.map(ressourceServices::toDTO).collect(Collectors.toList());

		if (ressources.isEmpty()) {
			return ResponseEntity.noContent().build(); // 204 No Content
		}

		return ResponseEntity.ok(ressources);
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

	// Supprimer une ressource
	@PostMapping("/delete")
	public ResponseEntity<String> deleteRessource(@RequestBody Map<String, Long> payload) {
		Long ressourceId = payload.get("id");

		if (ressourceId == null) {
			return ResponseEntity.badRequest().body("ID manquant");
		}

		boolean isDeleted = ressourceServices.deleteRessource(ressourceId);

		if (isDeleted) {
			return ResponseEntity.ok("Ressource supprimée avec succès.");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ressource non trouvée.");
		}
	}

	// Modifier une ressource
	@PutMapping("/{type}/{ressourceId}/modify")
	public ResponseEntity<RessourceDTO> updateRessource(@RequestBody Map<String, Object> ressourceData,
			@PathVariable("type") String type, @PathVariable("ressourceId") Long ressourceId) {

		Ressource updatedRessource;

		if ("article".equalsIgnoreCase(type)) {
			Article article = objectMapper.convertValue(ressourceData, Article.class);
			updatedRessource = ressourceServices.updateRessource(article, type, ressourceId);
		} else if ("test".equalsIgnoreCase(type)) {
			Test test = objectMapper.convertValue(ressourceData, Test.class);
			updatedRessource = ressourceServices.updateRessource(test, type, ressourceId);
		} else {
			return ResponseEntity.badRequest().build();
		}

		// Convertir l'objet en DTO avant de le retourner
		RessourceDTO ressourceDTO = ressourceServices.toDTO(updatedRessource);
		return ResponseEntity.ok(ressourceDTO);
	}

	// Modifier le statut (autorisé/suspendu) d'une ressource
	@PostMapping("/autorization")
	public ResponseEntity<String> autorizedRessource(@RequestBody Map<String, Long> payload) {
		Long ressourceId = payload.get("id");

		if (ressourceId == null) {
			return ResponseEntity.badRequest().body("ID manquant");
		}

		try {
			boolean statutModifie = ressourceServices.autorizedRessource(ressourceId);
			if (statutModifie) {
				return ResponseEntity.ok("Autorisation de la ressource modifiée avec succès.");
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ressource non trouvée.");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Erreur lors de la modification du statut.");
		}
	}

}

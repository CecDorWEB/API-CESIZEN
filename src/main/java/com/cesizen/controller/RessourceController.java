package com.cesizen.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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

	@GetMapping
	public List<RessourceDTO> getAllRessources() {
		return ressourceServices.getAllRessources().stream().map(ressourceServices::toDTO).collect(Collectors.toList());
	}

	// Créer une nouvelle ressource

	@PostMapping
	public RessourceDTO createRessource(@RequestBody RessourceDTO ressourceDTO) {
		Ressource ressource = ressourceServices.toEntity(ressourceDTO);
		Ressource savedRessource = ressourceServices.saveRessource(ressource);

		return ressourceServices.toDTO(savedRessource);
	}

}

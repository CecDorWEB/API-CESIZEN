package com.cesizen.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cesizen.model.TypeRessource;
import com.cesizen.services.TypeRessourceServices;

@RestController
@RequestMapping("/type")
public class TypeRessourceController {
	private TypeRessourceServices typeRessourceServices;

	public TypeRessourceController(TypeRessourceServices typeRessourceServices) {
		this.typeRessourceServices = typeRessourceServices;
	}

	@GetMapping
	public List<TypeRessource> getAllTypes() {
		return typeRessourceServices.getAllTypes();
	}

	@PostMapping
	public ResponseEntity<TypeRessource> addType(@RequestBody TypeRessource typeRessource) {
		System.out.println("Type received : " + typeRessource);
		TypeRessource savedType = typeRessourceServices.saveType(typeRessource);
		return new ResponseEntity<>(savedType, HttpStatus.CREATED);
	}
}

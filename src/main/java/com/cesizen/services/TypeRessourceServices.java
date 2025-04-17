package com.cesizen.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cesizen.model.TypeRessource;
import com.cesizen.repository.TypeRessourceRepository;

@Service
public class TypeRessourceServices {

	@Autowired
	private TypeRessourceRepository typeRessourceRepository;

	public List<TypeRessource> getAllTypes() {
		return typeRessourceRepository.findAll();
	}

	public TypeRessource saveType(TypeRessource type) {
		return typeRessourceRepository.save(type);
	}

}

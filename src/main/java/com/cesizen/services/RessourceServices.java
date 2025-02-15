package com.cesizen.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cesizen.DTO.RessourceDTO;
import com.cesizen.model.Ressource;
import com.cesizen.repository.RessourceRepository;

@Service
public class RessourceServices {
	@Autowired
	private RessourceRepository ressourceRepository;

	public List<Ressource> getAllRessource() {
		return (List<Ressource>) ressourceRepository.findAll();
	}

	public Ressource saveRessource(Ressource ressource) {
		return ressourceRepository.save(ressource);
	}

	LocalDate today = LocalDate.now();

	public RessourceDTO toDTO(Ressource ressource) {
		/**
		 * 
		 * String ressourceType;
		 * 
		 * if (ressource instanceof Article) { ressourceType = "article"; } else if
		 * (ressource instanceof Test) { ressourceType = "test"; } else { throw new
		 * IllegalArgumentException("Unknown ressource type"); }
		 * 
		 */
		return new RessourceDTO(ressource.getId(), ressource.getTitle(), ressource.getHeaderImage(),
				ressource.getHeaderIntroduction(), ressource.getPublicationDate(), ressource.isStatut());
//Attention il faudra revoir les champs à ajouter
	}

}

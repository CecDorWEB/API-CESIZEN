package com.cesizen.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cesizen.DTO.RessourceDTO;
import com.cesizen.model.Article;
import com.cesizen.model.Ressource;
import com.cesizen.model.Test;
import com.cesizen.model.TypeRessource;
import com.cesizen.model.User;
import com.cesizen.repository.RessourceRepository;
import com.cesizen.repository.TypeRessourceRepository;
import com.cesizen.repository.UserRepository;

@Service
public class RessourceServices {
	@Autowired
	private RessourceRepository ressourceRepository;

	@Autowired
	private TypeRessourceRepository typeRessourceRepository;

	@Autowired
	private UserRepository userRepository;

	// Récupérer toutes les ressources
	public List<Ressource> getAllRessources() {
		return (List<Ressource>) ressourceRepository.findAll();
	}

	// Récupérer tous les articles
	public List<Ressource> getAllArticles() {
		return (List<Ressource>) ressourceRepository.findAllArticles();
	}

	// Récupérer l'article avec l'id de mon choix
	public List<Ressource> getArticleById(Long articleId) {
		return (List<Ressource>) ressourceRepository.findArticleByArticleId(articleId);
	}

	// Récupérer tous les tests
	public List<Ressource> getAllTests() {
		return (List<Ressource>) ressourceRepository.findAllTests();
	}

	public Ressource saveRessource(Ressource ressource) {
		return ressourceRepository.save(ressource);
	}

	LocalDate today = LocalDate.now();

	public RessourceDTO toDTO(Ressource ressource) {
		Long ressourceType;

		if (ressource instanceof Article) {
			ressourceType = 1L;
		} else if (ressource instanceof Test) {
			ressourceType = 2L;
		} else {
			throw new IllegalArgumentException("Unknown ressource type");
		}

		return new RessourceDTO(ressource.getId(), ressource.getTitle(), ressource.getHeaderImage(),
				ressource.getHeaderIntroduction(), ressource.getPublicationDate(), ressource.getUpdateDate(),
				ressource.isStatut(), ressourceType, ressource.getUser().getId());

	}

	// Transformer le modèle DTO (incomplet) en Entité Ressource correspondant aux
	// //
	// champs de la BDD
	public Ressource toEntity(RessourceDTO ressourceDTO)

	{
		// ajouter les if pour les classes abstraites
		Ressource ressource;

		if (ressourceDTO.type_id().equals(1L)) {
			ressource = new Article();
		} else if (ressourceDTO.type_id().equals(2L)) {
			ressource = new Test();
		} else {
			throw new IllegalArgumentException("Invalid ressource type ID: " + ressourceDTO.type_id());
		}

		ressource.setId(ressourceDTO.id());
		ressource.setTitle(ressourceDTO.title());
		ressource.setHeaderImage(ressourceDTO.headerImage());
		ressource.setHeaderIntroduction(ressourceDTO.headerIntroduction());
		ressource.setPublicationDate(Date.valueOf(LocalDate.now()));
		ressource.setUpdateDate(null);
		ressource.setStatut(true);

		// Récupérer le type de la ressource
		TypeRessource typeRessource = typeRessourceRepository.findById(ressourceDTO.type_id())
				.orElseThrow(() -> new RuntimeException("Type with ID " + ressourceDTO.type_id() + "not found"));
		ressource.setTypeRessource(typeRessource);

		// Récupérer l'id du user à l'origine de la création de la ressource
		User user = userRepository.findById(ressourceDTO.user_id())
				.orElseThrow(() -> new RuntimeException("Type with ID " + ressourceDTO.user_id() + "not found"));
		ressource.setUser(user);

		return ressource;

	}

}

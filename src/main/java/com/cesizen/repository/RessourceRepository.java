package com.cesizen.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cesizen.model.Ressource;

@Repository
public interface RessourceRepository extends JpaRepository<Ressource, Long> {

	// Récupérer tous les articles
	@Query("SELECT r FROM Ressource r WHERE TYPE(r) = Article")
	List<Ressource> findAllArticles();

	// Récupérer toutes les ressources autorisées par type
	@Query("SELECT r FROM Ressource r WHERE r.statut = true AND r.typeRessource.id = :ressourceTypeId")
	List<Ressource> findAllRessourcesAllowedByType(@Param("ressourceTypeId") Long ressourceTypeId);

	// Récupérer l'article avec l'id de mon choix
	@Query("Select r FROM Ressource r WHERE TYPE(r) = Article AND r.id = :articleId")
	Ressource findArticleByArticleId(@Param("articleId") Long articleId);

	// Récupérer tous les tests
	@Query("SELECT r FROM Ressource r WHERE TYPE(r) = Test")
	List<Ressource> findAllTests();
}

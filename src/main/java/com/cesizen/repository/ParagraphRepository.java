package com.cesizen.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cesizen.model.Paragraph;

@Repository
public interface ParagraphRepository extends JpaRepository<Paragraph, Long> {

	@Query("SELECT p FROM Paragraph p WHERE p.article.id = :ressourceId ORDER BY paragraphOrder ASC")
	List<Paragraph> findParagraphByArticleId(@Param("ressourceId") Long ressourceId);
}

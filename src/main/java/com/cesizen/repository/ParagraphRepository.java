package com.cesizen.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cesizen.model.Paragraph;

@Repository
public interface ParagraphRepository extends JpaRepository<Paragraph, Long> {

	List<Paragraph> findParagraphByArticleId(Long articleId);
}

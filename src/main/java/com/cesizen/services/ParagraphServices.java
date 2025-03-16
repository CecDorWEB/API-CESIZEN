package com.cesizen.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cesizen.DTO.ParagraphDTO;
import com.cesizen.model.Article;
import com.cesizen.model.Paragraph;
import com.cesizen.model.Ressource;
import com.cesizen.repository.ParagraphRepository;
import com.cesizen.repository.RessourceRepository;

@Service
public class ParagraphServices {
	@Autowired
	private ParagraphRepository paragraphRepository;

	@Autowired
	private RessourceRepository ressourceRepository;

	public List<Paragraph> getParagraphsByArticleId(Long articleId) {
		return paragraphRepository.findParagraphByArticleId(articleId);
	}

	public ParagraphDTO toDTO(Paragraph paragraph) {
		return new ParagraphDTO(paragraph.getId(), paragraph.getParagraphOrder(), paragraph.getTitle(),
				paragraph.getBody(), paragraph.getVisualSupport(), paragraph.getAltVisualSupport());
	}

	/* Création Paragraphe */
	public Paragraph createParagraph(Long articleId, Paragraph paragraph) {

		Ressource ressource = ressourceRepository.findArticleByArticleId(articleId);

		if (ressource == null) {
			throw new RuntimeException("The provided resource is not an Article");
		}

		Article article = (Article) ressource;
		paragraph.setArticle(article);
		return paragraphRepository.save(paragraph);
	}

	public boolean deleteParagraph(Long paragraphId) {
		if (paragraphRepository.existsById(paragraphId)) {
			paragraphRepository.deleteById(paragraphId);
			return true;
		}
		return false;
	}
}

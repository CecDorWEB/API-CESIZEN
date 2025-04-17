package com.cesizen.services;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cesizen.DTO.ParagraphDTO;
import com.cesizen.model.Article;
import com.cesizen.model.Paragraph;
import com.cesizen.model.Ressource;
import com.cesizen.repository.ParagraphRepository;
import com.cesizen.repository.RessourceRepository;

import jakarta.persistence.EntityNotFoundException;

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

	/* Modifier paragraphe */
	public Paragraph updateParagraph(Paragraph paragraph, Long paragraphId) {
		Paragraph parDB = paragraphRepository.findById(paragraphId)
				.orElseThrow(() -> new EntityNotFoundException("Paragraphe non trouvé avec l'ID : " + paragraphId));
		;

		if (Objects.nonNull(paragraph.getParagraphOrder())) {
			parDB.setParagraphOrder(paragraph.getParagraphOrder());
		}

		if (Objects.nonNull(paragraph.getTitle()) && !"".equalsIgnoreCase(paragraph.getTitle())) {
			parDB.setTitle(paragraph.getTitle());
		}

		if (Objects.nonNull(paragraph.getBody()) && !"".equalsIgnoreCase(paragraph.getBody())) {
			parDB.setBody(paragraph.getBody());
		}

		if (Objects.nonNull(paragraph.getVisualSupport()) && !"".equalsIgnoreCase(paragraph.getVisualSupport())) {
			parDB.setVisualSupport(paragraph.getVisualSupport());
		}

		if (Objects.nonNull(paragraph.getAltVisualSupport()) && !"".equalsIgnoreCase(paragraph.getAltVisualSupport())) {
			parDB.setAltVisualSupport(paragraph.getAltVisualSupport());
		}

		return paragraphRepository.save(parDB);
	}

	/* Supprimer paragraphe */
	public boolean deleteParagraph(Long paragraphId) {
		if (paragraphRepository.existsById(paragraphId)) {
			paragraphRepository.deleteById(paragraphId);
			return true;
		}
		return false;
	}
}

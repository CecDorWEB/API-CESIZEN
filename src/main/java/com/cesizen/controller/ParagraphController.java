package com.cesizen.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cesizen.DTO.ParagraphDTO;
import com.cesizen.model.Paragraph;
import com.cesizen.services.ParagraphServices;

@RestController
@RequestMapping("/ressource/article")
public class ParagraphController {

	private final ParagraphServices paragraphService;

	public ParagraphController(ParagraphServices paragraphService) {
		this.paragraphService = paragraphService;
	}

	@GetMapping("/{articleId}/paragraph")
	public List<ParagraphDTO> getparagraphsbyArticleId(@PathVariable Long articleId) {
		return paragraphService.getParagraphsByArticleId(articleId).stream().map(paragraphService::toDTO)
				.collect(Collectors.toList());
	}

	// Ajouter un paragraphe
	@PostMapping("/{articleId}/paragraph")
	public ResponseEntity<ParagraphDTO> createParagraph(@PathVariable long articleId,
			@RequestBody Paragraph paragraph) {

		Paragraph createdParagraph = paragraphService.createParagraph(articleId, paragraph);

		ParagraphDTO dto = paragraphService.toDTO(createdParagraph);
		return ResponseEntity.ok(dto);
	}

	// Modifier un paragraphe
	@PutMapping("/paragraph/{paragraphId}/modify")
	public ResponseEntity<ParagraphDTO> updateParagraph(@RequestBody Paragraph paragraph,
			@PathVariable("paragraphId") Long paragraphId) {

		Paragraph updatedParagraph = paragraphService.updateParagraph(paragraph, paragraphId);

		// Convertir l'objet en DTO avant de le retourner
		ParagraphDTO updatedDTO = paragraphService.toDTO(updatedParagraph);
		return ResponseEntity.ok(updatedDTO);
	}

	// Supprimer un paragraphe
	@DeleteMapping("/paragraph/{paragraphId}")
	public ResponseEntity<String> deleteParagraph(@PathVariable Long paragraphId) {
		boolean deleted = paragraphService.deleteParagraph(paragraphId);
		if (deleted) {
			return ResponseEntity.ok("Paragraphe supprimé avec succès !");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paragraphe introuvable !");
		}
	}

}

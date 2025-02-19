package com.cesizen.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cesizen.model.Paragraph;
import com.cesizen.services.ParagraphServices;

@RestController
@RequestMapping("/ressource/article/{articleId}/paragraph")
public class ParagraphController {

	private final ParagraphServices paragraphService;

	public ParagraphController(ParagraphServices paragraphService) {
		this.paragraphService = paragraphService;
	}

	@GetMapping
	public ResponseEntity<List<Paragraph>> getparagraphsbyArticleId(@PathVariable Long articleId) {
		List<Paragraph> paragraphs = paragraphService.getParagraphsByArticleId(articleId);
		return ResponseEntity.ok(paragraphs);
	}

	@PostMapping
	public ResponseEntity<Paragraph> createParagraph(@PathVariable long articleId, @RequestBody Paragraph paragraph) {

		Paragraph createdParagraph = paragraphService.createParagraph(articleId, paragraph);

		return ResponseEntity.ok(createdParagraph);
	}

}

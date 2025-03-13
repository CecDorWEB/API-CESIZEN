package com.cesizen.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cesizen.DTO.ParagraphDTO;
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
	public List<ParagraphDTO> getparagraphsbyArticleId(@PathVariable Long articleId) {
		return paragraphService.getParagraphsByArticleId(articleId).stream().map(paragraphService::toDTO)
				.collect(Collectors.toList());
	}

	@PostMapping
	public ResponseEntity<ParagraphDTO> createParagraph(@PathVariable long articleId,
			@RequestBody Paragraph paragraph) {

		Paragraph createdParagraph = paragraphService.createParagraph(articleId, paragraph);

		ParagraphDTO dto = paragraphService.toDTO(createdParagraph);
		return ResponseEntity.ok(dto);
	}

}

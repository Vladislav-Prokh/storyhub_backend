package app.english.server.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.english.server.entity.EnglishText;
import app.english.server.service.EnglishTextService;

@RequestMapping("/texts")
@RestController
public class EnglishTextController {

	@Autowired
	private EnglishTextService textService;

	@GetMapping("/all")
	public List<EnglishText> findAll() {
		return textService.findAllTexts();
	}

	@GetMapping("/{id}")
	public ResponseEntity<EnglishText> findById(@PathVariable("id") Long id) {
		Optional<EnglishText> englishText = textService.findById(id);
		if (englishText.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(englishText.get());
	}

}

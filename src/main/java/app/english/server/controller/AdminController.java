package app.english.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.english.server.entity.EnglishText;
import app.english.server.service.EnglishTextService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private EnglishTextService textService;

	@PostMapping("/delete/text/{id}")
	public void deleteTextById(@PathVariable("id") Long id) {
		textService.deleteById(id);
	}

	@PostMapping("/add/text")
	public EnglishText deleteTextById(@RequestBody EnglishText text) {
		return textService.addText(text);
	}
}

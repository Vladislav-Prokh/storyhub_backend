package app.english.server.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.english.server.entity.EnglishText;
import app.english.server.entity.Translation;
import app.english.server.repository.EnglishTextRepository;
import app.english.server.repository.TranslationRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class EnglishTextService {

	@Autowired
	private EnglishTextRepository textRepository;

	@Autowired
	private TranslationRepository translationRepository;

	public List<EnglishText> findAllTexts() {
		return textRepository.findAll();
	}

	public Optional<EnglishText> findById(Long id) {
		return this.textRepository.findById(id);
	}

	@Transactional
	public void deleteById(Long id) {
		EnglishText text = textRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Text not found"));
		translationRepository.deleteAllByEnglishText(text);
		textRepository.delete(text);
	}

	@Transactional
	public EnglishText addText(EnglishText text) {

		Set<Translation> textTranslation = text.getTranslations();
		EnglishText savedText = textRepository.save(text);

		for (Translation translation : textTranslation) {
			translation.setEnglishText(savedText);
			if (translation.getTranslation_id() == 0) {
				translation.setTranslation_id(null);
			}
		}
		translationRepository.saveAll(textTranslation);

		return savedText;
	}
}

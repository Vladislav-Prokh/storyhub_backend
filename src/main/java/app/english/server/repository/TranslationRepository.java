package app.english.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.english.server.entity.EnglishText;
import app.english.server.entity.Translation;

@Repository
public interface TranslationRepository extends JpaRepository<Translation, Long> {
	void deleteAllByEnglishText(EnglishText text);
}

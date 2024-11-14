package app.english.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.english.server.entity.EnglishText;

@Repository
public interface EnglishTextRepository extends JpaRepository<EnglishText, Long> {

}

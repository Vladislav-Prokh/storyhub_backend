package app.english.server.entity;

import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "texts")
public class EnglishText {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long text_id;

	private String title;

	@Column(length = 2000)
	private String content;

	private String content_level;
	@Column(length = 300)
	private String mainImagePath;
	@JsonManagedReference
	@OneToMany(mappedBy = "englishText", fetch = FetchType.LAZY, cascade = CascadeType.MERGE, orphanRemoval = true)
	private Set<Translation> translations;

	@Override
	public int hashCode() {
		return Objects.hash(text_id, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		EnglishText that = (EnglishText) obj;
		return Objects.equals(text_id, that.text_id) && Objects.equals(title, that.title);
	}
}

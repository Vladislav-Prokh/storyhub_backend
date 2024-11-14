package app.english.server.entity;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "translations")
public class Translation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long translation_id;

	@Column(length = 2000)
	private String translation;
	@Column(length = 2)
	private String languageCode;
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "text_id")
	private EnglishText englishText;

	@Override
	public int hashCode() {
		return Objects.hash(translation_id, languageCode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Translation that = (Translation) obj;
		return Objects.equals(translation_id, that.translation_id) && Objects.equals(languageCode, that.languageCode);
	}
}
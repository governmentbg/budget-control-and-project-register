package bg.infosys.daeu.db.entity.pub;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Audited
@Entity
@Table(name = "n_translation_languages", schema="public")
public class TranslationLanguage {

	@Id
	@Column(name = _code)
	private String code;
	public static final String _code = "code";
	
	@Column(name = _name)
	private String name;
	public static final String _name = "name";
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public enum TranslationLanguagesEnum {
		BG("BG", "bg", "BG"),
		EN("EN", "en", "US"),
	    ES("ES", "es", "ES"),
	    DE("DE", "de", "DE"),
	    FR("FR", "fr", "FR");

	    private final String code;
	    private final String language;
	    private final String country;

	    TranslationLanguagesEnum(String code, String language, String country){
	        this.code = code;
	        this.language = language;
	        this.country = country;
	    }

	    public String getCode() {
	        return code;
	    }

		public String getLanguage() {
			return language;
		}

		public String getCountry() {
			return country;
		}
	}
}

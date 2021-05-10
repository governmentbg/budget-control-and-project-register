package bg.infosys.daeu.db.entity.pub;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import bg.infosys.daeu.db.entity.projects.ProjectFormConfig;

@Audited
@Entity
@Table(name = "translations_project_config", schema="public")
public class TranslationProjectConfig {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "translation_id")
	private ETranslation eTranslation;
	public static final String _eTranslation = "eTranslation";
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "config_id")
	private ProjectFormConfig config;
	public static final String _config = "config";
	
	@ManyToOne()
	@JoinColumn(name = "lang_code")
	private TranslationLanguage language;
	public static final String _language = "language";
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public ETranslation geteTranslation() {
		return eTranslation;
	}
	public void seteTranslation(ETranslation eTranslation) {
		this.eTranslation = eTranslation;
	}
	public ProjectFormConfig getConfig() {
		return config;
	}
	public void setConfig(ProjectFormConfig config) {
		this.config = config;
	}
	public TranslationLanguage getLanguage() {
		return language;
	}
	public void setLanguage(TranslationLanguage language) {
		this.language = language;
	}
	
}

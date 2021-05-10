package bg.infosys.daeu.db.dto;

import bg.infosys.daeu.db.entity.budgets.Auxheader;
import bg.infosys.daeu.db.entity.budgets.FormConfig;
import bg.infosys.daeu.db.entity.projects.ProjectFormConfig;
import bg.infosys.daeu.db.entity.pub.TranslationLanguage;
import bg.infosys.daeu.db.entity.tech_specs.TechSpecsFormConfig;

public class TranslateConfigDTO {

	private Integer id;
	private String labelName;
	private TranslationLanguage targetLanguage;
	private FormConfig budgetConfig;
	private Auxheader budgetAuxheader;
	private ProjectFormConfig projectConfig;
	private TechSpecsFormConfig techSpecsConfig;
	private Integer translationID;
	
	public TranslateConfigDTO() {}
	
	public TranslateConfigDTO(Integer id, String labelName, FormConfig budgetConfig, Auxheader budgetAuxheader, ProjectFormConfig projectConfig,
			TechSpecsFormConfig techSpecsConfig) {
		this.id = id;
		this.labelName = labelName;
		this.budgetConfig = budgetConfig;
		this.budgetAuxheader = budgetAuxheader;
		this.projectConfig = projectConfig;
		this.techSpecsConfig = techSpecsConfig;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLabelName() {
		return labelName;
	}
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}
	public TranslationLanguage getTargetLanguage() {
		return targetLanguage;
	}
	public void setTargetLanguage(TranslationLanguage targetLanguage) {
		this.targetLanguage = targetLanguage;
	}

	public FormConfig getBudgetConfig() {
		return budgetConfig;
	}

	public void setBudgetConfig(FormConfig budgetConfig) {
		this.budgetConfig = budgetConfig;
	}

	public Auxheader getBudgetAuxheader() {
		return budgetAuxheader;
	}

	public void setBudgetAuxheader(Auxheader budgetAuxheader) {
		this.budgetAuxheader = budgetAuxheader;
	}

	public ProjectFormConfig getProjectConfig() {
		return projectConfig;
	}

	public void setProjectConfig(ProjectFormConfig projectConfig) {
		this.projectConfig = projectConfig;
	}

	public TechSpecsFormConfig getTechSpecsConfig() {
		return techSpecsConfig;
	}

	public void setTechSpecsConfig(TechSpecsFormConfig techSpecsConfig) {
		this.techSpecsConfig = techSpecsConfig;
	}

	public Integer getTranslationID() {
		return translationID;
	}

	public void setTranslationID(Integer translationID) {
		this.translationID = translationID;
	}
	
}

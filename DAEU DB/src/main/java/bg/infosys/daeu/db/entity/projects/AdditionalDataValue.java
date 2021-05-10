package bg.infosys.daeu.db.entity.projects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Audited
@Entity
@Table(schema = "projects", name = "additional_data_values")
public class AdditionalDataValue implements Comparable<AdditionalDataValue> {
	@Id
	@SequenceGenerator(name = "additional_data_values_seq", sequenceName = "projects.additional_data_values_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "additional_data_values_seq")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "additional_data_id")
	private AdditionalData additionalData;
	public static final String _additionalData = "additionalData";

	@Column(name = _value)
	private String value;
	public static final String _value = "value";

	@Column(name = _argumentation)
	private String argumentation;
	public static final String _argumentation = "argumentation";

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_form_config_id")
	private ProjectFormConfig projectFormConfig;
	public static final String _projectFormConfig = "projectFormConfig";

	public AdditionalDataValue() {}

	public AdditionalDataValue(AdditionalData additionalData, ProjectFormConfig projectFormConfig) {
		this.additionalData = additionalData;
		this.projectFormConfig = projectFormConfig;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public AdditionalData getAdditionalData() {
		return additionalData;
	}

	public void setAdditionalData(AdditionalData additionalData) {
		this.additionalData = additionalData;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getArgumentation() {
		return argumentation;
	}

	public void setArgumentation(String argumentation) {
		this.argumentation = argumentation;
	}

	public ProjectFormConfig getProjectFormConfig() {
		return projectFormConfig;
	}

	public void setProjectFormConfig(ProjectFormConfig projectFormConfig) {
		this.projectFormConfig = projectFormConfig;
	}

	public AdditionalDataValue copyFrom(AdditionalData data, AdditionalDataValue that) {
		if (that != null) {
			this.additionalData = data;
			this.value = that.value;
			this.argumentation = that.argumentation;
			this.projectFormConfig = that.projectFormConfig;
		}
		return this;
	}


	@Override 
	public int compareTo(AdditionalDataValue o) { 
		return this.getProjectFormConfig().getOrderNum().compareTo(o.getProjectFormConfig().getOrderNum()); 
	}

}

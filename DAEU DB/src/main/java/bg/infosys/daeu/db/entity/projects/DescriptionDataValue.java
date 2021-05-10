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
@Table(schema = "projects", name = "description_data_values")
public class DescriptionDataValue  implements Comparable<DescriptionDataValue> {
	@Id
	@SequenceGenerator(name = "description_data_values_seq", sequenceName = "projects.description_data_values_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "description_data_values_seq")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "description_data_id")
	private DescriptionData descriptionData;
	public static final String _descriptionData = "descriptionData";

	@Column(name = _value)
	private String value;
	public static final String _value = "value";

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_form_config_id")
	private ProjectFormConfig projectFormConfig;
	public static final String _projectFormConfig = "projectFormConfig";

	public DescriptionDataValue() {}

	public DescriptionDataValue(DescriptionData descriptionData, ProjectFormConfig projectFormConfig) {
		this.descriptionData = descriptionData;
		this.projectFormConfig = projectFormConfig;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public DescriptionData getDescriptionData() {
		return descriptionData;
	}

	public void setDescriptionData(DescriptionData descriptionData) {
		this.descriptionData = descriptionData;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public ProjectFormConfig getProjectFormConfig() {
		return projectFormConfig;
	}

	public void setProjectFormConfig(ProjectFormConfig projectFormConfig) {
		this.projectFormConfig = projectFormConfig;
	}

	public DescriptionDataValue copyFrom(DescriptionData data, DescriptionDataValue that) {
		if (that != null) {
			this.descriptionData = data;
			this.value = that.value;
			this.projectFormConfig = that.projectFormConfig;
		}
		return this;
	}


	@Override 
	public int compareTo(DescriptionDataValue o) { 
		return this.getProjectFormConfig().getOrderNum().compareTo(o.getProjectFormConfig().getOrderNum()); 
	}

}

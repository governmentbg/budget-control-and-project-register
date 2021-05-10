package bg.infosys.daeu.db.entity.projects;

import java.math.BigDecimal;

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
@Table(schema = "projects", name = "isun_indicator_values")
public class IsunIndicatorValue {
	@Id
	@SequenceGenerator(name = "isun_indicator_values_seq", sequenceName = "projects.isun_indicator_values_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "isun_indicator_values_seq")
	private Integer id;
	
	@Column(name = _name)
	private String name;
	public static final String _name = "name";
	
	@Column(name = _tendency)
	private String tendency;
	public static final String _tendency = "tendency";
	
	@Column(name = _unit)
	private String unit;
	public static final String _unit = "unit";
	
	@Column(name = "base_value")
	private BigDecimal baseValue;
	public static final String _baseValue = "baseValue";
	
	@Column(name = "target_value")
	private BigDecimal targetValue;
	public static final String _targetValue = "targetValue";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_main_data_id")
	private ProjectMainData projectMainData;
	public static final String _projectMainData = "projectMainData";
	
	public IsunIndicatorValue() {}
	
	public IsunIndicatorValue(ProjectMainData projectMainData) {
		this.projectMainData = projectMainData;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTendency() {
		return tendency;
	}

	public void setTendency(String tendency) {
		this.tendency = tendency;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public BigDecimal getBaseValue() {
		return baseValue;
	}

	public void setBaseValue(BigDecimal baseValue) {
		this.baseValue = baseValue;
	}

	public BigDecimal getTargetValue() {
		return targetValue;
	}

	public void setTargetValue(BigDecimal targetValue) {
		this.targetValue = targetValue;
	}

	public ProjectMainData getProjectMainData() {
		return projectMainData;
	}

	public void setProjectMainData(ProjectMainData projectMainData) {
		this.projectMainData = projectMainData;
	}

	public IsunIndicatorValue copyFrom(ProjectMainData data, IsunIndicatorValue that) {
		if (that != null) {
			this.tendency = that.tendency;
			this.unit = that.unit;
			this.baseValue = that.baseValue;
			this.targetValue = that.targetValue;
			this.projectMainData = data;
		}
		return this;
	}
}

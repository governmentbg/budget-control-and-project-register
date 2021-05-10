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

import bg.infosys.daeu.db.entity.pub.Indicator;

@Audited
@Entity
@Table(schema = "projects", name = "indicator_values")
public class IndicatorValue implements Comparable<IndicatorValue>{
	@Id
	@SequenceGenerator(name = "indicator_values_seq", sequenceName = "projects.indicator_values_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "indicator_values_seq")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "indicator_id")
	private Indicator indicator;
	public static final String _indicator = "indicator";
	
	@Column(name = "current_value_num")
	private Integer currentValueNum;
	public static final String _currentValueNum = "currentValueNum";
	
	@Column(name = "current_value_string")
	private String currentValueString;
	public static final String _currentValueString = "currentValueString";
	
	@Column(name = "other_name")
	private String otherName;
	public static final String _otherName = "otherName";
	
	@Column(name = "target_value")
	private Integer targetValue;
	public static final String _targetValue = "targetValue";
	
	@Column(name = "value_type")
	private ValueType valueType;
	public static final String _valueType = "valueType";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_main_data_id")
	private ProjectMainData projectMainData;
	public static final String _projectMainData = "projectMainData";
	
	public IndicatorValue() {}
	
	public IndicatorValue(ProjectMainData projectMainData, Indicator indicator) {
		this.projectMainData = projectMainData;
		this.indicator = indicator;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Indicator getIndicator() {
		return indicator;
	}
	
	public void setIndicator(Indicator indicator) {
		this.indicator = indicator;
	}
	
	public Integer getCurrentValueNum() {
		return currentValueNum;
	}

	public void setCurrentValueNum(Integer currentValueNum) {
		this.currentValueNum = currentValueNum;
	}

	public String getCurrentValueString() {
		return currentValueString;
	}

	public void setCurrentValueString(String currentValueString) {
		this.currentValueString = currentValueString;
	}

	public Integer getTargetValue() {
		return targetValue;
	}
	
	public void setTargetValue(Integer targetValue) {
		this.targetValue = targetValue;
	}

	public ValueType getValueType() {
		return valueType;
	}

	public void setValueType(ValueType valueType) {
		this.valueType = valueType;
	}

	public ProjectMainData getProjectMainData() {
		return projectMainData;
	}

	public void setProjectMainData(ProjectMainData projectMainData) {
		this.projectMainData = projectMainData;
	}

	public String getOtherName() {
		return otherName;
	}

	public void setOtherName(String otherName) {
		this.otherName = otherName;
	}

	public IndicatorValue copyFrom(ProjectMainData data, IndicatorValue that) {
		if (that != null) {
			this.indicator = that.indicator;
			this.currentValueNum = that.currentValueNum;
			this.currentValueString = that.currentValueString;
			this.targetValue = that.targetValue;
			this.valueType = that.valueType;
			this.projectMainData = data;
			this.otherName = that.otherName;
		}
		return this;
	}
	
	public enum ValueType {
		PERCENT	("%",	"Процент"),
		NUM		("",	"Стойност");
		
		private final String code;
		private final String name;

		private ValueType(String code, String name) {
			this.code = code;
			this.name = name;
		}

		public String getCode() {
			return code;
		}

		public String getName() {
			return name;
		}
	}

	@Override
	public int compareTo(IndicatorValue o) {
		if(this.getIndicator() != null && o.getIndicator() != null) {
			return this.getIndicator().getId() - o.getIndicator().getId();
		}else if(this.getIndicator() != null || o.getIndicator() != null) {
			return 1;
		}
		return 0;
	}
}

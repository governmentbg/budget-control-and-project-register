package bg.infosys.daeu.db.entity.projects;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.Hibernate;
import org.hibernate.envers.Audited;

@Audited
@Entity
@Table(schema = "projects", name = "project_main_data")
public class ProjectMainData {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "project_version_id")
	private ProjectVersion projectVersion;
	public static final String _projectVersion = "projectVersion";
	
	@OneToMany(mappedBy = "projectMainData", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<ExecutionData> executionDatas;
	public static final String _executionDatas = "executionDatas";
	
	@OneToMany(mappedBy = "projectMainData", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@OrderBy
	private SortedSet<ProjectMainDataValue> mainDataValues;
	public static final String _mainDataValues = "mainDataValues";
	
	@OneToMany(mappedBy = "projectMainData", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@OrderBy
	private SortedSet<IndicatorValue> indicatorValues;
	public static final String _indicatorValues = "indicatorValues";
	
	@OneToMany(mappedBy = "projectMainData", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@OrderBy
	private Set<IsunIndicatorValue> isunIndicatorValues;
	public static final String _isunIndicatorValues = "isunIndicatorValues";
	
	public ProjectMainData() {
		this.executionDatas = new LinkedHashSet<ExecutionData>();
		this.mainDataValues = new TreeSet<ProjectMainDataValue>();
		this.indicatorValues = new TreeSet<IndicatorValue>();
		this.isunIndicatorValues = new HashSet<IsunIndicatorValue>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Set<ExecutionData> getExecutionDatas() {
		return executionDatas;
	}

	public void setExecutionDatas(LinkedHashSet<ExecutionData> executionDatas) {
		this.executionDatas = executionDatas;
	}

	public SortedSet<ProjectMainDataValue> getMainDataValues() {
		return mainDataValues;
	}

	public void setMainDataValues(SortedSet<ProjectMainDataValue> mainDataValues) {
		this.mainDataValues = mainDataValues;
	}

	public ProjectVersion getProjectVersion() {
		return projectVersion;
	}

	public void setProjectVersion(ProjectVersion projectVersion) {
		this.projectVersion = projectVersion;
	}

	public SortedSet<IndicatorValue> getIndicatorValues() {
		return indicatorValues;
	}

	public void setIndicatorValues(SortedSet<IndicatorValue> indicatorValues) {
		this.indicatorValues = indicatorValues;
	}

	public Set<IsunIndicatorValue> getIsunIndicatorValues() {
		return isunIndicatorValues;
	}

	public void setIsunIndicatorValues(Set<IsunIndicatorValue> isunIndicatorValues) {
		this.isunIndicatorValues = isunIndicatorValues;
	}

	public ProjectMainData copyFrom(ProjectVersion version, ProjectMainData that) {
		if (that != null) {
			this.projectVersion = version;
			this.executionDatas = new LinkedHashSet<ExecutionData>();
			for (ExecutionData data : that.executionDatas) {
				data = (ExecutionData) Hibernate.unproxy(data);
				ExecutionData tmp = new ExecutionData().copyFrom(this, data);
				this.executionDatas.add(tmp);
				
				for (AttachedDoc attachedDoc : version.getAttachedDoc()) {
					if (attachedDoc.getExecutionData() != null && attachedDoc.getExecutionData().getId() != null &&
							attachedDoc.getExecutionData().getId().equals(data.getId())) { //FIXME NPE If no check for null id
						attachedDoc.setExecutionData(tmp);
					}
				}
			}
			this.mainDataValues = new TreeSet<ProjectMainDataValue>();
			for (ProjectMainDataValue value : that.mainDataValues) {
				this.mainDataValues.add(new ProjectMainDataValue().copyFrom(this, value));
			}
			this.indicatorValues = new TreeSet<IndicatorValue>();
			for (IndicatorValue value : that.indicatorValues) {
				this.indicatorValues.add(new IndicatorValue().copyFrom(this, value));
			}
			this.isunIndicatorValues = new HashSet<IsunIndicatorValue>();
			for (IsunIndicatorValue value : that.isunIndicatorValues) {
				this.isunIndicatorValues.add(new IsunIndicatorValue().copyFrom(this, value));
			}
		}
		return this;
	}
}

package bg.infosys.daeu.db.entity.projects;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.envers.Audited;

import bg.infosys.daeu.db.entity.pub.CPVCode;

@Audited
@Entity
@Table(schema = "projects", name = "execution_data")
public class ExecutionData {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_main_data_id")
	private ProjectMainData projectMainData;
	public static final String _projectMainData = "projectMainData";
	
	@Column(name = "order_num")
	private Integer orderNum;
	public static final String _orderNum = "orderNum";
	
	@OneToMany(mappedBy = "executionData", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@OrderBy
	private SortedSet<ExecutionValue> executionValues;
	public static final String _executionValues = "executionValues";
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "additional_data_id")
	private AdditionalData additionalData;
	public static final String _additionalData = "additionalData";
	
	@ManyToMany(fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.JOIN)
	@JoinTable(name = "execution_datas_cpv", schema="projects",
			joinColumns = @JoinColumn(name="execution_data_id"),
			inverseJoinColumns = @JoinColumn(name = "cpv_id"))
	private Set<CPVCode> cpvCodes;
	public static final String _cpvCodes = "cpvCodes";
	
	public ExecutionData() {
		this.executionValues = new TreeSet<ExecutionValue>();
		this.cpvCodes = new TreeSet<CPVCode>();
	}

	public ExecutionData(ProjectMainData projectMainData) {
		this.projectMainData = projectMainData;
		this.executionValues = new TreeSet<ExecutionValue>();
		this.cpvCodes = new TreeSet<CPVCode>();
		this.additionalData = new AdditionalData();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ProjectMainData getProjectMainData() {
		return projectMainData;
	}

	public void setProjectMainData(ProjectMainData projectMainData) {
		this.projectMainData = projectMainData;
	}

	public SortedSet<ExecutionValue> getExecutionValues() {
		return executionValues;
	}

	public void setExecutionValues(SortedSet<ExecutionValue> executionValues) {
		this.executionValues = executionValues;
	}
	
	public Set<CPVCode> getCpvCodes() {
		return cpvCodes;
	}

	public void setCpvCodes(Set<CPVCode> cpvCodes) {
		this.cpvCodes = cpvCodes;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public AdditionalData getAdditionalData() {
		return additionalData;
	}

	public void setAdditionalData(AdditionalData additionalData) {
		this.additionalData = additionalData;
	}

	public ExecutionData copyFrom(ProjectMainData data, ExecutionData that) {
		if (that != null) {
			this.cpvCodes = that.cpvCodes;
			this.projectMainData = data;
			this.executionValues = new TreeSet<ExecutionValue>();
			this.orderNum = that.orderNum;
			this.additionalData = new AdditionalData().copyFrom(that.additionalData, this);
			for (ExecutionValue value : that.executionValues) {
				this.executionValues.add(new ExecutionValue().copyFrom(this, value));
			}
		}
		return this;
	}
}

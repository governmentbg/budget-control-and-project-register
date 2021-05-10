package bg.infosys.daeu.db.entity.projects;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Audited
@Entity
@Table(schema = "projects", name = "additional_data")
public class AdditionalData {
	@Id
	@SequenceGenerator(name = "additional_data_seq", sequenceName = "projects.additional_data_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "additional_data_seq")
	private Integer id;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "execution_data_id") private ExecutionData executionData;
	public static final String _executionData = "executionData";
	
	@OneToMany(mappedBy = "additionalData", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@OrderBy
	private SortedSet<AdditionalDataValue> additionalDataValues;
	public static final String _additionalDataValues = "additionalDataValues";
	
	public AdditionalData() {
		this.additionalDataValues = new TreeSet<AdditionalDataValue>();
	}

	public AdditionalData(ExecutionData executionData) {
		this.executionData = executionData;
		this.additionalDataValues = new TreeSet<AdditionalDataValue>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ExecutionData getExecutionData() { return executionData; }
	 
	public void setExecutionData(ExecutionData executionData) {
		this.executionData = executionData; 
	}

	public SortedSet<AdditionalDataValue> getAdditionalDataValues() {
		return additionalDataValues;
	}

	public void setAdditionalDataValues(SortedSet<AdditionalDataValue> additionalDataValues) {
		this.additionalDataValues = additionalDataValues;
	}

	public AdditionalData copyFrom(AdditionalData that, ExecutionData data) {
		if (that != null) {
			this.executionData = data;
			for (AdditionalDataValue value : that.additionalDataValues) {
				this.additionalDataValues.add(new AdditionalDataValue().copyFrom(this, value));
			}
		}
		return this;
	}
}

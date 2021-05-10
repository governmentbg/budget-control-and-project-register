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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Audited
@Entity
@Table(schema = "projects", name = "description_data")
public class DescriptionData{
	@Id
	@SequenceGenerator(name = "description_data_seq", sequenceName = "projects.description_data_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "description_data_seq")
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "additional_data_id")
	private AdditionalData additionalData;
	public static final String _additionalData = "additionalData";
	
	@OneToMany(mappedBy = "descriptionData", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@OrderBy
	private SortedSet<DescriptionDataValue> descriptionDataValues;
	public static final String _descriptionDataValues = "descriptionDataValues";
	
	public DescriptionData() {
		this.descriptionDataValues = new TreeSet<DescriptionDataValue>();
	}

	public DescriptionData(AdditionalData additionalData) {
		this.additionalData = additionalData;
		this.descriptionDataValues = new TreeSet<DescriptionDataValue>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public SortedSet<DescriptionDataValue> getDescriptionDataValues() {
		return descriptionDataValues;
	}

	public void setDescriptionDataValues(SortedSet<DescriptionDataValue> descriptionDataValues) {
		this.descriptionDataValues = descriptionDataValues;
	}

	public AdditionalData getAdditionalData() {
		return additionalData;
	}

	public void setAdditionalData(AdditionalData additionalData) {
		this.additionalData = additionalData;
	}

	public DescriptionData copyFrom(AdditionalData data, DescriptionData that) {
		if (that != null) {
			this.additionalData = data;
			for (DescriptionDataValue value : that.descriptionDataValues) {
				this.descriptionDataValues.add(new DescriptionDataValue().copyFrom(this, value));
			}
		}
		return this;
	}

}

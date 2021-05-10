package bg.infosys.daeu.db.entity.tech_specs;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

import bg.infosys.daeu.db.entity.pub.ComponentType;

@Audited
@Entity
@Table(schema = "tech_specs" ,name = "n_tech_row_types")
public class TechRowType {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = _id)
	private Integer id;
	public static final String _id = "id";
	
	@Column(name = _code, nullable = false)
	@Length(max = 32)
	@NotNull
	private String code;
	public static final String _code = "code";
	
	@ManyToOne
	@JoinColumn(name = "component_type_code")
	private ComponentType componentType;
	public static final String _componentType = "componentType";
	
	
	@OneToMany(mappedBy = "techRowType", fetch = FetchType.LAZY)
	@OrderBy
	@Where(clause = "is_valid = 'Y'")
	private SortedSet<TechSpecsType> numenclatures;
	public static final String _numenclatures = "numenclatures";
	
	public TechRowType() {
		numenclatures = new TreeSet<TechSpecsType>();
	}

	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public ComponentType getComponentType() {
		return componentType;
	}
	
	public void setComponentType(ComponentType componentType) {
		this.componentType = componentType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public SortedSet<TechSpecsType> getNumenclatures() {
		return numenclatures;
	}

	public void setNumenclatures(SortedSet<TechSpecsType> numenclatures) {
		this.numenclatures = numenclatures;
	}
}

package bg.infosys.daeu.db.entity.projects;

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

import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;

import bg.infosys.daeu.db.entity.pub.ComponentType;

@Audited
@Entity
@Table(schema = "projects", name = "n_row_types")
public class RowType {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = _id)
	private Integer id;
	public static final String _id = "id";
	
	@Column(name = "code", nullable = false)
	private String code;
	public static final String _code = "code";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "component_type_code")
	private ComponentType componentType;
	public static final String  _componentType = "componentType";
	
	@OneToMany(mappedBy = "projectRowType", fetch = FetchType.LAZY)
	@OrderBy
	@Where(clause = "is_valid = 'Y'")
	private SortedSet<ProjectNomenclatures> numenclatures;
	public static final String _numenclatures = "numenclatures";

	public RowType() {
		numenclatures = new TreeSet<ProjectNomenclatures>();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public SortedSet<ProjectNomenclatures> getNumenclatures() {
		return numenclatures;
	}

	public void setNumenclatures(SortedSet<ProjectNomenclatures> numenclatures) {
		this.numenclatures = numenclatures;
	}
	
}

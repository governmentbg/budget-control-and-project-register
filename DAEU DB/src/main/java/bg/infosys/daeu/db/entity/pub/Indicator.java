package bg.infosys.daeu.db.entity.pub;

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
@Table(name = "n_indicators")
public class Indicator {
	@Id
	@SequenceGenerator(name = "indicators__seq", sequenceName = "n_indicators_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "n_indicators_id_seq")
	private Integer id;
	
	@Column(name = _code)
	private String code;
	public static final String _code = "code";
	
	@Column(name = _name)
	private String name;
	public static final String _name = "name";
	
	@Column(name = _definition)
	private String definition;
	public static final String _definition = "definition";
	
	@Column(name = "is_road_map_indicator")
	private String isRoadMapIndicator;
	public static final String _isRoadMapIndicator = "isRoadMapIndicator";
	
	@Column(name = "isun_key")
	private String isunKey;
	public static final String _isunKey = "isunKey";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "value_type")
	private ComponentType type;
	public static final String _type = "type";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private Indicator parent;
	public static final String _parent = "parent";
	
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
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDefinition() {
		return definition;
	}
	
	public void setDefinition(String definition) {
		this.definition = definition;
	}
	
	public ComponentType getType() {
		return type;
	}
	
	public void setType(ComponentType type) {
		this.type = type;
	}
	
	public Indicator getParent() {
		return parent;
	}
	
	public void setParent(Indicator parent) {
		this.parent = parent;
	}
	
	public String getIsRoadMapIndicator() {
		return isRoadMapIndicator;
	}
	
	public void setIsRoadMapIndicator(String isRoadMapIndicator) {
		this.isRoadMapIndicator = isRoadMapIndicator;
	}
	
	public String getIsunKey() {
		return isunKey;
	}

	public void setIsunKey(String isunKey) {
		this.isunKey = isunKey;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Indicator other = (Indicator) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
}

package bg.infosys.daeu.db.entity.pub;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Audited
@Entity
@Table(name = "n_organization_types", schema = "public")
public class OrganizationType {

	@Id
	@SequenceGenerator(name = "organization_types_seq", sequenceName = "n_organization_types_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "organization_types_seq")
	private Integer id;
	
	@Column(name = _name)
	private String name;
	public static final String _name = "name";
	
	@Column(name = "is_valid")
	private String isValid;
	public static final String _isValid = "isValid";
	
	@Column(name = _type)
	private String type;
	public static final String _type = "type";
	
	@Column(name = _code)
	private String code;
	public static final String _code = "code";
	
	/* Getters && Setters */
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
	public String isValid() {
		return isValid;
	}
	public void setValid(String isValid) {
		this.isValid = isValid;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		OrganizationType that = (OrganizationType) o;

		return new EqualsBuilder()
				.append(this.getName(), that.getName())
				.append(this.isValid(),that.isValid())
				.isEquals();

	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(getId())
				.append(getName())
				.append(isValid())
				.toHashCode();
	}
	
	public enum OrganizationTypeConst {
		
		AO ("AO", "административен орган"),
		PUBLIC ("PUBLIC", "лице, осъществяващо публични функции"),
		ORGANIZATION ("ORGANIZATION", "организация, предоставяща обществени услуги");
		
		private final String code;
		private final String name;

		private OrganizationTypeConst(String code, String name) {
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

}

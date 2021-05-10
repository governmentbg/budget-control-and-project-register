package bg.infosys.daeu.db.entity.pub;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import bg.infosys.common.utils.Strings;

@Audited
@Entity
@Table(name = "n_letter_types")
public class LetterType {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = _description)
	private String description;
	public static final String _description = "description";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "module_type_code")
	private ModuleType moduleType;
	public static final String _moduleType = "moduleType";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "final_status_id")
	private Status finalStatus;
	public static final String _finalStatus = "finalStatus";
	
	@Column(name = "is_valid")
	private String isValid;
	public static final String _isValid = "isValid";
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public ModuleType getModuleType() {
		return moduleType;
	}

	public void setModuleType(ModuleType moduleType) {
		this.moduleType = moduleType;
	}
	
	public Status getFinalStatus() {
		return finalStatus;
	}

	public void setFinalStatus(Status finalStatus) {
		this.finalStatus = finalStatus;
	}

	public String getIsValid() {
		return isValid;
	}
	
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	
	public LetterType copyFrom(LetterType that) {
		if (that != null) {
			this.id = that.id;
			this.description = that.description;
			this.moduleType = that.moduleType;
			this.isValid = that.isValid;
			this.finalStatus = that.finalStatus;
		}
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		LetterType other = (LetterType) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public boolean isChanged(LetterType type) {
		if (this.id == null && (!Strings.isEmpty(this.description) || this.isValid.equals("N") || this.finalStatus != null)) return true;
		else if (this.id != null && (!this.description.equals(type.getDescription()) || !this.isValid.equals(type.getIsValid())
				|| (!this.finalStatus.getId().equals(type.finalStatus.getId())))) return true;
		return false;
	}
}

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

import bg.infosys.common.utils.Strings;

@Audited
@Entity
@Table(name = "n_mail_types")
public class MailType {
	@Id
	@SequenceGenerator(name = "mail_types_seq", sequenceName = "n_mail_types_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mail_types_seq")
	private Integer id;
	
	@Column( name = _description)
	private String description;
	public static final String _description = "description";
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "module_type_id")
	private ModuleType moduleType;
	public static final String _moduleType = "moduleType";
	
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
	
	public String getIsValid() {
		return isValid;
	}
	
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public MailType copyFrom(MailType that) {
		if (that != null) {
			this.id = that.id;
			this.description = that.description;
			this.moduleType = that.moduleType;
			this.isValid = that.isValid;
		}
		return this;
	}

	public boolean isChanged(MailType type) {
		if (this.id <= 0 && (!Strings.isEmpty(this.description) || this.isValid.equals("N"))) return true;
		else if (this.id > 0 && (!this.description.equals(type.getDescription()) || !this.isValid.equals(type.getIsValid()))) return true;
		return false;
	}
}

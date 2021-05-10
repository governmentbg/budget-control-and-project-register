package bg.infosys.daeu.db.entity.budgets;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Audited
@Entity
@Table(schema = "budgets", name = "n_change_types")
public class ChangeType {

	@Id
	@SequenceGenerator(name = "n_change_types_seq", sequenceName = "budgets.n_change_types_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "n_change_types_seq")
	private Integer id;
	
	@Column(name = _name)
	private String name;
	public static final String _name = "name";
	
	@Column(name = "is_valid")
	private String isValid;
	public static final String _isValid = "isValid";
	
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
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	
}

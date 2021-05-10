package bg.infosys.daeu.db.entity.pub;

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
@Table(name = "n_execution_statuses")
public class ExecutionStatus {
	@Id
	@SequenceGenerator(name = "execution_statuses_seq", sequenceName = "n_execution_statuses_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "execution_statuses_seq")
	private Integer id;
	
	@Column(name = _name)
	private String name;
	public static final String _name = "name";
	
	@Column(name = "is_valid")
	private String isValid;
	public static final String _isValid = "isValid";
	
	@Column(name = _code)
	private String code;
	public static final String _code = "code";
	
	public ExecutionStatus() {}
	
	
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
	
	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}

	public enum ExecutionStatusConst {
		
		SUBMITTED	 	("SUBMITTED",		"Подадено"),
		PROCESSED    	("PROCESSED", 		"В процес на оценка"),
		NOT_APPROVED 	("NOT_APPROVED", 	"Неодобрен"),
		FOR_EXECUTE  	("FOR_EXECUTE", 	"В изпълнение"),
		CANCELED    	("CANCELED", 		"Прекратен"),
		COMPLETED    	("COMPLETED", 		"Успешно приключил"),
		APPROVED    	("APPROVED", 		"Одобрен");
		
		private final String code;
		private final String name;

		private ExecutionStatusConst(String code, String name) {
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

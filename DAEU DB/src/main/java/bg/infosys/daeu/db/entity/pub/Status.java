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
@Table(name = "n_statuses")
public class Status implements Comparable<Status>{
	@Id
	@SequenceGenerator(name = "statuses_seq", sequenceName = "n_statuses_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "statuses_seq")
	private Integer id;
	public static final String _id = "id";
	
	@Column(name = _name)
	private String name;
	public static final String _name = "name";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "module_type_code")
	private ModuleType moduleType;
	public static final String _moduleType = "moduleType";
	
	@Column(name = "is_final_stage")
	private Boolean isFinalStage;
	public static final String _isFinalStage = "isFinalStage";
	
	@Column(name = "is_valid")
	private String isValid;
	public static final String _isValid = "isValid";
	
	@Column(name = _code)
	private String code;
	public static final String _code = "code";
	
	@Column(name = "order_num")
	private Integer orderNum;
	public static final String _orderNum = "orderNum";
	
	public Status() {}
	
	public Status(ModuleType type) {
		this.moduleType = type;
	}
	
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
	public ModuleType getModuleType() {
		return moduleType;
	}
	public void setModuleType(ModuleType moduleType) {
		this.moduleType = moduleType;
	}
	public Boolean getIsFinalStage() {
		return isFinalStage;
	}
	public void setIsFinalStage(Boolean isFinalStage) {
		this.isFinalStage = isFinalStage;
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

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
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
		Status other = (Status) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public int compareTo(Status o) {
		return this.orderNum - o.getOrderNum();
	}
	
	public enum StatusConst {
//		PROJECTS_NEW			("P",	"Ново"),								BUDGETS_NEW				("BC",	"Ново"),
//		PROJECTS_SENT			("P",	"Изпратено към ДАЕУ"),					BUDGETS_SENT			("BC",	"Изпратено към ДАЕУ"),
//		PROJECTS_INCOMING		("P",	"Входирано"),							BUDGETS_INCOMING		("BC",	"Входирано"),
//		PROJECTS_DISTRIBUTED	("P",	"Разпределено"),						BUDGETS_DISTRIBUTED		("BC",	"Разпределено"),
//		PROJECTS_PROCESSED		("P",	"В процес"),							BUDGETS_PROCESSED		("BC",	"В процес"),
//		PROJECTS_RETURNED		("P",	"Върнато за корекция"),					BUDGETS_RETURNED		("BC",	"Върнато за корекция"),
//		PROJECTS_CONTROL		("P",	"За контрол"),							BUDGETS_CONTROL			("BC",	"За контрол"),
//		PROJECTS_WAITING		("P",	"Очаква решение"),						BUDGETS_WAITING			("BC",	"Очаква решение"),
//		PROJECTS_RESOLVED		("P",	"Приключен контролен лист с решение"),	BUDGETS_RESOLVED		("BC",	"Приключен контролен лист с решение"),
//		PROJECTS_COORDINATION	("P",	"Изходящо писмо за съгласуване"),		BUDGETS_COORDINATION	("BC",	"Изходящо писмо за съгласуване"),
//		PROJECTS_FOR_OUTGOING	("P",	"За изходиране"),						BUDGETS_FOR_OUTGOING	("BC",	"За изходиране"),
//		PROJECTS_OUTGOING		("P",	"Изходирано"),							BUDGETS_OUTGOING		("BC",	"Изходирано"),
//		PROJECTS_APPROVED		("P",	"Утвърдено"),							BUDGETS_APPROVED		("BC",	"Утвърдено"),
//		PROJECTS_NOT_APPROVED	("P",	"Не утвърдено"),						BUDGETS_NOT_APPROVED	("BC",	"Не утвърдено"),
//		PROJECTS_APPROVED_MARKS	("P",	"Утвърдено с бележки"),					BUDGETS_APPROVED_MARKS	("BC",	"Утвърдено с бележки"),
//		PROJECTS_DOESNT_FIT_ZEU ("P", "Не попада в обхвата на ЗЕУ"),			BUDGETS_FINALIZED       ("BC",  "Финализирано"),
//		
	/* TECH_SPECS_NEW ("TS", "Ново"), */						
	/*	TECH_SPECS_SENT				("TS",	"Изпратено към ДАЕУ"),	*/			
		/*TECH_SPECS_INCOMING			("TS",	"Входирано"),	*/					
		/*TECH_SPECS_DISTRIBUTED		("TS",	"Разпределено"),*/					
		/*TECH_SPECS_PROCESSED		("TS",	"В процес"),	*/					
		/*TECH_SPECS_RETURNED			("TS",	"Върнато за корекция"),	*/			
		/*TECH_SPECS_CONTROL			("TS",	"За контрол"),		*/				
		/*TECH_SPECS_WAITING			("TS",	"Очаква решение"),*/					
		/* TECH_SPECS_RESOLVED ("TS", "Приключен контролен лист с решение"), */
		/* TECH_SPECS_COORDINATION ("TS", "Изходящо писмо за съгласуване"), */	
		/* TECH_SPECS_FOR_OUTGOING ("TS", "За изходиране"), */					
		/*TECH_SPECS_OUTGOING			("TS",	"Изходирано"),*/						
		/* TECH_SPECS_APPROVED ("TS", "Утвърдено"), */						
		/* TECH_SPECS_NOT_APPROVED ("TS", "Не утвърдено"), */					
		/* TECH_SPECS_APPROVED_MARKS ("TS", "Утвърдено с бележки"), */			
		/* TECH_SPECS_DOESNT_FIT_ZEU ("TS", "Не попада в обхвата на ЗЕУ"), */      
							
		NEW,
		SENT,
		INCOMING,
		DISTRIBUTED,
		PROCESSED,
		RETURNED,
		CONTROL,
		WAITING,
		RESOLVED,
		COORDINATION,
		FOR_OUTGOING,
		OUTGOING,
		APPROVED,
		NOT_APPROVED,
		APPROVED_MARKS,
		DOESNT_FIT_ZEU,
		FINALIZED,
		PENDING,
		WITHDRAWN,
		SENT_TOKEN,
		REGISTERED,
		NOT_REGISTERED;
		
//		private final String code;
//		private final String name;

		private StatusConst(/*String code , String name */) {
//			this.code = code;
//			this.name = name;
		}
//
//		public String getCode() {
//			return code;
//		}

//		public String getName() {
//			return name;
//		}
//		
//		public static List<StatusConst> valuesByType(String type) {
//			return Arrays.asList(StatusConst.values()).stream().filter(c -> c.name().equals(type)).collect(Collectors.toList());
//		}
	}
}

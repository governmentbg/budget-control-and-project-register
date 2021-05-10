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
@Table(name = "n_mail_templates")
public class MailTemplate {
	@Id
	@SequenceGenerator(name = "mail_templates_seq", sequenceName = "n_mail_templates_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mail_templates_seq")
	private Integer id;
	
	@Column(name = "mail_subject")
	private String subject;
	public static final String _subject = "subject";
	
	@Column(name = "mail_text")
	private String text;
	public static final String _text = "text";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mail_type_id")
	private MailType type;
	public static final String _type = "type";
	
	@Column(name = "is_valid")
	private String isValid;
	public static final String _isValid = "isValid";
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public MailType getType() {
		return type;
	}
	public void setType(MailType type) {
		this.type = type;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	//FIXME check this
	public enum MailTemplateConst {
		BUDGETS_CH_DECISION			("BC",	"Контролен лист в очаква решение"),
		BUDGETS_CH_RETURNED			("BC",	"Върнат контролен лист за допълнителна проверка"),
		BUDGETS_CH_CONTROL			("BC",	"Попълнен контролен лист"),
		BUDGETS_CH_PROCESS			("BC",	"Контролен лист в процес"),
		BUDGETS_CH_FINALIZED		("BC",	"Финализиран контролен лист"),
		BUDGETS_APPROVED			("BC",	"Бюджетен контрол с избран финален статус"),
		BUDGETS_CREATED_LETTER		("BC",	"Създадено писмо за Бюджетен контрол"),
		BUDGETS_FOR_COORDINATION	("BC",	"Писмо за съгласуване за Бюджетен контрол"),
		BUDGETS_COMMENT				("BC",	"Получен коментар за Бюджетен контрол"),
		BUDGETS_FOR_FINISHED		("BC",	"Съгласувано писмо с решение за Бюджетен контрол"),
		BUDGETS_FOR_OUTGOING		("BC",	"Получен Бюджетен контрол с писмо с решение"),
		BUDGETS_DISTRIBUTED			("BC",	"Разпределен Бюджетен контрол"),
		BUDGETS_OUTGOING			("BC",	"Изведен Бюджетен контрол"),
		BUDGETS_INCOMING			("BC",	"Въведен Бюджетен контрол"),
		BUDGETS_RECEIVED_PROPOSAL 	("BC",	"Получен Бюджетен контрол"),
		BUDGETS_RETURNED 			("BC",	"Бюджетен контрол върнат за корекция"),
		
		PROJECTS_CH_DECISION		("P",	"Контролен лист в очаква решение"),
		PROJECTS_CH_RETURNED		("P",	"Върнат контролен лист за допълнителна проверка"),
		PROJECTS_CH_CONTROL			("P",	"Попълнен контролен лист"),
		PROJECTS_CH_PROCESS			("P",	"Контролен лист в процес"),
		PROJECTS_CH_FINALIZED		("P",	"Финализиран контролен лист"),
		PROJECTS_APPROVED			("P",	"Проектно предложение или дейност с избран финален статус"),
		PROJECTS_CREATED_LETTER		("P",	"Създадено писмо за Проектно предложение или дейност"),
		PROJECTS_FOR_COORDINATION	("P",	"Писмо за съгласуване за Проектно предложение или дейност"),
		PROJECTS_COMMENT			("P",	"Получен коментар за Проектно предложение или дейност"),
		PROJECTS_FOR_FINISHED		("P",	"Съгласувано писмо с решение за Проектно предложение или дейност"),
		PROJECTS_FOR_OUTGOING		("P",	"Получено Проектно предложение или дейност с писмо с решение"),
		PROJECTS_DISTRIBUTED		("P",	"Разпределено Проектно предложение или дейност"),
		PROJECTS_OUTGOING			("P",	"Изведено Проектно предложение или дейност"),
		PROJECTS_INCOMING			("P",	"Въведено Проектно предложение или дейност"),
		PROJECTS_RECEIVED_PROPOSAL 	("P",	"Получено Проектно предложение или дейност"),
		PROJECTS_RETURNED 			("P",	"Проектно предложение или дейност върнато за корекция"),
		
		TECH_SPECS_CH_DECISION		("TS",	"Контролен лист в очаква решение"),
		TECH_SPECS_CH_RETURNED		("TS",	"Върнат контролен лист за допълнителна проверка"),
		TECH_SPECS_CH_CONTROL		("TS",	"Попълнен контролен лист"),
		TECH_SPECS_CH_PROCESS		("TS",	"Контролен лист в процес"),
		TECH_SPECS_CH_FINALIZED		("TS",	"Финализиран контролен лист"),
		TECH_SPECS_APPROVED			("TS",	"Техническа спецификация с избран финален статус"),
		TECH_SPECS_CREATED_LETTER	("TS",	"Създадено писмо за Техническа спецификация"),
		TECH_SPECS_FOR_COORDINATION	("TS",	"Писмо за съгласуване за Техническа спецификация"),
		TECH_SPECS_COMMENT			("TS",	"Получен коментар за Техническа спецификация"),
		TECH_SPECS_FOR_FINISHED		("TS",	"Съгласувано писмо с решение за Техническа спецификация"),
		TECH_SPECS_FOR_OUTGOING		("TS",	"Получена Техническа спецификация с писмо с решение"),
		TECH_SPECS_DISTRIBUTED		("TS",	"Разпределена Техническа спецификация"),
		TECH_SPECS_OUTGOING			("TS",	"Изведена Техническа спецификация"),
		TECH_SPECS_INCOMING			("TS",	"Въведена Техническа спецификация"),
		TECH_SPECS_RECEIVED_PROPOSAL ("TS",	"Получено предложение за Техническа спецификация"),
		TECH_SPECS_RETURNED 		("TS",	"Техническа спецификация върната за корекция"),
		TECH_SPECS_NOT_FIT_ZEU 		("TS",	"Техническа спецификация не попада в обхвата на ЗЕУ"),
		TECH_SPECS_FOR_IS 			("TS",	"Техническа спецификация е за Информационна система"),
		
		RESET_PASSWORD			  		("REG",	"Възстановена парола"),
		REGISTRATION_SEND_REQUEST  		("REG",	"Изпратена заявка"),
		REGISTRATION_RECEIVED_REQUEST  	("REG",	"Получена заявка"),
		REGISTRATION_NEW_TOKEN			("REG",	"Нов ключ"),
		REGISTRATION_APPROVED			("REG",	"Одобрена регистрация"),
		REGISTRATION_REFUSED			("REG",	"Отхвърлена регистрация");
		
		private final String code;
		private final String description;

		private MailTemplateConst(String code, String description) {
			this.code = code;
			this.description = description;
		}

		public String getCode() {
			return code;
		}

		public String getDescription() {
			return description;
		}
	}

	public MailTemplate copyFrom(MailTemplate that) {
		if (that != null) {
			this.id = that.id;
			this.subject = that.subject;
			this.text = that.text;
			this.isValid = that.isValid;
			this.type = that.type;
		}
		return this;
	}
	
	public boolean isChanged(MailTemplate template) {
		if (this.id <= 0 && (!Strings.isEmpty(this.subject) || (!Strings.isEmpty(this.text) || this.type != null || this.isValid.equals("N")))) return true;
		else if (this.id > 0 && (!this.subject.equals(template.getSubject()) || (!this.text.equals(template.getText())
				|| !this.type.equals(template.type) || !this.isValid.equals(template.getIsValid())))) return true;
		return false;
	}
}

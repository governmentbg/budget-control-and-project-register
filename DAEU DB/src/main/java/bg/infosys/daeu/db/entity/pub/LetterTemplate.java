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
@Table(name = "n_letter_templates")
public class LetterTemplate {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name")
	private String name;
	public static final String _name = "name";
	
	@Column(name = "subject")
	private String subject;
	public static final String _subject = "subject";
	
	@Column(name = "main_text")
	private String mainText;
	public static final String _mainText = "mainText";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "letter_type_id")
	private LetterType type;
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
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMainText() {
		return mainText;
	}
	
	public void setMainText(String mainText) {
		this.mainText = mainText;
	}
	
	public LetterType getType() {
		return type;
	}
	
	public void setType(LetterType type) {
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

	public LetterTemplate copyFrom(LetterTemplate that) {
		if (that != null) {
			this.id = that.id;
			this.name = that.name;
			this.mainText = that.mainText;
			this.subject = that.subject;
			this.type = new LetterType().copyFrom(that.type);
			this.isValid = that.isValid;
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
		LetterTemplate other = (LetterTemplate) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
	
	public boolean isChanged(LetterTemplate template) {
		if (this.id == null && (!Strings.isEmpty(this.mainText) || this.type != null
				|| (!Strings.isEmpty(this.subject) || this.isValid.equals("N")))) return true;
		else if (this.id != null && (!this.mainText.equals(template.getMainText()) || (!this.subject.equals(template.getSubject())
				|| !this.type.equals(template.type) || !this.isValid.equals(template.getIsValid())))) return true;
		return false;
	}
}

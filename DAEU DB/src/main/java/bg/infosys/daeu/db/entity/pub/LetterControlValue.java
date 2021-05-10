package bg.infosys.daeu.db.entity.pub;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.envers.Audited;

import bg.infosys.daeu.db.entity.security.User;

@Audited
@Entity
@Table(name = "letter_control_values", schema="public")
public class LetterControlValue implements Comparable<LetterControlValue> {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "letter_id")
	private Letter letter;
	public static final String _checklist = "checklist";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	public static final String _user = "user";
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "letter_control_config_id")
	private LetterControlConfig letterControlConfig;
	public static final String _letterControlConfig = "letterControlConfig";
	
	@Column(name = _value)
	private String value;
	public static final String _value = "value";
	
	@Column(name = "is_valid")
	private String isValid;
	public static final String _isValid = "isValid";
	
	@Column(name = "inserted_timestamp")
	private Date insertedTimestamp;
	public static final String _insertedTimestamp = "insertedTimestamp";
	
	@Column(name = "comment")
	private String comment;
	public static final String _comment = "comment";
	
	@Transient
	public boolean canCheck;
	
	public LetterControlValue() {}
	
	public LetterControlValue(Letter letter, LetterControlConfig letterControlConfig) {
		this.letter = letter;
		this.letterControlConfig = letterControlConfig;
		this.isValid = "Y";
		this.insertedTimestamp = new Date();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public Date getInsertedTimestamp() {
		return insertedTimestamp;
	}

	public void setInsertedTimestamp(Date insertedTimestamp) {
		this.insertedTimestamp = insertedTimestamp;
	}

	public Letter getLetter() {
		return letter;
	}

	public void setLetter(Letter letter) {
		this.letter = letter;
	}

	public LetterControlConfig getLetterControlConfig() {
		return letterControlConfig;
	}

	public void setLetterControlConfig(LetterControlConfig letterControlConfig) {
		this.letterControlConfig = letterControlConfig;
	}
	
	public boolean isCanCheck() {
		return canCheck;
	}

	public void setCanCheck(boolean canCheck) {
		this.canCheck = canCheck;
	}
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public LetterControlValue copyFrom(Letter letter, LetterControlValue that) {
		if (that != null) {
			this.user = that.user;
			this.letter = letter;
			this.letterControlConfig = that.letterControlConfig;
			this.insertedTimestamp = that.insertedTimestamp;
			this.isValid = that.isValid;
			this.value = that.value;
			this.comment = that.comment;
		}
		return this;
	}
	
	@Override 
	public int compareTo(LetterControlValue o) {
		return this.getLetterControlConfig().getOrderNum().compareTo(o.getLetterControlConfig().getOrderNum()); 
	}
}

package bg.infosys.daeu.db.entity.pub;

import java.util.Date;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.envers.Audited;

@Audited
@Entity
@Table(name = "letters")
public class Letter {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "letter_text")
	private String text;
	public static final String _text = "text";
	
	@Column(name = "letter_remark")
	private String remark;
	public static final String _remark = "remark";
	
	@Column(name = "insert_timestamp")
	private Date insertTimestamp;
	public static final String _insertTimestamp = "insertTimestamp";
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "letter_template_id")
	private LetterTemplate template;
	public static final String _template = "template";
	
	@OneToMany(mappedBy = "letter", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@OrderBy
	private SortedSet<LetterControlValue> letterControlValue;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "attached_doc_id")
	private LetterAttachedDoc letterAttachedDoc;
	public static final String _letterAttachedDoc = "letterAttachedDoc";
	
	public Letter() {
		letterControlValue = new TreeSet<>();
	}
	
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
	
	public String getRemark() {
		return remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Date getInsertTimestamp() {
		return insertTimestamp;
	}
	
	public void setInsertTimestamp(Date insertTimestamp) {
		this.insertTimestamp = insertTimestamp;
	}
	
	public LetterTemplate getTemplate() {
		return template;
	}
	
	public void setTemplate(LetterTemplate template) {
		this.template = template;
	}
	
	public Set<LetterControlValue> getLetterControlValue() {
		return letterControlValue;
	}

	public void setLetterControlValue(SortedSet<LetterControlValue> letterControlValue) {
		this.letterControlValue = letterControlValue;
	}

	public LetterAttachedDoc getLetterAttachedDoc() {
		return letterAttachedDoc;
	}

	public void setLetterAttachedDoc(LetterAttachedDoc letterAttachedDoc) {
		this.letterAttachedDoc = letterAttachedDoc;
	}

	public Letter copyFrom(Letter that) {
		if (that != null) {
			this.text = that.text;
			this.remark = that.remark;
			this.insertTimestamp = that.insertTimestamp;
			this.template = that.template;//new LetterTemplate().copyFrom(that.template);
			this.letterControlValue = new TreeSet<LetterControlValue>();
			for (LetterControlValue value : that.letterControlValue) {
				this.letterControlValue.add(new LetterControlValue().copyFrom(this, value));
			}
			this.letterAttachedDoc = new LetterAttachedDoc().copyFrom(this, this.letterAttachedDoc);
		}
		return this;
	}
}

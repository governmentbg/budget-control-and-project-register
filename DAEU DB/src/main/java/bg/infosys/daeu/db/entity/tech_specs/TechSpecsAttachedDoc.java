package bg.infosys.daeu.db.entity.tech_specs;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

import bg.infosys.daeu.db.entity.pub.AttachedDocType;

@Audited
@Entity
@Table(schema = "tech_specs" ,name = "tech_specs_attached_docs")
public class TechSpecsAttachedDoc implements Comparable<TechSpecsAttachedDoc> {
	@Id
	@SequenceGenerator(name = "tech_specs_attached_docs_seq", sequenceName = "tech_specs.tech_specs_attached_docs_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tech_specs_attached_docs_seq")
	private Integer id;
	
	@Column(name = "order_num")
	private Integer orderNum;
	public static final String _orderNum = "orderNum";
	
	@Column(name = _description)
	private String description;
	public static final String _description = "description";
	
	@Column(name = "file_name")
	@Length(max = 512)
	private String fileName;
	public static final String _fileName = "fileName";
	
	@Column(name = "file_path")
	private String filePath;
	public static final String _filePath = "filePath";
	
	@Column(name = "file_size")
	private String fileSize;
	public static final String _fileSize = "fileSize";
	
	@Column(name = "inserted_timestamp", columnDefinition = "TIMESTAMP")
	private Date insertedTimestamp;
	public static final String _insertedTimestamp = "insertedTimestamp";
	
	@Column(name = "is_valid")
	@Length(max = 1)
	private String isValid = "Y";
	public static final String _isValid = "isValid";
	
	@ManyToOne
	@JoinColumn(name = "tech_specs_version_id")
	private TechSpecsVersion techSpecsVersion;
	public static final String _techSpecsVersion = "techSpecsVersion";
	
	@ManyToOne  
	@JoinColumn(name = "doc_type_id")
	private AttachedDocType attachedDocType;
	public static final String _attachedDocType = "attachedDocType";
	
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getOrderNum() {
		return orderNum;
	}
	
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public Date getInsertedTimestamp() {
		return insertedTimestamp;
	}
	
	public void setInsertedTimestamp(Date insertedTimestamp) {
		this.insertedTimestamp = insertedTimestamp;
	}
	
	public String getIsValid() {
		return isValid;
	}
	
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	
	public TechSpecsVersion getTechSpecsVersion() {
		return techSpecsVersion;
	}
	
	public void setTechSpecsVersion(TechSpecsVersion techSpecsVersion) {
		this.techSpecsVersion = techSpecsVersion;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public AttachedDocType getAttachedDocType() {
		return attachedDocType;
	}

	public void setAttachedDocType(AttachedDocType attachedDocType) {
		this.attachedDocType = attachedDocType;
	}
	
	public TechSpecsAttachedDoc copyFrom(TechSpecsAttachedDoc that) {
		if (that != null) {
			this.attachedDocType = that.getAttachedDocType();
			this.description = that.description;
			this.fileName = that.getFileName();
			this.filePath = that.getFilePath();
			this.fileSize = that.getFileSize();
			this.isValid = that.getIsValid();
			this.insertedTimestamp = that.getInsertedTimestamp();
			this.orderNum = that.getOrderNum();
		}
		
		return this;
	}

	@Override
	public int compareTo(TechSpecsAttachedDoc o) {
		return this.orderNum - o.getOrderNum();
	}
}

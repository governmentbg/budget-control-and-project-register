package bg.infosys.daeu.db.entity.budgets;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Audited
@Entity
@Table(schema = "budgets", name = "budgets_attached_docs")
public class BudgetsAttachedDoc {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "file_name")
	private String fileName;
	public static final String _fileName = "fileName";
	
	@Column(name = _path)
	private String path;
	public static final String _path = "path";
	
	@Column(name = "inserted_timestamp")
	private Date insertedTimestamp;
	public static final String _insertedTimestamp = "insertedTimestamp";
	
	@Column(name = "is_valid")
	private String isValid;
	public static final String _isValid = "isValid";
	
	@Column(name = "content_type")
	private String contentType;
	public static final String _contentType = "content_type";
	
	@Column(name = "file_size")
	private String fileSize;
	public static final String _fileSize = "fileSize";
	
	@OneToOne
	@JoinColumn(name = "budget_form_version_id")
	private BudgetFormVersion budgetFormVersion;
	public static final String _budgetFormVersion = "budgetFormVersion";
	
	public BudgetsAttachedDoc() {}
	
	public BudgetsAttachedDoc(String fileName, String path, String fileSize, String contentType, BudgetFormVersion budgetFormVersion) {
		this.fileName = fileName;
		this.path = path;
		this.insertedTimestamp = new Date();
		this.isValid = "Y";
		this.fileSize = fileSize;
		this.contentType = contentType;
		this.budgetFormVersion = budgetFormVersion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
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

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public BudgetFormVersion getBudgetFormVersion() {
		return budgetFormVersion;
	}

	public void setBudgetFormVersion(BudgetFormVersion budgetFormVersion) {
		this.budgetFormVersion = budgetFormVersion;
	}

	public void replace(String fileName, String path, String fileSize, String contentType) {
		this.fileName = fileName;
		this.path = path;
		this.insertedTimestamp = new Date();
		this.isValid = "Y";
		this.fileSize = fileSize;
		this.contentType = contentType;
	}
}

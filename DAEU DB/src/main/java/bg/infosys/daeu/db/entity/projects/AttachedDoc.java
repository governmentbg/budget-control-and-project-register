package bg.infosys.daeu.db.entity.projects;

import java.util.Date;

import javax.persistence.CascadeType;
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

import bg.infosys.daeu.db.entity.pub.AttachedDocType;

@Audited
@Entity
@Table(schema = "projects", name = "attached_docs")
public class AttachedDoc implements Comparable<AttachedDoc>{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "order_num")
	private Integer orderNum;
	public static final String _orderNum = "orderNum";
	
	@Column(name = _description)
	private String description;
	public static final String _description = "description";
	
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_version_id")
	private ProjectVersion projectVersion;
	public static final String _projectVersion = "projectVersion";
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "execution_data_id")
	private ExecutionData executionData;
	public static final String _executionData = "executionData";
	
	@ManyToOne  
	@JoinColumn(name = "doc_type_id")
	private AttachedDocType attachedDocType;
	public static final String _attachedDocType = "attachedDocType";
	
	public AttachedDoc() {}
	
	public AttachedDoc(Integer orderNum, String description, String fileName, String path, ProjectVersion projectVersion, ExecutionData executionData,
			String fileSize, String contentType, AttachedDocType attachedDocType) {
		this.orderNum = orderNum;
		this.description = description;
		this.fileName = fileName;
		this.path = path;
		this.insertedTimestamp = new Date();
		this.projectVersion = projectVersion;
		this.executionData = executionData;
		this.isValid = "Y";
		this.fileSize = fileSize;
		this.contentType = contentType;
		this.attachedDocType = attachedDocType;
	}

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

	public ProjectVersion getProjectVersion() {
		return projectVersion;
	}

	public void setProjectVersion(ProjectVersion projectVersion) {
		this.projectVersion = projectVersion;
	}

	public ExecutionData getExecutionData() {
		return executionData;
	}

	public void setExecutionData(ExecutionData executionData) {
		this.executionData = executionData;
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

	public AttachedDocType getAttachedDocType() {
		return attachedDocType;
	}

	public void setAttachedDocType(AttachedDocType attachedDocType) {
		this.attachedDocType = attachedDocType;
	}

	public AttachedDoc copyFrom(ProjectVersion version, AttachedDoc that) {
		if (that != null) {
			this.orderNum = that.orderNum;
			this.description = that.description;
			this.fileName = that.fileName;
			this.path = that.path;
			this.insertedTimestamp = that.insertedTimestamp;
			this.isValid = that.isValid;
			this.projectVersion = version;
			this.executionData = that.executionData;
			this.fileSize = that.fileSize;
			this.contentType = that.contentType;
			this.attachedDocType = that.attachedDocType;
		}
		return this;
	}

	@Override
	public int compareTo(AttachedDoc o) {
		if(this.getOrderNum() != null && o.getOrderNum() != null) {
			return this.getOrderNum().compareTo(o.getOrderNum());
		}
		return 1;
	}
}

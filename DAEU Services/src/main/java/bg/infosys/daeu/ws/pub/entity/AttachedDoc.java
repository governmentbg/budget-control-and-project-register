package bg.infosys.daeu.ws.pub.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "attached_docs")
public class AttachedDoc {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "file_name")
	private String fileName;
	public static final String _fileName = "fileName";
	
	@Column(name = "content_type")
	private String contentType;
	public static final String _contentType = "contentType";
	
	@Column(name = "file_data")
	private String fileData;
	public static final String _fileData = "fileData";
	
	@Column(name = "public_data_id")
	private Integer publicDataId;
	public static final String _publicDataId = "publicDataId";
	
	@Column(name = _module)
	private String module;
	public static final String _module = "module";
	
	@Column(name = "file_type")
	private String fileType;
	public static final String _fileType = "fileType";
	
	public AttachedDoc() {}
	
	public AttachedDoc(String fileName, String contentType, String fileData, Integer publicDataId, String module, String fileType) {
		this.fileName = fileName;
		this.contentType = contentType;
		this.fileData = fileData;
		this.publicDataId = publicDataId;
		this.module = module;
		this.fileType = fileType;
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

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getFileData() {
		return fileData;
	}

	public void setFileData(String fileData) {
		this.fileData = fileData;
	}

	public Integer getPublicDataId() {
		return publicDataId;
	}

	public void setPublicDataId(Integer publicDataId) {
		this.publicDataId = publicDataId;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
}

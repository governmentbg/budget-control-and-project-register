package bg.infosys.daeu.db.entity.registration;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Audited
@Entity
@Table(schema = "registration", name = "registration_docs")
public class RegistrationDoc {
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
	
	@Column(name = "content_type")
	private String contentType;
	public static final String _contentType = "content_type";
	
	@Column(name = "file_size")
	private String fileSize;
	public static final String _fileSize = "fileSize";
	
	public RegistrationDoc() {}
	
	public RegistrationDoc(String fileNameAndExtension, String directory, String size, String contentType) {
		this.fileName = fileNameAndExtension;
		this.path = directory;
		this.fileSize = size;
		this.contentType = contentType;
		this.insertedTimestamp = new Date();
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
}

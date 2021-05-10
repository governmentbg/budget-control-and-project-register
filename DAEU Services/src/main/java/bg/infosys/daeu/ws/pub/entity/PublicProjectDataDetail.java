package bg.infosys.daeu.ws.pub.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "public_project_data_details")
public class PublicProjectDataDetail {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "exec_id")
	private Integer execId;
	public static final String _execId = "execId";
	
	@Column(name = _name)
	private String name;
	public static final String _name = "name";
	
	@Column(name = _value)
	private String value;
	public static final String _value = "value";
	
	@Column(name = _type)
	private String type;
	public static final String _type = "type";
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "project_id")
	private PublicProjectData publicProjectData;
	public static final String _publicProjectData = "publicProjectData";
	
	public PublicProjectDataDetail() {}
	
	public PublicProjectDataDetail(String name, String value) {
		this.name = name;
		this.value = value;
	}
	
	public PublicProjectDataDetail(String name, String value, Integer execId) {
		this.execId = execId;
		this.name = name;
		this.value = value;
	}
	
	public PublicProjectDataDetail(String name, String value, Integer execId, String type, PublicProjectData data) {
		this.execId = execId;
		this.name = name;
		this.value = value;
		this.type = type;
		this.publicProjectData = data;
	}
	
	public PublicProjectDataDetail(String name, String value, PublicProjectData entity, Integer execId) {
		this.name = name;
		this.value = value;
		this.publicProjectData = entity;
		this.execId = execId;
	}

	public Integer getExecId() {
		return execId;
	}

	public void setExecIdId(Integer execId) {
		this.execId = execId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PublicProjectData getPublicProjectData() {
		return publicProjectData;
	}

	public void setPublicProjectData(PublicProjectData publicProjectData) {
		this.publicProjectData = publicProjectData;
	}

	public void setExecId(Integer execId) {
		this.execId = execId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}

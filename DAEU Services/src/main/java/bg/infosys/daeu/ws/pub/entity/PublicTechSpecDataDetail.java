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
@Table(name = "public_tech_spec_data_details")
public class PublicTechSpecDataDetail {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
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
	@JoinColumn(name = "tech_spec_id")
	private PublicTechSpecData publicTechSpecData;
	public static final String _publicTechSpecData = "publicTechSpecData";
	
	public PublicTechSpecDataDetail() {}
	
	public PublicTechSpecDataDetail(String name, String value, String type, PublicTechSpecData data) {
		this.name = name;
		this.value = value;
		this.type = type;
		this.publicTechSpecData = data;
	}

	public PublicTechSpecData getPublicTechSpecData() {
		return publicTechSpecData;
	}

	public void setPublicTechSpecData(PublicTechSpecData publicTechSpecData) {
		this.publicTechSpecData = publicTechSpecData;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}

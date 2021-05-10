package bg.infosys.daeu.db.entity.security;

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

@Entity
@Audited
@Table(schema = "security", name = "distribution_authorities")
public class DistributionAuthority {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "is_valid")
	private String isValid;
	public static final String _isValid = "isValid";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private Authority parent;
	public static final String _parent = "parent";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "child_id")
	private Authority child;
	public static final String _child = "child";
	
	public DistributionAuthority() {}
	
	public DistributionAuthority(Authority parent, Authority child) {
		this.parent = parent;
		this.child = child;
		this.isValid = "Y";
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public Authority getParent() {
		return parent;
	}
	
	public void setParent(Authority parent) {
		this.parent = parent;
	}
	
	public Authority getChild() {
		return child;
	}
	
	public void setChild(Authority child) {
		this.child = child;
	}
}

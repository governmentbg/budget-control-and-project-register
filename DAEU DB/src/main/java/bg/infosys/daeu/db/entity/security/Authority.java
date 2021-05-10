package bg.infosys.daeu.db.entity.security;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.envers.Audited;

import bg.infosys.common.db.entity.security.IAuthority;
import bg.infosys.daeu.db.entity.pub.StatusAuthority;

@Audited
@Entity
@Table(name = "authorities", schema = "security")
public class Authority implements IAuthority {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	public static final String _id = "id";
	
	@Version
	@Column(name = _version)
	private Integer version;
	public static final String _version = "vrsn";
	
	private String name;
	public static final String _name = "name";
	
	private String type;
	public static final String _type = "type";
	
	private String description;
	public static final String _description = "description";
	
	@Column(name = "is_active")
	private boolean active = true;
	public static final String _active = "active";
	
	@Column(name = _internal)
	private String internal = "Y";
	public static final String _internal = "internal";
	
	@Column(name = "is_unique")
	private String unique = "N";
	public static final String _unique = "unique";
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "department_id")
	private Department department;
	public static final String _department = "department";
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "position_id")
	private Position position;
	public static final String _position = "position";
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.JOIN)
	@JoinTable(name = "authorities_authorities", schema="security",
			joinColumns = @JoinColumn(name="parent_id"),
			inverseJoinColumns = @JoinColumn(name = "child_id"))
	private Set<Authority> children;
	public static final String _children = "children";
	
	@OneToMany(mappedBy = "authority", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<StatusAuthority> statusAuthorities;
	public static final String _statusAuthorities = "statusAuthorities";
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getVersion() {
		return version;
	}
	
	public void setVersion(Integer version) {
		this.version = version;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Set<Authority> getChildren() {
		return children;
	}
	
	public void setChildren(Set<Authority> children) {
		this.children = children;
	}

	public String getInternal() {
		return internal;
	}

	public void setInternal(String internal) {
		this.internal = internal;
	}

	public String getUnique() {
		return unique;
	}

	public void setUnique(String unique) {
		this.unique = unique;
	}

	public Set<StatusAuthority> getStatusAuthorities() {
		return statusAuthorities;
	}

	public void setStatusAuthorities(Set<StatusAuthority> statusAuthorities) {
		this.statusAuthorities = statusAuthorities;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Authority other = (Authority) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	public Authority copyFrom(Authority that) {
		if (that != null) {
			this.id = that.id;
			this.version = that.version;
			this.name = that.name;
			this.type = that.type;
			this.description = that.description;
			this.active = that.active;
			this.internal = that.internal;
			this.unique = that.unique;
			this.department = that.department;
			this.position = that.position;
			this.children = that.children;
			this.statusAuthorities = that.statusAuthorities;
		}
		return this;
	}
}

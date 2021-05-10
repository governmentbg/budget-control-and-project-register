package bg.infosys.daeu.db.entity.security;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.envers.Audited;

import bg.infosys.common.db.entity.security.IUser;
import bg.infosys.common.utils.Strings;
import bg.infosys.daeu.db.entity.pub.Subject;

@Audited
@Entity
@Table(name = "users", schema = "security")
public class User implements IUser<Authority> {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	public static final String _id = "id";

	@Version
	private Integer vrsn;

	@Column
	private String username;
	public static final String _username = "username";

	@Column
	private String password;
	public static final String _password = "password";

	@Column
	private Boolean enabled = true;
	public static final String _enabled = "enabled";

	@Column(name = "first_name")
	private String firstName;
	public static final String _firstName = "firstName";

	@Column(name = "last_name")
	private String lastName;
	public static final String _lastName = "lastName";

	@Column
	private String email;
	public static final String _email = "email";

	@Column
	private String phone;
	public static final String _phone = "phone";

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "subject_id")
	private Subject subject;
	public static final String _subject = "subject";

	@ManyToMany(fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.JOIN)
	@JoinTable(name="users_authorities", schema="security",
	joinColumns = @JoinColumn(name="user_id"),
	inverseJoinColumns = @JoinColumn(name="authority_id"))
	private Set<Authority> authorities;
	public static final String _authorities = "authorities";
	
	private String lang;

	public User() {}
	
	public User(String username, String password, String firstName, String lastName, String email, String phone, Subject subject, Authority authority) {
		this.enabled = true;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.subject = subject;
		authorities = new HashSet<Authority>();
		authorities.add(authority);
	}

	public User(Integer id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public User(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVrsn() {
		return vrsn;
	}

	public void setVrsn(Integer vrsn) {
		this.vrsn = vrsn;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Set<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}

	public String getFullName() {
		return this.firstName + " " + this.lastName;
	}
	
	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}

	public String getRoleName() {
		String resultStr = null;
		if(this.authorities != null && !this.authorities.isEmpty()) {
			for (Authority a : this.authorities) {
				if(resultStr == null || resultStr.equals("")) {
					if(a.getName()!=null && !a.getName().equals("")){
						resultStr = a.getName();
					}
				}else{
					if(a.getName()!=null && !a.getName().equals("")){
						resultStr += ", " + a.getName();
					}
				}
			}
		}
		return resultStr;
	}

	public String getRoleDescription() {
		String resultStr = null;
		if(this.authorities != null && !this.authorities.isEmpty()) {
			for (Authority a : this.authorities) {
				if(a != null) {
					if(resultStr == null || resultStr.equals("")) {
						if(a.getDescription()!=null && !a.getDescription().equals("")){
							resultStr = a.getDescription();
						}
					}else{
						if(a.getDescription()!=null && !a.getDescription().equals("")){
							resultStr += ", " + a.getDescription();
						}
					}
				}
			}
		}
		return resultStr;
	}
	
	public boolean isFilled() {
		if (Strings.isEmpty(this.username)) return false;
		if (Strings.isEmpty(this.email)) return false;
		if (Strings.isEmpty(this.firstName)) return false;
		if (Strings.isEmpty(this.lastName)) return false;
		
		return true;
	}
}
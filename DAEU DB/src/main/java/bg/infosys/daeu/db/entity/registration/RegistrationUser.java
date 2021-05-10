package bg.infosys.daeu.db.entity.registration;

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

import bg.infosys.common.utils.Strings;
import bg.infosys.daeu.db.entity.pub.Status;
import bg.infosys.daeu.db.entity.pub.Subject;
import bg.infosys.daeu.db.entity.security.Authority;

@Audited
@Entity
@Table(name = "registration_users", schema = "registration")
public class RegistrationUser {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	public static final String _id = "id";

	@Column
	private String username;
	public static final String _username = "username";

	@Column
	private String password;
	public static final String _password = "password";

	@Column
	private String email;
	public static final String _email = "email";
	
	@Column(name = "first_name")
	private String firstName;
	public static final String _firstName = "firstName";

	@Column(name = "last_name")
	private String lastName;
	public static final String _lastName = "lastName";

	@Column
	private String phone;
	public static final String _phone = "phone";
	
	@Column(name = "is_active")
	private String isActive;
	public static final String _isActive = "isActive";
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "authority_id")
	private Authority authority;
	public static final String _authority = "authority";

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "subject_id")
	private Subject subject;
	public static final String _subject = "subject";
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "document_id")
	private RegistrationDoc doc;
	public static final String _doc = "doc";
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "status_id")
	private Status status;
	public static final String _status = "status";

	public RegistrationUser() {}

	public RegistrationUser(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Authority getAuthority() {
		return authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}

	public String getFullName() {
		return this.firstName + " " + this.lastName;
	}

	public RegistrationDoc getDoc() {
		return doc;
	}

	public void setDoc(RegistrationDoc doc) {
		this.doc = doc;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		RegistrationUser other = (RegistrationUser) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	public boolean isFilled() {
		if (Strings.isEmpty(this.username)) return false;
		if (Strings.isEmpty(this.password)) return false;
		if (Strings.isEmpty(this.email)) return false;
		if (Strings.isEmpty(this.firstName)) return false;
		if (Strings.isEmpty(this.lastName)) return false;
		if (Strings.isEmpty(this.phone)) return false;
		if (this.subject == null) return false;
		
		return true;
	}

	public void deletePersonalInfo() {
		this.password = null;
		this.firstName = null;
		this.lastName = null;
		this.phone = null;
		this.subject = null;
		this.email = null;
		this.isActive = "N";
	}
}
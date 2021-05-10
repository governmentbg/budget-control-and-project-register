package bg.infosys.daeu.db.entity.pub;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.envers.Audited;

@Audited
@Entity
@Table(name = "subject_versions")
public class SubjectVersion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	public static final String _id = "id";

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "subject_id")
	private Subject subject;
	public static final String _subject = "subject";

	@Column(name = "full_name", length = 1000)
	private String fullName;
	public static final String _fullName = "fullName";

	@Column(name = _email)
	private String email;
	public static final String _email = "email";

	@Column(name = _representative)
	private String representative;
	public static final String _representative = "representative";

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "parent_id")
	private SubjectVersion parent;
	public static final String _parent = "parent";

	@Column(name = "insert_timestamp")
	private Date insertTimestamp = new Date();
	public static final String _insertTimestamp = "insertTimestamp";

	@Column(name = "brra_status")
	private String brraStatus;
	public static final String _brraStatus = "brraStatus";
	
	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private Set<SubjectVersion> children;
    public static final String _children = "children";
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public SubjectVersion getParent() {
		return parent;
	}

	public void setParent(SubjectVersion parent) {
		this.parent = parent;
	}

	public String getRepresentative() {
		return representative;
	}

	public void setRepresentative(String representative) {
		this.representative = representative;
	}

	public Date getInsertTimestamp() {
		return insertTimestamp;
	}

	public void setInsertTimestamp(Date insertTimestamp) {
		this.insertTimestamp = insertTimestamp;
	}

	
	public Set<SubjectVersion> getChildren() {
		return children;
	}

	public void setChildren(Set<SubjectVersion> children) {
		this.children = children;
	}

	public String getBrraStatus() {
		return brraStatus;
	}

	public void setBrraStatus(String brraStatus) {
		this.brraStatus = brraStatus;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		SubjectVersion that = (SubjectVersion) o;

		return new EqualsBuilder()
				.append(this.fullName, that.fullName)
				.append(this.email, that.email)
				.append(this.parent, that.parent) //was commented
				.append(this.subject, that.subject)
				.isEquals();

	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(fullName)
				.append(email)
				.append(parent)//was commented
				.append(subject)
				.append(representative)
				.append(brraStatus)
				.toHashCode();
	}

	public boolean hasChanged(SubjectVersion that) {

		if (ObjectUtils.allNotNull(this.parent, that.parent)) {
			if (this.parent.hasChanged(that.parent)) {
				return true;
			}
		}

		return !(new EqualsBuilder()
				.append(this.fullName, that.fullName)
				.append(this.email, that.email)
				.append(this.representative, that.representative)
				.append(this.brraStatus, that.brraStatus)
				.append(this.parent, that.parent)
				.isEquals());
	}

	public static SubjectVersion clone(SubjectVersion versionToClone){
		SubjectVersion version = new SubjectVersion();
		version.setSubject(versionToClone.getSubject()); //was commented
		version.setFullName(versionToClone.getFullName());
		version.setEmail(versionToClone.getEmail());
		version.setParent(versionToClone.getParent());
		version.setRepresentative(versionToClone.getRepresentative());
		version.setBrraStatus(versionToClone.getBrraStatus());
		return version;
	}
}

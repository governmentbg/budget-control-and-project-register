package bg.infosys.daeu.db.entity.pub;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.envers.Audited;

@Audited
@Entity
@Table(name = "subjects")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    public static final String _id = "id";

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "organization_type_id")
    private OrganizationType orgType;
    public static final String _orgType = "orgType";

    @Column(name = "ident_num")
    private String identNum;
    public static final String _identNum = "identNum";

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "last_version_id")
    private SubjectVersion lastVersion;
    public static final String _lastVersion = "lastVersion";

    @Column(name = "is_valid")
    private String isValid = "Y"; 
    public static final String _isValid = "isValid";

    @Column(name = "iisda_number", length = 255)
    private String iisdaNumber;

    @Column(name = "administrative_kind", length = 255)
    private String adminKind;

//    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "open_data_id")
//    private OpenData openData;

    public Subject() {
		this.lastVersion = new SubjectVersion();
		this.lastVersion.setSubject(this);
    }
		
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OrganizationType getOrgType() {
        return orgType;
    }

    public void setOrgType(OrganizationType orgType) {
        this.orgType = orgType;
    }

    public String getIdentNum() {
        return identNum;
    }

    public void setIdentNum(String identNum) {
        this.identNum = identNum;
    }

    public SubjectVersion getLastVersion() {
        return lastVersion;
    }

    public void setLastVersion(SubjectVersion lastVersion) {
        this.lastVersion = lastVersion;
    }

    public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public String getIisdaNumber() {
        return iisdaNumber;
    }

    public void setIisdaNumber(String iisdaNumber) {
        this.iisdaNumber = iisdaNumber;
    }

    public String getAdminKind() {
        return adminKind;
    }

    public void setAdminKind(String adminKind) {
        this.adminKind = adminKind;
    }
//
//    public OpenData getOpenData() {
//        return openData;
//    }
//
//    public void setOpenData(OpenData openData) {
//        this.openData = openData;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subject that = (Subject) o;

        boolean equals = new EqualsBuilder()
                .append(this.orgType, that.orgType)
                .append(this.identNum, that.identNum)
                .append(this.adminKind, that.adminKind)
                .append(this.iisdaNumber, that.iisdaNumber)
                .isEquals();
        return equals;
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hb = new HashCodeBuilder(17, 37)
                .append(orgType)
                .append(identNum)
                .append(isValid)
                .append(iisdaNumber)
                .append(adminKind);

        return hb.toHashCode();
    }

    public boolean hasChanged(Subject that) {

        if (this.getLastVersion().hasChanged(that.getLastVersion())) {
            return true;
        }

        return !new EqualsBuilder()
                .append(identNum, that.getIdentNum())
                .append(adminKind, that.getAdminKind())
                .append(iisdaNumber, that.getIisdaNumber())
                .append(isValid, that.isValid)
                .isEquals();
    }

}
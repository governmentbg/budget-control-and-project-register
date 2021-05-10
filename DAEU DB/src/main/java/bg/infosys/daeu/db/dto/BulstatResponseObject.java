package bg.infosys.daeu.db.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class BulstatResponseObject {

	@XmlElement(name = "Subject")
	protected BulstatSubject subject;
	@XmlElement(name = "Managers")
	protected List<SubjectRelManager> managers;

	public static class BulstatSubject {
		@XmlElement(name = "UIC")
		public UIC uic;
		@XmlElement(name = "LegalEntitySubject")
		public LegalEntity legalEntitySubject;
		@XmlElement(name = "NaturalPersonSubject")
		public NaturalPerson naturalPersonSubject;

		public UIC getUic() {
			return uic;
		}
		public void setUic(UIC uic) {
			this.uic = uic;
		}
		public LegalEntity getLegalEntitySubject() {
			return legalEntitySubject;
		}
		public void setLegalEntitySubject(LegalEntity legalEntitySubject) {
			this.legalEntitySubject = legalEntitySubject;
		}
		public NaturalPerson getNaturalPersonSubject() {
			return naturalPersonSubject;
		}
		public void setNaturalPersonSubject(NaturalPerson naturalPersonSubject) {
			this.naturalPersonSubject = naturalPersonSubject;
		}
	}

	public static class LegalEntity {
		@XmlElement(name = "CyrillicFullName")
		public String cyrillicFullName;

		public String getCyrillicFullName() {
			return cyrillicFullName;
		}
		public void setCyrillicFullName(String cyrillicFullName) {
			this.cyrillicFullName = cyrillicFullName;
		}
	}

	public static class SubjectRelManager {
		@XmlElement(name = "RelatedSubject")
		public BulstatSubject relatedSubject;

		public BulstatSubject getRelatedSubject() {
			return relatedSubject;
		}
		public void setRelatedSubject(BulstatSubject relatedSubject) {
			this.relatedSubject = relatedSubject;
		}
	}

	public static class NaturalPerson {
		@XmlElement(name = "CyrillicName")
		public String cyrillicName;

		public String getCyrillicName() {
			return cyrillicName;
		}
		public void setCyrillicName(String cyrillicName) {
			this.cyrillicName = cyrillicName;
		}
	}

	public static class UIC {
		@XmlElement(name = "UIC")
		public String uic;

		public String getUic() {
			return uic;
		}
		public void setUic(String uic) {
			this.uic = uic;
		}
	}

	/* Getters & Setters */
	public BulstatSubject getSubject() {
		return subject;
	}

	public void setSubject(BulstatSubject subject) {
		this.subject = subject;
	}

	public List<SubjectRelManager> getManagers() {
		return managers;
	}

	public void setManagers(List<SubjectRelManager> managers) {
		this.managers = managers;
	}
}

package bg.infosys.daeu.ws.wsbg.regix.mon;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DiplomaDocumentType", namespace="http://egov.bg/RegiX/MOMN/RDSO", propOrder = {
    "intID",
    "intStudentID",
    "idType",
    "vcName1",
    "vcName2",
    "vcName3",
    "intBPlaceE",
    "codeNationality",
    "boolGender",
    "intDocumentType",
    "vcDocumentName",
    "intYearGraduated",
    "vcPrnSer",
    "vcPrnNo",
    "vcRegNo1",
    "vcRegNo2",
    "dtRegDate",
    "intSchoolID",
    "vcSchoolName",
    "intEKATTE",
    "intVETSpeciality",
    "vcVETSpecialityName",
    "intVETLevel",
    "vcVETLevelName",
    "intVETGroupIdent",
    "vcEducAreaName",
    "intEdForm",
    "vcEdFormName",
    "intClassType",
    "vcClassTypeName",
    "intMeanMark",
    "documentImages"
})
public class DiplomaDocumentType {

    protected Long intID;//da
    protected String intStudentID;//da ЕГН, ЛНЧ или служебен номер на ученика / студента
    @XmlElement(name = "IDType")
    @XmlSchemaType(name = "string")
    protected IdentifierType idType;//da  ЕГН
    protected String vcName1;//da Име на ученика
    protected String vcName2;//da Презиме на ученика
    protected String vcName3;//da Фамилия на ученика
    protected Integer intBPlaceE;
    protected String codeNationality;
    protected Boolean boolGender;//Пол на студента: 0 мъж, 1 жена
    protected Integer intDocumentType;
    protected String vcDocumentName;//da Наименование на документа
    protected Integer intYearGraduated;//da Година на завършване
    protected String vcPrnSer;//da  Серия на документа
    protected String vcPrnNo;//da Номер на документа
    protected String vcRegNo1;//Регистрационен на документа (част 1)
    protected String vcRegNo2;// Регистрационен на документа (част 2)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dtRegDate;
    protected Long intSchoolID;
    protected String vcSchoolName;//da Наименование на училището, издало документа
    protected Integer intEKATTE;//da Код на населеното място по ЕКАТТЕ на училището, издало документа
    protected Long intVETSpeciality;
    protected String vcVETSpecialityName;//da Наименование на специалност (при придобита професионална квалификация)
    protected Short intVETLevel;//da Код на придобита степен на професионална квалификация
    protected String vcVETLevelName;//da  Код на степен на професионална квалификация
    protected Integer intVETGroupIdent;//da  Код на професионално направление (при придобита степен на професионална квалификация)
    protected String vcEducAreaName;//da Наименование на професионално направление (при придобита степен на професионална квалификация)
    protected Short intEdForm; //Код на форма на обучение
    protected String vcEdFormName; //Наименование на форма на обучение
    protected Short intClassType; // Код на вид подготовка
    protected String vcClassTypeName;//Наименование на вид подготовка (профилирана, професионална и пр.)
    protected Float intMeanMark; //Среден успех от дипломата
    @XmlElement(name = "DocumentImages")
    protected DiplomaDocumentType.DocumentImages documentImages;

    public Long getIntID() {
        return intID;
    }

    public void setIntID(Long value) {
        this.intID = value;
    }

    public String getIntStudentID() {
        return intStudentID;
    }

    public void setIntStudentID(String value) {
        this.intStudentID = value;
    }

    public IdentifierType getIDType() {
        return idType;
    }

    public void setIDType(IdentifierType value) {
        this.idType = value;
    }

    public String getVcName1() {
        return vcName1;
    }

    public void setVcName1(String value) {
        this.vcName1 = value;
    }

    public String getVcName2() {
        return vcName2;
    }

    public void setVcName2(String value) {
        this.vcName2 = value;
    }

    public String getVcName3() {
        return vcName3;
    }

    public void setVcName3(String value) {
        this.vcName3 = value;
    }

    public Integer getIntBPlaceE() {
        return intBPlaceE;
    }

    public void setIntBPlaceE(Integer value) {
        this.intBPlaceE = value;
    }

    public String getCodeNationality() {
        return codeNationality;
    }

    public void setCodeNationality(String value) {
        this.codeNationality = value;
    }

    public Boolean isBoolGender() {
        return boolGender;
    }

    public void setBoolGender(Boolean value) {
        this.boolGender = value;
    }

    public Integer getIntDocumentType() {
        return intDocumentType;
    }

    public void setIntDocumentType(Integer value) {
        this.intDocumentType = value;
    }

    public String getVcDocumentName() {
        return vcDocumentName;
    }

    public void setVcDocumentName(String value) {
        this.vcDocumentName = value;
    }

    public Integer getIntYearGraduated() {
        return intYearGraduated;
    }

    public void setIntYearGraduated(Integer value) {
        this.intYearGraduated = value;
    }

    public String getVcPrnSer() {
        return vcPrnSer;
    }

    public void setVcPrnSer(String value) {
        this.vcPrnSer = value;
    }

    public String getVcPrnNo() {
        return vcPrnNo;
    }

    public void setVcPrnNo(String value) {
        this.vcPrnNo = value;
    }

    public String getVcRegNo1() {
        return vcRegNo1;
    }

    public void setVcRegNo1(String value) {
        this.vcRegNo1 = value;
    }

    public String getVcRegNo2() {
        return vcRegNo2;
    }

    public void setVcRegNo2(String value) {
        this.vcRegNo2 = value;
    }

    public XMLGregorianCalendar getDtRegDate() {
        return dtRegDate;
    }

    public void setDtRegDate(XMLGregorianCalendar value) {
        this.dtRegDate = value;
    }

    public Long getIntSchoolID() {
        return intSchoolID;
    }

    public void setIntSchoolID(Long value) {
        this.intSchoolID = value;
    }

    public String getVcSchoolName() {
        return vcSchoolName;
    }

    public void setVcSchoolName(String value) {
        this.vcSchoolName = value;
    }

    public Integer getIntEKATTE() {
        return intEKATTE;
    }

    public void setIntEKATTE(Integer value) {
        this.intEKATTE = value;
    }

    public Long getIntVETSpeciality() {
        return intVETSpeciality;
    }

    public void setIntVETSpeciality(Long value) {
        this.intVETSpeciality = value;
    }

    public String getVcVETSpecialityName() {
        return vcVETSpecialityName;
    }

    public void setVcVETSpecialityName(String value) {
        this.vcVETSpecialityName = value;
    }

    public Short getIntVETLevel() {
        return intVETLevel;
    }

    public void setIntVETLevel(Short value) {
        this.intVETLevel = value;
    }

    public String getVcVETLevelName() {
        return vcVETLevelName;
    }

    public void setVcVETLevelName(String value) {
        this.vcVETLevelName = value;
    }

    public Integer getIntVETGroupIdent() {
        return intVETGroupIdent;
    }

    public void setIntVETGroupIdent(Integer value) {
        this.intVETGroupIdent = value;
    }

    public String getVcEducAreaName() {
        return vcEducAreaName;
    }

    public void setVcEducAreaName(String value) {
        this.vcEducAreaName = value;
    }

    public Short getIntEdForm() {
        return intEdForm;
    }

    public void setIntEdForm(Short value) {
        this.intEdForm = value;
    }

    public String getVcEdFormName() {
        return vcEdFormName;
    }

    public void setVcEdFormName(String value) {
        this.vcEdFormName = value;
    }

    public Short getIntClassType() {
        return intClassType;
    }

    public void setIntClassType(Short value) {
        this.intClassType = value;
    }

    public String getVcClassTypeName() {
        return vcClassTypeName;
    }

    public void setVcClassTypeName(String value) {
        this.vcClassTypeName = value;
    }

    public Float getIntMeanMark() {
        return intMeanMark;
    }

    public void setIntMeanMark(Float value) {
        this.intMeanMark = value;
    }

    public DiplomaDocumentType.DocumentImages getDocumentImages() {
        return documentImages;
    }

    public void setDocumentImages(DiplomaDocumentType.DocumentImages value) {
        this.documentImages = value;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "documentImage"
    })
    public static class DocumentImages {

        @XmlElement(name = "DocumentImage")
        protected List<byte[]> documentImage;

        public List<byte[]> getDocumentImage() {
            if (documentImage == null) {
                documentImage = new ArrayList<byte[]>();
            }
            return this.documentImage;
        }

    }

}

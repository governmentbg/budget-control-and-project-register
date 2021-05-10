package bg.infosys.daeu.ws.wsbg.iisda.models.enums;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "ObjectKindEnum")
@XmlEnum
public enum ObjectKindEnum {

    @XmlEnumValue("GeneralDirectorate")
    GENERAL_DIRECTORATE("GeneralDirectorate"),
    @XmlEnumValue("Department")
    DEPARTMENT("Department"),
    @XmlEnumValue("Directorate")
    DIRECTORATE("Directorate"),
    @XmlEnumValue("Sector")
    SECTOR("Sector"),
    @XmlEnumValue("SecretaryGeneral")
    SECRETARY_GENERAL("SecretaryGeneral"),
    @XmlEnumValue("SecretaryMunicipality")
    SECRETARY_MUNICIPALITY("SecretaryMunicipality"),
    @XmlEnumValue("InformationSecurityOfficer_O")
    INFORMATION_SECURITY_OFFICER_O("InformationSecurityOfficer_O"),
    @XmlEnumValue("ChiefStateHealthInspector")
    CHIEF_STATE_HEALTH_INSPECTOR("ChiefStateHealthInspector"),
    @XmlEnumValue("GeneralInspectorate")
    GENERAL_INSPECTORATE("GeneralInspectorate"),
    @XmlEnumValue("Inspectorate")
    INSPECTORATE("Inspectorate"),
    @XmlEnumValue("InformationSecurityUnit")
    INFORMATION_SECURITY_UNIT("InformationSecurityUnit"),
    @XmlEnumValue("InternalAuditUnit")
    INTERNAL_AUDIT_UNIT("InternalAuditUnit"),
    @XmlEnumValue("FinancialControlUnit")
    FINANCIAL_CONTROL_UNIT("FinancialControlUnit"),
    @XmlEnumValue("InformationSecurityOfficer")
    INFORMATION_SECURITY_OFFICER("InformationSecurityOfficer"),
    @XmlEnumValue("FinancialController")
    FINANCIAL_CONTROLLER("FinancialController"),
    @XmlEnumValue("InternalAuditor")
    INTERNAL_AUDITOR("InternalAuditor"),
    @XmlEnumValue("ChiefArchitect")
    CHIEF_ARCHITECT("ChiefArchitect"),
    @XmlEnumValue("Treasurer")
    TREASURER("Treasurer"),
    @XmlEnumValue("SecretaryOfArea")
    SECRETARY_OF_AREA("SecretaryOfArea"),
    @XmlEnumValue("ChiefArchitect_N")
    CHIEF_ARCHITECT_N("ChiefArchitect_N"),
    @XmlEnumValue("SpecializedInspectorate")
    SPECIALIZED_INSPECTORATE("SpecializedInspectorate"),
    @XmlEnumValue("AdministrationEmployees")
    ADMINISTRATION_EMPLOYEES("AdministrationEmployees"),
    @XmlEnumValue("TerritorialDirectorate")
    TERRITORIAL_DIRECTORATE("TerritorialDirectorate"),
    @XmlEnumValue("PermanentSecretary")
    PERMANENT_SECRETARY("PermanentSecretary"),
    @XmlEnumValue("SecurityOfficerforAutomatedInformationSystems")
    SECURITY_OFFICERFOR_AUTOMATED_INFORMATION_SYSTEMS("SecurityOfficerforAutomatedInformationSystems");
    private final String value;

    ObjectKindEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ObjectKindEnum fromValue(String v) {
        for (ObjectKindEnum c: ObjectKindEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
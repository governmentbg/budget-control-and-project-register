package bg.infosys.daeu.ws.wsbg.iisda.models.enums;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "UnitPositionAdministrationTypeEnum")
@XmlEnum
public enum UnitPositionAdministrationTypeEnum {

    @XmlEnumValue("CommonAdministration")
    COMMON_ADMINISTRATION("CommonAdministration"),
    @XmlEnumValue("SpecializedAdministration")
    SPECIALIZED_ADMINISTRATION("SpecializedAdministration"),
    @XmlEnumValue("OtherAdministration")
    OTHER_ADMINISTRATION("OtherAdministration"),
    @XmlEnumValue("TerritorialUnit")
    TERRITORIAL_UNIT("TerritorialUnit"),
    @XmlEnumValue("DirectionAdministration")
    DIRECTION_ADMINISTRATION("DirectionAdministration"),
    @XmlEnumValue("TerritorialCommonAdministration")
    TERRITORIAL_COMMON_ADMINISTRATION("TerritorialCommonAdministration"),
    @XmlEnumValue("TerritorialSpecializedAdministration")
    TERRITORIAL_SPECIALIZED_ADMINISTRATION("TerritorialSpecializedAdministration");
    private final String value;

    UnitPositionAdministrationTypeEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static UnitPositionAdministrationTypeEnum fromValue(String v) {
        for (UnitPositionAdministrationTypeEnum c: UnitPositionAdministrationTypeEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

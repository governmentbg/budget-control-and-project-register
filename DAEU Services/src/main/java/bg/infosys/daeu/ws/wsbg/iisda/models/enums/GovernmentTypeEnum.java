package bg.infosys.daeu.ws.wsbg.iisda.models.enums;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "GovernmentTypeEnum")
@XmlEnum
public enum GovernmentTypeEnum {

    @XmlEnumValue("Regular")
    REGULAR("Regular"),
    @XmlEnumValue("Official")
    OFFICIAL("Official");

    private final String value;

    GovernmentTypeEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static GovernmentTypeEnum fromValue(String v) {
        for (GovernmentTypeEnum c : GovernmentTypeEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
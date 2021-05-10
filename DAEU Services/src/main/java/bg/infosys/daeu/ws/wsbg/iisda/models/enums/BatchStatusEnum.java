package bg.infosys.daeu.ws.wsbg.iisda.models.enums;


import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "BatchStatusEnum")
@XmlEnum
public enum BatchStatusEnum {

    @XmlEnumValue("Active")
    ACTIVE("Active"),
    @XmlEnumValue("Closed")
    CLOSED("Closed");
    private final String value;

    BatchStatusEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static BatchStatusEnum fromValue(String v) {
        for (BatchStatusEnum c : BatchStatusEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
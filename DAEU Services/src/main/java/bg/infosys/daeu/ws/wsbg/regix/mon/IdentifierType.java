package bg.infosys.daeu.ws.wsbg.regix.mon;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "IdentifierType")
@XmlEnum
public enum IdentifierType {

    EGN("EGN"),
    @XmlEnumValue("LNCh")
    LN_CH("LNCh"),
    IDN("IDN");
    private final String value;

    IdentifierType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static IdentifierType fromValue(String v) {
        for (IdentifierType c: IdentifierType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

package bg.infosys.daeu.ws.wsbg.regix.nra;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


@XmlType(name = "EikTypeType")
@XmlEnum
public enum EikTypeType {

	@XmlEnumValue("Bulstat")
    BULSTAT("Bulstat"),
    @XmlEnumValue("EGN")
    EGN("EGN"),
    @XmlEnumValue("LNC")
    LNC("LNC"),
    @XmlEnumValue("SystemNo")
    SYSTEM_NO("SystemNo"),
    @XmlEnumValue("BulstatCL")
    BULSTAT_CL("BulstatCL");
    private final String value;

    EikTypeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EikTypeType fromValue(String v) {
        for (EikTypeType c: EikTypeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

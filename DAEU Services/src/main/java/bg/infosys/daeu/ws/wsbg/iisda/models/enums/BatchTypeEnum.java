package bg.infosys.daeu.ws.wsbg.iisda.models.enums;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "BatchTypeEnum")
@XmlEnum
public enum BatchTypeEnum {

    COM("COM"),
    @XmlEnumValue("AdmStructure")
    ADM_STRUCTURE("AdmStructure"),
    @XmlEnumValue("AdvisoryBoard")
    ADVISORY_BOARD("AdvisoryBoard");
    private final String value;

    BatchTypeEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static BatchTypeEnum fromValue(String v) {
        for (BatchTypeEnum c: BatchTypeEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
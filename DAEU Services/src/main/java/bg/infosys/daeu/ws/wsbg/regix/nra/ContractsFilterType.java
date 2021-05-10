package bg.infosys.daeu.ws.wsbg.regix.nra;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


@XmlType(name = "ContractsFilterType")
@XmlEnum
public enum ContractsFilterType {

    @XmlEnumValue("All")
    ALL("All"),
    @XmlEnumValue("Active")
    ACTIVE("Active");
    private final String value;

    ContractsFilterType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ContractsFilterType fromValue(String v) {
        for (ContractsFilterType c: ContractsFilterType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

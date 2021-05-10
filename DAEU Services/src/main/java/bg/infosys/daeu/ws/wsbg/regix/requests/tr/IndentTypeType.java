package bg.infosys.daeu.ws.wsbg.regix.requests.tr;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


@XmlType(name = "IndentTypeType")
@XmlEnum
public enum IndentTypeType {

 EGN,
 UIC;

 public String value() {
     return name();
 }

 public static IndentTypeType fromValue(String v) {
     return valueOf(v);
 }

}

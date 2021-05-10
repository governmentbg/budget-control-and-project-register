package bg.infosys.daeu.ws.wsbg.regix.requests.tr;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DetailsType", propOrder = {
 "detail"
})
public class DetailsType {

 @XmlElement(name = "Detail", namespace = "http://egov.bg/RegiX/AV/TR")
 protected List<DetailType> detail;

 public List<DetailType> getDetail() {
     if (detail == null) {
         detail = new ArrayList<DetailType>();
     }
     return this.detail;
 }

}

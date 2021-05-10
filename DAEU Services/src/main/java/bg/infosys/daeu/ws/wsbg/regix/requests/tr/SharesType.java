package bg.infosys.daeu.ws.wsbg.regix.requests.tr;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SharesType", propOrder = {
 "share"
})
public class SharesType {

 @XmlElement(name = "Share")
 protected List<ShareType> share;

 public List<ShareType> getShare() {
     if (share == null) {
         share = new ArrayList<ShareType>();
     }
     return this.share;
 }

}
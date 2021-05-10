package bg.infosys.daeu.ws.wsbg.regix.requests.tr;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NonMonetaryDepositsType", propOrder = {
 "nonMonetaryDeposit"
})
public class NonMonetaryDepositsType {

 @XmlElement(name = "NonMonetaryDeposit")
 protected List<NonMonetaryDepositType> nonMonetaryDeposit;

 public List<NonMonetaryDepositType> getNonMonetaryDeposit() {
     if (nonMonetaryDeposit == null) {
         nonMonetaryDeposit = new ArrayList<NonMonetaryDepositType>();
     }
     return this.nonMonetaryDeposit;
 }

}
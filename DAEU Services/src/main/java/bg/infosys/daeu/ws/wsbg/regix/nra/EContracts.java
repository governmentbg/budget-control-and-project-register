package bg.infosys.daeu.ws.wsbg.regix.nra;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EContracts", propOrder = {
    "eContract"
})
public class EContracts {

    @XmlElement(name = "EContract")
    protected List<EContract> eContract;

    public List<EContract> getEContract() {
        if (eContract == null) {
            eContract = new ArrayList<EContract>();
        }
        return this.eContract;
    }

}

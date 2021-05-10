package bg.infosys.daeu.ws.wsbg.regix.nra;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StatusType", propOrder = {
    "code",
    "message"
})
public class StatusType {

    @XmlElement(name = "Code")
    protected Integer code;
    @XmlElement(name = "Message")
    protected String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer value) {
        this.code = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String value) {
        this.message = value;
    }

}

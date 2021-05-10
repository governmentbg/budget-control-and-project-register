package bg.infosys.daeu.ws.wsbg.iisda.misc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Calendar {

    private static final Logger logger = LoggerFactory.getLogger(Calendar.class);

    public static XMLGregorianCalendar toXMLGregorianCalendar(Date date) {

        if (date == null) {
            return null;
        }

        XMLGregorianCalendar xmlCalendar = null;

        try {
            GregorianCalendar gCalendar = new GregorianCalendar();
            gCalendar.setTime(date);
            xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCalendar);

        } catch (DatatypeConfigurationException e) {

            logger.error("issue with gregorian callendar : {}", e.getMessage());

        }
        return xmlCalendar;
    }
}

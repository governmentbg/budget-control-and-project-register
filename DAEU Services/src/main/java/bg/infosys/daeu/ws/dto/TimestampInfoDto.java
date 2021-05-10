package bg.infosys.daeu.ws.dto;

import java.math.BigInteger;
import java.util.Date;

public class TimestampInfoDto {
	private BigInteger serialNumber;
	private Date date;
	
	public BigInteger getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(BigInteger serialNumber) {
		this.serialNumber = serialNumber;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}

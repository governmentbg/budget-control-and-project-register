package bg.infosys.daeu.db.dto;

public class History {
	
	private String whatIsChanged ;
	private String userName;
	private String userIP;
	private String oldValue;
	private String newValue;
	private String dateOfChange;

	public History() {
		whatIsChanged = null;
		oldValue = null;
		newValue = null;
		dateOfChange = null;
	}

	public String getWhatIsChanged() {
		return whatIsChanged;
	}

	public String getOldValue() {
		return oldValue;
	}
	public String getNewValue() {
		return newValue;
	}
	public void setWhatIsChanged(String whatIsChangedObj) {
		this.whatIsChanged = whatIsChangedObj;
	}

	public String getDateOfChange() {
		return dateOfChange;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	public void setDateOfChange(String dateOfChange) {
		this.dateOfChange = dateOfChange;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserIP() {
		return userIP;
	}

	public void setUserIP(String userIP) {
		this.userIP = userIP;
	}
}

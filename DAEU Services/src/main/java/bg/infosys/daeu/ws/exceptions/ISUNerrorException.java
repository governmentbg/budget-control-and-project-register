package bg.infosys.daeu.ws.exceptions;

import bg.infosys.daeu.ws.pub.response.ISUNResponse;

@SuppressWarnings("serial")
public class ISUNerrorException extends Exception {
	private ISUNResponse.ReponseMessage response;
	
	public ISUNerrorException(ISUNResponse.ReponseMessage reponse) {
		this.response = reponse;
	}

	public ISUNResponse.ReponseMessage getResponse() {
		return response;
	}

	public void setResponse(ISUNResponse.ReponseMessage response) {
		this.response = response;
	}
}

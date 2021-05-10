package bg.infosys.daeu.ws.wsbg.regix.requests.bulstat;

import bg.infosys.daeu.ws.wsbg.regix.requests.Operation;

public enum BulstatOperation implements Operation {

	GET_STATE_OF_PLAY("TechnoLogica.RegiX.AVBulstat2Adapter.APIService.IAVBulstat2API.GetStateOfPlay");

	private final String key;

	private BulstatOperation(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}
}

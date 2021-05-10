package bg.infosys.daeu.ws.controllers;


import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bg.infosys.daeu.ws.exceptions.MissingInputValuesException;
import bg.infosys.daeu.ws.integrations.RIRRequestBuilder;
import bg.infosys.daeu.ws.wsbg.rir.RIRQuery;
import bg.infosys.daeu.ws.wsbg.rir.RIRQueryInput;
import bg.infosys.daeu.ws.wsbg.rir.entities.RIRResponse;


@RestController
@RequestMapping("rir")
public class RIREntryController {

	@Autowired
	RIRRequestBuilder rirRequestBuilder;

	@PostMapping(value = "/getRIRData")
	public ResponseEntity<RIRResponse> getOrganizationDetails(@RequestBody RIRQueryInput organizationQuery) {
		System.out.println("RIR : getOrganizationDetails");
		if (StringUtils.isEmpty(organizationQuery.getBulstat()) && StringUtils.isEmpty(organizationQuery.getIisdaNumber())) {
			throw new MissingInputValuesException("Моля задайте ИИСДА или БУЛСТАТ");
		}
		if (Objects.isNull(organizationQuery.getClassName())) {
			throw new MissingInputValuesException("Моля задайте клас за търсене");
		}

		RIRQuery query = rirRequestBuilder.prepareQuery(organizationQuery);
		HttpEntity<MultiValueMap<String, String>> entity = rirRequestBuilder.buildRequest(query);

		return rirRequestBuilder.sendRequest(entity);
	}
}

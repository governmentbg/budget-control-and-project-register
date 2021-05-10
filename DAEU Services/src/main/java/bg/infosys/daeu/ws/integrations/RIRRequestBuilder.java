package bg.infosys.daeu.ws.integrations;

import bg.infosys.common.utils.Strings;
import bg.infosys.daeu.ws.exceptions.RestExchangeException;
import bg.infosys.daeu.ws.wsbg.rir.RIRKey;
import bg.infosys.daeu.ws.wsbg.rir.RIRQuery;
import bg.infosys.daeu.ws.wsbg.rir.RIRQueryInput;
import bg.infosys.daeu.ws.wsbg.rir.entities.RIRResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

@Component
public class RIRRequestBuilder {

	private static final Logger logger = LoggerFactory.getLogger(RIRRequestBuilder.class);
	private static final String version = "1.3";
	private static final String url = "https://rir.egov.bg";
//	private static final String url = "http://172.23.114.141";
	private static final String mapping = "/webservices/rest.php?version=" + version;

	@Value("${rir.username}")
	private String username;

	@Value("${rir.password}")
	private String password;

	private RestTemplate restTemplate;
	private ObjectMapper objectMapper = new ObjectMapper();

	public RIRRequestBuilder() {
		restTemplate = new RestTemplate();
	}

	public ResponseEntity<RIRResponse> sendRequest(HttpEntity<MultiValueMap<String, String>> entity) {

		try {
			ResponseEntity<RIRResponse> response = restTemplate.exchange(url + mapping, HttpMethod.POST, entity, RIRResponse.class);
			logger.error("Rir success: "+response.getStatusCodeValue());
			return response;
		} catch (HttpStatusCodeException httpStatusCodeException) {
			logger.error("Rir error: "+httpStatusCodeException.getMessage());
			throw new RestExchangeException(httpStatusCodeException.getStatusCode(), httpStatusCodeException.getMessage());
		}
	}

	public RIRQuery prepareQuery(RIRQueryInput rirQueryInput) {
		RIRQuery.Builder builder = new RIRQuery.Builder();
		RIRKey rk = new RIRKey();

		switch (rirQueryInput.getClassName()) {
		case ASSET:
			String year = rirQueryInput.getYear();
			String yearQuery = Strings.isEmpty(year) ? "" : " AND `Asset`.purchase_date>=" + year + "";
			String orgIDQuery = (ObjectUtils.isNotEmpty(rirQueryInput.getBulstat()) ? "`Organization`.code='" + rirQueryInput.getBulstat() : "`Organization`.iisda_id='" + rirQueryInput.getIisdaNumber()) + "'";
			rk.setQuery("SELECT `Asset` FROM Asset AS `Asset` JOIN Organization AS `Organization` ON `Asset`.org_id = `Organization`.id JOIN Organization AS `Organization1` ON `Organization`.parent_id BELOW `Organization1`.id WHERE " + orgIDQuery + yearQuery);
			builder.setOutputFields("name", "cpv_category_id_friendlyname", "cpv_class_id_friendlyname", "cpv_group_id_friendlyname", 
					"ebk_codes_id_friendlyname", "purchase_date", "status" ,"cpv_section_id_friendlyname", "friendlyname","acquiring_price ");
			break;
		case ORGANIZATION:
			Map<String, String> criteria = new HashMap<>();
			Map.Entry<String, String> entry = ObjectUtils.isNotEmpty(rirQueryInput.getBulstat()) ? new AbstractMap.SimpleEntry<>("code", rirQueryInput.getBulstat()) : new AbstractMap.SimpleEntry<>("iisda_id", rirQueryInput.getIisdaNumber());
			criteria.put(entry.getKey(), entry.getValue());
			rk.setCriteria(criteria);
			break;
		case YEARLY_PLAN:
			String firstYear = rirQueryInput.getFirstYear();
			String secondYear = rirQueryInput.getSecondYear();
			String thirdYear = rirQueryInput.getThirdYear();
			String threeYearsPlanQuery = Strings.isEmpty(firstYear) ? "" : " AND `YearlyPlan`.year IN ('" + firstYear + "', '" + secondYear + "', '" + thirdYear + "')";
			String yearlyPlanOrgIDQuery = "`Organization1`.code='" + rirQueryInput.getBulstat() + "'";
			rk.setQuery("SELECT `YearlyPlan` FROM Yearly_plan AS `YearlyPlan` JOIN Organization AS `Organization` ON `YearlyPlan`.org_id = `Organization`.id "
					+ "JOIN Organization AS `Organization1` ON `Organization`.parent_id BELOW `Organization1`.id WHERE " + yearlyPlanOrgIDQuery + threeYearsPlanQuery);
			builder.setOutputFields("name", "org_id_friendlyname", "month", "year", "reason", "ebk_codes_id_friendlyname", "cpv_category_id_friendlyname", "cpv_class_id_friendlyname", "cpv_group_id_friendlyname", 
					"cpv_section_id_friendlyname", "planned_resources");
			break;
		default:
			break;
		}

		builder.setClassName(rirQueryInput.getClassName())
		.setKey(rk).setOperationType("core/get");

		return builder.build();
	}

	public HttpEntity<MultiValueMap<String, String>> buildRequest(RIRQuery rirQuery) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		String value = "";
		try {
			value = objectMapper.writeValueAsString(rirQuery);
		} catch (JsonProcessingException e) {
			logger.error("Cant process object into json");
		}
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("auth_user", username);
		map.add("auth_pwd", password);
		map.add("json_data", value);

		return new HttpEntity<>(map, headers);
	}

}

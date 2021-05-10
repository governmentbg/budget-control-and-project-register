package bg.infosys.daeu.ws.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bg.infosys.daeu.ws.exceptions.ISUNerrorException;
import bg.infosys.daeu.ws.pub.json.ISUNDecisionJSON;
import bg.infosys.daeu.ws.pub.json.ISUNProjectJSON;
import bg.infosys.daeu.ws.pub.response.ISUNInternalResponse;
import bg.infosys.daeu.ws.pub.response.ISUNResponse;
import bg.infosys.daeu.ws.services.AuthorizationService;
import bg.infosys.daeu.ws.services.ISUNService;

@RestController
@RequestMapping("isun")
public class ISUNController {
	@Autowired private AuthorizationService authorizationService;
	@Autowired private ISUNService isunService;
	
	@PostMapping(value = "/send-project")
	public ResponseEntity<ISUNResponse> sendProject(@RequestBody ISUNProjectJSON isunProject, @RequestHeader String authorization) {
		ISUNResponse response = new ISUNResponse();
		
		if (!authorizationService.isAuthorized(authorization)) {
			response.setErrorCode(ISUNResponse.ReponseMessage.UNAUTHORIZED.getCode());
			response.setErrorMessage(ISUNResponse.ReponseMessage.UNAUTHORIZED.getMessage());
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response); 
		}
		
		try {
			response.setId(isunService.createNewProject(isunProject));
		} catch (IllegalArgumentException | IllegalAccessException e) {
			response.setErrorCode("PARSING_ERROR");
			response.setErrorMessage("Parsing error occurred!");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		} catch (ISUNerrorException e) {
			if (e.getResponse().getCode().equals(ISUNResponse.ReponseMessage.NОТ_REQUIRED.getCode())) {

				response.setErrorCode(e.getResponse().getCode());
				response.setErrorMessage(e.getResponse().getMessage() + isunProject.getGuid());
				return ResponseEntity.status(HttpStatus.OK).body(response);
			}
			if (e.getResponse().getCode().equals(ISUNResponse.ReponseMessage.NОТ_RETURNED.getCode())) {

				response.setErrorCode(e.getResponse().getCode());
				response.setErrorMessage(e.getResponse().getMessage() + isunProject.getGuid());
				return ResponseEntity.status(HttpStatus.OK).body(response);
			}
			
			response.setErrorCode(e.getResponse().getCode());
			response.setErrorMessage(e.getResponse().getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@PostMapping(value = "/send-decision")
	public ResponseEntity<ISUNInternalResponse> sendDecision(@RequestBody ISUNDecisionJSON isunProject, @RequestHeader String authorization) {
		ISUNInternalResponse response = new ISUNInternalResponse();
		
		if (!authorizationService.isAuthorized(authorization) ) {
			response.setErrorCode(ISUNResponse.ReponseMessage.UNAUTHORIZED.getCode());
			response.setErrorMessage(ISUNResponse.ReponseMessage.UNAUTHORIZED.getMessage());
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response); 
		}
		
		String token = isunService.getAccessToken(null);
		
		if (token == null) {
			response.setErrorCode("ISUN_TOKEN_ERROR");
			response.setErrorMessage("Unable to obtain isun access token!");
			return ResponseEntity.status(HttpStatus.OK).body(response); 
		}
		
		ResponseEntity<ISUNInternalResponse> result = isunService.sendDecision(isunProject, token);
		
		return ResponseEntity.status(result.getStatusCode()).body(result.getBody());
	}
}

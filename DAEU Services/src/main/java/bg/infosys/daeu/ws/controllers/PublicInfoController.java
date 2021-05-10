package bg.infosys.daeu.ws.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bg.infosys.daeu.ws.pub.entity.PublicBudgetFormData;
import bg.infosys.daeu.ws.pub.entity.PublicProjectData;
import bg.infosys.daeu.ws.pub.entity.PublicTechSpecData;
import bg.infosys.daeu.ws.pub.json.PublicBudgetFormDataJSON;
import bg.infosys.daeu.ws.pub.json.PublicProjectDataJSON;
import bg.infosys.daeu.ws.pub.json.PublicTechSpecDataJSON;
import bg.infosys.daeu.ws.pub.response.PublicResponse;
import bg.infosys.daeu.ws.pub.response.PublicResponse.ReponseMessage;
import bg.infosys.daeu.ws.pub.util.PublicDataUtil;
import bg.infosys.daeu.ws.services.AuthorizationService;
import bg.infosys.daeu.ws.services.PublicInfoService;

@RestController
@RequestMapping("public")
public class PublicInfoController {
	private static final Logger LOGGER = LoggerFactory.getLogger(PublicInfoController.class);
	 
	@Autowired PublicInfoService publicInfoService;
	@Autowired PublicDataUtil publicDataUtil;
	@Autowired AuthorizationService authorizationService;

	@PostMapping(value = "/publish-all")
    public ResponseEntity<PublicResponse> publishAll() {
		LOGGER.info("The functionality of this method has not yet been implemented!");
        return ResponseEntity.ok(new PublicResponse(ReponseMessage.FAILED_SYNC.getMessage(), "The functionality of this method has not yet been implemented!"));
    }
	
	@PostMapping(value = "/publish-budget")
    public ResponseEntity<PublicResponse> publishBudgetForm(@RequestBody PublicBudgetFormDataJSON data, @RequestHeader String authorization) {
		PublicResponse response = new PublicResponse();
		
		if (!authorizationService.isAuthorized(authorization)) {
			response.setMessage(ReponseMessage.UNAUTHORIZED.getMessage());
			return ResponseEntity.ok(response);
		}
		
		if (data.getPublicId() == null) {
			response.setMessage(ReponseMessage.SUCCESSFUL_SYNC.getMessage());
			PublicBudgetFormData savedEntity = publicInfoService.savePublicBudgetForm(publicDataUtil.parseBudgetData(data));
			response.setPublicId(savedEntity.getId());
			publicDataUtil.saveAdditionalBudgetFormData(data, savedEntity);
		} else {
			PublicBudgetFormData entity = publicInfoService.findPublicBudgetForm(data.getPublicId());
			if (entity == null) {
				response.setMessage(ReponseMessage.FAILED_SYNC.getMessage());
				response.setReason("No entity found for public id: " + data.getPublicId() + "!");
				return ResponseEntity.ok(response);
			} else {
				PublicBudgetFormData savedEntity = publicInfoService.savePublicBudgetForm(publicDataUtil.syncBudgetData(entity, data));
				publicDataUtil.syncAdditionalBudgetFormData(data, savedEntity.getPublicBudgetFormDataDetails());
				publicDataUtil.syncAdditionalBudgetFormFields(data, entity);
			}
			response.setMessage(ReponseMessage.SUCCESSFUL_SYNC.getMessage());
		}
		return ResponseEntity.ok(response);
	}
	
	@PostMapping(value = "/publish-project")
    public ResponseEntity<PublicResponse> publishProject(@RequestBody PublicProjectDataJSON data, @RequestHeader String authorization) {
		PublicResponse response = new PublicResponse();
		
		if (!authorizationService.isAuthorized(authorization)) {
			response.setMessage(ReponseMessage.UNAUTHORIZED.getMessage());
			return ResponseEntity.ok(response);
		}
		
		if (data.getPublicId() == null) {
			response.setMessage(ReponseMessage.SUCCESSFUL_SYNC.getMessage());
			PublicProjectData savedEntity = publicInfoService.savePublicProject(publicDataUtil.parseProject(data));
			response.setPublicId(savedEntity.getId());
			publicDataUtil.saveAdditionalProjectData(data, savedEntity);
			publicDataUtil.saveAttachedDocs(data.getTechSpecsAttachedDocs(), savedEntity.getId(), "P", "TS");
		} else {
			PublicProjectData entity = publicInfoService.findPublicProject(data.getPublicId());
			if (entity == null) {
				response.setMessage(ReponseMessage.FAILED_SYNC.getMessage());
				response.setReason("No entity found for public id: " + data.getPublicId() + "!");
				return ResponseEntity.ok(response);
			} else {
				PublicProjectData savedEntity = publicInfoService.savePublicProject(publicDataUtil.syncProject(entity, data));
				publicDataUtil.saveAdditionalProjectData(data, savedEntity);
			}
			response.setMessage(ReponseMessage.SUCCESSFUL_SYNC.getMessage());
		}
		return ResponseEntity.ok(response);
	}
	
	@PostMapping(value = "/publish-tech")
    public ResponseEntity<PublicResponse> publishTechSpec(@RequestBody PublicTechSpecDataJSON data, @RequestHeader String authorization) {
		PublicResponse response = new PublicResponse();
		
		if (!authorizationService.isAuthorized(authorization)) {
			response.setMessage(ReponseMessage.UNAUTHORIZED.getMessage());
			return ResponseEntity.ok(response); 
		}
		
		if (data.getPublicId() == null) {
			response.setMessage(ReponseMessage.SUCCESSFUL_SYNC.getMessage());
			PublicTechSpecData savedEntity = publicInfoService.savePublicTechSpec(publicDataUtil.parseTechSpec(data));
			response.setPublicId(savedEntity.getId());
			publicDataUtil.saveAdditionalTechSpecsData(data, savedEntity);
		} else {
			PublicTechSpecData entity = publicInfoService.findPublicTechSpec(data.getPublicId());
			if (entity == null) {
				response.setMessage(ReponseMessage.FAILED_SYNC.getMessage());
				response.setReason("No entity found for public id: " + data.getPublicId() + "!");
				return ResponseEntity.ok(response);
			} else {
				PublicTechSpecData savedEntity = publicInfoService.savePublicTechSpec(publicDataUtil.syncTechSpec(entity, data));
				publicDataUtil.saveAdditionalTechSpecsData(data, savedEntity);
			}
			response.setMessage(ReponseMessage.SUCCESSFUL_SYNC.getMessage());
		}
		return ResponseEntity.ok(response);
	}
}

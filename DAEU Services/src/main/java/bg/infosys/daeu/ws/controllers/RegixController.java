package bg.infosys.daeu.ws.controllers;

import java.io.InputStream;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bg.infosys.daeu.db.dto.RegixRequestObject;
import bg.infosys.daeu.ws.wsbg.regix.CallContext;
import bg.infosys.daeu.ws.wsbg.regix.RegixClient;
import bg.infosys.daeu.ws.wsbg.regix.ServiceRequestData;
import bg.infosys.daeu.ws.wsbg.regix.ServiceResultData;
import bg.infosys.daeu.ws.wsbg.regix.mon.IdentifierType;
import bg.infosys.daeu.ws.wsbg.regix.mon.request.DiplomaSearchType;
import bg.infosys.daeu.ws.wsbg.regix.nra.EikTypeType;
import bg.infosys.daeu.ws.wsbg.regix.nra.request.EmploymentContractsRequest;
import bg.infosys.daeu.ws.wsbg.regix.nra.request.IdentityTypeRequest;
import bg.infosys.daeu.ws.wsbg.regix.requests.bulstat.BulstatOperation;
import bg.infosys.daeu.ws.wsbg.regix.requests.bulstat.GetStateOfPlayRequest;
import bg.infosys.daeu.ws.wsbg.regix.requests.tr.TROperation;
import bg.infosys.daeu.ws.wsbg.regix.requests.tr.actualstate.ActualStateRequestType;
import bg.infosys.daeu.ws.wsbg.regix.requests.tr.validuic.ValidUICRequestType;

@RestController
@RequestMapping("regix")
public class RegixController {

	//BRRA DATA
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/getValidUIC")
	public ResponseEntity getValidUIC() {
		//		@RequestBody RegixRequestObject regixRequest
		String keystorePassword = "123456";

		//		System.setProperty("com.sun.xml.ws.transport.http.client.HttpTransportPipe.dump", "true");
		//		System.setProperty("com.sun.xml.internal.ws.transport.http.client.HttpTransportPipe.dump", "true");
		//		System.setProperty("com.sun.xml.ws.transport.http.HttpAdapter.dump", "true");
		//		System.setProperty("com.sun.xml.ws.transport.http.HttpAdapter.dumpTreshold", "999999");
		//		System.setProperty("com.sun.xml.internal.ws.transport.http.HttpAdapter.dump", "true");

		InputStream is = RegixController.class.getResourceAsStream("/server.keystore");
		RegixClient client = RegixClient.create(is, keystorePassword.toCharArray());


		ValidUICRequestType req = new ValidUICRequestType();
		req.setUIC("123");

		ServiceRequestData requestData = RegixClient.createRequestData(TROperation.GET_VALID_UIC_INFO, req);

		CallContext ctx = new CallContext();
		ctx.setAdministrationOId("2.16.100.1.1.292.1.3");
		requestData.setCallContext(ctx);

		//		JAXBContext jaxbContext = JAXBContext.newInstance(ServiceRequestData.class);
		//		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		//		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		//		jaxbMarshaller.marshal(requestData, baos);
		//		System.out.println(new String(baos.toByteArray()));

		ServiceResultData response = client.executeSynchronous(requestData);
		return ResponseEntity.ok(response.getData().getResponse().getAny());
	}

	//BRRA DATA
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/actualState")
	public ResponseEntity getActualState(@RequestBody RegixRequestObject regixRequest) throws Exception {
		String keystorePassword = "123456";

		InputStream is = RegixController.class.getResourceAsStream("/server.keystore");
		RegixClient client = RegixClient.create(is, keystorePassword.toCharArray());

		ActualStateRequestType req = new ActualStateRequestType();
		req.setUIC(regixRequest.getUic());        

		ServiceRequestData requestData = RegixClient.createRequestData(TROperation.GET_ACTUAL_STATE, req);

		CallContext ctx = new CallContext();
		ctx.setAdministrationOId(regixRequest.getOid());
		requestData.setCallContext(ctx);

		ServiceResultData response = client.executeSynchronous(requestData);

		return ResponseEntity.ok(response.getData().getResponse().getAny());
	}

	//BULSTAT DATA
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/stateOfPlay")
	public ResponseEntity getStateOfPlay(@RequestBody RegixRequestObject regixRequest) throws Exception {
		String keystorePassword = "123456";

		InputStream is = RegixController.class.getResourceAsStream("/server.keystore");
		RegixClient client = RegixClient.create(is, keystorePassword.toCharArray());

		GetStateOfPlayRequest req = new GetStateOfPlayRequest();
		req.setUIC(regixRequest.getUic());        

		ServiceRequestData requestData = RegixClient.createRequestData(BulstatOperation.GET_STATE_OF_PLAY, req);

		CallContext ctx = new CallContext();
		ctx.setAdministrationOId(regixRequest.getOid());
		requestData.setCallContext(ctx);

		ServiceResultData response = client.executeSynchronous(requestData);

		return ResponseEntity.ok(response.getData().getResponse().getAny());
	}

	//NRA DATA
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/getEmploymentContracts")
	public ResponseEntity getEmploymentContracts(@RequestBody RegixRequestObject regixRequest) {
		String keystorePassword = "123456";

		InputStream is = RegixController.class.getResourceAsStream("/server_damtn.keystore");
		RegixClient client = RegixClient.create(is, keystorePassword.toCharArray());

		EmploymentContractsRequest req = new EmploymentContractsRequest();
		IdentityTypeRequest identity = new IdentityTypeRequest();
		identity.setId(regixRequest.getUic());//203773538
		identity.setType(EikTypeType.fromValue(regixRequest.getType()));
		req.setIdentity(identity);
		ServiceRequestData requestData = RegixClient.createRequestData(TROperation.GET_EMPLOYMENT_CONTRACTS, req);

		CallContext ctx = new CallContext();
		ctx.setAdministrationOId(regixRequest.getOid());
		requestData.setCallContext(ctx);

		ServiceResultData response = client.executeSynchronous(requestData);

		return ResponseEntity.ok(response.getData().getResponse().getAny());
	}


	//MON DATA
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/getDiplomaInfo")
	public ResponseEntity getDiplomaInfo(@RequestBody RegixRequestObject regixRequest) {
		String keystorePassword = "123456";

		InputStream is = RegixController.class.getResourceAsStream("/server_damtn.keystore");
		RegixClient client = RegixClient.create(is, keystorePassword.toCharArray());

		DiplomaSearchType req = new DiplomaSearchType();
		//			req.setStudentID("9906186340");
		//			req.setDocumentNumber("010550");
		//			req.setIDType(IdentifierType.EGN);
		req.setStudentID(regixRequest.getUic());
		req.setDocumentNumber(regixRequest.getNum());
		req.setIDType(IdentifierType.fromValue(regixRequest.getType()));

		ServiceRequestData requestData = RegixClient.createRequestData(TROperation.GET_DIPLOMA_INFO, req);

		CallContext ctx = new CallContext();
		ctx.setAdministrationOId(regixRequest.getOid());
		requestData.setCallContext(ctx);

		ServiceResultData response = client.executeSynchronous(requestData);

		return ResponseEntity.ok(response.getData().getResponse().getAny());
	}

}

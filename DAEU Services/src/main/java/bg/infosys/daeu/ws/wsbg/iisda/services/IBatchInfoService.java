package bg.infosys.daeu.ws.wsbg.iisda.services;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import bg.infosys.daeu.ws.wsbg.iisda.models.ArrayOfBatchIdentificationInfoType;
import bg.infosys.daeu.ws.wsbg.iisda.models.ArrayOfBatchType;
import bg.infosys.daeu.ws.wsbg.iisda.models.ArrayOfString;
import bg.infosys.daeu.ws.wsbg.iisda.models.enums.AdmStructureKindsEnum;
import bg.infosys.daeu.ws.wsbg.iisda.models.enums.BatchStatusEnum;
import bg.infosys.daeu.ws.wsbg.iisda.models.enums.BatchTypeEnum;

@WebService(targetNamespace = "http://iisda.government.bg/RAS/IntegrationServices", name = "IBatchInfoService")
@XmlSeeAlso({ObjectFactory.class})
public interface IBatchInfoService {

    @WebMethod(operationName = "SearchBatchesIdentificationInfo", action = "http://iisda.government.bg/RAS/IntegrationServices/IBatchInfoService/SearchBatchesIdentificationInfo")
    @Action(input = "http://iisda.government.bg/RAS/IntegrationServices/IBatchInfoService/SearchBatchesIdentificationInfo", output = "http://iisda.government.bg/RAS/IntegrationServices/IBatchInfoService/SearchBatchesIdentificationInfoResponse")
    @RequestWrapper(localName = "SearchBatchesIdentificationInfo", targetNamespace = "http://iisda.government.bg/RAS/IntegrationServices", className = "SearchBatchesIdentificationInfo")
    @ResponseWrapper(localName = "SearchBatchesIdentificationInfoResponse", targetNamespace = "http://iisda.government.bg/RAS/IntegrationServices", className = "SearchBatchesIdentificationInfoResponse")
    @WebResult(name = "SearchBatchesIdentificationInfoResult", targetNamespace = "http://iisda.government.bg/RAS/IntegrationServices")
    public ArrayOfBatchIdentificationInfoType searchBatchesIdentificationInfo(
            @WebParam(name = "batchIdentificationNumber", targetNamespace = "http://iisda.government.bg/RAS/IntegrationServices")
                    String batchIdentificationNumber,
            @WebParam(name = "batchUIC", targetNamespace = "http://iisda.government.bg/RAS/IntegrationServices")
                    String batchUIC,
            @WebParam(name = "admStructureKind", targetNamespace = "http://iisda.government.bg/RAS/IntegrationServices")
                    AdmStructureKindsEnum admStructureKind,
            @WebParam(name = "batchType", targetNamespace = "http://iisda.government.bg/RAS/IntegrationServices")
                    BatchTypeEnum batchType,
            @WebParam(name = "status", targetNamespace = "http://iisda.government.bg/RAS/IntegrationServices")
                    BatchStatusEnum status,
            @WebParam(name = "dateAt", targetNamespace = "http://iisda.government.bg/RAS/IntegrationServices")
                    javax.xml.datatype.XMLGregorianCalendar dateAt,
            @WebParam(name = "versionIDAt", targetNamespace = "http://iisda.government.bg/RAS/IntegrationServices")
                    Long versionIDAt
    );

    @WebMethod(operationName = "GetBatchDetailedInfo", action = "http://iisda.government.bg/RAS/IntegrationServices/IBatchInfoService/GetBatchDetailedInfo")
    @Action(input = "http://iisda.government.bg/RAS/IntegrationServices/IBatchInfoService/GetBatchDetailedInfo", output = "http://iisda.government.bg/RAS/IntegrationServices/IBatchInfoService/GetBatchDetailedInfoResponse")
    @RequestWrapper(localName = "GetBatchDetailedInfo", targetNamespace = "http://iisda.government.bg/RAS/IntegrationServices", className = "GetBatchDetailedInfo")
    @ResponseWrapper(localName = "GetBatchDetailedInfoResponse", targetNamespace = "http://iisda.government.bg/RAS/IntegrationServices", className = "GetBatchDetailedInfoResponse")
    @WebResult(name = "GetBatchDetailedInfoResult", targetNamespace = "http://iisda.government.bg/RAS/IntegrationServices")
    public ArrayOfBatchType getBatchDetailedInfo(
            @WebParam(name = "batchIdentificationNumber", targetNamespace = "http://iisda.government.bg/RAS/IntegrationServices")
                    ArrayOfString batchIdentificationNumber,
            @WebParam(name = "dateAt", targetNamespace = "http://iisda.government.bg/RAS/IntegrationServices")
                    javax.xml.datatype.XMLGregorianCalendar dateAt,
            @WebParam(name = "versionIDAt", targetNamespace = "http://iisda.government.bg/RAS/IntegrationServices")
                    java.lang.Long versionIDAt
    );

}

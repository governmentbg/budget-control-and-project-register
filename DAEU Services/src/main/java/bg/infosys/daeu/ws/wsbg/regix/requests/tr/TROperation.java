package bg.infosys.daeu.ws.wsbg.regix.requests.tr;

import bg.infosys.daeu.ws.wsbg.regix.requests.Operation;

public enum TROperation implements Operation {
    GET_ACTUAL_STATE("TechnoLogica.RegiX.AVTRAdapter.APIService.ITRAPI.GetActualState"),
    GET_VALID_UIC_INFO("TechnoLogica.RegiX.AVTRAdapter.APIService.ITRAPI.GetValidUICInfo"),
    PERSON_IN_COMPANIES_SEARCH("TechnoLogica.RegiX.AVTRAdapter.APIService.ITRAPI.PersonInCompaniesSearch"),
	GET_EMPLOYMENT_CONTRACTS("TechnoLogica.RegiX.NRAEmploymentContractsAdapter.APIService.INRAEmploymentContractsAPI.GetEmploymentContracts"),
	GET_DIPLOMA_INFO("TechnoLogica.RegiX.RDSOAdapter.APIService.IRDSOAPI.GetDiplomaInfo");
    
    private final String key;
    
    private TROperation(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}

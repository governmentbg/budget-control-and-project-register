package bg.infosys.daeu.ws.wsbg.etranslate;

import java.util.Arrays;
import java.util.List;

    public class ETranslateRequestObject {

    private int priority;
    private String requesterCallback;//externalReference;
    private String textToTranslate;
    private String sourceLanguage;
    private String domain = "SPD" ; // change to enum later ?
    private List<String> targetLanguages; // maybe add default ?
    private CallerInformation callerInformation;

//    @JsonProperty(value = "caller-information")
    public CallerInformation getCallerInformation() {
        return callerInformation;
    }

    public List<String> getTargetLanguages() {
        return targetLanguages;
    }

    public String getDomain() {
        return domain;
    }

    public String getSourceLanguage() {
        return sourceLanguage;
    }

    public String getTextToTranslate() {
        return textToTranslate;
    }

//    public String getExternalReference() {
//        return externalReference;
//    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
//
//    public void setExternalReference(String externalReference) {
//        this.externalReference = externalReference;
//    }

    public void setTextToTranslate(String textToTranslate) {
        this.textToTranslate = textToTranslate;
    }

    public void setSourceLanguage(String sourceLanguage) {
        this.sourceLanguage = sourceLanguage;
    }

    public void setTargetLanguages(String... targetLanguages) {
        this.targetLanguages = Arrays.asList(targetLanguages);
    }

    public void setCallerInformation(CallerInformation callerInformation){
        this.callerInformation = callerInformation;
    }

    public String getRequesterCallback() {
		return requesterCallback;
	}

	public void setRequesterCallback(String requesterCallback) {
		this.requesterCallback = requesterCallback;
	}

	public static class CallerInformation {

        private String application = "eGOV_BG_BudgetCS_20200211";
        private String username;

        public CallerInformation(){

        }

        public CallerInformation(String application, String username) {
            this.application = application;
            this.username = username;
        }

        public String getApplication() {
            return application;
        }

        public String getUsername() {
            return username;
        }

    }

}

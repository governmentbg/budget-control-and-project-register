package bg.infosys.daeu.db.dto;

import java.util.Arrays;
import java.util.List;


public class TranslateRequestObject {

	private int priority;
	private String externalReference;
	private String textToTranslate;
	private String sourceLanguage;
	private String domain = "SPD" ; // change to enum later ?
	private List<String> targetLanguages; // maybe add default ?
	private String requesterCallback;

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

	public String getExternalReference() {
		return externalReference;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public void setExternalReference(String externalReference) {
		this.externalReference = externalReference;
	}

	public void setTextToTranslate(String textToTranslate) {
		this.textToTranslate = textToTranslate;
	}

	public void setSourceLanguage(String sourceLanguage) {
		this.sourceLanguage = sourceLanguage;
	}

	public void setTargetLanguages(String... targetLanguages) {
		this.targetLanguages = Arrays.asList(targetLanguages);
	}

	public String getRequesterCallback() {
		return requesterCallback;
	}

	public void setRequesterCallback(String requesterCallback) {
		this.requesterCallback = requesterCallback;
	}

}

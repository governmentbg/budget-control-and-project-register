package bg.infosys.daeu.ws.wsbg.opendata.requests;

import java.util.Locale;

public class ResourceProperties {
	private String file_format;
	 private String name;
	 private int type;
	 private Locale locale;
	 //ArrayList < Object > custom_fields = new ArrayList < Object > ();
	 private String description;

	 public ResourceProperties() {}
	 
	 // Getter Methods 
	 public String getFile_format() {
	  return file_format;
	 }

	 public String getName() {
	  return name;
	 }

	 public int getType() {
	  return type;
	 }

	 public Locale getLocale() {
	  return locale;
	 }

	 public String getDescription() {
	  return description;
	 }

	 // Setter Methods 
	 public void setFile_format(String file_format) {
	  this.file_format = file_format;
	 }

	 public void setName(String name) {
	  this.name = name;
	 }

	 public void setType(int automaticUpload) {
	  this.type = automaticUpload;
	 }

	 public void setLocale(Locale locale) {
	  this.locale = locale;
	 }

	 public void setDescription(String description) {
	  this.description = description;
	 }
}

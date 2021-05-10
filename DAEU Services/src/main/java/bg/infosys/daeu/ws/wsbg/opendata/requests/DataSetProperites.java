package bg.infosys.daeu.ws.wsbg.opendata.requests;

import java.util.Locale;

public class DataSetProperites {
	 private Locale locale;
	 private String name;
	 private int category_id;
	 private String description;
	 private int visibility;
	 private String author_name;
	 private String author_email;
	 private String support_name;
	 private String[] tags;
	 private String support_email;
	 //private ArrayList < Object > custom_fields = new ArrayList < Object > ();

	 public DataSetProperites() {}
	 
	 // Getter Methods 
	 public Locale getLocale() {
	  return locale;
	 }

	 public String getName() {
	  return name;
	 }

	 public int getCategory_id() {
	  return category_id;
	 }

	 public String getDescription() {
	  return description;
	 }

	 public int getVisibility() {
	  return visibility;
	 }

	 public String getAuthor_name() {
	  return author_name;
	 }

	 public String getAuthor_email() {
	  return author_email;
	 }

	 public String getSupport_name() {
	  return support_name;
	 }

	 public String getSupport_email() {
	  return support_email;
	 }

	 public String[] getTags() {
		return tags;
	 }

	// Setter Methods 
	 public void setLocale(Locale locale) {
	  this.locale = locale;
	 }

	 public void setName(String name) {
	  this.name = name;
	 }

	 public void setCategory_id(int category_id) {
	  this.category_id = category_id;
	 }

	 public void setDescription(String description) {
	  this.description = description;
	 }

	 public void setVisibility(int visibility) {
	  this.visibility = visibility;
	 }

	 public void setAuthor_name(String author_name) {
	  this.author_name = author_name;
	 }

	 public void setAuthor_email(String author_email) {
	  this.author_email = author_email;
	 }

	 public void setSupport_name(String support_name) {
	  this.support_name = support_name;
	 }

	 public void setSupport_email(String support_email) {
	  this.support_email = support_email;
	 }
	 
	 public void setTags(String... tags) {
         this.tags = tags; // maybe ?
     }
}
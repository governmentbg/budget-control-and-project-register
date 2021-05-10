package bg.infosys.daeu.ws.pub.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import bg.infosys.common.utils.Strings;

public class TagUtil {
	public static final String convertEmailTags(String content, Map<String, Object> replaceValues) {
		content = convertSubject(content, (String) replaceValues.get(TemplateTag.SUBJECT.getTag()));
		content = convertOutNum(content, (String) replaceValues.get(TemplateTag.OUT_NUM.getTag()));
		content = convertOutDate(content, (Date) replaceValues.get(TemplateTag.OUT_DATE.getTag()));
		return content;
	}
	
	public static final Map<String, Object> createEmailReplaceStrings(String subject, String outNum, Date outDate) {
		Map<String, Object> replaceStrings = new HashMap<String, Object>();
		if (!Strings.isEmpty(subject)) replaceStrings.put(TemplateTag.SUBJECT.getTag(), subject);
		if (!Strings.isEmpty(outNum)) replaceStrings.put(TemplateTag.OUT_NUM.getTag(), outNum);
		if (outDate != null) replaceStrings.put(TemplateTag.OUT_DATE.getTag(), outDate);
		return replaceStrings;
	}
	
	private static String convertSubject(String content, String subject) {
		return content.replace(TemplateTag.SUBJECT.getTag(), subject != null ? subject : "");
	}
	
	private static String convertOutNum(String content, String outNum) {
		return content.replace(TemplateTag.OUT_NUM.getTag(), outNum != null ? outNum : "");
	}
	
	private static String convertOutDate(String content, Date outDate) {
		return content.replace(TemplateTag.OUT_DATE.getTag(), formatDate(outDate));
	}
	
	private static String formatDate(Date date) {
		return new SimpleDateFormat("dd.MM.yyyy").format(date);
	}
	
	public enum TemplateTag {
		RECIPIENT			("%RECIPIENT%"),
		SUBJECT				("%SUBJECT%"),
		OUT_NUM				("%OUT_NUM%"),
		OUT_DATE			("%OUT_DATE%"),
		FORM_TYPE			("%FORM_TYPE%"),
		CURR_DATE			("%CURR_DATE%"),
		EMP_NAME			("%EMP_NAME%"),
		IN_NUM_DATE			("%IN_NUM/DATE%"),
		NOTES				("%NOTES%"),
		
		NEW_LINE			("%NEW_LINE%"),
		
		REASON				("%REASON%"),
		TOKEN				("%TOKEN%"),
		USER_NAME			("%USER_NAME%");
		
		private final String tag;

		private TemplateTag(String tag) {
			this.tag = tag;
		}

		public String getTag() {
			return tag;
		}
	}
}

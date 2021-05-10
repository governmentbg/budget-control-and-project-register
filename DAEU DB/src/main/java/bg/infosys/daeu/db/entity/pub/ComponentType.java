package bg.infosys.daeu.db.entity.pub;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Audited
@Entity
@Table(name = "n_component_types")
public class ComponentType {

	@Id
	@Column(name = _code)
	private String code;
	public static final String _code = "code";
	
	@Column(name = _name)
	private String name;
	public static final String _name = "name";
	
	@Column(name = "has_placeholder")
	private String hasPlaceholder;
	public static final String _hasPlaceholder = "hasPlaceholder";
	
	@Column(name = "can_create")
	private String canCreate;
	public static final String _canCreate = "canCreate";
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHasPlaceholder() {
		return hasPlaceholder;
	}
	public void setHasPlaceholder(String hasPlaceholder) {
		this.hasPlaceholder = hasPlaceholder;
	}
	public String getCanCreate() {
		return canCreate;
	}
	public void setCanCreate(String canCreate) {
		this.canCreate = canCreate;
	}
	
	public enum ComponentTypeConst {
		ADDGRD			("ADDGRD",		"Добавяне на секции"),
		CHANGE  		("CHANGE",		"Стойности за предложение за промяна"),
		COMBO  			("COMBO",		"Падащ списък"),
		DATE   			("DATE",		"Дата"),
		LBL   			("LBL",			"Лейбъл"),
		MULTICOMBO 		("MULTICOMBO",	"Падащ списък с право на повече от един избран елемент"),
		NUM  			("NUM",			"Число"),
		RADIO  			("RADIO", 		"Радио бутони"),
		TOTALTXT  		("TOTALTXT",	"Текст за тоталите в Бюджетен контрол"),
		TXT  			("TXT",			"Текст"),
		YN  			("YN",			"Да/Не"),
		YNNA  			("YNNA",		"Да/ Не/ Не е приложимо"),
		BIGTXT  		("BIGTXT",		"Текстово поле с 10 000 символа");
		
		private final String code;
		private final String name;

		private ComponentTypeConst(String code, String name) {
			this.code = code;
			this.name = name;
		}

		public String getCode() {
			return code;
		}

		public String getName() {
			return name;
		}
	}
}

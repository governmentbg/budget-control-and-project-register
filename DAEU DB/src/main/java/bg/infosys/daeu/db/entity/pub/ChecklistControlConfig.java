package bg.infosys.daeu.db.entity.pub;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import bg.infosys.daeu.db.entity.security.Authority;

@Audited
@Entity
@Table(name = "checklist_control_config", schema="public")
public class ChecklistControlConfig implements Comparable<ChecklistControlConfig> {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "authority_id")
	private Authority authority;
	public static final String _authority = "authority";
	
	@Column(name = "is_final")
	private String isFinal ="N";
	public static final String _isFinal = "isFinal";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "checklist_type_id")
	private ChecklistType checklistType;
	public static final String _checklistType = "checklistType";
	
	@Column(name = "is_valid")
	private String isValid = "Y";
	public static final String _isValid = "isValid";
	
	@Column(name = "yes_option")
	private String yesOption;
	public static final String _yesOption = "yesOption";
	
	@Column(name = "no_option")
	private String noOption;
	public static final String _noOption = "noOption";
	
	@Column(name = "yes_with_notes_option")
	private String yesWithNotesOption;
	public static final String _yesWithNotesOption = "yesWithNotesOption";
	
	@Column(name = "order_num")
	private Integer orderNum;
	public static final String _orderNum = "orderNum";
	
	@Column(name = "hide_control")
	private String hideControl = "N";
	public static final String _hideControl = "hideControl";
	
	public ChecklistControlConfig() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Authority getAuthority() {
		return authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public String getYesOption() {
		return yesOption;
	}

	public void setYesOption(String yesOption) {
		this.yesOption = yesOption;
	}

	public String getNoOption() {
		return noOption;
	}

	public void setNoOption(String noOption) {
		this.noOption = noOption;
	}

	public ChecklistType getChecklistType() {
		return checklistType;
	}

	public void setChecklistType(ChecklistType checklistType) {
		this.checklistType = checklistType;
	}
	
	public String getIsFinal() {
		return isFinal;
	}

	public void setIsFinal(String isFinal) {
		this.isFinal = isFinal;
	}

	public String getHideControl() {
		return hideControl;
	}

	public void setHideControl(String hideControl) {
		this.hideControl = hideControl;
	}
	
	public String getYesWithNotesOption() {
		return yesWithNotesOption;
	}

	public void setYesWithNotesOption(String yesWithNotesOption) {
		this.yesWithNotesOption = yesWithNotesOption;
	}

	@Override
	public int compareTo(ChecklistControlConfig o) {
		return this.orderNum - o.getOrderNum();
	}
}

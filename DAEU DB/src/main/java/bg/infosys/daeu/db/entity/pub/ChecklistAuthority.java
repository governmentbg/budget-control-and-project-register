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
import javax.persistence.Transient;

import org.hibernate.envers.Audited;

import bg.infosys.daeu.db.entity.security.Authority;

@Audited
@Entity
@Table(name = "n_checklist_authority", schema = "public")
public class ChecklistAuthority implements Comparable<ChecklistAuthority> {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "order_num")
	private Integer orderNum;
	public static final String _orderNum = "orderNum";

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "checklist_type_id")
	private ChecklistType checklistType;
	public static final String _checklistType = "checklistType";

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id")
	private Authority authority;
	public static final String _authority = "authority";

	@Column(name = "is_valid")
	private String isValid = "Y";
	public static final String _isValid = "isValid";
	
	@Column(name = "hide_column")
	private String hideColumn = "N";
	public static final String _hideColumn = "hideColumn";

	@Transient
	private boolean isVisible = true;
	
	/* Getters & Setters */
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public ChecklistType getChecklistType() {
		return checklistType;
	}

	public void setChecklistType(ChecklistType checklistType) {
		this.checklistType = checklistType;
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

	public boolean getVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public String getHideColumn() {
		return hideColumn;
	}

	public void setHideColumn(String hideColumn) {
		this.hideColumn = hideColumn;
	}

	@Override
	public int compareTo(ChecklistAuthority o) {
		return this.orderNum - o.getOrderNum();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChecklistAuthority other = (ChecklistAuthority) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
	
	
}

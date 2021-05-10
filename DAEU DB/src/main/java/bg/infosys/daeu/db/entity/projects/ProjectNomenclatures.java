package bg.infosys.daeu.db.entity.projects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Audited
@Entity
@Table(schema = "projects" ,name = "n_projects")
public class ProjectNomenclatures implements Comparable<ProjectNomenclatures> {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "row_type_id")
	private RowType projectRowType;
	public static final String _projectRowType = "projectRowType";
	
	@Column(name = _value)
	private String value;
	public static final String _value = "value";
	
	@Column(name = "order_num")
	private Integer orderNum = 0;
	public static final String _orderNum = "orderNum";
	
	@Column(name = "is_valid")
	private String isValid = "Y";
	public static final String _isValid = "isValid";
	
	public ProjectNomenclatures() {}
	
	public ProjectNomenclatures(RowType rowType, Integer orderNum) {
		this.projectRowType = rowType;
		this.orderNum = orderNum;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public RowType getProjectRowType() {
		return projectRowType;
	}

	public void setProjectRowType(RowType projectRowType) {
		this.projectRowType = projectRowType;
	}

	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	
	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	@Override
	public int compareTo(ProjectNomenclatures o) {
		if (this.orderNum == null) {
			return -1;
		} else if(o.getOrderNum() == null) {
			return 1;
		} else if (this.orderNum == null && o.getOrderNum() == null) {
			return 0;
		} else {
			return this.orderNum - o.getOrderNum();
		}
	}
}

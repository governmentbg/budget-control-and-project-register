package bg.infosys.daeu.db.entity.budgets;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import bg.infosys.daeu.db.entity.pub.CPVCode;

@Audited
@Entity
@Table(schema = "budgets", name = "selected_cpv_code")
public class SelectedCPVCode {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cpv_code_id")
	private CPVCode cpvCode;
	public static final String _cpvCode = "cpvCode";
	
	public SelectedCPVCode() {}
	
	public SelectedCPVCode(CPVCode cpv) {
		this.cpvCode = cpv;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CPVCode getCpvCode() {
		return cpvCode;
	}

	public void setCpvCode(CPVCode cpvCode) {
		this.cpvCode = cpvCode;
	}
}

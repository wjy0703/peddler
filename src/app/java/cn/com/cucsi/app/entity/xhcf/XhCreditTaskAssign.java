package cn.com.cucsi.app.entity.xhcf;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import cn.com.cucsi.app.entity.baseinfo.Employee;
import cn.com.cucsi.core.orm.hibernate.AuditableEntity;

/**
 * XhCreditTaskAssign entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
// 表名与类名不相同时重新定义表名.
@Table(name = "XH_CREDIT_TASK_ASSIGN")
// 默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XhCreditTaskAssign extends AuditableEntity {

	// Fields
	private static final long serialVersionUID = -2276242824197821671L;
	private XhJksq loanApply;// 借款申请
	private Employee employee;// 初审人员
	
	private Employee secondAduitEmployee;// 复审人员
	
	private Employee finalAduitEmployee;// 复审人员
	
	@ManyToOne(cascade = CascadeType.REFRESH, optional = true,fetch = FetchType.LAZY)
	@JoinColumn(name = "FINAL_AUDIT_EMPLOYEE_ID")
	public Employee getFinalAduitEmployee() {
		return finalAduitEmployee;
	}

	public void setFinalAduitEmployee(Employee finalAduitEmployee) {
		this.finalAduitEmployee = finalAduitEmployee;
	}
	
	

	@ManyToOne(cascade = CascadeType.REFRESH, optional = true,fetch = FetchType.LAZY)
	@JoinColumn(name = "SECOND_AUDIT_EMPLOYEE_ID")
	public Employee getSecondAduitEmployee() {
		return secondAduitEmployee;
	}

	public void setSecondAduitEmployee(Employee secondAduitEmployee) {
		this.secondAduitEmployee = secondAduitEmployee;
	}

	@ManyToOne(cascade = CascadeType.REFRESH, optional = false,fetch = FetchType.LAZY)
	@JoinColumn(name = "LOAN_APPLY_ID")
	public XhJksq getLoanApply() {
		return loanApply;
	}

	
	
	public void setLoanApply(XhJksq loanApply) {
		this.loanApply = loanApply;
	}

	@ManyToOne(cascade = CascadeType.REFRESH, optional = false,fetch = FetchType.LAZY)
	@JoinColumn(name = "FIRST_AUDIT_EMPLOYEE_ID")
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}

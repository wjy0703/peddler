package cn.com.cucsi.app.entity.xhcf;



import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import cn.com.cucsi.core.orm.hibernate.AuditableEntity;

/**
 * XhLoanerAccount entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
// 表名与类名不相同时重新定义表名.
@Table(name = "XH_LOANER_ACCOUNT")
// 默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XhLoanerAccount extends AuditableEntity {

	// Fields
	private static final long serialVersionUID = -2276242824197821671L;
	private XhJkht loanContract;// 借款合同

	private XhAvailableValueOfClaims xhAvailableValueOfClaims;

	/** 可用债权价值 */
	@OneToOne(cascade = CascadeType.REFRESH, optional = false,fetch = FetchType.LAZY)
	@JoinColumn(name = "AVAILABLE_VALUE_ID")
	public XhAvailableValueOfClaims getXhAvailableValueOfClaims() {
		return xhAvailableValueOfClaims;
	}

	public void setXhAvailableValueOfClaims(
			XhAvailableValueOfClaims xhAvailableValueOfClaims) {
		this.xhAvailableValueOfClaims = xhAvailableValueOfClaims;
	}

	/** 借款合同 */
	@OneToOne(cascade = CascadeType.REFRESH, optional = false,fetch = FetchType.LAZY)
	@JoinColumn(name = "LOAN_CONTRACT_ID")
	public XhJkht getLoanContract() {
		return this.loanContract;
	}

	/** 借款合同 */
	public void setLoanContract(XhJkht loanContract) {
		this.loanContract = loanContract;
	}

	private XhJksq loanApply;// 借款申请

	/** 借款申请 */
	@OneToOne(cascade = CascadeType.REFRESH, optional = false,fetch = FetchType.LAZY)
	@JoinColumn(name = "LOAN_APPLY_ID")
	public XhJksq getLoanApply() {
		return this.loanApply;
	}

	/** 借款申请 */
	public void setLoanApply(XhJksq loanApply) {
		this.loanApply = loanApply;
	}

	private Double accountBalance;// 账户余额

	/** 账户余额 */
	@Column(columnDefinition = DEF_NUM15_5)
	public Double getAccountBalance() {
		return this.accountBalance;
	}

	/** 账户余额 */
	public void setAccountBalance(Double accountBalance) {
		this.accountBalance = accountBalance;
	}

	private Integer accountState;// 账户状态: 0 正常账户 1历史账户

	/** 账户状态: 0 正常账户 1历史账户 */
	@Column(columnDefinition = DEF_NUM18)
	public Integer getAccountState() {
		return this.accountState;
	}

	/** 账户状态: 0 正常账户 1历史账户 */
	public void setAccountState(Integer accountState) {
		this.accountState = accountState;
	}
}

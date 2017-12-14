package cn.com.cucsi.app.entity.xhcf;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
import org.springframework.format.annotation.DateTimeFormat;

import cn.com.cucsi.core.orm.hibernate.AuditableEntity;

/**
 * XhLoanerAccountInfo entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
// 表名与类名不相同时重新定义表名.
@Table(name = "XH_LOANER_ACCOUNT_INFO")
// 默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XhLoanerAccountInfo extends AuditableEntity {

	// Fields
	private static final long serialVersionUID = -2276242824197821671L;
	private XhLoanerAccount loanerMainAccount;// 借款人主账户

	/** 借款人主账户 */

	@ManyToOne(cascade = CascadeType.REFRESH, optional = false,fetch = FetchType.LAZY)
	@JoinColumn(name = "LOANER_MAIN_ACCOUNT_ID")
	public XhLoanerAccount getLoanerMainAccount() {
		return this.loanerMainAccount;
	}

	/** 借款人主账户 */
	public void setLoanerMainAccount(XhLoanerAccount loanerMainAccount) {
		this.loanerMainAccount = loanerMainAccount;
	}

	private Integer dealingType;// 交易类型 0:还款存入 1：结算划扣

	/** 交易类型 0:还款存入 1：结算划扣 */
	@Column(columnDefinition = DEF_NUM18)
	public Integer getDealingType() {
		return this.dealingType;
	}

	/** 交易类型 0:还款存入 1：结算划扣 */
	public void setDealingType(Integer dealingType) {
		this.dealingType = dealingType;
	}

	private Double deailingMoney;// 交易金额

	/** 交易金额 */
	@Column(columnDefinition = DEF_NUM15_5)
	public Double getDeailingMoney() {
		return this.deailingMoney;
	}

	/** 交易金额 */
	public void setDeailingMoney(Double deailingMoney) {
		this.deailingMoney = deailingMoney;
	}

	private Double balance;// 结余

	/** 交易金额 */
	@Column(columnDefinition = DEF_NUM15_5)
	public Double getBalance() {
		return this.balance;
	}

	/** 交易金额 */
	public void setBalance(Double balance) {
		this.balance = balance;
	}

	private Date dealingTime;// 交易时间

	/** 交易时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDealingTime() {
		return this.dealingTime;
	}

	/** 交易时间 */
	public void setDealingTime(Date dealingTime) {
		this.dealingTime = dealingTime;
	}
}

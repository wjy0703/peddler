package cn.com.cucsi.app.entity.xhcf;

import java.util.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.format.annotation.DateTimeFormat;

import cn.com.cucsi.core.orm.hibernate.AuditableEntity;
import cn.com.cucsi.core.orm.hibernate.IdEntity;

/**
 * XhCapitalOverdue entity. 
 * @author MyEclipse Persistence Tools
 */
@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "XH_CAPITAL_OVERDUE")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XhCapitalOverdue extends IdEntity{

	// Fields
	private static final long serialVersionUID = -2276242824197821671L;
	
	private String bankName;//银行名称 
	/**银行名称*/
	@Column(columnDefinition=DEF_STR256)
	public String getBankName() {
		return this.bankName;
	}
	/**银行名称*/
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	private String bankNumber;//银行账号
	/**银行账号*/
	@Column(columnDefinition=DEF_STR64)
	public String getBankNumber() {
		return this.bankNumber;
	}
	/**银行账号*/
	public void setBankNumber(String bankNumber) {
		this.bankNumber = bankNumber;
	}
	private String bankOpen;//银行开户行
	/**银行开户行*/
	@Column(columnDefinition=DEF_STR256)
	public String getBankOpen() {
		return this.bankOpen;
	}
	/**银行开户行*/
	public void setBankOpen(String bankOpen) {
		this.bankOpen = bankOpen;
	}
	private Double damagesMoney;//违约金
	/**违约金*/
	@Column(columnDefinition=DEF_NUM10_2)
	public Double getDamagesMoney() {
		return this.damagesMoney;
	}
	/**违约金*/
	public void setDamagesMoney(Double damagesMoney) {
		this.damagesMoney = damagesMoney;
	}
	private Long lenderId;//借款人ID
	/**借款人ID*/
	@Column(columnDefinition=DEF_NUM18)
	public Long getLenderId() {
		return this.lenderId;
	}
	/**借款人ID*/
	public void setLenderId(Long lenderId) {
		this.lenderId = lenderId;
	}
	private String lenderIdCard;//借款人身份证号
	/**借款人身份证号*/
	@Column(columnDefinition=DEF_STR32)
	public String getLenderIdCard() {
		return this.lenderIdCard;
	}
	/**借款人身份证号*/
	public void setLenderIdCard(String lenderIdCard) {
		this.lenderIdCard = lenderIdCard;
	}
	private String lenderName;//借款人名称
	/**借款人名称*/
	@Column(columnDefinition=DEF_STR64)
	public String getLenderName() {
		return this.lenderName;
	}
	/**借款人名称*/
	public void setLenderName(String lenderName) {
		this.lenderName = lenderName;
	}
	private String lenderNumber;//借款编号
	/**借款编号*/
	@Column(columnDefinition=DEF_STR64)
	public String getLenderNumber() {
		return this.lenderNumber;
	}
	/**借款编号*/
	public void setLenderNumber(String lenderNumber) {
		this.lenderNumber = lenderNumber;
	}
	private Date overdueDate;//逾期时间
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date getOverdueDate() {
		return overdueDate;
	}

	public void setOverdueDate(Date overdueDate) {
		this.overdueDate = overdueDate;
	}
	private Double overdueMoney;//逾期金额
	/**逾期金额*/
	@Column(columnDefinition=DEF_NUM10_2)
	public Double getOverdueMoney() {
		return this.overdueMoney;
	}
	/**逾期金额*/
	public void setOverdueMoney(Double overdueMoney) {
		this.overdueMoney = overdueMoney;
	}
	private String overdueStatr;//逾期状态
	/**逾期状态*/
	@Column(columnDefinition=DEF_STR32)
	public String getOverdueStatr() {
		return this.overdueStatr;
	}
	/**逾期状态*/
	public void setOverdueStatr(String overdueStatr) {
		this.overdueStatr = overdueStatr;
	}
	private Double punishInterest;//罚息
	/**罚息*/
	@Column(columnDefinition=DEF_NUM10_2)
	public Double getPunishInterest() {
		return this.punishInterest;
	}
	/**罚息*/
	public void setPunishInterest(Double punishInterest) {
		this.punishInterest = punishInterest;
	}
	private String spareField01;//备用字段1
	/**备用字段1*/
	@Column(columnDefinition=DEF_STR256)
	public String getSpareField01() {
		return this.spareField01;
	}
	/**备用字段1*/
	public void setSpareField01(String spareField01) {
		this.spareField01 = spareField01;
	}
	private String spareField02;//备用字段2
	/**备用字段2*/
	@Column(columnDefinition=DEF_STR256)
	public String getSpareField02() {
		return this.spareField02;
	}
	/**备用字段2*/
	public void setSpareField02(String spareField02) {
		this.spareField02 = spareField02;
	}
	private String spareField03;//备用字段3
	/**备用字段3*/
	@Column(columnDefinition=DEF_STR256)
	public String getSpareField03() {
		return this.spareField03;
	}
	/**备用字段3*/
	public void setSpareField03(String spareField03) {
		this.spareField03 = spareField03;
	}
	private String spareField04;//备用字段4
	@Column(columnDefinition=DEF_STR256)
	public String getSpareField04() {
		return spareField04;
	}
	public void setSpareField04(String spareField04) {
		this.spareField04 = spareField04;
	}
	private String spareField05;//备用字段5
	@Column(columnDefinition=DEF_STR256)
	public String getSpareField05() {
		return spareField05;
	}
	public void setSpareField05(String spareField05) {
		this.spareField05 = spareField05;
	}
	
	private String spareField06;//备用字段6
	@Column(columnDefinition=DEF_STR256)
	public String getSpareField06() {
		return spareField06;
	}
	public void setSpareField06(String spareField06) {
		this.spareField06 = spareField06;
	}
	
	private XhJkht loanContract;// 借款合同
	@OneToOne(cascade = CascadeType.REFRESH, optional = false,fetch = FetchType.LAZY)
	@JoinColumn(name = "LOAN_CONTRACT_ID")
	public XhJkht getLoanContract() {
		return loanContract;
	}
	public void setLoanContract(XhJkht loanContract) {
		this.loanContract = loanContract;
	}
	
}

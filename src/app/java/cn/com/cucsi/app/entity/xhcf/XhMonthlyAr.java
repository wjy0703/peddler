package cn.com.cucsi.app.entity.xhcf;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import cn.com.cucsi.core.orm.hibernate.AuditableEntity;
import cn.com.cucsi.core.orm.hibernate.IdEntity;

/**
 * XhMonthlyAr entity. 
 * @author MyEclipse Persistence Tools
 */
@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "XH_MONTHLY_AR")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE) 
public class XhMonthlyAr extends IdEntity{

	// Fields
	private static final long serialVersionUID = -2276242824197821671L;
	private Double additional;//帐外
	/**帐外*/
	@Column(columnDefinition=DEF_NUM15_5)
	public Double getAdditional() {
		return this.additional;
	}
	/**帐外*/
	public void setAdditional(Double additional) {
		this.additional = additional;
	}
	private String area;//省份
	/**省份*/
	@Column(columnDefinition=DEF_STR32)
	public String getArea() {
		return this.area;
	}
	/**省份*/
	public void setArea(String area) {
		this.area = area;
	}
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
	private String bankNumber;//银行账户
	/**银行账户*/
	@Column(columnDefinition=DEF_STR64)
	public String getBankNumber() {
		return this.bankNumber;
	}
	/**银行账户*/
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
	private String billDay;//账单日
	/**账单日*/
	@Column(columnDefinition=DEF_STR32)
	public String getBillDay() {
		return this.billDay;
	}
	/**账单日*/
	public void setBillDay(String billDay) {
		this.billDay = billDay;
	}
	private String city;//地市
	/**地市*/
	@Column(columnDefinition=DEF_STR64)
	public String getCity() {
		return this.city;
	}
	/**地市*/
	public void setCity(String city) {
		this.city = city;
	}
	private Double interest;//利息
	/**利息*/
	@Column(columnDefinition=DEF_NUM15_5)
	public Double getInterest() {
		return this.interest;
	}
	/**利息*/
	public void setInterest(Double interest) {
		this.interest = interest;
	}
	private Long loanId;//借款人ID
	/**借款人ID*/
	@Column(columnDefinition=DEF_NUM18)
	public Long getLoanId() {
		return this.loanId;
	}
	/**借款人ID*/
	public void setLoanId(Long loanId) {
		this.loanId = loanId;
	}
	private String loanIdCard;//借款人身份证号
	/**借款人身份证号*/
	@Column(columnDefinition=DEF_STR32)
	public String getLoanIdCard() {
		return this.loanIdCard;
	}
	/**借款人身份证号*/
	public void setLoanIdCard(String loanIdCard) {
		this.loanIdCard = loanIdCard;
	}
	private String loanName;//借款人名称
	/**借款人名称*/
	@Column(columnDefinition=DEF_STR64)
	public String getLoanName() {
		return this.loanName;
	}
	/**借款人名称*/
	public void setLoanName(String loanName) {
		this.loanName = loanName;
	}
	private String loanNumber;//借款人编号
	/**借款人编号*/
	@Column(columnDefinition=DEF_STR64)
	public String getLoanNumber() {
		return this.loanNumber;
	}
	/**借款人编号*/
	public void setLoanNumber(String loanNumber) {
		this.loanNumber = loanNumber;
	}
	private String loanPro;//借款产品
	/**借款产品*/
	@Column(columnDefinition=DEF_STR32)
	public String getLoanPro() {
		return this.loanPro;
	}
	/**借款产品*/
	public void setLoanPro(String loanPro) {
		this.loanPro = loanPro;
	}
	private String loanState;//借款状态
	/**借款状态*/
	@Column(columnDefinition=DEF_STR32)
	public String getLoanState() {
		return this.loanState;
	}
	/**借款状态*/
	public void setLoanState(String loanState) {
		this.loanState = loanState;
	}
	private Double money;//金额
	/**金额*/
	@Column(columnDefinition=DEF_NUM10_2)
	public Double getMoney() {
		return this.money;
	}
	/**金额*/
	public void setMoney(Double money) {
		this.money = money;
	}
	private Timestamp settlementDate;//结算日期
	/**结算日期*/
	@Column(insertable = false)
	public Timestamp getSettlementDate() {
		return this.settlementDate;
	}
	/**结算日期*/
	public void setSettlementDate(Timestamp settlementDate) {
		this.settlementDate = settlementDate;
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
}

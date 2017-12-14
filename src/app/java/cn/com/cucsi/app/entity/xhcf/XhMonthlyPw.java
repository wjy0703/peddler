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
 * XhMonthlyPw entity. 
 * @author MyEclipse Persistence Tools
 */
@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "XH_MONTHLY_PW")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XhMonthlyPw extends IdEntity{ 

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
	private Long lenderId;//出借人ID
	/**出借人ID*/
	@Column(columnDefinition=DEF_NUM18)
	public Long getLenderId() {
		return this.lenderId;
	}
	/**出借人ID*/
	public void setLenderId(Long lenderId) {
		this.lenderId = lenderId;
	}
	private String lenderIdCard;//出借人身份证号
	/**出借人身份证号*/
	@Column(columnDefinition=DEF_STR32)
	public String getLenderIdCard() {
		return this.lenderIdCard;
	}
	/**出借人身份证号*/
	public void setLenderIdCard(String lenderIdCard) {
		this.lenderIdCard = lenderIdCard;
	}
	private String lenderName;//出借人名称
	/**出借人名称*/
	@Column(columnDefinition=DEF_STR64)
	public String getLenderName() {
		return this.lenderName;
	}
	/**出借人名称*/
	public void setLenderName(String lenderName) {
		this.lenderName = lenderName;
	}
	private String lenderNumber;//出借编号
	/**出借编号*/
	@Column(columnDefinition=DEF_STR64)
	public String getLenderNumber() {
		return this.lenderNumber;
	}
	/**出借编号*/
	public void setLenderNumber(String lenderNumber) {
		this.lenderNumber = lenderNumber;
	}
	private String lenderState;//出借状态
	/**出借状态*/
	@Column(columnDefinition=DEF_STR32)
	public String getLenderState() {
		return this.lenderState;
	}
	/**出借状态*/
	public void setLenderState(String lenderState) {
		this.lenderState = lenderState;
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
	private Timestamp payDate;//付款日期
	/**付款日期*/
	@Column(insertable = false)
	public Timestamp getPayDate() {
		return this.payDate;
	}
	/**付款日期*/
	public void setPayDate(Timestamp payDate) {
		this.payDate = payDate;
	}
	private String payType;//付款类型
	/**付款类型*/
	@Column(columnDefinition=DEF_STR32)
	public String getPayType() {
		return this.payType;
	}
	/**付款类型*/
	public void setPayType(String payType) {
		this.payType = payType;
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

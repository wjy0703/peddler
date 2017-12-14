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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.format.annotation.DateTimeFormat;

import cn.com.cucsi.core.orm.hibernate.AuditableEntity;

/**
 * XhHouseLoanAuditInfo entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
// 表名与类名不相同时重新定义表名.
@Table(name = "XH_HOUSE_LOAN_AUDIT_INFO")
// 默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XhHouseLoanAuditInfo extends AuditableEntity {

	// Fields
	private static final long serialVersionUID = -2276242824197821671L;
	
	@Column(columnDefinition = DEF_STR50)
	public String getDyqPresonName() {
		return dyqPresonName;
	}

	public void setDyqPresonName(String dyqPresonName) {
		this.dyqPresonName = dyqPresonName;
	}
	@Column(columnDefinition = DEF_STR50)
	public String getDyqPresonPhone() {
		return dyqPresonPhone;
	}

	public void setDyqPresonPhone(String dyqPresonPhone) {
		this.dyqPresonPhone = dyqPresonPhone;
	}
	@Column(columnDefinition = DEF_STR50)
	public String getDyqPresonAddress() {
		return dyqPresonAddress;
	}

	public void setDyqPresonAddress(String dyqPresonAddress) {
		this.dyqPresonAddress = dyqPresonAddress;
	}
	@Column(columnDefinition = DEF_STR50)
	public String getDyqPresonCardId() {
		return dyqPresonCardId;
	}

	public void setDyqPresonCardId(String dyqPresonCardId) {
		this.dyqPresonCardId = dyqPresonCardId;
	}

	private String dyqPresonName;
	private String dyqPresonPhone;
	private String dyqPresonAddress;
	private String dyqPresonCardId;
	private String houseType;// 房屋性质

	/** 房屋性质 */
	@Column(columnDefinition = DEF_STR50)
	public String getHouseType() {
		return this.houseType;
	}

	/** 房屋性质 */
	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}

	private Double houseEvaluateValue;// 房屋评估价值

	/** 房屋评估价值 */
	@Column(columnDefinition = DEF_NUM15_5)
	public Double getHouseEvaluateValue() {
		return this.houseEvaluateValue;
	}

	/** 房屋评估价值 */
	public void setHouseEvaluateValue(Double houseEvaluateValue) {
		this.houseEvaluateValue = houseEvaluateValue;
	}

	private Double creditVisitFee;// 信访费

	/** 信访费 */
	@Column(columnDefinition = DEF_NUM15_5)
	public Double getCreditVisitFee() {
		return this.creditVisitFee;
	}

	/** 信访费 */
	public void setCreditVisitFee(Double creditVisitFee) {
		this.creditVisitFee = creditVisitFee;
	}

	private Double allFeeRate;// 综合费率

	/** 综合费率 */
	@Column(columnDefinition = DEF_NUM15_5)
	public Double getAllFeeRate() {
		return this.allFeeRate;
	}

	/** 综合费率 */
	public void setAllFeeRate(Double allFeeRate) {
		this.allFeeRate = allFeeRate;
	}

	private Double serviceFeeRate;// 服务费率

	/** 服务费率 */
	@Column(columnDefinition = DEF_NUM15_5)
	public Double getServiceFeeRate() {
		return this.serviceFeeRate;
	}

	/** 服务费率 */
	public void setServiceFeeRate(Double serviceFeeRate) {
		this.serviceFeeRate = serviceFeeRate;
	}

	private Double monthRate;// 月利率

	/** 月利率 */
	@Column(columnDefinition = DEF_NUM15_5)
	public Double getMonthRate() {
		return this.monthRate;
	}

	/** 月利率 */
	public void setMonthRate(Double monthRate) {
		this.monthRate = monthRate;
	}

	private Integer loanMonth;// 贷款期数

	/** 贷款期数 */
	@Column(columnDefinition = DEF_NUM18)
	public Integer getLoanMonth() {
		return this.loanMonth;
	}

	/** 贷款期数 */
	public void setLoanMonth(Integer loanMonth) {
		this.loanMonth = loanMonth;
	}

	private Date signContractDate;// 签约日期

	/** 签约日期 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getSignContractDate() {
		return this.signContractDate;
	}

	/** 签约日期 */
	public void setSignContractDate(Date signContractDate) {
		this.signContractDate = signContractDate;
	}

	// private Long houseLoanApplyId;//借款申请ID
	// /**借款申请ID*/
	// @Column(columnDefinition=DEF_NUM18)
	// public Long getHouseLoanApplyId() {
	// return this.houseLoanApplyId;
	// }
	// /**借款申请ID*/
	// public void setHouseLoanApplyId(Long houseLoanApplyId) {
	// this.houseLoanApplyId = houseLoanApplyId;
	// }
	// private Long houseLoanAduitId;//借款审核ID
	// /**借款审核ID*/
	// @Column(columnDefinition=DEF_NUM18)
	// public Long getHouseLoanAduitId() {
	// return this.houseLoanAduitId;
	// }
	// /**借款审核ID*/
	// public void setHouseLoanAduitId(Long houseLoanAduitId) {
	// this.houseLoanAduitId = houseLoanAduitId;
	// }

	private XhHouseLoanApply xhHouseLoanApply;

	@OneToOne(cascade = CascadeType.REFRESH, optional = false,fetch = FetchType.LAZY)
	@JoinColumn(name = "HOUSE_LOAN_APPLY_ID")
	public XhHouseLoanApply getXhHouseLoanApply() {
		return xhHouseLoanApply;
	}

	public void setXhHouseLoanApply(XhHouseLoanApply xhHouseLoanApply) {
		this.xhHouseLoanApply = xhHouseLoanApply;
	}

	private XhHouseLoanAudit xhHouseLoanAudit;

	@OneToOne(cascade = CascadeType.REFRESH, optional = false,fetch = FetchType.LAZY)
	@JoinColumn(name = "HOUSE_LOAN_ADUIT_ID")
	public XhHouseLoanAudit getXhHouseLoanAudit() {
		return xhHouseLoanAudit;
	}

	public void setXhHouseLoanAudit(XhHouseLoanAudit xhHouseLoanAudit) {
		this.xhHouseLoanAudit = xhHouseLoanAudit;
	}

	/** 收费方式 */
	private Integer chargeType;

	@Column(columnDefinition = DEF_NUM18)
	public Integer getChargeType() {
		return chargeType;
	}

	public void setChargeType(Integer chargeType) {
		this.chargeType = chargeType;
	}

	private double contractMoney;
	
	@Column(columnDefinition = DEF_NUM15_5)
	public double getContractMoney() {
		return contractMoney;
	}

	public void setContractMoney(double contractMoney) {
		this.contractMoney = contractMoney;
	}

}

package cn.com.cucsi.app.entity.xhcf;

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

import cn.com.cucsi.core.orm.hibernate.AuditableEntity;
import cn.com.cucsi.core.utils.DateUtils;

/**
 * XhCreditAudit entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
// 表名与类名不相同时重新定义表名.
@Table(name = "XH_CREDIT_AUDIT")
// 默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XhCreditAudit extends AuditableEntity {

	// Fields
	private static final long serialVersionUID = -2276242824197821671L;
	private String creditAuditReport;// 信审报告

	/** 信审报告 */
	@Column(columnDefinition = DEF_STR1000)
	public String getCreditAuditReport() {
		return this.creditAuditReport;
	}

	/** 信审报告 */
	public void setCreditAuditReport(String creditAuditReport) {
		this.creditAuditReport = creditAuditReport;
	}

	private String creditRefuseReason;// 拒贷原因

	/** 拒贷原因 */
	@Column(columnDefinition = DEF_STR200)
	public String getCreditRefuseReason() {
		return this.creditRefuseReason;
	}

	/** 拒贷原因 */
	public void setCreditRefuseReason(String creditRefuseReason) {
		this.creditRefuseReason = creditRefuseReason;
	}

	private String outVisitFee;// 外访费

	/** 外访费 */
	@Column(columnDefinition = DEF_NUM13_2)
	public String getOutVisitFee() {
		return this.outVisitFee;
	}

	/** 外访费 */
	public void setOutVisitFee(String outVisitFee) {
		this.outVisitFee = outVisitFee;
	}

	private String creditAllRate;// 综合费率

	/** 综合费率 */
	@Column(columnDefinition = DEF_NUM13_2)
	public String getCreditAllRate() {
		return this.creditAllRate;
	}

	/** 综合费率 */
	public void setCreditAllRate(String creditAllRate) {
		this.creditAllRate = creditAllRate;
	}

	private String creditMonth;// 授信期限

	/** 授信期限 */
	@Column(columnDefinition = DEF_NUM13_2)
	public String getCreditMonth() {
		return this.creditMonth;
	}

	/** 授信期限 */
	public void setCreditMonth(String creditMonth) {
		this.creditMonth = creditMonth;
	}

	private String creditAmount;// 授信金额

	/** 授信金额 */
	@Column(columnDefinition = DEF_NUM13_2)
	public String getCreditAmount() {
		return this.creditAmount;
	}

	/** 授信金额 */
	public void setCreditAmount(String creditAmount) {
		this.creditAmount = creditAmount;
	}

	private String creditState;// 信审状态：0信审未结束 1信审结束

	/** 信审状态：0信审未结束 1信审结束 */
	@Column(columnDefinition = DEF_INT4)
	public String getCreditState() {
		return this.creditState;
	}

	/** 信审状态：0信审未结束 1信审结束 */
	public void setCreditState(String creditState) {
		this.creditState = creditState;
	}

	private String creditResult;// 信审结果：1通过、0拒绝

	/** 信审结果：1通过、0拒绝 */
	@Column(columnDefinition = DEF_INT4)
	public String getCreditResult() {
		return this.creditResult;
	}

	/** 信审结果：1通过、0拒绝 */
	public void setCreditResult(String creditResult) {
		this.creditResult = creditResult;
	}
	private String passedCustomerNo;// 通过的客户编号
	/** 通过的客户编号 */
	@Column(columnDefinition = DEF_INT6)
	public String getPassedCustomerNo() {
		return this.passedCustomerNo;
	}
	/** 通过的客户编号 */
	public void setPassedCustomerNo(String passedCustomerNo) {
		this.passedCustomerNo = passedCustomerNo;
	}
	
	
	private String creditType;// 信审类型：初审、复审、终审

	/** 信审类型：初审、复审、终审 */
	@Column(columnDefinition = DEF_INT4)
	public String getCreditType() {
		return this.creditType;
	}

	/** 信审类型：初审、复审、终审 */
	public void setCreditType(String creditType) {
		this.creditType = creditType;
	}
	
	
	
	

	private XhJksq loanApply;// 借款申请
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false,fetch = FetchType.LAZY)
	@JoinColumn(name = "LOAN_APPLY_ID")
	public XhJksq getLoanApply() {
		return loanApply;
	}
	
	public void setLoanApply(XhJksq loanApply) {
		this.loanApply = loanApply;
	}

	//加急费用
    private Double urgentCreditFee;
    @Column(columnDefinition = DEF_NUM15_5)
	public Double getUrgentCreditFee() {
		return this.urgentCreditFee;
	}

  //加急费用
	public void setUrgentCreditFee(Double urgentCreditFee) {
		this.urgentCreditFee = urgentCreditFee;
	}
	/*
	private String firstAuditTime;

	public String getFirstAuditTime() {
		this.firstAuditTime = DateUtils.format(this.createTime, "yyyy-MM");
		return firstAuditTime;
	}

	public void setFirstAuditTime(String firstAuditTime) {
		this.firstAuditTime = DateUtils.format(this.createTime, "yyyy-MM");
	}
	*/

}

package cn.com.cucsi.vechicle.entity.xhcf;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import cn.com.cucsi.core.orm.hibernate.AuditableEntity;

/**
 * XhCarAudit entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
// 表名与类名不相同时重新定义表名.
@Table(name = "XH_CAR_AUDIT")
// 默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XhCarAudit extends AuditableEntity {

    // Fields
    private static final long serialVersionUID = -2276242824197821671L;
    
    private Long auditEmployeeId;
    
    @Column(columnDefinition = DEF_NUM18)
    public Long getAuditEmployeeId() {
		return auditEmployeeId;
	}

	public void setAuditEmployeeId(Long auditEmployeeId) {
		this.auditEmployeeId = auditEmployeeId;
	}
    
    
    private Long creditType;// 信审类型：初审(1)、复审(2)、终审(100)

	/** 信审类型：初审(1)、复审(2)、终审(100) */
    @Column(columnDefinition = DEF_NUM3)
    public Long getCreditType() {
        return this.creditType;
    }

    /** 信审类型：初审(1)、复审(2)、终审(100) */
    public void setCreditType(Long creditType) {
        this.creditType = creditType;
    }

    private Long creditState;// 信审状态：0信审未结束 1信审结束

    /** 信审状态：0信审未结束 1信审结束 */
    @Column(columnDefinition = DEF_NUM3)
    public Long getCreditState() {
        return this.creditState;
    }

    /** 信审状态：0信审未结束 1信审结束 */
    public void setCreditState(Long creditState) {
        this.creditState = creditState;
    }

    private Double creditAmount;// 审批金额

    /** 审批金额 */
    @Column(columnDefinition = DEF_NUM18_2)
    public Double getCreditAmount() {
        return this.creditAmount;
    }

    /** 审批金额 */
    public void setCreditAmount(Double creditAmount) {
        this.creditAmount = creditAmount;
    }

    private Long creditMonth;// 借款期限（天）

    /** 借款期限（天） */
    @Column(columnDefinition = DEF_NUM5)
    public Long getCreditMonth() {
        return this.creditMonth;
    }

    /** 借款期限（天） */
    public void setCreditMonth(Long creditMonth) {
        this.creditMonth = creditMonth;
    }

    private Double creditAllRate;// 综合费率

    /** 综合费率 */
    @Column(columnDefinition = DEF_NUM10_4)
    public Double getCreditAllRate() {
        return this.creditAllRate;
    }

    /** 综合费率 */
    public void setCreditAllRate(Double creditAllRate) {
        this.creditAllRate = creditAllRate;
    }

    private Double operationFee;// 业务收费

    /** 业务收费 */
    @Column(columnDefinition = DEF_NUM10_2)
    public Double getOperationFee() {
        return this.operationFee;
    }

    /** 业务收费 */
    public void setOperationFee(Double operationFee) {
        this.operationFee = operationFee;
    }

    private Double outVisitFee;// 外访费(需求说明文档没有)

    /** 外访费(需求说明文档没有) */
    @Column(columnDefinition = DEF_NUM10_2)
    public Double getOutVisitFee() {
        return this.outVisitFee;
    }

    /** 外访费(需求说明文档没有) */
    public void setOutVisitFee(Double outVisitFee) {
        this.outVisitFee = outVisitFee;
    }

    private Double urgentCreditFee;// 加急费需求说明文档没有)

    /** 加急费需求说明文档没有) */
    @Column(columnDefinition = DEF_NUM15_5)
    public Double getUrgentCreditFee() {
        return this.urgentCreditFee;
    }

    /** 加急费需求说明文档没有) */
    public void setUrgentCreditFee(Double urgentCreditFee) {
        this.urgentCreditFee = urgentCreditFee;
    }

    private String creditRefuseReason;// 拒贷原因

    /** 拒贷原因 */
    @Column(columnDefinition = DEF_STR1000)
    public String getCreditRefuseReason() {
        return this.creditRefuseReason;
    }

    /** 拒贷原因 */
    public void setCreditRefuseReason(String creditRefuseReason) {
        this.creditRefuseReason = creditRefuseReason;
    }

    private String creditAuditReport;// 信审意见

    /** 信审意见 */
    @Column(columnDefinition = DEF_STR1000)
    public String getCreditAuditReport() {
        return this.creditAuditReport;
    }

    /** 信审意见 */
    public void setCreditAuditReport(String creditAuditReport) {
        this.creditAuditReport = creditAuditReport;
    }

    private Double creditResult;// 信审结果：1通过、0拒绝

    /** 信审结果：1通过、0拒绝 */
    @Column(columnDefinition = DEF_NUM1)
    public Double getCreditResult() {
        return this.creditResult;
    }

    /** 信审结果：1通过、0拒绝 */
    public void setCreditResult(Double creditResult) {
        this.creditResult = creditResult;
    }

    private Long passedCustomerNo;// 信审通过后形成的客户编号

    /** 信审通过后形成的客户编号 */
    @Column(columnDefinition = DEF_NUM6)
    public Long getPassedCustomerNo() {
        return this.passedCustomerNo;
    }

    /** 信审通过后形成的客户编号 */
    public void setPassedCustomerNo(Long passedCustomerNo) {
        this.passedCustomerNo = passedCustomerNo;
    }
    
    
    /**
     * 申请实体
     */
    private XhCarLoanApply xhCarLoanApply;

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "CAR_APPLY_ID", unique = false, nullable = true, insertable = true, updatable = true)
    public XhCarLoanApply getXhCarLoanApply() {
        return xhCarLoanApply;
    }

    
    public void setXhCarLoanApply(XhCarLoanApply xhCarLoanApply) {
        this.xhCarLoanApply = xhCarLoanApply;
    }
}

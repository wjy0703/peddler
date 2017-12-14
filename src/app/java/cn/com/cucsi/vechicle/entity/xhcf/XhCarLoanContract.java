package cn.com.cucsi.vechicle.entity.xhcf;

import java.sql.Timestamp;

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

import cn.com.cucsi.app.entity.baseinfo.MiddleMan;
import cn.com.cucsi.core.orm.hibernate.AuditableEntity;

/**
 * XhCarLoanContract entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
// 表名与类名不相同时重新定义表名.
@Table(name = "XH_CAR_LOAN_CONTRACT")
// 默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XhCarLoanContract extends AuditableEntity {

    // Fields
    private static final long serialVersionUID = -2276242824197821671L;
    private String contractNum;// 合同编号

    /** 合同编号 */
    @Column(columnDefinition = DEF_STR50)
    public String getContractNum() {
        return this.contractNum;
    }

    /** 合同编号 */
    public void setContractNum(String contractNum) {
        this.contractNum = contractNum;
    }

    private Double contractMoney;// 合同金额

    /** 合同金额 */
    @Column(columnDefinition = DEF_NUM15_5)
    public Double getContractMoney() {
        return this.contractMoney;
    }

    /** 合同金额 */
    public void setContractMoney(Double contractMoney) {
        this.contractMoney = contractMoney;
    }

    private String qdrq;// 合同签订日期

    /** 合同签订日期 */
    @Column(columnDefinition = DEF_STR20)
    public String getQdrq() {
        return this.qdrq;
    }

    /** 合同签订日期 */
    public void setQdrq(String qdrq) {
        this.qdrq = qdrq;
    }

    private Timestamp contractDate;// 合同日期

    /** 合同日期 */
    @Column(insertable = false)
    public Timestamp getContractDate() {
        return this.contractDate;
    }

    /** 合同日期 */
    public void setContractDate(Timestamp contractDate) {
        this.contractDate = contractDate;
    }

    private Long state;// 0：待签约 1：已签约上传 -1：取消

    /** 0：待签约 1：已签约上传 -1：取消 */
    @Column(columnDefinition = DEF_NUM18)
    public Long getState() {
        return this.state;
    }

    /** 0：待签约 1：已签约上传 -1：取消 */
    public void setState(Long state) {
        this.state = state;
    }

    private MiddleMan middleMan;// 中间人ID

 // 多对一定义
 	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
 	@JoinColumn(name = "middle_man_id", unique= false, nullable=true, insertable=true, updatable=true)
 	public MiddleMan getMiddleMan() {
 		return middleMan;
 	}

 	public void setMiddleMan(MiddleMan middleMan) {
 		this.middleMan = middleMan;
 	}
    
    
    private Long xhJkhtId;// 借款合同ID

    /** 借款合同ID */
    @Column(columnDefinition = DEF_NUM18)
    public Long getXhJkhtId() {
        return this.xhJkhtId;
    }

    /** 借款合同ID */
    public void setXhJkhtId(Long xhJkhtId) {
        this.xhJkhtId = xhJkhtId;
    }

    private String hkr;// 还款日

    /** 还款日 */
    @Column(columnDefinition = DEF_STR2)
    public String getHkr() {
        return this.hkr;
    }

    /** 还款日 */
    public void setHkr(String hkr) {
        this.hkr = hkr;
    }

    private String noExtension;// 展期期数

    /** 展期期数 */
    @Column(columnDefinition = DEF_STR8)
    public String getNoExtension() {
        return this.noExtension;
    }

    /** 展期期数 */
    public void setNoExtension(String noExtension) {
        this.noExtension = noExtension;
    }

    private String isExtension;// 是否展期

    /** 是否展期 */
    @Column(columnDefinition = DEF_STR2)
    public String getIsExtension() {
        return this.isExtension;
    }

    /** 是否展期 */
    public void setIsExtension(String isExtension) {
        this.isExtension = isExtension;
    }

    private String remark;// 备注

    /** 备注 */
    @Column(columnDefinition = DEF_STR200)
    public String getRemark() {
        return this.remark;
    }

    /** 备注 */
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    private String overDate;// 还款截止日期

    /** 还款截止日期 */
    @Column(columnDefinition = DEF_STR20)
    public String getOverDate() {
        return this.overDate;
    }

    /** 还款截止日期 */
    public void setOverDate(String overDate) {
        this.overDate = overDate;
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
    
    private Double comlpexMoney;// 综合息费（IF(借款总额*总费率<1000,1000-利息,借款总额*总费率-利息)）

    /** 综合息费（IF(借款总额*总费率<1000,1000-利息,借款总额*总费率-利息)） */
    @Column(columnDefinition = DEF_NUM15_5)
    public Double getComlpexMoney() {
        return this.comlpexMoney;
    }

    /** 综合息费（IF(借款总额*总费率<1000,1000-利息,借款总额*总费率-利息)） */
    public void setComlpexMoney(Double comlpexMoney) {
        this.comlpexMoney = comlpexMoney;
    }

    private Double dkll;// 利息率（移交：0.5%；GPS：0.475%）

    /** 利息率（移交：0.5%；GPS：0.475%） */
    @Column(columnDefinition = DEF_NUM10_4)
    public Double getDkll() {
        return this.dkll;
    }

    /** 利息率（移交：0.5%；GPS：0.475%） */
    public void setDkll(Double dkll) {
        this.dkll = dkll;
    }

    private Double dkllComlpex;// 总费率（移交：3.5%；GPS：5% ，客户经理可录入）

    /** 总费率（移交：3.5%；GPS：5% ，客户经理可录入） */
    @Column(columnDefinition = DEF_NUM10_4)
    public Double getDkllComlpex() {
        return this.dkllComlpex;
    }

    /** 总费率（移交：3.5%；GPS：5% ，客户经理可录入） */
    public void setDkllComlpex(Double dkllComlpex) {
        this.dkllComlpex = dkllComlpex;
    }

    private Double interest;// 利息（借款总额*利息率）

    /** 利息（借款总额*利息率） */
    @Column(columnDefinition = DEF_NUM10_4)
    public Double getInterest() {
        return this.interest;
    }

    /** 利息（借款总额*利息率） */
    public void setInterest(Double interest) {
        this.interest = interest;
    }

    private Double serveComlpexMoney;// 总服务费率（IF(借款总额*总费率<1000,(1000-利息)/借款总额,总费率-利息率)）

    /** 总服务费率（IF(借款总额*总费率<1000,(1000-利息)/借款总额,总费率-利息率)） */
    @Column(columnDefinition = DEF_NUM10_4)
    public Double getServeComlpexMoney() {
        return this.serveComlpexMoney;
    }

    /** 总服务费率（IF(借款总额*总费率<1000,(1000-利息)/借款总额,总费率-利息率)） */
    public void setServeComlpexMoney(Double serveComlpexMoney) {
        this.serveComlpexMoney = serveComlpexMoney;
    }

    private Double adviceFee;// 咨询费（综合息费*59%）

    /** 咨询费（综合息费*59%） */
    @Column(columnDefinition = DEF_NUM10_4)
    public Double getAdviceFee() {
        return this.adviceFee;
    }

    /** 咨询费（综合息费*59%） */
    public void setAdviceFee(Double adviceFee) {
        this.adviceFee = adviceFee;
    }

    private Double auditFee;// 审核费（综合息费*5%）

    /** 审核费（综合息费*5%） */
    @Column(columnDefinition = DEF_NUM10_4)
    public Double getAuditFee() {
        return this.auditFee;
    }

    /** 审核费（综合息费*5%） */
    public void setAuditFee(Double auditFee) {
        this.auditFee = auditFee;
    }

    private Double serviceFee;// 服务费（综合息费-咨询费-审核费）

    /** 服务费（综合息费-咨询费-审核费） */
    @Column(columnDefinition = DEF_NUM10_4)
    public Double getServiceFee() {
        return this.serviceFee;
    }

    /** 服务费（综合息费-咨询费-审核费） */
    public void setServiceFee(Double serviceFee) {
        this.serviceFee = serviceFee;
    }
    
    private Double breakMoneyDkll;// 违约金利率借款总额的5%

    /** 违约金利率 借款总额的5%*/
    @Column(columnDefinition = DEF_NUM10_4)
    public Double getBreakMoneyDkll() {
        return this.breakMoneyDkll;
    }

    /** 违约金利率 借款总额的5%*/
    public void setBreakMoneyDkll(Double breakMoneyDkll) {
        this.breakMoneyDkll = breakMoneyDkll;
    }
    
    
    private Double breakMoney;// 违约金（借款金额*违约金利率）

    /**违约金（借款金额*违约金利率） */
    @Column(columnDefinition = DEF_NUM15_5)
    public Double getBreakMoney() {
        return this.breakMoney;
    }

    /** 违约金（借款金额*违约金利率） */
    public void setBreakMoney(Double breakMoney) {
        this.breakMoney = breakMoney;
    }

    private Double breakInterestDkll;// 违约罚息率(GPS:0.2%，移交：0.05%)

    /** 违约罚息率 (GPS:0.2%，移交：0.05%)*/
    @Column(columnDefinition = DEF_NUM10_4)
    public Double getBreakInterestDkll() {
        return this.breakInterestDkll;
    }

    /** 违约罚息率 (GPS:0.2%，移交：0.05%)*/
    public void setBreakInterestDkll(Double breakInterestDkll) {
        this.breakInterestDkll = breakInterestDkll;
    }
    

    private Double breakInterest;// 违约日利息（借款金额*违约罚息率）

    /** 违约日利息（借款金额*违约罚息率） */
    @Column(columnDefinition = DEF_NUM15_5)
    public Double getBreakInterest() {
        return this.breakInterest;
    }

    /** 违约日利息（借款金额*违约罚息率） */
    public void setBreakInterest(Double breakInterest) {
        this.breakInterest = breakInterest;
    }
    
}

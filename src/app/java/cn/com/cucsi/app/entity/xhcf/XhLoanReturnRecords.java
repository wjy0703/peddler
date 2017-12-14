package cn.com.cucsi.app.entity.xhcf;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.format.annotation.DateTimeFormat;

import cn.com.cucsi.core.orm.hibernate.AuditableEntity;

/**
 * XhLoanReturnRecords entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
// 表名与类名不相同时重新定义表名.
@Table(name = "XH_LOAN_RETURN_RECORDS")
// 默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XhLoanReturnRecords extends AuditableEntity {

    // Fields
    private static final long serialVersionUID = -2276242824197821671L;
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

    private Long dealingType;// 交易类型

    /** 交易类型 */
    @Column(columnDefinition = DEF_NUM18)
    public Long getDealingType() {
        return this.dealingType;
    }

    /** 交易类型 */
    public void setDealingType(Long dealingType) {
        this.dealingType = dealingType;
    }

    private Long loanerMainAccountId;// 主账户ID

    /** 主账户ID */
    @Column(columnDefinition = DEF_NUM18 ,nullable=true)
    public Long getLoanerMainAccountId() {
        return this.loanerMainAccountId;
    }

    /** 主账户ID */
    public void setLoanerMainAccountId(Long loanerMainAccountId) {
        this.loanerMainAccountId = loanerMainAccountId;
    }

    private String htbm;// 合同编码

    /** 合同编码 */
    @Column(columnDefinition = DEF_STR50)
    public String getHtbm() {
        return this.htbm;
    }

    /** 合同编码 */
    public void setHtbm(String htbm) {
        this.htbm = htbm;
    }

    
    
    
    private String errorState;// 错误原因

    /** 错误原因 */
    @Column(columnDefinition = DEF_STR100)
    public String getErrorState() {
        return this.errorState;
    }

    /** 错误原因 */
    public void setErrorState(String errorState) {
        this.errorState = errorState;
    }
    
    private String jkrxm;// 合同编码

    /** 合同编码 */
    @Column(columnDefinition = DEF_STR50)
    public String getJkrxm() {
        return this.jkrxm;
    }

    /** 合同编码 */
    public void setJkrxm(String jkrxm) {
        this.jkrxm = jkrxm;
    }
}

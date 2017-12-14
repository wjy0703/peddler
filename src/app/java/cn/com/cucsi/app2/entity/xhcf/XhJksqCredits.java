package cn.com.cucsi.app2.entity.xhcf;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import cn.com.cucsi.app.entity.xhcf.XhJksq;
import cn.com.cucsi.core.orm.hibernate.AuditableEntity;

/**
 * XhJksqCredits entity. 
 * @author MyEclipse Persistence Tools
 */
@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "XH_JKSQ_CREDITS")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XhJksqCredits extends AuditableEntity{

	// Fields
	private static final long serialVersionUID = -2276242824197821671L;
	private Long typeh;//信用资料类别 0:无抵押 1：有抵押
	/**信用资料类别 0:无抵押 1：有抵押*/
	@Column(columnDefinition=DEF_NUM1)
	public Long getTypeh() {
		return this.typeh;
	}
	/**信用资料类别 0:无抵押 1：有抵押*/
	public void setTypeh(Long typeh) {
		this.typeh = typeh;
	}
	private String mortage;//抵押物品(自有汽车等)
	/**抵押物品(自有汽车等)*/
	@Column(columnDefinition=DEF_STR20)
	public String getMortage() {
		return this.mortage;
	}
	/**抵押物品(自有汽车等)*/
	public void setMortage(String mortage) {
		this.mortage = mortage;
	}
	private String compBankName;//机构名称
	/**机构名称*/
	@Column(columnDefinition=DEF_STR50)
	public String getCompBankName() {
		return this.compBankName;
	}
	/**机构名称*/
	public void setCompBankName(String compBankName) {
		this.compBankName = compBankName;
	}
	private Double loanAmount;//贷款额度
	/**贷款额度*/
	@Column(columnDefinition=DEF_NUM18_3)
	public Double getLoanAmount() {
		return this.loanAmount;
	}
	/**贷款额度*/
	public void setLoanAmount(Double loanAmount) {
		this.loanAmount = loanAmount;
	}
	private Double monthReturn;//月还款额
	/**月还款额*/
	@Column(columnDefinition=DEF_NUM18_3)
	public Double getMonthReturn() {
		return this.monthReturn;
	}
	/**月还款额*/
	public void setMonthReturn(Double monthReturn) {
		this.monthReturn = monthReturn;
	}
	private Double remain;//借款余额
	/**借款余额*/
	@Column(columnDefinition=DEF_NUM18_3)
	public Double getRemain() {
		return this.remain;
	}
	/**借款余额*/
	public void setRemain(Double remain) {
		this.remain = remain;
	}
	private Long cloanCount;//借款期限
	/**借款期限*/
	@Column(columnDefinition=DEF_NUM4)
	public Long getCloanCount() {
		return this.cloanCount;
	}
	/**借款期限*/
	public void setCloanCount(Long cloanCount) {
		this.cloanCount = cloanCount;
	}
	private Long cloanReturnFile;//有无还款证明
	/**有无还款证明*/
	@Column(columnDefinition=DEF_NUM1)
	public Long getCloanReturnFile() {
		return this.cloanReturnFile;
	}
	/**有无还款证明*/
	public void setCloanReturnFile(Long cloanReturnFile) {
		this.cloanReturnFile = cloanReturnFile;
	}
	private String cloanComment;//还款情况说明
	/**还款情况说明*/
	@Column(columnDefinition=DEF_STR100)
	public String getCloanComment() {
		return this.cloanComment;
	}
	/**还款情况说明*/
	public void setCloanComment(String cloanComment) {
		this.cloanComment = cloanComment;
	}
	
	private XhJksq xhJksq;             //借款申请信息
    
    /**借款申请信息*/
	@ManyToOne(cascade=CascadeType.REFRESH,fetch = FetchType.LAZY)
    @JoinColumn(name="JKSQ_ID", unique= false, nullable=true, insertable=true, updatable=true)
    public XhJksq getXhJksq() {
        return xhJksq;
    }
    
    public void setXhJksq(XhJksq xhJksq) {
        this.xhJksq = xhJksq;
    }
}

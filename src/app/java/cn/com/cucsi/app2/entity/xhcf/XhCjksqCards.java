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
 * XhCjksqCards entity. 
 * @author MyEclipse Persistence Tools
 */
@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "XH_CJKSQ_CARDS")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XhCjksqCards extends AuditableEntity{

	// Fields
	private static final long serialVersionUID = -2276242824197821671L;
	private Long activeCount;//激活账户总数
	/**激活账户总数*/
	@Column(columnDefinition=DEF_NUM4)
	public Long getActiveCount() {
		return this.activeCount;
	}
	/**激活账户总数*/
	public void setActiveCount(Long activeCount) {
		this.activeCount = activeCount;
	}
	private Double moneySum;//授信总额
	/**授信总额*/
	@Column(columnDefinition=DEF_NUM18_2)
	public Double getMoneySum() {
		return this.moneySum;
	}
	/**授信总额*/
	public void setMoneySum(Double moneySum) {
		this.moneySum = moneySum;
	}
	private Long cardInuse;//在用账户数
	/**在用账户数*/
	@Column(columnDefinition=DEF_NUM4)
	public Long getCardInuse() {
		return this.cardInuse;
	}
	/**在用账户数*/
	public void setCardInuse(Long cardInuse) {
		this.cardInuse = cardInuse;
	}
	private Double singleCardUpper;//单张最高授信
	/**单张最高授信*/
	@Column(columnDefinition=DEF_NUM18_2)
	public Double getSingleCardUpper() {
		return this.singleCardUpper;
	}
	/**单张最高授信*/
	public void setSingleCardUpper(Double singleCardUpper) {
		this.singleCardUpper = singleCardUpper;
	}
	private Double singleCardLower;//单张最低授信
	/**单张最低授信*/
	@Column(columnDefinition=DEF_NUM18_2)
	public Double getSingleCardLower() {
		return this.singleCardLower;
	}
	/**单张最低授信*/
	public void setSingleCardLower(Double singleCardLower) {
		this.singleCardLower = singleCardLower;
	}
	private Double amountUsed;//已使用额度
	/**已使用额度*/
	@Column(columnDefinition=DEF_NUM18_2)
	public Double getAmountUsed() {
		return this.amountUsed;
	}
	/**已使用额度*/
	public void setAmountUsed(Double amountUsed) {
		this.amountUsed = amountUsed;
	}
	private Double estimateValue;//估值
	/**估值*/
	@Column(columnDefinition=DEF_NUM18_2)
	public Double getEstimateValue() {
		return this.estimateValue;
	}
	/**估值*/
	public void setEstimateValue(Double estimateValue) {
		this.estimateValue = estimateValue;
	}
	private Double useFrequency;//信用卡使用率
	/**信用卡使用率*/
	@Column(columnDefinition=DEF_NUM4_2)
	public Double getUseFrequency() {
		return this.useFrequency;
	}
	/**信用卡使用率*/
	public void setUseFrequency(Double useFrequency) {
		this.useFrequency = useFrequency;
	}
	private String exceedComment;//逾期情况说明
	/**逾期情况说明*/
	@Column(columnDefinition=DEF_STR200)
	public String getExceedComment() {
		return this.exceedComment;
	}
	/**逾期情况说明*/
	public void setExceedComment(String exceedComment) {
		this.exceedComment = exceedComment;
	}
	private String recentRecord;//最近6个月查询记录
	/**最近6个月查询记录*/
	@Column(columnDefinition=DEF_STR200)
	public String getRecentRecord() {
		return this.recentRecord;
	}
	/**最近6个月查询记录*/
	public void setRecentRecord(String recentRecord) {
		this.recentRecord = recentRecord;
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

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
 * XhCjksqVechicle entity. 
 * @author MyEclipse Persistence Tools
 */
@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "XH_CJKSQ_VECHICLE")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XhCjksqVechicle extends AuditableEntity{

	// Fields
	private static final long serialVersionUID = -2276242824197821671L;
	private String registerDate;//注册日期
	/**注册日期*/
	@Column(columnDefinition=DEF_STR20)
	public String getRegisterDate() {
		return this.registerDate;
	}
	/**注册日期*/
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	private String dengjiDate;//登记日期
	/**登记日期*/
	@Column(columnDefinition=DEF_STR20)
	public String getDengjiDate() {
		return this.dengjiDate;
	}
	/**登记日期*/
	public void setDengjiDate(String dengjiDate) {
		this.dengjiDate = dengjiDate;
	}
	private String owener;//产权归属
	/**产权归属*/
	@Column(columnDefinition=DEF_STR20)
	public String getOwener() {
		return this.owener;
	}
	/**产权归属*/
	public void setOwener(String owener) {
		this.owener = owener;
	}
	private Long endorse;//有无抵押 0:无 1:有
	/**有无抵押 0:无 1:有*/
	@Column(columnDefinition=DEF_NUM2)
	public Long getEndorse() {
		return this.endorse;
	}
	/**有无抵押 0:无 1:有*/
	public void setEndorse(Long endorse) {
		this.endorse = endorse;
	}
	private Double borrowmoney;//借款余额
	/**借款余额*/
	@Column(columnDefinition=DEF_NUM18_2)
	public Double getBorrowmoney() {
		return this.borrowmoney;
	}
	/**借款余额*/
	public void setBorrowmoney(Double borrowmoney) {
		this.borrowmoney = borrowmoney;
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
	private String valueWay;//估值/确认途径
	/**估值/确认途径*/
	@Column(columnDefinition=DEF_STR20)
	public String getValueWay() {
		return this.valueWay;
	}
	/**估值/确认途径*/
	public void setValueWay(String valueWay) {
		this.valueWay = valueWay;
	}
	private String marchetValueComment;//市场报价(类似备注)
	/**市场报价(类似备注)*/
	@Column(columnDefinition=DEF_STR50)
	public String getMarchetValueComment() {
		return this.marchetValueComment;
	}
	/**市场报价(类似备注)*/
	public void setMarchetValueComment(String marchetValueComment) {
		this.marchetValueComment = marchetValueComment;
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

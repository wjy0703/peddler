package cn.com.cucsi.app2.entity.xhcf;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import cn.com.cucsi.app.entity.xhcf.XhJksq;
import cn.com.cucsi.core.orm.hibernate.AuditableEntity;

/**
 * XhJksqHouse entity. 
 * @author MyEclipse Persistence Tools
 */
@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "XH_JKSQ_HOUSE")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XhJksqHouse extends AuditableEntity{

	// Fields
	private static final long serialVersionUID = -2276242824197821671L;
	private String address;//住宅地址
	/**住宅地址*/
	@Column(columnDefinition=DEF_STR200)
	public String getAddress() {
		return this.address;
	}
	/**住宅地址*/
	public void setAddress(String address) {
		this.address = address;
	}
	private String typeh;//住宅类型( 全款，按揭 -- 下拉或单选)
	/**住宅类型( 全款，按揭 -- 下拉或单选)*/
	@Column(columnDefinition=DEF_STR10)
	public String getTypeh() {
		return this.typeh;
	}
	/**住宅类型( 全款，按揭 -- 下拉或单选)*/
	public void setTypeh(String typeh) {
		this.typeh = typeh;
	}
	private String buildYear;//建筑年份
	/**建筑年份*/
	@Column(columnDefinition=DEF_STR20)
	public String getBuildYear() {
		return this.buildYear;
	}
	/**建筑年份*/
	public void setBuildYear(String buildYear) {
		this.buildYear = buildYear;
	}
	
	private String buyDate;//购买日期
	/**建筑年份*/
	@Column(columnDefinition=DEF_STR20)
	public String getBuyDate() {
		return this.buyDate;
	}
	/**建筑年份*/
	public void setBuyDate(String buyDate) {
		this.buyDate = buyDate;
	}
	
	private Double area;//销售面积
	/**销售面积*/
	@Column(columnDefinition=DEF_NUM10_2)
	public Double getArea() {
		return this.area;
	}
	/**销售面积*/
	public void setArea(Double area) {
		this.area = area;
	}
	private String bank;//按揭银行
	/**按揭银行*/
	@Column(columnDefinition=DEF_STR50)
	public String getBank() {
		return this.bank;
	}
	/**按揭银行*/
	public void setBank(String bank) {
		this.bank = bank;
	}
	private Double loanMoney;//借款总额
	/**借款总额*/
	@Column(columnDefinition=DEF_NUM18_2)
	public Double getLoanMoney() {
		return this.loanMoney;
	}
	/**借款总额*/
	public void setLoanMoney(Double loanMoney) {
		this.loanMoney = loanMoney;
	}
	private Double loanMonth;//贷款年限
	/**贷款年限*/
	@Column(columnDefinition=DEF_NUM4_1)
	public Double getLoanMonth() {
		return this.loanMonth;
	}
	/**贷款年限*/
	public void setLoanMonth(Double loanMonth) {
		this.loanMonth = loanMonth;
	}
	private Double buyMoney;//够买价格
	/**够买价格*/
	@Column(columnDefinition=DEF_NUM18_2)
	public Double getBuyMoney() {
		return this.buyMoney;
	}
	/**够买价格*/
	public void setBuyMoney(Double buyMoney) {
		this.buyMoney = buyMoney;
	}
	private Double remainmoney;//借款余额
	/**借款余额*/
	@Column(columnDefinition=DEF_NUM18_2)
	public Double getRemainmoney() {
		return this.remainmoney;
	}
	/**借款余额*/
	public void setRemainmoney(Double remainmoney) {
		this.remainmoney = remainmoney;
	}
	private Double monthReturn;//月还款
	/**月还款*/
	@Column(columnDefinition=DEF_NUM18_2)
	public Double getMonthReturn() {
		return this.monthReturn;
	}
	/**月还款*/
	public void setMonthReturn(Double monthReturn) {
		this.monthReturn = monthReturn;
	}
	private String chouseOwner;//产权归属
	/**产权归属*/
	@Column(columnDefinition=DEF_STR20)
	public String getChouseOwner() {
		return this.chouseOwner;
	}
	/**产权归属*/
	public void setChouseOwner(String chouseOwner) {
		this.chouseOwner = chouseOwner;
	}
	private Long chouseEndorse;//有无抵押 0:无 1:有
	/**有无抵押 0:无 1:有*/
	@Column(columnDefinition=DEF_NUM1)
	public Long getChouseEndorse() {
		return this.chouseEndorse;
	}
	/**有无抵押 0:无 1:有*/
	public void setChouseEndorse(Long chouseEndorse) {
		this.chouseEndorse = chouseEndorse;
	}
	private Double chouseValue;//估值
	/**估值*/
	@Column(columnDefinition=DEF_NUM18_2)
	public Double getChouseValue() {
		return this.chouseValue;
	}
	/**估值*/
	public void setChouseValue(Double chouseValue) {
		this.chouseValue = chouseValue;
	}
	private String chouseMarchetValue;//市场报价(一般是写信息例如 100米 8000元一米)
	/**市场报价(一般是写信息例如 100米 8000元一米)*/
	@Column(columnDefinition=DEF_STR50)
	public String getChouseMarchetValue() {
		return this.chouseMarchetValue;
	}
	/**市场报价(一般是写信息例如 100米 8000元一米)*/
	public void setChouseMarchetValue(String chouseMarchetValue) {
		this.chouseMarchetValue = chouseMarchetValue;
	}
	private String chouseValueWay;//估值/确认途径
	/**估值/确认途径*/
	@Column(columnDefinition=DEF_STR20)
	public String getChouseValueWay() {
		return this.chouseValueWay;
	}
	/**估值/确认途径*/
	public void setChouseValueWay(String chouseValueWay) {
		this.chouseValueWay = chouseValueWay;
	}
	
	
	private XhJksq xhJksq;             //借款申请信息
    
    /**借款申请信息*/
	@OneToOne(cascade=CascadeType.REFRESH,fetch = FetchType.LAZY)
    @JoinColumn(name="JKSQ_ID", unique= false, nullable=true, insertable=true, updatable=true)
    public XhJksq getXhJksq() {
        return xhJksq;
    }
    
    public void setXhJksq(XhJksq xhJksq) {
        this.xhJksq = xhJksq;
    }
}

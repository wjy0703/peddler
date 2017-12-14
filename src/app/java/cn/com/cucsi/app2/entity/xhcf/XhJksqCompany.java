package cn.com.cucsi.app2.entity.xhcf;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import cn.com.cucsi.app.entity.xhcf.XhJksq;
import cn.com.cucsi.core.orm.hibernate.AuditableEntity;

/**
 * XhJksqCompany entity. 
 * @author MyEclipse Persistence Tools
 */
@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "XH_JKSQ_COMPANY")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XhJksqCompany extends AuditableEntity{

	// Fields
	private static final long serialVersionUID = -2276242824197821671L;
	private String busiLicences;//营业执照
	/**营业执照*/
	@Column(columnDefinition=DEF_STR32)
	public String getBusiLicences() {
		return this.busiLicences;
	}
	/**营业执照*/
	public void setBusiLicences(String busiLicences) {
		this.busiLicences = busiLicences;
	}
	private String startDate;//成立日期
	/**成立日期*/
	@Column(columnDefinition=DEF_STR20)
	public String getStartDate() {
		return this.startDate;
	}
	/**成立日期*/
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	private Double registerMoney;//注册资金(注册/实收资本)
	/**注册资金(注册/实收资本)*/
	@Column(columnDefinition=DEF_NUM18_2)
	public Double getRegisterMoney() {
		return this.registerMoney;
	}
	/**注册资金(注册/实收资本)*/
	public void setRegisterMoney(Double registerMoney) {
		this.registerMoney = registerMoney;
	}
	private String areaType;//经营场所(采用枚举类方式，自由，租用，按揭)
	/**经营场所(采用枚举类方式，自由，租用，按揭)*/
	@Column(columnDefinition=DEF_STR10)
	public String getAreaType() {
		return this.areaType;
	}
	/**经营场所(采用枚举类方式，自由，租用，按揭)*/
	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}
	private Double moneyUsed;//租金或月还款(和上面类型有关)
	/**租金或月还款(和上面类型有关)*/
	@Column(columnDefinition=DEF_NUM18_2)
	public Double getMoneyUsed() {
		return this.moneyUsed;
	}
	/**租金或月还款(和上面类型有关)*/
	public void setMoneyUsed(Double moneyUsed) {
		this.moneyUsed = moneyUsed;
	}
	private Double areaSquare;//营业面积
	/**营业面积*/
	@Column(columnDefinition=DEF_NUM18_2)
	public Double getAreaSquare() {
		return this.areaSquare;
	}
	/**营业面积*/
	public void setAreaSquare(Double areaSquare) {
		this.areaSquare = areaSquare;
	}
	private String weakMonth;//淡季月份
	/**淡季月份*/
	@Column(columnDefinition=DEF_STR32)
	public String getWeakMonth() {
		return this.weakMonth;
	}
	/**淡季月份*/
	public void setWeakMonth(String weakMonth) {
		this.weakMonth = weakMonth;
	}
	private Double weakMonthEarn;//淡季月份收入
	/**淡季月份收入*/
	@Column(columnDefinition=DEF_NUM18_2)
	public Double getWeakMonthEarn() {
		return this.weakMonthEarn;
	}
	/**淡季月份收入*/
	public void setWeakMonthEarn(Double weakMonthEarn) {
		this.weakMonthEarn = weakMonthEarn;
	}
	private String strongMonth;//旺季月份
	/**旺季月份*/
	@Column(columnDefinition=DEF_STR32)
	public String getStrongMonth() {
		return this.strongMonth;
	}
	/**旺季月份*/
	public void setStrongMonth(String strongMonth) {
		this.strongMonth = strongMonth;
	}
	private Double strongMonthEarn;//旺季月份收入
	/**旺季月份收入*/
	@Column(columnDefinition=DEF_NUM18_2)
	public Double getStrongMonthEarn() {
		return this.strongMonthEarn;
	}
	/**旺季月份收入*/
	public void setStrongMonthEarn(Double strongMonthEarn) {
		this.strongMonthEarn = strongMonthEarn;
	}
	private String middleMonth;//平季月份
	/**平季月份*/
	@Column(columnDefinition=DEF_STR32)
	public String getMiddleMonth() {
		return this.middleMonth;
	}
	/**平季月份*/
	public void setMiddleMonth(String middleMonth) {
		this.middleMonth = middleMonth;
	}
	private Double middleMonthEarn;//平季月份收入
	/**平季月份收入*/
	@Column(columnDefinition=DEF_NUM18_2)
	public Double getMiddleMonthEarn() {
		return this.middleMonthEarn;
	}
	/**平季月份收入*/
	public void setMiddleMonthEarn(Double middleMonthEarn) {
		this.middleMonthEarn = middleMonthEarn;
	}
	private String supplierOne;//主要供应商1
	/**主要供应商1*/
	@Column(columnDefinition=DEF_STR50)
	public String getSupplierOne() {
		return this.supplierOne;
	}
	/**主要供应商1*/
	public void setSupplierOne(String supplierOne) {
		this.supplierOne = supplierOne;
	}
	private String supplierTwo;//主要供应商2
	/**主要供应商2*/
	@Column(columnDefinition=DEF_STR50)
	public String getSupplierTwo() {
		return this.supplierTwo;
	}
	/**主要供应商2*/
	public void setSupplierTwo(String supplierTwo) {
		this.supplierTwo = supplierTwo;
	}
	private String supplierThree;//主要供应商3
	/**主要供应商3*/
	@Column(columnDefinition=DEF_STR50)
	public String getSupplierThree() {
		return this.supplierThree;
	}
	/**主要供应商3*/
	public void setSupplierThree(String supplierThree) {
		this.supplierThree = supplierThree;
	}
	private String cname;//公司名称
	/**公司名称*/
	@Column(columnDefinition=DEF_STR50)
	public String getCname() {
		return this.cname;
	}
	/**公司名称*/
	public void setCname(String cname) {
		this.cname = cname;
	}
	private String cbusniessType;//公司型式
	/**公司型式*/
	@Column(columnDefinition=DEF_STR20)
	public String getCbusniessType() {
		return this.cbusniessType;
	}
	/**公司型式*/
	public void setCbusniessType(String cbusniessType) {
		this.cbusniessType = cbusniessType;
	}
	private String cbusinessAddr;//公司经营地点
	/**公司经营地点*/
	@Column(columnDefinition=DEF_STR50)
	public String getCbusinessAddr() {
		return this.cbusinessAddr;
	}
	/**公司经营地点*/
	public void setCbusinessAddr(String cbusinessAddr) {
		this.cbusinessAddr = cbusinessAddr;
	}
	private Long cbusinessPeriod;//场地合同有效期
	/**场地合同有效期*/
	@Column(columnDefinition=DEF_NUM4)
	public Long getCbusinessPeriod() {
		return this.cbusinessPeriod;
	}
	/**场地合同有效期*/
	public void setCbusinessPeriod(Long cbusinessPeriod) {
		this.cbusinessPeriod = cbusinessPeriod;
	}
	private Double cstockholderRatio;//股东/股权比例
	/**股东/股权比例*/
	@Column(columnDefinition=DEF_NUM4_2)
	public Double getCstockholderRatio() {
		return this.cstockholderRatio;
	}
	/**股东/股权比例*/
	public void setCstockholderRatio(Double cstockholderRatio) {
		this.cstockholderRatio = cstockholderRatio;
	}
	private String cchangeInfo;//变更情况
	/**变更情况*/
	@Column(columnDefinition=DEF_STR100)
	public String getCchangeInfo() {
		return this.cchangeInfo;
	}
	/**变更情况*/
	public void setCchangeInfo(String cchangeInfo) {
		this.cchangeInfo = cchangeInfo;
	}
	private String crunningStatus;//业务经营情况
	/**业务经营情况*/
	@Column(columnDefinition=DEF_STR512)
	public String getCrunningStatus() {
		return this.crunningStatus;
	}
	/**业务经营情况*/
	public void setCrunningStatus(String crunningStatus) {
		this.crunningStatus = crunningStatus;
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
    
    List<XhJksqcompanyUprelated> xhJksqcompanyUprelateds;
    
    @OneToMany(targetEntity = XhJksqcompanyUprelated.class, cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "XHJKSQCOMPANY_ID", updatable = false)
    public List<XhJksqcompanyUprelated> getXhJksqcompanyUprelateds() {
        return xhJksqcompanyUprelateds;
    }

    
    public void setXhJksqcompanyUprelateds(List<XhJksqcompanyUprelated> xhJksqcompanyUprelateds) {
        this.xhJksqcompanyUprelateds = xhJksqcompanyUprelateds;
    }
    
    List<XhJksqcompanyDownrelated> xhJksqcompanyDownrelateds;
    
    @OneToMany(targetEntity = XhJksqcompanyDownrelated.class, cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "XHJKSQCOMPANY_ID", updatable = false)
    public List<XhJksqcompanyDownrelated> getXhJksqcompanyDownrelateds() {
        return xhJksqcompanyDownrelateds;
    }

    
    public void setXhJksqcompanyDownrelateds(List<XhJksqcompanyDownrelated> xhJksqcompanyDownrelateds) {
        this.xhJksqcompanyDownrelateds = xhJksqcompanyDownrelateds;
    }
    
    List<XhCjksqBankRecords> xhCjksqBankRecords;
    
    @OneToMany(targetEntity = XhCjksqBankRecords.class, cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "JKSQCOMPANY_ID", updatable = false)
    public List<XhCjksqBankRecords> getXhCjksqBankRecords() {
        return xhCjksqBankRecords;
    }

    
    public void setXhCjksqBankRecords(List<XhCjksqBankRecords> xhCjksqBankRecords) {
        this.xhCjksqBankRecords = xhCjksqBankRecords;
    }
}

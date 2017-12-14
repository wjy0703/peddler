package cn.com.cucsi.app.entity.xhcf;

import java.sql.Timestamp;
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

import cn.com.cucsi.app.entity.baseinfo.Employee;
import cn.com.cucsi.app.entity.baseinfo.Organi;
import cn.com.cucsi.core.orm.hibernate.AuditableEntity;

/**
 * XhHouseLoanConsult entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
// 表名与类名不相同时重新定义表名.
@Table(name = "XH_HOUSE_LOAN_CONSULT")
// 默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XhHouseLoanConsult extends AuditableEntity {

	// Fields
	private static final long serialVersionUID = -2276242824197821671L;
	private String customerName;// 客户姓名

	/** 客户姓名 */
	@Column(columnDefinition = DEF_STR20)
	public String getCustomerName() {
		return this.customerName;
	}

	/** 客户姓名 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	private String customerSex;// 性别

	/** 性别 */
	@Column(columnDefinition = DEF_STR20)
	public String getCustomerSex() {
		return this.customerSex;
	}

	/** 性别 */
	public void setCustomerSex(String customerSex) {
		this.customerSex = customerSex;
	}

	private String identificationCard;// 身份证号码

	/** 身份证号码 */
	@Column(columnDefinition = DEF_STR20)
	public String getIdentificationCard() {
		return this.identificationCard;
	}

	/** 身份证号码 */
	public void setIdentificationCard(String identificationCard) {
		this.identificationCard = identificationCard;
	}

	private String customerSource;// 客户来源

	/** 客户来源 */
	@Column(columnDefinition = DEF_STR20)
	public String getCustomerSource() {
		return this.customerSource;
	}

	/** 客户来源 */
	public void setCustomerSource(String customerSource) {
		this.customerSource = customerSource;
	}

	private String marrital;// 婚否

	/** 婚否 */
	@Column(columnDefinition = DEF_STR20)
	public String getMarrital() {
		return this.marrital;
	}

	/** 婚否 */
	public void setMarrital(String marrital) {
		this.marrital = marrital;
	}

	private String customerTel;// 电话

	/** 电话 */
	@Column(columnDefinition = DEF_STR20)
	public String getCustomerTel() {
		return this.customerTel;
	}

	/** 电话 */
	public void setCustomerTel(String customerTel) {
		this.customerTel = customerTel;
	}

	private String nowAddress;// 现住址

	/** 现住址 */
	@Column(columnDefinition = DEF_STR50)
	public String getNowAddress() {
		return this.nowAddress;
	}

	/** 现住址 */
	public void setNowAddress(String nowAddress) {
		this.nowAddress = nowAddress;
	}

	private String houseType;// 房产性质

	/** 房产性质 */
	@Column(columnDefinition = DEF_STR50)
	public String getHouseType() {
		return this.houseType;
	}

	/** 房产性质 */
	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}

	private String houseLimit;// 使用年限

	/** 使用年限 */
	@Column(columnDefinition = DEF_STR50)
	public String getHouseLimit() {
		return this.houseLimit;
	}

	/** 使用年限 */
	public void setHouseLimit(String houseLimit) {
		this.houseLimit = houseLimit;
	}

	private String houseRegion;// 所属区县

	/** 所属区县 */
	@Column(columnDefinition = DEF_STR50)
	public String getHouseRegion() {
		return this.houseRegion;
	}

	/** 所属区县 */
	public void setHouseRegion(String houseRegion) {
		this.houseRegion = houseRegion;
	}

	private String houseArea;// 房产面积

	/** 房产面积 */
	@Column(columnDefinition = DEF_STR50)
	public String getHouseArea() {
		return this.houseArea;
	}

	/** 房产面积 */
	public void setHouseArea(String houseArea) {
		this.houseArea = houseArea;
	}

	private String houseYears;// 建成年代

	/** 建成年代 */
	@Column(columnDefinition = DEF_STR50)
	public String getHouseYears() {
		return this.houseYears;
	}

	/** 建成年代 */
	public void setHouseYears(String houseYears) {
		this.houseYears = houseYears;
	}

	private String houseFloor;// 房屋楼层

	/** 房屋楼层 */
	@Column(columnDefinition = DEF_STR50)
	public String getHouseFloor() {
		return this.houseFloor;
	}

	/** 房屋楼层 */
	public void setHouseFloor(String houseFloor) {
		this.houseFloor = houseFloor;
	}

	private String houseAddress;// 房屋地址

	/** 房屋地址 */
	@Column(columnDefinition = DEF_STR50)
	public String getHouseAddress() {
		return this.houseAddress;
	}

	/** 房屋地址 */
	public void setHouseAddress(String houseAddress) {
		this.houseAddress = houseAddress;
	}

	private String houseInfo;// 房产详细情况

	/**
	 * 房产详 细情况
	 */
	@Column(columnDefinition = DEF_STR200)
	public String getHouseInfo() {
		return this.houseInfo;
	}

	/**
	 * 房产详 细情况
	 */
	public void setHouseInfo(String houseInfo) {
		this.houseInfo = houseInfo;
	}

	private Double allLoanRate;// 综合利率

	/** 综合利率 */
	@Column(columnDefinition = DEF_NUM15_5)
	public Double getAllLoanRate() {
		return this.allLoanRate;
	}

	/** 综合利率 */
	public void setAllLoanRate(Double allLoanRate) {
		this.allLoanRate = allLoanRate;
	}

	private Double loanAmount;// 贷款额度

	/** 贷款额度 */
	@Column(columnDefinition = DEF_NUM15_5)
	public Double getLoanAmount() {
		return this.loanAmount;
	}

	/** 贷款额度 */
	public void setLoanAmount(Double loanAmount) {
		this.loanAmount = loanAmount;
	}

	private Long loanMonth;// 贷款周期

	/** 贷款周期 */
	@Column(columnDefinition = DEF_NUM18)
	public Long getLoanMonth() {
		return this.loanMonth;
	}

	/** 贷款周期 */
	public void setLoanMonth(Long loanMonth) {
		this.loanMonth = loanMonth;
	}

	private Double assessPrice;// 评估价格

	/** 评估价格 */
	@Column(columnDefinition = DEF_NUM15_5)
	public Double getAssessPrice() {
		return this.assessPrice;
	}

	/** 评估价格 */
	public void setAssessPrice(Double assessPrice) {
		this.assessPrice = assessPrice;
	}

	private String loanUse;// 贷款用途

	/** 贷款用途 */
	@Column(columnDefinition = DEF_STR50)
	public String getLoanUse() {
		return this.loanUse;
	}

	/** 贷款用途 */
	public void setLoanUse(String loanUse) {
		this.loanUse = loanUse;
	}

	private String backSource;// 还款来源

	/** 还款来源 */
	@Column(columnDefinition = DEF_STR50)
	public String getBackSource() {
		return this.backSource;
	}

	/** 还款来源 */
	public void setBackSource(String backSource) {
		this.backSource = backSource;
	}

	private String customerRequired;// 客户要求

	/** 客户要求 */
	@Column(columnDefinition = DEF_STR50)
	public String getCustomerRequired() {
		return this.customerRequired;
	}

	/** 客户要求 */
	public void setCustomerRequired(String customerRequired) {
		this.customerRequired = customerRequired;
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

	private Employee customrerManager;// 客户经理

	/** 客户经理 */
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false,fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMRER_MANAGER_ID")
	public Employee getCustomrerManager() {
		return this.customrerManager;
	}

	/** 客户经理 */
	public void setCustomrerManager(Employee customrerManager) {
		this.customrerManager = customrerManager;
	}

	private Employee teamManager;// 团队经理

	/** 团队经理 */
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false,fetch = FetchType.LAZY)
	@JoinColumn(name = "TEAM_MANAGER_ID")
	public Employee getTeamManager() {
		return this.teamManager;
	}

	/** 团队经理 */
	public void setTeamManager(Employee teamManager) {
		this.teamManager = teamManager;
	}

	private Employee lodgeQuery;// 涉诉查询

	/** 涉诉查询 */

	@ManyToOne(cascade = CascadeType.REFRESH, optional = false,fetch = FetchType.LAZY)
	@JoinColumn(name = "LODGE_QUERY_ID")
	public Employee getLodgeQuery() {
		return this.lodgeQuery;
	}

	/** 涉诉查询 */
	public void setLodgeQuery(Employee lodgeQuery) {
		this.lodgeQuery = lodgeQuery;
	}

	private Organi organ;// 组织机构

	/** 组织机构 */
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false,fetch = FetchType.LAZY)
	@JoinColumn(name = "ORGAN_ID")
	public Organi getOrgan() {
		return this.organ;
	}

	/** 组织机构 */
	public void setOrgan(Organi organ) {
		this.organ = organ;
	}
}

package cn.com.cucsi.app.entity.xhcf;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.format.annotation.DateTimeFormat;

import cn.com.cucsi.core.orm.hibernate.AuditableEntity;

/**
 * XhHouseLoanApply entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
// 表名与类名不相同时重新定义表名.
@Table(name = "XH_HOUSE_LOAN_APPLY")
// 默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XhHouseLoanApply extends AuditableEntity {

	// Fields
	private static final long serialVersionUID = -2276242824197821671L;
	private String applyType;// 申请类型

	/** 申请类型 */
	@Column(columnDefinition = DEF_STR8)
	public String getApplyType() {
		return this.applyType;
	}

	/** 申请类型 */
	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}

	private String houseRightNum;// 产权证号

	/** 产权证号 */
	@Column(columnDefinition = DEF_STR50)
	public String getHouseRightNum() {
		return this.houseRightNum;
	}

	/** 产权证号 */
	public void setHouseRightNum(String houseRightNum) {
		this.houseRightNum = houseRightNum;
	}

	private String bankNum;// 帐号

	/** 帐号 */
	@Column(columnDefinition = DEF_STR50)
	public String getBankNum() {
		return this.bankNum;
	}

	/** 帐号 */
	public void setBankNum(String bankNum) {
		this.bankNum = bankNum;
	}

	private String bankOpen;// 开户行

	/** 开户行 */
	@Column(columnDefinition = DEF_STR50)
	public String getBankOpen() {
		return this.bankOpen;
	}

	/** 开户行 */
	public void setBankOpen(String bankOpen) {
		this.bankOpen = bankOpen;
	}

	private String bankAccountName;// 户名

	/** 户名 */
	@Column(columnDefinition = DEF_STR50)
	public String getBankAccountName() {
		return this.bankAccountName;
	}

	/** 户名 */
	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}

	private String companyAdress;// 工作单位

	/** 工作单位 */
	@Column(columnDefinition = DEF_STR50)
	public String getCompanyAdress() {
		return this.companyAdress;
	}

	/** 工作单位 */
	public void setCompanyAdress(String companyAdress) {
		this.companyAdress = companyAdress;
	}

	private String companyNature;// 单位性质

	/** 单位性质 */
	@Column(columnDefinition = DEF_STR32)
	public String getCompanyNature() {
		return this.companyNature;
	}

	/** 单位性质 */
	public void setCompanyNature(String companyNature) {
		this.companyNature = companyNature;
	}

	private String companyPhone;// 单位电话

	/** 单位电话 */
	@Column(columnDefinition = DEF_STR32)
	public String getCompanyPhone() {
		return this.companyPhone;
	}

	/** 单位电话 */
	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}

	private String backMoneyType;// 还款方式

	/** 还款方式 */
	@Column(columnDefinition = DEF_STR20)
	public String getBackMoneyType() {
		return this.backMoneyType;
	}

	/** 还款方式 */
	public void setBackMoneyType(String backMoneyType) {
		this.backMoneyType = backMoneyType;
	}

	private String homePhone;// 住址电话

	/** 住址电话 */
	@Column(columnDefinition = DEF_STR20)
	public String getHomePhone() {
		return this.homePhone;
	}

	/** 住址电话 */
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	private String houseAddress;// 房屋座落

	/** 房屋座落 */
	@Column(columnDefinition = DEF_STR50)
	public String getHouseAddress() {
		return this.houseAddress;
	}

	/** 房屋座落 */
	public void setHouseAddress(String houseAddress) {
		this.houseAddress = houseAddress;
	}

	private String houseArea;// 房屋面积

	/** 房屋面积 */
	@Column(columnDefinition = DEF_STR20)
	public String getHouseArea() {
		return this.houseArea;
	}

	/** 房屋面积 */
	public void setHouseArea(String houseArea) {
		this.houseArea = houseArea;
	}

	private String isOwnCompany;// 是否自营公司

	/** 是否自营公司 */
	@Column(columnDefinition = DEF_STR20)
	public String getIsOwnCompany() {
		return this.isOwnCompany;
	}

	/** 是否自营公司 */
	public void setIsOwnCompany(String isOwnCompany) {
		this.isOwnCompany = isOwnCompany;
	}

	private Long loanMonth;// 借款期数

	/** 借款期数 */
	@Column(columnDefinition = DEF_NUM18)
	public Long getLoanMonth() {
		return this.loanMonth;
	}

	/** 借款期数 */
	public void setLoanMonth(Long loanMonth) {
		this.loanMonth = loanMonth;
	}

	private Date loanApplyDate;// 申请日期

	/** 申请日期 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getLoanApplyDate() {
		return this.loanApplyDate;
	}

	/** 申请日期 */
	public void setLoanApplyDate(Date loanApplyDate) {
		this.loanApplyDate = loanApplyDate;
	}

	private Double loanLoanAmount;// 借款金额

	/** 借款金额 */
	@Column(columnDefinition = DEF_STR20)
	public Double getLoanLoanAmount() {
		return this.loanLoanAmount;
	}

	/** 借款金额 */
	public void setLoanLoanAmount(Double loanLoanAmount) {
		this.loanLoanAmount = loanLoanAmount;
	}

	private String loanUse;// 借款用途

	/** 借款用途 */
	@Column(columnDefinition = DEF_STR50)
	public String getLoanUse() {
		return this.loanUse;
	}

	/** 借款用途 */
	public void setLoanUse(String loanUse) {
		this.loanUse = loanUse;
	}

	private String loanSrcAddress;// 借款人户籍地址

	/** 借款人户籍地址 */
	@Column(columnDefinition = DEF_STR50)
	public String getLoanSrcAddress() {
		return this.loanSrcAddress;
	}

	/** 借款人户籍地址 */
	public void setLoanSrcAddress(String loanSrcAddress) {
		this.loanSrcAddress = loanSrcAddress;
	}

	private String loanCode;// 借款编号

	/** 借款编号 */
	@Column(columnDefinition = DEF_STR32)
	public String getLoanCode() {
		return this.loanCode;
	}

	/** 借款编号 */
	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}

	private String mortgAount;// 抵押价值

	/** 抵押价值 */
	@Column(columnDefinition = DEF_STR50)
	public String getMortgAount() {
		return this.mortgAount;
	}

	/** 抵押价值 */
	public void setMortgAount(String mortgAount) {
		this.mortgAount = mortgAount;
	}

	private String oldHomePhone;// 老家宅电

	/** 老家宅电 */
	@Column(columnDefinition = DEF_STR50)
	public String getOldHomePhone() {
		return this.oldHomePhone;
	}

	/** 老家宅电 */
	public void setOldHomePhone(String oldHomePhone) {
		this.oldHomePhone = oldHomePhone;
	}

	private String houseRightValue;// 产权价值

	/** 产权价值 */
	@Column(columnDefinition = DEF_STR50)
	public String getHouseRightValue() {
		return this.houseRightValue;
	}

	/** 产权价值 */
	public void setHouseRightValue(String houseRightValue) {
		this.houseRightValue = houseRightValue;
	}

	private Double fixedRate;// 核定利率

	/** 核定利率 */
	@Column(columnDefinition = DEF_STR20)
	public Double getFixedRate() {
		return this.fixedRate;
	}

	/** 核定利率 */
	public void setFixedRate(Double fixedRate) {
		this.fixedRate = fixedRate;
	}

	private String spAdress;// 配偶地址

	/** 配偶地址 */
	@Column(columnDefinition = DEF_STR64)
	public String getSpAdress() {
		return this.spAdress;
	}

	/** 配偶地址 */
	public void setSpAdress(String spAdress) {
		this.spAdress = spAdress;
	}

	private String spAge;// 配偶年龄

	/** 配偶年龄 */
	@Column(columnDefinition = DEF_STR50)
	public String getSpAge() {
		return this.spAge;
	}

	/** 配偶年龄 */
	public void setSpAge(String spAge) {
		this.spAge = spAge;
	}

	private Double spIncome;// 配偶年收入

	/** 配偶年收入 */
	@Column(columnDefinition = DEF_NUM10_2)
	public Double getSpIncome() {
		return this.spIncome;
	}

	/** 配偶年收入 */
	public void setSpIncome(Double spIncome) {
		this.spIncome = spIncome;
	}

	private String spComp;// 配偶工作单位

	/** 配偶工作单位 */
	@Column(columnDefinition = DEF_STR50)
	public String getSpComp() {
		return this.spComp;
	}

	/** 配偶工作单位 */
	public void setSpComp(String spComp) {
		this.spComp = spComp;
	}

	private String spCompAdress;// 配偶工作单位地址

	/** 配偶工作单位地址 */
	@Column(columnDefinition = DEF_STR50)
	public String getSpCompAdress() {
		return this.spCompAdress;
	}

	/** 配偶工作单位地址 */
	public void setSpCompAdress(String spCompAdress) {
		this.spCompAdress = spCompAdress;
	}

	private String spCompPhone;// 配偶工作单位电话

	/** 配偶工作单位电话 */
	@Column(columnDefinition = DEF_STR50)
	public String getSpCompPhone() {
		return this.spCompPhone;
	}

	/** 配偶工作单位电话 */
	public void setSpCompPhone(String spCompPhone) {
		this.spCompPhone = spCompPhone;
	}

	private String spDep;// 配偶职务

	/** 配偶职务 */
	@Column(columnDefinition = DEF_STR50)
	public String getSpDep() {
		return this.spDep;
	}

	/** 配偶职务 */
	public void setSpDep(String spDep) {
		this.spDep = spDep;
	}

	private String spSex;// 配偶性别

	/** 配偶性别 */
	@Column(columnDefinition = DEF_STR8)
	public String getSpSex() {
		return this.spSex;
	}

	/** 配偶性别 */
	public void setSpSex(String spSex) {
		this.spSex = spSex;
	}

	private String spHomePhone;// 配偶家庭电话

	/** 配偶家庭电话 */
	@Column(columnDefinition = DEF_STR32)
	public String getSpHomePhone() {
		return this.spHomePhone;
	}

	/** 配偶家庭电话 */
	public void setSpHomePhone(String spHomePhone) {
		this.spHomePhone = spHomePhone;
	}

	private String spName;// 配偶姓名

	/** 配偶姓名 */
	@Column(columnDefinition = DEF_STR32)
	public String getSpName() {
		return this.spName;
	}

	/** 配偶姓名 */
	public void setSpName(String spName) {
		this.spName = spName;
	}

	private String spTelephone;// 配偶手机

	/** 配偶手机 */
	@Column(columnDefinition = DEF_STR32)
	public String getSpTelephone() {
		return this.spTelephone;
	}

	/** 配偶手机 */
	public void setSpTelephone(String spTelephone) {
		this.spTelephone = spTelephone;
	}

	private String spWorkLimit;// 配偶工作年限

	/** 配偶工作年限 */
	@Column(columnDefinition = DEF_STR3)
	public String getSpWorkLimit() {
		return this.spWorkLimit;
	}

	/** 配偶工作年限 */
	public void setSpWorkLimit(String spWorkLimit) {
		this.spWorkLimit = spWorkLimit;
	}

	private String spIdNum;// 配偶身份证号

	/** 配偶身份证号 */
	@Column(columnDefinition = DEF_STR32)
	public String getSpIdNum() {
		return this.spIdNum;
	}

	/** 配偶身份证号 */
	public void setSpIdNum(String spIdNum) {
		this.spIdNum = spIdNum;
	}

	private String loanState;// 借款状态

	/** 借款状态 */
	@Column(columnDefinition = DEF_STR8)
	public String getLoanState() {
		return this.loanState;
	}

	/** 借款状态 */
	public void setLoanState(String loanState) {
		this.loanState = loanState;
	}

	private String telephone;// 手机

	/** 手机 */
	@Column(columnDefinition = DEF_STR32)
	public String getTelephone() {
		return this.telephone;
	}

	/** 手机 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	private Long organiId;// 组织机构

	/** 组织机构 */
	@Column(columnDefinition = DEF_NUM18)
	public Long getOrganiId() {
		return this.organiId;
	}

	/** 组织机构 */
	public void setOrganiId(Long organiId) {
		this.organiId = organiId;
	}

	private String area;// 区域

	/** 区域 */
	@Column(columnDefinition = DEF_STR16)
	public String getArea() {
		return this.area;
	}

	/** 区域 */
	public void setArea(String area) {
		this.area = area;
	}

	private String city;// 城市

	/** 城市 */
	@Column(columnDefinition = DEF_STR16)
	public String getCity() {
		return this.city;
	}

	/** 城市 */
	public void setCity(String city) {
		this.city = city;
	}

	private String province;// 省份

	/** 省份 */
	@Column(columnDefinition = DEF_STR16)
	public String getProvince() {
		return this.province;
	}

	/** 省份 */
	public void setProvince(String province) {
		this.province = province;
	}

	private String loanerName;// 借款人姓名

	/** 借款人姓名 */
	@Column(columnDefinition = DEF_STR50)
	public String getLoanerName() {
		return this.loanerName;
	}

	/** 借款人姓名 */
	public void setLoanerName(String loanerName) {
		this.loanerName = loanerName;
	}

	private String loanerSex;// 借款人性别

	/** 借款人性别 */
	@Column(columnDefinition = DEF_STR50)
	public String getLoanerSex() {
		return this.loanerSex;
	}

	/** 借款人性别 */
	public void setLoanerSex(String loanerSex) {
		this.loanerSex = loanerSex;
	}

	private String loanerIdNumber;// 借款人身份证号

	/** 借款人身份证号 */
	@Column(columnDefinition = DEF_STR50)
	public String getLoanerIdNumber() {
		return this.loanerIdNumber;
	}

	/** 借款人身份证号 */
	public void setLoanerIdNumber(String loanerIdNumber) {
		this.loanerIdNumber = loanerIdNumber;
	}

	private Long loanerAge;// 借款人年龄

	/** 借款人年龄 */
	@Column(columnDefinition = DEF_NUM18)
	public Long getLoanerAge() {
		return this.loanerAge;
	}

	/** 借款人年龄 */
	public void setLoanerAge(Long loanerAge) {
		this.loanerAge = loanerAge;
	}

	private String houseOwnerName;// 产权人姓名

	/** 产权人姓名 */
	@Column(columnDefinition = DEF_STR50)
	public String getHouseOwnerName() {
		return this.houseOwnerName;
	}

	/** 产权人姓名 */
	public void setHouseOwnerName(String houseOwnerName) {
		this.houseOwnerName = houseOwnerName;
	}

	private String houseOwnerIdNum;// 产权人身份证号

	/** 产权人身份证号 */
	@Column(columnDefinition = DEF_STR50)
	public String getHouseOwnerIdNum() {
		return this.houseOwnerIdNum;
	}

	/** 产权人身份证号 */
	public void setHouseOwnerIdNum(String houseOwnerIdNum) {
		this.houseOwnerIdNum = houseOwnerIdNum;
	}

	private Long houseOwnerAge;// 产权人年龄

	/** 产权人年龄 */
	@Column(columnDefinition = DEF_NUM18)
	public Long getHouseOwnerAge() {
		return this.houseOwnerAge;
	}

	/** 产权人年龄 */
	public void setHouseOwnerAge(Long houseOwnerAge) {
		this.houseOwnerAge = houseOwnerAge;
	}

	private String houseJointName;// 共有权人姓名

	/** 共有权人姓名 */
	@Column(columnDefinition = DEF_STR50)
	public String getHouseJointName() {
		return this.houseJointName;
	}

	/** 共有权人姓名 */
	public void setHouseJointName(String houseJointName) {
		this.houseJointName = houseJointName;
	}

	private String houseJointSex;// 共有权人性别

	/** 共有权人性别 */
	@Column(columnDefinition = DEF_STR50)
	public String getHouseJointSex() {
		return this.houseJointSex;
	}

	/** 共有权人性别 */
	public void setHouseJointSex(String houseJointSex) {
		this.houseJointSex = houseJointSex;
	}

	private String houseJointIdNum;// 共有权人身份证号

	/** 共有权人身份证号 */
	@Column(columnDefinition = DEF_STR50)
	public String getHouseJointIdNum() {
		return this.houseJointIdNum;
	}

	/** 共有权人身份证号 */
	public void setHouseJointIdNum(String houseJointIdNum) {
		this.houseJointIdNum = houseJointIdNum;
	}

	private Long houseJointAge;// 共有权人年龄

	/** 共有权人年龄 */
	@Column(columnDefinition = DEF_NUM18)
	public Long getHouseJointAge() {
		return this.houseJointAge;
	}

	/** 共有权人年龄 */
	public void setHouseJointAge(Long houseJointAge) {
		this.houseJointAge = houseJointAge;
	}

	private String marital;// 婚姻状况

	/** 婚姻状况 */
	@Column(columnDefinition = DEF_STR50)
	public String getMarital() {
		return this.marital;
	}

	/** 婚姻状况 */
	public void setMarital(String marital) {
		this.marital = marital;
	}

	private String hasChild;// 有无子女

	/** 有无子女 */
	@Column(columnDefinition = DEF_STR50)
	public String getHasChild() {
		return this.hasChild;
	}

	/** 有无子女 */
	public void setHasChild(String hasChild) {
		this.hasChild = hasChild;
	}

	private String houseOwnerSex;// 产权人性别

	/** 产权人性别 */
	@Column(columnDefinition = DEF_STR50)
	public String getHouseOwnerSex() {
		return this.houseOwnerSex;
	}

	/** 产权人性别 */
	public void setHouseOwnerSex(String houseOwnerSex) {
		this.houseOwnerSex = houseOwnerSex;
	}

	private String firstLxrName;// 亲属姓名

	/** 亲属姓名 */
	@Column(columnDefinition = DEF_STR50)
	public String getFirstLxrName() {
		return this.firstLxrName;
	}

	/** 亲属姓名 */
	public void setFirstLxrName(String firstLxrName) {
		this.firstLxrName = firstLxrName;
	}

	private String firstLxrRelation;// 亲属与本人关系

	/** 亲属与本人关系 */
	@Column(columnDefinition = DEF_STR50)
	public String getFirstLxrRelation() {
		return this.firstLxrRelation;
	}

	/** 亲属与本人关系 */
	public void setFirstLxrRelation(String firstLxrRelation) {
		this.firstLxrRelation = firstLxrRelation;
	}

	private String firstLxrWorkUnit;// 亲属单位

	/** 亲属单位 */
	@Column(columnDefinition = DEF_STR50)
	public String getFirstLxrWorkUnit() {
		return this.firstLxrWorkUnit;
	}

	/** 亲属单位 */
	public void setFirstLxrWorkUnit(String firstLxrWorkUnit) {
		this.firstLxrWorkUnit = firstLxrWorkUnit;
	}

	private String firstLxrAddress;// 亲属单位或家庭住址

	/** 亲属单位或家庭住址 */
	@Column(columnDefinition = DEF_STR50)
	public String getFirstLxrAddress() {
		return this.firstLxrAddress;
	}

	/** 亲属单位或家庭住址 */
	public void setFirstLxrAddress(String firstLxrAddress) {
		this.firstLxrAddress = firstLxrAddress;
	}

	private String secondLxrName;// 朋友姓名

	/** 朋友姓名 */
	@Column(columnDefinition = DEF_STR50)
	public String getSecondLxrName() {
		return this.secondLxrName;
	}

	/** 朋友姓名 */
	public void setSecondLxrName(String secondLxrName) {
		this.secondLxrName = secondLxrName;
	}

	private String secondLxrRelation;// 朋友与本人关系

	/** 朋友与本人关系 */
	@Column(columnDefinition = DEF_STR50)
	public String getSecondLxrRelation() {
		return this.secondLxrRelation;
	}

	/** 朋友与本人关系 */
	public void setSecondLxrRelation(String secondLxrRelation) {
		this.secondLxrRelation = secondLxrRelation;
	}

	private String secondLxrWorkUnit;// 朋友工作单位

	/** 朋友工作单位 */
	@Column(columnDefinition = DEF_STR50)
	public String getSecondLxrWorkUnit() {
		return this.secondLxrWorkUnit;
	}

	/** 朋友工作单位 */
	public void setSecondLxrWorkUnit(String secondLxrWorkUnit) {
		this.secondLxrWorkUnit = secondLxrWorkUnit;
	}

	private String secondLxrAddress;// 朋友单位地址或家庭住址

	/** 朋友单位地址或家庭住址 */
	@Column(columnDefinition = DEF_STR50)
	public String getSecondLxrAddress() {
		return this.secondLxrAddress;
	}

	/** 朋友单位地址或家庭住址 */
	public void setSecondLxrAddress(String secondLxrAddress) {
		this.secondLxrAddress = secondLxrAddress;
	}

	private String firstLxrTelphone;// 亲属联系电话

	/** 亲属联系电话 */
	@Column(columnDefinition = DEF_STR50)
	public String getFirstLxrTelphone() {
		return this.firstLxrTelphone;
	}

	/** 亲属联系电话 */
	public void setFirstLxrTelphone(String firstLxrTelphone) {
		this.firstLxrTelphone = firstLxrTelphone;
	}

	private String secondLxrTelphone;// 朋友联系电话

	/** 朋友联系电话 */
	@Column(columnDefinition = DEF_STR50)
	public String getSecondLxrTelphone() {
		return this.secondLxrTelphone;
	}

	/** 朋友联系电话 */
	public void setSecondLxrTelphone(String secondLxrTelphone) {
		this.secondLxrTelphone = secondLxrTelphone;
	}

	private String thirdLxrName;// 同事姓名

	/** 同事姓名 */
	@Column(columnDefinition = DEF_STR50)
	public String getThirdLxrName() {
		return this.thirdLxrName;
	}

	/** 同事姓名 */
	public void setThirdLxrName(String thirdLxrName) {
		this.thirdLxrName = thirdLxrName;
	}

	private String thirdLxrRelation;// 同事与本人关系

	/** 同事与本人关系 */
	@Column(columnDefinition = DEF_STR50)
	public String getThirdLxrRelation() {
		return this.thirdLxrRelation;
	}

	/** 同事与本人关系 */
	public void setThirdLxrRelation(String thirdLxrRelation) {
		this.thirdLxrRelation = thirdLxrRelation;
	}

	private String thirdLxrWorkUnit;// 同事单位

	/** 同事单位 */
	@Column(columnDefinition = DEF_STR50)
	public String getThirdLxrWorkUnit() {
		return this.thirdLxrWorkUnit;
	}

	/** 同事单位 */
	public void setThirdLxrWorkUnit(String thirdLxrWorkUnit) {
		this.thirdLxrWorkUnit = thirdLxrWorkUnit;
	}

	private String thirdLxrAddress;// 同事单位地址或家庭住址

	/** 同事单位地址或家庭住址 */
	@Column(columnDefinition = DEF_STR50)
	public String getThirdLxrAddress() {
		return this.thirdLxrAddress;
	}

	/** 同事单位地址或家庭住址 */
	public void setThirdLxrAddress(String thirdLxrAddress) {
		this.thirdLxrAddress = thirdLxrAddress;
	}

	private String thirdLxrTelphone;// 同事联系电话

	/** 同事联系电话 */
	@Column(columnDefinition = DEF_STR50)
	public String getThirdLxrTelphone() {
		return this.thirdLxrTelphone;
	}

	/** 同事联系电话 */
	public void setThirdLxrTelphone(String thirdLxrTelphone) {
		this.thirdLxrTelphone = thirdLxrTelphone;
	}

	private Double makeLoanAmount;// 放款金额

	/** 放款金额 */
	@Column(columnDefinition = DEF_NUM15_5)
	public Double getMakeLoanAmount() {
		return this.makeLoanAmount;
	}

	/** 放款金额 */
	public void setMakeLoanAmount(Double makeLoanAmount) {
		this.makeLoanAmount = makeLoanAmount;
	}

	private XhJksq loanApply;

	@OneToOne(cascade = CascadeType.REFRESH, optional = false)
	@JoinColumn(name = "LOAN_APPLY_ID")
	public XhJksq getLoanApply() {
		return loanApply;
	}

	public void setLoanApply(XhJksq loanApply) {
		this.loanApply = loanApply;
	}
	
	
	private XhHouseLoanConsult xhHouseLoanConsult;
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false,fetch = FetchType.LAZY)
	@JoinColumn(name = "LOAN_CONSULT_ID")
	public XhHouseLoanConsult getXhHouseLoanConsult() {
		return xhHouseLoanConsult;
	}

	public void setXhHouseLoanConsult(XhHouseLoanConsult xhHouseLoanConsult) {
		this.xhHouseLoanConsult = xhHouseLoanConsult;
	}
	
			
}

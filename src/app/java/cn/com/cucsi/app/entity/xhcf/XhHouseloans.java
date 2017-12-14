package cn.com.cucsi.app.entity.xhcf;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import cn.com.cucsi.app.entity.baseinfo.Organi;
import cn.com.cucsi.app.entity.baseinfo.Xydkzx;
import cn.com.cucsi.core.orm.hibernate.AuditableEntity;

/**
 * XhJksq entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
// 表名与类名不相同时重新定义表名.
@Table(name = "XH_HOUSE_LOANS")
// 默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XhHouseloans extends AuditableEntity {

	// Fields
	private static final long serialVersionUID = -2276242824197821671L;

	// 借款人基础信息
	private String jkrxm; // 借款人姓名
	private String borsex; // 借款人性别
	private String borIdNumber; // 借款人身份证号
	private String borAge; // 借款人年龄
	private String ropertyName; // 产权人姓名
	private String ropertySex; // 产权人性别
	private String ropertyIdNumber; // 产权人身份证号
	private String ropertyIdAge; // 产权人年龄
	private String gyRopertyName; // 共有产权人姓名
	private String gyRopertySex; // 共有产权人性别
	private String gyRopertyIdNumber; // 共有产权人身份证号
	private String gyRopertyIdAge; // 共有产权人年龄
	private String applicationMatter; // 申请事项
	private String propertyNumber;//产权证号
	private String maritalStatus; // 婚姻状况
	private String ywzn; // 有无子女
	private String jkrhjAddress; // 借款人户籍地址
	private String houseAddress; // 房屋座落
	private String propertyValue; // 产权价值
	private String housesArea;//房屋面积
	private String isMortgage;//有无抵押
	private Double mortgageaAount;//抵押金额
	private String homePhone; // 住址电话
	private String telephone; // 移动电话
	private String oldHomePhone; //老宅电话
	private String isOwnCompany; //是否自营公司
	private String company; // 工作单位
	private String companyPhone; // 单位电话
	private String companyNature; // 单位性质
	private String companyAdress;// 单位地址
	private Double registerFunds; // 注册资金
	private String companiesNumber;// 公司人数
	private String bodyStatus;// 身体状况
	private String xueLi;// 学历
	private String fkje; // 放款金额
	private String province;//省份
	private String city;//地市
	private String area;//区县
	// 配偶信息
	private String spouseName; // 配偶姓名
	private String spouseGenders;// 配偶性别
	private String spouseZjhm; // 配偶证件号码
	private String spouseAge;// 年龄
	private String spouseAdress; // 配偶现住址
	private String spouseTelephone;// 配偶移动电话
	private String spouseHomePhone;// 配偶家庭电话
	private String spouseCompany;// 配偶工作单位
	private String spouseDepFunction;// 配偶部门与职务
	private String spouseCompanyPhone;// 配偶单位电话
	private String spouseCompanyAdress;// 配偶单位地址
	private String spouseAnnualIncome;// 配偶年收入
	private String spouseWorkinglife;// 配偶工作年限

	// 紧急联系人
//	private Set<XhJkjjlxr> xhJkjjlxrs = new HashSet<XhJkjjlxr>();

	// 借款申请信息
	private Double jkLoanQuota;// 借款申请额度
	private String jkCycle;// 借款周期
	private java.sql.Date jkLoanDate;// 申请日期
	private String jkUse;// 借款用途
//	private String togetherPerson;// 有无共同还款人
	private String bankOpen;// 账户开户行
	private String bankUsername;// 账户名称
	private String bankNum;// 账户号码
//	private String jkType;// 借款类型A：老板贷；B：老板楼易贷；C：薪水贷；D：薪水楼易贷；E：精英贷
	private String loanCode;// 借款编号
	private String hkWay;// 还款方式



//	private Templet templet; // 动态信息模板
	private String data; // 动态信息数据

	// 其他信息和关联表
	// 0：暂存;01：已提交，待填写共同还款人资料;02：已提交，待上传授信资料
	// 03：授信资料已上传，待信审;30：信审中;3a：信审拒绝
	// 50：信审通过，待合同制作;55：待签订合同;60 ：待放款
	// 65 ：已放款;80 ：超时冻结;81 ：客户放弃;82 ：拒贷;end ：已完成
	private String state;
	private String waitAccMoney; // 待核算金额
//	private Organi organi;// 组织

//	private Xydkzx xydkzx;// 咨询
	// private XhJkht xhjkht;//借款合同
	// private XhJksqtypemsg xhjksqtypemsg;//借款申请贷款类型动态信息
	private String appState; // 0为无借款变更申请;1为有变更申请

	/** 借款人姓名 */
	@Column(columnDefinition = DEF_STR32)
	public String getJkrxm() {
		return this.jkrxm;
	}

	/** 借款人姓名 */
	public void setJkrxm(String jkrxm) {
		this.jkrxm = jkrxm;
	}


	

	/** 家庭电话 */
	@Column(columnDefinition = DEF_STR32)
	public String getHomePhone() {
		return homePhone;
	}

	/** 家庭电话 */
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	/** 工作单位 */
	@Column(columnDefinition = DEF_STR512)
	public String getCompany() {
		return company;
	}

	/** 工作单位 */
	public void setCompany(String company) {
		this.company = company;
	}

	/** 单位电话 */
	@Column(columnDefinition = DEF_STR32)
	public String getCompanyPhone() {
		return companyPhone;
	}

	/** 单位电话 */
	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}

	/** 单位地址 */
	@Column(columnDefinition = DEF_STR512)
	public String getCompanyAdress() {
		return companyAdress;
	}

	/** 单位地址 */
	public void setCompanyAdress(String companyAdress) {
		this.companyAdress = companyAdress;
	}

	/** 单位性质 */
	@Column(columnDefinition = DEF_STR32)
	public String getCompanyNature() {
		return companyNature;
	}

	/** 单位性质 */
	public void setCompanyNature(String companyNature) {
		this.companyNature = companyNature;
	}

	/** 移动电话 */
	@Column(columnDefinition = DEF_STR32)
	public String getTelephone() {
		return telephone;
	}

	/** 移动电话 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/** 婚姻状况 */
	@Column(columnDefinition = DEF_STR32)
	public String getMaritalStatus() {
		return maritalStatus;
	}

	/** 婚姻状况 */
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	/** 有无子女 */
	@Column(columnDefinition = DEF_STR8)
	public String getYwzn() {
		return ywzn;
	}

	/** 有无子女 */
	public void setYwzn(String ywzn) {
		this.ywzn = ywzn;
	}


	/** 配偶姓名 */
	@Column(columnDefinition = DEF_STR32)
	public String getSpouseName() {
		return spouseName;
	}

	/** 配偶姓名 */
	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}

	/** 配偶性别 */
	@Column(columnDefinition = DEF_STR8)
	public String getSpouseGenders() {
		return spouseGenders;
	}

	/** 配偶性别 */
	public void setSpouseGenders(String spouseGenders) {
		this.spouseGenders = spouseGenders;
	}

	

	/** 配偶现住址 */
	@Column(columnDefinition = DEF_STR512)
	public String getSpouseAdress() {
		return spouseAdress;
	}

	/** 配偶现住址 */
	public void setSpouseAdress(String spouseAdress) {
		this.spouseAdress = spouseAdress;
	}


	/** 配偶证件号码 */
	@Column(columnDefinition = DEF_STR32)
	public String getSpouseZjhm() {
		return spouseZjhm;
	}

	/** 配偶证件号码 */
	public void setSpouseZjhm(String spouseZjhm) {
		this.spouseZjhm = spouseZjhm;
	}

	/** 配偶移动电话 */
	@Column(columnDefinition = DEF_STR32)
	public String getSpouseTelephone() {
		return spouseTelephone;
	}

	/** 配偶移动电话 */
	public void setSpouseTelephone(String spouseTelephone) {
		this.spouseTelephone = spouseTelephone;
	}

	/** 配偶家庭电话 */
	@Column(columnDefinition = DEF_STR32)
	public String getSpouseHomePhone() {
		return spouseHomePhone;
	}

	/** 配偶家庭电话 */
	public void setSpouseHomePhone(String spouseHomePhone) {
		this.spouseHomePhone = spouseHomePhone;
	}

	/** 配偶工作单位 */
	@Column(columnDefinition = DEF_STR512)
	public String getSpouseCompany() {
		return spouseCompany;
	}

	/** 配偶工作单位 */
	public void setSpouseCompany(String spouseCompany) {
		this.spouseCompany = spouseCompany;
	}

	/** 配偶部门与职务 */
	@Column(columnDefinition = DEF_STR64)
	public String getSpouseDepFunction() {
		return spouseDepFunction;
	}

	/** 配偶部门与职务 */
	public void setSpouseDepFunction(String spouseDepFunction) {
		this.spouseDepFunction = spouseDepFunction;
	}

	/** 配偶单位电话 */
	@Column(columnDefinition = DEF_STR32)
	public String getSpouseCompanyPhone() {
		return spouseCompanyPhone;
	}

	/** 配偶单位电话 */
	public void setSpouseCompanyPhone(String spouseCompanyPhone) {
		this.spouseCompanyPhone = spouseCompanyPhone;
	}

	/** 配偶单位地址 */
	@Column(columnDefinition = DEF_STR512)
	public String getSpouseCompanyAdress() {
		return spouseCompanyAdress;
	}

	/** 配偶单位地址 */
	public void setSpouseCompanyAdress(String spouseCompanyAdress) {
		this.spouseCompanyAdress = spouseCompanyAdress;
	}

	/** 配偶年收入 */
	@Column(columnDefinition = DEF_NUM10_2)
	public String getSpouseAnnualIncome() {
		return spouseAnnualIncome;
	}

	/** 配偶年收入 */
	public void setSpouseAnnualIncome(String spouseAnnualIncome) {
		this.spouseAnnualIncome = spouseAnnualIncome;
	}

	/** 配偶工作年限 */
	@Column(columnDefinition = DEF_STR3)
	public String getSpouseWorkinglife() {
		return spouseWorkinglife;
	}

	/** 配偶工作年限 */
	public void setSpouseWorkinglife(String spouseWorkinglife) {
		this.spouseWorkinglife = spouseWorkinglife;
	}

	/**
	 * 紧急联系人
	 */
	// 一对多定义
//	@OneToMany(targetEntity = XhJkjjlxr.class, cascade = CascadeType.ALL)
//	@Fetch(FetchMode.JOIN)
//	// @JoinColumn(name="XHJKSQ_ID", nullable=true, updatable=true)
//	@JoinColumn(name = "XHJKSQ_ID", updatable = false)
//	public Set<XhJkjjlxr> getXhJkjjlxrs() {
//		return xhJkjjlxrs;
//	}
//
//	/**
//	 * 紧急联系人
//	 */
//	public void setXhJkjjlxrs(Set<XhJkjjlxr> xhJkjjlxrs) {
//		this.xhJkjjlxrs = xhJkjjlxrs;
//	}

	/** 放款金额 */
	@Column(columnDefinition = DEF_NUM10_2)
	public String getFkje() {
		return this.fkje;
	}

	
	public void setFkje(String fkje) {
		this.fkje = fkje;
	}
	/** 省分 */
	@Column(columnDefinition=DEF_STR16)
	public String getProvince() {
		return province;
	}
	
	public void setProvince(String province) {
		this.province = province;
	}
	/** 城市 */
	@Column(columnDefinition=DEF_STR16)
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	/** 区域*/
	@Column(columnDefinition=DEF_STR16)
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	/** 借款申请额度 */
	@Column(columnDefinition = DEF_NUM10_2)
	public Double getJkLoanQuota() {
		return this.jkLoanQuota;
	}

	/** 借款申请额度 */
	public void setJkLoanQuota(Double jkLoanQuota) {
		this.jkLoanQuota = jkLoanQuota;
	}

	/** 借款周期 */
	@Column(columnDefinition = DEF_STR4)
	public String getJkCycle() {
		return this.jkCycle;
	}

	/** 借款周期 */
	public void setJkCycle(String jkCycle) {
		this.jkCycle = jkCycle;
	}

	/** 申请日期 */
	public java.sql.Date getJkLoanDate() {
		return this.jkLoanDate;
	}

	/** 申请日期 */
	public void setJkLoanDate(java.sql.Date jkLoanDate) {
		this.jkLoanDate = jkLoanDate;
	}

	/** 借款用途 */
	@Column(columnDefinition = DEF_STR4000)
	public String getJkUse() {
		return this.jkUse;
	}

	/** 借款用途 */
	public void setJkUse(String jkUse) {
		this.jkUse = jkUse;
	}

//	/** 有无共同还款人 */
//	@Column(columnDefinition = DEF_STR50)
//	public String getTogetherPerson() {
//		return this.togetherPerson;
//	}
//
//	/** 有无共同还款人 */
//	public void setTogetherPerson(String togetherPerson) {
//		this.togetherPerson = togetherPerson;
//	}

	/** 账户开户行 */
	@Column(columnDefinition = DEF_STR50)
	public String getBankOpen() {
		return this.bankOpen;
	}

	/** 账户开户行 */
	public void setBankOpen(String bankOpen) {
		this.bankOpen = bankOpen;
	}

	/** 账户名称 */
	@Column(columnDefinition = DEF_STR50)
	public String getBankUsername() {
		return this.bankUsername;
	}

	/** 账户名称 */
	public void setBankUsername(String bankUsername) {
		this.bankUsername = bankUsername;
	}

	/** 账户号码 */
	@Column(columnDefinition = DEF_STR50)
	public String getBankNum() {
		return this.bankNum;
	}

	/** 账户号码 */
	public void setBankNum(String bankNum) {
		this.bankNum = bankNum;
	}

//	/** 借款类型A：老板贷；B：老板楼易贷；C：薪水贷；D：薪水楼易贷；E：精英贷 */
//	@Column(columnDefinition = DEF_STR3)
//	public String getJkType() {
//		return this.jkType;
//	}
//
//	/** 借款类型A：老板贷；B：老板楼易贷；C：薪水贷；D：薪水楼易贷；E：精英贷 */
//	public void setJkType(String jkType) {
//		this.jkType = jkType;
//	}

	/** 还款方式 */
	@Column(columnDefinition = DEF_STR32)
	public String getHkWay() {
		return hkWay;
	}

	public void setHkWay(String hkWay) {
		this.hkWay = hkWay;
	}

	/** 借款编码 */
	@Column(columnDefinition = DEF_STR32)
	public String getLoanCode() {
		return loanCode;
	}

	/** 借款编码 */
	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}

	

	/** 动态信息模板 */
//	@OneToOne(cascade = CascadeType.REFRESH)
//	@JoinColumn(name = "TEMPLET_ID", unique = false, nullable = true, insertable = true, updatable = true)
//	public Templet getTemplet() {
//		return templet;
//	}
//
//	/** 动态信息模板 */
//	public void setTemplet(Templet templet) {
//		this.templet = templet;
//	}

	/** 借款申请动态信息 */
	@Column(columnDefinition = DEF_STR4000)
	public String getData() {
		return data;
	}

	/** 借款申请动态信息 */
	public void setData(String data) {
		this.data = data;
	}

//	/** 组织 */
//	@OneToOne(cascade = CascadeType.REFRESH)
//	@JoinColumn(name = "organi_id", unique = false, nullable = true, insertable = true, updatable = true)
//	public Organi getOrgani() {
//		return organi;
//	}
//
//	/** 组织 */
//	public void setOrgani(Organi organi) {
//		this.organi = organi;
//	}

	/**
	 * 借款申请单状态 0：暂存;01：已提交，待填写共同还款人资料;02：已提交，待上传授信资料 03：授信资料已上传，待信审;30：信审中;state
	 * ：3a：信审拒绝 50：信审通过，待合同制作;55：待签订合同;60 ：待放款 65 ：已放款;80 ：超时冻结;81 ：客户放弃;82
	 * ：拒贷;end ：已完成
	 * */
	@Column(columnDefinition = DEF_STR8)
	public String getState() {
		return state;
	}

	/**
	 * 借款申请单状态 0：暂存;01：已提交，待填写共同还款人资料;02：已提交，待上传授信资料 03：授信资料已上传，待信审;30：信审中;state
	 * ：3a：信审拒绝 50：信审通过，待合同制作;55：待签订合同;60 ：待放款 61 ：已放款;80 ：超时冻结;81 ：客户放弃;82
	 * ：拒贷;end ：已完成
	 * */
	public void setState(String state) {
		this.state = state;
	}

	/** 待核算金额 */
	@Column(columnDefinition = DEF_STR32)
	public String getWaitAccMoney() {
		return waitAccMoney;
	}

	/** 待核算金额 */
	public void setWaitAccMoney(String waitAccMoney) {
		this.waitAccMoney = waitAccMoney;
	}

	/** 借款咨询信息 */
//	@OneToOne(cascade = CascadeType.REFRESH)
//	@JoinColumn(name = "XYDKZX_ID", unique = false, nullable = true, insertable = true, updatable = true)
//	public Xydkzx getXydkzx() {
//		return xydkzx;
//	}
//
//	/** 借款咨询信息 */
//	public void setXydkzx(Xydkzx xydkzx) {
//		this.xydkzx = xydkzx;
//	}

	/** 0为无借款变更申请;1为有变更申请 */
	@Column(columnDefinition = DEF_STR8)
	public String getAppState() {
		return appState;
	}

	/** 0为无借款变更申请;1为有变更申请 */
	public void setAppState(String appState) {
		this.appState = appState;
	}
	/** 借款人性别 */
	@Column(columnDefinition = DEF_STR8)
	public String getBorsex() {
		return borsex;
	}

	public void setBorsex(String borsex) {
		this.borsex = borsex;
	}
	/** 借款人身份证号 */
	@Column(columnDefinition = DEF_STR40)
	public String getBorIdNumber() {
		return borIdNumber;
	}

	public void setBorIdNumber(String borIdNumber) {
		this.borIdNumber = borIdNumber;
	}
	/** 借款人年龄 */
	@Column(columnDefinition = DEF_STR8)
	public String getBorAge() {
		return borAge;
	}

	public void setBorAge(String borAge) {
		this.borAge = borAge;
	}
	/** 产权人姓名 */
	@Column(columnDefinition = DEF_STR32)
	public String getRopertyName() {
		return ropertyName;
	}

	public void setRopertyName(String ropertyName) {
		this.ropertyName = ropertyName;
	}
	/** 产权人性别 */
	@Column(columnDefinition = DEF_STR8)
	public String getRopertySex() {
		return ropertySex;
	}

	public void setRopertySex(String ropertySex) {
		this.ropertySex = ropertySex;
	}
	/** 产权人身份证号 */
	@Column(columnDefinition = DEF_STR40)
	public String getRopertyIdNumber() {
		return ropertyIdNumber;
	}

	public void setRopertyIdNumber(String ropertyIdNumber) {
		this.ropertyIdNumber = ropertyIdNumber;
	}
	/** 产权人身份年龄 */
	@Column(columnDefinition = DEF_STR8)
	public String getRopertyIdAge() {
		return ropertyIdAge;
	}

	public void setRopertyIdAge(String ropertyIdAge) {
		this.ropertyIdAge = ropertyIdAge;
	}
	/** 共有产权人身份姓名 */
	@Column(columnDefinition = DEF_STR32)
	public String getGyRopertyName() {
		return gyRopertyName;
	}

	public void setGyRopertyName(String gyRopertyName) {
		this.gyRopertyName = gyRopertyName;
	}
	/** 共有产权人身份性别 */
	@Column(columnDefinition = DEF_STR8)
	public String getGyRopertySex() {
		return gyRopertySex;
	}

	public void setGyRopertySex(String gyRopertySex) {
		this.gyRopertySex = gyRopertySex;
	}
	/** 共有产权人身份证*/
	@Column(columnDefinition = DEF_STR40)
	public String getGyRopertyIdNumber() {
		return gyRopertyIdNumber;
	}

	public void setGyRopertyIdNumber(String gyRopertyIdNumber) {
		this.gyRopertyIdNumber = gyRopertyIdNumber;
	}
	/** 共有产权人年龄*/
	@Column(columnDefinition = DEF_STR8)
	public String getGyRopertyIdAge() {
		return gyRopertyIdAge;
	}

	public void setGyRopertyIdAge(String gyRopertyIdAge) {
		this.gyRopertyIdAge = gyRopertyIdAge;
	}
	/** 申请事项*/
	@Column(columnDefinition = DEF_STR8)
	public String getApplicationMatter() {
		return applicationMatter;
	}

	public void setApplicationMatter(String applicationMatter) {
		this.applicationMatter = applicationMatter;
	}
	/** 申请事项*/
	@Column(columnDefinition = DEF_STR40)
	public String getPropertyNumber() {
		return propertyNumber;
	}

	public void setPropertyNumber(String propertyNumber) {
		this.propertyNumber = propertyNumber;
	}
	/** 借款人户籍地址*/
	@Column(columnDefinition = DEF_STR100)
	public String getJkrhjAddress() {
		return jkrhjAddress;
	}

	public void setJkrhjAddress(String jkrhjAddress) {
		this.jkrhjAddress = jkrhjAddress;
	}
	/** 房屋座落*/
	@Column(columnDefinition = DEF_STR100)
	public String getHouseAddress() {
		return houseAddress;
	}

	public void setHouseAddress(String houseAddress) {
		this.houseAddress = houseAddress;
	}
	/** 产权价值*/
	@Column(columnDefinition = DEF_STR32)
	public String getPropertyValue() {
		return propertyValue;
	}

	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}
	/** 房屋面积*/
	@Column(columnDefinition = DEF_STR32)
	public String getHousesArea() {
		return housesArea;
	}

	public void setHousesArea(String housesArea) {
		this.housesArea = housesArea;
	}
	/** 有无抵押*/
	@Column(columnDefinition = DEF_STR8)
	public String getIsMortgage() {
		return isMortgage;
	}

	public void setIsMortgage(String isMortgage) {
		this.isMortgage = isMortgage;
	}
	/** 抵押金额*/
	@Column(columnDefinition = DEF_NUM10_2)
	public Double getMortgageaAount() {
		return mortgageaAount;
	}

	public void setMortgageaAount(Double mortgageaAount) {
		this.mortgageaAount = mortgageaAount;
	}
	/** 老宅电话*/
	@Column(columnDefinition = DEF_STR32)
	public String getOldHomePhone() {
		return oldHomePhone;
	}

	public void setOldHomePhone(String oldHomePhone) {
		this.oldHomePhone = oldHomePhone;
	}
	/** 是否自营公司*/
	@Column(columnDefinition = DEF_STR8)
	public String getIsOwnCompany() {
		return isOwnCompany;
	}

	public void setIsOwnCompany(String isOwnCompany) {
		this.isOwnCompany = isOwnCompany;
	}
	/** 注册资金*/
	@Column(columnDefinition = DEF_NUM10_2)
	public Double getRegisterFunds() {
		return registerFunds;
	}

	public void setRegisterFunds(Double registerFunds) {
		this.registerFunds = registerFunds;
	}
	/** 公司人数*/
	@Column(columnDefinition = DEF_STR8)
	public String getCompaniesNumber() {
		return companiesNumber;
	}

	public void setCompaniesNumber(String companiesNumber) {
		this.companiesNumber = companiesNumber;
	}
	/** 身体状况*/
	@Column(columnDefinition = DEF_STR8)
	public String getBodyStatus() {
		return bodyStatus;
	}

	public void setBodyStatus(String bodyStatus) {
		this.bodyStatus = bodyStatus;
	}
	/** 学历*/
	@Column(columnDefinition = DEF_STR10)
	public String getXueLi() {
		return xueLi;
	}
	
	public void setXueLi(String xueLi) {
		this.xueLi = xueLi;
	}
	/** 年龄*/
	@Column(columnDefinition = DEF_STR10)
	public String getSpouseAge() {
		return spouseAge;
	}

	public void setSpouseAge(String spouseAge) {
		this.spouseAge = spouseAge;
	}

	

}

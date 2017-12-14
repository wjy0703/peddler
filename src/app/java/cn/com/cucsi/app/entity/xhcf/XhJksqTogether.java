package cn.com.cucsi.app.entity.xhcf;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import cn.com.cucsi.core.orm.hibernate.AuditableEntity;

/**
 * XhJksqTogether entity. 
 * @author MyEclipse Persistence Tools
 */
@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "XH_JKSQ_TOGETHER")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XhJksqTogether extends AuditableEntity{

	// Fields
	private static final long serialVersionUID = -2276242824197821671L;
	
	//借款人基础信息
	private String togetherName;				//共同借款人姓名
	private String identification;				//身份证号码
	private String genders;						//性别
	private String age;								//年龄
	private String telephone;						//手机号码
	private String maritalStatus;				//婚姻状况
	private String ywzn;							//有无子女
	private String email;							//电子邮箱
	private String qqhm;							//QQ号码
	private String hjadress;						//户籍地址
	private String address;  						//现住址
	private String addressPhone;				//现住址电话
	private String homePhone;					//家庭电话
	private String company;						//工作单位
	private String companyPhone; 			//单位电话
	private String companyAdress;				//单位地址
	private String depFunction;					//部门与职务
	private String department;					//部门名称
	private String function;						//职务
	private String liveState;						//居住状况 01:自购房屋;02:贷款购置房屋;03:亲属房屋;04:租房，房租* 元/月;05:其他
	private String liveMessage;					//居住状况说明信息
	//主要收入来源
	private String monthlySalary;				//每月工资(含奖金及补助)* 元/月
	private String rental;							//房屋出租* 元/月
	private String otherIncome;					//其他所得* 元/年
	private String annualIncome;				//年总收入* 元
	private String socialFund; 					//是否拥有社保基金:是/否
	
	private String loanQuota;					//借款申请额度
	private String jkCycle;						//申请还款期限
	//还款资金来源
	private String sourceOfFunds;				//01:独立还款，02:家人协助还款，03:其他方式_____
	private String sourceOfFundsInfo;		//还款资金来源中的  其他方式  说明
	
	
	private String state;							//暂存、提交等状态
	
	private XhJksq xhjksq;
	
	
	
	//紧急联系人
//	private Set<XhJkjjlxr> xhJkjjlxrs = new HashSet<XhJkjjlxr>();
	
	/**共同借款人姓名*/
	@Column(columnDefinition=DEF_STR32)
	public String getTogetherName() {
		return togetherName;
	}
	
	/**共同借款人姓名*/
	public void setTogetherName(String togetherName) {
		this.togetherName = togetherName;
	}
	
	/**身份证号码*/
	@Column(columnDefinition=DEF_STR32)
	public String getIdentification() {
		return identification;
	}
	
	/**身份证号码*/
	public void setIdentification(String identification) {
		this.identification = identification;
	}
	
	/**性别*/
	@Column(columnDefinition=DEF_STR8)
	public String getGenders() {
		return genders;
	}

	/**性别*/
	public void setGenders(String genders) {
		this.genders = genders;
	}
	
	/**年龄*/
	@Column(columnDefinition=DEF_STR3)
	public String getAge() {
		return age;
	}

	/**年龄*/
	public void setAge(String age) {
		this.age = age;
	}
	
	/**手机号码*/
	@Column(columnDefinition=DEF_STR32)
	public String getTelephone() {
		return telephone;
	}
	
	/**手机号码*/
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	/**婚姻状况*/
	@Column(columnDefinition=DEF_STR32)
	public String getMaritalStatus() {
		return maritalStatus;
	}
	
	/**婚姻状况*/
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	
	/**有无子女*/
	@Column(columnDefinition=DEF_STR8)
	public String getYwzn() {
		return ywzn;
	}
	
	/**有无子女*/
	public void setYwzn(String ywzn) {
		this.ywzn = ywzn;
	}
	
	/**电子邮箱*/
	@Column(columnDefinition=DEF_STR64)
	public String getEmail() {
		return email;
	}
	
	/**电子邮箱*/
	public void setEmail(String email) {
		this.email = email;
	}

	/**QQ号码*/
	@Column(columnDefinition=DEF_STR32)
	public String getQqhm() {
		return qqhm;
	}
	
	/**QQ号码*/
	public void setQqhm(String qqhm) {
		this.qqhm = qqhm;
	}
	
	/**户籍地址*/
	@Column(columnDefinition=DEF_STR512)
	public String getHjadress() {
		return hjadress;
	}
	
	/**户籍地址*/
	public void setHjadress(String hjadress) {
		this.hjadress = hjadress;
	}
	
	/**现住址*/
	@Column(columnDefinition=DEF_STR512)
	public String getAddress() {
		return address;
	}
	
	/**现住址*/
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**现住址电话*/
	@Column(columnDefinition=DEF_STR32)
	public String getAddressPhone() {
		return addressPhone;
	}

	/**现住址电话*/
	public void setAddressPhone(String addressPhone) {
		this.addressPhone = addressPhone;
	}
	
	/**家庭电话*/
	@Column(columnDefinition=DEF_STR32)
	public String getHomePhone() {
		return homePhone;
	}
	
	/**家庭电话*/
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}
	
	/**工作单位*/
	@Column(columnDefinition=DEF_STR512)
	public String getCompany() {
		return company;
	}
	
	/**工作单位*/
	public void setCompany(String company) {
		this.company = company;
	}
	
	/**单位电话*/
	@Column(columnDefinition=DEF_STR32)
	public String getCompanyPhone() {
		return companyPhone;
	}
	
	/**单位电话*/
	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}
	
	/**单位地址*/
	@Column(columnDefinition=DEF_STR512)
	public String getCompanyAdress() {
		return companyAdress;
	}
	
	/**单位地址*/
	public void setCompanyAdress(String companyAdress) {
		this.companyAdress = companyAdress;
	}
	
	/**部门与职务*/
	@Column(columnDefinition=DEF_STR64)
	public String getDepFunction() {
		return depFunction;
	}

	/**部门与职务*/
	public void setDepFunction(String depFunction) {
		this.depFunction = depFunction;
	}

	/**部门*/
	@Column(columnDefinition=DEF_STR64)
	public String getDepartment() {
		return department;
	}

	/**部门*/
	public void setDepartment(String department) {
		this.department = department;
	}

	/**职务*/
	@Column(columnDefinition=DEF_STR64)
	public String getFunction() {
		return function;
	}

	/**职务*/
	public void setFunction(String function) {
		this.function = function;
	}
	/**
	 *居住状况 01:自购房屋;02:贷款购置房屋;03:亲属房屋;04:租房，房租* 元/月;05:其他
	 */
	@Column(columnDefinition=DEF_STR8)
	public String getLiveState() {
		return liveState;
	}
	
	/**
	 * 居住状况 01:自购房屋;02:贷款购置房屋;03:亲属房屋;04:租房，房租* 元/月;05:其他
	 */
	public void setLiveState(String liveState) {
		this.liveState = liveState;
	}
	
	/**居住状况说明信息*/
	@Column(columnDefinition=DEF_STR1024)
	public String getLiveMessage() {
		return liveMessage;
	}
	
	/**居住状况说明信息*/
	public void setLiveMessage(String liveMessage) {
		this.liveMessage = liveMessage;
	}
	
	/**每月工资(含奖金及补助)* 元/月*/
	@Column(columnDefinition=DEF_STR13)
	public String getMonthlySalary() {
		return monthlySalary;
	}
	
	/**每月工资(含奖金及补助)* 元/月*/
	public void setMonthlySalary(String monthlySalary) {
		this.monthlySalary = monthlySalary;
	}

	/**房屋出租* 元/月*/
	@Column(columnDefinition=DEF_STR13)
	public String getRental() {
		return rental;
	}
	
	/**房屋出租* 元/月*/
	public void setRental(String rental) {
		this.rental = rental;
	}
	
	/**其他所得* 元/年*/
	@Column(columnDefinition=DEF_STR13)
	public String getOtherIncome() {
		return otherIncome;
	}
	
	/**其他所得* 元/年*/
	public void setOtherIncome(String otherIncome) {
		this.otherIncome = otherIncome;
	}

	/**年总收入* 元*/
	@Column(columnDefinition=DEF_NUM10_2)
	public String getAnnualIncome() {
		return annualIncome;
	}
	
	/**年总收入* 元*/
	public void setAnnualIncome(String annualIncome) {
		this.annualIncome = annualIncome;
	}

	/**是否拥有社保基金:是/否*/
	@Column(columnDefinition=DEF_STR8)
	public String getSocialFund() {
		return socialFund;
	}
	
	/**是否拥有社保基金:是/否*/
	public void setSocialFund(String socialFund) {
		this.socialFund = socialFund;
	}
	
	/**借款申请额度*/
	@Column(columnDefinition=DEF_NUM10_2)
	public String getLoanQuota() {
		return loanQuota;
	}

	/**借款申请额度*/
	public void setLoanQuota(String loanQuota) {
		this.loanQuota = loanQuota;
	}
	
	/**借款周期*/
	@Column(columnDefinition=DEF_STR4)
	public String getJkCycle() {
		return this.jkCycle;
	}
	
	/**借款周期*/
	public void setJkCycle(String jkCycle) {
		this.jkCycle = jkCycle;
	}

	/**还款资金来源：01:独立还款，02:家人协助还款，03:其他方式_____*/
	@Column(columnDefinition=DEF_STR8)
	public String getSourceOfFunds() {
		return sourceOfFunds;
	}

	/**还款资金来源：01:独立还款，02:家人协助还款，03:其他方式_____*/
	public void setSourceOfFunds(String sourceOfFunds) {
		this.sourceOfFunds = sourceOfFunds;
	}

	/**还款资金来源中的  其他方式  说明*/
	@Column(columnDefinition=DEF_STR512)
	public String getSourceOfFundsInfo() {
		return sourceOfFundsInfo;
	}

	/**还款资金来源中的  其他方式  说明*/
	public void setSourceOfFundsInfo(String sourceOfFundsInfo) {
		this.sourceOfFundsInfo = sourceOfFundsInfo;
	}

	/**借款申请单信息*/
	@OneToOne(cascade=CascadeType.REFRESH,fetch = FetchType.LAZY)
	@JoinColumn(name="XHJKSQ_ID", unique= false, nullable=true, insertable=true, updatable=true)
	public XhJksq getXhjksq() {
		return xhjksq;
	}

	/**借款申请单信息*/
	public void setXhjksq(XhJksq xhjksq) {
		this.xhjksq = xhjksq;
	}

	/**共同还款人暂存、提交等状态*/
	@Column(columnDefinition=DEF_STR8)
	public String getState() {
		return state;
	}

	/**共同还款人暂存、提交等状态*/
	public void setState(String state) {
		this.state = state;
	}

	
	
	List<XhJkjjlxr> xhJkjjlxrs;
	/**紧急联系人*/
	//一对多定义
	@OneToMany(targetEntity=XhJkjjlxr.class,cascade=CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="XHJKSQ_TOGETHER_ID", nullable=true, updatable=true)
	public List<XhJkjjlxr> getXhJkjjlxrs() {
		return xhJkjjlxrs;
	}

	/**紧急联系人*/
	public void setXhJkjjlxrs(List<XhJkjjlxr> xhJkjjlxrs) {
		this.xhJkjjlxrs = xhJkjjlxrs;
	}
	
}

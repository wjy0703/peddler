package cn.com.cucsi.app.entity.bean;

import cn.com.cucsi.app.entity.xhcf.XhJkjjlxr;
import cn.com.cucsi.app.entity.xhcf.XhJksq;
import cn.com.cucsi.app.entity.xhcf.XhJksqTogether;
import cn.com.cucsi.core.orm.hibernate.AuditableEntity;

/**
 * XhJksqTogetherComplement
 * @author MyEclipse Persistence Tools
 */
public class XhJksqTogetherComplement extends AuditableEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4770866990118647850L;
	// Fields
	
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
	
	private XhJkjjlxr xhjkjjlxr1;
	private XhJkjjlxr xhjkjjlxr2;
	private XhJkjjlxr xhjkjjlxr3;
	private XhJkjjlxr xhjkjjlxr4;
	private XhJkjjlxr xhjkjjlxr5;
	private XhJkjjlxr xhjkjjlxr6;
	
	public XhJksqTogetherComplement(XhJksqTogether xhjksqTogether, XhJkjjlxr [] xhjkjjlxrs){
		// 共同借款人姓名
		if(null != xhjksqTogether.getTogetherName() && !"".equals(xhjksqTogether.getTogetherName().trim())){
			togetherName = "readonly=\"readonly\"";
		}
		// 身份证号码
		if(null != xhjksqTogether.getIdentification() && !"".equals(xhjksqTogether.getIdentification().trim())){
			identification = "readonly=\"readonly\"";
		}
		// 性别
		if(null != xhjksqTogether.getGenders() && !"".equals(xhjksqTogether.getGenders().trim())){
			genders = "readonly=\"readonly\"";
		}
		// 年龄
		if(null != xhjksqTogether.getAge() && !"".equals(xhjksqTogether.getAge().trim())){
			age = "readonly=\"readonly\"";
		}
		// 手机号码
		if(null != xhjksqTogether.getTelephone() && !"".equals(xhjksqTogether.getTelephone().trim())){
			telephone = "readonly=\"readonly\"";
		}
		// 婚姻状况
		if(null != xhjksqTogether.getMaritalStatus() && !"".equals(xhjksqTogether.getMaritalStatus().trim())){
			maritalStatus = "readonly=\"readonly\"";
		}
		// 有无子女
		if(null != xhjksqTogether.getYwzn() && !"".equals(xhjksqTogether.getYwzn().trim())){
			ywzn = "readonly=\"readonly\"";
		}
		// 电子邮箱
		if(null != xhjksqTogether.getEmail() && !"".equals(xhjksqTogether.getEmail().trim())){
			email = "readonly=\"readonly\"";
		}
		// QQ号码
		if(null != xhjksqTogether.getQqhm() && !"".equals(xhjksqTogether.getQqhm().trim())){
			qqhm = "readonly=\"readonly\"";
		}
		// 户籍地址
		if(null != xhjksqTogether.getHjadress() && !"".equals(xhjksqTogether.getHjadress().trim())){
			hjadress = "readonly=\"readonly\"";
		}
		// 现住址
		if(null != xhjksqTogether.getAddress() && !"".equals(xhjksqTogether.getAddress().trim())){
			address = "readonly=\"readonly\"";
		}
		// 现住址电话
		if(null != xhjksqTogether.getAddressPhone() && !"".equals(xhjksqTogether.getAddressPhone().trim())){
			addressPhone = "readonly=\"readonly\"";
		}
		// 家庭电话
		if(null != xhjksqTogether.getHomePhone() && !"".equals(xhjksqTogether.getHomePhone().trim())){
			homePhone = "readonly=\"readonly\"";
		}
		// 工作单位
		if(null != xhjksqTogether.getCompany() && !"".equals(xhjksqTogether.getCompany().trim())){
			company = "readonly=\"readonly\"";
		}
		// 单位电话
		if(null != xhjksqTogether.getCompanyPhone() && !"".equals(xhjksqTogether.getCompanyPhone().trim())){
			companyPhone = "readonly=\"readonly\"";
		}
		// 单位地址
		if(null != xhjksqTogether.getCompanyAdress() && !"".equals(xhjksqTogether.getCompanyAdress().trim())){
			companyAdress = "readonly=\"readonly\"";
		}
		// 部门与职务
		if(null != xhjksqTogether.getDepFunction() && !"".equals(xhjksqTogether.getDepFunction().trim())){
			depFunction = "readonly=\"readonly\"";
		}
		// 部门名称
		if(null != xhjksqTogether.getDepartment() && !"".equals(xhjksqTogether.getDepartment().trim())){
			department = "readonly=\"readonly\"";
		}
		// 职务
		if(null != xhjksqTogether.getFunction() && !"".equals(xhjksqTogether.getFunction().trim())){
			function = "readonly=\"readonly\"";
		}
		// 居住状况 01:自购房屋;02:贷款购置房屋;03:亲属房屋;04:租房，房租* 元/月;05:其他
		if(null != xhjksqTogether.getLiveState() && !"".equals(xhjksqTogether.getLiveState().trim())){
			liveState = "readonly=\"readonly\"";
		}
		// 居住状况说明信息
		if(null != xhjksqTogether.getLiveMessage() && !"".equals(xhjksqTogether.getLiveMessage().trim())){
			liveMessage = "readonly=\"readonly\"";
		}
		//主要收入来源
		// 每月工资(含奖金及补助)* 元/月
		if(null != xhjksqTogether.getMonthlySalary() && !"".equals(xhjksqTogether.getMonthlySalary().trim())){
			monthlySalary = "readonly=\"readonly\"";
		}
		// 房屋出租* 元/月
		if(null != xhjksqTogether.getRental() && !"".equals(xhjksqTogether.getRental().trim())){
			rental = "readonly=\"readonly\"";
		}
		// 其他所得* 元/年
		if(null != xhjksqTogether.getOtherIncome() && !"".equals(xhjksqTogether.getOtherIncome().trim())){
			otherIncome = "readonly=\"readonly\"";
		}
		// 年总收入* 元
		if(null != xhjksqTogether.getAnnualIncome() && !"".equals(xhjksqTogether.getAnnualIncome().trim())){
			annualIncome = "readonly=\"readonly\"";
		}
		// 是否拥有社保基金:是/否
		if(null != xhjksqTogether.getSocialFund() && !"".equals(xhjksqTogether.getSocialFund().trim())){
			socialFund = "readonly=\"readonly\"";
		}
		
		// 借款申请额度
		if(null != xhjksqTogether.getLoanQuota() && !"".equals(xhjksqTogether.getLoanQuota().trim())){
			loanQuota = "readonly=\"readonly\"";
		}
		// 申请还款期限
		if(null != xhjksqTogether.getJkCycle() && !"".equals(xhjksqTogether.getJkCycle().trim())){
			jkCycle = "readonly=\"readonly\"";
		}
		
		//还款资金来源
		// 01:独立还款，02:家人协助还款，03:其他方式_____
		if(null != xhjksqTogether.getSourceOfFunds() && !"".equals(xhjksqTogether.getSourceOfFunds().trim())){
			sourceOfFunds = "readonly=\"readonly\"";
		}
		// 还款资金来源中的  其他方式  说明
		if(null != xhjksqTogether.getSourceOfFundsInfo() && !"".equals(xhjksqTogether.getSourceOfFundsInfo().trim())){
			sourceOfFundsInfo = "readonly=\"readonly\"";
		}
		
		// 暂存、提交等状态
		if(null != xhjksqTogether.getState() && !"".equals(xhjksqTogether.getState().trim())){
			state = "readonly=\"readonly\"";
		}
		
		//借款申请补充中的紧急联系人
		XhJkjjlxr xhjkjjlxr = null;
		for(int i=0;i<xhjkjjlxrs.length;i++){
			xhjkjjlxr = xhjkjjlxrs[i];
			XhJkjjlxr xhjkjjlxr0 = null;
			if(null != xhjkjjlxr){
				xhjkjjlxr0 = new XhJkjjlxr();
				//紧急联系人姓名
				if(null != xhjkjjlxr.getName() && !"".equals(xhjkjjlxr.getName().trim())){
					xhjkjjlxr0.setName("readonly=\"readonly\"");
				}
				//与本人关系
				if(null != xhjkjjlxr.getYbrgx() && !"".equals(xhjkjjlxr.getYbrgx().trim())){
					xhjkjjlxr0.setYbrgx("readonly=\"readonly\"");
				}
				//紧急联系人工作单位
				if(null != xhjkjjlxr.getJjlxrgzdw() && !"".equals(xhjkjjlxr.getJjlxrgzdw().trim())){
					xhjkjjlxr0.setJjlxrgzdw("readonly=\"readonly\"");
				}
				//单位地址或家庭住址
				if(null != xhjkjjlxr.getJjlxrdwdzhjtzz() && !"".equals(xhjkjjlxr.getJjlxrdwdzhjtzz().trim())){
					xhjkjjlxr0.setJjlxrdwdzhjtzz("readonly=\"readonly\"");
				}
				//紧急联系人联系电话
				if(null != xhjkjjlxr.getJjlxrlxdh() && !"".equals(xhjkjjlxr.getJjlxrlxdh().trim())){
					xhjkjjlxr0.setJjlxrlxdh("readonly=\"readonly\"");
				}
				//联系人类型
				if(null != xhjkjjlxr.getLxrlx() && !"".equals(xhjkjjlxr.getLxrlx().trim())){
					xhjkjjlxr0.setLxrlx("readonly=\"readonly\"");
				}
			}
			if(i == 0){
				xhjkjjlxr1 = xhjkjjlxr0;
			}else if(i == 1){
				xhjkjjlxr2 = xhjkjjlxr0;
			}else if(i == 2){
				xhjkjjlxr3 = xhjkjjlxr0;
			}else if(i == 3){
				xhjkjjlxr4 = xhjkjjlxr0;
			}else if(i == 4){
				xhjkjjlxr5 = xhjkjjlxr0;
			}else if(i == 5){
				xhjkjjlxr6 = xhjkjjlxr0;
			}
		}
		
	}
	
	/**共同借款人姓名*/
	public String getTogetherName() {
		return togetherName;
	}
	
	/**共同借款人姓名*/
	public void setTogetherName(String togetherName) {
		this.togetherName = togetherName;
	}
	
	/**身份证号码*/
	public String getIdentification() {
		return identification;
	}
	
	/**身份证号码*/
	public void setIdentification(String identification) {
		this.identification = identification;
	}
	
	/**性别*/
	public String getGenders() {
		return genders;
	}

	/**性别*/
	public void setGenders(String genders) {
		this.genders = genders;
	}
	
	/**年龄*/
	public String getAge() {
		return age;
	}

	/**年龄*/
	public void setAge(String age) {
		this.age = age;
	}
	
	/**手机号码*/
	public String getTelephone() {
		return telephone;
	}
	
	/**手机号码*/
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	/**婚姻状况*/
	public String getMaritalStatus() {
		return maritalStatus;
	}
	
	/**婚姻状况*/
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	
	/**有无子女*/
	public String getYwzn() {
		return ywzn;
	}
	
	/**有无子女*/
	public void setYwzn(String ywzn) {
		this.ywzn = ywzn;
	}
	
	/**电子邮箱*/
	public String getEmail() {
		return email;
	}
	
	/**电子邮箱*/
	public void setEmail(String email) {
		this.email = email;
	}

	/**QQ号码*/
	public String getQqhm() {
		return qqhm;
	}
	
	/**QQ号码*/
	public void setQqhm(String qqhm) {
		this.qqhm = qqhm;
	}
	
	/**户籍地址*/
	public String getHjadress() {
		return hjadress;
	}
	
	/**户籍地址*/
	public void setHjadress(String hjadress) {
		this.hjadress = hjadress;
	}
	
	/**现住址*/
	public String getAddress() {
		return address;
	}
	
	/**现住址*/
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**现住址电话*/
	public String getAddressPhone() {
		return addressPhone;
	}

	/**现住址电话*/
	public void setAddressPhone(String addressPhone) {
		this.addressPhone = addressPhone;
	}
	
	/**家庭电话*/
	public String getHomePhone() {
		return homePhone;
	}
	
	/**家庭电话*/
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}
	
	/**工作单位*/
	public String getCompany() {
		return company;
	}
	
	/**工作单位*/
	public void setCompany(String company) {
		this.company = company;
	}
	
	/**单位电话*/
	public String getCompanyPhone() {
		return companyPhone;
	}
	
	/**单位电话*/
	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}
	
	/**单位地址*/
	public String getCompanyAdress() {
		return companyAdress;
	}
	
	/**单位地址*/
	public void setCompanyAdress(String companyAdress) {
		this.companyAdress = companyAdress;
	}
	
	/**部门与职务*/
	public String getDepFunction() {
		return depFunction;
	}

	/**部门与职务*/
	public void setDepFunction(String depFunction) {
		this.depFunction = depFunction;
	}

	/**部门*/
	public String getDepartment() {
		return department;
	}

	/**部门*/
	public void setDepartment(String department) {
		this.department = department;
	}

	/**职务*/
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
	public String getLiveMessage() {
		return liveMessage;
	}
	
	/**居住状况说明信息*/
	public void setLiveMessage(String liveMessage) {
		this.liveMessage = liveMessage;
	}
	
	/**每月工资(含奖金及补助)* 元/月*/
	public String getMonthlySalary() {
		return monthlySalary;
	}
	
	/**每月工资(含奖金及补助)* 元/月*/
	public void setMonthlySalary(String monthlySalary) {
		this.monthlySalary = monthlySalary;
	}

	/**房屋出租* 元/月*/
	public String getRental() {
		return rental;
	}
	
	/**房屋出租* 元/月*/
	public void setRental(String rental) {
		this.rental = rental;
	}
	
	/**其他所得* 元/年*/
	public String getOtherIncome() {
		return otherIncome;
	}
	
	/**其他所得* 元/年*/
	public void setOtherIncome(String otherIncome) {
		this.otherIncome = otherIncome;
	}

	/**年总收入* 元*/
	public String getAnnualIncome() {
		return annualIncome;
	}
	
	/**年总收入* 元*/
	public void setAnnualIncome(String annualIncome) {
		this.annualIncome = annualIncome;
	}

	/**是否拥有社保基金:是/否*/
	public String getSocialFund() {
		return socialFund;
	}
	
	/**是否拥有社保基金:是/否*/
	public void setSocialFund(String socialFund) {
		this.socialFund = socialFund;
	}
	
	/**借款申请额度*/
	public String getLoanQuota() {
		return loanQuota;
	}

	/**借款申请额度*/
	public void setLoanQuota(String loanQuota) {
		this.loanQuota = loanQuota;
	}
	
	/**借款周期*/
	public String getJkCycle() {
		return this.jkCycle;
	}
	
	/**借款周期*/
	public void setJkCycle(String jkCycle) {
		this.jkCycle = jkCycle;
	}

	/**还款资金来源：01:独立还款，02:家人协助还款，03:其他方式_____*/
	public String getSourceOfFunds() {
		return sourceOfFunds;
	}

	/**还款资金来源：01:独立还款，02:家人协助还款，03:其他方式_____*/
	public void setSourceOfFunds(String sourceOfFunds) {
		this.sourceOfFunds = sourceOfFunds;
	}

	/**还款资金来源中的  其他方式  说明*/
	public String getSourceOfFundsInfo() {
		return sourceOfFundsInfo;
	}

	/**还款资金来源中的  其他方式  说明*/
	public void setSourceOfFundsInfo(String sourceOfFundsInfo) {
		this.sourceOfFundsInfo = sourceOfFundsInfo;
	}

	/**借款申请单信息*/
	public XhJksq getXhjksq() {
		return xhjksq;
	}

	/**借款申请单信息*/
	public void setXhjksq(XhJksq xhjksq) {
		this.xhjksq = xhjksq;
	}

	/**共同还款人暂存、提交等状态*/
	public String getState() {
		return state;
	}

	/**共同还款人暂存、提交等状态*/
	public void setState(String state) {
		this.state = state;
	}

	public XhJkjjlxr getXhjkjjlxr1() {
		return xhjkjjlxr1;
	}

	public void setXhjkjjlxr1(XhJkjjlxr xhjkjjlxr1) {
		this.xhjkjjlxr1 = xhjkjjlxr1;
	}

	public XhJkjjlxr getXhjkjjlxr2() {
		return xhjkjjlxr2;
	}

	public void setXhjkjjlxr2(XhJkjjlxr xhjkjjlxr2) {
		this.xhjkjjlxr2 = xhjkjjlxr2;
	}

	public XhJkjjlxr getXhjkjjlxr3() {
		return xhjkjjlxr3;
	}

	public void setXhjkjjlxr3(XhJkjjlxr xhjkjjlxr3) {
		this.xhjkjjlxr3 = xhjkjjlxr3;
	}

	public XhJkjjlxr getXhjkjjlxr4() {
		return xhjkjjlxr4;
	}

	public void setXhjkjjlxr4(XhJkjjlxr xhjkjjlxr4) {
		this.xhjkjjlxr4 = xhjkjjlxr4;
	}

	public XhJkjjlxr getXhjkjjlxr5() {
		return xhjkjjlxr5;
	}

	public void setXhjkjjlxr5(XhJkjjlxr xhjkjjlxr5) {
		this.xhjkjjlxr5 = xhjkjjlxr5;
	}

	public XhJkjjlxr getXhjkjjlxr6() {
		return xhjkjjlxr6;
	}

	public void setXhjkjjlxr6(XhJkjjlxr xhjkjjlxr6) {
		this.xhjkjjlxr6 = xhjkjjlxr6;
	}

	
	
}

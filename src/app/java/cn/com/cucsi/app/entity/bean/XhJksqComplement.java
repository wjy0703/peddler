package cn.com.cucsi.app.entity.bean;

import java.util.List;

import javax.persistence.Column;

import cn.com.cucsi.app.entity.xhcf.XhJkjjlxr;
import cn.com.cucsi.app.entity.xhcf.XhJksq;
import cn.com.cucsi.core.orm.hibernate.AuditableEntity;

/**
 * XhJksqComplement
 * @author MyEclipse Persistence Tools
 */
public class XhJksqComplement extends AuditableEntity {

	// Fields
	private static final long serialVersionUID = -2276242824197821671L;

	// 借款人基础信息
	private String jkrxm; // 借款人姓名
	private String englishName; // 英文名称
	private String genders; // 性别
	private String birthday; // 出生日期
	private String pocertificates; // 证件类型
	private String zjhm; // 证件号码
	private String hjadress; // 户籍地址
	private String homeAddress;//现住址
	private String homePhone; // 家庭电话
	private String company; // 工作单位
	private String companyPhone; // 单位电话
	private String companyAdress;// 单位地址
	private String companyNature; // 单位性质
	private String telephone; // 移动电话
	private String email; // 电子邮箱
	private String maritalStatus; // 婚姻状况
	private String ywzn; // 有无子女
	private String qqhm; // QQ号码
	private String annualIncome; // 年收入
	private String incomeIllustration; // 收入说明
	private String liveState; // 居住状态 01:自有房屋，有无贷款月供*元;02:亲属产权;03:租房，房租*元/月;04:其他
	private String liveMessage; // 居住说明信息
	private String txdz; // 通讯地址
	private String province; // 省份
	private String city; // 地市
	private String area; // 区县

	private String fkje; // 放款金额

	// 配偶信息
	private String spouseName; // 配偶姓名
	private String spouseGenders;// 配偶性别
	private String spouseBirthday;// 配偶出生日期
	private String spouseAdress; // 配偶现住址
	private String spousePocertificates;// 配偶证件类型
	private String spouseZjhm; // 配偶证件号码
	private String spouseTelephone;// 配偶移动电话
	private String spouseHomePhone;// 配偶家庭电话
	private String spouseCompany;// 配偶工作单位
	private String spouseDepFunction;// 配偶部门与职务
	private String spouseCompanyPhone;// 配偶单位电话
	private String spouseCompanyAdress;// 配偶单位地址
	private String spouseAnnualIncome;// 配偶年收入
	private String spouseWorkinglife;// 配偶工作年限

	// 借款申请信息
	private String jkLoanQuota;// 借款申请额度
	private String jkCycle;// 借款周期
	private String jkLoanDate;// 申请日期
	private String jkUse;// 借款用途
	private String togetherPerson;// 有无共同还款人
	private String bankOpen;// 账户开户行
	private String bankUsername;// 账户名称
	private String bankNum;// 账户号码
	private String jkType;// 借款类型A：老板贷；B：老板楼易贷；C：薪水贷；D：薪水楼易贷；E：精英贷
	private String loanCode;// 借款编号
	private String hkWay;// 还款方式

	private String data; // 动态信息数据

	// 其他信息和关联表
	// 0：暂存;01：已提交，待填写共同还款人资料;02：已提交，待上传授信资料
	// 03：授信资料已上传，待信审;30：信审中;3a：信审拒绝
	// 50：信审通过，待合同制作;55：待签订合同;60 ：待放款
	// 65 ：已放款;80 ：超时冻结;81 ：客户放弃;82 ：拒贷;end ：已完成
	private String state;
	
	private String backup01;//贷款标志位；CAR ：车贷；HOUSE：房贷； CREDIT：信贷；
	private String backup02;//备用字段2
	private String backup03;//备用字段3
	private String backup04;//备用字段4
	private String backup05;//备用字段5
	private String backup06;//备用字段6
	private String backup07;//备用字段7
	private String backup08;//备用字段8
	private String backup09;//备用字段9
	
	private XhJkjjlxr xhjkjjlxr1;
	private XhJkjjlxr xhjkjjlxr2;
	private XhJkjjlxr xhjkjjlxr3;
	private XhJkjjlxr xhjkjjlxr4;
	private XhJkjjlxr xhjkjjlxr5;
	private XhJkjjlxr xhjkjjlxr6;
	
	public XhJksqComplement(){}

	public XhJksqComplement(XhJksq xhjksq, XhJkjjlxr [] xhjkjjlxrs){
		// 借款人姓名
		if(null != xhjksq.getJkrxm() && !"".equals(xhjksq.getJkrxm().trim())){
			jkrxm = "readonly=\"readonly\"";
		}
		// 英文名称
		if(null != xhjksq.getEnglishName() && !"".equals(xhjksq.getEnglishName().trim())){
			englishName = "readonly=\"readonly\"";
		}
		// 性别
		if(null != xhjksq.getGenders() && !"".equals(xhjksq.getGenders().trim())){
			genders = "readonly=\"readonly\"";
		}
		// 出生日期
		if(null != xhjksq.getBirthday() && !"".equals(xhjksq.getBirthday().trim())){
			birthday = "readonly=\"readonly\"";
		}
		// 证件类型
		if(null != xhjksq.getPocertificates() && !"".equals(xhjksq.getPocertificates())){
			pocertificates = "readonly=\"readonly\"";
		}
		// 证件号码
		if(null != xhjksq.getZjhm() && !"".equals(xhjksq.getZjhm().trim())){
			zjhm = "readonly=\"readonly\"";
		}
		// 户籍地址
		if(null != xhjksq.getHjadress() && !"".equals(xhjksq.getHjadress().trim())){
			hjadress = "readonly=\"readonly\"";
		}
		// 家庭电话
		if(null != xhjksq.getHomePhone() && !"".equals(xhjksq.getHomePhone().trim())){
			homePhone = "readonly=\"readonly\"";
		}
		// 工作单位
		if(null != xhjksq.getCompany() && !"".equals(xhjksq.getCompany().trim())){
			company = "readonly=\"readonly\"";
		}
		// 单位电话
		if(null != xhjksq.getCompanyPhone() && !"".equals(xhjksq.getCompanyPhone().trim())){
			companyPhone = "readonly=\"readonly\"";
		}
		// 单位地址
		if(null != xhjksq.getCompanyAdress() && !"".equals(xhjksq.getCompanyAdress().trim())){
			companyAdress = "readonly=\"readonly\"";
		}
		// 单位性质
		if(null != xhjksq.getCompanyNature() && !"".equals(xhjksq.getCompanyNature().trim())){
			companyNature = "readonly=\"readonly\"";
		}
		// 移动电话
		if(null != xhjksq.getTelephone() && !"".equals(xhjksq.getTelephone().trim())){
			telephone = "readonly=\"readonly\"";
		}
		// 电子邮箱
		if(null != xhjksq.getEmail() && !"".equals(xhjksq.getEmail().trim())){
			email = "readonly=\"readonly\"";
		}
		// 婚姻状况
		if(null != xhjksq.getMaritalStatus() && !"".equals(xhjksq.getMaritalStatus().trim())){
			maritalStatus = "readonly=\"readonly\"";
		}
		// 有无子女
		if(null != xhjksq.getYwzn() && !"".equals(xhjksq.getYwzn().trim())){
			ywzn = "readonly=\"readonly\"";
		}
		// QQ号码
		if(null != xhjksq.getQqhm() && !"".equals(xhjksq.getQqhm().trim())){
			qqhm = "readonly=\"readonly\"";
		}
		// 年收入
		if(null != xhjksq.getAnnualIncome() && !"".equals(xhjksq.getAnnualIncome().trim())){
			annualIncome = "readonly=\"readonly\"";
		}
		// 收入说明
		if(null != xhjksq.getIncomeIllustration() && !"".equals(xhjksq.getIncomeIllustration().trim())){
			incomeIllustration = "readonly=\"readonly\"";
		}
		// 居住状态 01:自有房屋，有无贷款月供*元;02:亲属产权;03:租房，房租*元/月;04:其他
		if(null != xhjksq.getLiveState() && !"".equals(xhjksq.getLiveState().trim())){
			liveState = "readonly=\"readonly\"";
		}
		// 居住说明信息
		if(null != xhjksq.getLiveMessage() && !"".equals(xhjksq.getLiveMessage().trim())){
			liveMessage = "readonly=\"readonly\"";
		}
		// 通讯地址
		if(null != xhjksq.getTxdz() && !"".equals(xhjksq.getTxdz().trim())){
			txdz = "readonly=\"readonly\"";
		}
		// 省份
		if(null != xhjksq.getProvince() ){
			province = "readonly=\"readonly\"";
		}
		// 地市
		if(null != xhjksq.getCity() ){
			city = "readonly=\"readonly\"";
		}
		// 区县
		if(null != xhjksq.getArea() ){
			area = "readonly=\"readonly\"";
		}

		// 放款金额
		if(null != xhjksq.getFkje() && !"".equals(xhjksq.getFkje().trim())){
			fkje = "readonly=\"readonly\"";
		}

		// 配偶信息
		// 配偶姓名
		if(null != xhjksq.getSpouseName() && !"".equals(xhjksq.getSpouseName().trim())){
			spouseName = "readonly=\"readonly\"";
		}
		// 配偶性别
		if(null != xhjksq.getSpouseGenders() && !"".equals(xhjksq.getSpouseGenders().trim())){
			spouseGenders = "readonly=\"readonly\"";
		}
		// 配偶出生日期
		if(null != xhjksq.getSpouseBirthday() && !"".equals(xhjksq.getSpouseBirthday().trim())){
			spouseBirthday = "readonly=\"readonly\"";
		}
		// 配偶现住址
		if(null != xhjksq.getSpouseAdress() && !"".equals(xhjksq.getSpouseAdress().trim())){
			spouseAdress = "readonly=\"readonly\"";
		}
		// 配偶证件类型
		if(null != xhjksq.getSpousePocertificates() && !"".equals(xhjksq.getSpousePocertificates().trim())){
			spousePocertificates = "readonly=\"readonly\"";
		}
		// 配偶证件号码
		if(null != xhjksq.getSpouseZjhm() && !"".equals(xhjksq.getSpouseZjhm().trim())){
			spouseZjhm = "readonly=\"readonly\"";
		}
		// 配偶移动电话
		if(null != xhjksq.getSpouseTelephone() && !"".equals(xhjksq.getSpouseTelephone().trim())){
			spouseTelephone = "readonly=\"readonly\"";
		}
		// 配偶家庭电话
		if(null != xhjksq.getSpouseHomePhone() && !"".equals(xhjksq.getSpouseHomePhone().trim())){
			spouseHomePhone = "readonly=\"readonly\"";
		}
		// 配偶工作单位
		if(null != xhjksq.getSpouseCompany() && !"".equals(xhjksq.getSpouseCompany().trim())){
			spouseCompany = "readonly=\"readonly\"";
		}
		// 配偶部门与职务
		if(null != xhjksq.getSpouseDepFunction() && !"".equals(xhjksq.getSpouseDepFunction().trim())){
			spouseDepFunction = "readonly=\"readonly\"";
		}
		// 配偶单位电话
		if(null != xhjksq.getSpouseCompanyPhone() && !"".equals(xhjksq.getSpouseCompanyPhone().trim())){
			spouseCompanyPhone = "readonly=\"readonly\"";
		}
		// 配偶单位地址
		if(null != xhjksq.getSpouseCompanyAdress() && !"".equals(xhjksq.getSpouseCompanyAdress().trim())){
			spouseCompanyAdress = "readonly=\"readonly\"";
		}
		// 配偶年收入
		if(null != xhjksq.getSpouseAnnualIncome() && !"".equals(xhjksq.getSpouseAnnualIncome().trim())){
			spouseAnnualIncome = "readonly=\"readonly\"";
		}
		// 配偶工作年限
		if(null != xhjksq.getSpouseWorkinglife() && !"".equals(xhjksq.getSpouseWorkinglife().trim())){
			spouseWorkinglife = "readonly=\"readonly\"";
		}
		
		// 借款申请额度
		if(null != xhjksq.getJkLoanQuota() && !"".equals(xhjksq.getJkLoanQuota().trim())){
			jkLoanQuota = "readonly=\"readonly\"";
		}
		// 借款周期
		if(null != xhjksq.getJkCycle() && !"".equals(xhjksq.getJkCycle().trim())){
			jkCycle = "readonly=\"readonly\"";
		}
		// 申请日期
		if(null != xhjksq.getJkLoanDate()){
			jkLoanDate = "readonly=\"readonly\"";
		}
		// 借款用途
		if(null != xhjksq.getJkUse() && !"".equals(xhjksq.getJkUse().trim())){
			jkUse = "readonly=\"readonly\"";
		}
		// 有无共同还款人
		if(null != xhjksq.getTogetherPerson() && !"".equals(xhjksq.getTogetherPerson().trim())){
			togetherPerson = "readonly=\"readonly\"";
		}
		// 账户开户行
		if(null != xhjksq.getBankOpen() && !"".equals(xhjksq.getBankOpen().trim())){
			bankOpen = "readonly=\"readonly\"";
		}
		// 账户名称
		if(null != xhjksq.getBankUsername() && !"".equals(xhjksq.getBankUsername().trim())){
			bankUsername = "readonly=\"readonly\"";
		}
		// 账户号码
		if(null != xhjksq.getBankNum() && !"".equals(xhjksq.getBankNum().trim())){
			bankNum = "readonly=\"readonly\"";
		}
		// 借款类型A：老板贷；B：老板楼易贷；C：薪水贷；D：薪水楼易贷；E：精英贷
		if(null != xhjksq.getJkType() && !"".equals(xhjksq.getJkType().trim())){
			jkType = "readonly=\"readonly\"";
		}
		// 借款编号
		if(null != xhjksq.getLoanCode() && !"".equals(xhjksq.getLoanCode().trim())){
			loanCode = "readonly=\"readonly\"";
		}
		// 还款方式
		if(null != xhjksq.getHkWay() && !"".equals(xhjksq.getHkWay().trim())){
			hkWay = "readonly=\"readonly\"";
		}
		//现住址
		if(null != xhjksq.getHomeAddress() && !"".equals(xhjksq.getHomeAddress().trim())){
			homeAddress = "readonly=\"readonly\"";
		}
		//备用字段1 贷款标志位；CAR ：车贷；HOUSE：房贷； CREDIT：信贷；
		if(null != xhjksq.getBackup01() && !"".equals(xhjksq.getBackup01().trim())){
			backup01 = "readonly=\"readonly\"";
		}
		//备用字段2
		if(null != xhjksq.getBackup02() && !"".equals(xhjksq.getBackup02().trim())){
			backup02 = "readonly=\"readonly\"";
		}
		//备用字段3
		if(null != xhjksq.getBackup03() && !"".equals(xhjksq.getBackup03().trim())){
			backup03 = "readonly=\"readonly\"";
		}
		//备用字段4
		if(null != xhjksq.getBackup04() && !"".equals(xhjksq.getBackup04().trim())){
			backup04 = "readonly=\"readonly\"";
		}
		//备用字段5
		if(null != xhjksq.getBackup05() && !"".equals(xhjksq.getBackup05().trim())){
			backup05 = "readonly=\"readonly\"";
		}
		//备用字段6
		if(null != xhjksq.getBackup06() && !"".equals(xhjksq.getBackup06().trim())){
			backup06 = "readonly=\"readonly\"";
		}
		//备用字段7
		if(null != xhjksq.getBackup07() && !"".equals(xhjksq.getBackup07().trim())){
			backup07 = "readonly=\"readonly\"";
		}
		//备用字段8
		if(null != xhjksq.getBackup08() && !"".equals(xhjksq.getBackup08().trim())){
			backup08 = "readonly=\"readonly\"";
		}
		//备用字段9
		if(null != xhjksq.getBackup09() && !"".equals(xhjksq.getBackup09().trim())){
			backup09 = "readonly=\"readonly\"";
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
	
	/** 借款人姓名 */
	public String getJkrxm() {
		return this.jkrxm;
	}

	/** 借款人姓名 */
	public void setJkrxm(String jkrxm) {
		this.jkrxm = jkrxm;
	}

	/** 英文名称 */
	public String getEnglishName() {
		return englishName;
	}

	/** 英文名称 */
	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	/** 性别 */
	public String getGenders() {
		return genders;
	}

	/** 性别 */
	public void setGenders(String genders) {
		this.genders = genders;
	}

	/** 出生日期 */
	public String getBirthday() {
		return birthday;
	}

	/** 出生日期 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	/** 证件类型 */
	public String getPocertificates() {
		return pocertificates;
	}

	/** 证件类型 */
	public void setPocertificates(String pocertificates) {
		this.pocertificates = pocertificates;
	}

	/** 证件号码 */
	public String getZjhm() {
		return zjhm;
	}

	/** 证件号码 */
	public void setZjhm(String zjhm) {
		this.zjhm = zjhm;
	}

	/** 户籍地址 */
	public String getHjadress() {
		return hjadress;
	}

	/** 户籍地址 */
	public void setHjadress(String hjadress) {
		this.hjadress = hjadress;
	}

	/** 家庭电话 */
	public String getHomePhone() {
		return homePhone;
	}

	/** 家庭电话 */
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	/** 工作单位 */
	public String getCompany() {
		return company;
	}

	/** 工作单位 */
	public void setCompany(String company) {
		this.company = company;
	}

	/** 单位电话 */
	public String getCompanyPhone() {
		return companyPhone;
	}

	/** 单位电话 */
	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}

	/** 单位地址 */
	public String getCompanyAdress() {
		return companyAdress;
	}

	/** 单位地址 */
	public void setCompanyAdress(String companyAdress) {
		this.companyAdress = companyAdress;
	}

	/** 单位性质 */
	public String getCompanyNature() {
		return companyNature;
	}

	/** 单位性质 */
	public void setCompanyNature(String companyNature) {
		this.companyNature = companyNature;
	}

	/** 移动电话 */
	public String getTelephone() {
		return telephone;
	}

	/** 移动电话 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/** 电子邮箱 */
	public String getEmail() {
		return email;
	}

	/** 电子邮箱 */
	public void setEmail(String email) {
		this.email = email;
	}

	/** 婚姻状况 */
	public String getMaritalStatus() {
		return maritalStatus;
	}

	/** 婚姻状况 */
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	/** 有无子女 */
	public String getYwzn() {
		return ywzn;
	}

	/** 有无子女 */
	public void setYwzn(String ywzn) {
		this.ywzn = ywzn;
	}

	/** QQ号码 */
	public String getQqhm() {
		return qqhm;
	}

	/** QQ号码 */
	public void setQqhm(String qqhm) {
		this.qqhm = qqhm;
	}

	/** 年收入 */
	public String getAnnualIncome() {
		return annualIncome;
	}

	/** 年收入 */
	public void setAnnualIncome(String annualIncome) {
		this.annualIncome = annualIncome;
	}

	/** 收入说明 */
	public String getIncomeIllustration() {
		return incomeIllustration;
	}

	/** 收入说明 */
	public void setIncomeIllustration(String incomeIllustration) {
		this.incomeIllustration = incomeIllustration;
	}

	/**
	 * 居住状态 01:自有房屋，有无贷款月供*元;02:亲属产权;03:租房，房租* 元/月;04:其他
	 */
	public String getLiveState() {
		return liveState;
	}

	/**
	 * 居住状态 01:自有房屋，有无贷款月供*元;02:亲属产权;03:租房，房租* 元/月;04:其他
	 */
	public void setLiveState(String liveState) {
		this.liveState = liveState;
	}

	/** 居住说明信息 */
	public String getLiveMessage() {
		return liveMessage;
	}

	/** 居住说明信息 */
	public void setLiveMessage(String liveMessage) {
		this.liveMessage = liveMessage;
	}

	/** 通讯地址 */
	public String getTxdz() {
		return txdz;
	}

	/** 通讯地址 */
	public void setTxdz(String txdz) {
		this.txdz = txdz;
	}

	/** 省份 */
	public String getProvince() {
		return province;
	}

	/** 省份 */
	public void setProvince(String province) {
		this.province = province;
	}

	/** 地市 */
	public String getCity() {
		return city;
	}

	/** 地市 */
	public void setCity(String city) {
		this.city = city;
	}

	/** 区县 */
	public String getArea() {
		return area;
	}

	/** 区县 */
	public void setArea(String area) {
		this.area = area;
	}

	/** 配偶姓名 */
	public String getSpouseName() {
		return spouseName;
	}

	/** 配偶姓名 */
	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}

	/** 配偶性别 */
	public String getSpouseGenders() {
		return spouseGenders;
	}

	/** 配偶性别 */
	public void setSpouseGenders(String spouseGenders) {
		this.spouseGenders = spouseGenders;
	}

	/** 配偶出生日期 */
	public String getSpouseBirthday() {
		return spouseBirthday;
	}

	/** 配偶出生日期 */
	public void setSpouseBirthday(String spouseBirthday) {
		this.spouseBirthday = spouseBirthday;
	}

	/** 配偶现住址 */
	public String getSpouseAdress() {
		return spouseAdress;
	}

	/** 配偶现住址 */
	public void setSpouseAdress(String spouseAdress) {
		this.spouseAdress = spouseAdress;
	}

	/** 配偶证件类型 */
	public String getSpousePocertificates() {
		return spousePocertificates;
	}

	/** 配偶证件类型 */
	public void setSpousePocertificates(String spousePocertificates) {
		this.spousePocertificates = spousePocertificates;
	}

	/** 配偶证件号码 */
	public String getSpouseZjhm() {
		return spouseZjhm;
	}

	/** 配偶证件号码 */
	public void setSpouseZjhm(String spouseZjhm) {
		this.spouseZjhm = spouseZjhm;
	}

	/** 配偶移动电话 */
	public String getSpouseTelephone() {
		return spouseTelephone;
	}

	/** 配偶移动电话 */
	public void setSpouseTelephone(String spouseTelephone) {
		this.spouseTelephone = spouseTelephone;
	}

	/** 配偶家庭电话 */
	public String getSpouseHomePhone() {
		return spouseHomePhone;
	}

	/** 配偶家庭电话 */
	public void setSpouseHomePhone(String spouseHomePhone) {
		this.spouseHomePhone = spouseHomePhone;
	}

	/** 配偶工作单位 */
	public String getSpouseCompany() {
		return spouseCompany;
	}

	/** 配偶工作单位 */
	public void setSpouseCompany(String spouseCompany) {
		this.spouseCompany = spouseCompany;
	}

	/** 配偶部门与职务 */
	public String getSpouseDepFunction() {
		return spouseDepFunction;
	}

	/** 配偶部门与职务 */
	public void setSpouseDepFunction(String spouseDepFunction) {
		this.spouseDepFunction = spouseDepFunction;
	}

	/** 配偶单位电话 */
	public String getSpouseCompanyPhone() {
		return spouseCompanyPhone;
	}

	/** 配偶单位电话 */
	public void setSpouseCompanyPhone(String spouseCompanyPhone) {
		this.spouseCompanyPhone = spouseCompanyPhone;
	}

	/** 配偶单位地址 */
	public String getSpouseCompanyAdress() {
		return spouseCompanyAdress;
	}

	/** 配偶单位地址 */
	public void setSpouseCompanyAdress(String spouseCompanyAdress) {
		this.spouseCompanyAdress = spouseCompanyAdress;
	}

	/** 配偶年收入 */
	public String getSpouseAnnualIncome() {
		return spouseAnnualIncome;
	}

	/** 配偶年收入 */
	public void setSpouseAnnualIncome(String spouseAnnualIncome) {
		this.spouseAnnualIncome = spouseAnnualIncome;
	}

	/** 配偶工作年限 */
	public String getSpouseWorkinglife() {
		return spouseWorkinglife;
	}

	/** 配偶工作年限 */
	public void setSpouseWorkinglife(String spouseWorkinglife) {
		this.spouseWorkinglife = spouseWorkinglife;
	}

	/** 放款金额 */
	public String getFkje() {
		return this.fkje;
	}

	/** 借款申请额度 */
	public void setFkje(String fkje) {
		this.fkje = fkje;
	}

	/** 借款申请额度 */
	public String getJkLoanQuota() {
		return this.jkLoanQuota;
	}

	/** 借款申请额度 */
	public void setJkLoanQuota(String jkLoanQuota) {
		this.jkLoanQuota = jkLoanQuota;
	}

	/** 借款周期 */
	public String getJkCycle() {
		return this.jkCycle;
	}

	/** 借款周期 */
	public void setJkCycle(String jkCycle) {
		this.jkCycle = jkCycle;
	}

	/** 申请日期 */
	public String getJkLoanDate() {
		return this.jkLoanDate;
	}

	/** 申请日期 */
	public void setJkLoanDate(String jkLoanDate) {
		this.jkLoanDate = jkLoanDate;
	}

	/** 借款用途 */
	public String getJkUse() {
		return this.jkUse;
	}

	/** 借款用途 */
	public void setJkUse(String jkUse) {
		this.jkUse = jkUse;
	}

	/** 有无共同还款人 */
	public String getTogetherPerson() {
		return this.togetherPerson;
	}

	/** 有无共同还款人 */
	public void setTogetherPerson(String togetherPerson) {
		this.togetherPerson = togetherPerson;
	}

	/** 账户开户行 */
	public String getBankOpen() {
		return this.bankOpen;
	}

	/** 账户开户行 */
	public void setBankOpen(String bankOpen) {
		this.bankOpen = bankOpen;
	}

	/** 账户名称 */
	public String getBankUsername() {
		return this.bankUsername;
	}

	/** 账户名称 */
	public void setBankUsername(String bankUsername) {
		this.bankUsername = bankUsername;
	}

	/** 账户号码 */
	public String getBankNum() {
		return this.bankNum;
	}

	/** 账户号码 */
	public void setBankNum(String bankNum) {
		this.bankNum = bankNum;
	}

	/** 借款类型A：老板贷；B：老板楼易贷；C：薪水贷；D：薪水楼易贷；E：精英贷 */
	public String getJkType() {
		return this.jkType;
	}

	/** 借款类型A：老板贷；B：老板楼易贷；C：薪水贷；D：薪水楼易贷；E：精英贷 */
	public void setJkType(String jkType) {
		this.jkType = jkType;
	}

	/** 还款方式 */
	public String getHkWay() {
		return hkWay;
	}

	public void setHkWay(String hkWay) {
		this.hkWay = hkWay;
	}

	/** 借款编码 */
	public String getLoanCode() {
		return loanCode;
	}

	/** 借款编码 */
	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}

	/** 借款申请动态信息 */
	public String getData() {
		return data;
	}

	/** 借款申请动态信息 */
	public void setData(String data) {
		this.data = data;
	}

	/**
	 * 借款申请单状态 0：暂存;01：已提交，待填写共同还款人资料;02：已提交，待上传授信资料 03：授信资料已上传，待信审;30：信审中;state
	 * ：3a：信审拒绝 50：信审通过，待合同制作;55：待签订合同;60 ：待放款 65 ：已放款;80 ：超时冻结;81 ：客户放弃;82
	 * ：拒贷;end ：已完成
	 * */
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

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	/** 备用字段1 */
	public String getBackup01() {
		return backup01;
	}

	/** 备用字段1 */
	public void setBackup01(String backup01) {
		this.backup01 = backup01;
	}

	/** 备用字段2 */
	public String getBackup02() {
		return backup02;
	}

	/** 备用字段2 */
	public void setBackup02(String backup02) {
		this.backup02 = backup02;
	}

	/** 备用字段3 */
	public String getBackup03() {
		return backup03;
	}

	/** 备用字段3 */
	public void setBackup03(String backup03) {
		this.backup03 = backup03;
	}

	/** 备用字段4 */
	public String getBackup04() {
		return backup04;
	}

	/** 备用字段4 */
	public void setBackup04(String backup04) {
		this.backup04 = backup04;
	}

	/** 备用字段5 */
	public String getBackup05() {
		return backup05;
	}

	/** 备用字段5 */
	public void setBackup05(String backup05) {
		this.backup05 = backup05;
	}

	/** 备用字段6 */
	public String getBackup06() {
		return backup06;
	}

	/** 备用字段6 */
	public void setBackup06(String backup06) {
		this.backup06 = backup06;
	}

	/** 备用字段7 */
	public String getBackup07() {
		return backup07;
	}

	/** 备用字段7 */
	public void setBackup07(String backup07) {
		this.backup07 = backup07;
	}

	/** 备用字段8 */
	public String getBackup08() {
		return backup08;
	}

	/** 备用字段8 */
	public void setBackup08(String backup08) {
		this.backup08 = backup08;
	}

	/** 备用字段9 */
	public String getBackup09() {
		return backup09;
	}

	/** 备用字段9 */
	public void setBackup09(String backup09) {
		this.backup09 = backup09;
	}




}

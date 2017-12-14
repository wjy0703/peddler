package cn.com.cucsi.app.entity.xhcf;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import cn.com.cucsi.app.entity.baseinfo.City;
import cn.com.cucsi.app.entity.baseinfo.Organi;
import cn.com.cucsi.app.entity.baseinfo.Xydkzx;
import cn.com.cucsi.core.orm.hibernate.AuditableEntity;

/**
 * XhJksqUPHistory entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
// 表名与类名不相同时重新定义表名.
@Table(name = "XH_JKSQ_UPHISTORY")
// 默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XhJksqUPHistory extends AuditableEntity {

	// Fields
	private static final long serialVersionUID = -2276242824197821671L;
	private XhJksq xhjksq;
	
	// 借款人基础信息
	private String jkrxm; // 借款人姓名
	private String englishName; // 英文名称
	private String genders; // 性别
	private String birthday; // 出生日期
	private String pocertificates; // 证件类型
	private String zjhm; // 证件号码
	private String hjadress; // 户籍地址
	private String homeAddress;// 现住址
	private String homePhone; // 家庭电话
	private String zy;				//职业
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
	private City province; // 省份
	private City city; // 地市
	private City area; // 区县

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

	// 紧急联系人
//	private Set<XhJkjjlxr> xhJkjjlxrs = new HashSet<XhJkjjlxr>();

	// 借款申请信息
	private String jkLoanQuota;// 借款申请额度
	private String jkCycle;// 借款周期
	private java.sql.Date jkLoanDate;// 申请日期
	private String jkUse;// 借款用途
	private String togetherPerson;// 有无共同还款人
	private String bankOpen;// 账户开户行
	private String bankUsername;// 账户名称
	private String bankNum;// 账户号码
	private String jkType;// 借款类型A：老板贷；B：老板楼易贷；C：薪水贷；D：薪水楼易贷；E：精英贷
	private String loanCode;// 借款编号
	private String hkWay;// 还款方式

	private Templet templet; // 动态信息模板
	private String data; // 动态信息数据

	// 其他信息和关联表
	// 0：暂存;01：已提交，待填写共同还款人资料;02：已提交，待上传授信资料
	// 03：授信资料已上传，待信审;30：信审中;3a：信审拒绝
	// 50：信审通过，待合同制作;55：待签订合同;60 ：待放款
	// 65 ：已放款;80 ：超时冻结;81 ：客户放弃;82 ：拒贷;end ：已完成
	private String state;
	private String waitAccMoney; // 待核算金额
	private Organi organi;// 组织

	private Xydkzx xydkzx;// 咨询
	
	private String appState; // 
	private String auditPerson;//审核人
	private String auditIdea;//审核意见
	private Timestamp auditTime;//审核时间
	
	private String backup01;//备用字段1
	private String backup02;//备用字段2
	private String backup03;//备用字段3
	private String backup04;//备用字段4
	private String backup05;//备用字段5
	private String backup06;//备用字段6
	private String backup07;//备用字段7
	private String backup08;//备用字段8
	private String backup09;//备用字段9
	
	public XhJksqUPHistory(){}
	
	public XhJksqUPHistory(XhJksq xhJksq){
		super();
		// 借款人基础信息
		xhjksq = xhJksq;
		jkrxm = xhJksq.getJkrxm(); // 借款人姓名
		englishName = xhJksq.getEnglishName(); // 英文名称
		genders = xhJksq.getGenders(); // 性别
		birthday = xhJksq.getBirthday(); // 出生日期
		pocertificates = xhJksq.getPocertificates(); // 证件类型
		zjhm = xhJksq.getZjhm(); // 证件号码
		hjadress = xhJksq.getHjadress(); // 户籍地址
		homeAddress = xhJksq.getHomeAddress();//现住址
		homePhone = xhJksq.getHomePhone(); // 家庭电话
		zy = xhJksq.getZy();				//职业
		company = xhJksq.getCompany(); // 工作单位
		companyPhone = xhJksq.getCompanyPhone(); // 单位电话
		companyAdress = xhJksq.getCompanyAdress();// 单位地址
		companyNature = xhJksq.getCompanyNature(); // 单位性质
		telephone = xhJksq.getTelephone(); // 移动电话
		email = xhJksq.getEmail(); // 电子邮箱
		maritalStatus = xhJksq.getMaritalStatus(); // 婚姻状况
		ywzn = xhJksq.getYwzn(); // 有无子女
		qqhm = xhJksq.getQqhm(); // QQ号码
		annualIncome = xhJksq.getAnnualIncome(); // 年收入
		incomeIllustration = xhJksq.getIncomeIllustration(); // 收入说明
		liveState = xhJksq.getLiveState(); // 居住状态 01:自有房屋，有无贷款月供*元;02:亲属产权;03:租房，房租*元/月;04:其他
		liveMessage = xhJksq.getLiveMessage(); // 居住说明信息
		txdz = xhJksq.getTxdz(); // 通讯地址
		province = xhJksq.getProvince(); // 省份
		city = xhJksq.getCity(); // 地市
		area = xhJksq.getArea(); // 区县

		fkje = xhJksq.getFkje(); // 放款金额

		// 配偶信息
		spouseName = xhJksq.getSpouseName(); // 配偶姓名
		spouseGenders = xhJksq.getSpouseGenders();// 配偶性别
		spouseBirthday = xhJksq.getSpouseBirthday();// 配偶出生日期
		spouseAdress = xhJksq.getSpouseAdress(); // 配偶现住址
		spousePocertificates = xhJksq.getSpousePocertificates();// 配偶证件类型
		spouseZjhm = xhJksq.getSpouseZjhm(); // 配偶证件号码
		spouseTelephone = xhJksq.getSpouseTelephone();// 配偶移动电话
		spouseHomePhone = xhJksq.getSpouseHomePhone();// 配偶家庭电话
		spouseCompany = xhJksq.getSpouseCompany();// 配偶工作单位
		spouseDepFunction = xhJksq.getSpouseDepFunction();// 配偶部门与职务
		spouseCompanyPhone = xhJksq.getSpouseCompanyPhone();// 配偶单位电话
		spouseCompanyAdress = xhJksq.getSpouseCompanyAdress();// 配偶单位地址
		spouseAnnualIncome = xhJksq.getSpouseAnnualIncome();// 配偶年收入
		spouseWorkinglife = xhJksq.getSpouseWorkinglife();// 配偶工作年限

		// 借款申请信息
		jkLoanQuota = xhJksq.getJkLoanQuota();// 借款申请额度
		jkCycle = xhJksq.getJkCycle();// 借款周期
		jkLoanDate = xhJksq.getJkLoanDate();// 申请日期
		jkUse = xhJksq.getJkUse();// 借款用途
		togetherPerson = xhJksq.getTogetherPerson();// 有无共同还款人
		bankOpen = xhJksq.getBankOpen();// 账户开户行
		bankUsername = xhJksq.getBankUsername();// 账户名称
		bankNum = xhJksq.getBankNum();// 账户号码
		jkType = xhJksq.getJkType();// 借款类型A：老板贷；B：老板楼易贷；C：薪水贷；D：薪水楼易贷；E：精英贷
		loanCode = xhJksq.getLoanCode();// 借款编号
		hkWay = xhJksq.getHkWay();// 还款方式

		templet = xhJksq.getTemplet(); // 动态信息模板
		data = xhJksq.getData(); // 动态信息数据

		// 其他信息和关联表
		// 0：暂存;01：已提交，待填写共同还款人资料;02：已提交，待上传授信资料
		// 03：授信资料已上传，待信审;30：信审中;3a：信审拒绝
		// 50：信审通过，待合同制作;55：待签订合同;60 ：待放款
		// 65 ：已放款;80 ：超时冻结;81 ：客户放弃;82 ：拒贷;end ：已完成
		state = xhJksq.getState();
		waitAccMoney = xhJksq.getWaitAccMoney(); // 待核算金额
		organi = xhJksq.getOrgani();// 组织

		xydkzx = xhJksq.getXydkzx();// 咨询
		
		appState = xhJksq.getAppState(); // 
		auditPerson = xhJksq.getAuditPerson();//审核人
		auditIdea = xhJksq.getAuditIdea();//审核意见
		auditTime = xhJksq.getAuditTime();//审核时间
		
		backup01 = xhJksq.getBackup01();//备用字段1
		backup02 = xhJksq.getBackup02();//备用字段2
		backup03 = xhJksq.getBackup03();//备用字段3
		backup04 = xhJksq.getBackup04();//备用字段4
		backup05 = xhJksq.getBackup05();//备用字段5
		backup06 = xhJksq.getBackup06();//备用字段6
		backup07 = xhJksq.getBackup07();//备用字段7
		backup08 = xhJksq.getBackup08();//备用字段8
		backup09 = xhJksq.getBackup09();//备用字段9
	}
	
	public void setUpHistory(XhJksqUPHistory jksqhistory){
		// 借款人基础信息
		jkrxm = jksqhistory.getJkrxm(); // 借款人姓名
		englishName = jksqhistory.getEnglishName(); // 英文名称
		genders = jksqhistory.getGenders(); // 性别
		birthday = jksqhistory.getBirthday(); // 出生日期
		pocertificates = jksqhistory.getPocertificates(); // 证件类型
		zjhm = jksqhistory.getZjhm(); // 证件号码
		hjadress = jksqhistory.getHjadress(); // 户籍地址
		homeAddress = jksqhistory.getHomeAddress();//现住址
		homePhone = jksqhistory.getHomePhone(); // 家庭电话
		company = jksqhistory.getCompany(); // 工作单位
		companyPhone = jksqhistory.getCompanyPhone(); // 单位电话
		companyAdress = jksqhistory.getCompanyAdress();// 单位地址
		companyNature = jksqhistory.getCompanyNature(); // 单位性质
		telephone = jksqhistory.getTelephone(); // 移动电话
		email = jksqhistory.getEmail(); // 电子邮箱
		maritalStatus = jksqhistory.getMaritalStatus(); // 婚姻状况
		ywzn = jksqhistory.getYwzn(); // 有无子女
		qqhm = jksqhistory.getQqhm(); // QQ号码
		annualIncome = jksqhistory.getAnnualIncome(); // 年收入
		incomeIllustration = jksqhistory.getIncomeIllustration(); // 收入说明
		liveState = jksqhistory.getLiveState(); // 居住状态 01:自有房屋，有无贷款月供*元;02:亲属产权;03:租房，房租*元/月;04:其他
		liveMessage = jksqhistory.getLiveMessage(); // 居住说明信息
		txdz = jksqhistory.getTxdz(); // 通讯地址
		province = jksqhistory.getProvince(); // 省份
		city = jksqhistory.getCity(); // 地市
		area = jksqhistory.getArea(); // 区县

		// 配偶信息
		spouseName = jksqhistory.getSpouseName(); // 配偶姓名
		spouseGenders = jksqhistory.getSpouseGenders();// 配偶性别
		spouseBirthday = jksqhistory.getSpouseBirthday();// 配偶出生日期
		spouseAdress = jksqhistory.getSpouseAdress(); // 配偶现住址
		spousePocertificates = jksqhistory.getSpousePocertificates();// 配偶证件类型
		spouseZjhm = jksqhistory.getSpouseZjhm(); // 配偶证件号码
		spouseTelephone = jksqhistory.getSpouseTelephone();// 配偶移动电话
		spouseHomePhone = jksqhistory.getSpouseHomePhone();// 配偶家庭电话
		spouseCompany = jksqhistory.getSpouseCompany();// 配偶工作单位
		spouseDepFunction = jksqhistory.getSpouseDepFunction();// 配偶部门与职务
		spouseCompanyPhone = jksqhistory.getSpouseCompanyPhone();// 配偶单位电话
		spouseCompanyAdress = jksqhistory.getSpouseCompanyAdress();// 配偶单位地址
		spouseAnnualIncome = jksqhistory.getSpouseAnnualIncome();// 配偶年收入
		spouseWorkinglife = jksqhistory.getSpouseWorkinglife();// 配偶工作年限

		// 借款申请信息
		jkLoanQuota = jksqhistory.getJkLoanQuota();// 借款申请额度
		jkCycle = jksqhistory.getJkCycle();// 借款周期
		jkLoanDate = jksqhistory.getJkLoanDate();// 申请日期
		jkUse = jksqhistory.getJkUse();// 借款用途
		togetherPerson = jksqhistory.getTogetherPerson();// 有无共同还款人
		bankOpen = jksqhistory.getBankOpen();// 账户开户行
		bankUsername = jksqhistory.getBankUsername();// 账户名称
		bankNum = jksqhistory.getBankNum();// 账户号码
		jkType = jksqhistory.getJkType();// 借款类型A：老板贷；B：老板楼易贷；C：薪水贷；D：薪水楼易贷；E：精英贷
		loanCode = jksqhistory.getLoanCode();// 借款编号
		hkWay = jksqhistory.getHkWay();// 还款方式

		templet = jksqhistory.getTemplet(); // 动态信息模板
		data = jksqhistory.getData(); // 动态信息数据

		appState = jksqhistory.getAppState(); // 
		auditPerson = jksqhistory.getAuditPerson();//审核人
		auditIdea = jksqhistory.getAuditIdea();//审核意见
		auditTime = jksqhistory.getAuditTime();//审核时间
		
		backup01 = jksqhistory.getBackup01();//备用字段1
		backup02 = jksqhistory.getBackup02();//备用字段2
		backup03 = jksqhistory.getBackup03();//备用字段3
		backup04 = jksqhistory.getBackup04();//备用字段4
		backup05 = jksqhistory.getBackup05();//备用字段5
		backup06 = jksqhistory.getBackup06();//备用字段6
		backup07 = jksqhistory.getBackup07();//备用字段7
		backup08 = jksqhistory.getBackup08();//备用字段8
		backup09 = jksqhistory.getBackup09();//备用字段9
	}
	
	/**借款申请单信息*/
	@OneToOne(cascade=CascadeType.REFRESH,fetch = FetchType.LAZY)
	@JoinColumn(name="XHJKSQ_ID", unique= false, nullable=true, insertable=true, updatable=false)
	public XhJksq getXhjksq() {
		return xhjksq;
	}

	/**借款申请单信息*/
	public void setXhjksq(XhJksq xhjksq) {
		this.xhjksq = xhjksq;
	}

	/** 借款人姓名 */
	@Column(columnDefinition = DEF_STR32)
	public String getJkrxm() {
		return this.jkrxm;
	}

	/** 借款人姓名 */
	public void setJkrxm(String jkrxm) {
		this.jkrxm = jkrxm;
	}

	/** 英文名称 */
	@Column(columnDefinition = DEF_STR32)
	public String getEnglishName() {
		return englishName;
	}

	/** 英文名称 */
	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	/** 性别 */
	@Column(columnDefinition = DEF_STR8)
	public String getGenders() {
		return genders;
	}

	/** 性别 */
	public void setGenders(String genders) {
		this.genders = genders;
	}

	/** 出生日期 */
	@Column(columnDefinition = DEF_STR32)
	public String getBirthday() {
		return birthday;
	}

	/** 出生日期 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	/** 证件类型 */
	@Column(columnDefinition = DEF_STR32)
	public String getPocertificates() {
		return pocertificates;
	}

	/** 证件类型 */
	public void setPocertificates(String pocertificates) {
		this.pocertificates = pocertificates;
	}

	/** 证件号码 */
	@Column(columnDefinition = DEF_STR32)
	public String getZjhm() {
		return zjhm;
	}

	/** 证件号码 */
	public void setZjhm(String zjhm) {
		this.zjhm = zjhm;
	}

	/** 户籍地址 */
	@Column(columnDefinition = DEF_STR512)
	public String getHjadress() {
		return hjadress;
	}

	/** 户籍地址 */
	public void setHjadress(String hjadress) {
		this.hjadress = hjadress;
	}

	/** 现住址 */
	@Column(columnDefinition = DEF_STR512)
	public String getHomeAddress() {
		return homeAddress;
	}

	/** 现住址 */
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
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

	/** 职业 */
	@Column(columnDefinition = DEF_STR512)
	public String getZy() {
		return zy;
	}

	/** 职业 */
	public void setZy(String zy) {
		this.zy = zy;
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

	/** 电子邮箱 */
	@Column(columnDefinition = DEF_STR64)
	public String getEmail() {
		return email;
	}

	/** 电子邮箱 */
	public void setEmail(String email) {
		this.email = email;
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

	/** QQ号码 */
	@Column(columnDefinition = DEF_STR32)
	public String getQqhm() {
		return qqhm;
	}

	/** QQ号码 */
	public void setQqhm(String qqhm) {
		this.qqhm = qqhm;
	}

	/** 年收入 */
	@Column(columnDefinition = DEF_NUM10_2)
	public String getAnnualIncome() {
		return annualIncome;
	}

	/** 年收入 */
	public void setAnnualIncome(String annualIncome) {
		this.annualIncome = annualIncome;
	}

	/** 收入说明 */
	@Column(columnDefinition = DEF_STR4000)
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
	@Column(columnDefinition = DEF_STR8)
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
	@Column(columnDefinition = DEF_STR1024)
	public String getLiveMessage() {
		return liveMessage;
	}

	/** 居住说明信息 */
	public void setLiveMessage(String liveMessage) {
		this.liveMessage = liveMessage;
	}

	/** 通讯地址 */
	@Column(columnDefinition = DEF_STR512)
	public String getTxdz() {
		return txdz;
	}

	/** 通讯地址 */
	public void setTxdz(String txdz) {
		this.txdz = txdz;
	}

//	/** 省份 */
//	@Column(columnDefinition = DEF_STR16)
//	public String getProvince() {
//		return province;
//	}
//
//	/** 省份 */
//	public void setProvince(String province) {
//		this.province = province;
//	}
//
//	/** 地市 */
//	@Column(columnDefinition = DEF_STR16)
//	public String getCity() {
//		return city;
//	}
//
//	/** 地市 */
//	public void setCity(String city) {
//		this.city = city;
//	}
//
//	/** 区县 */
//	@Column(columnDefinition = DEF_STR16)
//	public String getArea() {
//		return area;
//	}
//
//	/** 区县 */
//	public void setArea(String area) {
//		this.area = area;
//	}
	
	/** 省份 */
	@OneToOne(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
	@JoinColumn(name = "province", unique = false, nullable = true, insertable = true, updatable = true)
	public City getProvince() {
		return province;
	}
	
	/** 省份 */
	public void setProvince(City province) {
		this.province = province;
	}
	
	/** 地市 */
	@OneToOne(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
	@JoinColumn(name = "city", unique = false, nullable = true, insertable = true, updatable = true)
	public City getCity() {
		return city;
	}
	
	/** 地市 */
	public void setCity(City city) {
		this.city = city;
	}
	
	/** 区县 */
	@OneToOne(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
	@JoinColumn(name = "area", unique = false, nullable = true, insertable = true, updatable = true)
	public City getArea() {
		return area;
	}
	
	/** 区县 */
	public void setArea(City area) {
		this.area = area;
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

	/** 配偶出生日期 */
	@Column(columnDefinition = DEF_STR32)
	public String getSpouseBirthday() {
		return spouseBirthday;
	}

	/** 配偶出生日期 */
	public void setSpouseBirthday(String spouseBirthday) {
		this.spouseBirthday = spouseBirthday;
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

	/** 配偶证件类型 */
	@Column(columnDefinition = DEF_STR32)
	public String getSpousePocertificates() {
		return spousePocertificates;
	}

	/** 配偶证件类型 */
	public void setSpousePocertificates(String spousePocertificates) {
		this.spousePocertificates = spousePocertificates;
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

	/** 借款申请额度 */
	public void setFkje(String fkje) {
		this.fkje = fkje;
	}

	/** 借款申请额度 */
	@Column(columnDefinition = DEF_NUM10_2)
	public String getJkLoanQuota() {
		return this.jkLoanQuota;
	}

	/** 借款申请额度 */
	public void setJkLoanQuota(String jkLoanQuota) {
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

	/** 有无共同还款人 */
	@Column(columnDefinition = DEF_STR50)
	public String getTogetherPerson() {
		return this.togetherPerson;
	}

	/** 有无共同还款人 */
	public void setTogetherPerson(String togetherPerson) {
		this.togetherPerson = togetherPerson;
	}

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

	/** 借款类型A：老板贷；B：老板楼易贷；C：薪水贷；D：薪水楼易贷；E：精英贷 */
	@Column(columnDefinition = DEF_STR3)
	public String getJkType() {
		return this.jkType;
	}

	/** 借款类型A：老板贷；B：老板楼易贷；C：薪水贷；D：薪水楼易贷；E：精英贷 */
	public void setJkType(String jkType) {
		this.jkType = jkType;
	}

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

	// /**信审审核状态*/
	// @Column(columnDefinition=DEF_STR8)
	// public String getExamState() {
	// return this.examState;
	// }
	//
	// /**信审审核状态*/
	// public void setExamState(String examState) {
	// this.examState = examState;
	// }
	//
	// /**信审审核结束状态*/
	// @Column(columnDefinition=DEF_STR8)
	// public String getExamEndState() {
	// return this.examEndState;
	// }
	//
	// /**信审审核结束状态*/
	// public void setExamEndState(String examEndState) {
	// this.examEndState = examEndState;
	// }

	// /**制作合同和签订合同审核状态*/
	// @Column(columnDefinition=DEF_STR8)
	// public String getJkhtexamState() {
	// return jkhtexamState;
	// }
	//
	// /**制作合同和签订合同审核状态*/
	// public void setJkhtexamState(String jkhtexamState) {
	// this.jkhtexamState = jkhtexamState;
	// }
	//
	// /**制作合同和签订合同审核结束状态*/
	// @Column(columnDefinition=DEF_STR8)
	// public String getJkhtexamEndState() {
	// return jkhtexamEndState;
	// }
	//
	// /**制作合同和签订合同审核结束状态*/
	// public void setJkhtexamEndState(String jkhtexamEndState) {
	// this.jkhtexamEndState = jkhtexamEndState;
	// }

	/** 动态信息模板 */
	@OneToOne(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
	@JoinColumn(name = "TEMPLET_ID", unique = false, nullable = true, insertable = true, updatable = true)
	public Templet getTemplet() {
		return templet;
	}

	/** 动态信息模板 */
	public void setTemplet(Templet templet) {
		this.templet = templet;
	}

	/** 借款申请动态信息 */
	@Column(columnDefinition = DEF_STR4000)
	public String getData() {
		return data;
	}

	/** 借款申请动态信息 */
	public void setData(String data) {
		this.data = data;
	}

	/** 组织 */
	@OneToOne(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
	@JoinColumn(name = "organi_id", unique = false, nullable = true, insertable = true, updatable = true)
	public Organi getOrgani() {
		return organi;
	}

	/** 组织 */
	public void setOrgani(Organi organi) {
		this.organi = organi;
	}

	/**
	 * 借款申请单状态 参考表BASE_ATTR
	 * */
	@Column(columnDefinition = DEF_STR8)
	public String getState() {
		return state;
	}

	/**
	 * 借款申请单状态 参考表BASE_ATTR
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
	@OneToOne(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
	@JoinColumn(name = "XYDKZX_ID", unique = false, nullable = true, insertable = true, updatable = true)
	public Xydkzx getXydkzx() {
		return xydkzx;
	}

	/** 借款咨询信息 */
	public void setXydkzx(Xydkzx xydkzx) {
		this.xydkzx = xydkzx;
	}

	/**  */
	@Column(columnDefinition = DEF_STR8)
	public String getAppState() {
		return appState;
	}

	/**  */
	public void setAppState(String appState) {
		this.appState = appState;
	}
	
	/** 审核人*/
	@Column(columnDefinition=DEF_STR32)
	public String getAuditPerson() {
		return auditPerson;
	}

	/** 审核人*/
	public void setAuditPerson(String auditPerson) {
		this.auditPerson = auditPerson;
	}

	/** 审核意见*/
	@Column(columnDefinition=DEF_STR512)
	public String getAuditIdea() {
		return auditIdea;
	}

	/** 审核意见*/
	public void setAuditIdea(String auditIdea) {
		this.auditIdea = auditIdea;
	}

	/** 审核时间*/
	@Column(insertable = false)
	public Timestamp getAuditTime() {
		return auditTime;
	}

	/** 审核时间*/
	public void setAuditTime(Timestamp auditTime) {
		this.auditTime = auditTime;
	}
	
	/**备用字段1 */
	@Column(columnDefinition = DEF_STR32)
	public String getBackup01() {
		return backup01;
	}

	/**备用字段1 */
	public void setBackup01(String backup01) {
		this.backup01 = backup01;
	}

	/**备用字段2 */
	@Column(columnDefinition = DEF_STR32)
	public String getBackup02() {
		return backup02;
	}

	/**备用字段2 */
	public void setBackup02(String backup02) {
		this.backup02 = backup02;
	}

	/**备用字段3 */
	@Column(columnDefinition = DEF_STR32)
	public String getBackup03() {
		return backup03;
	}

	/**备用字段3 */
	public void setBackup03(String backup03) {
		this.backup03 = backup03;
	}

	/**备用字段4 */
	@Column(columnDefinition = DEF_STR32)
	public String getBackup04() {
		return backup04;
	}

	/**备用字段4 */
	public void setBackup04(String backup04) {
		this.backup04 = backup04;
	}

	/**备用字段5 */
	@Column(columnDefinition = DEF_STR32)
	public String getBackup05() {
		return backup05;
	}

	/**备用字段5 */
	public void setBackup05(String backup05) {
		this.backup05 = backup05;
	}

	/**备用字段6 */
	@Column(columnDefinition = DEF_STR128)
	public String getBackup06() {
		return backup06;
	}

	/**备用字段6 */
	public void setBackup06(String backup06) {
		this.backup06 = backup06;
	}

	/**备用字段7 */
	@Column(columnDefinition = DEF_STR256)
	public String getBackup07() {
		return backup07;
	}

	/**备用字段7 */
	public void setBackup07(String backup07) {
		this.backup07 = backup07;
	}

	/**备用字段8 */
	@Column(columnDefinition = DEF_STR512)
	public String getBackup08() {
		return backup08;
	}

	/**备用字段8 */
	public void setBackup08(String backup08) {
		this.backup08 = backup08;
	}

	/**备用字段9 */
	@Column(columnDefinition = DEF_STR1024)
	public String getBackup09() {
		return backup09;
	}

	/**备用字段9 */
	public void setBackup09(String backup09) {
		this.backup09 = backup09;
	}	
	// /**借款合同信息*/
	// @OneToOne(cascade=CascadeType.REFRESH)
	// @JoinColumn(name="XHJKHT_ID", unique= false, nullable=true,
	// insertable=true, updatable=true)
	// public XhJkht getXhjkht() {
	// return xhjkht;
	// }
	//
	// /**借款合同信息*/
	// public void setXhjkht(XhJkht xhjkht) {
	// this.xhjkht = xhjkht;
	// }

	// /**借款申请贷款类型动态信息*/
	// @OneToOne(cascade=CascadeType.REFRESH)
	// @JoinColumn(name="XhJKSQTYPEMSG_ID", unique= false, nullable=true,
	// insertable=true, updatable=true)
	// public XhJksqtypemsg getXhjksqtypemsg() {
	// return xhjksqtypemsg;
	// }
	//
	// /**借款申请贷款类型动态信息*/
	// public void setXhjksqtypemsg(XhJksqtypemsg xhjksqtypemsg) {
	// this.xhjksqtypemsg = xhjksqtypemsg;
	// }

}

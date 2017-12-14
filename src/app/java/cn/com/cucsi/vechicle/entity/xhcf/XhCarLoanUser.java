package cn.com.cucsi.vechicle.entity.xhcf;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
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

import cn.com.cucsi.app.entity.baseinfo.Employee;
import cn.com.cucsi.app.entity.baseinfo.Organi;
import cn.com.cucsi.app.entity.baseinfo.Xydkzx;
import cn.com.cucsi.core.orm.hibernate.AuditableEntity;

/**
 * XhCarLoanUser entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
// 表名与类名不相同时重新定义表名.
@Table(name = "XH_CAR_LOAN_USER")
// 默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XhCarLoanUser extends AuditableEntity {

    // Fields
    private static final long serialVersionUID = -2276242824197821671L;
    private String userName;// 借款人姓名

    /** 借款人姓名 */
    @Column(columnDefinition = DEF_STR100)
    public String getUserName() {
        return this.userName;
    }

    /** 借款人姓名 */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    private String genders;// 性别

    /** 性别 */
    @Column(columnDefinition = DEF_STR8)
    public String getGenders() {
        return this.genders;
    }

    /** 性别 */
    public void setGenders(String genders) {
        this.genders = genders;
    }

    private String birthday;// 出生日期

    /** 出生日期 */
    @Column(columnDefinition = DEF_STR32)
    public String getBirthday() {
        return this.birthday;
    }

    /** 出生日期 */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    private String telephone;// 移动电话

    /** 移动电话 */
    @Column(columnDefinition = DEF_STR32)
    public String getTelephone() {
        return this.telephone;
    }

    /** 移动电话 */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    private String cardNumber;// 证件号码

    /** 证件号码 */
    @Column(columnDefinition = DEF_STR100)
    public String getCardNumber() {
        return this.cardNumber;
    }

    /** 证件号码 */
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    private String pocertificates;// 证件类型

    /** 证件类型 */
    @Column(columnDefinition = DEF_STR32)
    public String getPocertificates() {
        return this.pocertificates;
    }

    /** 证件类型 */
    public void setPocertificates(String pocertificates) {
        this.pocertificates = pocertificates;
    }

    private String expiredDate;// 证件有效期

    /** 证件有效期 */
    @Column(columnDefinition = DEF_STR20)
    public String getExpiredDate() {
        return this.expiredDate;
    }

    /** 证件有效期 */
    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }

    private String isNotExpired;// 是否长期有效

    /** 是否长期有效 */
    @Column(columnDefinition = DEF_STR10)
    public String getIsNotExpired() {
        return this.isNotExpired;
    }

    /** 是否长期有效 */
    public void setIsNotExpired(String isNotExpired) {
        this.isNotExpired = isNotExpired;
    }

    private String hjadress;// 户籍地址

    /** 户籍地址 */
    @Column(columnDefinition = DEF_STR512)
    public String getHjadress() {
        return this.hjadress;
    }

    /** 户籍地址 */
    public void setHjadress(String hjadress) {
        this.hjadress = hjadress;
    }

    private String temporaryCrede;// 暂住证（有，无）

    /** 暂住证（有，无） */
    @Column(columnDefinition = DEF_STR20)
    public String getTemporaryCrede() {
        return this.temporaryCrede;
    }

    /** 暂住证（有，无） */
    public void setTemporaryCrede(String temporaryCrede) {
        this.temporaryCrede = temporaryCrede;
    }

    private String homeAddress;// 本市地址（现住址)

    /** 本市地址（现住址) */
    @Column(columnDefinition = DEF_STR512)
    public String getHomeAddress() {
        return this.homeAddress;
    }

    /** 本市地址（现住址) */
    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    private String homePhone;// 本市住宅电话(家庭电话)

    /** 本市住宅电话(家庭电话) */
    @Column(columnDefinition = DEF_STR32)
    public String getHomePhone() {
        return this.homePhone;
    }

    /** 本市住宅电话(家庭电话) */
    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    private String firstLiveDateStart;// 起始居住时间

    /** 起始居住时间 */
    @Column(columnDefinition = DEF_STR20)
    public String getFirstLiveDateStart() {
        return this.firstLiveDateStart;
    }

    /** 起始居住时间 */
    public void setFirstLiveDateStart(String firstLiveDateStart) {
        this.firstLiveDateStart = firstLiveDateStart;
    }

    private String firstComeYear;// 初来本市年份

    /** 初来本市年份 */
    @Column(columnDefinition = DEF_STR20)
    public String getFirstComeYear() {
        return this.firstComeYear;
    }

    /** 初来本市年份 */
    public void setFirstComeYear(String firstComeYear) {
        this.firstComeYear = firstComeYear;
    }

    private String dependentRelatives;// 供养亲属人数

    /** 供养亲属人数 */
    @Column(columnDefinition = DEF_STR20)
    public String getDependentRelatives() {
        return this.dependentRelatives;
    }

    /** 供养亲属人数 */
    public void setDependentRelatives(String dependentRelatives) {
        this.dependentRelatives = dependentRelatives;
    }

    private String maritalStatus;// 婚姻状况

    /** 婚姻状况 */
    @Column(columnDefinition = DEF_STR32)
    public String getMaritalStatus() {
        return this.maritalStatus;
    }

    /** 婚姻状况 */
    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    private String hasChildren;// 有无子女

    /** 有无子女 */
    @Column(columnDefinition = DEF_STR8)
    public String getHasChildren() {
        return this.hasChildren;
    }

    /** 有无子女 */
    public void setHasChildren(String hasChildren) {
        this.hasChildren = hasChildren;
    }

    private String educationType;// 教育程度

    /** 教育程度 */
    @Column(columnDefinition = DEF_STR32)
    public String getEducationType() {
        return this.educationType;
    }

    /** 教育程度 */
    public void setEducationType(String educationType) {
        this.educationType = educationType;
    }

    private Double creditLimit;// 信用卡额度（万元）

    /** 信用卡额度（万元） */
    @Column(columnDefinition = DEF_NUM18_4)
    public Double getCreditLimit() {
        return this.creditLimit;
    }

    /** 信用卡额度（万元） */
    public void setCreditLimit(Double creditLimit) {
        this.creditLimit = creditLimit;
    }

    private String liveState;// 房产

    /** 房产 */
    @Column(columnDefinition = DEF_STR8)
    public String getLiveState() {
        return this.liveState;
    }

    /** 房产 */
    public void setLiveState(String liveState) {
        this.liveState = liveState;
    }

    private Double liveMonthRent;// 月租金

    /** 月租金 */
    @Column(columnDefinition = DEF_NUM10_2)
    public Double getLiveMonthRent() {
        return this.liveMonthRent;
    }

    /** 月租金 */
    public void setLiveMonthRent(Double liveMonthRent) {
        this.liveMonthRent = liveMonthRent;
    }

    private String businessType;// 企业类型

    /** 企业类型 */
    @Column(columnDefinition = DEF_STR20)
    public String getBusinessType() {
        return this.businessType;
    }

    /** 企业类型 */
    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    private String establishedDate;// 成立时间

    /** 成立时间 */
    @Column(columnDefinition = DEF_STR20)
    public String getEstablishedDate() {
        return this.establishedDate;
    }

    /** 成立时间 */
    public void setEstablishedDate(String establishedDate) {
        this.establishedDate = establishedDate;
    }
    private Long crmarea;// 管辖区域(做查询依据)

    /** 管辖城市(做查询依据) */
    @Column(columnDefinition = DEF_NUM18)
    public Long getCrmarea() {
        return this.crmarea;
    }

    /** 管辖城市(做查询依据) */
    public void setCrmarea(Long crmarea) {
        this.crmarea = crmarea;
    }
    private Long crmcity;// 管辖城市(不做查询依据)

    /** 管辖城市(不做查询依据) */
    @Column(columnDefinition = DEF_NUM18)
    public Long getCrmcity() {
        return this.crmcity;
    }

    /** 管辖城市(不做查询依据) */
    public void setCrmcity(Long crmcity) {
        this.crmcity = crmcity;
    }

    private Long crmprovince;// 管辖省份(不做查询依据)

    /** 管辖省份(不做查询依据) */
    @Column(columnDefinition = DEF_NUM18)
    public Long getCrmprovince() {
        return this.crmprovince;
    }

    /** 管辖省份(不做查询依据) */
    public void setCrmprovince(Long crmprovince) {
        this.crmprovince = crmprovince;
    }

    private Long orgnId;// 销售团队(不做查询依据)

    /** 销售团队(不做查询依据) */
    @Column(columnDefinition = DEF_NUM18)
    public Long getOrgnId() {
        return this.orgnId;
    }

    /** 销售团队(不做查询依据) */
    public void setOrgnId(Long orgnId) {
        this.orgnId = orgnId;
    }
    
    /*
     * 开发团队
     */
    private String kftd;
    @Column(columnDefinition=DEF_STR50)
	public String getKftd() {
		return this.kftd;
	}

	public void setKftd(String kftd) {
		this.kftd = kftd;
	}

  /*  private Long teamLeaderId;// 团队经理(不做查询依据)

    // ** 团队经理(不做查询依据) *//*

    @Column(columnDefinition = DEF_NUM18)
    public Long getTeamLeaderId() {
        return this.teamLeaderId;
    }

    // ** 团队经理(不做查询依据) *//*

    public void setTeamLeaderId(Long teamLeaderId) {
        this.teamLeaderId = teamLeaderId;
    }

    private Long customerLeaderId;// 客户经理(不做查询依据)

    // ** 客户经理(不做查询依据) *//*

    @Column(columnDefinition = DEF_NUM18)
    public Long getCustomerLeaderId() {
        return this.customerLeaderId;
    }

    // ** 客户经理(不做查询依据) *//*

    public void setCustomerLeaderId(Long customerLeaderId) {
        this.customerLeaderId = customerLeaderId;
    }*/

    // private Long employeeServiceStaff;//客服(不做查询依据)
    // /** 客服(不做查询依据) */
    // @Column(columnDefinition=DEF_NUM18)
    // public Long getEmployeeServiceStaff()
    // { return this.employeeServiceStaff; }
    // /** 客服(不做查询依据) */
    // public void setEmployeeServiceStaff(Long employeeServiceStaff)
    // {
    // this.employeeServiceStaff = employeeServiceStaff; }

    private String knownWay;// 客户来源

    /** 客户来源 */
    @Column(columnDefinition = DEF_STR20)
    public String getKnownWay() {
        return this.knownWay;
    }

    /** 客户来源 */
    public void setKnownWay(String knownWay) {
        this.knownWay = knownWay;
    }

    private String personLawCase;// 客户人法情况

    /** 客户人法情况 */
    @Column(columnDefinition = DEF_STR512)
    public String getPersonLawCase() {
        return this.personLawCase;
    }

    /** 客户人法情况 */
    public void setPersonLawCase(String personLawCase) {
        this.personLawCase = personLawCase;
    }

    private Long area;// 区县

    /** 区县 */
    @Column(columnDefinition = DEF_NUM18)
    public Long getArea() {
        return this.area;
    }

    /** 区县 */
    public void setArea(Long area) {
        this.area = area;
    }

    private Long city;// 地市

    /** 地市 */
    @Column(columnDefinition = DEF_NUM18)
    public Long getCity() {
        return this.city;
    }

    /** 地市 */
    public void setCity(Long city) {
        this.city = city;
    }

    private Long province;// 省份

    /** 省份 */
    @Column(columnDefinition = DEF_NUM18)
    public Long getProvince() {
        return this.province;
    }

    /** 省份 */
    public void setProvince(Long province) {
        this.province = province;
    }

    private String email;// 电子邮箱

    /** 电子邮箱 */
    @Column(columnDefinition = DEF_STR64)
    public String getEmail() {
        return this.email;
    }

    /** 电子邮箱 */
    public void setEmail(String email) {
        this.email = email;
    }

    private String englishName;// 英文名称

    /** 英文名称 */
    @Column(columnDefinition = DEF_STR32)
    public String getEnglishName() {
        return this.englishName;
    }

    /** 英文名称 */
    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    private String backup01;// 备用字段01

    /** 备用字段01 */
    @Column(columnDefinition = DEF_STR32)
    public String getBackup01() {
        return this.backup01;
    }

    /** 备用字段01 */
    public void setBackup01(String backup01) {
        this.backup01 = backup01;
    }

    private String backup02;// 备用字段02

    /** 备用字段02 */
    @Column(columnDefinition = DEF_STR128)
    public String getBackup02() {
        return this.backup02;
    }

    /** 备用字段02 */
    public void setBackup02(String backup02) {
        this.backup02 = backup02;
    }

    private String backup03;// 备用字段03

    /** 备用字段03 */
    @Column(columnDefinition = DEF_STR32)
    public String getBackup03() {
        return this.backup03;
    }

    /** 备用字段03 */
    public void setBackup03(String backup03) {
        this.backup03 = backup03;
    }

    private String backup04;// 备用字段04

    /** 备用字段04 */
    @Column(columnDefinition = DEF_STR32)
    public String getBackup04() {
        return this.backup04;
    }

    /** 备用字段04 */
    public void setBackup04(String backup04) {
        this.backup04 = backup04;
    }

    private String backup05;// 备用字段05

    /** 备用字段05 */
    @Column(columnDefinition = DEF_STR32)
    public String getBackup05() {
        return this.backup05;
    }

    /** 备用字段05 */
    public void setBackup05(String backup05) {
        this.backup05 = backup05;
    }

    private List<XhCarUserOffice> xhCarUserOffice = new ArrayList<XhCarUserOffice>();

    /**
     * 工作单位
     */
    // 一对多定义
    @OneToMany(targetEntity = XhCarUserOffice.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "XHCARLOANUSER_ID", updatable = false)
    public List<XhCarUserOffice> getXhCarUserOffice() {
        return xhCarUserOffice;
    }

    public void setXhCarUserOffice(List<XhCarUserOffice> xhCarUserOffice) {
        this.xhCarUserOffice = xhCarUserOffice;
    }

    // 紧急联系人
    private List<XhCarLxr> xhCarLxr = new ArrayList<XhCarLxr>();
    // 一对多定义
    @OneToMany(targetEntity = XhCarLxr.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "XHCARLOANUSER_ID", updatable = false)
    // 集合中对象id的缓存.
    public List<XhCarLxr> getXhCarLxr() {
        return xhCarLxr;
    }

    public void setXhCarLxr(List<XhCarLxr> xhCarLxr) {
        this.xhCarLxr = xhCarLxr;
    }

    private Employee employeeService;

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_SERVICE_STAFF", unique = false, nullable = true, insertable = true, updatable = true)
    public Employee getEmployeeService() {
        return employeeService;
    }

    public void setEmployeeService(Employee employeeService) {
        this.employeeService = employeeService;
    }
    //客户状态。0.咨询，1.借款人
    private String userState;
    /**客户状态。0.咨询，1.借款人*/
    @Column(columnDefinition = DEF_STR2)
	public String getUserState() {
		return userState;
	}
	/**客户状态。0.咨询，1.借款人*/
	public void setUserState(String userState) {
		this.userState = userState;
	}
	
	/**状态 暂时未用*/
    private String state;
    /**状态*/
    @Column(columnDefinition = DEF_STR8)
	public String getState() {
		return state;
	}
	/**状态*/
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * 人员的车辆信息
	 */
	private List<XhCarMessage> xhCarMessage = new ArrayList<XhCarMessage>();

	// 一对多定义
    @OneToMany(targetEntity = XhCarMessage.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "CAR_USER_ID", updatable = false)
    // 集合中对象id的缓存.
    public List<XhCarMessage> getXhCarMessage() {
        return xhCarMessage;
    }

    
    public void setXhCarMessage(List<XhCarMessage> xhCarMessage) {
        this.xhCarMessage = xhCarMessage;
    }
    
    private Organi organi;// 组织

    //一对一定义
  	@OneToOne(cascade=CascadeType.REFRESH,fetch = FetchType.LAZY)
  	@JoinColumn(name="organi_id", unique= false, nullable=true, insertable=true, updatable=true)
  	public Organi getOrgani() {
  		return organi;
  	}

  	public void setOrgani(Organi organi) {
  		this.organi = organi;
  	}
  	
  	private Employee employeeCrm;
  //一对一定义
  	@OneToOne(cascade=CascadeType.REFRESH,fetch = FetchType.LAZY)
  	@JoinColumn(name="CUSTOMER_LEADER_ID", unique= false, nullable=true, insertable=true, updatable=true)
  	public Employee getEmployeeCrm() {
  		return employeeCrm;
  	}

  	public void setEmployeeCrm(Employee employeeCrm) {
  		this.employeeCrm = employeeCrm;
  	}

  	//一对一定义
  	private Employee employeeCca;
  	@OneToOne(cascade=CascadeType.REFRESH,fetch = FetchType.LAZY)
  	@JoinColumn(name="TEAM_LEADER_ID", unique= false, nullable=true, insertable=true, updatable=true)
  	public Employee getEmployeeCca() {
  		return employeeCca;
  	}

  	public void setEmployeeCca(Employee employeeCca) {
  		this.employeeCca = employeeCca;
  	}
	
}

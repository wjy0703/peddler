package cn.com.cucsi.vechicle.entity.xhcf;

import java.util.ArrayList;
import java.util.List;

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
import org.hibernate.annotations.OrderBy;

import cn.com.cucsi.app.entity.baseinfo.Employee;
import cn.com.cucsi.app.entity.baseinfo.Organi;
import cn.com.cucsi.core.orm.hibernate.AuditableEntity;
import cn.com.cucsi.vechicle.util.CarState;

/**
 * XhCarLoanApply entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
// 表名与类名不相同时重新定义表名.
@Table(name = "XH_CAR_LOAN_APPLY")
// 默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XhCarLoanApply extends AuditableEntity {

    // Fields
    private static final long serialVersionUID = -2276242824197821671L;
    private Double jkLoanQuota;// 借款申请额度

    /** 借款申请额度 */
    @Column(columnDefinition = DEF_NUM15_5)
    public Double getJkLoanQuota() {
        return this.jkLoanQuota;
    }

    /** 借款申请额度 */
    public void setJkLoanQuota(Double jkLoanQuota) {
        this.jkLoanQuota = jkLoanQuota;
    }

    private Double loanScale;// 借款成数（借款总额/车辆评估金额）

    /** 借款成数（借款总额/车辆评估金额） */
    @Column(columnDefinition = DEF_NUM10_2)
    public Double getLoanScale() {
        return this.loanScale;
    }

    /** 借款成数（借款总额/车辆评估金额） */
    public void setLoanScale(Double loanScale) {
        this.loanScale = loanScale;
    }

    private Double comlpexMoney;// 综合息费（IF(借款总额*总费率<1000,1000-利息,借款总额*总费率-利息)）

    /** 综合息费（IF(借款总额*总费率<1000,1000-利息,借款总额*总费率-利息)） */
    @Column(columnDefinition = DEF_NUM15_5)
    public Double getComlpexMoney() {
        return this.comlpexMoney;
    }

    /** 综合息费（IF(借款总额*总费率<1000,1000-利息,借款总额*总费率-利息)） */
    public void setComlpexMoney(Double comlpexMoney) {
        this.comlpexMoney = comlpexMoney;
    }

    private Long jkCycle;// 借款周期

    /** 借款周期 */
    @Column(columnDefinition = DEF_NUM4)
    public Long getJkCycle() {
        return this.jkCycle;
    }

    /** 借款周期 */
    public void setJkCycle(Long jkCycle) {
        this.jkCycle = jkCycle;
    }

    private String jkType;// 借款类型GPS移交 （0->GPS  1:移交）

    /** 借款类型GPS移交 */
    public String getJkType() {
        return this.jkType;
    }

    /** 借款类型GPS移交 */
    public void setJkType(String jkType) {
        this.jkType = jkType;
    }

    private String jkLoanDate;// 申请日期

    /** 申请日期 */
    @Column(columnDefinition = DEF_STR20)
    public String getJkLoanDate() {
        return this.jkLoanDate;
    }

    /** 申请日期 */
    public void setJkLoanDate(String jkLoanDate) {
        this.jkLoanDate = jkLoanDate;
    }

    private String bankCity;// 开卡（省/市）

    /** 开卡（省/市） */
    @Column(columnDefinition = DEF_STR32)
    public String getBankCity() {
        return this.bankCity;
    }

    /** 开卡（省/市） */
    public void setBankCity(String bankCity) {
        this.bankCity = bankCity;
    }

    private String bankOpen;// 账户开户行

    /** 账户开户行 */
    @Column(columnDefinition = DEF_STR256)
    public String getBankOpen() {
        return this.bankOpen;
    }

    /** 账户开户行 */
    public void setBankOpen(String bankOpen) {
        this.bankOpen = bankOpen;
    }

    private String bankUsername;// 账户名称

    /** 账户名称 */
    @Column(columnDefinition = DEF_STR256)
    public String getBankUsername() {
        return this.bankUsername;
    }

    /** 账户名称 */
    public void setBankUsername(String bankUsername) {
        this.bankUsername = bankUsername;
    }

    private String bankNum;// 账户号码

    /** 账户号码 */
    @Column(columnDefinition = DEF_STR256)
    public String getBankNum() {
        return this.bankNum;
    }

    /** 账户号码 */
    public void setBankNum(String bankNum) {
        this.bankNum = bankNum;
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
    
    private Long crmcity;// 管辖城市(做查询依据)

    /** 管辖城市(做查询依据) */
    @Column(columnDefinition = DEF_NUM18)
    public Long getCrmcity() {
        return this.crmcity;
    }

    /** 管辖城市(做查询依据) */
    public void setCrmcity(Long crmcity) {
        this.crmcity = crmcity;
    }

    private Long crmprovince;// 管辖省份(做查询依据)

    /** 管辖省份(做查询依据) */
    @Column(columnDefinition = DEF_NUM18)
    public Long getCrmprovince() {
        return this.crmprovince;
    }

    /** 管辖省份(做查询依据) */
    public void setCrmprovince(Long crmprovince) {
        this.crmprovince = crmprovince;
    }
    
    private Long openBankProvince;//开卡省份
    /**开卡省份*/
    @Column(columnDefinition = DEF_NUM18)
    public Long getOpenBankProvince() {
		return this.openBankProvince;
	}
    /**开卡省份*/
	public void setOpenBankProvince(Long openBankProvince) {
		this.openBankProvince = openBankProvince;
	}

	private Long openBankCity;//开卡市
	/**开卡市*/
	 @Column(columnDefinition = DEF_NUM18)
	public Long getOpenBankCity() {
		return this.openBankCity;
	}
	 /**开卡市*/
	public void setOpenBankCity(Long openBankCity) {
		this.openBankCity = openBankCity;
	}
	
    
//    private Long teamLeaderId;// 团队经理(做查询依据)
//
//    /** 团队经理(做查询依据) */
//    @Column(columnDefinition = DEF_NUM18)
//    public Long getTeamLeaderId() {
//        return this.teamLeaderId;
//    }
//
//    /** 团队经理(做查询依据) */
//    public void setTeamLeaderId(Long teamLeaderId) {
//        this.teamLeaderId = teamLeaderId;
//    }
//
//    private Long customerLeaderId;// 客户经理(做查询依据)
//
//    /** 客户经理(做查询依据) */
//    @Column(columnDefinition = DEF_NUM18)
//    public Long getCustomerLeaderId() {
//        return this.customerLeaderId;
//    }
//
//    /** 客户经理(做查询依据) */
//    public void setCustomerLeaderId(Long customerLeaderId) {
//        this.customerLeaderId = customerLeaderId;
//    }
    
   

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
    

    private Employee employeeService;

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_SERVICE_STAFF", unique = false, nullable = true, insertable = true, updatable = true)
    public Employee getEmployeeService() {
        return employeeService;
    }

    public void setEmployeeService(Employee employeeService) {
        this.employeeService = employeeService;
    }


    private String subState;// 提交状态

    /** 提交状态 */
    @Column(columnDefinition = DEF_STR8)
    public String getSubState() {
        return this.subState;
    }

    /** 提交状态 */
    public void setSubState(String subState) {
        this.subState = subState;
    }

    private String state;// 借款申请流程状态，状态参考实体

    /** 借款申请流程状态，状态参考实体 */
    @Column(columnDefinition = DEF_STR8)
    public String getState() {
        return this.state;
    }

    /** 借款申请流程状态，状态参考实体 */
    public void setState(String state) {
    		this.state = state;
    }

    private String loanCode;// 借款编号

    /** 借款编号 */
    @Column(columnDefinition = DEF_STR64)
    public String getLoanCode() {
        return this.loanCode;
    }

    /** 借款编号 */
    public void setLoanCode(String loanCode) {
        this.loanCode = loanCode;
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
    
    /**有无共同还款人*/
    private String togetherPerson;
    /**有无共同还款人*/
    @Column(columnDefinition = DEF_STR50)
	public String getTogetherPerson() {
		return togetherPerson;
	}
	/**有无共同还款人*/
	public void setTogetherPerson(String togetherPerson) {
		this.togetherPerson = togetherPerson;
	}
	
	
	private XhCarLoanUser xhCarLoanUser;

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "CAR_USER_ID", unique = false, nullable = true, insertable = true, updatable = true)
    public XhCarLoanUser getXhCarLoanUser() {
        return xhCarLoanUser;
    }

    
    public void setXhCarLoanUser(XhCarLoanUser xhCarLoanUser) {
        this.xhCarLoanUser = xhCarLoanUser;
    }
    
    /**
     * 人员的车辆信息
     */
    private XhCarMessage xhCarMessage;

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "CAR_MESS_ID", unique = false, nullable = true, insertable = true, updatable = true)
    
    
    public XhCarMessage getXhCarMessage() {
		return xhCarMessage;
	}

	public void setXhCarMessage(XhCarMessage xhCarMessage) {
		this.xhCarMessage = xhCarMessage;
	}

    /**
     * 申请评估信息相关内容
     */
    private XhCarLoanApplyComplement xhCarLoanApplyComplement;


	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "CAR_APPLY_COMPLEMENT_ID", unique = false, nullable = true, insertable = true, updatable = true)
    public XhCarLoanApplyComplement getXhCarLoanApplyComplement() {
        return xhCarLoanApplyComplement;
    }

    
    public void setXhCarLoanApplyComplement(XhCarLoanApplyComplement xhCarLoanApplyComplement) {
        this.xhCarLoanApplyComplement = xhCarLoanApplyComplement;
    }
    
    /**
     * 申请对应的合同
     */
    private XhCarLoanContract xhCarLoanContract;

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "CAR_APPLY_CONTRACT_ID", unique = false, nullable = true, insertable = true, updatable = true)
    public XhCarLoanContract getXhCarLoanContract() {
        return xhCarLoanContract;
    }

    
    public void setXhCarLoanContract(XhCarLoanContract xhCarLoanContract) {
        this.xhCarLoanContract = xhCarLoanContract;
    }
    
    /**
     * 申请对应的审核信息
     */
    private List<XhCarAudit> xhCarAudits = new ArrayList<XhCarAudit>();

    // 一对多定义
    @OneToMany(targetEntity = XhCarAudit.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    @OrderBy(clause=" CREATE_TIME ASC ")
    @JoinColumn(name = "CAR_APPLY_ID", updatable = false)
    // 集合中对象id的缓存.
    public List<XhCarAudit> getXhCarAudit() {
        return xhCarAudits;
    }

    
    public void setXhCarAudit(List<XhCarAudit> xhCarAudits) {
        this.xhCarAudits = xhCarAudits;
    }
    
    private String noExtension;// 展期期数

    /** 展期期数 */
    @Column(columnDefinition = DEF_STR8)
    public String getNoExtension() {
        return this.noExtension;
    }

    /** 展期期数 */
    public void setNoExtension(String noExtension) {
        this.noExtension = noExtension;
    }

    private String isExtension;// 是否展期；0否，1是

    /** 是否展期 ；0否，1是*/
    @Column(columnDefinition = DEF_STR2)
    public String getIsExtension() {
        return this.isExtension;
    }

    /** 是否展期；0否，1是 */
    public void setIsExtension(String isExtension) {
        this.isExtension = isExtension;
    }
    
    private Long partenCarApplyId;//上笔车借申请ID

    /**上笔车借申请ID*/
    @Column(columnDefinition = DEF_NUM18)
    public Long getPartenCarApplyId() {
        return this.partenCarApplyId;
    }

    /**上笔车借申请ID*/
    public void setPartenCarApplyId(Long partenCarApplyId) {
        this.partenCarApplyId = partenCarApplyId;
    }
    
    private String isHaveextension;// 是否已展期；0否，1是

    /**是否已展期；0否，1是*/
    @Column(columnDefinition = DEF_STR2)
    public String getIsHaveextension() {
        return this.isHaveextension;
    }

    /**是否已展期；0否，1是*/
    public void setIsHaveextension(String isHaveextension) {
        this.isHaveextension = isHaveextension;
    }
    
    private Long originalCarApplyId;//原始车借申请ID

    /** 原始车借申请ID*/
    @Column(columnDefinition = DEF_NUM18)
    public Long getOriginalCarApplyId() {
        return this.originalCarApplyId;
    }

    /**原始车借申请ID*/
    public void setOriginalCarApplyId(Long originalCarApplyId) {
        this.originalCarApplyId = originalCarApplyId;
    }
    
    private Long xhJksqId;// 借款申请ID

    /** 借款申请ID */
    @Column(columnDefinition = DEF_NUM18)
    public Long getXhJksqId() {
        return this.xhJksqId;
    }

    /** 借款申请ID */
    public void setXhJksqId(Long xhJksqId) {
        this.xhJksqId = xhJksqId;
    }

    
    /**
     * 申请对应的审核信息
     */
    private List<XhCarState> xhCarStates = new ArrayList<XhCarState>();

    // 一对多定义
    @OneToMany(targetEntity = XhCarState.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "CAR_APPLY_ID", updatable = false)
    @OrderBy(clause=" CREATE_TIME DESC")
    // 集合中对象id的缓存.
    public List<XhCarState> getXhCarState() {
        return xhCarStates;
    }

    
    public void setXhCarState(List<XhCarState> xhCarStates) {
        this.xhCarStates = xhCarStates;
    }
    
   
    private Double operationFee;// 业务收费

    /** 业务收费 */
    @Column(columnDefinition = DEF_NUM10_2)
    public Double getOperationFee() {
        return this.operationFee;
    }

    /** 业务收费 */
    public void setOperationFee(Double operationFee) {
        this.operationFee = operationFee;
    }
    
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

    
}

package cn.com.cucsi.vechicle.entity.xhcf;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import cn.com.cucsi.core.orm.hibernate.AuditableEntity;

/**
 * XhCarLoanApplyComplement entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
// 表名与类名不相同时重新定义表名.
@Table(name = "XH_CAR_LOAN_APPLY_COMPLEMENT")
// 默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XhCarLoanApplyComplement extends AuditableEntity {

    // Fields
    private static final long serialVersionUID = -2276242824197821671L;
    private String auditHjadress;// 审核户籍地址

    /** 审核户籍地址 */
    @Column(columnDefinition = DEF_STR512)
    public String getAuditHjadress() {
        return this.auditHjadress;
    }

    /** 审核户籍地址 */
    public void setAuditHjadress(String auditHjadress) {
        this.auditHjadress = auditHjadress;
    }

    private String auditZjhm;// 身份真伪验证

    /** 身份真伪验证 */
    @Column(columnDefinition = DEF_STR64)
    public String getAuditZjhm() {
        return this.auditZjhm;
    }

    /** 身份真伪验证 */
    public void setAuditZjhm(String auditZjhm) {
        this.auditZjhm = auditZjhm;
    }

    private String auditTemporary;// 审核暂住证

    /** 审核暂住证 */
    @Column(columnDefinition = DEF_STR64)
    public String getAuditTemporary() {
        return this.auditTemporary;
    }

    /** 审核暂住证 */
    public void setAuditTemporary(String auditTemporary) {
        this.auditTemporary = auditTemporary;
    }

    private String auditPersonlaw;// 审核客户人法

    /** 审核客户人法 */
    @Column(columnDefinition = DEF_STR512)
    public String getAuditPersonlaw() {
        return this.auditPersonlaw;
    }

    /** 审核客户人法 */
    public void setAuditPersonlaw(String auditPersonlaw) {
        this.auditPersonlaw = auditPersonlaw;
    }

    private String auditHomeadress;// 审核现住址

    /** 审核现住址 */
    @Column(columnDefinition = DEF_STR512)
    public String getAuditHomeadress() {
        return this.auditHomeadress;
    }

    /** 审核现住址 */
    public void setAuditHomeadress(String auditHomeadress) {
        this.auditHomeadress = auditHomeadress;
    }

    private String audit114;// 114电话查询情况

    /** 114电话查询情况 */
    @Column(columnDefinition = DEF_STR512)
    public String getAudit114() {
        return this.audit114;
    }

    /** 114电话查询情况 */
    public void setAudit114(String audit114) {
        this.audit114 = audit114;
    }

    private String auditWork;// 客户工作审核情况

    /** 客户工作审核情况 */
    @Column(columnDefinition = DEF_STR512)
    public String getAuditWork() {
        return this.auditWork;
    }

    /** 客户工作审核情况 */
    public void setAuditWork(String auditWork) {
        this.auditWork = auditWork;
    }

    private String auditCredit;// 征信报告显示情况

    /** 征信报告显示情况 */
    @Column(columnDefinition = DEF_STR512)
    public String getAuditCredit() {
        return this.auditCredit;
    }

    /** 征信报告显示情况 */
    public void setAuditCredit(String auditCredit) {
        this.auditCredit = auditCredit;
    }

    private Double assessMoney;// 评估金额

    /** 评估金额 */
    @Column(columnDefinition = DEF_NUM18_2)
    public Double getAssessMoney() {
        return this.assessMoney;
    }

    /** 评估金额 */
    public void setAssessMoney(Double assessMoney) {
        this.assessMoney = assessMoney;
    }

    private Double suggestMoney;// 建议借款额

    /** 建议借款额 */
    @Column(columnDefinition = DEF_NUM18_2)
    public Double getSuggestMoney() {
        return this.suggestMoney;
    }

    /** 建议借款额 */
    public void setSuggestMoney(Double suggestMoney) {
        this.suggestMoney = suggestMoney;
    }

    private String assessPerson;// 评估师姓名

    /** 评估师姓名 */
    @Column(columnDefinition = DEF_STR30)
    public String getAssessPerson() {
        return this.assessPerson;
    }

    /** 评估师姓名 */
    public void setAssessPerson(String assessPerson) {
        this.assessPerson = assessPerson;
    }

    private String breakRules;// 违章及事故情况

    /** 违章及事故情况 */
    @Column(columnDefinition = DEF_STR512)
    public String getBreakRules() {
        return this.breakRules;
    }

    /** 违章及事故情况 */
    public void setBreakRules(String breakRules) {
        this.breakRules = breakRules;
    }

    private String assessFinish;// 车辆评估报告结论

    /** 车辆评估报告结论 */
    @Column(columnDefinition = DEF_STR512)
    public String getAssessFinish() {
        return this.assessFinish;
    }

    /** 车辆评估报告结论 */
    public void setAssessFinish(String assessFinish) {
        this.assessFinish = assessFinish;
    }

    private String visualInspection;// 外观监测

    /** 外观监测 */
    @Column(columnDefinition = DEF_STR512)
    public String getVisualInspection() {
        return this.visualInspection;
    }

    /** 外观监测 */
    public void setVisualInspection(String visualInspection) {
        this.visualInspection = visualInspection;
    }

    private String inspectionFlag;// 车年检情况(有无)

    /** 车年检情况(有无) */
    @Column(columnDefinition = DEF_STR10)
    public String getInspectionFlag() {
        return this.inspectionFlag;
    }

    /** 车年检情况(有无) */
    public void setInspectionFlag(String inspectionFlag) {
        this.inspectionFlag = inspectionFlag;
    }

    private String inspection;// 车年检情况

    /** 车年检情况 */
    @Column(columnDefinition = DEF_STR512)
    public String getInspection() {
        return this.inspection;
    }

    /** 车年检情况 */
    public void setInspection(String inspection) {
        this.inspection = inspection;
    }

    private String trafficInsuranceFlag;// 交强险(有无)

    /** 交强险(有无) */
    @Column(columnDefinition = DEF_STR10)
    public String getTrafficInsuranceFlag() {
        return this.trafficInsuranceFlag;
    }

    /** 交强险(有无) */
    public void setTrafficInsuranceFlag(String trafficInsuranceFlag) {
        this.trafficInsuranceFlag = trafficInsuranceFlag;
    }

    private String trafficInsurance;// 交强险

    /** 交强险 */
    @Column(columnDefinition = DEF_STR20)
    public String getTrafficInsurance() {
        return this.trafficInsurance;
    }

    /** 交强险 */
    public void setTrafficInsurance(String trafficInsurance) {
        this.trafficInsurance = trafficInsurance;
    }

    private String businessInsuranceFlag;// 商业险(有无)

    /** 商业险(有无) */
    @Column(columnDefinition = DEF_STR10)
    public String getBusinessInsuranceFlag() {
        return this.businessInsuranceFlag;
    }

    /** 商业险(有无) */
    public void setBusinessInsuranceFlag(String businessInsuranceFlag) {
        this.businessInsuranceFlag = businessInsuranceFlag;
    }

    private String businessInsurance;// 商业险

    /** 商业险 */
    @Column(columnDefinition = DEF_STR20)
    public String getBusinessInsurance() {
        return this.businessInsurance;
    }

    /** 商业险 */
    public void setBusinessInsurance(String businessInsurance) {
        this.businessInsurance = businessInsurance;
    }

    private String chassisNumber;// 车架号

    /** 车架号 */
    @Column(columnDefinition = DEF_STR64)
    public String getChassisNumber() {
        return this.chassisNumber;
    }

    /** 车架号 */
    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    private String madeTime;// 出厂日期

    /** 出厂日期 */
    @Column(columnDefinition = DEF_STR20)
    public String getMadeTime() {
        return this.madeTime;
    }

    /** 出厂日期 */
    public void setMadeTime(String madeTime) {
        this.madeTime = madeTime;
    }

    private String plate;// 车牌号码

    /** 车牌号码 */
    @Column(columnDefinition = DEF_STR20)
    public String getPlate() {
        return this.plate;
    }

    /** 车牌号码 */
    public void setPlate(String plate) {
        this.plate = plate;
    }

    private String registerTime;// 登记日期

    /** 登记日期 */
    @Column(columnDefinition = DEF_STR20)
    public String getRegisterTime() {
        return this.registerTime;
    }

    /** 登记日期 */
    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    private String lable;// 车辆厂牌型号

    /** 车辆厂牌型号 */
    @Column(columnDefinition = DEF_STR20)
    public String getLable() {
        return this.lable;
    }

    /** 车辆厂牌型号 */
    public void setLable(String lable) {
        this.lable = lable;
    }
    
    private String telephone;//申请人电话信息
    @Column(columnDefinition = DEF_STR32)
    
    public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	private String audittelephone;//申请人电话信息审核
	 @Column(columnDefinition = DEF_STR256)
	 
	 public String getAudittelephone() {
			return audittelephone;
		}

		public void setAudittelephone(String audittelephone) {
			this.audittelephone = audittelephone;
		}
    /**
     * 申请实体
     */
    private XhCarLoanApply xhCarLoanApply;

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)

    @JoinColumn(name = "CAR_APPLY_ID", unique = false, nullable = true, insertable = true, updatable = true)
    public XhCarLoanApply getXhCarLoanApply() {
        return xhCarLoanApply;
    }

    
    public void setXhCarLoanApply(XhCarLoanApply xhCarLoanApply) {
        this.xhCarLoanApply = xhCarLoanApply;
    }
}

package cn.com.cucsi.app2.entity.xhcf;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import cn.com.cucsi.app.entity.xhcf.XhJksq;
import cn.com.cucsi.core.orm.hibernate.AuditableEntity;

/**
 * XhJksqOffice entity. 
 * @author MyEclipse Persistence Tools
 */
@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "XH_JKSQ_OFFICE")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XhJksqOffice extends AuditableEntity{

	// Fields
	private static final long serialVersionUID = -2276242824197821671L;
	private String name;//单位名称
	/**单位名称*/
	@Column(columnDefinition=DEF_STR20)
	public String getName() {
		return this.name;
	}
	/**单位名称*/
	public void setName(String name) {
		this.name = name;
	}
	private String postcode;//邮编
	/**邮编*/
	@Column(columnDefinition=DEF_STR10)
	public String getPostcode() {
		return this.postcode;
	}
	/**邮编*/
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	private String address;//单位地址
	/**单位地址*/
	@Column(columnDefinition=DEF_STR200)
	public String getAddress() {
		return this.address;
	}
	/**单位地址*/
	public void setAddress(String address) {
		this.address = address;
	}
	private String typea;//行业类别
	/**行业类别*/
	@Column(columnDefinition=DEF_STR20)
	public String getTypea() {
		return this.typea;
	}
	/**行业类别*/
	public void setTypea(String typea) {
		this.typea = typea;
	}
	private String typeh;//单位类型（下拉）
	/**单位类型（下拉）*/
	@Column(columnDefinition=DEF_STR20)
	public String getTypeh() {
		return this.typeh;
	}
	/**单位类型（下拉）*/
	public void setTypeh(String typeh) {
		this.typeh = typeh;
	}
	private String phone;//单位电话
	/**单位电话*/
	@Column(columnDefinition=DEF_STR20)
	public String getPhone() {
		return this.phone;
	}
	/**单位电话*/
	public void setPhone(String phone) {
		this.phone = phone;
	}
	private String website;//有无抵押 0:无 1:有
	/**有无抵押 0:无 1:有*/
	@Column(columnDefinition=DEF_STR50)
	public String getWebsite() {
		return this.website;
	}
	/**有无抵押 0:无 1:有*/
	public void setWebsite(String website) {
		this.website = website;
	}
	private String department;//部门
	/**部门*/
	@Column(columnDefinition=DEF_STR32)
	public String getDepartment() {
		return this.department;
	}
	/**部门*/
	public void setDepartment(String department) {
		this.department = department;
	}
	private String duty;//职务
	/**职务*/
	@Column(columnDefinition=DEF_STR20)
	public String getDuty() {
		return this.duty;
	}
	/**职务*/
	public void setDuty(String duty) {
		this.duty = duty;
	}
	private Long personCount;//员工数量
	/**员工数量*/
	@Column(columnDefinition=DEF_NUM4)
	public Long getPersonCount() {
		return this.personCount;
	}
	/**员工数量*/
	public void setPersonCount(Long personCount) {
		this.personCount = personCount;
	}
	private Double monthSalary;//月收入
	/**月收入*/
	@Column(columnDefinition=DEF_NUM18_2)
	public Double getMonthSalary() {
		return this.monthSalary;
	}
	/**月收入*/
	public void setMonthSalary(Double monthSalary) {
		this.monthSalary = monthSalary;
	}
	private Long salaryDay;//支薪日
	/**支薪日*/
	@Column(columnDefinition=DEF_NUM2)
	public Long getSalaryDay() {
		return this.salaryDay;
	}
	/**支薪日*/
	public void setSalaryDay(Long salaryDay) {
		this.salaryDay = salaryDay;
	}
	private String salaryType;//支付方式
	/**支付方式*/
	@Column(columnDefinition=DEF_STR10)
	public String getSalaryType() {
		return this.salaryType;
	}
	/**支付方式*/
	public void setSalaryType(String salaryType) {
		this.salaryType = salaryType;
	}
	private Double workYear;//服务年薪
	/**服务年薪*/
	@Column(columnDefinition=DEF_NUM4_2)
	public Double getWorkYear() {
		return this.workYear;
	}
	/**服务年薪*/
	public void setWorkYear(Double workYear) {
		this.workYear = workYear;
	}
	private Double bonus;//其他收入
	/**其他收入*/
	@Column(columnDefinition=DEF_NUM18_2)
	public Double getBonus() {
		return this.bonus;
	}
	/**其他收入*/
	public void setBonus(Double bonus) {
		this.bonus = bonus;
	}
	private String cofficeStartyear;//公司成立时间
	/**公司成立时间*/
	@Column(columnDefinition=DEF_STR20)
	public String getCofficeStartyear() {
		return this.cofficeStartyear;
	}
	/**公司成立时间*/
	public void setCofficeStartyear(String cofficeStartyear) {
		this.cofficeStartyear = cofficeStartyear;
	}
	private Long cofficeFund;//社保/公积金 0:无 1：有
	/**社保/公积金 0:无 1：有*/
	@Column(columnDefinition=DEF_NUM1)
	public Long getCofficeFund() {
		return this.cofficeFund;
	}
	/**社保/公积金 0:无 1：有*/
	public void setCofficeFund(Long cofficeFund) {
		this.cofficeFund = cofficeFund;
	}
	private Double cofficeFundNumber;//社保/公积金基数
	/**社保/公积金基数*/
	@Column(columnDefinition=DEF_NUM18_2)
	public Double getCofficeFundNumber() {
		return this.cofficeFundNumber;
	}
	/**社保/公积金基数*/
	public void setCofficeFundNumber(Double cofficeFundNumber) {
		this.cofficeFundNumber = cofficeFundNumber;
	}
	private String cofficeSalaryEnsure;//工作证明工资
	/**工作证明工资*/
	@Column(columnDefinition=DEF_STR20)
	public String getCofficeSalaryEnsure() {
		return this.cofficeSalaryEnsure;
	}
	/**工作证明工资*/
	public void setCofficeSalaryEnsure(String cofficeSalaryEnsure) {
		this.cofficeSalaryEnsure = cofficeSalaryEnsure;
	}
	private String cofficeSalaryBankEnsure;//银行代发工资
	/**银行代发工资*/
	@Column(columnDefinition=DEF_STR20)
	public String getCofficeSalaryBankEnsure() {
		return this.cofficeSalaryBankEnsure;
	}
	/**银行代发工资*/
	public void setCofficeSalaryBankEnsure(String cofficeSalaryBankEnsure) {
		this.cofficeSalaryBankEnsure = cofficeSalaryBankEnsure;
	}
	
	
	
	private XhJksq xhJksq;             //借款申请信息
    
    /**借款申请信息*/
    @ManyToOne(cascade=CascadeType.REFRESH,fetch = FetchType.LAZY)
    @JoinColumn(name="JKSQ_ID", unique= false, nullable=true, insertable=true, updatable=true)
    public XhJksq getXhJksq() {
        return xhJksq;
    }
    
    public void setXhJksq(XhJksq xhJksq) {
        this.xhJksq = xhJksq;
    }
}

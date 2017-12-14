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

import cn.com.cucsi.app.entity.xhcf.XhJksq;
import cn.com.cucsi.core.orm.hibernate.AuditableEntity;

/**
 * XhCarUserOffice entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
// 表名与类名不相同时重新定义表名.
@Table(name = "XH_CAR_USER_OFFICE")
// 默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XhCarUserOffice extends AuditableEntity {

    // Fields
    private static final long serialVersionUID = -2276242824197821671L;
    private String name;// 单位名称

    /** 单位名称 */
    @Column(columnDefinition = DEF_STR20)
    public String getName() {
        return this.name;
    }

    /** 单位名称 */
    public void setName(String name) {
        this.name = name;
    }

    private String department;// 部门

    /** 部门 */
    @Column(columnDefinition = DEF_STR30)
    public String getDepartment() {
        return this.department;
    }

    /** 部门 */
    public void setDepartment(String department) {
        this.department = department;
    }

    private String address;// 单位地址

    /** 单位地址 */
    @Column(columnDefinition = DEF_STR200)
    public String getAddress() {
        return this.address;
    }

    /** 单位地址 */
    public void setAddress(String address) {
        this.address = address;
    }

    private String phone;// 单位电话

    /** 单位电话 */
    @Column(columnDefinition = DEF_STR20)
    public String getPhone() {
        return this.phone;
    }

    /** 单位电话 */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    private String duty;// 职务级别

    /** 职务级别 */
    @Column(columnDefinition = DEF_STR20)
    public String getDuty() {
        return this.duty;
    }

    /** 职务级别 */
    public void setDuty(String duty) {
        this.duty = duty;
    }

    private Long monthSalary;// 月基本薪金

    /** 月基本薪金 */
    @Column(columnDefinition = DEF_NUM18)
    public Long getMonthSalary() {
        return this.monthSalary;
    }

    /** 月基本薪金 */
    public void setMonthSalary(Long monthSalary) {
        this.monthSalary = monthSalary;
    }

    private Long bonus;// 其他收入

    /** 其他收入 */
    @Column(columnDefinition = DEF_NUM18)
    public Long getBonus() {
        return this.bonus;
    }

    /** 其他收入 */
    public void setBonus(Long bonus) {
        this.bonus = bonus;
    }

    private String startWorkDate;// 起始服务时间(年/月)

    /** 起始服务时间(年/月) */
    @Column(columnDefinition = DEF_STR20)
    public String getStartWorkDate() {
        return this.startWorkDate;
    }

    /** 起始服务时间(年/月) */
    public void setStartWorkDate(String startWorkDate) {
        this.startWorkDate = startWorkDate;
    }

    private String typeh;// 单位性质（下拉）

    /** 单位性质（下拉） */
    @Column(columnDefinition = DEF_STR20)
    public String getTypeh() {
        return this.typeh;
    }

    /** 单位性质（下拉） */
    public void setTypeh(String typeh) {
        this.typeh = typeh;
    }

    private String npostcode;// 邮编

    /** 邮编 */
    @Column(columnDefinition = DEF_STR10)
    public String getNpostcode() {
        return this.npostcode;
    }

    /** 邮编 */
    public void setNpostcode(String npostcode) {
        this.npostcode = npostcode;
    }

    private String ntypea;// 行业类别

    /** 行业类别 */
    @Column(columnDefinition = DEF_STR20)
    public String getNtypea() {
        return this.ntypea;
    }

    /** 行业类别 */
    public void setNtypea(String ntypea) {
        this.ntypea = ntypea;
    }

    private String nwebsite;// 公司网址

    /** 公司网址 */
    @Column(columnDefinition = DEF_STR50)
    public String getNwebsite() {
        return this.nwebsite;
    }

    /** 公司网址 */
    public void setNwebsite(String nwebsite) {
        this.nwebsite = nwebsite;
    }

    private Long npersonCount;// 员工数量

    /** 员工数量 */
    @Column(columnDefinition = DEF_NUM4)
    public Long getNpersonCount() {
        return this.npersonCount;
    }

    /** 员工数量 */
    public void setNpersonCount(Long npersonCount) {
        this.npersonCount = npersonCount;
    }

    private Long nsalaryDay;// 支薪日

    /** 支薪日 */
    @Column(columnDefinition = DEF_NUM2)
    public Long getNsalaryDay() {
        return this.nsalaryDay;
    }

    /** 支薪日 */
    public void setNsalaryDay(Long nsalaryDay) {
        this.nsalaryDay = nsalaryDay;
    }

    private String nsalaryType;// 支付方式

    /** 支付方式 */
    @Column(columnDefinition = DEF_STR10)
    public String getNsalaryType() {
        return this.nsalaryType;
    }

    /** 支付方式 */
    public void setNsalaryType(String nsalaryType) {
        this.nsalaryType = nsalaryType;
    }

    private Long workYear;// 服务年薪

    /** 服务年薪 */
    @Column(columnDefinition = DEF_NUM2)
    public Long getWorkYear() {
        return this.workYear;
    }

    /** 服务年薪 */
    public void setWorkYear(Long workYear) {
        this.workYear = workYear;
    }

    private String cofficeStartyear;// 公司成立时间

    /** 公司成立时间 */
    @Column(columnDefinition = DEF_STR20)
    public String getCofficeStartyear() {
        return this.cofficeStartyear;
    }

    /** 公司成立时间 */
    public void setCofficeStartyear(String cofficeStartyear) {
        this.cofficeStartyear = cofficeStartyear;
    }

    private Long cofficeFund;// 社保/公积金 0:无 1：有

    /** 社保/公积金 0:无 1：有 */
    @Column(columnDefinition = DEF_NUM1)
    public Long getCofficeFund() {
        return this.cofficeFund;
    }

    /** 社保/公积金 0:无 1：有 */
    public void setCofficeFund(Long cofficeFund) {
        this.cofficeFund = cofficeFund;
    }

    private Long cofficeFundNumber;// 社保/公积金基数

    /** 社保/公积金基数 */
    @Column(columnDefinition = DEF_NUM18)
    public Long getCofficeFundNumber() {
        return this.cofficeFundNumber;
    }

    /** 社保/公积金基数 */
    public void setCofficeFundNumber(Long cofficeFundNumber) {
        this.cofficeFundNumber = cofficeFundNumber;
    }

    private String cofficeSalaryEnsure;// 工作证明工资

    /** 工作证明工资 */
    @Column(columnDefinition = DEF_STR20)
    public String getCofficeSalaryEnsure() {
        return this.cofficeSalaryEnsure;
    }

    /** 工作证明工资 */
    public void setCofficeSalaryEnsure(String cofficeSalaryEnsure) {
        this.cofficeSalaryEnsure = cofficeSalaryEnsure;
    }

    private String cofficeSalaryBankEnsure;// 银行代发工资

    /** 银行代发工资 */
    @Column(columnDefinition = DEF_STR20)
    public String getCofficeSalaryBankEnsure() {
        return this.cofficeSalaryBankEnsure;
    }

    /** 银行代发工资 */
    public void setCofficeSalaryBankEnsure(String cofficeSalaryBankEnsure) {
        this.cofficeSalaryBankEnsure = cofficeSalaryBankEnsure;
    }

    private XhCarLoanUser xhCarLoanUser;

    // 借款申请信息
    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "XHCARLOANUSER_ID", unique = false, nullable = true, insertable = true, updatable = true)
    public XhCarLoanUser getXhCarLoanUser() {
        return xhCarLoanUser;
    }

    public void setXhCarLoanUser(XhCarLoanUser xhCarLoanUser) {
        this.xhCarLoanUser = xhCarLoanUser;
    }

}

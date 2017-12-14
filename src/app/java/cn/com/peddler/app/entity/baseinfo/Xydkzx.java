package cn.com.cucsi.app.entity.baseinfo;
import cn.com.cucsi.app.entity.baseinfo.Employee;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import cn.com.cucsi.core.orm.hibernate.AuditableEntity;
/**
 * 信用贷款咨询表.
 * 
 * @author yumaolin
 */
@Entity

@Table(name = "XH_XYDKZX")
public class Xydkzx extends AuditableEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3045884521169011229L;
	/**
	 * 咨询编码
	 */
	private String zxbm;
	/**
	 * 咨询客户名称
	 */
	private String khmc;
	/**
	 * 性别
	 */
	private String sex;
	/**
	 * 固定电话
	 */
	private String telephone;
	/**
	 * 移动电话
	 */
	private String phone;
	
	/**
	 * 所在城市
	 */
	private String crty;
	/**
	 * 团队经理姓名
	 */
	private String teamName;
	/**
	 * 销售人员姓名
	 */
	private String saleName;
	/**
	 * 计划借贷金额
	 */
	private String planAmount;
	/**
	 * 贷款用途
	 */
	private String loanPurpose;
	/**
	 * 沟通记录
	 */
	private String gtjl;
	/**
	 * 借款类型
	 */
	private String jklx;
	/**
	 * 客户评价
	 */
	private String khpj;
	/**
	 * 是否重点客户
	 */
	private String zdkh;
	/**
	 * 车牌号
	 */
	private String carNumber;
	/**
	 * 车架号
	 */
	private String carShelf;
	/**
	 * 省份
	 */
	private String crmprovince;
	/**
	 * 状态
	 */
	private String zhuangTai;
	private Employee employeeCrm;
	private Employee employeeCca;
	private Organi organi;
	/**
	 * 沟通时间
	 */
	private String gtTime;
	
	/**
	 * 咨询类型0:新客户咨询1：循环贷（老客户）咨询
	 */
	private String type;
	@Column(columnDefinition=DEF_STR2)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Column(columnDefinition=DEF_STR32)
	public String getZxbm() {
		
		return zxbm;
	}
	public void setZxbm(String zxbm) {
		this.zxbm = zxbm;
	}
	@Column(columnDefinition=DEF_STR16)
	public String getKhmc() {
		return khmc;
	}
	public void setKhmc(String khmc) {
		this.khmc = khmc;
	}
	@Column(columnDefinition=DEF_STR2)
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	@Column(columnDefinition=DEF_STR16)
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	@Column(columnDefinition=DEF_STR16)
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Column(columnDefinition=DEF_STR16)
	public String getCrty() {
		return crty;
	}
	public void setCrty(String crty) {
		this.crty = crty;
	}
	@Column(columnDefinition=DEF_STR16)
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	@Column(columnDefinition=DEF_STR16)
	public String getSaleName() {
		return saleName;
	}
	public void setSaleName(String saleName) {
		this.saleName = saleName;
	}
	@Column(columnDefinition=DEF_NUM15_5)
	public String getPlanAmount() {
		return planAmount;
	}
	public void setPlanAmount(String planAmount) {
		this.planAmount = planAmount;
	}
	@Column(columnDefinition=DEF_STR128)
	public String getLoanPurpose() {
		return loanPurpose;
	}
	public void setLoanPurpose(String loanPurpose) {
		this.loanPurpose = loanPurpose;
	}
	@Column(columnDefinition=DEF_STR512)
	public String getGtjl() {
		return gtjl;
	}
	public void setGtjl(String gtjl) {
		this.gtjl = gtjl;
	}
	@Column(columnDefinition=DEF_STR8)
	public String getJklx() {
		return jklx;
	}
	public void setJklx(String jklx) {
		this.jklx = jklx;
	}
	@Column(columnDefinition=DEF_STR8)
	public String getKhpj() {
		return khpj;
	}
	public void setKhpj(String khpj) {
		this.khpj = khpj;
	}
	@Column(columnDefinition=DEF_STR8)
	public String getZdkh() {
		return zdkh;
	}
	public void setZdkh(String zdkh) {
		this.zdkh = zdkh;
	}
	@Column(columnDefinition=DEF_STR20)
	public String getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	@Column(columnDefinition=DEF_STR20)
	public String getCarShelf() {
		return carShelf;
	}
	public void setCarShelf(String carShelf) {
		this.carShelf = carShelf;
	}
	@Column(columnDefinition=DEF_STR16)
	public String getCrmprovince() {
		return crmprovince;
	}
	public void setCrmprovince(String crmprovince) {
		this.crmprovince = crmprovince;
	}
	@Column(columnDefinition=DEF_STR8)
	public String getZhuangTai() {
		return zhuangTai;
	}
	public void setZhuangTai(String zhuangTai) {
		this.zhuangTai = zhuangTai;
	}
	@OneToOne(cascade=CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name="EMPLOYEE_CRM", unique= false, nullable=true, insertable=true, updatable=true)
	public Employee getEmployeeCrm() {
		return employeeCrm;
	}
	public void setEmployeeCrm(Employee employeeCrm) {
		this.employeeCrm = employeeCrm;
	}
	@OneToOne(cascade=CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name="EMPLOYEE_CCA", unique= false, nullable=true, insertable=true, updatable=true)
	public Employee getEmployeeCca() {
		return employeeCca;
	}
	public void setEmployeeCca(Employee employeeCca) {
		this.employeeCca = employeeCca;
	}
	@Column(columnDefinition=DEF_STR40)
	public String getGtTime() {
		return gtTime;
	}
	public void setGtTime(String gtTime) {
		this.gtTime = gtTime;
	}
	//一对一定义
	@OneToOne(cascade=CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name="organi_id", unique= false, nullable=true, insertable=true, updatable=true)
	public Organi getOrgani() {
		return organi;
	}

	public void setOrgani(Organi organi) {
		this.organi = organi;
	}
}

package cn.com.cucsi.app.entity.xhcf;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import cn.com.cucsi.app.entity.baseinfo.Employee;
import cn.com.cucsi.app.entity.baseinfo.MiddleMan;
import cn.com.cucsi.core.orm.hibernate.AuditableEntity;


@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "XH_JYGL")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FangKuangGuanLi extends AuditableEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7912547908875538445L;
	private String jkrbh;
	private String jkrxm;//借款人姓名
	private String htbh;//合同编号
	private Double htje;//合同金额
	private Double fkje;//放款金额
	private String htzt;//合同状态
	private String zjhm;//证件号码
	private String city;//所在城市
	private Employee employeeCrm;
	private Employee employeeCca;
	private Long fkqs;//放款期数
	private String fkzh;//放款账户
	private String fksj;//放款时间
	private String zhmc;//账户名称
	private String zhkhh;//账户开户行
	private String yhzh;//银行账号
	private String yhmc;//银行名称
	private String fkzt;//放款状态
	private XhJksq xhJksq;
	private MiddleMan middleMan;//中间人
	private XhAvailableValueOfClaims xhAvailableValueOfClaims;//可用债权价值
//	private XhJkht XhJkht;	
	public String getJkrbh() {
		return jkrbh;
	}
	public void setJkrbh(String jkrbh) {
		this.jkrbh = jkrbh;
	}
	@Column(columnDefinition=DEF_STR20)
	public String getJkrxm() {
		return jkrxm;
	}
	public void setJkrxm(String jkrxm) {
		this.jkrxm = jkrxm;
	}
	@Column(columnDefinition=DEF_STR32)
	public String getHtbh() {
		return htbh;
	}
	public void setHtbh(String htbh) {
		this.htbh = htbh;
	}
	@Column(columnDefinition=DEF_NUM10_2)
	public Double getHtje() {
		return htje;
	}
	public void setHtje(Double htje) {
		this.htje = htje;
	}
	@Column(columnDefinition=DEF_NUM10_2)
	public Double getFkje() {
		return fkje;
	}
	public void setFkje(Double fkje) {
		this.fkje = fkje;
	}
	@Column(columnDefinition=DEF_STR10)
	public String getHtzt() {
		return htzt;
	}
	public void setHtzt(String htzt) {
		this.htzt = htzt;
	}
	@Column(columnDefinition=DEF_STR32)
	public String getZjhm() {
		return zjhm;
	}
	public void setZjhm(String zjhm) {
		this.zjhm = zjhm;
	}
	@Column(columnDefinition=DEF_STR16)
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@OneToOne(cascade=CascadeType.REFRESH,fetch = FetchType.LAZY)
	@JoinColumn(name="EMPLOYEE_CRM", unique= false, nullable=true, insertable=true, updatable=true)
	public Employee getEmployeeCrm() {
		return employeeCrm;
	}
	public void setEmployeeCrm(Employee employeeCrm) {
		this.employeeCrm = employeeCrm;
	}
	@OneToOne(cascade=CascadeType.REFRESH,fetch = FetchType.LAZY)
	@JoinColumn(name="EMPLOYEE_CCA", unique= false, nullable=true, insertable=true, updatable=true)
	public Employee getEmployeeCca() {
		return employeeCca;
	}
	public void setEmployeeCca(Employee employeeCca) {
		this.employeeCca = employeeCca;
	}
	
	@Column(columnDefinition=DEF_STR32)
	public String getFkzh() {
		return fkzh;
	}
	public void setFkzh(String fkzh) {
		this.fkzh = fkzh;
	}
	@Column(columnDefinition=DEF_STR20)
	public String getFksj() {
		return fksj;
	}
	public void setFksj(String fksj) {
		this.fksj = fksj;
	}
	@Column(columnDefinition=DEF_STR32)
	public String getZhmc() {
		return zhmc;
	}
	public void setZhmc(String zhmc) {
		this.zhmc = zhmc;
	}
	@Column(columnDefinition=DEF_STR50)
	public String getZhkhh() {
		return zhkhh;
	}
	public void setZhkhh(String zhkhh) {
		this.zhkhh = zhkhh;
	}
	@Column(columnDefinition=DEF_STR32)
	public String getYhzh() {
		return yhzh;
	}
	public void setYhzh(String yhzh) {
		this.yhzh = yhzh;
	}
	@Column(columnDefinition=DEF_STR50)
	public String getYhmc() {
		return yhmc;
	}
	public void setYhmc(String yhmc) {
		this.yhmc = yhmc;
	}
	
	@Column(columnDefinition=DEF_STR8)
	public String getFkzt() {
		return fkzt;
	}
	public void setFkzt(String fkzt) {
		this.fkzt = fkzt;
	}
	/**借款申请ID*/
	//一对一定义
	@OneToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name="jksq_id", unique= false, nullable=true, insertable=true, updatable=true)
	public XhJksq getXhJksq() {
		return xhJksq;
	}
	public void setXhJksq(XhJksq xhJksq) {
		this.xhJksq = xhJksq;
	}
	/**中间人ID*/
	@OneToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name="middleman_id", unique= false, nullable=true, insertable=true, updatable=true)
	public MiddleMan getMiddleMan() {
		return middleMan;
	}
	public void setMiddleMan(MiddleMan middleMan) {
		this.middleMan = middleMan;
	}
	/**可用债权价值ID*/
	@OneToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name="KYZQJZ_ID", unique= false, nullable=true, insertable=true, updatable=true)
	public XhAvailableValueOfClaims getXhAvailableValueOfClaims() {
		return xhAvailableValueOfClaims;
	}
	/**可用债权价值ID*/
	public void setXhAvailableValueOfClaims(
			XhAvailableValueOfClaims xhAvailableValueOfClaims) {
		this.xhAvailableValueOfClaims = xhAvailableValueOfClaims;
	}
	@Column(columnDefinition=DEF_NUM4)
	public Long getFkqs() {
		return fkqs;
	}
	public void setFkqs(Long fkqs) {
		this.fkqs = fkqs;
	}
}

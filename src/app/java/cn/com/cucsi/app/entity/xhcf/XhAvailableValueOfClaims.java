package cn.com.cucsi.app.entity.xhcf;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import cn.com.cucsi.app.entity.baseinfo.MiddleMan;
import cn.com.cucsi.core.orm.hibernate.AuditableEntity;

/**
 * XhAvailableValueOfClaims entity. 
 * @author MyEclipse Persistence Tools
 */
@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "XH_AVAILABLE_VALUE_OF_CLAIMS")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XhAvailableValueOfClaims extends AuditableEntity{

	// Fields
	private static final long serialVersionUID = -2276242824197821671L;
	/**借款申请ID*/
	private XhJksq xhJksq;
	/**借款申请ID*/
	@OneToOne(cascade=CascadeType.REFRESH,fetch = FetchType.LAZY)
	@JoinColumn(name="JKSQ_ID", unique= false, nullable=true, insertable=true, updatable=true)
	public XhJksq getXhJksq() {
		return xhJksq;
	}
	/**借款申请ID*/
	public void setXhJksq(XhJksq xhJksq) {
		this.xhJksq = xhJksq;
	}
	private String jkbh;//借款编号
	/**借款编号*/
	@Column(columnDefinition=DEF_STR64)
	public String getJkbh() {
		return this.jkbh;
	}
	/**借款编号*/
	public void setJkbh(String jkbh) {
		this.jkbh = jkbh;
	}
	private Double jkje;//借款金额
	/**借款金额*/
	@Column(columnDefinition=DEF_NUM15_5)
	public Double getJkje() {
		return this.jkje;
	}
	/**借款金额*/
	public void setJkje(Double jkje) {
		this.jkje = jkje;
	}
	private Double kyzqjz;//可用债权价值
	/**可用债权价值*/
	@Column(columnDefinition=DEF_NUM15_5)
	public Double getKyzqjz() {
		return this.kyzqjz;
	}
	/**可用债权价值*/
	public void setKyzqjz(Double kyzqjz) {
		this.kyzqjz = kyzqjz;
	}

	private Double zjrcybl;//中间人持有比例
	/**中间人持有比例*/
	@Column(columnDefinition=DEF_NUM15_5)
	public Double getZjrcybl() {
		return this.zjrcybl;
	}
	/**中间人持有比例*/
	public void setZjrcybl(Double zjrcybl) {
		this.zjrcybl = zjrcybl;
	}
	private Long syqs;//还款期限(月)
	/**还款期限(月)*/
	@Column(columnDefinition=DEF_NUM4)
	public Long getSyqs() {
		return this.syqs;
	}
	/**还款期限(月)*/
	public void setSyqs(Long syqs) {
		this.syqs = syqs;
	}
	private String sqhkrq;//首期还款日期
	/**首期还款日期*/
	@Column(columnDefinition=DEF_STR20)
	public String getSqhkrq() {
		return this.sqhkrq;
	}
	/**首期还款日期*/
	public void setSqhkrq(String sqhkrq) {
		this.sqhkrq = sqhkrq;
	}
	private String mqhkrq;//末期还款日期
	/**末期还款日期*/
	@Column(columnDefinition=DEF_STR20)
	public String getMqhkrq() {
		return this.mqhkrq;
	}
	/**末期还款日期*/
	public void setMqhkrq(String mqhkrq) {
		this.mqhkrq = mqhkrq;
	}
	private Long syhkys;//剩余还款月数
	/**剩余还款月数*/
	@Column(columnDefinition=DEF_NUM4)
	public Long getSyhkys() {
		return this.syhkys;
	}
	/**剩余还款月数*/
	public void setSyhkys(Long syhkys) {
		this.syhkys = syhkys;
	}
	private Double sytjje;//剩余推荐金额
	/**剩余推荐金额*/
	@Column(columnDefinition=DEF_NUM15_5)
	public Double getSytjje() {
		return this.sytjje;
	}
	/**剩余推荐金额*/
	public void setSytjje(Double sytjje) {
		this.sytjje = sytjje;
	}
	/**借款合同*/
	private XhJkht loanContract;// 借款合同
	/**借款合同*/
	@OneToOne(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
	@JoinColumn(name = "LOAN_CONTRACT_ID", unique = false, nullable = true, insertable = true, updatable = true)
	public XhJkht getLoanContract() {
		return loanContract;
	}
	/**借款合同*/
	public void setLoanContract(XhJkht loanContract) {
		this.loanContract = loanContract;
	}
	/**放款*/
	private XhMakeLoans makeLoan;// 放款
	/**放款*/
	@OneToOne(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
	@JoinColumn(name = "MAKE_LOAN_ID", unique = false, nullable = true, insertable = true, updatable = true)
	public XhMakeLoans getMakeLoan() {
		return makeLoan;
	}
	/**放款*/
	public void setMakeLoan(XhMakeLoans makeLoan) {
		this.makeLoan = makeLoan;
	}
	
	private MiddleMan middleMan;// 中间人（账户）ID

	/** 中间人（账户） */
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false,fetch = FetchType.LAZY)
	@JoinColumn(name = "ZJR_ID")
	public MiddleMan getMiddleMan() {
		return middleMan;
	}

	/** 中间人（账户） */
	public void setMiddleMan(MiddleMan middleMan) {
		this.middleMan = middleMan;
	}
	
	private Double zqyjcyl;//预计债权收益率(年)
	/**预计债权收益率(年)*/
	@Column(columnDefinition=DEF_NUM15_5)
	public Double getZqyjcyl() {
		return this.zqyjcyl;
	}
	/**预计债权收益率(年)*/
	public void setZqyjcyl(Double zqyjcyl) {
		this.zqyjcyl = zqyjcyl;
	}
	
	private String hkr;//还款日
	/**还款日*/
	@Column(columnDefinition=DEF_STR20)
	public String getHkr() {
		return this.hkr;
	}
	/**还款日*/
	public void setHkr(String hkr) {
		this.hkr = hkr;
	}
}

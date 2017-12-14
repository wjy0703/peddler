package cn.com.cucsi.app.entity.xhcf;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;

import cn.com.cucsi.core.orm.hibernate.IdEntity;

/**
 * XhCapitalLoanReport entity.
 * 
 * @author MyEclipse Persistence Tools
 */

@Entity
// 表名与类名不相同时重新定义表名.
@Table(name = "XH_CAPITAL_LOAN_REPORT")
// 默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XhCapitalLoanReport extends IdEntity {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	List<XhCapitalLoanReportInfo> XhCapitalLoanReportInfos = new ArrayList();

	@OneToMany(targetEntity = XhCapitalLoanReportInfo.class, cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "reportId", updatable = false)
	// 集合按erpProjectNo排序.
	@OrderBy("lendNo")
	public List<XhCapitalLoanReportInfo> getXhCapitalLoanReportInfos() {
		return XhCapitalLoanReportInfos;
	}

	public void setXhCapitalLoanReportInfos(
			List<XhCapitalLoanReportInfo> xhCapitalLoanReportInfos) {
		XhCapitalLoanReportInfos = xhCapitalLoanReportInfos;
	}

	// Fields
	private static final long serialVersionUID = -2276242824197821671L;
	private String lenderNumber;// 客户编号

	/** 客户编号 */
	@Column(columnDefinition = DEF_STR64)
	public String getLenderNumber() {
		return this.lenderNumber;
	}

	/** 客户编号 */
	public void setLenderNumber(String lenderNumber) {
		this.lenderNumber = lenderNumber;
	}

	private String reportCycle;// 报告周期

	/** 报告周期 */
	@Column(columnDefinition = DEF_STR64)
	public String getReportCycle() {
		return this.reportCycle;
	}

	/** 报告周期 */
	public void setReportCycle(String reportCycle) {
		this.reportCycle = reportCycle;
	}

	private Long accountLevel;// 账户级别

	/** 账户级别 */
	@Column(columnDefinition = DEF_NUM18)
	public Long getAccountLevel() {
		return this.accountLevel;
	}

	/** 账户级别 */
	public void setAccountLevel(Long accountLevel) {
		this.accountLevel = accountLevel;
	}

	private Date reportDate;// 报告日

	/** 报告日 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getReportDate() {
		return this.reportDate;
	}

	/** 报告日 */
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	private Double allMoneyOfCurrent;// 报告日资产总额

	/** 报告日资产总额 */
	@Column(columnDefinition = DEF_NUM15_5)
	public Double getAllMoneyOfCurrent() {
		return this.allMoneyOfCurrent;
	}

	/** 报告日资产总额 */
	public void setAllMoneyOfCurrent(Double allMoneyOfCurrent) {
		this.allMoneyOfCurrent = allMoneyOfCurrent;
	}

	private String lenderName;// 客户姓名

	/** 客户姓名 */
	@Column(columnDefinition = DEF_STR64)
	public String getLenderName() {
		return this.lenderName;
	}

	/** 客户姓名 */
	public void setLenderName(String lenderName) {
		this.lenderName = lenderName;
	}

	/** 可用债权价值 */

	XhcfCjrxx lender = new XhcfCjrxx();

	/** 可用债权价值 */
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false,fetch = FetchType.LAZY)
	@JoinColumn(name = "LOAD_ID")
	public XhcfCjrxx getLender() {
		return lender;
	}

	public void setLender(XhcfCjrxx lender) {
		this.lender = lender;
	}

	private String state;// 客户姓名
	
	

	/** 客户姓名 */
	@Column(columnDefinition = DEF_STR64)
	public String getState() {
		return this.state;
	}

	/** 客户姓名 */
	public void setState(String state) {
		this.state = state;
	}
	
	
	private String billSendState;// 账单发送状态
	
	/**账单发送状态*/
	 @Column(columnDefinition = DEF_STR8)
	public String getBillSendState() {
		return this.billSendState;
	}
	 /**账单发送状态*/
	public void setBillSendState(String billSendState) {
		this.billSendState = billSendState;
	}
	
	private String fillReason;
	
	
	/**账单发送失败原因*/
	@Column(columnDefinition = DEF_STR128)
	public String getFillReason() {
		return this.fillReason;
	}

	public void setFillReason(String fillReason) {
		this.fillReason = fillReason;
	}
	

	/** 可用债权价值 */
	XhTzsq tzsq = new XhTzsq();

	

	/** 可用债权价值 */
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false,fetch = FetchType.LAZY)
	@JoinColumn(name = "ZQTJ_ID")
	public XhTzsq getTzsq() {
		return tzsq;
	}
	
	public void setTzsq(XhTzsq tzsq) {
		this.tzsq = tzsq;
	}

}

package cn.com.cucsi.app.entity.xhcf;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import cn.com.cucsi.core.orm.hibernate.AuditableEntity;
import cn.com.cucsi.core.orm.hibernate.IdEntity;

/**
 * XhCapitalLoanReportInfo entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
// 表名与类名不相同时重新定义表名.
@Table(name = "XH_CAPITAL_LOAN_REPORT_INFO")
// 默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XhCapitalLoanReportInfo extends IdEntity {

	// Fields
	private static final long serialVersionUID = -2276242824197821671L;
	private Long reportId;// 报告主表

	/** 报告主表 */
	@Column(columnDefinition = DEF_NUM18)
	public Long getReportId() {
		return this.reportId;
	}

	/** 报告主表 */
	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}

	private String lendNo;// 出借编号

	/** 出借编号 */
	@Column(columnDefinition = DEF_STR64)
	public String getLendNo() {
		return this.lendNo;
	}

	/** 出借编号 */
	public void setLendNo(String lendNo) {
		this.lendNo = lendNo;
	}

	private Timestamp firstLendDate;// 初始出借日期

	/** 初始出借日期 */
	@Column(insertable = false)
	public Timestamp getFirstLendDate() {
		return this.firstLendDate;
	}

	/** 初始出借日期 */
	public void setFirstLendDate(Timestamp firstLendDate) {
		this.firstLendDate = firstLendDate;
	}

	private String lendType;// 出借及回收方式

	/** 出借及回收方式 */
	@Column(columnDefinition = DEF_STR64)
	public String getLendType() {
		return this.lendType;
	}

	/** 出借及回收方式 */
	public void setLendType(String lendType) {
		this.lendType = lendType;
	}

	private Double firstLendMoney;// 初始出借金额

	/** 初始出借金额 */
	@Column(columnDefinition = DEF_NUM15_5)
	public Double getFirstLendMoney() {
		return this.firstLendMoney;
	}

	/** 初始出借金额 */
	public void setFirstLendMoney(Double firstLendMoney) {
		this.firstLendMoney = firstLendMoney;
	}

	private Double shoudBack;// 本期应还金额

	/** 本期应还金额 */
	@Column(columnDefinition = DEF_NUM15_5)
	public Double getShoudBack() {
		return this.shoudBack;
	}

	/** 本期应还金额 */
	public void setShoudBack(Double shoudBack) {
		this.shoudBack = shoudBack;
	}

	private Double realBack;// 本期实际还款金额

	/** 本期实际还款金额 */
	@Column(columnDefinition = DEF_NUM15_5)
	public Double getRealBack() {
		return this.realBack;
	}

	/** 本期实际还款金额 */
	public void setRealBack(Double realBack) {
		this.realBack = realBack;
	}

	private Double latePayMoney;// 延迟支付金额

	/** 延迟支付金额 */
	@Column(columnDefinition = DEF_NUM15_5)
	public Double getLatePayMoney() {
		return this.latePayMoney;
	}

	/** 延迟支付金额 */
	public void setLatePayMoney(Double latePayMoney) {
		this.latePayMoney = latePayMoney;
	}

	private Double mngFeeRate;// 账户管理费率

	/** 账户管理费率 */
	@Column(columnDefinition = DEF_NUM15_5)
	public Double getMngFeeRate() {
		return this.mngFeeRate;
	}

	/** 账户管理费率 */
	public void setMngFeeRate(Double mngFeeRate) {
		this.mngFeeRate = mngFeeRate;
	}

	private Double mngFee;// 账户管理费

	/** 账户管理费 */
	@Column(columnDefinition = DEF_NUM15_5)
	public Double getMngFee() {
		return this.mngFee;
	}

	/** 账户管理费 */
	public void setMngFee(Double mngFee) {
		this.mngFee = mngFee;
	}

	private Double reLend;// 当期受让金额

	/** 当期受让金额 */
	@Column(columnDefinition = DEF_NUM15_5)
	public Double getReLend() {
		return this.reLend;
	}

	/** 当期受让金额 */
	public void setReLend(Double reLend) {
		this.reLend = reLend;
	}

	private Double drawing;// 当期回收金额

	/** 当期回收金额 */
	@Column(columnDefinition = DEF_NUM15_5)
	public Double getDrawing() {
		return this.drawing;
	}

	/** 当期回收金额 */
	public void setDrawing(Double drawing) {
		this.drawing = drawing;
	}

	private Double allMoney;// 当前全部账户资产

	/** 当前全部账户资产 */
	@Column(columnDefinition = DEF_NUM15_5)
	public Double getAllMoney() {
		return this.allMoney;
	}

	/** 当前全部账户资产 */
	public void setAllMoney(Double allMoney) {
		this.allMoney = allMoney;
	}
}

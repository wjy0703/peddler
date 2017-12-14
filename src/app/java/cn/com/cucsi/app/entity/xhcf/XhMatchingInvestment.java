package cn.com.cucsi.app.entity.xhcf;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
import org.springframework.format.annotation.DateTimeFormat;

import cn.com.cucsi.core.orm.hibernate.AuditableEntity;

/**
 * XhMatchingInvestment entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
// 表名与类名不相同时重新定义表名.
@Table(name = "XH_MATCHING_INVESTMENT")
// 默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XhMatchingInvestment extends AuditableEntity {

	// Fields
	private static final long serialVersionUID = -2276242824197821671L;
	private XhTzsq investApply;// 投资申请

	/** 投资申请 */
	@OneToOne(cascade = CascadeType.REFRESH, optional = false,fetch = FetchType.LAZY)
	@JoinColumn(name = "INVEST_APPLY_ID")
	public XhTzsq getInvestApply() {
		return this.investApply;
	}

	/** 投资申请 */
	public void setInvestApply(XhTzsq investApply) {
		this.investApply = investApply;
	}

	private Date firstInvestDate;// 出借日期

	/** 出借日期 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getFirstInvestDate() {
		return this.firstInvestDate;
	}

	/** 出借日期 */
	public void setFirstInvestDate(Date firstInvestDate) {
		this.firstInvestDate = firstInvestDate;
	}

	private Integer billDay;// 账单日

	/** 账单日 */
	@Column(columnDefinition = DEF_NUM18)
	public Integer getBillDay() {
		return this.billDay;
	}

	/** 账单日 */
	public void setBillDay(Integer billDay) {
		this.billDay = billDay;
	}

	private Integer investType;// 出借方式（ 产品类型）

	/** 出借方式（ 产品类型） */
	@Column(columnDefinition = DEF_NUM18)
	public Integer getInvestType() {
		return this.investType;
	}

	/** 出借方式（ 产品类型） */
	public void setInvestType(Integer investType) {
		this.investType = investType;
	}

	private Double matchedAmount;// 已匹配金额

	/** 已匹配金额 */
	@Column(columnDefinition = DEF_NUM10_2)
	public Double getMatchedAmount() {
		return this.matchedAmount;
	}

	/** 已匹配金额 */
	public void setMatchedAmount(Double matchedAmount) {
		this.matchedAmount = matchedAmount;
	}

	private Double matchingAmount;// 待匹配金额

	/** 待匹配金额 */
	@Column(columnDefinition = DEF_NUM10_2)
	public Double getMatchingAmount() {
		return this.matchingAmount;
	}

	/** 待匹配金额 */
	public void setMatchingAmount(Double matchingAmount) {
		this.matchingAmount = matchingAmount;
	}

	private Date deductDate;// 划扣日期

	/** 划扣日期 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getDeductDate() {
		return this.deductDate;
	}

	/** 划扣日期 */
	public void setDeductDate(Date deductDate) {
		this.deductDate = deductDate;
	}

	private Date deliveryDate;// 交割日期

	/** 交割日期 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getDeliveryDate() {
		return this.deliveryDate;
	}

	/** 交割日期 */
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	private Integer investState;// 状态 0: 未完成匹配 1:完成匹配 2 待审批 3 审核拒绝 4 审核通过，待订购，
								// 5取消订购 6 已订购，带交割 7已交割

	/** 状态 0: 未完成匹配 1:完成匹配 2 待审批 3 审核拒绝 4 审核通过，待订购， 5取消订购 6 已订购，带交割 7已交割 */
	@Column(columnDefinition = DEF_NUM18)
	public Integer getInvestState() {
		return this.investState;
	}

	/** 状态 0: 未完成匹配 1:完成匹配 2 待审批 3 审核拒绝 4 审核通过，待订购， 5取消订购 6 已订购，带交割 7已交割 */
	public void setInvestState(Integer investState) {
		this.investState = investState;
	}

	private Integer investMode;// 0首期 1非首期

	/** 0首期 1非首期 */
	@Column(columnDefinition = DEF_NUM18)
	public Integer getInvestMode() {
		return this.investMode;
	}

	/** 0首期 1非首期 */
	public void setInvestMode(Integer investMode) {
		this.investMode = investMode;
	}
}

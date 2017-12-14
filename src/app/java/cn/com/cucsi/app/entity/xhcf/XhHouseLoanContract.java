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

import cn.com.cucsi.app.entity.baseinfo.MiddleMan;
import cn.com.cucsi.core.orm.hibernate.AuditableEntity;

/**
 * XhHouseLoanContract entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
// 表名与类名不相同时重新定义表名.
@Table(name = "XH_HOUSE_LOAN_CONTRACT")
// 默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XhHouseLoanContract extends AuditableEntity {

	// Fields
	private static final long serialVersionUID = -2276242824197821671L;
	
	
	
	private String contractAduitState;// 合同审核意见

	/** 合同审核意见 */
	@Column(columnDefinition = DEF_STR50)
	public String getContractAduitState() {
		return this.contractAduitState;
	}
	
	/** 合同审核意见 */
	public void setContractAduitState(String contractAduitState) {
		this.contractAduitState = contractAduitState;
	}
	
	
	private String contractAduitResult;// 合同审核意见

	/** 合同审核意见 */
	@Column(columnDefinition = DEF_STR50)
	public String getContractAduitResult() {
		return this.contractAduitResult;
	}
	
	/** 合同审核意见 */
	public void setContractAduitResult(String contractAduitResult) {
		this.contractAduitResult = contractAduitResult;
	}
	
	
	private String contractNum;// 合同编号

	/** 合同编号 */
	@Column(columnDefinition = DEF_STR50)
	public String getContractNum() {
		return this.contractNum;
	}

	/** 合同编号 */
	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}

	private Double contractMoney;// 合同金额

	/** 合同金额 */
	@Column(columnDefinition = DEF_NUM15_5)
	public Double getContractMoney() {
		return this.contractMoney;
	}

	/** 合同金额 */
	public void setContractMoney(Double contractMoney) {
		this.contractMoney = contractMoney;
	}

	private Date contractDate;// 合同日期

	/** 合同日期 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getContractDate() {
		return this.contractDate;
	}

	/** 合同日期 */
	public void setContractDate(Date contractDate) {
		this.contractDate = contractDate;
	}

	private Long state;// 0：待签约 1：已签约上传 -1：取消

	/** 0：待签约 1：已签约上传 -1：取消 */
	@Column(columnDefinition = DEF_NUM18)
	public Long getState() {
		return this.state;
	}

	/** 0：待签约 1：已签约上传 -1：取消 */
	public void setState(Long state) {
		this.state = state;
	}

	private XhHouseLoanAuditInfo xhHouseLoanAuditInfo;// 信审ID

	private XhHouseLoanApply xhHouseLoanApply;// 申请表ID

	@OneToOne(cascade = CascadeType.REFRESH, optional = false,fetch = FetchType.LAZY)
	@JoinColumn(name = "AUDIT_INFO_ID")
	public XhHouseLoanAuditInfo getXhHouseLoanAuditInfo() {
		return xhHouseLoanAuditInfo;
	}

	public void setXhHouseLoanAuditInfo(
			XhHouseLoanAuditInfo xhHouseLoanAuditInfo) {
		this.xhHouseLoanAuditInfo = xhHouseLoanAuditInfo;
	}

	@OneToOne(cascade = CascadeType.REFRESH, optional = false,fetch = FetchType.LAZY)
	@JoinColumn(name = "LOAN_APPLY_ID")
	public XhHouseLoanApply getXhHouseLoanApply() {
		return xhHouseLoanApply;
	}

	public void setXhHouseLoanApply(XhHouseLoanApply xhHouseLoanApply) {
		this.xhHouseLoanApply = xhHouseLoanApply;
	}

	private String remark;// 备注

	/** 备注 */
	@Column(columnDefinition = DEF_STR200)
	public String getRemark() {
		return this.remark;
	}

	/** 备注 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	private MiddleMan middleMan;// 出借人ID

	@ManyToOne(cascade = CascadeType.REFRESH, optional = false,fetch = FetchType.LAZY)
	@JoinColumn(name = "MIDDLE_MAN_ID")
	public MiddleMan getMiddleMan() {
		return middleMan;
	}

	public void setMiddleMan(MiddleMan middleMan) {
		this.middleMan = middleMan;
	}

	private XhJkht jkht;

	@OneToOne(cascade = CascadeType.REFRESH, optional = false,fetch = FetchType.LAZY)
	@JoinColumn(name = "XH_JKHT_ID")
	public XhJkht getJkht() {
		return jkht;
	}

	public void setJkht(XhJkht jkht) {
		this.jkht = jkht;
	}
}

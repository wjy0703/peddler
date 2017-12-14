package cn.com.cucsi.app.entity.xhcf;

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
 * XhHouseLoanAudit entity. 
 * @author MyEclipse Persistence Tools
 */
@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "XH_HOUSE_LOAN_AUDIT")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XhHouseLoanAudit extends AuditableEntity{

	// Fields
	private static final long serialVersionUID = -2276242824197821671L;
	private String houseAddress;//房屋所在地
	/**房屋所在地*/
	@Column(columnDefinition=DEF_STR50)
	public String getHouseAddress() {
		return this.houseAddress;
	}
	/**房屋所在地*/
	public void setHouseAddress(String houseAddress) {
		this.houseAddress = houseAddress;
	}
	private String houseAroundObject;//房屋周边设施
	/**房屋周边设施*/
	@Column(columnDefinition=DEF_STR200)
	public String getHouseAroundObject() {
		return this.houseAroundObject;
	}
	/**房屋周边设施*/
	public void setHouseAroundObject(String houseAroundObject) {
		this.houseAroundObject = houseAroundObject;
	}
	private Double houseBankMortgageValue;//银行抵押金额
	/**银行抵押金额*/
	@Column(columnDefinition=DEF_NUM15_5)
	public Double getHouseBankMortgageValue() {
		return this.houseBankMortgageValue;
	}
	/**银行抵押金额*/
	public void setHouseBankMortgageValue(Double houseBankMortgageValue) {
		this.houseBankMortgageValue = houseBankMortgageValue;
	}
	private String loanReason;//用款目的
	/**用款目的*/
	@Column(columnDefinition=DEF_STR50)
	public String getLoanReason() {
		return this.loanReason;
	}
	/**用款目的*/
	public void setLoanReason(String loanReason) {
		this.loanReason = loanReason;
	}
	private String repaySource;//还款来源
	/**还款来源*/
	@Column(columnDefinition=DEF_STR50)
	public String getRepaySource() {
		return this.repaySource;
	}
	/**还款来源*/
	public void setRepaySource(String repaySource) {
		this.repaySource = repaySource;
	}
	private Double makableValue;//可放款金额
	/**可放款金额*/
	@Column(columnDefinition=DEF_NUM15_5)
	public Double getMakableValue() {
		return this.makableValue;
	}
	/**可放款金额*/
	public void setMakableValue(Double makableValue) {
		this.makableValue = makableValue;
	}
	private String otherAuditInfo;//其它审核信息
	/**其它审核信息*/
	@Column(columnDefinition=DEF_STR200)
	public String getOtherAuditInfo() {
		return this.otherAuditInfo;
	}
	/**其它审核信息*/
	public void setOtherAuditInfo(String otherAuditInfo) {
		this.otherAuditInfo = otherAuditInfo;
	}
	private Double buildingArea;//房屋建筑面积
	/**房屋建筑面积*/
	@Column(columnDefinition=DEF_NUM15_5)
	public Double getBuildingArea() {
		return this.buildingArea;
	}
	/**房屋建筑面积*/
	public void setBuildingArea(Double buildingArea) {
		this.buildingArea = buildingArea;
	}
	private Double buildingYears;//房屋年限
	/**房屋年限*/
	@Column(columnDefinition=DEF_NUM18_2)
	public Double getBuildingYears() {
		return this.buildingYears;
	}
	/**房屋年限*/
	public void setBuildingYears(Double buildingYears) {
		this.buildingYears = buildingYears;
	}
	private Double aduitUnitPrice;//综合评定单价
	/**综合评定单价*/
	@Column(columnDefinition=DEF_NUM15_5)
	public Double getAduitUnitPrice() {
		return this.aduitUnitPrice;
	}
	/**综合评定单价*/
	public void setAduitUnitPrice(Double aduitUnitPrice) {
		this.aduitUnitPrice = aduitUnitPrice;
	}
	private Double marketUnitPrice;//市场价值
	/**市场价值*/
	@Column(columnDefinition=DEF_NUM15_5)
	public Double getMarketUnitPrice() {
		return this.marketUnitPrice;
	}
	/**市场价值*/
	public void setMarketUnitPrice(Double marketUnitPrice) {
		this.marketUnitPrice = marketUnitPrice;
	}
	private String mediumOneName;//中介1名称
	/**中介1名称*/
	@Column(columnDefinition=DEF_STR50)
	public String getMediumOneName() {
		return this.mediumOneName;
	}
	/**中介1名称*/
	public void setMediumOneName(String mediumOneName) {
		this.mediumOneName = mediumOneName;
	}
	private Double mediumOnePrice;//中介1收房价
	/**中介1收房价*/
	@Column(columnDefinition=DEF_NUM15_5)
	public Double getMediumOnePrice() {
		return this.mediumOnePrice;
	}
	/**中介1收房价*/
	public void setMediumOnePrice(Double mediumOnePrice) {
		this.mediumOnePrice = mediumOnePrice;
	}
	private String mediumTwoName;//中介2名称
	/**中介2名称*/
	@Column(columnDefinition=DEF_STR50)
	public String getMediumTwoName() {
		return this.mediumTwoName;
	}
	/**中介2名称*/
	public void setMediumTwoName(String mediumTwoName) {
		this.mediumTwoName = mediumTwoName;
	}
	private Double mediumTwoPrice;//中介2收房价
	/**中介2收房价*/
	@Column(columnDefinition=DEF_NUM15_5)
	public Double getMediumTwoPrice() {
		return this.mediumTwoPrice;
	}
	/**中介2收房价*/
	public void setMediumTwoPrice(Double mediumTwoPrice) {
		this.mediumTwoPrice = mediumTwoPrice;
	}
	private String mediumThreeName;//中介3名称
	/**中介3名称*/
	@Column(columnDefinition=DEF_STR50)
	public String getMediumThreeName() {
		return this.mediumThreeName;
	}
	/**中介3名称*/
	public void setMediumThreeName(String mediumThreeName) {
		this.mediumThreeName = mediumThreeName;
	}
	private Double mediumThreePrice;//中介3收房价
	/**中介3收房价*/
	@Column(columnDefinition=DEF_NUM15_5)
	public Double getMediumThreePrice() {
		return this.mediumThreePrice;
	}
	/**中介3收房价*/
	public void setMediumThreePrice(Double mediumThreePrice) {
		this.mediumThreePrice = mediumThreePrice;
	}
	private String aduitState;//审核状态
	/**审核状态*/
	@Column(columnDefinition=DEF_STR20)
	public String getAduitState() {
		return this.aduitState;
	}
	/**审核状态*/
	public void setAduitState(String aduitState) {
		this.aduitState = aduitState;
	}
	private String aduitResult;//审核结果
	/**审核结果*/
	@Column(columnDefinition=DEF_STR20)
	public String getAduitResult() {
		return this.aduitResult;
	}
	/**审核结果*/
	public void setAduitResult(String aduitResult) {
		this.aduitResult = aduitResult;
	}
	private String aduitSuggestion;//审核意见
	/**审核意见*/
	@Column(columnDefinition=DEF_STR20)
	public String getAduitSuggestion() {
		return this.aduitSuggestion;
	}
	/**审核意见*/
	public void setAduitSuggestion(String aduitSuggestion) {
		this.aduitSuggestion = aduitSuggestion;
	}
	
	private XhHouseLoanApply xhHouseLoanApply;

	@OneToOne(cascade = CascadeType.REFRESH, optional = false,fetch = FetchType.LAZY)
	@JoinColumn(name = "LOAN_APPLY_ID")
	public XhHouseLoanApply getXhHouseLoanApply() {
		return xhHouseLoanApply;
	}

	public void setXhHouseLoanApply(XhHouseLoanApply xhHouseLoanApply) {
		this.xhHouseLoanApply = xhHouseLoanApply;
	}
}

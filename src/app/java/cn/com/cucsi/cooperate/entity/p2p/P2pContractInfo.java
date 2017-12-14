package cn.com.cucsi.cooperate.entity.p2p;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import cn.com.cucsi.core.orm.hibernate.IdEntity;

/**
 * P2pContractInfo entity. 
 * @author MyEclipse Persistence Tools
 */
@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "P2P_CONTRACT_INFO")
public class P2pContractInfo extends IdEntity{

	// Fields
	private static final long serialVersionUID = -2276242824197821671L;
	private Long targetId;//借款标的Idjksqid
	/**借款标的Idjksqid*/
	@Column(columnDefinition=DEF_NUM18)
	public Long getTargetId() {
		return this.targetId;
	}
	/**借款标的Idjksqid*/
	public void setTargetId(Long targetId) {
		this.targetId = targetId;
	}
	private String contNumber;//合同编号
	/**合同编号*/
	@Column(columnDefinition=DEF_STR50)
	public String getContNumber() {
		return this.contNumber;
	}
	/**合同编号*/
	public void setContNumber(String contNumber) {
		this.contNumber = contNumber;
	}
	private String contUrl;//合同的超级连接
	/**合同的超级连接*/
	@Column(columnDefinition=DEF_STR512)
	public String getContUrl() {
		return this.contUrl;
	}
	/**合同的超级连接*/
	public void setContUrl(String contUrl) {
		this.contUrl = contUrl;
	}
	private String contStateUrl;//声明书的超级连接
	/**声明书的超级连接*/
	@Column(columnDefinition=DEF_STR512)
	public String getContStateUrl() {
		return this.contStateUrl;
	}
	/**声明书的超级连接*/
	public void setContStateUrl(String contStateUrl) {
		this.contStateUrl = contStateUrl;
	}
	private Double contAmount;//合同金额
	/**合同金额*/
	@Column(columnDefinition=DEF_NUM18_5)
	public Double getContAmount() {
		return this.contAmount;
	}
	/**合同金额*/
	public void setContAmount(Double contAmount) {
		this.contAmount = contAmount;
	}
	private String status;//合同状态
	/**合同状态*/
	@Column(columnDefinition=DEF_STR10)
	public String getStatus() {
		return this.status;
	}
	/**合同状态*/
	public void setStatus(String status) {
		this.status = status;
	}
	private Timestamp createTime;//合同生成时间
	/**合同生成时间*/
	@Column(insertable = false)
	public Timestamp getCreateTime() {
		return this.createTime;
	}
	/**合同生成时间*/
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	private Timestamp effectTime;//合同生效时间
	/**合同生效时间*/
	@Column(insertable = false)
	public Timestamp getEffectTime() {
		return this.effectTime;
	}
	/**合同生效时间*/
	public void setEffectTime(Timestamp effectTime) {
		this.effectTime = effectTime;
	}
	private Long accountId;//借款人Id
	/**借款人Id*/
	@Column(columnDefinition=DEF_NUM18)
	public Long getAccountId() {
		return this.accountId;
	}
	/**借款人Id*/
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	private Double payOutMoney;//放款金额
	/**放款金额*/
	@Column(columnDefinition=DEF_NUM18_5)
	public Double getPayOutMoney() {
		return this.payOutMoney;
	}
	/**放款金额*/
	public void setPayOutMoney(Double payOutMoney) {
		this.payOutMoney = payOutMoney;
	}
	private Timestamp signTime;//借款人签订时间
	/**借款人签订时间*/
	@Column(insertable = false)
	public Timestamp getSignTime() {
		return this.signTime;
	}
	/**借款人签订时间*/
	public void setSignTime(Timestamp signTime) {
		this.signTime = signTime;
	}
	private Long investId;//出借人Id，P2P网站生成的Id
	/**出借人Id，P2P网站生成的Id*/
	@Column(columnDefinition=DEF_NUM18)
	public Long getInvestId() {
		return this.investId;
	}
	/**出借人Id，P2P网站生成的Id*/
	public void setInvestId(Long investId) {
		this.investId = investId;
	}
	private Double payInMoney;//出借人划扣金额
	/**出借人划扣金额*/
	@Column(columnDefinition=DEF_NUM18_5)
	public Double getPayInMoney() {
		return this.payInMoney;
	}
	/**出借人划扣金额*/
	public void setPayInMoney(Double payInMoney) {
		this.payInMoney = payInMoney;
	}
	private Timestamp investTime;//出借人签订时间
	/**出借人签订时间*/
	@Column(insertable = false)
	public Timestamp getInvestTime() {
		return this.investTime;
	}
	/**出借人签订时间*/
	public void setInvestTime(Timestamp investTime) {
		this.investTime = investTime;
	}
	private String brType;//借款类型
	/**借款类型*/
	@Column(columnDefinition=DEF_STR30)
	public String getBrType() {
		return this.brType;
	}
	/**借款类型*/
	public void setBrType(String brType) {
		this.brType = brType;
	}
	private Double brRate;//借款利率
	/**借款利率*/
	@Column(columnDefinition=DEF_NUM18_5)
	public Double getBrRate() {
		return this.brRate;
	}
	/**借款利率*/
	public void setBrRate(Double brRate) {
		this.brRate = brRate;
	}
	private Double icRate;//年化利率
	/**年化利率*/
	@Column(columnDefinition=DEF_NUM18_5)
	public Double getIcRate() {
		return this.icRate;
	}
	/**年化利率*/
	public void setIcRate(Double icRate) {
		this.icRate = icRate;
	}
	private Long term;//期数
	/**期数*/
	@Column(columnDefinition=DEF_NUM18)
	public Long getTerm() {
		return this.term;
	}
	/**期数*/
	public void setTerm(Long term) {
		this.term = term;
	}
	private Long contId;//P2P生成的合同Id
	/**P2P生成的合同Id*/
	@Column(columnDefinition=DEF_NUM18)
	public Long getContId() {
		return this.contId;
	}
	/**P2P生成的合同Id*/
	public void setContId(Long contId) {
		this.contId = contId;
	}
}

package cn.com.cucsi.cooperate.entity.p2p;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import cn.com.cucsi.core.orm.hibernate.IdEntity;

/**
 * P2pLendInfo entity. 
 * @author MyEclipse Persistence Tools
 */
@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "P2P_LEND_INFO")
public class P2pLendInfo extends IdEntity{

	// Fields
	private static final long serialVersionUID = -2276242824197821671L;
	
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
	private String idType;//出借人证件类型
	/**出借人证件类型*/
	@Column(columnDefinition=DEF_STR32)
	public String getIdType() {
		return this.idType;
	}
	/**出借人证件类型*/
	public void setIdType(String idType) {
		this.idType = idType;
	}
	private String idNum;//出借人证件号
	/**出借人证件号*/
	@Column(columnDefinition=DEF_STR50)
	public String getIdNum() {
		return this.idNum;
	}
	/**出借人证件号*/
	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}
	private String userName;//出借人姓名
	/**出借人姓名*/
	@Column(columnDefinition=DEF_STR50)
	public String getUserName() {
		return this.userName;
	}
	/**出借人姓名*/
	public void setUserName(String userName) {
		this.userName = userName;
	}
	private String bankCode;//银行编码
	/**银行编码*/
	@Column(columnDefinition=DEF_STR50)
	public String getBankCode() {
		return this.bankCode;
	}
	/**银行编码*/
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	private String accountBank;//开户行名称
	/**开户行名称*/
	@Column(columnDefinition=DEF_STR50)
	public String getAccountBank() {
		return this.accountBank;
	}
	/**开户行名称*/
	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
	}
	private String accountNo;//银行卡号
	/**银行卡号*/
	@Column(columnDefinition=DEF_STR50)
	public String getAccountNo() {
		return this.accountNo;
	}
	/**银行卡号*/
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	private String telNo;//联系电话
	/**联系电话*/
	@Column(columnDefinition=DEF_STR50)
	public String getTelNo() {
		return this.telNo;
	}
	/**联系电话*/
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	private String email;//邮箱
	/**邮箱*/
	@Column(columnDefinition=DEF_STR100)
	public String getEmail() {
		return this.email;
	}
	/**邮箱*/
	public void setEmail(String email) {
		this.email = email;
	}
	private Long contractId;//chp合同ID
	/**chp合同ID*/
	@Column(columnDefinition=DEF_NUM18)
	public Long getContractId() {
		return this.contractId;
	}
	/**chp合同ID*/
	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}
	private Double investAmount;//出借人投资金额
	/**出借人投资金额*/
	@Column(columnDefinition=DEF_NUM18_5)
	public Double getInvestAmount() {
		return this.investAmount;
	}
	/**出借人投资金额*/
	public void setInvestAmount(Double investAmount) {
		this.investAmount = investAmount;
	}
	private String investStatus;//划扣状态
	/**划扣状态*/
	@Column(columnDefinition=DEF_STR32)
	public String getInvestStatus() {
		return this.investStatus;
	}
	/**划扣状态*/
	public void setInvestStatus(String investStatus) {
		this.investStatus = investStatus;
	}
	private Double investPayMy;//划扣金额
	/**划扣金额*/
	@Column(columnDefinition=DEF_NUM18_5)
	public Double getInvestPayMy() {
		return this.investPayMy;
	}
	/**划扣金额*/
	public void setInvestPayMy(Double investPayMy) {
		this.investPayMy = investPayMy;
	}
	private Timestamp investDateTime;//划扣时间
	/**划扣时间*/
	@Column(insertable = false)
	public Timestamp getInvestDateTime() {
		return this.investDateTime;
	}
	/**划扣时间*/
	public void setInvestDateTime(Timestamp investDateTime) {
		this.investDateTime = investDateTime;
	}
}

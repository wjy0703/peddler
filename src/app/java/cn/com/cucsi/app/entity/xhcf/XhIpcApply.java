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
 * XhIpcApply entity. 
 * @author MyEclipse Persistence Tools
 */
@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "XH_IPC_APPLY")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XhIpcApply extends AuditableEntity{

	// Fields
	private static final long serialVersionUID = -2276242824197821671L;
	private String customerName;//客户姓名
	/**客户姓名*/
	@Column(columnDefinition=DEF_STR50)
	public String getCustomerName() {
		return this.customerName;
	}
	/**客户姓名*/
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	private String customerNum;//客户编号
	/**客户编号*/
	@Column(columnDefinition=DEF_STR50)
	public String getCustomerNum() {
		return this.customerNum;
	}
	/**客户编号*/
	public void setCustomerNum(String customerNum) {
		this.customerNum = customerNum;
	}
	private String customerPhone;//客户电话
	/**客户电话*/
	@Column(columnDefinition=DEF_STR50)
	public String getCustomerPhone() {
		return this.customerPhone;
	}
	/**客户电话*/
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	private String customerCardId;//开户行
	/**开户行*/
	@Column(columnDefinition=DEF_STR50)
	public String getCustomerCardId() {
		return this.customerCardId;
	}
	/**开户行*/
	public void setCustomerCardId(String customerCardId) {
		this.customerCardId = customerCardId;
	}
	private String bankName;//账户名
	/**账户名*/
	@Column(columnDefinition=DEF_STR50)
	public String getBankName() {
		return this.bankName;
	}
	/**账户名*/
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	private String bankCardNum;//账（卡）号
	/**账（卡）号*/
	@Column(columnDefinition=DEF_STR50)
	public String getBankCardNum() {
		return this.bankCardNum;
	}
	/**账（卡）号*/
	public void setBankCardNum(String bankCardNum) {
		this.bankCardNum = bankCardNum;
	}
	private String togetherName;//共借人姓名
	/**共借人姓名*/
	@Column(columnDefinition=DEF_STR50)
	public String getTogetherName() {
		return this.togetherName;
	}
	/**共借人姓名*/
	public void setTogetherName(String togetherName) {
		this.togetherName = togetherName;
	}
	private String togetherRelation;//共借人关系
	/**共借人关系*/
	@Column(columnDefinition=DEF_STR50)
	public String getTogetherRelation() {
		return this.togetherRelation;
	}
	/**共借人关系*/
	public void setTogetherRelation(String togetherRelation) {
		this.togetherRelation = togetherRelation;
	}
	private String togetherPhone;//共借人电话
	/**共借人电话*/
	@Column(columnDefinition=DEF_STR50)
	public String getTogetherPhone() {
		return this.togetherPhone;
	}
	/**共借人电话*/
	public void setTogetherPhone(String togetherPhone) {
		this.togetherPhone = togetherPhone;
	}
	private String customerCompAddress;//商铺（单位）地址
	/**商铺（单位）地址*/
	@Column(columnDefinition=DEF_STR200)
	public String getCustomerCompAddress() {
		return this.customerCompAddress;
	}
	/**商铺（单位）地址*/
	public void setCustomerCompAddress(String customerCompAddress) {
		this.customerCompAddress = customerCompAddress;
	}
	private String customerHomeAddress;//家庭住址
	/**家庭住址*/
	@Column(columnDefinition=DEF_STR200)
	public String getCustomerHomeAddress() {
		return this.customerHomeAddress;
	}
	/**家庭住址*/
	public void setCustomerHomeAddress(String customerHomeAddress) {
		this.customerHomeAddress = customerHomeAddress;
	}
	private String togetherHomeAddress;//共借人住址
	/**共借人住址*/
	@Column(columnDefinition=DEF_STR200)
	public String getTogetherHomeAddress() {
		return this.togetherHomeAddress;
	}
	/**共借人住址*/
	public void setTogetherHomeAddress(String togetherHomeAddress) {
		this.togetherHomeAddress = togetherHomeAddress;
	}
	private String txl;//通讯录 
	/**通讯录 */
	@Column(columnDefinition=DEF_STR50)
	public String getTxl() {
		return this.txl;
	}
	/**通讯录 */
	public void setTxl(String txl) {
		this.txl = txl;
	}
	private String kfEmp;//开发信贷员

	/**开发信贷员
*/
	@Column(columnDefinition=DEF_STR50)
	public String getKfEmp() {
		return this.kfEmp;
	}
	/**开发信贷员
*/
	public void setKfEmp(String kfEmp) {
		this.kfEmp = kfEmp;
	}
	private String fzEmp;//负责信贷员
	/**负责信贷员*/
	@Column(columnDefinition=DEF_STR50)
	public String getFzEmp() {
		return this.fzEmp;
	}
	/**负责信贷员*/
	public void setFzEmp(String fzEmp) {
		this.fzEmp = fzEmp;
	}
	private String whEmp;//维护人员

	/**维护人员
*/
	@Column(columnDefinition=DEF_STR50)
	public String getWhEmp() {
		return this.whEmp;
	}
	/**维护人员
*/
	public void setWhEmp(String whEmp) {
		this.whEmp = whEmp;
	}
	private String loanType;//借款类型
	/**借款类型*/
	@Column(columnDefinition=DEF_STR50)
	public String getLoanType() {
		return this.loanType;
	}
	/**借款类型*/
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
	private String loanUse;//借款用途
	/**借款用途*/
	@Column(columnDefinition=DEF_STR50)
	public String getLoanUse() {
		return this.loanUse;
	}
	/**借款用途*/
	public void setLoanUse(String loanUse) {
		this.loanUse = loanUse;
	}
	
	
	private XhJksq loanApply;

	@OneToOne(cascade = CascadeType.REFRESH, optional = false,fetch = FetchType.LAZY)
	@JoinColumn(name = "LOAN_APPLY_ID")
	public XhJksq getLoanApply() {
		return loanApply;
	}

	public void setLoanApply(XhJksq loanApply) {
		this.loanApply = loanApply;
	}
	
	private String state;//状态 0：未录入合同 1：已录入合同
	@Column(columnDefinition=DEF_STR32)
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}

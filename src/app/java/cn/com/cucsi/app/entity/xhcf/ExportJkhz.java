package cn.com.cucsi.app.entity.xhcf;

import cn.com.cucsi.app.entity.baseinfo.Employee;

public class ExportJkhz {

	private String city;// 机构
	private String jkrxm;// 借款人姓名
	private String zjhm; // 证件号码
	private String customerNumber;// 客户编码
	private String loanProducts;// 贷款产品
	private String approvalAmount;// 审批金额
	private String contractAmount;// 合同金额
	private String loanNature;// 贷款性质
	private String rate;// 综合利率
	private String petitionCost;// 信访咨询费
	private String fkqs;// 放款期数
	private String fkDate;// 放款时间
	private String dqDate;// 到期时间
	private String fdtd;// 个贷团队
	private String gdjl;// 个贷经理
	private String sqDate;// 申请日期
	private String wfdz;// 外访地址
	private String wfDate;// 外访日期
	private String auditPerson;// 审核人
	private String auditDate;// 审核时间
	private String auditTeam;// 审核团队经理
	private Employee employeeCrm;
	private Employee employeeCca;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getJkrxm() {
		return jkrxm;
	}

	public void setJkrxm(String jkrxm) {
		this.jkrxm = jkrxm;
	}

	public String getZjhm() {
		return zjhm;
	}

	public void setZjhm(String zjhm) {
		this.zjhm = zjhm;
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getLoanProducts() {
		return loanProducts;
	}

	public void setLoanProducts(String loanProducts) {
		this.loanProducts = loanProducts;
	}

	public String getApprovalAmount() {
		return approvalAmount;
	}

	public void setApprovalAmount(String approvalAmount) {
		this.approvalAmount = approvalAmount;
	}

	public String getContractAmount() {
		return contractAmount;
	}

	public void setContractAmount(String contractAmount) {
		this.contractAmount = contractAmount;
	}

	public String getLoanNature() {
		return loanNature;
	}

	public void setLoanNature(String loanNature) {
		this.loanNature = loanNature;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getPetitionCost() {
		return petitionCost;
	}

	public void setPetitionCost(String petitionCost) {
		this.petitionCost = petitionCost;
	}

	public String getFkqs() {
		return fkqs;
	}

	public void setFkqs(String fkqs) {
		this.fkqs = fkqs;
	}

	public String getFkDate() {
		return fkDate;
	}

	public void setFkDate(String fkDate) {
		this.fkDate = fkDate;
	}

	public String getDqDate() {
		return dqDate;
	}

	public void setDqDate(String dqDate) {
		this.dqDate = dqDate;
	}

	public String getFdtd() {
		return fdtd;
	}

	public void setFdtd(String fdtd) {
		this.fdtd = fdtd;
	}

	public String getGdjl() {
		return gdjl;
	}

	public void setGdjl(String gdjl) {
		this.gdjl = gdjl;
	}

	public String getSqDate() {
		return sqDate;
	}

	public void setSqDate(String sqDate) {
		this.sqDate = sqDate;
	}

	public String getWfdz() {
		return wfdz;
	}

	public void setWfdz(String wfdz) {
		this.wfdz = wfdz;
	}

	public String getWfDate() {
		return wfDate;
	}

	public void setWfDate(String wfDate) {
		this.wfDate = wfDate;
	}

	public String getAuditPerson() {
		return auditPerson;
	}

	public void setAuditPerson(String auditPerson) {
		this.auditPerson = auditPerson;
	}

	public String getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(String auditDate) {
		this.auditDate = auditDate;
	}

	public String getAuditTeam() {
		return auditTeam;
	}

	public void setAuditTeam(String auditTeam) {
		this.auditTeam = auditTeam;
	}

	public Employee getEmployeeCrm() {
		return employeeCrm;
	}

	public void setEmployeeCrm(Employee employeeCrm) {
		this.employeeCrm = employeeCrm;
	}

	public Employee getEmployeeCca() {
		return employeeCca;
	}

	public void setEmployeeCca(Employee employeeCca) {
		this.employeeCca = employeeCca;
	}

	public ExportJkhz(String city, String jkrxm, String zjhm,
			String customerNumber, String loanProducts, String approvalAmount,
			String contractAmount, String loanNature, String rate,
			String petitionCost, String fkqs, String fkDate, String dqDate,
			String fdtd, String gdjl, String sqDate, String wfdz,
			String wfDate, String auditPerson, String auditDate,
			String auditTeam) {

		this.city = city;
		this.jkrxm = jkrxm;
		this.zjhm = zjhm;
		this.customerNumber = customerNumber;
		this.loanProducts = loanProducts;
		this.approvalAmount = approvalAmount;
		this.contractAmount = contractAmount;
		this.loanNature = loanNature;
		this.rate = rate;
		this.petitionCost = petitionCost;
		this.fkqs = fkqs;
		this.fkDate = fkDate;
		this.dqDate = dqDate;
		this.fdtd = fdtd;
		this.gdjl = gdjl;
		this.sqDate = sqDate;
		this.wfdz = wfdz;
		this.wfDate = wfDate;
		this.auditPerson = auditPerson;
		this.auditDate = auditDate;
		this.auditTeam = auditTeam;
	}
}

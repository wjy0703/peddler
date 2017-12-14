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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.format.annotation.DateTimeFormat;

import cn.com.cucsi.core.orm.hibernate.AuditableEntity;

/**
 * XhIpcConstract entity. 
 * @author MyEclipse Persistence Tools
 */
@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "XH_IPC_CONSTRACT")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XhIpcConstract extends AuditableEntity{

	// Fields
	private static final long serialVersionUID = -2276242824197821671L;
	private Date auditDate;//审核时间
	/**审核时间*/
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getAuditDate() {
		return this.auditDate;
	}
	/**审核时间*/
	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}
	private String auditIdea;//审核意见
	/**审核意见*/
	@Column(columnDefinition=DEF_STR512)
	public String getAuditIdea() {
		return this.auditIdea;
	}
	/**审核意见*/
	public void setAuditIdea(String auditIdea) {
		this.auditIdea = auditIdea;
	}
	private String auditPerson;//审核人
	/**审核人*/
	@Column(columnDefinition=DEF_STR32)
	public String getAuditPerson() {
		return this.auditPerson;
	}
	/**审核人*/
	public void setAuditPerson(String auditPerson) {
		this.auditPerson = auditPerson;
	}
	private Double fkje;//放款金额
	/**放款金额*/
	@Column(columnDefinition=DEF_NUM10_2)
	public Double getFkje() {
		return this.fkje;
	}
	/**放款金额*/
	public void setFkje(Double fkje) {
		this.fkje = fkje;
	}
	private Double fwf;//服务费
	/**服务费*/
	@Column(columnDefinition=DEF_NUM10_2)
	public Double getFwf() {
		return this.fwf;
	}
	/**服务费*/
	public void setFwf(Double fwf) {
		this.fwf = fwf;
	}
	private Double htje;//合同金额
	/**合同金额*/
	@Column(columnDefinition=DEF_NUM10_2)
	public Double getHtje() {
		return this.htje;
	}
	/**合同金额*/
	public void setHtje(Double htje) {
		this.htje = htje;
	}
	private String jkhtbm;//借款合同编号
	/**借款合同编号*/
	@Column(columnDefinition=DEF_STR20)
	public String getJkhtbm() {
		return this.jkhtbm;
	}
	/**借款合同编号*/
	public void setJkhtbm(String jkhtbm) {
		this.jkhtbm = jkhtbm;
	}
	private String qdrq;//合同签订日期
	/**合同签订日期*/
	@Column(columnDefinition=DEF_STR20)
	public String getQdrq() {
		return this.qdrq;
	}
	/**合同签订日期*/
	public void setQdrq(String qdrq) {
		this.qdrq = qdrq;
	}
	private String qshkrq;//起始还款日期
	/**起始还款日期*/
	@Column(columnDefinition=DEF_STR20)
	public String getQshkrq() {
		return this.qshkrq;
	}
	/**起始还款日期*/
	public void setQshkrq(String qshkrq) {
		this.qshkrq = qshkrq;
	}
	private String state;//状态0暂存,1待审批，2审批通过，3审批不通过，9删除
	/**状态0暂存,1待审批，2审批通过，3审批不通过，9删除*/
	@Column(columnDefinition=DEF_STR2)
	public String getState() {
		return this.state;
	}
	/**状态0暂存,1待审批，2审批通过，3审批不通过，9删除*/
	public void setState(String state) {
		this.state = state;
	}
	private Double xyshf;//信用审核费
	/**信用审核费*/
	@Column(columnDefinition=DEF_NUM10_2)
	public Double getXyshf() {
		return this.xyshf;
	}
	/**信用审核费*/
	public void setXyshf(Double xyshf) {
		this.xyshf = xyshf;
	}
	private Double ybjje;//月本金金额
	/**月本金金额*/
	@Column(columnDefinition=DEF_NUM10_2)
	public Double getYbjje() {
		return this.ybjje;
	}
	/**月本金金额*/
	public void setYbjje(Double ybjje) {
		this.ybjje = ybjje;
	}
	private Double yhkje;//月还款金额
	/**月还款金额*/
	@Column(columnDefinition=DEF_NUM10_2)
	public Double getYhkje() {
		return this.yhkje;
	}
	/**月还款金额*/
	public void setYhkje(Double yhkje) {
		this.yhkje = yhkje;
	}
	private Double ylxje;//月利息金额
	/**月利息金额*/
	@Column(columnDefinition=DEF_NUM10_2)
	public Double getYlxje() {
		return this.ylxje;
	}
	/**月利息金额*/
	public void setYlxje(Double ylxje) {
		this.ylxje = ylxje;
	}
	private Double zhglf;//账户管理费
	/**账户管理费*/
	@Column(columnDefinition=DEF_NUM10_2)
	public Double getZhglf() {
		return this.zhglf;
	}
	/**账户管理费*/
	public void setZhglf(Double zhglf) {
		this.zhglf = zhglf;
	}
	private Double zxf;//咨询费
	/**咨询费*/
	@Column(columnDefinition=DEF_NUM10_2)
	public Double getZxf() {
		return this.zxf;
	}
	/**咨询费*/
	public void setZxf(Double zxf) {
		this.zxf = zxf;
	}
	private Double yzhfl;//综合费率
	/**综合费率*/
	@Column(columnDefinition=DEF_NUM10_2)
	public Double getYzhfl() {
		return this.yzhfl;
	}
	/**综合费率*/
	public void setYzhfl(Double yzhfl) {
		this.yzhfl = yzhfl;
	}
	private Long hkqs;//还款期数
	/**还款期数*/
	@Column(columnDefinition=DEF_NUM2)
	public Long getHkqs() {
		return this.hkqs;
	}
	/**还款期数*/
	public void setHkqs(Long hkqs) {
		this.hkqs = hkqs;
	}
	private Double xff;//信访费
	/**信访费*/
	@Column(columnDefinition=DEF_NUM10_2)
	public Double getXff() {
		return this.xff;
	}
	/**信访费*/
	public void setXff(Double xff) {
		this.xff = xff;
	}
	private Date auditQzqrDate;//签约确认审核时间
	/**签约确认审核时间*/
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getAuditQzqrDate() {
		return this.auditQzqrDate;
	}
	/**签约确认审核时间*/
	public void setAuditQzqrDate(Date auditQzqrDate) {
		this.auditQzqrDate = auditQzqrDate;
	}
	private String auditQzqrIdea;//签约确认审核意见
	/**签约确认审核意见*/
	@Column(columnDefinition=DEF_STR512)
	public String getAuditQzqrIdea() {
		return this.auditQzqrIdea;
	}
	/**签约确认审核意见*/
	public void setAuditQzqrIdea(String auditQzqrIdea) {
		this.auditQzqrIdea = auditQzqrIdea;
	}
	private String auditQzqrPerson;//签约确认审核人
	/**签约确认审核人*/
	@Column(columnDefinition=DEF_STR32)
	public String getAuditQzqrPerson() {
		return this.auditQzqrPerson;
	}
	/**签约确认审核人*/
	public void setAuditQzqrPerson(String auditQzqrPerson) {
		this.auditQzqrPerson = auditQzqrPerson;
	}
	private Double dkll;//贷款利率
	/**贷款利率*/
	@Column(columnDefinition=DEF_NUM10_2)
	public Double getDkll() {
		return this.dkll;
	}
	/**贷款利率*/
	public void setDkll(Double dkll) {
		this.dkll = dkll;
	}
	private Double pdje;//批贷金额
	/**批贷金额*/
	@Column(columnDefinition=DEF_NUM10_2)
	public Double getPdje() {
		return this.pdje;
	}
	/**批贷金额*/
	public void setPdje(Double pdje) {
		this.pdje = pdje;
	}
	private Double syje;//剩余金额
	/**剩余金额*/
	@Column(columnDefinition=DEF_NUM10_2)
	public Double getSyje() {
		return this.syje;
	}
	/**剩余金额*/
	public void setSyje(Double syje) {
		this.syje = syje;
	}
	private Double syll;//剩余利率
	/**剩余利率*/
	@Column(columnDefinition=DEF_NUM10_2)
	public Double getSyll() {
		return this.syll;
	}
	/**剩余利率*/
	public void setSyll(Double syll) {
		this.syll = syll;
	}
	private Long middlemanId;//居间人ID
	/**居间人ID*/
	@Column(columnDefinition=DEF_NUM18)
	public Long getMiddlemanId() {
		return this.middlemanId;
	}
	/**居间人ID*/
	public void setMiddlemanId(Long middlemanId) {
		this.middlemanId = middlemanId;
	}
	private String hkr;//还款日
	/**还款日*/
	@Column(columnDefinition=DEF_STR2)
	public String getHkr() {
		return this.hkr;
	}
	/**还款日*/
	public void setHkr(String hkr) {
		this.hkr = hkr;
	}
	private String tmpName;//临时姓名
	/**临时姓名*/
	@Column(columnDefinition=DEF_STR32)
	public String getTmpName() {
		return this.tmpName;
	}
	/**临时姓名*/
	public void setTmpName(String tmpName) {
		this.tmpName = tmpName;
	}
//	private Long ipcApplyId;//申请ID
//	/**申请ID*/
//	@Column(columnDefinition=DEF_NUM18)
//	public Long getIpcApplyId() {
//		return this.ipcApplyId;
//	}
//	/**申请ID*/
//	public void setIpcApplyId(Long ipcApplyId) {
//		this.ipcApplyId = ipcApplyId;
//	}
	
	
	
	
	private XhIpcApply ipcApply;

	@OneToOne(cascade = CascadeType.REFRESH, optional = false,fetch = FetchType.LAZY)
	@JoinColumn(name = "IPC_APPLY_ID")
	public XhIpcApply getIpcApply() {
		return ipcApply;
	}
	public void setIpcApply(XhIpcApply ipcApply) {
		this.ipcApply = ipcApply;
	}
	
	private XhJkht jkht;

	
	@OneToOne(cascade = CascadeType.REFRESH, optional = false,fetch = FetchType.LAZY)
	@JoinColumn(name = "JKHT_ID")
	public XhJkht getJkht() {
		return jkht;
	}

	public void setJkht(XhJkht jkht) {
		this.jkht = jkht;
	}
	
}

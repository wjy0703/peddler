package cn.com.cucsi.app.entity.xhcf;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import cn.com.cucsi.app.entity.baseinfo.Employee;
import cn.com.cucsi.app.entity.baseinfo.Organi;
import cn.com.cucsi.app.entity.baseinfo.MiddleMan;
import cn.com.cucsi.app.entity.xhcf.XhJksq;
import cn.com.cucsi.core.orm.hibernate.AuditableEntity;

import cn.com.cucsi.core.orm.hibernate.AuditableEntity;

/**
 * XhJkht entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
// 表名与类名不相同时重新定义表名.
@Table(name = "XH_JKHT")
// 默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XhJkht extends AuditableEntity {

	// Fields
	private static final long serialVersionUID = -2276242824197821671L;
	private int hkqs;// 还款期数
	private String hkr;// 还款日
	private Double yzhfl;// 月综合费率

	private Double zxf;// 咨询费

	private Double zhglf;// 账户管理费

	private Double ylxje;// 月利息金额

//	private Double yll;// 月利率

	private Double yhkje;// 月还款金额

	private Double ybjje;// 月本金金额

	private Double xyshf;// 信用审核费

	private String state;// 状态0暂存,1待审批，2审批通过，3审批不通过，9删除

	private XhJksq xhJksq;

	private MiddleMan middleMan;

	/** 状态0暂存,1待审批，2审批通过，3审批不通过，9删除 */
	@Column(columnDefinition = DEF_STR2)
	public String getState() {
		return this.state;
	}

	/** 状态0暂存,1待审批，2审批通过，3审批不通过，9删除 */
	public void setState(String state) {
		this.state = state;
	}

	private String qshkrq;// 起始还款日期

	/** 起始还款日期 */
	@Column(columnDefinition = DEF_STR20)
	public String getQshkrq() {
		return this.qshkrq;
	}

	/** 起始还款日期 */
	public void setQshkrq(String qshkrq) {
		this.qshkrq = qshkrq;
	}

	private String qdrq;// 合同签订日期

	/** 合同签订日期 */
	@Column(columnDefinition = DEF_STR20)
	public String getQdrq() {
		return this.qdrq;
	}

	/** 合同签订日期 */
	public void setQdrq(String qdrq) {
		this.qdrq = qdrq;
	}

	private String jkhtbm;// 借款合同编号

	/** 借款合同编号 */
	@Column(columnDefinition = DEF_STR20)
	public String getJkhtbm() {
		return this.jkhtbm;
	}

	/** 借款合同编号 */
	public void setJkhtbm(String jkhtbm) {
		this.jkhtbm = jkhtbm;
	}

	private Double htje;// 合同金额

	private Double fwf;// 服务费

	private Double fkje;// 放款金额

	private String auditPerson;// 审核人

	/** 审核人 */
	@Column(columnDefinition = DEF_STR32)
	public String getAuditPerson() {
		return this.auditPerson;
	}

	/** 审核人 */
	public void setAuditPerson(String auditPerson) {
		this.auditPerson = auditPerson;
	}

	private String auditIdea;// 审核意见

	/** 审核意见 */
	@Column(columnDefinition = DEF_STR512)
	public String getAuditIdea() {
		return this.auditIdea;
	}

	/** 审核意见 */
	public void setAuditIdea(String auditIdea) {
		this.auditIdea = auditIdea;
	}

	private Timestamp auditDate;// 审核时间

	@Column(insertable = false)
	public Timestamp getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Timestamp auditDate) {
		this.auditDate = auditDate;
	}

	private String auditQzqrPerson;// 签字确认审核人

	/** 签字确认审核人 */
	@Column(columnDefinition = DEF_STR32)
	public String getAuditQzqrPerson() {
		return this.auditQzqrPerson;
	}

	/** 签字确认审核人 */
	public void setAuditQzqrPerson(String auditQzqrPerson) {
		this.auditQzqrPerson = auditQzqrPerson;
	}

	private String auditQzqrIdea;// 签字确认审核意见

	/** 签字确认审核意见 */
	@Column(columnDefinition = DEF_STR512)
	public String getAuditQzqrIdea() {
		return this.auditQzqrIdea;
	}

	/** 签字确认审核意见 */
	public void setAuditQzqrIdea(String auditQzqrIdea) {
		this.auditQzqrIdea = auditQzqrIdea;
	}

	private Timestamp auditQzqrDate;// 签字确认审核时间

	@Column(insertable = false)
	public Timestamp getAuditQzqrDate() {
		return auditQzqrDate;
	}

	public void setAuditQzqrDate(Timestamp auditQzqrDate) {
		this.auditQzqrDate = auditQzqrDate;
	}

	private Double xff;// 信访费

	@Column(columnDefinition = DEF_NUM18)
	public int getHkqs() {
		return hkqs;
	}

	public void setHkqs(int hkqs) {
		this.hkqs = hkqs;
	}

	@Column(columnDefinition = DEF_NUM10_2)
	public Double getYzhfl() {
		return yzhfl;
	}

	public void setYzhfl(Double yzhfl) {
		this.yzhfl = yzhfl;
	}

	@Column(columnDefinition = DEF_NUM10_2)
	public Double getZxf() {
		return zxf;
	}

	public void setZxf(Double zxf) {
		this.zxf = zxf;
	}

	@Column(columnDefinition = DEF_NUM10_2)
	public Double getZhglf() {
		return zhglf;
	}

	public void setZhglf(Double zhglf) {
		this.zhglf = zhglf;
	}

	@Column(columnDefinition = DEF_NUM10_2)
	public Double getYlxje() {
		return ylxje;
	}

	public void setYlxje(Double ylxje) {
		this.ylxje = ylxje;
	}

//	@Column(columnDefinition = DEF_NUM10_2)
//	public Double getYll() {
//		return yll;
//	}
//
//	public void setYll(Double yll) {
//		this.yll = yll;
//	}

	@Column(columnDefinition = DEF_NUM10_2)
	public Double getYhkje() {
		return yhkje;
	}

	public void setYhkje(Double yhkje) {
		this.yhkje = yhkje;
	}

	@Column(columnDefinition = DEF_NUM10_2)
	public Double getYbjje() {
		return ybjje;
	}

	public void setYbjje(Double ybjje) {
		this.ybjje = ybjje;
	}

	@Column(columnDefinition = DEF_NUM10_2)
	public Double getXyshf() {
		return xyshf;
	}

	public void setXyshf(Double xyshf) {
		this.xyshf = xyshf;
	}

	@Column(columnDefinition = DEF_NUM10_2)
	public Double getHtje() {
		return htje;
	}

	public void setHtje(Double htje) {
		this.htje = htje;
	}

	@Column(columnDefinition = DEF_NUM10_2)
	public Double getFwf() {
		return fwf;
	}

	public void setFwf(Double fwf) {
		this.fwf = fwf;
	}

	@Column(columnDefinition = DEF_NUM10_2)
	public Double getFkje() {
		return fkje;
	}

	public void setFkje(Double fkje) {
		this.fkje = fkje;
	}

	@Column(columnDefinition = DEF_NUM10_2)
	public Double getXff() {
		return xff;
	}

	public void setXff(Double xff) {
		this.xff = xff;
	}





	@Column(columnDefinition = DEF_STR2)
	public String getHkr() {
		return hkr;
	}

	public void setHkr(String hkr) {
		this.hkr = hkr;
	}

	private Double pdje;// 批贷金额



	private Double dkll;// 贷款利率

	private Double syll;// 剩余利率

	private Double syje;// 剩余金额

	@Column(columnDefinition = DEF_NUM10_2)
	public Double getPdje() {
		return pdje;
	}

	public void setPdje(Double pdje) {
		this.pdje = pdje;
	}

	@Column(columnDefinition = DEF_NUM10_2)
	public Double getDkll() {
		return dkll;
	}

	public void setDkll(Double dkll) {
		this.dkll = dkll;
	}

	@Column(columnDefinition = DEF_NUM10_2)
	public Double getSyll() {
		return syll;
	}

	public void setSyll(Double syll) {
		this.syll = syll;
	}

	@Column(columnDefinition = DEF_NUM10_2)
	public Double getSyje() {
		return syje;
	}

	public void setSyje(Double syje) {
		this.syje = syje;
	}

	// //一对一定义
	// @OneToOne(cascade=CascadeType.REFRESH)
	// @JoinColumn(name="organi_id", unique= false, nullable=true,
	// insertable=true, updatable=true)
	// public Organi getOrgani() {
	// return organi;
	// }
	//
	// public void setOrgani(Organi organi) {
	// this.organi = organi;
	// }

	// 一对一定义
	@OneToOne(cascade = CascadeType.REFRESH, optional = false,fetch = FetchType.LAZY)
	@JoinColumn(name = "jksq_id")
	public XhJksq getXhJksq() {
		return xhJksq;
	}

	public void setXhJksq(XhJksq xhJksq) {
		this.xhJksq = xhJksq;
	}

	// 多对一定义
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "middleman_id", unique= false, nullable=true, insertable=true, updatable=true)
	public MiddleMan getMiddleMan() {
		return middleMan;
	}

	public void setMiddleMan(MiddleMan middleMan) {
		this.middleMan = middleMan;
	}
	

	//加急费用
    private Double urgentCreditFee;
    @Column(columnDefinition = DEF_NUM15_5)
	public Double getUrgentCreditFee() {
		return this.urgentCreditFee;
	}

  //加急费用
	public void setUrgentCreditFee(Double urgentCreditFee) {
		this.urgentCreditFee = urgentCreditFee;
	}
	
	private String rePayType;
	@Column(columnDefinition = DEF_STR2)
	public String getRePayType() {
		return rePayType;
	}

	public void setRePayType(String rePayType) {
		this.rePayType = rePayType;
	}
	
	
}

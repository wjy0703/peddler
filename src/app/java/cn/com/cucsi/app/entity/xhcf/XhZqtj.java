package cn.com.cucsi.app.entity.xhcf;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import cn.com.cucsi.app.entity.baseinfo.Organi;
import cn.com.cucsi.core.orm.hibernate.AuditableEntity;

/**
 * XhZqtj entity. 
 * @author MyEclipse Persistence Tools
 */
@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "XH_ZQTJ")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XhZqtj extends AuditableEntity{

	// Fields
	private static final long serialVersionUID = -2276242824197821671L;
	private XhTzsq xhTzsq;//投资申请ID
	/**投资申请ID*/
	@OneToOne(cascade=CascadeType.REFRESH,fetch = FetchType.LAZY)
	@JoinColumn(name="TZSQ_ID", unique= false, nullable=true, insertable=true, updatable=true)
	public XhTzsq getXhTzsq() {
		return this.xhTzsq;
	}
	/**投资申请ID*/
	public void setXhTzsq(XhTzsq xhTzsq) {
		this.xhTzsq = xhTzsq;
	}
	private Double money;//资金
	/**资金*/
	@Column(columnDefinition=DEF_NUM15_5)
	public Double getMoney() {
		return this.money;
	}
	/**资金*/
	public void setMoney(Double money) {
		this.money = money;
	}
	private String state;//状态0暂存,1待审批，2审批通过，3审批不通过，9删除，
	/**状态0暂存,1待审批，2审批通过，3审批不通过，9删除，*/
	@Column(columnDefinition=DEF_STR2)
	public String getState() {
		return this.state;
	}
	/**状态0暂存,1待审批，2审批通过，3审批不通过，9删除，*/
	public void setState(String state) {
		this.state = state;
	}
	
	private String statedg;//订购标记0未订购,1已订购，2已交割,8待划扣，7待马晨确认，6结算中心处理
	/**订购标记0未订购,1已订购，2已交割,8待划扣，7待马晨确认，6结算中心处理*/
	@Column(columnDefinition=DEF_STR2)
	public String getStatedg() {
		return this.statedg;
	}
	/**订购标记0未订购,1已订购，2已交割,8待划扣，7待马晨确认，6结算中心处理*/
	public void setStatedg(String statedg) {
		this.statedg = statedg;
	}
	
	private Organi organi;
	//一对一定义
	@OneToOne(cascade=CascadeType.REFRESH,fetch = FetchType.LAZY)
	@JoinColumn(name="organi_id", unique= false, nullable=true, insertable=true, updatable=true)
	public Organi getOrgani() {
		return organi;
	}

	public void setOrgani(Organi organi) {
		this.organi = organi;
	}
	
	private Set<XhZqtjDetails> xhZqtjDetails;
	//一对多定义
	@OneToMany(targetEntity=XhZqtjDetails.class,cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="ZQTJ_ID",updatable=false)
	public Set<XhZqtjDetails> getXhZqtjDetails() {
		return xhZqtjDetails;
	}
	public void setXhZqtjDetails(Set<XhZqtjDetails> xhZqtjDetails) {
		this.xhZqtjDetails = xhZqtjDetails;
	}
	
	private String lentState;//债权状态0首期,1非首期
	/**债权状态0首期,1非首期，*/
	@Column(columnDefinition=DEF_STR2)
	public String getLentState() {
		return this.lentState;
	}
	/**债权状态0首期,1非首期，*/
	public void setLentState(String lentState) {
		this.lentState = lentState;
	}
	
	private String lentStateYj;//债权状态_月结0首期,1非首期
	/**债权状态_月结0首期,1非首期，*/
	@Column(columnDefinition=DEF_STR2)
	public String getLentStateYj() {
		return this.lentStateYj;
	}
	/**债权状态_月结0首期,1非首期，*/
	public void setLentStateYj(String lentStateYj) {
		this.lentStateYj = lentStateYj;
	}
	
	private String jhtzrq;//计划投资日期
	/**计划投资日期*/
	@Column(columnDefinition=DEF_STR20)
	public String getJhtzrq() {
		return this.jhtzrq;
	}
	/**计划投资日期*/
	public void setJhtzrq(String jhtzrq) {
		this.jhtzrq = jhtzrq;
	}
	
	private String xybgrq;//下一个报告日
	/**下一个报告日*/
	@Column(columnDefinition=DEF_STR20)
	public String getXybgrq() {
		return this.xybgrq;
	}
	/**下一个报告日*/
	public void setXybgrq(String xybgrq) {
		this.xybgrq = xybgrq;
	}
	
	private Double xybgqjkryhke;//下一个报告期借款人应还款额
	/**下一个报告期借款人应还款额*/
	@Column(columnDefinition=DEF_NUM15_5)
	public Double getXybgqjkryhke() {
		return this.xybgqjkryhke;
	}
	/**下一个报告期借款人应还款额*/
	public void setXybgqjkryhke(Double xybgqjkryhke) {
		this.xybgqjkryhke = xybgqjkryhke;
	}
	
	private Double zhglf ;//账户管理费
	/**账户管理费*/
	@Column(columnDefinition=DEF_NUM15_5)
	public Double getZhglf() {
		return this.zhglf;
	}
	/**账户管理费*/
	public void setZhglf(Double zhglf) {
		this.zhglf = zhglf;
	}
	
	private String zhjb;//账户级别
	/**账户级别*/
	@Column(columnDefinition=DEF_STR20)
	public String getZhjb() {
		return this.zhjb;
	}
	/**账户级别*/
	public void setZhjb(String zhjb) {
		this.zhjb = zhjb;
	}
	
	private Double fwfl ;//服务费率
	/**服务费率*/
	@Column(columnDefinition=DEF_NUM15_5)
	public Double getFwfl() {
		return this.fwfl;
	}
	/**服务费率*/
	public void setFwfl(Double fwfl) {
		this.fwfl = fwfl;
	}
	
	private Double yjxybgrzcze;//预计下一个报告日您的资产总额
	/**预计下一个报告日您的资产总额*/
	@Column(columnDefinition=DEF_NUM15_5)
	public Double getYjxybgrzcze() {
		return this.yjxybgrzcze;
	}
	/**预计下一个报告日您的资产总额*/
	public void setYjxybgrzcze(Double yjxybgrzcze) {
		this.yjxybgrzcze = yjxybgrzcze;
	}
	
	private String zdr;//账单日
	/**账单日*/
	@Column(columnDefinition=DEF_STR20)
	public String getZdr() {
		return this.zdr;
	}
	/**账单日*/
	public void setZdr(String zdr) {
		this.zdr = zdr;
	}
	
	private XhcfCjrxx cjrxx;
	//一对一定义
	@OneToOne(cascade=CascadeType.REFRESH,fetch = FetchType.LAZY)
	@JoinColumn(name="CJRXX_ID", unique= false, nullable=true, insertable=true, updatable=true)
	public XhcfCjrxx getCjrxx() {
		return cjrxx;
	}

	public void setCjrxx(XhcfCjrxx cjrxx) {
		this.cjrxx = cjrxx;
	}
	
	private String fsqbgrq;//非首期报告日
	/**非首期报告日*/
	@Column(columnDefinition=DEF_STR20)
	public String getFsqbgrq() {
		return this.fsqbgrq;
	}
	/**非首期报告日*/
	public void setFsqbgrq(String fsqbgrq) {
		this.fsqbgrq = fsqbgrq;
	}
	
    private String billSendState;// 账单发送状态
	
	/**账单发送状态*/
	 @Column(columnDefinition = DEF_STR8)
	public String getBillSendState() {
		return this.billSendState;
	}
	 /**账单发送状态*/
	public void setBillSendState(String billSendState) {
		this.billSendState = billSendState;
	}
	
	private String agreementMakeState;// 债权制作状态
	
	
	/**债权制作状态*/
	 @Column(columnDefinition = DEF_STR8)
	public String getAgreementMakeState() {
		return this.agreementMakeState;
	}
	 
	/**债权制作状态*/
	public void setAgreementMakeState(String agreementMakeState) {
		this.agreementMakeState = agreementMakeState;
	}
	
	private String fillReason;
	
	
	/**账单发送失败原因*/
	@Column(columnDefinition = DEF_STR128)
	public String getFillReason() {
		return this.fillReason;
	}

	public void setFillReason(String fillReason) {
		this.fillReason = fillReason;
	}
}

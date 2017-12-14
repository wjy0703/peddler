package cn.com.cucsi.app.entity.xhcf;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import cn.com.cucsi.app.entity.baseinfo.Organi;
import cn.com.cucsi.app.entity.baseinfo.Tzcp;
import cn.com.cucsi.core.orm.hibernate.AuditableEntity;

/**
 * XhTzsqHistory entity. 
 * @author MyEclipse Persistence Tools
 */
@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "XH_TZSQ_UPHISTORY")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XhTzsqHistory extends AuditableEntity{
	// Fields
	private static final long serialVersionUID = -2276242824197821673L;
	private String tzsqbh;//协议编号
	/**协议编号*/
	@Column(columnDefinition=DEF_STR20)
	public String getTzsqbh() {
		return this.tzsqbh;
	}
	/**协议编号*/
	public void setTzsqbh(String tzsqbh) {
		this.tzsqbh = tzsqbh;
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
	private String jhtzje;//计划投资金额
	/**计划投资金额*/
	@Column(columnDefinition=DEF_STR20)
	public String getJhtzje() {
		return this.jhtzje;
	}
	/**计划投资金额*/
	public void setJhtzje(String jhtzje) {
		this.jhtzje = jhtzje;
	}
	private String tzfs;//投资方式
	/**投资方式*/
	@Column(columnDefinition=DEF_STR20)
	public String getTzfs() {
		return this.tzfs;
	}
	/**投资方式*/
	public void setTzfs(String tzfs) {
		this.tzfs = tzfs;
	}
	private String hsfs;//回收方式
	/**回收方式*/
	@Column(columnDefinition=DEF_STR64)
	public String getHsfs() {
		return this.hsfs;
	}
	/**回收方式*/
	public void setHsfs(String hsfs) {
		this.hsfs = hsfs;
	}
	private String fkfs;//付款方式
	/**付款方式*/
	@Column(columnDefinition=DEF_STR64)
	public String getFkfs() {
		return this.fkfs;
	}
	/**付款方式*/
	public void setFkfs(String fkfs) {
		this.fkfs = fkfs;
	}
	private String sffxjbc;//是否风险金补偿
	/**是否风险金补偿*/
	@Column(columnDefinition=DEF_STR10)
	public String getSffxjbc() {
		return this.sffxjbc;
	}
	/**是否风险金补偿*/
	public void setSffxjbc(String sffxjbc) {
		this.sffxjbc = sffxjbc;
	}
	private String jhhkrq;//计划划扣日期
	/**计划划扣日期*/
	@Column(columnDefinition=DEF_STR20)
	public String getJhhkrq() {
		return this.jhhkrq;
	}
	/**计划划扣日期*/
	public void setJhhkrq(String jhhkrq) {
		this.jhhkrq = jhhkrq;
	}
	private String sqrq;//申请日期
	/**申请日期*/
	@Column(columnDefinition=DEF_STR20)
	public String getSqrq() {
		return this.sqrq;
	}
	/**申请日期*/
	public void setSqrq(String sqrq) {
		this.sqrq = sqrq;
	}
	private String bmzg;//部门主管
	/**部门主管*/
	@Column(columnDefinition=DEF_STR20)
	public String getBmzg() {
		return this.bmzg;
	}
	/**部门主管*/
	public void setBmzg(String bmzg) {
		this.bmzg = bmzg;
	}
	private String xybb;//协议版本
	/**协议版本*/
	@Column(columnDefinition=DEF_STR10)
	public String getXybb() {
		return this.xybb;
	}
	/**协议版本*/
	public void setXybb(String xybb) {
		this.xybb = xybb;
	}
	private String xszkly;//销售折扣率
	/**销售折扣率*/
	@Column(columnDefinition=DEF_STR10)
	public String getXszkly() {
		return this.xszkly;
	}
	/**销售折扣率*/
	public void setXszkly(String xszkly) {
		this.xszkly = xszkly;
	}
	private String xszklyxqx;//销售折扣率有效期限
	/**销售折扣率有效期限*/
	@Column(columnDefinition=DEF_STR10)
	public String getXszklyxqx() {
		return this.xszklyxqx;
	}
	/**销售折扣率有效期限*/
	public void setXszklyxqx(String xszklyxqx) {
		this.xszklyxqx = xszklyxqx;
	}
	private String remark;//备注
	/**备注*/
	@Column(columnDefinition=DEF_STR255)
	public String getRemark() {
		return this.remark;
	}
	/**备注*/
	public void setRemark(String remark) {
		this.remark = remark;
	}
	private String sqdyj;//申请单原件
	/**申请单原件*/
	@Column(columnDefinition=DEF_STR200)
	public String getSqdyj() {
		return this.sqdyj;
	}
	/**申请单原件*/
	public void setSqdyj(String sqdyj) {
		this.sqdyj = sqdyj;
	}
	private String tzfkkhh;//投资付款开户行
	/**投资付款开户行*/
	@Column(columnDefinition=DEF_STR64)
	public String getTzfkkhh() {
		return this.tzfkkhh;
	}
	/**投资付款开户行*/
	public void setTzfkkhh(String tzfkkhh) {
		this.tzfkkhh = tzfkkhh;
	}
	private String tzfkkhz;//投资付款卡或折
	/**投资付款卡或折*/
	@Column(columnDefinition=DEF_STR64)
	public String getTzfkkhz() {
		return this.tzfkkhz;
	}
	/**投资付款卡或折*/
	public void setTzfkkhz(String tzfkkhz) {
		this.tzfkkhz = tzfkkhz;
	}
	private String tzfkyhmc;//投资付款银行名称
	/**投资付款银行名称*/
	@Column(columnDefinition=DEF_STR100)
	public String getTzfkyhmc() {
		return this.tzfkyhmc;
	}
	/**投资付款银行名称*/
	public void setTzfkyhmc(String tzfkyhmc) {
		this.tzfkyhmc = tzfkyhmc;
	}
	private String tzfkkhmc;//投资付款开户名称
	/**投资付款开户名称*/
	@Column(columnDefinition=DEF_STR100)
	public String getTzfkkhmc() {
		return this.tzfkkhmc;
	}
	/**投资付款开户名称*/
	public void setTzfkkhmc(String tzfkkhmc) {
		this.tzfkkhmc = tzfkkhmc;
	}
	private String tzfkyhzh;//投资付款银行帐号
	/**投资付款银行帐号*/
	@Column(columnDefinition=DEF_STR100)
	public String getTzfkyhzh() {
		return this.tzfkyhzh;
	}
	/**投资付款银行帐号*/
	public void setTzfkyhzh(String tzfkyhzh) {
		this.tzfkyhzh = tzfkyhzh;
	}
	private String hszjkhh;//回收资金开户行
	/**回收资金开户行*/
	@Column(columnDefinition=DEF_STR64)
	public String getHszjkhh() {
		return this.hszjkhh;
	}
	/**回收资金开户行*/
	public void setHszjkhh(String hszjkhh) {
		this.hszjkhh = hszjkhh;
	}
	private String hszjkhz;//回收资金卡或折
	/**回收资金卡或折*/
	@Column(columnDefinition=DEF_STR64)
	public String getHszjkhz() {
		return this.hszjkhz;
	}
	/**回收资金卡或折*/
	public void setHszjkhz(String hszjkhz) {
		this.hszjkhz = hszjkhz;
	}
	private String hszjyhmc;//回收资金银行名称
	/**回收资金银行名称*/
	@Column(columnDefinition=DEF_STR100)
	public String getHszjyhmc() {
		return this.hszjyhmc;
	}
	/**回收资金银行名称*/
	public void setHszjyhmc(String hszjyhmc) {
		this.hszjyhmc = hszjyhmc;
	}
	private String hszjkhmc;//回收资金开户名称
	/**回收资金开户名称*/
	@Column(columnDefinition=DEF_STR100)
	public String getHszjkhmc() {
		return this.hszjkhmc;
	}
	/**回收资金开户名称*/
	public void setHszjkhmc(String hszjkhmc) {
		this.hszjkhmc = hszjkhmc;
	}
	private String hszjyhzh;//回收资金银行帐号
	/**回收资金银行帐号*/
	@Column(columnDefinition=DEF_STR100)
	public String getHszjyhzh() {
		return this.hszjyhzh;
	}
	/**回收资金银行帐号*/
	public void setHszjyhzh(String hszjyhzh) {
		this.hszjyhzh = hszjyhzh;
	}
	private String sqzt;//申请状态
	/**申请状态*/
	@Column(columnDefinition=DEF_STR20)
	public String getSqzt() {
		return this.sqzt;
	}
	/**申请状态*/
	public void setSqzt(String sqzt) {
		this.sqzt = sqzt;
	}
	private String cjzq;//出借周期
	/**出借周期*/
	@Column(columnDefinition=DEF_STR20)
	public String getCjzq() {
		return this.cjzq;
	}
	/**出借周期*/
	public void setCjzq(String cjzq) {
		this.cjzq = cjzq;
	}
	private String tzjgr;//交割日
	/**交割日*/
	@Column(columnDefinition=DEF_STR20)
	public String getTzjgr() {
		return this.tzjgr;
	}
	/**交割日*/
	public void setTzjgr(String tzjgr) {
		this.tzjgr = tzjgr;
	}
	private String sghkrq;//首个还款日期
	/**首个还款日期*/
	@Column(columnDefinition=DEF_STR20)
	public String getSghkrq() {
		return this.sghkrq;
	}
	/**首个还款日期*/
	public void setSghkrq(String sghkrq) {
		this.sghkrq = sghkrq;
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
	private String tzzjzt;//投资资金状态
	/**投资资金状态*/
	@Column(columnDefinition=DEF_STR20)
	public String getTzzjzt() {
		return this.tzzjzt;
	}
	/**投资资金状态*/
	public void setTzzjzt(String tzzjzt) {
		this.tzzjzt = tzzjzt;
	}
	private String syqs;//使用期数
	/**使用期数*/
	@Column(columnDefinition=DEF_STR20)
	public String getSyqs() {
		return this.syqs;
	}
	/**使用期数*/
	public void setSyqs(String syqs) {
		this.syqs = syqs;
	}
	private String ppzt;//匹配状态
	/**匹配状态*/
	@Column(columnDefinition=DEF_STR10)
	public String getPpzt() {
		return this.ppzt;
	}
	/**匹配状态*/
	public void setPpzt(String ppzt) {
		this.ppzt = ppzt;
	}
	private String lastCjzq;//剩余出借周期
	/**剩余出借周期*/
	@Column(columnDefinition=DEF_STR20)
	public String getLastCjzq() {
		return this.lastCjzq;
	}
	/**剩余出借周期*/
	public void setLastCjzq(String lastCjzq) {
		this.lastCjzq = lastCjzq;
	}
	private String state;//状态0暂存,1提交，2审批通过，3审批不通过，9删除， 8.待审批
	/**状态0暂存,1提交，2审批通过，3审批不通过，9删除，8.待审批 */
	@Column(columnDefinition=DEF_STR2)
	public String getState() {
		return this.state;
	}
	/**状态0暂存,1提交，2审批通过，3审批不通过，9删除， 8.待审批*/
	public void setState(String state) {
		this.state = state;
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
	private Timestamp auditTime;//审核时间
	/**审核时间*/
	@Column(insertable = false)
	public Timestamp getAuditTime() {
		return this.auditTime;
	}
	/**审核时间*/
	public void setAuditTime(Timestamp auditTime) {
		this.auditTime = auditTime;
	}
	private String upstate;//变更状态-1，无修改，0暂存, 1待审批，3审批不通过
	/**变更状态-1，无修改，0暂存, 1待审批，3审批不通过*/
	@Column(columnDefinition=DEF_STR2)
	public String getUpstate() {
		return this.upstate;
	}
	/**变更状态-1，无修改，0暂存, 1待审批，3审批不通过*/
	public void setUpstate(String upstate) {
		this.upstate = upstate;
	}
	
	
	
	private String firstdate;//首期天数
	/**首期天数*/
	@Column(columnDefinition=DEF_STR2)
	public String getFirstdate() {
		return this.firstdate;
	}
	/**首期天数*/
	public void setFirstdate(String firstdate) {
		this.firstdate = firstdate;
	}
	
	private String overstate;//完结状态0，未完结，2，已完结
	/**完结状态0，未完结，2，已完结*/
	@Column(columnDefinition=DEF_STR2)
	public String getOverstate() {
		return this.overstate;
	}
	/**完结状态0，未完结，2，已完结*/
	public void setOverstate(String overstate) {
		this.overstate = overstate;
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
	//投资产品
	private Tzcp tzcp;
	//一对一定义
	@OneToOne(cascade=CascadeType.REFRESH,fetch = FetchType.LAZY)
	@JoinColumn(name="TZCP_ID", unique= false, nullable=true, insertable=true, updatable=true)
	public Tzcp getTzcp() {
		return tzcp;
	}
	public void setTzcp(Tzcp tzcp) {
		this.tzcp = tzcp;
	}
	private XhTzsq xhTzsq;
	//一对一定义
	@OneToOne(cascade=CascadeType.REFRESH,fetch = FetchType.LAZY)
	@JoinColumn(name="TZSQ_ID", unique= false, nullable=true, insertable=true, updatable=true)
	public XhTzsq getXhTzsq() {
		return xhTzsq;
	}

	public void setXhTzsq(XhTzsq xhTzsq) {
		this.xhTzsq = xhTzsq;
	}
	
	public XhTzsqHistory() {
		super();
		// TODO Auto-generated constructor stub
	}
	public XhTzsqHistory(XhTzsq xth) {
		super();
		this.xhTzsq = xth;
		this.tzsqbh = xth.getTzsqbh();
		this.jhtzrq = xth.getJhtzrq();
		this.jhtzje = xth.getJhtzje();
		this.tzfs = xth.getTzfs();
		this.hsfs = xth.getHsfs();
		this.fkfs = xth.getFkfs();
		this.sffxjbc = xth.getSffxjbc();
		this.jhhkrq = xth.getJhhkrq();
		this.sqrq = xth.getSqrq();
		this.bmzg = xth.getBmzg();
		this.xybb = xth.getXybb();
		this.xszkly = xth.getXszkly();
		this.xszklyxqx = xth.getXszklyxqx();
		this.remark = xth.getRemark();
		this.sqdyj = xth.getSqdyj();
		this.tzfkkhh = xth.getTzfkkhh();
		this.tzfkkhz = xth.getTzfkkhz();
		this.tzfkyhmc = xth.getTzfkyhmc();
		this.tzfkkhmc = xth.getTzfkkhmc();
		this.tzfkyhzh = xth.getTzfkyhzh();
		this.hszjkhh = xth.getHszjkhh();
		this.hszjkhz = xth.getHszjkhz();
		this.hszjyhmc = xth.getHszjyhmc();
		this.hszjkhmc = xth.getHszjkhmc();
		this.hszjyhzh = xth.getHszjyhzh();
		this.sqzt = xth.getSqzt();
		this.cjzq = xth.getCjzq();
		this.tzjgr = xth.getTzjgr();
		this.sghkrq = xth.getSghkrq();
		this.zdr = xth.getZdr();
		this.tzzjzt = xth.getTzzjzt();
		this.syqs = xth.getSyqs();
		this.ppzt = xth.getPpzt();
		this.lastCjzq = xth.getLastCjzq();
		this.state = xth.getState();
		this.auditPerson = xth.getAuditPerson();
		this.auditIdea = xth.getAuditIdea();
		this.auditTime = xth.getAuditTime();
		this.upstate = xth.getUpstate();
		this.firstdate = xth.getFirstdate();
		this.overstate = xth.getOverstate();
		this.cjrxx = xth.getCjrxx();
		this.organi = xth.getOrgani();
		this.tzcp = xth.getTzcp();
	}
	
	public void toXhTzsqHistory(XhTzsqHistory xth) {
		this.jhtzrq = xth.getJhtzrq();
		this.jhtzje = xth.getJhtzje();
		//this.tzfs = xth.getTzfs();
		//this.hsfs = xth.getHsfs();
		this.fkfs = xth.getFkfs();
		this.tzfkkhh = xth.getTzfkkhh();
		this.tzfkyhmc = xth.getTzfkyhmc();
		this.tzfkyhzh = xth.getTzfkyhzh();
		this.hszjkhh = xth.getTzfkkhh();
		this.hszjyhmc = xth.getTzfkyhmc();
		this.hszjyhzh = xth.getTzfkyhzh();
		this.upstate = xth.getUpstate();
		//this.tzcp = xth.getTzcp();
	}
	
	
}

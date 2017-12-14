package cn.com.cucsi.app.entity.xhcf;

import java.sql.Timestamp;
import java.util.HashSet;
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

import cn.com.cucsi.app.entity.baseinfo.Employee;
import cn.com.cucsi.app.entity.baseinfo.Organi;
import cn.com.cucsi.core.orm.hibernate.AuditableEntity;

/**
 * 出借人基本信息 entity. 
 * @author MyEclipse Persistence Tools
 */
@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "XH_CJRXX")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XhcfCjrxx extends AuditableEntity{

	// Fields
	private static final long serialVersionUID = -2276242824197821672L;
	private String khbm;//客户编码
	
	private String cjrxm;//出借人名字
	private String ywmc;//英文名称
	private String cjrxb;//出借人性别
	private String csrq;//出生日期
	
	private String wjbh;//问卷编号
	
	private String mqxm;//母亲姓名
	private String hyzk;//婚姻状况
	private String zjlx;//证件类型
	private String zjhm;//证件号码
	private String zjqfrq;//签发日期
	private String sfcq;//是否长期有效
	private String qjsxrq;//失效日期
	private String fzjg;//发证机关
	private String cjrxl;//学历
	private String cjrzy;//出借人职业
	private String hy;//所属行业
	private String gzdwmc;//工作单位
	private String gznx;//工作年限
	private String cjrdwgm;//出借人单位规模
	private String zw;//出借人职务
	private String yddh;//移动电话
	private String gddh;//固定电话
	private String dzyx;//电子邮箱
	private String cjrfx;//客户传真
	private String crmsztd;//CRM所在团队
	private String crmtd;//CRM团队
	private Employee employeeCrm;
	private Employee employeeCca;
	private String khly;//客户来源
	private String kftd;//开发团队
	private String optionTime;//首次联系日期
	private String yb;//邮编
	private String province;//省份
	private String city;//地市
	private String area;//区县
	private String txdz;//通讯地址
	private String crmprovince;//crm省份
	private String crmcity;//crm地市
	private String tzfkkhh;//投资付款开户行
	private String tzfkkhz;//投资付款卡或折
	private String tzfkyhmc;//投资付款银行名称
	private String tzfkkhmc;//投资付款开户名称
	private String tzfkyhzh;//投资付款银行帐号
	
	private String hszjkhh;//回收资金开户行
	private String hszjkhz;//回收资金卡或折
	private String hszjyhmc;//回收资金银行名称
	private String hszjkhmc;//回收资金开户名称
	private String hszjyhzh;//回收资金银行帐号
	
	private String sfqdwtxy;//是否签订委托协议
	private String qdxyrq;//协议签订日期
	private String wtxyzl;//委托协议种类
	private String wtxybbh;//委托协议版本号
	
	private String remark;//备注
	private Organi organi;
	private String cjrzt;//出借人状态
	private String cjrState;//客户状态。0.投资咨询，1.出借人
	private String ztFlag;//状态
	private String cjrfj;//出借人附件
	private String state;//0暂存,1待审批，2审批通过，3审批不通过，9删除， 
	private String auditPerson;
	private String auditIdea;
	private Timestamp auditTime;
	
	private String upstate;//变更状态-1，无修改，0暂存, 1待审批，3审批不通过
	private UpdateCjrxxHistory history;
	
	private Set<UpdateCjrxxHistory> upHistorys = new HashSet<UpdateCjrxxHistory>();
	
	private String jjlxrzwmc;//紧急联系人中文姓名
	private String jjlxrywmc;//紧急联系人英文名称
	private String jjlxrxb;//紧急联系人性别
	private String jjlxrcsrq;//紧急联系人出生日期
	private String jjlxrzjlx;//紧急联系人证件类型
	private String jjlxrzjhm;//紧急联系人证件号码
	private String jjlxryddh;//紧急联系人移动电话
	private String jjlxrgddh;//紧急联系人固定电话
	private String jjlxrdzyx;//紧急联系人电子邮箱
	private String yndgx;//与您的关系
	private String zqjsfs;//账单收取方式
	private String jjlxryb;//紧急联系人邮编
	private String jjlxrprovince;//省份
	private String jjlxrcity;//地市
	private String jjlxrarea;//区县
	private String jjlxrtxdz;//紧急联系人通讯地址
	
	private String cjrxz;//出借人性质
	private String qybm;//区域编码
	private String userName;//操作人
	private String khjlName;//客户经理名称
	private String yhmc;//银行名称
	private String khmc;//开户名称
	private String khh;//开户行
	private String yhzh;//银行帐号
	private String khch;//客户称呼
	private String tjr;//推荐人
	
	private String cjryx;//客户意向
	private String officetel;//办公室电话
	private String kglxsj;//可供联系时间
	private String gxcs;//管辖城市
	private String gj;//所在国籍
	private String yy;//语言
	private String changeFlag;//是否变更标示
//	private Set xhcfCjrxxlcxgjls = new HashSet(0);
//	private Set xhcfTzsqs = new HashSet(0);
//	private Set xhcfCjrgtjls = new HashSet(0);

	// Property accessors
	@Column(columnDefinition=DEF_STR20)
	public String getChangeFlag() {
		return changeFlag;
	}

	public void setChangeFlag(String changeFlag) {
		this.changeFlag = changeFlag;
	}

	@Column(columnDefinition=DEF_STR64)
	public String getCjrxm() {
		return this.cjrxm;
	}

	public void setCjrxm(String cjrxm) {
		this.cjrxm = cjrxm;
	}
	@Column(columnDefinition=DEF_STR20)
	public String getYwmc() {
		return this.ywmc;
	}

	public void setWjbh(String wjbh) {
		this.wjbh = wjbh;
	}
	@Column(columnDefinition=DEF_STR20)
	public String getWjbh() {
		return this.wjbh;
	}

	public void setYwmc(String ywmc) {
		this.ywmc = ywmc;
	}
	@Column(columnDefinition=DEF_STR20)
	public String getCsrq() {
		return this.csrq;
	}

	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}
	@Column(columnDefinition=DEF_STR100)
	public String getFzjg() {
		return this.fzjg;
	}

	public void setFzjg(String fzjg) {
		this.fzjg = fzjg;
	}
	@Column(columnDefinition=DEF_STR20)
	public String getMqxm() {
		return this.mqxm;
	}

	public void setMqxm(String mqxm) {
		this.mqxm = mqxm;
	}
	@Column(columnDefinition=DEF_STR50)
	public String getZjhm() {
		return this.zjhm;
	}

	public void setZjhm(String zjhm) {
		this.zjhm = zjhm;
	}
	@Column(columnDefinition=DEF_STR20)
	public String getZjqfrq() {
		return this.zjqfrq;
	}

	public void setZjqfrq(String zjqfrq) {
		this.zjqfrq = zjqfrq;
	}
	
	@Column(columnDefinition=DEF_STR2)
	public String getSfcq() {
		return sfcq;
	}

	public void setSfcq(String sfcq) {
		this.sfcq = sfcq;
	}

	@Column(columnDefinition=DEF_STR20)
	public String getQjsxrq() {
		return this.qjsxrq;
	}

	public void setQjsxrq(String qjsxrq) {
		this.qjsxrq = qjsxrq;
	}
	@Column(columnDefinition=DEF_STR200)
	public String getGzdwmc() {
		return this.gzdwmc;
	}

	public void setGzdwmc(String gzdwmc) {
		this.gzdwmc = gzdwmc;
	}
	@Column(columnDefinition=DEF_STR3)
	public String getGznx() {
		return this.gznx;
	}

	public void setGznx(String gznx) {
		this.gznx = gznx;
	}
	@Column(columnDefinition=DEF_STR20)
	public String getYddh() {
		return this.yddh;
	}

	public void setYddh(String yddh) {
		this.yddh = yddh;
	}
	@Column(columnDefinition=DEF_STR20)
	public String getGddh() {
		return this.gddh;
	}

	public void setGddh(String gddh) {
		this.gddh = gddh;
	}
	@Column(columnDefinition=DEF_STR200)
	public String getTxdz() {
		return this.txdz;
	}

	public void setTxdz(String txdz) {
		this.txdz = txdz;
	}
	@Column(columnDefinition=DEF_STR100)
	public String getDzyx() {
		return this.dzyx;
	}

	public void setDzyx(String dzyx) {
		this.dzyx = dzyx;
	}
	@Column(columnDefinition=DEF_STR10)
	public String getYb() {
		return this.yb;
	}

	public void setYb(String yb) {
		this.yb = yb;
	}
	@Column(columnDefinition=DEF_STR20)
	public String getJjlxrzwmc() {
		return this.jjlxrzwmc;
	}

	public void setJjlxrzwmc(String jjlxrzwmc) {
		this.jjlxrzwmc = jjlxrzwmc;
	}
	@Column(columnDefinition=DEF_STR20)
	public String getJjlxrywmc() {
		return this.jjlxrywmc;
	}

	public void setJjlxrywmc(String jjlxrywmc) {
		this.jjlxrywmc = jjlxrywmc;
	}
	@Column(columnDefinition=DEF_STR20)
	public String getJjlxrcsrq() {
		return this.jjlxrcsrq;
	}

	public void setJjlxrcsrq(String jjlxrcsrq) {
		this.jjlxrcsrq = jjlxrcsrq;
	}
	@Column(columnDefinition=DEF_STR50)
	public String getJjlxrzjhm() {
		return this.jjlxrzjhm;
	}

	public void setJjlxrzjhm(String jjlxrzjhm) {
		this.jjlxrzjhm = jjlxrzjhm;
	}
	@Column(columnDefinition=DEF_STR20)
	public String getJjlxryddh() {
		return this.jjlxryddh;
	}

	public void setJjlxryddh(String jjlxryddh) {
		this.jjlxryddh = jjlxryddh;
	}
	@Column(columnDefinition=DEF_STR20)
	public String getJjlxrgddh() {
		return this.jjlxrgddh;
	}

	public void setJjlxrgddh(String jjlxrgddh) {
		this.jjlxrgddh = jjlxrgddh;
	}
	@Column(columnDefinition=DEF_STR200)
	public String getJjlxrtxdz() {
		return this.jjlxrtxdz;
	}

	public void setJjlxrtxdz(String jjlxrtxdz) {
		this.jjlxrtxdz = jjlxrtxdz;
	}
	@Column(columnDefinition=DEF_STR100)
	public String getJjlxrdzyx() {
		return this.jjlxrdzyx;
	}

	public void setJjlxrdzyx(String jjlxrdzyx) {
		this.jjlxrdzyx = jjlxrdzyx;
	}
	@Column(columnDefinition=DEF_STR10)
	public String getJjlxryb() {
		return this.jjlxryb;
	}

	public void setJjlxryb(String jjlxryb) {
		this.jjlxryb = jjlxryb;
	}
	@Column(columnDefinition=DEF_STR20)
	public String getCjrzy() {
		return this.cjrzy;
	}

	public void setCjrzy(String cjrzy) {
		this.cjrzy = cjrzy;
	}
	@Column(columnDefinition=DEF_STR20)
	public String getCjrdwgm() {
		return this.cjrdwgm;
	}

	public void setCjrdwgm(String cjrdwgm) {
		this.cjrdwgm = cjrdwgm;
	}
	@Column(columnDefinition=DEF_STR20)
	public String getZqjsfs() {
		return this.zqjsfs;
	}

	public void setZqjsfs(String zqjsfs) {
		this.zqjsfs = zqjsfs;
	}
	@Column(columnDefinition=DEF_STR20)
	public String getJjlxrzjlx() {
		return this.jjlxrzjlx;
	}

	public void setJjlxrzjlx(String jjlxrzjlx) {
		this.jjlxrzjlx = jjlxrzjlx;
	}
	@Column(columnDefinition=DEF_STR20)
	public String getCjrzt() {
		return this.cjrzt;
	}

	public void setCjrzt(String cjrzt) {
		this.cjrzt = cjrzt;
	}
	@Column(columnDefinition=DEF_STR20)
	public String getCjrxb() {
		return this.cjrxb;
	}

	public void setCjrxb(String cjrxb) {
		this.cjrxb = cjrxb;
	}
	@Column(columnDefinition=DEF_STR20)
	public String getYy() {
		return this.yy;
	}

	public void setYy(String yy) {
		this.yy = yy;
	}
	@Column(columnDefinition=DEF_STR20)
	public String getHyzk() {
		return this.hyzk;
	}

	public void setHyzk(String hyzk) {
		this.hyzk = hyzk;
	}
	@Column(columnDefinition=DEF_STR20)
	public String getGj() {
		return this.gj;
	}

	public void setGj(String gj) {
		this.gj = gj;
	}
	@Column(columnDefinition=DEF_STR20)
	public String getCjrxz() {
		return this.cjrxz;
	}

	public void setCjrxz(String cjrxz) {
		this.cjrxz = cjrxz;
	}
	@Column(columnDefinition=DEF_STR20)
	public String getYndgx() {
		return this.yndgx;
	}

	public void setYndgx(String yndgx) {
		this.yndgx = yndgx;
	}
	@Column(columnDefinition=DEF_STR20)
	public String getJjlxrxb() {
		return this.jjlxrxb;
	}

	public void setJjlxrxb(String jjlxrxb) {
		this.jjlxrxb = jjlxrxb;
	}
	@Column(columnDefinition=DEF_STR20)
	public String getZw() {
		return this.zw;
	}

	public void setZw(String zw) {
		this.zw = zw;
	}
	@Column(columnDefinition=DEF_STR20)
	public String getHy() {
		return this.hy;
	}

	public void setHy(String hy) {
		this.hy = hy;
	}
	@Column(columnDefinition=DEF_STR20)
	public String getZjlx() {
		return this.zjlx;
	}

	public void setZjlx(String zjlx) {
		this.zjlx = zjlx;
	}
	@Column(columnDefinition=DEF_STR20)
	public String getZtFlag() {
		return this.ztFlag;
	}

	public void setZtFlag(String ztFlag) {
		this.ztFlag = ztFlag;
	}
	@Column(columnDefinition=DEF_STR10)
	public String getQybm() {
		return this.qybm;
	}

	public void setQybm(String qybm) {
		this.qybm = qybm;
	}
	
	@Column(columnDefinition=DEF_STR20)
	public String getKhbm() {
		return this.khbm;
	}

	public void setKhbm(String khbm) {
		this.khbm = khbm;
	}
	@Column(columnDefinition=DEF_STR20)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Column(columnDefinition=DEF_STR20)
	public String getKhjlName() {
		return this.khjlName;
	}

	public void setKhjlName(String khjlName) {
		this.khjlName = khjlName;
	}
	@Column(columnDefinition=DEF_STR20)
	public String getOptionTime() {
		return this.optionTime;
	}

	public void setOptionTime(String optionTime) {
		this.optionTime = optionTime;
	}
	@Column(columnDefinition=DEF_STR50)
	public String getYhmc() {
		return this.yhmc;
	}

	public void setYhmc(String yhmc) {
		this.yhmc = yhmc;
	}
	@Column(columnDefinition=DEF_STR100)
	public String getKhmc() {
		return this.khmc;
	}

	public void setKhmc(String khmc) {
		this.khmc = khmc;
	}
	@Column(columnDefinition=DEF_STR100)
	public String getKhh() {
		return this.khh;
	}

	public void setKhh(String khh) {
		this.khh = khh;
	}
	@Column(columnDefinition=DEF_STR100)
	public String getYhzh() {
		return this.yhzh;
	}

	public void setYhzh(String yhzh) {
		this.yhzh = yhzh;
	}
	@Column(columnDefinition=DEF_STR50)
	public String getGxcs() {
		return this.gxcs;
	}

	public void setGxcs(String gxcs) {
		this.gxcs = gxcs;
	}
	@Column(columnDefinition=DEF_STR50)
	public String getKftd() {
		return this.kftd;
	}

	public void setKftd(String kftd) {
		this.kftd = kftd;
	}
	@Column(columnDefinition=DEF_STR255)
	public String getCjrfj() {
		return this.cjrfj;
	}

	public void setCjrfj(String cjrfj) {
		this.cjrfj = cjrfj;
	}
	@Column(columnDefinition=DEF_STR64)
	public String getTjr() {
		return tjr;
	}

	public void setTjr(String tjr) {
		this.tjr = tjr;
	}
	@Column(columnDefinition=DEF_STR64)
	public String getKhly() {
		return khly;
	}

	public void setKhly(String khly) {
		this.khly = khly;
	}
	@Column(columnDefinition=DEF_STR32)
	public String getCjrfx() {
		return cjrfx;
	}

	public void setCjrfx(String cjrfx) {
		this.cjrfx = cjrfx;
	}
	@Column(columnDefinition=DEF_STR32)
	public String getCjryx() {
		return cjryx;
	}

	public void setCjryx(String cjryx) {
		this.cjryx = cjryx;
	}
	@Column(columnDefinition=DEF_STR32)
	public String getOfficetel() {
		return officetel;
	}

	public void setOfficetel(String officetel) {
		this.officetel = officetel;
	}
	@Column(columnDefinition=DEF_STR32)
	public String getKglxsj() {
		return kglxsj;
	}

	public void setKglxsj(String kglxsj) {
		this.kglxsj = kglxsj;
	}
	@Column(columnDefinition=DEF_STR255)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(columnDefinition=DEF_STR4)
	public String getKhch() {
		return khch;
	}

	public void setKhch(String khch) {
		this.khch = khch;
	}
	//一对一定义
	@OneToOne(cascade=CascadeType.REFRESH,fetch = FetchType.LAZY)
	@JoinColumn(name="EMPLOYEE_CRM", unique= false, nullable=true, insertable=true, updatable=true)
	public Employee getEmployeeCrm() {
		return employeeCrm;
	}

	public void setEmployeeCrm(Employee employeeCrm) {
		this.employeeCrm = employeeCrm;
	}

	//一对一定义
	@OneToOne(cascade=CascadeType.REFRESH,fetch = FetchType.LAZY)
	@JoinColumn(name="EMPLOYEE_CCA", unique= false, nullable=true, insertable=true, updatable=true)
	public Employee getEmployeeCca() {
		return employeeCca;
	}

	public void setEmployeeCca(Employee employeeCca) {
		this.employeeCca = employeeCca;
	}
	@Column(columnDefinition=DEF_STR16)
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	@Column(columnDefinition=DEF_STR16)
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	@Column(columnDefinition=DEF_STR16)
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	@Column(columnDefinition=DEF_STR16)
	public String getCrmprovince() {
		return crmprovince;
	}

	public void setCrmprovince(String crmprovince) {
		this.crmprovince = crmprovince;
	}
	@Column(columnDefinition=DEF_STR16)
	public String getCrmcity() {
		return crmcity;
	}

	public void setCrmcity(String crmcity) {
		this.crmcity = crmcity;
	}
	@Column(columnDefinition=DEF_STR2)
	public String getCjrState() {
		return cjrState;
	}

	public void setCjrState(String cjrState) {
		this.cjrState = cjrState;
	}
	//一对一定义
	@OneToOne(cascade=CascadeType.REFRESH,fetch = FetchType.LAZY)
	@JoinColumn(name="organi_id", unique= false, nullable=true, insertable=true, updatable=true)
	public Organi getOrgani() {
		return organi;
	}

	public void setOrgani(Organi organi) {
		this.organi = organi;
	}
	@Column(columnDefinition=DEF_STR20)
	public String getCjrxl() {
		return cjrxl;
	}

	public void setCjrxl(String cjrxl) {
		this.cjrxl = cjrxl;
	}
	@Column(columnDefinition=DEF_STR50)
	public String getCrmsztd() {
		return crmsztd;
	}

	public void setCrmsztd(String crmsztd) {
		this.crmsztd = crmsztd;
	}
	@Column(columnDefinition=DEF_STR50)
	public String getCrmtd() {
		return crmtd;
	}

	public void setCrmtd(String crmtd) {
		this.crmtd = crmtd;
	}
	@Column(columnDefinition=DEF_STR16)
	public String getJjlxrprovince() {
		return jjlxrprovince;
	}

	public void setJjlxrprovince(String jjlxrprovince) {
		this.jjlxrprovince = jjlxrprovince;
	}
	@Column(columnDefinition=DEF_STR16)
	public String getJjlxrcity() {
		return jjlxrcity;
	}

	public void setJjlxrcity(String jjlxrcity) {
		this.jjlxrcity = jjlxrcity;
	}
	@Column(columnDefinition=DEF_STR16)
	public String getJjlxrarea() {
		return jjlxrarea;
	}

	public void setJjlxrarea(String jjlxrarea) {
		this.jjlxrarea = jjlxrarea;
	}
	@Column(columnDefinition=DEF_STR64)
	public String getTzfkkhh() {
		return tzfkkhh;
	}

	public void setTzfkkhh(String tzfkkhh) {
		this.tzfkkhh = tzfkkhh;
	}
	@Column(columnDefinition=DEF_STR64)
	public String getTzfkkhz() {
		return tzfkkhz;
	}
	public void setTzfkkhz(String tzfkkhz) {
		this.tzfkkhz = tzfkkhz;
	}
	@Column(columnDefinition=DEF_STR100)
	public String getTzfkyhmc() {
		return tzfkyhmc;
	}
	public void setTzfkyhmc(String tzfkyhmc) {
		this.tzfkyhmc = tzfkyhmc;
	}
	@Column(columnDefinition=DEF_STR100)
	public String getTzfkkhmc() {
		return tzfkkhmc;
	}
	public void setTzfkkhmc(String tzfkkhmc) {
		this.tzfkkhmc = tzfkkhmc;
	}
	@Column(columnDefinition=DEF_STR100)
	public String getTzfkyhzh() {
		return tzfkyhzh;
	}

	public void setTzfkyhzh(String tzfkyhzh) {
		this.tzfkyhzh = tzfkyhzh;
	}
	@Column(columnDefinition=DEF_STR64)
	public String getHszjkhh() {
		return hszjkhh;
	}

	public void setHszjkhh(String hszjkhh) {
		this.hszjkhh = hszjkhh;
	}
	@Column(columnDefinition=DEF_STR64)
	public String getHszjkhz() {
		return hszjkhz;
	}

	public void setHszjkhz(String hszjkhz) {
		this.hszjkhz = hszjkhz;
	}
	@Column(columnDefinition=DEF_STR100)
	public String getHszjyhmc() {
		return hszjyhmc;
	}

	public void setHszjyhmc(String hszjyhmc) {
		this.hszjyhmc = hszjyhmc;
	}
	@Column(columnDefinition=DEF_STR100)
	public String getHszjkhmc() {
		return hszjkhmc;
	}

	public void setHszjkhmc(String hszjkhmc) {
		this.hszjkhmc = hszjkhmc;
	}
	@Column(columnDefinition=DEF_STR100)
	public String getHszjyhzh() {
		return hszjyhzh;
	}

	public void setHszjyhzh(String hszjyhzh) {
		this.hszjyhzh = hszjyhzh;
	}
	@Column(columnDefinition=DEF_STR2)
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	@Column(columnDefinition=DEF_STR32)
	public String getAuditPerson() {
		return auditPerson;
	}

	public void setAuditPerson(String auditPerson) {
		this.auditPerson = auditPerson;
	}
	@Column(columnDefinition=DEF_STR512)
	public String getAuditIdea() {
		return auditIdea;
	}

	public void setAuditIdea(String auditIdea) {
		this.auditIdea = auditIdea;
	}
	@Column(columnDefinition=DEF_STR2)
	public String getUpstate() {
		return upstate;
	}

	public void setUpstate(String upstate) {
		this.upstate = upstate;
	}
	
	@Column(columnDefinition=DEF_STR20)
	public String getSfqdwtxy() {
		return sfqdwtxy;
	}

	public void setSfqdwtxy(String sfqdwtxy) {
		this.sfqdwtxy = sfqdwtxy;
	}
	@Column(columnDefinition=DEF_STR20)
	public String getQdxyrq() {
		return qdxyrq;
	}

	public void setQdxyrq(String qdxyrq) {
		this.qdxyrq = qdxyrq;
	}
	@Column(columnDefinition=DEF_STR20)
	public String getWtxyzl() {
		return wtxyzl;
	}

	public void setWtxyzl(String wtxyzl) {
		this.wtxyzl = wtxyzl;
	}
	@Column(columnDefinition=DEF_STR20)
	public String getWtxybbh() {
		return wtxybbh;
	}

	public void setWtxybbh(String wtxybbh) {
		this.wtxybbh = wtxybbh;
	}

	//一对一定义
	@OneToOne(cascade=CascadeType.REFRESH,fetch = FetchType.LAZY)
	@JoinColumn(name="upid", unique= false, nullable=true, insertable=true, updatable=true)
	public UpdateCjrxxHistory getHistory() {
		return history;
	}

	public void setHistory(UpdateCjrxxHistory history) {
		this.history = history;
	}
	//一对多定义
	@OneToMany(targetEntity=UpdateCjrxxHistory.class,cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="CJRXX_ID",updatable=false)
	public Set<UpdateCjrxxHistory> getUpHistorys() {
		return upHistorys;
	}

	public void setUpHistorys(Set<UpdateCjrxxHistory> upHistorys) {
		this.upHistorys = upHistorys;
	}
	
	@Column(insertable = false)
	public Timestamp getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Timestamp auditTime) {
		this.auditTime = auditTime;
	}

	public XhcfCjrxx() {
		super();
	}
	/**
	 * 修改审批通过，赋值
	 * @param uch
	 */
	public void setXhcfCjrxx(UpdateCjrxxHistory uch) {
		this.yddh = uch.getYddh();
		this.gddh = uch.getGddh();
		this.dzyx = uch.getDzyx();
		this.gzdwmc = uch.getGzdwmc();
		this.zqjsfs = uch.getZqjsfs();
		this.yb = uch.getYb();
		this.province = uch.getProvince();
		this.city = uch.getCity();
		this.area = uch.getArea();
		this.txdz = uch.getTxdz();
		this.dzyx = uch.getDzyx();
		this.jjlxrzwmc = uch.getJjlxrzwmc();
		this.jjlxrzjlx = uch.getJjlxrzjlx();
		this.jjlxrzjhm = uch.getJjlxrzjhm();
		this.jjlxryddh = uch.getJjlxryddh();
		this.jjlxrgddh = uch.getJjlxrgddh();
	}
	
	/**
	 * 开户赋值
	 * @param uch
	 */
	public void setOpenAccCjrxx(XhcfCjrxx uch) {
		this.mqxm = uch.getMqxm();
		this.tzfkkhh = uch.getTzfkkhh();
		this.tzfkkhz = uch.getTzfkkhz();
		this.tzfkyhmc = uch.getTzfkyhmc();
		this.tzfkkhmc = uch.getTzfkkhmc();
		this.tzfkyhzh = uch.getTzfkyhzh();
		this.hszjkhh = uch.getHszjkhh();
		this.hszjkhz = uch.getHszjkhz();
		this.hszjyhmc = uch.getHszjyhmc();
		this.hszjkhmc = uch.getHszjkhmc();
		this.hszjyhzh = uch.getHszjyhzh();
		this.zqjsfs = uch.getZqjsfs();
		this.dzyx = uch.getDzyx();
		this.sfqdwtxy = uch.getSfqdwtxy();//是否签订委托协议
		this.qdxyrq = uch.getQdxyrq();//协议签订日期
		this.wtxyzl = uch.getWtxyzl();//委托协议种类
		this.wtxybbh = uch.getWtxybbh();//委托协议版本号
	}
	
}
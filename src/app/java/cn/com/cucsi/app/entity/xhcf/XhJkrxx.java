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
 * @author hj
 *
 */
@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "XH_JKRXX")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XhJkrxx extends AuditableEntity {

	// Fields

	private String jkrxm;			//中文名字
	private String ywmc;			//英文名称
	private String csrq;			//出生日期
	private String hjszd;			//户籍所在地
	private String xzz;				//现住址
	private String xzzdh;			//现住址电话
	private String mqxm;			//母亲姓名
	private String zjhm;			//证件号码
	private String zjqfrq;			//签发日期
	private String qjsxrq;			//失效日期
	private String fzjg;				//发证机关
	private String gzdwmc;		//工作单位
	private short gznx;			//工作年限
	private String dwdh;			//单位电话
	private String dwdz;			//单位地址
	private String yddh;			//移动电话
	private String jtdh;				//家庭电话
	private String txdz;			//通讯地址
	private String dzyx;			//电子邮箱
	private String yb;				//邮编
	private double sylv;			//适用利率
	private String qqhm;			//QQ号码
	private String ywzn;			//有无子女
	private double nsr;			//年收入
	private String srsm;			//收入说明
	private String zzzksm;		//居住状况说明
	private String poxm;			//配偶姓名
	private short ponl;			//配偶年龄
	private String pocsrq;			//配偶出生日期
	private String pocertificates;//配偶证件类型
	private String posfzh;			//配偶证件号码
	private String poxzdz;		//配偶现住地址
	private String posj;			//配偶手机
	private String pojtdh;			//配偶家庭电话
	private String pogzdw;		//配偶工作单位
	private String pobmyzw;		//配偶部门与职务
	private String podwdh;		//配偶单位电话
	private String podwdz;		//配偶单位地址
	private double ponsr;		//配偶年收入
	private double pogzyx;		//配偶工作年限
	private double zysrlysrje;	//主要收入来源收入金额
	private String sfyysbjj;		//是否拥有社保基金
	private String zw;				//职务
	private String dkrhy;			//行业
	private String zy;				//职业
	private String dwgm;			//单位规模
	private String yy;				//语言
	private String gj;				//国籍
	private String xb;				//性别
	private String dkrzt;			//贷款人状态
	private String zysrlylx;		//主要收入来源类型
	private String poxb;			//配偶性别
	private String jzzk;				//居住状况
	private String dwxz;			//单位性质
	private String zjlx;				//证件类型
	private String hyzk;			//婚姻状况
	private String ztFlag;			//修改状态
	private String qybm;			//区域编码
	private String khbm;			//客户编码
	private String userName;	//操作人
	private String khjlName;		//客户经理名称
	private String optionTime;	//操作时间
	
	private String liveState;		//居住状态  01:自有房屋，有无贷款月供*元;02:亲属产权;03:租房，房租* 元/月;04:其他
	private String liveMessage; //居住状态说明
	
	private Employee employee;
	private String loginName;

	private String state;		//0暂存 	1待审批 	2审批通过，3审批不通过，9删除
	private String upstate;	//变更状态-1，无修改，0暂存, 1待审批，3审批不通过
	
	private String province;	//省份
	private String city;			//地市
	private String area;		//区县
	private Organi organi;	//所在组织
	private String auditPerson;			//审核人
	private String auditIdea;				//审核意见
	private Timestamp auditTime;		//审核时间
	
	private String changeState;			//是否有变更申请。1：有变更申请;其他为无变更申请
	
//	private Set<XhJkjjlxr> xhJkjjlxrs = new HashSet<XhJkjjlxr>();//紧急联系人
//	private Set<XhJksq> xhjksqs = new HashSet<XhJksq>();//借款申请信息
	
	// Constructors

	/** default constructor */
	public XhJkrxx() {
	}

	/** minimal constructor */
	public XhJkrxx(String jkrxm, String zjhm, String yddh,
			String txdz, String dzyx, double nsr) {
		this.jkrxm = jkrxm;
		this.zjhm = zjhm;
		this.yddh = yddh;
		this.txdz = txdz;
		this.dzyx = dzyx;
		this.nsr = nsr;
	}

	/** full constructor */
	public XhJkrxx(String jkrxm, String ywmc, String csrq,
			String hjszd, String xzz, String xzzdh, String mqxm, String zjhm,
			String zjqfrq, String qjsxrq, String fzjg, String gzdwmc,
			short gznx, String dwdh, String dwdz, String yddh, String jtdh,
			String txdz, String dzyx, String yb, double sylv, String qqhm,
			String ywzn, double nsr, String srsm, String zzzksm, String poxm,
			short ponl, String pocsrq, String pocertificates, String posfzh, String poxzdz, String posj,
			String pojtdh, String pogzdw, String pobmyzw, String podwdh,
			String podwdz, double ponsr, double pogzyx, double zysrlysrje,
			String sfyysbjj, String zw, String dkrhy, String zy, String dwgm,
			String yy, String gj, String xb, String dkrzt, String zysrlylx,
			String poxb, String jzzk, String dwxz, String zjlx, String hyzk,
			String ztFlag, String qybm, String khbm, String userName,
			String khjlName, String optionTime, String liveState, String liveMessage, Set xhcfDkrxxlcxgjls,
			Set xhcfDkjjlxrs, Set xhcfDksqs, String state, String upstate, 
			String province, String city, String area, Organi organi, 
			String auditPerson, String auditIdea, Timestamp auditTime, 
			String changeState) {
		this.jkrxm = jkrxm;
		this.ywmc = ywmc;
		this.csrq = csrq;
		this.hjszd = hjszd;
		this.xzz = xzz;
		this.xzzdh = xzzdh;
		this.mqxm = mqxm;
		this.zjhm = zjhm;
		this.zjqfrq = zjqfrq;
		this.qjsxrq = qjsxrq;
		this.fzjg = fzjg;
		this.gzdwmc = gzdwmc;
		this.gznx = gznx;
		this.dwdh = dwdh;
		this.dwdz = dwdz;
		this.yddh = yddh;
		this.jtdh = jtdh;
		this.txdz = txdz;
		this.dzyx = dzyx;
		this.yb = yb;
		this.sylv = sylv;
		this.qqhm = qqhm;
		this.ywzn = ywzn;
		this.nsr = nsr;
		this.srsm = srsm;
		this.zzzksm = zzzksm;
		this.poxm = poxm;
		this.ponl = ponl;
		this.pocsrq = pocsrq;
		this.pocertificates = pocertificates;
		this.posfzh = posfzh;
		this.poxzdz = poxzdz;
		this.posj = posj;
		this.pojtdh = pojtdh;
		this.pogzdw = pogzdw;
		this.pobmyzw = pobmyzw;
		this.podwdh = podwdh;
		this.podwdz = podwdz;
		this.ponsr = ponsr;
		this.pogzyx = pogzyx;
		this.zysrlysrje = zysrlysrje;
		this.sfyysbjj = sfyysbjj;
		this.zw = zw;
		this.dkrhy = dkrhy;
		this.zy = zy;
		this.dwgm = dwgm;
		this.yy = yy;
		this.gj = gj;
		this.xb = xb;
		this.dkrzt = dkrzt;
		this.zysrlylx = zysrlylx;
		this.poxb = poxb;
		this.jzzk = jzzk;
		this.dwxz = dwxz;
		this.zjlx = zjlx;
		this.hyzk = hyzk;
		this.ztFlag = ztFlag;
		this.qybm = qybm;
		this.khbm = khbm;
		this.userName = userName;
		this.khjlName = khjlName;
		this.optionTime = optionTime;
		this.liveState = liveState;
		this.liveMessage = liveMessage;
		this.upstate = upstate;
//		this.xhcfDkrxxlcxgjls = xhcfDkrxxlcxgjls;
//		this.xhcfDkjjlxrs = xhcfDkjjlxrs;
//		this.xhcfDksqs = xhcfDksqs;
		this.state = state;
		this.province = province;
		this.city = city;
		this.area = area;
		this.organi = organi;
		this.auditPerson = auditPerson;
		this.auditIdea = auditIdea;
		this.auditTime = auditTime;
		this.changeState = changeState;
	}

	public void setXhDkrxx(XhJkrxx xhdkrxx){
		if(null != xhdkrxx.getJkrxm() && !"".equals(xhdkrxx.getJkrxm())){
			this.jkrxm = xhdkrxx.getJkrxm();//借款人姓名
		}
		if(null != xhdkrxx.getYwmc() && !"".equals(xhdkrxx.getYwmc())){
			this.ywmc = xhdkrxx.getYwmc();//英文名
		}
		if(null != xhdkrxx.getXb() && !"".equals(xhdkrxx.getXb())){
			this.xb = xhdkrxx.getXb();//性别
		}
		if(null != xhdkrxx.getCsrq() && !"".equals(xhdkrxx.getCsrq())){
			this.csrq = xhdkrxx.getCsrq();//出生日期
		}
		if(null != xhdkrxx.getZjlx() && !"".equals(xhdkrxx.getZjlx())){
			this.zjlx = xhdkrxx.getZjlx();//证件类型
		}
		if(null != xhdkrxx.getZjhm() && !"".equals(xhdkrxx.getZjhm())){
			this.zjhm = xhdkrxx.getZjhm();//证件号码
		}
		if(null != xhdkrxx.getHjszd() && !"".equals(xhdkrxx.getHjszd())){
			this.hjszd = xhdkrxx.getHjszd();//户籍地址
		}
		if(null != xhdkrxx.getXzzdh() && !"".equals(xhdkrxx.getXzzdh())){
			this.xzzdh = xhdkrxx.getXzzdh();//家庭电话
		}
		if(null != xhdkrxx.getGzdwmc() && !"".equals(xhdkrxx.getGzdwmc())){
			this.gzdwmc = xhdkrxx.getGzdwmc();//工作单位
		}
		if(null != xhdkrxx.getDwdh() && !"".equals(xhdkrxx.getDwdh())){
			this.dwdh = xhdkrxx.getDwdh();//单位电话
		}
		if(null != xhdkrxx.getDwdz() && !"".equals(xhdkrxx.getDwdz())){
			this.dwdz = xhdkrxx.getDwdz();//单位地址
		}
		if(null != xhdkrxx.getDwxz() && !"".equals(xhdkrxx.getDwxz())){
			this.dwxz = xhdkrxx.getDwxz();//单位性质
		}
		if(null != xhdkrxx.getYddh() && !"".equals(xhdkrxx.getYddh())){
			this.yddh = xhdkrxx.getYddh();//移动电话
		}
		if(null != xhdkrxx.getYddh() && !"".equals(xhdkrxx.getYddh())){
			this.dzyx = xhdkrxx.getDzyx();//电子邮箱
		}
		if(null != xhdkrxx.getHyzk() && !"".equals(xhdkrxx.getHyzk())){
			this.hyzk = xhdkrxx.getHyzk();//婚姻状况
		}
		if(null != xhdkrxx.getYwzn() && !"".equals(xhdkrxx.getYwzn())){
			this.ywzn = xhdkrxx.getYwzn();//有无子女
		}
		if(null != xhdkrxx.getQqhm() && !"".equals(xhdkrxx.getQqhm())){
			this.qqhm = xhdkrxx.getQqhm();//QQ号码
		}
		if(0 != xhdkrxx.getNsr() ){
			this.nsr = xhdkrxx.getNsr();//年收入
		}
		if(null != xhdkrxx.getSrsm() && !"".equals(xhdkrxx.getSrsm())){
			this.srsm = xhdkrxx.getSrsm();//收入说明
		}
		if(null != xhdkrxx.getLiveState() && !"".equals(xhdkrxx.getLiveState())){
			this.liveState = xhdkrxx.getLiveState();//居住状态
		}
		if(null != xhdkrxx.getLiveState() && !"".equals(xhdkrxx.getLiveState())){
			this.liveMessage = xhdkrxx.getLiveMessage();//居住状态说明
		}
		if(null != xhdkrxx.getPoxm() && !"".equals(xhdkrxx.getPoxm())){
			this.poxm = xhdkrxx.getPoxm();//配偶姓名
		}
		if(null != xhdkrxx.getPoxb() && !"".equals(xhdkrxx.getPoxb())){
			this.poxb = xhdkrxx.getPoxb();//配偶性别
		}
		if(null != xhdkrxx.getPoxb() && !"".equals(xhdkrxx.getPoxb())){
			this.pocsrq = xhdkrxx.getPocsrq();//配偶出生日期
		}
		if(null != xhdkrxx.getPoxzdz() && !"".equals(xhdkrxx.getPoxzdz())){
			this.poxzdz = xhdkrxx.getPoxzdz();//配偶现住址
		}
		if(null != xhdkrxx.getPocertificates() && !"".equals(xhdkrxx.getPocertificates())){
			this.pocertificates = xhdkrxx.getPocertificates();//配偶证件类型
		}
		if(null != xhdkrxx.getPosfzh() && !"".equals(xhdkrxx.getPosfzh())){
			this.posfzh = xhdkrxx.getPosfzh();//配偶证件号码
		}
		if(null != xhdkrxx.getPosj() && !"".equals(xhdkrxx.getPosj())){
			this.posj = xhdkrxx.getPosj();//配偶移动电话
		}
		if(null != xhdkrxx.getPojtdh() && !"".equals(xhdkrxx.getPojtdh())){
			this.pojtdh = xhdkrxx.getPojtdh();//配偶家庭电话
		}
		if(null != xhdkrxx.getPogzdw() && !"".equals(xhdkrxx.getPogzdw())){
			this.pogzdw = xhdkrxx.getPogzdw();//配偶工作单位
		}
		if(null != xhdkrxx.getPobmyzw() && !"".equals(xhdkrxx.getPobmyzw())){
			this.pobmyzw = xhdkrxx.getPobmyzw();//配偶部门与职务
		}
		if(null != xhdkrxx.getPodwdh() && !"".equals(xhdkrxx.getPodwdh())){
			this.podwdh = xhdkrxx.getPodwdh();//配偶单位电话
		}
		if(null != xhdkrxx.getPodwdh() && !"".equals(xhdkrxx.getPodwdh())){
			this.podwdz = xhdkrxx.getPodwdz();//配偶单位地址
		}
		if(0 != xhdkrxx.getPonsr() ){
			this.ponsr = xhdkrxx.getPonsr();//配偶年收入
		}
		if(null != xhdkrxx.getPodwdh() && !"".equals(xhdkrxx.getPodwdh())){
			this.pogzyx = xhdkrxx.getPogzyx();//配偶工作年现
		}
		
	}
	
	// Property accessors

	/**
	 * 中文名字
	 */
	@Column(columnDefinition=DEF_STR32)
	public String getJkrxm() {
		return this.jkrxm;
	}

	/**
	 * 中文名字
	 */
	public void setJkrxm(String jkrxm) {
		this.jkrxm = jkrxm;
	}

	/**
	 * 英文名称
	 */
	@Column(columnDefinition=DEF_STR32)
	public String getYwmc() {
		return this.ywmc;
	}

	/**
	 * 英文名称
	 */
	public void setYwmc(String ywmc) {
		this.ywmc = ywmc;
	}

	/**
	 * 出生日期
	 */
	@Column(columnDefinition=DEF_STR32)
	public String getCsrq() {
		return this.csrq;
	}

	/**
	 * 出生日期
	 */
	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}

	/**
	 * 户籍所在地
	 */
	@Column(columnDefinition=DEF_STR128)
	public String getHjszd() {
		return this.hjszd;
	}

	/**
	 * 户籍所在地
	 */
	public void setHjszd(String hjszd) {
		this.hjszd = hjszd;
	}

	/**
	 * 现住址
	 */
	@Column(columnDefinition=DEF_STR128)
	public String getXzz() {
		return this.xzz;
	}

	/**
	 * 现住址
	 */
	public void setXzz(String xzz) {
		this.xzz = xzz;
	}

	/**
	 * 现住址电话
	 */
	@Column(columnDefinition=DEF_STR32)
	public String getXzzdh() {
		return this.xzzdh;
	}

	/**
	 * 现住址电话
	 */
	public void setXzzdh(String xzzdh) {
		this.xzzdh = xzzdh;
	}

	/**
	 * 母亲姓名
	 */
	@Column(columnDefinition=DEF_STR32)
	public String getMqxm() {
		return this.mqxm;
	}

	/**
	 * 母亲姓名
	 */
	public void setMqxm(String mqxm) {
		this.mqxm = mqxm;
	}

	/**
	 * 证件号码
	 */
	@Column(columnDefinition=DEF_STR64)
	public String getZjhm() {
		return this.zjhm;
	}

	/**
	 * 证件号码
	 */
	public void setZjhm(String zjhm) {
		this.zjhm = zjhm;
	}

	/**
	 * 签发日期
	 */
	@Column(columnDefinition=DEF_STR32)
	public String getZjqfrq() {
		return this.zjqfrq;
	}

	/**
	 * 签发日期
	 */
	public void setZjqfrq(String zjqfrq) {
		this.zjqfrq = zjqfrq;
	}

	/**
	 * 失效日期
	 */
	@Column(columnDefinition=DEF_STR32)
	public String getQjsxrq() {
		return this.qjsxrq;
	}

	/**
	 * 失效日期
	 */
	public void setQjsxrq(String qjsxrq) {
		this.qjsxrq = qjsxrq;
	}

	/**
	 * 发证机关
	 */
	@Column(columnDefinition=DEF_STR128)
	public String getFzjg() {
		return this.fzjg;
	}

	/**
	 * 发证机关
	 */
	public void setFzjg(String fzjg) {
		this.fzjg = fzjg;
	}

	/**
	 * 工作单位
	 */
	@Column(columnDefinition=DEF_STR256)
	public String getGzdwmc() {
		return this.gzdwmc;
	}

	/**
	 * 工作单位
	 */
	public void setGzdwmc(String gzdwmc) {
		this.gzdwmc = gzdwmc;
	}

	/**
	 * 工作年限
	 */
	@Column(columnDefinition=DEF_INT4)
	public short getGznx() {
		return this.gznx;
	}

	/**
	 * 工作年限
	 */
	public void setGznx(short gznx) {
		this.gznx = gznx;
	}

	/**
	 * 单位电话
	 */
	@Column(columnDefinition=DEF_STR32)
	public String getDwdh() {
		return this.dwdh;
	}

	/**
	 * 单位电话
	 */
	public void setDwdh(String dwdh) {
		this.dwdh = dwdh;
	}

	/**
	 * 单位地址
	 */
	@Column(columnDefinition=DEF_STR256)
	public String getDwdz() {
		return this.dwdz;
	}

	/**
	 * 单位地址
	 */
	public void setDwdz(String dwdz) {
		this.dwdz = dwdz;
	}

	/**
	 * 移动电话
	 */
	@Column(columnDefinition=DEF_STR32)
	public String getYddh() {
		return this.yddh;
	}

	/**
	 * 移动电话
	 */
	public void setYddh(String yddh) {
		this.yddh = yddh;
	}

	/**
	 * 家庭电话
	 */
	@Column(columnDefinition=DEF_STR32)
	public String getJtdh() {
		return this.jtdh;
	}

	/**
	 * 家庭电话
	 */
	public void setJtdh(String jtdh) {
		this.jtdh = jtdh;
	}

	/**
	 * 通讯地址
	 */
	@Column(columnDefinition=DEF_STR256)
	public String getTxdz() {
		return this.txdz;
	}

	/**
	 * 通讯地址
	 */
	public void setTxdz(String txdz) {
		this.txdz = txdz;
	}

	/**
	 * 电子邮箱
	 */
	@Column(columnDefinition=DEF_STR128)
	public String getDzyx() {
		return this.dzyx;
	}

	/**
	 * 电子邮箱
	 */
	public void setDzyx(String dzyx) {
		this.dzyx = dzyx;
	}
	
	/**
	 * 邮编
	 */
	@Column(columnDefinition=DEF_STR32)
	public String getYb() {
		return this.yb;
	}
	
	/**
	 * 邮编
	 */
	public void setYb(String yb) {
		this.yb = yb;
	}
	
	/**
	 * 适用利率
	 */
	@Column(columnDefinition=DEF_NUM36_25)
	public double getSylv() {
		return this.sylv;
	}
	
	/**
	 * 适用利率
	 */
	public void setSylv(double sylv) {
		this.sylv = sylv;
	}
	
	/**
	 * QQ号码
	 */
	@Column(columnDefinition=DEF_STR32)
	public String getQqhm() {
		return this.qqhm;
	}
	
	/**
	 * QQ号码
	 */
	public void setQqhm(String qqhm) {
		this.qqhm = qqhm;
	}
	
	/**
	 * 有无子女
	 */
	@Column(columnDefinition=DEF_STR10)
	public String getYwzn() {
		return this.ywzn;
	}
	
	/**
	 * 有无子女
	 */
	public void setYwzn(String ywzn) {
		this.ywzn = ywzn;
	}	
	
	/**
	 * 年收入
	 */
	@Column(columnDefinition=DEF_NUM26_5)
	public double getNsr() {
		return this.nsr;
	}
	
	/**
	 * 年收入
	 */
	public void setNsr(double nsr) {
		this.nsr = nsr;
	}
	
	/**
	 * 收入说明
	 */
	@Column(columnDefinition=DEF_STR1024)
	public String getSrsm() {
		return this.srsm;
	}
	
	/**
	 * 收入说明
	 */
	public void setSrsm(String srsm) {
		this.srsm = srsm;
	}
	
	/**
	 * 居住状况说明
	 */
	@Column(columnDefinition=DEF_STR128)
	public String getZzzksm() {
		return this.zzzksm;
	}
	
	/**
	 * 居住状况说明
	 */
	public void setZzzksm(String zzzksm) {
		this.zzzksm = zzzksm;
	}
	
	/**
	 * 配偶姓名
	 */
	@Column(columnDefinition=DEF_STR32)
	public String getPoxm() {
		return this.poxm;
	}
	
	/**
	 * 配偶姓名
	 */
	public void setPoxm(String poxm) {
		this.poxm = poxm;
	}
	
	/**
	 * 配偶年龄
	 */
	@Column(columnDefinition=DEF_INT3)
	public short getPonl() {
		return this.ponl;
	}
	
	/**
	 * 配偶年龄
	 */
	public void setPonl(short ponl) {
		this.ponl = ponl;
	}
	
	/**
	 * 配偶出生日期
	 */
	@Column(columnDefinition=DEF_STR32)
	public String getPocsrq() {
		return pocsrq;
	}
	
	/**
	 * 配偶出生日期
	 */
	public void setPocsrq(String pocsrq) {
		this.pocsrq = pocsrq;
	}

	/**
	 * 配偶证件类型
	 */
	public String getPocertificates() {
		return pocertificates;
	}

	/**
	 * 配偶证件类型
	 */
	public void setPocertificates(String pocertificates) {
		this.pocertificates = pocertificates;
	}

	/**
	 * 配偶证件号码
	 */
	@Column(columnDefinition=DEF_STR32)
	public String getPosfzh() {
		return this.posfzh;
	}
	
	/**
	 * 配偶证件号码
	 */
	public void setPosfzh(String posfzh) {
		this.posfzh = posfzh;
	}
	
	/**
	 * 配偶现住地址
	 */
	@Column(columnDefinition=DEF_STR128)
	public String getPoxzdz() {
		return this.poxzdz;
	}
	
	/**
	 * 配偶现住地址
	 */
	public void setPoxzdz(String poxzdz) {
		this.poxzdz = poxzdz;
	}
	
	/**
	 * 配偶手机
	 */
	@Column(columnDefinition=DEF_STR32)
	public String getPosj() {
		return this.posj;
	}
	
	/**
	 * 配偶手机
	 */
	public void setPosj(String posj) {
		this.posj = posj;
	}
	
	/**
	 * 配偶家庭电话
	 */
	@Column(columnDefinition=DEF_STR32)
	public String getPojtdh() {
		return this.pojtdh;
	}
	
	/**
	 * 配偶家庭电话
	 */
	public void setPojtdh(String pojtdh) {
		this.pojtdh = pojtdh;
	}
	
	/**
	 * 配偶工作单位
	 */
	@Column(columnDefinition=DEF_STR128)
	public String getPogzdw() {
		return this.pogzdw;
	}
	
	/**
	 * 配偶工作单位
	 */
	public void setPogzdw(String pogzdw) {
		this.pogzdw = pogzdw;
	}
	
	/**
	 * 配偶部门与职务
	 */
	@Column(columnDefinition=DEF_STR64)
	public String getPobmyzw() {
		return this.pobmyzw;
	}
	
	/**
	 * 配偶部门与职务
	 */
	public void setPobmyzw(String pobmyzw) {
		this.pobmyzw = pobmyzw;
	}
	
	/**
	 * 配偶单位电话
	 */
	@Column(columnDefinition=DEF_STR32)
	public String getPodwdh() {
		return this.podwdh;
	}
	
	/**
	 * 配偶单位电话
	 */
	public void setPodwdh(String podwdh) {
		this.podwdh = podwdh;
	}
	
	/**
	 * 配偶单位地址
	 */
	@Column(columnDefinition=DEF_STR128)
	public String getPodwdz() {
		return this.podwdz;
	}
	
	/**
	 * 配偶单位地址
	 */
	public void setPodwdz(String podwdz) {
		this.podwdz = podwdz;
	}
	
	/**
	 * 配偶年收入
	 */
	@Column(columnDefinition=DEF_NUM26_5)
	public double getPonsr() {
		return this.ponsr;
	}
	
	/**
	 * 配偶年收入
	 */
	public void setPonsr(double ponsr) {
		this.ponsr = ponsr;
	}
	
	/**
	 * 配偶工作年限
	 */
	@Column(columnDefinition=DEF_NUM4_2)
	public double getPogzyx() {
		return this.pogzyx;
	}
	
	/**
	 * 配偶工作年限
	 */
	public void setPogzyx(double pogzyx) {
		this.pogzyx = pogzyx;
	}
	
	/**
	 * 主要收入来源收入金额
	 */
	@Column(columnDefinition=DEF_NUM10_2)
	public double getZysrlysrje() {
		return this.zysrlysrje;
	}
	
	/**
	 * 主要收入来源收入金额
	 */
	public void setZysrlysrje(double zysrlysrje) {
		this.zysrlysrje = zysrlysrje;
	}
	
	/**
	 * 是否拥有社保基金
	 */
	@Column(columnDefinition=DEF_STR10)
	public String getSfyysbjj() {
		return this.sfyysbjj;
	}
	
	/**
	 * 是否拥有社保基金
	 */
	public void setSfyysbjj(String sfyysbjj) {
		this.sfyysbjj = sfyysbjj;
	}
	
	/**
	 * 职务
	 */
	@Column(columnDefinition=DEF_STR32)
	public String getZw() {
		return this.zw;
	}
	
	/**
	 * 职务
	 */
	public void setZw(String zw) {
		this.zw = zw;
	}
	
	/**
	 * 行业
	 */
	@Column(columnDefinition=DEF_STR32)
	public String getDkrhy() {
		return this.dkrhy;
	}
	
	/**
	 * 行业
	 */
	public void setDkrhy(String dkrhy) {
		this.dkrhy = dkrhy;
	}
	
	/**
	 * 职业
	 */
	@Column(columnDefinition=DEF_STR10)
	public String getZy() {
		return this.zy;
	}
	
	/**
	 * 职业
	 */
	public void setZy(String zy) {
		this.zy = zy;
	}
	
	/**
	 * 单位规模
	 */
	@Column(columnDefinition=DEF_STR32)
	public String getDwgm() {
		return this.dwgm;
	}
	
	/**
	 * 单位规模
	 */
	public void setDwgm(String dwgm) {
		this.dwgm = dwgm;
	}
	
	/**
	 * 语言
	 */
	@Column(columnDefinition=DEF_STR32)
	public String getYy() {
		return this.yy;
	}
	
	/**
	 * 语言
	 */
	public void setYy(String yy) {
		this.yy = yy;
	}
	
	/**
	 * 国籍
	 */
	@Column(columnDefinition=DEF_STR32)
	public String getGj() {
		return this.gj;
	}
	
	/**
	 * 国籍
	 */
	public void setGj(String gj) {
		this.gj = gj;
	}
	
	/**
	 * 性别
	 */
	@Column(columnDefinition=DEF_STR32)
	public String getXb() {
		return this.xb;
	}
	
	/**
	 * 性别
	 */
	public void setXb(String xb) {
		this.xb = xb;
	}
	
	/**
	 * 贷款人状态
	 */
	@Column(columnDefinition=DEF_STR32)
	public String getDkrzt() {
		return this.dkrzt;
	}
	
	/**
	 * 贷款人状态
	 */
	public void setDkrzt(String dkrzt) {
		this.dkrzt = dkrzt;
	}
	
	/**
	 * 主要收入来源类型
	 */
	@Column(columnDefinition=DEF_STR32)
	public String getZysrlylx() {
		return this.zysrlylx;
	}
	
	/**
	 * 主要收入来源类型
	 */
	public void setZysrlylx(String zysrlylx) {
		this.zysrlylx = zysrlylx;
	}
	
	/**
	 * 配偶性别
	 */
	@Column(columnDefinition=DEF_STR32)
	public String getPoxb() {
		return this.poxb;
	}
	
	/**
	 * 配偶性别
	 */
	public void setPoxb(String poxb) {
		this.poxb = poxb;
	}
	
	/**
	 * 居住状况
	 */
	@Column(columnDefinition=DEF_STR32)
	public String getJzzk() {
		return this.jzzk;
	}
	
	/**
	 * 居住状况
	 */
	public void setJzzk(String jzzk) {
		this.jzzk = jzzk;
	}
	
	/**
	 * 单位性质
	 */
	@Column(columnDefinition=DEF_STR32)
	public String getDwxz() {
		return this.dwxz;
	}
	
	/**
	 * 单位性质
	 */
	public void setDwxz(String dwxz) {
		this.dwxz = dwxz;
	}
	
	/**
	 * 证件类型
	 */
	@Column(columnDefinition=DEF_STR32)
	public String getZjlx() {
		return this.zjlx;
	}
	
	/**
	 * 证件类型
	 */
	public void setZjlx(String zjlx) {
		this.zjlx = zjlx;
	}
	
	/**
	 * 婚姻状况
	 */
	@Column(columnDefinition=DEF_STR32)
	public String getHyzk() {
		return this.hyzk;
	}
	
	/**
	 * 婚姻状况
	 */
	public void setHyzk(String hyzk) {
		this.hyzk = hyzk;
	}
	
	/**
	 * 修改状态
	 */
	@Column(columnDefinition=DEF_STR32)
	public String getZtFlag() {
		return this.ztFlag;
	}
	
	/**
	 * 修改状态
	 */
	public void setZtFlag(String ztFlag) {
		this.ztFlag = ztFlag;
	}
	
	/**
	 * 区域编码
	 */
	@Column(columnDefinition=DEF_STR10)
	public String getQybm() {
		return this.qybm;
	}
	
	/**
	 * 区域编码
	 */
	public void setQybm(String qybm) {
		this.qybm = qybm;
	}
	
	/**
	 * 客户编码
	 */
	@Column(columnDefinition=DEF_STR32)
	public String getKhbm() {
		return this.khbm;
	}
	
	/**
	 * 客户编码
	 */
	public void setKhbm(String khbm) {
		this.khbm = khbm;
	}
	
	/**
	 * 操作人
	 */
	@Column(columnDefinition=DEF_STR32)
	public String getUserName() {
		return this.userName;
	}
	
	/**
	 * 操作人
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * 客户经理名称
	 */
	@Column(columnDefinition=DEF_STR32)
	public String getKhjlName() {
		return this.khjlName;
	}
	
	/**
	 * 客户经理名称
	 */
	public void setKhjlName(String khjlName) {
		this.khjlName = khjlName;
	}
	
	/**
	 * 操作时间
	 */
	@Column(columnDefinition=DEF_STR32)
	public String getOptionTime() {
		return this.optionTime;
	}
	
	/**
	 * 操作时间
	 */
	public void setOptionTime(String optionTime) {
		this.optionTime = optionTime;
	}
	
	/**
	 * 居住状态  01:自有房屋，有无贷款月供*元;02:亲属产权;03:租房，房租* 元/月;04:其他
	 */
	@Column(columnDefinition=DEF_STR32)
	public String getLiveState() {
		return liveState;
	}

	
	/**
	 * 居住状态  01:自有房屋，有无贷款月供*元;02:亲属产权;03:租房，房租* 元/月;04:其他
	 */
	public void setLiveState(String liveState) {
		this.liveState = liveState;
	}

	/**
	 * 居住状态说明
	 */
	@Column(columnDefinition=DEF_STR64)
	public String getLiveMessage() {
		return liveMessage;
	}

	/**
	 * 居住状态说明
	 */
	public void setLiveMessage(String liveMessage) {
		this.liveMessage = liveMessage;
	}

	//一对一定义
	@OneToOne(cascade=CascadeType.REFRESH,fetch = FetchType.LAZY)
	@JoinColumn(name="EMPLOYEE_ID", unique= false, nullable=true, insertable=true, updatable=true)
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Column(columnDefinition=DEF_STR32)
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Column(columnDefinition=DEF_STR2)
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(columnDefinition=DEF_STR2)
	public String getUpstate() {
		return upstate;
	}

	public void setUpstate(String upstate) {
		this.upstate = upstate;
	}

	/**
	 * 省份
	 */
	@Column(columnDefinition=DEF_STR16)
	public String getProvince() {
		return province;
	}

	/**
	 * 省份
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * 地市
	 */
	@Column(columnDefinition=DEF_STR16)
	public String getCity() {
		return city;
	}

	/**
	 * 地市
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * 区县
	 */
	@Column(columnDefinition=DEF_STR16)
	public String getArea() {
		return area;
	}

	/**
	 * 区县
	 */
	public void setArea(String area) {
		this.area = area;
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

	/**
	 * 审核人
	 */
	@Column(columnDefinition=DEF_STR32)
	public String getAuditPerson() {
		return auditPerson;
	}

	/**
	 * 审核人
	 */
	public void setAuditPerson(String auditPerson) {
		this.auditPerson = auditPerson;
	}

	/**
	 * 审核人意见
	 */
	@Column(columnDefinition=DEF_STR2048)
	public String getAuditIdea() {
		return auditIdea;
	}

	/**
	 * 审核人意见
	 */
	public void setAuditIdea(String auditIdea) {
		this.auditIdea = auditIdea;
	}

	/**
	 * 审核时间
	 */
	public Timestamp getAuditTime() {
		return auditTime;
	}

	/**
	 * 审核时间
	 */
	public void setAuditTime(Timestamp auditTime) {
		this.auditTime = auditTime;
	}

	/**
	 * 是否有变更申请。1：有变更申请;其他为无变更申请
	 */
	@Column(columnDefinition=DEF_STR2)
	public String getChangeState() {
		return changeState;
	}

	/**
	 * 是否有变更申请。1：有变更申请;其他为无变更申请
	 */
	public void setChangeState(String changeState) {
		this.changeState = changeState;
	}

	/**
	 * 紧急联系人
	 */
//	//一对多定义
//	@OneToMany(targetEntity=XhJkjjlxr.class,cascade=CascadeType.ALL)
//	@Fetch(FetchMode.JOIN)
//	@JoinColumn(name="JKRXX_ID",updatable=false)
//	public Set<XhJkjjlxr> getXhJkjjlxrs() {
//		return xhJkjjlxrs;
//	}
//
//	/**
//	 * 紧急联系人
//	 */
//	public void setXhJkjjlxrs(Set<XhJkjjlxr> xhJkjjlxrs) {
//		this.xhJkjjlxrs = xhJkjjlxrs;
//	}

//	/**借款申请信息*/
//	//一对多定义
//	@OneToMany(targetEntity=XhJkjjlxr.class,cascade=CascadeType.ALL)
//	@Fetch(FetchMode.JOIN)
//	@JoinColumn(name="XHJKSQ_ID",updatable=false)
//	public Set<XhJksq> getXhjksqs() {
//		return xhjksqs;
//	}
//	
//	/**借款申请信息*/
//	public void setXhjksqs(Set<XhJksq> xhjksqs) {
//		this.xhjksqs = xhjksqs;
//	}

	
	

}
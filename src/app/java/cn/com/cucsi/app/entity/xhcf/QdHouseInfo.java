package cn.com.cucsi.app.entity.xhcf;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import cn.com.cucsi.core.orm.hibernate.AuditableEntity;

/**
 * QdHouseInfo entity. 
 * @author MyEclipse Persistence Tools
 */
@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "QD_HOUSE_INFO")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class QdHouseInfo extends AuditableEntity{

	// Fields
	private static final long serialVersionUID = -2276242824197821671L;
	private String loanCode;//借款编号
	/**借款编号*/
	@Column(columnDefinition=DEF_STR32)
	public String getLoanCode() {
		return this.loanCode;
	}
	/**借款编号*/
	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}
	private String state;//借款人状态
	/**借款人状态*/
	@Column(columnDefinition=DEF_STR20)
	public String getState() {
		return this.state;
	}
	/**借款人状态*/
	public void setState(String state) {
		this.state = state;
	}
	private String jkrxm;//借款人姓名
	/**借款人姓名*/
	@Column(columnDefinition=DEF_STR1000)
	public String getJkrxm() {
		return this.jkrxm;
	}
	/**借款人姓名*/
	public void setJkrxm(String jkrxm) {
		this.jkrxm = jkrxm;
	}
	private String jkUse;//借款人借款用途
	/**借款人借款用途*/
	@Column(columnDefinition=DEF_STR32)
	public String getJkUse() {
		return this.jkUse;
	}
	/**借款人借款用途*/
	public void setJkUse(String jkUse) {
		this.jkUse = jkUse;
	}
	private Long city;//城市
	/**城市*/
	@Column(columnDefinition=DEF_NUM18)
	public Long getCity() {
		return this.city;
	}
	/**城市*/
	public void setCity(Long city) {
		this.city = city;
	}
	private String jkType;//借款方式
	/**借款方式*/
	@Column(columnDefinition=DEF_STR32)
	public String getJkType() {
		return this.jkType;
	}
	/**借款方式*/
	public void setJkType(String jkType) {
		this.jkType = jkType;
	}
	private Long zqr;//债权人
	/**债权人*/
	@Column(columnDefinition=DEF_NUM18)
	public Long getZqr() {
		return this.zqr;
	}
	/**债权人*/
	public void setZqr(Long zqr) {
		this.zqr = zqr;
	}
	private Double htje;//初始借款金额
	/**初始借款金额*/
	@Column(columnDefinition=DEF_NUM10_2)
	public Double getHtje() {
		return this.htje;
	}
	/**初始借款金额*/
	public void setHtje(Double htje) {
		this.htje = htje;
	}
	private Double ybjje;//月本
	/**月本*/
	@Column(columnDefinition=DEF_NUM10_2)
	public Double getYbjje() {
		return this.ybjje;
	}
	/**月本*/
	public void setYbjje(Double ybjje) {
		this.ybjje = ybjje;
	}
	private Double ylxje;//月息
	/**月息*/
	@Column(columnDefinition=DEF_NUM10_2)
	public Double getYlxje() {
		return this.ylxje;
	}
	/**月息*/
	public void setYlxje(Double ylxje) {
		this.ylxje = ylxje;
	}
	private Double zhglf;//月帐号管理费
	/**月帐号管理费*/
	@Column(columnDefinition=DEF_NUM10_2)
	public Double getZhglf() {
		return this.zhglf;
	}
	/**月帐号管理费*/
	public void setZhglf(Double zhglf) {
		this.zhglf = zhglf;
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
	private String qdrq;//初始借款时间
	/**初始借款时间*/
	@Column(columnDefinition=DEF_STR20)
	public String getQdrq() {
		return this.qdrq;
	}
	/**初始借款时间*/
	public void setQdrq(String qdrq) {
		this.qdrq = qdrq;
	}
	private Long hkqs;//还款期限
	/**还款期限*/
	@Column(columnDefinition=DEF_NUM2)
	public Long getHkqs() {
		return this.hkqs;
	}
	/**还款期限*/
	public void setHkqs(Long hkqs) {
		this.hkqs = hkqs;
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
	private Double dkll;//贷款利息
	/**贷款利息*/
	@Column(columnDefinition=DEF_NUM10_2)
	public Double getDkll() {
		return this.dkll;
	}
	/**贷款利息*/
	public void setDkll(Double dkll) {
		this.dkll = dkll;
	}
	private String returnrq;//no还款截止日期
	/**no还款截止日期*/
	@Column(columnDefinition=DEF_STR20)
	public String getReturnrq() {
		return this.returnrq;
	}
	/**no还款截止日期*/
	public void setReturnrq(String returnrq) {
		this.returnrq = returnrq;
	}
	private String personWorkCondition;//no借款人职业情况
	/**no借款人职业情况*/
	@Column(columnDefinition=DEF_STR200)
	public String getPersonWorkCondition() {
		return this.personWorkCondition;
	}
	/**no借款人职业情况*/
	public void setPersonWorkCondition(String personWorkCondition) {
		this.personWorkCondition = personWorkCondition;
	}
	private String zjhm;//证件号码
	/**证件号码*/
	@Column(columnDefinition=DEF_STR255)
	public String getZjhm() {
		return this.zjhm;
	}
	/**证件号码*/
	public void setZjhm(String zjhm) {
		this.zjhm = zjhm;
	}
	private Long jqsqid;//借款申请ID
	/**借款申请ID*/
	@Column(columnDefinition=DEF_NUM18)
	public Long getJqsqid() {
		return this.jqsqid;
	}
	/**借款申请ID*/
	public void setJqsqid(Long jqsqid) {
		this.jqsqid = jqsqid;
	}
	private Long jqhtid;//借款合同ID
	/**借款合同ID*/
	@Column(columnDefinition=DEF_NUM18)
	public Long getJqhtid() {
		return this.jqhtid;
	}
	/**借款合同ID*/
	public void setJqhtid(Long jqhtid) {
		this.jqhtid = jqhtid;
	}
	private Long kyzqid;//可用债权ID
	/**可用债权ID*/
	@Column(columnDefinition=DEF_NUM18)
	public Long getKyzqid() {
		return this.kyzqid;
	}
	/**可用债权ID*/
	public void setKyzqid(Long kyzqid) {
		this.kyzqid = kyzqid;
	}
	private String meta1;//备用一个
	/**备用一个*/
	@Column(columnDefinition=DEF_STR100)
	public String getMeta1() {
		return this.meta1;
	}
	/**备用一个*/
	public void setMeta1(String meta1) {
		this.meta1 = meta1;
	}
	private Long province;//省份
	/**省份*/
	@Column(columnDefinition=DEF_NUM18)
	public Long getProvince() {
		return this.province;
	}
	/**省份*/
	public void setProvince(Long province) {
		this.province = province;
	}
}

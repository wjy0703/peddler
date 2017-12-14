package cn.com.cucsi.app.entity.xhcf;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.format.annotation.DateTimeFormat;

import cn.com.cucsi.core.orm.hibernate.AuditableEntity;

/**
 * 资金逾期表.
 * 
 * 使用JPA annotation定义ORM关系.
 * 使用Hibernate annotation定义JPA 1.0未覆盖的部分.
 * 关于属性的annotation 放到get方法的上面。
 * @author 马道永
 */
@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "XH_CAPITAL_OVERDUE")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CapitalOverdue extends AuditableEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1269839504347877022L;
	
	//出借id
	private Long lenderId;
	
	//出借编号
	private String lenderNumber;
	
	//出借名称
	private String lenderName;
	
	//出借身份证号
	private String lenderIdCard;
	
	//银行名称
	private String bankName;
	
	//开户行
	private String bankOpen;
	
	//银行账号
	private String bankNumber;
	
	//逾期日期 用于计算逾期天数 当前时间-逾期时间
	private Date overdueDate;
	
	//逾期金额
	private Double overdueMoney;
	
	//违约金
	private Double damagesMoney;
	
	//罚息
	private Double punishInterest;
	
	//状态
	private String overdueStatr;
	
	//备用字段
	private String spareField01;
	
	//备用字段
	private String spareField02;
	
	//备用字段
	private String spareField03;

	@Column(columnDefinition= DEF_ID )
	public Long getLenderId() {
		return lenderId;
	}

	public void setLenderId(Long lenderId) {
		this.lenderId = lenderId;
	}

	@Column(columnDefinition=DEF_STR64)
	public String getLenderNumber() {
		return lenderNumber;
	}

	public void setLenderNumber(String lenderNumber) {
		this.lenderNumber = lenderNumber;
	}

	@Column(columnDefinition=DEF_STR64)
	public String getLenderName() {
		return lenderName;
	}

	public void setLenderName(String lenderName) {
		this.lenderName = lenderName;
	}

	@Column(columnDefinition=DEF_STR32)
	public String getLenderIdCard() {
		return lenderIdCard;
	}

	public void setLenderIdCard(String lenderIdCard) {
		this.lenderIdCard = lenderIdCard;
	}

	@Column(columnDefinition=DEF_STR256)
	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	@Column(columnDefinition=DEF_STR256)
	public String getBankOpen() {
		return bankOpen;
	}

	public void setBankOpen(String bankOpen) {
		this.bankOpen = bankOpen;
	}

	@Column(columnDefinition=DEF_STR64)
	public String getBankNumber() {
		return bankNumber;
	}

	public void setBankNumber(String bankNumber) {
		this.bankNumber = bankNumber;
	}


	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date getOverdueDate() {
		return overdueDate;
	}

	public void setOverdueDate(Date overdueDate) {
		this.overdueDate = overdueDate;
	}

	@Column(columnDefinition=DEF_NUM10_2)
	public Double getOverdueMoney() {
		return overdueMoney;
	}

	public void setOverdueMoney(Double overdueMoney) {
		this.overdueMoney = overdueMoney;
	}

	@Column(columnDefinition=DEF_NUM10_2)
	public Double getDamagesMoney() {
		return damagesMoney;
	}

	public void setDamagesMoney(Double damagesMoney) {
		this.damagesMoney = damagesMoney;
	}

	@Column(columnDefinition=DEF_NUM10_2)
	public Double getPunishInterest() {
		return punishInterest;
	}

	public void setPunishInterest(Double punishInterest) {
		this.punishInterest = punishInterest;
	}

	@Column(columnDefinition=DEF_STR32)
	public String getOverdueStatr() {
		return overdueStatr;
	}

	public void setOverdueStatr(String overdueStatr) {
		this.overdueStatr = overdueStatr;
	}

	@Column(columnDefinition=DEF_STR256)
	public String getSpareField01() {
		return spareField01;
	}

	public void setSpareField01(String spareField01) {
		this.spareField01 = spareField01;
	}

	@Column(columnDefinition=DEF_STR256)
	public String getSpareField02() {
		return spareField02;
	}

	public void setSpareField02(String spareField02) {
		this.spareField02 = spareField02;
	}

	@Column(columnDefinition=DEF_STR256)
	public String getSpareField03() {
		return spareField03;
	}

	public void setSpareField03(String spareField03) {
		this.spareField03 = spareField03;
	}

}

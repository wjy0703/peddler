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
import cn.com.cucsi.core.orm.hibernate.IdEntity;

/**
 * XhMonthlyDwInfo entity. 
 * @author MyEclipse Persistence Tools
 */
@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "XH_MONTHLY_DW_INFO")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XhMonthlyDwInfo extends IdEntity{

	// Fields
	private static final long serialVersionUID = -2276242824197821671L;
	
	private Double interest;//利息
	/**利息*/
	@Column(columnDefinition=DEF_NUM15_5)
	public Double getInterest() { 
		return this.interest;
	}
	/**利息*/
	public void setInterest(Double interest) {
		this.interest = interest;
	}
	private String loanName;//借款人名称
	/**借款人名称*/
	@Column(columnDefinition=DEF_STR64)
	public String getLoanName() {
		return this.loanName;
	}
	/**借款人名称*/
	public void setLoanName(String loanName) {
		this.loanName = loanName;
	}
	private Double money;//金额
	/**金额*/
	@Column(columnDefinition=DEF_NUM15_5)
	public Double getMoney() {
		return this.money;
	}
	/**金额*/
	public void setMoney(Double money) {
		this.money = money;
	}
	private Long zqtjId;//月应付主表ID
	/**月应付主表ID*/
	@Column(columnDefinition=DEF_NUM18)
	public Long getZqtjId() {
		return this.zqtjId;
	}
	/**月应付主表ID*/
	public void setZqtjId(Long zqtjId) {
		this.zqtjId = zqtjId;
	}
}

package cn.com.cucsi.app.entity.xhcf;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import cn.com.cucsi.core.orm.hibernate.AuditableEntity;

/**
 * XhZqtjDetails entity. 
 * @author MyEclipse Persistence Tools
 */
@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "XH_ZQTJ_DETAILS")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XhZqtjDetails extends AuditableEntity{

	// Fields
	private static final long serialVersionUID = -2276242824197821671L;
	
	private XhZqtj xhZqtj;//债权推荐ID
	/**债权推荐ID*/
	@OneToOne(cascade=CascadeType.REFRESH,fetch = FetchType.LAZY)
	@JoinColumn(name="ZQTJ_ID", unique= false, nullable=true, insertable=true, updatable=true)
	public XhZqtj getXhZqtj() {
		return xhZqtj;
	}
	/**债权推荐ID*/
	public void setXhZqtj(XhZqtj xhZqtj) {
		this.xhZqtj = xhZqtj;
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
	private String hkzq;//还款周期
	/**还款周期*/
	@Column(columnDefinition=DEF_STR20)
	public String getHkzq() {
		return this.hkzq;
	}
	/**还款周期*/
	public void setHkzq(String hkzq) {
		this.hkzq = hkzq;
	}
	
	private Long kyzqjzId;//可用债权价值ID
	/**可用债权价值ID*/
	@Column(name="KYZQJZ_ID")
	public Long getKyzqjzId() {
		return this.kyzqjzId;
	}
	/**可用债权价值ID*/
	public void setKyzqjzId(Long kyzqjzId) {
		this.kyzqjzId = kyzqjzId;
	}
	
	
	private Double zqcybi;//债权持有比例
	/**债权持有比例*/
	@Column(columnDefinition=DEF_NUM15_5)
	public Double getZqcybi() {
		return this.zqcybi;
	}
	/**债权持有比例*/
	public void setZqcybi(Double zqcybi) {
		this.zqcybi = zqcybi;
	}
	
	private Double moneySy;//剩余资金
	/**剩余资金*/
	@Column(columnDefinition=DEF_NUM15_5)
	public Double getMoneySy() {
		return this.moneySy;
	}
	/**剩余资金*/
	public void setMoneySy(Double moneySy) {
		this.moneySy = moneySy;
	}
	private String hkzqSy;//剩余还款周期
	/**剩余还款周期*/
	@Column(columnDefinition=DEF_STR20)
	public String getHkzqSy() {
		return this.hkzqSy;
	}
	/**剩余还款周期*/
	public void setHkzqSy(String hkzqSy) {
		this.hkzqSy = hkzqSy;
	}
	
	
	private Double moneyMonth;//每月还本金
	/**每月还本金*/
	@Column(columnDefinition=DEF_NUM15_5)
	public Double getMoneyMonth() {
		return this.moneyMonth;
	}
	/**每月还本金*/
	public void setMoneyMonth(Double moneyMonth) {
		this.moneyMonth = moneyMonth;
	}
	
	private Double zqlixiMonth;//每月还利息
	/**每月还利息*/
	@Column(columnDefinition=DEF_NUM15_5)
	public Double getZqlixiMonth() {
		return this.zqlixiMonth;
	}
	/**每月还利息*/
	public void setZqlixiMonth(Double zqlixiMonth) {
		this.zqlixiMonth = zqlixiMonth;
	}
	
	private Double zqlixiMonthSg;//每月还利息(首个)
	/**每月还利息(首个)*/
	@Column(columnDefinition=DEF_NUM15_5)
	public Double getZqlixiMonthSg() {
		return this.zqlixiMonthSg;
	}
	/**每月还利息(首个)*/
	public void setZqlixiMonthSg(Double zqlixiMonthSg) {
		this.zqlixiMonthSg = zqlixiMonthSg;
	}
}

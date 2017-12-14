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
 * XhLentmoneywater entity. 
 * @author MyEclipse Persistence Tools
 */
@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "XH_LENTMONEYWATER")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XhLentmoneywater extends AuditableEntity{

	// Fields
	private static final long serialVersionUID = -2276242824197821671L;
	private String state;//状态0申请单,1推荐，2回款
	/**状态0申请单,1推荐，2回款*/
	@Column(columnDefinition=DEF_STR2)
	public String getState() {
		return this.state;
	}
	/**状态0申请单,1推荐，2回款*/
	public void setState(String state) {
		this.state = state;
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
	
	private Long zqtjId;//债权推荐ID
	/**债权推荐ID*/
	@Column(name="ZQTJ_ID")
	public Long getZqtjId() {
		return this.zqtjId;
	}
	/**债权推荐ID*/
	public void setZqtjId(Long zqtjId) {
		this.zqtjId = zqtjId;
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
	
	
	private Double finalMoney;//本金
	/**本金*/
	@Column(columnDefinition=DEF_NUM15_5)
	public Double getFinalMoney() {
		return this.finalMoney;
	}
	/**本金*/
	public void setFinalMoney(Double finalMoney) {
		this.finalMoney = finalMoney;
	}
	
	private Double finalInterest;//利息
	/**利息*/
	@Column(columnDefinition=DEF_NUM15_5)
	public Double getFinalInterest() {
		return this.finalInterest;
	}
	/**利息*/
	public void setFinalInterest(Double finalInterest) {
		this.finalInterest = finalInterest;
	}
	
	private Double finalZhglf;//账户管理费
	/**账户管理费*/
	@Column(columnDefinition=DEF_NUM15_5)
	public Double getFinalZhglf() {
		return this.finalZhglf;
	}
	/**账户管理费*/
	public void setFinalZhglf(Double finalZhglf) {
		this.finalZhglf = finalZhglf;
	}
}

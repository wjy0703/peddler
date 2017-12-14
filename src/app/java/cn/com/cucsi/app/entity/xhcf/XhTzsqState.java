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
 * XhTzsqState entity. 
 * @author MyEclipse Persistence Tools
 */
@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "XH_TZSQ_STATE")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XhTzsqState extends AuditableEntity{

	// Fields
	private static final long serialVersionUID = -2276242824197821671L;
	
	private String describe;//借款申请流程状态说明
	/**借款申请流程状态说明*/
	@Column(columnDefinition=DEF_STR1000)
	public String getDescribe() {
		return this.describe;
	}
	/**借款申请流程状态说明*/
	public void setDescribe(String describe) {
		this.describe = describe;
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
	private String remarks;//备注
	/**备注*/
	@Column(columnDefinition=DEF_STR1000)
	public String getRemarks() {
		return this.remarks;
	}
	/**备注*/
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
private String state;
	
	/**
	 * 借款申请单状态 
	 * */
	@Column(columnDefinition = DEF_STR2)
	public String getState(){
		return state;
	}

	/**
	 * 借款申请单状态 
	 * */
	public void setState(String state){
		this.state = state;
	}
}

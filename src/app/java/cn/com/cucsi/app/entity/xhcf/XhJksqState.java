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
 * XhJksqState entity. 
 * 借款申请流程状态表
 * @author MyEclipse Persistence Tools
 */
@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "XH_JKSQ_STATE")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XhJksqState extends AuditableEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8820109869005304867L;
	
	private XhJksq xhjksq;
	private String describe;
	private String remarks;
	
	/**借款申请单信息*/
	@OneToOne(cascade=CascadeType.REFRESH,fetch = FetchType.LAZY)
	@JoinColumn(name="XHJKSQ_ID", unique= false, nullable=true, insertable=true, updatable=false)
	public XhJksq getXhjksq() {
		return xhjksq;
	}

	/**借款申请单信息*/
	public void setXhjksq(XhJksq xhjksq) {
		this.xhjksq = xhjksq;
	}

	/**借款申请单流程状态描述*/
	@Column(columnDefinition=DEF_STR1000)
	public String getDescribe() {
		return describe;
	}

	/**借款申请单流程状态描述*/
	public void setDescribe(String describe) {
		this.describe = describe;
	}

	/**备注*/
	@Column(columnDefinition=DEF_STR1000)
	public String getRemarks() {
		return remarks;
	}

	/**备注*/
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
	private String state;
	
	/**
	 * 借款申请单状态 
	 * */
	@Column(columnDefinition = DEF_STR8)
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

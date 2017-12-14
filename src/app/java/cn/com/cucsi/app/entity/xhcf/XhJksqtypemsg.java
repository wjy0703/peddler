package cn.com.cucsi.app.entity.xhcf;

import java.util.HashSet;
import java.util.Set;

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
 * XhJksqtypemsg entity. 
 * @author MyEclipse Persistence Tools
 */
@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "XH_JKSQTYPEMSG")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XhJksqtypemsg extends AuditableEntity{

	// Fields
	private static final long serialVersionUID = -2276242824197821671L;

	
	private XhJksq xhjksq;//借款申请信息
	private Templet templet;//模板
	private String key;//字段名称
	private String value;//字段值
	
	/**字段名称*/
	@Column(columnDefinition=DEF_STR2048)
	public String getKey() {
		return this.key;
	}
	
	/**字段名称*/
	public void setKey(String key) {
		this.key = key;
	}
	
	/**字段值*/
	@Column(columnDefinition=DEF_STR4000)
	public String getValue() {
		return this.value;
	}
	
	/**字段值*/
	public void setValue(String value) {
		this.value = value;
	}

	/**借款申请信息*/
	@OneToOne(cascade=CascadeType.REFRESH,fetch = FetchType.LAZY)
	@JoinColumn(name="JKSQ_ID", unique= false, nullable=true, insertable=true, updatable=true)
	public XhJksq getXhjksq() {
		return xhjksq;
	}

	/**借款申请信息*/
	public void setXhjksq(XhJksq xhjksq) {
		this.xhjksq = xhjksq;
	}

	/**模板信息*/
	@OneToOne(cascade=CascadeType.REFRESH,fetch = FetchType.LAZY)
	@JoinColumn(name="TEMPLET_ID", unique= false, nullable=true, insertable=true, updatable=true)
	public Templet getTemplet() {
		return templet;
	}

	/**模板信息*/
	public void setTemplet(Templet templet) {
		this.templet = templet;
	}
	
	
	
}

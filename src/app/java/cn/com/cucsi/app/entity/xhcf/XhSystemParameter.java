package cn.com.cucsi.app.entity.xhcf;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import cn.com.cucsi.core.orm.hibernate.AuditableEntity;

/**
 * XhSystemParameter entity. 
 * @author MyEclipse Persistence Tools
 */
@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "XH_SYSTEM_PARAMETER")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XhSystemParameter extends AuditableEntity{

	// Fields
	private static final long serialVersionUID = -2276242824197821671L;
	private String reamrk;//备注
	/**备注*/
	@Column(columnDefinition=DEF_STR512)
	public String getReamrk() {
		return this.reamrk;
	}
	/**备注*/
	public void setReamrk(String reamrk) {
		this.reamrk = reamrk;
	}
	private String sysValue;//变量值
	/**变量值*/
	@Column(columnDefinition=DEF_STR64)
	public String getSysValue() {
		return this.sysValue;
	}
	/**变量值*/
	public void setSysValue(String sysValue) {
		this.sysValue = sysValue;
	}
	private String sysCname;//变量说明
	/**变量说明*/
	@Column(columnDefinition=DEF_STR64)
	public String getSysCname() {
		return this.sysCname;
	}
	/**变量说明*/
	public void setSysCname(String sysCname) {
		this.sysCname = sysCname;
	}
	private String sysName;//变量名
	/**变量名*/
	@Column(columnDefinition=DEF_STR64)
	public String getSysName() {
		return this.sysName;
	}
	/**变量名*/
	public void setSysName(String sysName) {
		this.sysName = sysName;
	}
}

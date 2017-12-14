package cn.com.peddler.app.entity.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import cn.com.peddler.core.orm.hibernate.AuditableEntity;

/**
 * Authority entity. 
 * @author MyEclipse Persistence Tools
 */
@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "authority")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Authority extends AuditableEntity{

	// Fields
	private static final long serialVersionUID = -2276242824197821671L;
	
	private String cname;//别名
	/**别名*/
	@Column(columnDefinition=DEF_STR256)
	public String getCname() {
		return this.cname;
	}
	/**别名*/
	public void setCname(String cname) {
		this.cname = cname;
	}
	private String vflag;//标记
	/**标记*/
	@Column(columnDefinition=DEF_STR4)
	public String getVflag() {
		return this.vflag;
	}
	/**标记*/
	public void setVflag(String vflag) {
		this.vflag = vflag;
	}
	private String aname;//资源名称
	/**资源名称*/
	@Column(columnDefinition=DEF_STR256)
	public String getAname() {
		return this.aname;
	}
	/**资源名称*/
	public void setAname(String aname) {
		this.aname = aname;
	}
	private String vpath;//路径
	/**路径*/
	@Column(columnDefinition=DEF_STR256)
	public String getVpath() {
		return this.vpath;
	}
	/**路径*/
	public void setVpath(String vpath) {
		this.vpath = vpath;
	}
	private String vsts;//状态
	/**状态*/
	@Column(columnDefinition=DEF_STR4)
	public String getVsts() {
		return this.vsts;
	}
	/**状态*/
	public void setVsts(String vsts) {
		this.vsts = vsts;
	}
	private String vtype;//类型
	/**类型*/
	@Column(columnDefinition=DEF_STR4)
	public String getVtype() {
		return this.vtype;
	}
	/**类型*/
	public void setVtype(String vtype) {
		this.vtype = vtype;
	}
	private String vsystype;//系统属性
	/**系统属性*/
	@Column(columnDefinition=DEF_STR4)
	public String getVsystype() {
		return this.vsystype;
	}
	/**系统属性*/
	public void setVsystype(String vsystype) {
		this.vsystype = vsystype;
	}
}

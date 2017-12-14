package cn.com.cucsi.app.entity.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import cn.com.cucsi.core.orm.hibernate.AuditableEntity;

/**
 * 权限.
 * 
 * 注释见{@link User}.
 * 
 * @author jiangxd
 */
@Entity
@Table(name = "ACCT_AUTHORITY")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Authority extends AuditableEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2276242824197821671L;

	/**
	 * SpringSecurity中默认的角色/授权名前缀.
	 */
	public static final String AUTHORITY_PREFIX = "ROLE_";

	private String name;
	private String cname;
    private String path;
    private String sts;
    private String flag; 
    private String type;
    
	public Authority() {
	}

	public Authority(Long id, String name, String cname, String path) {
		this.id = id;
		this.name = name;
		this.path = path;
		this.setCname(cname);
	}

	@Column(columnDefinition=DEF_STR256, nullable = false, unique = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(columnDefinition=DEF_STR256, nullable = false)
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	@Column(columnDefinition=DEF_STR1)
	public String getSts() {
		return sts;
	}
	
	public void setSts(String sts) {
		this.sts = sts;
	}

	@Column(columnDefinition=DEF_STR32)
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	@Column(columnDefinition=DEF_STR256)
	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	@Transient
	public String getPrefixedName() {
		return AUTHORITY_PREFIX + name;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Column(length=1)
	public String getFlag(){
		return this.flag;
	}
	
	public void setFlag(String flag) {
		this.flag = flag;
		
	}
}

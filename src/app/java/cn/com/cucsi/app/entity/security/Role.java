package cn.com.cucsi.app.entity.security;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import cn.com.cucsi.app.entity.baseinfo.Menu;
import cn.com.cucsi.core.orm.hibernate.AuditableEntity;
import cn.com.cucsi.core.utils.ReflectionUtils;

/**
 * 角色.
 * 
 * 注释见{@link User}.
 * 
 * @author jiangxd
 */
@Entity
@Table(name = "ACCT_ROLE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Role extends AuditableEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1490079389529844637L;
	private String name;
	private String cname;
	private String sts;
	private String flag;
	
	private List<Authority> authorityList = new LinkedList<Authority>();
	
	private List<User> userList = new LinkedList<User>();
	
	private List<Menu> menuList = new LinkedList<Menu>();

	public Role() {

	}

	public Role(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	@Column(columnDefinition=DEF_STR256, nullable = false, unique = true)
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	@Column(columnDefinition=DEF_STR256)
	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCname() {
		return cname;
	}
	
	@Column(columnDefinition=DEF_STR1)
	public void setSts(String sts) {
		this.sts = sts;
	}

	public String getSts() {
		return sts;
	}

	@Column(columnDefinition=DEF_STR1)
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH },targetEntity=Authority.class,fetch = FetchType.LAZY)
	@JoinTable(name = "ACCT_ROLE_AUTHORITY", joinColumns = { @JoinColumn(name = "ROLE_ID") }, inverseJoinColumns = { @JoinColumn(name = "AUTHORITY_ID") })
	@Fetch(FetchMode.SUBSELECT)
	@OrderBy("id")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public List<Authority> getAuthorityList() {
		return authorityList;
	}
	
	public void setAuthorityList(List<Authority> authorityList) {
		this.authorityList = authorityList;
	}

	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH },targetEntity=Menu.class,fetch = FetchType.LAZY)
	@JoinTable(name = "BASE_MENU_ROLE", joinColumns = { @JoinColumn(name = "ROLE_ID") }, inverseJoinColumns = { @JoinColumn(name = "MENU_ID") })
	@Fetch(FetchMode.SUBSELECT)
	@OrderBy("id")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}

	@ManyToMany(targetEntity=User.class, mappedBy="roleList",fetch = FetchType.LAZY)
	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	@Transient
	public String getAuthNames() {
		return ReflectionUtils.convertElementPropertyToString(authorityList, "name", ", ");
	}
	
	@Transient
	public String getMenuNames() {
		return ReflectionUtils.convertElementPropertyToString(menuList, "menuName", ", ");
	}
	
	@Transient
	public String getAuthCNames() {
		return ReflectionUtils.convertElementPropertyToString(authorityList, "cname", ", ");
	}

	@Transient
	public String getAuthIds() {
		return ReflectionUtils.convertElementPropertyToString(authorityList, "id", ",");
	}
	
	@Transient
	public String getMenuIds() {
		return ReflectionUtils.convertElementPropertyToString(menuList, "id", ", ");
	}
	
	@Transient
	public List<Menu> getMenuOne() {
		return ReflectionUtils.convertElementPropertyToListValue(menuList, 1);
	}
	
	@Transient
	@SuppressWarnings("unchecked")
	public List<Long> getAuthIdList() {
		return ReflectionUtils.convertElementPropertyToList(authorityList, "id");
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}

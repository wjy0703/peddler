package cn.com.peddler.app.entity.security;

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

import cn.com.peddler.app.entity.login.Menutable;
import cn.com.peddler.core.orm.hibernate.AuditableEntity;
import cn.com.peddler.core.utils.ReflectionUtils;

/**
 * Roleinfo entity. 
 * @author MyEclipse Persistence Tools
 */
@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "roleinfo")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Roleinfo extends AuditableEntity{

	// Fields
	private static final long serialVersionUID = -1490079389529844637L;
	
	
	public Roleinfo(Long id, String rolename) {
		this.id = id;
		this.rolename = rolename;
	}
	private String rolename;//名称
	/**名称*/
	@Column(columnDefinition=DEF_STR20)
	public String getRolename() {
		return this.rolename;
	}
	/**名称*/
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	private String vtypes;//属性
	/**属性*/
	@Column(columnDefinition=DEF_STR2)
	public String getVtypes() {
		return this.vtypes;
	}
	/**属性*/
	public void setVtypes(String vtypes) {
		this.vtypes = vtypes;
	}
	private Long busid;//所属企业
	/**所属企业*/
	@Column(columnDefinition=DEF_NUM10)
	public Long getBusid() {
		return this.busid;
	}
	/**所属企业*/
	public void setBusid(Long busid) {
		this.busid = busid;
	}
	private String flag;
	@Column(columnDefinition=DEF_STR1)
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	private List<Authority> authorityList = new LinkedList<Authority>();
	
	private List<Userinfo> userList = new LinkedList<Userinfo>();
	
	private List<Menutable> menuList = new LinkedList<Menutable>();
	
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH },targetEntity=Authority.class,fetch = FetchType.LAZY)
	@JoinTable(name = "roleauthority", joinColumns = { @JoinColumn(name = "roleid") }, inverseJoinColumns = { @JoinColumn(name = "authorityid") })
	@Fetch(FetchMode.SUBSELECT)
	@OrderBy("id")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public List<Authority> getAuthorityList() {
		return authorityList;
	}
	
	public void setAuthorityList(List<Authority> authorityList) {
		this.authorityList = authorityList;
	}

	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH },targetEntity=Menutable.class,fetch = FetchType.LAZY)
	@JoinTable(name = "rolemenu", joinColumns = { @JoinColumn(name = "roleid") }, inverseJoinColumns = { @JoinColumn(name = "menuid") })
	@Fetch(FetchMode.SUBSELECT)
	@OrderBy("id")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public List<Menutable> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menutable> menuList) {
		this.menuList = menuList;
	}

	@ManyToMany(targetEntity=Userinfo.class, mappedBy="roleinfo",fetch = FetchType.LAZY)
	public List<Userinfo> getUserList() {
		return userList;
	}

	public void setUserList(List<Userinfo> userList) {
		this.userList = userList;
	}

	@Transient
	public String getAuthNames() {
		return ReflectionUtils.convertElementPropertyToString(authorityList, "aname", ", ");
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
	public List<Menutable> getMenuOne() {
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

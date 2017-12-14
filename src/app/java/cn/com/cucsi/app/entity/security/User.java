package cn.com.cucsi.app.entity.security;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import cn.com.cucsi.app.entity.baseinfo.Employee;
import cn.com.cucsi.core.orm.hibernate.AuditableEntity;
import cn.com.cucsi.core.utils.ReflectionUtils;

/**
 * 用户.
 * 
 * 使用JPA annotation定义ORM关系.
 * 使用Hibernate annotation定义JPA 1.0未覆盖的部分.
 * 关于属性的annotation 放到get方法的上面。
 * @author jiangxd
 */
@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "ACCT_USER")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User extends AuditableEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1960184739612606201L;

	private String loginName;
	
	private String password;
	
	private String cname;
	
	private String ip;
	
	private Date lastLoginDate;
	
	private String sts;	
	
	private Employee employee;
	

	private List<Role> roleList = new LinkedList<Role>();//有序的关联对象集合
	//private String roleNames;
	
	//字段非空且唯一, 用于提醒Entity使用者及生成DDL.
	@Column(columnDefinition=DEF_STR16, nullable = false, unique = true)
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Column(columnDefinition=DEF_STR32)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(columnDefinition=DEF_STR256)
	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	@Column(columnDefinition=DEF_STR64)
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Column(nullable = true)
	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	
	public Date getLastLoginDate() {
		return lastLoginDate;
	}
	
	public void setSts(String sts) {
		this.sts = sts;
	}

	@Column(columnDefinition=DEF_STR1)
	public String getSts() {
		return sts;
	}
	
	//一对一定义
	@OneToOne(cascade=CascadeType.REFRESH,fetch = FetchType.LAZY)
	@JoinColumn(name="EMPLOYEE_ID", unique= false, nullable=true, insertable=true, updatable=true)
	public Employee getEmployee() {
		return employee;
	}
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	//多对多定义
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH },targetEntity=Role.class,fetch = FetchType.LAZY)
	//中间表定义,表名采用默认命名规则
	@JoinTable(name = "ACCT_USER_ROLE", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
	//Fecth策略定义
	@Fetch(FetchMode.SUBSELECT)
	//集合按id排序.
	@OrderBy("id")
	//集合中对象id的缓存.
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	/**
	 * 用户拥有的角色代码字符串, 多角色代码用','分隔.
	 */
	//非持久化属性.
	@Transient
	public String getRoleNames() {
		return ReflectionUtils.convertElementPropertyToString(roleList, "name", ", ");
	}

	/**
	 * 用户拥有的角色名称字符串, 多个角色名称用','分隔.
	 */
	//非持久化属性.
	@Transient
	public String getRoleCNames() {
		return ReflectionUtils.convertElementPropertyToString(roleList, "cname", ", ");
	}

	/**
	 * 用户拥有的角色id字符串, 多个角色id用','分隔.
	 */
	//非持久化属性.
	@Transient
	public String getRoleIds() {
		return ReflectionUtils.convertElementPropertyToString(roleList, "id", ", ");
	}
	
	/**
	 * 用户拥有的角色id字符串, 多个角色id用','分隔.
	 */
	//非持久化属性.
	@Transient
	public String getRoleId() {
		return ReflectionUtils.convertElementPropertyToString(roleList, "id", ",");
	}
	
	/**
	 * 用户拥有的角色id字符串, 多个角色id用','分隔.
	 */
	//非持久化属性.
	@Transient
	@SuppressWarnings("unchecked")
	public List<Long> getRoleIdList() {
		return ReflectionUtils.convertElementPropertyToList(roleList, "id");
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
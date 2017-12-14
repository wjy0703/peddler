package cn.com.peddler.app.entity.baseinfo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import cn.com.peddler.core.orm.hibernate.AuditableEntity;
1
/**
 * 员工.
 * 
 * 使用JPA annotation定义ORM关系.
 * 使用Hibernate annotation定义JPA 1.0未覆盖的部分.
 * 
 * @author jiangxd
 */
@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "BASE_EMPLOYEE")

public class Employee extends AuditableEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1958107672378804945L;
	private String name;
	private String sex;
	private String mobile;
	private String phone;
	private String officeTel;	
	private String mail;
	private String spell;
	private Integer sortNo;
	private String description;
	private String sts;
	private Dept dept;
	private BasePosition position;
	private Organi organi;
	private String empNo;
	
	@Column(columnDefinition=DEF_STR32)
	public String getEmpNo() {
		return empNo;
	}


	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}


	public void setName(String name) {
		this.name = name;
	}
	

	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "position_id")
	public BasePosition getPosition() {
		return position;
	}

	public void setPosition(BasePosition position) {
		this.position = position;
	}

	@Column(columnDefinition=DEF_STR32)
	public String getName() {
		return name;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	@Column(columnDefinition=DEF_STR256)
	public String getMail() {
		return mail;
	}

	public void setSts(String sts) {
		this.sts = sts;
	}

	@Column(columnDefinition=DEF_STATUS)
	public String getSts() {
		return sts;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Column(columnDefinition=DEF_STR1)	
	public String getSex() {
		return sex;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(columnDefinition=DEF_STR16)
	public String getMobile() {
		return mobile;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(columnDefinition=DEF_STR16)
	public String getPhone() {
		return phone;
	}
	
	public void setOfficeTel(String officeTel) {
		this.officeTel = officeTel;
	}

	@Column(columnDefinition=DEF_STR16)
	public String getOfficeTel() {
		return officeTel;
	}

	public void setSpell(String spell) {
		this.spell = spell;
	}

	@Column(columnDefinition=DEF_STR256)
	public String getSpell() {
		return spell;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	@Column(columnDefinition=DEF_INT4)
	public Integer getSortNo() {
		return sortNo;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(columnDefinition=DEF_STR256)
	public String getDescription() {
		return description;
	}

	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "dept_id")
	public Dept getDept() {
		return dept;
	}
	
	public void setDept(Dept dept) {
		this.dept = dept;
	}

	//一对一定义
	@OneToOne(cascade=CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name="organi_id", unique= false, nullable=true, insertable=true, updatable=true)
	public Organi getOrgani() {
		return organi;
	}

	public void setOrgani(Organi organi) {
		this.organi = organi;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
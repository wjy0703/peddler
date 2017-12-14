package cn.com.cucsi.app.entity.baseinfo;


import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import cn.com.cucsi.core.orm.hibernate.AuditableEntity;

/**
 * 部门.
 * 
 * @author kevan
 */
@Entity
@Table(name = "BASE_DEPT")
public class Dept extends AuditableEntity{

	private static final long serialVersionUID = 533999380252969758L;

	private String name;
	private String coding;
	private String cname;
	private String pinyin;
	private Integer sortNo;
	private Integer deptLevel;
	private String sts;

	private Dept parent;
	
	private List<Employee> dept_employees = new LinkedList<Employee>();

	@Column(columnDefinition=DEF_STR128, nullable = false)	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Column(columnDefinition=DEF_STR1)
	public String getSts() {
		return sts;
	}

	public void setSts(String sts) {
		this.sts = sts;
	}


	@Column(columnDefinition=DEF_STR256)
	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	@Column(columnDefinition=DEF_INT4)
	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}
	
	@Column(columnDefinition=DEF_STR16)
	public String getCoding() {
		return coding;
	}

	public void setCoding(String coding) {
		this.coding = coding;
	}

	@Column(columnDefinition=DEF_INT4)
	public Integer getDeptLevel() {
		return deptLevel;
	}

	public void setDeptLevel(Integer deptLevel) {
		this.deptLevel = deptLevel;
	}

	@Column(columnDefinition=DEF_STR64)
	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}
	
	@ManyToOne(cascade=CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	public Dept getParent() {
		return parent;
	}

	public void setParent(Dept parent) {
		this.parent = parent;
	}

	@OneToMany(mappedBy = "dept", cascade = {CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.LAZY)
	public List<Employee> getDept_employees() {
		return dept_employees;
	}

	public void setDept_employees(List<Employee> dept_employees) {
		this.dept_employees = dept_employees;
	}

}
package cn.com.cucsi.app.entity.xhcf;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import cn.com.cucsi.app.entity.baseinfo.Organi;
import cn.com.cucsi.core.orm.hibernate.AuditableEntity;

/**
 * Templet entity. 
 * @author MyEclipse Persistence Tools
 */
@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "XH_TEMPLET")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Templet extends AuditableEntity{
	
	private static final long serialVersionUID = 5111188226307878991L;
	private String names;		//页面显示名称格式：单位性质、职务、在岗时间等（采用数组方式存储数据）
	private String keys;		//页面表单名称格式：formData_key1、formData_key2、formData_key3等（采用数组方式存储数据）
	private String types;		//页面组件类型格式：text、select等（采用数组方式存储数据）
	private String type;		//模板类型   暂定1：借款类型 2：初审 3：复审 4：终审
	private String describe;  //模板描述
	private String state;		//在用状态   0:为在用；其他为不在用
	
	
	public Templet(){}
	
	public Templet(String names, String keys, String types, String type, 
			String describe, String state){
		this.names = names;
		this.keys = keys;
		this.types = types;
		this.type = type;
		this.describe = describe;
		this.state = state;
	}
	/**
	 * 页面显示名称格式
	 */
	@Column(columnDefinition=DEF_STR2048)
	public String getNames() {
		return names;
	}
	
	/**
	 * 页面显示名称格式
	 */
	public void setNames(String names) {
		this.names = names;
	}
	
	/**
	 * 页面表单名称格式formData_key1、formData_key2、formData_key3等
	 */
	@Column(columnDefinition=DEF_STR2048)
	public String getKeys() {
		return keys;
	}
	
	/**
	 * 页面表单名称格式formData_key1、formData_key2、formData_key3等
	 */
	public void setKeys(String keys) {
		this.keys = keys;
	}
	
	/**
	 * 页面组件类型格式：text、select等
	 */
	@Column(columnDefinition=DEF_STR2048)
	public String getTypes() {
		return types;
	}
	
	/**
	 * 页面组件类型格式：text、select等
	 */
	public void setTypes(String types) {
		this.types = types;
	}
	
	/**
	 * 模板类型
	 */
	@Column(columnDefinition=DEF_STR3)
	public String getType() {
		return type;
	}
	
	/**
	 * 模板类型
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * 描述
	 */
	@Column(columnDefinition=DEF_STR4000)
	public String getDescribe() {
		return describe;
	}
	
	/**
	 * 描述
	 */
	public void setDescribe(String describe) {
		this.describe = describe;
	}

	/**
	 * 在用状态   0:为在用；其他为不在用
	 */
	@Column(columnDefinition=DEF_STR2)
	public String getState() {
		return state;
	}

	/**
	 * 在用状态   0:为在用；其他为不在用
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	
}

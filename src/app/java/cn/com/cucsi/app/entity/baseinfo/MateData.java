package cn.com.cucsi.app.entity.baseinfo;

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
 * 字典表.
 * 
 * 使用JPA annotation定义ORM关系.
 * 使用Hibernate annotation定义JPA 1.0未覆盖的部分.
 * 关于属性的annotation 放到get方法的上面。
 * @author 马道永
 */
@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "XH_MATE_DATA")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MateData extends AuditableEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1269839504347877022L;
	
	//名称
	private String name;
	
	//值
	private String value;
	
	//状态
	private Long state;
	
	//编码类型
	private MateDataType code;
	
	//银行类补充一列银行代码
	private String bankCode;
	
	@Column(columnDefinition=DEF_STR16)
	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	@Column(columnDefinition=DEF_STR128)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(columnDefinition=DEF_STR128)
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Column(columnDefinition=DEF_STR16)
	public Long getState() {
		return state;
	}

	public void setState(Long state) {
		this.state = state;
	}

	//一对一定义
	@OneToOne(cascade=CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name="MATEDATATYPE_ID", unique= false, nullable=true, insertable=true, updatable=true)
	public MateDataType getCode() {
		return code;
	}

	public void setCode(MateDataType code) {
		this.code = code;
	}

	

}

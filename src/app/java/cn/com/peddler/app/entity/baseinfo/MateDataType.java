package cn.com.peddler.app.entity.baseinfo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import cn.com.peddler.core.orm.hibernate.AuditableEntity;
1
/**
 * 字典类型表.
 * 
 * 使用JPA annotation定义ORM关系.
 * 使用Hibernate annotation定义JPA 1.0未覆盖的部分.
 * 关于属性的annotation 放到get方法的上面。
 * @author 马道永
 */
@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "XH_MATEDATA_TYPE")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MateDataType extends AuditableEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1269839504347877022L;
	
	//名称
	private String typeName;
	
	//描述
	private String typeDes;
	
	//状态
	private Long typeState;
	
	//类型编码
	private String typeCode;

	@Column(columnDefinition=DEF_STR16)
	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	@Column(columnDefinition=DEF_STR128)
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Column(columnDefinition=DEF_STR128)
	public String getTypeDes() {
		return typeDes;
	}

	public void setTypeDes(String typeDes) {
		this.typeDes = typeDes;
	}

	@Column(columnDefinition= DEF_ID )
	public Long getTypeState() {
		return typeState;
	}

	public void setTypeState(Long typeState) {
		this.typeState = typeState;
	}


}

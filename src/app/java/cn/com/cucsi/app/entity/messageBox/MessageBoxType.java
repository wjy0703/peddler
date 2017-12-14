package cn.com.cucsi.app.entity.messageBox;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import cn.com.cucsi.core.orm.hibernate.IdEntity;

/**
 * 信息盒子类型.
 * 
 * 使用JPA annotation定义ORM关系.
 * 使用Hibernate annotation定义JPA 1.0未覆盖的部分.
 * 关于属性的annotation 放到get方法的上面。
 * @author jiangxd
 */
@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "XH_MESSAGE_BOX_TYPE")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MessageBoxType extends IdEntity {

	private static final long serialVersionUID = -1960184739612606201L;

	private String typeName;
	
	private String typeCoding;
	
	private String typeDescription;

	@Column(columnDefinition=DEF_STR32)
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Column(columnDefinition=DEF_STR32)
	public String getTypeCoding() {
		return typeCoding;
	}

	public void setTypeCoding(String typeCoding) {
		this.typeCoding = typeCoding;
	}

	@Column(columnDefinition=DEF_STR512)
	public String getTypeDescription() {
		return typeDescription;
	}

	public void setTypeDescription(String typeDescription) {
		this.typeDescription = typeDescription;
	}

}
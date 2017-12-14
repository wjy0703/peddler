package cn.com.cucsi.app.entity.baseinfo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import cn.com.cucsi.core.orm.hibernate.AuditableEntity;

/**
 * 组织机构.
 * 
 * 使用JPA annotation定义ORM关系.
 * 使用Hibernate annotation定义JPA 1.0未覆盖的部分.
 * 关于属性的annotation 放到get方法的上面。
 * @author 马道永
 */
@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "BASE_ZZJG")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Organi extends AuditableEntity {

	private static final long serialVersionUID = 5847918086908696708L;
	
	//名称
	private String rganiName;
	
	//编码
	private String organiCode;
	
	//是否在用
	private String organiFlag;
	
	//描述
	private String organiDes;
	
	//级别描述
	private String levelMess;
	
	//副节点ID
	private Long parentId;

	@Column(columnDefinition=DEF_STR128)
	public String getRganiName() {
		return rganiName;
	}

	public void setRganiName(String rganiName) {
		this.rganiName = rganiName;
	}

	@Column(columnDefinition=DEF_STR16)
	public String getOrganiCode() {
		return organiCode;
	}

	public void setOrganiCode(String organiCode) {
		this.organiCode = organiCode;
	}

	@Column(columnDefinition=DEF_STR16)
	public String getOrganiFlag() {
		return organiFlag;
	}

	public void setOrganiFlag(String organiFlag) {
		this.organiFlag = organiFlag;
	}

	@Column(columnDefinition=DEF_STR512)
	public String getOrganiDes() {
		return organiDes;
	}

	public void setOrganiDes(String organiDes) {
		this.organiDes = organiDes;
	}

	@Column(columnDefinition= DEF_ID )
	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
	@Column(columnDefinition=DEF_STR128)
	public String getLevelMess() {
		return levelMess;
	}

	public void setLevelMess(String levelMess) {
		this.levelMess = levelMess;
	}
	
}

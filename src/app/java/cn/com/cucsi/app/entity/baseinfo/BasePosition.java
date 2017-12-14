package cn.com.cucsi.app.entity.baseinfo;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import cn.com.cucsi.core.orm.hibernate.AuditableEntity;

/**
 * BasePosition entity. 
 * @author MyEclipse Persistence Tools
 */
@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "BASE_POSITION")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BasePosition extends AuditableEntity{

	// Fields
	private static final long serialVersionUID = -2276242824197821671L;
	

	
	private String positionName;//职务名称
	/**职务名称*/
	@Column(columnDefinition=DEF_STR100)
	public String getPositionName() {
		return this.positionName;
	}
	/**职务名称*/
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	private String positionCode;//职务权限级别
	/**职务权限级别*/
	@Column(columnDefinition=DEF_STR16)
	public String getPositionCode() {
		return this.positionCode;
	}
	/**职务权限级别*/
	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}

	private String positionLevel;//职级标准
	/**职级标准*/
	@Column(columnDefinition=DEF_STR16)
	public String getPositionLevel() {
		return this.positionLevel;
	}
	/**职级标准*/
	public void setPositionLevel(String positionLevel) {
		this.positionLevel = positionLevel;
	}
	private String positionLevelCode;//职级英文编码
	/**职级英文编码*/
	@Column(columnDefinition=DEF_STR100)
	public String getPositionLevelCode() {
		return this.positionLevelCode;
	}
	/**职级英文编码*/
	public void setPositionLevelCode(String positionLevelCode) {
		this.positionLevelCode = positionLevelCode;
	}
	private String positionLevelValue;//职级VALUE
	/**职级VALUE*/
	@Column(columnDefinition=DEF_STR100)
	public String getPositionLevelValue() {
		return this.positionLevelValue;
	}
	/**职级VALUE*/
	public void setPositionLevelValue(String positionLevelValue) {
		this.positionLevelValue = positionLevelValue;
	}
}

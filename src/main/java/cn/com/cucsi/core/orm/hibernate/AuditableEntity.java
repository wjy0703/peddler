package cn.com.cucsi.core.orm.hibernate;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * 含审计信息的Entity基类.
 * 
 * @author jiangxd
 */
@MappedSuperclass
public class AuditableEntity extends IdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4325869609939728747L;
	protected Timestamp createTime;
	protected String createBy;
	protected Timestamp lastModifyTime;
	protected String lastModifyBy;

	/**
	 * 创建时间.
	 */
	//本属性只在save时有效,update时无效.
	@Column(updatable = false)
	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	/**
	 * 创建的操作员的登录名.
	 */
	@Column(columnDefinition=DEF_STR256, updatable = false)
	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	/**
	 * 最后修改时间.
	 */
	//本属性只在update时有效,save时无效.
	@Column(insertable = false)
	public Timestamp getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(Timestamp lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	/**
	 * 最后修改的操作员的登录名.
	 */
	@Column(columnDefinition=DEF_STR256, insertable = false)
	public String getLastModifyBy() {
		return lastModifyBy;
	}

	public void setLastModifyBy(String lastModifyBy) {
		this.lastModifyBy = lastModifyBy;
	}
}

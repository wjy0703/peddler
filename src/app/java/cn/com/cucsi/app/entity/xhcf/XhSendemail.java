package cn.com.cucsi.app.entity.xhcf;

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
 * XhSendemail entity. 
 * @author MyEclipse Persistence Tools
 */
@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "XH_SENDEMAIL")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XhSendemail extends AuditableEntity{

	// Fields
	private static final long serialVersionUID = -2276242824197821671L;
	private Long tableId;//任务表ID
	/**任务表ID*/
	@Column(columnDefinition=DEF_NUM18)
	public Long getTableId() {
		return this.tableId;
	}
	/**任务表ID*/
	public void setTableId(Long tableId) {
		this.tableId = tableId;
	}
	private String tableName;//任务表名称
	/**任务表名称*/
	@Column(columnDefinition=DEF_STR200)
	public String getTableName() {
		return this.tableName;
	}
	/**任务表名称*/
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	private String state;//状态0待发送1已发送
	/**状态0待发送1已发送*/
	@Column(columnDefinition=DEF_STR2)
	public String getState() {
		return this.state;
	}
	/**状态0待发送1已发送*/
	public void setState(String state) {
		this.state = state;
	}
}

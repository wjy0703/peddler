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
 * XhMaintainLog entity. 
 * @author MyEclipse Persistence Tools
 */
@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "XH_MAINTAIN_LOG")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XhMaintainLog extends AuditableEntity{

	// Fields
	private static final long serialVersionUID = -2276242824197821671L;
	private String title;//标题
	/**标题*/
	@Column(columnDefinition=DEF_STR128)
	public String getTitle() {
		return this.title;
	}
	/**标题*/
	public void setTitle(String title) {
		this.title = title;
	}
	private String detContent;//详细内容
	/**详细内容*/
	@Column(columnDefinition=DEF_STR1024)
	public String getDetContent() {
		return this.detContent;
	}
	/**详细内容*/
	public void setDetContent(String detContent) {
		this.detContent = detContent;
	}
}

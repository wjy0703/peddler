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
 * XhBlackList entity. 
 * @author MyEclipse Persistence Tools
 */
@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "XH_BLACK_LIST")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XhBlackList extends AuditableEntity{

	// Fields
	private static final long serialVersionUID = -2276242824197821671L;
	private String name;//客户姓名
	/**客户姓名*/
	@Column(columnDefinition=DEF_STR100)
	public String getName() {
		return this.name;
	}
	/**客户姓名*/
	public void setName(String name) {
		this.name = name;
	}
	private String identifId;//客户身份证号
	/**客户身份证号*/
	@Column(columnDefinition=DEF_STR100)
	public String getIdentifId() {
		return this.identifId;
	}
	/**客户身份证号*/
	public void setIdentifId(String identifId) {
		this.identifId = identifId;
	}
}

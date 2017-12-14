package cn.com.cucsi.app.entity.baseinfo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import cn.com.cucsi.core.orm.hibernate.AuditableEntity;

/**
 * 投资产品表.
 * 
 * 使用JPA annotation定义ORM关系.
 * 使用Hibernate annotation定义JPA 1.0未覆盖的部分.
 * 关于属性的annotation 放到get方法的上面。
 * @author 马道永
 */
@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "XH_TZCP")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Tzcp extends AuditableEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1269839504347877022L;
	
	//投资产品名称
	private String tzcpMc;
	
	//投资产品状态
	private String tzcpZt;
	
	//投资产品利率
	private Long tzcpLl;
	
	//投资产品描述
	private String tzcpMs;
	
	//投资产品分类
	private String tzcpFl;
	
	//投资产品类型
	private String tzcpLx;
	
	//是否有管理费
	private String isAdmin;
	
	//产品周期
	private String tzcpZq;
	
	//投资产品编号
	private String tzcpBh;

	@Column(columnDefinition=DEF_STR512)
	public String getTzcpMc() {
		return tzcpMc;
	}

	public void setTzcpMc(String tzcpMc) {
		this.tzcpMc = tzcpMc;
	}

	@Column(columnDefinition=DEF_STR16)
	public String getTzcpZt() {
		return tzcpZt;
	}

	public void setTzcpZt(String tzcpZt) {
		this.tzcpZt = tzcpZt;
	}

	@Column(columnDefinition=DEF_ID)
	public Long getTzcpLl() {
		return tzcpLl;
	}

	public void setTzcpLl(Long tzcpLl) {
		this.tzcpLl = tzcpLl;
	}

	@Column(columnDefinition=DEF_STR512)
	public String getTzcpMs() {
		return tzcpMs;
	}

	public void setTzcpMs(String tzcpMs) {
		this.tzcpMs = tzcpMs;
	}

	@Column(columnDefinition=DEF_STR16)
	public String getTzcpLx() {
		return tzcpLx;
	}

	public void setTzcpLx(String tzcpLx) {
		this.tzcpLx = tzcpLx;
	}

	@Column(columnDefinition=DEF_STR16)
	public String getTzcpFl() {
		return tzcpFl;
	}

	public void setTzcpFl(String tzcpFl) {
		this.tzcpFl = tzcpFl;
	}

	@Column(columnDefinition=DEF_STR16)
	public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Column(columnDefinition=DEF_STR16)
	public String getTzcpZq() {
		return tzcpZq;
	}

	public void setTzcpZq(String tzcpZq) {
		this.tzcpZq = tzcpZq;
	}

	@Column(columnDefinition=DEF_STR16)
	public String getTzcpBh() {
		return tzcpBh;
	}

	public void setTzcpBh(String tzcpBh) {
		this.tzcpBh = tzcpBh;
	}

}

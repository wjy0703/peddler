package cn.com.peddler.app.entity.login;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import cn.com.peddler.core.orm.hibernate.AuditableEntity;

/**
 * Reatpackage entity. 
 * @author MyEclipse Persistence Tools
 */
@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "reatpackage")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Reatpackage extends AuditableEntity{

	// Fields
	private static final long serialVersionUID = -2276242824197821671L;
	private Long id;//id
	/**id*/
	@Column(columnDefinition=DEF_NUM10)
	public Long getId() {
		return this.id;
	}
	/**id*/
	public void setId(Long id) {
		this.id = id;
	}
	private String reatname;//套餐名称
	/**套餐名称*/
	@Column(columnDefinition=DEF_STR50)
	public String getReatname() {
		return this.reatname;
	}
	/**套餐名称*/
	public void setReatname(String reatname) {
		this.reatname = reatname;
	}
	private String cycke;//周期（月）
	/**周期（月）*/
	@Column(columnDefinition=DEF_STR3)
	public String getCycke() {
		return this.cycke;
	}
	/**周期（月）*/
	public void setCycke(String cycke) {
		this.cycke = cycke;
	}
	private String price;//套餐价格
	/**套餐价格*/
	@Column(columnDefinition=DEF_STR8)
	public String getPrice() {
		return this.price;
	}
	/**套餐价格*/
	public void setPrice(String price) {
		this.price = price;
	}
	private Timestamp createtime;//创建时间
	/**创建时间*/
	@Column(insertable = false)
	public Timestamp getCreatetime() {
		return this.createtime;
	}
	/**创建时间*/
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	private Timestamp modifytime;//修改时间
	/**修改时间*/
	@Column(insertable = false)
	public Timestamp getModifytime() {
		return this.modifytime;
	}
	/**修改时间*/
	public void setModifytime(Timestamp modifytime) {
		this.modifytime = modifytime;
	}
	private String createuser;//创建人
	/**创建人*/
	@Column(columnDefinition=DEF_STR40)
	public String getCreateuser() {
		return this.createuser;
	}
	/**创建人*/
	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}
	private String modifyuser;//修改人
	/**修改人*/
	@Column(columnDefinition=DEF_STR40)
	public String getModifyuser() {
		return this.modifyuser;
	}
	/**修改人*/
	public void setModifyuser(String modifyuser) {
		this.modifyuser = modifyuser;
	}
	private String vtypes;//属性
	/**属性*/
	@Column(columnDefinition=DEF_STR2)
	public String getVtypes() {
		return this.vtypes;
	}
	/**属性*/
	public void setVtypes(String vtypes) {
		this.vtypes = vtypes;
	}
}

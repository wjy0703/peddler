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
 * Businessinfo entity. 
 * @author MyEclipse Persistence Tools
 */
@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "businessinfo")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Businessinfo extends AuditableEntity{

	// Fields
	private static final long serialVersionUID = -2276242824197821671L;
	private String busiaccount;//公司账户
	/**公司账户*/
	@Column(columnDefinition=DEF_STR40)
	public String getBusiaccount() {
		return this.busiaccount;
	}
	/**公司账户*/
	public void setBusiaccount(String busiaccount) {
		this.busiaccount = busiaccount;
	}
	private String businame;//公司名
	/**公司名*/
	@Column(columnDefinition=DEF_STR40)
	public String getBusiname() {
		return this.businame;
	}
	/**公司名*/
	public void setBusiname(String businame) {
		this.businame = businame;
	}
	private String corporation;//法人
	/**法人*/
	@Column(columnDefinition=DEF_STR10)
	public String getCorporation() {
		return this.corporation;
	}
	/**法人*/
	public void setCorporation(String corporation) {
		this.corporation = corporation;
	}
	private String card;//证件号码
	/**证件号码*/
	@Column(columnDefinition=DEF_STR30)
	public String getCard() {
		return this.card;
	}
	/**证件号码*/
	public void setCard(String card) {
		this.card = card;
	}
	private String phone;//联系方式
	/**联系方式*/
	@Column(columnDefinition=DEF_STR20)
	public String getPhone() {
		return this.phone;
	}
	/**联系方式*/
	public void setPhone(String phone) {
		this.phone = phone;
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
	private String vtypes;//属性（在用、欠费、停用）
	/**属性（在用、欠费、停用）*/
	@Column(columnDefinition=DEF_STR2)
	public String getVtypes() {
		return this.vtypes;
	}
	/**属性（在用、欠费、停用）*/
	public void setVtypes(String vtypes) {
		this.vtypes = vtypes;
	}
	private String tctypes;//套餐类型
	/**套餐类型*/
	@Column(columnDefinition=DEF_STR40)
	public String getTctypes() {
		return this.tctypes;
	}
	/**套餐类型*/
	public void setTctypes(String tctypes) {
		this.tctypes = tctypes;
	}
	private Timestamp starttime;//生效时间
	/**生效时间*/
	@Column(insertable = false)
	public Timestamp getStarttime() {
		return this.starttime;
	}
	/**生效时间*/
	public void setStarttime(Timestamp starttime) {
		this.starttime = starttime;
	}
	private Timestamp overtime;//到期时间
	/**到期时间*/
	@Column(insertable = false)
	public Timestamp getOvertime() {
		return this.overtime;
	}
	/**到期时间*/
	public void setOvertime(Timestamp overtime) {
		this.overtime = overtime;
	}
}

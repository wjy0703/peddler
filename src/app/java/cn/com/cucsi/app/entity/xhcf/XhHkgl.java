package cn.com.cucsi.app.entity.xhcf;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import cn.com.cucsi.core.orm.hibernate.AuditableEntity;

@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "XH_HKGL")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XhHkgl extends AuditableEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4514370992009477419L;
	private String zhbm;//账号编码
	private String khm;//开户名
	private String hkyh;//回款银行
	private String khh;//开户行
	private String zh;//账号
	private String byykkrq;//本月应回款日期
	private String byyhkje;//本月应回款金额
	private String sjhkrq;//实际回款日期
	private String sjhkje;//实际回款金额
	private String qkje;//欠款金额
	private String zwclje;//帐外处理金额
	
	private String tdjl;//团队经理
	private String khjl;//客户经理
	private String jkr;//借款人

	@Column(columnDefinition=DEF_STR20)
	public String getZhbm() {
		return zhbm;
	}
	
	public void setZhbm(String zhbm) {
		this.zhbm = zhbm;
	}
	@Column(columnDefinition=DEF_STR16)
	public String getKhm() {
		return khm;
	}
	public void setKhm(String khm) {
		this.khm = khm;
	}
	@Column(columnDefinition=DEF_STR40)
	public String getHkyh() {
		return hkyh;
	}
	public void setHkyh(String hkyh) {
		this.hkyh = hkyh;
	}
	@Column(columnDefinition=DEF_STR40)
	public String getKhh() {
		return khh;
	}
	public void setKhh(String khh) {
		this.khh = khh;
	}
	@Column(columnDefinition=DEF_STR40)
	public String getZh() {
		return zh;
	}
	public void setZh(String zh) {
		this.zh = zh;
	}
	@Column(columnDefinition=DEF_STR20)
	public String getByykkrq() {
		return byykkrq;
	}
	public void setByykkrq(String byykkrq) {
		this.byykkrq = byykkrq;
	}
	@Column(columnDefinition=DEF_STR10)
	public String getByyhkje() {
		return byyhkje;
	}
	public void setByyhkje(String byyhkje) {
		this.byyhkje = byyhkje;
	}
	@Column(columnDefinition=DEF_STR20)
	public String getSjhkrq() {
		return sjhkrq;
	}
	public void setSjhkrq(String sjhkrq) {
		this.sjhkrq = sjhkrq;
	}
	@Column(columnDefinition=DEF_STR10)
	public String getSjhkje() {
		return sjhkje;
	}
	public void setSjhkje(String sjhkje) {
		this.sjhkje = sjhkje;
	}
	@Column(columnDefinition=DEF_STR10)
	public String getQkje() {
		return qkje;
	}
	public void setQkje(String qkje) {
		this.qkje = qkje;
	}
	@Column(columnDefinition=DEF_STR10)
	public String getZwclje() {
		return zwclje;
	}
	public void setZwclje(String zwclje) {
		this.zwclje = zwclje;
	}
	@Column(columnDefinition=DEF_STR20)
	public String getTdjl() {
		return tdjl;
	}
	@Column(columnDefinition=DEF_STR20)
	public void setTdjl(String tdjl) {
		this.tdjl = tdjl;
	}
	@Column(columnDefinition=DEF_STR20)
	public String getKhjl() {
		return khjl;
	}

	public void setKhjl(String khjl) {
		this.khjl = khjl;
	}

	@Column(columnDefinition=DEF_STR20)
	public String getJkr() {
		return jkr;
	}

	public void setJkr(String jkr) {
		this.jkr = jkr;
	}
}

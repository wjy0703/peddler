package cn.com.cucsi.app.entity.xhcf;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import cn.com.cucsi.core.orm.hibernate.AuditableEntity;

@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "XH_FKGL")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XhFkgl extends AuditableEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2031409484063184660L;
	private String cjrbh;//出借人编号
	private String khm;//开户名
	private String khyh;//开户银行
	private String khh;//开户行
	private String zh;//账号
	private String yfkje;//因付款金额
	private String sjyfje;//实际应付金额
	private String sjfkrq;//实际付款日期
	private String fklx;//付款类型
	
	@Column(columnDefinition=DEF_STR20)
	public String getCjrbh() {
		return cjrbh;
	}
	public void setCjrbh(String cjrbh) {
		this.cjrbh = cjrbh;
	}
	@Column(columnDefinition=DEF_STR10)
	public String getKhm() {
		return khm;
	}
	public void setKhm(String khm) {
		this.khm = khm;
	}
	@Column(columnDefinition=DEF_STR20)
	public String getKhyh() {
		return khyh;
	}
	public void setKhyh(String khyh) {
		this.khyh = khyh;
	}
	@Column(columnDefinition=DEF_STR50)
	public String getKhh() {
		return khh;
	}
	public void setKhh(String khh) {
		this.khh = khh;
	}
	@Column(columnDefinition=DEF_STR32)
	public String getZh() {
		return zh;
	}
	public void setZh(String zh) {
		this.zh = zh;
	}
	@Column(columnDefinition=DEF_STR20)
	public String getYfkje() {
		return yfkje;
	}
	public void setYfkje(String yfkje) {
		this.yfkje = yfkje;
	}
	@Column(columnDefinition=DEF_STR20)
	public String getSjyfje() {
		return sjyfje;
	}
	public void setSjyfje(String sjyfje) {
		this.sjyfje = sjyfje;
	}
	@Column(columnDefinition=DEF_STR20)
	public String getSjfkrq() {
		return sjfkrq;
	}
	public void setSjfkrq(String sjfkrq) {
		this.sjfkrq = sjfkrq;
	}
	@Column(columnDefinition=DEF_STR5)
	public String getFklx() {
		return fklx;
	}
	public void setFklx(String fklx) {
		this.fklx = fklx;
	}
}

package cn.com.cucsi.app.entity.xhcf;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import cn.com.cucsi.app.entity.baseinfo.Xydkzx;
import cn.com.cucsi.core.orm.hibernate.AuditableEntity;

@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "XH_ZXGTJL")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ZxGtjl extends AuditableEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -256189186586454012L;
	private String gtjl;//沟通记录
	private String khpj;//客户评价
	private String zdkh;//重点客户
	private Xydkzx xydkzx;//咨询ID
	private String bcgtrq;//本次沟通日期
	
	@Column(columnDefinition=DEF_STR512)
	public String getGtjl() {
		return gtjl;
	}
	public void setGtjl(String gtjl) {
		this.gtjl = gtjl;
	}
	@Column(columnDefinition=DEF_STR8)
	public String getKhpj() {
		return khpj;
	}
	public void setKhpj(String khpj) {
		this.khpj = khpj;
	}
	@Column(columnDefinition=DEF_STR8)
	public String getZdkh() {
		return zdkh;
	}
	public void setZdkh(String zdkh) {
		this.zdkh = zdkh;
	}
	@OneToOne(cascade=CascadeType.REFRESH,fetch = FetchType.LAZY)
	@JoinColumn(name="xydkzx_id", unique= false, nullable=true, insertable=true, updatable=true)
	public Xydkzx getXydkzx() {
		return xydkzx;
	}
	public void setXydkzx(Xydkzx xydkzx) {
		this.xydkzx = xydkzx;
	}

	@Column(columnDefinition=DEF_STR32)
	public String getBcgtrq() {
		return bcgtrq;
	}
	public void setBcgtrq(String bcgtrq) {
		this.bcgtrq = bcgtrq;
	}
	
	
}

package cn.com.cucsi.app.entity.xhcf;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import cn.com.cucsi.core.orm.hibernate.AuditableEntity;

/**
 * XhCjrgtjl entity. 
 * @author MyEclipse Persistence Tools
 */
@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "XH_CJRGTJL")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XhCjrgtjl extends AuditableEntity{

	// Fields
	private static final long serialVersionUID = -2276242824197821671L;
	private String yxczrq;//意向出资日期
	/**意向出资日期*/
	@Column(columnDefinition=DEF_STR40)
	public String getYxczrq() {
		return this.yxczrq;
	}
	/**意向出资日期*/
	public void setYxczrq(String yxczrq) {
		this.yxczrq = yxczrq;
	}
	private String xclxrq;//下次联系日期
	/**下次联系日期*/
	@Column(columnDefinition=DEF_STR40)
	public String getXclxrq() {
		return this.xclxrq;
	}
	/**下次联系日期*/
	public void setXclxrq(String xclxrq) {
		this.xclxrq = xclxrq;
	}
	private String bcgtrq;//本次沟通日期
	/**本次沟通日期*/
	@Column(columnDefinition=DEF_STR40)
	public String getBcgtrq() {
		return this.bcgtrq;
	}
	/**本次沟通日期*/
	public void setBcgtrq(String bcgtrq) {
		this.bcgtrq = bcgtrq;
	}
	private String gtkssj;//沟通开始时间
	/**沟通开始时间*/
	@Column(columnDefinition=DEF_STR40)
	public String getGtkssj() {
		return this.gtkssj;
	}
	/**沟通开始时间*/
	public void setGtkssj(String gtkssj) {
		this.gtkssj = gtkssj;
	}
	private String gtjssj;//沟通结束时间
	/**沟通结束时间*/
	@Column(columnDefinition=DEF_STR40)
	public String getGtjssj() {
		return this.gtjssj;
	}
	/**沟通结束时间*/
	public void setGtjssj(String gtjssj) {
		this.gtjssj = gtjssj;
	}
	private String bclxr;//本次联系人
	/**本次联系人*/
	@Column(columnDefinition=DEF_STR20)
	public String getBclxr() {
		return this.bclxr;
	}
	/**本次联系人*/
	public void setBclxr(String bclxr) {
		this.bclxr = bclxr;
	}
	private String gtnrms;//沟通内容描述
	/**沟通内容描述*/
	@Column(columnDefinition=DEF_STR1000)
	public String getGtnrms() {
		return this.gtnrms;
	}
	/**沟通内容描述*/
	public void setGtnrms(String gtnrms) {
		this.gtnrms = gtnrms;
	}
	private String bcgtfs;//本次沟通方式
	/**本次沟通方式*/
	@Column(columnDefinition=DEF_STR20)
	public String getBcgtfs() {
		return this.bcgtfs;
	}
	/**本次沟通方式*/
	public void setBcgtfs(String bcgtfs) {
		this.bcgtfs = bcgtfs;
	}
	private String yxcp;//意向产品
	/**意向产品*/
	@Column(columnDefinition=DEF_STR20)
	public String getYxcp() {
		return this.yxcp;
	}
	/**意向产品*/
	public void setYxcp(String yxcp) {
		this.yxcp = yxcp;
	}
	private String xclxfs;//下次联系方式
	/**下次联系方式*/
	@Column(columnDefinition=DEF_STR20)
	public String getXclxfs() {
		return this.xclxfs;
	}
	/**下次联系方式*/
	public void setXclxfs(String xclxfs) {
		this.xclxfs = xclxfs;
	}
	private String khyx;//客户意向
	/**客户意向*/
	@Column(columnDefinition=DEF_STR20)
	public String getKhyx() {
		return this.khyx;
	}
	/**客户意向*/
	public void setKhyx(String khyx) {
		this.khyx = khyx;
	}
	private String cjrState;//客户状态。0.投资咨询，1.出借人
	/**客户状态。0.投资咨询，1.出借人*/
	@Column(columnDefinition=DEF_STR2)
	public String getCjrState() {
		return this.cjrState;
	}
	/**客户状态。0.投资咨询，1.出借人*/
	public void setCjrState(String cjrState) {
		this.cjrState = cjrState;
	}
	private String yxczje;//意向出资金额
	/**意向出资金额*/
	@Column(columnDefinition=DEF_STR40)
	public String getYxczje() {
		return this.yxczje;
	}
	/**意向出资金额*/
	public void setYxczje(String yxczje) {
		this.yxczje = yxczje;
	}
	private XhcfCjrxx cjrxx;
	//一对一定义
	@OneToOne(cascade=CascadeType.REFRESH,fetch = FetchType.LAZY)
	@JoinColumn(name="cjrxx_id", unique= false, nullable=true, insertable=true, updatable=true)
	public XhcfCjrxx getCjrxx() {
		return cjrxx;
	}

	public void setCjrxx(XhcfCjrxx cjrxx) {
		this.cjrxx = cjrxx;
	}
}

package cn.com.cucsi.app2.entity.xhcf;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import cn.com.cucsi.app.entity.xhcf.XhJksq;
import cn.com.cucsi.core.orm.hibernate.AuditableEntity;

/**
 * XhJksqcompanyUprelated entity. 
 * @author MyEclipse Persistence Tools
 */
@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "XH_JKSQCOMPANY_UPRELATED")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XhJksqcompanyUprelated extends AuditableEntity{

	// Fields
	private static final long serialVersionUID = -2276242824197821671L;
	private String companyName;//公司名称
	/**公司名称*/
	@Column(columnDefinition=DEF_STR50)
	public String getCompanyName() {
		return this.companyName;
	}
	/**公司名称*/
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	private String contactType;//合同类型
	/**合同类型*/
	@Column(columnDefinition=DEF_STR20)
	public String getContactType() {
		return this.contactType;
	}
	/**合同类型*/
	public void setContactType(String contactType) {
		this.contactType = contactType;
	}
	private Double contactMoney;//合同金额
	/**合同金额*/
	@Column(columnDefinition=DEF_NUM18_2)
	public Double getContactMoney() {
		return this.contactMoney;
	}
	/**合同金额*/
	public void setContactMoney(Double contactMoney) {
		this.contactMoney = contactMoney;
	}
	private Long contactDue;//合同期限
	/**合同期限*/
	@Column(columnDefinition=DEF_NUM4)
	public Long getContactDue() {
		return this.contactDue;
	}
	/**合同期限*/
	public void setContactDue(Long contactDue) {
		this.contactDue = contactDue;
	}
	private String contactHandleType;//结算方式
	/**结算方式*/
	@Column(columnDefinition=DEF_STR20)
	public String getContactHandleType() {
		return this.contactHandleType;
	}
	/**结算方式*/
	public void setContactHandleType(String contactHandleType) {
		this.contactHandleType = contactHandleType;
	}
	private String phoneBackinfo;//电话核实情况
	/**电话核实情况*/
	@Column(columnDefinition=DEF_STR100)
	public String getPhoneBackinfo() {
		return this.phoneBackinfo;
	}
	/**电话核实情况*/
	public void setPhoneBackinfo(String phoneBackinfo) {
		this.phoneBackinfo = phoneBackinfo;
	}
	private String phoneOther;//电话及来源
	/**电话及来源*/
	@Column(columnDefinition=DEF_STR50)
	public String getPhoneOther() {
		return this.phoneOther;
	}
	/**电话及来源*/
	public void setPhoneOther(String phoneOther) {
		this.phoneOther = phoneOther;
	}
	
    private XhJksqCompany xhJksqCompany;             //借款申请信息
    
    /**借款申请信息*/
    @ManyToOne(cascade=CascadeType.REFRESH,fetch = FetchType.LAZY)
    @JoinColumn(name="XHJKSQCOMPANY_ID", unique= false, nullable=true, insertable=true, updatable=true)
    public XhJksqCompany getXhJksqCompany() {
        return xhJksqCompany;
    }
    
    public void setXhJksqCompany(XhJksqCompany xhJksq) {
        this.xhJksqCompany = xhJksq;
    }
}

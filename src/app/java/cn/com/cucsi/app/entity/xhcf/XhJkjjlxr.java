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

import cn.com.cucsi.core.orm.hibernate.AuditableEntity;

@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "XH_JKJJLXR")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XhJkjjlxr extends AuditableEntity {

	// Fields

//	private long jjlxrbh;					//紧急联系人编号
//	private XhJkrxx xhcfDkrxx;			//贷款人信息编号
	private String name;					//紧急联系人姓名
	private String ybrgx;					//与本人关系
	private String jjlxrgzdw;				//紧急联系人工作单位  --->工作单位
	private String jjlxrdwdzhjtzz;		//单位地址或家庭住址         --->新住址
	private String jjlxrlxdh;				//紧急联系人联系电话-->新手机电话
	private String homePhone;              //住宅电话
	private String officePhone;            //单位电话
	private String lxrlx;					//联系人类型
	private String optionUser;			//操作人
	private String optionTime;			//操作时间 
   
	private XhJksq xhJksq;				//借款申请信息
	
	
	private XhJksqUPHistory xhjksqjphistory; //变更申请历史
	// Constructors

	/** default constructor */
	public XhJkjjlxr() {
	}


	/** full constructor */
	public XhJkjjlxr(//XhJkrxx xhcfDkrxx,
			String jjlxrgzdw, String jjlxrdwdzhjtzz, String jjlxrlxdh,
			String lxrlx, String ybrgx, String optionUser, String optionTime, 
			String name) {
//		this.xhcfDkrxx = xhcfDkrxx;
		this.jjlxrgzdw = jjlxrgzdw;
		this.jjlxrdwdzhjtzz = jjlxrdwdzhjtzz;
		this.jjlxrlxdh = jjlxrlxdh;
		this.lxrlx = lxrlx;
		this.ybrgx = ybrgx;
		this.optionUser = optionUser;
		this.optionTime = optionTime;
		this.name = name;
	}

	// Property accessors

//	/**
//	 * 紧急联系人编号
//	 */
//	public long getJjlxrbh() {
//		return this.jjlxrbh;
//	}
//
//	/**
//	 * 紧急联系人编号
//	 */
//	public void setJjlxrbh(long jjlxrbh) {
//		this.jjlxrbh = jjlxrbh;
//	}

//	/**
//	 * 借款人信息
//	 */
//	@OneToOne(cascade=CascadeType.REFRESH)
//	@JoinColumn(name="JKRXX_ID", unique= false, nullable=true, insertable=true, updatable=true)
//	public XhJkrxx getXhcfDkrxx() {
//		return this.xhcfDkrxx;
//	}
//
//	/**
//	 * 借款人信息
//	 */
//	public void setXhcfDkrxx(XhJkrxx xhcfDkrxx) {
//		this.xhcfDkrxx = xhcfDkrxx;
//	}

	/**
	 * 紧急联系工作单位
	 */
	@Column(columnDefinition=DEF_STR256)
	public String getJjlxrgzdw() {
		return this.jjlxrgzdw;
	}

	/**
	 * 紧急联系工作单位
	 */
	public void setJjlxrgzdw(String jjlxrgzdw) {
		this.jjlxrgzdw = jjlxrgzdw;
	}

	/**
	 * 单位地址或家庭住址
	 */
	@Column(columnDefinition=DEF_STR256)
	public String getJjlxrdwdzhjtzz() {
		return this.jjlxrdwdzhjtzz;
	}

	/**
	 * 单位地址或家庭住址
	 */
	public void setJjlxrdwdzhjtzz(String jjlxrdwdzhjtzz) {
		this.jjlxrdwdzhjtzz = jjlxrdwdzhjtzz;
	}

	/**
	 * 紧急联系人联系电话
	 */
	@Column(columnDefinition=DEF_STR32)
	public String getJjlxrlxdh() {
		return this.jjlxrlxdh;
	}

	/**
	 * 紧急联系人联系电话
	 */
	public void setJjlxrlxdh(String jjlxrlxdh) {
		this.jjlxrlxdh = jjlxrlxdh;
	}

	/**
	 * 联系人类型
	 */
	@Column(columnDefinition=DEF_STR32)
	public String getLxrlx() {
		return this.lxrlx;
	}

	/**
	 * 联系人类型
	 */
	public void setLxrlx(String lxrlx) {
		this.lxrlx = lxrlx;
	}

	/**
	 * 与本人关系
	 */
	@Column(columnDefinition=DEF_STR32)
	public String getYbrgx() {
		return this.ybrgx;
	}

	/**
	 * 与本人关系
	 */
	public void setYbrgx(String ybrgx) {
		this.ybrgx = ybrgx;
	}

	/**
	 * 操作人
	 */
	@Column(columnDefinition=DEF_STR32)
	public String getOptionUser() {
		return this.optionUser;
	}

	/**
	 * 操作人
	 */
	public void setOptionUser(String optionUser) {
		this.optionUser = optionUser;
	}

	/**
	 * 操作时间 
	 */
	@Column(columnDefinition=DEF_STR32)
	public String getOptionTime() {
		return this.optionTime;
	}

	/**
	 * 操作时间 
	 */
	public void setOptionTime(String optionTime) {
		this.optionTime = optionTime;
	}

	/**
	 * 紧急联系人姓名 
	 */
	@Column(columnDefinition=DEF_STR32)
	public String getName() {
		return name;
	}

	/**
	 * 紧急联系人姓名 
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**借款申请信息*/
	@OneToOne(cascade=CascadeType.REFRESH,fetch = FetchType.LAZY)
	@JoinColumn(name="XHJKSQ_ID", unique= false, nullable=true, insertable=true, updatable=true)
	public XhJksq getXhJksq() {
		return xhJksq;
	}

	/**借款申请信息*/
	public void setXhJksq(XhJksq xhJksq) {
		this.xhJksq = xhJksq;
	}

	
	private XhJksqTogether xhJksqTogether;   //共同还款人信息
	/**共同还款人信息*/
	@OneToOne(cascade=CascadeType.REFRESH,fetch = FetchType.LAZY)
	@JoinColumn(name="XHJKSQ_TOGETHER_ID", unique= false, nullable=true, insertable=true, updatable=true)
	public XhJksqTogether getXhJksqTogether() {
		return xhJksqTogether;
	}

	/**共同还款人信息*/
	public void setXhJksqTogether(XhJksqTogether xhJksqTogether) {
		this.xhJksqTogether = xhJksqTogether;
	}

	/** 借款申请变更信息*/
	@OneToOne(cascade=CascadeType.REFRESH,fetch = FetchType.LAZY)
	@JoinColumn(name="jksq_history_id", unique= false, nullable=true, insertable=true, updatable=true)
	public XhJksqUPHistory getXhjksqjphistory() {
		return xhjksqjphistory;
	}

	/** 借款申请变更信息*/
	public void setXhjksqjphistory(XhJksqUPHistory xhjksqjphistory) {
		this.xhjksqjphistory = xhjksqjphistory;
	}


	@Column(columnDefinition=DEF_STR32)
    public String getHomePhone() {
        return homePhone;
    }


    
    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }


    @Column(columnDefinition=DEF_STR32)
    public String getOfficePhone() {
        return officePhone;
    }


    
    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    
	
	

}
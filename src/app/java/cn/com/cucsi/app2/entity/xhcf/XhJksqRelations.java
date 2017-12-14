package cn.com.cucsi.app2.entity.xhcf;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

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
 * XhJksqRelations entity. 
 * @author MyEclipse Persistence Tools
 */
@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "XH_JKSQ_RELATIONS")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XhJksqRelations extends AuditableEntity{

	// Fields
	private static final long serialVersionUID = -2276242824197821671L;
	private String name;//直系亲属姓名
	/**直系亲属姓名*/
	@Column(columnDefinition=DEF_STR1000)
	public String getName() {
		return this.name;
	}
	/**直系亲属姓名*/
	public void setName(String name) {
		this.name = name;
	}
	private String idCard;//直系亲属身份证号
	/**直系亲属身份证号*/
	@Column(columnDefinition=DEF_STR50)
	public String getIdCard() {
		return this.idCard;
	}
	/**直系亲属身份证号*/
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	private Double age;//直系亲属年龄
	/**直系亲属年龄*/
	@Column(columnDefinition=DEF_NUM18)
	public Double getAge() {
		return this.age;
	}
	/**直系亲属年龄*/
	public void setAge(Double age) {
		this.age = age;
	}
	private String phone;//直系亲属电话
	/**直系亲属电话*/
	@Column(columnDefinition=DEF_STR20)
	public String getPhone() {
		return this.phone;
	}
	/**直系亲属电话*/
	public void setPhone(String phone) {
		this.phone = phone;
	}
	private String office;//单位名称
	/**单位名称*/
	@Column(columnDefinition=DEF_STR200)
	public String getOffice() {
		return this.office;
	}
	/**单位名称*/
	public void setOffice(String office) {
		this.office = office;
	}
	private String officePosition;//职务
	/**职务*/
	@Column(columnDefinition=DEF_STR64)
	public String getOfficePosition() {
		return this.officePosition;
	}
	/**职务*/
	public void setOfficePosition(String officePosition) {
		this.officePosition = officePosition;
	}
	private String officeAdress;//单位地址
	/**单位地址*/
	@Column(columnDefinition=DEF_STR512)
	public String getOfficeAdress() {
		return this.officeAdress;
	}
	/**单位地址*/
	public void setOfficeAdress(String officeAdress) {
		this.officeAdress = officeAdress;
	}
	private String officePhone;//单位电话
	/**单位电话*/
	@Column(columnDefinition=DEF_STR32)
	public String getOfficePhone() {
		return this.officePhone;
	}
	/**单位电话*/
	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}
	private Double monthIncome;//月收入
	/**月收入*/
	@Column(columnDefinition=DEF_NUM18_2)
	public Double getMonthIncome() {
		return this.monthIncome;
	}
	/**月收入*/
	public void setMonthIncome(Double monthIncome) {
		this.monthIncome = monthIncome;
	}
	private String relClass;//直系亲属类别（0：父母，1：配偶，2：子女）
	/**直系亲属类别（0：父母，1：配偶，2：子女）*/
	@Column(columnDefinition=DEF_STR2)
	public String getRelClass() {
		return this.relClass;
	}
	/**直系亲属类别（0：父母，1：配偶，2：子女）*/
	public void setRelClass(String relClass) {
		this.relClass = relClass;
	}
	
	private XhJksq xhJksq;             //借款申请信息
    
    /**借款申请信息*/
    @ManyToOne(cascade=CascadeType.REFRESH,fetch = FetchType.LAZY)
    @JoinColumn(name="JKSQ_ID", unique= false, nullable=true, insertable=true, updatable=true)
    public XhJksq getXhJksq() {
        return xhJksq;
    }
    
    public void setXhJksq(XhJksq xhJksq) {
        this.xhJksq = xhJksq;
    }
	
}

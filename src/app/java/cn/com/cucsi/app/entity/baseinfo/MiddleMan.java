package cn.com.cucsi.app.entity.baseinfo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import cn.com.cucsi.core.orm.hibernate.AuditableEntity;

/**
 * 中间人表.
 * 
 * 使用JPA annotation定义ORM关系.
 * 使用Hibernate annotation定义JPA 1.0未覆盖的部分.
 * 关于属性的annotation 放到get方法的上面。
 * @author 马道永
 */
@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "BASE_MIDDLE_MAN")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MiddleMan extends AuditableEntity {

	private static final long serialVersionUID = 5847918086908696708L;
	
	//中间人
	private String middleManName;
	
	//身份证
	private String idCard;
	
	//地址
	private String addr;
	
	//开户行
	private String credAddr;
	
	//卡号
	private String credId;
	private MiddleMan parent;


	@Column(columnDefinition=DEF_STR128)
	public String getMiddleManName() {
		return middleManName;
	}

	public void setMiddleManName(String middleManName) {
		this.middleManName = middleManName;
	}

	@Column(columnDefinition=DEF_STR128)
	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	@Column(columnDefinition=DEF_STR512)
	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	@Column(columnDefinition=DEF_STR512)
	public String getCredAddr() {
		return credAddr;
	}

	public void setCredAddr(String credAddr) {
		this.credAddr = credAddr;
	}

	@Column(columnDefinition=DEF_STR128)
	public String getCredId() {
		return credId;
	}

	public void setCredId(String credId) {
		this.credId = credId;
	}
	@ManyToOne(cascade=CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	public MiddleMan getParent() {
		return parent;
	}

	public void setParent(MiddleMan parent) {
		this.parent = parent;
	}
}

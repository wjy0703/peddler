package cn.com.cucsi.app.entity.xhcf;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import cn.com.cucsi.core.orm.hibernate.IdEntity;

/**
 * 信审IMG表.
 * 
 * 使用JPA annotation定义ORM关系.
 * 使用Hibernate annotation定义JPA 1.0未覆盖的部分.
 * 关于属性的annotation 放到get方法的上面。
 * @author 马道永
 */
@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "XH_AUDIT_IMG")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AuditUploadImg extends IdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1269839504347877022L;
	
	private String typeName;
	
	private String idNo;
	
	private String imgSrc;
	
	private Long jksqId;
	
	private Long uploadId;

	@Column(columnDefinition=DEF_STR256)
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Column(columnDefinition=DEF_STR32)
	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	@Column(columnDefinition=DEF_STR512)
	public String getImgSrc() {
		return imgSrc;
	}

	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}

	@Column(columnDefinition= DEF_ID )
	public Long getJksqId() {
		return jksqId;
	}

	public void setJksqId(Long jksqId) {
		this.jksqId = jksqId;
	}

	@Column(columnDefinition= DEF_ID )
	public Long getUploadId() {
		return uploadId;
	}

	public void setUploadId(Long uploadId) {
		this.uploadId = uploadId;
	}

}

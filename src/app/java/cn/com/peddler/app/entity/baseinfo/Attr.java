package cn.com.cucsi.app.entity.baseinfo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.com.cucsi.core.orm.hibernate.IdEntity;

@Entity
@Table(name = "BASE_ATTR")
public class Attr extends IdEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3161126111744773020L;
	
	private String keyName;
	private String value;
	private Integer sortNo;
	private String description;
	
	private AttrType attrType;

	@Column(columnDefinition=DEF_STR256, nullable=false)
	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	@Column(columnDefinition=DEF_STR256, nullable=false)
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Column(columnDefinition=DEF_INT4)
	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	@Column(columnDefinition=DEF_STR256)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "type_id")
	public AttrType getAttrType() {
		return attrType;
	}

	public void setAttrType(AttrType attrType) {
		this.attrType = attrType;
	}

}

package cn.com.cucsi.app.entity.baseinfo;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import cn.com.cucsi.core.orm.hibernate.IdEntity;

@Entity
@Table(name = "BASE_ATTR_TYPE")
public class AttrType extends IdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 836008722637537891L;
	
	private String coding;
	private String name;
	private String description;
	private String specialSetting;
	private String tableName;
	private Integer sortNo;
	
	private List<Attr> attrs = new LinkedList<Attr>();

	@Column(columnDefinition=DEF_STR32)
	public String getCoding() {
		return coding;
	}

	public void setCoding(String coding) {
		this.coding = coding;
	}

	@Column(columnDefinition=DEF_STR128)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(columnDefinition=DEF_STR256)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(columnDefinition=DEF_STR256)
	public String getSpecialSetting() {
		return specialSetting;
	}

	public void setSpecialSetting(String specialSetting) {
		this.specialSetting = specialSetting;
	}

	@Column(columnDefinition=DEF_STR128)
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	@Column(columnDefinition=DEF_INT4)
	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	@OneToMany(mappedBy = "attrType", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	
	public List<Attr> getAttrs() {
		return attrs;
	}

	public void setAttrs(List<Attr> attrs) {
		this.attrs = attrs;
	}

}

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

import cn.com.cucsi.core.orm.hibernate.AuditableEntity;

/**
 * XhCjksqSurveyDetails entity. 
 * @author MyEclipse Persistence Tools
 */
@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "XH_CJKSQ_SURVEY_DETAIL")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XhCjksqSurveyDetail extends AuditableEntity{

	// Fields
	private static final long serialVersionUID = -2276242824197821671L;
	private String itemsName;//外访具体条目(对应枚举类中的内容)
	/**外访具体条目(对应枚举类中的内容)*/
	@Column(columnDefinition=DEF_STR20)
	public String getItemsName() {
		return this.itemsName;
	}
	/**外访具体条目(对应枚举类中的内容)*/
	public void setItemsName(String itemsName) {
		this.itemsName = itemsName;
	}
	private Long itemsResult;//外访具体条目结果0:否  1：通过
	/**外访具体条目结果0:否  1：通过*/
	@Column(columnDefinition=DEF_NUM4)
	public Long getItemsResult() {
		return this.itemsResult;
	}
	/**外访具体条目结果0:否  1：通过*/
	public void setItemsResult(Long itemsResult) {
		this.itemsResult = itemsResult;
	}
	
	
    private XhCjksqSurveyItem xhCjksqSurveyItem;             //借款申请信息
    
    /**借款申请信息*/
    @ManyToOne(cascade=CascadeType.REFRESH,fetch = FetchType.LAZY)
    @JoinColumn(name="JKSQ_SURVEY_ID", unique= false, nullable=true, insertable=true, updatable=true)
    public XhCjksqSurveyItem getXhCjksqSurveyItem() {
        return xhCjksqSurveyItem;
    }
    
    public void setXhCjksqSurveyItem(XhCjksqSurveyItem xhCjksqSurveyItem) {
        this.xhCjksqSurveyItem = xhCjksqSurveyItem;
    }
}

package cn.com.cucsi.app2.entity.xhcf;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import oracle.sql.NUMBER;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import cn.com.cucsi.core.orm.hibernate.AuditableEntity;

/**
 * XhCjksqSurveyMain entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
// 表名与类名不相同时重新定义表名.
@Table(name = "XH_CJKSQ_SURVEY_MAIN")
// 默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XhCjksqSurveyMain extends AuditableEntity {

    private Long jksqId;
    
    
    @Column(columnDefinition = DEF_NUM18)
    public Long getJksqId() {
        return jksqId;
    }

    
    public void setJksqId(Long jksqId) {
        this.jksqId = jksqId;
    }

    // Fields
    private static final long serialVersionUID = -2276242824197821671L;
    private String demandWordTemplate;// 外访要求的上传表格（多个用，隔开)

    /** 外访要求的上传表格（多个用，隔开) */
    @Column(columnDefinition = DEF_STR50)
    public String getDemandWordTemplate() {
        return this.demandWordTemplate;
    }

    /** 外访要求的上传表格（多个用，隔开) */
    public void setDemandWordTemplate(String demandWordTemplate) {
        this.demandWordTemplate = demandWordTemplate;
    }

    List<XhCjksqSurveyItem> xhCjksqSurveyItems;

    @OneToMany(targetEntity = XhCjksqSurveyItem.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "JKSQ_SURVEY_MAIN_ID", updatable = false)
    public List<XhCjksqSurveyItem> getXhCjksqSurveyItems() {
        return xhCjksqSurveyItems;
    }

    public void setXhCjksqSurveyItems(List<XhCjksqSurveyItem> xhCjksqSurveyItems) {
        this.xhCjksqSurveyItems = xhCjksqSurveyItems;
    }

}

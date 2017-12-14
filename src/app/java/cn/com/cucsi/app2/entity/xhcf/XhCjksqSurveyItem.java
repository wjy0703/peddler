package cn.com.cucsi.app2.entity.xhcf;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import cn.com.cucsi.app.entity.xhcf.XhJksq;
import cn.com.cucsi.core.orm.hibernate.AuditableEntity;

/**
 * XhCjksqSurveyItem entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
// 表名与类名不相同时重新定义表名.
@Table(name = "XH_CJKSQ_SURVEY_ITEM")
// 默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XhCjksqSurveyItem extends AuditableEntity {

    // Fields
    private static final long serialVersionUID = -2276242824197821671L;
    private String demandWords;// 外访要求

    /** 外访要求 */
    @Column(columnDefinition = DEF_STR512)
    public String getDemandWords() {
        return this.demandWords;
    }

    /** 外访要求 */
    public void setDemandWords(String demandWords) {
        this.demandWords = demandWords;
    }

    private String demandrReply;// 外访回复

    /** 外访回复 */
    @Column(columnDefinition = DEF_STR512)
    public String getDemandrReply() {
        return this.demandrReply;
    }

    /** 外访回复 */
    public void setDemandrReply(String demandrReply) {
        this.demandrReply = demandrReply;
    }

    private Long surveyType;// 外访类型 0:外访家庭，1:外访单位

    /** 外访类型 0:外访家庭，1:外访单位 */
    @Column(columnDefinition = DEF_NUM4)
    public Long getSurveyType() {
        return this.surveyType;
    }

    /** 外访类型 0:外访家庭，1:外访单位 */
    public void setSurveyType(Long surveyType) {
        this.surveyType = surveyType;
    }
    
    
    private XhCjksqSurveyMain xhCjksqSurveyMain;             //借款申请信息
    
    /**借款申请信息*/
    @ManyToOne(cascade=CascadeType.REFRESH,fetch = FetchType.LAZY)
    @JoinColumn(name="JKSQ_SURVEY_MAIN_ID", unique= false, nullable=true, insertable=true, updatable=true)
    public XhCjksqSurveyMain getXhCjksqSurveyMain() {
        return xhCjksqSurveyMain;
    }
    
    public void setXhCjksqSurveyMain(XhCjksqSurveyMain xhCjksqSurveyMain) {
        this.xhCjksqSurveyMain = xhCjksqSurveyMain;
    }

    List<XhCjksqSurveyDetail> xhCjksqSurveyDetails;

    @OneToMany(targetEntity = XhCjksqSurveyDetail.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    //此处column定义为JKSQ_SURVEY_ITEM_ID更为合适
    @JoinColumn(name = "JKSQ_SURVEY_ID", updatable = false)
    public List<XhCjksqSurveyDetail> getXhCjksqSurveyDetails() {
        return xhCjksqSurveyDetails;
    }

    public void setXhCjksqSurveyDetails(List<XhCjksqSurveyDetail> xhCjksqSurveyDetails) {
        this.xhCjksqSurveyDetails = xhCjksqSurveyDetails;
    }
}

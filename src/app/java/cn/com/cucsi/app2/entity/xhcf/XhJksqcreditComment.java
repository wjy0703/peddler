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
 * XhJksqcreditComment entity. 
 * @author MyEclipse Persistence Tools
 */
@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "XH_JKSQCREDIT_COMMENT")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XhJksqcreditComment extends AuditableEntity{

	// Fields
	private static final long serialVersionUID = -2276242824197821671L;
	private String onefour;//114/10000查询 之（是，否，其他）
	/**114/10000查询 之（是，否，其他）*/
	@Column(columnDefinition=DEF_STR10)
	public String getOnefour() {
		return this.onefour;
	}
	/**114/10000查询 之（是，否，其他）*/
	public void setOnefour(String onefour) {
		this.onefour = onefour;
	}
	private String onefourComment;//114/10000查询之备注
	/**114/10000查询之备注*/
	@Column(columnDefinition=DEF_STR100)
	public String getOnefourComment() {
		return this.onefourComment;
	}
	/**114/10000查询之备注*/
	public void setOnefourComment(String onefourComment) {
		this.onefourComment = onefourComment;
	}
	private String gzaic;//红盾网/工商局网之(是，否，其他)
	/**红盾网/工商局网之(是，否，其他)*/
	@Column(columnDefinition=DEF_STR10)
	public String getGzaic() {
		return this.gzaic;
	}
	/**红盾网/工商局网之(是，否，其他)*/
	public void setGzaic(String gzaic) {
		this.gzaic = gzaic;
	}
	private String gzaicComment;//红盾网/工商局网之备注
	/**红盾网/工商局网之备注*/
	@Column(columnDefinition=DEF_STR100)
	public String getGzaicComment() {
		return this.gzaicComment;
	}
	/**红盾网/工商局网之备注*/
	public void setGzaicComment(String gzaicComment) {
		this.gzaicComment = gzaicComment;
	}
	private String baidu;//百度网查公司/个人信息之(是，否，其他)
	/**百度网查公司/个人信息之(是，否，其他)*/
	@Column(columnDefinition=DEF_STR10)
	public String getBaidu() {
		return this.baidu;
	}
	/**百度网查公司/个人信息之(是，否，其他)*/
	public void setBaidu(String baidu) {
		this.baidu = baidu;
	}
	private String baiduComment;//百度网查公司/个人信息之备注
	/**百度网查公司/个人信息之备注*/
	@Column(columnDefinition=DEF_STR100)
	public String getBaiduComment() {
		return this.baiduComment;
	}
	/**百度网查公司/个人信息之备注*/
	public void setBaiduComment(String baiduComment) {
		this.baiduComment = baiduComment;
	}
	private String ptopnet;//P2P网络逾期黑名单查询之(是，否，其他)
	/**P2P网络逾期黑名单查询之(是，否，其他)*/
	@Column(columnDefinition=DEF_STR10)
	public String getPtopnet() {
		return this.ptopnet;
	}
	/**P2P网络逾期黑名单查询之(是，否，其他)*/
	public void setPtopnet(String ptopnet) {
		this.ptopnet = ptopnet;
	}
	private String ptopnetComment2;//P2P网络逾期黑名单查询之备注
	/**P2P网络逾期黑名单查询之备注*/
	@Column(columnDefinition=DEF_STR100)
	public String getPtopnetComment2() {
		return this.ptopnetComment2;
	}
	/**P2P网络逾期黑名单查询之备注*/
	public void setPtopnetComment2(String ptopnetComment2) {
		this.ptopnetComment2 = ptopnetComment2;
	}
	private String court;//全国法院被执行人信息查询之(是，否，其他)
	/**全国法院被执行人信息查询之(是，否，其他)*/
	@Column(columnDefinition=DEF_STR10)
	public String getCourt() {
		return this.court;
	}
	/**全国法院被执行人信息查询之(是，否，其他)*/
	public void setCourt(String court) {
		this.court = court;
	}
	private String courtComment;//全国法院被执行人信息查询之备注
	/**全国法院被执行人信息查询之备注*/
	@Column(columnDefinition=DEF_STR100)
	public String getCourtComment() {
		return this.courtComment;
	}
	/**全国法院被执行人信息查询之备注*/
	public void setCourtComment(String courtComment) {
		this.courtComment = courtComment;
	}
	private String othercomment;//其他重要资料说明及风险点
	/**其他重要资料说明及风险点*/
	@Column(columnDefinition=DEF_STR1000)
	public String getOthercomment() {
		return this.othercomment;
	}
	/**其他重要资料说明及风险点*/
	public void setOthercomment(String othercomment) {
		this.othercomment = othercomment;
	}
	private String onefourComment2;//114/10000查询之备注
	/**114/10000查询之备注*/
	@Column(columnDefinition=DEF_STR100)
	public String getOnefourComment2() {
		return this.onefourComment2;
	}
	/**114/10000查询之备注*/
	public void setOnefourComment2(String onefourComment2) {
		this.onefourComment2 = onefourComment2;
	}
	private String otherBorrowComment;//其他借款统计
	/**其他借款统计*/
	@Column(columnDefinition=DEF_STR100)
	public String getOtherBorrowComment() {
		return this.otherBorrowComment;
	}
	/**其他借款统计*/
	public void setOtherBorrowComment(String otherBorrowComment) {
		this.otherBorrowComment = otherBorrowComment;
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
    
    
    /**
     * 信用情况
     */
    private String creditCondition;
    
    /**
     * 房产/车产
     */
    private String houseVechicle;
    
    /**
     * 月均流水
     */
    private String averageMonthBankRecord;
    
    @Column(columnDefinition = DEF_STR100)
    public String getCreditCondition() {
        return creditCondition;
    }
    
    public void setCreditCondition(String creditCondition) {
        this.creditCondition = creditCondition;
    }
    @Column(columnDefinition = DEF_STR100)
    public String getHouseVechicle() {
        return houseVechicle;
    }
    
    public void setHouseVechicle(String houseVechicle) {
        this.houseVechicle = houseVechicle;
    }
    
    @Column(columnDefinition = DEF_STR100)
    public String getAverageMonthBankRecord() {
        return averageMonthBankRecord;
    }
    
    public void setAverageMonthBankRecord(String averageMonthBankRecord) {
        this.averageMonthBankRecord = averageMonthBankRecord;
    }
    
    private String outVisitFee;// 外访费

    /** 外访费 */
    @Column(columnDefinition = DEF_NUM13_2)
    public String getOutVisitFee() {
        return this.outVisitFee;
    }

    /** 外访费 */
    public void setOutVisitFee(String outVisitFee) {
        this.outVisitFee = outVisitFee;
    }
    
    private String creditMonth;// 授信期限  建议期限

    /** 授信期限 */
    @Column(columnDefinition = DEF_NUM13_2)
    public String getCreditMonth() {
        return this.creditMonth;
    }

    /** 授信期限 */
    public void setCreditMonth(String creditMonth) {
        this.creditMonth = creditMonth;
    }

    private String creditAmount;// 授信金额  建议额度, 

    /** 授信金额 */
    @Column(columnDefinition = DEF_NUM13_2)
    public String getCreditAmount() {
        return this.creditAmount;
    }

    /** 授信金额 */
    public void setCreditAmount(String creditAmount) {
        this.creditAmount = creditAmount;
    }
    /**
     * 加急费
     */
    private Double urgentCreditFee; 
    @Column(columnDefinition = DEF_NUM15_5)
    public Double getUrgentCreditFee() {
        return this.urgentCreditFee;
    }

  //加急费用
    public void setUrgentCreditFee(Double urgentCreditFee) {
        this.urgentCreditFee = urgentCreditFee;
    }
     
    /**
     * 审核建议
     */
    private String auditInfomations;
    @Column(columnDefinition = DEF_STR100)
    public String getAuditInfomations() {
        return auditInfomations;
    }
    
    public void setAuditInfomations(String auditInfomations) {
        this.auditInfomations = auditInfomations;
    }
    
    
}

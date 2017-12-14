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
 * XhCjksqBankRecords entity. 
 * @author MyEclipse Persistence Tools
 */
@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "XH_CJKSQ_BANK_RECORDS")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XhCjksqBankRecords extends AuditableEntity{

	// Fields
	private static final long serialVersionUID = -2276242824197821671L;
	private String currentDate;//余额数对应的截至日期
	/**余额数对应的截至日期*/
	@Column(columnDefinition=DEF_STR20)
	public String getCurrentDate() {
		return this.currentDate;
	}
	/**余额数对应的截至日期*/
	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}
	private Double remainAmount;//余额
	/**余额*/
	@Column(columnDefinition=DEF_NUM18_2)
	public Double getRemainAmount() {
		return this.remainAmount;
	}
	/**余额*/
	public void setRemainAmount(Double remainAmount) {
		this.remainAmount = remainAmount;
	}
	private String bank;//银行(下拉)
	/**银行(下拉)*/
	@Column(columnDefinition=DEF_STR50)
	public String getBank() {
		return this.bank;
	}
	/**银行(下拉)*/
	public void setBank(String bank) {
		this.bank = bank;
	}
	private Long one;//月份
	/**月份*/
	@Column(columnDefinition=DEF_NUM4)
	public Long getOne() {
		return this.one;
	}
	/**月份*/
	public void setOne(Long one) {
		this.one = one;
	}
	private Double onecount;//月收入情况
	/**月收入情况*/
	@Column(columnDefinition=DEF_NUM18_2)
	public Double getOnecount() {
		return this.onecount;
	}
	/**月收入情况*/
	public void setOnecount(Double onecount) {
		this.onecount = onecount;
	}
	private Long two;//月份
	/**月份*/
	@Column(columnDefinition=DEF_NUM4)
	public Long getTwo() {
		return this.two;
	}
	/**月份*/
	public void setTwo(Long two) {
		this.two = two;
	}
	private Double twocount;//月收入情况
	/**月收入情况*/
	@Column(columnDefinition=DEF_NUM18_2)
	public Double getTwocount() {
		return this.twocount;
	}
	/**月收入情况*/
	public void setTwocount(Double twocount) {
		this.twocount = twocount;
	}
	private Long three;//月份
	/**月份*/
	@Column(columnDefinition=DEF_NUM4)
	public Long getThree() {
		return this.three;
	}
	/**月份*/
	public void setThree(Long three) {
		this.three = three;
	}
	private Double threecount;//月收入情况
	/**月收入情况*/
	@Column(columnDefinition=DEF_NUM18_2)
	public Double getThreecount() {
		return this.threecount;
	}
	/**月收入情况*/
	public void setThreecount(Double threecount) {
		this.threecount = threecount;
	}
	private Long four;//月份
	/**月份*/
	@Column(columnDefinition=DEF_NUM4)
	public Long getFour() {
		return this.four;
	}
	/**月份*/
	public void setFour(Long four) {
		this.four = four;
	}
	private Double fourcount;//月收入情况
	/**月收入情况*/
	@Column(columnDefinition=DEF_NUM18_2)
	public Double getFourcount() {
		return this.fourcount;
	}
	/**月收入情况*/
	public void setFourcount(Double fourcount) {
		this.fourcount = fourcount;
	}
	private Long five;//月份
	/**月份*/
	@Column(columnDefinition=DEF_NUM4)
	public Long getFive() {
		return this.five;
	}
	/**月份*/
	public void setFive(Long five) {
		this.five = five;
	}
	private Double fivecount;//月收入情况
	/**月收入情况*/
	@Column(columnDefinition=DEF_NUM18_2)
	public Double getFivecount() {
		return this.fivecount;
	}
	/**月收入情况*/
	public void setFivecount(Double fivecount) {
		this.fivecount = fivecount;
	}
	private Long six;//月份
	/**月份*/
	@Column(columnDefinition=DEF_NUM4)
	public Long getSix() {
		return this.six;
	}
	/**月份*/
	public void setSix(Long six) {
		this.six = six;
	}
	private Double sixcount3;//月收入情况
	/**月收入情况*/
	@Column(columnDefinition=DEF_NUM18_2)
	public Double getSixcount3() {
		return this.sixcount3;
	}
	/**月收入情况*/
	public void setSixcount3(Double sixcount3) {
		this.sixcount3 = sixcount3;
	}
	private String yesOrNo;//盖章及余额是否正确
	/**盖章及余额是否正确*/
	@Column(columnDefinition=DEF_STR10)
	public String getYesOrNo() {
		return this.yesOrNo;
	}
	/**盖章及余额是否正确*/
	public void setYesOrNo(String yesOrNo) {
		this.yesOrNo = yesOrNo;
	}
	private String bankComments;//银行流水信息备注
	/**银行流水信息备注*/
	@Column(columnDefinition=DEF_STR100)
	public String getBankComments(){
		return this.bankComments;
	}
	/**银行流水信息备注*/
	public void setBankComments(String bankComments){
		this.bankComments = bankComments;
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
     *  XhJksqCompany
     */
    private XhJksqCompany xhJksqCompany;             //借款申请信息
    
    /**借款申请信息*/
    @ManyToOne(cascade=CascadeType.REFRESH,fetch = FetchType.LAZY)
    @JoinColumn(name="JKSQCOMPANY_ID", unique= false, nullable=true, insertable=true, updatable=true)
    public XhJksqCompany getXhJksqCompany() {
        return xhJksqCompany;
    }
    
    public void setxhJksqCompany(XhJksqCompany xhJksqCompany) {
        this.xhJksqCompany = xhJksqCompany;
    }
    
    
    
}

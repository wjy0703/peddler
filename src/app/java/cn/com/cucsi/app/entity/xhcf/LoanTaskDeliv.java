package cn.com.cucsi.app.entity.xhcf;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import cn.com.cucsi.core.orm.hibernate.AuditableEntity;

/**
 * 借款端任务交割历史表.
 * 
 * 使用JPA annotation定义ORM关系.
 * 使用Hibernate annotation定义JPA 1.0未覆盖的部分.
 * 关于属性的annotation 放到get方法的上面。
 * @author 马道永
 */
@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "XH_LOAN_TASK_DELIVERY")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class LoanTaskDeliv extends AuditableEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1269839504347877022L;
	
	//客户姓名
	private String customerName;
	
	//团队经理A
	private Long teamLeaderOld;
	
	//团队经理B
	private Long teamLeaderNew;
	
	//客户经理A
	private Long customerLeaderOld;
	
	//客户经理B
	private Long customerLeaderNew;
	
	//交割条数
	private Integer delivCount;
	
	//原因
	private String reasonInfo;
	
	//状态0:正在交割 1：交割完成
	private Integer sts;

	@Column(columnDefinition= DEF_ID )
	public Long getTeamLeaderOld() {
		return teamLeaderOld;
	}

	public void setTeamLeaderOld(Long teamLeaderOld) {
		this.teamLeaderOld = teamLeaderOld;
	}

	@Column(columnDefinition= DEF_ID )
	public Long getTeamLeaderNew() {
		return teamLeaderNew;
	}

	public void setTeamLeaderNew(Long teamLeaderNew) {
		this.teamLeaderNew = teamLeaderNew;
	}

	@Column(columnDefinition= DEF_ID )
	public Long getCustomerLeaderOld() {
		return customerLeaderOld;
	}

	public void setCustomerLeaderOld(Long customerLeaderOld) {
		this.customerLeaderOld = customerLeaderOld;
	}

	@Column(columnDefinition= DEF_ID )
	public Long getCustomerLeaderNew() {
		return customerLeaderNew;
	}

	public void setCustomerLeaderNew(Long customerLeaderNew) {
		this.customerLeaderNew = customerLeaderNew;
	}

	@Column(columnDefinition= DEF_INT8 )
	public Integer getDelivCount() {
		return delivCount;
	}

	public void setDelivCount(Integer delivCount) {
		this.delivCount = delivCount;
	}

	@Column(columnDefinition=DEF_STR512)
	public String getReasonInfo() {
		return reasonInfo;
	}

	public void setReasonInfo(String reasonInfo) {
		this.reasonInfo = reasonInfo;
	}

	@Column(columnDefinition= DEF_INT8 )
	public Integer getSts() {
		return sts;
	}

	public void setSts(Integer sts) {
		this.sts = sts;
	}

	@Column(columnDefinition=DEF_STR256)
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

}

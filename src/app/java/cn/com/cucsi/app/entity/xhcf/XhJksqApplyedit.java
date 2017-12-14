package cn.com.cucsi.app.entity.xhcf;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import cn.com.cucsi.core.orm.hibernate.AuditableEntity;

/**
 * XhJksqApplyedit entity. 
 * @author MyEclipse Persistence Tools
 */
@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "XH_JKSQ_APPLYEDIT")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XhJksqApplyedit extends AuditableEntity{

	// Fields
	private static final long serialVersionUID = -2276242824197821671L;
	private String bankup01;//备用字段
	/**备用字段*/
	@Column(columnDefinition=DEF_STR32)
	public String getBankup01() {
		return this.bankup01;
	}
	/**备用字段*/
	public void setBankup01(String bankup01) {
		this.bankup01 = bankup01;
	}
	private String commentInfo;//申请修改备注信息
	/**申请修改备注信息*/
	@Column(columnDefinition=DEF_STR512)
	public String getCommentInfo() {
		return this.commentInfo;
	}
	/**申请修改备注信息*/
	public void setCommentInfo(String commentInfo) {
		this.commentInfo = commentInfo;
	}
	private Long jksqId;//借款申请ID
	/**借款申请ID*/
	@Column(columnDefinition=DEF_NUM18)
	public Long getJksqId() {
		return this.jksqId;
	}
	/**借款申请ID*/
	public void setJksqId(Long jksqId) {
		this.jksqId = jksqId;
	}
	private Long operatorId;//申请修改人ID
	/**申请修改人ID*/
	@Column(columnDefinition=DEF_NUM18)
	public Long getOperatorId() {
		return this.operatorId;
	}
	/**申请修改人ID*/
	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}
	private String state;//状态 0：提交变更，1审批不通过，2审批通过
	/**状态 0：提交变更，1审批不通过，2审批通过*/
	@Column(columnDefinition=DEF_STR10)
	public String getState() {
		return this.state;
	}
	/**状态 0：提交变更，1审批不通过，2审批通过*/
	public void setState(String state) {
		this.state = state;
	}
	private String sysName;//系统名称
	/**系统名称*/
	@Column(columnDefinition=DEF_STR256)
	public String getSysName() {
		return this.sysName;
	}
	/**系统名称*/
	public void setSysName(String sysName) {
		this.sysName = sysName;
	}
	private String sysModule;//系统模块
	/**系统模块*/
	@Column(columnDefinition=DEF_STR256)
	public String getSysModule() {
		return this.sysModule;
	}
	/**系统模块*/
	public void setSysModule(String sysModule) {
		this.sysModule = sysModule;
	}
	private String requirType;//需求类型
	/**需求类型*/
	@Column(columnDefinition=DEF_STR32)
	public String getRequirType() {
		return this.requirType;
	}
	/**需求类型*/
	public void setRequirType(String requirType) {
		this.requirType = requirType;
	}
	private String updateCause;//修改原因
	/**修改原因*/
	@Column(columnDefinition=DEF_STR256)
	public String getUpdateCause() {
		return this.updateCause;
	}
	/**修改原因*/
	public void setUpdateCause(String updateCause) {
		this.updateCause = updateCause;
	}
}

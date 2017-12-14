package cn.com.cucsi.app.entity.messageBox;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import cn.com.cucsi.app.entity.baseinfo.Employee;
import cn.com.cucsi.app.entity.baseinfo.Organi;
import cn.com.cucsi.app.entity.security.Role;
import cn.com.cucsi.core.orm.hibernate.AuditableEntity;

/**
 * 信息盒子.
 * 
 * 使用JPA annotation定义ORM关系.
 * 使用Hibernate annotation定义JPA 1.0未覆盖的部分.
 * 关于属性的annotation 放到get方法的上面。
 * @author jiangxd
 */
@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "XH_MESSAGE_BOX")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MessageBox extends AuditableEntity {

	private static final long serialVersionUID = -1960184739612606201L;

	private String messageTitle;
	
	private String messageContent;
	
	private String targetUrl;
	
	private String targetRel;
	
	private Role displaysRole;
	
	private Employee displaysEmployee;
	
	private MessageBoxType messageBoxType;
	
	private String messageState;//状态 0：未读 1：已读 注：如果为已读通知消息，则删除
	
	private Long eventId;//根据类型描述事件ID，作为与事件关系桥 注：通知消息为0
	
	private String organiScope;//组织机构 与角色配合实现分级显示
	
	private String readType;//读写类型1:人员可改 2：角色可改 3：角色与机构能改

	@Column(columnDefinition=DEF_STR128)
	public String getTargetRel() {
		return targetRel;
	}

	public void setTargetRel(String targetRel) {
		this.targetRel = targetRel;
	}

	@Column(columnDefinition=DEF_STR256)
	public String getMessageTitle() {
		return messageTitle;
	}

	public void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle;
	}

	@Column(columnDefinition=DEF_STR1024)
	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	
	@Column(columnDefinition=DEF_STR512)
	public String getTargetUrl() {
		return targetUrl;
	}

	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}

	@OneToOne(cascade=CascadeType.REFRESH,fetch = FetchType.LAZY)
	@JoinColumn(name="ROLE_ID", unique= false, nullable=true, insertable=true, updatable=true)
	public Role getDisplaysRole() {
		return displaysRole;
	}

	public void setDisplaysRole(Role displaysRole) {
		this.displaysRole = displaysRole;
	}

	@OneToOne(cascade=CascadeType.REFRESH,fetch = FetchType.LAZY)
	@JoinColumn(name="EMPLOYEE_ID", unique= false, nullable=true, insertable=true, updatable=true)
	public Employee getDisplaysEmployee() {
		return displaysEmployee;
	}

	public void setDisplaysEmployee(Employee displaysEmployee) {
		this.displaysEmployee = displaysEmployee;
	}

	@OneToOne(cascade=CascadeType.REFRESH,fetch = FetchType.LAZY)
	@JoinColumn(name="TYPE_ID", unique= false, nullable=true, insertable=true, updatable=true)
	public MessageBoxType getMessageBoxType() {
		return messageBoxType;
	}

	public void setMessageBoxType(MessageBoxType messageBoxType) {
		this.messageBoxType = messageBoxType;
	}

	@Column(columnDefinition=DEF_STR2)
	public String getMessageState() {
		return messageState;
	}

	public void setMessageState(String messageState) {
		this.messageState = messageState;
	}

	@Column(columnDefinition= DEF_ID )
	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	@Column(columnDefinition=DEF_STR4000)
	public String getOrganiScope() {
		return organiScope;
	}

	public void setOrganiScope(String organiScope) {
		this.organiScope = organiScope;
	}

	@Column(columnDefinition=DEF_STR2)
	public String getReadType() {
		return readType;
	}

	public void setReadType(String readType) {
		this.readType = readType;
	}
	
}
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
 * XhBorrowscore entity. 
 * @author MyEclipse Persistence Tools
 */
@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "XH_BORROWSCORE")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class XhBorrowscore extends AuditableEntity{

	// Fields
	private static final long serialVersionUID = -2276242824197821671L;
	private Integer marriage;//婚姻分数
	/**婚姻分数*/
	@Column(columnDefinition=DEF_INT4)
	public Integer getMarriage() {
		return this.marriage;
	}
	/**婚姻分数*/
	public void setMarriage(Integer marriage) {
		this.marriage = marriage;
	}
	private Integer education;//文化程度
	/**文化程度*/
	@Column(columnDefinition=DEF_INT4)
	public Integer getEducation() {
		return this.education;
	}
	/**文化程度*/
	public void setEducation(Integer education) {
		this.education = education;
	}
	private Integer households;//户口登记
	/**户口登记*/
	@Column(columnDefinition=DEF_INT4)
	public Integer getHouseholds() {
		return this.households;
	}
	/**户口登记*/
	public void setHouseholds(Integer households) {
		this.households = households;
	}
	private Integer totalWorkyear;//总工作年龄
	/**总工作年龄*/
	@Column(columnDefinition=DEF_INT4)
	public Integer getTotalWorkyear() {
		return this.totalWorkyear;
	}
	/**总工作年龄*/
	public void setTotalWorkyear(Integer totalWorkyear) {
		this.totalWorkyear = totalWorkyear;
	}
	private Integer socialSecurity;//社保
	/**社保*/
	@Column(columnDefinition=DEF_INT4)
	public Integer getSocialSecurity() {
		return this.socialSecurity;
	}
	/**社保*/
	public void setSocialSecurity(Integer socialSecurity) {
		this.socialSecurity = socialSecurity;
	}
	private Integer house;//住房情况
	/**住房情况*/
	@Column(columnDefinition=DEF_INT4)
	public Integer getHouse() {
		return this.house;
	}
	/**住房情况*/
	public void setHouse(Integer house) {
		this.house = house;
	}
	private Integer vechicle;//车辆情况
	/**车辆情况*/
	@Column(columnDefinition=DEF_INT4)
	public Integer getVechicle() {
		return this.vechicle;
	}
	/**车辆情况*/
	public void setVechicle(Integer vechicle) {
		this.vechicle = vechicle;
	}
	private Integer officeType;//单位性质
	/**单位性质*/
	@Column(columnDefinition=DEF_INT4)
	public Integer getOfficeType() {
		return this.officeType;
	}
	/**单位性质*/
	public void setOfficeType(Integer officeType) {
		this.officeType = officeType;
	}
	private Integer officePosition;//单位岗位性质
	/**单位岗位性质*/
	@Column(columnDefinition=DEF_INT4)
	public Integer getOfficePosition() {
		return this.officePosition;
	}
	/**单位岗位性质*/
	public void setOfficePosition(Integer officePosition) {
		this.officePosition = officePosition;
	}
	private Integer officeYear;//单位工作年限
	/**单位工作年限*/
	@Column(columnDefinition=DEF_INT4)
	public Integer getOfficeYear() {
		return this.officeYear;
	}
	/**单位工作年限*/
	public void setOfficeYear(Integer officeYear) {
		this.officeYear = officeYear;
	}
	private Integer certification;//职业证书
	/**职业证书*/
	@Column(columnDefinition=DEF_INT4)
	public Integer getCertification() {
		return this.certification;
	}
	/**职业证书*/
	public void setCertification(Integer certification) {
		this.certification = certification;
	}
	private Integer monthSalary;//月收入
	/**月收入*/
	@Column(columnDefinition=DEF_INT4)
	public Integer getMonthSalary() {
		return this.monthSalary;
	}
	/**月收入*/
	public void setMonthSalary(Integer monthSalary) {
		this.monthSalary = monthSalary;
	}
	private Integer consumePercent;//月供支出比
	/**月供支出比*/
	@Column(columnDefinition=DEF_INT4)
	public Integer getConsumePercent() {
		return this.consumePercent;
	}
	/**月供支出比*/
	public void setConsumePercent(Integer consumePercent) {
		this.consumePercent = consumePercent;
	}
	private Integer creditRecord;//信用记录
	/**信用记录*/
	@Column(columnDefinition=DEF_INT4)
	public Integer getCreditRecord() {
		return this.creditRecord;
	}
	/**信用记录*/
	public void setCreditRecord(Integer creditRecord) {
		this.creditRecord = creditRecord;
	}
	private Integer oldCustomer;//老客户
	/**老客户*/
	@Column(columnDefinition=DEF_INT4)
	public Integer getOldCustomer() {
		return this.oldCustomer;
	}
	/**老客户*/
	public void setOldCustomer(Integer oldCustomer) {
		this.oldCustomer = oldCustomer;
	}
	private Integer publicRecord;//公共记录
	/**公共记录*/
	@Column(columnDefinition=DEF_INT4)
	public Integer getPublicRecord() {
		return this.publicRecord;
	}
	/**公共记录*/
	public void setPublicRecord(Integer publicRecord) {
		this.publicRecord = publicRecord;
	}
	private Long employeeId;//评分人
	/**评分人*/
	@Column(columnDefinition=DEF_NUM18)
	public Long getEmployeeId() {
		return this.employeeId;
	}
	/**评分人*/
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
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
	private Integer scoreType=0;//评分类型 0:门店评分,1：信审评分
	/**评分类型 0:门店评分,1：信审评分*/
	@Column(columnDefinition=DEF_INT4)
	public Integer getScoreType() {
		return this.scoreType;
	}
	/**评分类型 0:门店评分,1：信审评分*/
	public void setScoreType(Integer scoreType) {
		this.scoreType = scoreType;
	}
	private Integer age;//年龄分数
	/**年龄分数*/
	@Column(columnDefinition=DEF_INT4)
	public Integer getAge() {
		return this.age;
	}
	/**年龄分数*/
	public void setAge(Integer age) {
		this.age = age;
	}
	private Integer healthState;//健康状况
	/**健康状况*/
	@Column(columnDefinition=DEF_INT4)
	public Integer getHealthState() {
		return this.healthState;
	}
	/**健康状况*/
	public void setHealthState(Integer healthState) {
		this.healthState = healthState;
	}
}

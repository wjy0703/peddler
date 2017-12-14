package cn.com.cucsi.app.entity.baseinfo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import cn.com.cucsi.core.orm.hibernate.IdEntity;


@Entity
@Table(name = "BASE_CUST_INFO")
public class Customer  extends IdEntity{

	private static final long serialVersionUID = 4432819598097508720L;
  
	/**
	 * 姓名
	 */
	private String name;
	
	/**
	 * 性别
	 */
	private String sex;
	
	/**
	 * 出生日期
	 */
	private Date birthDay;
	
	/**
	 * 职务
	 */
	private String post;
	
	/**
	 * 个人简介
	 */
	private String briefIntroduce;
	
	/**
	 * 手机1
	 */
	private String mobile1;
	
	/**
	 * 手机2
	 */
	private String mobile2;
	
	/**
	 * 联系电话1
	 */
	private String tel1;
	
	/**
	 * 联系电话2
	 */
	private String tel2;
	
	/**
	 * 邮箱
	 */
	private String email;
	
	/**
	 * QQ号码
	 */
	private String qq;
	
	/**
	 * MSN号码
	 */
	private String msn;
	
	/**
	 * 联系地址
	 */
	private String address;
	
	/**
	 * 邮政编码
	 */
	private String zip;
	
	/**
	 * 备注
	 */
	private String remark;
	

	public void setName(String name) {
		this.name = name;
	}

	@Column(columnDefinition=DEF_STR32, nullable=false)
	public String getName() {
		return name;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(columnDefinition=DEF_STR1)
	public String getSex() {
		return sex;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date getBirthDay() {
		return birthDay;
	}

	public void setPost(String post) {
		this.post = post;
	}

	@Column(columnDefinition=DEF_STR16)
	public String getPost() {
		return post;
	}

	public void setBriefIntroduce(String briefIntroduce) {
		this.briefIntroduce = briefIntroduce;
	}

	@Column(columnDefinition=DEF_STR256)
	public String getBriefIntroduce() {
		return briefIntroduce;
	}

	public void setMobile1(String mobile1) {
		this.mobile1 = mobile1;
	}

	@Column(columnDefinition=DEF_STR16)
	public String getMobile1() {
		return mobile1;
	}

	public void setMobile2(String mobile2) {
		this.mobile2 = mobile2;
	}

	@Column(columnDefinition=DEF_STR16)
	public String getMobile2() {
		return mobile2;
	}

	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}

	@Column(columnDefinition=DEF_STR16)
	public String getTel1() {
		return tel1;
	}

	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}

	@Column(columnDefinition=DEF_STR16)
	public String getTel2() {
		return tel2;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(columnDefinition=DEF_STR128)
	public String getEmail() {
		return email;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	@Column(columnDefinition=DEF_STR16)
	public String getQq() {
		return qq;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}

	@Column(columnDefinition=DEF_STR128)
	public String getMsn() {
		return msn;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(columnDefinition=DEF_STR256)
	public String getAddress() {
		return address;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	@Column(columnDefinition=DEF_STR8)
	public String getZip() {
		return zip;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}	
	
	@Column(columnDefinition=DEF_STR4000)
	public String getRemark() {
		return remark;
	}

}
 
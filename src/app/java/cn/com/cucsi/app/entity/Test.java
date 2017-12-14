package cn.com.cucsi.app.entity;

import java.sql.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.Table;

import cn.com.cucsi.core.orm.hibernate.IdEntity;

@Entity
@Table(name = "jxd_test")
public class Test extends IdEntity {

	/**
	 * 这是各种数据类型映射举例的测试实体类，共开发人员参考。
	 * @author jiangxd
	 */
	private static final long serialVersionUID = -7063370290586535474L;

	private String a;
	private Integer b;	
	private Date c;
	private String d;
	private byte[] e;
	
	public void setA(String a) {
		this.a = a;
	}
	
	@Column(columnDefinition=DEF_STR32, nullable=false)
	public String getA() {
		return a;
	}
	
	@Column(columnDefinition=DEF_INT4)
	public Integer getB() {
		return b;
	}
	public void setB(Integer b) {
		this.b = b;
	}
	
	public Date getC() {
		return c;
	}
	public void setC(Date c) {
		this.c = c;
	}
	
	@Lob
	@Basic(fetch=FetchType.LAZY) 
	public String getD() {
		return d;
	}
	public void setD(String d) {
		this.d = d;
	}
	
	@Lob
	@Basic(fetch=FetchType.LAZY) 
	public byte[] getE() {
		return e;
	}
	public void setE(byte[] e) {
		this.e = e;
	}
	
	
}

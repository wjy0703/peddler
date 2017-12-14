package cn.com.cucsi.app.entity.baseinfo;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import cn.com.cucsi.app.entity.security.Role;
import cn.com.cucsi.core.orm.hibernate.IdEntity;
import cn.com.cucsi.core.utils.ReflectionUtils;

/**
 * 菜单.
 * 
 * 使用JPA annotation定义ORM关系.
 * 使用Hibernate annotation定义JPA 1.0未覆盖的部分.
 * 关于属性的annotation 要么全放到属性定义的前面，要么全放到方法的前面，放到方法的前面要注意
 * 都放到get方法的前面。
 * @author jiangxd
 */
@Entity
//表名与类名不相同时重新定义表名.
@Table(name = "BASE_MENU")

public class Menu extends IdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1960184739612606201L;

	private String menuName;
	
	private String menuUrl;
	
	private Integer menuType;
	
	private Integer levelId;
	
	private Integer sortNo;
	
	/**
	 * 窗口打开目标区域
	 * 取值：_blank, 默认：navTab,
	 */
	private String target;

	/**
	 * 窗口引用名称，用来标识不同功能的窗口
	 */
	private String rel;
	
	/**
	 * 打开的页面是否是外部页面，默认值：P
	 */
	private String external;
	
	/**
	 * 配合rel使用，决定要打开的页面是否在相同ref页面引用中打开。默认值：P
	 */
	private String fresh;
	
	/**
	 * 自定义打开窗口的标签,默认值为空，当等于空时，打开的页面标签标题使用menuName值。
	 */
	private String title;
	
	private String sts;
	
	@Column(columnDefinition=DEF_STR16)
	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	@Column(columnDefinition=DEF_STR16)
	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

	@Column(columnDefinition=DEF_STR1)
	public String getExternal() {
		return external;
	}

	public void setExternal(String external) {
		this.external = external;
	}

	@Column(columnDefinition=DEF_STR1)
	public String getFresh() {
		return fresh;
	}

	public void setFresh(String fresh) {
		this.fresh = fresh;
	}

	@Column(columnDefinition=DEF_STR32)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	private Menu parent;	

	//字段非空且唯一, 用于提醒Entity使用者及生成DDL.
	@Column(columnDefinition=DEF_STR64, nullable = false)
	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	
	@Column(columnDefinition=DEF_STATUS)
	public void setSts(String sts) {
		this.sts = sts;
	}

	public String getSts() {
		return sts;
	}

	@Column(columnDefinition=DEF_STR256)
	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	@Column(columnDefinition=DEF_INT4)
	public Integer getMenuType() {
		return menuType;
	}

	public void setMenuType(Integer menuType) {
		this.menuType = menuType;
	}

	@Column(columnDefinition=DEF_INT4)
	public Integer getLevelId() {
		return levelId;
	}

	public void setLevelId(Integer levelId) {
		this.levelId = levelId;
	}

	@Column(columnDefinition=DEF_INT4)
	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name = "heiger_menu")
	public Menu getParent() {
		return parent;
	}

	public void setParent(Menu parent) {
		this.parent = parent;
	}

	@Transient
	public String getAttrTarget(){
		String _target = target;
		if (null==target || "".equals(target) || "null".equals(target)){
			_target = "navTab";
		}
		return "target=\"" + _target + "\" ";
	}
	
	@Transient
	public String getAttrRel(){
		String _rel = rel;
		if (null==rel || "".equals(rel) || "null".equals(rel)){
			_rel = "rel_"+ getId();
		}
		return "rel=\"" + _rel + "\" ";
	}

	@Transient
	public String getAttrExternal(){
		if (external.equals("Y")){
			return "external=\"true\" ";
		}
		else if(external.equals("N")){
			return "external=\"false\" ";
		}
		else{
			return "";
		}
	}

	@Transient
	public String getAttrFresh(){
		if (fresh.equals("N")){
			return "fresh=\"false\" ";
		}
		else if (fresh.equals("Y")){
			return "fresh=\"true\" ";
		}
		else{
			return "";
		}
	}
	
	@Transient
	public String getAttrTitle(){		
		if (null!=title && !"".equals(title) && !"null".equals(title)){
			return "title=\"" + title + "\" ";
		}
		else{
			return "";
		}
	}

	@Transient
	public String getAttrMenuUrl(){
		if (null!=menuUrl && !"".equals(menuUrl) && !"null".equals(menuUrl)){
			if(menuUrl.indexOf("http://")==0){
				return "href=\"" + menuUrl + "\" ";
			}
			else if(menuUrl.indexOf("javascript:")==0){
				return "href=\"" + menuUrl + "\" ";
			}
			else{
				return "href=\"_CTX_" + menuUrl + "\" ";
			}
		}
		else{
			return "";
		}
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
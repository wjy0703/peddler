package cn.com.cucsi.app.taglib;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.hibernate.tool.hbm2x.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.com.cucsi.app.service.baseinfo.AttrCacheManager;
import cn.com.cucsi.app.web.util.MetaDataTypeConverter;

@Component
@Scope("prototype")
public class AttrTag extends SimpleTagSupport
{
	/**
	 * 类型 对应数据库XH_MATETYPE中的 TYPE_CODE字段
	 */
	String coding;
	
	String name;
	
	String clazz;
	
	String id;
	
	String value;
	
	/**
	 * 是否显示提示选项
	 */
	boolean hasTitle = true;
	
	String title;
	
	String titleValue="";
	
	//@Autowired
	AttrCacheManager attrCacheManager;
	
	@Override
	public void doTag() throws JspException, IOException {
		initManager();
		StringBuffer select = new StringBuffer();		 
		List<Map<String, Object>> options = attrCacheManager.getAttrByCoding(MetaDataTypeConverter.getCodingName(coding));
		select.append("<select name= \"" + getName() + "\" class = \"" + getClazz() + "\"" );
		if(StringUtils.isNotEmpty(getId())){
			select.append(" id=\"" + getId() + "\">");			
		}else{
			select.append(">");			
		}
		if(isHasTitle()){
			if(StringUtils.isNotEmpty(getTitle())){
				select.append("<option value=\""+ getTitleValue()+"\">"+getTitle() +"</option>");
			}else{
				select.append("<option value=\""+ getTitleValue() +"\">请选择</option>");
			}
		}
		for(Map<String, Object> option : options){
			if(StringUtils.isNotEmpty(getValue()) && StringUtils.equals(getValue(), option.get("VALUE").toString())) {
				select.append("<option value=\"" + option.get("VALUE") + "\" selected=\"selected\">"+option.get("DESCRIPTION") +"</option>");
			}else{
				select.append("<option value=\"" + option.get("VALUE") + "\">"+ option.get("DESCRIPTION") +"</option>");
			}
		}
		select.append("</select>");
		getJspContext().getOut().write(select.toString()); 
	}

	
	
	private void initManager(){
		if(attrCacheManager == null){
			ServletContext servletContext = ((PageContext)getJspContext()).getServletContext();
		    WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		    attrCacheManager = (AttrCacheManager) ctx.getBean("attrCacheManager");
		}
	}



	public String getCoding() {
		return coding;
	}



	public void setCoding(String coding) {
		this.coding = coding;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getClazz() {
		return clazz;
	}



	public void setClazz(String clazz) {
		this.clazz = clazz;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getValue() {
		return value;
	}



	public void setValue(String value) {
		this.value = value;
	}



	public boolean isHasTitle() {
		return hasTitle;
	}



	public void setHasTitle(boolean hasTitle) {
		this.hasTitle = hasTitle;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getTitleValue() {
		return titleValue;
	}



	public void setTitleValue(String titleValue) {
		this.titleValue = titleValue;
	}
	
	
	
}
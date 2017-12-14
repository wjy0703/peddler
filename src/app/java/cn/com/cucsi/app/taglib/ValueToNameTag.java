package cn.com.cucsi.app.taglib;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.hibernate.tool.hbm2x.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.com.cucsi.app.entity.baseinfo.City;
import cn.com.cucsi.app.service.baseinfo.AddressManager;
import cn.com.cucsi.app.service.baseinfo.AttrCacheManager;
import cn.com.cucsi.app.web.util.MetaDataTypeConverter;

/**
 * 枚举类转换成对应的值
 *
 * @author xjs
 * @date 2013-8-9 下午3:34:40
 */
@Component
@Scope("prototype")
public class ValueToNameTag extends SimpleTagSupport
{
	/**
	 * 类型 对应数据库BASE_ATTR_TYPE中的 coding字段
	 */
	String coding;
	
	String value;
	
	
	//@Autowired
	AttrCacheManager attrCacheManager;
	
	@Override
	public void doTag() throws JspException, IOException {
		initManager();
		String name = attrCacheManager.getAttrName(MetaDataTypeConverter.getCodingName(coding),value);
		getJspContext().getOut().write(name); 
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


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}

    
}
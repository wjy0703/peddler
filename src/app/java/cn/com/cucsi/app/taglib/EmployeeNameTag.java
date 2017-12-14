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
import cn.com.cucsi.app.service.baseinfo.EmployeeCacheManager;
import cn.com.cucsi.app.web.util.MetaDataTypeConverter;

/**
 * 枚举类转换成对应的值
 *
 * @author xjs
 * @date 2013-8-9 下午3:34:40
 */
@Component
@Scope("prototype")
public class EmployeeNameTag extends SimpleTagSupport
{

	
	Long id;	
	
	//@Autowired
	EmployeeCacheManager employeeCacheManager;
	
	@Override
	public void doTag() throws JspException, IOException {
		initManager();
		String name = "";
		try{
			if(getId()!=null && getId() > 0){ 
			 name = employeeCacheManager.getName(getId());
			}
		}catch(Exception e){
			
		}
		getJspContext().getOut().write(name); 
	}

	
	private void initManager(){
		if(employeeCacheManager == null){
			ServletContext servletContext = ((PageContext)getJspContext()).getServletContext();
		    WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		    employeeCacheManager = (EmployeeCacheManager) ctx.getBean("employeeCacheManager");
		}
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}

   
}
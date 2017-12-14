package cn.com.cucsi.app.taglib;

import java.io.IOException;
import java.util.List;
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

@Component
@Scope("prototype")
public class SingleAddressTag extends SimpleTagSupport
{
	
	String id;
	
	//@Autowired
	AddressManager addressManager;
	
	
	@Override
	public void doTag() throws JspException, IOException {
		initManager();
		String newId = getId();
		String name = "";
		if(newId != null && !"".equals(newId)){
			name = addressManager.getCityNameById(Long.parseLong(newId));
		}
		getJspContext().getOut().write(name); 
	}
    
	
	
	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	private void initManager(){
		if(addressManager == null){
			ServletContext servletContext = ((PageContext)getJspContext()).getServletContext();
		    WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		    addressManager = (AddressManager) ctx.getBean("addressManager");
		}
	}
	
}
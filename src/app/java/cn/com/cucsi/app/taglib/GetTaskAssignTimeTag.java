package cn.com.cucsi.app.taglib;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.com.cucsi.app.service.baseinfo.TaglibManager;

@Component
@Scope("prototype")
public class GetTaskAssignTimeTag extends SimpleTagSupport
{
	//@Autowired
	TaglibManager taglibManager;
	
	String jksqId;

	

	public String getJksqId() {
		return jksqId;
	}

	public void setJksqId(String jksqId) {
		this.jksqId = jksqId;
	}

	@Override
	public void doTag() throws JspException, IOException {
		String names =  getTogetherNameFromJksqId(this.getJksqId());
		getJspContext().getOut().write(names); 
	}

	private String getTogetherNameFromJksqId(String jksqId) {
	  	initManager();
		return taglibManager.getTaskAsignTimeJksqId(Integer.parseInt(jksqId));
	}
	
	private void initManager(){
		if(taglibManager == null){
			ServletContext servletContext = ((PageContext)getJspContext()).getServletContext();
		    WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		    taglibManager = (TaglibManager) ctx.getBean("taglibManager");
		}
	}
	
}
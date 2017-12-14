package cn.com.cucsi.app.taglib;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.com.cucsi.app.service.baseinfo.AttrCacheManager;

@Component
@Scope("prototype")
public class EnumToNameTag extends SimpleTagSupport {
	// @Autowired
	AttrCacheManager attrManager;

	String state;

	public String getState() {
		return state;
	}

	public void setState(String state) throws JspException {
		// this.state = (String)
		// ExpressionEvaluatorManager.evaluate("state",state.toString(),
		// Object.class, (PageContext)getJspContext());
		this.state = state;
	}

	@Override
	public void doTag() throws JspException, IOException {
		String name = getNameFromState(this.getState());
		getJspContext().getOut().write(name);
	}

	private String getNameFromState(String state) {
		initManager();
		Map enumValue = attrManager.getAttrByValue(state);
		if (enumValue == null)
			return "";

		return enumValue.get("stateName").toString();
	}

	private void initManager() {
		if (attrManager == null) {
			ServletContext servletContext = ((PageContext) getJspContext())
					.getServletContext();
			WebApplicationContext ctx = WebApplicationContextUtils
					.getWebApplicationContext(servletContext);
			attrManager = (AttrCacheManager) ctx.getBean("attrCacheManager");
		}
	}

}
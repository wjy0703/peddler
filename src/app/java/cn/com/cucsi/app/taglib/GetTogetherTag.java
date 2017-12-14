package cn.com.cucsi.app.taglib;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.hibernate.tool.hbm2x.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.com.cucsi.app.service.baseinfo.TaglibManager;

@Component
public class GetTogetherTag extends SimpleTagSupport
{
	//@Autowired
	TaglibManager taglibManager;
	
	String lendId;

	public String getLendId() {
		return lendId;
	}

	public void setLendId(String lendId) {
		this.lendId = lendId;
	}
	
	/**
	 * 是否工借人
	 */
	String yesOrNo;
		
    public String getYesOrNo() {
        return yesOrNo;
    }

    
    public void setYesOrNo(String yesOrNo) {
        this.yesOrNo = yesOrNo;
    }

    @Override
	public void doTag() throws JspException, IOException {
        
		String names = "";
		if("是".equals(getYesOrNo()))
		    names = getTogetherNameFromJksqId(this.getLendId());
		getJspContext().getOut().write(names); 
	}

	private String getTogetherNameFromJksqId(String lendId) {
	  	initManager();
		List<Map<String, Object>> names = taglibManager.getTogetherNameTypeJksqId(Integer.parseInt(lendId));
		String name = "";
		if(names == null)
			return name;
		Set<String> set = new HashSet<String>();
		
		for(int index = 0 ; index < names.size() ; index++ ){
			Map<String, Object> singleName = names.get(index);			
			set.add(singleName.get("name") != null ? singleName.get("name").toString() : "");
		}
		Iterator<String> it = set.iterator();
		while(it.hasNext()){
			name += it.next() + ",";  
		}
		if(StringUtils.isNotEmpty(name ))
			name = name.substring(0,name.length()-1);
		return name;
	}
	
	private void initManager(){
		if(taglibManager == null){
			ServletContext servletContext = ((PageContext)getJspContext()).getServletContext();
		    WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		    taglibManager = (TaglibManager) ctx.getBean("taglibManager");
		}
	}
	
}
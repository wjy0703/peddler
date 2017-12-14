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
public class GetTogetherIdentificationTag extends SimpleTagSupport
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
     * 是否共借人
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
		String ids = "";
		if("是".equals(getYesOrNo()))
		    ids =  getTogetherNameFromJksqId(this.getLendId());
		getJspContext().getOut().write(ids); 
	}

	private String getTogetherNameFromJksqId(String lendId) {
	  	initManager();
		List<Map<String, Object>> results = taglibManager.getTogetherNameTypeJksqId(Integer.parseInt(lendId));
		String IDENTIFICATION = "";
		if(results == null)
			return IDENTIFICATION;
		Set<String> set = new HashSet<String>();
		
		for(int index = 0 ; index < results.size() ; index++ ){
			Map<String, Object> item = results.get(index);			
			set.add(item.get("IDENTIFICATION")!= null ? item.get("IDENTIFICATION").toString():"");
		}
		Iterator<String> it = set.iterator();
		while(it.hasNext()){
			IDENTIFICATION += it.next() + ",";  
		}
		if(StringUtils.isNotEmpty(IDENTIFICATION ))
			IDENTIFICATION = IDENTIFICATION.substring(0,IDENTIFICATION.length()-1);
		return IDENTIFICATION;
	}
	
	private void initManager(){
		if(taglibManager == null){
			ServletContext servletContext = ((PageContext)getJspContext()).getServletContext();
		    WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		    taglibManager = (TaglibManager) ctx.getBean("taglibManager");
		}
	}
	
}
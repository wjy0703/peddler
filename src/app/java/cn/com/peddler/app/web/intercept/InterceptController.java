package cn.com.peddler.app.web.intercept;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.peddler.app.entity.login.Businessinfo;
import cn.com.peddler.app.service.baseinfo.BusinessinfoManager;
import cn.com.peddler.app.service.reat.ReatpackageManager;
import cn.com.peddler.app.util.RequestPageUtils;
import cn.com.peddler.core.orm.Page;
import cn.com.peddler.core.web.ServletUtils;

@Controller
@RequestMapping(value="/intercept/businessinfo")
public class InterceptController {
	private BusinessinfoManager businessinfoManager;
	@Autowired
	public void setBusinessinfoManager(BusinessinfoManager businessinfoManager) {
		this.businessinfoManager = businessinfoManager;
	}
	@Autowired
	private ReatpackageManager reatpackageManager;
	
	@RequestMapping(value="/listBusinessinfo")
	public String listBusinessinfo(HttpServletRequest request, Model model){
		// 处理分页的参数
		Page<Businessinfo> page = new RequestPageUtils<Businessinfo>()
                .generatePage(request);
		
		Map<String, Object> map = ServletUtils.getParametersStartingWith2(request, "filter_");		
		
		businessinfoManager.searchBusinessinfo(page, map);
	
		model.addAttribute("page", page);
		model.addAttribute("map", map);
		return "intercept/reat/businessinfoIndex";
		
	}
	
}

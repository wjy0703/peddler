package cn.com.peddler.app.web.reat;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.peddler.app.entity.login.Businessinfo;
import cn.com.peddler.app.service.baseinfo.BusinessinfoManager;
import cn.com.peddler.app.util.RequestPageUtils;
import cn.com.peddler.core.orm.Page;
import cn.com.peddler.core.web.DwzResult;
import cn.com.peddler.core.web.ServletUtils;

@Controller
@RequestMapping(value="/businessinfo")
public class BusinessinfoController {
	private BusinessinfoManager businessinfoManager;
	@Autowired
	public void setBusinessinfoManager(BusinessinfoManager businessinfoManager) {
		this.businessinfoManager = businessinfoManager;
	}
	
	@RequestMapping(value="/listBusinessinfo")
	public String listBusinessinfo(HttpServletRequest request, Model model){
		// 处理分页的参数
		Page<Businessinfo> page = new RequestPageUtils<Businessinfo>()
                .generatePage(request);
		
		Map<String, Object> map = ServletUtils.getParametersStartingWith2(request, "filter_");		
		
		businessinfoManager.searchBusinessinfo(page, map);
	
		model.addAttribute("page", page);
		model.addAttribute("map", map);
		return "folder/businessinfoIndex";
		
	}
	
	@RequestMapping(value="/saveBusinessinfo",method=RequestMethod.POST)
	public String saveBusinessinfo(@ModelAttribute("businessinfo") Businessinfo businessinfo, HttpServletRequest request, HttpServletResponse response){
		
		businessinfoManager.saveBusinessinfo(businessinfo);

		DwzResult success = new DwzResult("200","保存成功","rel_listBusinessinfo","","closeCurrent","");
		ServletUtils.renderJson(response, success);
		
		return null;
	}
	
	@RequestMapping(value="/addBusinessinfo", method=RequestMethod.GET)
	public String addBusinessinfo(HttpServletRequest request, Model model){
		model.addAttribute("businessinfo", new Businessinfo());
		return "folder/businessinfoInput";
	}
	
	@RequestMapping(value="/editBusinessinfo/{Id}", method=RequestMethod.GET)
	public String editBusinessinfo(@PathVariable Long Id, Model model){
		Businessinfo businessinfo = businessinfoManager.getBusinessinfo(Id);
		model.addAttribute("businessinfo",businessinfo);
		return "folder/businessinfoInput";
	}

	@RequestMapping(value="/delBusinessinfo/{Id}")
	public String delUser(@PathVariable Long Id, HttpServletResponse response){
		businessinfoManager.deleteBusinessinfo(Id);
		DwzResult success = new DwzResult("200","删除成功","rel_listBusinessinfo","","","");
		ServletUtils.renderJson(response, success);
		return null;
	}
	
	@RequestMapping(value="/batchdelBusinessinfo")
	public String batchDelUser(HttpServletRequest request, HttpServletResponse response){
		String ids = request.getParameter("ids");
		String[] Ids = ids.split(",");
		boolean isSuccess = businessinfoManager.batchDelBusinessinfo(Ids);
		
		DwzResult success = null;
		if (isSuccess){
			success = new DwzResult("200","删除成功","rel_listBusinessinfo","","","");
		}
		else{
			success = new DwzResult("300","删除失败","rel_listBusinessinfo","","","");
		}
		ServletUtils.renderJson(response, success);
		return null;
	}
}

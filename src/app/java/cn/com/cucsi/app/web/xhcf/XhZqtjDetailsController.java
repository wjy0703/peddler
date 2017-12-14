package cn.com.cucsi.app.web.xhcf;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.com.cucsi.app.entity.xhcf.XhZqtjDetails;
import cn.com.cucsi.app.service.xhcf.XhZqtjDetailsManager;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.web.DwzResult;
import cn.com.cucsi.core.web.ServletUtils;

@Controller
@RequestMapping(value="/xhZqtjDetails")
public class XhZqtjDetailsController {
	private XhZqtjDetailsManager xhZqtjDetailsManager;
	@Autowired
	public void setXhZqtjDetailsManager(XhZqtjDetailsManager xhZqtjDetailsManager) {
		this.xhZqtjDetailsManager = xhZqtjDetailsManager;
	}
	
	@RequestMapping(value="/listXhZqtjDetails")
	public String listXhZqtjDetails(HttpServletRequest request, Model model){
		// 处理分页的参数
		Page<XhZqtjDetails> page = new Page<XhZqtjDetails>();
		String pageSize = request.getParameter("numPerPage");
		if (StringUtils.isNotBlank(pageSize)){
			page.setPageSize(Integer.valueOf(pageSize));
		}
		String pageNo = request.getParameter("pageNum");
		if(StringUtils.isNotBlank(pageNo)){
			page.setPageNo(Integer.valueOf(pageNo));
		}
		String orderBy = request.getParameter("orderField");
		if(StringUtils.isNotBlank(orderBy)){
			page.setOrderBy(orderBy);
		}
		String order = request.getParameter("orderDirection");
		if(StringUtils.isNotBlank(order)){
			page.setOrder(order);
		}
		
		Map<String, Object> map = ServletUtils.getParametersStartingWith2(request, "filter_");		
		
		xhZqtjDetailsManager.searchXhZqtjDetails(page, map);
	
		model.addAttribute("page", page);
		model.addAttribute("map", map);
		return "zqtj/xhZqtjDetailsIndex";
		
	}
	
	@RequestMapping(value="/listXhZqtjDetails/{zqid}")
	public String listXhZqtjDetailsByZqid(HttpServletRequest request, @PathVariable Long zqid,Model model){
		// 处理分页的参数
		Page<XhZqtjDetails> page = new Page<XhZqtjDetails>();
		String pageSize = request.getParameter("numPerPage");
		if (StringUtils.isNotBlank(pageSize)){
			page.setPageSize(Integer.valueOf(pageSize));
		}
		String pageNo = request.getParameter("pageNum");
		if(StringUtils.isNotBlank(pageNo)){
			page.setPageNo(Integer.valueOf(pageNo));
		}
		String orderBy = request.getParameter("orderField");
		if(StringUtils.isNotBlank(orderBy)){
			page.setOrderBy(orderBy);
		}
		String order = request.getParameter("orderDirection");
		if(StringUtils.isNotBlank(order)){
			page.setOrder(order);
		}
		
		Map<String, Object> map = ServletUtils.getParametersStartingWith2(request, "filter_");		
		
		map.put("kyzqjzId",zqid);
		map.put("zqtjstate","9");
		map.put("overstate","2");
		xhZqtjDetailsManager.searchXhZqtjDetails(page, map);
	
		model.addAttribute("page", page);
		model.addAttribute("map", map);
		return "zqtj/xhZqtjDetailsIndexByKyzqjzId";
		
	}
	
	@RequestMapping(value="/saveXhZqtjDetails",method=RequestMethod.POST)
	public String saveXhZqtjDetails(@ModelAttribute("xhZqtjDetails") XhZqtjDetails xhZqtjDetails, HttpServletRequest request, HttpServletResponse response){
		
		xhZqtjDetailsManager.saveXhZqtjDetails(xhZqtjDetails);

		DwzResult success = new DwzResult("200","保存成功","rel_listXhZqtjDetails","","closeCurrent","");
		ServletUtils.renderJson(response, success);
		
		return null;
	}
	
	@RequestMapping(value="/addXhZqtjDetails", method=RequestMethod.GET)
	public ModelAndView add(){
		return new ModelAndView("folder/xhZqtjDetailsInput", "xhZqtjDetails", new XhZqtjDetails());
	}
	
	@RequestMapping(value="/editXhZqtjDetails/{Id}", method=RequestMethod.GET)
	public ModelAndView edit(@PathVariable Long Id){
		XhZqtjDetails xhZqtjDetails = xhZqtjDetailsManager.getXhZqtjDetails(Id);
		return new ModelAndView("folder/xhZqtjDetailsInput", "xhZqtjDetails", xhZqtjDetails);
	}

	@RequestMapping(value="/delXhZqtjDetails/{Id}")
	public String delUser(@PathVariable Long Id, HttpServletResponse response){
		xhZqtjDetailsManager.deleteXhZqtjDetails(Id);
		DwzResult success = new DwzResult("200","删除成功","rel_listXhZqtjDetails","","","");
		ServletUtils.renderJson(response, success);
		return null;
	}
	
	@RequestMapping(value="/batchdelXhZqtjDetails")
	public String batchDelUser(HttpServletRequest request, HttpServletResponse response){
		String ids = request.getParameter("ids");
		String[] Ids = ids.split(",");
		boolean isSuccess = xhZqtjDetailsManager.batchDelXhZqtjDetails(Ids);
		
		DwzResult success = null;
		if (isSuccess){
			success = new DwzResult("200","删除成功","rel_listXhZqtjDetails","","","");
		}
		else{
			success = new DwzResult("300","删除失败","rel_listXhZqtjDetails","","","");
		}
		ServletUtils.renderJson(response, success);
		return null;
	}
}

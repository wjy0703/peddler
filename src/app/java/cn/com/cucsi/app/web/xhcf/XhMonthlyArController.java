package cn.com.cucsi.app.web.xhcf;

import java.util.HashMap;
import java.util.List;
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

import cn.com.cucsi.app.entity.baseinfo.Attr;
import cn.com.cucsi.app.entity.baseinfo.City;
import cn.com.cucsi.app.entity.xhcf.XhMonthlyAr;
import cn.com.cucsi.app.service.baseinfo.BaseInfoManager;
import cn.com.cucsi.app.service.xhcf.XhMonthlyArManager;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.web.DwzResult;
import cn.com.cucsi.core.web.ServletUtils;

@Controller
@RequestMapping(value="/xhMonthlyAr")
public class XhMonthlyArController {
	private XhMonthlyArManager xhMonthlyArManager;

	private BaseInfoManager baseInfoManager;
	
	@Autowired
	public void setBaseInfoManager(BaseInfoManager baseInfoManager) {
		this.baseInfoManager = baseInfoManager;
	}
	
	@Autowired
	public void setXhMonthlyArManager(XhMonthlyArManager xhMonthlyArManager) {
		this.xhMonthlyArManager = xhMonthlyArManager;
	}
	
	@RequestMapping(value="/listXhMonthlyAr")
	public String listXhMonthlyAr(HttpServletRequest request, Model model){
		// 处理分页的参数
		Page<XhMonthlyAr> page = new Page<XhMonthlyAr>();
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
		List<City> province = baseInfoManager.getSuggestCity("0");
		model.addAttribute("province", province);
		
		Map<String, Object> filter2 = new HashMap<String, Object>();
		filter2.put("attrType", "3");
		List<Attr> attrList2 = baseInfoManager.getAttrList(filter2);
		request.setAttribute("jkTypeList", attrList2);
		
		xhMonthlyArManager.searchXhMonthlyAr(page, map);
	
		model.addAttribute("page", page);
		model.addAttribute("map", map);
		return "jsgl/xhMonthlyArIndex";
		
	}
	
	@RequestMapping(value="/saveXhMonthlyAr",method=RequestMethod.POST)
	public String saveXhMonthlyAr(@ModelAttribute("xhMonthlyAr") XhMonthlyAr xhMonthlyAr, HttpServletRequest request, HttpServletResponse response){
		
		xhMonthlyArManager.saveXhMonthlyAr(xhMonthlyAr);

		DwzResult success = new DwzResult("200","保存成功","rel_listXhMonthlyAr","","closeCurrent","");
		ServletUtils.renderJson(response, success);
		
		return null;
	}
	
	@RequestMapping(value="/addXhMonthlyAr", method=RequestMethod.GET)
	public ModelAndView add(){
		return new ModelAndView("jsgl/xhMonthlyArInput", "xhMonthlyAr", new XhMonthlyAr());
	}
	
	@RequestMapping(value="/editXhMonthlyAr/{Id}", method=RequestMethod.GET)
	public ModelAndView edit(@PathVariable Long Id){
		XhMonthlyAr xhMonthlyAr = xhMonthlyArManager.getXhMonthlyAr(Id);
		return new ModelAndView("jsgl/xhMonthlyArInput", "xhMonthlyAr", xhMonthlyAr);
	}

	@RequestMapping(value="/delXhMonthlyAr/{Id}")
	public String delUser(@PathVariable Long Id, HttpServletResponse response){
		xhMonthlyArManager.deleteXhMonthlyAr(Id);
		DwzResult success = new DwzResult("200","删除成功","rel_listXhMonthlyAr","","","");
		ServletUtils.renderJson(response, success);
		return null;
	}
	
	@RequestMapping(value="/batchdelXhMonthlyAr")
	public String batchDelUser(HttpServletRequest request, HttpServletResponse response){
		String ids = request.getParameter("ids");
		String[] Ids = ids.split(",");
		boolean isSuccess = xhMonthlyArManager.batchDelXhMonthlyAr(Ids);
		
		DwzResult success = null;
		if (isSuccess){
			success = new DwzResult("200","删除成功","rel_listXhMonthlyAr","","","");
		}
		else{
			success = new DwzResult("300","删除失败","rel_listXhMonthlyAr","","","");
		}
		ServletUtils.renderJson(response, success);
		return null;
	}
}

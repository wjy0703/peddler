package cn.com.cucsi.app.web.xhcf;

import java.util.Date;
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

import cn.com.cucsi.app.entity.xhcf.XhMonthlyDwInfo;
import cn.com.cucsi.app.service.xhcf.XhMonthlyDwInfoManager;
import cn.com.cucsi.app.service.xhcf.XhMonthlyDwManager;
import cn.com.cucsi.app.web.util.CreditHarmonyComputeUtilties;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.web.DwzResult;
import cn.com.cucsi.core.web.ServletUtils;

@Controller
@RequestMapping(value="/xhMonthlyDwInfo")
public class XhMonthlyDwInfoController {
	private XhMonthlyDwInfoManager xhMonthlyDwInfoManager;
	@Autowired
	public void setXhMonthlyDwInfoManager(XhMonthlyDwInfoManager xhMonthlyDwInfoManager) {
		this.xhMonthlyDwInfoManager = xhMonthlyDwInfoManager;
	}
	
	
	private XhMonthlyDwManager xhMonthlyDwManager;
	@Autowired
	public void setXhMonthlyDwManager(XhMonthlyDwManager xhMonthlyDwManager) {
		this.xhMonthlyDwManager = xhMonthlyDwManager;
	}
	
	@RequestMapping(value="/listXhMonthlyDwInfo/{lendNo},{payDate}",method=RequestMethod.GET)
	public String listXhMonthlyDwInfo(@PathVariable String lendNo,@PathVariable String payDate, HttpServletRequest request, Model model){
		// 处理分页的参数
		Page<XhMonthlyDwInfo> page = new Page<XhMonthlyDwInfo>();
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
		
		
		
		
		
		Long[] ids=xhMonthlyDwManager.getXhMonthlyDws(lendNo,CreditHarmonyComputeUtilties.StringToDate(payDate, "yyyy-MM-dd"));
	
		Map<String, Object> map = ServletUtils.getParametersStartingWith2(request, "filter_");		
		map.put("ids",ids);
		xhMonthlyDwInfoManager.searchXhMonthlyDwInfo(page, map);

		model.addAttribute("page", page);
		model.addAttribute("map", map);
		return "jsgl/xhMonthlyDwInfoIndex";
		
	}
	
	@RequestMapping(value="/saveXhMonthlyDwInfo",method=RequestMethod.POST)
	public String saveXhMonthlyDwInfo(@ModelAttribute("xhMonthlyDwInfo") XhMonthlyDwInfo xhMonthlyDwInfo, HttpServletRequest request, HttpServletResponse response){
		
		xhMonthlyDwInfoManager.saveXhMonthlyDwInfo(xhMonthlyDwInfo);

		DwzResult success = new DwzResult("200","保存成功","rel_listXhMonthlyDwInfo","","closeCurrent","");
		ServletUtils.renderJson(response, success);
		
		return null;
	}
	
	@RequestMapping(value="/addXhMonthlyDwInfo", method=RequestMethod.GET)
	public ModelAndView add(){
		return new ModelAndView("folder/xhMonthlyDwInfoInput", "xhMonthlyDwInfo", new XhMonthlyDwInfo());
	}
	
	@RequestMapping(value="/editXhMonthlyDwInfo/{Id}", method=RequestMethod.GET)
	public ModelAndView edit(@PathVariable Long Id){
		XhMonthlyDwInfo xhMonthlyDwInfo = xhMonthlyDwInfoManager.getXhMonthlyDwInfo(Id);
		return new ModelAndView("folder/xhMonthlyDwInfoInput", "xhMonthlyDwInfo", xhMonthlyDwInfo);
	}

	@RequestMapping(value="/delXhMonthlyDwInfo/{Id}")
	public String delUser(@PathVariable Long Id, HttpServletResponse response){
		xhMonthlyDwInfoManager.deleteXhMonthlyDwInfo(Id);
		DwzResult success = new DwzResult("200","删除成功","rel_listXhMonthlyDwInfo","","","");
		ServletUtils.renderJson(response, success);
		return null;
	}
	
	@RequestMapping(value="/batchdelXhMonthlyDwInfo")
	public String batchDelUser(HttpServletRequest request, HttpServletResponse response){
		String ids = request.getParameter("ids");
		String[] Ids = ids.split(",");
		boolean isSuccess = xhMonthlyDwInfoManager.batchDelXhMonthlyDwInfo(Ids);
		
		DwzResult success = null;
		if (isSuccess){
			success = new DwzResult("200","删除成功","rel_listXhMonthlyDwInfo","","","");
		}
		else{
			success = new DwzResult("300","删除失败","rel_listXhMonthlyDwInfo","","","");
		}
		ServletUtils.renderJson(response, success);
		return null;
	}
}

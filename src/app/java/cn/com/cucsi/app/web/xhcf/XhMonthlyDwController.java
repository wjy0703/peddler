package cn.com.cucsi.app.web.xhcf;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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

import cn.com.cucsi.app.entity.xhcf.XhMonthlyDw;
import cn.com.cucsi.app.service.xhcf.XhMonthlyDwManager;
import cn.com.cucsi.app.web.util.CreditHarmonyComputeUtilties;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.web.DwzResult;
import cn.com.cucsi.core.web.ServletUtils;

@Controller
@RequestMapping(value="/xhMonthlyDw")
public class XhMonthlyDwController {
	private XhMonthlyDwManager xhMonthlyDwManager;
	@Autowired
	public void setXhMonthlyDwManager(XhMonthlyDwManager xhMonthlyDwManager) {
		this.xhMonthlyDwManager = xhMonthlyDwManager;
	}
	
	@RequestMapping(value="/listXhMonthlyDw")
	public String listXhMonthlyDw(HttpServletRequest request, Model model){
		// 处理分页的参数
		JdbcPage page = new JdbcPage();
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
		
		
		
		if (map.get("payDate") == null) {
			GregorianCalendar calendar = new GregorianCalendar();

			if (calendar.get(Calendar.DATE) <= 15) {
				map.put("payDate", "" + calendar.get(Calendar.YEAR) + "-"
						+ (calendar.get(Calendar.MONTH) + 1) + "-" + 15);
			} else if (calendar.get(Calendar.MONTH) != 2) {
				map.put("payDate", "" + calendar.get(Calendar.YEAR) + "-"
						+ (calendar.get(Calendar.MONTH) + 1) + "-" + 30);
			} else if (calendar.isLeapYear(calendar.get(Calendar.YEAR))) {
				map.put("payDate", "" + calendar.get(Calendar.YEAR) + "-"
						+ (calendar.get(Calendar.MONTH) + 1) + "-" + 29);
			} else {
				map.put("payDate", "" + calendar.get(Calendar.YEAR) + "-"
						+ calendar.get(Calendar.MONTH) + "-" + 28);
			}
		}
		
		
		if(map.get("payDate")!=null&&!map.get("payDate").equals("")){
			String payDate =(String)map.get("payDate");
			Date dPayDate=CreditHarmonyComputeUtilties.StringToDate(payDate,  "yyyy-MM-dd");
			map.put("payDate", dPayDate);
			
		}
		
		//xhMonthlyDwManager.searchXhMonthlyDw(page, map);
		List<Map<String,Object>> xhMonthlyDwList= xhMonthlyDwManager.searchXhMonthlyDw("queryXhMonthlyDwList", map, page);
		model.addAttribute("xhMonthlyDwList", xhMonthlyDwList);
		model.addAttribute("page", page);
		model.addAttribute("map", map);
		
		//List allPayDates=xhMonthlyDwManager.getAllPayDate();
		//model.addAttribute("allPayDates", allPayDates);
		
		
		
		return "jsgl/xhMonthlyDwIndex";
		
	}
	
	@RequestMapping(value="/listAssetsAccounting")
	public String listAssetsAccounting(HttpServletRequest request, Model model){
		// 处理分页的参数
		JdbcPage page = new JdbcPage();
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
		if(!map.containsKey("tzcp")){
			map.put("tzcp", "81,82");
		}
		
		List<Map<String,Object>> xhMonthlyDwList= xhMonthlyDwManager.listAssetsAccounting("queryAssetsAccountingList", map, page);
		model.addAttribute("xhMonthlyDwList", xhMonthlyDwList);
		model.addAttribute("page", page);
		model.addAttribute("map", map);
		
		//List allPayDates=xhMonthlyDwManager.getAllPayDate();
		//model.addAttribute("allPayDates", allPayDates);
		
		
		
		return "jsgl/listAssetsAccounting";
		
	}
	
	@RequestMapping(value="/listAssetsAccountingInfo")
	public String listAssetsAccountingInfo(HttpServletRequest request, Model model){
		// 处理分页的参数
		JdbcPage page = new JdbcPage();
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
		
		
		if(map.get("payDate")!=null&&!map.get("payDate").equals("")){
			String payDate =(String)map.get("payDate");
			Date dPayDate=CreditHarmonyComputeUtilties.StringToDate(payDate,  "yyyy-MM-dd");
			map.put("payDate", dPayDate);
			
		}
		
		List<Map<String,Object>> xhMonthlyDwList= xhMonthlyDwManager.listAssetsAccountingInfo("queryAssetsAccountingInfoList", map, page);
		model.addAttribute("xhMonthlyDwList", xhMonthlyDwList);
		model.addAttribute("page", page);
		model.addAttribute("map", map);
		
		//List allPayDates=xhMonthlyDwManager.getAllPayDate();
		//model.addAttribute("allPayDates", allPayDates);
		
		
		
		return "jsgl/listAssetsAccountingInfo";
		
	}
	
	@RequestMapping(value="/saveXhMonthlyDw",method=RequestMethod.POST)
	public String saveXhMonthlyDw(@ModelAttribute("xhMonthlyDw") XhMonthlyDw xhMonthlyDw, HttpServletRequest request, HttpServletResponse response){
		
		xhMonthlyDwManager.saveXhMonthlyDw(xhMonthlyDw);

		DwzResult success = new DwzResult("200","保存成功","rel_listXhMonthlyDw","","closeCurrent","");
		ServletUtils.renderJson(response, success);
		
		return null;
	}
	
	@RequestMapping(value="/addXhMonthlyDw", method=RequestMethod.GET)
	public ModelAndView add(){
		return new ModelAndView("jsgl/xhMonthlyDwInput", "xhMonthlyDw", new XhMonthlyDw());
	}
	
	@RequestMapping(value="/editXhMonthlyDw/{Id}", method=RequestMethod.GET)
	public ModelAndView edit(@PathVariable Long Id){
		XhMonthlyDw xhMonthlyDw = xhMonthlyDwManager.getXhMonthlyDw(Id);
		return new ModelAndView("jsgl/xhMonthlyDwInput", "xhMonthlyDw", xhMonthlyDw);
	}

	
}

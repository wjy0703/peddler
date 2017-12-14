package cn.com.cucsi.app.web.xhcf;

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

import cn.com.cucsi.app.entity.baseinfo.Employee;
import cn.com.cucsi.app.entity.xhcf.XhHouseLoanApply;
import cn.com.cucsi.app.entity.xhcf.XhHouseLoanAudit;
import cn.com.cucsi.app.service.baseinfo.BaseInfoManager;
import cn.com.cucsi.app.service.xhcf.XhHouseLoanApplyManager;
import cn.com.cucsi.app.service.xhcf.XhHouseLoanAuditManager;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.web.DwzResult;
import cn.com.cucsi.core.web.ServletUtils;

@Controller
@RequestMapping(value = "/xhHouseLoanAudit")
public class XhHouseLoanAuditController {
	private XhHouseLoanAuditManager xhHouseLoanAuditManager;

	private XhHouseLoanApplyManager xhHouseLoanApplyManager;

	
	private BaseInfoManager baseInfoManager;
	@Autowired
	public void setBaseInfoManager(BaseInfoManager baseInfoManager) {
		this.baseInfoManager = baseInfoManager;
	}
	
	@Autowired
	public void setXhHouseLoanAuditManager(
			XhHouseLoanAuditManager xhHouseLoanAuditManager) {
		this.xhHouseLoanAuditManager = xhHouseLoanAuditManager;
	}

	@Autowired
	public void setXhHouseLoanApplyManager(
			XhHouseLoanApplyManager xhHouseLoanApplyManager) {
		this.xhHouseLoanApplyManager = xhHouseLoanApplyManager;
	}

	@RequestMapping(value = "/listXhHouseLoanApplyByState/{state}")
	public String listXhHouseLoanApplyByState(@PathVariable Integer state,
			HttpServletRequest request, Model model) {
		// 处理分页的参数
		Page<XhHouseLoanApply> page = new Page<XhHouseLoanApply>();
		String pageSize = request.getParameter("numPerPage");
		if (StringUtils.isNotBlank(pageSize)) {
			page.setPageSize(Integer.valueOf(pageSize));
		}
		String pageNo = request.getParameter("pageNum");
		if (StringUtils.isNotBlank(pageNo)) {
			page.setPageNo(Integer.valueOf(pageNo));
		}
		String orderBy = request.getParameter("orderField");
		if (StringUtils.isNotBlank(orderBy)) {
			page.setOrderBy(orderBy);
		}
		String order = request.getParameter("orderDirection");
		if (StringUtils.isNotBlank(order)) {
			page.setOrder(order);
		}

		Map<String, Object> map = ServletUtils.getParametersStartingWith2(
				request, "filter_");
		
		
		
	

		Employee emp = baseInfoManager.getUserEmployee();
		
		if(emp.getName().equals("寇振红")){
			map.put("city", "'北京'");
		}
		if(emp.getName().equals("葛翔")){
			map.put("city", "'长春' ,'哈尔滨'");
		}
//		if(emp.getName().equals("吕思琪")){
//			map.put("city", "长春");
//		}
//		if(emp.getName().equals("赵丽娜")){
//			map.put("city", "哈尔滨");
//		}
		map.put("loanState", String.valueOf(state));
		xhHouseLoanApplyManager.searchXhHouseLoanApply(page, map);

		model.addAttribute("page", page);
		model.addAttribute("map", map);
		if (state == 31)
			return "houseLoan/xhHouseLoanFirstAuditIndex";
		else if (state == 32)
			return "houseLoan/xhHouseLoanSecondAuditIndex";
		else if (state == 33)
			return "houseLoan/xhHouseLoanFirstAuditIndex";
		else
			return null;
	}

	@RequestMapping(value = "/listXhHouseLoanAudit")
	public String listXhHouseLoanAudit(HttpServletRequest request, Model model) {
		// 处理分页的参数
		Page<XhHouseLoanAudit> page = new Page<XhHouseLoanAudit>();
		String pageSize = request.getParameter("numPerPage");
		if (StringUtils.isNotBlank(pageSize)) {
			page.setPageSize(Integer.valueOf(pageSize));
		}
		String pageNo = request.getParameter("pageNum");
		if (StringUtils.isNotBlank(pageNo)) {
			page.setPageNo(Integer.valueOf(pageNo));
		}
		String orderBy = request.getParameter("orderField");
		if (StringUtils.isNotBlank(orderBy)) {
			page.setOrderBy(orderBy);
		}
		String order = request.getParameter("orderDirection");
		if (StringUtils.isNotBlank(order)) {
			page.setOrder(order);
		}

		
	
		Map<String, Object> map = ServletUtils.getParametersStartingWith2(
				request, "filter_");
		Employee emp = baseInfoManager.getUserEmployee();
		
		if(emp.getName().equals("寇振红")){
			map.put("city", "'北京'");
		}
		if(emp.getName().equals("葛翔")){
			map.put("city", "'长春' ,'哈尔滨'");
		}
//		if(emp.getName().equals("吕思琪")){
//			map.put("city", "长春");
//		}
//		if(emp.getName().equals("赵丽娜")){
//			map.put("city", "哈尔滨");
//		}
		xhHouseLoanAuditManager.searchXhHouseLoanAudit(page, map);

		model.addAttribute("page", page);
		model.addAttribute("map", map);
		return "houseLoan/xhHouseLoanAuditIndex";

	}

	/**
	 * 保存初审信息
	 * 
	 * @param xhHouseLoanAudit
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/saveXhHouseLoanAudit", method = RequestMethod.POST)
	public String saveXhHouseLoanAudit(
			@ModelAttribute("xhHouseLoanAudit") XhHouseLoanAudit xhHouseLoanAudit,
			HttpServletRequest request, HttpServletResponse response) {
		String Id = request.getParameter("house_apply_id");
		XhHouseLoanApply xhHouseLoanApply = xhHouseLoanApplyManager
				.getXhHouseLoanApply(Long.parseLong(Id));
		xhHouseLoanAudit.setXhHouseLoanApply(xhHouseLoanApply);
		xhHouseLoanAuditManager.saveXhHouseLoanAudit(xhHouseLoanAudit);

		DwzResult success = new DwzResult("200", "保存成功",
				"rel_listXhHouseLoanAudit", "", "closeCurrent", "");
		ServletUtils.renderJson(response, success);

		return null;
	}

	/**
	 * 保存复审信息
	 * 
	 * @param xhHouseLoanAudit
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/saveXhHouseLoanSecondAudit", method = RequestMethod.POST)
	public String saveXhHouseLoanSecondAudit(
			@ModelAttribute("xhHouseLoanAudit") XhHouseLoanAudit xhHouseLoanAudit,
			HttpServletRequest request, HttpServletResponse response) {
		String Id = request.getParameter("house_apply_id");
		XhHouseLoanApply xhHouseLoanApply = xhHouseLoanApplyManager
				.getXhHouseLoanApply(Long.parseLong(Id));
		xhHouseLoanAudit.setXhHouseLoanApply(xhHouseLoanApply);
		xhHouseLoanAuditManager.saveXhHouseLoanSecondAudit(xhHouseLoanAudit);

		DwzResult success = new DwzResult("200", "保存成功",
				"rel_listXhHouseLoanAudit", "", "closeCurrent", "");
		ServletUtils.renderJson(response, success);

		return null;
	}

	@RequestMapping(value = "/addXhHouseLoanAudit/{Id}", method = RequestMethod.GET)
	public ModelAndView add(@PathVariable Long Id, Model model,
			HttpServletRequest request) {
		XhHouseLoanApply xhHouseLoanApply = xhHouseLoanApplyManager
				.getXhHouseLoanApply(Id);

		model.addAttribute("xhHouseLoanApply", xhHouseLoanApply);
		return new ModelAndView("houseLoan/xhHouseLoanAuditInput",
				"xhHouseLoanAudit", new XhHouseLoanAudit());
	}

	/**
	 * 录入复审意见
	 * 
	 * @param Id
	 * @return
	 */
	@RequestMapping(value = "/xhHouseLoanSecondAuditInput/{Id}", method = RequestMethod.GET)
	public ModelAndView xhHouseLoanSecondAuditInput(@PathVariable Long Id) {
		XhHouseLoanAudit xhHouseLoanAudit = xhHouseLoanAuditManager
				.getXhHouseLoanAuditByApplyId(Id);

		// XhHouseLoanAudit xhHouseLoanAudit = xhHouseLoanAuditManager
		// .getXhHouseLoanAudit(Id);
		return new ModelAndView("houseLoan/xhHouseLoanSecondAuditInput",
				"xhHouseLoanAudit", xhHouseLoanAudit);
	}

	/**
	 * 
	 * @param Id
	 * @return
	 */
	@RequestMapping(value = "/editXhHouseLoanAudit/{Id}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable Long Id) {
		XhHouseLoanAudit xhHouseLoanAudit = xhHouseLoanAuditManager
				.getXhHouseLoanAuditByApplyId(Id);

		// XhHouseLoanAudit xhHouseLoanAudit = xhHouseLoanAuditManager
		// .getXhHouseLoanAudit(Id);
		return new ModelAndView("houseLoan/xhHouseLoanSecondAuditInput",
				"xhHouseLoanAudit", xhHouseLoanAudit);
	}

}

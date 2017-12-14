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
import cn.com.cucsi.app.entity.xhcf.XhHouseLoanAuditInfo;
import cn.com.cucsi.app.service.baseinfo.BaseInfoManager;
import cn.com.cucsi.app.service.xhcf.XhHouseLoanApplyManager;
import cn.com.cucsi.app.service.xhcf.XhHouseLoanAuditInfoManager;
import cn.com.cucsi.app.service.xhcf.XhHouseLoanAuditManager;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.web.DwzResult;
import cn.com.cucsi.core.web.ServletUtils;

@Controller
@RequestMapping(value = "/xhHouseLoanAudit")
public class XhHouseLoanAuditInfoController {
	private XhHouseLoanAuditInfoManager xhHouseLoanAuditInfoManager;

	private BaseInfoManager baseInfoManager;

	@Autowired
	public void setBaseInfoManager(BaseInfoManager baseInfoManager) {
		this.baseInfoManager = baseInfoManager;
	}

	@Autowired
	public void setXhHouseLoanAuditInfoManager(
			XhHouseLoanAuditInfoManager xhHouseLoanAuditInfoManager) {
		this.xhHouseLoanAuditInfoManager = xhHouseLoanAuditInfoManager;
	}

	private XhHouseLoanAuditManager xhHouseLoanAuditManager;

	private XhHouseLoanApplyManager xhHouseLoanApplyManager;

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

	@RequestMapping(value = "/listXhHouseLoanAuditInfo")
	public String listXhHouseLoanAuditInfo(HttpServletRequest request,
			Model model) {
		// 处理分页的参数
		Page<XhHouseLoanAuditInfo> page = new Page<XhHouseLoanAuditInfo>();
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

		if (emp.getName().equals("寇振红")) {
			map.put("city", "'北京'");
		}
		if (emp.getName().equals("葛翔")) {
			map.put("city", "'长春' ,'哈尔滨'");
		}

		xhHouseLoanAuditInfoManager.searchXhHouseLoanAuditInfo(page, map);

		model.addAttribute("page", page);
		model.addAttribute("map", map);
		return "houseLoan/xhHouseLoanAuditInfoIndex";

	}

	@RequestMapping(value = "/saveXhHouseLoanAuditInfo", method = RequestMethod.POST)
	public String saveXhHouseLoanAuditInfo(
			@ModelAttribute("xhHouseLoanAuditInfo") XhHouseLoanAuditInfo xhHouseLoanAuditInfo,
			HttpServletRequest request, HttpServletResponse response) {
		String applyId = request.getParameter("apply_id");
		String auditId = request.getParameter("aduit_id");

		XhHouseLoanApply xhHouseLoanApply = xhHouseLoanApplyManager
				.getXhHouseLoanApply(Long.parseLong(applyId));

		XhHouseLoanAudit xhHouseLoanAudit = xhHouseLoanAuditManager
				.getXhHouseLoanAuditByApplyId(Long.parseLong(applyId));
		xhHouseLoanAuditInfo.setXhHouseLoanApply(xhHouseLoanApply);
		xhHouseLoanAuditInfo.setXhHouseLoanAudit(xhHouseLoanAudit);
		xhHouseLoanApply.setLoanState("50");
		xhHouseLoanAuditInfoManager
				.saveXhHouseLoanAuditInfo(xhHouseLoanAuditInfo);

		DwzResult success = new DwzResult("200", "保存成功",
				"rel_listXhHouseAuditInfo", "", "closeCurrent", "");
		ServletUtils.renderJson(response, success);

		return null;
	}

	@RequestMapping(value = "/addXhHouseLoanAuditInfo/{Id}", method = RequestMethod.GET)
	public ModelAndView add(@PathVariable Long Id, Model model) {

		XhHouseLoanApply xhHouseLoanApply = xhHouseLoanApplyManager
				.getXhHouseLoanApply(Id);

		XhHouseLoanAudit xhHouseLoanAudit = xhHouseLoanAuditManager
				.getXhHouseLoanAuditByApplyId(Id);
		model.addAttribute("xhHouseLoanApply", xhHouseLoanApply);
		model.addAttribute("xhHouseLoanAudit", xhHouseLoanAudit);

		return new ModelAndView("houseLoan/xhHouseLoanAuditInfoInput",
				"xhHouseLoanAuditInfo", new XhHouseLoanAuditInfo());
	}

	@RequestMapping(value = "/editXhHouseLoanAuditInfo/{Id}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable Long Id, Model model) {
		XhHouseLoanAuditInfo xhHouseLoanAuditInfo = xhHouseLoanAuditInfoManager
				.getXhHouseLoanAuditInfo(Id);

		XhHouseLoanApply xhHouseLoanApply = xhHouseLoanAuditInfo
				.getXhHouseLoanApply();
		model.addAttribute("xhHouseLoanApply", xhHouseLoanApply);
		return new ModelAndView("houseLoan/xhHouseLoanAuditInfoInput",
				"xhHouseLoanAuditInfo", xhHouseLoanAuditInfo);
	}

}

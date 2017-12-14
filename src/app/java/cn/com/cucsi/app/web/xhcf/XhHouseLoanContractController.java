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
import cn.com.cucsi.app.entity.xhcf.XhHouseLoanAuditInfo;
import cn.com.cucsi.app.entity.xhcf.XhHouseLoanContract;
import cn.com.cucsi.app.entity.xhcf.XhJkht;
import cn.com.cucsi.app.service.baseinfo.BaseInfoManager;
import cn.com.cucsi.app.service.xhcf.XhHouseLoanApplyManager;
import cn.com.cucsi.app.service.xhcf.XhHouseLoanAuditInfoManager;
import cn.com.cucsi.app.service.xhcf.XhHouseLoanAuditManager;
import cn.com.cucsi.app.service.xhcf.XhHouseLoanContractManager;
import cn.com.cucsi.app.service.xhcf.XhJkhtManager;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.web.DwzResult;
import cn.com.cucsi.core.web.ServletUtils;

@Controller
@RequestMapping(value = "/xhHouseLoanContract")
public class XhHouseLoanContractController {

	private BaseInfoManager baseInfoManager;

	@Autowired
	public void setBaseInfoManager(BaseInfoManager baseInfoManager) {
		this.baseInfoManager = baseInfoManager;
	}

	private XhHouseLoanContractManager xhHouseLoanContractManager;

	private XhJkhtManager jkhtManager;

	@Autowired
	public void setJkhtManager(XhJkhtManager jkhtManager) {
		this.jkhtManager = jkhtManager;
	}

	@Autowired
	public void setXhHouseLoanContractManager(
			XhHouseLoanContractManager xhHouseLoanContractManager) {
		this.xhHouseLoanContractManager = xhHouseLoanContractManager;
	}

	private XhHouseLoanAuditInfoManager xhHouseLoanAuditInfoManager;

	private XhHouseLoanApplyManager xhHouseLoanApplyManager;

	@Autowired
	public void setXhHouseLoanAuditInfoManager(
			XhHouseLoanAuditInfoManager xhHouseLoanAuditInfoManager) {
		this.xhHouseLoanAuditInfoManager = xhHouseLoanAuditInfoManager;
	}

	@Autowired
	public void setXhHouseLoanApplyManager(
			XhHouseLoanApplyManager xhHouseLoanApplyManager) {
		this.xhHouseLoanApplyManager = xhHouseLoanApplyManager;
	}

	@RequestMapping(value = "/listXhHouseLoanApplyByState/{state}")
	public String listXhHouseLoanApplyByState(@PathVariable Long state,
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

		if (emp.getName().equals("寇振红")) {
			map.put("city", "北京");
		}
		if (emp.getName().equals("葛翔")) {
			map.put("city", "长春 ,哈尔滨");
		}

		map.put("loanState", String.valueOf(state));
		xhHouseLoanApplyManager.searchXhHouseLoanApply(page, map);

		model.addAttribute("page", page);
		model.addAttribute("map", map);
		if (state == 50)
			return "houseLoan/xhHouseLoanToContractIndex";

		else
			return null;
	}

	@RequestMapping(value = "/listXhHouseLoanContract")
	public String listXhHouseLoanContract(HttpServletRequest request,
			Model model) {
		// 处理分页的参数
		Page<XhHouseLoanContract> page = new Page<XhHouseLoanContract>();
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

		xhHouseLoanContractManager.searchXhHouseLoanContract(page, map);

		model.addAttribute("page", page);
		model.addAttribute("map", map);
		return "houseLoan/xhHouseLoanContractIndex";

	}

	@RequestMapping(value = "/saveXhHouseLoanContract", method = RequestMethod.POST)
	public String saveXhHouseLoanContract(
			@ModelAttribute("xhHouseLoanContract") XhHouseLoanContract xhHouseLoanContract,
			HttpServletRequest request, HttpServletResponse response) {
		String Id = request.getParameter("apply_id");
		XhHouseLoanApply xhHouseLoanApply = xhHouseLoanApplyManager
				.getXhHouseLoanApply(Long.parseLong(Id));
		XhHouseLoanAuditInfo xhHouseLoanAuditInfo = xhHouseLoanAuditInfoManager
				.getXhHouseLoanAuditInfoByApplyId(Long.parseLong(Id));
		xhHouseLoanContract.setXhHouseLoanApply(xhHouseLoanApply);
		xhHouseLoanContract.setXhHouseLoanAuditInfo(xhHouseLoanAuditInfo);
		String jkhtId = request.getParameter("jkht_id");
		if (null != jkhtId && !"".equals(jkhtId)) {
			XhJkht jkht = jkhtManager.getXhJkht(Long.parseLong(jkhtId));
			xhHouseLoanContract.setJkht(jkht);
		}
		xhHouseLoanContractManager.saveXhHouseLoanContract(xhHouseLoanContract);

		DwzResult success = new DwzResult("200", "保存成功",
				"rel_listXhHouseLoanContract", "", "closeCurrent", "");
		ServletUtils.renderJson(response, success);

		return null;
	}

	@RequestMapping(value = "/addXhHouseLoanContract/{Id}", method = RequestMethod.GET)
	public ModelAndView add(@PathVariable Long Id, Model model) {
		XhHouseLoanApply xhHouseLoanApply = xhHouseLoanApplyManager
				.getXhHouseLoanApply(Id);
		XhHouseLoanAuditInfo xhHouseLoanAuditInfo = xhHouseLoanAuditInfoManager
				.getXhHouseLoanAuditInfoByApplyId(Id);
		model.addAttribute("xhHouseLoanApply", xhHouseLoanApply);
		model.addAttribute("xhHouseLoanAuditInfo", xhHouseLoanAuditInfo);
		return new ModelAndView("houseLoan/xhHouseLoanContractInput",
				"xhHouseLoanContract", new XhHouseLoanContract());
	}

	@RequestMapping(value = "/editXhHouseLoanContract/{Id}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable Long Id) {
		XhHouseLoanContract xhHouseLoanContract = xhHouseLoanContractManager
				.getXhHouseLoanContract(Id);
		return new ModelAndView("houseLoan/xhHouseLoanContractEdit",
				"xhHouseLoanContract", xhHouseLoanContract);
	}

	@RequestMapping(value = "/auditXhHouseLoanContract/{Id}", method = RequestMethod.GET)
	public ModelAndView auditXhHouseLoanContract(@PathVariable Long Id) {
		XhHouseLoanContract xhHouseLoanContract = xhHouseLoanContractManager
				.getXhHouseLoanContract(Id);

		return new ModelAndView("houseLoan/xhHouseLoanContractAuditInput",
				"xhHouseLoanContract", xhHouseLoanContract);
	}

	/**
	 * 合同确认
	 * 
	 * @param Id
	 * @return
	 */
	@RequestMapping(value = "/confirmXhHouseLoanContract/{Id}", method = RequestMethod.POST)
	public String confirm(@PathVariable Long Id, HttpServletRequest request,
			HttpServletResponse response) {
		XhHouseLoanContract xhHouseLoanContract = xhHouseLoanContractManager
				.getXhHouseLoanContract(Id);
		xhHouseLoanContract.setState(Long.parseLong("2"));
		xhHouseLoanContract.getXhHouseLoanApply().setLoanState("60");
		xhHouseLoanContract.getXhHouseLoanApply().getLoanApply().setState("60");
		xhHouseLoanContractManager
				.confirmXhHouseLoanContract(xhHouseLoanContract);

		DwzResult success = new DwzResult("200", "保存成功",
				"rel_listXhHouseLoanContract", "", "closeCurrent", "");
		ServletUtils.renderJson(response, success);

		return null;
	}

}

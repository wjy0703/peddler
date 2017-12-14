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

import cn.com.cucsi.app.entity.baseinfo.Employee;
import cn.com.cucsi.app.entity.xhcf.XhHouseLoanConsult;
import cn.com.cucsi.app.service.baseinfo.BaseInfoManager;
import cn.com.cucsi.app.service.xhcf.XhHouseLoanConsultManager;
import cn.com.cucsi.app.web.util.RequestPageUtils;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.web.DwzResult;
import cn.com.cucsi.core.web.ServletUtils;

@Controller
@RequestMapping(value = "/house")
public class XhHouseLoanConsultController {
	

	private BaseInfoManager baseInfoManager;

	@Autowired
	public void setBaseInfoManager(BaseInfoManager baseInfoManager) {
		this.baseInfoManager = baseInfoManager;
	}
	private XhHouseLoanConsultManager xhHouseLoanConsultManager;
	@Autowired
	public void setXhHouseLoanConsultManager(
			XhHouseLoanConsultManager xhHouseLoanConsultManager) {
		this.xhHouseLoanConsultManager = xhHouseLoanConsultManager;
	}

	@RequestMapping(value = "/listXhHouseLoanConsult")
	public String listXhHouseLoanConsult(HttpServletRequest request, Model model) {
		// 处理分页的参数
/*		Page<XhHouseLoanConsult> page = new Page<XhHouseLoanConsult>();
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
		}*/
		Page<XhHouseLoanConsult> page = (new RequestPageUtils<XhHouseLoanConsult>()).generatePage(request);
		
		Map<String, Object> map = ServletUtils.getParametersStartingWith2(
				request, "filter_");

		Employee emp = baseInfoManager.getUserEmployee();
		String empLevel = emp.getPosition().getPositionLevel();
		if (empLevel.equals("B") || empLevel.equals("C")) {
			map.put("organId", emp.getOrgani().getId());
		} else if (empLevel.equals("E")) {

			map.put("organId", emp.getOrgani().getId());
			map.put("teamManagerId", emp.getId());

		}

		xhHouseLoanConsultManager.searchXhHouseLoanConsult(page, map);

		model.addAttribute("page", page);
		model.addAttribute("map", map);
		return "houseLoan/xhHouseLoanConsultIndex";

	}

	@RequestMapping(value = "/saveXhHouseLoanConsult", method = RequestMethod.POST)
	public String saveXhHouseLoanConsult(
			@ModelAttribute("xhHouseLoanConsult") XhHouseLoanConsult xhHouseLoanConsult,
			HttpServletRequest request, HttpServletResponse response) {

		String teamManagerId = request.getParameter("teamManagerId");
		String customerManagerId = request.getParameter("customerManagerId");
//		String teamManagerId ="1";
//		String customerManagerId = "1";
		String lodgeQueryId = request.getParameter("lodgeQueryId");
		Employee customerManager = baseInfoManager.getEmployee(Long
				.parseLong(customerManagerId));
		Employee teamManager = baseInfoManager.getEmployee(Long
				.parseLong(teamManagerId));
		Employee lodgeQuery = baseInfoManager.getEmployee(Long
				.parseLong(lodgeQueryId));

		xhHouseLoanConsult.setCustomrerManager(customerManager);
		xhHouseLoanConsult.setTeamManager(teamManager);
		xhHouseLoanConsult.setLodgeQuery(lodgeQuery);
		Employee emp = baseInfoManager.getUserEmployee();

		xhHouseLoanConsult.setOrgan(emp.getOrgani());
		xhHouseLoanConsultManager.saveXhHouseLoanConsult(xhHouseLoanConsult);

		DwzResult success = new DwzResult("200", "保存成功",
				"rel_listXhHouseLoanConsult", "", "closeCurrent", "");
		ServletUtils.renderJson(response, success);

		return null;
	}

	@RequestMapping(value = "/addXhHouseLoanConsult", method = RequestMethod.GET)
	public ModelAndView add(Model model) {

		Employee emp = baseInfoManager.getUserEmployee();// 由团队经理录入 咨询信息，
															// 此处自动获取登入职工
		// 获取客户经理列表
		Map<String, Object> filters = new HashMap();
		filters.put("organi.rganiName", emp.getOrgani().getRganiName());
		filters.put("position.positionName", "客户经理");
		Page<Employee> page = baseInfoManager.searchEmployee(
				new Page<Employee>(), filters);
		model.addAttribute("customerManagers", page.getResult());
		// 获取该机构人员列表
		Map<String, Object> filters1 = new HashMap();
		filters1.put("organi.rganiName", emp.getOrgani().getRganiName());

		Page<Employee> page1 = baseInfoManager.searchEmployee(
				new Page<Employee>(), filters1);
		model.addAttribute("lodges", page1.getResult());// 涉诉查询人员列表
		// 团队经理

		model.addAttribute("teamManager", emp);

		model.addAttribute("customerManagers", page.getResult());

		return new ModelAndView("houseLoan/xhHouseLoanConsultInput",
				"xhHouseLoanConsult", new XhHouseLoanConsult());
	}

	@RequestMapping(value = "/editXhHouseLoanConsult/{Id}")
	public ModelAndView edit(@PathVariable Long Id, Model model) {
		XhHouseLoanConsult xhHouseLoanConsult = xhHouseLoanConsultManager
				.getXhHouseLoanConsult(Id);
		/*if(null!=xhHouseLoanConsult.getCustomrerManager()){
			Employee customerManager = baseInfoManager
					.getEmployee(xhHouseLoanConsult.getCustomrerManager().getId());
			model.addAttribute("customerManager", customerManager);
		}
		if(null!=xhHouseLoanConsult
				.getTeamManager()){
			Employee teamManager = baseInfoManager.getEmployee(xhHouseLoanConsult
					.getTeamManager().getId());
			
			model.addAttribute("teamManager", teamManager);
		}
		if(null!=xhHouseLoanConsult
				.getLodgeQuery()){
			Employee lodge = baseInfoManager.getEmployee(xhHouseLoanConsult
					.getLodgeQuery().getId());
			model.addAttribute("lodge", lodge);
		}*/
		
		return new ModelAndView("houseLoan/xhHouseLoanConsultInput",
				"xhHouseLoanConsult", xhHouseLoanConsult);
	}

}

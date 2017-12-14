package cn.com.cucsi.app.web.baseinfo;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.cucsi.app.entity.baseinfo.BasePosition;
import cn.com.cucsi.app.entity.baseinfo.Employee;
import cn.com.cucsi.app.entity.baseinfo.Organi;
import cn.com.cucsi.app.entity.security.User;
import cn.com.cucsi.app.service.baseinfo.BaseInfoManager;
import cn.com.cucsi.app.service.security.AccountManager;
import cn.com.cucsi.app.service.security.OperatorDetails;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils;
import cn.com.cucsi.core.web.DwzResult;
import cn.com.cucsi.core.web.ServletUtils;

@Controller
@RequestMapping(value = "/baseinfo")
public class EmployeeController {

	private Logger logger = Logger.getLogger(EmployeeController.class);

	private BaseInfoManager baseInfoManager;

	private AccountManager accountManager;

	@Autowired
	public void setBaseInfoManager(BaseInfoManager baseInfoManager) {
		this.baseInfoManager = baseInfoManager;
	}

	@Autowired
	public void setAccountManager(AccountManager accountManager) {
		this.accountManager = accountManager;
	}

	@RequestMapping(value = "/suggestemployee")
	public String getEmployees(@RequestParam String inputValue,
			HttpServletResponse response) {
		List<Employee> list = baseInfoManager.getSuggestEmployee(inputValue);
		List<Map<String, Object>> list1 = new LinkedList<Map<String, Object>>();
		for (Employee emp : list) {
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			map.put("id", emp.getId());
			map.put("name", emp.getName());
			String deptname = "";
			if(emp.getOrgani() != null){
				deptname = emp.getOrgani().getRganiName();
			}
			map.put("deptname", deptname);
			list1.add(map);
		}
		logger.info("suggest query is ok!");
		ServletUtils.renderJson(response, list1);
		return null;
	}

	@RequestMapping(value = "/suggestdept")
	public String getDepts(@RequestParam String inputValue,
			HttpServletResponse response) {
		List<Organi> list = baseInfoManager.getSuggestDept(inputValue);
		if ((list != null) && (!list.isEmpty())) {
			List<Map<String, Object>> list1 = new LinkedList<Map<String, Object>>();
			for (Organi dept : list) {
				Map<String, Object> map = new LinkedHashMap<String, Object>();
				map.put("id", dept.getId());
				map.put("name", dept.getRganiName());
				list1.add(map);
			}
			logger.info("suggest query is ok!");
			ServletUtils.renderJson(response, list1);
		}
		return null;
	}
	@RequestMapping(value = "/getdept")
	public String getdept(
	HttpServletResponse response, Model model) {
	return "baseinfo/retdept";
	}
	
	@RequestMapping(value = "/getTreeDept")
	public String getTreeDept(HttpServletResponse response, Model model) {
		model.addAttribute("result",baseInfoManager.buildOrganiByTopId());
		return "baseinfo/treeLookup";
	}
	
	/**
	 * 根据职务查找员工BySongjf
	 * 
	 * @return
	 */
	@RequestMapping(value = "/findEmpByPosition/{Id}")
	public String findEmpByPosition(@PathVariable Long Id,
			HttpServletResponse response,HttpServletRequest request) {
		String name = request.getParameter("employee.name");
		String empname = request.getParameter("empname");
		System.out.println("findEmpByPosition=====name====>"+name + ";empname==>"+empname);
		List<Employee> list = baseInfoManager.findEmpByPosId(Id);
		List<Map<String, Object>> list1 = new LinkedList<Map<String, Object>>();
		for (Employee emp : list) {
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			map.put("id", emp.getId());
			map.put("name", emp.getName());
			map.put("deptname", emp.getOrgani().getRganiName());
			list1.add(map);
		}
		logger.info("suggest findEmpByPosition is ok!");
		ServletUtils.renderJson(response, list1);
		return null;

	}
	
	@RequestMapping(value = "/emplookupByPosition/{Id}")
	public String emplookupByPosition(@PathVariable Long Id,HttpServletRequest request, 
			HttpServletResponse response, Model model) {
		// 处理分页的参数
		Page<Employee> page = new Page<Employee>();
		String pageSize = request.getParameter("numPerPage");
		System.out.println("pageSize=====>"+pageSize);
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

		Map<String, Object> params = ServletUtils.getParametersStartingWith2(
				request, "filter_");
		
		params.put("sts", "0");
		params.put("position_id", Id);
		baseInfoManager.searchEmployee(page, params);
		model.addAttribute("params", params);
		model.addAttribute("page", page);
//		ServletUtils.renderJson(response, page.getResult());
		return "baseinfo/emplookupByPosition";
	}
	
	
	@RequestMapping(value = "/emplookup")
	public String emplookup(HttpServletRequest request, 
			HttpServletResponse response, Model model) {
		// 处理分页的参数
		Page<Employee> page = new Page<Employee>();
		String pageSize = request.getParameter("numPerPage");
		System.out.println("pageSize=====>"+pageSize);
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

		Map<String, Object> params = ServletUtils.getParametersStartingWith2(
				request, "filter_");
		String parentFlag = request.getParameter("parentFlag");
		String lookId = request.getParameter("lookId");
		
		if (lookId != null && !"".equals(lookId)) {
			//查询上一级的人员
			if("1".equals(parentFlag)){
				Organi og = baseInfoManager.gerOrgani(Long.parseLong(lookId));
				System.out.println("og.getParentId()=====>"+og.getParentId());
				if(null != og && !"".equals(og.getParentId())){
					params.put("parentId", og.getParentId());
					params.put("lookId", lookId);
					params.put("parentFlag", parentFlag);
					model.addAttribute("parentFlag", parentFlag);
				}else{
					params.put("lookId", lookId);
				}
			}else{
				// StringBuffer data = new StringBuffer();
				// baseInfoManager.getEmployeeOa(data, Long.valueOf(lookId));
				// data.deleteCharAt(data.lastIndexOf(","));
				// params.put("lookId", data.toString());
				params.put("lookId", lookId);
				params.put("code", "0007");
//				params.put("sts", "0");
			}
		}
		params.put("sts", "0");
		String code = request.getParameter("code");
		String code2 = request.getParameter("code2");
		if (code != null && !"".equals(code)) {
			params.put("code", code);
			model.addAttribute("code", code);
		}
		if (code2 != null && !"".equals(code2)) {
			params.put("code2", code2);
			model.addAttribute("code2", code2);
		}
		baseInfoManager.searchEmployee(page, params);
		model.addAttribute("params", params);
		model.addAttribute("page", page);
		model.addAttribute("lookId", lookId);
//		ServletUtils.renderJson(response, page.getResult());
		return "baseinfo/emplookup";
	}
	
	@RequestMapping(value = "/emplookAll")
	public String emplookAll(HttpServletRequest request, 
			HttpServletResponse response, Model model) {
		// 处理分页的参数
		Page<Employee> page = new Page<Employee>();
		String pageSize = request.getParameter("numPerPage");
		System.out.println("pageSize=====>"+pageSize);
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

		Map<String, Object> params = ServletUtils.getParametersStartingWith2(
				request, "filter_");
		
		baseInfoManager.searchEmployee(page, params);
		model.addAttribute("params", params);
		model.addAttribute("page", page);
		return "baseinfo/emplookup";
	}
	
	
	@RequestMapping(value = "/emplookup2")
	public String emplookup2(HttpServletRequest request, 
			HttpServletResponse response, Model model) {
		// 处理分页的参数
		Page<Employee> page = new Page<Employee>();
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
		
		Map<String, Object> params = ServletUtils.getParametersStartingWith2(
				request, "filter_");
		String lookId = request.getParameter("lookId");
		if (lookId != null && !"".equals(lookId)) {
			params.put("lookId", lookId);
			params.put("code", "0007");
			params.put("sts", "0");
		}
		
		baseInfoManager.searchEmployee(page, params);
		
		model.addAttribute("page", page);
		model.addAttribute("lookId", lookId);
		if(null != page.getResult() && page.getResult().size()>0){
			List<Map<String, Object>> list1 = new LinkedList<Map<String, Object>>();
			for (Employee emp : page.getResult()) {
				Map<String, Object> map = new LinkedHashMap<String, Object>();
				map.put("id", emp.getId());
				map.put("name", emp.getName());
				map.put("deptname", emp.getOrgani().getRganiName());
				list1.add(map);
			}
			ServletUtils.renderJson(response, list1);
		}
		return null;
	}

	@RequestMapping(value = "/listemployee")
	public String list(HttpServletRequest request, Model model) {
		logger.debug("dd");
		// 处理分页的参数
		Page<Employee> page = new Page<Employee>();
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

		Map<String, Object> params = ServletUtils.getParametersStartingWith2(
				request, "filter_");

		baseInfoManager.searchEmployee(page, params);
		
		logger.info("suggest query is ok!");
		model.addAttribute("organiId", request.getParameter("filter_organi.id"));
	    model.addAttribute("organiName", request.getParameter("filter_organi.name"));
		model.addAttribute("page", page);
		model.addAttribute("map", params);
		model.addAttribute("position", baseInfoManager.getSuggestPosition());
		return "baseinfo/employee";
	}

	@RequestMapping(value = "/saveemployee", method = RequestMethod.POST)
	public String save(@ModelAttribute("employee") Employee employee,
			HttpServletRequest request, HttpServletResponse response) {
		String code = "300";
		String msg = "保存失败，员工已存在！";
		boolean res = baseInfoManager.saveEmployee(employee);

		if(res){
			code = "200";
			msg = "保存成功";
		}
		
		DwzResult success = new DwzResult(code, msg, "rel_listemployee","", "", "");
		ServletUtils.renderJson(response, success);

		return null;
	}

	@RequestMapping(value = "/addemployee", method = RequestMethod.GET)
	public ModelAndView add() {
		return new ModelAndView("baseinfo/employee-input", "employee",
				new Employee());
	}

	@RequestMapping(value = "/editemployee/{Id}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable Long Id) {
		Employee employee = baseInfoManager.getEmployee(Id);
		return new ModelAndView("baseinfo/employee-input", "employee", employee);
	}

	@RequestMapping(value = "/editLoginUser", method = RequestMethod.GET)
	public String editLoginUser(Model model, HttpServletResponse response) {
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		User user = accountManager.getUser(operator.getUserId());
		user.getEmployee();
		if (user.getEmployee() != null) {
			model.addAttribute("employee", user.getEmployee());
			return "baseinfo/employee-input1";
		} else {
			DwzResult success = new DwzResult("300", "删除成功", "", "", "", "");
			ServletUtils.renderJson(response, success);
			return null;
		}
	}

	@RequestMapping(value = "/delemployee/{Id}")
	public String delUser(@PathVariable Long Id, HttpServletResponse response) {
		baseInfoManager.deleteEmployee(Id);
		DwzResult success = new DwzResult("200", "删除成功", "rel_listemployee",
				"", "", "");
		ServletUtils.renderJson(response, success);
		return null;
	}

	@RequestMapping(value = "/getSuggestPosition")
	public String getSuggestPosition(HttpServletResponse response) {
		List<BasePosition> positions = baseInfoManager.getSuggestPosition();
		logger.info("suggest query is ok!");
		ServletUtils.renderJson(response, positions);
		return null;
	}
	
	@RequestMapping(value = "/openSuggestPosition")
	public String openSuggestPosition(HttpServletResponse response, Model model) {
		List<BasePosition> positions = baseInfoManager.getSuggestPosition();
		logger.info("suggest query is ok!");
		model.addAttribute("positions", positions);
		return "baseinfo/getPosition";
	}
	
	@RequestMapping(value="/initCharts/{Id}", method=RequestMethod.GET)
	public String initAnalyInfo(@PathVariable Long Id, Model model){
		model.addAttribute("analyName", "DEMO");
		model.addAttribute("analyId", "DEMO");
		String jsonData = "{name: '话务量',data: [['黑龙江',   40.0],['北京',   20.0],['上海',   30.0]]},{categories:['a', 'b']}";
		model.addAttribute("data", jsonData);
		return "baseinfo/analyInfo";
	}
	
	@RequestMapping(value="/getDemoInfo")
	@ResponseBody
	public String getDemoInfo(HttpServletRequest request, HttpServletResponse response){
		//String jsonData = "{name: '话务量',data: [1, 2, 3, 4, 5, 6]},{name: '话务量',data: [7, 8, 9, 10, 11, 12]},{categories:['a', 'b', 'c', 'd', 'e', 'f']}";
		String jsonData = "{name: '话务量',data: [['黑龙江',   40.0],['北京',   20.0],['上海',   30.0]]},{categories:['a', 'b']}";
		try {
			response.getWriter().write(jsonData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}

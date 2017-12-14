package cn.com.cucsi.app.web.baseinfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.tool.hbm2x.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.cucsi.app.entity.security.User;
import cn.com.cucsi.app.service.baseinfo.EmployeeRelatedManager;
import cn.com.cucsi.app.service.baseinfo.UserManager;
import cn.com.cucsi.app.service.security.OperatorDetails;
import cn.com.cucsi.app.web.util.EmployeeLeveCodeEnum;
import cn.com.cucsi.app.web.util.EmployeeLevelConverter;
import cn.com.cucsi.app.web.util.JdbcPageUtils;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils;
import cn.com.cucsi.core.web.ServletUtils;

@Controller
@RequestMapping(value = "/employeefind")
public class EmployeePositionController {
	
	@Autowired
	EmployeeRelatedManager employeeRelatedManager ;
	
	@Autowired
	UserManager userManager;
	
	/**
	 * 通过职务查找本部门及子部门的员工
	 * 
	 * @return
	 */
	
	@RequestMapping(value = "/byposition/{level}")
	public String findEmpByPosition(@PathVariable String level,HttpServletResponse response,HttpServletRequest request,Model model) {
		return prepareViewData(level,request,model);
	}

	private List<String> getLevelList(String level) {
		return EmployeeLevelConverter.getLevelList(level);
	}
	
	@RequestMapping(value = "/byposition/{clazz}/{level}")
	public String findEmpByPositionWithClass(@PathVariable String level,@PathVariable String clazz,HttpServletResponse response,HttpServletRequest request,Model model) {
		if(StringUtils.isNotEmpty(clazz)){
			model.addAttribute("clazz",clazz);
		}
		return prepareViewData(level,request,model);
		
	}
	
	private String prepareViewData(String level, HttpServletRequest request,Model model) {
		JdbcPage page = JdbcPageUtils.generatePage(request);
		/*OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		User u = userManager.queryById(operator.getUserId());
		long orgId = u.getEmployee().getOrgani().getId();*/
		List<String> levels = getLevelList(level);
		Map<String, Object> params = ServletUtils.getParametersStartingWith2(request, "filter_");;
		List<Map<String, Object>> items = employeeRelatedManager.getEmployeesByLevelWithCurrentOrgid(levels, page,params);
		model.addAttribute("items",items);
		model.addAttribute("level",level);
		model.addAttribute("page",page);
		return "baseinfo/employeeLookupByPosition";
	}

}

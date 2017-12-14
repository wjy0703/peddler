package cn.com.cucsi.vechicle.web;

import java.io.IOException;
import java.net.URLDecoder;
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

import cn.com.cucsi.app.entity.baseinfo.Employee;
import cn.com.cucsi.app.entity.xhcf.XhJkjjlxr;
import cn.com.cucsi.app.service.baseinfo.BaseInfoManager;
import cn.com.cucsi.app.service.security.OperatorDetails;
import cn.com.cucsi.app.web.util.JdbcPageUtils;
import cn.com.cucsi.app2.entity.xhcf.XhJksqOffice;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils;
import cn.com.cucsi.core.web.DwzResult;
import cn.com.cucsi.core.web.ServletUtils;
import cn.com.cucsi.vechicle.entity.xhcf.XhCarLoanUser;
import cn.com.cucsi.vechicle.entity.xhcf.XhCarLxr;
import cn.com.cucsi.vechicle.entity.xhcf.XhCarUserOffice;
import cn.com.cucsi.vechicle.service.XhCarLoanUserManager;
import cn.com.cucsi.vechicle.service.XhCarLxrManager;

@Controller
@RequestMapping(value="/xhCarLoanUser")
public class XhCarLoanUserController {
	private XhCarLoanUserManager xhCarLoanUserManager;
	@Autowired
	public void setXhCarLoanUserManager(XhCarLoanUserManager xhCarLoanUserManager) {
		this.xhCarLoanUserManager = xhCarLoanUserManager;
	}
	
	private XhCarLxrManager xhCarLxrManager;
	@Autowired
	public void setXhCarLxrManager(XhCarLxrManager xhCarLxrManager) {
		this.xhCarLxrManager = xhCarLxrManager;
	}
	
	private BaseInfoManager baseInfoManager;
	
	@Autowired
	public void setBaseInfoManager(BaseInfoManager baseInfoManager) {
		this.baseInfoManager = baseInfoManager;
	}

	@RequestMapping(value="/listXhCarLoanUser")
	public String listXhCarLoanUser(HttpServletRequest request, Model model){
		// 处理分页的参数
		JdbcPage page = JdbcPageUtils.generatePage(request);
		
		Map<String, Object> map = ServletUtils.getParametersStartingWith2(request, "filter_");	
		//得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		//过滤查询内容，所需条件   ----开始
		Employee emp = baseInfoManager.getUserEmployee();
		String loginName = operator.getCtiCode();
		map.put("loginName", loginName);
		map.put("emp", emp);
		//过滤查询内容，所需条件   ----结束
		//xhCarLoanUserManager.searchXhCarLoanUser(page, map);
		List<Map<String, Object>> listCarLoanUser = xhCarLoanUserManager.searchXhCarLoanUser("queryXhCarLoanUserList", map, page);
	
		model.addAttribute("listCarLoanUser", listCarLoanUser);
		model.addAttribute("page", page);
		model.addAttribute("map", map);
		return "xhCarLoan/xhCarLoanUserIndex";
		
	}
	
	@RequestMapping(value="/saveXhCarLoanUser",method=RequestMethod.POST)
	public void saveXhCarLoanUser(@ModelAttribute("xhCarLoanUser") XhCarLoanUser xhCarLoanUser, HttpServletRequest request, HttpServletResponse response){
		
		 List<XhCarLxr> lxrs=xhCarLoanUser.getXhCarLxr();
	        if(lxrs!=null){
	            for(int index=0;index<lxrs.size();index++){
	            	XhCarLxr lxr=lxrs.get(index);
	                lxr.setXhCarLoanUser(xhCarLoanUser);
	            }
	        }
	        List<XhCarUserOffice> offices=xhCarLoanUser.getXhCarUserOffice();
	        if(offices != null)
	            for(int index=0;index<offices.size();index++){
	            	XhCarUserOffice Office=offices.get(index);
	                Office.setXhCarLoanUser(xhCarLoanUser);
	                
	            }
	        String opt = request.getParameter("opt");
	        xhCarLoanUser.setState(opt);
	        Employee emp = baseInfoManager.getEmployee(xhCarLoanUser.getEmployeeCrm().getId());
	        xhCarLoanUser.setOrgani(emp.getOrgani());
	        String dept = request.getParameter("employeeCrm.deptname");
	        xhCarLoanUser.setKftd(dept);
		xhCarLoanUserManager.saveXhCarLoanUser(xhCarLoanUser);
        
		
		DwzResult success = new DwzResult("200","保存成功","rel_listXhCarLoanUser","","closeCurrent","");
		ServletUtils.renderJson(response, success);
		
	}
	
	@RequestMapping(value="/addXhCarLoanUser", method=RequestMethod.GET)
	public ModelAndView add(Model model){
		Employee emp = baseInfoManager.getUserEmployee();
		XhCarLoanUser xhCarLoanUser  = new XhCarLoanUser();
		xhCarLoanUser.setEmployeeService(emp);
		//车借客服
		model.addAttribute("staffname", xhCarLoanUser.getEmployeeService().getName());
		model.addAttribute("staffid", xhCarLoanUser.getEmployeeService().getId());
		model.addAttribute("employee", emp);
		return new ModelAndView("xhCarLoan/xhCarLoanUserInput", "xhCarLoanUser", xhCarLoanUser);
	}
	
	//--------------------------------------验证       开始-----------------------------------
	
		@RequestMapping(value="/chkValue")
		public String chkValue(HttpServletRequest request, HttpServletResponse response) {
			
			try {
				String propertyName = request.getParameter("propertyName");
				String newValue = URLDecoder.decode(request.getParameter(propertyName), "UTF-8");
				String oldValue = URLDecoder.decode(request.getParameter("oldValue"), "UTF-8");
				String errmes = URLDecoder.decode(request.getParameter("errmes"), "UTF-8");
				response.setContentType("text/html;charset=utf-8");
				System.out.println("propertyName===>" + propertyName + ";newValue==>" + newValue + ";oldValue==>" +oldValue + ";errmes==>" +errmes);
				if (xhCarLoanUserManager.isCheckUnique(propertyName, newValue, oldValue)) {
					//ServletUtils.renderText(response, "true");
				} else {
					//ServletUtils.render(response, "false", "hello");
					ServletUtils.renderText(response, errmes + "已经存在");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		//--------------------------------------验证        结束-----------------------------------
	
	@RequestMapping(value="/editXhCarLoanUser/{Id}", method=RequestMethod.GET)
	public ModelAndView edit(@PathVariable Long Id,Model model){
		XhCarLoanUser xhCarLoanUser = xhCarLoanUserManager.getXhCarLoanUser(Id);
		model.addAttribute("staffname", xhCarLoanUser.getEmployeeService().getName());
		model.addAttribute("staffid", xhCarLoanUser.getEmployeeService().getId());
		Employee emp = baseInfoManager.getUserEmployee();
		xhCarLoanUser.setEmployeeService(emp);
		model.addAttribute("employee", emp);
		return new ModelAndView("xhCarLoan/xhCarLoanUserInput", "xhCarLoanUser", xhCarLoanUser);
	}
	
	
	/**
	 * 查看用户基本信息
	 *
	 * @param Id
	 * @param model
	 * @return
	 * @author xjs
	 * @date 2013-10-14 下午2:09:45
	 */
	@RequestMapping(value="/editXhCarLoanUserLook/{Id}", method=RequestMethod.GET)
    public ModelAndView  editLook(@PathVariable Long Id,Model model){
	    model.addAttribute("look","1");
	    XhCarLoanUser xhCarLoanUser = xhCarLoanUserManager.getXhCarLoanUser(Id);
	    model.addAttribute("staffname", xhCarLoanUser.getEmployeeService().getName());
		model.addAttribute("staffid", xhCarLoanUser.getEmployeeService().getId());
		Employee emp = baseInfoManager.getUserEmployee();
		xhCarLoanUser.setEmployeeService(emp);
		model.addAttribute("employee", emp);
		return new ModelAndView("xhCarLoan/xhCarLoanUserInput", "xhCarLoanUser", xhCarLoanUser);
    }

	@RequestMapping(value="/delXhCarLoanUser/{Id}")
	public String delUser(@PathVariable Long Id, HttpServletResponse response){
		xhCarLoanUserManager.deleteXhCarLoanUser(Id);
		DwzResult success = new DwzResult("200","删除成功","rel_listXhCarLoanUser","","","");
		ServletUtils.renderJson(response, success);
		return null;
	}
	
	@RequestMapping(value="/batchdelXhCarLoanUser")
	public String batchDelUser(HttpServletRequest request, HttpServletResponse response){
		String ids = request.getParameter("ids");
		String[] Ids = ids.split(",");
		boolean isSuccess = xhCarLoanUserManager.batchDelXhCarLoanUser(Ids);
		
		DwzResult success = null;
		if (isSuccess){
			success = new DwzResult("200","删除成功","rel_listXhCarLoanUser","","","");
		}
		else{
			success = new DwzResult("300","删除失败","rel_listXhCarLoanUser","","","");
		}
		ServletUtils.renderJson(response, success);
		return null;
	}
	
	@RequestMapping(value = "/oracleDeleteLxr/{Id}", method = RequestMethod.POST)
    public void saveLxrChange(@PathVariable("Id") Long Id, HttpServletRequest request, HttpServletResponse response) {
		xhCarLxrManager.deleteXhCarLxr(Id);
        ServletUtils.renderJson(response, "1");
        
    }
}

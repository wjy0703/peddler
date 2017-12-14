package cn.com.cucsi.vechicle.web;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.h2.java.lang.Integer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.com.cucsi.app.entity.baseinfo.Employee;
import cn.com.cucsi.app.entity.xhcf.XhJkht;
import cn.com.cucsi.app.service.baseinfo.BaseInfoManager;
import cn.com.cucsi.app.service.security.OperatorDetails;
import cn.com.cucsi.app.web.util.HibernateAwareBeanUtilsBean;
import cn.com.cucsi.app.web.util.JdbcPageUtils;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils;
import cn.com.cucsi.core.web.DwzResult;
import cn.com.cucsi.core.web.ServletUtils;
import cn.com.cucsi.vechicle.entity.xhcf.XhCarAudit;
import cn.com.cucsi.vechicle.entity.xhcf.XhCarLoanApply;
import cn.com.cucsi.vechicle.entity.xhcf.XhCarLoanContract;
import cn.com.cucsi.vechicle.entity.xhcf.XhCarLoanUser;
import cn.com.cucsi.vechicle.service.XhCarAuditManager;
import cn.com.cucsi.vechicle.service.XhCarLoanApplyComplementManager;
import cn.com.cucsi.vechicle.service.XhCarLoanApplyManager;
import cn.com.cucsi.vechicle.service.XhCarLoanContractManager;
import cn.com.cucsi.vechicle.service.XhCarLoanUserManager;
import cn.com.cucsi.vechicle.service.XhCarMessageManager;
import cn.com.cucsi.vechicle.util.CarOperation;
import cn.com.cucsi.vechicle.util.CarStateExchange;

@Controller
@RequestMapping(value="/xhCarLoanApply")
public class XhCarLoanApplyController {
	
    private Logger logger = LoggerFactory.getLogger(XhCarLoanApplyController.class);
	
	@Autowired
	private XhCarLoanApplyManager xhCarLoanApplyManager;
	
	@Autowired 
	private XhCarLoanUserManager xhCarLoanUserManager;
	
	@Autowired 
	private XhCarLoanContractManager xhCarLoanContractManager;
	
	@Autowired
	private XhCarMessageManager xhCarMessageManager;
	
	@Autowired
	private XhCarLoanApplyComplementManager xhCarLoanApplyComplementManager;
	
	@Autowired
	private BaseInfoManager baseInfoManager;
	
//	@Autowired
//	private XhCarLxrManager xhCarLxrManager;
	
	@Autowired
	private XhCarAuditManager xhCarAuditManager;
	
	@RequestMapping(value="/listXhCarLoanApply")
	public String listXhCarLoanApply(HttpServletRequest request, Model model){
		// 处理分页的参数
		//Page<XhCarLoanApply> page = new RequestPageUtils<XhCarLoanApply>().generatePage(request);
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
		//xhCarLoanApplyManager.searchXhCarLoanApply("queryXhCarLoanApplyList",page, map);
		List<Map<String,Object>> xhCarLoanApplies = xhCarLoanApplyManager.searchXhCarLoanApply("queryXhCarLoanApplyList", map,page);
		for(int index = 0 ; index < xhCarLoanApplies.size() ; index++){
		    Map<String,Object> item = xhCarLoanApplies.get(index);
		    item.put("editable", CarStateExchange.isEditable(item));
		    item.put("agreement", CarStateExchange.isAgreement(item));
		    item.put("refuse", CarStateExchange.isRefuse(item));
		}
		
		model.addAttribute("xhCarLoanApply", xhCarLoanApplies);
		model.addAttribute("page", page);
		model.addAttribute("map", map);
		return "xhCarLoan/apply/xhCarLoanApplyIndex";
		
	}
	
	@RequestMapping(value="/listXhCarLoanApplyAudit/{type}")
	public String listXhCarLoanApplyAudit(HttpServletRequest request, Model model,@PathVariable String type){
	        // 处理分页的参数
	        //Page<XhCarLoanApply> page = new RequestPageUtils<XhCarLoanApply>().generatePage(request);
	        JdbcPage page = JdbcPageUtils.generatePage(request);
	        Map<String, Object> map = ServletUtils.getParametersStartingWith2(request, "filter_");      
	        //得到当前登录用户
	        OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
	        //xhCarLoanApplyManager.searchXhCarLoanApply("queryXhCarLoanApplyList",page, map);
	        CarOperation operation ;
	        if("first".equals(type)){//初审
	             operation = CarOperation.FIRST_AUDIT;
	           //过滤查询内容，所需条件   ----开始
	             Employee emp = baseInfoManager.getUserEmployee();
	 	        String loginName = operator.getCtiCode();
	 	       map.put("loginName", loginName);
		        map.put("emp", emp);
	 	       //过滤查询内容，所需条件   ----结束
	        }else if("second".equals(type)){
	             operation = CarOperation.SECOND_AUDIT;
	        }else{
	             operation = CarOperation.FINAL_AUDIT;
	        }
	        map.put("inStates",CarStateExchange.getStateList(operation));
	        List<Map<String,Object>> xhCarLoanApply = xhCarLoanApplyManager.searchXhCarLoanApplyAudit(map, page);
	        model.addAttribute("xhCarLoanApply", xhCarLoanApply);
	        model.addAttribute("page", page);
	        model.addAttribute("map", map);
	        model.addAttribute("type",type);
	        return "xhCarLoan/apply/audit/xhCarLoanApplyList";
	        
	}
	
	
	@RequestMapping(value="/listXhCarLoanApplyAuditOver/{type}")
	public String listXhCarLoanApplyAuditOver(HttpServletRequest request, Model model,@PathVariable String type){
	        // 处理分页的参数
	        //Page<XhCarLoanApply> page = new RequestPageUtils<XhCarLoanApply>().generatePage(request);
	        JdbcPage page = JdbcPageUtils.generatePage(request);
	        Map<String, Object> map = ServletUtils.getParametersStartingWith2(request, "filter_");      
	        //得到当前登录用户
	        OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
	        //过滤查询内容，所需条件   ----开始
	        //过滤查询内容，所需条件   ----结束
	        //xhCarLoanApplyManager.searchXhCarLoanApply("queryXhCarLoanApplyList",page, map);
	        Long state ;
	        if("first".equals(type)){//初审
	             state = Long.parseLong("1");
	           //过滤查询内容，所需条件   ----开始
	             Employee emp = baseInfoManager.getUserEmployee();
	 	        String loginName = operator.getCtiCode();
	 	       map.put("loginName", loginName);
		        map.put("emp", emp);
	 	       //过滤查询内容，所需条件   ----结束
	        }else if("second".equals(type)){
	        	state = Long.parseLong("2");
	        }else{
	        	state = Long.parseLong("100");
	        }
	        map.put("inStates",state);
	        List<Map<String,Object>> xhCarLoanApply = xhCarLoanApplyManager.searchXhCarLoanApplyAuditOver(map, page);
	        model.addAttribute("xhCarLoanApply", xhCarLoanApply);
	        model.addAttribute("page", page);
	        model.addAttribute("map", map);
	        model.addAttribute("type",type);
	        return "xhCarLoan/apply/audit/xhCarLoanApplyOverList";
	        
	}
	
	
	
	@RequestMapping(value="/saveXhCarLoanApply",method=RequestMethod.POST)
	public void saveXhCarLoanApply(@ModelAttribute("xhCarLoanApply") XhCarLoanApply xhCarLoanApply, HttpServletRequest request, HttpServletResponse response){
	  //取得操作类型,暂存还是提交
        String optionStr = StringUtils.hasText(request.getParameter("opt")) ? request.getParameter("opt"):"0";
        CarOperation  option = CarOperation.fromStr(optionStr);//0应该改成optionStr
        
        Employee emp = baseInfoManager.getEmployee(xhCarLoanApply.getEmployeeCrm().getId());
        //设置zu
        xhCarLoanApply.setOrgani(emp.getOrgani());
//        Long customerLeaderId = StringUtils.hasText(request.getParameter("employeeCrm.id")) ? Long.parseLong(request.getParameter("employeeCrm.id")):null;
//        Long teamLeaderId = StringUtils.hasText(request.getParameter("employeeCca.id")) ? Long.parseLong(request.getParameter("employeeCca.id")):null;
//        xhCarLoanApply.setCustomerLeaderId(customerLeaderId);
//        xhCarLoanApply.setTeamLeaderId(teamLeaderId);        
        xhCarLoanApplyManager.saveApply(xhCarLoanApply,option);
        DwzResult success = new DwzResult("200","保存成功","rel_listXhCarLoanApply","","closeCurrent","");
        ServletUtils.renderJson(response, success);
	}
	
	@RequestMapping(value="/addOrEditXhCarLoanApply/{userId}", method=RequestMethod.GET)
	public String addOrEdit(@PathVariable Long userId,Model model){
		XhCarLoanApply apply = new XhCarLoanApply();
		XhCarLoanUser user = xhCarLoanUserManager.getXhCarLoanUser(userId);
		
		//取车借客服
		model.addAttribute("staffname",user.getEmployeeService().getName());
		model.addAttribute("staffid", user.getEmployeeService().getId());
		
		apply.setCrmprovince(user.getCrmprovince());
		apply.setCrmcity(user.getCrmcity());
		apply.setCrmarea(user.getCrmarea());
		apply.setKnownWay(user.getKnownWay());
		apply.setPersonLawCase(user.getPersonLawCase());
//		String teamLeaderName = "";
//	    String customerLeaderName = "";
//	    String teamOrgName = "";
	    if(user.getEmployeeCca() != null){
	    	apply.setEmployeeCca(user.getEmployeeCca());
//	        apply.setTeamLeaderId(user.getEmployeeCca().getId());  //团队经理
//	        teamLeaderName =  baseInfoManager.getEmployee(user.getEmployeeCca().getId()).getName();
	    }
	    if(user.getEmployeeCrm() != null){
//	        Employee employee = baseInfoManager.getEmployee(user.getEmployeeCrm().getId());
	        apply.setEmployeeCrm(user.getEmployeeCrm());
//	        apply.setCustomerLeaderId(user.getEmployeeCrm().getId());  //客户经理
//	        customerLeaderName = employee.getName();
//	        teamOrgName = employee.getDept()!=null ? employee.getDept().getName():"";	        
	    }
		//apply.setEmployeeServiceStaff(Long.toString(user.getEmployeeServiceStaff()));
	    Employee emp = baseInfoManager.getUserEmployee();
	    model.addAttribute("employee", emp);
		apply.setEmployeeService(emp);
		apply.setXhCarLoanUser(user);
		
		model.addAttribute("userId", userId);
		model.addAttribute("xhCarLoanUser", user);
		model.addAttribute("xhCarLoanApply", apply);
		
//		model.addAttribute("teamLeaderName",teamLeaderName);
//		model.addAttribute("teamOrgName",teamOrgName);
//		model.addAttribute("customerLeaderName",customerLeaderName);
		
		return "xhCarLoan/apply/xhCarLoanApplyInput";
		
	}
	
	
	@RequestMapping(value="/addXhCarLoanApply/{userId}", method=RequestMethod.GET)
	public String add(@PathVariable Long userId,HttpServletRequest request,Model model){
		
		//**如果用户包含申请，转移到相应的列表界面
		JdbcPage page = JdbcPageUtils.generatePage(request);
		Map<String, Object> map = ServletUtils.getParametersStartingWith2(request, "filter_");	
		map.put("carUserId", userId);
		model.addAttribute("userId", userId);
		List<Map<String,Object>> xhCarLoanApply = xhCarLoanApplyManager.searchXhCarLoanApply("queryXhCarLoanApplyList", map,page);
		if(xhCarLoanApply.size()>0){
			for(int index = 0 ; index < xhCarLoanApply.size() ; index++){
			    Map<String,Object> item = xhCarLoanApply.get(index);
			    item.put("editable", CarStateExchange.isEditable(item));
			    
			}
            model.addAttribute("xhCarLoanApplyHas",xhCarLoanApply);
            return "xhCarLoan/apply/xhCarLoanApplyDecide";
        }
		//进入新申请页面
		return addOrEdit(userId,model);	
		
	}
	
	/**
	 * 编辑申请
	 *
	 * @param Id
	 * @param model
	 * @return
	 * @author xjs
	 * @date 2013-10-14 下午2:10:07
	 */
	
	@RequestMapping(value="/editXhCarLoanApply/{Id}", method=RequestMethod.GET)
	public String  edit(@PathVariable Long Id,Model model){
		XhCarLoanApply xhCarLoanApply = xhCarLoanApplyManager.getXhCarLoanApply(Id);
		model.addAttribute("staffname",xhCarLoanApply.getXhCarLoanUser().getEmployeeService().getName());
		model.addAttribute("staffid", xhCarLoanApply.getXhCarLoanUser().getEmployeeService().getId());
		Employee emp = baseInfoManager.getUserEmployee();
		xhCarLoanApply.setEmployeeService(emp);
		String teamLeaderName = "";
        String customerLeaderName = "";
        String teamOrgName = "";
//        if(xhCarLoanApply.getTeamLeaderId()!= null){
//            teamLeaderName =  baseInfoManager.getEmployee(xhCarLoanApply.getTeamLeaderId()).getName();
//        }
//        if(xhCarLoanApply.getCustomerLeaderId() != null){
//            Employee employee = baseInfoManager.getEmployee(xhCarLoanApply.getCustomerLeaderId());
//            customerLeaderName = employee.getName();
//            teamOrgName = employee.getDept()!=null ? employee.getDept().getName():"";
//        }
        model.addAttribute("employee", emp);
        model.addAttribute("teamOrgName",teamOrgName);
		model.addAttribute("teamLeaderName",teamLeaderName);
        model.addAttribute("customerLeaderName",customerLeaderName);
        model.addAttribute("xhCarLoanApply",xhCarLoanApply);
		return "xhCarLoan/apply/xhCarLoanApplyInput";
	}
	
	/**
	 * 查看申请
	 *
	 * @param Id
	 * @param model
	 * @return
	 * @author xjs
	 * @date 2013-10-14 下午2:09:45
	 */
	@RequestMapping(value="/editXhCarLoanApplyLook/{Id}", method=RequestMethod.GET)
    public String  editLook(@PathVariable Long Id,Model model){
	    model.addAttribute("look","1");
        return edit(Id,model);
    }
	
	/**
	 * 待确定签署
	 * 
	 * @param Id
	 * @param model
	 * @author fjh
	 * @return
	 */
	@RequestMapping(value = "/carQianShuInput/{Id}", method = RequestMethod.GET)
	public ModelAndView carQianShuInput(@PathVariable Long Id, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		// 得到当前登录用户
		XhCarLoanContract xhCarLoanContract = new XhCarLoanContract();
		//判断合同是否存在
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("car_apply_id", Id);
		XhCarAudit xhCarAudit = xhCarLoanContractManager.getXhCarAudit(map);
		model.addAttribute("xhCarAudit", xhCarAudit);
		List<XhCarLoanContract> queryXhCarLoanContractList = xhCarLoanContractManager.queryXhCarLoanContractList(map);
		if(null != queryXhCarLoanContractList && queryXhCarLoanContractList.size()==1){
			xhCarLoanContract = queryXhCarLoanContractList.get(0);
		}
		XhCarLoanApply xhCarLoanApply = xhCarLoanContractManager.getXhCarLoanApply(Id);
		xhCarLoanContract.setXhCarLoanApply(xhCarLoanApply);
		xhCarLoanContract.setContractNum(xhCarLoanApply.getLoanCode());
		
		xhCarLoanContract.setDkllComlpex(xhCarAudit.getCreditAllRate());
		
		return new ModelAndView("xhCarLoan/apply/xhCarLoanSignInput", "xhCarLoanContract", xhCarLoanContract);
	}
	
	/**
	 * 待确定签署保存
	 * 
	 * @param XhCarLoanContract
	 * @param request
	 * @param response
	 * * @author fjh
	 * @return
	 */
	@RequestMapping(value = "/SavecarQianShuInput", method = RequestMethod.POST)
	public String SavecarQianShuInput(@ModelAttribute("xhCarLoanContract") XhCarLoanContract xhCarLoanContract,
			HttpServletRequest request, HttpServletResponse response) {
		
		//XhCarLoanApply xhCarLoanApply = xhCarLoanApplyManager.getXhCarLoanApply(xhCarLoanContract.getXhCarLoanApply().getId());
		//xhCarLoanApply.setState("529");
		//xhCarLoanContract.setXhCarLoanApply(xhCarLoanApply);
		//xhCarLoanApplyManager.saveXhCarLoanApply(xhCarLoanApply);
		XhCarLoanContract xhCarLoanContractOld = xhCarLoanContractManager.getXhCarLoanContract(xhCarLoanContract.getId());
		xhCarLoanContractOld.setQdrq(xhCarLoanContract.getQdrq());
		xhCarLoanContractManager.saveXhCarLoanContractQs(xhCarLoanContractOld);

		DwzResult success = new DwzResult("200","保存成功","rel_listXhCarLoanApply","","closeCurrent","");
		ServletUtils.renderJson(response, success);
		return null;
		
	}
	
	/**
	 * 进入展期页
	 * @param Id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/makeExtension/{Id}", method=RequestMethod.GET)
	public String  makeExtension(@PathVariable Long Id,Model model){
		XhCarLoanApply xhCarLoanApply = xhCarLoanApplyManager.getXhCarLoanApply(Id);
		model.addAttribute("partenCarApplyId", xhCarLoanApply.getId());
		model.addAttribute("jkLoanQuota1", xhCarLoanApply.getJkLoanQuota());
		xhCarLoanApply.setId(new XhCarLoanApply().getId());
        model.addAttribute("xhCarLoanApply",xhCarLoanApply);
		return "xhCarLoan/apply/xhCarLoanApplyExtensionInput";
	}
	
	/**
	 * 编辑展期页
	 * @param Id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/editExtension/{Id}", method=RequestMethod.GET)
	public String  editExtension(@PathVariable Long Id,Model model){
		XhCarLoanApply xhCarLoanApply = xhCarLoanApplyManager.getXhCarLoanApply(Id);
		//取上一笔申请的借款金额，用来做js判断
		XhCarLoanApply xhCarLoanApplyOracle = xhCarLoanApplyManager.getXhCarLoanApply(xhCarLoanApply.getPartenCarApplyId());
		System.out.println(xhCarLoanApply.getPartenCarApplyId());
		model.addAttribute("jkLoanQuota1", xhCarLoanApplyOracle.getJkLoanQuota());
		
		model.addAttribute("jkLoanQuota", xhCarLoanApply.getJkLoanQuota());
		model.addAttribute("jkLoanDate", xhCarLoanApply.getJkLoanDate());
		model.addAttribute("partenCarApplyId", xhCarLoanApply.getPartenCarApplyId());
        model.addAttribute("xhCarLoanApply",xhCarLoanApply);
		return "xhCarLoan/apply/xhCarLoanApplyExtensionInput";
	}
	
	/**
	 * 展期申请
	 * @param xhCarLoanApply
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/saveXhCarLoanExtension",method=RequestMethod.POST)
	public synchronized void saveXhCarLoanExtension(@ModelAttribute("xhCarLoanApply") XhCarLoanApply xhCarLoanApply, HttpServletRequest request, HttpServletResponse response){
	  //取得操作类型,暂存还是提交
        String optionStr = StringUtils.hasText(request.getParameter("opt")) ? request.getParameter("opt"):"0";
        CarOperation  option = CarOperation.fromStr(optionStr);//0应该改成optionStr
        Employee emp = baseInfoManager.getEmployee(xhCarLoanApply.getEmployeeCrm().getId());
        //设置zu
        xhCarLoanApply.setOrgani(emp.getOrgani());
        //得到上一笔借款申请
        XhCarLoanApply xhCarLoanApplyOracle = xhCarLoanApplyManager.getXhCarLoanApply(xhCarLoanApply.getPartenCarApplyId());
        if(xhCarLoanApply.getId()==null){
        	//如果上一笔借款申请是否已展期为否
        	if(xhCarLoanApplyOracle.getIsHaveextension().equals("0")){
           	 xhCarLoanApplyManager.saveExtension(xhCarLoanApply,xhCarLoanApplyOracle,option);
           }
        }else{
        	xhCarLoanApplyManager.saveExtension(xhCarLoanApply,xhCarLoanApplyOracle,option);
        }
       
        DwzResult success = new DwzResult("200","保存成功","rel_listXhCarLoanApply","","closeCurrent","");
        ServletUtils.renderJson(response, success);
	}
}

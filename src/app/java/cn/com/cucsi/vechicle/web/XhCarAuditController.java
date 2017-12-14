package cn.com.cucsi.vechicle.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import cn.com.cucsi.app.entity.baseinfo.Employee;
import cn.com.cucsi.app.service.baseinfo.BaseInfoManager;
import cn.com.cucsi.core.web.DwzResult;
import cn.com.cucsi.core.web.ServletUtils;
import cn.com.cucsi.vechicle.entity.xhcf.XhCarAudit;
import cn.com.cucsi.vechicle.entity.xhcf.XhCarLoanApply;
import cn.com.cucsi.vechicle.entity.xhcf.XhCarLoanApplyComplement;
import cn.com.cucsi.vechicle.entity.xhcf.XhCarLoanUser;
import cn.com.cucsi.vechicle.service.XhCarAuditManager;
import cn.com.cucsi.vechicle.service.XhCarLoanApplyComplementManager;
import cn.com.cucsi.vechicle.service.XhCarLoanApplyManager;
import cn.com.cucsi.vechicle.service.XhCarLoanUserManager;
import cn.com.cucsi.vechicle.service.XhCarMessageManager;
import cn.com.cucsi.vechicle.util.CarOperation;

@Controller
@RequestMapping(value="/xhCarAudit")
public class XhCarAuditController {
    
    public final static Map<String,String> RFRESH_URLS;
    //刷新的列表页面，复审初审等
    static{
        RFRESH_URLS = new HashMap<String,String>();
        RFRESH_URLS.put("1","rel_listlistXhCarLoanApplyAuditFirst");
        RFRESH_URLS.put("2","rel_listlistXhCarLoanApplyAuditSecond");
        RFRESH_URLS.put("100","rel_listlistXhCarLoanApplyAuditFinal");
    }
  
    private Logger logger = LoggerFactory.getLogger(XhCarAuditController.class);
    @Autowired
	private XhCarAuditManager xhCarAuditManager;
    
    @Autowired
    private XhCarLoanApplyManager xhCarLoanApplyManager;
    
    @Autowired 
    private XhCarLoanUserManager xhCarLoanUserManager;
    
    @Autowired
    private XhCarMessageManager xhCarMessageManager;
    
    @Autowired
    private XhCarLoanApplyComplementManager xhCarLoanApplyComplementManager;
    
    @Autowired
	private BaseInfoManager baseInfoManager;
	
    @RequestMapping(value="/saveXhCarLoanApplyAudit",method=RequestMethod.POST)
    public void saveXhCarLoanApplyComplement(@ModelAttribute("xhCarLoanApply") XhCarLoanApply xhCarLoanApply,HttpServletRequest request, HttpServletResponse response){
        
        CarOperation operation = CarOperation.fromStr(request.getParameter("operation"));
        xhCarAuditManager.saveXhCarAudit(xhCarLoanApply,operation);
        
        String refreshPage = RFRESH_URLS.get(request.getParameter("xhCarAudit[0].creditType"));
        DwzResult success = new DwzResult("200","保存成功",refreshPage,"","closeCurrent","");
        ServletUtils.renderJson(response, success);
        
    }
    
    //进入初审页面
    @RequestMapping(value="/carLoanApplyAudit/{Id}", method=RequestMethod.GET)
    public ModelAndView carLoanApplyComplement(@PathVariable Long Id,Model model){
        XhCarLoanApply xhCarLoanApply = xhCarLoanApplyManager.getXhCarLoanApply(Id);
        XhCarLoanUser user = xhCarLoanApply.getXhCarLoanUser();
        //初审页面提取user信息
        XhCarLoanApplyComplement complement = xhCarLoanApply.getXhCarLoanApplyComplement();
        	 complement.setAuditHjadress(user.getHjadress());
             complement.setAuditTemporary(user.getTemporaryCrede());
             complement.setAuditHomeadress(user.getHomeAddress());
             complement.setTelephone(user.getTelephone());
             complement.setSuggestMoney(xhCarLoanApply.getJkLoanQuota());
        String teamLeaderName = "";
	    String customerLeaderName = "";
	    if(user.getEmployeeCca() != null){
	        teamLeaderName =  baseInfoManager.getEmployee(user.getEmployeeCca().getId()).getName();
	    }
	    if(user.getEmployeeCrm() != null){
	        Employee employee = baseInfoManager.getEmployee(user.getEmployeeCrm().getId());
	        customerLeaderName = employee.getName();
	    }
	    
	    //判断是否为车借老客户
	    List<Map<String,Object>> isExtension = xhCarAuditManager.isExtension(Id, user.getId());
	    if(isExtension.size()>0){
	    	XhCarLoanApply xhCarLoanApplyOracle = xhCarLoanApplyManager.getXhCarLoanApply(Long.parseLong(isExtension.get(0).get("ID").toString()));
	    	if(xhCarLoanApplyOracle.getState()=="102"){
	    		model.addAttribute("applyState", "1");
	    	}else{
	    		model.addAttribute("applyState", "0");
	    	}
	    	
	    	model.addAttribute("isExtension", "1");
	    	model.addAttribute("xhCarLoanApplyOracle", xhCarLoanApplyOracle);
	    }
	    
	    //提取审核信息
	    List<Map<String, Object>> auditList =  xhCarAuditManager.getAudit(Id);
	    if(auditList.size()>0){
	    model.addAttribute("auditList", auditList);
	    XhCarAudit xhCarAudit = xhCarAuditManager.getXhCarAudit(Long.parseLong(auditList.get(auditList.size()-1).get("ID")+""));
	    model.addAttribute("xhCarAudit", xhCarAudit);
	    }
        model.addAttribute("opera", "31");
        model.addAttribute("creditType","1");
        model.addAttribute("teamLeaderName",teamLeaderName);
        model.addAttribute("customerLeaderName",customerLeaderName);
        return new ModelAndView("xhCarLoan/apply/audit/xhCarLoanApplyFirstAuditInput", "xhCarLoanApply", xhCarLoanApply);
    }
    
    //进入复审页面
    @RequestMapping(value="/carLoanApplySecondAudit/{Id}", method=RequestMethod.GET)
    public ModelAndView carLoanApply(@PathVariable Long Id,Model model){
        XhCarLoanApply xhCarLoanApply = xhCarLoanApplyManager.getXhCarLoanApply(Id);
        XhCarLoanUser user = xhCarLoanApply.getXhCarLoanUser();
        String teamLeaderName = "";
	    String customerLeaderName = "";
	    if(user.getEmployeeCca() != null){
	        teamLeaderName =  baseInfoManager.getEmployee(user.getEmployeeCca().getId()).getName();
	    }
	    if(user.getEmployeeCrm() != null){
	        Employee employee = baseInfoManager.getEmployee(user.getEmployeeCrm().getId());
	        customerLeaderName = employee.getName();
	    }
	    
	    //判断是否为车借老客户
	    List<Map<String,Object>> isExtension = xhCarAuditManager.isExtension(Id, user.getId());
	    if(isExtension.size()>0){
	    	XhCarLoanApply xhCarLoanApplyOracle = xhCarLoanApplyManager.getXhCarLoanApply(Long.parseLong(isExtension.get(0).get("ID").toString()));
	    	if(xhCarLoanApplyOracle.getState()=="102"){
	    		model.addAttribute("applyState", "1");
	    	}else{
	    		model.addAttribute("applyState", "0");
	    	}
	    	
	    	model.addAttribute("isExtension", "1");
	    	model.addAttribute("xhCarLoanApplyOracle", xhCarLoanApplyOracle);
	    }
	    
	    //提取审核信息
	    List<Map<String, Object>> auditList =  xhCarAuditManager.getAudit(Id);
	    model.addAttribute("auditList", auditList);
	    XhCarAudit xhCarAudit = xhCarAuditManager.getXhCarAudit(Long.parseLong(auditList.get(auditList.size()-1).get("ID")+""));
	    model.addAttribute("xhCarAudit", xhCarAudit);
	    
	    
        model.addAttribute("opera", "32");
        model.addAttribute("creditType","2");
        model.addAttribute("teamLeaderName",teamLeaderName);
        model.addAttribute("customerLeaderName",customerLeaderName);
        return new ModelAndView("xhCarLoan/apply/audit/xhCarLoanApplyFirstAuditInput", "xhCarLoanApply", xhCarLoanApply);
    }
    
    //进入终审页面
    @RequestMapping(value="/carLoanApplyLastAudit/{Id}", method=RequestMethod.GET)
    public ModelAndView carLoanApplyLastAudit(@PathVariable Long Id,Model model){
        XhCarLoanApply xhCarLoanApply = xhCarLoanApplyManager.getXhCarLoanApply(Id);
        XhCarLoanUser user = xhCarLoanApply.getXhCarLoanUser();
        String teamLeaderName = "";
	    String customerLeaderName = "";
	    if(user.getEmployeeCca() != null){
	        teamLeaderName =  baseInfoManager.getEmployee(user.getEmployeeCca().getId()).getName();
	    }
	    if(user.getEmployeeCrm() != null){
	        Employee employee = baseInfoManager.getEmployee(user.getEmployeeCrm().getId());
	        customerLeaderName = employee.getName();
	    }
	    
	    //判断是否为车借老客户
	    List<Map<String,Object>> isExtension = xhCarAuditManager.isExtension(Id, user.getId());
	    if(isExtension.size()>0){
	    	XhCarLoanApply xhCarLoanApplyOracle = xhCarLoanApplyManager.getXhCarLoanApply(Long.parseLong(isExtension.get(0).get("ID").toString()));
	    	if(xhCarLoanApplyOracle.getState()=="102"){
	    		model.addAttribute("applyState", "1");
	    	}else{
	    		model.addAttribute("applyState", "0");
	    	}
	    	
	    	model.addAttribute("isExtension", "1");
	    	model.addAttribute("xhCarLoanApplyOracle", xhCarLoanApplyOracle);
	    }
	    
	    //提取审核信息
	    List<Map<String, Object>> auditList =  xhCarAuditManager.getAudit(Id);
	    model.addAttribute("auditList", auditList);
	    XhCarAudit xhCarAudit = xhCarAuditManager.getXhCarAudit(Long.parseLong(auditList.get(auditList.size()-1).get("ID")+""));
	    model.addAttribute("xhCarAudit", xhCarAudit);
	    
        model.addAttribute("opera", "33");
        model.addAttribute("creditType","100");
        model.addAttribute("teamLeaderName",teamLeaderName);
        model.addAttribute("customerLeaderName",customerLeaderName);
        return new ModelAndView("xhCarLoan/apply/audit/xhCarLoanApplyFirstAuditInput", "xhCarLoanApply", xhCarLoanApply);
    }
}

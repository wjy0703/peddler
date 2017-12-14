package cn.com.cucsi.app2.web;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import cn.com.cucsi.app.dao.loan.XhJksqStateDao;
import cn.com.cucsi.app.entity.baseinfo.Attr;
import cn.com.cucsi.app.entity.baseinfo.City;
import cn.com.cucsi.app.entity.baseinfo.Employee;
import cn.com.cucsi.app.entity.baseinfo.MiddleMan;
import cn.com.cucsi.app.entity.baseinfo.Xydkzx;
import cn.com.cucsi.app.entity.xhcf.XhCreditAudit;
import cn.com.cucsi.app.entity.xhcf.XhCreditTaskAssign;
import cn.com.cucsi.app.entity.xhcf.XhJkht;
import cn.com.cucsi.app.entity.xhcf.XhJksq;
import cn.com.cucsi.app.entity.xhcf.XhJksqState;
import cn.com.cucsi.app.entity.xhcf.XhJksqTogether;
import cn.com.cucsi.app.entity.xhcf.XhUploadFiles;
import cn.com.cucsi.app.service.PublicService;
import cn.com.cucsi.app.service.baseinfo.BaseInfoManager;
import cn.com.cucsi.app.service.excel.StatisticalService;
import cn.com.cucsi.app.service.loan.XhJksqManager;
import cn.com.cucsi.app.service.security.OperatorDetails;
import cn.com.cucsi.app.service.xhcf.XhCreditAuditManager;
import cn.com.cucsi.app.service.xhcf.XhCreditTaskAssignManager;
import cn.com.cucsi.app.service.xhcf.XhJkhtManager;
import cn.com.cucsi.app.web.InitSetupListener;
import cn.com.cucsi.app.web.util.FileUtil;
import cn.com.cucsi.app.web.util.HibernateAwareBeanUtilsBean;
import cn.com.cucsi.app.web.util.JdbcPageUtils;
import cn.com.cucsi.app.web.util.P2PJsonUtils;
import cn.com.cucsi.app.web.util.RequestPageUtils;
import cn.com.cucsi.app2.entity.xhcf.XhCjksqBankRecords;
import cn.com.cucsi.app2.entity.xhcf.XhJksqCredits;
import cn.com.cucsi.app2.entity.xhcf.XhJksqHouse;
import cn.com.cucsi.app2.entity.xhcf.XhJksqcreditComment;
import cn.com.cucsi.app2.service.xhcf.XhCjksqBankRecordsManager;
import cn.com.cucsi.app2.service.xhcf.XhCjksqCardsManager;
import cn.com.cucsi.app2.service.xhcf.XhCjksqVechicleManager;
import cn.com.cucsi.app2.service.xhcf.XhJksqCreditsManager;
import cn.com.cucsi.app2.service.xhcf.XhJksqHouseManager;
import cn.com.cucsi.app2.service.xhcf.XhJksqcompanyDownrelatedManager;
import cn.com.cucsi.app2.service.xhcf.XhJksqcompanyUprelatedManager;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils;
import cn.com.cucsi.core.utils.PropertiesUtils;
import cn.com.cucsi.core.web.DwzResult;
import cn.com.cucsi.core.web.ServletUtils;

import com.ibm.icu.text.SimpleDateFormat;

/**
 * 
 * @author 
 * @date 2012-12-14
 */
@Controller
@RequestMapping(value = "/loan")
public class XhNewCreditAuditController {
	private XhCreditAuditManager xhCreditAuditManager;
	private BaseInfoManager baseInfoManager;
    private XhJksqManager loanApplyManager;
	private XhJkhtManager xhJkhtManager;
    //loan/newInitAuditInfo/1232312
    @Autowired
	public void setBaseInfoManager(BaseInfoManager baseInfoManager) {
		this.baseInfoManager = baseInfoManager;
	}
    @Autowired
	public void setXhJkhtManager(XhJkhtManager xhJkhtManager) {
		this.xhJkhtManager = xhJkhtManager;
	}
    
    
    
    @Autowired 
    private XhCjksqCardsManager xhCjksqCardsManager;
    @Autowired
    private XhJksqCreditsManager xhJksqCreditsManager;
    @Autowired
    private XhCjksqBankRecordsManager xhCjksqBankRecordsManager;
    @Autowired
    private XhJksqHouseManager xhJksqHouseManager;
    @Autowired
    private XhCjksqVechicleManager xhCjksqVechicleManager;
    @Autowired
    private XhJksqcompanyUprelatedManager xhJksqcompanyUprelatedManager;
    @Autowired
    private XhJksqcompanyDownrelatedManager xhJksqcompanyDownrelatedManager;


    @Autowired
    public void setXhjksqManager(XhJksqManager loanApplyManager) {
        this.loanApplyManager = loanApplyManager;
    }
    @Autowired
    public void setXhCreditAuditManager(XhCreditAuditManager xhCreditAuditManager) {
        this.xhCreditAuditManager = xhCreditAuditManager;
    }
    /**
     * 初始化信审数据 MDY 2013-08-29
     * 
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/newInitAuditInfo/{Id}")
    public String initAuditInfo(@PathVariable Long Id, HttpServletRequest request, Model model) {
        xhCreditAuditManager.getInitAuditInfoByJksqId(Id);
        List<City> province = baseInfoManager.getSuggestCity("0");
		request.setAttribute("province", province);
        //------借款申请
        XhJksq loanApply = loanApplyManager.getXhJksq(Id);
        List<XhJksqcreditComment> comments = loanApply.getXhJksqcreditComments();
        //------城市
        City city = loanApply.getProvince();
        //------借款类型A：老板贷；B：老板楼易贷；C：薪水贷；D：薪水楼易贷；E：精英贷
        //String jkType = loanApply.getJkType();
        //------咨询
        
        /******/
        Map<String, Object> map = WebUtils.getParametersStartingWith(request, "filter_");
        map.put("pocertificates", loanApply.getPocertificates());
        map.put("zjhm", loanApply.getZjhm());
        map.put("id", loanApply.getId());
        List<Map<String,Object>> list = loanApplyManager.searchXhJksqAll("queryLaokehuAndYuqi", map);
        if(list.size()==0){
        	request.setAttribute("personState", "否");
        }else{
        	request.setAttribute("personState", "是");
        }
        request.setAttribute("list", list);
        /****/
        if ("是".equals(loanApply.getTogetherPerson())) {
            XhJksqTogether xhJksqTogether = loanApplyManager.getXhJksqTogether(loanApply);
            model.addAttribute("xhJksqTogether",xhJksqTogether);
        }
        
        Xydkzx xydkzx = loanApply.getXydkzx();
        request.setAttribute("comments", comments);
        request.setAttribute("city", city);
        request.setAttribute("xydkzx", xydkzx);
        request.setAttribute("loanApply", loanApply);
        request.setAttribute("loanApplyId", Id);
        return "creditAuditInfo/initAuditInfo";
    }
    /**
     * 保存新版信审
     * @param Id
     * @param request
     * @param model
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     */
    @RequestMapping(value = "/saveAuditInfo/{Id}")
    public void saveAuditInfo(@ModelAttribute("xhJksq") XhJksq xhJksq, 
    		HttpServletRequest request, Model model, HttpServletResponse response) {
    	String option = request.getParameter("option");
    	
    	XhJksq xhJksqData = loanApplyManager.getXhJksq(xhJksq.getId());
    	
    	HibernateAwareBeanUtilsBean beanCopy = new HibernateAwareBeanUtilsBean();
    	try {
			beanCopy.copyProperties(xhJksqData, xhJksq);
		} catch (Exception e) {
		    System.out.println("bean拷贝发生错误");
		}
    	loanApplyManager.saveXhJksq(xhJksqData);
    	
    	DwzResult success ;
    	if("1".equals(option)){
    		success = new DwzResult("200", "保存成功", "rel_listLoanApplyByState", "", "closeCurrent", "");
    	}else{
    		success = new DwzResult("200", "保存成功", "rel_listLoanApplyByState", "", "", "");
    	}
    	ServletUtils.renderJson(response, success);
//        return "loan/listLoanApplyByState/31";
    	
    }
    
    @RequestMapping(value = "/oracleDeleteCards/{Id}", method = RequestMethod.POST)
    public void saveCardsChange(@PathVariable("Id") Long Id, HttpServletRequest request, HttpServletResponse response) {
    	xhCjksqCardsManager.deleteXhCjksqCards(Id);
        ServletUtils.renderJson(response, "1");
        
    }

    @RequestMapping(value = "/oracleDeleteCredits/{Id}", method = RequestMethod.POST)
    public void saveCreditsChange(@PathVariable("Id") Long Id, HttpServletRequest request, HttpServletResponse response) {
    	xhJksqCreditsManager.deleteCredit(Id);
        ServletUtils.renderJson(response, "1");
        
    }
    @RequestMapping(value = "/oracleDeleteBank/{Id}", method = RequestMethod.POST)
    public void saveBankChange(@PathVariable("Id") Long Id, HttpServletRequest request, HttpServletResponse response) {
    	xhCjksqBankRecordsManager.deleteXhCjksqBankRecords(Id);
    	ServletUtils.renderJson(response, "1");
    	
    }
    @RequestMapping(value = "/oracleDeleteHouses/{Id}", method = RequestMethod.POST)
    public void saveHousesChange(@PathVariable("Id") Long Id, HttpServletRequest request, HttpServletResponse response) {
    	xhJksqHouseManager.deleteXhJksqHouse(Id);
    	ServletUtils.renderJson(response, "1");
    	
    }
    @RequestMapping(value = "/oracleDeleteVechicles/{Id}", method = RequestMethod.POST)
    public void saveVechiclesChange(@PathVariable("Id") Long Id, HttpServletRequest request, HttpServletResponse response) {
    	xhCjksqVechicleManager.deleteXhCjksqVechicle(Id); 
    	ServletUtils.renderJson(response, "1");
    	
    }
    @RequestMapping(value = "/oracleDeleteCompanyUp/{Id}", method = RequestMethod.POST)
    public void saveCompanyUpChange(@PathVariable("Id") Long Id, HttpServletRequest request, HttpServletResponse response) {
    	xhJksqcompanyUprelatedManager.deleteXhJksqcompanyUprelated(Id);
    	ServletUtils.renderJson(response, "1");
    	
    }
    @RequestMapping(value = "/oracleDeleteCompanyDown/{Id}", method = RequestMethod.POST)
    public void saveCompanyDownChange(@PathVariable("Id") Long Id, HttpServletRequest request, HttpServletResponse response) {
    	xhJksqcompanyDownrelatedManager.deleteXhJksqcompanyDownrelated(Id);
    	ServletUtils.renderJson(response, "1");
    	
    }
    
    /**
     * 信审复议列表页
     * @param typedata 1.READY 待复议列表 2.OVER 已复议列表
     * @param request
     * @param response
     * @param model
     * @return
     * @author yzl
     */
	@RequestMapping(value = "/listLoanFuYi/{typedata}")
	public String listJksq(@PathVariable String typedata,HttpServletRequest request,
			HttpServletResponse response, Model model) {
		// 得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		if (operator == null) {
			return "redirect:top.login";
		}
		JdbcPage page = JdbcPageUtils.generatePage(request);
		// 借款申请状态
		Map<String, Object> filter = new HashMap<String, Object>();
		filter.put("attrType", "2");
		List<Attr> attrList = baseInfoManager.getAttrList(filter);
		request.setAttribute("attrList", attrList);

		// 借款类型
		Map<String, Object> filter2 = new HashMap<String, Object>();
		filter2.put("attrType", "3");
		List<Attr> attrList2 = baseInfoManager.getAttrList(filter2);
		request.setAttribute("jkTypeList", attrList2);

		List<City> province = baseInfoManager.getSuggestCity("0");
		request.setAttribute("province", province);

		Map<String, Object> map = WebUtils.getParametersStartingWith(request,
				"filter_");
		Employee emp = baseInfoManager.getUserEmployee();
		String loginName = operator.getCtiCode();
		map.put("loginName", loginName);
		map.put("emp", emp);
		if("READY".equals(typedata)){
			//----------待复议列表
			map.put("state", "70.A");//待复议状态为70.A
			model.addAttribute("typedata","READY");
		}else if("OVER".equals(typedata)){
			//----------已复议列表
			map.put("CREDIT_TYPE", "4");//信审表复议
			model.addAttribute("typedata","OVER");
		}
		// 贷款标志
		map.put("backup01", "CREDIT");
		List<Map<String, Object>> listJksq = new ArrayList<Map<String,Object>>();
		//判断是否循环贷
		String type = request.getParameter("type");
		if(type != null && !"".equals(type)){
			map.put("type", "10");
			model.addAttribute("type", type);
			listJksq = loanApplyManager.searchXhJksqLoop("loanApplyJksqList", map, page);
		}else{
			listJksq = loanApplyManager.searchXhJksq("loanApplyJksqList", map, page);
			
		}
		//XhJksqState xhJksqState = 
		model.addAttribute("listJksq", listJksq);
		model.addAttribute("page", page);

		model.addAttribute("organiId", request.getParameter("filter_organi.id"));
	    model.addAttribute("organiName", request.getParameter("filter_organi.name"));
		model.addAttribute("filter_jkrxm", request.getParameter("filter_jkrxm"));
		model.addAttribute("filter_telephone",request.getParameter("filter_telephone"));
		model.addAttribute("filter_zjhm", request.getParameter("filter_zjhm"));
		model.addAttribute("filter_jkType",request.getParameter("filter_jkType"));
		model.addAttribute("filter_state_injksp",request.getParameter("filter_state_injksp"));
		model.addAttribute("filter_province",request.getParameter("filter_province"));
		model.addAttribute("filter_city", request.getParameter("filter_city"));
		String filter_province = request.getParameter("filter_province");
		if (null != filter_province && !"".equals(filter_province)) {
			List<City> city = baseInfoManager.getSuggestCity(filter_province);
			request.setAttribute("city", city);
		}
		model.addAttribute("filter_teamName",request.getParameter("filter_teamName"));
		model.addAttribute("filter_saleName",request.getParameter("filter_saleName"));
		model.addAttribute("map", map);
		
		
		
		return "loan/fuYiListReadyOrOver";
	}
	
	
	   /**
	    * 信审复议结果保存
	    * @param xhJkht 借款合同内容
	    * @param request
	    * @param response
	    * @return
	    * @author yzl
	    */
		@RequestMapping(value = "/saveLoanFuYi", method = RequestMethod.POST)
		public String saveJksqFuYi(@ModelAttribute("xhJkht") XhJkht xhJkht,
				HttpServletRequest request, HttpServletResponse response) {
			Long id = Long.parseLong(request.getParameter("xhJksq.id"));
			XhJksq xhJksq = loanApplyManager.getXhJksq(id);
			XhJkht xhJkhtNew = xhJkhtManager.getXhJkht(xhJkht.getId());
			
	    	HibernateAwareBeanUtilsBean beanCopy = new HibernateAwareBeanUtilsBean();
	    	try {
				beanCopy.copyProperties(xhJkhtNew, xhJkht);
			} catch (Exception e) {
			    System.out.println("bean拷贝发生错误");
			}
			xhJkhtNew.setXhJksq(xhJksq);
			xhJkhtNew.setQdrq(xhJkht.getQdrq());
			
			//xhJkhtManager.saveXhJkht(xhJkhtNew);
			String fujgms = request.getParameter("fujgms");//复议结果描述
			String yesOrNo = request.getParameter("yesOrNo");//是否变更审批结果
			XhCreditAudit xhCreditAudit = new XhCreditAudit();
			xhCreditAudit.setCreditAmount(xhJkhtNew.getPdje().toString());
			xhCreditAudit.setCreditAllRate(xhJkhtNew.getYzhfl().toString());
			xhCreditAudit.setOutVisitFee(xhJkhtNew.getXff().toString());
			String hkqs = xhJkhtNew.getHkqs()+"";
			xhCreditAudit.setCreditMonth(hkqs);
			xhCreditAudit.setUrgentCreditFee((Double)(xhJkhtNew.getUrgentCreditFee()));
			xhCreditAudit.setCreditResult(yesOrNo);//通过OR拒绝
			xhCreditAudit.setCreditAuditReport(fujgms);
			xhCreditAudit.setCreditState("1");//信审结束
			xhCreditAudit.setCreditType("4");//4：复议
			xhCreditAudit.setLoanApply(xhJksq);
			//保存一条新信审记录
//			xhCreditAuditManager.saveXhCreditAudit(xhCreditAudit, request);
//			boolean isSuccess = xhCreditAuditManager.saveXhLoanFuYi(xhJkhtNew, request);
			
            //修改存储复议方法
            boolean isSuccess = xhCreditAuditManager.saveXhLoanFuYi(xhJkhtNew,xhCreditAudit, request);

			DwzResult success = null;
			if (isSuccess) {
				success = new DwzResult("200", "保存成功", "rel_listLoanFuYi", "",
						"closeCurrent", "");
			} else {
				success = new DwzResult("300", "保存失败", "rel_listLoanFuYi", "",
						"closeCurrent", "");
			}
			ServletUtils.renderJson(response, success);
			return null;

		}
		
		/**
		 *  信审单条复议页
		 * @param Id 借款申请ID--jksqId
		 * @param model
		 * @param request
		 * @param response
		 * @return
		 * @author yzl
		 */
		@RequestMapping(value = "/loanFuYiInput/{Id}", method = RequestMethod.GET)
		public ModelAndView jksqFuYiInput(@PathVariable Long Id, Model model,
				HttpServletRequest request, HttpServletResponse response) {
			XhJkht xhJkht = xhJkhtManager.findLoanContarctByApplyID(Id);
			return new ModelAndView("loan/jksqFuYiInput","xhJkht",xhJkht);
		}
		
		/**
		 *  信审已复议单条查看页
		 * @param Id 借款申请ID--jksqId
		 * @param model
		 * @param request
		 * @param response
		 * @return
		 * @author yzl
		 */
		@RequestMapping(value = "/loanFuYiQueryOne/{Id}", method = RequestMethod.GET)
		public ModelAndView jksqFuYiQueryOne(@PathVariable Long Id, Model model,
				HttpServletRequest request, HttpServletResponse response) {
			XhJkht xhJkht = xhJkhtManager.findLoanContarctByApplyID(Id);
			Page<XhCreditAudit> page = new Page<XhCreditAudit>();
			Map<String, Object> map = ServletUtils.getParametersStartingWith2(
					request, "filter_");
			// 信审记录中对应每一个借款ID只有一条是完成信审的，状态为1
//			map.put("creditState", "1");
			map.put("creditType", "4");//复议内容 状态为4
			map.put("loanApplyId", Id);
			page = xhCreditAuditManager.searchXhCreditAudit(page, map);
			if (page.getResult().size() == 0) {
				DwzResult success = null;
				success = new DwzResult("300", "打开合同失败", "rel_listJksq", "",
						"closeCurrent", "");
				ServletUtils.renderJson(response, success);
				return null;
			}
			XhCreditAudit xhCreditAudit = page.getResult().get(0);
			model.addAttribute("xhCreditAudit",xhCreditAudit);
			return new ModelAndView("loan/jksqFuYiQueryOne","xhJkht",xhJkht);
		}
		
}

package cn.com.cucsi.app2.web;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import cn.com.cucsi.app.entity.baseinfo.Attr;
import cn.com.cucsi.app.entity.baseinfo.Employee;
import cn.com.cucsi.app.entity.baseinfo.MateData;
import cn.com.cucsi.app.entity.baseinfo.MateDataType;
import cn.com.cucsi.app.entity.baseinfo.Xydkzx;
import cn.com.cucsi.app.entity.security.Authority;
import cn.com.cucsi.app.entity.xhcf.LoanTaskDeliv;
import cn.com.cucsi.app.entity.xhcf.XhCreditAudit;
import cn.com.cucsi.app.entity.xhcf.XhJkjjlxr;
import cn.com.cucsi.app.entity.xhcf.XhJksq;
import cn.com.cucsi.app.entity.xhcf.XhJksqApplyedit;
import cn.com.cucsi.app.entity.xhcf.XhJksqTogether;
import cn.com.cucsi.app.service.baseinfo.BaseInfoManager;
import cn.com.cucsi.app.service.loan.XhJksqApplyeditManager;
import cn.com.cucsi.app.service.loan.XhJksqManager;
import cn.com.cucsi.app.service.security.OperatorDetails;
import cn.com.cucsi.app.service.xhcf.DksqManager;
import cn.com.cucsi.app.service.xhcf.XhBlackListManager;
import cn.com.cucsi.app.web.util.JdbcPageUtils;
import cn.com.cucsi.app.web.util.JksqStateUtils;
import cn.com.cucsi.app.web.util.RequestPageUtils;
import cn.com.cucsi.app2.entity.xhcf.XhCjksqSurveyMain;
import cn.com.cucsi.app2.entity.xhcf.XhJksqCompany;
import cn.com.cucsi.app2.entity.xhcf.XhJksqCredits;
import cn.com.cucsi.app2.entity.xhcf.XhJksqHouse;
import cn.com.cucsi.app2.entity.xhcf.XhJksqOffice;
import cn.com.cucsi.app2.service.xhcf.XhCjksqSurveyMainManager;
import cn.com.cucsi.app2.service.xhcf.XhJksqCompanyManager;
import cn.com.cucsi.app2.service.xhcf.XhJksqCreditsManager;
import cn.com.cucsi.app2.service.xhcf.XhJksqHouseManager;
import cn.com.cucsi.app2.service.xhcf.XhJksqOfficeManager;
import cn.com.cucsi.app2.service.xhcf.XhNewJksqManager;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils;
import cn.com.cucsi.core.test.utils.DataUtils;
import cn.com.cucsi.core.web.DwzResult;
import cn.com.cucsi.core.web.ServletUtils;

@Controller("XhJksqController")
@RequestMapping(value = "/xhNewJksq")
public class XhJksqController {

    private static Logger logger = LoggerFactory.getLogger(XhJksqController.class);
    
	private static final String JKSQ_TOKEN = "JKSQ_FORM_TOKEN";

    @Autowired
	private XhJksqManager xhjksqManager;

	@Autowired
	private XhJksqApplyeditManager xhApplayManager;

	@Autowired
	private BaseInfoManager baseInfoManager;

	@Autowired
	private DksqManager dksqManager;

	@Autowired
	private XhNewJksqManager xhNewJksqManager;

	@Autowired
	private XhJksqCreditsManager xhJksqCreditsManager;

	@Autowired
	private XhJksqHouseManager xhJksqHouseManager;

	@Autowired
	private XhJksqOfficeManager xhJksqOfficeManager;

	@Autowired
	private XhJksqCompanyManager xhJksqCompanyManager;

	@Autowired
	private XhBlackListManager xhBlackListManager;
	
	@Autowired
	private XhCjksqSurveyMainManager surveyMainManager;
	
	@RequestMapping(value = "/saveXhJksq", method = RequestMethod.POST)
	public void saveXhJksq(@ModelAttribute("xhJksq") XhJksq xhJksq,
			HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		
	    
	    
		// 加入黑名单验证，如果是黑名单客户，拒绝进件
		boolean isBlackListCustomer = false;
		String zjhm = xhJksq.getZjhm();
		if (null != zjhm){
			List blackList=xhBlackListManager.findBlackListBySFId(zjhm.trim());
			
			if(blackList.size()>0){
				isBlackListCustomer=true;
			}
			//非循环贷
			if(!StringUtils.hasText(request.getParameter("loopApply"))){
    			Map<String,String> conditions = new HashMap<String,String>();
                conditions.put("cardId", zjhm);
                if(xhJksq.getId() != null)
                    conditions.put("myownId", xhJksq.getId().toString());
                    
                Map<String,Object> relatedItems = xhNewJksqManager.getRelatedItems(conditions);
                if("0".equals(relatedItems.get("canSubmit"))){
                    DwzResult success = new DwzResult("500", "该客户有相关借款申请，不符合进件条件！", "", "", "", "");
                    ServletUtils.renderJson(response, success);
                    return ;
                }
                String spouseCardId = xhJksq.getSpouseZjhm();
                if(StringUtils.hasText(spouseCardId)){
                    conditions.put("cardId", spouseCardId);
                    relatedItems = xhNewJksqManager.getRelatedItems(conditions);
                    if("0".equals(relatedItems.get("canSubmit"))){
                        DwzResult success = new DwzResult("500", "该客户配偶有相关借款申请，不符合进件条件！", "", "", "", "");
                        ServletUtils.renderJson(response, success);
                        return ;
                    }
                }
			}
		}
			
		if(isBlackListCustomer){
			DwzResult success = new DwzResult("500", "该客户为黑名单客户，不符合进件条件！", "", "", "", "");
			ServletUtils.renderJson(response, success);
		}
		else{//非黑名单客户，正常进件
			
		    //直系亲属
          /*  List<XhJksqRelations> relations = xhJksq.getXhJksqRelations();
            if (relations != null)
                for (int index = 0; index < relations.size(); index++) {
                    XhJksqRelations relation = relations.get(index);
                    relation.setXhJksq(xhJksq);

                }
		    */
		    
		    String token = request.getParameter("jksqToken");
	        if(!validateSubmit(token,session)){
	            //800代表不做任何操作
	            DwzResult success = new DwzResult("800", "数据已提交，请耐心等待！！或已经与服务器失去联系！", "", "", "", "");
	            ServletUtils.renderJson(response, success);
	            return ;
	        }
		    
			List<XhJkjjlxr> lxrs = xhJksq.getXhJkjjlxrs();
			if (lxrs != null) {
				for (int index = 0; index < lxrs.size(); index++) {
					XhJkjjlxr lxr = lxrs.get(index);
					lxr.setXhJksq(xhJksq);

				}
			}
			List<XhJksqOffice> offices = xhJksq.getXhJksqOffices();
			if (offices != null)
				for (int index = 0; index < offices.size(); index++) {
					XhJksqOffice Office = offices.get(index);
					Office.setXhJksq(xhJksq);

				}

			List<XhJksqCompany> companys = xhJksq.getXhJksqCompanys();
			if (companys != null)
				for (int index = 0; index < companys.size(); index++) {
					XhJksqCompany company = companys.get(index);
					company.setXhJksq(xhJksq);

				}

			List<XhJksqHouse> houses = xhJksq.getXhJksqHouses();
			if (houses != null)
				for (int index = 0; index < houses.size(); index++) {
					XhJksqHouse house = houses.get(index);
					house.setXhJksq(xhJksq);

				}
			List<XhJksqCredits> credits = xhJksq.getXhJksqCredits();
			if (credits != null)
				for (int index = 0; index < credits.size(); index++) {
					XhJksqCredits credit = credits.get(index);
					credit.setXhJksq(xhJksq);

				}

			String option = request.getParameter("opt");
		
			//门店真正的提交,设置进件时间
			String storeToCenter = request.getParameter("storeToCenter");
			if(StringUtils.hasText(storeToCenter) && !"0".equals(option)){
			    xhJksq.setBackup02(String.valueOf(new Timestamp(new Date().getTime())));
			}
			
			try {
				DwzResult success;
				if (xhJksq.getId() == null) {
					// 代表是新的申请页面，刷新咨询表
					success = new DwzResult("200", "保存成功", "rel_xydkzx,rel_listLoopConsulting", "",
							"closeCurrent", "");
				} else {
					// 刷新借款申请列表
					success = new DwzResult("200", "保存成功", "rel_listJksq", "",
							"closeCurrent", "");
				}
				
				int check = xhNewJksqManager.saveXhJksq(xhJksq, option);
				if(check == 1){
				    success = new DwzResult("200", "保存成功", "rel_listJksqCheck", "",
                            "closeCurrent", "");
				}
				
				if(logger.isInfoEnabled()){
				    logger.info("为用户 " + SpringSecurityUtils.getCurrentUserName() + " : 清除session :  " + session.getAttribute(JKSQ_TOKEN) );
				}

				session.removeAttribute(JKSQ_TOKEN);
				
				ServletUtils.renderJson(response, success);
				return;
			} catch (Exception e) {
				e.printStackTrace();
				DwzResult success = new DwzResult("500", "保存失败", "", "", "", "");
				ServletUtils.renderJson(response, success);
			}
		}
	

	}

	/**
	 * 判断是否是第一次提交，如果是返回真值，同时去掉session对应的值
	 *
	 * @param token
	 * @param session
	 * @return
	 * @author xjs
	 * @date 2013-10-22 上午9:26:37
	 */
	private synchronized boolean  validateSubmit(String token, HttpSession session) {
        Object sessionToken  = session.getAttribute(JKSQ_TOKEN);
	    if(sessionToken != null){
            if(StringUtils.hasText(token)){
                if(token.equals(sessionToken.toString())){
                    if(logger.isInfoEnabled()){
                        logger.info( SpringSecurityUtils.getCurrentUserName() +" -- 用户提交的 使用的token是: " + token);
                    }
                    //session.removeAttribute(JKSQ_TOKEN);
                    return true;
                }
            }
        }
	    if(logger.isErrorEnabled()){
	        logger.error("异常出现，检查前面用户: "+SpringSecurityUtils.getCurrentUserName() + " --是否使用 "+token+" 已提交过 ");
	        logger.error("服务器Token   --  "+sessionToken);
	    }
	   
        return false;
    }

    
    /**
     * 循环贷申请页面
     *
     * @param Id
     * @param model
     * @return
     * @author MDY
     * @date 2013-10-23
     */
    @RequestMapping(value = "/addLoopXhJksq/{Id}", method = RequestMethod.GET)
    public String addLoopXhJksq(@PathVariable Long Id,Model model,HttpServletRequest request) {
    	Xydkzx xydkzx = dksqManager.getXydkzx(Id);
        XhJksq xhJksq =  xhjksqManager.getXhJksq(new Long(xydkzx.getZdkh()));
        if ("是".equals(xhJksq.getTogetherPerson())) {
            XhJksqTogether xhJksqTogether = xhjksqManager.getXhJksqTogether(xhJksq);
            model.addAttribute("xhJksqTogether",xhJksqTogether);
        }
        xhJksq.setJkLoanQuota(xydkzx.getPlanAmount());//计划借款金额
        xhJksq.setJkTypeMore(xydkzx.getLoanPurpose());
        Long empId = this.getServiceId(xhJksq.getFirstServiceId());
        xhJksq.setId(null);
		model.addAttribute("empId",empId);
		model.addAttribute("empId2","");
		model.addAttribute("consultId", xydkzx.getId());
        model.addAttribute("xhJksq", xhJksq);
        return "jksq/newapply/xhJksqLoopInput";
    }
   
    /*
     * 
	 * @param Id
	 *            咨询id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addJksq/{Id}", method = RequestMethod.GET)
	public String add(@PathVariable Long Id, Model model,HttpSession session) {
	    String token = generateRandomStr();
        session.setAttribute(JKSQ_TOKEN, token);
        if(logger.isInfoEnabled()){
            logger.info("为用户：" + SpringSecurityUtils.getCurrentUserName() +" -- 创建token :  " + token);
        }
		XhJksq xhjksq = new XhJksq();
		// 咨询信息
		Xydkzx xydkzx = dksqManager.getXydkzx(Id);
		xhjksq.setJkLoanQuota(xydkzx.getPlanAmount());// 计划借款金额
		xhjksq.setGenders(xydkzx.getSex());// 性别
		xhjksq.setJkrxm(xydkzx.getKhmc());// 借款人姓名
		xhjksq.setHomePhone(xydkzx.getTelephone());// 固定电话
		xhjksq.setTelephone(xydkzx.getPhone());// 移动电话
		xhjksq.setJkTypeMore(xydkzx.getLoanPurpose());
		//线上P2P
//        if(xydkzx.getIs_P2P() != null && "是".equals(xydkzx.getIs_P2P())){
//        	xhjksq.setZjhm(xydkzx.getIdNumber());
//            xhjksq.setPocertificates(xydkzx.getIdType());
//        }
		model.addAttribute("consultId", Id);
		model.addAttribute("xhJksq", xhjksq);
//		Long empId = this.get(xhjksq);
		Long empId = this.getServiceId(xhjksq.getFirstServiceId());
		model.addAttribute("empId",empId);
		model.addAttribute("empId2","");
		return "jksq/newapply/xhJksqInput";

	}
	
    /**
     * 生成随机的字符串
     *
     * @return
     * @author xjs
     * @date 2013-10-22 上午9:04:15
     */
	private String generateRandomStr() {
        return DataUtils.randomName("jksq");
    }

    public Long getServiceId(Long serviceId){
		if(null == serviceId){
			Employee emp = baseInfoManager.getUserEmployee();
			return emp.getId();
		}else{
			return serviceId;
		}
	}

    
    //storeToCenter
    @RequestMapping(value = "/storeToCenter/{Id}", method = RequestMethod.GET)
    public String storeToCenter(@PathVariable Long Id, Model model,HttpServletRequest request,HttpSession session) {
         model.addAttribute("storeToCenter","yes");
         return edit(Id,model,request,session);
    }
    
    //storeToCenter
    @RequestMapping(value = "/alwaysChange/{Id}", method = RequestMethod.GET)
    public String alwaysChange(@PathVariable Long Id, Model model,HttpServletRequest request,HttpSession session) {
         model.addAttribute("alwaysChange","yes");
         return  edit(Id,model,request,session);
    }
    
    
	/**
	 * 编辑页面
	 * 
	 * @param Id
	 * @param model
	 * @return
	 * @author xjs
	 * @date 2013-9-14 下午12:55:09
	 */
	@RequestMapping(value = "/editXhJksq/{Id}", method = RequestMethod.GET)
	public String edit(@PathVariable Long Id, Model model,HttpServletRequest request,HttpSession session) {
		XhJksq xhJksq = xhjksqManager.getXhJksq(Id);
		// 取得审核信息，目的是在页面显示
		List<XhCreditAudit> xhCreditAuditList = xhjksqManager
				.getXhCreditAudit(xhJksq);
		if (null != xhCreditAuditList && xhCreditAuditList.size() > 0) {
			XhCreditAudit xhcreditAudit = null;
			boolean first = true, second = true, third = true; 
			for (int i = 0; i < xhCreditAuditList.size(); i++) {
				xhcreditAudit = xhCreditAuditList.get(i);

				if ("1".equals(xhcreditAudit.getCreditType().toString())) {// 初审
					if (first) {
						model.addAttribute("firstXhCreditAudit", xhcreditAudit);
						first = false;
					}
				} else if ("2".equals(xhcreditAudit.getCreditType().toString())) {// 复审
					if (second) {
						model.addAttribute("secondXhCreditAudit", xhcreditAudit);
						second = false;
					}

				} else if ("3".equals(xhcreditAudit.getCreditType().toString())) {// 终审
					if (third) {
						model.addAttribute("lastXhCreditAudit", xhcreditAudit);
						third = false;
					}
				}
			}
		}
		if ("是".equals(xhJksq.getTogetherPerson())) {
			XhJksqTogether xhJksqTogether = xhjksqManager
					.getXhJksqTogether(xhJksq);
			model.addAttribute("xhJksqTogether", xhJksqTogether);
		}
		model.addAttribute("xhJksq", xhJksq);
		
		Long empId = getServiceId(xhJksq.getFirstServiceId());
		model.addAttribute("empId",empId);
		if("0".equals(xhJksq.getState())){
			model.addAttribute("empId2","");
		}else{
		    Long secondServiceId;
		    if(model.containsAttribute("storeToCenter")){//复核的时候，填入当前登入人ID
		        secondServiceId = this.getServiceId(xhJksq.getSecondServiceId());
		    }else{
		        secondServiceId = xhJksq.getSecondServiceId();
		    }
		    model.addAttribute("empId2",secondServiceId);
		}
		//非查看时才写session，否则不写值
		if(!model.containsAttribute("look")){
		    String token = generateRandomStr();
		    session.setAttribute(JKSQ_TOKEN, token);
		    if(logger.isInfoEnabled()){
		        logger.info("为用户：" + SpringSecurityUtils.getCurrentUserName() +" -- 创建token :  " + token);
		    }
		}
		return "jksq/newapply/xhJksqInput";
	}

	@RequestMapping(value = "/lookXhJksq/{Id}", method = RequestMethod.GET)
	public String lookJksqs(@PathVariable Long Id, Model model,HttpServletRequest request,HttpSession session) {
		model.addAttribute("look", "look");
		return edit(Id, model, request,session);
	}

	/**
	 * 保存共同还款人基础信息
	 * 
	 * @param xhjksqtogether
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/saveJksqTogetherInfo")
	public void saveJksqTogetherInfo(
			@ModelAttribute("xhjksqtogether") XhJksqTogether xhjksqtogether,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		// 得到当前登录用户
		String msg = "保存成功";
		// xhjksqManager.saveXhJksqTogether(request, xhjksqtogether);
		String jksqId = request.getParameter("jksqId");
		XhJksq xhJksq = xhNewJksqManager.getXhJksq(Long.parseLong(jksqId));
		xhjksqtogether.setXhjksq(xhJksq);
		xhNewJksqManager.saveTogtherPerson(xhJksq, xhjksqtogether);
		DwzResult success = new DwzResult("200", msg, "rel_listJksq", "",
				"closeCurrent", "");
		ServletUtils.renderJson(response, success);
	}

	/**
	 * 添加借款申请的共同还款人的新增页面
	 * 
	 * @param Id
	 *            借款申请ID
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addJksqTogether/{Id}", method = RequestMethod.GET)
	public ModelAndView addJksqTogether(@PathVariable Long Id,
			HttpServletRequest request, Model model) {
		XhJksq xhJksq = xhjksqManager.getXhJksq(Id);
		XhJksqTogether xhJksqTogether = xhjksqManager.getXhJksqTogether(xhJksq);
		model.addAttribute("together", xhJksqTogether);
		return new ModelAndView("jksq/newapply/jksqTogetherInput", "xhjksq",
				xhJksq);
	}

	/**
	 * 
	 * @param Id
	 * @param model
	 *            变更申请
	 * @return
	 */

	@RequestMapping(value = "/addNewjksqChange/{Id}", method = RequestMethod.GET)
	public String addNewjksqChange(@PathVariable Long Id, Model model) {

		XhJksq xhJksq = xhjksqManager.getXhJksq(Id);

		model.addAttribute("jksq", xhJksq);
		return "jksq/newapply/xhJksqApplyeditInput";
	}

	@RequestMapping(value = "/deleteJksqCredit/{Id}", method = RequestMethod.POST)
	public void deleteJksqCredit(@PathVariable("Id") Long Id,
			HttpServletRequest request, HttpServletResponse response) {
		xhJksqCreditsManager.deleteCredit(Id);
		DwzResult success = new DwzResult("200", "删除成功", "", "", "", "");
		ServletUtils.renderJson(response, success);

	}

	@RequestMapping(value = "/delete/{target}/{Id}", method = RequestMethod.POST)
	public void deleteRelatedInfos(@PathVariable("target") String target,
			@PathVariable("Id") Long Id, HttpServletRequest request,
			HttpServletResponse response) {
		if ("office".equals(target)) {
			xhJksqOfficeManager.delete(Id);
		} else if ("house".equals(target)) {
			xhJksqHouseManager.delete(Id);
		} else if ("company".equals(target)) {
			xhJksqCompanyManager.delete(Id);
		}
		ServletUtils.renderJson(response, "1");
	}

	@RequestMapping(value = "/saveNewjksqChange", method = RequestMethod.POST)
	public void saveNewjksqChange(@ModelAttribute("jksq") XhJksqApplyedit jksq,
			HttpServletRequest request, HttpServletResponse response) {
		// XhJksqApplyedit
		// jksqSave=xhApplayManager.getXhJksqApplyedit(jksq.getId());
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		XhJksq xhJksq = xhjksqManager.getXhJksq(jksq.getJksqId());
		xhJksq.setAppState("1");

		jksq.setState("0");
		jksq.setOperatorId(operator.getUserId());
		xhApplayManager.saveXhJksqApplyedit(jksq);
		DwzResult success = new DwzResult("200", "保存成功", "rel_listJksq", "",
				"closeCurrent", "");
		ServletUtils.renderJson(response, success);

	}

	@RequestMapping(value = "/spNewjksqChange/{Id}", method = RequestMethod.GET)
	public String spNewjksqChange(@PathVariable Long Id, Model model) {

		XhJksq xhJksq = xhjksqManager.getXhJksq(Id);
		XhJksqApplyedit edit = xhApplayManager.getXhJksqApplyeditByJksqId(Id);
		model.addAttribute("jksq", xhJksq);
		model.addAttribute("edit", edit);
		return "jksq/newapply/xhJksqApplyeditSpInput";
	}

	@RequestMapping(value = "/saveNewjksqSpChange", method = RequestMethod.POST)
	public void saveNewjksqSpChange(
			@ModelAttribute("jksq") XhJksqApplyedit jksq,
			HttpServletRequest request, HttpServletResponse response) {
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		XhJksq xhJksq = xhjksqManager.getXhJksq(jksq.getJksqId());
		XhJksqApplyedit edit = xhApplayManager.getXhJksqApplyedit(jksq.getId());
		edit.setState(jksq.getState());
		if (jksq.getState().equals("1")) {
			xhJksq.setAppState("0");
		} else {
			xhJksq.setAppState("2");
		}
		jksq.setOperatorId(operator.getUserId());
		xhApplayManager.saveXhJksqApplyedit(edit);
		DwzResult success = new DwzResult("200", "保存成功", "rel_listJksq", "",
				"closeCurrent", "");
		ServletUtils.renderJson(response, success);

	}

	@RequestMapping(value = "/listJksq")
	public String listJksq(HttpServletRequest request, HttpServletResponse response, Model model,HttpSession session) {
		// 得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		 if(logger.isDebugEnabled()){
             logger.debug(" 用户：" + SpringSecurityUtils.getCurrentUserName() +" 进入列表页面 --token :  " + session.getAttribute(JKSQ_TOKEN));
         }
		JdbcPage page = JdbcPageUtils.generatePage(request);
		// 借款申请状态
		Map<String, Object> filter = new HashMap<String, Object>();
		filter.put("attrType", "2");
		List<Attr> attrList = baseInfoManager.getAttrList(filter);
		request.setAttribute("attrList", attrList);

		Map<String, Object> map = WebUtils.getParametersStartingWith(request,
				"filter_");
		Employee emp = baseInfoManager.getUserEmployee();
		String loginName = operator.getCtiCode();
		map.put("loginName", loginName);
		map.put("emp", emp);

		// 贷款标志
		map.put("backup01", "CREDIT");
		List<Map<String, Object>> listJksq = new ArrayList<Map<String, Object>>();
		// 判断是否循环贷
		String type = request.getParameter("type");
		if (type != null && !"".equals(type)) {
			map.put("type", "10");
			model.addAttribute("type", type);
			listJksq = xhjksqManager.searchXhJksqLoop("loanApplyJksqList", map,
					page);
		} else {
			listJksq = xhjksqManager.searchXhJksq("loanApplyJksqList", map,
					page);
		}

		for (int index = 0; index < listJksq.size(); index++) {
			Map<String, Object> jksq = listJksq.get(index);
			jksq.put("canEdit", JksqStateUtils.isCanChange(jksq));
			jksq.put("canApplyChange", JksqStateUtils.isApplyChange(jksq));
			jksq.put("survey", surveyInfo(jksq));
			
		}

		model.addAttribute("listJksq", listJksq);
		model.addAttribute("page", page);

		model.addAttribute("organiId", request.getParameter("filter_organi.id"));
		model.addAttribute("organiName",
				request.getParameter("filter_organi.name"));

		RequestPageUtils.fillModelWithRequest(model, request);
		model.addAttribute("map", map);

		return "jksq/newapply/jksqNewList";
	}
	private String surveyInfo(Map<String, Object> jksq) {
        //31.A是等待外访的状态
	    if("31.A".equals(jksq.get("STATE") !=null ? jksq.get("STATE").toString() :"")){
	             // 查数据库 有外访任务的返回一个值，没有的返回另外一个值
	    	XhCjksqSurveyMain surveyMain = surveyMainManager.
	    						getXhCjksqSurveyMainFromJksqId(Long.parseLong(jksq.get("ID").toString()));
	    	if(surveyMain.getId()!=null){
	    		return "1";
	    	}
	    }
	    
	    return "0";
    }

	/**
	 * 待复核借款申请页
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/listJksqCheck")
	public String listJksqFh(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		// 得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		JdbcPage page = JdbcPageUtils.generatePage(request);
		// 借款申请状态
		Map<String, Object> filter = new HashMap<String, Object>();
		filter.put("attrType", "2");
		List<Attr> attrList = baseInfoManager.getAttrList(filter);
		request.setAttribute("attrList", attrList);

		Map<String, Object> map = WebUtils.getParametersStartingWith(request,"filter_");
		Employee emp = baseInfoManager.getUserEmployee();
		String loginName = operator.getCtiCode();
		map.put("loginName", loginName);
		map.put("emp", emp);
		List<Map<String, Object>> listJksq = xhNewJksqManager.searchXhJksq(map,page);
		model.addAttribute("listJksq", listJksq);
		model.addAttribute("page", page);
		model.addAttribute("organiId", request.getParameter("filter_organi.id"));
		model.addAttribute("organiName",request.getParameter("filter_organi.name"));
		RequestPageUtils.fillModelWithRequest(model, request);
		model.addAttribute("map", map);

		return "jksq/newapply/jksqNewListCheck";
	}
	
	
	/**
	 * 借款变更审批列表页
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/listChangeAuditJksq")
	public String listChangeAuditJksq(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		// 得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();

		JdbcPage page = JdbcPageUtils.generatePage(request);

		// 借款申请状态
		Map<String, Object> filter = new HashMap<String, Object>();
		filter.put("attrType", "1");
		List<Attr> attrList = baseInfoManager.getAttrList(filter);
		request.setAttribute("attrList", attrList);

		Map<String, Object> map = WebUtils.getParametersStartingWith(request,
				"filter_");
		Employee emp = baseInfoManager.getUserEmployee();
		String loginName = operator.getCtiCode();
		map.put("loginName", loginName);
		map.put("emp", emp);
		map.put("backup01", "CREDIT");
		List<Map<String, Object>> jksqChangeExam = xhjksqManager
				.searchXhJksqChangeExam("queryJksqList", map, page);
		model.addAttribute("jksqChangeExam", jksqChangeExam);
		model.addAttribute("page", page);

		RequestPageUtils.fillModelWithRequest(model, request);
		return "jksq/newapply/changeAuditJksqList";
	}
	
	@RequestMapping(value = "/checkBlackList", method = RequestMethod.POST)
	public void checkBlackList(
			HttpServletRequest request, HttpServletResponse response) {
		
		// 加入黑名单验证，如果是黑名单客户，拒绝进件
		boolean isBlackListCustomer = false;
		String zjhm = request.getParameter("zjhm");
		if (null != zjhm){
			List blackList=xhBlackListManager.findBlackListBySFId(zjhm.trim());
			if(blackList.size()>0){
				isBlackListCustomer=true;
			}
		}
			
		if(isBlackListCustomer){
			DwzResult success = new DwzResult("500", "该客户为黑名单客户，不符合进件条件！", "", "", "", "");
			ServletUtils.renderJson(response, 1);
		}
	}
	
	/**
	 * ajax核实证件信息 MDY 2013-7-24
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/isIdInfoByZjhm")
	@ResponseBody
	public Map<String, Object> isIdInfoByZjhm(HttpServletRequest request){
		Map<String, Object> modelMap = xhjksqManager.isIdInfoByZjhm(request);
		return modelMap;
	}
	
	/**
	 * 加载相同证件号信息
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/loadIdInfo")
	public String loadIdInfo(HttpServletRequest request, Model model) {
		JdbcPage page = new JdbcPage();
		Map<String, Object> map = new HashMap<String, Object>();
		String zjhm = request.getParameter("cradId").toString();//证件号码
		String id = request.getParameter("ID").toString();//jksqid
		List<Map<String, Object>> listJksq = new ArrayList<Map<String,Object>>();
		if(!"".equals(id)){
			map.put("id", id);
			listJksq = xhjksqManager.searchXhJksq("queryJksqList", map, page);
			/*for(int i=0;i<listJksq.size();i++){
				Map<String, Object> jksq = listJksq.get(i);
				if(!"101".equals(jksq.get("STATE")) ){
					
				}
			}*/
			model.addAttribute("result",listJksq);
			model.addAttribute("checkRelation","1");
			model.addAttribute("idCard",zjhm);
			return "jksq/listIdInfo";
		}
		map.put("zjhm", zjhm);
		listJksq = xhjksqManager.searchXhJksq("queryJksqList", map, page);
		model.addAttribute("result",listJksq);
		model.addAttribute("checkRelation","0");
		//return "jksq/listIdInfo";
		return "jksq/newapply/relatedInfos/relatedItems";
	}
	
	/**
	 * 借款端任务交割历史 MDY
	 * @param request
	 * @param response
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/listLoanTask")
	public String listLoanTask(HttpServletRequest request, HttpServletResponse response, Model model,HttpSession session) {
		JdbcPage page = JdbcPageUtils.generatePage(request);
		Map<String, Object> map = WebUtils.getParametersStartingWith(request, "filter_");
		List<Map<String, Object>> listLoanTask = xhjksqManager.searchLoanTask("loanTaskList", map, page);
		model.addAttribute("listLoanTask", listLoanTask);
		model.addAttribute("page", page);
		model.addAttribute("map", map);
		return "jksq/newapply/listLoanTask";
	}
	
	/**
	 * 初始化任务交割添加 MDY
	 * @return
	 */
	@RequestMapping(value="/addLoanTask", method=RequestMethod.GET)
	public ModelAndView addLoanTask(){
		return new ModelAndView("jksq/newapply/loanTask-input", "loanTaskDeliv", new LoanTaskDeliv());
	}
	
	/**
	 * 保存任务交割，并执行存储过程 MDY
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/saveLoanTask",method=RequestMethod.POST)
	public String saveLoanTask(HttpServletRequest request, HttpServletResponse response){
		String[] res = xhjksqManager.saveLoanTask(request);
		xhjksqManager.executeCall(res);
		DwzResult success = new DwzResult("200","保存成功","rel_listLoanTask","","closeCurrent","");
		ServletUtils.renderJson(response, success);
		return null;
	}	
	
	
	/**
     * 加载相同证件号信息
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "/onlyRelatedPage")
    public String onlyRelatedPage(HttpServletRequest request, Model model) {
        return "jksq/newapply/relatedInfos/relatedItems";
    }
	
    /**
     * 返回相关的记录信息	
     *
     * @param request
     * @param response
     * @author xjs
     * @date 2013-11-2 下午9:31:07
     */
    @RequestMapping(value="/loadRelatedItems")
    public void saveBasePosition(HttpServletRequest request, HttpServletResponse response){
        String cardId = request.getParameter("cardId");
        String myownId = request.getParameter("myownId");
        Map<String,String> params = new HashMap<String,String>();
        params.put("cardId", cardId);
        params.put("myownId", myownId);
        Map<String, Object> items = xhNewJksqManager.getRelatedItems(params);
        ServletUtils.renderJson(response, items);
    }
	
	
}

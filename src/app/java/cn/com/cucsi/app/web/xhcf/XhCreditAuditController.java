package cn.com.cucsi.app.web.xhcf;

import java.io.File;
import java.util.ArrayList;
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

import cn.com.cucsi.app.entity.baseinfo.Attr;
import cn.com.cucsi.app.entity.baseinfo.City;
import cn.com.cucsi.app.entity.baseinfo.Employee;
import cn.com.cucsi.app.entity.baseinfo.MateData;
import cn.com.cucsi.app.entity.xhcf.XhCreditAudit;
import cn.com.cucsi.app.entity.xhcf.XhCreditTaskAssign;
import cn.com.cucsi.app.entity.xhcf.XhJksq;
import cn.com.cucsi.app.entity.xhcf.XhUploadFiles;
import cn.com.cucsi.app.service.PublicService;
import cn.com.cucsi.app.service.baseinfo.BaseInfoManager;
import cn.com.cucsi.app.service.excel.StatisticalService;
import cn.com.cucsi.app.service.loan.XhJksqManager;
import cn.com.cucsi.app.service.security.OperatorDetails;
import cn.com.cucsi.app.service.xhcf.XhCreditAuditManager;
import cn.com.cucsi.app.service.xhcf.XhCreditTaskAssignManager;
import cn.com.cucsi.app.web.InitSetupListener;
import cn.com.cucsi.app.web.util.FileUtil;
import cn.com.cucsi.app.web.util.JdbcPageUtils;
import cn.com.cucsi.app.web.util.JksqStateUtils;
import cn.com.cucsi.app.web.util.P2PJsonUtils;
import cn.com.cucsi.app.web.util.RequestPageUtils;
import cn.com.cucsi.app2.entity.xhcf.XhCjksqSurveyMain;
import cn.com.cucsi.app2.service.xhcf.XhCjksqSurveyMainManager;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils;
import cn.com.cucsi.core.utils.PropertiesUtils;
import cn.com.cucsi.core.web.DwzResult;
import cn.com.cucsi.core.web.ServletUtils;

/**
 * 
 * @author Songjf
 * @date 2012-12-14
 */
@Controller
@RequestMapping(value = "/loan")
public class XhCreditAuditController {

    @Autowired
    private XhCjksqSurveyMainManager surveyMainManager;
    
	private XhCreditAuditManager xhCreditAuditManager;
	private XhCreditTaskAssignManager xhCreditTaskAssignManager;
	private XhJksqManager loanApplyManager;
	private BaseInfoManager baseInfoManager;
	private StatisticalService statisticalService;

	@Autowired
	public void setStatisticalService(StatisticalService statisticalService) {
		this.statisticalService = statisticalService;
	}

	@Autowired
	public void setXhCreditTaskAssignManager(
			XhCreditTaskAssignManager xhCreditTaskAssignManager) {
		this.xhCreditTaskAssignManager = xhCreditTaskAssignManager;
	}

	@Autowired
	public void setXhCreditAuditManager(
			XhCreditAuditManager xhCreditAuditManager) {
		this.xhCreditAuditManager = xhCreditAuditManager;
	}

	/**
	 * 通过状态查询借款申请列表,流程处理各步骤可以通用
	 * 
	 * @param request
	 * @param model
	 * @return
	 */

	@RequestMapping(value = "/listLoanApplyByState/{state}")
	public String listLoanApplyeByState(@PathVariable Integer state,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {

		// 处理分页的参数
		JdbcPage page = JdbcPageUtils.generatePage(request);

		// 封装查询条件
		Map<String, Object> map = WebUtils.getParametersStartingWith(request,
				"filter_");
		if (state == 31) {
			map.put("state_injksp", "31,31.A,31.C,32#B");// 加入等待外访的状态,及外访已回状态
		} else if (state == 32) {
			map.put("state_injksp", "32,34#J,33#B,32#Q");// 加入初审拒绝代审批申请,及终审退回复审状态
		} else if (state == 33) {
			map.put("state_injksp", "33,33#Q");
		} else {
			map.put("state_injksp", state);
		}

		// ============目前查询存在权限的，问题前端个贷团队经理及客服人员只能查看所在团队的，城市经理查看其管辖城市的,总部能查看所有的=====

		// =============考虑员工表里面加所在部门,这样总公司部门
		// 能与下级机构区分开，总公司对应一个机构，每个机构内部的权限可以通过部门或职务来控制

		// ============如果state是初审状态,初审人员只能查看自己被分派的初审任务====================

		RequestPageUtils.fillModelWithRequest(model, request);

		// 查找借款申请

		List<Map<String, Object>> listJksq = null;
		
		if (state == 30) {
			listJksq = loanApplyManager.searchXhJksq("loanApplyJksqList", map, page);
		}else if (state == 31) {
			Employee emp = baseInfoManager.getUserEmployee();
			if (emp.getId() != 1) {
				map.put("FIRST_EMPLOYEEID", emp.getId());
			}
			listJksq = loanApplyManager.getXhJksq(map, page);
			if(listJksq != null){
    			for (int index = 0; index < listJksq.size(); index++) {
    	            Map<String, Object> jksq = listJksq.get(index);
    	            jksq.put("survey", surveyInfo(jksq));
    	            
    	        }
			}
		} else if (state == 32) {
			Employee emp = baseInfoManager.getUserEmployee();
			if (emp.getId() != 1) {
				map.put("SECOND_EMPLOYEEID", emp.getId());
			}
			listJksq = loanApplyManager.getXhJksq(map, page);
		} else {
			Employee emp = baseInfoManager.getUserEmployee();
			if (emp.getId() != 1) {
				map.put("FINAL_EMPLOYEEID", emp.getId());
			}
			listJksq = loanApplyManager.getXhJksq(map, page);
		}
		model.addAttribute("organiId", request.getParameter("filter_organi.id"));
		model.addAttribute("organiName",
				request.getParameter("filter_organi.name"));
		model.addAttribute("listJksq", listJksq);
		model.addAttribute("map", map);
		model.addAttribute("page", page);
		String returnUrl = null;
		switch (state) {
		case 30:
			map.put("minValue", "30");
			map.put("maxValue", "30");
			returnUrl = "creditAudit/listAuditTaskAssin";
			break;
		case 31:
			map.put("minValue", "31");
			map.put("maxValue", "31.C");
			returnUrl = "creditAudit/listFirstAuditTask";
			break;
		case 32:
			map.put("minValue", "32");
			map.put("maxValue", "32");
			returnUrl = "creditAudit/listSecondAuditTask";
			break;
		case 33:
			map.put("minValue", "33");
			map.put("maxValue", "33");
			returnUrl = "creditAudit/listFinalAuditTask";
			break;
		}
		map.put("attrType", "2");
		List<Attr> attrList = baseInfoManager.getAttrList(map);
		request.setAttribute("attrList", attrList);
		return returnUrl;
	}

	private String surveyInfo(Map<String, Object> jksq) {
        //31.A是等待外访的状态
        if("31.C".equals(jksq.get("STATE") !=null ? jksq.get("STATE").toString() :"")){
                 // 查数据库 有外访任务的返回一个值，没有的返回另外一个值
            XhCjksqSurveyMain surveyMain = surveyMainManager.
                                getXhCjksqSurveyMainFromJksqId(Long.parseLong(jksq.get("ID").toString()));
            if(surveyMain.getId()!=null){
                return "1";
            }
        }
        
        return "0";
    }
	
	@Autowired
	public void setXhjksqManager(XhJksqManager loanApplyManager) {
		this.loanApplyManager = loanApplyManager;
	}

	@Autowired
	public void setBaseInfoManager(BaseInfoManager baseInfoManager) {
		this.baseInfoManager = baseInfoManager;
	}

	/**
	 * 根据类型判断不同的信审阶段,查看该阶段的信审结果 说明：初审阶段，不同的初审人员只能够看到自己审核过的记录，复审、终审无限制
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/listCreditAuditResultByType/{creditType}")
	public String listCreditAuditResultByState(@PathVariable String creditType,
			HttpServletRequest request, Model model) {
		// 处理分页的参数
		Page<XhCreditAudit> page = new RequestPageUtils<XhCreditAudit>()
				.generatePage(request);

		Map<String, Object> map = ServletUtils.getParametersStartingWith2(
				request, "filter_");
		// 初审阶段限制查询条件
		Employee emp = baseInfoManager.getUserEmployee();

		if (creditType.equals("1")) {
			if (1 != emp.getId()) {
				map.put("createBy", emp.getName());
		}
	}
			
		
		map.put("creditType", creditType);
		xhCreditAuditManager.searchXhCreditAudit(page, map);// mdy

		model.addAttribute("page", page);
		model.addAttribute("map", map);

		String returnUrl = null;
		switch (new Integer(creditType)) {
		case 1:
			map.put("minValue", "32");
			returnUrl = "creditAudit/listFirstAuditResult";
			break;
		case 2:
			map.put("minValue", "33");
			returnUrl = "creditAudit/listSecondAuditResult";
			break;
		case 3:
			map.put("minValue", "34");
			returnUrl = "creditAudit/listFinalAuditResult";
			break;
		}
		map.put("attrType", "2");
		List<Attr> attrList = baseInfoManager.getAttrList(map);
		request.setAttribute("attrList", attrList);
		return returnUrl;

	}

	/**
	 * 保存初审、复审及终审的结果
	 * 
	 * @param Id
	 * @param xhCreditAudit
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/saveAuditResult/{Id}", method = RequestMethod.POST)
	public String saveXhCreditAudit(@PathVariable Long Id,
			@ModelAttribute("xhCreditAudit") XhCreditAudit xhCreditAudit,
			HttpServletRequest request, HttpServletResponse response) {

		xhCreditAudit.setLoanApply(loanApplyManager.getXhJksq(Id));
		xhCreditAudit.setCreditType(request.getParameter("creditType"));
		// if(xhCreditAudit.getCreditType().equals("1")){
		// Map<String, Object> filter = new HashMap();
		// filter.put("loanApplyId", Id);
		//
		//
		// List<Map<String, Object>> firstCreditTasks =
		// xhCreditTaskAssignManager
		// .searchXhCreditTaskAssign(
		// "queryXhCreditTaskAssignList", filter);
		// if (firstCreditTasks.size() != 0) {
		// String applyIds = "";
		// for (Map task : firstCreditTasks) {
		// task.get("ID")
		// xhCreditTaskAssignManager.getXhCreditTaskAssign(id)
		//
		// }
		// }
		// }
		xhCreditAudit.setCreditResult(request.getParameter("creditResult"));

		xhCreditAuditManager.saveXhCreditAudit(xhCreditAudit, request);

		DwzResult success = new DwzResult("200", "保存成功",
				"rel_listLoanApplyByState", "", "closeCurrent", "");
		ServletUtils.renderJson(response, success);

		return null;
	}

	/**
	 * 进入初审页面
	 * 
	 * @param Id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addFirstAuditResult/{Id}", method = RequestMethod.GET)
	public ModelAndView addFirstAuditResult(@PathVariable Long Id, Model model,
			HttpServletRequest request) {
		XhJksq loanApply = loanApplyManager.getXhJksq(Id);
		request.setAttribute("loanApply", loanApply);
		request.setAttribute("loanApplyId", Id);
		List<XhCreditAudit> xhCreditAuditList = loanApplyManager
				.getXhCreditAudit(loanApply);
		if (null != xhCreditAuditList && xhCreditAuditList.size() > 0) {
			XhCreditAudit xhcreditAudit = null;
			boolean first = true, second = true, third = true;
			for (int i = 0; i < xhCreditAuditList.size(); i++) {
				xhcreditAudit = xhCreditAuditList.get(i);

				if ("1".equals(xhcreditAudit.getCreditType().toString())) {// 初审
					if (first) {
						model.addAttribute("xhCreditAudit", xhcreditAudit);
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
		// 信审综合利率,取消下拉列表，改为input输入
		// List<MateData> zhflMateDateList =
		// baseInfoManager.getTypeByCode("10004");
		// model.addAttribute("zhflList", zhflMateDateList);
		return new ModelAndView("creditAudit/addFirstAuditResult",
				"xhCreditAudit", new XhCreditAudit());
	}

	/**
	 * 
	 * 
	 * @param Id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/lookFirstAuditResult/{Id}", method = RequestMethod.GET)
	public ModelAndView lookFirstAuditResult(@PathVariable Long Id,
			Model model, HttpServletRequest request) {
		XhJksq loanApply = loanApplyManager.getXhJksq(Id);
		request.setAttribute("loanApply", loanApply);
		request.setAttribute("loanApplyId", Id);
		// // 信审综合利率
		// List<MateData> zhflMateDateList =
		// baseInfoManager.getTypeByCode("10004");
		// model.addAttribute("zhflList", zhflMateDateList);
		return new ModelAndView("creditAudit/addFirstAuditResult",
				"xhCreditAudit", new XhCreditAudit());
	}

	/**
	 * 进入复审页面
	 * 
	 * @param Id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addSecondAuditResult/{Id}", method = RequestMethod.GET)
	public ModelAndView addSecondAuditResult(@PathVariable Long Id,
			Model model, HttpServletRequest request) {
		XhJksq loanApply = loanApplyManager.getXhJksq(Id);
		/*
		 * Map map = new HashMap(); map.put("loanApplyId", Id);
		 * map.put("creditType", "1"); List<XhCreditAudit> firstAuditList =
		 * this.xhCreditAuditManager.getAllXhCreditAudit(map);
		 */
		request.setAttribute("loanApply", loanApply);
		request.setAttribute("loanApplyId", Id);
		// 信审综合利率
		// List<MateData> zhflMateDateList =
		// baseInfoManager.getTypeByCode("10004");
		// model.addAttribute("zhflList", zhflMateDateList);
		// model.addAttribute("firstAuditList",firstAuditList);
		return new ModelAndView("creditAudit/addSecondAuditResult",
				"xhCreditAudit", new XhCreditAudit());
	}

	/**
	 * 进入终审页面
	 * 
	 * @param Id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addFinalAuditResult/{Id}", method = RequestMethod.GET)
	public ModelAndView add(@PathVariable Long Id, Model model,
			HttpServletRequest request) {
		request.setAttribute("loanApplyId", Id);
		XhJksq loanApply = loanApplyManager.getXhJksq(Id);
		request.setAttribute("loanApply", loanApply);
		// 信审综合利率
		// List<MateData> zhflMateDateList =
		// baseInfoManager.getTypeByCode("10004");
		// model.addAttribute("zhflList", zhflMateDateList);
		return new ModelAndView("creditAudit/addFinalAuditResult",
				"xhCreditAudit", new XhCreditAudit());
	}

	/**
	 * 已分派信神任务列表
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/listAuditTaskAssignResult")
	public String listXhCreditTaskAssign(HttpServletRequest request, Model model) {
		// 处理分页的参数
		Page<XhCreditTaskAssign> page = new RequestPageUtils<XhCreditTaskAssign>()
				.generatePage(request);

		// ================待添加过滤条件 ： 初审人员自己审批的

		Map<String, Object> map = ServletUtils.getParametersStartingWith2(
				request, "filter_");

		Page<XhCreditTaskAssign> creditTaskAssign = xhCreditTaskAssignManager
				.searchXhCreditTaskAssign(page, map);

		map.put("attrType", "2");
		map.put("minValue", "31");
		List<Attr> attrList = baseInfoManager.getAttrList(map);
		request.setAttribute("attrList", attrList);
		model.addAttribute("creditTaskAssigns", creditTaskAssign);
		model.addAttribute("page", page);
		model.addAttribute("map", map);
		return "creditAudit/listAuditTaskAssignResult";

	}

	/**
	 * 上传文件
	 * 
	 * @param Id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/uploadFile/{Id},{upflag}", method = RequestMethod.GET)
	public String uploadFile(@PathVariable Long Id,
			@PathVariable String upflag, HttpServletRequest request, Model model) {
		// 得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		if (operator == null) {
			return "redirect:../../login";
		}
		request.setAttribute("Id", Id + "," + upflag);
		request.setAttribute("upflag", upflag);
		request.setAttribute("upUrl", "loan/saveUploadFile");
		return "creditAudit/uploadFiles";
	}

	/**
	 * 保存上传文件
	 * 
	 * @param Id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveUploadFile")
	public void saveUploadFile(HttpServletRequest request,
			HttpServletResponse response) {
		/*
		 * List<Map<String, String>> fileName = PropertiesUtils.upFile(request,
		 * "upload"); boolean flag =
		 * xhCreditTaskAssignManager.uploadFile(request, fileName); String msg =
		 * "保存失败"; if (flag) { msg = "保存成功"; } DwzResult success = new
		 * DwzResult("200", msg, "rel_listLoanApplyByState", "", "closeCurrent",
		 * ""); ServletUtils.renderJson(response, success); return null;
		 */
		List<Map<String, String>> fileName;
		try {
			fileName = PropertiesUtils.upFileFullFunctions(request, "upload");
			ServletUtils.renderJson(response, fileName);
		} catch (Exception e) {
			ServletUtils.renderJson(response, "0");
		}
	}

	/**
	 * 保存上传文件
	 * 
	 * @param Id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveUploadFileBack")
	public void saveUploadFileBack(HttpServletRequest request,
			HttpServletResponse response) {
		String files = request.getParameter("fileName");
		List<Map<String, String>> fileName = P2PJsonUtils.filestrToMap(files);
		xhCreditTaskAssignManager.uploadFile(request, fileName);
		ServletUtils.renderJson(response, "1");
	}

	@RequestMapping(value = "/downLoadFile/{Id},{downFlag}")
	public String downLoadFile(@PathVariable Long Id,
			@PathVariable String downFlag, HttpServletRequest request,
			HttpServletResponse response, Model model) {

		if (null != downFlag && !"".equals(downFlag)) {
			String[] upflag = downFlag.split(":");
			for (int i = 0; i < upflag.length; i++) {
				if ("1".equals(upflag[i].trim())) {
					List<XhUploadFiles> files = xhCreditTaskAssignManager
							.downLoadFile(request, Id, upflag[i]);
					model.addAttribute("first", files);
				} else if ("2".equals(upflag[i].trim())) {
					List<XhUploadFiles> files = xhCreditTaskAssignManager
							.downLoadFile(request, Id, upflag[i]);
					model.addAttribute("second", files);
				} else if ("3".equals(upflag[i].trim())) {
					List<XhUploadFiles> files = xhCreditTaskAssignManager
							.downLoadFile(request, Id, upflag[i]);
					model.addAttribute("last", files);
				} else if ("0".equals(upflag[i].trim())) {// 签约
					List<XhUploadFiles> files = xhCreditTaskAssignManager
							.downLoadFile(request, Id, upflag[i]);
					model.addAttribute("signed", files);
				} else if ("sx".equals(upflag[i].trim())) {// 授信
					upflag[i] = "授信";
					List<XhUploadFiles> files = xhCreditTaskAssignManager
							.downLoadFile(request, Id, upflag[i]);
					model.addAttribute("accredited", files);
				} else if ("wf".equals(upflag[i].trim())) {// 授信
					upflag[i] = "外访";
					List<XhUploadFiles> files = xhCreditTaskAssignManager
							.downLoadFile(request, Id, upflag[i]);
					model.addAttribute("waifang", files);
				}
			}
		}
		return "creditAudit/downloadFile";
	}

	@RequestMapping(value = "/downFile/{Id}")
	public String exportProref(@PathVariable Long Id,
			HttpServletRequest request, HttpServletResponse response) {
		XhUploadFiles xhUploadFiles = xhCreditTaskAssignManager
				.getXhUploadFiles(Id);
		String base = InitSetupListener.filePath + xhUploadFiles.getFilepath();// request.getRealPath(xhUploadFiles.getFilepath());
		// System.out.println("base===>" + base);
		String fileName = xhUploadFiles.getNewfilename();
		// String ext =
		// fileName.substring(fileName.lastIndexOf("."),fileName.length());
		// System.out.println("ext========"+ext);
		// String path = base + File.separator + fileName;
		String filePath = base + File.separator;
		String downLoadPath = filePath + fileName;

		FileUtil.downLoad(downLoadPath, xhUploadFiles.getFilename(), request,
				response);
		return null;
	}

	/**
	 * 显示初审信息
	 * 
	 * @param Id
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/listFirstAuditHistory/{Id}/{creditType}")
	public String listFirstAuditHistory(@PathVariable Long Id,
			@PathVariable String creditType, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Map map = new HashMap();
		map.put("loanApplyId", Id);
		map.put("creditType", creditType);
		List<XhCreditAudit> firstAuditList = this.xhCreditAuditManager
				.getAllXhCreditAudit(map);
		XhCreditAudit xhCreditAudit = null;
		if (firstAuditList != null && firstAuditList.size() > 0) {
			xhCreditAudit = firstAuditList.get(0);
		}
		model.addAttribute("xhCreditAudit", xhCreditAudit);
		// XhCreditAudit a;
		return "creditAudit/listFirstAuditHistory";
	}

	/**
	 * 显示信审结果到门店并说明附加放款条件
	 * 
	 * @param Id
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/showAduitResultAndNotice/{Id}/{creditType}")
	public String showAduitResultAndNotice(@PathVariable Long Id,
			@PathVariable String creditType, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Map map = new HashMap();
		map.put("loanApplyId", Id);
		map.put("creditType", creditType);
		List<XhCreditAudit> firstAuditList = this.xhCreditAuditManager
				.getAllXhCreditAudit(map);
		XhCreditAudit xhCreditAudit = null;
		if (firstAuditList != null && firstAuditList.size() > 0) {
			xhCreditAudit = firstAuditList.get(0);
		}
		model.addAttribute("xhCreditAudit", xhCreditAudit);
		// XhCreditAudit a; showAuditResultAndNotice
		return "creditAudit/showAuditResultAndNotice";
	}

	@RequestMapping(value = "/changeStateAfterUpload/{Id}")
	public void changeStateAfterUpload(@PathVariable Long Id,
			HttpServletResponse response) {
		XhJksq loanApply = loanApplyManager.getXhJksq(Id);
		if (loanApply.getState().equals("32#T")) {
			loanApply.setState("32#Q");
			loanApplyManager.saveXhJksq(loanApply);
		}
		if (loanApply.getState().equals("33#T")) {
			loanApply.setState("33#Q");
			loanApplyManager.saveXhJksq(loanApply);
		}

		DwzResult success = new DwzResult("200", "保存成功", "rel_listJksq", "",
				"closeCurrent", "");
		ServletUtils.renderJson(response, success);

	}

	
	
	/**
	 * 进入补齐资料审核页面
	 * 
	 * @param Id
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/showCheckAduitFile/{Id}/{creditType}")
	public String showCheckAduitFile(@PathVariable Long Id,
			@PathVariable String creditType, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Map map = new HashMap();
		map.put("loanApplyId", Id);
		map.put("creditType", creditType);
		List<XhCreditAudit> AuditResultList = this.xhCreditAuditManager
				.getAllXhCreditAudit(map);
		XhCreditAudit xhCreditAudit = null;
		if (AuditResultList != null && AuditResultList.size() > 0) {
			xhCreditAudit = AuditResultList.get(0);
		}
		model.addAttribute("xhCreditAudit", xhCreditAudit);
		// XhCreditAudit a; showAuditResultAndNotice
		return "creditAudit/checkAduitFileToPost";
	}
	
	
	
	/**
	 * 补齐材料后审核
	 * 
	 * @param Id
	 * @param response
	 */
	@RequestMapping(value = "/checkAduitFileToPost/{Id}")
	public void checkAduitFileToPost(@PathVariable Long Id,
			HttpServletRequest request, HttpServletResponse response) {
		XhJksq loanApply = loanApplyManager.getXhJksq(Id);
		String result = request.getParameter("isOk");
		if (loanApply.getState().equals("32#Q")) {
			if (result.equals("0"))
				loanApply.setState("32#T");
			else if (result.equals("1"))
				loanApply.setState("50");

		} else if (loanApply.getState().equals("33#Q")) {
			if (result.equals("0"))
				loanApply.setState("33#T");
			else if (result.equals("1"))
				loanApply.setState("50");
		}
		loanApplyManager.saveXhJksq(loanApply);
		DwzResult success = new DwzResult("200", "保存成功", "rel_listLoanApplyByState", "",
				"closeCurrent", "");
		ServletUtils.renderJson(response, success);

	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/listByAuditPerson")
	public String listFirstAuditHistory(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		// 查询已经审查过的记录 --通过名字查询
		List<Employee> allAuditPersons = baseInfoManager.findEmpByPosId(Long
				.parseLong("4"));
		model.addAttribute("allAuditPersons", allAuditPersons);

		return "creditAudit/listAuditByAuditPersonNotHandleBefore";
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/listByAuditPersonNext")
	public String listByAuditPersonNext(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		// 查询已经审查过的记录 --通过名字查询
		String auditPersonId = request.getParameter("filter_auditPerson");
		String creatTime = request.getParameter("filter_createTime");
		model.addAttribute("filter_auditPerson", auditPersonId);
		model.addAttribute("filter_successState",
				request.getParameter("filter_successState"));
		model.addAttribute("filter_auditPersonName",
				request.getParameter("filter_auditPersonName"));
		model.addAttribute("filter_createTime", creatTime);
		// List<Employee> allAuditPersons = baseInfoManager.findEmpByPosId(Long
		// .parseLong("4"));
		// model.addAttribute("allAuditPersons", allAuditPersons);
		if ("1".equals(request.getParameter("filter_successState"))) {
			if (null != auditPersonId && !"".endsWith(auditPersonId)) {
				Page<XhCreditAudit> page = new RequestPageUtils<XhCreditAudit>()
						.generatePage(request);

				Map<String, Object> map = new HashMap<String, Object>();
				// 初审阶段限制查询条件
				String empName = baseInfoManager.getEmployee(
						Long.parseLong(auditPersonId)).getName();
				if (null != creatTime && !"".equals(creatTime)) {
					map.put("firstAuditTime", creatTime);
				}
				map.put("createBy", empName);
				map.put("creditType", "1");
				xhCreditAuditManager.searchXhCreditAudit(page, map);
				String tgl = xhCreditAuditManager
						.searchXhCreditAuditTongGuo(map);
				// if (searchXhCreditAuditTongGuo.size() == 1) {
				model.addAttribute("tgl", tgl);
				// }
				model.addAttribute("page", page);
			}
			return "creditAudit/listAuditByAuditPerson";
		} else {
			// 查询没有审核的记录
			// 处理分页的参数
			if (null != auditPersonId && !"".endsWith(auditPersonId)) {
				if (StringUtils.isEmpty(auditPersonId))// 首次进入直接挑战页面
					return "creditAudit/listAuditByAuditPersonNotHandle";

				JdbcPage page = JdbcPageUtils.generatePage(request);
				// 封装查询条件
				Map<String, Object> map = WebUtils.getParametersStartingWith(
						request, "filter_");
				map.put("state", 31);
				map.put("FIRST_EMPLOYEEID", auditPersonId);
				/*
				 * ---修改in(..........................)
				 * 
				 * Map<String, Object> filter = new HashMap<String, Object>();
				 * filter.put("firstAuditEmployeeId",
				 * Long.parseLong(auditPersonId));
				 * 
				 * List<Map<String, Object>> firstCreditTasks =
				 * xhCreditTaskAssignManager
				 * .searchXhCreditTaskAssign("queryXhCreditTaskAssignList",
				 * filter); if (firstCreditTasks.size() != 0) { String applyIds
				 * = ""; for (Map task : firstCreditTasks) {
				 * 
				 * applyIds = applyIds + task.get("LOAN_APPLY_ID") + ",";
				 * 
				 * } applyIds = applyIds.substring(0,
				 * applyIds.lastIndexOf(',')); // applyIds = applyIds + ")";
				 * map.put("applyIds", applyIds);
				 * 
				 * } else { map.put("applyIds", 0);// }
				 */
				// 查找借款申请
				List<Map<String, Object>> listJksq = loanApplyManager
						.getXhJksq(map, page);
				// 封装页面过滤条件
				// 证件类型
				/*
				 * ---修改in(..........................) List<MateData>
				 * zjlxMateDateList = baseInfoManager.getTypeByCode("0005");
				 * model.addAttribute("zjlx0005", zjlxMateDateList);// ? String
				 * filter_zjlx = request.getParameter("filter_zjlx"); if (null
				 * != filter_zjlx && !"".equals(filter_zjlx)) {
				 * model.addAttribute("zjlx", filter_zjlx); }
				 * 
				 * List<City> province = baseInfoManager.getSuggestCity("0");
				 * request.setAttribute("province", province);
				 */
				model.addAttribute("listJksq", listJksq);
				model.addAttribute("page", page);
				/*
				 * model.addAttribute("filter_jkrxm",
				 * request.getParameter("filter_jkrxm"));
				 * model.addAttribute("filter_telephone",
				 * request.getParameter("filter_telephone"));
				 * model.addAttribute("filter_zjhm",
				 * request.getParameter("filter_zjhm"));
				 * model.addAttribute("filter_jkType",
				 * request.getParameter("filter_jkType"));
				 * model.addAttribute("filter_state",
				 * request.getParameter("filter_state"));
				 * model.addAttribute("filter_province",
				 * request.getParameter("filter_province"));
				 * model.addAttribute("filter_city",
				 * request.getParameter("filter_city")); String filter_province
				 * = request.getParameter("filter_province"); if (null !=
				 * filter_province && !"".equals(filter_province)) { List<City>
				 * city = baseInfoManager.getSuggestCity(filter_province);
				 * request.setAttribute("city", city); }
				 */
			}
			return "creditAudit/listAuditByAuditPersonNotHandle";
		}
	}

	/**
	 * 設置進件狀態為等待外訪
	 * 
	 * @param Id
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/waitingVisitResult/{Id}", method = RequestMethod.POST)
	public ModelAndView waitingVisitResult(@PathVariable Long Id,
			HttpServletResponse response) {

		boolean isSuccess = false;
		try {
			xhCreditAuditManager.waitingVisitResult(Id);
			isSuccess = true;
		} catch (Exception e) {

			e.printStackTrace();
		}

		DwzResult success = null;
		if (isSuccess) {
			success = new DwzResult("200", "已修改该进件状态为等待外审！",
					"rel_listLoanApplyByState", "", "", "");
		} else {
			success = new DwzResult("300", "修改该进件状态失败！",
					"rel_listLoanApplyByState", "", "", "");
		}

		ServletUtils.renderJson(response, success);

		return null;
	}

	/**
	 * 通过信审信息获得调整列表 MDY 2013-7-18
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/listJksqCredit")
	public String listJksqCredit(HttpServletRequest request, Model model) {
		// 处理分页的参数
		JdbcPage page = JdbcPageUtils.generatePage(request);
		// 封装查询条件
		Map<String, Object> map = WebUtils.getParametersStartingWith(request,
				"filter_");
		// 查找借款人信审
		List<Map<String, Object>> listJksqCredit = loanApplyManager
				.listJksqCredit("queryCreditAuditList", map, page);
		model.addAttribute("listJksqCredit", listJksqCredit);
		model.addAttribute("page", page);

		List<City> province = baseInfoManager.getSuggestCity("0");
		request.setAttribute("province", province);
		String filter_province = request.getParameter("filter_province");
		if (null != filter_province && !"".equals(filter_province)) {
			List<City> city = baseInfoManager.getSuggestCity(filter_province);
			request.setAttribute("city", city);
		}
		// RequestPageUtils.fillModelWithRequest(model, request);
		model.addAttribute("filter_jkrxm", request.getParameter("filter_jkrxm"));
		model.addAttribute("filter_telephone",
				request.getParameter("filter_telephone"));
		model.addAttribute("filter_zjhm", request.getParameter("filter_zjhm"));
		model.addAttribute("filter_jkType",
				request.getParameter("filter_jkType"));
		model.addAttribute("filter_state", request.getParameter("filter_state"));
		model.addAttribute("filter_province",
				request.getParameter("filter_province"));
		model.addAttribute("filter_city", request.getParameter("filter_city"));
		model.addAttribute("filter_teamName",
				request.getParameter("filter_teamName"));
		model.addAttribute("filter_saleName",
				request.getParameter("filter_saleName"));
		model.addAttribute("filter_startDate",
				request.getParameter("filter_startDate"));
		model.addAttribute("filter_endDate",
				request.getParameter("filter_endDate"));
		return "creditAudit/listJksqCredit";
	}

	/**
	 * 获得借款人信审数据 MDY 2013-7-18
	 * 
	 * @param Id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getJksqCredit/{Id}", method = RequestMethod.GET)
	public String getJksqCredit(@PathVariable Long Id, Model model) {
		XhJksq xhJksq = loanApplyManager.getXhJksq(Id);
		List<Map<String, Object>> listMap = loanApplyManager.getJksqCredit(Id);
		if (xhJksq.getTogetherPerson() != null
				&& ("是").equals(xhJksq.getTogetherPerson())) {
			List<Map<String, Object>> listTogetherPerson = loanApplyManager
					.getTogetherPerson(Id);
			model.addAttribute("listTogetherPerson", listTogetherPerson);
		}
		model.addAttribute("xhJksq", xhJksq);
		model.addAttribute("listMap", listMap);
		return "creditAudit/updateJksqCredit";
	}

	/**
	 * 删除借款共同借款人 MDY 2013-7-18
	 * 
	 * @param Id
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/delTogetherPerson/{Id}")
	public String delTogetherPerson(@PathVariable Long Id,
			HttpServletResponse response) {
		loanApplyManager.deleteTogetherPerson(Id);
		DwzResult success = new DwzResult("200", "删除成功",
				"rel_listJksqCredit,rel_getJksqCredit", "", "", "");
		ServletUtils.renderJson(response, success);
		return null;
	}

	/**
	 * ajax保存信审信息 MDY 2013-7-18
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/updateJksqCredit")
	@ResponseBody
	public Map<String, Object> updateJksqCredit(HttpServletRequest request) {
		Map<String, Object> modelMap = loanApplyManager
				.updateJksqCredit(request);
		return modelMap;
	}

	/**
	 * ajax保存共借人信息 MDY 2013-7-18
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/updateTogetherPerson")
	@ResponseBody
	public Map<String, Object> updateTogetherPerson(HttpServletRequest request) {
		Map<String, Object> modelMap = loanApplyManager
				.updateTogetherPerson(request);
		return modelMap;
	}

	/**
	 * ajax新建共借人信息 MDY 2013-7-18
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addTogetherPerson")
	@ResponseBody
	public Map<String, Object> addTogetherPerson(HttpServletRequest request) {
		Map<String, Object> modelMap = loanApplyManager
				.addTogetherPerson(request);
		return modelMap;
	}

	/**
	 * 回收待初审分派为未分派 MDY 2013-7-26
	 */
	@RequestMapping(value = "/retrieveCreditAudit/{Id}")
	public String retrieveCreditAudit(@PathVariable Long Id,
			HttpServletResponse response) {
		String[] msg = xhCreditAuditManager.retrieveCreditAudit(Id);
		DwzResult success = new DwzResult(msg[0], msg[1],
				"rel_alterationexamine", "", "", "");
		ServletUtils.renderJson(response, success);
		return null;
	}

	/**
	 * 门店选择带回
	 * 
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getMdList")
	public String getMdList(HttpServletResponse response, Model model) {
		List<Map<String, Object>> mendianTree = statisticalService
				.mendianList("mendian");
		model.addAttribute("mendianTree", mendianTree);
		return "creditAudit/mdLookup";
	}

	/**
	 * 初始化信审人员KPI MDY 2013-08-16
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/listByAuditPersonKpi")
	public String listByAuditPersonKpi(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		// 查询已经审查过的记录 --通过名字查询
		List<Employee> allAuditPersons = baseInfoManager.findEmpByPosId(Long
				.parseLong("4"));
		model.addAttribute("allAuditPersons", allAuditPersons);
		model.addAttribute("filter_createTime", PublicService.getDateMonth());
		return "creditAudit/listByAuditPersonKpi";
	}

	/**
	 * 信审人员KPI计算 MDY 2013-08-16
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getAuditPersonKpi")
	public String getAuditPersonKpi(HttpServletRequest request, Model model) {
		Map<String, Object> map = WebUtils.getParametersStartingWith(request,
				"filter_");
		List<Map<String, Object>> listResult = xhCreditAuditManager
				.getAuditPersonKpiControl(map);
		model.addAttribute("map", map);
		model.addAttribute("listResult", listResult);
		return "creditAudit/auditPersonKpi";
	}

	/**
	 * ajax信审人员验证 MDY 2013-7-18
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/loginPersonValidate")
	@ResponseBody
	public Map<String, Object> loginPersonValidate(HttpServletRequest request) {
		Map<String, Object> modelMap = loanApplyManager
				.loginPersonValidate(request);
		return modelMap;
	}

	/**
	 * 初始化信审数据 MDY 2013-08-29
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/initAuditInfo/{Id}")
	public String initAuditInfo(@PathVariable Long Id,
			HttpServletRequest request, Model model) {
		// Id = (long) 212106;
		xhCreditAuditManager.getInitAuditInfoByJksqId(Id);
		model.addAttribute("jksqId", Id);
		return "creditAuditInfo/initAuditInfo";
	}

	@RequestMapping(value = "/listAuditImg")
	public String listPublicMessage(HttpServletRequest request, Model model) {
		JdbcPage page = JdbcPageUtils.generatePage(request);
		page.setPageSize(1);
		String jksqId = request.getParameter("jksqId");
		String typeName = request.getParameter("typeName");
		List<Map<String, Object>> imgList = xhCreditAuditManager.listAuditImg(
				jksqId, typeName, page);
		model.addAttribute("imgList", imgList);
		model.addAttribute("page", page);
		model.addAttribute("jksqId", jksqId);
		model.addAttribute("typeName", typeName);
		return "creditAuditInfo/listAuditImg";
	}
}

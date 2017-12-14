package cn.com.cucsi.app.web.xhcf;

import java.io.File;
import java.sql.Timestamp;
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

import cn.com.cucsi.app.Constants;
import cn.com.cucsi.app.dao.loan.XhJksqStateDao;
import cn.com.cucsi.app.entity.baseinfo.Attr;
import cn.com.cucsi.app.entity.baseinfo.City;
import cn.com.cucsi.app.entity.baseinfo.Employee;
import cn.com.cucsi.app.entity.baseinfo.MateData;
import cn.com.cucsi.app.entity.baseinfo.MiddleMan;
import cn.com.cucsi.app.entity.xhcf.XhCreditAudit;
import cn.com.cucsi.app.entity.xhcf.XhJkht;
import cn.com.cucsi.app.entity.xhcf.XhJksq;
import cn.com.cucsi.app.entity.xhcf.XhJksqState;
import cn.com.cucsi.app.entity.xhcf.XhUploadFiles;
import cn.com.cucsi.app.entity.xhcf.XhZqtj;
import cn.com.cucsi.app.service.baseinfo.BaseInfoManager;
import cn.com.cucsi.app.service.loan.XhJksqManager;
import cn.com.cucsi.app.service.security.OperatorDetails;
import cn.com.cucsi.app.service.xhcf.XhCreditAuditManager;
import cn.com.cucsi.app.service.xhcf.XhJkhtManager;
import cn.com.cucsi.app.web.InitSetupListener;
import cn.com.cucsi.app.web.util.CreditHarmonyComputeUtilties;
import cn.com.cucsi.app.web.util.FileUtil;
import cn.com.cucsi.app.web.util.HibernateAwareBeanUtilsBean;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils;
import cn.com.cucsi.core.utils.DateUtils;
import cn.com.cucsi.core.web.DwzResult;
import cn.com.cucsi.core.web.ServletUtils;

@Controller
@RequestMapping(value = "/xhJkht")
public class XhJkhtController {

	private static final String I1_REDIRECT_URL = Constants.getAttrValue(
			"JKSQ_STATE", "暂存");
	
	private static final Object countLock = new Object();

//	@Autowired
//	ServletContext context;

	private XhJkhtManager xhJkhtManager;
	@Autowired
	private XhCreditAuditManager xhCreditAuditManager;

	public void setXhCreditAuditManager(
			XhCreditAuditManager xhCreditAuditManager) {
		this.xhCreditAuditManager = xhCreditAuditManager;
	}

	@Autowired
	public void setXhJkhtManager(XhJkhtManager xhJkhtManager) {
		this.xhJkhtManager = xhJkhtManager;
	}

	private BaseInfoManager baseInfoManager;

	@Autowired
	public void setBaseInfoManager(BaseInfoManager baseInfoManager) {
		this.baseInfoManager = baseInfoManager;
	}

	private XhJksqManager xhjksqManager;

	@Autowired
	public void setXhjksqManager(XhJksqManager xhjksqManager) {
		this.xhjksqManager = xhjksqManager;
	}

	/**
	 * 50:借款申请列表页-合同制作 51:借款申请列表页-合同制作审核 56:借款申请列表页-签字确认审批 0:合同查看
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	// @RequestMapping(value = "/listJksq/{state}")
	// public String listJksq(@PathVariable Integer state,
	// HttpServletRequest request, HttpServletResponse response,
	// Model model) {
	// // 得到当前登录用户
	// OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
	// if (operator == null) {
	// return "redirect:../../login";
	// }
	// // Page<XhJksq> page = new Page<XhJksq>();
	// JdbcPage page = new JdbcPage();
	// String pageSize = request.getParameter("numPerPage");
	// if (StringUtils.isNotBlank(pageSize)) {
	// page.setPageSize(Integer.valueOf(pageSize));
	// }
	// String pageNo = request.getParameter("pageNum");
	// if (StringUtils.isNotBlank(pageNo)) {
	// page.setPageNo(Integer.valueOf(pageNo));
	// }
	// String orderBy = request.getParameter("orderField");
	// if (StringUtils.isNotBlank(orderBy)) {
	// page.setOrderBy(orderBy);
	// }
	// String order = request.getParameter("orderDirection");
	// if (StringUtils.isNotBlank(order)) {
	// page.setOrder(order);
	// }
	// // 证件类型
	// List<MateData> zjlxMateDateList = baseInfoManager.getTypeByCode("0005");
	// model.addAttribute("zjlx0005", zjlxMateDateList);
	// String filter_zjlx = request.getParameter("filter_zjlx");
	// if (null != filter_zjlx && !"".equals(filter_zjlx)) {
	// model.addAttribute("zjlx", filter_zjlx);
	// }
	//
	// List<City> province = baseInfoManager.getSuggestCity("0");
	// request.setAttribute("province", province);
	//
	// Map<String, Object> map = WebUtils.getParametersStartingWith(request,
	// "filter_");
	// // 合同制作制作由总公司操作 ，不需要过滤
	// // Employee emp = baseInfoManager.getUserEmployee();
	// // String loginName = operator.getCtiCode();
	// // map.put("loginName", loginName);
	// // map.put("org_id", emp.getOrgani().getId());
	// // map.put("jiLianFlag", "1");// 1为查询自己的及其子部门所有，0只查询自己的，不设置此项为不做限制
	// // map.put("org_id", emp.getOrgani().getId());
	// switch (state) {
	// case 50:
	// map.put("state_in", "50，52");
	// break;
	// case 51:
	// map.put("state", "51");
	// break;
	// case 56:
	// map.put("state", "56");
	// break;
	// case 0:
	// map.put("state_g", "51");
	// break;
	// }
	//
	// List<Map<String, Object>> listJksq = xhjksqManager.searchXhJksq(
	// "queryJksqList", map, page);
	// model.addAttribute("listJksq", listJksq);
	// model.addAttribute("page", page);
	//
	// model.addAttribute("filter_jkrxm", request.getParameter("filter_jkrxm"));
	// model.addAttribute("filter_telephone",
	// request.getParameter("filter_telephone"));
	// model.addAttribute("filter_zjhm", request.getParameter("filter_zjhm"));
	// model.addAttribute("filter_jkType",
	// request.getParameter("filter_jkType"));
	// model.addAttribute("filter_state", request.getParameter("filter_state"));
	// model.addAttribute("filter_province",
	// request.getParameter("filter_province"));
	// model.addAttribute("filter_city", request.getParameter("filter_city"));
	// String filter_province = request.getParameter("filter_province");
	// if (null != filter_province && !"".equals(filter_province)) {
	// List<City> city = baseInfoManager.getSuggestCity(filter_province);
	// request.setAttribute("city", city);
	// }
	// model.addAttribute("filter_teamName",
	// request.getParameter("filter_teamName"));
	// model.addAttribute("filter_saleName",
	// request.getParameter("filter_saleName"));
	// String returnUrl = null;
	// switch (state) {
	// case 50:
	// returnUrl = "jkht/jksqList";
	// break;
	// case 51:
	// returnUrl = "jkht/jksqListHtzzsh";
	// break;
	// case 56:
	// returnUrl = "jkht/jksqListQzqrsh";
	// break;
	// case 0:
	// returnUrl = "jkht/jksqListQuery";
	// break;
	// }
	// return returnUrl;
	// }
	/**
	 * 借款申请列表页
	 */
	/**
	 * 50:借款申请列表页-合同制作 51:借款申请列表页-合同制作审核 56:借款申请列表页-签字确认审批 0:合同查看
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/listJksq/{state}")
	public String listJksq(@PathVariable Integer state,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		// 得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		if (operator == null) {
			return "redirect:../../login";
		}
		JdbcPage page = new JdbcPage();
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
		// 证件类型
		// List<MateData> zjlxMateDateList =
		// baseInfoManager.getTypeByCode("0005");
		// model.addAttribute("zjlx0005", zjlxMateDateList);
		// String filter_zjlx = request.getParameter("filter_zjlx");
		// if (null != filter_zjlx && !"".equals(filter_zjlx)) {
		// model.addAttribute("zjlx", filter_zjlx);
		// }
		// 借款申请状态
		Map<String, Object> filter = new HashMap<String, Object>();
		filter.put("attrType", "1");
		List<Attr> attrList = baseInfoManager.getAttrList(filter);
		request.setAttribute("attrList", attrList);

		List<City> province = baseInfoManager.getSuggestCity("0");
		request.setAttribute("province", province);

		Map<String, Object> map = WebUtils.getParametersStartingWith(request,
				"filter_");
		map.put("backup01", "CREDIT");
		
		//产品List
		List cpList = xhjksqManager.searchCpType();
		
		request.setAttribute("cpList", cpList);
		// Employee emp = baseInfoManager.getUserEmployee();
		// String loginName = operator.getCtiCode();
		// map.put("loginName", loginName);
		// map.put("emp", emp);
		// map.put("org_id", emp.getOrgani().getId());
		// map.put("jiLianFlag", "1");// 1为查询自己的及其子部门所有，0只查询自己的，不设置此项为不做限制
		switch (state) {
		case 50:
			map.put("state_injksp", "50,52,89,50.F");//待审核利率李彪 添加  (复议完成、待审核利率) 状态  50.F
			break;
		case 51:
			map.put("state", "51");
			break;
		case 56:
			map.put("state_injksp", "56,88");
			break;
		case 71:
			map.put("state", "71");
			break;
		case 81:
			map.put("state", "81");
			break;
		case 99:
			map.put("state", "99");
			break;
		case 70:
			map.put("state_injksp", "70,70.F");//待确定签署列表 添加 (复议完成、待确定签署) 状态 70.F
			break;
		case 0:
			map.put("state_injksp", "51,52,55,56,58,60,61,57,90,100,101,102,103");
			break;
		}
		//queryJksqList变更loanApplyJksqList MDY 2013-08-16
		List<Map<String, Object>> listJksq = xhjksqManager.searchXhJksq(
				"loanApplyJksqList", map, page);
		
		model.addAttribute("organiId", request.getParameter("filter_organi.id"));
	    model.addAttribute("organiName", request.getParameter("filter_organi.name"));
	    
		model.addAttribute("listJksq", listJksq);
		model.addAttribute("page", page);

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
		String filter_province = request.getParameter("filter_province");
		if (null != filter_province && !"".equals(filter_province)) {
			List<City> city = baseInfoManager.getSuggestCity(filter_province);
			request.setAttribute("city", city);
		}
		model.addAttribute("filter_teamName",
				request.getParameter("filter_teamName"));
		model.addAttribute("filter_saleName",
				request.getParameter("filter_saleName"));

		String returnUrl = null;
		switch (state) {
		case 50:
			returnUrl = "jkht/jksqList";
			break;
		case 51:
			returnUrl = "jkht/jksqListHtzzsh";
			break;
		case 56:
			returnUrl = "jkht/jksqListQzqrsh";
			break;
		case 71:
			returnUrl = "jkht/jksqListMadeJkht";
			break;
		case 81:
			returnUrl = "jkht/jksqListKhfq";
			break;
		case 99:
			returnUrl = "jkht/jksqListInvalid";
			break;
		case 70:
			returnUrl = "jkht/jksqListDqrqs";
			break;
		case 0:
			returnUrl = "jkht/jksqListQuery";
			break;
		}
		return returnUrl;
	}

	/*
	 * 临时
	 */
	@RequestMapping(value = "/demo", method = RequestMethod.GET)
	public String viewDemo(Model model, HttpServletRequest request) {

		return "jkht/demo";
	}

	/**
	 * 协议查看框架页
	 * 
	 * @param Id
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/queryAgaee/{Id}", method = RequestMethod.GET)
	public ModelAndView queryAgaee(@PathVariable Long Id,
			HttpServletRequest request, Model model) {
		XhJksq xhJksq = xhjksqManager.getXhJksq(Id);
		model.addAttribute("xhJksq", xhJksq);
		// XhJkht xhJkht = xhJksq.getXhjkht();
		XhJkht xhJkht = xhJkhtManager.findLoanContarctByApplyID(Id);
		return new ModelAndView("jkht/queryAgaee", "xhJkht", xhJkht);
	}

	/**
	 * 协议文档查看
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/queryAgaeeFile", method = RequestMethod.GET)
	public ModelAndView queryAgaeeFile(HttpServletRequest request, Model model) {
		String Id = request.getParameter("Id");
		String sType = request.getParameter("sType");
		model.addAttribute("id", Id);
		String fileName = Id + sType + ".swf" ;
		String filePath = InitSetupListener.filePath + "agaeeFile" + File.separator + Id + File.separator + fileName;
		File swfFile = new File(filePath);
		if(!swfFile.exists()){
		    model.addAttribute("swfServer",InitSetupListener.swfBackServer);
		}else{
		    model.addAttribute("swfServer",InitSetupListener.swfServer);
		}
		return new ModelAndView("jkht/queryAgaeeFile", "fileName", fileName);
	}

	@RequestMapping(value = "/downAgaeeFile")
	public String downAgaeeFile(HttpServletRequest request,
			HttpServletResponse response) {
		String Id = request.getParameter("Id");
		String sType = request.getParameter("sType");
		String base = InitSetupListener.filePath + "agaeeFile" + File.separator
				+ Id;// request.getRealPath(xhUploadFiles.getFilepath());
		// System.out.println("base===>" + base);
		String fileName = Id + sType + ".doc";
		// String ext =
		// fileName.substring(fileName.lastIndexOf("."),fileName.length());
		// System.out.println("ext========"+ext);
		// String path = base + File.separator + fileName;
		String filePath = base + File.separator;
		String downLoadPath = filePath + fileName;

		FileUtil.downLoad(downLoadPath, fileName, request, response);
		/*
		 * try { java.io.BufferedInputStream bis = null;
		 * java.io.BufferedOutputStream bos = null;
		 * response.setContentType("text/html;charset=utf-8");
		 * request.setCharacterEncoding("UTF-8"); //
		 * System.out.println(downLoadPath); try { long fileLength = new
		 * File(downLoadPath).length();
		 * response.setContentType("application/octet-stream; charset=utf-8");
		 * response.setHeader( "Content-disposition", "attachment; filename=" //
		 * + new String(fileName.getBytes("UTF-8"), "GB2312")); +
		 * URLEncoder.encode( fileName, "utf-8"));
		 * response.setHeader("Content-Length", String.valueOf(fileLength)); bis
		 * = new BufferedInputStream(new FileInputStream(downLoadPath)); bos =
		 * new BufferedOutputStream(response.getOutputStream()); byte[] buff =
		 * new byte[1024]; int bytesRead; while (-1 != (bytesRead =
		 * bis.read(buff, 0, buff.length))) { bos.write(buff, 0, bytesRead); } }
		 * catch (Exception e) { // e.printStackTrace(); } finally { if (bos !=
		 * null) { bos.close(); } if (bis != null) { bis.close(); }
		 * 
		 * } } catch (UnsupportedEncodingException e) { e.printStackTrace(); }
		 * catch (IOException e) { e.printStackTrace(); }
		 */
		return null;
	}

	/**
	 * 暂时无用
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/listXhJkht")
	public String listXhJkht(HttpServletRequest request, Model model) {
		// 处理分页的参数
		Page<XhJkht> page = new Page<XhJkht>();
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

		xhJkhtManager.searchXhJkht(page, map);

		model.addAttribute("page", page);
		model.addAttribute("map", map);
		return "jkht/xhJkhtIndex";

	}

	/**
	 * 保存借款合同
	 * 
	 * @param xhJkht
	 * @param request
	 * @param response
	 * @return
	 */

	@SuppressWarnings("finally")
	@RequestMapping(value = "/saveXhJkht", method = RequestMethod.POST)
	public void saveXhJkht(@ModelAttribute("xhJkht") XhJkht xhJkht,
			HttpServletRequest request, HttpServletResponse response) {
		DwzResult success = null;
		try {
			Long id = Long.parseLong(request.getParameter("xhJksq.id"));
			XhJksq xhJksq = xhjksqManager.getXhJksq(id);
			//xhJksq.setLoanCode(xhJkht.getJkhtbm());
			xhJkht.setXhJksq(xhJksq);
			/*
			String fkzh = request.getParameter("middleMan.id");
			MiddleMan middleMan = baseInfoManager.getMiddleMan(Long
					.valueOf(fkzh));
			xhJkht.setMiddleMan(middleMan);
			if (xhJkht.getHkr() == null) {
				xhJkht.setHkr(CreditHarmonyComputeUtilties
						.getBackMoneyDateOfMonth(xhJkht.getQshkrq()));
			}
			*/
			boolean isSuccess = xhJkhtManager.saveXhJkht(xhJkht, request);

			if (isSuccess) {
				success = new DwzResult("200", "保存成功", "rel_listJksqForJkht",
						"", "closeCurrent", "");
			} else {
				success = new DwzResult("300", "保存失败", "",
						"", "", "");
			}
		} catch(Exception e){
			success = new DwzResult("300", "保存失败", "",
					"", "", "");
			
		}
		ServletUtils.renderJson(response, success);
	}

	/**
	 * 审核借款合同保存
	 * 
	 * @param xhJkht
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/saveAuditXhJkht", method = RequestMethod.POST)
	public String saveAuditXhJkht(@ModelAttribute("xhJkht") XhJkht xhJkht,
			HttpServletRequest request, HttpServletResponse response) {
		Long id = Long.parseLong(request.getParameter("xhJksq.id"));
		String state = request.getParameter("state");
		String opt = request.getParameter("opt");
		String fkzh = request.getParameter("middleMan.id");
		MiddleMan middleMan = baseInfoManager.getMiddleMan(Long.valueOf(fkzh));
		XhJksq xhJksq = xhjksqManager.getXhJksq(id);
		if ("2".equals(opt)) {
			xhJksq.setState("99");
			xhJkht.setState("9");
		} else {
			xhJkht.setState(state);
		}
		xhJkht.setXhJksq(xhJksq);
		xhJkht.setMiddleMan(middleMan);
		if (xhJkht.getHkr() == null) {
			xhJkht.setHkr(CreditHarmonyComputeUtilties
					.getBackMoneyDateOfMonth(xhJkht.getQshkrq()));
		}
		Employee emp = baseInfoManager.getUserEmployee();
		xhJkht.setAuditPerson(emp.getName());
		xhJkht.setAuditDate(new Timestamp(new Date().getTime()));

		boolean isSuccess = xhJkhtManager.saveAuditXhJkht(xhJkht);

		DwzResult success = null;
		if (isSuccess) {
			success = new DwzResult("200", "保存成功", "rel_listJksqHtzzsh", "",
					"closeCurrent", "");
		} else {
			success = new DwzResult("300", "保存失败", "rel_listJksqHtzzsh", "",
					"closeCurrent", "");
		}
		ServletUtils.renderJson(response, success);
		return null;

	}

	/**
	 * 审核借款合同保存
	 * 
	 * @param xhJkht
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/saveInvalidXhJkht")
	public String saveInvalidXhJkht(HttpServletRequest request,
			HttpServletResponse response) {
		Long jksqid = Long.parseLong(request.getParameter("jksqid"));
		Long jkhtid = Long.parseLong(request.getParameter("jkhtid"));
		XhJksq xhJksq = xhjksqManager.getXhJksq(jksqid);
		XhJkht xhJkht = xhJkhtManager.getXhJkht(jkhtid);
		xhJksq.setState("99");
		xhJkht.setState("9");
		xhJkht.setXhJksq(xhJksq);
		Employee emp = baseInfoManager.getUserEmployee();
		xhJkht.setAuditPerson(emp.getName());
		xhJkht.setAuditDate(new Timestamp(new Date().getTime()));
		xhJkhtManager.saveXhJksq(xhJksq);
		boolean isSuccess = xhJkhtManager.saveInvalidXhJkht(xhJkht);

		DwzResult success = null;
		if (isSuccess) {
			success = new DwzResult("200", "保存成功", "rel_listJksqHtzzsh", "",
					"closeCurrent", "");
		} else {
			success = new DwzResult("300", "保存失败", "rel_listJksqHtzzsh", "",
					"closeCurrent", "");
		}
		ServletUtils.renderJson(response, success);
		return null;

	}

	/**
	 * 审核签字确认借款合同保存
	 * 
	 * @param xhJkht
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/saveAuditXhJkhtQzqr", method = RequestMethod.POST)
	public String saveAuditXhJkhtQzqr(@ModelAttribute("xhJkht") XhJkht xhJkht,
			HttpServletRequest request, HttpServletResponse response) {

		// XhJkht xhJkhtFind = xhJkhtManager.getXhJkht(xhJkht.getId());
		// Employee emp = baseInfoManager.getUserEmployee();
		// xhJkhtFind.setAuditQzqrPerson(emp.getName());
		// xhJkhtFind.setAuditQzqrIdea(xhJkht.getAuditQzqrIdea());
		// // xhJkhtFind.setState(xhJkht.getState());
		// xhJkhtFind.setAuditQzqrDate(new Timestamp(new Date().getTime()));
		Long id = Long.parseLong(request.getParameter("xhJksq.id"));
		String state = request.getParameter("state");
		String fkzh = request.getParameter("middleMan.id");
		MiddleMan middleMan = baseInfoManager.getMiddleMan(Long.valueOf(fkzh));
		XhJksq xhJksq = xhjksqManager.getXhJksq(id);
		xhJksq.setLoanCode(xhJkht.getJkhtbm().trim());
		xhJkht.setXhJksq(xhJksq);
		xhJkht.setState(state);
		xhJkht.setMiddleMan(middleMan);
		Employee emp = baseInfoManager.getUserEmployee();
		xhJkht.setAuditQzqrPerson(emp.getName());
		xhJkht.setAuditQzqrDate(new Timestamp(new Date().getTime()));
		if (xhJkht.getHkr() == null) {
			xhJkht.setHkr(CreditHarmonyComputeUtilties
					.getBackMoneyDateOfMonth(xhJkht.getQshkrq()));
		}
		boolean isSuccess = xhJkhtManager.saveAuditXhJkhtQzqr(xhJkht, request);

		DwzResult success = null;
		if (isSuccess) {
			success = new DwzResult("200", "保存成功", "rel_listJksqQzqrsh", "",
					"closeCurrent", "");
		} else {
			success = new DwzResult("300", "保存失败", "rel_listJksqQzqrsh", "",
					"closeCurrent", "");
		}
		ServletUtils.renderJson(response, success);
		return null;

	}

	/**
	 * 新增借款合同
	 * 
	 * @param Id
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addXhJkht/{Id}", method = RequestMethod.GET)
	public ModelAndView add(@PathVariable Long Id, HttpServletRequest request,
			Model model, HttpServletResponse response) {
		/*
		List<MiddleMan> MiddleMan = xhJkhtManager.getSuggestMiddleMan("0");
		request.setAttribute("MiddleMan", MiddleMan);
*/
		Page<XhCreditAudit> page = new Page<XhCreditAudit>();

		Map<String, Object> map = ServletUtils.getParametersStartingWith2(
				request, "filter_");
		// 信审记录中对应每一个借款ID只有一条是完成信审的，状态为1
		map.put("creditState", "1");
		map.put("loanApplyId", Id);
		xhCreditAuditManager.searchXhCreditAudit(page, map);

		if (page.getResult().size() == 0) {
			DwzResult success = null;
			success = new DwzResult("300", "打开合同失败", "rel_listJksq", "",
					"closeCurrent", "");
			ServletUtils.renderJson(response, success);
			return null;
		}
		XhCreditAudit creditAduit = page.getResult().get(0);

		// System.out.println("creditAduit.getLoanApply().getOrgani().getOrganiCode():"+creditAduit.getLoanApply().getOrgani().getOrganiCode());

		XhJksq xhJksq = xhjksqManager.getXhJksq(Id);
		XhJkht xhJkht = new XhJkht();
		/*
		// 定义合同编码:需要单独封装
		// 合同编号规则：
		// 合同编号是按照地区+营业部+客户序号原则出具的
		// 地区：4位，如北京为0010，哈尔滨为0451，长春为0431
		// 营业部：2位，北京、哈尔滨、长春目前都只是一个营业部，如出现第二个营业部，则第二个营业部的客户合同营业部编号为02
		// 客户序号：6位，就是按照信审出具批贷结果的顺序编号
		// 客户序号应在信审通过后绑定
		String NumOfPassedAduit = creditAduit.getPassedCustomerNo();
		String areaCode = baseInfoManager.getAreaCode(creditAduit
				.getLoanApply().getCity().getId());// 城市编号
		System.out.println("creditAduit:" + creditAduit);
		System.out.println("creditAduit.getLoanApply():"	+ creditAduit.getLoanApply());
		System.out.println("creditAduit.getLoanApply().getOrgani():"
				+ creditAduit.getLoanApply().getOrgani());
		String organiCode = xhJksq.getOrgani().getOrganiCode();// 营业部编号
		String codeOFContract = areaCode
				+ organiCode
				+ CreditHarmonyComputeUtilties.getAddStrLen(
						Integer.parseInt(NumOfPassedAduit), 6);
		xhJkht.setJkhtbm(codeOFContract);
*/
		// String city=xhJksq.getCity();

		xhJkht.setXhJksq(xhJksq);
		xhJkht.setPdje(Double.parseDouble(creditAduit.getCreditAmount()));
		xhJkht.setYzhfl(Double.parseDouble(creditAduit.getCreditAllRate()));
		xhJkht.setXff(Double.parseDouble(creditAduit.getOutVisitFee()));
		xhJkht.setHkqs(Integer.parseInt(creditAduit.getCreditMonth()));
		xhJkht.setUrgentCreditFee(creditAduit.getUrgentCreditFee());

		// 贷款利率
		List<MateData> dkllMateDateList = baseInfoManager
				.getTypeByCode("10003");
		model.addAttribute("dkllList", dkllMateDateList);
		//还款付息方式
		List<MateData> rePayTypeMateDateList = baseInfoManager
				.getTypeByCode("10006");
		model.addAttribute("rePayTypeList", rePayTypeMateDateList);

		// request.setAttribute("xhjkht",xhjkht);
		return new ModelAndView("jkht/xhJkhtInput", "xhJkht", xhJkht);
	}
	
	
	
	/**
	 * 查看借款合同
	 * 
	 * @param Id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/queryXhJkht/{Id}", method = RequestMethod.GET)
	public ModelAndView queryXhJkht(@PathVariable Long Id, Model model,
			HttpServletRequest request) {
		List<MiddleMan> MiddleMan = xhJkhtManager.getSuggestMiddleMan("0");
		model.addAttribute("MiddleMan", MiddleMan);
		XhJkht xhJkht = xhJkhtManager.findLoanContarctByApplyID(Id);
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("fileOwner", "XH_JKHT");
		filters.put("mainId", xhJkht.getId());
		List<XhUploadFiles> files = baseInfoManager.findXhUploadFiles(filters);
		model.addAttribute("files", files);
		return new ModelAndView("jkht/xhJkhtQuery", "xhJkht", xhJkht);
	}

	/**
	 * 编辑借款合同
	 * 
	 * @param Id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/editXhJkht/{Id}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable Long Id, Model model,
			HttpServletRequest request) {
		List<MiddleMan> MiddleMan = xhJkhtManager.getSuggestMiddleMan("0");
		model.addAttribute("MiddleMan", MiddleMan);
		XhJkht xhJkht = xhJkhtManager.findLoanContarctByApplyID(Id);

		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("fileOwner", "XH_JKHT");
		filters.put("mainId", xhJkht.getId());
		List<XhUploadFiles> files = baseInfoManager.findXhUploadFiles(filters);
		model.addAttribute("files", files);

		// 贷款利率
		List<MateData> dkllMateDateList = baseInfoManager
				.getTypeByCode("10003");
		model.addAttribute("dkllList", dkllMateDateList);
		return new ModelAndView("jkht/xhJkhtInput", "xhJkht", xhJkht);
	}

	/**
	 * 审核借款合同
	 * 
	 * @param Id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/auditXhJkht/{Id}", method = RequestMethod.GET)
	public ModelAndView auditXhJkht(@PathVariable Long Id, Model model) {
		List<MiddleMan> MiddleMan = xhJkhtManager.getSuggestMiddleMan("0");
		model.addAttribute("MiddleMan", MiddleMan);
		XhJkht xhJkht = xhJkhtManager.findLoanContarctByApplyID(Id);
		return new ModelAndView("jkht/auditXhJkhtInput", "xhJkht", xhJkht);
	}

	/**
	 * 审核签字确认借款合同
	 * 
	 * @param Id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/auditXhJkhtQzqr/{Id}", method = RequestMethod.GET)
	public ModelAndView auditXhJkhtQzqr(@PathVariable Long Id, Model model) {
		// 得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		// String loginName = operator.getUsername();
		// String loginName = operator.getCtiCode();
		if (operator == null) {
			return new ModelAndView("redirect:../login");
		}

		List<MiddleMan> MiddleMan = xhJkhtManager.getSuggestMiddleMan("0");
		model.addAttribute("MiddleMan", MiddleMan);
		XhJkht xhJkht = xhJkhtManager.findLoanContarctByApplyID(Id);

		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("fileOwner", "XH_JKHT");
		filters.put("mainId", xhJkht.getId());
		List<XhUploadFiles> files = baseInfoManager.findXhUploadFiles(filters);
		model.addAttribute("files", files);

		return new ModelAndView("jkht/auditXhJkhtQzqrInput", "xhJkht", xhJkht);
	}

	
	/**
	 * 待确定签署
	 * 
	 * @param Id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/jksqQianShuInput/{Id}", method = RequestMethod.GET)
	public ModelAndView jksqQianShuInput(@PathVariable Long Id, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		// 得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		// String loginName = operator.getUsername();
		// String loginName = operator.getCtiCode();
		if (operator == null) {
			return new ModelAndView("redirect:../login");
		}

		List<MiddleMan> MiddleMan = xhJkhtManager.getSuggestMiddleMan("0");
		model.addAttribute("MiddleMan", MiddleMan);
		XhJkht xhJkht = xhJkhtManager.findLoanContarctByApplyID(Id);
		XhJksq xhJksq = xhJkht.getXhJksq();
		Page<XhCreditAudit> page = new Page<XhCreditAudit>();

		Map<String, Object> map = ServletUtils.getParametersStartingWith2(
				request, "filter_");
		// 信审记录中对应每一个借款ID只有一条是完成信审的，状态为1
		map.put("creditState", "1");
		map.put("loanApplyId", Id);
		xhCreditAuditManager.searchXhCreditAudit(page, map);

		if (page.getResult().size() == 0) {
			DwzResult success = null;
			success = new DwzResult("300", "打开合同失败", "rel_listJksq", "",
					"closeCurrent", "");
			ServletUtils.renderJson(response, success);
			return null;
		}
		/*
		XhCreditAudit creditAduit = page.getResult().get(0);
		// 定义合同编码:需要单独封装
		// 合同编号规则：
		// 合同编号是按照地区+营业部+客户序号原则出具的
		// 地区：4位，如北京为0010，哈尔滨为0451，长春为0431
		// 营业部：2位，北京、哈尔滨、长春目前都只是一个营业部，如出现第二个营业部，则第二个营业部的客户合同营业部编号为02
		// 客户序号：4位，就是按照信审出具批贷结果的顺序编号
		// 客户序号应在信审通过后绑定
		String NumOfPassedAduit = creditAduit.getPassedCustomerNo();
		String areaCode = baseInfoManager.getAreaCode(creditAduit
				.getLoanApply().getCity().getId());// 城市编号
		System.out.println("creditAduit:" + creditAduit);
		System.out.println("creditAduit.getLoanApply():"	+ creditAduit.getLoanApply());
		System.out.println("creditAduit.getLoanApply().getOrgani():"
				+ creditAduit.getLoanApply().getOrgani());
		String organiCode = xhJksq.getOrgani().getOrganiCode();// 营业部编号
		String codeOFContract = areaCode
				+ organiCode
				+ CreditHarmonyComputeUtilties.getAddStrLen(
						Integer.parseInt(NumOfPassedAduit), 4);
		xhJkht.setJkhtbm(codeOFContract);
*/
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("fileOwner", "XH_JKHT");
		filters.put("mainId", xhJkht.getId());
		List<XhUploadFiles> files = baseInfoManager.findXhUploadFiles(filters);
		model.addAttribute("files", files);

		return new ModelAndView("jkht/jksqQianShuInput", "xhJkht", xhJkht);
	}
	
	
	
	/**
	 * 待确定签署保存
	 * 
	 * @param xhJkht
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/saveJksqQianShu", method = RequestMethod.POST)
	public String saveJksqQianShu(@ModelAttribute("xhJkht") XhJkht xhJkht,
			HttpServletRequest request, HttpServletResponse response) {

		// XhJkht xhJkhtFind = xhJkhtManager.getXhJkht(xhJkht.getId());
		// Employee emp = baseInfoManager.getUserEmployee();
		// xhJkhtFind.setAuditQzqrPerson(emp.getName());
		// xhJkhtFind.setAuditQzqrIdea(xhJkht.getAuditQzqrIdea());
		// // xhJkhtFind.setState(xhJkht.getState());
		// xhJkhtFind.setAuditQzqrDate(new Timestamp(new Date().getTime()));
		Long id = Long.parseLong(request.getParameter("xhJksq.id"));
		
		XhJksq xhJksq = xhjksqManager.getXhJksq(id);
		XhJkht xhJkhtNew = xhJkhtManager.getXhJkht(xhJkht.getId());
//		xhJksq.setLoanCode(xhJkht.getJkhtbm());
//		xhJkhtNew.setJkhtbm(xhJkht.getJkhtbm());
		xhJkhtNew.setXhJksq(xhJksq);
		xhJkhtNew.setQdrq(xhJkht.getQdrq());
		
		boolean isSuccess = xhJkhtManager.saveXhJkhtQianShu(xhJkhtNew, request);

		DwzResult success = null;
		if (isSuccess) {
			success = new DwzResult("200", "保存成功", "rel_listJksq", "",
					"closeCurrent", "");
		} else {
			success = new DwzResult("300", "保存失败", "rel_listJksq", "",
					"closeCurrent", "");
		}
		ServletUtils.renderJson(response, success);
		return null;

	}
	
	/**
	 * 复议查看
	 * @param Id jksqId
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/jksqFuYiIndex/{Id}", method = RequestMethod.GET)
	public ModelAndView jksqFuYiIndex(@PathVariable Long Id, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		XhJkht xhJkht = xhJkhtManager.findLoanContarctByApplyID(Id);
		Page<XhCreditAudit> page = new Page<XhCreditAudit>();
		Map<String, Object> map = ServletUtils.getParametersStartingWith2(
				request, "filter_");
		// 信审记录中对应每一个借款ID只有一条是完成信审的，状态为1
		map.put("creditState", "1");
		map.put("loanApplyId", Id);
		Page<XhCreditAudit> creditPage = xhCreditAuditManager.searchXhCreditAudit(page,map);
		XhCreditAudit xhCreditAudit = creditPage.getResult().get(0);
		model.addAttribute("xhCreditAudit",xhCreditAudit);

		if (page.getResult().size() == 0) {
			DwzResult success = null;
			success = new DwzResult("300", "打开合同失败", "rel_listJksq", "",
					"closeCurrent", "");
			ServletUtils.renderJson(response, success);
			return null;
		}
		return new ModelAndView("jkht/jksqFuYiIndex", "xhJkht", xhJkht);
	}
	
	
	/**
	 * 待复议列表
	 * @param Id jksqId
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/jksqFuYiInput/{Id}", method = RequestMethod.GET)
	public ModelAndView jksqFuYiInput(@PathVariable Long Id, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		XhJkht xhJkht = xhJkhtManager.findLoanContarctByApplyID(Id);
		Page<XhCreditAudit> page = new Page<XhCreditAudit>();

		Map<String, Object> map = ServletUtils.getParametersStartingWith2(
				request, "filter_");
		// 信审记录中对应每一个借款ID只有一条是完成信审的，状态为1
		map.put("creditState", "1");
		map.put("loanApplyId", Id);
		xhCreditAuditManager.searchXhCreditAudit(page, map);

		if (page.getResult().size() == 0) {
			DwzResult success = null;
			success = new DwzResult("300", "打开合同失败", "rel_listJksq", "",
					"closeCurrent", "");
			ServletUtils.renderJson(response, success);
			return null;
		}
		return new ModelAndView("jkht/jksqFuYiInput", "xhJkht", xhJkht);
	}
	
	
	
	/**
	 * 待复议保存
	 * 
	 * @param xhJkht
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/saveJksqFuYi", method = RequestMethod.POST)
	public String saveJksqFuYi(@ModelAttribute("xhJkht") XhJkht xhJkht,
			HttpServletRequest request, HttpServletResponse response) {

		Long id = Long.parseLong(request.getParameter("xhJksq.id"));
		
		XhJksq xhJksq = xhjksqManager.getXhJksq(id);
		XhJkht xhJkhtNew = xhJkhtManager.getXhJkht(xhJkht.getId());
		xhJkhtNew.setXhJksq(xhJksq);
		xhJkhtNew.setQdrq(xhJkht.getQdrq());
		//复议申请描述--backup08
		xhJksq.setBackup08(request.getParameter("backup08"));
		
		boolean isSuccess = xhJkhtManager.saveXhJkhtFuYi(xhJkhtNew, request);

		DwzResult success = null;
		if (isSuccess) {
			xhjksqManager.saveXhJksq(xhJksq);
			success = new DwzResult("200", "保存成功", "rel_listJksq", "",
					"closeCurrent", "");
		} else {
			success = new DwzResult("300", "保存失败", "rel_listJksq", "",
					"closeCurrent", "");
		}
		ServletUtils.renderJson(response, success);
		return null;

	}
	
	
	/**
	 * 审核签字确认借款合同
	 * 
	 * @param Id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/madeXhJkht/{Id}", method = RequestMethod.GET)
	public ModelAndView madeXhJkhtInput(@PathVariable Long Id, Model model) {
		// 得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		// String loginName = operator.getUsername();
		// String loginName = operator.getCtiCode();
		if (operator == null) {
			return new ModelAndView("redirect:../login");
		}

		List<MiddleMan> MiddleMan = xhJkhtManager.getSuggestMiddleMan("0");
		model.addAttribute("MiddleMan", MiddleMan);
		XhJkht xhJkht = xhJkhtManager.findLoanContarctByApplyID(Id);

		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("fileOwner", "XH_JKHT");
		filters.put("mainId", xhJkht.getId());
		List<XhUploadFiles> files = baseInfoManager.findXhUploadFiles(filters);
		model.addAttribute("files", files);
		//还款付息方式
		List<MateData> rePayTypeMateDateList = baseInfoManager
				.getTypeByCode("10006");
		model.addAttribute("rePayTypeList", rePayTypeMateDateList);
		
		return new ModelAndView("jkht/madeXhJkhtInput", "xhJkht", xhJkht);
	}
	
	
	/**
	 * 待确定签署保存
	 * 
	 * @param xhJkht
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/saveMadeXhJkht", method = RequestMethod.POST)
	public String saveMadeXhJkht(@ModelAttribute("xhJkht") XhJkht xhJkht,
			HttpServletRequest request, HttpServletResponse response) {
		DwzResult success = null;
	    String first = null;
	    Object wordgenerate = null;
	    try{
	    	/*
			synchronized(countLock){
				wordgenerate = context.getAttribute("wordgenerate");
				if ( wordgenerate != null) {
					success = new DwzResult("300", "合同制作进程被占用，请稍候重新制作,",
							"", "", "", "");
					ServletUtils.renderJson(response, success);
					return null;
				}
				first = "not";
				context.setAttribute("wordgenerate", request.getSession().getId());
			}
			*/
			Long id = Long.parseLong(request.getParameter("xhJksq.id"));
			
			XhJksq xhJksq = xhjksqManager.getXhJksq(id);
			XhJkht xhJkhtNew = xhJkhtManager.getXhJkht(xhJkht.getId());
			xhJksq.setLoanCode(xhJkht.getJkhtbm().trim());
			xhJkhtNew.setJkhtbm(xhJkht.getJkhtbm().trim());
			xhJkhtNew.setXhJksq(xhJksq);
			xhJkhtNew.setQshkrq(xhJkht.getQshkrq());
			xhJkhtNew.setRePayType(xhJkht.getRePayType());
			String fkzh = request.getParameter("middleMan.id");
			MiddleMan middleMan = baseInfoManager.getMiddleMan(Long
					.valueOf(fkzh));
			xhJkhtNew.setMiddleMan(middleMan);
			xhJkhtNew.setHkr(CreditHarmonyComputeUtilties
					.getBackMoneyDateOfMonth(xhJkhtNew.getQshkrq()));
			
			boolean isSuccess = xhJkhtManager.saveXhJkhtMade(xhJkhtNew, request);
			if (isSuccess) {
				//String time = baseInfoManager.waiteTime("xh_jkht");
				String mess = "保存成功,稍后查看。";
				success = new DwzResult("200", mess, "rel_listJksqMadeJkht", "",
						"closeCurrent", "");
			} else {
				success = new DwzResult("300", "保存失败", "rel_listJksqMadeJkht", "",
						"closeCurrent", "");
			}
			
	    }catch(Exception e){
			success = new DwzResult("300", "保存失败", "",
					"", "", "");
			
		}finally{
			/*
            if("not".equals(first)){
                context.removeAttribute("wordgenerate");
            }
            */
	    }
		ServletUtils.renderJson(response, success);
		return null;

	}
	@RequestMapping(value = "/lookImg")
	public String lookImg(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String path = request.getParameter("path");
		String name = request.getParameter("name");
		String mainId = request.getParameter("mainId");
		String fileOwner = request.getParameter("fileOwner");
		String order = request.getParameter("order");
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("fileOwner", fileOwner);
		filters.put("mainId", Long.valueOf(mainId));
		List<XhUploadFiles> files = baseInfoManager.findXhUploadFiles(filters);
		model.addAttribute("files", files);
		model.addAttribute("filesCon", files.size());

		// request.setAttribute("fileOwner",fileOwner);
		// request.setAttribute("mainId",mainId);
		request.setAttribute("imgAddress", path + "/" + name);
		request.setAttribute("order", order);
		// 因为直接输出内容而不经过jsp,因此返回null.
		return "jkht/imgOne";
	}

	// @RequestMapping(value="/lookImgNext")
	// public String lookImgNext(HttpServletRequest request, HttpServletResponse
	// response, Model model) {
	// String imgAddress = request.getParameter("imgAddress");
	// String mainId = request.getParameter("mainId");
	// String fileOwner = request.getParameter("fileOwner");
	// String order = request.getParameter("order");
	// Map<String, Object> filters = new HashMap<String, Object>();
	// filters.put("fileOwner", fileOwner);
	// filters.put("mainId", Long.valueOf(mainId));
	// List<XhUploadFiles> files = baseInfoManager.findXhUploadFiles(filters);
	// model.addAttribute("files", files);
	// model.addAttribute("filesCon", files.size());
	// request.setAttribute("imgAddress",imgAddress);
	// //因为直接输出内容而不经过jsp,因此返回null.
	// return "jkht/img";
	// }
	// @RequestMapping(value="/dispImg")
	// @ResponseBody
	// public String dispImg(HttpServletRequest request){
	// String imgAddress = request.getParameter("imgAddress");
	// String order = request.getParameter("order");
	// return imgAddress;
	// }
	/**
	 * 删除借款合同
	 * 
	 * @param Id
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/delXhJkht/{Id}")
	public String delUser(@PathVariable Long Id, HttpServletResponse response) {

		boolean isSuccess = xhJkhtManager.deleteXhJkhtByjksq(Id);

		DwzResult success = null;
		if (isSuccess) {
			success = new DwzResult("200", "删除成功", "rel_listJksqForJkht", "",
					"", "");
		} else {
			success = new DwzResult("300", "删除失败", "rel_listJksqForJkht", "",
					"", "");
		}
		ServletUtils.renderJson(response, success);
		return null;

	}

	/**
	 * 批量删除借款合同
	 * 
	 * @param Id
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/batchdelXhJkht")
	public String batchDelUser(HttpServletRequest request,
			HttpServletResponse response) {
		String ids = request.getParameter("ids");
		String[] Ids = ids.split(",");
		boolean isSuccess = xhJkhtManager.batchDelXhJkht(Ids);

		DwzResult success = null;
		if (isSuccess) {
			success = new DwzResult("200", "删除成功", "rel_listXhJkht", "", "", "");
		} else {
			success = new DwzResult("300", "删除失败", "rel_listXhJkht", "", "", "");
		}
		ServletUtils.renderJson(response, success);
		return null;
	}

	/**
	 * 重新制作
	 * 
	 * @param cjrxx
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/madeXhJkhtReset/{Id}")
	public ModelAndView madeXhJkht(@PathVariable Long Id,
			HttpServletResponse response, Model model) {

		List<MiddleMan> MiddleMan = xhJkhtManager.getSuggestMiddleMan("0");
		model.addAttribute("MiddleMan", MiddleMan);
		XhJkht xhJkht = xhJkhtManager.findLoanContarctByApplyID(Id);

		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("fileOwner", "XH_JKHT");
		filters.put("mainId", xhJkht.getId());
		List<XhUploadFiles> files = baseInfoManager.findXhUploadFiles(filters);
		model.addAttribute("files", files);

		// 贷款利率
		List<MateData> dkllMateDateList = baseInfoManager
				.getTypeByCode("10003");
		model.addAttribute("dkllList", dkllMateDateList);
		return new ModelAndView("jkht/madeXhJkht", "xhJkht", xhJkht);
		/*
		 * //得到当前登录用户 OperatorDetails operator =
		 * SpringSecurityUtils.getCurrentUser(); // String loginName =
		 * operator.getUsername(); // String loginName = operator.getCtiCode();
		 * if(operator==null) { return "redirect:../login"; } XhJkht xhJkht =
		 * xhJkhtManager.findLoanContarctByApplyID(Id); if
		 * (xhJkhtManager.createFile(xhJkht)) { DwzResult success = new
		 * DwzResult("200","合同制作成功","","","","");
		 * ServletUtils.renderJson(response, success); }else{ DwzResult success
		 * = new DwzResult("200","合同制作失败","","","","");
		 * ServletUtils.renderJson(response, success); } return null;
		 */
	}

	/**
	 * 重新制作-- 保存借款合同
	 * 
	 * @param xhJkht
	 * @param request
	 * @param response
	 * @return
	 */

	@RequestMapping(value = "/madeXhJkhtSave", method = RequestMethod.POST)
	public void madeXhJkhtSave(@ModelAttribute("xhJkht") XhJkht xhJkht,
			HttpServletRequest request, HttpServletResponse response) {
	    DwzResult success = null;
	    String first = null;
	    Object wordgenerate = null;
	    try{
    		/*
    		synchronized(countLock){
                wordgenerate = context.getAttribute("wordgenerate");
                if ( wordgenerate != null) {
                    success = new DwzResult("300", "合同制作进程被占用，请稍候重新制作,",
                            "", "", "", "");
                    ServletUtils.renderJson(response, success);
                    return ;
                }
                first = "not";
                context.setAttribute("wordgenerate", request.getSession().getId());
            }
            */
    		/*try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }*/
    		boolean isSuccess = xhJkhtManager.madeXhJkhtSave(xhJkht, request);
    	   		
    		if (isSuccess) {
    			//String time = baseInfoManager.waiteTime("xh_jkht");
    			String mess = "保存成功,稍后查看。";
    			success = new DwzResult("200", mess, "rel_listJksqQuery", "",
    					"closeCurrent", "");
    		} else {
    			success = new DwzResult("300", "保存失败", "rel_listJksqQuery", "",
    					"closeCurrent", "");
    		}
	    }finally{
	    	/*
            if("not".equals(first)){
                context.removeAttribute("wordgenerate");
            }
            */
	    }
	    ServletUtils.renderJson(response, success);

	}

	/**
	 * 协议查看框架页
	 * 
	 * @param Id
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/downLoadFile/{Id}", method = RequestMethod.GET)
	public ModelAndView downLoadFile(@PathVariable Long Id,
			HttpServletRequest request, Model model) {
		XhJksq xhJksq = xhjksqManager.getXhJksq(Id);
		model.addAttribute("xhJksq", xhJksq);
		// XhJkht xhJkht = xhJksq.getXhjkht();
		XhJkht xhJkht = xhJkhtManager.findLoanContarctByApplyID(Id);

		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("fileOwner", "XH_JKHT");
		filters.put("mainId", xhJkht.getId());
		List<XhUploadFiles> files = baseInfoManager.findXhUploadFiles(filters);
		model.addAttribute("files", files);
		return new ModelAndView("jkht/downloadFile", "xhJkht", xhJkht);
	}
	
	/**
	 * back remove somethig
	 *
	 * @param request
	 * @param response
	 * @author xjs
	 * @date 2013-8-5 下午9:04:38
	 */
	@RequestMapping(value = "/iferror")
	@ResponseBody
    public String errorif(HttpServletRequest request, HttpServletResponse response) {
	   //context.removeAttribute("wordgenerate");
	   return "success";
	}
	
	
	/**
	 * 编辑重置利率借款合同
	 * 
	 * @param Id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/resetXhJkht/{Id}", method = RequestMethod.GET)
	public ModelAndView resetXhJkht(@PathVariable Long Id, Model model,
			HttpServletRequest request) {
		List<MiddleMan> MiddleMan = xhJkhtManager.getSuggestMiddleMan("0");
		model.addAttribute("MiddleMan", MiddleMan);
		XhJkht xhJkht = xhJkhtManager.findLoanContarctByApplyID(Id);

		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("fileOwner", "XH_JKHT");
		filters.put("mainId", xhJkht.getId());
		List<XhUploadFiles> files = baseInfoManager.findXhUploadFiles(filters);
		model.addAttribute("files", files);

		// 贷款利率
		List<MateData> dkllMateDateList = baseInfoManager
				.getTypeByCode("10003");
		model.addAttribute("dkllList", dkllMateDateList);
		return new ModelAndView("jkht/xhJkhtResetInput", "xhJkht", xhJkht);
	}
	
	/**
	 * 借款合同重置利率
	 * 
	 * @param xhJkht
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value = "/saveResetXhJkht", method = RequestMethod.POST)
	public void saveResetXhJkht(@ModelAttribute("xhJkht") XhJkht xhJkht,
			HttpServletRequest request, HttpServletResponse response) {
		DwzResult success = null;
		HibernateAwareBeanUtilsBean beanCopy = new HibernateAwareBeanUtilsBean();
		try {
			Long id = Long.parseLong(request.getParameter("xhJksq.id"));
			XhJksq xhJksq = xhjksqManager.getXhJksq(id);
			//xhJksq.setLoanCode(xhJkht.getJkhtbm());
			xhJkht.setXhJksq(xhJksq);
			
			XhJkht xhJkhtold = xhJkhtManager.getXhJkht(xhJkht.getId());
			
			beanCopy.copyProperties(xhJkhtold, xhJkht);
			
			xhJkhtManager.saveXhJkht(xhJkhtold);

			success = new DwzResult("200", "保存成功", "rel_listJksqQuery",
					"", "closeCurrent", "");
		} catch(Exception e){
			success = new DwzResult("300", "保存失败", "",
					"", "", "");
			e.printStackTrace();
		}
		ServletUtils.renderJson(response, success);
	}
	
	/**
	 * 编辑重置利率借款合同（旧版公式）
	 * 
	 * @param Id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/resetOldXhJkht/{Id}", method = RequestMethod.GET)
	public ModelAndView resetOldXhJkht(@PathVariable Long Id, Model model,
			HttpServletRequest request) {
		List<MiddleMan> MiddleMan = xhJkhtManager.getSuggestMiddleMan("0");
		model.addAttribute("MiddleMan", MiddleMan);
		XhJkht xhJkht = xhJkhtManager.findLoanContarctByApplyID(Id);

		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("fileOwner", "XH_JKHT");
		filters.put("mainId", xhJkht.getId());
		List<XhUploadFiles> files = baseInfoManager.findXhUploadFiles(filters);
		model.addAttribute("files", files);

		// 贷款利率
		List<MateData> dkllMateDateList = baseInfoManager
				.getTypeByCode("10003");
		model.addAttribute("dkllList", dkllMateDateList);
		return new ModelAndView("jkht/xhJkhtResetOldInput", "xhJkht", xhJkht);
	}
	
	
	
	
	/**
	 * 批量确认
	 */
	@RequestMapping(value = "/saveQrfkPl")
	public void saveQrfkPl(HttpServletRequest request,
			HttpServletResponse response) {
			
		String ids = request.getParameter("ids");
		String[] Ids = ids.split(",");
		String id = "";
		XhJksq xhJksq = new XhJksq();
		for (int i = 0; i < Ids.length; i++) {
			id = Ids[i];
			xhJksq = xhjksqManager.getXhJksq(Long.parseLong(id));
			xhJkhtManager.saveXhJkhtQrfk(xhJksq);
		}
		
		DwzResult success = new DwzResult("200", "操作成功", "rel_listJksqFkqrsh", "", "", "");
		ServletUtils.renderJson(response, success);
	}
	
	
	/*
	 * 退回
	 */
	@RequestMapping(value = "/QrfkPlBack/{Id}",method = RequestMethod.GET)
	public ModelAndView QrfkPlBack(@PathVariable Long Id,Model model,
			HttpServletRequest request) {
		XhJksq xhJksq = xhjksqManager.getXhJksq(Id);
		return new ModelAndView("jkht/xhJkhtQrfkBackInput", "xhJksq", xhJksq);
	}
	
	@RequestMapping(value = "/saveQrfkPlBack", method = RequestMethod.POST)
	public String saveQrfkPlBack(@ModelAttribute("xhJksq") XhJksq xhJksq,HttpServletRequest request,
			HttpServletResponse response) {
			
		//String id = request.getParameter("id");
		String remark = request.getParameter("remark");
		xhJksq = xhjksqManager.getXhJksq(xhJksq.getId());
		xhJkhtManager.saveXhJkhtQrfkBack(xhJksq,remark);
		
		DwzResult success = new DwzResult("200", "操作成功", "", "", "closeCurrent", "");
		ServletUtils.renderJson(response, success);
		return null;
	}
	
}




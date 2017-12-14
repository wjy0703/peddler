package cn.com.cucsi.app.web.xhcf;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import cn.com.cucsi.app.entity.baseinfo.Attr;
import cn.com.cucsi.app.entity.baseinfo.City;
import cn.com.cucsi.app.entity.baseinfo.Employee;
import cn.com.cucsi.app.entity.baseinfo.Organi;
import cn.com.cucsi.app.service.baseinfo.BaseInfoManager;
import cn.com.cucsi.app.service.excel.LoanBackExcelService;
import cn.com.cucsi.app.service.excel.StatisticalService;
import cn.com.cucsi.app.service.excel.TzsqExcelService;
import cn.com.cucsi.app.service.excel.YqbbExcelService;
import cn.com.cucsi.app.service.excel.ZwdqExcelService;
import cn.com.cucsi.app.service.security.OperatorDetails;
import cn.com.cucsi.app.service.xhcf.CjrxxManager;
import cn.com.cucsi.app.service.xhcf.ExportManager;
import cn.com.cucsi.app.service.xhcf.XhTzsqManager;
import cn.com.cucsi.app.web.InitSetupListener;
import cn.com.cucsi.app.web.util.FileUtil;
import cn.com.cucsi.app.web.util.JdbcPageUtils;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils;
import cn.com.cucsi.core.utils.DateUtils;

@Controller
@RequestMapping(value = "/report")
public class ReportController {
	private ExportManager exportManager;
	private BaseInfoManager baseInfoManager;
	 @Autowired
	    private XhTzsqManager xhTzsqManager;

	@Autowired
	public void setBaseInfoManager(BaseInfoManager baseInfoManager) {
		this.baseInfoManager = baseInfoManager;
	}

	@Autowired
	public void setExportManager(ExportManager exportManager) {
		this.exportManager = exportManager;
	}

	private CjrxxManager cjrxxManager;

	@Autowired
	public void setCjrxxManager(CjrxxManager cjrxxManager) {
		this.cjrxxManager = cjrxxManager;
	}

	private YqbbExcelService yqbbExcelService;

	@Autowired
	public void setYqbbExcelService(YqbbExcelService yqbbExcelService) {
		this.yqbbExcelService = yqbbExcelService;
	}

	@RequestMapping(value = "/jkhz")
	public String appStat(HttpServletRequest request, Model model) {
		// 得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		if (operator == null) {
			return "redirect:../login";
		}
		String loginName = SpringSecurityUtils.getCurrentUserName();
		// Employee employee =
		// accountManager.findUserByLoginName(loginName).getEmployee();
		// model.addAttribute("employee", employee);
		// List<Map<String, Object>> cityList =
		// accountManager.getCityList(employee.getCity().getCityCode(),
		// employee.getCity().getId()+"");
		// model.addAttribute("cityList", cityList);

		String filter_jkbh = request.getParameter("filter_jkbh");
		String filter_jkrxm = request.getParameter("filter_jkrxm");
		String filter_crm = request.getParameter("employeeCca.id");
		String filter_cca = request.getParameter("employeeCrm.id");
		String filter_crty = request.getParameter("filter_crty");
		String startjkrq = request.getParameter("startjkrq");

		String endjkrq = request.getParameter("endjkrq");
		String filter_ztFlag = request.getParameter("filter_ztFlag");

		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> conditions = new HashMap<String, Object>();
		String sql = "";
		if (null != filter_jkbh && !"".equals(filter_jkbh)) {
			sql = sql + " AND T.LOAN_CODE like '%" + filter_jkbh + "%' ";
		}
		if (null != filter_jkrxm && !"".equals(filter_jkrxm)) {
			sql = sql + " and T.JKRXM like '%" + filter_jkrxm + "%' ";
		}
		if (null != filter_crm && !"".equals(filter_crm)) {
			sql = sql + " and D.EMPLOYEE_CRM like '%" + filter_crm + "%' ";
		}
		if (null != filter_cca && !"".equals(filter_cca)) {
			sql = sql + " and D.EMPLOYEE_CCA like '%" + filter_cca + "%' ";
		}
		// if(null == cityChoose || "".equals(cityChoose)){
		// cityChoose = employee.getCity().getId() +"";
		// }
		// sql = sql + " and A.CITY_ID = '"+cityChoose+"' ";
		if (null != filter_crty && !"".equals(filter_crty)) {
			sql = sql + " and T.CITY = '" + filter_crty + "' ";
		}
		if (null != startjkrq && !"".equals(startjkrq)) {
			sql = sql + " and TO_CHAR(T.JK_LOAN_DATE,'yyyy-MM-dd') > '"
					+ startjkrq + "' ";
		}
		if (null != endjkrq && !"".equals(endjkrq)) {
			sql = sql + " and TO_CHAR(T.JK_LOAN_DATE,'yyyy-MM-dd') < '"
					+ endjkrq + "' ";
		}

		conditions.put("sql", sql);

		List<Map<String, Object>> applicList = exportManager.getJksq(sql);
		map.put("queryList", applicList);
		List<Map<String, Object>> applicCountList = exportManager
				.getJksqCount(sql);
		map.put("applicCountList", applicCountList);
		model.addAttribute("row", map);

		model.addAttribute("filter_jkbh", filter_jkbh);
		model.addAttribute("filter_jkrxm", filter_jkrxm);
		model.addAttribute("filter_crm", filter_crm);
		model.addAttribute("filter_cca", filter_cca);
		model.addAttribute("startjkrq", startjkrq);
		model.addAttribute("endjkrq", endjkrq);
		List<City> crmprovince = baseInfoManager.getSuggestCity("0");
		request.setAttribute("crmprovince", crmprovince);
		return "report/jkhz";
	}

	@RequestMapping(value = "/exportJkhz")
	public String listjkzh(HttpServletRequest request, Model model,
			HttpServletResponse response) throws IOException {
		String filePath = request.getSession().getServletContext()
				.getRealPath("themes\\temp\\");
		String loginName = SpringSecurityUtils.getCurrentUserName();
		// Employee employee =
		// accountManager.findUserByLoginName(loginName).getEmployee();
		String filter_jkbh = request.getParameter("filter_jkbh");
		String filter_jkrxm = request.getParameter("filter_jkrxm");
		String filter_crm = request.getParameter("employeeCca.id");
		String filter_cca = request.getParameter("employeeCrm.id");
		String filter_crty = request.getParameter("filter_crty");
		String startjkrq = request.getParameter("startjkrq");

		String endjkrq = request.getParameter("endjkrq");
		String filter_ztFlag = request.getParameter("filter_ztFlag");

		String sql = "";
		if (null != filter_jkbh && !"".equals(filter_jkbh)) {
			sql = sql + " AND T.LOAN_CODE like '%" + filter_jkbh + "%' ";
		}
		if (null != filter_jkrxm && !"".equals(filter_jkrxm)) {
			sql = sql + " and T.JKRXM like '%" + filter_jkrxm + "%' ";
		}
		if (null != filter_crm && !"".equals(filter_crm)) {
			sql = sql + " and D.EMPLOYEE_CRM like '%" + filter_crm + "%' ";
		}
		if (null != filter_cca && !"".equals(filter_cca)) {
			sql = sql + " and D.EMPLOYEE_CCA like '%" + filter_cca + "%' ";
		}
		if (null != filter_crty && !"".equals(filter_crty)) {
			sql = sql + " and T.CITY = '" + filter_crty + "' ";
		}
		// if(null == cityChoose || "".equals(cityChoose)){
		// cityChoose = employee.getCity().getId() +"";
		// }
		// sql = sql + " and A.CITY_ID = '"+cityChoose+"' ";
		if (null != startjkrq && !"".equals(startjkrq)) {
			sql = sql + " and TO_CHAR(T.JK_LOAN_DATE,'yyyy-MM-dd') > '"
					+ startjkrq + "' ";
		}
		if (null != endjkrq && !"".equals(endjkrq)) {
			sql = sql + " and TO_CHAR(T.JK_LOAN_DATE,'yyyy-MM-dd') < '"
					+ endjkrq + "' ";
		}
		String path = exportManager.exportJksq(filePath, sql);
		response.setContentType("APPLICATION/OCTET-STREAM");
		Date createTime = DateUtils.getSqlDate();
		response.setHeader(
				"Content-Disposition",
				"attachment; filename=\"JieKuanHuiZhong_"
						+ createTime.toString() + "_.xls" + "\"");
		File excel = new File(path);
		if (!excel.exists()) {
			excel.mkdirs();
		} else {
			FileInputStream fis = new FileInputStream(excel);
			ServletOutputStream clientOut = response.getOutputStream();
			byte[] buffer = new byte[1024];
			int read = 0;
			while ((read = fis.read(buffer, 0, buffer.length)) != -1) {
				clientOut.write(buffer, 0, read);
			}
			fis.close();
			excel.delete();
		}
		return null;

	}

	// 货款咨询统计查询
	@RequestMapping(value = "/hkzxtj")
	public String listhkzxtj(HttpServletRequest request, Model model) {
		// 得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		if (operator == null) {
			return "redirect:../login";
		}
		String loginName = SpringSecurityUtils.getCurrentUserName();
		String filter_jkbh = request.getParameter("filter_zxbm");
		String filter_jkrxm = request.getParameter("filter_zxrxm");
		String filter_crm = request.getParameter("employeeCrm.id");
		String filter_cca = request.getParameter("employeeCca.id");
		String startjkrq = request.getParameter("startjkrq");
		String endjkrq = request.getParameter("endjkrq");

		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> conditions = new HashMap<String, Object>();
		String sql = "";
		if (null != filter_jkbh && !"".equals(filter_jkbh)) {
			sql = sql + " AND T.LOAN_CODE like '%" + filter_jkbh + "%' ";
		}
		if (null != filter_jkrxm && !"".equals(filter_jkrxm)) {
			sql = sql + " and T.JKRXM like '%" + filter_jkrxm + "%' ";
		}
		if (null != filter_crm && !"".equals(filter_crm)) {
			sql = sql + " and A.EMPLOYEE_CRM like '%" + filter_crm + "%' ";
		}
		if (null != filter_cca && !"".equals(filter_cca)) {
			sql = sql + " and A.EMPLOYEE_CCA like '%" + filter_cca + "%' ";
		}

		if (null != startjkrq && !"".equals(startjkrq)) {
			sql = sql + " and TO_CHAR(A.CREATE_TIME,'yyyy-MM-dd') > '"
					+ startjkrq + "' ";
		}
		if (null != endjkrq && !"".equals(endjkrq)) {
			sql = sql + " and TO_CHAR(A.CREATE_TIME,'yyyy-MM-dd') < '"
					+ endjkrq + "' ";
		}
		conditions.put("sql", sql);

		List<Map<String, Object>> applicList = exportManager.getZxtj(sql);
		map.put("queryList", applicList);
		model.addAttribute("row", map);
		model.addAttribute("filter_jkbh", filter_jkbh);
		model.addAttribute("filter_jkrxm", filter_jkrxm);
		model.addAttribute("filter_crm", filter_crm);
		model.addAttribute("filter_cca", filter_cca);
		model.addAttribute("startjkrq", startjkrq);
		model.addAttribute("endjkrq", endjkrq);
		return "report/hkzxtj";
	}

	// 借款咨询统计导出查询
	@RequestMapping(value = "/exportZxhz")
	public String listzxhz(HttpServletRequest request, Model model,
			HttpServletResponse response) throws IOException {
		String filePath = request.getSession().getServletContext()
				.getRealPath("themes\\temp\\");
		String loginName = SpringSecurityUtils.getCurrentUserName();
		// Employee employee =
		// accountManager.findUserByLoginName(loginName).getEmployee();
		String filter_jkbh = request.getParameter("filter_zxbm");
		String filter_jkrxm = request.getParameter("filter_zxrxm");
		String filter_crm = request.getParameter("employeeCrm.id");
		String filter_cca = request.getParameter("employeeCca.id");
		String startjkrq = request.getParameter("startjkrq");
		String endjkrq = request.getParameter("endjkrq");

		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> conditions = new HashMap<String, Object>();
		String sql = "";
		if (null != filter_jkbh && !"".equals(filter_jkbh)) {
			sql = sql + " AND T.LOAN_CODE like '%" + filter_jkbh + "%' ";
		}
		if (null != filter_jkrxm && !"".equals(filter_jkrxm)) {
			sql = sql + " and T.JKRXM like '%" + filter_jkrxm + "%' ";
		}
		if (null != filter_crm && !"".equals(filter_crm)) {
			sql = sql + " and A.EMPLOYEE_CRM like '%" + filter_crm + "%' ";
		}
		if (null != filter_cca && !"".equals(filter_cca)) {
			sql = sql + " and A.EMPLOYEE_CCA like '%" + filter_cca + "%' ";
		}

		if (null != startjkrq && !"".equals(startjkrq)) {
			sql = sql + " and TO_CHAR(A.CREATE_TIME,'yyyy-MM-dd') > '"
					+ startjkrq + "' ";
		}
		if (null != endjkrq && !"".equals(endjkrq)) {
			sql = sql + " and TO_CHAR(A.CREATE_TIME,'yyyy-MM-dd') < '"
					+ endjkrq + "' ";
		}
		String path = exportManager.exportZxtj(filePath, sql);
		response.setContentType("APPLICATION/OCTET-STREAM");
		Date createTime = DateUtils.getSqlDate();
		response.setHeader("Content-Disposition",
				"attachment; filename=\"jieKuanZiXun_" + createTime.toString()
						+ "_.xls" + "\"");
		File excel = new File(path);
		if (!excel.exists()) {
			excel.mkdirs();
		} else {
			FileInputStream fis = new FileInputStream(excel);
			ServletOutputStream clientOut = response.getOutputStream();
			byte[] buffer = new byte[1024];
			int read = 0;
			while ((read = fis.read(buffer, 0, buffer.length)) != -1) {
				clientOut.write(buffer, 0, read);
			}
			fis.close();
			excel.delete();
		}
		return null;

	}

	@RequestMapping(value = "/lczx")
	public String listlczx(HttpServletRequest request, Model model) {
		// 得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		if (operator == null) {
			return "redirect:../login";
		}
		String loginName = SpringSecurityUtils.getCurrentUserName();
		String filter_jkbh = request.getParameter("filter_jkbh");
		String filter_jkrxm = request.getParameter("filter_jkrxm");
		String filter_crm = request.getParameter("employeeCrm.id");
		String filter_cca = request.getParameter("employeeCca.id");
		String startjkrq = request.getParameter("startjkrq");
		String endjkrq = request.getParameter("endjkrq");

		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> conditions = new HashMap<String, Object>();
		String sql = "";
		if (null != filter_jkbh && !"".equals(filter_jkbh)) {
			sql = sql + " AND A.ZXBM like '%" + filter_jkbh + "%' ";
		}
		if (null != filter_jkrxm && !"".equals(filter_jkrxm)) {
			sql = sql + " and A.KHMC like '%" + filter_jkrxm + "%' ";
		}
		if (null != filter_crm && !"".equals(filter_crm)) {
			sql = sql + " and A.EMPLOYEE_CRM like '%" + filter_crm + "%' ";
		}
		if (null != filter_cca && !"".equals(filter_cca)) {
			sql = sql + " and A.EMPLOYEE_CCA like '%" + filter_cca + "%' ";
		}

		if (null != startjkrq && !"".equals(startjkrq)) {
			sql = sql + " and TO_CHAR(A.CREATE_TIME,'yyyy-MM-dd') > '"
					+ startjkrq + "' ";
		}
		if (null != endjkrq && !"".equals(endjkrq)) {
			sql = sql + " and TO_CHAR(A.CREATE_TIME,'yyyy-MM-dd') < '"
					+ endjkrq + "' ";
		}
		conditions.put("sql", sql);
		List<Map<String, Object>> applicList = exportManager.getlczx(sql);
		map.put("queryList", applicList);
		List<Map<String, Object>> applicCountList = exportManager
				.getLczxCount(sql);
		map.put("applicCountList", applicCountList);
		model.addAttribute("row", map);
		model.addAttribute("filter_jkbh", filter_jkbh);
		model.addAttribute("filter_jkrxm", filter_jkrxm);
		model.addAttribute("filter_crm", filter_crm);
		model.addAttribute("filter_cca", filter_cca);
		model.addAttribute("startjkrq", startjkrq);
		model.addAttribute("endjkrq", endjkrq);

		return "report/lczx";

	}

	@RequestMapping(value = "/exportLczx")
	public String listLczx(HttpServletRequest request, Model model,
			HttpServletResponse response) throws IOException {
		String filePath = request.getSession().getServletContext()
				.getRealPath("themes\\temp\\");
		String loginName = SpringSecurityUtils.getCurrentUserName();
		// Employee employee =
		// accountManager.findUserByLoginName(loginName).getEmployee();
		String filter_jkbh = request.getParameter("filter_jkbh");
		String filter_jkrxm = request.getParameter("filter_jkrxm");
		String filter_crm = request.getParameter("employeeCrm.id");
		String filter_cca = request.getParameter("employeeCca.id");
		String startjkrq = request.getParameter("startjkrq");
		String endjkrq = request.getParameter("endjkrq");

		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> conditions = new HashMap<String, Object>();
		String sql = "";
		if (null != filter_jkbh && !"".equals(filter_jkbh)) {
			sql = sql + " AND A.ZXBM like '%" + filter_jkbh + "%' ";
		}
		if (null != filter_jkrxm && !"".equals(filter_jkrxm)) {
			sql = sql + " and A.KHMC like '%" + filter_jkrxm + "%' ";
		}
		if (null != filter_crm && !"".equals(filter_crm)) {
			sql = sql + " and A.EMPLOYEE_CRM like '%" + filter_crm + "%' ";
		}
		if (null != filter_cca && !"".equals(filter_cca)) {
			sql = sql + " and A.EMPLOYEE_CCA like '%" + filter_cca + "%' ";
		}

		if (null != startjkrq && !"".equals(startjkrq)) {
			sql = sql + " and TO_CHAR(A.CREATE_TIME,'yyyy-MM-dd') > '"
					+ startjkrq + "' ";
		}
		if (null != endjkrq && !"".equals(endjkrq)) {
			sql = sql + " and TO_CHAR(A.CREATE_TIME,'yyyy-MM-dd') < '"
					+ endjkrq + "' ";
		}
		String path = exportManager.exportLczx(filePath, sql);
		response.setContentType("APPLICATION/OCTET-STREAM");
		Date createTime = DateUtils.getSqlDate();
		response.setHeader("Content-Disposition",
				"attachment; filename=\"LCZX_" + createTime.toString()
						+ "_.xls" + "\"");
		File excel = new File(path);
		if (!excel.exists()) {
			excel.mkdirs();
		} else {
			FileInputStream fis = new FileInputStream(excel);
			ServletOutputStream clientOut = response.getOutputStream();
			byte[] buffer = new byte[1024];
			int read = 0;
			while ((read = fis.read(buffer, 0, buffer.length)) != -1) {
				clientOut.write(buffer, 0, read);
			}
			fis.close();
			excel.delete();
		}
		return null;

	}

	@RequestMapping(value = "/hhcs")
	public String listhhcs(HttpServletRequest request, Model model) {

		return "report/hhcs";

	}

	@RequestMapping(value = "/hhhk")
	public String listhhhk(HttpServletRequest request, Model model) {

		return "report/hhhk";

	}

	@RequestMapping(value = "/lcxs")
	public String listlcxs(HttpServletRequest request, Model model) {

		return "report/lcxs";

	}

	@RequestMapping(value = "/lcyf")
	public String listlcys(HttpServletRequest request, Model model) {
		
		return "report/lcyf";

	}

	@Autowired
	private StatisticalService statisticalService;

	@RequestMapping(value = "/wfgztj")
	public String listwfgztj(HttpServletRequest request, Model model) {
		// 得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		if (operator == null) {
			return "redirect:../login";
		}

		List<Map<String, Object>> mendianTree = statisticalService
				.mendianTree("mendian");
		model.addAttribute("mendianTree", mendianTree);
		return "report/wfgztj";

	}

	@RequestMapping(value = "/wfgztjHj")
	public String listwfgztjHj(HttpServletRequest request, Model model) {
		// 得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		if (operator == null) {
			return "redirect:../login";
		}
		Map<String, Object> map = WebUtils.getParametersStartingWith(request,
				"filter_");
		// Map<String, Object> map = new HashMap<String, Object>();
		// String orgid = request.getParameter("organi.id");
		// String orgName = request.getParameter("organi.name");
		// String date = request.getParameter("date");

		if (map.containsKey("orgid") && map.containsKey("date")) {
			String orgid = String.valueOf(map.get("orgid"));
			String date = String.valueOf(map.get("date"));
			if (StringUtils.isNotEmpty(orgid) && StringUtils.isNotEmpty(date)) {
				map.put("orgid", orgid);
				map.put("date", date);
				// List<Map<String,Object>> list =
				// statisticalService.rebJksqStatis2(orgid, date);
				List<Map<String, Object>> list = statisticalService
						.getJksqForPage(orgid, date);
				model.addAttribute("list", list);
			}
		}

		model.addAttribute("map", map);
		return "report/wfgztjHj";

	}

	@RequestMapping(value = "/wfgztjTzsqIndex")
	public String wfgztjTzsq1(HttpServletRequest request, Model model) {
		// 得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		if (operator == null) {
			return "redirect:../login";
		}

		List<Map<String, Object>> mendianTree = statisticalService
				.mendianTree("cfmd");
		model.addAttribute("mendianTree", mendianTree);
		return "report/wfgztjTzsq1";

	}

	@RequestMapping(value = "/wfgztjTzsq")
	public String wfgztjTzsq(HttpServletRequest request, Model model) {
		// 得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		if (operator == null) {
			return "redirect:../login";
		}
		Map<String, Object> map = WebUtils.getParametersStartingWith(request,
				"filter_");
		// Map<String, Object> map = new HashMap<String, Object>();
		// String orgid = request.getParameter("organi.id");
		// String orgName = request.getParameter("organi.name");
		// String date = request.getParameter("date");

		if (map.containsKey("orgid") && map.containsKey("date")) {
			String orgid = String.valueOf(map.get("orgid"));
			String date = String.valueOf(map.get("date"));
			if (StringUtils.isNotEmpty(orgid) && StringUtils.isNotEmpty(date)) {
				map.put("orgid", orgid);
				map.put("date", date);
				// List<Map<String,Object>> list =
				// statisticalService.rebJksqStatis2(orgid, date);
				Map<String, Object> list = statisticalService.rebTzsqStatis(
						orgid, date);
				model.addAttribute("list", list);
			}
		}

		model.addAttribute("map", map);
		return "report/wfgztjTzsq";

	}

	// 信审统计查询
	@RequestMapping(value = "/xstj")
	public String listXstj(HttpServletRequest request, Model model) {
		// 得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		if (operator == null) {
			return "redirect:../login";
		}
		String loginName = SpringSecurityUtils.getCurrentUserName();
		String filter_jkrxm = request.getParameter("filter_jkrxm");
		String startjjrq = request.getParameter("startjjrq");
		String endjjrq = request.getParameter("endjjrq");

		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> conditions = new HashMap<String, Object>();
		String sql = "";
		if (null != filter_jkrxm && !"".equals(filter_jkrxm)) {
			sql = sql + " AND B.JKRXM like '%" + filter_jkrxm + "%' ";
		}

		if (null != startjjrq && !"".equals(startjjrq)) {
			sql = sql + " and to_char(b.create_time,'yyyy-mm-dd') > '"
					+ startjjrq + "' ";
		}
		if (null != endjjrq && !"".equals(endjjrq)) {
			sql = sql + " and to_char(b.create_time,'yyyy-mm-dd') < '"
					+ endjjrq + "' ";
		}
		conditions.put("sql", sql);

		List<Map<String, Object>> applicList = exportManager.getXstj(sql);
		map.put("queryList", applicList);
		List<Map<String, Object>> applicCountList = exportManager
				.getXstjCount(sql);
		map.put("applicCountList", applicCountList);
		model.addAttribute("row", map);
		model.addAttribute("filter_jkbh", filter_jkrxm);
		model.addAttribute("startjjrq", startjjrq);
		model.addAttribute("endjjrq", endjjrq);

		return "report/xstj";

	}

	@RequestMapping(value = "/exportXstj")
	public String listXstj(HttpServletRequest request, Model model,
			HttpServletResponse response) throws IOException {
		String filePath = request.getSession().getServletContext()
				.getRealPath("themes\\temp\\");
		String loginName = SpringSecurityUtils.getCurrentUserName();
		// Employee employee =
		// accountManager.findUserByLoginName(loginName).getEmployee();
		String filter_jkbh = request.getParameter("filter_jkbh");
		String filter_jkrxm = request.getParameter("filter_jkrxm");
		String filter_crm = request.getParameter("filter_crm");

		String sql = "";
		if (null != filter_jkbh && !"".equals(filter_jkbh)) {
			sql = sql + " AND T.LOAN_CODE like '%" + filter_jkbh + "%' ";
		}
		if (null != filter_jkrxm && !"".equals(filter_jkrxm)) {
			sql = sql + " and T.JKRXM like '%" + filter_jkrxm + "%' ";
		}
		if (null != filter_crm && !"".equals(filter_crm)) {
			sql = sql + " and TEAMNAME like '%" + filter_crm + "%' ";
		}
		String path = exportManager.exportXstj(filePath, sql);
		response.setContentType("APPLICATION/OCTET-STREAM");
		Date createTime = DateUtils.getSqlDate();
		response.setHeader("Content-Disposition",
				"attachment; filename=\"XSTJ_" + createTime.toString()
						+ "_.xls" + "\"");
		File excel = new File(path);
		if (!excel.exists()) {
			excel.mkdirs();
		} else {
			FileInputStream fis = new FileInputStream(excel);
			ServletOutputStream clientOut = response.getOutputStream();
			byte[] buffer = new byte[1024];
			int read = 0;
			while ((read = fis.read(buffer, 0, buffer.length)) != -1) {
				clientOut.write(buffer, 0, read);
			}
			fis.close();
			excel.delete();
		}
		return null;

	}

	private TzsqExcelService tzsqExcelService;

	@Autowired
	public void setTzsqExcelService(TzsqExcelService tzsqExcelService) {
		this.tzsqExcelService = tzsqExcelService;
	}

	private ZwdqExcelService zwdqExcelService;

	@Autowired
	public void setZwdqExcelService(ZwdqExcelService zwdqExcelService) {
		this.zwdqExcelService = zwdqExcelService;
	}

	/**
	 * 理财业绩报表
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/lcyj")
	public String lcyj(HttpServletRequest request, Model model) {
		
		// 处理分页的参数
		JdbcPage page = JdbcPageUtils.generatePage(request);

		Map<String, Object> map = WebUtils.getParametersStartingWith(request,
				"filter_");
		// Map<String, Object> map =
		// ServletUtils.getParametersStartingWith2(request, "filter_");
		// 过滤查询内容，所需条件 ----开始
		map.put("startdate", request.getParameter("filter_startdate"));
		map.put("enddate", request.getParameter("filter_enddate"));
		map.put("filter_date", request.getParameter("filter_date"));
		Employee emp = baseInfoManager.getUserEmployee();
		// 得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		String loginName = operator.getCtiCode();
		map.put("loginName", loginName);
		map.put("emp", emp);
		// 过滤查询内容，所需条件 ----结束
		List<Map<String, Object>> listTzsq = tzsqExcelService.searchXhTzsq(map,
				page);

		model.addAttribute("listTzsq", listTzsq);
		model.addAttribute("map", map);
		Map<String, Object> mapYyb = new HashMap<String, Object>();
		mapYyb.put("cjrState", "1");
		mapYyb.put("state", "2");
		List<Map<String, Object>> listYyb = cjrxxManager.searchYyb(
				"queryYybList", mapYyb);
		model.addAttribute("listYyb", listYyb);
		model.addAttribute("page", page);
		return "report/lcyj";
	}

	/**
	 * 债务到期报表
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/zwdq")
	public String zwdq(HttpServletRequest request, Model model) {
		// 得到当前登录用户
		
		// 处理分页的参数
		JdbcPage page = JdbcPageUtils.generatePage(request);

		Map<String, Object> map = WebUtils.getParametersStartingWith(request,
				"filter_");
		// Map<String, Object> map =
		// ServletUtils.getParametersStartingWith2(request, "filter_");
		// 过滤查询内容，所需条件 ----开始
		Employee emp = baseInfoManager.getUserEmployee();
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		String loginName = operator.getCtiCode();
		map.put("loginName", loginName);
		map.put("emp", emp);
		// 过滤查询内容，所需条件 ----结束
		List<Map<String, Object>> listTzsq = zwdqExcelService.searchXhTzsq(map,
				page);

		model.addAttribute("listTzsq", listTzsq);
		model.addAttribute("map", map);
		Map<String, Object> mapYyb = new HashMap<String, Object>();
		mapYyb.put("cjrState", "1");
		mapYyb.put("state", "2");
		List<Map<String, Object>> listYyb = cjrxxManager.searchYyb(
				"queryYybList", mapYyb);
		model.addAttribute("listYyb", listYyb);
		model.addAttribute("page", page);
		return "report/zwdq";
	}

	/**
	 * 逾期报表
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/yqbb")
	public String yqbb(HttpServletRequest request, Model model) {
		// 得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		if (operator == null) {
			return "redirect:../login";
		}
		// 处理分页的参数
		JdbcPage page = JdbcPageUtils.generatePage(request);

		Map<String, Object> map = WebUtils.getParametersStartingWith(request,
				"filter_");
		// Map<String, Object> map =
		// ServletUtils.getParametersStartingWith2(request, "filter_");
		// 过滤查询内容，所需条件 ----开始
		Employee emp = baseInfoManager.getUserEmployee();
		String loginName = operator.getCtiCode();
		map.put("loginName", loginName);
		map.put("emp", emp);
		// 过滤查询内容，所需条件 ----结束
		List<Map<String, Object>> listTzsq = yqbbExcelService
				.searchXhCapitalOverdue(map, page);
		model.addAttribute("organiId", request.getParameter("filter_organi.id"));
	    model.addAttribute("organiName", request.getParameter("filter_organi.name"));
		model.addAttribute("listTzsq", listTzsq);
		model.addAttribute("map", map);
		Map<String, Object> mapYyb = new HashMap<String, Object>();
		mapYyb.put("cjrState", "1");
		mapYyb.put("state", "2");

		List<City> province = baseInfoManager.getSuggestCity("0");
		request.setAttribute("province", province);
		model.addAttribute("page", page);
		return "report/yqbb";
	}
	@Autowired
    LoanBackExcelService loanBackExcelService;
	
	/**
	 * 进件登记报表
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/jjdj")
	public String jjdj(HttpServletRequest request, Model model) {
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		// 处理分页的参数
		JdbcPage page = JdbcPageUtils.generatePage(request);

		Map<String, Object> map = WebUtils.getParametersStartingWith(request,
				"filter_");
		// 过滤查询内容，所需条件 ----开始
		Employee emp = baseInfoManager.getUserEmployee();
		String loginName = operator.getCtiCode();
		map.put("loginName", loginName);
		map.put("emp", emp);
		//取状态
		Map<String, Object> filter = new HashMap<String, Object>();
		filter.put("attrType", "1");
		List<Attr> attrList = baseInfoManager.getAttrList(filter);
		request.setAttribute("attrList", attrList);

		// 过滤查询内容，所需条件 ----结束
		List<Map<String, Object>> listJjdz = cjrxxManager.searchJjdj(map,
				page);
		List<Map<String,Object>> listYyb = loanBackExcelService.queryOrgin();
		model.addAttribute("listYyb", listYyb);
		
		model.addAttribute("listJjdz", listJjdz);
		model.addAttribute("map", map);
		model.addAttribute("page", page);
		
		return "report/jjdj";
	}
	
	
	@RequestMapping(value = "/exportJjdj")
	public synchronized ModelAndView exportJjdj(HttpServletRequest request,
			HttpServletResponse response){
		// 得到当前登录用户 searchXhZqtjForDown
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		String mouFilePath2 = InitSetupListener.filePath + "excel"
				+ File.separator;
		Map<String, Object> map = WebUtils.getParametersStartingWith(request,
				"filter_");
		// 过滤查询内容，所需条件 ----开始
		Employee emp = baseInfoManager.getUserEmployee();
		String loginName = operator.getCtiCode();
		map.put("loginName", loginName);
		map.put("emp", emp);
		// 过滤查询内容，所需条件 ----结束
		String orgName = "";
		if(map.containsKey("yyb")){
			String value = String.valueOf(map.get("yyb"));
			if(StringUtils.isNotEmpty(value)) {
				Organi organi = baseInfoManager.gerOrgani(Long.parseLong(value));
				orgName = organi.getRganiName();
			}
		}
		String fileName = "";
		String filePath = "";
		Date createTime = new Date();
		/*
		String gsdq = String.valueOf(map.get("gsdq"));
		if ("0021".equals(gsdq)) {
			fileName = "000191400200580_S022"
					+ DateUtils.format(createTime, "yyyyMMdd") + "_0001好易联.xls";
		} else {
		*/
			fileName = orgName + "进件登记表" + DateUtils.format(createTime, "yyyyMMdd")
					+ ".xls";
		//}
		filePath = mouFilePath2 + fileName;
		if(StringUtils.isNotEmpty(orgName)){
			cjrxxManager.exportProref2(filePath, map,orgName);
		}else{
			cjrxxManager.exportProref3(filePath, map);
		}
		response.setContentType("APPLICATION/OCTET-STREAM");

		FileUtil.downLoad(filePath, fileName, request, response);
		//System.out.println("删除单个文件===>" + filePath);
		//FileUtil.deleteFile(filePath);
		//System.out.println("删除单个文件   成功===>" + filePath);

		return null;
	}
	
	/**
	 * 财富进件登记查询
	 * 
	 */
	@RequestMapping(value="/cfjjdj")
	public String cfjjdj(HttpServletRequest request, Model model){
		// 得到当前登录用户 searchXhZqtjForDown
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
	    // 处理分页的参数
		JdbcPage page = JdbcPageUtils.generatePage(request);
		Map<String, Object> map = WebUtils.getParametersStartingWith(request, "filter_");
		// 过滤查询内容，所需条件 ----开始
		Employee emp = baseInfoManager.getUserEmployee();
		String loginName = operator.getCtiCode();
		map.put("loginName", loginName);
		map.put("emp", emp);
		// 过滤查询内容，所需条件 ----结束
		List<Map<String,Object>> listTzsq = xhTzsqManager.searchCfjjdj(map,page);
		
		model.addAttribute("listTzsq", listTzsq);
		Map<String, Object> mapYyb = new HashMap<String, Object>();
		mapYyb.put("cjrState", "1");
		mapYyb.put("state", "2");
		List<Map<String, Object>> listYyb = cjrxxManager.searchYyb(
				"queryYybList", mapYyb);
		model.addAttribute("listYyb", listYyb);
		model.addAttribute("page", page);
		model.addAttribute("map", map);
		return "report/cfjjdj";
	}
	
	@RequestMapping(value = "/exportCfJjdj")
	public synchronized ModelAndView exportCfJjdj(HttpServletRequest request,
			HttpServletResponse response){
		// 得到当前登录用户 searchXhZqtjForDown
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		String mouFilePath2 = InitSetupListener.filePath + "excel"
				+ File.separator;
		Map<String, Object> map = WebUtils.getParametersStartingWith(request,
				"filter_");
		// 过滤查询内容，所需条件 ----开始
		Employee emp = baseInfoManager.getUserEmployee();
		String loginName = operator.getCtiCode();
		map.put("loginName", loginName);
		map.put("emp", emp);
		// 过滤查询内容，所需条件 ----结束
		String orgName = "";
		
		if(map.containsKey("yyb")){
			String value = String.valueOf(map.get("yyb"));
			if(StringUtils.isNotEmpty(value)) {
				Organi organi = baseInfoManager.gerOrgani(Long.parseLong(value));
				orgName = organi.getRganiName();
			}
		}
		String fileName = "";
		String filePath = "";
		Date createTime = new Date();
		/*
		String gsdq = String.valueOf(map.get("gsdq"));
		if ("0021".equals(gsdq)) {
			fileName = "000191400200580_S022"
					+ DateUtils.format(createTime, "yyyyMMdd") + "_0001好易联.xls";
		} else {
		*/
			fileName = orgName + "财富进件登记表" + DateUtils.format(createTime, "yyyyMMdd")
					+ ".xls";
		//}
		filePath = mouFilePath2 + fileName;
		xhTzsqManager.exportCfJjdj(filePath, map);
		response.setContentType("APPLICATION/OCTET-STREAM");

		FileUtil.downLoad(filePath, fileName, request, response);
		//System.out.println("删除单个文件===>" + filePath);
		//FileUtil.deleteFile(filePath);
		//System.out.println("删除单个文件   成功===>" + filePath);

		return null;
	}
	public static void main(String[] args) {
		System.out.println(DateUtils.format(new Date(),"yyyy年MM月dd日"));
	}
}

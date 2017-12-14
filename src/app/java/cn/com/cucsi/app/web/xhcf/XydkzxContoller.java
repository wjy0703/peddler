package cn.com.cucsi.app.web.xhcf;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.cucsi.app.entity.baseinfo.City;
import cn.com.cucsi.app.entity.baseinfo.Employee;
import cn.com.cucsi.app.entity.baseinfo.MateData;
import cn.com.cucsi.app.entity.baseinfo.Xydkzx;
import cn.com.cucsi.app.entity.xhcf.ZxGtjl;
import cn.com.cucsi.app.service.baseinfo.BaseInfoManager;
import cn.com.cucsi.app.service.loan.XhJksqManager;
import cn.com.cucsi.app.service.security.OperatorDetails;
import cn.com.cucsi.app.service.xhcf.DksqManager;
import cn.com.cucsi.app.service.xhcf.ZxGtglManager;
import cn.com.cucsi.app.web.util.JdbcPageUtils;
import cn.com.cucsi.app.web.util.RequestPageUtils;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils;
import cn.com.cucsi.core.web.DwzResult;
import cn.com.cucsi.core.web.ServletUtils;

@Controller
@RequestMapping(value = "/zxgl")
public class XydkzxContoller {
	private Logger logger = LoggerFactory.getLogger(XydkzxContoller.class);
	private ZxGtglManager zxGtglManager;
	private DksqManager dksqManager;
	private BaseInfoManager baseInfoManager;
	private XhJksqManager xhjksqManager;

	@Autowired
	public void setZxGtglManager(ZxGtglManager zxGtglManager) {
		this.zxGtglManager = zxGtglManager;
	}

	@Autowired
	public void setDksqManager(DksqManager dksqManager) {
		this.dksqManager = dksqManager;
	}

	@Autowired
	public void setBaseInfoManager(BaseInfoManager baseInfoManager) {
		this.baseInfoManager = baseInfoManager;
	}

	@RequestMapping(value = "/listxydkzx")
	public String list(HttpServletRequest request, Model model) {
		// 处理分页的参数
		// 得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		JdbcPage page = JdbcPageUtils.generatePage(request);
		Map<String, Object> params = ServletUtils.getParametersStartingWith2(request, "filter_");
		Employee emp = baseInfoManager.getUserEmployee();
		String loginName = operator.getCtiCode();
		params.put("loginName", loginName);
		params.put("emp", emp);
		params.put("type", "0");
		logger.debug("查询咨询列表 参数为: " +params);
		List<Map<String, Object>> listKhzx = dksqManager.searchKhzx("queryXhKhzxList", params, page);
		model.addAttribute("organiId", request.getParameter("filter_organi.id"));
	    model.addAttribute("organiName", request.getParameter("filter_organi.name"));
		model.addAttribute("listKhzx", listKhzx);
		model.addAttribute("page", page);
		return "zxgl/xydkzx";
	}

	// 沟通管理
	@RequestMapping(value = "/listZxgtjl")
	public String listXhCjrgtjl(HttpServletRequest request, Model model) {
		// 处理分页的参数
		Page<ZxGtjl> page = new RequestPageUtils<ZxGtjl>().generatePage(request);
		Map<String, Object> params = ServletUtils.getParametersStartingWith2(
				request, "filter_");
		String xydkzx_id = request.getParameter("xydkzx_id");
		if (null != xydkzx_id && !"".equals(xydkzx_id)) {
			params.put("xydkzx_id", xydkzx_id);
			Xydkzx xyzx = dksqManager.getXydkzx(Long.parseLong(xydkzx_id));
			model.addAttribute("xydkzx", xyzx);
		}
		logger.debug("查询咨询记录列表 参数为: " +params);
		zxGtglManager.searchZxGtjl(page, params);

		model.addAttribute("page", page);
		model.addAttribute("map", params);
		return "zxgl/zxgyhl";

	}

	@RequestMapping(value = "/addXhCjrgtjl/{Id}", method = RequestMethod.GET)
	public ModelAndView tzsqjrxx(@PathVariable Long Id,
			HttpServletRequest request) {
		Xydkzx xydkzx = dksqManager.getXydkzx(Id);
		this.initialize(request, xydkzx);
		return new ModelAndView("zxgl/zxgyhlInput", "xydkzx", xydkzx);
	}

	private void initialize(HttpServletRequest request, Xydkzx xydkzx) {
		List<City> crmprovince = baseInfoManager.getSuggestCity("0");
		request.setAttribute("crmprovince", crmprovince);

		if (xydkzx != null) {
			if (StringUtils.isNotEmpty(xydkzx.getCrmprovince())) {
				List<City> citys = baseInfoManager.getSuggestCity(xydkzx
						.getCrmprovince());
				request.setAttribute("crty", citys);
			}
		}
	}


	@RequestMapping(value = "/saveZxGtjl", method = RequestMethod.POST)
	public void saveZxGtjl(@ModelAttribute("zxGtjl") ZxGtjl zxGtjl,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		// 保存沟通记录
		DwzResult success;

		if (zxGtjl.getXydkzx() != null && zxGtjl.getXydkzx().getId() != null) {
			Xydkzx xydkzx = dksqManager.getXydkzx(zxGtjl.getXydkzx().getId());
			zxGtjl.setXydkzx(xydkzx);
			zxGtglManager.saveZxGtjl(zxGtjl);
			xydkzx.setGtjl(zxGtjl.getGtjl());// 将沟通记录反写回咨询信息
			xydkzx.setGtTime(zxGtjl.getBcgtrq());
			if(!xydkzx.getZhuangTai().equals("3")){
				xydkzx.setZhuangTai(request.getParameter("state"));// songjf
			}
			
			dksqManager.saveXydkzx(xydkzx);
			success = new DwzResult("200", "保存成功", "rel_xydkzx", "",
					"closeCurrent", "");
		} else {
			success = new DwzResult("200", "保存失败", "", "", "", "");
		}
		ServletUtils.renderJson(response, success);
	}

	@RequestMapping(value = "/editZxGtjl/{Id}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable Long Id, HttpServletRequest request,
			Model model) {
		
		ZxGtjl ZxGtjl = zxGtglManager.getZxGtjl(Id);
		Xydkzx xydkzx = ZxGtjl.getXydkzx();
		model.addAttribute("xydkzx", xydkzx);
		model.addAttribute("xydkzx_id", xydkzx.getId());
		return new ModelAndView("zxgl/zxgyhlInput", "zxGtjl", ZxGtjl);
	}

	@RequestMapping(value = "/lookZxGtjl")
	public String viewkZxGtjl(HttpServletRequest request, Model model) {
		// 处理分页的参数
	    Page<ZxGtjl> page = new RequestPageUtils<ZxGtjl>().generatePage(request);
  		Map<String, Object> params = ServletUtils.getParametersStartingWith2(
				request, "filter_");
		String xydkzx_id = request.getParameter("xydkzx_id");
		if (null != xydkzx_id && !"".equals(xydkzx_id)) {
			params.put("xydkzx_id", xydkzx_id);
			Xydkzx xyzx = dksqManager.getXydkzx(Long.parseLong(xydkzx_id));
			model.addAttribute("xydkzx", xyzx);
		}
		zxGtglManager.searchZxGtjl(page, params);
		model.addAttribute("page", page);
		model.addAttribute("map", params);
		return "zxgl/zxgyhlLook";

	}

	@RequestMapping(value = "/inputZxGtjlLook/{Id}", method = RequestMethod.GET)
	public ModelAndView ZxGtjlLook(@PathVariable Long Id,
			HttpServletRequest request, Model model) {
		// 查看沟通记录
		ZxGtjl ZxGtjl = zxGtglManager.getZxGtjl(Id);
		Xydkzx xydkzx = ZxGtjl.getXydkzx();
		model.addAttribute("xydkzx", xydkzx);
		model.addAttribute("xydkzx_id", xydkzx.getId());
		return new ModelAndView("zxgl/zxgyhlInputLook", "zxGtjl", ZxGtjl);
	}

	@RequestMapping(value = "/delXhCjrgtjl/{Id}")
	public void delZxGtjl(@PathVariable Long Id, HttpServletResponse response) {
		// 得到当前登录用户
		zxGtglManager.deleteZxGtjl(Id);
		DwzResult success = new DwzResult("200", "删除成功", "rel_xydkzx", "", "",
				"");
		ServletUtils.renderJson(response, success);
	}

	@RequestMapping(value = "/addxydkzy", method = RequestMethod.GET)
	public ModelAndView add(HttpServletRequest request, Model model) {
		Employee employee = baseInfoManager.getUserEmployee();
		model.addAttribute("employee", employee);
		
		// 信用贷款新增
		Date date = new Date();
		SimpleDateFormat fat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String gtTime = fat.format(date);
		model.addAttribute("gtTime", gtTime);
		
		this.initialize(request, null);
		return new ModelAndView("zxgl/xydk-input", "xydkzx", new Xydkzx());
	}

	@RequestMapping(value = "/savexydk", method = RequestMethod.POST)
	public void savexydk(@ModelAttribute("xydkzx") Xydkzx xydkzx,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		// 信用贷款保存
		Date gtTime = new Date();
		SimpleDateFormat gt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String gtTimeString = gt.format(gtTime);
		if(xydkzx.getType() == null){
			xydkzx.setType("0");
		}
		xydkzx.setGtTime(gtTimeString);
		Employee emp = baseInfoManager.getUserEmployee();
		xydkzx.setOrgani(emp.getOrgani());
		dksqManager.saveXydkzx(xydkzx);
		if (xydkzx.getLastModifyBy() == null) {
			Date currentTime = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String dateString = formatter.format(currentTime);
			ZxGtjl zxgtjl = new ZxGtjl();
			zxgtjl.setXydkzx(dksqManager.getXydkzx(xydkzx.getId()));
			zxgtjl.setGtjl(xydkzx.getGtjl());
			zxgtjl.setBcgtrq(dateString);
			zxGtglManager.saveZxGtjl(zxgtjl);
		}
		DwzResult success = new DwzResult("200", "保存成功", "rel_xydkzx", "",
				"closeCurrent", "");
		ServletUtils.renderJson(response, success);
	}

	@RequestMapping(value = "/editxydkzx/{Id}", method = RequestMethod.GET)
	public ModelAndView editConsult(@PathVariable Long Id, HttpServletRequest request, Model model) {
		// 信用贷款修改
		Xydkzx xydkzx = dksqManager.getXydkzx(Id);
		Employee employee = baseInfoManager.getUserEmployee();
		model.addAttribute("employee", employee);
		this.initialize(request, xydkzx);
		return new ModelAndView("zxgl/xydk-input", "xydkzx", xydkzx);
	}
	
	/**
	 * 初始化循环贷信息参数 MDY 2013-7-24
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/loopConsulting ", method = RequestMethod.GET)
	public ModelAndView loopConsulting(HttpServletRequest request, Model model) {
		model.addAttribute("employee", baseInfoManager.getUserEmployee());
		this.initialize(request, null);
		return new ModelAndView("zxgl/loopConsulting-input", "xydkzx", new Xydkzx());
	}
	
	/**
	 * ajax核实循环贷咨询证件信息 MDY 2013-7-24
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/isIdInfo")
	@ResponseBody
	public Map<String, Object> isIdInfo(HttpServletRequest request){
		Map<String, Object> modelMap = baseInfoManager.isIdInfo(request);
		return modelMap;
	}
	
	/**
	 * ajax核实证件信息 MDY 2013-7-24
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/isIdInfoByZjhm")
	@ResponseBody
	public Map<String, Object> isIdInfoByZjhm(HttpServletRequest request){
		Map<String, Object> modelMap = baseInfoManager.isIdInfoByZjhm(request);
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
		map.put("zjhm", zjhm);
		List<Map<String, Object>> listJksq = xhjksqManager.searchXhJksq("queryJksqList", map, page);
		model.addAttribute("result",listJksq);
		return "jksq/listIdInfo";
	}
	
	/**
	 * 保存循环贷信息，包括咨询信息，借款申请信息，借款申请紧急联系人等信息拷贝 MDY 2013-7-24
	 * @param xydkzx
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/saveLoopConsulting", method = RequestMethod.POST)
	public void saveLoopConsulting(@ModelAttribute("xydkzx") Xydkzx xydkzx,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		String jksqId = request.getParameter("jksqId").toString();//咨询信息ID
		String[] res = xhjksqManager.saveLoopConsulting(new Long(jksqId), xydkzx);
		
		DwzResult success = new DwzResult(res[0], res[1], "rel_listLoopConsulting", "", "closeCurrent", "");
		ServletUtils.renderJson(response, success);
	}

	/**
	 * 循环贷信息列表 根据咨询类型0：新客户1：循环贷（老客户）此处加载循环贷信息列表 MDY 2013-7-24
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/listLoopConsulting")
	public String listLoopConsulting(HttpServletRequest request, Model model) {
		// 处理分页的参数
		// 得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		
		JdbcPage page = JdbcPageUtils.generatePage(request);
		Map<String, Object> params = ServletUtils.getParametersStartingWith2(request, "filter_");
		Employee emp = baseInfoManager.getUserEmployee();
		String loginName = operator.getCtiCode();
		params.put("loginName", loginName);
		params.put("emp", emp);
		params.put("type", "1");
		List<Map<String, Object>> listKhzx = dksqManager.searchKhzx("queryXhKhzxList", params, page);
		model.addAttribute("organiId", request.getParameter("filter_organi.id"));
	    model.addAttribute("organiName", request.getParameter("filter_organi.name"));
		model.addAttribute("listKhzx", listKhzx);
		model.addAttribute("page", page);
		return "zxgl/listLoopxydkzx";
	}
	@Autowired
	public void setXhjksqManager(XhJksqManager xhjksqManager) {
		this.xhjksqManager = xhjksqManager;
	}

}

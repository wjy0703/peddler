package cn.com.cucsi.app.web.xhcf;

import java.io.IOException;
import java.net.URLDecoder;
import java.sql.Timestamp;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import cn.com.cucsi.app.entity.baseinfo.City;
import cn.com.cucsi.app.entity.baseinfo.Employee;
import cn.com.cucsi.app.entity.baseinfo.MateData;
import cn.com.cucsi.app.entity.baseinfo.Tzcp;
import cn.com.cucsi.app.entity.xhcf.UpdateCjrxxHistory;
import cn.com.cucsi.app.entity.xhcf.XhCjrgtjl;
import cn.com.cucsi.app.entity.xhcf.XhTzsq;
import cn.com.cucsi.app.entity.xhcf.XhUploadFiles;
import cn.com.cucsi.app.entity.xhcf.XhcfCjrxx;
import cn.com.cucsi.app.service.baseinfo.BaseInfoManager;
import cn.com.cucsi.app.service.security.OperatorDetails;
import cn.com.cucsi.app.service.xhcf.CjrxxManager;
import cn.com.cucsi.app.service.xhcf.XhCjrgtjlManager;
import cn.com.cucsi.app.service.xhcf.XhTzsqManager;
import cn.com.cucsi.app.web.util.CreditHarmonyComputeUtilties;
import cn.com.cucsi.app.web.util.HibernateAwareBeanUtilsBean;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils;
import cn.com.cucsi.core.utils.PropertiesUtils;
import cn.com.cucsi.core.web.DwzResult;
import cn.com.cucsi.core.web.ServletUtils;
/**
 * 出借人基础信息管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/cjrxx")
public class CjrxxController {
	private CjrxxManager cjrxxManager;
	private BaseInfoManager baseInfoManager;
	private XhCjrgtjlManager xhCjrgtjlManager;
	private XhTzsqManager xhTzsqManager;
	@Autowired
	public void setXhTzsqManager(XhTzsqManager xhTzsqManager) {
		this.xhTzsqManager = xhTzsqManager;
	}
	@Autowired
	public void setXhCjrgtjlManager(XhCjrgtjlManager xhCjrgtjlManager) {
		this.xhCjrgtjlManager = xhCjrgtjlManager;
	}
	@Autowired
	public void setCjrxxManager(CjrxxManager cjrxxManager) {
		this.cjrxxManager = cjrxxManager;
	}
	@Autowired
	public void setBaseInfoManager(BaseInfoManager baseInfoManager) {
		this.baseInfoManager = baseInfoManager;
	}
	
	//--------------------------------------省市区级联       开始-----------------------------------
	@RequestMapping(value="/getCity")
	public String getCity(HttpServletRequest request, Model model,HttpServletResponse response) throws Exception{
		// 处理分页的参数
		String code = request.getParameter("code");
		List<City> city = new ArrayList<City>();
		if(StringUtils.isNotEmpty(code)){
			city = baseInfoManager.getSuggestCity(code);
		}
		//model.addAttribute("city", city);
		String meg = "[[\"\",\"所有城市\"]";
		for(City c : city){
			meg += ",[\""+c.getId()+"\",\""+c.getName()+"\"]";
		}
		meg += "]";
		response.getWriter().println(meg);
		return null;
	}
	
	@RequestMapping(value="/getArea")
	public String getArea(HttpServletRequest request, Model model,HttpServletResponse response) throws Exception{
		String code = request.getParameter("code");
		List<City> city = new ArrayList<City>();
		if(StringUtils.isNotEmpty(code)){
			city = baseInfoManager.getSuggestCity(code);
		}
		String meg = "[[\"\",\"所有区县\"]";
		for(City c : city){
			meg += ",[\""+c.getId()+"\",\""+c.getName()+"\"]";
		}
		meg += "]";
		response.getWriter().println(meg);
		return null;
	}
	//--------------------------------------省市区级联       结束-----------------------------------
	
	//--------------------------------------出借人基本信息       开始-----------------------------------
	/**
	 * 出借人基本信息-查询
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/listcjrxx")
	public String list(HttpServletRequest request, Model model){
		//得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
//				String loginName = operator.getUsername();
//				String loginName = operator.getCtiCode();
		if(operator==null)
		{
			return "redirect:../login";
		}
		// 处理分页的参数
		JdbcPage page = new JdbcPage();
		String pageSize = request.getParameter("numPerPage");
		if (StringUtils.isNotBlank(pageSize)){
			page.setPageSize(Integer.valueOf(pageSize));
		}
		String pageNo = request.getParameter("pageNum");
		if(StringUtils.isNotBlank(pageNo)){
			page.setPageNo(Integer.valueOf(pageNo));
		}
		String orderBy = request.getParameter("orderField");
		if(StringUtils.isNotBlank(orderBy)){
			page.setOrderBy(orderBy);
		}
		String order = request.getParameter("orderDirection");
		if(StringUtils.isNotBlank(order)){
			page.setOrder(order);
		}
		
//		Map<String, Object> params = ServletUtils.getParametersStartingWith2(request, "filter_");	
//		params.put("cjrState", "1");
//		cjrxxManager.searchCjrxx(page, params);
	
		Map<String, Object> map = WebUtils.getParametersStartingWith(request, "filter_");
//		Map<String, Object> map = ServletUtils.getParametersStartingWith2(request, "filter_");	
		//过滤查询内容，所需条件   ----开始
		Employee emp = baseInfoManager.getUserEmployee();
		String loginName = operator.getCtiCode();
		map.put("loginName", loginName);
		map.put("emp", emp);
		//过滤查询内容，所需条件   ----结束
		map.put("cjrState", "1");
		List<Map<String,Object>> listCjrxx = cjrxxManager.searchCjrxx("queryCjrxxList", map,page);
		List<Map<String,Object>> listYyb = cjrxxManager.searchYyb("queryYybList", map);
		model.addAttribute("listYyb", listYyb);
		model.addAttribute("listCjrxx", listCjrxx);
		model.addAttribute("map", map);
		
		model.addAttribute("page", page);
		List<City> province = baseInfoManager.getSuggestCity("0");
		request.setAttribute("province", province);
		return "cjrxx/cjrxxIndex";
		
	}
	/**
	 * 出借人基本信息-保存
	 * @param cjrxx
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/savecjrxx",method=RequestMethod.POST)
	public String save(@ModelAttribute("cjrxx") XhcfCjrxx cjrxx, HttpServletRequest request, HttpServletResponse response){
		//得到当前登录用户                                                                                             
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
//						String loginName = operator.getUsername();
//						String loginName = operator.getCtiCode();
		if(operator==null)
		{
			return "redirect:../login";
		}
		if (cjrxx.getEmployeeCrm() != null && cjrxx.getEmployeeCrm().getId()!=null){
			cjrxx.setEmployeeCrm(baseInfoManager.getEmployee(cjrxx.getEmployeeCrm().getId()));
		}
		else{
			cjrxx.setEmployeeCrm(null);
		}
		if (cjrxx.getEmployeeCca() != null && cjrxx.getEmployeeCca().getId()!=null){
			cjrxx.setEmployeeCca(baseInfoManager.getEmployee(cjrxx.getEmployeeCca().getId()));
		}
		else{
			cjrxx.setEmployeeCca(null);
		}
		Employee emp = baseInfoManager.getUserEmployee();
		cjrxx.setOrgani(emp.getOrgani());
		cjrxx.setCjrState("1");
		cjrxx.setZtFlag("0");
		cjrxx.setUpstate("-1");
		
		cjrxx.setDzyx(request.getParameter("dzyx"));//电子邮箱
		cjrxx.setKftd(request.getParameter("employeeCrm.deptname"));
//		cjrxx.setKhbm(baseInfoManager.getSequenceFro("CJRXX_KHBM_SEQUENCE"));
		cjrxxManager.saveCjrxx(cjrxx);

		DwzResult success = new DwzResult("200","保存成功","rel_listcjrxx","","closeCurrent","");
		ServletUtils.renderJson(response, success);
		
		return null;
	}
	
	/**
	 * 出借人基本信息-保存
	 * @param cjrxx
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/savecjrxxadd",method=RequestMethod.POST)
	public String savecjrxxadd(@ModelAttribute("cjrxx") XhcfCjrxx cjrxx, HttpServletRequest request, HttpServletResponse response){
		//得到当前登录用户                                                                                             
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
//						String loginName = operator.getUsername();
//						String loginName = operator.getCtiCode();
		if(operator==null)
		{
			return "redirect:../login";
		}
		if (cjrxx.getEmployeeCrm() != null && cjrxx.getEmployeeCrm().getId()!=null){
			cjrxx.setEmployeeCrm(baseInfoManager.getEmployee(cjrxx.getEmployeeCrm().getId()));
		}
		else{
			cjrxx.setEmployeeCrm(null);
		}
		if (cjrxx.getEmployeeCca() != null && cjrxx.getEmployeeCca().getId()!=null){
			cjrxx.setEmployeeCca(baseInfoManager.getEmployee(cjrxx.getEmployeeCca().getId()));
		}
		else{
			cjrxx.setEmployeeCca(null);
		}
		
		cjrxx.setDzyx(request.getParameter("dzyx"));//电子邮箱
		cjrxx.setKftd(request.getParameter("employeeCrm.deptname"));
//		cjrxx.setKhbm(baseInfoManager.getSequenceFro("CJRXX_KHBM_SEQUENCE"));
		HibernateAwareBeanUtilsBean beanCopy = new HibernateAwareBeanUtilsBean();
		XhcfCjrxx cjrxxOld = cjrxxManager.getCjrxx(cjrxx.getId());
    	try {
    		beanCopy.copyProperties(cjrxxOld, cjrxx);
    		cjrxxManager.saveCjrxx(cjrxxOld);
		} catch (Exception e) {
		    System.out.println("bean拷贝发生错误");
		}

		DwzResult success = new DwzResult("200","保存成功","rel_listcjrxx","","closeCurrent","");
		ServletUtils.renderJson(response, success);
		
		return null;
	}
	/**
	 * 出借人基本信息-进入新增页
	 * @param cjrxx
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addcjrxx", method=RequestMethod.GET)
	public ModelAndView add(HttpServletRequest request){
		//得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
	//						String loginName = operator.getUsername();
	//						String loginName = operator.getCtiCode();
		if(operator==null)
		{
			return new ModelAndView("redirect:../login");
		}
		Employee employee = baseInfoManager.getUserEmployee();
		request.setAttribute("employee", employee);
		this.initialize(request);
		return new ModelAndView("cjrxx/cjrxxInput", "cjrxx", new XhcfCjrxx());
	}
	
	/**
	 * 出借人基本信息-进入编辑页
	 * @param cjrxx
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editcjrxx/{Id}", method=RequestMethod.GET)
	public ModelAndView edit(@PathVariable Long Id,HttpServletRequest request){
		//得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
//						String loginName = operator.getUsername();
//						String loginName = operator.getCtiCode();
		if(operator==null)
		{
			return new ModelAndView("redirect:../login");
		}
		XhcfCjrxx cjrxx = cjrxxManager.getCjrxx(Id);
		Employee employee = baseInfoManager.getUserEmployee();
		request.setAttribute("employee", employee);
		this.initialize(request);
		return new ModelAndView("cjrxx/cjrxxInput", "cjrxx", cjrxx);
	}
	
	/**
	 * 出借人基本信息-提交待审批
	 * @param cjrxx
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/subCjrxx/{Id}")
	public String subCjrxx(@PathVariable Long Id, HttpServletResponse response){
		//得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
//						String loginName = operator.getUsername();
//						String loginName = operator.getCtiCode();
		if(operator==null)
		{
			return "redirect:../login";
		}
		XhcfCjrxx cjrxx = cjrxxManager.getCjrxx(Id);
		cjrxx.setState("1");
		cjrxxManager.saveCjrxx(cjrxx);
		DwzResult success = new DwzResult("200","提交成功","rel_listcjrxx","","","");
		ServletUtils.renderJson(response, success);
		return null;
	}
	/**
	 * 出借人基本信息-删除
	 * @param cjrxx
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/delcjrxx/{Id}")
	public String delCjrxx(@PathVariable Long Id, HttpServletResponse response){
		//得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
//						String loginName = operator.getUsername();
//						String loginName = operator.getCtiCode();
		if(operator==null)
		{
			return "redirect:../login";
		}
		cjrxxManager.deleteCjrxx(Id);
		DwzResult success = new DwzResult("200","删除成功","rel_listcjrxx","","","");
		ServletUtils.renderJson(response, success);
		return null;
	}
	/**
	 * 出借人基本信息-批量删除
	 * @param cjrxx
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/batchDelCjrxx")
	public String batchDelCjrxx(HttpServletRequest request, HttpServletResponse response){
		//得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
//						String loginName = operator.getUsername();
//						String loginName = operator.getCtiCode();
		if(operator==null)
		{
			return "redirect:../login";
		}
		String ids = request.getParameter("ids");
		String[] Ids = ids.split(",");
		boolean isSuccess = cjrxxManager.batchDelcjrxx(Ids);
		
		DwzResult success = null;
		if (isSuccess){
			success = new DwzResult("200","删除成功","rel_listcjrxx","","","");
		}
		else{
			success = new DwzResult("300","删除失败","rel_listcjrxx","","","");
		}
		ServletUtils.renderJson(response, success);
		return null;
	}
	/**
	 * 开户申请
	 * @param Id
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/openAccCjrxx/{Id}", method=RequestMethod.GET)
	public ModelAndView openAcccjrxx(@PathVariable Long Id,HttpServletRequest request){
		//得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
//						String loginName = operator.getUsername();
//						String loginName = operator.getCtiCode();
		if(operator==null)
		{
			return new ModelAndView("redirect:../login");
		}
		String loginName = operator.getCtiCode();
		request.setAttribute("loginName", loginName);
		XhcfCjrxx cjrxx = cjrxxManager.getCjrxx(Id);
		
		this.initialize(request);
		return new ModelAndView("cjrxx/openAccCjrxx", "cjrxx", cjrxx);
	}
	
	
	/**
	 * 开户申请-保存
	 * @param Id
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/saveOpenAccCjrxx",method=RequestMethod.POST)
	public String saveOpenAccCjrxx(@ModelAttribute("cjrxx") XhcfCjrxx cjrxx, HttpServletRequest request, HttpServletResponse response){
		//得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
//							String loginName = operator.getUsername();
//							String loginName = operator.getCtiCode();
		if(operator==null)
		{
			return "redirect:../login";
		}
		cjrxx.setDzyx(cjrxx.getDzyx());
		String isGouTong = request.getParameter("isGouTong");
		if(isGouTong!= null && isGouTong.equals("0")){
			cjrxx.setHszjkhh(cjrxx.getTzfkkhh());
			cjrxx.setHszjkhz(cjrxx.getTzfkkhz());
			cjrxx.setHszjyhmc(cjrxx.getTzfkyhmc());
			cjrxx.setHszjkhmc(cjrxx.getTzfkkhmc());
			cjrxx.setHszjyhzh(cjrxx.getTzfkyhzh());
		}
		XhcfCjrxx cjrxxOld = cjrxxManager.getCjrxx(cjrxx.getId());
		cjrxxOld.setOpenAccCjrxx(cjrxx);
		cjrxxOld.setState(cjrxx.getState());
		cjrxxManager.saveCjrxx(cjrxxOld);

		DwzResult success = new DwzResult("200","保存成功","rel_listcjrxx","","closeCurrent","");
		ServletUtils.renderJson(response, success);
		
		return null;
	}
	/**
	 * 首期债权
	 * @param Id
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/firstZqCjrxx/{Id}", method=RequestMethod.GET)
	public ModelAndView firstZqjrxx(@PathVariable Long Id,HttpServletRequest request){
		//得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
//						String loginName = operator.getUsername();
//						String loginName = operator.getCtiCode();
		if(operator==null)
		{
			return new ModelAndView("redirect:../login");
		}
		XhcfCjrxx cjrxx = cjrxxManager.getCjrxx(Id);
		List<City> province = baseInfoManager.getSuggestCity("0");
		request.setAttribute("province", province);
		List<MateData> tzcp = baseInfoManager.getTypeByCode("0010");
		request.setAttribute("tzcp", tzcp);
		List<MateData> zjlx = baseInfoManager.getTypeByCode("0005");
		request.setAttribute("zjlx", zjlx);
		return new ModelAndView("cjrxx/firstZqCjrxx", "cjrxx", cjrxx);
	}
	
	/**
	 * 投资申请
	 * @param Id
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/tzsqCjrxx/{Id}", method=RequestMethod.GET)
	public ModelAndView tzsqjrxx(@PathVariable Long Id,HttpServletRequest request){
		//得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
//						String loginName = operator.getUsername();
//						String loginName = operator.getCtiCode();
		if(operator==null)
		{
			return new ModelAndView("redirect:../login");
		}
		XhcfCjrxx cjrxx = cjrxxManager.getCjrxx(Id);
		
		this.initialize(request);
		return new ModelAndView("cjrxx/tzsqCjrxx", "cjrxx", cjrxx);
	}
	
	/**
	 * 投资申请-保存
	 * @param Id
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/saveTzsqCjrxx", method=RequestMethod.POST)
	public String saveTzsqCjrxx(@ModelAttribute("xhTzsq") XhTzsq xhTzsq,HttpServletRequest request, HttpServletResponse response){
		//得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
//						String loginName = operator.getUsername();
//						String loginName = operator.getCtiCode();
		if(operator==null)
		{
			return "redirect:../login";
		}
		if (xhTzsq.getCjrxx() != null && xhTzsq.getCjrxx().getId()!=null){
			xhTzsq.setCjrxx(cjrxxManager.getCjrxx(xhTzsq.getCjrxx().getId()));
			xhTzsq.setEmployeeCca(xhTzsq.getCjrxx().getEmployeeCca());
			xhTzsq.setEmployeeCrm(xhTzsq.getCjrxx().getEmployeeCrm());
		}
		else{
			xhTzsq.setCjrxx(null);
		}
		if (xhTzsq.getTzcp() != null && xhTzsq.getTzcp().getId()!=null){
			Tzcp cp = baseInfoManager.getCp(xhTzsq.getTzcp().getId());
			xhTzsq.setTzcp(cp);
			xhTzsq.setTzfs(cp.getTzcpMc());
			xhTzsq.setHsfs(cp.getTzcpMc());
			xhTzsq.setLastCjzq(cp.getTzcpZq());
			xhTzsq.setCjzq(cp.getTzcpZq());
		}
		else{
			xhTzsq.setTzcp(null);
		}
		Employee emp = baseInfoManager.getUserEmployee();
		xhTzsq.setOrgani(emp.getOrgani());
//		xhTzsq.setState("0");
		xhTzsq.setUpstate("-1");
		xhTzsq.setOverstate("0");
//		String sqtype = request.getParameter("sqfs");
//		xhTzsq.setSqtype(sqtype);
		xhTzsq.setXszklyxqx(CreditHarmonyComputeUtilties.getXszklyxqx(xhTzsq.getJhtzrq(),
											xhTzsq.getTzcp().getId()));
		xhTzsq.setFirstdate(CreditHarmonyComputeUtilties.getFirstdate(xhTzsq.getJhtzrq()));
		xhTzsqManager.saveXhTzsq(xhTzsq);
		DwzResult success = new DwzResult("200","保存成功","rel_listcjrxx","","closeCurrent","");
		ServletUtils.renderJson(response, success);
		return null;
	}
	
	/**
	 * 出借人基本信息-进入编辑页
	 * @param cjrxx
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/cjrxxComplement", method=RequestMethod.GET)
	public ModelAndView cjrxxComplement(HttpServletRequest request){
		//得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
//						String loginName = operator.getUsername();
//						String loginName = operator.getCtiCode();
		if(operator==null)
		{
			return new ModelAndView("redirect:../login");
		}
		String id = request.getParameter("cjrxx_id");
		XhcfCjrxx cjrxx = cjrxxManager.getCjrxx(Long.parseLong(id));
		Employee employee = baseInfoManager.getUserEmployee();
		request.setAttribute("employee", employee);
		this.initialize(request);
		return new ModelAndView("cjrxx/cjrxxComplement", "cjrxx", cjrxx);
	}
	//--------------------------------------出借人基本信息       结束-----------------------------------
	
	//--------------------------------------投资咨询       开始-----------------------------------
	/**
	 * 投资咨询 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/listTzZxCjr")
	public String listTzZxCjr(HttpServletRequest request, Model model){
		//得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
//						String loginName = operator.getUsername();
//						String loginName = operator.getCtiCode();
		if(operator==null)
		{
			return "redirect:../login";
		}
		// 处理分页的参数
		JdbcPage page = new JdbcPage();
		String pageSize = request.getParameter("numPerPage");
		if (StringUtils.isNotBlank(pageSize)){
			page.setPageSize(Integer.valueOf(pageSize));
		}
		String pageNo = request.getParameter("pageNum");
		if(StringUtils.isNotBlank(pageNo)){
			page.setPageNo(Integer.valueOf(pageNo));
		}
		String orderBy = request.getParameter("orderField");
		if(StringUtils.isNotBlank(orderBy)){
			page.setOrderBy(orderBy);
		}
		String order = request.getParameter("orderDirection");
		if(StringUtils.isNotBlank(order)){
			page.setOrder(order);
		}
		
//		Map<String, Object> params = ServletUtils.getParametersStartingWith2(request, "filter_");	
		//取出所有页面以SEARCH_为前缀的属性，做为查询条件
//		params.put("cjrState", "0");
//		cjrxxManager.searchCjrxx(page, params);
		Map<String, Object> map = WebUtils.getParametersStartingWith(request, "filter_");
		//过滤查询内容，所需条件   ----开始
		Employee emp = baseInfoManager.getUserEmployee();
		String loginName = operator.getCtiCode();
//		System.out.println("loginName==>" + loginName);
		map.put("loginName", loginName);
		map.put("emp", emp);
		//过滤查询内容，所需条件   ----结束
		//map.put("cjrState", "0");
		List<Map<String,Object>> listCjrxx = cjrxxManager.searchCjrxx("queryCjrxxList", map,page);
		
		model.addAttribute("listCjrxx", listCjrxx);
		model.addAttribute("map", map);
		model.addAttribute("page", page);
		return "cjrxx/tzZxIndex";
		
	}
	
	/**
	 * 投资咨询-保存
	 * @param cjrxx
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/saveTzzx",method=RequestMethod.POST)
	public String saveTzzx(@ModelAttribute("cjrxx") XhcfCjrxx cjrxx,@ModelAttribute("xhCjrgtjl") XhCjrgtjl xhCjrgtjl, HttpServletRequest request, HttpServletResponse response){
		//得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
//						String loginName = operator.getUsername();
//						String loginName = operator.getCtiCode();
		if(operator==null)
		{
			return "redirect:../login";
		}
		if (cjrxx.getEmployeeCrm().getId()==null){
			cjrxx.setEmployeeCrm(null);
		}
		else{
			cjrxx.setEmployeeCrm(baseInfoManager.getEmployee(cjrxx.getEmployeeCrm().getId()));
		}
		Employee emp = baseInfoManager.getUserEmployee();
		cjrxx.setOrgani(emp.getOrgani());
		cjrxx.setCjrState("0");
		cjrxx.setZtFlag("0");
		cjrxx.setState("0");
		cjrxx.setKftd(request.getParameter("employeeCrm.deptname"));
		cjrxx.setWjbh(request.getParameter("wjbh"));
		cjrxxManager.saveCjrxx(cjrxx);
		String isGouTong = request.getParameter("isGouTong");
		if(isGouTong!= null && isGouTong.equals("1")){
			xhCjrgtjl.setCjrState("0");
			xhCjrgtjl.setCjrxx(cjrxx);
			xhCjrgtjlManager.saveXhCjrgtjl(xhCjrgtjl);
		}
		DwzResult success = new DwzResult("200","保存成功","rel_listTzZxCjr","","closeCurrent","");
		ServletUtils.renderJson(response, success);
		
		return null;
	}
	/**
	 * 投资咨询-进入新增页
	 * @param cjrxx
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/addTzzx", method=RequestMethod.GET)
	public ModelAndView addTzzx(HttpServletRequest request){
		//得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
//						String loginName = operator.getUsername();
//						String loginName = operator.getCtiCode();
		if(operator==null)
		{
			return new ModelAndView("redirect:../login");
		}
		Employee employee = baseInfoManager.getUserEmployee();
		request.setAttribute("employee", employee);
		this.initialize(request);
		return new ModelAndView("cjrxx/tzzxInput", "cjrxx", new XhcfCjrxx());
	}
	
	/**
	 * 投资咨询-进入编辑页
	 * @param cjrxx
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/editTzzx/{Id}", method=RequestMethod.GET)
	public ModelAndView editTzzx(@PathVariable Long Id,HttpServletRequest request){
		//得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
//						String loginName = operator.getUsername();
//						String loginName = operator.getCtiCode();
		if(operator==null)
		{
			return new ModelAndView("redirect:../login");
		}
		XhcfCjrxx cjrxx = cjrxxManager.getCjrxx(Id);
		this.initialize(request);
		return new ModelAndView("cjrxx/tzzxInput", "cjrxx", cjrxx);
	}
	/**
	 * 投资咨询-删除
	 * @param cjrxx
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/delTzzx/{Id}")
	public String delTzzx(@PathVariable Long Id, HttpServletResponse response){
		//得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
//						String loginName = operator.getUsername();
//						String loginName = operator.getCtiCode();
		if(operator==null)
		{
			return "redirect:../login";
		}
		cjrxxManager.deleteCjrxx(Id);
		DwzResult success = new DwzResult("200","删除成功","rel_listTzZxCjr","","","");
		ServletUtils.renderJson(response, success);
		return null;
	}
	
	/**
	 * 投资咨询-转正
	 * @param cjrxx
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/toCjrTzzx/{Id}")
	public String toCjrTzzx(@PathVariable Long Id, HttpServletResponse response){
		//得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
//						String loginName = operator.getUsername();
//						String loginName = operator.getCtiCode();
		if(operator==null)
		{
			return "redirect:../login";
		}
		XhcfCjrxx cjrxx = cjrxxManager.getCjrxx(Id);
//		cjrxx.setKhbm(baseInfoManager.getSequenceFro("CJRXX_KHBM_SEQUENCE"));
		cjrxx.setCjrState("1");
		cjrxxManager.saveCjrxx(cjrxx);
		DwzResult success = new DwzResult("200","转正成功","rel_listTzZxCjr","","","");
		ServletUtils.renderJson(response, success);
		return null;
	}
	/**
	 * 投资咨询-批量删除
	 * @param cjrxx
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/batchDelTzzx")
	public String batchDelTzzx(HttpServletRequest request, HttpServletResponse response){
		//得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
//						String loginName = operator.getUsername();
//						String loginName = operator.getCtiCode();
		if(operator==null)
		{
			return "redirect:../login";
		}
		String ids = request.getParameter("ids");
		String[] Ids = ids.split(",");
		boolean isSuccess = cjrxxManager.batchDelcjrxx(Ids);
		
		DwzResult success = null;
		if (isSuccess){
			success = new DwzResult("200","删除成功","rel_listTzZxCjr","","","");
		}
		else{
			success = new DwzResult("300","删除失败","rel_listTzZxCjr","","","");
		}
		ServletUtils.renderJson(response, success);
		return null;
	}
	//--------------------------------------投资咨询       结束-----------------------------------
	


	//--------------------------------------开户信息审批       开始-----------------------------------
	/**
	 * 开户信息审批 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/listAccAuditCjr")
	public String listAccAuditCjr(HttpServletRequest request, Model model){
		//得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
//						String loginName = operator.getUsername();
//						String loginName = operator.getCtiCode();
		if(operator==null)
		{
			return "redirect:../login";
		}
		// 处理分页的参数
		JdbcPage page = new JdbcPage();
		String pageSize = request.getParameter("numPerPage");
		if (StringUtils.isNotBlank(pageSize)){
			page.setPageSize(Integer.valueOf(pageSize));
		}
		String pageNo = request.getParameter("pageNum");
		if(StringUtils.isNotBlank(pageNo)){
			page.setPageNo(Integer.valueOf(pageNo));
		}
		String orderBy = request.getParameter("orderField");
		if(StringUtils.isNotBlank(orderBy)){
			page.setOrderBy(orderBy);
		}
		String order = request.getParameter("orderDirection");
		if(StringUtils.isNotBlank(order)){
			page.setOrder(order);
		}
//		Map<String, Object> params = ServletUtils.getParametersStartingWith2(request, "filter_");		
//		params.put("cjrState", "1");
//		cjrxxManager.searchCjrxx(page, params);
		Map<String, Object> map = WebUtils.getParametersStartingWith(request, "filter_");
		//过滤查询内容，所需条件   ----开始
		Employee emp = baseInfoManager.getUserEmployee();
		String loginName = operator.getCtiCode();
		map.put("loginName", loginName);
		map.put("emp", emp);
		//过滤查询内容，所需条件   ----结束
		map.put("cjrState", "1");
		//状态0暂存,1待审批，2审批通过，3审批不通过，9删除， 
		if(map.containsKey("state")){
			String value = String.valueOf(map.get("state"));
			if(StringUtils.isEmpty(value)) {
				map.put("state", "1");
			}
		}else{
			map.put("state", "1");
		}
		List<Map<String,Object>> listCjrxx = cjrxxManager.searchCjrxx("queryCjrxxList", map,page);
		
		model.addAttribute("listCjrxx", listCjrxx);
		model.addAttribute("map", map);
		model.addAttribute("page", page);
		List<City> province = baseInfoManager.getSuggestCity("0");
		request.setAttribute("province", province);
		return "cjrxx/accAuditIndex";
		
	}
	
	/**
	 * 开户信息审批 -进入编辑页
	 * @param cjrxx
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/accAuditCjrxx/{Id}", method=RequestMethod.GET)
	public ModelAndView accAuditCjrxx(@PathVariable Long Id,HttpServletRequest request){
		//得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
//						String loginName = operator.getUsername();
//						String loginName = operator.getCtiCode();
		if(operator==null)
		{
			return new ModelAndView("redirect:../login");
		}
		XhcfCjrxx cjrxx = cjrxxManager.getCjrxx(Id);
		this.initialize(request);
		return new ModelAndView("cjrxx/accAuditInput", "cjrxx", cjrxx);
	}
	
	/**
	 * 信息审批 -保存
	 * @param cjrxx
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/saveAccAudit",method=RequestMethod.POST)
	public String saveAccAudit(@ModelAttribute("cjrxx") XhcfCjrxx cjrxx, HttpServletRequest request, HttpServletResponse response){
		//得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
//						String loginName = operator.getUsername();
//						String loginName = operator.getCtiCode();
		if(operator==null)
		{
			return "redirect:../login";
		}
		XhcfCjrxx cjrxxFind = cjrxxManager.getCjrxx(cjrxx.getId());
		Employee emp = baseInfoManager.getUserEmployee();
		cjrxxFind.setAuditPerson(emp.getName());
		cjrxxFind.setAuditIdea(cjrxx.getAuditIdea());
		cjrxxFind.setState(cjrxx.getState());
		cjrxxFind.setAuditTime(new Timestamp(new Date().getTime()));
//		String khbm = request.getParameter("bianma");
		//审批通过，备份一次历史信息
		if(cjrxx.getState().equals("2")){
			//客户编号
//			String khbm = "";
			//地区：0000（区号，前补0）
//			khbm += baseInfoManager.getAreaCode(Long.parseLong(cjrxxFind.getCity()));
			//营业部：00
//			khbm += cjrxxFind.getOrgani().getOrganiCode();
			//0000
//			khbm += baseInfoManager.getSequenceFro("CJRXX_KHBM_SEQUENCE");
//			cjrxxFind.setKhbm(khbm);
			//审批通过，备份一次历史信息
			UpdateCjrxxHistory uch = new UpdateCjrxxHistory(cjrxxFind);
			cjrxxManager.saveUpdateCjrxx(uch);
		}
		cjrxxManager.saveCjrxx(cjrxxFind);
		DwzResult success = new DwzResult("200","保存成功","rel_listAccAuditCjr","","closeCurrent","");
		ServletUtils.renderJson(response, success);
		
		return null;
	}
	//--------------------------------------开户信息审批       结束-----------------------------------
	
	
	//--------------------------------------出借人信息变更申请       开始-----------------------------------
	/**
	 * 出借人信息变更申请
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/listMessChangeCjr")
	public String listMessChangeCjr(HttpServletRequest request, Model model){
		//得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
//					String loginName = operator.getUsername();
//					String loginName = operator.getCtiCode();
		if(operator==null)
		{
			return "redirect:../login";
		}
		// 处理分页的参数
		JdbcPage page = new JdbcPage();
		String pageSize = request.getParameter("numPerPage");
		if (StringUtils.isNotBlank(pageSize)){
			page.setPageSize(Integer.valueOf(pageSize));
		}
		String pageNo = request.getParameter("pageNum");
		if(StringUtils.isNotBlank(pageNo)){
			page.setPageNo(Integer.valueOf(pageNo));
		}
		String orderBy = request.getParameter("orderField");
		if(StringUtils.isNotBlank(orderBy)){
			page.setOrderBy(orderBy);
		}
		String order = request.getParameter("orderDirection");
		if(StringUtils.isNotBlank(order)){
			page.setOrder(order);
		}
		
//		Map<String, Object> params = ServletUtils.getParametersStartingWith2(request, "filter_");		
//		params.put("cjrState", "1");
//		cjrxxManager.searchCjrxx(page, params);
		Map<String, Object> map = WebUtils.getParametersStartingWith(request, "filter_");
		//过滤查询内容，所需条件   ----开始
		Employee emp = baseInfoManager.getUserEmployee();
		String loginName = operator.getCtiCode();
		map.put("loginName", loginName);
		map.put("emp", emp);
		//过滤查询内容，所需条件   ----结束
		map.put("cjrState", "1");
		map.put("state", "2");
		List<Map<String,Object>> listCjrxx = cjrxxManager.searchCjrxx("queryCjrxxList", map,page);
		
		model.addAttribute("listCjrxx", listCjrxx);
		model.addAttribute("map", map);
		model.addAttribute("page", page);
		List<City> province = baseInfoManager.getSuggestCity("0");
		request.setAttribute("province", province);
		return "cjrxx/messChangeIndex";
		
	}
	
	/**
	 * 出借人信息变更-申请页
	 * @param cjrxx
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/messChangeCjrxx/{Id}", method=RequestMethod.GET)
	public ModelAndView messChangeCjrxx(@PathVariable Long Id,HttpServletRequest request){
		//得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
//					String loginName = operator.getUsername();
//					String loginName = operator.getCtiCode();
		if(operator==null)
		{
			return new ModelAndView("redirect:../login");
		}
		XhcfCjrxx cjrxx = cjrxxManager.getCjrxx(Id);
		UpdateCjrxxHistory uch;
		//如果主表的变更状态为-1，则新建并初始化变更实体
		if(!cjrxx.getUpstate().equals("-1")){
			uch = cjrxx.getHistory();
		}else{
			uch = new UpdateCjrxxHistory(cjrxx);
		}
		this.initializeUCH(request, uch);
		return new ModelAndView("cjrxx/messChangeInput", "cjrxx", uch);
	}
	
	/**
	 * 出借人信息变更-保存
	 * @param cjrxx
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/saveMessChange",method=RequestMethod.POST)
	public String saveMessChange(@ModelAttribute("cjrxx") UpdateCjrxxHistory cjrxx, HttpServletRequest request, HttpServletResponse response){
		//得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
//						String loginName = operator.getUsername();
//						String loginName = operator.getCtiCode();
		if(operator==null)
		{
			return "redirect:../login";
		}
		XhcfCjrxx cjrxxFind = cjrxxManager.getCjrxx(cjrxx.getCjrxx().getId());
		//保存变更信息
		UpdateCjrxxHistory uch;
		if(cjrxx.getId() != null && !cjrxx.getId().equals("")){
			uch = cjrxxManager.getUpdateCjrxx(cjrxx.getId());
		}else{
			uch = new UpdateCjrxxHistory(cjrxxFind);
		}
		//将变更实体信息替换表单信息
		uch.toUpdateCjrxxHistory(cjrxx);
		uch.setUpstate(cjrxx.getState());
		cjrxxManager.saveUpdateCjrxx(uch);
		//保存主表信息
		cjrxxFind.setHistory(uch);
		cjrxxFind.setUpstate(cjrxx.getState());
		cjrxxManager.saveCjrxx(cjrxxFind);

		DwzResult success = new DwzResult("200","保存成功","rel_listcjrxx","","closeCurrent","");
		
		ServletUtils.renderJson(response, success);
		
		return null;
	}
	
	/**
	 * 出借人信息变更-提交待审批
	 * @param cjrxx
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/subMessChange/{Id}")
	public String subMessChange(@PathVariable Long Id, HttpServletResponse response){
		//得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
//						String loginName = operator.getUsername();
//						String loginName = operator.getCtiCode();
		if(operator==null)
		{
			return "redirect:../login";
		}
		XhcfCjrxx cjrxx = cjrxxManager.getCjrxx(Id);
		//设置变更状态为待审批
		cjrxx.setUpstate("1");
		cjrxxManager.saveCjrxx(cjrxx);
		DwzResult success = new DwzResult("200","提交成功","rel_listcjrxx","","","");
		ServletUtils.renderJson(response, success);
		return null;
	}
	//--------------------------------------出借人信息变更申请       结束-----------------------------------
	
	//--------------------------------------出借人信息变更审批       开始-----------------------------------
	/**
	 * 出借人信息变更审批
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/listMessChangeAuditCjr")
	public String listMessChangeAuditCjr(HttpServletRequest request, Model model){
		//得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
//					String loginName = operator.getUsername();
//					String loginName = operator.getCtiCode();
		if(operator==null)
		{
			return "redirect:../login";
		}
		// 处理分页的参数
		JdbcPage page = new JdbcPage();
		String pageSize = request.getParameter("numPerPage");
		if (StringUtils.isNotBlank(pageSize)){
			page.setPageSize(Integer.valueOf(pageSize));
		}
		String pageNo = request.getParameter("pageNum");
		if(StringUtils.isNotBlank(pageNo)){
			page.setPageNo(Integer.valueOf(pageNo));
		}
		String orderBy = request.getParameter("orderField");
		if(StringUtils.isNotBlank(orderBy)){
			page.setOrderBy(orderBy);
		}
		String order = request.getParameter("orderDirection");
		if(StringUtils.isNotBlank(order)){
			page.setOrder(order);
		}
		
//		Map<String, Object> params = ServletUtils.getParametersStartingWith2(request, "filter_");		
//		params.put("cjrState", "1");
//		cjrxxManager.searchCjrxx(page, params);
		Map<String, Object> map = WebUtils.getParametersStartingWith(request, "filter_");
		//过滤查询内容，所需条件   ----开始
		Employee emp = baseInfoManager.getUserEmployee();
		String loginName = operator.getCtiCode();
		map.put("loginName", loginName);
		map.put("emp", emp);
		//过滤查询内容，所需条件   ----结束
		map.put("cjrState", "1");
		map.put("state", "2");
		map.put("upstate", "1");
		List<Map<String,Object>> listCjrxx = cjrxxManager.searchCjrxx("queryCjrxxList", map,page);
		
		model.addAttribute("listCjrxx", listCjrxx);
		model.addAttribute("map", map);
		model.addAttribute("page", page);
		List<City> province = baseInfoManager.getSuggestCity("0");
		request.setAttribute("province", province);
		return "cjrxx/messChangeAuditIndex";
		
	}
	
	/**
	 * 出借人信息变更审批-审批页
	 * @param cjrxx
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/auditMessChange/{Id}", method=RequestMethod.GET)
	public ModelAndView auditMessChange(@PathVariable Long Id,HttpServletRequest request){
		//得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
//					String loginName = operator.getUsername();
//					String loginName = operator.getCtiCode();
		if(operator==null)
		{
			return new ModelAndView("redirect:../login");
		}
		XhcfCjrxx cjrxx = cjrxxManager.getCjrxx(Id);
		UpdateCjrxxHistory uch = cjrxx.getHistory();
		if(StringUtils.isNotEmpty(cjrxx.getProvince())){
			List<City> city = baseInfoManager.getSuggestCity(cjrxx.getProvince());
			request.setAttribute("oldcity", city);
			if(StringUtils.isNotEmpty(cjrxx.getCity())){
				List<City> area = baseInfoManager.getSuggestCity(cjrxx.getCity());
				request.setAttribute("oldarea", area);
			}
		}
		
		this.initializeUCH(request, uch);
		return new ModelAndView("cjrxx/messChangeAuditInput", "cjrxx", uch);
	}
	
	/**
	 * 出借人信息变更审批-审批保存
	 * @param cjrxx
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/saveMessChangeAudit",method=RequestMethod.POST)
	public String saveMessChangeAudit(@ModelAttribute("cjrxx") UpdateCjrxxHistory cjrxx, HttpServletRequest request, HttpServletResponse response){
		//得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
//						String loginName = operator.getUsername();
//						String loginName = operator.getCtiCode();
		if(operator==null)
		{
			return "redirect:../login";
		}
		Employee emp = baseInfoManager.getUserEmployee();
		UpdateCjrxxHistory uch = cjrxxManager.getUpdateCjrxx(cjrxx.getId());
		uch.setUpstate(cjrxx.getUpstate());
		uch.setAuditIdea(cjrxx.getAuditIdea());
		uch.setAuditPerson(emp.getName());
		uch.setAuditTime(new Timestamp(new Date().getTime()));
		cjrxxManager.saveUpdateCjrxx(uch);
		if(cjrxx.getUpstate().equals("3")){
			//审批不通过，则只修改主表的变更状态
			XhcfCjrxx cjrxxFind = cjrxxManager.getCjrxx(cjrxx.getCjrxx().getId());
			cjrxxFind.setUpstate("3");
			cjrxxManager.saveCjrxx(cjrxxFind);
		}else{
			//审批通过，则将变更表信息复制给主表，同时主表变更状态设置-1
			XhcfCjrxx cjrxxFind = cjrxxManager.getCjrxx(cjrxx.getCjrxx().getId());
			cjrxxFind.setXhcfCjrxx(uch);
			cjrxxFind.setUpstate("-1");
			cjrxxManager.saveCjrxx(cjrxxFind);
		}

		DwzResult success = new DwzResult("200","保存成功","rel_listMessChangeAuditCjr","","closeCurrent","");
		ServletUtils.renderJson(response, success);
		
		return null;
	}
	//--------------------------------------出借人信息变更审批       结束-----------------------------------
	
	//--------------------------------------出借人信息变更历史       开始-----------------------------------
	/**
	 * 出借人信息变更历史
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/listMessChangeHisCjr")
	public String listMessChangeHisCjr(HttpServletRequest request, Model model){
		//得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
//					String loginName = operator.getUsername();
//					String loginName = operator.getCtiCode();
		if(operator==null)
		{
			return "redirect:../login";
		}
		// 处理分页的参数
		JdbcPage page = new JdbcPage();
		String pageSize = request.getParameter("numPerPage");
		if (StringUtils.isNotBlank(pageSize)){
			page.setPageSize(Integer.valueOf(pageSize));
		}
		String pageNo = request.getParameter("pageNum");
		if(StringUtils.isNotBlank(pageNo)){
			page.setPageNo(Integer.valueOf(pageNo));
		}
		String orderBy = request.getParameter("orderField");
		if(StringUtils.isNotBlank(orderBy)){
			page.setOrderBy(orderBy);
		}
		String order = request.getParameter("orderDirection");
		if(StringUtils.isNotBlank(order)){
			page.setOrder(order);
		}
		
//		Map<String, Object> params = ServletUtils.getParametersStartingWith2(request, "filter_");		
//		params.put("cjrState", "1");
//		cjrxxManager.searchCjrxx(page, params);
		Map<String, Object> map = WebUtils.getParametersStartingWith(request, "filter_");
		//过滤查询内容，所需条件   ----开始
		Employee emp = baseInfoManager.getUserEmployee();
		String loginName = operator.getCtiCode();
		map.put("loginName", loginName);
		map.put("emp", emp);
		//过滤查询内容，所需条件   ----结束
		map.put("cjrState", "1");
		map.put("state", "2");
		List<Map<String,Object>> listCjrxx = cjrxxManager.searchCjrxx("queryCjrxxList", map,page);
		
		model.addAttribute("listCjrxx", listCjrxx);
		model.addAttribute("map", map);
		model.addAttribute("page", page);
		List<City> province = baseInfoManager.getSuggestCity("0");
		request.setAttribute("province", province);
		return "cjrxx/messChangeHisIndex";
	}
	/**
	 * 出借人信息变更历史-查看页
	 * @param cjrxx
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/getCjrxxHis/{Id}", method=RequestMethod.GET)
	public ModelAndView getCjrxxHis(@PathVariable Long Id,HttpServletRequest request){
		//得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
//					String loginName = operator.getUsername();
//					String loginName = operator.getCtiCode();
		if(operator==null)
		{
			return new ModelAndView("redirect:../login");
		}
		XhcfCjrxx cjrxx = cjrxxManager.getCjrxx(Id);
		this.initialize(request);
		return new ModelAndView("cjrxx/messChangeHisInput", "cjrxx", cjrxx);
	}
	
	/**
	 * 出借人信息变更历史-详细页
	 * @param cjrxx
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/getCjrxxHisDetailed/{Id}", method=RequestMethod.GET)
	public ModelAndView getCjrxxHisDetailed(@PathVariable Long Id,HttpServletRequest request){
		//得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
//					String loginName = operator.getUsername();
//					String loginName = operator.getCtiCode();
		if(operator==null)
		{
			return new ModelAndView("redirect:../login");
		}
		UpdateCjrxxHistory uch = cjrxxManager.getUpdateCjrxx(Id);
		//System.out.println(uch.getCjrxx().getId());
		XhcfCjrxx cjrxx = cjrxxManager.getCjrxx(uch.getCjrxx().getId());
		this.initialize(request);
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("fileOwner", "XH_CJRXX");
		filters.put("mainId", Id);
		List<XhUploadFiles> files = baseInfoManager.findXhUploadFiles(filters);
		request.setAttribute("files", files);
		//判断显示电话号码条件，团队经理，非本人录入的不能查看
		String loginName = operator.getCtiCode();
		request.setAttribute("loginName", loginName);
		Employee emp = baseInfoManager.getUserEmployee();
		//职务编号
		String positionCode = emp.getPosition().getPositionCode();
		request.setAttribute("positionCode", positionCode);
		return new ModelAndView("cjrxx/lookOut", "cjrxx", cjrxx);
	}
	/**
	 * 出借人基本信息-查看页
	 * @param cjrxx
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/lookOutCjrxx/{Id}", method=RequestMethod.GET)
	public ModelAndView lookOutCjrxx(@PathVariable Long Id,HttpServletRequest request){
		//得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
//					String loginName = operator.getUsername();
//					String loginName = operator.getCtiCode();
		if(operator==null)
		{
			return new ModelAndView("redirect:../login");
		}
		
		XhcfCjrxx cjrxx = cjrxxManager.getCjrxx(Id);
		this.initialize(request);
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("fileOwner", "XH_CJRXX");
		filters.put("mainId", Id);
		List<XhUploadFiles> files = baseInfoManager.findXhUploadFiles(filters);
		request.setAttribute("files", files);
		//判断显示电话号码条件，团队经理，非本人录入的不能查看
		String loginName = operator.getCtiCode();
		request.setAttribute("loginName", loginName);
		Employee emp = baseInfoManager.getUserEmployee();
		//职务编号
		String positionCode = emp.getPosition().getPositionCode();
		request.setAttribute("positionCode", positionCode);
		
		return new ModelAndView("cjrxx/lookOut", "cjrxx", cjrxx);
	}
	//--------------------------------------出借人信息变更历史       结束-----------------------------------
	
	//--------------------------------------页面下拉菜单信息初始化       开始-----------------------------------
	/**
	 * 页面下拉菜单信息初始化
	 * @param request
	 * @param cjrxx
	 */
	public void initializeUCH(HttpServletRequest request,UpdateCjrxxHistory cjrxx){
		List<City> province = baseInfoManager.getSuggestCity("0");
		request.setAttribute("province", province);
		
		if(cjrxx != null){
			if(StringUtils.isNotEmpty(cjrxx.getProvince())){
				List<City> city = baseInfoManager.getSuggestCity(cjrxx.getProvince());
				request.setAttribute("city", city);
				if(StringUtils.isNotEmpty(cjrxx.getCity())){
					List<City> area = baseInfoManager.getSuggestCity(cjrxx.getCity());
					request.setAttribute("area", area);
				}
			}
			
			if(StringUtils.isNotEmpty(cjrxx.getCrmprovince())){
				List<City> crmcity = baseInfoManager.getSuggestCity(cjrxx.getCrmprovince());
				request.setAttribute("crmcity", crmcity);
			}
			
			if(StringUtils.isNotEmpty(cjrxx.getJjlxrprovince())){
				List<City> city = baseInfoManager.getSuggestCity(cjrxx.getJjlxrprovince());
				request.setAttribute("jjlxrcity", city);
				if(StringUtils.isNotEmpty(cjrxx.getJjlxrcity())){
					List<City> area = baseInfoManager.getSuggestCity(cjrxx.getJjlxrcity());
					request.setAttribute("jjlxrarea", area);
				}
			}
		}
//		List<MateData> tzcp = baseInfoManager.getTypeByCode("0010");
//		request.setAttribute("tzcp", tzcp);
		List<MateData> zjlx = baseInfoManager.getTypeByCode("0005");
		request.setAttribute("zjlx", zjlx);
		//婚姻状况
		List<MateData> hyzk = baseInfoManager.getTypeByCode("0009");
		request.setAttribute("hyzk", hyzk);
		//职业
		List<MateData> zy = baseInfoManager.getTypeByCode("0003");
		request.setAttribute("zy", zy);
		//单位规模
		List<MateData> dwgm = baseInfoManager.getTypeByCode("0011");
		request.setAttribute("dwgm", dwgm);
		//账单收取方式
		List<MateData> zqjsfs = baseInfoManager.getTypeByCode("0012");
		request.setAttribute("zqjsfs", zqjsfs);
		//性别
		List<MateData> xb = baseInfoManager.getTypeByCode("0004");
		request.setAttribute("xb", xb);
		//开户行
		List<MateData> khh = baseInfoManager.getTypeByCode("0001");
		request.setAttribute("khh", khh);
		//卡或折
		List<MateData> khz = baseInfoManager.getTypeByCode("0013");
		request.setAttribute("khz", khz);
		//客户来源
		List<MateData> khly = baseInfoManager.getTypeByCode("0015");
		request.setAttribute("khly", khly);
	}
	/**
	 * 页面下拉菜单信息初始化
	 * @param request
	 * @param cjrxx
	 */
	public void initialize(HttpServletRequest request){
//		List<MateData> tzcp = baseInfoManager.getTypeByCode("0010");
//		request.setAttribute("tzcp", tzcp);
		//CARD_TYPE 0005
		//投资产品
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("tzcpFl", "投资产品");
		List<Tzcp> tzcp = baseInfoManager.findTzcp(filters);
		request.setAttribute("tzcp", tzcp);
	}
	//--------------------------------------页面下拉菜单信息初始化       结束-----------------------------------
	
	//--------------------------------------上传测试       开始-----------------------------------
	
	/**
	 * 上传测试
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/upLoadeTest/{Id}")
	public String upLoadeTest(@PathVariable Long Id,HttpServletRequest request, Model model){
		//得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
//				String loginName = operator.getUsername();
//				String loginName = operator.getCtiCode();
		if(operator==null)
		{
			return "redirect:../login";
		}
		request.setAttribute("Id", Id);
		request.setAttribute("nextUrl", "cjrxx/upLoadFile");
		return "cjrxx/uploadFile";
		
	}
	/**
	 * 上传测试
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/upLoadeTestTwo/{Id}")
	public String upLoadeTestTwo(@PathVariable Long Id,HttpServletRequest request, Model model){
		//得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
//				String loginName = operator.getUsername();
//				String loginName = operator.getCtiCode();
		if(operator==null)
		{
			return "redirect:../login";
		}
		request.setAttribute("Id", Id);
		request.setAttribute("upUrl", "cjrxx/upLoadFile");
		return "cjrxx/upLoadeTest";
	}
	/**
	 * 上传测试
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/upLoadFile")
	public String upLoadFile(HttpServletRequest request, HttpServletResponse response, Model model){
		//得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
//				String loginName = operator.getUsername();
//				String loginName = operator.getCtiCode();
		if(operator==null)
		{
			return "redirect:../login";
		}
		
		String id = request.getParameter("id");
		System.out.println("ID=======" + id);
		List<Map<String,String>> fileName = PropertiesUtils.upFile(request, "upload");
		XhUploadFiles xhUploadFiles = new XhUploadFiles();
		if(fileName.size()>0){
			for(Map<String,String> m:fileName){
				xhUploadFiles = new XhUploadFiles();
				xhUploadFiles.setFilename(m.get("fileName"));
				xhUploadFiles.setFilepath("upload");
				xhUploadFiles.setNewfilename(m.get("newFileName"));
				xhUploadFiles.setFileOwner("XH_CJRXX");
				xhUploadFiles.setMainId(Long.parseLong(id));
				baseInfoManager.saveXhUploadFiles(xhUploadFiles);
			}
		}
		return null;
		
	}
	
	@RequestMapping(value="/lookImg")
	public String lookImg(HttpServletRequest request, HttpServletResponse response) {
		String path = request.getParameter("path");
		String name = request.getParameter("name");
		
		request.setAttribute("imgAddress",path+"/"+name);
		//因为直接输出内容而不经过jsp,因此返回null.
		return "cjrxx/imgOne";
	}
	@RequestMapping(value="/lookImgNext")
	public String lookImgNext(HttpServletRequest request, HttpServletResponse response) {
		String imgAddress = request.getParameter("imgAddress");
		
		request.setAttribute("imgAddress",imgAddress);
		//因为直接输出内容而不经过jsp,因此返回null.
		return "cjrxx/img";
	}
	
	@RequestMapping(value = "/downLoadFile/{Id}")
	public String downLoadFile(@PathVariable Long Id,
			HttpServletRequest request,
			HttpServletResponse response, Model model){
		
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("fileOwner", "XH_CJRXX");
		filters.put("mainId", Id);
		List<XhUploadFiles> files = baseInfoManager.findXhUploadFiles(filters);
		request.setAttribute("files", files);
		return "tzsq/downloadFile";
	}
	//--------------------------------------上传测试       结束 -----------------------------------
	
	
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
			if (cjrxxManager.isCheckUnique(propertyName, newValue, oldValue)) {
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
	
	/**
	 * 出借人基本信息-查询
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/cjrxxLookUp")
	public String cjrxxLookUp(HttpServletRequest request, Model model){
		//得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
//				String loginName = operator.getUsername();
//				String loginName = operator.getCtiCode();
		if(operator==null)
		{
			return "redirect:../login";
		}
		// 处理分页的参数
		JdbcPage page = new JdbcPage();
		String pageSize = request.getParameter("numPerPage");
		if (StringUtils.isNotBlank(pageSize)){
			page.setPageSize(Integer.valueOf(pageSize));
		}
		String pageNo = request.getParameter("pageNum");
		if(StringUtils.isNotBlank(pageNo)){
			page.setPageNo(Integer.valueOf(pageNo));
		}
		String orderBy = request.getParameter("orderField");
		if(StringUtils.isNotBlank(orderBy)){
			page.setOrderBy(orderBy);
		}
		String order = request.getParameter("orderDirection");
		if(StringUtils.isNotBlank(order)){
			page.setOrder(order);
		}
		
//		Map<String, Object> params = ServletUtils.getParametersStartingWith2(request, "filter_");	
//		params.put("cjrState", "1");
//		cjrxxManager.searchCjrxx(page, params);
	
		Map<String, Object> map = WebUtils.getParametersStartingWith(request, "filter_");
//		Map<String, Object> map = ServletUtils.getParametersStartingWith2(request, "filter_");	
		//过滤查询内容，所需条件   ----开始
		Employee emp = baseInfoManager.getUserEmployee();
		String loginName = operator.getCtiCode();
		map.put("loginName", loginName);
		map.put("emp", emp);
		//过滤查询内容，所需条件   ----结束
		map.put("cjrState", "1");
		map.put("state", "2");
		List<Map<String,Object>> listCjrxx = cjrxxManager.searchCjrxx("queryCjrxxList", map,page);
		
		model.addAttribute("listCjrxx", listCjrxx);
		model.addAttribute("map", map);
		
		model.addAttribute("page", page);
		return "cjrxx/cjrxxlookup";
		
	}
	
}

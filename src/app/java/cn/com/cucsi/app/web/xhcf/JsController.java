package cn.com.cucsi.app.web.xhcf;

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

import cn.com.cucsi.app.entity.baseinfo.City;
import cn.com.cucsi.app.entity.baseinfo.MateData;
import cn.com.cucsi.app.entity.xhcf.XhJkjjlxr;
import cn.com.cucsi.app.entity.xhcf.XhJkrxx;
import cn.com.cucsi.app.service.baseinfo.BaseInfoManager;
import cn.com.cucsi.app.service.loan.LoanManager;
import cn.com.cucsi.app.service.security.AccountManager;
import cn.com.cucsi.app.service.security.OperatorDetails;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils;
import cn.com.cucsi.core.web.DwzResult;
import cn.com.cucsi.core.web.ServletUtils;

@Controller
@RequestMapping(value="/js")
public class JsController {
	
	private AccountManager accountManager;
	private LoanManager loanManager;
	private BaseInfoManager baseInfoManager;
	
	//***** 月结****start************************************************************
	/**
	 * 月结列表页
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/listJs")
	public String listJkht(HttpServletRequest request,HttpServletResponse response, Model model){
		//得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
//		String loginName = operator.getUsername();
//		String loginName = SpringSecurityUtils.getCurrentUserName();
		if(operator==null)
		{
			return "redirect:../login";
		}
		Page<XhJkrxx> page = new Page<XhJkrxx>();
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
		
		
		Map<String, Object> params = ServletUtils.getParametersStartingWith2(request, "filter_");
	//	loanManager.searchXhcfDkrxx(page, params);
		model.addAttribute("page", page);
		return "jsgl/jsList";
	}
	
	
	/**
	 * 月应收列表页
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/listYys")
	public String listYys(HttpServletRequest request,HttpServletResponse response, Model model){
		//得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
//		String loginName = operator.getUsername();
//		String loginName = SpringSecurityUtils.getCurrentUserName();
		if(operator==null)
		{
			return "redirect:../login";
		}
		Page<XhJkrxx> page = new Page<XhJkrxx>();
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
		
		
		Map<String, Object> params = ServletUtils.getParametersStartingWith2(request, "filter_");
	//	loanManager.searchXhcfDkrxx(page, params);
		model.addAttribute("page", page);
		return "jsgl/yysList";
	}	
	
	/**
	 * 月应收列表页
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/listYyf")
	public String listYyf(HttpServletRequest request,HttpServletResponse response, Model model){
		//得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
//		String loginName = operator.getUsername();
//		String loginName = SpringSecurityUtils.getCurrentUserName();
		if(operator==null)
		{
			return "redirect:../login";
		}
		Page<XhJkrxx> page = new Page<XhJkrxx>();
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
		
		
		Map<String, Object> params = ServletUtils.getParametersStartingWith2(request, "filter_");
	//	loanManager.searchXhcfDkrxx(page, params);
		model.addAttribute("page", page);
		return "jsgl/yyfList";
	}		
	
	
	
	
	
	
	
	

	
	/**
	 * 借款审批列表页
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/listAuditJkht")
	public String listAuditJkht(HttpServletRequest request,HttpServletResponse response, Model model){
		//得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
//		String loginName = operator.getUsername();
//		String loginName = SpringSecurityUtils.getCurrentUserName();
		if(operator==null)
		{
			return "redirect:../login";
		}
		Page<XhJkrxx> page = new Page<XhJkrxx>();
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
		//证件类型
		List<MateData> zjlxMateDateList = baseInfoManager.getTypeByCode("0005");
		model.addAttribute("zjlx0005", zjlxMateDateList);
		String filter_zjlx = request.getParameter("filter_zjlx");
		if(null != filter_zjlx && !"".equals(filter_zjlx)){
			model.addAttribute("zjlx", filter_zjlx);
		}
		
		List<City> province = baseInfoManager.getSuggestCity("0");
		request.setAttribute("province", province);
		
		Map<String, Object> params = ServletUtils.getParametersStartingWith2(request, "filter_");
	//	loanManager.searchXhcfDkrxx(page, params);
		model.addAttribute("page", page);
		return "jkht/auditJkhtList";
	}
	
	/**
	 * 初始化借款审批页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/addAuditJkht", method=RequestMethod.GET)
	public ModelAndView addAuditJkht(HttpServletRequest request){
		List<City> province = baseInfoManager.getSuggestCity("0");
		request.setAttribute("province", province);
		List<MateData> tzcp = baseInfoManager.getTypeByCode("0010");
		request.setAttribute("tzcp", tzcp);
		List<MateData> zjlx = baseInfoManager.getTypeByCode("0005");
		request.setAttribute("zjlx", zjlx);
		return new ModelAndView("jkht/auditJkhtInput", "dkrxx", new XhJkrxx());
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	//保存借款人基础信息
	@RequestMapping(value="/saveLoanBaseMsg")
	public String saveLoanBaseMsg(@ModelAttribute("xhcfdkrxx") XhJkrxx xhcfdkrxx,
			HttpServletRequest request,HttpServletResponse response, Model model){
		//得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		String loginName = operator.getUsername();
//		String loginName = SpringSecurityUtils.getCurrentUserName();
		if(operator==null)
		{
			return "redirect:../login";
		}
		String msg = "保存失败！";
		boolean res = true;//loanManager.saveLoanBaseMsg(request, xhcfdkrxx, loginName);
		if(res){
			msg = "保存成功！";
		}
		DwzResult success = new DwzResult("200",msg,"rel_loanperson","","closeCurrent","");
		ServletUtils.renderJson(response, success);
		return null;
	}
	
	//查看借款人基础信息
	@RequestMapping(value="/lookXhcfDkrxx/{Id}", method=RequestMethod.GET)
	public ModelAndView lookXhcfDkrxx(@PathVariable Long Id, HttpServletRequest request, Model model){
		XhJkrxx xhcfDkrxx = loanManager.getXhcfDkrxx(Id);
		//证件类型
		List<MateData> zjlxMateDateList = baseInfoManager.getTypeByCode("0005");
		model.addAttribute("zjlx0005", zjlxMateDateList);
		//单位性质
		List<MateData> dwxzMateDateList = baseInfoManager.getTypeByCode("0006");
		model.addAttribute("dwxz0006", dwxzMateDateList);
		//婚姻状况
		List<MateData> hyzkMateDateList = baseInfoManager.getTypeByCode("0009");
		model.addAttribute("hyzk0009", hyzkMateDateList);
		return new ModelAndView("loan/loanlookXhcfDkrxx", "xhcfDkrxx", xhcfDkrxx);
	}
	
	//初始化修改借款人基础信息
	@RequestMapping(value="/initEditXhcfDkrxx/{Id}", method=RequestMethod.GET)
	public ModelAndView initEditXhcfDkrxx(@PathVariable Long Id, HttpServletRequest request, Model model){
		XhJkrxx xhcfDkrxx = loanManager.getXhcfDkrxx(Id);
		//证件类型
		List<MateData> zjlxMateDateList = baseInfoManager.getTypeByCode("0005");
		model.addAttribute("zjlx0005", zjlxMateDateList);
		//单位性质
		List<MateData> dwxzMateDateList = baseInfoManager.getTypeByCode("0006");
		model.addAttribute("dwxz0006", dwxzMateDateList);
		//婚姻状况
		List<MateData> hyzkMateDateList = baseInfoManager.getTypeByCode("0009");
		model.addAttribute("hyzk0009", hyzkMateDateList);
		return new ModelAndView("loan/loaneditXhcfDkrxx", "xhcfDkrxx", xhcfDkrxx);
	}
	
	//保存修改借款人基础信息
	@RequestMapping(value="/editLoanBaseMsg", method=RequestMethod.POST)
	public String editLoanBaseMsg(@ModelAttribute("xhcfdkrxx") XhJkrxx xhcfdkrxx,
			HttpServletRequest request, HttpServletResponse response, Model model){
		String msg = "修改失败！";
		boolean res = loanManager.saveEditXhcfDkrxx(request, xhcfdkrxx);
		if(res){
			msg = "修改成功！";
		}
		DwzResult success = new DwzResult("200",msg,"rel_loanperson","","closeCurrent","");
		ServletUtils.renderJson(response, success);
		return null;
	}

	//借款人开户申请
	@RequestMapping(value="/initOpenXhcfDkrxx/{Id}", method=RequestMethod.GET)
	public ModelAndView initOpenXhcfDkrxx(@PathVariable Long Id, HttpServletRequest request, Model model){
		XhJkrxx xhcfDkrxx = loanManager.getXhcfDkrxx(Id);
		//证件类型
		List<MateData> zjlxMateDateList = baseInfoManager.getTypeByCode("0005");
		model.addAttribute("zjlx0005", zjlxMateDateList);
		//单位性质
		List<MateData> dwxzMateDateList = baseInfoManager.getTypeByCode("0006");
		model.addAttribute("dwxz0006", dwxzMateDateList);
		//婚姻状况
		List<MateData> hyzkMateDateList = baseInfoManager.getTypeByCode("0009");
		model.addAttribute("hyzk0009", hyzkMateDateList);
		
		List<City> province = baseInfoManager.getSuggestCity("0");
		request.setAttribute("province", province);
		
		return new ModelAndView("loan/loanopenAccXhcfDkrxx", "xhcfDkrxx", xhcfDkrxx);
	}
	
	//保存修改借款人基础信息
	@RequestMapping(value="/saveOpenXhcfDkrxx", method=RequestMethod.POST)
	public String saveOpenXhcfDkrxx(@ModelAttribute("xhcfdkrxx") XhJkrxx xhcfdkrxx,
			HttpServletRequest request, HttpServletResponse response, Model model){
		String msg = "保存失败！";
		//需要创建借款人开户实体
		boolean res = loanManager.saveEditXhcfDkrxx(request, xhcfdkrxx);
		if(res){
			msg = "保存成功！";
		}
		DwzResult success = new DwzResult("200",msg,"rel_loanperson","","closeCurrent","");
		ServletUtils.renderJson(response, success);
		return null;
	}
	
	//紧急联系人查询    未写呢
	@RequestMapping(value="/contactXhcfDkrxx/{Id}", method=RequestMethod.GET)
	public String contactXhcfDkrxx(HttpServletRequest request,HttpServletResponse response, Model model){
		//得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
//		String loginName = operator.getUsername();
//		String loginName = SpringSecurityUtils.getCurrentUserName();
		if(operator==null)
		{
			return "redirect:../login";
		}
//		Page<XhcfDkrxx> page = new Page<XhcfDkrxx>();
//		String pageSize = request.getParameter("numPerPage");
//		if (StringUtils.isNotBlank(pageSize)){
//			page.setPageSize(Integer.valueOf(pageSize));
//		}
//		String pageNo = request.getParameter("pageNum");
//		if(StringUtils.isNotBlank(pageNo)){
//			page.setPageNo(Integer.valueOf(pageNo));
//		}
//		String orderBy = request.getParameter("orderField");
//		if(StringUtils.isNotBlank(orderBy)){
//			page.setOrderBy(orderBy);
//		}
//		String order = request.getParameter("orderDirection");
//		if(StringUtils.isNotBlank(order)){
//			page.setOrder(order);
//		}
		//证件类型
		List<MateData> zjlxMateDateList = baseInfoManager.getTypeByCode("0005");
		model.addAttribute("zjlx0005", zjlxMateDateList);
		String filter_zjlx = request.getParameter("filter_zjlx");
		if(null != filter_zjlx && !"".equals(filter_zjlx)){
			model.addAttribute("zjlx", filter_zjlx);
		}
//		Map<String, Object> params = ServletUtils.getParametersStartingWith2(request, "filter_");
//		loanManager.searchXhcfDkrxx(page, params);
//		model.addAttribute("page", page);
		return "loan/contactXhcfDkrxx";
	}
	
	//保存借款紧急联系人
	@RequestMapping(value="/saveLoanContact", method=RequestMethod.POST)
	public String saveLoanContact(@ModelAttribute("xhdkjjlxr") XhJkjjlxr xhdkjjlxr,
			HttpServletRequest request, HttpServletResponse response, Model model){
		String msg = "保存失败！";
		//需要创建借款人开户实体
		boolean res = loanManager.saveLoanContact(request, xhdkjjlxr);
		if(res){
			msg = "保存成功！";
		}
		DwzResult success = new DwzResult("200",msg,"rel_loanperson","","closeCurrent","");
		ServletUtils.renderJson(response, success);
		return null;
	}
	//*****借款人基础信息*****end*************************************************************
	
	//*****借款人信息变更申请****start***********************************************************
	
	//借款人申请变更基础信息查询
	@RequestMapping(value="/loanalteration")
	public String loanAlterationList(HttpServletRequest request,HttpServletResponse response, Model model){
		//得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		if(operator==null)
		{
			return "redirect:../login";
		}
		Page<XhJkrxx> page = new Page<XhJkrxx>();
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
		//证件类型
		List<MateData> zjlxMateDateList = baseInfoManager.getTypeByCode("0005");
		model.addAttribute("zjlx0005", zjlxMateDateList);
		String filter_zjlx = request.getParameter("filter_zjlx");
		if(null != filter_zjlx && !"".equals(filter_zjlx)){
			model.addAttribute("zjlx", filter_zjlx);
		}
		Map<String, Object> params = ServletUtils.getParametersStartingWith2(request, "filter_");
		loanManager.queryXhJkrxxxgjl(page, params);
		model.addAttribute("page", page);
		return "loan/loanalteration";
	}
	
	//初始化新增变更申请
	@RequestMapping(value="/addXhcfDkrxxxgjl")
	public String addXhcfDkrxxxgjl(HttpServletRequest request,HttpServletResponse response, Model model){
		//得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
//		String loginName = operator.getUsername();
//		String loginName = SpringSecurityUtils.getCurrentUserName();
		if(operator==null)
		{
			return "redirect:../login";
		}
		//证件类型
		List<MateData> zjlxMateDateList = baseInfoManager.getTypeByCode("0005");
		model.addAttribute("zjlx0005", zjlxMateDateList);
		//单位性质
		List<MateData> dwxzMateDateList = baseInfoManager.getTypeByCode("0006");
		model.addAttribute("dwxz0006", dwxzMateDateList);
		//婚姻状况
		List<MateData> hyzkMateDateList = baseInfoManager.getTypeByCode("0009");
		model.addAttribute("hyzk0009", hyzkMateDateList);
		return "loan/loanchangeappli";
	}
	//*****借款人信息变更申请*****end************************************************************
	
	@Autowired
	public void setAccountManager(AccountManager accountManager) {
		this.accountManager = accountManager;
	}

	@Autowired
	public void setLoanManager(LoanManager loanManager) {
		this.loanManager = loanManager;
	}

	@Autowired
	public void setBaseInfoManager(BaseInfoManager baseInfoManager) {
		this.baseInfoManager = baseInfoManager;
	}



	
}

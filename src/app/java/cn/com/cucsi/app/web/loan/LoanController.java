package cn.com.cucsi.app.web.loan;

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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import cn.com.cucsi.app.entity.baseinfo.Attr;
import cn.com.cucsi.app.entity.baseinfo.City;
import cn.com.cucsi.app.entity.baseinfo.Employee;
import cn.com.cucsi.app.entity.baseinfo.MateData;
import cn.com.cucsi.app.entity.xhcf.XhJkht;
import cn.com.cucsi.app.entity.xhcf.XhJkjjlxr;
import cn.com.cucsi.app.entity.xhcf.XhJkrxx;
import cn.com.cucsi.app.entity.xhcf.XhJkrxxUPHistory;
import cn.com.cucsi.app.entity.xhcf.XhJksq;
import cn.com.cucsi.app.entity.xhcf.XhJksqState;
import cn.com.cucsi.app.service.baseinfo.BaseInfoManager;
import cn.com.cucsi.app.service.loan.LoanManager;
import cn.com.cucsi.app.service.loan.XhJksqManager;
import cn.com.cucsi.app.service.security.AccountManager;
import cn.com.cucsi.app.service.security.OperatorDetails;
import cn.com.cucsi.app.service.templet.TempletManager;
import cn.com.cucsi.app.service.xhcf.XhJkhtManager;
import cn.com.cucsi.app.web.util.JdbcPageUtils;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils;
import cn.com.cucsi.core.web.DwzResult;
import cn.com.cucsi.core.web.ServletUtils;

@Controller
@RequestMapping(value="/loan")
public class LoanController {
	
	private AccountManager accountManager;
	private LoanManager loanManager;
	private BaseInfoManager baseInfoManager;
	private TempletManager templetManager;
	private XhJksqManager xhjksqManager;
	private XhJkhtManager xhJkhtManager;
	
	//*****借款人基础信息****start************************************************************
	//贷款人基础信息查询
	@RequestMapping(value="/listLoanApplies")
	public String loanPersonList(HttpServletRequest request,HttpServletResponse response, Model model){
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
		Map<String, Object> params = ServletUtils.getParametersStartingWith2(request, "filter_");
		loanManager.searchXhcfDkrxx(page, params);
		model.addAttribute("page", page);
		return "loan/listLoanApplies";
	}
	
	//初始化新增借款人基础信息页面
	@RequestMapping(value="/addXhcfDkrxx")
	public String addXhcfDkrxx(HttpServletRequest request,HttpServletResponse response, Model model){
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
		//与借款人关系
		List<MateData> lxrlxMateDateList = baseInfoManager.getTypeByCode("0014");
		model.addAttribute("lxrlx0014", lxrlxMateDateList);
		
		
		String lbd = templetManager.getLoanDATA("A");
		model.addAttribute("lbd", lbd);
		String lblyd = templetManager.getLoanDATA("B");
		model.addAttribute("lblyd", lblyd);
		String xsd = templetManager.getLoanDATA("C");
		model.addAttribute("xsd", xsd);
		String xslyd = templetManager.getLoanDATA("D");
		model.addAttribute("xslyd", xslyd);
		String jyd = templetManager.getLoanDATA("E");
		model.addAttribute("jyd", jyd);
		
		return "loan/loanbasemsg";
	}
	
	//保存借款人基础信息
	@RequestMapping(value="/saveLoanBaseMsg")
	public String saveLoanBaseMsg(@ModelAttribute("xhcfdkrxx") XhJksq xhjksq,
			HttpServletRequest request,HttpServletResponse response, Model model){
		//得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		if(operator==null)
		{
			return "redirect:../login";
		}
		String loginName = operator.getUsername();
		String msg = "保存失败！";
		boolean res = loanManager.saveLoanBaseMsg(request, loginName, xhjksq);
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
	public String contactXhcfDkrxx(@PathVariable Long Id, HttpServletRequest request, 
			HttpServletResponse response, Model model){
		//得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
//		String loginName = operator.getUsername();
//		String loginName = SpringSecurityUtils.getCurrentUserName();
		if(operator==null)
		{
			return "redirect:../login";
		}
		Page<XhJkjjlxr> page = new Page<XhJkjjlxr>();
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
		loanManager.searchXhDkjjlxr(page, params);
		model.addAttribute("page", page);
		
		XhJkrxx xhDkrxx = loanManager.getXhcfDkrxx(Id);
		model.addAttribute("xhdkrxx", xhDkrxx);
		
		//与借款人关系
		List<MateData> hyzkMateDateList = baseInfoManager.getTypeByCode("0014");
		model.addAttribute("ybrgx0014", hyzkMateDateList);
		
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
		DwzResult success = new DwzResult("200",msg,"jjlxr","","","");
		ServletUtils.renderJson(response, success);
		return null;
	}
	
	//初始化修改紧急联系人信息
	@RequestMapping(value="/editContactXhcfDkrxx/{Id}", method=RequestMethod.GET)
	public ModelAndView initEditContactXhcfDkrxx(@PathVariable Long Id, HttpServletRequest request, Model model){
		XhJkjjlxr xhdkjjlxr = loanManager.getXhDkjjlxr(Id);
		//与借款人关系
		List<MateData> hyzkMateDateList = baseInfoManager.getTypeByCode("0014");
		model.addAttribute("ybrgx0014", hyzkMateDateList);
		return new ModelAndView("loan/loaneditContactXhcfDkrxx", "xhdkjjlxr", xhdkjjlxr);
	}
	
	//保存  修改的借款紧急联系人
	@RequestMapping(value="/saveEditLoanContact", method=RequestMethod.POST)
	public String saveEditLoanContact(@ModelAttribute("xhdkjjlxr") XhJkjjlxr xhdkjjlxr,
			HttpServletRequest request, HttpServletResponse response, Model model){
		String msg = "修改失败！";
		boolean res = loanManager.saveEditLoanContact(request, xhdkjjlxr);
		if(res){
			msg = "修改成功！";
		}
//		DwzResult success = new DwzResult("200",msg,"jjlxr","","closeCurrent","");
		DwzResult success = new DwzResult("200",msg,"jjlxr","","","");
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
	
	//添加借款人信息变更申请中的借款人带回
	@RequestMapping(value = "/jkrlookup")
	public String emplookup(HttpServletRequest request, Model model){
		// 处理分页的参数 
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
		loanManager.getXhJkrxxList(page, params);
		
		
		model.addAttribute("page", page);
		
		return "loan/loanlookup";
	}
	
	//保存借款人变更申请
	@RequestMapping(value="/saveLoanChangeappli", method=RequestMethod.POST)
	public String saveLoanChangeappli(@ModelAttribute("XhcfDkrxxxgjl") XhJkrxxUPHistory xhcfdkrxxxgjl,
			HttpServletRequest request, HttpServletResponse response, Model model){
		String name = request.getParameter("dwzjkrxm.name");
		String msg = "保存失败！";
		//需要创建借款人开户实体
		boolean res = loanManager.saveLoanChangeappli(request, xhcfdkrxxxgjl);
		if(res){
			msg = "保存成功！";
		}
		DwzResult success = new DwzResult("200",msg,"rel_loanalteration","","closeCurrent","");
		ServletUtils.renderJson(response, success);
		return null;
	}
	
	//查看某借款人信息变更列表
	@RequestMapping(value="/lookAlterationList/{Id}", method=RequestMethod.GET)
	public ModelAndView lookAlterationList(@PathVariable Long Id, HttpServletRequest request, Model model){
		Page<XhJkrxxUPHistory> page = new Page<XhJkrxxUPHistory>();
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
		loanManager.searchXhJkrxxxgjl(page, params, Id);
//		model.addAttribute("page", page);		

		return new ModelAndView("loan/loanlookAlterationList", "page", page);
	}
	
	//查看借款人某一条信息变更
	@RequestMapping(value="/lookAlteration/{Id}", method=RequestMethod.GET)
	public ModelAndView lookAlteration(@PathVariable Long Id, HttpServletRequest request, Model model){
		XhJkrxxUPHistory xhcfdkrxxxgjl = loanManager.getXhJkrxxUPHistory(Id);
		//证件类型
		List<MateData> zjlxMateDateList = baseInfoManager.getTypeByCode("0005");
		model.addAttribute("zjlx0005", zjlxMateDateList);
		//单位性质
		List<MateData> dwxzMateDateList = baseInfoManager.getTypeByCode("0006");
		model.addAttribute("dwxz0006", dwxzMateDateList);
		//婚姻状况
		List<MateData> hyzkMateDateList = baseInfoManager.getTypeByCode("0009");
		model.addAttribute("hyzk0009", hyzkMateDateList);
		return new ModelAndView("loan/loanlookAlteration", "xhcfdkrxxxgjl", xhcfdkrxxxgjl);
	}
	//*****借款人信息变更申请*****end************************************************************
	
	//*****借款人信息变更审批****start***********************************************************
	//借款人申请变更基础信息查询
	@RequestMapping(value="/alterationexamine")
	public String loanAlterationExamList(HttpServletRequest request,HttpServletResponse response, Model model){
		//得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		if(operator==null)
		{
			return "redirect:../login";
		}
		Page<XhJkrxxUPHistory> page = new Page<XhJkrxxUPHistory>();
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
//		loanManager.searchXhcfDkrxxxgjl(page, params);
		model.addAttribute("page", page);
		return "loan/loanalterationexamine";
	}
	
	//初始化借款人信息变更审批
	@RequestMapping(value="/examAlteration/{Id}", method=RequestMethod.GET)
	public ModelAndView examAlteration(@PathVariable Long Id, HttpServletRequest request, Model model){
		XhJkrxxUPHistory xhcfdkrxxxgjl = loanManager.getXhJkrxxUPHistory(Id);
		//证件类型
		List<MateData> zjlxMateDateList = baseInfoManager.getTypeByCode("0005");
		model.addAttribute("zjlx0005", zjlxMateDateList);
		//单位性质
		List<MateData> dwxzMateDateList = baseInfoManager.getTypeByCode("0006");
		model.addAttribute("dwxz0006", dwxzMateDateList);
		//婚姻状况
		List<MateData> hyzkMateDateList = baseInfoManager.getTypeByCode("0009");
		model.addAttribute("hyzk0009", hyzkMateDateList);
		return new ModelAndView("loan/loanexamAlteration", "xhcfdkrxxxgjl", xhcfdkrxxxgjl);
	}
	
	//保存借款人审批
	@RequestMapping(value="/loanExamAlteration", method=RequestMethod.POST)
	public String saveLoanExamAlteration(@ModelAttribute("XhcfDkrxxxgjl") XhJkrxxUPHistory xhcfdkrxxxgjl,
			HttpServletRequest request, HttpServletResponse response, Model model){
		String msg = "保存失败！";
		//需要创建借款人开户实体
		boolean res = loanManager.saveLoanChangeappli(request, xhcfdkrxxxgjl);
		if(res){
			msg = "保存成功！";
		}
		DwzResult success = new DwzResult("200",msg,"rel_alterationexamine","","closeCurrent","");
		ServletUtils.renderJson(response, success);
		return null;
	}
	//*****借款人信息变更审批****end************************************************************
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

	@Autowired
	public void setTempletManager(TempletManager templetManager) {
		this.templetManager = templetManager;
	}

	/**
	 * 信审复议列表页
	 *//*
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
			model.addAttribute("typedate","READY");
		}else if("OVER".equals(typedata)){
			//----------已复议列表
			map.put("state_injksp", "50.F,70.F");//已复议状态为:50.F 复议完成，待审核利率、70.F 复议完成，待签署
			model.addAttribute("typedate","OVER");
		}
		
		
		// 贷款标志
		map.put("backup01", "CREDIT");
		List<Map<String, Object>> listJksq = new ArrayList<Map<String,Object>>();
		//判断是否循环贷
		String type = request.getParameter("type");
		if(type != null && !"".equals(type)){
			map.put("type", "10");
			model.addAttribute("type", type);
			listJksq = xhjksqManager.searchXhJksqLoop("loanApplyJksqList", map, page);
		}else{
			listJksq = xhjksqManager.searchXhJksq("loanApplyJksqList", map, page);
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
	@Autowired
	public void setXhjksqManager(XhJksqManager xhjksqManager) {
		this.xhjksqManager = xhjksqManager;
	}
	@Autowired
	public void setXhJkhtManager(XhJkhtManager xhJkhtManager) {
		this.xhJkhtManager = xhJkhtManager;
	}
	
	*//**
	 * 复议结果保存
	 * 
	 * @param xhJkht
	 * @param request
	 * @param response
	 * @return
	 *//*
	@RequestMapping(value = "/saveLoanFuYi", method = RequestMethod.POST)
	public String saveJksqFuYi(@ModelAttribute("xhJkht") XhJkht xhJkht,
			HttpServletRequest request, HttpServletResponse response) {

		// XhJkht xhJkhtFind = xhJkhtManager.getXhJkht(xhJkht.getId());
		// Employee emp = baseInfoManager.getUserEmployee();
		// xhJkhtFind.setAuditQzqrPerson(emp.getName());
		// xhJkhtFind.setAuditQzqrIdea(xhJkht.getAuditQzqrIdea());
		// // xhJkhtFind.setState(xhJkht.getState());
		// xhJkhtFind.setAuditQzqrDate(new Timestamp(new Date().getTime()));
		
		Long id = Long.parseLong(request.getParameter("xhJksq.id"));
		XhJksq xhJksq = xhjksqManager.getXhJksq(id);
		//--保存借款合同修改信息
		xhJkhtManager.saveXhJkht(xhJkht);
		//
		
		XhJkht xhJkhtNew = xhJkhtManager.getXhJkht(xhJkht.getId());
//		xhJksq.setLoanCode(xhJkht.getJkhtbm());
//		xhJkhtNew.setJkhtbm(xhJkht.getJkhtbm());
		xhJkhtNew.setXhJksq(xhJksq);
		xhJkhtNew.setQdrq(xhJkht.getQdrq());
		boolean isSuccess = xhJkhtManager.saveXhJkhtFuYi(xhJkhtNew, request);

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

	}*/
	
}

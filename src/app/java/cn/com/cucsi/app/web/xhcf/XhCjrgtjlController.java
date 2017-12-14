package cn.com.cucsi.app.web.xhcf;

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

import cn.com.cucsi.app.entity.baseinfo.MateData;
import cn.com.cucsi.app.entity.baseinfo.Tzcp;
import cn.com.cucsi.app.entity.xhcf.XhCjrgtjl;
import cn.com.cucsi.app.entity.xhcf.XhcfCjrxx;
import cn.com.cucsi.app.service.baseinfo.BaseInfoManager;
import cn.com.cucsi.app.service.security.OperatorDetails;
import cn.com.cucsi.app.service.xhcf.CjrxxManager;
import cn.com.cucsi.app.service.xhcf.XhCjrgtjlManager;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils;
import cn.com.cucsi.core.web.DwzResult;
import cn.com.cucsi.core.web.ServletUtils;

@Controller
@RequestMapping(value="/xhCjrgtjl")
public class XhCjrgtjlController {
	private CjrxxManager cjrxxManager;
	private BaseInfoManager baseInfoManager;
	private XhCjrgtjlManager xhCjrgtjlManager;
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
	@RequestMapping(value="/listXhCjrgtjl")
	public String listXhCjrgtjl(HttpServletRequest request, Model model){
		// 处理分页的参数
		Page<XhCjrgtjl> page = new Page<XhCjrgtjl>();
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
		String cjrxx_id = request.getParameter("cjrxx_id");
		String cjrState = request.getParameter("cjrState");
		if(null != cjrxx_id && !"".equals(cjrxx_id)){
			params.put("cjrxx_id", cjrxx_id);
			params.put("cjrState", cjrState);
			XhcfCjrxx cjrxx = cjrxxManager.getCjrxx(Long.parseLong(cjrxx_id));
			model.addAttribute("cjrxx", cjrxx);
		}
		xhCjrgtjlManager.searchXhCjrgtjl(page, params);
	
		model.addAttribute("page", page);
		model.addAttribute("map", params);
		return "cjrxx/xhCjrgtjlIndex";
		
	}
	
	@RequestMapping(value="/saveXhCjrgtjl",method=RequestMethod.POST)
	public String saveXhCjrgtjl(@ModelAttribute("xhCjrgtjl") XhCjrgtjl xhCjrgtjl, HttpServletRequest request, HttpServletResponse response,Model model){
		//得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
//								String loginName = operator.getUsername();
//								String loginName = SpringSecurityUtils.getCurrentUserName();
		if(operator==null)
		{
			return "redirect:../login";
		}
		DwzResult success;
		if (xhCjrgtjl.getCjrxx() != null && xhCjrgtjl.getCjrxx().getId()!=null){
			XhcfCjrxx cjrxx = cjrxxManager.getCjrxx(xhCjrgtjl.getCjrxx().getId());
			xhCjrgtjl.setCjrxx(cjrxx);
			xhCjrgtjlManager.saveXhCjrgtjl(xhCjrgtjl);
			success = new DwzResult("200","保存成功","","","","");
		}else{
			success = new DwzResult("200","保存失败","","","","");
		}
		ServletUtils.renderJson(response, success);
		return null;
	}
	
	@RequestMapping(value="/addXhCjrgtjl", method=RequestMethod.GET)
	public ModelAndView add(HttpServletRequest request,Model model){
		//得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
//								String loginName = operator.getUsername();
//								String loginName = SpringSecurityUtils.getCurrentUserName();
		if(operator==null)
		{
			return new ModelAndView("redirect:../login");
		}
		String cjrxx_id = request.getParameter("cjrxx_id");
		String cjrState = request.getParameter("cjrState");
		XhcfCjrxx cjrxx = cjrxxManager.getCjrxx(Long.parseLong(cjrxx_id));
		model.addAttribute("cjrxx_id", cjrxx_id);
		model.addAttribute("cjrState", cjrState);
		model.addAttribute("cjrxx", cjrxx);
//		List<MateData> tzcp = baseInfoManager.getTypeByCode("0010");
//		request.setAttribute("tzcp", tzcp);
		//投资产品
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("tzcpFl", "投资产品");
		List<Tzcp> tzcp = baseInfoManager.findTzcp(filters);
		request.setAttribute("tzcp", tzcp);
		List<MateData> zjlx = baseInfoManager.getTypeByCode("0005");
		request.setAttribute("zjlx", zjlx);
		return new ModelAndView("cjrxx/xhCjrgtjlInput", "xhCjrgtjl", new XhCjrgtjl());
	}
	
	@RequestMapping(value="/editXhCjrgtjl/{Id}", method=RequestMethod.GET)
	public ModelAndView edit(@PathVariable Long Id,HttpServletRequest request,Model model){
		//得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
//										String loginName = operator.getUsername();
//										String loginName = SpringSecurityUtils.getCurrentUserName();
		if(operator==null)
		{
			return new ModelAndView("redirect:../login");
		}
		XhCjrgtjl XhCjrgtjl = xhCjrgtjlManager.getXhCjrgtjl(Id);
		XhcfCjrxx cjrxx = XhCjrgtjl.getCjrxx();
		model.addAttribute("cjrxx", cjrxx);
		model.addAttribute("cjrxx_id", cjrxx.getId());
		model.addAttribute("cjrState", XhCjrgtjl.getCjrState());
//		List<MateData> tzcp = baseInfoManager.getTypeByCode("0010");
//		request.setAttribute("tzcp", tzcp);
		//投资产品
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("tzcpFl", "投资产品");
		List<Tzcp> tzcp = baseInfoManager.findTzcp(filters);
		request.setAttribute("tzcp", tzcp);
		List<MateData> zjlx = baseInfoManager.getTypeByCode("0005");
		request.setAttribute("zjlx", zjlx);
		return new ModelAndView("cjrxx/xhCjrgtjlInput", "xhCjrgtjl", XhCjrgtjl);
	}

	@RequestMapping(value="/delXhCjrgtjl/{Id}")
	public String delUser(@PathVariable Long Id, HttpServletResponse response){
		//得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
//										String loginName = operator.getUsername();
//										String loginName = SpringSecurityUtils.getCurrentUserName();
		if(operator==null)
		{
			return "redirect:../login";
		}
		xhCjrgtjlManager.deleteXhCjrgtjl(Id);
		DwzResult success = new DwzResult("200","删除成功","rel_listXhCjrgtjl","","","");
		ServletUtils.renderJson(response, success);
		return null;
	}
	
	@RequestMapping(value="/batchdelXhCjrgtjl")
	public String batchDelUser(HttpServletRequest request, HttpServletResponse response){
		//得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
//										String loginName = operator.getUsername();
//										String loginName = SpringSecurityUtils.getCurrentUserName();
		if(operator==null)
		{
			return "redirect:../login";
		}
		String ids = request.getParameter("ids");
		String[] Ids = ids.split(",");
		boolean isSuccess = xhCjrgtjlManager.batchDelXhCjrgtjl(Ids);
		
		DwzResult success = null;
		if (isSuccess){
			success = new DwzResult("200","删除成功","rel_listXhCjrgtjl","","","");
		}
		else{
			success = new DwzResult("300","删除失败","rel_listXhCjrgtjl","","","");
		}
		ServletUtils.renderJson(response, success);
		return null;
	}
}

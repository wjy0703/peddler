package cn.com.cucsi.app.web.xhcf;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import cn.com.cucsi.app.entity.baseinfo.MiddleMan;
import cn.com.cucsi.app.entity.baseinfo.Xydkzx;
import cn.com.cucsi.app.entity.xhcf.FangKuangGuanLi;
import cn.com.cucsi.app.entity.xhcf.XhJkht;
import cn.com.cucsi.app.entity.xhcf.XhJksq;
import cn.com.cucsi.app.entity.xhcf.XhcfCjrxx;

import cn.com.cucsi.app.service.baseinfo.BaseInfoManager;
import cn.com.cucsi.app.service.loan.XhJksqManager;
import cn.com.cucsi.app.service.security.OperatorDetails;
import cn.com.cucsi.app.service.xhcf.JiaoYiManager;
import cn.com.cucsi.app.service.xhcf.XhJkhtManager;

import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils;
import cn.com.cucsi.core.web.DwzResult;
import cn.com.cucsi.core.web.ServletUtils;

@Controller
@RequestMapping(value="/jygl")
public class JiaoYiController {
	private JiaoYiManager jiaoYiManager;
	private BaseInfoManager baseInfoManager;
	private XhJksqManager xhjksqManager;
	private XhJkhtManager xhJkhtManager;
	@Autowired
	public void setXhJkhtManager(XhJkhtManager xhJkhtManager) {
		this.xhJkhtManager = xhJkhtManager;
	}
	@Autowired
	public void setXhjksqManager(XhJksqManager xhjksqManager) {
		this.xhjksqManager = xhjksqManager;
	}
	@Autowired
	public void setJiaoYiManager(JiaoYiManager jiaoYiManager) {
		this.jiaoYiManager = jiaoYiManager;
	}
	@Autowired
	public void setBaseInfoManager(BaseInfoManager baseInfoManager) {
		this.baseInfoManager = baseInfoManager;
	}
	@RequestMapping(value="/listFangKuan")
	public String list(HttpServletRequest request, Model model){
		// 待放款处理分页的参数
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
//		
//		jiaoYiManager.searchFkgl(page, params);
	    Map<String, Object> map = WebUtils.getParametersStartingWith(request, "filter_");
	    Employee emp = baseInfoManager.getUserEmployee();
	    String loginName = SpringSecurityUtils.getCurrentUserName();
	    List<Map<String,Object>> listKhzx = jiaoYiManager.searchFkglList("queryXhFkglList", map,page);
	    model.addAttribute("filter_jkrbh", request.getParameter("filter_jkrbh"));
	    model.addAttribute("listKhzx", listKhzx);
		model.addAttribute("page", page);

		return "jygl/jygl-fkgl";
		
	}
	@RequestMapping(value="/listYiJingFangKuan")
	public String listyfk(HttpServletRequest request, Model model){
		// 待放款处理分页的参数
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
//		
//		jiaoYiManager.searchFkgl(page, params);
	    Map<String, Object> map = WebUtils.getParametersStartingWith(request, "filter_");
	    Employee emp = baseInfoManager.getUserEmployee();
	    String loginName = SpringSecurityUtils.getCurrentUserName();
	    List<Map<String,Object>> listKhzx = jiaoYiManager.searchFkglList("queryXhyFkglList", map,page);
	    model.addAttribute("filter_jkrbh", request.getParameter("filter_jkrbh"));
	    model.addAttribute("listKhzx", listKhzx);
		model.addAttribute("page", page);

		return "jygl/yfkList";
		
	}
	@RequestMapping(value="/listMiddleMan")
	public String zjrzhlist(HttpServletRequest request, Model model){
		// 中间人处理分页的参数
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
	
	    Map<String, Object> map = WebUtils.getParametersStartingWith(request, "filter_");
	    Employee emp = baseInfoManager.getUserEmployee();
	    String loginName = SpringSecurityUtils.getCurrentUserName();
	    List<Map<String,Object>> listZjr = jiaoYiManager.searchZjrList("queryXhZjzhList", map,page);
	    model.addAttribute("filter_zjzhmc", request.getParameter("filter_zjzhmc"));
	    model.addAttribute("listZjr", listZjr);
		model.addAttribute("page", page);

		return "jygl/zjzhlookup";
		
	}
	
	@RequestMapping(value="/addfangkuan/{Id}", method=RequestMethod.GET)
	public ModelAndView add(@PathVariable Long Id,HttpServletRequest request, Model model){
		//放款管理新增
		Date date = new Date();
		SimpleDateFormat fat = new SimpleDateFormat("yyyy-MM-dd");
		String fksj = fat.format(date);
		model.addAttribute("fksj", fksj);
		XhJksq xhJksq = xhjksqManager.getXhJksq(Id);
//		XhJkht jkht= xhJkhtManager.getXhJkht(Id);
//		FangKuangGuanLi fk= new FangKuangGuanLi();
//		fk.setHtbh(jkht.getJkhtbm());
		return new ModelAndView("jygl/fkgl-input", "xhJksq", xhJksq);
		
		
	}

	@RequestMapping(value="/savefangkuan",method=RequestMethod.POST)
	public String savecldk(@ModelAttribute("fangKuangGuanLi") FangKuangGuanLi fangKuangGuanLi, HttpServletRequest request, HttpServletResponse response){
		// //放款管理保存
		// XhJksq xhJksq =
		// xhjksqManager.getXhJksq(fangKuangGuanLi.getXhJksq().getId());
		// XhJkht xhJkht = xhJkhtManager.getXhJkht(xhJksq.getXhjkht().getId());
		// fangKuangGuanLi.setHtbh(xhJkht.getJkhtbm());
		// fangKuangGuanLi.setHtje(xhJkht.getHtje());
		// long fkqs=xhJkht.getHkqs();
		// fangKuangGuanLi.setFkqs(fkqs);
		// fangKuangGuanLi.setXhJksq(xhJksq);
		// fangKuangGuanLi.setJkrxm(xhJksq.getJkrxm());
		// fangKuangGuanLi.setZhmc(xhJksq.getBankUsername());
		// fangKuangGuanLi.setYhzh(xhJksq.getBankNum());
		// fangKuangGuanLi.setZhkhh(xhJksq.getBankOpen());
		// fangKuangGuanLi.setJkrbh(xhJksq.getLoanCode());
		// jiaoYiManager.savefangKuanGuanLi(fangKuangGuanLi);
		// xhJksq.setState("65");
		// xhjksqManager.saveXhJksq(xhJksq);
		// jiaoYiManager.saveXhKyzqjz(fangKuangGuanLi);
		// // XhJksq xhJksq =
		// xhjksqManager.getXhJksq(fangKuangGuanLi.getXhJksq().getId());
		// // xhJksq.setXhjkht(xhJkht);
		// DwzResult success = new
		// DwzResult("200","保存成功1","rel_listFangKuan","","closeCurrent","");
		// ServletUtils.renderJson(response, success);
	
		return null;
	}
	@RequestMapping(value="/editfangkuan/{Id}", method=RequestMethod.GET)
	public ModelAndView edit(@PathVariable Long Id,HttpServletRequest request){
		//放款管理修改
		FangKuangGuanLi fangKuangGuanLi = jiaoYiManager.getFangKuangGuanLi(Id);
		FangKuangGuanLi fangKuangGuanLi1 = new FangKuangGuanLi();
		fangKuangGuanLi1.setFkzt("2");
		this.initialize(request, null);
		return new ModelAndView("jygl/fkgl-input", "FangKuangGuanLi", fangKuangGuanLi);
	}
	
	public void initialize(HttpServletRequest request,MiddleMan middleMan){
		List<MiddleMan> MiddleMan = jiaoYiManager.getSuggestMiddleMan("0");
		request.setAttribute("fkzh", MiddleMan);
	}
	@RequestMapping(value="/viewfangkuan/{Id}", method=RequestMethod.GET)
	public ModelAndView lookOut(@PathVariable Long Id){
		//得到当前登录用户
		FangKuangGuanLi fangKuangGuanLi = jiaoYiManager.getFangKuangGuanLi(Id);
		return new ModelAndView("jygl/jygl-fkgllook", "FangKuangGuanLi", fangKuangGuanLi);
	}
}

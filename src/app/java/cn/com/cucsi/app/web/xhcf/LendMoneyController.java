package cn.com.cucsi.app.web.xhcf;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.com.cucsi.app.entity.baseinfo.City;
import cn.com.cucsi.app.entity.baseinfo.MateData;
import cn.com.cucsi.app.entity.xhcf.XhcfCjrxx;
import cn.com.cucsi.app.service.baseinfo.BaseInfoManager;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.web.ServletUtils;

/**
 * 资金出借报告
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/lend")
public class LendMoneyController {
	private BaseInfoManager baseInfoManager;
	@Autowired
	public void setBaseInfoManager(BaseInfoManager baseInfoManager) {
		this.baseInfoManager = baseInfoManager;
	}
	//--------------------------------------资金出借报告        开始-----------------------------------
	/**
	 * 资金出借报告
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/listLendMoney")
	public String listLendMoney(HttpServletRequest request, Model model){
		// 处理分页的参数
		Page<XhcfCjrxx> page = new Page<XhcfCjrxx>();
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
		
//		cjrxxManager.searchCjrxx(page, params);
	
		model.addAttribute("page", page);
		List<City> province = baseInfoManager.getSuggestCity("0");
		request.setAttribute("province", province);
		return "jsgl/lendMoneyIndex";
		
	}
	
	/**
	 * 资金出借报告-进入查看月报页
	 * @param cjrxx
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/lookLendMoney", method=RequestMethod.GET)
	public ModelAndView lookLendMoney(HttpServletRequest request){
		List<City> province = baseInfoManager.getSuggestCity("0");
		request.setAttribute("province", province);
		List<MateData> tzcp = baseInfoManager.getTypeByCode("0010");
		request.setAttribute("tzcp", tzcp);
		List<MateData> zjlx = baseInfoManager.getTypeByCode("0005");
		request.setAttribute("zjlx", zjlx);
		return new ModelAndView("jsgl/lookLendMoneyInput", "cjrxx", new XhcfCjrxx());
	}
	/**
	 * 资金出借报告-进入月报明细页
	 * @param cjrxx
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/monthLendMoney", method=RequestMethod.GET)
	public ModelAndView monthLendMoney(HttpServletRequest request){
		List<City> province = baseInfoManager.getSuggestCity("0");
		request.setAttribute("province", province);
		List<MateData> tzcp = baseInfoManager.getTypeByCode("0010");
		request.setAttribute("tzcp", tzcp);
		List<MateData> zjlx = baseInfoManager.getTypeByCode("0005");
		request.setAttribute("zjlx", zjlx);
		return new ModelAndView("jsgl/monthLendMoneyInput", "cjrxx", new XhcfCjrxx());
	}
	//--------------------------------------资金出借报告      结束-----------------------------------
}

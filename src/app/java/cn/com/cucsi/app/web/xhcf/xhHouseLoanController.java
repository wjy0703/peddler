package cn.com.cucsi.app.web.xhcf;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.com.cucsi.app.entity.baseinfo.City;
import cn.com.cucsi.app.entity.baseinfo.MateData;
import cn.com.cucsi.app.entity.baseinfo.Xydkzx;
import cn.com.cucsi.app.entity.security.Role;
import cn.com.cucsi.app.entity.security.User;
import cn.com.cucsi.app.entity.xhcf.XhHouseloans;
import cn.com.cucsi.app.entity.xhcf.XhMatchingInvestment;
import cn.com.cucsi.app.entity.xhcf.ZxGtjl;
import cn.com.cucsi.app.service.baseinfo.BaseInfoManager;
import cn.com.cucsi.app.service.security.OperatorDetails;
import cn.com.cucsi.app.service.xhcf.XhHouseLoansManager;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils;
import cn.com.cucsi.core.web.DwzResult;
import cn.com.cucsi.core.web.ServletUtils;
@Controller
@RequestMapping(value = "/house")
public class xhHouseLoanController {
	private BaseInfoManager baseInfoManager;
	private XhHouseLoansManager houseLoansManager;
	@Autowired
	public void setXhHouseLoansManager(XhHouseLoansManager houseLoansManager) {
		this.houseLoansManager = houseLoansManager;
	}
	@Autowired
	public void setBaseInfoManager(BaseInfoManager baseInfoManager) {
		this.baseInfoManager = baseInfoManager;
	}
	@RequestMapping(value = "/listHouseLoans")
	public String listXhMatchingInvestment(HttpServletRequest request,
			Model model) {
		// 处理分页的参数
		Page<XhMatchingInvestment> page = new Page<XhMatchingInvestment>();
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

//		xhMatchingInvestmentManager.searchXhMatchingInvestment(page, map);
		//开户行
		List<MateData> bankOpenMateDateList = baseInfoManager.getTypeByCode("0020");
		request.setAttribute("bankOpen0020", bankOpenMateDateList);
		//还款方式
		List<MateData> hkWayMateDateList = baseInfoManager.getTypeByCode("0019");
		request.setAttribute("hkWay0019", hkWayMateDateList);
		// 单位性质
		List<MateData> dwxzMateDateList = baseInfoManager.getTypeByCode("0006");
		model.addAttribute("dwxz0006", dwxzMateDateList);
		// 婚姻状况
		List<MateData> hyzkMateDateList = baseInfoManager.getTypeByCode("0009");
		model.addAttribute("hyzk0009", hyzkMateDateList);
		// 与借款人关系
		List<MateData> lxrlxMateDateList = baseInfoManager.getTypeByCode("0014");
		model.addAttribute("lxrlx0014", lxrlxMateDateList);

		//有无子女
		List<MateData> ywznMateDateList = baseInfoManager.getTypeByCode("0018");
		request.setAttribute("ywzn0018", ywznMateDateList);
		model.addAttribute("page", page);
		model.addAttribute("map", map);
		return "housesLoans/xhHouseIndex";

	}
	@RequestMapping(value="/addHouseLoans", method=RequestMethod.GET)
	public ModelAndView add(HttpServletRequest request,Model model){
		//信用贷款新增
//		Date date = new Date();
//		SimpleDateFormat fat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//		String gtTime = fat.format(date);
//		model.addAttribute("gtTime", gtTime); 
		this.initialize(request, null);
		return new ModelAndView("housesLoans/xhHouseInput", "houseLoans", new XhHouseloans());
	}
	@RequestMapping(value="/saveHouseLoans",method=RequestMethod.POST)
	public String save(@ModelAttribute("houseloans") XhHouseloans houseloans, HttpServletRequest request, HttpServletResponse response){
		
		
	
		
		houseLoansManager.saveXhHouseloans(houseloans);

		DwzResult success = new DwzResult("200","保存成功","","","","");
		ServletUtils.renderJson(response, success);
		
		return null;
	}
	public void initialize(HttpServletRequest request,XhHouseloans houseLoans){
		List<City> province = baseInfoManager.getSuggestCity("0");
		request.setAttribute("province", province);
		
		if(houseLoans != null){
			if(StringUtils.isNotEmpty(houseLoans.getProvince())){
				List<City> crty = baseInfoManager.getSuggestCity(houseLoans.getProvince());
				request.setAttribute("crty", crty);
				}
			}
		
		
	}
}

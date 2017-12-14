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

import cn.com.cucsi.app.dao.baseinfo.CityDao;
import cn.com.cucsi.app.dao.xhcf.XhAvailableValueOfClaimsDao;
import cn.com.cucsi.app.entity.baseinfo.City;
import cn.com.cucsi.app.entity.baseinfo.MiddleMan;
import cn.com.cucsi.app.entity.xhcf.QdHouseInfo;
import cn.com.cucsi.app.entity.xhcf.XhAvailableValueOfClaims;
import cn.com.cucsi.app.entity.xhcf.XhJkht;
import cn.com.cucsi.app.entity.xhcf.XhJksq;
import cn.com.cucsi.app.service.baseinfo.BaseInfoManager;
import cn.com.cucsi.app.service.loan.XhJksqManager;
import cn.com.cucsi.app.service.xhcf.QdHouseInfoManager;
import cn.com.cucsi.app.service.xhcf.XhJkhtManager;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.web.DwzResult;
import cn.com.cucsi.core.web.ServletUtils;

@Controller
@RequestMapping(value = "/qdHouseInfo")
public class QdHouseInfoController {
	private QdHouseInfoManager qdHouseInfoManager;

	@Autowired
	private BaseInfoManager baseInfoManager;


	




	@Autowired
	public void setQdHouseInfoManager(QdHouseInfoManager qdHouseInfoManager) {
		this.qdHouseInfoManager = qdHouseInfoManager;
	}

	@RequestMapping(value = "/listQdHouseInfo")
	public String listQdHouseInfo(HttpServletRequest request, Model model) {
		// 处理分页的参数
		Page<QdHouseInfo> page = new Page<QdHouseInfo>();
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
		if(map != null){
			if(map.get("city") != null)
				map.put("city", Long.parseLong(map.get("city").toString()));
			if(map.get("province") != null)
				map.put("province", Long.parseLong(map.get("province").toString()));
		}

		qdHouseInfoManager.searchQdHouseInfo(page, map);
		
		List<City> crmprovince = baseInfoManager.getSuggestCity("0");
		request.setAttribute("crmprovince", crmprovince);

		model.addAttribute("page", page);
		model.addAttribute("map", map);
		return "qdpage/qdHouseInfoIndex";

	}

	@RequestMapping(value = "/saveQdHouseInfo", method = RequestMethod.POST)
	public String saveQdHouseInfo(
			@ModelAttribute("qdHouseInfo") QdHouseInfo qdHouseInfo,
			HttpServletRequest request, HttpServletResponse response) {
		
		qdHouseInfoManager.saveQdHouseInfoWithRelatee(qdHouseInfo);

		DwzResult success = new DwzResult("200", "保存成功", "rel_listQdHouseInfo",
				"", "closeCurrent", "");
		ServletUtils.renderJson(response, success);
		return null;
	}


	@RequestMapping(value = "/addQdHouseInfo", method = RequestMethod.GET)
	public ModelAndView add(HttpServletRequest request) {
		List<City> crmprovince = baseInfoManager.getSuggestCity("0");
		
		List<MiddleMan> middleMans = baseInfoManager.getAllMiddleMan();
	    
		request.setAttribute("middleMans", middleMans);
		request.setAttribute("crmprovince", crmprovince);
		return new ModelAndView("qdpage/qdHouseInfoInput", "qdHouseInfo",
				new QdHouseInfo());
	}

	@RequestMapping(value = "/editQdHouseInfo/{Id}", method = RequestMethod.GET)
	public ModelAndView edit(HttpServletRequest request,@PathVariable Long Id) {
		QdHouseInfo qdHouseInfo = qdHouseInfoManager.getQdHouseInfo(Id);
		List<City> crmprovince = baseInfoManager.getSuggestCity("0");
		if( qdHouseInfo!= null && qdHouseInfo.getProvince() != null){ 
			List<City> cities = baseInfoManager.getSuggestCity(qdHouseInfo.getProvince().toString());
			request.setAttribute("cities", cities);
		}
		
		List<MiddleMan> middleMans = baseInfoManager.getAllMiddleMan();
	    
		request.setAttribute("middleMans", middleMans);
		request.setAttribute("crmprovince", crmprovince);
		return new ModelAndView("qdpage/qdHouseInfoInput", "qdHouseInfo",
				qdHouseInfo);
	}

	@RequestMapping(value = "/delQdHouseInfo/{Id}")
	public String delUser(@PathVariable Long Id, HttpServletResponse response) {
		qdHouseInfoManager.deleteQdHouseInfo(Id);
		DwzResult success = new DwzResult("200", "删除成功", "rel_listQdHouseInfo",
				"", "", "");
		ServletUtils.renderJson(response, success);
		return null;
	}

	@RequestMapping(value = "/batchdelQdHouseInfo")
	public String batchDelUser(HttpServletRequest request,
			HttpServletResponse response) {
		String ids = request.getParameter("ids");
		String[] Ids = ids.split(",");
		boolean isSuccess = qdHouseInfoManager.batchDelQdHouseInfo(Ids);

		DwzResult success = null;
		if (isSuccess) {
			success = new DwzResult("200", "删除成功", "rel_listQdHouseInfo", "",
					"", "");
		} else {
			success = new DwzResult("300", "删除失败", "rel_listQdHouseInfo", "",
					"", "");
		}
		ServletUtils.renderJson(response, success);
		return null;
	}
}

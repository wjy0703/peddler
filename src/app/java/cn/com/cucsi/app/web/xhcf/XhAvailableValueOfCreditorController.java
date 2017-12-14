package cn.com.cucsi.app.web.xhcf;

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

import cn.com.cucsi.app.entity.xhcf.XhAvailableValueOfClaims;

import cn.com.cucsi.app.service.xhcf.JyglManager;

import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.web.DwzResult;
import cn.com.cucsi.core.web.ServletUtils;

@Controller
@RequestMapping(value = "/match")
public class XhAvailableValueOfCreditorController {
	

	private JyglManager jyglManager;

	@Autowired
	public void setJyglManager(JyglManager jyglManager) {
		this.jyglManager = jyglManager;
	}



	@RequestMapping(value = "/listXhAvailableValueOfCreditor")
	public String listXhAvailableValueOfCreditor(HttpServletRequest request,
			Model model) {
		// 处理分页的参数
		Page<XhAvailableValueOfClaims> page = new Page<XhAvailableValueOfClaims>();
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
//		String old = request.getParameter("old");
//		if("OLD".equals(old)){
//			map.put("syhkysMax", 0);//历史债权价值列出可用期数小于0的
//			model.addAttribute("OLD",old);
//		}else{
		String old = "";
		String syhkysMin = request.getParameter("filter_syhkysMin");
		if(null == syhkysMin || "".equals(syhkysMin) || Integer.parseInt(syhkysMin)<1){
			map.put("syhkysMin", 1);//可用债权价值只列出可用期数大于0的
		}
		model.addAttribute("OLD",old);
//		model.addAttribute(syhkysMin);
//		}
		
		jyglManager.queryXhAvailableValueOfClaims(page, map);
		String kyzqjzall = jyglManager.getKyzqjzall(map);
		model.addAttribute("page", page);
		model.addAttribute("map", map);
		model.addAttribute("kyzqjzall", kyzqjzall);
		return "match/xhAvailableValueOfCreditorIndex";

	}
	
	@RequestMapping(value = "/listXhAvailableValueOfCreditorOld")
	public String listXhAvailableValueOfCreditorOld(HttpServletRequest request,
			Model model) {
		// 处理分页的参数
		Page<XhAvailableValueOfClaims> page = new Page<XhAvailableValueOfClaims>();
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
		String old = "Old";
//		if("OLD".equals(old)){
		map.put("syhkysMax", 0);//历史债权价值列出可用期数小于0的
		model.addAttribute("OLD",old);
//		}else{
//			map.put("syhkysMin", 1);//可用债权价值只列出可用期数大于0的
//		}
		
		jyglManager.queryXhAvailableValueOfClaims(page, map);
		String kyzqjzall = jyglManager.getKyzqjzall(map);
		model.addAttribute("page", page);
		model.addAttribute("map", map);
		model.addAttribute("kyzqjzall", kyzqjzall);
		return "match/xhAvailableValueOfCreditorIndex";

	}

//	/**
//	 * 弹窗 查找可用债权 选择带回匹配页
//	 * 
//	 * @param request
//	 * @param model
//	 * @return
//	 */
//	@RequestMapping(value = "/xhFindAvaliableValue/{Id}")
//	public String xhFindAvaliableValue(@PathVariable Long Id,
//			HttpServletRequest request, Model model) {
//		// 处理分页的参数
//		Page<XhAvailableValueOfCreditor> page = new Page<XhAvailableValueOfCreditor>();
//		String pageSize = request.getParameter("numPerPage");
//		if (StringUtils.isNotBlank(pageSize)) {
//			page.setPageSize(Integer.valueOf(pageSize));
//		}
//		String pageNo = request.getParameter("pageNum");
//		if (StringUtils.isNotBlank(pageNo)) {
//			page.setPageNo(Integer.valueOf(pageNo));
//		}
//		String orderBy = request.getParameter("orderField");
//		if (StringUtils.isNotBlank(orderBy)) {
//			page.setOrderBy(orderBy);
//		}
//		String order = request.getParameter("orderDirection");
//		if (StringUtils.isNotBlank(order)) {
//			page.setOrder(order);
//		}
//		Map<String, Object> map = ServletUtils.getParametersStartingWith2(
//				request, "filter_");
//
//		xhAvailableValueOfCreditorManager.searchXhAvailableValueOfCreditor(
//				page, map);
//
//		model.addAttribute("page", page);
//		model.addAttribute("map", map);
//		model.addAttribute("Id", Id);
//		return "match/xhFindAvaliableValue";
//
//	}

//	@RequestMapping(value = "/saveXhAvailableValueOfCreditor", method = RequestMethod.POST)
//	public String saveXhAvailableValueOfCreditor(
//			@ModelAttribute("xhAvailableValueOfCreditor") XhAvailableValueOfCreditor xhAvailableValueOfCreditor,
//			HttpServletRequest request, HttpServletResponse response) {
//
//		xhAvailableValueOfCreditorManager
//				.saveXhAvailableValueOfCreditor(xhAvailableValueOfCreditor);
//
//		DwzResult success = new DwzResult("200", "保存成功",
//				"rel_listXhAvailableValueOfCreditor", "", "closeCurrent", "");
//		ServletUtils.renderJson(response, success);
//
//		return null;
//	}
//
//	@RequestMapping(value = "/addXhAvailableValueOfCreditor", method = RequestMethod.GET)
//	public ModelAndView add() {
//		return new ModelAndView("match/xhAvailableValueOfCreditorInput",
//				"xhAvailableValueOfCreditor", new XhAvailableValueOfCreditor());
//	}
//
//	@RequestMapping(value = "/editXhAvailableValueOfCreditor/{Id}", method = RequestMethod.GET)
//	public ModelAndView edit(@PathVariable Long Id) {
//		XhAvailableValueOfCreditor xhAvailableValueOfCreditor = xhAvailableValueOfCreditorManager
//				.getXhAvailableValueOfCreditor(Id);
//		return new ModelAndView("folder/xhAvailableValueOfCreditorInput",
//				"xhAvailableValueOfCreditor", xhAvailableValueOfCreditor);
//	}
//
//	@RequestMapping(value = "/delXhAvailableValueOfCreditor/{Id}")
//	public String delUser(@PathVariable Long Id, HttpServletResponse response) {
//		xhAvailableValueOfCreditorManager.deleteXhAvailableValueOfCreditor(Id);
//		DwzResult success = new DwzResult("200", "删除成功",
//				"rel_listXhAvailableValueOfCreditor", "", "", "");
//		ServletUtils.renderJson(response, success);
//		return null;
//	}
//
//	@RequestMapping(value = "/batchdelXhAvailableValueOfCreditor")
//	public String batchDelUser(HttpServletRequest request,
//			HttpServletResponse response) {
//		String ids = request.getParameter("ids");
//		String[] Ids = ids.split(",");
//		boolean isSuccess = xhAvailableValueOfCreditorManager
//				.batchDelXhAvailableValueOfCreditor(Ids);
//
//		DwzResult success = null;
//		if (isSuccess) {
//			success = new DwzResult("200", "删除成功",
//					"rel_listXhAvailableValueOfCreditor", "", "", "");
//		} else {
//			success = new DwzResult("300", "删除失败",
//					"rel_listXhAvailableValueOfCreditor", "", "", "");
//		}
//		ServletUtils.renderJson(response, success);
//		return null;
//	}
}

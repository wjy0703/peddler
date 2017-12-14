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

import cn.com.cucsi.app.entity.xhcf.XhMatchingInvestment;
import cn.com.cucsi.app.service.xhcf.XhMatchingInvestmentManager;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.web.DwzResult;
import cn.com.cucsi.core.web.ServletUtils;

@Controller
@RequestMapping(value = "/match")
public class XhMatchingInvestmentController {
	private XhMatchingInvestmentManager xhMatchingInvestmentManager;

	@Autowired
	public void setXhMatchingInvestmentManager(
			XhMatchingInvestmentManager xhMatchingInvestmentManager) {
		this.xhMatchingInvestmentManager = xhMatchingInvestmentManager;
	}

	@RequestMapping(value = "/listMatchingInvestment")
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

		xhMatchingInvestmentManager.searchXhMatchingInvestment(page, map);

		model.addAttribute("page", page);
		model.addAttribute("map", map);
		return "match/xhMatchingInvestmentIndex";

	}

	

	@RequestMapping(value = "/saveXhMatchingInvestment", method = RequestMethod.POST)
	public String saveXhMatchingInvestment(
			@ModelAttribute("xhMatchingInvestment") XhMatchingInvestment xhMatchingInvestment,
			HttpServletRequest request, HttpServletResponse response) {

		xhMatchingInvestmentManager
				.saveXhMatchingInvestment(xhMatchingInvestment);

		DwzResult success = new DwzResult("200", "保存成功",
				"rel_listXhMatchingInvestment", "", "closeCurrent", "");
		ServletUtils.renderJson(response, success);

		return null;
	}

	@RequestMapping(value = "/addXhMatchingInvestment", method = RequestMethod.GET)
	public ModelAndView add() {
		return new ModelAndView("match/xhMatchingInvestmentInput",
				"xhMatchingInvestment", new XhMatchingInvestment());
	}

	@RequestMapping(value = "/editXhMatchingInvestment/{Id}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable Long Id) {
		XhMatchingInvestment xhMatchingInvestment = xhMatchingInvestmentManager
				.getXhMatchingInvestment(Id);
		return new ModelAndView("match/xhMatchingInvestmentInput",
				"xhMatchingInvestment", xhMatchingInvestment);
	}

	@RequestMapping(value = "/delXhMatchingInvestment/{Id}")
	public String delUser(@PathVariable Long Id, HttpServletResponse response) {
		xhMatchingInvestmentManager.deleteXhMatchingInvestment(Id);
		DwzResult success = new DwzResult("200", "删除成功",
				"rel_listXhMatchingInvestment", "", "", "");
		ServletUtils.renderJson(response, success);
		return null;
	}

	@RequestMapping(value = "/batchdelXhMatchingInvestment")
	public String batchDelUser(HttpServletRequest request,
			HttpServletResponse response) {
		String ids = request.getParameter("ids");
		String[] Ids = ids.split(",");
		boolean isSuccess = xhMatchingInvestmentManager
				.batchDelXhMatchingInvestment(Ids);

		DwzResult success = null;
		if (isSuccess) {
			success = new DwzResult("200", "删除成功",
					"rel_listXhMatchingInvestment", "", "", "");
		} else {
			success = new DwzResult("300", "删除失败",
					"rel_listXhMatchingInvestment", "", "", "");
		}
		ServletUtils.renderJson(response, success);
		return null;
	}
}

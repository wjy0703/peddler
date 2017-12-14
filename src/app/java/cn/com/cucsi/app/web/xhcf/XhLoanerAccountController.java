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

import cn.com.cucsi.app.entity.xhcf.XhLoanerAccount;
import cn.com.cucsi.app.entity.xhcf.XhLoanerAccountInfo;
import cn.com.cucsi.app.service.xhcf.XhLoanerAccountInfoManager;
import cn.com.cucsi.app.service.xhcf.XhLoanerAccountManager;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.web.DwzResult;
import cn.com.cucsi.core.web.ServletUtils;

@Controller
@RequestMapping(value = "/xhLoanerAccount")
public class XhLoanerAccountController {
	private XhLoanerAccountManager xhLoanerAccountManager;
	
	private XhLoanerAccountInfoManager xhLoanerAccountInfoManager;

	@Autowired
	public void setXhLoanerAccountInfoManager(
			XhLoanerAccountInfoManager xhLoanerAccountInfoManager) {
		this.xhLoanerAccountInfoManager = xhLoanerAccountInfoManager;
	}

	@Autowired
	public void setXhLoanerAccountManager(
			XhLoanerAccountManager xhLoanerAccountManager) {
		this.xhLoanerAccountManager = xhLoanerAccountManager;
	}

	@RequestMapping(value = "/listXhLoanerAccount")
	public String listXhLoanerAccount(HttpServletRequest request, Model model) {
		// 处理分页的参数
		Page<XhLoanerAccount> page = new Page<XhLoanerAccount>();
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

		xhLoanerAccountManager.searchXhLoanerAccount(page, map);

		model.addAttribute("page", page);
		model.addAttribute("map", map);
		return "loanerAccount/xhLoanerAccountIndex";

	}

	@RequestMapping(value = "/saveXhLoanerAccount", method = RequestMethod.POST)
	public String saveXhLoanerAccount(
			@ModelAttribute("xhLoanerAccount") XhLoanerAccount xhLoanerAccount,
			HttpServletRequest request, HttpServletResponse response) {

		xhLoanerAccountManager.saveXhLoanerAccount(xhLoanerAccount);

		DwzResult success = new DwzResult("200", "保存成功",
				"rel_listXhLoanerAccount", "", "closeCurrent", "");
		ServletUtils.renderJson(response, success);

		return null;
	}

	@RequestMapping(value = "/addXhLoanerAccount", method = RequestMethod.GET)
	public ModelAndView add() {
		return new ModelAndView("loanerAccount/xhLoanerAccountInput",
				"xhLoanerAccount", new XhLoanerAccount());
	}

	@RequestMapping(value = "/advanceXhLoanerAccount/{Id}", method = RequestMethod.GET)
	public String advanceXhLoanerAccount(@PathVariable Long Id, Model model) {
		XhLoanerAccount xhLoanerAccount = xhLoanerAccountManager
				.getXhLoanerAccount(Id);
		XhLoanerAccountInfo xhLoanerAccountInfo = new XhLoanerAccountInfo();
		xhLoanerAccountInfo.setLoanerMainAccount(xhLoanerAccount);
		List<Map<String, Object>> list = xhLoanerAccountInfoManager.getJkhtByAccount(xhLoanerAccount.getLoanContract().getId());
		model.addAttribute("list", list);
		model.addAttribute("xhLoanerAccountInfo", xhLoanerAccountInfo);
		return "loanerAccount/advanceAccountInput";
	}
	
	@RequestMapping(value = "/editXhLoanerAccount/{Id}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable Long Id) {
		XhLoanerAccount xhLoanerAccount = xhLoanerAccountManager
				.getXhLoanerAccount(Id);
		XhLoanerAccountInfo xhLoanerAccountInfo = new XhLoanerAccountInfo();
		xhLoanerAccountInfo.setLoanerMainAccount(xhLoanerAccount);
		return new ModelAndView("loanerAccount/xhLoanerAccountInput",
				"xhLoanerAccountInfo", xhLoanerAccountInfo);
	}

	@RequestMapping(value = "/delXhLoanerAccount/{Id}")
	public String delUser(@PathVariable Long Id, HttpServletResponse response) {
		xhLoanerAccountManager.deleteXhLoanerAccount(Id);
		DwzResult success = new DwzResult("200", "删除成功",
				"rel_listXhLoanerAccount", "", "", "");
		ServletUtils.renderJson(response, success);
		return null;
	}

	@RequestMapping(value = "/batchdelXhLoanerAccount")
	public String batchDelUser(HttpServletRequest request,
			HttpServletResponse response) {
		String ids = request.getParameter("ids");
		String[] Ids = ids.split(",");
		boolean isSuccess = xhLoanerAccountManager.batchDelXhLoanerAccount(Ids);

		DwzResult success = null;
		if (isSuccess) {
			success = new DwzResult("200", "删除成功", "rel_listXhLoanerAccount",
					"", "", "");
		} else {
			success = new DwzResult("300", "删除失败", "rel_listXhLoanerAccount",
					"", "", "");
		}
		ServletUtils.renderJson(response, success);
		return null;
	}
}

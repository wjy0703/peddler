package cn.com.cucsi.app.web.xhcf;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.com.cucsi.app.entity.baseinfo.Employee;
import cn.com.cucsi.app.entity.xhcf.XhHouseLoanApply;
import cn.com.cucsi.app.entity.xhcf.XhHouseLoanConsult;
import cn.com.cucsi.app.entity.xhcf.XhJksq;
import cn.com.cucsi.app.service.baseinfo.BaseInfoManager;
import cn.com.cucsi.app.service.loan.XhJksqManager;
import cn.com.cucsi.app.service.security.OperatorDetails;
import cn.com.cucsi.app.service.xhcf.XhHouseLoanApplyManager;
import cn.com.cucsi.app.service.xhcf.XhHouseLoanConsultManager;
import cn.com.cucsi.app.web.util.RequestPageUtils;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils;
import cn.com.cucsi.core.utils.PropertiesUtils;
import cn.com.cucsi.core.web.DwzResult;
import cn.com.cucsi.core.web.ServletUtils;

@Controller
@RequestMapping(value = "/houseApply")
public class XhHouseLoanApplyController {
	private XhHouseLoanApplyManager xhHouseLoanApplyManager;
	private XhJksqManager xhJksqManager;

	
	
	
	private XhHouseLoanConsultManager xhHouseLoanConsultManager;

	@Autowired
	public void setXhHouseLoanConsultManager(
			XhHouseLoanConsultManager xhHouseLoanConsultManager) {
		this.xhHouseLoanConsultManager = xhHouseLoanConsultManager;
	}
	private BaseInfoManager baseInfoManager;
	@Autowired
	public void setBaseInfoManager(BaseInfoManager baseInfoManager) {
		this.baseInfoManager = baseInfoManager;
	}

	@Autowired
	public void setXhJksqManager(XhJksqManager xhJksqManager) {
		this.xhJksqManager = xhJksqManager;
	}

	@Autowired
	public void setXhHouseLoanApplyManager(
			XhHouseLoanApplyManager xhHouseLoanApplyManager) {
		this.xhHouseLoanApplyManager = xhHouseLoanApplyManager;
	}

	@RequestMapping(value = "/listXhHouseLoanApply")
	public String listXhHouseLoanApply(HttpServletRequest request, Model model) {

		Page<XhHouseLoanApply> page = (new RequestPageUtils<XhHouseLoanApply>()).generatePage(request);

		Map<String, Object> map = ServletUtils.getParametersStartingWith2(request, "filter_");

		Employee emp = baseInfoManager.getUserEmployee();
		String empLevel = emp.getPosition().getPositionLevel();
		if (empLevel.equals("B") || empLevel.equals("C")) {
			map.put("organiId", emp.getOrgani().getId());
		} else if (empLevel.equals("E")) {

			map.put("organiId", emp.getOrgani().getId());
			map.put("teamManagerId", emp.getId());

		}
		xhHouseLoanApplyManager.searchXhHouseLoanApply(page, map);

		model.addAttribute("page", page);
		model.addAttribute("map", map);
		return "houseLoan/xhHouseLoanApplyIndex";

	}

	@RequestMapping(value = "/saveXhHouseLoanApply", method = RequestMethod.POST)
	public void saveXhHouseLoanApply(
			@ModelAttribute("xhHouseLoanApply") XhHouseLoanApply xhHouseLoanApply,
			HttpServletRequest request, HttpServletResponse response) {

		String consultId = null;
		if (request.getParameter("consultId") != null || (!request.getParameter("consultId").equals(""))) {
			consultId = request.getParameter("consultId");
		}

		XhHouseLoanConsult xhHouseLoanConsult = xhHouseLoanConsultManager
				.getXhHouseLoanConsult(Long.parseLong(consultId));
		xhHouseLoanApply.setXhHouseLoanConsult(xhHouseLoanConsult);
		XhJksq jksq = null;
		if (request.getParameter("jksqId") != null
				&& !request.getParameter("jksqId").equals("")) {
			jksq = xhJksqManager.getXhJksq(Long.parseLong(request
					.getParameter("jksqId")));
			xhHouseLoanApply.setLoanApply(jksq);
		}
		String opt = request.getParameter("opt");
		
		if (opt != null && !opt.equals("")) {
			if (opt.equals("0"))
				xhHouseLoanApply.setLoanState("0");
			if (opt.equals("1"))
				xhHouseLoanApply.setLoanState("1");
		}

		// xhJksqManager.saveXhJksq(request, jksq);
		xhHouseLoanApplyManager.saveXhHouseLoanApply(xhHouseLoanApply);
		DwzResult success = new DwzResult("200", "保存成功", "rel_listHouseLoanApply", "rel_listHouseLoanApply","closeCurrent", "rel_listHouseLoanApply");
		ServletUtils.renderJson(response, success);
	
	}

	@RequestMapping(value = "/addXhHouseLoanApply/{Id}", method = RequestMethod.GET)
	public ModelAndView add(@PathVariable Long Id, Model model) {

		XhHouseLoanConsult xhHouseLoanConsult = xhHouseLoanConsultManager
				.getXhHouseLoanConsult(Id);

		model.addAttribute("xhHouseLoanConsult", xhHouseLoanConsult);
		model.addAttribute("consultId", Id);
		return new ModelAndView("houseLoan/xhHouseLoanApplyInput",
				"xhHouseLoanApply", new XhHouseLoanApply());
	}

	@RequestMapping(value = "/editXhHouseLoanApply/{Id}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable Long Id, Model model) {
		XhHouseLoanApply xhHouseLoanApply = xhHouseLoanApplyManager
				.getXhHouseLoanApply(Id);
		XhHouseLoanConsult xhHouseLoanConsult = xhHouseLoanApply
				.getXhHouseLoanConsult();
		model.addAttribute("xhHouseLoanConsult", xhHouseLoanConsult);
		model.addAttribute("consultId", xhHouseLoanApply
				.getXhHouseLoanConsult().getId());
		model.addAttribute("jksqId", xhHouseLoanApply.getLoanApply().getId());

		return new ModelAndView("houseLoan/xhHouseLoanApplyInput",
				"xhHouseLoanApply", xhHouseLoanApply);
	}

	/**
	 * 上传文件
	 * 
	 * @param Id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/uploadFile/{Id},{upflag}", method = RequestMethod.GET)
	public String uploadFile(@PathVariable Long Id,
			@PathVariable String upflag, HttpServletRequest request, Model model) {
		// 得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		if (operator == null) {
			return "redirect:../../login";
		}

		request.setAttribute("Id", Id + "," + upflag);
		request.setAttribute("upflag", upflag);
		request.setAttribute("upUrl", "houseApply/saveUploadFile");

		return "houseLoan/uploadFile";
	}

	/**
	 * 保存上传文件
	 * 
	 * @param Id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveUploadFile")
	public String saveUploadFile(HttpServletRequest request,
			HttpServletResponse response) {
		List<Map<String, String>> fileName = PropertiesUtils.upFile(request,
				"upload");

		boolean flag = xhHouseLoanApplyManager.uploadFile(request, fileName);
		String msg = "保存失败";
		if (flag) {
			msg = "保存成功";
		}
		DwzResult success = new DwzResult("200", msg, "rel_listHouseLoanApply",
				"", "closeCurrent", "");
		ServletUtils.renderJson(response, success);
		return null;
	}
}

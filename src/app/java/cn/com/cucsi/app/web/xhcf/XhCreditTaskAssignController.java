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

import cn.com.cucsi.app.entity.baseinfo.Attr;
import cn.com.cucsi.app.entity.baseinfo.City;
import cn.com.cucsi.app.entity.baseinfo.MateData;
import cn.com.cucsi.app.entity.xhcf.XhCreditAudit;
import cn.com.cucsi.app.entity.xhcf.XhCreditTaskAssign;
import cn.com.cucsi.app.entity.xhcf.XhJkjjlxr;
import cn.com.cucsi.app.entity.xhcf.XhJksq;
import cn.com.cucsi.app.entity.xhcf.XhJksqTogether;
import cn.com.cucsi.app.service.baseinfo.BaseInfoManager;
import cn.com.cucsi.app.service.loan.XhJksqManager;
import cn.com.cucsi.app.service.templet.TempletManager;
import cn.com.cucsi.app.service.xhcf.XhCreditTaskAssignManager;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.web.DwzResult;
import cn.com.cucsi.core.web.ServletUtils;

@Controller
@RequestMapping(value = "/creditAudit")
public class XhCreditTaskAssignController {

	private XhCreditTaskAssignManager xhCreditTaskAssignManager;
	private BaseInfoManager baseInfoManager;
	private XhJksqManager loanApplyManager;
	private XhJksqManager xhjksqManager;
	private TempletManager templetManager;

	@Autowired
	public void setLoanApplyManager(XhJksqManager loanApplyManager) {
		this.loanApplyManager = loanApplyManager;
	}

	@Autowired
	public void setBaseInfoManager(BaseInfoManager baseInfoManager) {
		this.baseInfoManager = baseInfoManager;
	}

	@Autowired
	public void setXhCreditTaskAssignManager(
			XhCreditTaskAssignManager xhCreditTaskAssignManager) {
		this.xhCreditTaskAssignManager = xhCreditTaskAssignManager;
	}

	@Autowired
	public void setXhjksqManager(XhJksqManager xhjksqManager) {
		this.xhjksqManager = xhjksqManager;
	}

	@Autowired
	public void setTempletManager(TempletManager templetManager) {
		this.templetManager = templetManager;
	}

	/**
	 * 信审人员选择页
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/selectCreditAuditPerson/{Id}", method = RequestMethod.GET)
	public ModelAndView selectCreditAuditPerson(@PathVariable Long Id,
			HttpServletRequest request, Model model) {
		// 处理分页的参数
		request.setAttribute("loan_apply_id", Id);

		// 证件类型
		List<MateData> zjlxMateDateList = baseInfoManager.getTypeByCode("0005");
		model.addAttribute("zjlx0005", zjlxMateDateList);
		// 单位性质
		List<MateData> dwxzMateDateList = baseInfoManager.getTypeByCode("0006");
		model.addAttribute("dwxz0006", dwxzMateDateList);
		// 婚姻状况
		List<MateData> hyzkMateDateList = baseInfoManager.getTypeByCode("0009");
		model.addAttribute("hyzk0009", hyzkMateDateList);
		// 与借款人关系
		List<MateData> lxrlxMateDateList = baseInfoManager
				.getTypeByCode("0014");
		model.addAttribute("lxrlx0014", lxrlxMateDateList);
		// 职业
		// List<MateData> zy = baseInfoManager.getTypeByCode("0003");
		// request.setAttribute("zy", zy);
		// 有无子女
		List<MateData> ywznMateDateList = baseInfoManager.getTypeByCode("0018");
		request.setAttribute("ywzn0018", ywznMateDateList);
		// 还款方式
		List<MateData> hkWayMateDateList = baseInfoManager
				.getTypeByCode("0019");
		request.setAttribute("hkWay0019", hkWayMateDateList);
		// 开户行
		List<MateData> bankOpenMateDateList = baseInfoManager
				.getTypeByCode("0020");
		request.setAttribute("bankOpen0020", bankOpenMateDateList);
		// 借款用途
		List<MateData> jkUseMateDateList = baseInfoManager
				.getTypeByCode("0027");
		request.setAttribute("jkUse0027", jkUseMateDateList);

		XhJksq xhJksq = xhjksqManager.getXhJksq(Id);

		// 省份
		List<City> province = baseInfoManager.getSuggestCity("0");
		request.setAttribute("province", province);
		// 地市
		if (null != xhJksq.getProvince()) {
			List<City> city = baseInfoManager.getSuggestCity(xhJksq
					.getProvince().getId().toString());
			request.setAttribute("city", city);
			// 区县
			// if(StringUtils.isNotEmpty(xhJksq.getCity().getId())){
			if (null != xhJksq.getCity()) {
				List<City> area = baseInfoManager.getSuggestCity(xhJksq
						.getCity().getId().toString());
				request.setAttribute("area", area);
			}
		}
		// 紧急联系人
		List<XhJkjjlxr> relativesList = xhjksqManager.getXhJkjjlxrList(Id,
				"亲属", "jksq");
		request.setAttribute("relatives", relativesList);
		List<XhJkjjlxr> friendList = xhjksqManager.getXhJkjjlxrList(Id, "朋友",
				"jksq");
		request.setAttribute("friend", friendList);
		List<XhJkjjlxr> workmateList = xhjksqManager.getXhJkjjlxrList(Id, "同事",
				"jksq");
		request.setAttribute("workmate", workmateList);

		// 信审是否审核，如审核，审核是否通过
		List<XhCreditAudit> xhCreditAuditList = xhjksqManager
				.getXhCreditAudit(xhJksq);
		System.out.println(xhCreditAuditList.size());
		if (null != xhCreditAuditList && xhCreditAuditList.size() > 0) {
			XhCreditAudit xhcreditAudit = null;
			boolean first = true, second = true, third = true;
			for (int i = 0; i < xhCreditAuditList.size(); i++) {
				xhcreditAudit = xhCreditAuditList.get(i);

				if ("1".equals(xhcreditAudit.getCreditType().toString())) {// 初审
					if (first) {
						model.addAttribute("firstXhCreditAudit", xhcreditAudit);
						first = false;
					}
				} else if ("2".equals(xhcreditAudit.getCreditType().toString())) {// 复审
					if (second) {
						model.addAttribute("secondXhCreditAudit", xhcreditAudit);
						second = false;
					}

				} else if ("3".equals(xhcreditAudit.getCreditType().toString())) {// 终审
					if (third) {
						model.addAttribute("lastXhCreditAudit", xhcreditAudit);
						third = false;
					}
				}
			}
		}

		// 共同借款人信息
		if ("是".equals(xhJksq.getTogetherPerson())) {
			XhJksqTogether xhJksqTogether = xhjksqManager
					.getXhJksqTogether(xhJksq);
			if (null != xhJksqTogether) {
				if (null != xhJksqTogether.getMonthlySalary()
						&& !"".equals(xhJksqTogether.getMonthlySalary().trim())) {
					request.setAttribute("cBoxMonthlySalaryShow", true);
				}
				if (null != xhJksqTogether.getRental()
						&& !"".equals(xhJksqTogether.getRental().trim())) {
					request.setAttribute("cBoxRentalShow", true);
				}
				if (null != xhJksqTogether.getOtherIncome()
						&& !"".equals(xhJksqTogether.getOtherIncome().trim())) {
					request.setAttribute("cBoxOtherIncomeShow", true);
				}
				if (null != xhJksqTogether.getAnnualIncome()
						&& !"".equals(xhJksqTogether.getAnnualIncome().trim())) {
					request.setAttribute("cBoxAnnualIncomeShow", true);
				}
				if (null != xhJksqTogether.getSocialFund()
						&& !"".equals(xhJksqTogether.getSocialFund().trim())) {
					request.setAttribute("cBoxSocialFundShow", true);
				}
				// 共同还款人紧急联系人信息
				List<XhJkjjlxr> relativesTogetherList = xhjksqManager
						.getXhJkjjlxrList(xhJksqTogether.getId(), "亲属",
								"jksqTogether");
				request.setAttribute("relativesTogether", relativesTogetherList);
				List<XhJkjjlxr> friendTogetherList = xhjksqManager
						.getXhJkjjlxrList(xhJksqTogether.getId(), "朋友",
								"jksqTogether");
				request.setAttribute("friendTogether", friendTogetherList);
				List<XhJkjjlxr> workmateTogetherList = xhjksqManager
						.getXhJkjjlxrList(xhJksqTogether.getId(), "同事",
								"jksqTogether");
				request.setAttribute("workmateTogether", workmateTogetherList);
			}
			request.setAttribute("xhJksqTogether", xhJksqTogether);
		}
		// return "creditAudit/selectCreditAuditPerson";
		return new ModelAndView("creditAudit/selectCreditAuditPerson", "jksq",
				xhJksq);
	}

	@RequestMapping(value = "/saveCreditTaskAssign/{Id}", method = RequestMethod.POST)
	public String saveXhCreditTaskAssign(
			@PathVariable Long Id,
			@ModelAttribute("xhCreditTaskAssign") XhCreditTaskAssign xhCreditTaskAssign,
			HttpServletRequest request, HttpServletResponse response) {
		xhCreditTaskAssign.setLoanApply(loanApplyManager.getXhJksq(Id));
		xhCreditTaskAssignManager.saveXhCreditTaskAssign(xhCreditTaskAssign);

		DwzResult success = new DwzResult("200", "保存成功", "rel_listLoanApply",
				"", "closeCurrent", "");
		ServletUtils.renderJson(response, success);

		return null;
	}
}

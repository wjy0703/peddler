package cn.com.cucsi.app.web.xhcf;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.util.WebUtils;

import cn.com.cucsi.app.entity.baseinfo.Attr;
import cn.com.cucsi.app.entity.baseinfo.City;
import cn.com.cucsi.app.entity.baseinfo.Employee;
import cn.com.cucsi.app.entity.baseinfo.MateData;
import cn.com.cucsi.app.entity.baseinfo.Organi;
import cn.com.cucsi.app.entity.baseinfo.Xydkzx;
import cn.com.cucsi.app.entity.bean.XhJksqComplement;
import cn.com.cucsi.app.entity.bean.XhJksqTogetherComplement;
import cn.com.cucsi.app.entity.xhcf.XhCreditAudit;
import cn.com.cucsi.app.entity.xhcf.XhJkjjlxr;
import cn.com.cucsi.app.entity.xhcf.XhJksq;
import cn.com.cucsi.app.entity.xhcf.XhJksqState;
import cn.com.cucsi.app.entity.xhcf.XhJksqTogether;
import cn.com.cucsi.app.entity.xhcf.XhJksqUPHistory;
import cn.com.cucsi.app.entity.xhcf.XhUploadFiles;
import cn.com.cucsi.app.service.baseinfo.BaseInfoManager;
import cn.com.cucsi.app.service.loan.XhJksqManager;
import cn.com.cucsi.app.service.security.OperatorDetails;
import cn.com.cucsi.app.service.templet.TempletManager;
import cn.com.cucsi.app.service.xhcf.DksqManager;
import cn.com.cucsi.app.web.InitSetupListener;
import cn.com.cucsi.app.web.util.FileUtil;
import cn.com.cucsi.app.web.util.JdbcPageUtils;
import cn.com.cucsi.app.web.util.P2PJsonUtils;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils;
import cn.com.cucsi.core.utils.DateUtils;
import cn.com.cucsi.core.utils.PropertiesUtils;
import cn.com.cucsi.core.web.DwzResult;
import cn.com.cucsi.core.web.ServletUtils;

@Controller
@RequestMapping(value = "/jksq")
public class JksqController {

	private BaseInfoManager baseInfoManager;
	private XhJksqManager xhjksqManager;

	private TempletManager templetManager;
	private DksqManager dksqManager;

	// *****借款申请基础信息****start************************************************************
	/**
	 * 借款申请列表页
	 */
	@RequestMapping(value = "/listJksq")
	public String listJksq(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		// 得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		if (operator == null) {
			return "redirect:top.login";
		}
		JdbcPage page = JdbcPageUtils.generatePage(request);
		// 借款申请状态
		Map<String, Object> filter = new HashMap<String, Object>();
		filter.put("attrType", "2");
		List<Attr> attrList = baseInfoManager.getAttrList(filter);
		request.setAttribute("attrList", attrList);

		// 借款类型
		Map<String, Object> filter2 = new HashMap<String, Object>();
		filter2.put("attrType", "3");
		List<Attr> attrList2 = baseInfoManager.getAttrList(filter2);
		request.setAttribute("jkTypeList", attrList2);

		List<City> province = baseInfoManager.getSuggestCity("0");
		request.setAttribute("province", province);

		Map<String, Object> map = WebUtils.getParametersStartingWith(request,
				"filter_");
		Employee emp = baseInfoManager.getUserEmployee();
		String loginName = operator.getCtiCode();
		map.put("loginName", loginName);
		map.put("emp", emp);

		
		// 贷款标志
		map.put("backup01", "CREDIT");
		List<Map<String, Object>> listJksq = new ArrayList<Map<String,Object>>();
		//判断是否循环贷
		String type = request.getParameter("type");
		if(type != null && !"".equals(type)){
			map.put("type", "10");
			model.addAttribute("type", type);
			listJksq = xhjksqManager.searchXhJksqLoop("loanApplyJksqList", map, page);
		}else{
			listJksq = xhjksqManager.searchXhJksq("loanApplyJksqList", map, page);
		}
		model.addAttribute("listJksq", listJksq);
		model.addAttribute("page", page);

		model.addAttribute("organiId", request.getParameter("filter_organi.id"));
	    model.addAttribute("organiName", request.getParameter("filter_organi.name"));
		model.addAttribute("filter_jkrxm", request.getParameter("filter_jkrxm"));
		model.addAttribute("filter_telephone",request.getParameter("filter_telephone"));
		model.addAttribute("filter_zjhm", request.getParameter("filter_zjhm"));
		model.addAttribute("filter_jkType",request.getParameter("filter_jkType"));
		model.addAttribute("filter_state_injksp",request.getParameter("filter_state_injksp"));
		model.addAttribute("filter_province",request.getParameter("filter_province"));
		model.addAttribute("filter_city", request.getParameter("filter_city"));
		String filter_province = request.getParameter("filter_province");
		if (null != filter_province && !"".equals(filter_province)) {
			List<City> city = baseInfoManager.getSuggestCity(filter_province);
			request.setAttribute("city", city);
		}
		model.addAttribute("filter_teamName",request.getParameter("filter_teamName"));
		model.addAttribute("filter_saleName",request.getParameter("filter_saleName"));
		model.addAttribute("map", map);

		return "jksq/jksqList";
	}

	/**
	 * 借款申请-进入新增页
	 */
	@RequestMapping(value = "/addJksq/{Id}", method = RequestMethod.GET)
	public ModelAndView addJksq(@PathVariable Long Id,
			HttpServletRequest request, Model model) {
		

		// 借款类型
		Map<String, Object> filter = new HashMap<String, Object>();
		filter.put("attrType", "3");
		List<Attr> attrList = baseInfoManager.getAttrList(filter);
		if (null != attrList && attrList.size() > 0) {
			Attr attr = null;
			for (int i = 0; i < attrList.size(); i++) {
				attr = attrList.get(i);
				String tempd = templetManager.getLoanDATA(attr.getValue());
				model.addAttribute(attr.getKeyName(), tempd);
			}
		}

		// 咨询信息
		Xydkzx xydkzx = dksqManager.getXydkzx(Id);
		return new ModelAndView("jksq/jksqInput", "xydkzx", xydkzx);
	}
	
	/**
	 * 借款申请-进入新增页
	 */
	@RequestMapping(value = "/addLoopJksq/{Id}", method = RequestMethod.GET)
	public String addLoopJksq(@PathVariable Long Id,
			HttpServletRequest request, Model model) {
		// 省份
		List<City> province = baseInfoManager.getSuggestCity("0");
		request.setAttribute("province", province);
		// 咨询信息
		Xydkzx xydkzx = dksqManager.getXydkzx(Id);
		//查询是否存在借款申请信息
		List<XhJksq> ListXhJksq = xhjksqManager.findJksqByXyList(Id);
		model.addAttribute("isJksq", "false");
		if(ListXhJksq != null && ListXhJksq.size() != 0){
			model.addAttribute("jksq", ListXhJksq.get(0));
			if(ListXhJksq.get(0).getXhJkjjlxrs() != null && ListXhJksq.get(0).getXhJkjjlxrs().size() != 0){
				model.addAttribute("isJksq", "true");
				XhJksq xhJksqInfo = ListXhJksq.get(0);
				// 地市
				if (null != xhJksqInfo.getProvince()) {
					List<City> city = baseInfoManager.getSuggestCity(xhJksqInfo
							.getProvince().getId().toString());
					model.addAttribute("city", city);
					// 区县
					if (null != xhJksqInfo.getCity()) {
						List<City> area = baseInfoManager.getSuggestCity(xhJksqInfo
								.getCity().getId().toString());
						model.addAttribute("area", area);
					}
				}
				// 紧急联系人
				String jksqState = "jksq";
				List<XhJkjjlxr> relativesList = xhjksqManager.getXhJkjjlxrList(xhJksqInfo.getId(),
						"亲属", jksqState);
				model.addAttribute("relatives", relativesList);
				List<XhJkjjlxr> friendList = xhjksqManager.getXhJkjjlxrList(xhJksqInfo.getId(), "朋友",
						jksqState);
				model.addAttribute("friend", friendList);
				List<XhJkjjlxr> workmateList = xhjksqManager.getXhJkjjlxrList(xhJksqInfo.getId(), "同事",
						jksqState);
				model.addAttribute("workmate", workmateList);
				String flag = "true";
				Map<String, String> map = null;
				// 借款类型
				Map<String, Object> filter = new HashMap<String, Object>();
				filter.put("attrType", "3");
				List<Attr> attrList = baseInfoManager.getAttrList(filter);
				if (null != attrList && attrList.size() > 0) {
					Attr attr = null;
					for (int i = 0; i < attrList.size(); i++) {
						attr = attrList.get(i);
						map = templetManager.getLoanShowDATA(ListXhJksq.get(0), attr.getValue(),
								flag);
						flag = map.get("flag");
						model.addAttribute(attr.getKeyName(), map.get("key"));
					}
				}
			}
		}
		

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

		 String lbd = templetManager.getLoanDATA("A");
		 model.addAttribute("lbd", lbd);
		 String lblyd = templetManager.getLoanDATA("B");
		 model.addAttribute("lblyd", lblyd);
		 String xsd = templetManager.getLoanDATA("C");
		 model.addAttribute("xsd", xsd);
		 String xslyd = templetManager.getLoanDATA("D");
		 model.addAttribute("xslyd", xslyd);
		 String jyd = templetManager.getLoanDATA("E");
		 model.addAttribute("jyd", jyd);
		model.addAttribute("xydkzx", xydkzx);


		return "jksq/loopJksqInput";
	}

	/**
	 * 保存借款申请基础信息
	 * 
	 * @param xhjksq
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/saveJksqBaseMsg")
	public String saveJksqBaseMsg(@ModelAttribute("xhjksq") XhJksq xhjksq,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		// 得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		String msg = "保存失败！";
		String res = "";
		if(xhjksq.getId() != null){
			res = xhjksqManager.saveLoopXhJksq(request, xhjksq);
		}else{
			res = xhjksqManager.saveXhJksq(request, xhjksq);
		}
		if ("true".equals(res)) {
			msg = "保存成功！";
		} else if ("exist".equals(res)) {
			msg = "已存在该咨询借款申请！";
		}
		DwzResult success = new DwzResult("200", msg, "rel_xydkzx,rel_listLoopConsulting", "",
				"closeCurrent", "");
		ServletUtils.renderJson(response, success);
		return null;
	}

	/**
	 * 查看借款申请信息
	 * 
	 * @param Id
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "jksqShow/{Id}", method = RequestMethod.GET)
	public ModelAndView showJksqMsg(@PathVariable Long Id,
			HttpServletRequest request, Model model) {
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

		// *********借款申请 借款类型动态类型********start***************************
		String flag = "true";
		Map<String, String> map = null;

		// 借款类型
		Map<String, Object> filter = new HashMap<String, Object>();
		filter.put("attrType", "3");
		List<Attr> attrList = baseInfoManager.getAttrList(filter);
		if (null != attrList && attrList.size() > 0) {
			Attr attr = null;
			for (int i = 0; i < attrList.size(); i++) {
				attr = attrList.get(i);
				map = templetManager.getLoanShowDATA(xhJksq, attr.getValue(),
						flag);
				flag = map.get("flag");
				model.addAttribute(attr.getKeyName(), map.get("key"));
			}
		}

		// map = templetManager.getLoanShowDATA(xhJksq, "A", flag);
		// flag = map.get("flag");
		// model.addAttribute("lbd", map.get("key"));
		//
		// map = templetManager.getLoanShowDATA(xhJksq, "B", flag);
		// flag = map.get("flag");
		// model.addAttribute("lblyd", map.get("key"));
		//
		// map = templetManager.getLoanShowDATA(xhJksq, "C", flag);
		// flag = map.get("flag");
		// model.addAttribute("xsd", map.get("key"));
		//
		// map = templetManager.getLoanShowDATA(xhJksq, "D", flag);
		// flag = map.get("flag");
		// model.addAttribute("xslyd", map.get("key"));
		//
		// map = templetManager.getLoanShowDATA(xhJksq, "E", flag);
		// flag = map.get("flag");
		// model.addAttribute("jyd", map.get("key"));
		// *********借款申请 借款类型动态类型*********end****************************

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
		List<XhCreditAudit> xhCreditAuditList = xhjksqManager.getXhCreditAudit(xhJksq);
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
		return new ModelAndView("jksq/jksqShow", "jksq", xhJksq);
	}

	/**
	 * 初始化借款申请修改
	 * 
	 * @param Id
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "jksqEdit/{Id}", method = RequestMethod.GET)
	public ModelAndView initEditJksqMsg(@PathVariable Long Id,
			HttpServletRequest request, Model model) {
		
		XhJksq xhJksq = xhjksqManager.getXhJksq(Id);
		if (null != xhJksq) {
			// 借款咨询信息
			Xydkzx xydkzx = xhJksq.getXydkzx();
			model.addAttribute("xydkzx", xydkzx);
		}
		// *********借款申请 借款类型动态类型********start***************************
		String flag = "true";
		Map<String, String> map = null;
		// 借款类型
		Map<String, Object> filter = new HashMap<String, Object>();
		filter.put("attrType", "3");
		List<Attr> attrList = baseInfoManager.getAttrList(filter);
		if (null != attrList && attrList.size() > 0) {
			Attr attr = null;
			for (int i = 0; i < attrList.size(); i++) {
				attr = attrList.get(i);
				map = templetManager.getLoanShowDATA(xhJksq, attr.getValue(),
						flag);
				flag = map.get("flag");
				model.addAttribute(attr.getKeyName(), map.get("key"));
			}
		}
	
		// *********借款申请 借款类型动态类型*********end****************************

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

		// 共同借款人信息
		if ("是".equals(xhJksq.getTogetherPerson())) {
			XhJksqTogether xhJksqTogether = xhjksqManager
					.getXhJksqTogether(xhJksq);
			if (null != xhJksqTogether) {
				if (null != xhJksqTogether.getMonthlySalary()
						&& !"".equals(xhJksqTogether.getMonthlySalary().trim())) {
					request.setAttribute("cBoxMonthlySalaryEdit", true);
				}
				if (null != xhJksqTogether.getRental()
						&& !"".equals(xhJksqTogether.getRental().trim())) {
					request.setAttribute("cBoxRentalEdit", true);
				}
				if (null != xhJksqTogether.getOtherIncome()
						&& !"".equals(xhJksqTogether.getOtherIncome().trim())) {
					request.setAttribute("cBoxOtherIncomeEdit", true);
				}
				if (null != xhJksqTogether.getAnnualIncome()
						&& !"".equals(xhJksqTogether.getAnnualIncome().trim())) {
					request.setAttribute("cBoxAnnualIncomeEdit", true);
				}
				if (null != xhJksqTogether.getSocialFund()
						&& !"".equals(xhJksqTogether.getSocialFund().trim())) {
					request.setAttribute("cBoxSocialFundEdit", true);
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
		
		List<XhCreditAudit> xhCreditAuditList = xhjksqManager.getXhCreditAudit(xhJksq);
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
        
		return new ModelAndView("jksq/jksqEdit", "jksq", xhJksq);
	}

	/**
	 * 修改借款申请基础信息
	 * 
	 * @param xhjksq
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/editSaveJksqMsg")
	public void editSaveJksqMsg(@ModelAttribute("xhjksq") XhJksq xhjksq, HttpServletRequest request, HttpServletResponse response) {
		String msg = "修改失败！";
		boolean res = xhjksqManager.editSaveXhJksq(request, xhjksq);
		if (res) {
			msg = "修改成功！";
		}
		DwzResult success = new DwzResult("200", msg, "rel_listJksq", "","closeCurrent", "");
		ServletUtils.renderJson(response, success);
	}

	/**
	 * 初始化借款申请补充信息
	 * 
	 * @param Id
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "jksqComplement/{Id}", method = RequestMethod.GET)
	public ModelAndView initJksqComplementMsg(@PathVariable Long Id,
			HttpServletRequest request, Model model) {
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
		if (null != xhJksq) {
			// 借款咨询信息
			Xydkzx xydkzx = xhJksq.getXydkzx();
			model.addAttribute("xydkzx", xydkzx);
		}
		// *********借款申请 借款类型动态类型********start***************************
		String flag = "true";
		Map<String, String> map = null;
		// 借款类型
		Map<String, Object> filter = new HashMap<String, Object>();
		filter.put("attrType", "3");
		List<Attr> attrList = baseInfoManager.getAttrList(filter);
		if (null != attrList && attrList.size() > 0) {
			Attr attr = null;
			for (int i = 0; i < attrList.size(); i++) {
				attr = attrList.get(i);
				map = templetManager.getLoanShowDATA(xhJksq, attr.getValue(),
						flag, "C");
				flag = map.get("flag");
				model.addAttribute(attr.getKeyName(), map.get("key"));
			}
		}

		// map = templetManager.getLoanShowDATA(xhJksq, "A", flag, "C");
		// flag = map.get("flag");
		// model.addAttribute("lbd", map.get("key"));
		//
		// map = templetManager.getLoanShowDATA(xhJksq, "B", flag, "C");
		// flag = map.get("flag");
		// model.addAttribute("lblyd", map.get("key"));
		//
		// map = templetManager.getLoanShowDATA(xhJksq, "C", flag, "C");
		// flag = map.get("flag");
		// model.addAttribute("xsd", map.get("key"));
		//
		// map = templetManager.getLoanShowDATA(xhJksq, "D", flag, "C");
		// flag = map.get("flag");
		// model.addAttribute("xslyd", map.get("key"));
		//
		// map = templetManager.getLoanShowDATA(xhJksq, "E", flag, "C");
		// flag = map.get("flag");
		// model.addAttribute("jyd", map.get("key"));
		// *********借款申请 借款类型动态类型*********end****************************

		// 省份
		List<City> province = baseInfoManager.getSuggestCity("0");
		request.setAttribute("province", province);
		// 地市
		if (null != xhJksq.getProvince()) {
			List<City> city = baseInfoManager.getSuggestCity(xhJksq
					.getProvince().getId().toString());
			request.setAttribute("city", city);
			// 区县
			if (null != xhJksq.getCity()) {
				List<City> area = baseInfoManager.getSuggestCity(xhJksq
						.getCity().getId().toString());
				request.setAttribute("area", area);
			}
		}
		// 紧急联系人
		XhJkjjlxr[] xhjkjjlxrs = xhjksqManager.getJksqComplementJkjjlxr(Id);
		request.setAttribute("xhjkjjlxrs", xhjkjjlxrs);

		request.setAttribute("xhjkjjlxr1", xhjkjjlxrs[0]);
		request.setAttribute("xhjkjjlxr2", xhjkjjlxrs[1]);
		request.setAttribute("xhjkjjlxr3", xhjkjjlxrs[2]);
		request.setAttribute("xhjkjjlxr4", xhjkjjlxrs[3]);
		request.setAttribute("xhjkjjlxr5", xhjkjjlxrs[4]);
		request.setAttribute("xhjkjjlxr6", xhjkjjlxrs[5]);

		// 是否可读
		XhJksqComplement xhJksqComplement = new XhJksqComplement(xhJksq,
				xhjkjjlxrs);
		request.setAttribute("jksqcomp", xhJksqComplement);

		// 共同借款人信息
		if ("是".equals(xhJksq.getTogetherPerson())) {
			XhJksqTogether xhJksqTogether = xhjksqManager
					.getXhJksqTogether(xhJksq);

			if (null != xhJksqTogether) {
				if (null != xhJksqTogether.getMonthlySalary()
						&& !"".equals(xhJksqTogether.getMonthlySalary().trim())) {
					request.setAttribute("cBoxMonthlySalaryComplement", true);
				} else {
					request.setAttribute("cBoxMonthlySalaryComplement", false);
				}
				if (null != xhJksqTogether.getRental()
						&& !"".equals(xhJksqTogether.getRental().trim())) {
					request.setAttribute("cBoxRentalComplement", true);
				} else {
					request.setAttribute("cBoxRentalComplement", false);
				}
				if (null != xhJksqTogether.getOtherIncome()
						&& !"".equals(xhJksqTogether.getOtherIncome().trim())) {
					request.setAttribute("cBoxOtherIncomeComplement", true);
				} else {
					request.setAttribute("cBoxOtherIncomeComplement", false);
				}
				if (null != xhJksqTogether.getAnnualIncome()
						&& !"".equals(xhJksqTogether.getAnnualIncome().trim())) {
					request.setAttribute("cBoxAnnualIncomeComplement", true);
				} else {
					request.setAttribute("cBoxAnnualIncomeComplement", false);
				}
				if (null != xhJksqTogether.getSocialFund()
						&& !"".equals(xhJksqTogether.getSocialFund().trim())) {
					request.setAttribute("cBoxSocialFundComplement", true);
				} else {
					request.setAttribute("cBoxSocialFundComplement", false);
				}
				// 共同还款人紧急联系人信息
				// List<XhJkjjlxr> relativesTogetherList =
				// xhjksqManager.getXhJkjjlxrList(xhJksqTogether.getId(), "亲属",
				// "jksqTogether");
				// request.setAttribute("relativesTogether",
				// relativesTogetherList);
				// List<XhJkjjlxr> friendTogetherList =
				// xhjksqManager.getXhJkjjlxrList(xhJksqTogether.getId(), "朋友",
				// "jksqTogether");
				// request.setAttribute("friendTogether", friendTogetherList);
				// List<XhJkjjlxr> workmateTogetherList =
				// xhjksqManager.getXhJkjjlxrList(xhJksqTogether.getId(), "同事",
				// "jksqTogether");
				// request.setAttribute("workmateTogether",
				// workmateTogetherList);

				XhJkjjlxr[] togethorXhjkjjlxrs = xhjksqManager
						.getComplementJkjjlxr(xhJksqTogether.getId(),
								"jksqTogether");
				request.setAttribute("togethorXhjkjjlxr1",
						togethorXhjkjjlxrs[0]);
				request.setAttribute("togethorXhjkjjlxr2",
						togethorXhjkjjlxrs[1]);
				request.setAttribute("togethorXhjkjjlxr3",
						togethorXhjkjjlxrs[2]);
				request.setAttribute("togethorXhjkjjlxr4",
						togethorXhjkjjlxrs[3]);
				request.setAttribute("togethorXhjkjjlxr5",
						togethorXhjkjjlxrs[4]);
				request.setAttribute("togethorXhjkjjlxr6",
						togethorXhjkjjlxrs[5]);
				// 是否可读
				XhJksqTogetherComplement xhJksqTogetherComplement = new XhJksqTogetherComplement(
						xhJksqTogether, togethorXhjkjjlxrs);
				request.setAttribute("jksqTogetherComp",
						xhJksqTogetherComplement);

			}
			request.setAttribute("xhJksqTogether", xhJksqTogether);
		}
		return new ModelAndView("jksq/jksqComplement", "jksq", xhJksq);
	}

	/**
	 * 保存 “补充借款申请基础信息”
	 * 
	 * @param xhjksq
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/complementSaveJksqMsg")
	public String complementSaveJksqMsg(
			@ModelAttribute("xhjksq") XhJksq xhjksq,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		// 得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		if (operator == null) {
			return "redirect:../../login";
		}
		String msg = "补充失败！";
		boolean res = xhjksqManager.complementSaveJksq(request, xhjksq);
		if (res) {
			msg = "补充成功！";
		}
		DwzResult success = new DwzResult("200", msg, "", "", "closeCurrent",
				"");
		ServletUtils.renderJson(response, success);
		return null;
	}

	/**
	 * 借款申请流程历史状态(信审端)
	 * 
	 * @param Id
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/jksqHistoryList/{Id}")
	public String jksqHistoryList(@PathVariable Long Id,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		// 得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		if (operator == null) {
			return "redirect:../../login";
		}
		Page<XhJksqState> page = new Page<XhJksqState>();
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
		xhjksqManager.jksqHistoryList(Id, page);
		model.addAttribute("page", page);
		model.addAttribute("jksqId", Id);
		return "jksq/jksqHistoryList";
	}
	
	/**
	 * 借款申请流程历史状态（借款前端）
	 * 
	 * @param Id
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/listOptionHistory/{Id}")
	public String listOptionHistory(@PathVariable Long Id,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		// 得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		if (operator == null) {
			return "redirect:../../login";
		}
		Page<XhJksqState> page = new Page<XhJksqState>();
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
		xhjksqManager.jksqHistoryList(Id, page);
		model.addAttribute("page", page);
		model.addAttribute("jksqId", Id);
		return "jksq/listOptionHistory";
	}

	// *****借款申请基础信息*****end*************************************************************

	// ***共同借款人***start*******************************************************
	/**
	 * 添加借款申请的共同还款人的新增页面
	 * 
	 * @param Id
	 *            借款申请ID
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addJksqTogether/{Id},{toghFlag}", method = RequestMethod.GET)
	public ModelAndView addJksqTogether(@PathVariable Long Id,
			@PathVariable String toghFlag, HttpServletRequest request,
			Model model) {
		// 省份
		List<City> province = baseInfoManager.getSuggestCity("0");
		request.setAttribute("province", province);

		XhJksq xhjksq = xhjksqManager.getXhJksq(Id);

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
		// 有无子女
		List<MateData> ywznMateDateList = baseInfoManager.getTypeByCode("0018");
		model.addAttribute("ywzn0018", ywznMateDateList);

		model.addAttribute("toghFlag", toghFlag);

		return new ModelAndView("jksq/jksqTogetherInput", "xhjksq", xhjksq);
	}

	/**
	 * 保存共同还款人基础信息
	 * 
	 * @param xhjksqtogether
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/saveJksqTogetherInfo")
	public String saveJksqTogetherInfo(
			@ModelAttribute("xhjksqtogether") XhJksqTogether xhjksqtogether,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		// 得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		if (operator == null) {
			return "redirect:../../login";
		}
		String msg = "保存失败！";
		boolean res = xhjksqManager.saveXhJksqTogether(request, xhjksqtogether);
		if (res) {
			msg = "保存成功！";
		}
		DwzResult success = new DwzResult("200", msg, "rel_listJksq", "",
				"closeCurrent", "");
		ServletUtils.renderJson(response, success);
		return null;
	}

	/**
	 * 保存 “补充借款申请共借人基础信息”
	 * 
	 * @param xhjksq
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/complementSaveTogetherMsg")
	public String complementSaveTogetherMsg(
			@ModelAttribute("xhjksqtogether") XhJksqTogether xhjksqtogether,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		String msg = "补充失败";
		boolean flag = xhjksqManager.complementSaveTogetherMsg(request,
				xhjksqtogether);
		if (flag) {
			msg = "补充成功！";
		}
		DwzResult success = new DwzResult("200", msg, "", "", "closeCurrent",
				"");
		ServletUtils.renderJson(response, success);
		return null;
	}

	// ***共同借款人****end********************************************************

	// *****上传授信文件****start******************************************************
	/**
	 * 初始化上传授信文件
	 * 
	 * @param Id
	 *            借款申请ID
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/initUpLoad/{Id}")
	public String initUpLoad(@PathVariable Long Id, HttpServletRequest request,
			Model model) {
		// 得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		if (operator == null) {
			return "redirect:../../login";
		}
		request.setAttribute("Id", Id);
		request.setAttribute("nextUrl", "jksq/upLoadAccredited");
		return "cjrxx/upLoadeTestOne";
	}

	/**
	 * 上传授信文件
	 * 
	 * @param Id
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/upLoadAccredited/{Id}")
	public String upLoadAccredited(@PathVariable Long Id,
			HttpServletRequest request, Model model) {
		// 得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		if (operator == null) {
			return "redirect:../../login";
		}
		request.setAttribute("Id", Id);
		request.setAttribute("upUrl", "jksq/upLoadFile");
		return "jksq/upLoadAccredited";
	}

	/**
	 * 上传授信资料 只上传文件不做数据库操作
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/upLoadFile")
	public void upLoadFile(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		List<Map<String, String>> fileName;
		try {
			fileName = PropertiesUtils.upFileFullFunctions(request,"upload");
			ServletUtils.renderJson(response, fileName);
		} catch (Exception e) {
			ServletUtils.renderJson(response, "0");
		}
	}
	
	/**
	 * 上传授信资料写数据库
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/upLoadFileBack")
	public void upLoadFileBack(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		String files = request.getParameter("fileName");
		List<Map<String, String>> fileName = P2PJsonUtils.filestrToMap(files);
		XhJksq jksq = xhjksqManager.getXhJksq(Long.parseLong(id));
		if(null != jksq && ("31.A".equals(jksq.getState())||"31.C".equals(jksq.getState()))){
			xhjksqManager.uploadAndChangeState(id, fileName, "31.C", "外访", "XH_JKSQ");
		}else{
			xhjksqManager.uploadAndChangeState(id, fileName, "22", "授信", "XH_JKSQ");
		}
		ServletUtils.renderJson(response, "1");
	}

	@RequestMapping(value = "/newUpLoadAccredited/{Id}")
	public String newUpLoadAccredited(@PathVariable Long Id,
			HttpServletRequest request, Model model) {
		// 得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		if (operator == null) {
			return "redirect:../../login";
		}
		request.setAttribute("Id", Id);
		request.setAttribute("upUrl", "jksq/upLoadFile");
		return "jksq/upLoadAccredited";
	}

	/**
	 * 补充上传授信
	 * 
	 * @param Id
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/buchongUpLoadAccredited/{Id}")
	public String buchongUpLoadAccredited(@PathVariable Long Id,
			HttpServletRequest request, Model model) {
		// 得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		if (operator == null) {
			return "redirect:../../login";
		}
		request.setAttribute("Id", Id);
		request.setAttribute("upUrl", "jksq/buChongUpLoadFile");
		return "jksq/upLoadAccredited";
	}

	@RequestMapping(value = "/buChongUpLoadFile")
	public void buChongUpLoadFile(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		List<Map<String, String>> fileName = null; 
		try {
			fileName = PropertiesUtils.upFileFullFunctions(request,"upload");
			ServletUtils.renderJson(response, fileName);
		} catch (Exception e) {
			ServletUtils.renderJson(response, "0");
		}
	}

	@RequestMapping(value = "/buChongUpLoadFileBack")
	public void buChongUpLoadFileBack(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		String files = request.getParameter("fileName");
		List<Map<String, String>> fileName = P2PJsonUtils.filestrToMap(files);
		xhjksqManager.uploadFile(id, fileName, "补充授信", "XH_JKSQ");
		ServletUtils.renderJson(response, "1");
	}

	

	// *****上传授信文件*****end*******************************************************

	// *****上传签约文件****start******************************************************
	/**
	 * 初始化签约上传
	 * 
	 * @param Id
	 *            借款申请ID
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/initSignedUpUpLoad/{Id}")
	public String initSignedUpUpLoad(@PathVariable Long Id,
			HttpServletRequest request, Model model) {
		// 得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		if (operator == null) {
			return "redirect:../../login";
		}
		request.setAttribute("Id", Id);
		request.setAttribute("nextUrl", "jksq/upLoadSignedUp");
		return "cjrxx/upLoadeTestOne";
	}

	/**
	 * 上传签约文件
	 * 
	 * @param Id
	 *            借款申请ID
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/upLoadSignedUp/{Id}")
	public String upLoadSignedUp(@PathVariable Long Id,
			HttpServletRequest request, Model model) {
		// 得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		if (operator == null) {
			return "redirect:../../login";
		}
		request.setAttribute("Id", Id);
		XhJksq shJksq = xhjksqManager.getXhJksq(Id);
		request.setAttribute("jksqId", shJksq.getId());
		request.setAttribute("upUrl", "jksq/upLoadFileSignedUp");
		return "jksq/upLoadAccredited";
	}

	/**
	 * 上传签约资料 不写数据到数据库
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/upLoadFileSignedUp")
	public void upLoadFileSignedUp(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		List<Map<String, String>> fileName = null;
		try {
			fileName = PropertiesUtils.upFileFullFunctions(request,"upload");
			ServletUtils.renderJson(response, fileName);
		} catch (Exception e) {
			ServletUtils.renderJson(response, "0");
		}
	}
	
	/**
	 * 上传签约资料 写数据到数据库
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/upLoadFileSignedUpBack")
	public void upLoadFileSignedUpBack(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		// String jksqId = request.getParameter("jksqId");
		String files = request.getParameter("fileName");
		List<Map<String, String>> fileName = P2PJsonUtils.filestrToMap(files);
		xhjksqManager.uploadAndChangeState(id, fileName, "56", "签约", "XH_JKHT");
		ServletUtils.renderJson(response, "1");
	}

	@RequestMapping(value = "/newUpLoadSignedUp/{Id}")
	public String newUpLoadSignedUp(@PathVariable Long Id,
			HttpServletRequest request, Model model) {
		// 得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		if (operator == null) {
			return "redirect:../../login";
		}
		request.setAttribute("Id", Id);
		XhJksq shJksq = xhjksqManager.getXhJksq(Id);
		request.setAttribute("jksqId", shJksq.getId());
		request.setAttribute("upUrl", "jksq/upLoadFileSignedUp");
		return "jksq/upLoadAccredited";
	}

	// *****上传签约文件*****end*******************************************************

	// *****借款申请的变更申请******start*************************************************
	/**
	 * 借款变更申请列表页
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/listChangeJksq")
	public String jksqChangeList(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		// 得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		if (operator == null) {
			return "redirect:../login";
		}

		JdbcPage page = new JdbcPage();
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
		// 证件类型
		List<MateData> zjlxMateDateList = baseInfoManager.getTypeByCode("0005");
		model.addAttribute("zjlx0005", zjlxMateDateList);
		String filter_zjlx = request.getParameter("filter_zjlx");
		if (null != filter_zjlx && !"".equals(filter_zjlx)) {
			model.addAttribute("zjlx", filter_zjlx);
		}
		// 借款申请状态
		Map<String, Object> filter = new HashMap<String, Object>();
		filter.put("attrType", "1");
		List<Attr> attrList = baseInfoManager.getAttrList(filter);
		request.setAttribute("attrList", attrList);

		// 借款类型
		Map<String, Object> filter2 = new HashMap<String, Object>();
		filter2.put("attrType", "3");
		List<Attr> attrList2 = baseInfoManager.getAttrList(filter2);
		request.setAttribute("jkTypeList", attrList2);

		List<City> province = baseInfoManager.getSuggestCity("0");
		request.setAttribute("province", province);

		Map<String, Object> map = WebUtils.getParametersStartingWith(request,
				"filter_");
		Employee emp = baseInfoManager.getUserEmployee();
		String loginName = operator.getCtiCode();
		map.put("loginName", loginName);
		map.put("emp", emp);
		map.put("backup01", "CREDIT");
		List<Map<String, Object>> jksqChange = xhjksqManager
				.searchXhJksqChange("queryJksqList", map, page);
		model.addAttribute("jksqChange", jksqChange);
		model.addAttribute("page", page);

		model.addAttribute("filter_jkrxm", request.getParameter("filter_jkrxm"));
		model.addAttribute("filter_telephone",
				request.getParameter("filter_telephone"));
		model.addAttribute("filter_zjhm", request.getParameter("filter_zjhm"));
		model.addAttribute("filter_jkType",
				request.getParameter("filter_jkType"));
		model.addAttribute("filter_state", request.getParameter("filter_state"));
		model.addAttribute("filter_province",
				request.getParameter("filter_province"));
		model.addAttribute("filter_city", request.getParameter("filter_city"));
		String filter_province = request.getParameter("filter_province");
		if (null != filter_province && !"".equals(filter_province)) {
			List<City> city = baseInfoManager.getSuggestCity(filter_province);
			request.setAttribute("city", city);
		}
		model.addAttribute("filter_teamName",
				request.getParameter("filter_teamName"));
		model.addAttribute("filter_saleName",
				request.getParameter("filter_saleName"));

		return "jksq/jksqChangeIndex";
	}

	/**
	 * 新增借款申请的变更申请
	 * 
	 * @param Id
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addjksqChange/{Id}", method = RequestMethod.GET)
	public ModelAndView addjksqChange(@PathVariable Long Id,
			HttpServletRequest request, Model model) {
		// 投资产品
		List<MateData> tzcp = baseInfoManager.getTypeByCode("0010");
		request.setAttribute("tzcp", tzcp);
		// 证件类型
		List<MateData> zjlx = baseInfoManager.getTypeByCode("0005");
		request.setAttribute("zjlx", zjlx);
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
		XhJksqUPHistory xhJksqUPHistory = null;
		if (null == xhJksq.getAppState() || "-1".equals(xhJksq.getAppState())) {// 无借款申请变更
			xhJksqUPHistory = new XhJksqUPHistory(xhJksq);
			this.initJksqChangeInfo(Id, xhJksq, "jksq", request, model);
		} else {// 已有借款申请变更
			xhJksqUPHistory = xhJksq.getXhJksqUPHistory();
			XhJksq newXhJksq = new XhJksq(xhJksqUPHistory, "all");
			this.initJksqChangeInfo(xhJksqUPHistory.getId(), newXhJksq,
					"jksqChange", request, model);
		}
		return new ModelAndView("jksq/jksqChangeInput", "jksq", xhJksqUPHistory);
	}

	/**
	 * 保存变更申请
	 * 
	 * @param jksqhistory
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/jksqChangeSave")
	public String jksqChangeSave(
			@ModelAttribute("jksqhistory") XhJksqUPHistory jksqhistory,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		// 得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		if (operator == null) {
			return "redirect:../../login";
		}
		String msg = "变更申请失败！";
		boolean res = xhjksqManager.jksqChangeSave(request, jksqhistory);
		if (res) {
			msg = "变更申请成功！";
		}
		// DwzResult success = new DwzResult("200", msg, "rel_listChangeJksq",
		// "", "closeCurrent", "");
		DwzResult success = new DwzResult("200", msg, "rel_listJksq", "",
				"closeCurrent", "");
		ServletUtils.renderJson(response, success);
		return null;
	}

	/**
	 * 变更信息提交
	 * 
	 * @param Id
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/subJkspChange/{Id}", method = RequestMethod.POST)
	public String subJkspChange(@PathVariable Long Id,
			HttpServletResponse response) {
		// 得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		if (operator == null) {
			return "redirect:../login";
		}
		String msg = "提交失败！";
		boolean res = xhjksqManager.subJkspChange(Id);
		if (res) {
			msg = "提交成功！";
		}
		// DwzResult success = new
		// DwzResult("200",msg,"rel_listChangeJksq","","","");
		DwzResult success = new DwzResult("200", msg, "rel_listJksq", "", "",
				"");
		ServletUtils.renderJson(response, success);
		return null;
	}

	/**
	 * 借款申请变更的部分共用方法
	 * 
	 * @param Id
	 * @param xhJksq
	 * @param request
	 * @param model
	 */
	private void initJksqChangeInfo(Long Id, XhJksq xhJksq, String jksqState,
			HttpServletRequest request, Model model) {
		// *********借款申请 借款类型动态类型********start***************************
		String flag = "true";
		Map<String, String> map = null;

		// 借款类型
		Map<String, Object> filter = new HashMap<String, Object>();
		filter.put("attrType", "3");
		List<Attr> attrList = baseInfoManager.getAttrList(filter);
		if (null != attrList && attrList.size() > 0) {
			Attr attr = null;
			for (int i = 0; i < attrList.size(); i++) {
				attr = attrList.get(i);
				map = templetManager.getLoanShowDATA(xhJksq, attr.getValue(),
						flag);
				flag = map.get("flag");
				model.addAttribute(attr.getKeyName(), map.get("key"));
			}
		}

		// map = templetManager.getLoanShowDATA(xhJksq, "A", flag);
		// flag = map.get("flag");
		// model.addAttribute("lbd", map.get("key"));
		//
		// map = templetManager.getLoanShowDATA(xhJksq, "B", flag);
		// flag = map.get("flag");
		// model.addAttribute("lblyd", map.get("key"));
		//
		// map = templetManager.getLoanShowDATA(xhJksq, "C", flag);
		// flag = map.get("flag");
		// model.addAttribute("xsd", map.get("key"));
		//
		// map = templetManager.getLoanShowDATA(xhJksq, "D", flag);
		// flag = map.get("flag");
		// model.addAttribute("xslyd", map.get("key"));
		//
		// map = templetManager.getLoanShowDATA(xhJksq, "E", flag);
		// flag = map.get("flag");
		// model.addAttribute("jyd", map.get("key"));
		// *********借款申请 借款类型动态类型*********end****************************

		// 省份
		List<City> province = baseInfoManager.getSuggestCity("0");
		request.setAttribute("province", province);
		// 地市
		if (null != xhJksq.getProvince()) {
			List<City> city = baseInfoManager.getSuggestCity(xhJksq
					.getProvince().getId().toString());
			request.setAttribute("city", city);
			// 区县
			if (null != xhJksq.getCity()) {
				List<City> area = baseInfoManager.getSuggestCity(xhJksq
						.getCity().getId().toString());
				request.setAttribute("area", area);
			}
		}
		// 紧急联系人
		List<XhJkjjlxr> relativesList = xhjksqManager.getXhJkjjlxrList(Id,
				"亲属", jksqState);
		request.setAttribute("relatives", relativesList);
		List<XhJkjjlxr> friendList = xhjksqManager.getXhJkjjlxrList(Id, "朋友",
				jksqState);
		request.setAttribute("friend", friendList);
		List<XhJkjjlxr> workmateList = xhjksqManager.getXhJkjjlxrList(Id, "同事",
				jksqState);
		request.setAttribute("workmate", workmateList);
	}

	// *****借款申请的变更申请*******end**************************************************

	// *******借款变更审批***start************************************************
	/**
	 * 借款变更审批列表页
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/listChangeAuditJksq")
	public String listChangeAuditJksq(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		// 得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		if (operator == null) {
			return "redirect:../login";
		}
		JdbcPage page = new JdbcPage();
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
		// 证件类型
		List<MateData> zjlxMateDateList = baseInfoManager.getTypeByCode("0005");
		model.addAttribute("zjlx0005", zjlxMateDateList);
		String filter_zjlx = request.getParameter("filter_zjlx");
		if (null != filter_zjlx && !"".equals(filter_zjlx)) {
			model.addAttribute("zjlx", filter_zjlx);
		}
		// 借款申请状态
		Map<String, Object> filter = new HashMap<String, Object>();
		filter.put("attrType", "1");
		List<Attr> attrList = baseInfoManager.getAttrList(filter);
		request.setAttribute("attrList", attrList);

		// 借款类型
		Map<String, Object> filter2 = new HashMap<String, Object>();
		filter2.put("attrType", "3");
		List<Attr> attrList2 = baseInfoManager.getAttrList(filter2);
		request.setAttribute("jkTypeList", attrList2);

		List<City> province = baseInfoManager.getSuggestCity("0");
		request.setAttribute("province", province);

		Map<String, Object> map = WebUtils.getParametersStartingWith(request,
				"filter_");
		Employee emp = baseInfoManager.getUserEmployee();
		String loginName = operator.getCtiCode();
		map.put("loginName", loginName);
		map.put("emp", emp);
		map.put("backup01", "CREDIT");
		List<Map<String, Object>> jksqChangeExam = xhjksqManager
				.searchXhJksqChangeExam("queryJksqList", map, page);
		model.addAttribute("jksqChangeExam", jksqChangeExam);
		model.addAttribute("page", page);

		model.addAttribute("filter_jkrxm", request.getParameter("filter_jkrxm"));
		model.addAttribute("filter_telephone",
				request.getParameter("filter_telephone"));
		model.addAttribute("filter_zjhm", request.getParameter("filter_zjhm"));
		model.addAttribute("filter_jkType",
				request.getParameter("filter_jkType"));
		model.addAttribute("filter_state", request.getParameter("filter_state"));
		model.addAttribute("filter_province",
				request.getParameter("filter_province"));
		model.addAttribute("filter_city", request.getParameter("filter_city"));
		String filter_province = request.getParameter("filter_province");
		if (null != filter_province && !"".equals(filter_province)) {
			List<City> city = baseInfoManager.getSuggestCity(filter_province);
			request.setAttribute("city", city);
		}
		model.addAttribute("filter_teamName",
				request.getParameter("filter_teamName"));
		model.addAttribute("filter_saleName",
				request.getParameter("filter_saleName"));

		return "jksq/changeAuditJksqList";
	}

	/**
	 * 初始化变更申请审核
	 * 
	 * @param Id
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/jksqChangeExam/{Id}", method = RequestMethod.GET)
	public ModelAndView initJksqChangeExam(@PathVariable Long Id,
			HttpServletRequest request, Model model) {
		// 投资产品
		List<MateData> tzcp = baseInfoManager.getTypeByCode("0010");
		request.setAttribute("tzcp", tzcp);
		// 证件类型
		// List<MateData> zjlx = baseInfoManager.getTypeByCode("0005");
		// request.setAttribute("zjlx", zjlx);
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
		XhJksqUPHistory xhJksqUPHistory = null;
		if ("-1".equals(xhJksq.getAppState())) {// 无借款申请变更
			xhJksqUPHistory = new XhJksqUPHistory(xhJksq);
			this.initJksqChangeInfo(Id, xhJksq, "jksq", request, model);
		} else {// 已有借款申请变更
			xhJksqUPHistory = xhJksq.getXhJksqUPHistory();
			XhJksq newXhJksq = new XhJksq(xhJksqUPHistory, "all");
			this.initJksqChangeInfo(xhJksqUPHistory.getId(), newXhJksq,
					"jksqChange", request, model);
		}
		return new ModelAndView("jksq/changeAuditJksqInput", "jksq",
				xhJksqUPHistory);
	}

	/**
	 * 变更申请审批
	 * 
	 * @param history
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/saveJksqChangeExam", method = RequestMethod.POST)
	public String saveJksqChangeExam(
			@ModelAttribute("history") XhJksqUPHistory history,
			HttpServletRequest request, HttpServletResponse response) {
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		if (operator == null) {
			return "redirect:../login";
		}
		String msg = "审核失败！";
		boolean res = xhjksqManager.saveJksqChangeExam(request, history);
		if (res) {
			msg = "审核成功！";
		}
		DwzResult success = new DwzResult("200", msg,
				"rel_listChangeAuditJksq", "", "closeCurrent", "");
		ServletUtils.renderJson(response, success);
		return null;
	}

	// *******借款变更审批****end*************************************************

	// *******借款变更历史***start*************************************************
	/**
	 * 借款信息变更历史列表
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/listChangeHis")
	public String jksqChangeHisList(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		// 得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		if (operator == null) {
			return "redirect:../login";
		}

		JdbcPage page = new JdbcPage();
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
		// 证件类型
		List<MateData> zjlxMateDateList = baseInfoManager.getTypeByCode("0005");
		model.addAttribute("zjlx0005", zjlxMateDateList);
		String filter_zjlx = request.getParameter("filter_zjlx");
		if (null != filter_zjlx && !"".equals(filter_zjlx)) {
			model.addAttribute("zjlx", filter_zjlx);
		}

		// 借款申请状态
		Map<String, Object> filter = new HashMap<String, Object>();
		filter.put("attrType", "1");
		List<Attr> attrList = baseInfoManager.getAttrList(filter);
		request.setAttribute("attrList", attrList);

		// 借款类型
		Map<String, Object> filter2 = new HashMap<String, Object>();
		filter2.put("attrType", "3");
		List<Attr> attrList2 = baseInfoManager.getAttrList(filter2);
		request.setAttribute("jkTypeList", attrList2);

		List<City> province = baseInfoManager.getSuggestCity("0");
		request.setAttribute("province", province);

		Map<String, Object> map = WebUtils.getParametersStartingWith(request,
				"filter_");
		Employee emp = baseInfoManager.getUserEmployee();
		String loginName = operator.getCtiCode();
		map.put("loginName", loginName);
		map.put("emp", emp);
		map.put("backup01", "CREDIT");
		List<Map<String, Object>> jksqChange = xhjksqManager
				.searchXhJksqChange("queryJksqList", map, page);
		model.addAttribute("jksqChange", jksqChange);
		model.addAttribute("page", page);

		model.addAttribute("filter_jkrxm", request.getParameter("filter_jkrxm"));
		model.addAttribute("filter_telephone",
				request.getParameter("filter_telephone"));
		model.addAttribute("filter_zjhm", request.getParameter("filter_zjhm"));
		model.addAttribute("filter_jkType",
				request.getParameter("filter_jkType"));
		model.addAttribute("filter_state", request.getParameter("filter_state"));
		model.addAttribute("filter_province",
				request.getParameter("filter_province"));
		model.addAttribute("filter_city", request.getParameter("filter_city"));
		String filter_province = request.getParameter("filter_province");
		if (null != filter_province && !"".equals(filter_province)) {
			List<City> city = baseInfoManager.getSuggestCity(filter_province);
			request.setAttribute("city", city);
		}
		model.addAttribute("filter_teamName",
				request.getParameter("filter_teamName"));
		model.addAttribute("filter_saleName",
				request.getParameter("filter_saleName"));

		return "jksq/jksqChangeHisIndex";
	}

	//
	@RequestMapping(value = "/listChangeHis/{Id}")
	public ModelAndView listChangeHis(@PathVariable Long Id,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		// 得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		if (operator == null) {
			return new ModelAndView("redirect:../login");
		}

		// 投资产品
		List<MateData> tzcp = baseInfoManager.getTypeByCode("0010");
		request.setAttribute("tzcp", tzcp);
		// 证件类型
		// List<MateData> zjlx = baseInfoManager.getTypeByCode("0005");
		// request.setAttribute("zjlx", zjlx);
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

		XhJksq xhjksq = xhjksqManager.getXhJksq(Id);

		// Page<XhJksqUPHistory> page = new Page<XhJksqUPHistory>();
		JdbcPage page = new JdbcPage();
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
		// Map<String, Object> params =
		// ServletUtils.getParametersStartingWith2(request, "filter_");
		// xhjksqManager.searchXhJksqUPHistory(page, params, xhjksq);
		model.addAttribute("page", page);

		Map<String, Object> map = WebUtils.getParametersStartingWith(request,
				"filter_");
		Employee emp = baseInfoManager.getUserEmployee();
		String loginName = operator.getCtiCode();
		map.put("loginName", loginName);
		map.put("emp", emp);

		List<Map<String, Object>> jksqChangeHis = xhjksqManager
				.searchXhJksqUPHistory("queryHisList", map, page, xhjksq);
		model.addAttribute("his", jksqChangeHis);

		// 省份
		List<City> province = baseInfoManager.getSuggestCity("0");
		request.setAttribute("province", province);
		// 地市
		if (null != xhjksq.getProvince()) {
			List<City> city = baseInfoManager.getSuggestCity(xhjksq
					.getProvince().getId().toString());
			request.setAttribute("city", city);
			// 区县
			if (null != xhjksq.getCity()) {
				List<City> area = baseInfoManager.getSuggestCity(xhjksq
						.getCity().getId().toString());
				request.setAttribute("area", area);
			}
		}

		return new ModelAndView("jksq/jksqChangeHisList", "jksq", xhjksq);
	}

	@RequestMapping(value = "jksqHisShow/{Id}", method = RequestMethod.GET)
	public ModelAndView jksqHisShow(@PathVariable Long Id,
			HttpServletRequest request, Model model) {
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

		XhJksqUPHistory xhJksqUPHistory = xhjksqManager.getXhJksqUPHistory(Id);
		// *********借款申请 借款类型动态类型********start***************************
		XhJksq newXhJksq = new XhJksq(xhJksqUPHistory, "all");
		String flag = "true";
		Map<String, String> map = null;

		// 借款类型
		Map<String, Object> filter = new HashMap<String, Object>();
		filter.put("attrType", "3");
		List<Attr> attrList = baseInfoManager.getAttrList(filter);
		if (null != attrList && attrList.size() > 0) {
			Attr attr = null;
			for (int i = 0; i < attrList.size(); i++) {
				attr = attrList.get(i);
				map = templetManager.getLoanShowDATA(newXhJksq,
						attr.getValue(), flag);
				flag = map.get("flag");
				model.addAttribute(attr.getKeyName(), map.get("key"));
			}
		}

		// map = templetManager.getLoanShowDATA(newXhJksq, "A", flag);
		// flag = map.get("flag");
		// model.addAttribute("lbd", map.get("key"));
		//
		// map = templetManager.getLoanShowDATA(newXhJksq, "B", flag);
		// flag = map.get("flag");
		// model.addAttribute("lblyd", map.get("key"));
		//
		// map = templetManager.getLoanShowDATA(newXhJksq, "C", flag);
		// flag = map.get("flag");
		// model.addAttribute("xsd", map.get("key"));
		//
		// map = templetManager.getLoanShowDATA(newXhJksq, "D", flag);
		// flag = map.get("flag");
		// model.addAttribute("xslyd", map.get("key"));
		//
		// map = templetManager.getLoanShowDATA(newXhJksq, "E", flag);
		// flag = map.get("flag");
		// model.addAttribute("jyd", map.get("key"));
		// *********借款申请 借款类型动态类型*********end****************************

		// 省份
		List<City> province = baseInfoManager.getSuggestCity("0");
		request.setAttribute("province", province);
		// 地市
		if (null != xhJksqUPHistory.getProvince()) {
			List<City> city = baseInfoManager.getSuggestCity(xhJksqUPHistory
					.getProvince().getId().toString());
			request.setAttribute("city", city);
			// 区县
			if (null != xhJksqUPHistory.getCity()) {
				List<City> area = baseInfoManager
						.getSuggestCity(xhJksqUPHistory.getCity().getId()
								.toString());
				request.setAttribute("area", area);
			}
		}
		// 紧急联系人
		List<XhJkjjlxr> relativesList = xhjksqManager.getXhJkjjlxrList(Id,
				"亲属", "jksqChange");
		request.setAttribute("relatives", relativesList);
		List<XhJkjjlxr> friendList = xhjksqManager.getXhJkjjlxrList(Id, "朋友",
				"jksqChange");
		request.setAttribute("friend", friendList);
		List<XhJkjjlxr> workmateList = xhjksqManager.getXhJkjjlxrList(Id, "同事",
				"jksqChange");
		request.setAttribute("workmate", workmateList);

		return new ModelAndView("jksq/jksqHisShow", "jksq", xhJksqUPHistory);
	}

	// *******借款变更历史***start*************************************************

	// *******客户放弃***start*************************************************
	@RequestMapping(value = "/userGiveUp/{Id}", method = RequestMethod.GET)
	public ModelAndView userGiveUp(@PathVariable Long Id, HttpServletResponse response,HttpServletRequest request, Model model) {

		XhJksq xhJksq = xhjksqManager.getXhJksq(Id);

		return new ModelAndView("jksq/jksqKhfq", "jksq", xhJksq);
	}

	@RequestMapping(value = "/userGiveUpSave", method = RequestMethod.POST)
	public String userGiveUpSave( HttpServletRequest request, HttpServletResponse response) {
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		if (operator == null) {
			return "redirect:../login";
		}
		String msg = "客户放弃失败！";
		String id = request.getParameter("id");
		Long Id = Long.parseLong(id);
		String mess = request.getParameter("mess");
		//System.out.println("客户放弃id======" +id + ";;mess==="+mess);
		//XhJksq xhjksq = xhjksqManager.getXhJksq(Id);
		//boolean res = xhjksqManager.changeState(xhjksq, "81");
		boolean res = xhjksqManager.userGiveUp(Id,mess);
		if (res) {
			msg = "客户放弃成功！";
		}
		DwzResult success = new DwzResult("200", msg, "", "", "closeCurrent",
				"");
		ServletUtils.renderJson(response, success);
		return null;
	}
	// *******客户放弃****end**************************************************

	/**
	 * 选择借款申请对应的操作"
	 * 
	 * @param Id
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/selectOption/{Id}")
	public String selectOption(@PathVariable Long Id,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		// 得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		if (operator == null) {
			return "redirect:../login";
		}

		request.setAttribute("Id", Id);
		return "jksq/optionDialog";
	}

	/**
	 * 借款人信用初审-借款人信用初审页
	 * 
	 * @param cjrxx
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/listJksqxycs")
	public String listjkrsycs(HttpServletRequest request, Model model) {
		// 得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		if (operator == null) {
			return "redirect:../../login";
		}
		// 处理分页的参数
		// Page<XhCreditAudit> page = new Page<XhCreditAudit>();
		JdbcPage page = new JdbcPage();
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
		// 封装查询条件
		// 证件类型
		List<MateData> zjlxMateDateList = baseInfoManager.getTypeByCode("0005");
		model.addAttribute("zjlx0005", zjlxMateDateList);
		String filter_zjlx = request.getParameter("filter_zjlx");
		if (null != filter_zjlx && !"".equals(filter_zjlx)) {
			model.addAttribute("zjlx", filter_zjlx);
		}

		List<City> province = baseInfoManager.getSuggestCity("0");
		request.setAttribute("province", province);

		Map<String, Object> map = WebUtils.getParametersStartingWith(request,
				"filter_");
		Employee emp = baseInfoManager.getUserEmployee();
		String loginName = operator.getCtiCode();
		map.put("loginName", loginName);
		map.put("org_id", emp.getOrgani().getId());
		List<Map<String, Object>> listJksq = xhjksqManager.searchXhJksq(
				"queryJksqList", map, page);
		model.addAttribute("listJksq", listJksq);
		model.addAttribute("page", page);

		model.addAttribute("filter_jkrxm", request.getParameter("filter_jkrxm"));
		model.addAttribute("filter_telephone",
				request.getParameter("filter_telephone"));
		model.addAttribute("filter_zjhm", request.getParameter("filter_zjhm"));
		model.addAttribute("filter_jkType",
				request.getParameter("filter_jkType"));
		model.addAttribute("filter_state", request.getParameter("filter_state"));
		model.addAttribute("filter_province",
				request.getParameter("filter_province"));
		model.addAttribute("filter_city", request.getParameter("filter_city"));
		String filter_province = request.getParameter("filter_province");
		if (null != filter_province && !"".equals(filter_province)) {
			List<City> city = baseInfoManager.getSuggestCity(filter_province);
			request.setAttribute("city", city);
		}
		model.addAttribute("filter_teamName",
				request.getParameter("filter_teamName"));
		model.addAttribute("filter_saleName",
				request.getParameter("filter_saleName"));
		return "jksq/jkrxycsList";

	}

	// 初审新增
	@RequestMapping(value = "/addJkrxycs")
	public String addJkrxyfs(HttpServletRequest request,
			HttpServletResponse response, Model model) {

		return "jksq/jkrxycsInput";
	}

	/**
	 * 借款人信用复审-借款人信用复审页
	 * 
	 * @param cjrxx
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/listJksqxyfs")
	public String listjkrsyfs(HttpServletRequest request, Model model) {
		// 得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		if (operator == null) {
			return "redirect:../../login";
		}
		// 处理分页的参数
		// Page<XhCreditAudit> page = new Page<XhCreditAudit>();
		JdbcPage page = new JdbcPage();
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
		// 封装查询条件
		// 证件类型
		List<MateData> zjlxMateDateList = baseInfoManager.getTypeByCode("0005");
		model.addAttribute("zjlx0005", zjlxMateDateList);
		String filter_zjlx = request.getParameter("filter_zjlx");
		if (null != filter_zjlx && !"".equals(filter_zjlx)) {
			model.addAttribute("zjlx", filter_zjlx);
		}

		List<City> province = baseInfoManager.getSuggestCity("0");
		request.setAttribute("province", province);

		Map<String, Object> map = WebUtils.getParametersStartingWith(request,
				"filter_");
		Employee emp = baseInfoManager.getUserEmployee();
		String loginName = operator.getCtiCode();
		map.put("loginName", loginName);
		map.put("org_id", emp.getOrgani().getId());
		List<Map<String, Object>> listJksq = xhjksqManager.searchXhJksq(
				"queryJksqList", map, page);
		model.addAttribute("listJksq", listJksq);
		model.addAttribute("page", page);

		model.addAttribute("filter_jkrxm", request.getParameter("filter_jkrxm"));
		model.addAttribute("filter_telephone",
				request.getParameter("filter_telephone"));
		model.addAttribute("filter_zjhm", request.getParameter("filter_zjhm"));
		model.addAttribute("filter_jkType",
				request.getParameter("filter_jkType"));
		model.addAttribute("filter_state", request.getParameter("filter_state"));
		model.addAttribute("filter_province",
				request.getParameter("filter_province"));
		model.addAttribute("filter_city", request.getParameter("filter_city"));
		String filter_province = request.getParameter("filter_province");
		if (null != filter_province && !"".equals(filter_province)) {
			List<City> city = baseInfoManager.getSuggestCity(filter_province);
			request.setAttribute("city", city);
		}
		model.addAttribute("filter_teamName",
				request.getParameter("filter_teamName"));
		model.addAttribute("filter_saleName",
				request.getParameter("filter_saleName"));

		return "jksq/jkrxyfsList";
	}

	// 复审新增
	@RequestMapping(value = "/addJkrxyfs")
	public String addJkrxycs(HttpServletRequest request,
			HttpServletResponse response, Model model) {

		return "jksq/jkrxyfsInput";
	}

	/**
	 * 借款人信用终审-借款人信用终审页
	 * 
	 * @param cjrxx
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/listJksqxyzs")
	public String listjkrsyzs(HttpServletRequest request, Model model) {
		// 得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		if (operator == null) {
			return "redirect:../../login";
		}
		// 处理分页的参数
		// Page<XhCreditAudit> page = new Page<XhCreditAudit>();
		JdbcPage page = new JdbcPage();
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
		// 封装查询条件
		// 证件类型
		List<MateData> zjlxMateDateList = baseInfoManager.getTypeByCode("0005");
		model.addAttribute("zjlx0005", zjlxMateDateList);
		String filter_zjlx = request.getParameter("filter_zjlx");
		if (null != filter_zjlx && !"".equals(filter_zjlx)) {
			model.addAttribute("zjlx", filter_zjlx);
		}

		List<City> province = baseInfoManager.getSuggestCity("0");
		request.setAttribute("province", province);

		Map<String, Object> map = WebUtils.getParametersStartingWith(request,
				"filter_");
		Employee emp = baseInfoManager.getUserEmployee();
		String loginName = operator.getCtiCode();
		map.put("loginName", loginName);
		map.put("org_id", emp.getOrgani().getId());
		List<Map<String, Object>> listJksq = xhjksqManager.searchXhJksq(
				"queryJksqList", map, page);
		model.addAttribute("listJksq", listJksq);
		model.addAttribute("page", page);

		model.addAttribute("filter_jkrxm", request.getParameter("filter_jkrxm"));
		model.addAttribute("filter_telephone",
				request.getParameter("filter_telephone"));
		model.addAttribute("filter_zjhm", request.getParameter("filter_zjhm"));
		model.addAttribute("filter_jkType",
				request.getParameter("filter_jkType"));
		model.addAttribute("filter_state", request.getParameter("filter_state"));
		model.addAttribute("filter_province",
				request.getParameter("filter_province"));
		model.addAttribute("filter_city", request.getParameter("filter_city"));
		String filter_province = request.getParameter("filter_province");
		if (null != filter_province && !"".equals(filter_province)) {
			List<City> city = baseInfoManager.getSuggestCity(filter_province);
			request.setAttribute("city", city);
		}
		model.addAttribute("filter_teamName",
				request.getParameter("filter_teamName"));
		model.addAttribute("filter_saleName",
				request.getParameter("filter_saleName"));
		return "jksq/jkrxyzsList";

	}

	/**
	 * 查看已上传文件
	 * 
	 * @param Id
	 *            借款申请ID
	 * @param upFlag
	 *            上传文件类型
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/seeUpLoadFile/{Id},{upFlag}")
	public String seeUpLoadFile(@PathVariable Long Id,
			@PathVariable String upFlag, HttpServletRequest request,
			HttpServletResponse response, Model model) {

		if (null != upFlag && !"".equals(upFlag)) {
			String[] upflag = upFlag.split(":");
			for (int i = 0; i < upflag.length; i++) {
				if ("1".equals(upflag[i].trim())) {
					List<XhUploadFiles> files = baseInfoManager.seeUpLoadFile(
							Id, upflag[i]);
					model.addAttribute("first", files);
				} else if ("2".equals(upflag[i].trim())) {
					List<XhUploadFiles> files = baseInfoManager.seeUpLoadFile(
							Id, upflag[i]);
					model.addAttribute("second", files);
				} else if ("3".equals(upflag[i].trim())) {
					List<XhUploadFiles> files = baseInfoManager.seeUpLoadFile(
							Id, upflag[i]);
					model.addAttribute("last", files);
				} else if ("0".equals(upflag[i].trim())) {// 签约
					List<XhUploadFiles> files = baseInfoManager.seeUpLoadFile(
							Id, upflag[i]);
					model.addAttribute("signed", files);
				} else if ("sx".equals(upflag[i].trim())) {// 授信
					upflag[i] = "授信";
					List<XhUploadFiles> files = baseInfoManager.seeUpLoadFile(
							Id, upflag[i]);
					model.addAttribute("accredited", files);
				}else if ("wf".equals(upflag[i].trim())) {// 外访
					upflag[i] = "外访";
					List<XhUploadFiles> files = baseInfoManager.seeUpLoadFile
							( Id, upflag[i]);
					model.addAttribute("waifang", files);
				}
			}
		}
		// List<XhUploadFiles> files = baseInfoManager.seeUpLoadFile(Id,null);
		// model.addAttribute("files", files);
		return "jksq/seeUploadFile";
	}
	
	@RequestMapping(value = "/listJksqExpire")
	public String listJksqExpire(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		// 得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		if (operator == null) {
			return "redirect:top.login";
		}
		JdbcPage page = new JdbcPage();
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
		// 借款申请状态
		Map<String, Object> filter = new HashMap<String, Object>();
		filter.put("attrType", "2");
		filter.put("minValue", "100");
		filter.put("maxValue", "103");
		List<Attr> attrList = baseInfoManager.getAttrList(filter);
		request.setAttribute("attrList", attrList);

		// 借款类型
		Map<String, Object> filter2 = new HashMap<String, Object>();
		filter2.put("attrType", "3");
		List<Attr> attrList2 = baseInfoManager.getAttrList(filter2);
		request.setAttribute("jkTypeList", attrList2);

		List<City> province = baseInfoManager.getSuggestCity("0");
		request.setAttribute("province", province);

		Map<String, Object> map = WebUtils.getParametersStartingWith(request,
				"filter_");
		Employee emp = baseInfoManager.getUserEmployee();
		String loginName = operator.getCtiCode();
		map.put("loginName", loginName);
		map.put("emp", emp);
		if(map.get("state_injksp") == null){
			map.put("state_injksp", "61,100,101,102");
		}
		
		// 贷款标志
		String backup01 = request.getParameter("type");
		map.put("type", backup01);
		map.put("backup01", backup01);
		List<Map<String, Object>> listJksq = xhjksqManager.searchXhJksqExpire("queryJksqExpireList", map, page);
		model.addAttribute("listJksq", listJksq);
		model.addAttribute("page", page);

		model.addAttribute("organiId", request.getParameter("filter_organi.id"));
	    model.addAttribute("organiName", request.getParameter("filter_organi.name"));
		String filter_province = request.getParameter("filter_province");
		if (null != filter_province && !"".equals(filter_province)) {
			List<City> city = baseInfoManager.getSuggestCity(filter_province);
			request.setAttribute("city", city);
		}
		model.addAttribute("map", map);

		return "jksq/jksqExpireList";
	}
	
	@RequestMapping(value="/jksqSettle/{Id}")
	public String delUser(@PathVariable Long Id, HttpServletRequest request, HttpServletResponse response){
		String state = request.getParameter("state");
		XhJksq jksq = xhjksqManager.getXhJksq(Id);
		jksq.setState(state);
		xhjksqManager.saveXhJksq(jksq);
		DwzResult success = new DwzResult("200","操作成功","rel_jksqExpireList","","","");
		ServletUtils.renderJson(response, success);
		return null;
	}
	
	@RequestMapping(value = "/jksqLxr/{Id}")
	public ModelAndView exportJjdj(@PathVariable Long Id,HttpServletRequest request,
			HttpServletResponse response){
		// 得到当前登录用户 searchXhZqtjForDown
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		String mouFilePath2 = InitSetupListener.filePath + "excel"
				+ File.separator;
		Map<String, Object> map = WebUtils.getParametersStartingWith(request,
				"filter_");
		// 过滤查询内容，所需条件 ----开始
		String loginName = operator.getCtiCode();
		map.put("loginName", loginName);
		// 过滤查询内容，所需条件 ----结束
		
		String fileName = "";
		String filePath = "";
		Date createTime = new Date();
		XhJksq xhjksq=xhjksqManager.getXhJksq(Id);
		/*
		String gsdq = String.valueOf(map.get("gsdq"));
		if ("0021".equals(gsdq)) {
			fileName = "000191400200580_S022"
					+ DateUtils.format(createTime, "yyyyMMdd") + "_0001好易联.xls";
		} else {
		*/
			fileName = xhjksq.getJkrxm() +"_电话申请"+ DateUtils.format(createTime, "yyyyMMdd")
					+ ".xls";
		//}
		filePath = mouFilePath2 + fileName;
		
		xhjksqManager.exportJksqlxr(filePath, Id);
		
		
		response.setContentType("APPLICATION/OCTET-STREAM");

		FileUtil.downLoad(filePath, fileName, request, response);
		System.out.println("删除单个文件===>" + filePath);
		FileUtil.deleteFile(filePath);
		System.out.println("删除单个文件   成功===>" + filePath);

		return null;
	}

	@Autowired
	public void setBaseInfoManager(BaseInfoManager baseInfoManager) {
		this.baseInfoManager = baseInfoManager;
	}

	@Autowired
	public void setXhjksqManager(XhJksqManager xhjksqManager) {
		this.xhjksqManager = xhjksqManager;
	}

	@Autowired
	public void setTempletManager(TempletManager templetManager) {
		this.templetManager = templetManager;
	}

	@Autowired
	public void setDksqManager(DksqManager dksqManager) {
		this.dksqManager = dksqManager;
	}

}

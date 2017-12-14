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

import cn.com.cucsi.app.entity.baseinfo.MiddleMan;
import cn.com.cucsi.app.entity.xhcf.XhHouseLoanApply;
import cn.com.cucsi.app.entity.xhcf.XhHouseLoanAuditInfo;
import cn.com.cucsi.app.entity.xhcf.XhIpcApply;
import cn.com.cucsi.app.entity.xhcf.XhIpcConstract;
import cn.com.cucsi.app.entity.xhcf.XhJkht;
import cn.com.cucsi.app.service.baseinfo.BaseInfoManager;
import cn.com.cucsi.app.service.xhcf.XhIpcApplyManager;
import cn.com.cucsi.app.service.xhcf.XhIpcConstractManager;
import cn.com.cucsi.app.service.xhcf.XhJkhtManager;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.web.DwzResult;
import cn.com.cucsi.core.web.ServletUtils;

@Controller
@RequestMapping(value = "/ipc")
public class XhIpcConstractController {

    @Autowired
	private XhJkhtManager jkhtManager;
	
    @Autowired
	private BaseInfoManager baseInfoManager;

    @Autowired
	private XhIpcApplyManager xhIpcApplyManager;
	
    @Autowired
	private XhIpcConstractManager xhIpcConstractManager;


	public void setXhIpcConstractManager(
			XhIpcConstractManager xhIpcConstractManager) {
		this.xhIpcConstractManager = xhIpcConstractManager;
	}

	@RequestMapping(value = "/listXhIpcConstract")
	public String listXhIpcConstract(HttpServletRequest request, Model model) {
		// 处理分页的参数
		Page<XhIpcConstract> page = new Page<XhIpcConstract>();
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

		xhIpcConstractManager.searchXhIpcConstract(page, map);

		model.addAttribute("page", page);
		model.addAttribute("map", map);
		return "ipc/xhIpcConstractIndex";

	}

	@RequestMapping(value = "/saveXhIpcConstract", method = RequestMethod.POST)
	public String saveXhIpcConstract(
			@ModelAttribute("xhIpcConstract") XhIpcConstract xhIpcConstract,
			HttpServletRequest request, HttpServletResponse response) {
		
		
		String Id = request.getParameter("apply_id");
		XhIpcApply xhIpcApply = xhIpcApplyManager
				.getXhIpcApply(Long.parseLong(Id));
		
		xhIpcConstract.setIpcApply(xhIpcApply);
	
		String jkhtId = request.getParameter("jkht_id");
		if (null != jkhtId && !"".equals(jkhtId)) {
			XhJkht jkht = jkhtManager.getXhJkht(Long.parseLong(jkhtId));
			xhIpcConstract.setJkht(jkht);
		}
		
		

		xhIpcConstractManager.saveXhIpcConstract(xhIpcConstract);

		DwzResult success = new DwzResult("200", "保存成功",
				"rel_listXhIpcApply", "", "closeCurrent", "");
		ServletUtils.renderJson(response, success);

		return null;
	}

	@RequestMapping(value = "/addXhIpcConstract/{Id}", method = RequestMethod.GET)
	public ModelAndView add(@PathVariable Long Id, Model model) {
		XhIpcApply xhIpcApply = xhIpcApplyManager.getXhIpcApply(Id);
		model.addAttribute("xhIpcApply", xhIpcApply);
		return new ModelAndView("ipc/xhIpcConstractInput", "xhIpcConstract",
				new XhIpcConstract());
	}

	@RequestMapping(value = "/editXhIpcConstract/{Id}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable Long Id, Model model) {
		

		XhIpcConstract xhIpcConstract = xhIpcConstractManager.getXhIpcConstract(Id);
		
		XhIpcApply xhIpcApply = xhIpcConstract.getIpcApply();
		
		if(xhIpcConstract.getMiddlemanId() != null){
		    MiddleMan man = baseInfoManager.getMiddleMan(xhIpcConstract.getMiddlemanId());
			model.addAttribute("middleManName",man != null ? man.getMiddleManName() : "");
		}
		model.addAttribute("xhIpcApply", xhIpcApply);
		
		
		return new ModelAndView("ipc/xhIpcConstractInput", "xhIpcConstract",
				xhIpcConstract);
	}
	
	@RequestMapping(value = "/lookXhIpcConstract/{Id}", method = RequestMethod.GET)
	public ModelAndView look(@PathVariable Long Id, Model model) {
		

		XhIpcConstract xhIpcConstract = xhIpcConstractManager.getXhIpcConstract(Id);
		
		XhIpcApply xhIpcApply = xhIpcConstract.getIpcApply();
		
		if(xhIpcConstract.getMiddlemanId() != null){
		    MiddleMan man = baseInfoManager.getMiddleMan(xhIpcConstract.getMiddlemanId());
			model.addAttribute("middleManName",man != null ? man.getMiddleManName() : "");
		}
		model.addAttribute("xhIpcApply", xhIpcApply);
		
		
		return new ModelAndView("ipc/xhIpcConstractLook", "xhIpcConstract",
				xhIpcConstract);
	}

}

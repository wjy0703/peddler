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

import cn.com.cucsi.app.entity.xhcf.XhIpcApply;
import cn.com.cucsi.app.entity.xhcf.XhJksq;
import cn.com.cucsi.app.service.loan.XhJksqManager;
import cn.com.cucsi.app.service.xhcf.XhIpcApplyManager;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.web.DwzResult;
import cn.com.cucsi.core.web.ServletUtils;

@Controller
@RequestMapping(value="/ipc")
public class XhIpcApplyController {
	private XhIpcApplyManager xhIpcApplyManager;
	
	private XhJksqManager xhJksqManager;
	
	@Autowired
	public void setXhJksqManager(XhJksqManager xhJksqManager) {
		this.xhJksqManager = xhJksqManager;
	}
	
	@Autowired
	public void setXhIpcApplyManager(XhIpcApplyManager xhIpcApplyManager) {
		this.xhIpcApplyManager = xhIpcApplyManager;
	}
	
	@RequestMapping(value="/listXhIpcApply")
	public String listXhIpcApply(HttpServletRequest request, Model model){
		// 处理分页的参数
		Page<XhIpcApply> page = new Page<XhIpcApply>();
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
		
		Map<String, Object> map = ServletUtils.getParametersStartingWith2(request, "filter_");		
		
		xhIpcApplyManager.searchXhIpcApply(page, map);
	
		model.addAttribute("page", page);
		model.addAttribute("map", map);
		return "ipc/xhIpcApplyIndex";
		
	}
	
	@RequestMapping(value="/saveXhIpcApply",method=RequestMethod.POST)
	public String saveXhIpcApply(@ModelAttribute("xhIpcApply") XhIpcApply xhIpcApply, HttpServletRequest request, HttpServletResponse response){
		
		
		XhJksq jksq = null;
		if (request.getParameter("jksqId") != null
				&& !request.getParameter("jksqId").equals("")) {
			jksq = xhJksqManager.getXhJksq(Long.parseLong(request
					.getParameter("jksqId")));
			xhIpcApply.setLoanApply(jksq);
		}
		xhIpcApply.setState("0");
		xhIpcApplyManager.saveXhIpcApply(xhIpcApply);

		DwzResult success = new DwzResult("200","保存成功","rel_listXhIpcApply","","closeCurrent","");
		ServletUtils.renderJson(response, success);
		
		return null;
	}
	
	@RequestMapping(value="/addXhIpcApply", method=RequestMethod.GET)
	public ModelAndView add(){
		return new ModelAndView("ipc/xhIpcApplyInput", "xhIpcApply", new XhIpcApply());
	}
	
	@RequestMapping(value="/editXhIpcApply/{Id}", method=RequestMethod.GET)
	public ModelAndView edit(@PathVariable Long Id){
		XhIpcApply xhIpcApply = xhIpcApplyManager.getXhIpcApply(Id);
		return new ModelAndView("ipc/xhIpcApplyInput", "xhIpcApply", xhIpcApply);
	}

	
}

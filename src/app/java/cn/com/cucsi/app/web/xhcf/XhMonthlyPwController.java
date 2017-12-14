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

import cn.com.cucsi.app.entity.xhcf.XhMonthlyPw;
import cn.com.cucsi.app.service.xhcf.XhMonthlyPwManager;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.web.DwzResult;
import cn.com.cucsi.core.web.ServletUtils;

@Controller
@RequestMapping(value="/xhMonthlyPw")
public class XhMonthlyPwController {
	private XhMonthlyPwManager xhMonthlyPwManager;
	@Autowired
	public void setXhMonthlyPwManager(XhMonthlyPwManager xhMonthlyPwManager) {
		this.xhMonthlyPwManager = xhMonthlyPwManager;
	}
	
	@RequestMapping(value="/listXhMonthlyPw")
	public String listXhMonthlyPw(HttpServletRequest request, Model model){
		// 处理分页的参数
		Page<XhMonthlyPw> page = new Page<XhMonthlyPw>();
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
		
		xhMonthlyPwManager.searchXhMonthlyPw(page, map);
	
		model.addAttribute("page", page);
		model.addAttribute("map", map);
		return "folder/xhMonthlyPwIndex";
		
	}
	
	@RequestMapping(value="/saveXhMonthlyPw",method=RequestMethod.POST)
	public String saveXhMonthlyPw(@ModelAttribute("xhMonthlyPw") XhMonthlyPw xhMonthlyPw, HttpServletRequest request, HttpServletResponse response){
		
		xhMonthlyPwManager.saveXhMonthlyPw(xhMonthlyPw);

		DwzResult success = new DwzResult("200","保存成功","rel_listXhMonthlyPw","","closeCurrent","");
		ServletUtils.renderJson(response, success);
		
		return null;
	}
	
	@RequestMapping(value="/addXhMonthlyPw", method=RequestMethod.GET)
	public ModelAndView add(){
		return new ModelAndView("folder/xhMonthlyPwInput", "xhMonthlyPw", new XhMonthlyPw());
	}
	
	@RequestMapping(value="/editXhMonthlyPw/{Id}", method=RequestMethod.GET)
	public ModelAndView edit(@PathVariable Long Id){
		XhMonthlyPw xhMonthlyPw = xhMonthlyPwManager.getXhMonthlyPw(Id);
		return new ModelAndView("folder/xhMonthlyPwInput", "xhMonthlyPw", xhMonthlyPw);
	}

	@RequestMapping(value="/delXhMonthlyPw/{Id}")
	public String delUser(@PathVariable Long Id, HttpServletResponse response){
		xhMonthlyPwManager.deleteXhMonthlyPw(Id);
		DwzResult success = new DwzResult("200","删除成功","rel_listXhMonthlyPw","","","");
		ServletUtils.renderJson(response, success);
		return null;
	}
	
	@RequestMapping(value="/batchdelXhMonthlyPw")
	public String batchDelUser(HttpServletRequest request, HttpServletResponse response){
		String ids = request.getParameter("ids");
		String[] Ids = ids.split(",");
		boolean isSuccess = xhMonthlyPwManager.batchDelXhMonthlyPw(Ids);
		
		DwzResult success = null;
		if (isSuccess){
			success = new DwzResult("200","删除成功","rel_listXhMonthlyPw","","","");
		}
		else{
			success = new DwzResult("300","删除失败","rel_listXhMonthlyPw","","","");
		}
		ServletUtils.renderJson(response, success);
		return null;
	}
}

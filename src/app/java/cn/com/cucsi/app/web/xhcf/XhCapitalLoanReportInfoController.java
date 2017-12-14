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

import cn.com.cucsi.app.entity.xhcf.XhCapitalLoanReportInfo;
import cn.com.cucsi.app.service.xhcf.XhCapitalLoanReportInfoManager;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.web.DwzResult;
import cn.com.cucsi.core.web.ServletUtils;

@Controller
@RequestMapping(value="/xhCapitalLoanReportInfo")
public class XhCapitalLoanReportInfoController {
	private XhCapitalLoanReportInfoManager xhCapitalLoanReportInfoManager;
	@Autowired
	public void setXhCapitalLoanReportInfoManager(XhCapitalLoanReportInfoManager xhCapitalLoanReportInfoManager) {
		this.xhCapitalLoanReportInfoManager = xhCapitalLoanReportInfoManager;
	}
	
	@RequestMapping(value="/listXhCapitalLoanReportInfo")
	public String listXhCapitalLoanReportInfo(HttpServletRequest request, Model model){
		// 处理分页的参数
		Page<XhCapitalLoanReportInfo> page = new Page<XhCapitalLoanReportInfo>();
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
		
		xhCapitalLoanReportInfoManager.searchXhCapitalLoanReportInfo(page, map);
	
		model.addAttribute("page", page);
		model.addAttribute("map", map);
		return "folder/xhCapitalLoanReportInfoIndex";
		
	}
	
	@RequestMapping(value="/saveXhCapitalLoanReportInfo",method=RequestMethod.POST)
	public String saveXhCapitalLoanReportInfo(@ModelAttribute("xhCapitalLoanReportInfo") XhCapitalLoanReportInfo xhCapitalLoanReportInfo, HttpServletRequest request, HttpServletResponse response){
		
		xhCapitalLoanReportInfoManager.saveXhCapitalLoanReportInfo(xhCapitalLoanReportInfo);

		DwzResult success = new DwzResult("200","保存成功","rel_listXhCapitalLoanReportInfo","","closeCurrent","");
		ServletUtils.renderJson(response, success);
		
		return null;
	}
	
	@RequestMapping(value="/addXhCapitalLoanReportInfo", method=RequestMethod.GET)
	public ModelAndView add(){
		return new ModelAndView("folder/xhCapitalLoanReportInfoInput", "xhCapitalLoanReportInfo", new XhCapitalLoanReportInfo());
	}
	
	@RequestMapping(value="/editXhCapitalLoanReportInfo/{Id}", method=RequestMethod.GET)
	public ModelAndView edit(@PathVariable Long Id){
		XhCapitalLoanReportInfo xhCapitalLoanReportInfo = xhCapitalLoanReportInfoManager.getXhCapitalLoanReportInfo(Id);
		return new ModelAndView("folder/xhCapitalLoanReportInfoInput", "xhCapitalLoanReportInfo", xhCapitalLoanReportInfo);
	}

	@RequestMapping(value="/delXhCapitalLoanReportInfo/{Id}")
	public String delUser(@PathVariable Long Id, HttpServletResponse response){
		xhCapitalLoanReportInfoManager.deleteXhCapitalLoanReportInfo(Id);
		DwzResult success = new DwzResult("200","删除成功","rel_listXhCapitalLoanReportInfo","","","");
		ServletUtils.renderJson(response, success);
		return null;
	}
	
	@RequestMapping(value="/batchdelXhCapitalLoanReportInfo")
	public String batchDelUser(HttpServletRequest request, HttpServletResponse response){
		String ids = request.getParameter("ids");
		String[] Ids = ids.split(",");
		boolean isSuccess = xhCapitalLoanReportInfoManager.batchDelXhCapitalLoanReportInfo(Ids);
		
		DwzResult success = null;
		if (isSuccess){
			success = new DwzResult("200","删除成功","rel_listXhCapitalLoanReportInfo","","","");
		}
		else{
			success = new DwzResult("300","删除失败","rel_listXhCapitalLoanReportInfo","","","");
		}
		ServletUtils.renderJson(response, success);
		return null;
	}
}

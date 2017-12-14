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

import cn.com.cucsi.app.entity.xhcf.XhSystemParameter;
import cn.com.cucsi.app.service.xhcf.XhSystemParameterManager;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.web.DwzResult;
import cn.com.cucsi.core.web.ServletUtils;

@Controller
@RequestMapping(value="/xhSystemParameter")
public class XhSystemParameterController {
	private XhSystemParameterManager xhSystemParameterManager;
	@Autowired
	public void setXhSystemParameterManager(XhSystemParameterManager xhSystemParameterManager) {
		this.xhSystemParameterManager = xhSystemParameterManager;
	}
	
	@RequestMapping(value="/listXhSystemParameter")
	public String listXhSystemParameter(HttpServletRequest request, Model model){
		// 处理分页的参数
		Page<XhSystemParameter> page = new Page<XhSystemParameter>();
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
		
		Map<String, Object> params = ServletUtils.getParametersStartingWith2(request, "filter_");		
		
		xhSystemParameterManager.searchXhSystemParameter(page, params);
	
		model.addAttribute("page", page);
		model.addAttribute("map", params);
		return "baseinfo/xhSystemParameterIndex";
		
	}
	
	@RequestMapping(value="/saveXhSystemParameter",method=RequestMethod.POST)
	public String saveXhSystemParameter(@ModelAttribute("xhSystemParameter") XhSystemParameter xhSystemParameter, HttpServletRequest request, HttpServletResponse response){
		
		xhSystemParameterManager.saveXhSystemParameter(xhSystemParameter);

		DwzResult success = new DwzResult("200","保存成功","rel_listXhSystemParameter","","closeCurrent","");
		ServletUtils.renderJson(response, success);
		
		return null;
	}
	
	@RequestMapping(value="/addXhSystemParameter", method=RequestMethod.GET)
	public ModelAndView add(){
		return new ModelAndView("baseinfo/xhSystemParameterInput", "xhSystemParameter", new XhSystemParameter());
	}
	
	@RequestMapping(value="/editXhSystemParameter/{Id}", method=RequestMethod.GET)
	public ModelAndView edit(@PathVariable Long Id){
		XhSystemParameter xhSystemParameter = xhSystemParameterManager.getXhSystemParameter(Id);
		return new ModelAndView("baseinfo/xhSystemParameterInput", "xhSystemParameter", xhSystemParameter);
	}

	@RequestMapping(value="/delXhSystemParameter/{Id}")
	public String delUser(@PathVariable Long Id, HttpServletResponse response){
		xhSystemParameterManager.deleteXhSystemParameter(Id);
		DwzResult success = new DwzResult("200","删除成功","rel_listXhSystemParameter","","","");
		ServletUtils.renderJson(response, success);
		return null;
	}
	
	@RequestMapping(value="/batchdelXhSystemParameter")
	public String batchDelUser(HttpServletRequest request, HttpServletResponse response){
		String ids = request.getParameter("ids");
		String[] Ids = ids.split(",");
		boolean isSuccess = xhSystemParameterManager.batchDelXhSystemParameter(Ids);
		
		DwzResult success = null;
		if (isSuccess){
			success = new DwzResult("200","删除成功","rel_listXhSystemParameter","","","");
		}
		else{
			success = new DwzResult("300","删除失败","rel_listXhSystemParameter","","","");
		}
		ServletUtils.renderJson(response, success);
		return null;
	}
}

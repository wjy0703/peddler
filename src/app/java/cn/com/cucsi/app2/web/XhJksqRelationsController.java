package cn.com.cucsi.app2.web;

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

import cn.com.cucsi.app2.entity.xhcf.XhJksqRelations;
import cn.com.cucsi.app2.service.xhcf.XhJksqRelationsManager;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.web.DwzResult;
import cn.com.cucsi.core.web.ServletUtils;

@Controller
@RequestMapping(value="/xhJksqRelations")
public class XhJksqRelationsController {
	private XhJksqRelationsManager xhJksqRelationsManager;
	@Autowired
	public void setXhJksqRelationsManager(XhJksqRelationsManager xhJksqRelationsManager) {
		this.xhJksqRelationsManager = xhJksqRelationsManager;
	}
	
	@RequestMapping(value="/listXhJksqRelations")
	public String listXhJksqRelations(HttpServletRequest request, Model model){
		// 处理分页的参数
		Page<XhJksqRelations> page = new Page<XhJksqRelations>();
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
		
		xhJksqRelationsManager.searchXhJksqRelations(page, map);
	
		model.addAttribute("page", page);
		model.addAttribute("map", map);
		return "folder/xhJksqRelationsIndex";
		
	}
	
	@RequestMapping(value="/saveXhJksqRelations",method=RequestMethod.POST)
	public String saveXhJksqRelations(@ModelAttribute("xhJksqRelations") XhJksqRelations xhJksqRelations, HttpServletRequest request, HttpServletResponse response){
		
		xhJksqRelationsManager.saveXhJksqRelations(xhJksqRelations);

		DwzResult success = new DwzResult("200","保存成功","rel_listXhJksqRelations","","closeCurrent","");
		ServletUtils.renderJson(response, success);
		
		return null;
	}
	
	@RequestMapping(value="/addXhJksqRelations", method=RequestMethod.GET)
	public ModelAndView add(){
		return new ModelAndView("folder/xhJksqRelationsInput", "xhJksqRelations", new XhJksqRelations());
	}
	
	@RequestMapping(value="/editXhJksqRelations/{Id}", method=RequestMethod.GET)
	public ModelAndView edit(@PathVariable Long Id){
		XhJksqRelations xhJksqRelations = xhJksqRelationsManager.getXhJksqRelations(Id);
		return new ModelAndView("folder/xhJksqRelationsInput", "xhJksqRelations", xhJksqRelations);
	}

	@RequestMapping(value="/delXhJksqRelations/{Id}")
	public String delUser(@PathVariable Long Id, HttpServletResponse response){
		xhJksqRelationsManager.deleteXhJksqRelations(Id);
		DwzResult success = new DwzResult("200","删除成功","rel_listXhJksqRelations","","","");
		ServletUtils.renderJson(response, success);
		return null;
	}
	
	@RequestMapping(value="/batchdelXhJksqRelations")
	public String batchDelUser(HttpServletRequest request, HttpServletResponse response){
		String ids = request.getParameter("ids");
		String[] Ids = ids.split(",");
		boolean isSuccess = xhJksqRelationsManager.batchDelXhJksqRelations(Ids);
		
		DwzResult success = null;
		if (isSuccess){
			success = new DwzResult("200","删除成功","rel_listXhJksqRelations","","","");
		}
		else{
			success = new DwzResult("300","删除失败","rel_listXhJksqRelations","","","");
		}
		ServletUtils.renderJson(response, success);
		return null;
	}
	
	@RequestMapping(value = "/oracleDeleteRelation/{Id}", method = RequestMethod.POST)
    public void saveCreditsChange(@PathVariable("Id") Long Id, HttpServletRequest request, HttpServletResponse response) {
    	xhJksqRelationsManager.deleteXhJksqRelations(Id);
        ServletUtils.renderJson(response, "1");
        
    }
}

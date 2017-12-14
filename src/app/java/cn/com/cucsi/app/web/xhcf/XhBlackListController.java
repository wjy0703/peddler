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

import cn.com.cucsi.app.entity.xhcf.XhBlackList;
import cn.com.cucsi.app.service.xhcf.XhBlackListManager;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.web.DwzResult;
import cn.com.cucsi.core.web.ServletUtils;

@Controller
@RequestMapping(value="/xhBlackList")
public class XhBlackListController {
	private XhBlackListManager xhBlackListManager;
	@Autowired
	public void setXhBlackListManager(XhBlackListManager xhBlackListManager) {
		this.xhBlackListManager = xhBlackListManager;
	}
	
	@RequestMapping(value="/listXhBlackList")
	public String listXhBlackList(HttpServletRequest request, Model model){
		// 处理分页的参数
		Page<XhBlackList> page = new Page<XhBlackList>();
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
		
		xhBlackListManager.searchXhBlackList(page, map);
	
		model.addAttribute("page", page);
		model.addAttribute("map", map);
		return "folder/xhBlackListIndex";
		
	}
	
	@RequestMapping(value="/saveXhBlackList",method=RequestMethod.POST)
	public String saveXhBlackList(@ModelAttribute("xhBlackList") XhBlackList xhBlackList, HttpServletRequest request, HttpServletResponse response){
		
		xhBlackListManager.saveXhBlackList(xhBlackList);

		DwzResult success = new DwzResult("200","保存成功","rel_listXhBlackList","","closeCurrent","");
		ServletUtils.renderJson(response, success);
		
		return null;
	}
	
	@RequestMapping(value="/addXhBlackList", method=RequestMethod.GET)
	public ModelAndView add(){
		return new ModelAndView("folder/xhBlackListInput", "xhBlackList", new XhBlackList());
	}
	
	@RequestMapping(value="/editXhBlackList/{Id}", method=RequestMethod.GET)
	public ModelAndView edit(@PathVariable Long Id){
		XhBlackList xhBlackList = xhBlackListManager.getXhBlackList(Id);
		return new ModelAndView("folder/xhBlackListInput", "xhBlackList", xhBlackList);
	}

	@RequestMapping(value="/delXhBlackList/{Id}")
	public String delUser(@PathVariable Long Id, HttpServletResponse response){
		xhBlackListManager.deleteXhBlackList(Id);
		DwzResult success = new DwzResult("200","删除成功","rel_listXhBlackList","","","");
		ServletUtils.renderJson(response, success);
		return null;
	}
	
	@RequestMapping(value="/batchdelXhBlackList")
	public String batchDelUser(HttpServletRequest request, HttpServletResponse response){
		String ids = request.getParameter("ids");
		String[] Ids = ids.split(",");
		boolean isSuccess = xhBlackListManager.batchDelXhBlackList(Ids);
		
		DwzResult success = null;
		if (isSuccess){
			success = new DwzResult("200","删除成功","rel_listXhBlackList","","","");
		}
		else{
			success = new DwzResult("300","删除失败","rel_listXhBlackList","","","");
		}
		ServletUtils.renderJson(response, success);
		return null;
	}
}

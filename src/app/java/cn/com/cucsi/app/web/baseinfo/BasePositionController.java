package cn.com.cucsi.app.web.baseinfo;



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


import cn.com.cucsi.app.entity.baseinfo.BasePosition;
import cn.com.cucsi.app.service.baseinfo.BasePositionManager;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.web.DwzResult;
import cn.com.cucsi.core.web.ServletUtils;

@Controller
@RequestMapping(value="/basePosition")
public class BasePositionController {
	private BasePositionManager basePositionManager;
	@Autowired
	public void setBasePositionManager(BasePositionManager basePositionManager) {
		this.basePositionManager = basePositionManager;
	}
	
	@RequestMapping(value="/listBasePosition")
	public String listBasePosition(HttpServletRequest request, Model model){
		// 处理分页的参数
		Page<BasePosition> page = new Page<BasePosition>();
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
		
		basePositionManager.searchBasePosition(page, map);
		
	
		model.addAttribute("page", page);
		model.addAttribute("map", map);
		return "baseinfo/basePositionIndex";
		
	}
	
	@RequestMapping(value="/saveBasePosition",method=RequestMethod.POST)
	public String saveBasePosition(@ModelAttribute("basePosition") BasePosition basePosition, HttpServletRequest request, HttpServletResponse response){
		
		basePositionManager.saveBasePosition(basePosition);

		DwzResult success = new DwzResult("200","保存成功","rel_listBasePosition","","closeCurrent","");
		ServletUtils.renderJson(response, success);
		
		return null;
	}
	
	@RequestMapping(value="/addBasePosition", method=RequestMethod.GET)
	public ModelAndView add(){
		return new ModelAndView("baseinfo/basePositionInput", "basePosition", new BasePosition());
	}
	
	@RequestMapping(value="/editBasePosition/{Id}", method=RequestMethod.GET)
	public ModelAndView edit(@PathVariable Long Id){
		BasePosition basePosition = basePositionManager.getBasePosition(Id);
		return new ModelAndView("baseinfo/basePositionInput", "basePosition", basePosition);
	}

	@RequestMapping(value="/delBasePosition/{Id}")
	public String delUser(@PathVariable Long Id, HttpServletResponse response){
		basePositionManager.deleteBasePosition(Id);
		DwzResult success = new DwzResult("200","删除成功","rel_listBasePosition","","","");
		ServletUtils.renderJson(response, success);
		return null;
	}
	
	@RequestMapping(value="/batchdelBasePosition")
	public String batchDelUser(HttpServletRequest request, HttpServletResponse response){
		String ids = request.getParameter("ids");
		String[] Ids = ids.split(",");
		boolean isSuccess = basePositionManager.batchDelBasePosition(Ids);
		
		DwzResult success = null;
		if (isSuccess){
			success = new DwzResult("200","删除成功","rel_listBasePosition","","","");
		}
		else{
			success = new DwzResult("300","删除失败","rel_listBasePosition","","","");
		}
		ServletUtils.renderJson(response, success);
		return null;
	}
}

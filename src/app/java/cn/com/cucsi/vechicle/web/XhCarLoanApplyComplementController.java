package cn.com.cucsi.vechicle.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.com.cucsi.app.web.util.RequestPageUtils;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.web.DwzResult;
import cn.com.cucsi.core.web.ServletUtils;
import cn.com.cucsi.vechicle.entity.xhcf.XhCarLoanApplyComplement;
import cn.com.cucsi.vechicle.service.XhCarLoanApplyComplementManager;

@Controller
@RequestMapping(value="/xhCarLoanApplyComplement")
public class XhCarLoanApplyComplementController {
	private XhCarLoanApplyComplementManager xhCarLoanApplyComplementManager;
	@Autowired
	public void setXhCarLoanApplyComplementManager(XhCarLoanApplyComplementManager xhCarLoanApplyComplementManager) {
		this.xhCarLoanApplyComplementManager = xhCarLoanApplyComplementManager;
	}
	
	@RequestMapping(value="/listXhCarLoanApplyComplement")
	public String listXhCarLoanApplyComplement(HttpServletRequest request, Model model){
		// 处理分页的参数
		Page<XhCarLoanApplyComplement> page = new RequestPageUtils<XhCarLoanApplyComplement>().generatePage(request);
		
		Map<String, Object> map = ServletUtils.getParametersStartingWith2(request, "filter_");		
		
		xhCarLoanApplyComplementManager.searchXhCarLoanApplyComplement(page, map);
	
		model.addAttribute("page", page);
		model.addAttribute("map", map);
		return "folder/xhCarLoanApplyComplementIndex";
		
	}
	
	@RequestMapping(value="/saveXhCarLoanApplyComplement",method=RequestMethod.POST)
	public String saveXhCarLoanApplyComplement(@ModelAttribute("xhCarLoanApplyComplement") XhCarLoanApplyComplement xhCarLoanApplyComplement, HttpServletRequest request, HttpServletResponse response){
		
		xhCarLoanApplyComplementManager.saveXhCarLoanApplyComplement(xhCarLoanApplyComplement);

		DwzResult success = new DwzResult("200","保存成功","rel_listXhCarLoanApplyComplement","","closeCurrent","");
		ServletUtils.renderJson(response, success);
		
		return null;
	}
	
	@RequestMapping(value="/addXhCarLoanApplyComplement", method=RequestMethod.GET)
	public ModelAndView add(){
		return new ModelAndView("folder/xhCarLoanApplyComplementInput", "xhCarLoanApplyComplement", new XhCarLoanApplyComplement());
	}
	
	@RequestMapping(value="/editXhCarLoanApplyComplement/{Id}", method=RequestMethod.GET)
	public ModelAndView edit(@PathVariable Long Id){
		XhCarLoanApplyComplement xhCarLoanApplyComplement = xhCarLoanApplyComplementManager.getXhCarLoanApplyComplement(Id);
		return new ModelAndView("folder/xhCarLoanApplyComplementInput", "xhCarLoanApplyComplement", xhCarLoanApplyComplement);
	}

	@RequestMapping(value="/delXhCarLoanApplyComplement/{Id}")
	public String delUser(@PathVariable Long Id, HttpServletResponse response){
		xhCarLoanApplyComplementManager.deleteXhCarLoanApplyComplement(Id);
		DwzResult success = new DwzResult("200","删除成功","rel_listXhCarLoanApplyComplement","","","");
		ServletUtils.renderJson(response, success);
		return null;
	}
	
	@RequestMapping(value="/batchdelXhCarLoanApplyComplement")
	public String batchDelUser(HttpServletRequest request, HttpServletResponse response){
		String ids = request.getParameter("ids");
		String[] Ids = ids.split(",");
		boolean isSuccess = xhCarLoanApplyComplementManager.batchDelXhCarLoanApplyComplement(Ids);
		
		DwzResult success = null;
		if (isSuccess){
			success = new DwzResult("200","删除成功","rel_listXhCarLoanApplyComplement","","","");
		}
		else{
			success = new DwzResult("300","删除失败","rel_listXhCarLoanApplyComplement","","","");
		}
		ServletUtils.renderJson(response, success);
		return null;
	}
}

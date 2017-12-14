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
import cn.com.cucsi.vechicle.entity.xhcf.XhCarMessage;
import cn.com.cucsi.vechicle.service.XhCarMessageManager;

@Controller
@RequestMapping(value="/xhCarMessage")
public class XhCarMessageController {
	private XhCarMessageManager xhCarMessageManager;
	@Autowired
	public void setXhCarMessageManager(XhCarMessageManager xhCarMessageManager) {
		this.xhCarMessageManager = xhCarMessageManager;
	}
	
	@RequestMapping(value="/listXhCarMessage")
	public String listXhCarMessage(HttpServletRequest request, Model model){
		// 处理分页的参数
		Page<XhCarMessage> page = new RequestPageUtils<XhCarMessage>().generatePage(request);
		
		Map<String, Object> map = ServletUtils.getParametersStartingWith2(request, "filter_");		
		
		xhCarMessageManager.searchXhCarMessage(page, map);
	
		model.addAttribute("page", page);
		model.addAttribute("map", map);
		return "folder/xhCarMessageIndex";
		
	}
	
	@RequestMapping(value="/saveXhCarMessage",method=RequestMethod.POST)
	public String saveXhCarMessage(@ModelAttribute("xhCarMessage") XhCarMessage xhCarMessage, HttpServletRequest request, HttpServletResponse response){
		
		xhCarMessageManager.saveXhCarMessage(xhCarMessage);

		DwzResult success = new DwzResult("200","保存成功","rel_listXhCarMessage","","closeCurrent","");
		ServletUtils.renderJson(response, success);
		
		return null;
	}
	
	@RequestMapping(value="/addXhCarMessage", method=RequestMethod.GET)
	public ModelAndView add(){
		return new ModelAndView("folder/xhCarMessageInput", "xhCarMessage", new XhCarMessage());
	}
	
	@RequestMapping(value="/editXhCarMessage/{Id}", method=RequestMethod.GET)
	public ModelAndView edit(@PathVariable Long Id){
		XhCarMessage xhCarMessage = xhCarMessageManager.getXhCarMessage(Id);
		return new ModelAndView("folder/xhCarMessageInput", "xhCarMessage", xhCarMessage);
	}

	@RequestMapping(value="/delXhCarMessage/{Id}")
	public String delUser(@PathVariable Long Id, HttpServletResponse response){
		xhCarMessageManager.deleteXhCarMessage(Id);
		DwzResult success = new DwzResult("200","删除成功","rel_listXhCarMessage","","","");
		ServletUtils.renderJson(response, success);
		return null;
	}
	
	@RequestMapping(value="/batchdelXhCarMessage")
	public String batchDelUser(HttpServletRequest request, HttpServletResponse response){
		String ids = request.getParameter("ids");
		String[] Ids = ids.split(",");
		boolean isSuccess = xhCarMessageManager.batchDelXhCarMessage(Ids);
		
		DwzResult success = null;
		if (isSuccess){
			success = new DwzResult("200","删除成功","rel_listXhCarMessage","","","");
		}
		else{
			success = new DwzResult("300","删除失败","rel_listXhCarMessage","","","");
		}
		ServletUtils.renderJson(response, success);
		return null;
	}
}

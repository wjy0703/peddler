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

import cn.com.cucsi.app.entity.xhcf.XhCapitalOverdue;
import cn.com.cucsi.app.entity.xhcf.XhJksq;
import cn.com.cucsi.app.service.xhcf.XhCapitalOverdueManager;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.web.DwzResult;
import cn.com.cucsi.core.web.ServletUtils;

@Controller
@RequestMapping(value="/xhCapitalOverdue")
public class XhCapitalOverdueController {
	private XhCapitalOverdueManager xhCapitalOverdueManager;
	@Autowired
	public void setXhCapitalOverdueManager(XhCapitalOverdueManager xhCapitalOverdueManager) {
		this.xhCapitalOverdueManager = xhCapitalOverdueManager;
	}
	
	/**
	 * 逾期管理列表 MDY 2013-08-26
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/listXhCapitalOverdue")
	public String listXhCapitalOverdue(HttpServletRequest request, Model model){
		// 处理分页的参数
		Page<XhCapitalOverdue> page = new Page<XhCapitalOverdue>();
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
		
		xhCapitalOverdueManager.searchXhCapitalOverdue(page, map);
	
		model.addAttribute("page", page);
		model.addAttribute("map", map);
		return "jsgl/xhCapitalOverdueIndex";
		
	}
	
	/**
	 * 写入逾期状态 追回、未缴、未付、坏账 MDY 2013-08-26
	 * @param Id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/setOverdueStateInfo/{Id}")
	public String setOverdueStateInfo(@PathVariable Long Id, HttpServletRequest request, HttpServletResponse response){
		String state = request.getParameter("state");
		xhCapitalOverdueManager.setOverdueStateInfo(Id, state);
		DwzResult success = new DwzResult("200","操作成功","rel_listXhCapitalOverdue","","","");
		ServletUtils.renderJson(response, success);
		return null;
	}
	
	/**
	 * 保存逾期数据 MDY 2013-08-26
	 * @param xhCapitalOverdue
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/saveXhCapitalOverdue",method=RequestMethod.POST)
	public String saveXhCapitalOverdue(@ModelAttribute("xhCapitalOverdue") XhCapitalOverdue xhCapitalOverdue, HttpServletRequest request, HttpServletResponse response){
		xhCapitalOverdueManager.saveXhCapitalOverdue(xhCapitalOverdue);
		DwzResult success = new DwzResult("200","保存成功","rel_listXhCapitalOverdue","","closeCurrent","");
		ServletUtils.renderJson(response, success);
		
		return null;
	}
	
	/**
	 * 保存挂账、减免操作MDY 2013-08-26
	 * @param xhCapitalOverdue
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/setOverdueBuyer",method=RequestMethod.POST)
	public String setOverdueBuyer(@ModelAttribute("xhCapitalOverdue") XhCapitalOverdue xhCapitalOverdue, HttpServletRequest request, HttpServletResponse response){
		String state = "200";
		String msg = "操作成功！";
		Integer res = xhCapitalOverdueManager.setOverdueBuyer(xhCapitalOverdue);
		if(res == 1){
			state = "300";
			msg = "操作失败，该记录为逾期状态，挂账金额负数为追回逾期费用！";
		}else if(res == 2){
			state = "300";
			msg = "操作失败，该记录为追回状态，挂账金额正数为追回逾期费用！";
		}
		DwzResult success = new DwzResult(state,msg,"","","closeCurrent","");
		ServletUtils.renderJson(response, success);
		
		return null;
	}
	
	@RequestMapping(value="/addXhCapitalOverdue", method=RequestMethod.GET)
	public ModelAndView add(){
		return new ModelAndView("folder/xhCapitalOverdueInput", "xhCapitalOverdue", new XhCapitalOverdue());
	}
	
	/**
	 * 初始化挂账、减免操作MDY 2013-08-26
	 * @param Id
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/editXhCapitalOverdue/{Id}", method=RequestMethod.GET)
	public String edit(@PathVariable Long Id, HttpServletRequest request, Model model){
		String type = request.getParameter("type");
		XhCapitalOverdue xhCapitalOverdue = xhCapitalOverdueManager.getXhCapitalOverdue(Id);
		model.addAttribute("xhCapitalOverdue", xhCapitalOverdue);
		model.addAttribute("type", type);
		return "jsgl/xhCapitalOverdueInput";
	}
	
	/**
	 * 冲抵操作MDY 2013-10-24
	 * @param Id
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/offsetXhCapitalOverdue/{Id}", method=RequestMethod.GET)
	public String offsetXhCapitalOverdue(@PathVariable Long Id, HttpServletRequest request, Model model){
		XhCapitalOverdue xhCapitalOverdue = xhCapitalOverdueManager.getXhCapitalOverdue(Id);
		double money = xhCapitalOverdue.getLoanContract().getYhkje();
		model.addAttribute("xhCapitalOverdue", xhCapitalOverdue);
		model.addAttribute("money", money);
		return "jsgl/offsetCapitalOverdueInput";
	}
	
	/**
	 * 冲抵逾期保存MDY 2013-10-24
	 * @param xhCapitalOverdue
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/saveOffsetOverdue",method=RequestMethod.POST)
	public String saveOffsetOverdue(@ModelAttribute("xhCapitalOverdue") XhCapitalOverdue xhCapitalOverdue, HttpServletRequest request, HttpServletResponse response){
		String[] msg = xhCapitalOverdueManager.saveOffsetOverdue(xhCapitalOverdue);
		xhCapitalOverdueManager.initOverDay(xhCapitalOverdue.getId());
		DwzResult success = new DwzResult(msg[0],msg[1],"","","closeCurrent","");
		ServletUtils.renderJson(response, success);
		
		return null;
	}
	
	/**
	 * 取消逾期操作MDY 2013-10-9
	 * @param Id
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/cancelXhCapitalOverdue/{Id}", method=RequestMethod.GET)
	public String cancelXhCapitalOverdue(@PathVariable Long Id, HttpServletRequest request, Model model){
		XhCapitalOverdue xhCapitalOverdue = xhCapitalOverdueManager.getXhCapitalOverdue(Id);
		model.addAttribute("xhCapitalOverdue", xhCapitalOverdue);
		return "jsgl/cancelxhCapitalOverdueInput";
	}
	
	/**
	 * 逾期天数调整操作MDY 2013-10-9
	 * @param Id
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/xhOverdueDay/{Id}", method=RequestMethod.GET)
	public String xhOverdueDay(@PathVariable Long Id, HttpServletRequest request, Model model){
		XhCapitalOverdue xhCapitalOverdue = xhCapitalOverdueManager.getXhCapitalOverdue(Id);
		model.addAttribute("xhCapitalOverdue", xhCapitalOverdue);
		return "jsgl/overdueDayInput";
	}
	
	
	/**
	 * 逾期天数MDY 2013-10-09
	 * @param xhCapitalOverdue
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/saveOverdueDay",method=RequestMethod.POST)
	public String saveOverdueDay(@ModelAttribute("xhCapitalOverdue") XhCapitalOverdue xhCapitalOverdue, HttpServletRequest request, HttpServletResponse response){
		xhCapitalOverdueManager.saveOverdueDay(xhCapitalOverdue);
		DwzResult success = new DwzResult("200","操作成功","","","closeCurrent","");
		ServletUtils.renderJson(response, success);
		
		return null;
	}
	
	/**
	 * 取消逾期MDY 2013-10-09
	 * @param xhCapitalOverdue
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/deleteOverdueSetAcc",method=RequestMethod.POST)
	public String deleteOverdueSetAcc(@ModelAttribute("xhCapitalOverdue") XhCapitalOverdue xhCapitalOverdue, HttpServletRequest request, HttpServletResponse response){
		xhCapitalOverdueManager.deleteOverdueSetAcc(xhCapitalOverdue);
		DwzResult success = new DwzResult("200","操作成功","","","closeCurrent","");
		ServletUtils.renderJson(response, success);
		
		return null;
	}

	@RequestMapping(value="/delXhCapitalOverdue/{Id}")
	public String delUser(@PathVariable Long Id, HttpServletResponse response){
		xhCapitalOverdueManager.deleteXhCapitalOverdue(Id);
		DwzResult success = new DwzResult("200","删除成功","rel_listXhCapitalOverdue","","","");
		ServletUtils.renderJson(response, success);
		return null;
	}
	
	@RequestMapping(value="/batchdelXhCapitalOverdue")
	public String batchDelUser(HttpServletRequest request, HttpServletResponse response){
		String ids = request.getParameter("ids");
		String[] Ids = ids.split(",");
		boolean isSuccess = xhCapitalOverdueManager.batchDelXhCapitalOverdue(Ids);
		
		DwzResult success = null;
		if (isSuccess){
			success = new DwzResult("200","删除成功","rel_listXhCapitalOverdue","","","");
		}
		else{
			success = new DwzResult("300","删除失败","rel_listXhCapitalOverdue","","","");
		}
		ServletUtils.renderJson(response, success);
		return null;
	}
	
	/**
	 * 导入EXCEL 未公开 直接调用
	 * @param url http://127.0.0.1:8080/CHP/xhCapitalOverdue/excelOver
	 * @param model1
	 * @return
	 */
	@RequestMapping(value="/excelOver")
	public String excelOver(HttpServletRequest request, Model model){
		try {
			xhCapitalOverdueManager.excelOver();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}

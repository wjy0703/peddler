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

import cn.com.cucsi.app.entity.xhcf.XhBorrowscore;
import cn.com.cucsi.app.entity.xhcf.XhJksq;
import cn.com.cucsi.app.service.loan.XhJksqManager;
import cn.com.cucsi.app.service.security.OperatorDetails;
import cn.com.cucsi.app.service.xhcf.XhBorrowscoreManager;
import cn.com.cucsi.app2.service.xhcf.XhNewJksqManager;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils;
import cn.com.cucsi.core.web.DwzResult;
import cn.com.cucsi.core.web.ServletUtils;

@Controller
@RequestMapping(value="/xhBorrowscore")
public class XhBorrowscoreController {
	@Autowired
	private XhBorrowscoreManager xhBorrowscoreManager;
	@Autowired
	private XhJksqManager xhJksqManager;
	@Autowired
	private XhNewJksqManager XhNewJksqManager;
	
	@RequestMapping(value="/listXhBorrowscore")
	public String listXhBorrowscore(HttpServletRequest request, Model model){
		// 处理分页的参数
		Page<XhBorrowscore> page = new Page<XhBorrowscore>();
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
		
		xhBorrowscoreManager.searchXhBorrowscore(page, map);
	
		model.addAttribute("page", page);
		model.addAttribute("map", map);
		return "folder/xhBorrowscoreIndex";
		
	}
	
	@RequestMapping(value="/saveXhBorrowscore",method=RequestMethod.POST)
	public void saveXhBorrowscore(@ModelAttribute("xhBorrowscore") XhBorrowscore xhBorrowscore, HttpServletRequest request, HttpServletResponse response){
		String scoreType = request.getParameter("scoreType");
		String jksqId = request.getParameter("jksqId");
		String zf = request.getParameter("zf");
		
		XhJksq xhJksq = xhJksqManager.getXhJksq(Long.parseLong(jksqId));
		if("0".equals(scoreType)){

            if ("303".equals(xhJksq.getState())) {
                xhJksq.setBackup06(zf);
                if (Integer.parseInt(zf) < 55) {
                    xhJksq.setState("104");
                } else {

                    xhJksq.setState("02");
                }
            }

			xhBorrowscore.setScoreType(0);//门店评分后转到信审评分
			//下面调用了两个save方法，不能保证事务，暂时可以这样，因为后面的只是添加了申请的状态变化历史
			xhBorrowscoreManager.saveXhBorrowscore(xhBorrowscore);
			XhNewJksqManager.saveXhJksqState(xhJksq, "门店评分完成", "门店评分完成");
			
		}else{
			xhBorrowscore.setScoreType(1);
			//信审评分
			xhBorrowscoreManager.saveXhBorrowscore(xhBorrowscore);
			XhNewJksqManager.saveXhJksqState(xhJksq, "信审评分完成", "信审评分完成");
		}
		DwzResult success = new DwzResult("200","保存成功","","","closeCurrent","");
		ServletUtils.renderJson(response, success);
	}
	
	@RequestMapping(value="/addXhBorrowscore", method=RequestMethod.GET)
	public ModelAndView add(){
		return new ModelAndView("folder/xhBorrowscoreInput", "xhBorrowscore", new XhBorrowscore());
	}
	
	@RequestMapping(value="/editXhBorrowscore/{Id}", method=RequestMethod.GET)
	public ModelAndView edit(@PathVariable Long Id){
		XhBorrowscore xhBorrowscore = xhBorrowscoreManager.getXhBorrowscore(Id);
		return new ModelAndView("folder/xhBorrowscoreInput", "xhBorrowscore", xhBorrowscore);
	}

	@RequestMapping(value="/delXhBorrowscore/{Id}")
	public String delUser(@PathVariable Long Id, HttpServletResponse response){
		xhBorrowscoreManager.deleteXhBorrowscore(Id);
		DwzResult success = new DwzResult("200","删除成功","rel_listXhBorrowscore","","","");
		ServletUtils.renderJson(response, success);
		return null;
	}
	
	@RequestMapping(value="/batchdelXhBorrowscore")
	public String batchDelUser(HttpServletRequest request, HttpServletResponse response){
		String ids = request.getParameter("ids");
		String[] Ids = ids.split(",");
		boolean isSuccess = xhBorrowscoreManager.batchDelXhBorrowscore(Ids);
		
		DwzResult success = null;
		if (isSuccess){
			success = new DwzResult("200","删除成功","rel_listXhBorrowscore","","","");
		}
		else{
			success = new DwzResult("300","删除失败","rel_listXhBorrowscore","","","");
		}
		ServletUtils.renderJson(response, success);
		return null;
	}
	
	/**
	 * 借款申请评分信息门店
	 * 
	 * @param Id
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "jksqGrade/{Id}", method = RequestMethod.GET)
	public String showJksqGrade(@PathVariable Long Id,HttpServletRequest request, Model model) {
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		Long employeeId = operator.getUserId();
		model.addAttribute("employeeId",employeeId);
		model.addAttribute("scoreType",0);
		
		XhBorrowscore xhBorrowscore = xhBorrowscoreManager.queryByJksqId(Id,0);
		if("1".equals(request.getParameter("look"))){
		    model.addAttribute("look","1");
		}
		model.addAttribute("jksq_id",Id);
		model.addAttribute("score",xhBorrowscore);
		this.listXhBorrowscore(request, model);
		return "jksq/jksqGradeShow";
	}
	/**
	 * 借款申请评分信息信审
	 * 
	 * @param Id
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "jksqAuditGrade/{Id}", method = RequestMethod.GET)
	public String showJksqAuditGrade(@PathVariable Long Id,
			HttpServletRequest request, Model model) {
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		Long employeeId = operator.getUserId();
		model.addAttribute("employeeId",employeeId);
		
		XhBorrowscore xhBorrowscore = xhBorrowscoreManager.queryByJksqId(Id,1);
		if(xhBorrowscore.getId() == null){
	    	 xhBorrowscore = xhBorrowscoreManager.queryByJksqId(Id,0);
	    	 model.addAttribute("mendian","1");
		}
		if("1".equals(request.getParameter("look"))){
            model.addAttribute("look","1");
        }
		model.addAttribute("scoreType",1);
		model.addAttribute("jksq_id",Id);
		model.addAttribute("score",xhBorrowscore);
		this.listXhBorrowscore(request, model);
		return "jksq/jksqGradeShow";
	}
	
}

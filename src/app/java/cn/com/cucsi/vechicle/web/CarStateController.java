package cn.com.cucsi.vechicle.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.cucsi.app.entity.xhcf.XhJksqState;
import cn.com.cucsi.app.service.security.OperatorDetails;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils;
import cn.com.cucsi.vechicle.entity.xhcf.XhCarState;
import cn.com.cucsi.vechicle.service.CarStateManager;


@Controller
@RequestMapping(value="/CarState")
public class CarStateController {
	
	private Logger logger = LoggerFactory.getLogger(CarStateController.class);
	
	@Autowired
	private CarStateManager carStateManager;
	
	@RequestMapping(value="/listCarStateHistory/{Id}")
	public String listCarStateHistory(@PathVariable Long Id,
			HttpServletRequest request, HttpServletResponse response,
			Model model){
		
		// 得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		if (operator == null) {
			return "redirect:../../login";
		}
		Page<XhCarState> page = new Page<XhCarState>();
		String pageSize = request.getParameter("numPerPage");
		if (StringUtils.isNotBlank(pageSize)) {
			page.setPageSize(Integer.valueOf(pageSize));
		}
		String pageNo = request.getParameter("pageNum");
		if (StringUtils.isNotBlank(pageNo)) {
			page.setPageNo(Integer.valueOf(pageNo));
		}
		String orderBy = request.getParameter("orderField");
		if (StringUtils.isNotBlank(orderBy)) {
			page.setOrderBy(orderBy);
		}
		String order = request.getParameter("orderDirection");
		if (StringUtils.isNotBlank(order)) {
			page.setOrder(order);
		}
		carStateManager.CarStateHistoryList(Id, page);
		model.addAttribute("page", page);
		model.addAttribute("carApplyId", "Id");
		return "xhCarLoan/apply/listCarStateHistory";
		
	}
	
	
	
	
}

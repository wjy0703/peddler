package cn.com.cucsi.app2.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.cucsi.app.entity.xhcf.XhLoanReturnRecords;
import cn.com.cucsi.app.service.xhcf.XhLoanReturnRecordsManager;
import cn.com.cucsi.app2.entity.xhcf.XhJksqRelations;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.web.ServletUtils;


@Controller
@RequestMapping(value = "/xhRefundFailRecord")
public class XhRefundFailRecordController {
	
	@Autowired
	private XhLoanReturnRecordsManager xhLoanReturnRecordsManager;
	
	@RequestMapping(value="/listXhRefundFailRecord")
	public String listXhRefundFailRecord(HttpServletRequest request, Model model){
		// 处理分页的参数
		Page<XhLoanReturnRecords> page = new Page<XhLoanReturnRecords>();
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
		xhLoanReturnRecordsManager.searchxhLoanReturnFailRecords(page, map);
		model.addAttribute("page", page);
		model.addAttribute("map", map);
		return "loanerAccount/xhRefundFailRecordIndex";
		
	}

}

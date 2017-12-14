package cn.com.cucsi.app.web.report;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import cn.com.cucsi.app.service.excel.LoanBackExcelService;
import cn.com.cucsi.app.web.util.JdbcPageUtils;
import cn.com.cucsi.core.orm.JdbcPage;
/**
 * 业务统计管理
 * @author xjs
 *
 */
@Controller
@RequestMapping(value="/report/loanBack")
public class ReportLoanBackController {
	
/*  @Autowired
	private BaseInfoManager baseInfoManager;*/

    @Autowired
    LoanBackExcelService loanBackExcelService;
    
	@RequestMapping(value="/listLoanBackAll")
	public String list(HttpServletRequest request, Model model){
		JdbcPage page = JdbcPageUtils.generatePage(request);
		Map<String, Object> map = WebUtils.getParametersStartingWith(request, "filter_");
		List<Map<String,Object>> list = loanBackExcelService.queryRows(map, page);
		model.addAttribute("list", list);
		
		List<Map<String,Object>> listYyb = loanBackExcelService.queryOrgin();
		model.addAttribute("listYyb", listYyb);
		model.addAttribute("map", map);
     	model.addAttribute("page", page);
		return "report/loanBackReport";		
	}

}

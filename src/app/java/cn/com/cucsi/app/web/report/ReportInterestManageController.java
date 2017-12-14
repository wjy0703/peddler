package cn.com.cucsi.app.web.report;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import cn.com.cucsi.app.entity.baseinfo.Employee;
import cn.com.cucsi.app.service.baseinfo.BaseInfoManager;
import cn.com.cucsi.app.service.excel.InterestManageExcelService;
import cn.com.cucsi.app.service.excel.LoanBackExcelService;
import cn.com.cucsi.app.service.excel.StatisticalService;
import cn.com.cucsi.app.service.security.OperatorDetails;
import cn.com.cucsi.app.web.util.JdbcPageUtils;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils;
/**
 * 业务统计管理
 * @author xjs
 *
 */
@Controller
@RequestMapping(value="/report/interestManage")
public class ReportInterestManageController {
	
/*  @Autowired
	private BaseInfoManager baseInfoManager;*/

    @Autowired
    InterestManageExcelService interestManageExcelService;
    
    @Autowired
    private StatisticalService statisticalService;
    @Autowired
    LoanBackExcelService loanBackExcelService;
    
	@RequestMapping(value="/listInterestManageAll")
	public String list(HttpServletRequest request, Model model){
		JdbcPage page = JdbcPageUtils.generatePage(request);
		Map<String, Object> map = WebUtils.getParametersStartingWith(request, "filter_");
		List<Map<String,Object>> list = interestManageExcelService.queryRows(map, page);
		List<Map<String,Object>> listYyb = loanBackExcelService.queryOrgin();
		model.addAttribute("listYyb", listYyb);
		
//		List<Map<String,Object>> mendianlist = statisticalService.mendianList("cfmd");
//	    model.addAttribute("mendianlist", mendianlist);
	    model.addAttribute("map", map);
		model.addAttribute("datas", list);
     	model.addAttribute("page", page);
		return "report/interestManageReport";		
	}

}

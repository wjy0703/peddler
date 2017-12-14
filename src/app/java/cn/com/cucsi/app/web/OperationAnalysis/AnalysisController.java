package cn.com.cucsi.app.web.OperationAnalysis;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.cucsi.app.service.OperationAnalysis.AnalysisManager;
import cn.com.cucsi.app.service.excel.StatisticalService;
import cn.com.cucsi.app.service.security.OperatorDetails;
import cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils;
import cn.com.cucsi.core.web.ServletUtils;

@Controller
@RequestMapping(value="/analysis")
public class AnalysisController {

	private Logger logger = LoggerFactory.getLogger(AnalysisController.class);
	
	private AnalysisManager analysisManager;
	
	private StatisticalService statisticalService;
	
	@Autowired
	public void setStatisticalService(StatisticalService statisticalService) {
		this.statisticalService = statisticalService;
	}

	@Autowired
	public void setAnalysisManager(AnalysisManager analysisManager) {
		this.analysisManager = analysisManager;
	}
	
	/**
	 * 区域金额汇总 MDY 2013-07-31
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/getRegionalSumTotal")
	public String getRegionalSumTotal(HttpServletRequest request, Model model){
		String title = "区域金额汇总";
		Map<String, Object> map = ServletUtils.getParametersStartingWith2(request, "filter_");
		if(map.size() == 0){
			map.put("type", "column");
		}
		String data = analysisManager.getRegionalSumTotal(map, title);
		this.sendInfo(map, model, data, "regionalSumTotal", "区域金额汇总");
		return "OperationAnalysis/regionalSumTotal";
	}
	
	/**
	 * 门店金额汇总 MDY 2013-07-31
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/getStoreSumTotal")
	public String getStoreSumTotal(HttpServletRequest request, Model model){
		String title = "门店金额汇总";
		Map<String, Object> map = ServletUtils.getParametersStartingWith2(request, "filter_");
		if(map.size() == 0){
			map.put("type", "column");
		}
		String data = analysisManager.getStoreSumTotal(map, title);
		this.sendInfo(map, model, data, "storeSumTotal", title);
		return "OperationAnalysis/storeSumTotal";
	}
	
	/**
	 * 团队金额汇总 MDY 2013-07-32
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/getTeamSumTotal/{Id}")
	public String getTeamSumTotal(@PathVariable Long Id, HttpServletRequest request, Model model){
		boolean isRes = false;
		String title = "团队金额汇总";
		String data = "";
		List<Map<String,Object>> mendianTree = statisticalService.mendianList("cfmd");
		if(Id != 0){
			isRes = true;
			List<Map<String,Object>> tuanDuiTree = statisticalService.mendianTreeTd(Id);
			data = analysisManager.getTremSumTotal(tuanDuiTree);
			title = analysisManager.getTiele(mendianTree, Id);
			this.sendInfo(null, model, data, "teamSumTotal", title);
		}
		model.addAttribute("mendianTree", mendianTree);
		model.addAttribute("isRes", isRes);
		return "OperationAnalysis/teamSumTotal";
		
	}
	
	/**
	 * 图表统一输入方法 MDY 2013-07-31
	 * @param request
	 * @param model
	 * @param data
	 * @param render
	 * @param title
	 */
	private void sendInfo(Map<String, Object> map, Model model, String data, String render, String title){
		model.addAttribute("render", render);
		model.addAttribute("title", title);
		model.addAttribute("subtitle", analysisManager.getSubtitleInfo(map));
		model.addAttribute("data", data);
		model.addAttribute("map", map);
	}

}

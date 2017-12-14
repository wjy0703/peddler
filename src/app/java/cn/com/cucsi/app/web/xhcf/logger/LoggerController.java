package cn.com.cucsi.app.web.xhcf.logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.cucsi.app.entity.xhcf.XhTzsq;
import cn.com.cucsi.app.entity.xhcf.XhcfCjrxx;
import cn.com.cucsi.app.service.excel.StatisticalService;
import cn.com.cucsi.app.service.xhcf.LeaveReplaceManager;
import cn.com.cucsi.app.web.util.ChangeRecord;
import cn.com.cucsi.app.web.util.ExtractDataFromRequest;
import cn.com.cucsi.app.web.util.log.ReconfigLog;
import cn.com.cucsi.app.web.util.log.SingleLogInfo;
import cn.com.cucsi.core.web.DwzResult;
import cn.com.cucsi.core.web.ServletUtils;
/**
 * 变更出借端口理财经理
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/logControl")
public class LoggerController {
	

	@RequestMapping(value="/listAndEdit")
	public String preChangeCjrxxLeadersSingle(HttpServletRequest request, Model model,HttpServletResponse response){
	        String level = request.getParameter("level");
	        String clazzName = request.getParameter("clazzName");
	        String setLevel = request.getParameter("setLevel");
	        if("1".equals(setLevel))
	            ReconfigLog.changeLevel(clazzName, level);
	        List<SingleLogInfo> infos;
	        if(StringUtils.hasText(level)){
	            infos = ReconfigLog.getLogInfos(level);
	        }else if(StringUtils.hasText(clazzName)){
	            infos = ReconfigLog.getLogInfosByName(clazzName);
	        }else{
	            infos = ReconfigLog.getRootLogInfos();
	        }
	        model.addAttribute("logs", infos);
	        return "logger/logInfo";
	}
	
}

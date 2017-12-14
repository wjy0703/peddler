package cn.com.cucsi.app.web.xhcf;

import java.io.File;
import java.util.Date;
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
import org.springframework.web.util.WebUtils;

import cn.com.cucsi.app.entity.baseinfo.Employee;
import cn.com.cucsi.app.entity.baseinfo.Organi;
import cn.com.cucsi.app.entity.xhcf.XhMaintainLog;
import cn.com.cucsi.app.service.security.OperatorDetails;
import cn.com.cucsi.app.service.xhcf.XhMaintainLogManager;
import cn.com.cucsi.app.web.InitSetupListener;
import cn.com.cucsi.app.web.util.FileUtil;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils;
import cn.com.cucsi.core.utils.DateUtils;
import cn.com.cucsi.core.web.DwzResult;
import cn.com.cucsi.core.web.ServletUtils;

@Controller
@RequestMapping(value="/xhMaintainLog")
public class XhMaintainLogController {
	private XhMaintainLogManager xhMaintainLogManager;
	@Autowired
	public void setXhMaintainLogManager(XhMaintainLogManager xhMaintainLogManager) {
		this.xhMaintainLogManager = xhMaintainLogManager;
	}
	
	@RequestMapping(value="/listXhMaintainLog")
	public String listXhMaintainLog(HttpServletRequest request, Model model){
		// 处理分页的参数
		Page<XhMaintainLog> page = new Page<XhMaintainLog>();
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
		
		xhMaintainLogManager.searchXhMaintainLog(page, map);
	
		model.addAttribute("page", page);
		model.addAttribute("map", map);
		return "baseinfo/xhMaintainLogIndex";
		
	}
	
	@RequestMapping(value="/saveXhMaintainLog",method=RequestMethod.POST)
	public String saveXhMaintainLog(@ModelAttribute("xhMaintainLog") XhMaintainLog xhMaintainLog, HttpServletRequest request, HttpServletResponse response){
		
		xhMaintainLogManager.saveXhMaintainLog(xhMaintainLog);

		DwzResult success = new DwzResult("200","保存成功","rel_listXhMaintainLog","","closeCurrent","");
		ServletUtils.renderJson(response, success);
		
		return null;
	}
	
	@RequestMapping(value="/addXhMaintainLog", method=RequestMethod.GET)
	public ModelAndView add(){
		return new ModelAndView("baseinfo/xhMaintainLogInput", "xhMaintainLog", new XhMaintainLog());
	}
	
	@RequestMapping(value="/editXhMaintainLog/{Id}", method=RequestMethod.GET)
	public ModelAndView edit(@PathVariable Long Id){
		XhMaintainLog xhMaintainLog = xhMaintainLogManager.getXhMaintainLog(Id);
		return new ModelAndView("baseinfo/xhMaintainLogInput", "xhMaintainLog", xhMaintainLog);
	}

	@RequestMapping(value="/delXhMaintainLog/{Id}")
	public String delUser(@PathVariable Long Id, HttpServletResponse response){
		xhMaintainLogManager.deleteXhMaintainLog(Id);
		DwzResult success = new DwzResult("200","删除成功","rel_listXhMaintainLog","","","");
		ServletUtils.renderJson(response, success);
		return null;
	}
	
	@RequestMapping(value="/batchdelXhMaintainLog")
	public String batchDelUser(HttpServletRequest request, HttpServletResponse response){
		String ids = request.getParameter("ids");
		String[] Ids = ids.split(",");
		boolean isSuccess = xhMaintainLogManager.batchDelXhMaintainLog(Ids);
		
		DwzResult success = null;
		if (isSuccess){
			success = new DwzResult("200","删除成功","rel_listXhMaintainLog","","","");
		}
		else{
			success = new DwzResult("300","删除失败","rel_listXhMaintainLog","","","");
		}
		ServletUtils.renderJson(response, success);
		return null;
	}
	
	/**
	 * 导出系统日志维护报表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/exportXhMaintainLog")
	public ModelAndView exportXhMaintainLog(HttpServletRequest request,
			HttpServletResponse response){
		// 得到当前登录用户 searchXhZqtjForDown
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		String mouFilePath2 = InitSetupListener.filePath + "excel"
				+ File.separator;
		Map<String, Object> map = ServletUtils.getParametersStartingWith2(request, "filter_");
		
		
		String fileName = "";
		String filePath = "";
		Date createTime = new Date();
		/*
		String gsdq = String.valueOf(map.get("gsdq"));
		if ("0021".equals(gsdq)) {
			fileName = "000191400200580_S022"
					+ DateUtils.format(createTime, "yyyyMMdd") + "_0001好易联.xls";
		} else {
		*/
			fileName =  "系统日志维护报表" + DateUtils.format(createTime, "yyyyMMdd")
					+ ".xls";
		//}
		filePath = mouFilePath2 + fileName;

		xhMaintainLogManager.exportXhMaintainLog(filePath, map);
		
		response.setContentType("APPLICATION/OCTET-STREAM");

		FileUtil.downLoad(filePath, fileName, request, response);
		//System.out.println("删除单个文件===>" + filePath);
		//FileUtil.deleteFile(filePath);
		//System.out.println("删除单个文件   成功===>" + filePath);

		return null;
	}
}

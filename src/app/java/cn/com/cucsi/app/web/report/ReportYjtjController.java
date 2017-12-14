package cn.com.cucsi.app.web.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import javax.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import cn.com.cucsi.app.entity.baseinfo.Employee;
import cn.com.cucsi.app.service.baseinfo.BaseInfoManager;
import cn.com.cucsi.app.service.excel.IpcyjtjExcelService;
import cn.com.cucsi.app.service.excel.LoanBackExcelService;
import cn.com.cucsi.app.service.excel.StatisticalService;
import cn.com.cucsi.app.service.excel.YjtjExcelService;
import cn.com.cucsi.app.service.security.OperatorDetails;
import cn.com.cucsi.app.web.InitSetupListener;
import cn.com.cucsi.app.web.util.JdbcPageUtils;
import cn.com.cucsi.app.web.util.SalaryExcelReport;
import cn.com.cucsi.app.web.util.SalaryExcelReportForLend;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils;
import cn.com.cucsi.core.web.DwzResult;
import cn.com.cucsi.core.web.ServletUtils;
/**
 * 业务统计管理
 * @author xjs
 *
 */
@Controller
@RequestMapping(value="/report/yjtj")
public class ReportYjtjController {
	
    @Autowired
	private BaseInfoManager baseInfoManager;

    @Autowired
    IpcyjtjExcelService ipcyjtjExcelService;
    
    @Autowired
    YjtjExcelService yjtjExcelService;
    
    @Autowired
    LoanBackExcelService loanBackExcelService;
    
    @Autowired
    private StatisticalService statisticalService;
    
    @RequestMapping(value="/listIpcyjtjAll")
	public String listIpc(HttpServletRequest request, Model model){
		//得到当前登录用户
				OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
				if(operator==null)
				{
					return "redirect:../login";
				}
				JdbcPage page = JdbcPageUtils.generatePage(request);
				
				Map<String, Object> map = WebUtils.getParametersStartingWith(request, "filter_");
				Employee emp = baseInfoManager.getUserEmployee();
				List<Map<String,Object>> listYyb = loanBackExcelService.queryOrgin();
				String loginName = operator.getCtiCode();
				map.put("loginName", loginName);
				map.put("emp", emp);
				map.put("backup01", "IPC");
				List<Map<String,Object>> list = ipcyjtjExcelService.queryRows(map, page);
				model.addAttribute("listYyb", listYyb);
				model.addAttribute("map", map);
				model.addAttribute("list", list);
		     	model.addAttribute("page", page);
		return "report/ipcyjtjReport";		
	}
    
	@RequestMapping(value="/listYjtjAll")
	public String list(HttpServletRequest request, Model model){
		//得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		if(operator==null)
		{
			return "redirect:../login";
		}
		JdbcPage page = JdbcPageUtils.generatePage(request);
		
		Map<String, Object> map = WebUtils.getParametersStartingWith(request, "filter_");
		Employee emp = baseInfoManager.getUserEmployee();
		List<Map<String,Object>> listYyb = loanBackExcelService.queryOrgin();
		String loginName = operator.getCtiCode();
		map.put("loginName", loginName);
		map.put("emp", emp);
		map.put("backup01", "");
		List<Map<String,Object>> list = yjtjExcelService.queryRows(map, page);
		model.addAttribute("listYyb", listYyb);
		model.addAttribute("map", map);
		model.addAttribute("list", list);
     	model.addAttribute("page", page);
		return "report/yjtjReport";		
	}
	
	
	@RequestMapping(value="/listYjkhHj")
	public String listYjkhHj(HttpServletRequest request, Model model){
		//得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		if(operator==null)
		{
			return "redirect:../login";
		}
		Map<String, Object> map = WebUtils.getParametersStartingWith(request, "filter_");
		//Map<String, Object> map = new HashMap<String, Object>();
		//String orgid = request.getParameter("organi.id");
		//String orgName = request.getParameter("organi.name");
		//String date = request.getParameter("date");
		
		if(map.containsKey("orgid") && map.containsKey("date")){
			String orgid = String.valueOf(map.get("orgid"));
			String date = String.valueOf(map.get("date"));
			if(StringUtils.isNotEmpty(orgid) && StringUtils.isNotEmpty(date)){
				map.put("orgid", orgid);
				map.put("date", date);
				Map<String,Object> list = statisticalService.rebTzsqStatis(orgid, date);
				model.addAttribute("list", list);
			}
		}
		
		List<Map<String,Object>> mendianlist = statisticalService.mendianList("cfmd");
		model.addAttribute("mendianlist", mendianlist);
//		List<Map<String,Object>> mendianTree = statisticalService.mendianTree();
//		model.addAttribute("mendianTree", mendianTree);
		model.addAttribute("map", map);
		return "report/yjkhHjReport";		
	}
	
	
	
	@RequestMapping(value="/exportYjkhHj")
	public void exportYjkhHj(HttpServletRequest request, Model model,HttpServletResponse response){
	    
		Map<String, Object> map = WebUtils.getParametersStartingWith(request, "filter_");
				
		if(map.containsKey("orgid") && map.containsKey("date")){
			  String orgid = String.valueOf(map.get("orgid"));
			  String date = String.valueOf(map.get("date"));
			  if(StringUtils.isNotEmpty(orgid) && StringUtils.isNotEmpty(date)){
			      SalaryExcelReport ser = new SalaryExcelReport();
			      try{
    				    String fullPath = InitSetupListener.filePath + "excel"  + File.separator;
    				    File directory = new File(fullPath);
    		            if (!directory.exists())
    		            	directory.mkdirs();
    				    List<Map<String, Object>> rebJksqStatis = statisticalService.rebJksqStatis2(orgid,date);
    					String templateFilePath = InitSetupListener.rootPath + "salaryTemplate" + File.separator;
    				 
    					
    				    String file = templateFilePath + getFileTemplate(orgid);
    					//System.out.println(file);
    					ser.openExcel(file, true);// false为不显示打开Excel
    					int templateTeam = 7;
    					int departRow = 6;
    					String generateFileName = "";
    					for(int index = 0 ; index < rebJksqStatis.size() ; index++ ){
    						Map<String, Object> data = rebJksqStatis.get(index);
    						String orgnizationName = data.get("RGANI_NAME") != null ? data.get("RGANI_NAME").toString() : "";
    						generateFileName = orgnizationName + "_业绩统计"+date+".xls";
    						templateTeam = ser.copyRow(data,templateTeam,departRow);
    					
    					}
    					ser.deleteRow(templateTeam);
    					ser.deleteRow(templateTeam);
    					File fileExist = new File(fullPath + generateFileName);
    					while(fileExist.exists()){
    						fileExist.delete();
    						if(fileExist.exists()){//文件未删除，则等待1秒
    							try {
    								Thread.sleep(1000);
    							} catch (InterruptedException e) {
    								e.printStackTrace();
    							}
    						}
    					}
    					ser.closeExcel(fullPath + generateFileName ,false);
    					InputStream is = null;
    					try {
    						response.setContentType("application/xls");   
    						response.setHeader("Content-Disposition", "attachment; filename=" +  java.net.URLEncoder.encode(generateFileName, "UTF-8"));
    						
    						is = new FileInputStream(fullPath + generateFileName);
    						IOUtils.copy(is, response.getOutputStream());
    										
    					} catch (Exception e) {
    						//if(e instanceof ClientAbortException)
    						//e.printStackTrace();
    					}finally{
    						
    						try {
    							is.close();
    						} catch (IOException e) {
    							e.printStackTrace();
    						}		
    						File fileTemp = new File(fullPath + generateFileName);
    						if(fileTemp.exists())
    							fileTemp.delete();
    						
    					}
			      }catch(Exception e){
			          e.printStackTrace();
			          ser.releaseSource();
			      }
					return ;
			  }
		}
		DwzResult success = new DwzResult("300", "查询条件不足", "",	"", "", "");
		ServletUtils.renderJson(response, success);
	}

	/**
	 * 根据组织机构获取模板，北京地区和二线城市模板不一样
	 * @param orgid
	 * @return
	 */
	private String getFileTemplate(String orgid) {
		//6760和6761为北京地区，此处不完善，应该从数据库获得绩效考核的级别模板
		if("6760".equals(orgid)||"6761".equals(orgid)){
			return "city2003template.xls";
		}else{
			return "city2003templateTwo.xls";
		}
	}
	
	/**
	 * 导出出借端报表
	 * @param request
	 * @param model
	 * @param response
	 */
	@RequestMapping(value="/exportLendExcel")
	public void exportLendExcel(HttpServletRequest request, Model model,HttpServletResponse response){
		Map<String, Object> map = WebUtils.getParametersStartingWith(request, "filter_");
		Cookie cookie = new Cookie("fileDownload","true");	
		cookie.setPath("/");
		response.addCookie(cookie);
		
		if(map.containsKey("orgid") && map.containsKey("date")){
			  String orgid = String.valueOf(map.get("orgid"));
			  String date = String.valueOf(map.get("date"));
			  if(StringUtils.isNotEmpty(orgid) && StringUtils.isNotEmpty(date)){
				    String fullPath = InitSetupListener.filePath + "excel"  + File.separator;
				    File directory = new File(fullPath);
		            if (!directory.exists())
		            	directory.mkdirs();
		            Map<String,Object> data = statisticalService.rebTzsqStatis(orgid, date);
					String templateFilePath = InitSetupListener.rootPath + "salaryTemplate" + File.separator;
					String file = templateFilePath + getLendFileTemplate(orgid);
					String generateFileName = data.get("RGANI_NAME") + "_出借业绩统计"+date+".xls";
					
					File fileExist = new File(fullPath + generateFileName);
					while(fileExist.exists()){
						fileExist.delete();
						if(fileExist.exists()){//文件未删除，则等待1秒
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
					
					SalaryExcelReportForLend serl = new SalaryExcelReportForLend();
					serl.writeLendData(data,file,fullPath + generateFileName);
					InputStream is = null;
					try {
						response.setContentType("application/xls");   
						response.setHeader("Content-Disposition", "attachment; filename=" +  java.net.URLEncoder.encode(generateFileName, "UTF-8"));
						is = new FileInputStream(fullPath + generateFileName);
						IOUtils.copy(is, response.getOutputStream());
										
					} catch (Exception e) {
						//if(e instanceof ClientAbortException)
						//e.printStackTrace();
					}finally{
						
						try {
							is.close();
						} catch (IOException e) {
							e.printStackTrace();
						}		
						File fileTemp = new File(fullPath + generateFileName);
						if(fileTemp.exists())
							fileTemp.delete();
						
					}
					return ;
			  }
		}
		DwzResult success = new DwzResult("300", "查询条件不足", "",	"", "", "");
		ServletUtils.renderJson(response, success);
	}

    /**
     * 返回出借段模板名称
     * @param orgid
     * @return
     */
	private String getLendFileTemplate(String orgid) {
		
		return "lend.xls";
	}
}

package cn.com.cucsi.app.web.xhcf;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.cucsi.app.entity.xhcf.XhTzsq;
import cn.com.cucsi.app.entity.xhcf.XhcfCjrxx;
import cn.com.cucsi.app.service.excel.StatisticalService;
import cn.com.cucsi.app.service.xhcf.LeaveReplaceManager;
import cn.com.cucsi.app.web.util.ChangeRecord;
import cn.com.cucsi.app.web.util.ChangeRecordXlsUtils;
import cn.com.cucsi.app.web.util.Java2Word;
import cn.com.cucsi.app.web.util.SalaryExcelReport;
import cn.com.cucsi.core.web.DwzResult;
import cn.com.cucsi.core.web.ServletUtils;
/**
 * 变更出借端口理财经理
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/changeCjrxx")
public class LeaveReplaceController {
	
	@Autowired
	StatisticalService statisticService;
	
	@Autowired
	private LeaveReplaceManager leaveReplaceManager;
	
	@RequestMapping(value="/preChangeCjrxxLeaders")
	public String preChangeCjrxxLeaders(HttpServletRequest request, Model model,HttpServletResponse response){	
		return "cjrxx/preChangeCjrxxLeaders";
	}
	
	@RequestMapping(value="/preChangeCjrxxLeadersSingle")
	public String preChangeCjrxxLeadersSingle(HttpServletRequest request, Model model,HttpServletResponse response){
/*		List<Map<String, Object>> rebJksqStatis = statisticService.rebJksqStatis("6760","2013-06");
		    SalaryExcelReport ser = new SalaryExcelReport();
			String file = "c:\\city2003template.xls";
			ser.openExcel(file, false);// false为不显示打开Excel
			
			int templateTeam = 6;
			int departRow = 5;
			for(int index = 0 ; index < rebJksqStatis.size() ; index++ ){
				Map<String, Object> data = rebJksqStatis.get(index);
				templateTeam = ser.copyRow(data,templateTeam,departRow);
			}
			ser.deleteRow(templateTeam);
			ser.deleteRow(templateTeam);
			ser.closeExcel("c:\\lionelabcde.xls",false);
	        System.out.println("done");*/
		return "cjrxx/preChangeCjrxxLeadersSingle";
	}
	
	@RequestMapping(value="/changeCjrxxLeaders")
	public void changeCjrxxLeaders(HttpServletRequest request, Model model,HttpServletResponse response){	
		String name = request.getParameter("name");
		String oldCrmName = request.getParameter("employBefore.id");//原客户经理
		String crmName = request.getParameter("employeeCrm.id");//新客户经理
		String ccaName = request.getParameter("employeeCca.id");//团队经理
		String tzsqbh = request.getParameter("tzsqbh");//投资申请ID
		System.out.println("name==>" + name + 
				"oldCrmName==>" + oldCrmName + 
				"crmName==>" + crmName + 
				"ccaName==>" + ccaName + 
				"tzsqbh==>" + tzsqbh );
		try{
			leaveReplaceManager.changeCjrxxLeaders(name, oldCrmName, crmName, ccaName,tzsqbh);
			DwzResult success = new DwzResult("200","保存成功","rel_preChangeCjrxxLeaders","","","");
			ServletUtils.renderJson(response, success);
		}catch(Exception e){
			DwzResult success = new DwzResult("300",e.getMessage(),"rel_preChangeCjrxxLeaders","","","");
			ServletUtils.renderJson(response, success);

		}
	}
	
	
	/**
	 * 根据上传文件生成记录同时以json形式返回前台
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/generateChangeRecords")
	public void changeRecordFromXls(HttpServletRequest  request , HttpServletResponse response){
		Map<String,String> map = new HashMap<String,String>();
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(10240000);
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setFileSizeMax(10002400000l);
		upload.setSizeMax(10002400000l);
		upload.setHeaderEncoding("UTF-8");
		try {
			List<?> items = upload.parseRequest(request);
			FileItem item = null;
			for (int i = 0 ;i < items.size(); i++){
				item = (FileItem) items.get(i);
				// 保存文件
				if (!item.isFormField() && item.getName().length() > 0) {
					InputStream stream = item.getInputStream();
					List<ChangeRecord> changeRecords = ChangeRecordXlsUtils.getChangeRecords(stream);
					List<ChangeRecord> successRecords = new ArrayList();
					List<ChangeRecord> failedRecords = new ArrayList();
					for(int index = 0 ; index < changeRecords.size() ; index++){
						ChangeRecord record = changeRecords.get(index);
						if(changeCjrxxLeadersByRecord(record)){
							System.out.println("成功的修改一条记录 " + record);
							successRecords.add(record);
						}else{
							failedRecords.add(record);
						}
					}				
					ServletUtils.renderJson(response, successRecords);
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *  
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/getTzsqBhByCjrxxId")
	public void getTzsqBhByCjrxxId(HttpServletRequest  request , HttpServletResponse response){
		String name = request.getParameter("name");
		Long cjrxxId =   leaveReplaceManager.getCjrxxIdByName(name);
		if(cjrxxId != null){
			List<XhTzsq> lendRecords = leaveReplaceManager.queryByCjrxxId(cjrxxId);
			List<String> tzNumbers = new ArrayList<String>(); 
			for(int index = 0 ; index < lendRecords.size() ; index++){
				tzNumbers.add(lendRecords.get(index).getTzsqbh());
			}
			ServletUtils.renderJson(response, tzNumbers);
		}
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/getCreateByByName")
	public void getCreateByByName(HttpServletRequest  request , HttpServletResponse response){
		String name = request.getParameter("name");
		List<XhcfCjrxx> cjrxxs = leaveReplaceManager.queryCjrxxByNameAndLeader(name, null);
		
		List<String> tzNumbers = new ArrayList<String>(); 
		for(int index = 0 ; index < cjrxxs.size() ; index++){
				tzNumbers.add(cjrxxs.get(index).getCreateBy());
		}
		ServletUtils.renderJson(response, tzNumbers);		
	}
	
	
	
	
	/***
	 * 
	 * @param record
	 * @return
	 */
	private boolean changeCjrxxLeadersByRecord(ChangeRecord record) {
		try{
			String tzsqBh = record.getUserNumAndLendNum().trim();
			int index = tzsqBh.lastIndexOf(" ");
			if(index > 0 ){
				record.setUserNumAndLendNum(tzsqBh.substring(index + 1));
			}
			leaveReplaceManager.changeCjrxxLeaders(record.getName(), record.getOldCrmName(), record.getCrmName(), record.getCcaName(),record.getUserNumAndLendNum());
		}catch (Exception e) {
			return false;
		}
		return true;
	}
}

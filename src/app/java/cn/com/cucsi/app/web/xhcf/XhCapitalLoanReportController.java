package cn.com.cucsi.app.web.xhcf;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sun.research.ws.wadl.Request;

import cn.com.cucsi.app.entity.baseinfo.BasePosition;
import cn.com.cucsi.app.entity.baseinfo.Tzcp;
import cn.com.cucsi.app.entity.xhcf.XhCapitalLoanReport;
import cn.com.cucsi.app.entity.xhcf.XhCapitalLoanReportInfo;
import cn.com.cucsi.app.entity.xhcf.XhZqtj;
import cn.com.cucsi.app.entity.xhcf.XhcfCjrxx;
import cn.com.cucsi.app.service.baseinfo.BaseInfoManager;
import cn.com.cucsi.app.service.xhcf.XhCapitalLoanReportManager;
import cn.com.cucsi.app.web.InitSetupListener;
import cn.com.cucsi.app.web.baseinfo.EmployeeController;
import cn.com.cucsi.app.web.util.CreditHarmonyComputeUtilties;
import cn.com.cucsi.app.web.util.FileUtil;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.utils.DBUUID;
import cn.com.cucsi.core.web.DwzResult;
import cn.com.cucsi.core.web.ServletUtils;

@Controller
@RequestMapping(value = "/xhCapitalLoanReport")
public class XhCapitalLoanReportController {

	private Logger logger = Logger
			.getLogger(XhCapitalLoanReportController.class);
	private static final Object countLock = new Object();

	@Autowired
	ServletContext context;
	private XhCapitalLoanReportManager xhCapitalLoanReportManager;

	@Autowired
	public void setXhCapitalLoanReportManager(
			XhCapitalLoanReportManager xhCapitalLoanReportManager) {

		this.xhCapitalLoanReportManager = xhCapitalLoanReportManager;
	}
	private BaseInfoManager baseInfoManager;
	
	@Autowired
	public void setBaseInfoManager(BaseInfoManager baseInfoManager) {
		this.baseInfoManager = baseInfoManager;
	}
	/**
	 * 生成资金出借报告
	 * 
	 * @param Id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/makeCapitalLoanReportFile/{Id}")
	public void makeCapitalLoanReportFile(@PathVariable Long Id,
			HttpServletRequest request, HttpServletResponse response) {
		DwzResult success = null;
		String first = null;
	    Object wordgenerate = null;
	    try{
	    	/*
		    synchronized(countLock){
	            wordgenerate = context.getAttribute("wordgenerate");
	            if ( wordgenerate != null) {
	                success = new DwzResult("300", "报告制作进程被占用，请稍候重新制作,",
	                        "", "", "", "");
	                ServletUtils.renderJson(response, success);
	                return ;
	            }
	            first = "not";
	            context.setAttribute("wordgenerate", request.getSession().getId());
	        }
	        */
			xhCapitalLoanReportManager.makeCapitalLoanReportFile(Id);
			String time = baseInfoManager.waiteTime("xh_zzcapital_loan_report");
			String time2 = baseInfoManager.waiteTime("xh_zqtj");
			int timeall = Integer.parseInt(time)+Integer.parseInt(time2);
			String mess = "操作成功,资金出借报告预计需要" + timeall + "分钟可以全部制作完成。";
			success = new DwzResult("200", mess,
					"rel_listXhCapitalLoanReport", "", "closeCurrent", "");
			ServletUtils.renderJson(response, success);
	    }finally{
	    	/*
            if("not".equals(first)){
                context.removeAttribute("wordgenerate");
            }
            */
	    }
	}

	@RequestMapping(value = "/listXhCapitalLoanReport")
	public String listXhCapitalLoanReport(HttpServletRequest request,
			Model model) {
		// 处理分页的参数
		Page<XhCapitalLoanReport> page = new Page<XhCapitalLoanReport>();
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

		Map<String, Object> map = ServletUtils.getParametersStartingWith2(
				request, "filter_");

		if (map.get("reportDate") == null) {
			GregorianCalendar calendar = new GregorianCalendar();

			if (calendar.get(Calendar.DATE) <= 15) {
				map.put("reportDate", "" + calendar.get(Calendar.YEAR) + "-"
						+ (calendar.get(Calendar.MONTH) + 1) + "-" + 15);
			} else if (calendar.get(Calendar.MONTH) != 2) {
				map.put("reportDate", "" + calendar.get(Calendar.YEAR) + "-"
						+ (calendar.get(Calendar.MONTH) + 1) + "-" + 30);
			} else if (calendar.isLeapYear(calendar.get(Calendar.YEAR))) {
				map.put("reportDate", "" + calendar.get(Calendar.YEAR) + "-"
						+ (calendar.get(Calendar.MONTH) + 1) + "-" + 29);
			} else {
				map.put("reportDate", "" + calendar.get(Calendar.YEAR) + "-"
						+ calendar.get(Calendar.MONTH) + "-" + 28);
			}
		}

		xhCapitalLoanReportManager.searchXhCapitalLoanReport(page, map);
		//投资产品
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("tzcpFl", "投资产品");
		List<Tzcp> tzcp = baseInfoManager.findTzcp(filters);
		request.setAttribute("tzcp", tzcp);
		
		//List<Date> reportDates = xhCapitalLoanReportManager.getAllReportDate();
		//model.addAttribute("reportDates", reportDates);
		model.addAttribute("page", page);
		model.addAttribute("map", map);
		return "jsgl/xhCapitalLoanReportIndex";

	}
	
	
	/**
	 * 下载对账文件(word格式)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/downLoadXhCapitalLoanReports")
	public synchronized ModelAndView downLoadXhCapitalLoanReports(HttpServletRequest request,
			HttpServletResponse response) {
		
	    String[] ids = request.getParameterValues("needDownWordId");
        Map<String, Object> map = new HashMap<String, Object>();
        List<Map<String, Object>> listReport;
        int count = ids != null ? ids.length : 0;    
        
        String mouFilePath2 = InitSetupListener.filePath + "report" + File.separator;
         File[] srcfile = new File[count];
         if (map.get("reportDate") == null) {
             GregorianCalendar calendar = new GregorianCalendar();

             if (calendar.get(Calendar.DATE) <= 15) {
                 map.put("reportDate", "" + calendar.get(Calendar.YEAR) + "-"
                         + (calendar.get(Calendar.MONTH) + 1) + "-" + 15);
             } else if (calendar.get(Calendar.MONTH) != 2) {
                 map.put("reportDate", "" + calendar.get(Calendar.YEAR) + "-"
                         + (calendar.get(Calendar.MONTH) + 1) + "-" + 30);
             } else if (calendar.isLeapYear(calendar.get(Calendar.YEAR))) {
                 map.put("reportDate", "" + calendar.get(Calendar.YEAR) + "-"
                         + (calendar.get(Calendar.MONTH) + 1) + "-" + 29);
             } else {
                 map.put("reportDate", "" + calendar.get(Calendar.YEAR) + "-"
                         + calendar.get(Calendar.MONTH) + "-" + 28);
             }
         }
         String fileName = "";
         String reportDate="";
         String filePath = "";
         String lendNumber ="";
         String cjrxm = "";
         String downLoadPath = "";
      
        for (int i = 0; i < count; i++) {
            String id = ids[i];
            map.put("id", id);
            listReport =  xhCapitalLoanReportManager.searchXhCapitalLoanReport("queryLoanReportListForDown", map);
           
            reportDate = listReport.get(0).get("REPORT_DATE").toString();
            filePath = mouFilePath2 + reportDate + File.separator;
            lendNumber = listReport.get(0).get("TZSQBH").toString();
            lendNumber = lendNumber.substring(lendNumber.lastIndexOf('-') + 1);
            cjrxm = listReport.get(0).get("CJRXM").toString();
            
            fileName = "资金出借报告-" + cjrxm + "-" + lendNumber+"("+listReport.get(0).get("ID")+")" + ".doc";
            
            downLoadPath = filePath + fileName;
            srcfile[i] = new File(downLoadPath);
            i++;
        }
        String zipFileName = DBUUID.getID()+".zip";
        String zipUrl = mouFilePath2+zipFileName;
        File zipfile=new File(zipUrl);
        FileUtil.zipFiles(srcfile, zipfile);
        FileUtil.downLoad(zipUrl,zipFileName,request,response);
        FileUtil.deleteFile(zipUrl);
        return null;
	}
	
	/**
	 * 下载对账文件(PDF格式)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/downLoadXhCapitalLoanReportsPDF")
	public synchronized ModelAndView downLoadXhCapitalLoanReportsPDF(HttpServletRequest request,
			HttpServletResponse response) {
		
	    String[] ids = request.getParameterValues("needDownPDFId");
        Map<String, Object> map = new HashMap<String, Object>();
        List<Map<String, Object>> listReport;
        int count = ids != null ? ids.length : 0;    
        
        String mouFilePath2 = InitSetupListener.filePath + "customerReport" + File.separator;
         File[] srcfile = new File[count];
         if (map.get("reportDate") == null) {
             GregorianCalendar calendar = new GregorianCalendar();

             if (calendar.get(Calendar.DATE) <= 15) {
                 map.put("reportDate", "" + calendar.get(Calendar.YEAR) + "-"
                         + (calendar.get(Calendar.MONTH) + 1) + "-" + 15);
             } else if (calendar.get(Calendar.MONTH) != 2) {
                 map.put("reportDate", "" + calendar.get(Calendar.YEAR) + "-"
                         + (calendar.get(Calendar.MONTH) + 1) + "-" + 30);
             } else if (calendar.isLeapYear(calendar.get(Calendar.YEAR))) {
                 map.put("reportDate", "" + calendar.get(Calendar.YEAR) + "-"
                         + (calendar.get(Calendar.MONTH) + 1) + "-" + 29);
             } else {
                 map.put("reportDate", "" + calendar.get(Calendar.YEAR) + "-"
                         + calendar.get(Calendar.MONTH) + "-" + 28);
             }
         }
         String fileName = "";
         String reportDate="";
         String filePath = "";
         String lendNumber ="";
         String cjrxm = "";
         String downLoadPath = "";
      
        for (int i = 0; i < count; i++) {
            String id = ids[i];
            map.put("id", id);
            listReport =  xhCapitalLoanReportManager.searchXhCapitalLoanReport("queryLoanReportListForDown", map);
           
            reportDate = listReport.get(0).get("REPORT_DATE").toString();
            filePath = mouFilePath2 + reportDate + File.separator;
            lendNumber = listReport.get(0).get("TZSQBH").toString();
            lendNumber = lendNumber.substring(lendNumber.lastIndexOf('-') + 1);
            cjrxm = listReport.get(0).get("CJRXM").toString();
            
            fileName = "资金出借报告-" + cjrxm + "-" + lendNumber+"("+listReport.get(0).get("ID")+")" + ".pdf";
            
            downLoadPath = filePath + fileName;
            srcfile[i] = new File(downLoadPath);
            i++;
        }
        String zipFileName = DBUUID.getID()+".zip";
        String zipUrl = mouFilePath2+zipFileName;
        File zipfile=new File(zipUrl);
        FileUtil.zipFiles(srcfile, zipfile);
        FileUtil.downLoad(zipUrl,zipFileName,request,response);
        FileUtil.deleteFile(zipUrl);
        return null;
	}
	
	
	@RequestMapping(value = "/saveXhCapitalLoanReport", method = RequestMethod.POST)
	public String saveXhCapitalLoanReport(
			@ModelAttribute("xhCapitalLoanReport") XhCapitalLoanReport xhCapitalLoanReport,
			HttpServletRequest request, HttpServletResponse response) {

		xhCapitalLoanReportManager.saveXhCapitalLoanReport(xhCapitalLoanReport);

		DwzResult success = new DwzResult("200", "保存成功",
				"rel_listXhCapitalLoanReport", "", "closeCurrent", "");
		ServletUtils.renderJson(response, success);

		return null;
	}

	@RequestMapping(value = "/addXhCapitalLoanReport", method = RequestMethod.GET)
	public ModelAndView add() {
		return new ModelAndView("jsgl/xhCapitalLoanReportInput",
				"xhCapitalLoanReport", new XhCapitalLoanReport());
	}

	@RequestMapping(value = "/editXhCapitalLoanReport/{Id}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable Long Id, Model model) {
		XhCapitalLoanReport xhCapitalLoanReport = xhCapitalLoanReportManager
				.getXhCapitalLoanReport(Id);
		List<XhCapitalLoanReportInfo> lendList = xhCapitalLoanReport
				.getXhCapitalLoanReportInfos();
		String lendNo = null;
		
		

		boolean flag = true;
		Map<String, XhCapitalLoanReportInfo> map = new HashMap();
		for (XhCapitalLoanReportInfo info : lendList) {

			lendNo = info.getLendNo();
			if (map.size() == 0 || !map.get(lendNo).getLendNo().equals(lendNo)) {

				map.put(lendNo, info);
				if (flag && !info.getLendType().equals("月息通")) {

					map.get(lendNo).setAllMoney(
							map.get(lendNo).getAllMoney()
									+ info.getFirstLendMoney());
					flag = false;

				}
			} else {
				map.get(lendNo).setShoudBack(
						(map.get(lendNo).getShoudBack() + info.getShoudBack()));
				map.get(lendNo).setRealBack(
						(map.get(lendNo).getRealBack() + info.getRealBack()));
				map.get(lendNo).setDrawing(
						(map.get(lendNo).getDrawing() + info.getDrawing()));
				map.get(lendNo).setLatePayMoney(
						(map.get(lendNo).getLatePayMoney() + info
								.getLatePayMoney()));

				map.get(lendNo).setReLend(
						(map.get(lendNo).getReLend() + info.getReLend()));
				if (info.getFirstLendDate().getTime() < map.get(lendNo)
						.getFirstLendDate().getTime()) {
					map.get(lendNo).setFirstLendDate(info.getFirstLendDate());
				}
				
				if (!info.getLendType().equals("月息通")) {

				
					map.get(lendNo).setAllMoney(
							map.get(lendNo).getAllMoney() + info.getAllMoney());
				}

			}

		}
		
		logger.info("map size is:" + map.size());
		model.addAttribute("map", map);
		model.addAttribute("Current_All_Money", map.get(lendNo).getAllMoney());

		return new ModelAndView("jsgl/xhCapitalLoanReportInput",
				"xhCapitalLoanReport", xhCapitalLoanReport);
	}

	@RequestMapping(value = "/delXhCapitalLoanReport/{Id}")
	public String delUser(@PathVariable Long Id, HttpServletResponse response) {
		xhCapitalLoanReportManager.deleteXhCapitalLoanReport(Id);
		DwzResult success = new DwzResult("200", "删除成功",
				"rel_listXhCapitalLoanReport", "", "", "");
		ServletUtils.renderJson(response, success);
		return null;
	}

	/**
	 * 批量生成报告
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/batchMakeCapitalLoanReport")
	public void batchDelUser(HttpServletRequest request,
			HttpServletResponse response) {
		DwzResult success = null;
		String first = null;
	    Object wordgenerate = null;
	    try{
		    synchronized(countLock){
	            wordgenerate = context.getAttribute("wordgenerate");
	            if ( wordgenerate != null) {
	                success = new DwzResult("300", "报告制作进程被占用，请稍候重新制作,",
	                        "", "", "", "");
	                ServletUtils.renderJson(response, success);
	                return ;
	            }
	            
	            first = "not";
	            context.setAttribute("wordgenerate", request.getSession().getId());
	        }
			String ids = request.getParameter("ids");
			String[] Ids = ids.split(",");
	
			for (int i = 0; i < Ids.length; i++) {
				xhCapitalLoanReportManager.makeCapitalLoanReportFile(Long
						.parseLong(Ids[i]));
				logger.info("正在生成资金出借报告，报告ID为：" + Ids[i]);
			}
			String time = baseInfoManager.waiteTime("xh_zzcapital_loan_report");
			String time2 = baseInfoManager.waiteTime("xh_zqtj");
			int timeall = Integer.parseInt(time)+Integer.parseInt(time2);
			String mess = "操作成功,资金出借报告预计需要" + timeall + "分钟可以全部制作完成。";
			success = new DwzResult("200", mess,
					"rel_listXhCapitalLoanReport", "", "", "");
			ServletUtils.renderJson(response, success);
	    }finally{
            if("not".equals(first)){
                context.removeAttribute("wordgenerate");
            }
	    }

	}
	
	/**
	 * 批量发送
	 * @param Id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/saveMailPl")
	public String saveMailPl(HttpServletRequest request,HttpServletResponse response){
		
		String ids = request.getParameter("ids");
		String[] Ids = ids.split(",");
		String id = "";
		
		for (int i = 0; i < Ids.length; i++) {
			id = Ids[i];
			XhCapitalLoanReport xhCapitalLoanReport = xhCapitalLoanReportManager.getXhCapitalLoanReport(Long.parseLong(id));
			xhCapitalLoanReport.setBillSendState("1");
			xhCapitalLoanReportManager.saveXhCapitalLoanReport(xhCapitalLoanReport);
		    xhCapitalLoanReportManager.saveXhSendemail(id);
		}

		DwzResult success = new DwzResult("200", "发送成功", "rel_listXhCapitalLoanReport", "",
				"", "");
		ServletUtils.renderJson(response, success);
		return null;
	}
	
	@RequestMapping(value = "/downReportFile")
	public String downReportFile(
			HttpServletRequest request,
			HttpServletResponse response, Model model){
		String Id = request.getParameter("id");
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("tzsqid", Long.parseLong(Id));
		List<XhCapitalLoanReport> files = xhCapitalLoanReportManager.findByTzsq(filters);
		request.setAttribute("files", files);
		return "tzsq/downReportFile";
	}
	/**
	 * 下载对账文件（PDF格式）
	 * 
	 * @return
	 */
	@RequestMapping(value = "/downLoadXhCapitalLoanReport")
	public ModelAndView getInputStream(HttpServletRequest request,
			HttpServletResponse response) {
		String id = request.getParameter("id");
		Long Id = Long.parseLong(id);
		XhCapitalLoanReport xhCapitalLoanReport = xhCapitalLoanReportManager
				.getXhCapitalLoanReport(Id);
		Map filters = new HashMap();
		filters.put("id", id);
		List<Map<String, Object>> reportInfos = xhCapitalLoanReportManager.searchXhCapitalLoanReport(
				"queryXhCapitalLoanReportList", filters);
		String lendNumber = (String) reportInfos.get(0).get("LEND_NO");
		XhcfCjrxx lender = xhCapitalLoanReport.getLender();
		
		lendNumber = lendNumber.substring(lendNumber.lastIndexOf('-') + 1);
		String reportDate = CreditHarmonyComputeUtilties
				.dateToString(xhCapitalLoanReport.getReportDate());
		// 资金出借报告-" + lender.getCjrxm() + "-"+reportDate + "
//		String fileName = "资金出借报告-" + lender.getCjrxm() + "-" + lendNumber+"("+id+")" + ".doc";
		String fileName = "资金出借报告-" + lender.getCjrxm() + "-" + lendNumber+"("+id+")" + ".pdf";
		/*
		String mouFilePath2 = InitSetupListener.filePath + "report"
				+ File.separator;
		String filePath = mouFilePath2 + reportDate
				+ File.separator;
		*/
		String mouFilePathTz = InitSetupListener.filePath + "customerReport"
                + File.separator;
		String filePath = mouFilePathTz + reportDate
				+ File.separator;
		System.out.println("filePath===>" + filePath);
		String downLoadPath = filePath + fileName;
		
		FileUtil.downLoad(downLoadPath,fileName,request,response);
		return null;
	}
	
	/**
	 * 下载对账文件（word格式）
	 * 
	 * @return
	 */
	@RequestMapping(value = "/downLoadXhCapitalLoanReportWord")
	public ModelAndView getInputStreamWord(HttpServletRequest request,
			HttpServletResponse response) {
		String id = request.getParameter("id");
		Long Id = Long.parseLong(id);
		XhCapitalLoanReport xhCapitalLoanReport = xhCapitalLoanReportManager
				.getXhCapitalLoanReport(Id);
		Map filters = new HashMap();
		filters.put("id", id);
		List<Map<String, Object>> reportInfos = xhCapitalLoanReportManager.searchXhCapitalLoanReport(
				"queryXhCapitalLoanReportList", filters);
		String lendNumber = (String) reportInfos.get(0).get("LEND_NO");
		XhcfCjrxx lender = xhCapitalLoanReport.getLender();
		
		lendNumber = lendNumber.substring(lendNumber.lastIndexOf('-') + 1);
		String reportDate = CreditHarmonyComputeUtilties
				.dateToString(xhCapitalLoanReport.getReportDate());
		// 资金出借报告-" + lender.getCjrxm() + "-"+reportDate + "
		String fileName = "资金出借报告-" + lender.getCjrxm() + "-" + lendNumber+"("+id+")" + ".doc";
		//String fileName = "资金出借报告-" + lender.getCjrxm() + "-" + lendNumber+"("+id+")" + ".pdf";
		/*
		String mouFilePath2 = InitSetupListener.filePath + "report"
				+ File.separator;
		String filePath = mouFilePath2 + reportDate
				+ File.separator;
		*/
		String mouFilePathTz = InitSetupListener.filePath + "report"
                + File.separator;
		String filePath = mouFilePathTz + reportDate
				+ File.separator;
		System.out.println("filePath===>" + filePath);
		String downLoadPath = filePath + fileName;
		
		FileUtil.downLoad(downLoadPath,fileName,request,response);
		return null;
	}
	/**
	 * 查看出借资金报告
	 */
	@RequestMapping(value="/lookXhCapitalLoanReport",method=RequestMethod.GET)
	public String lookReport(HttpServletRequest request,HttpServletResponse response,Model model){
		String id=request.getParameter("id");
		Long Id=Long.parseLong(id);
		XhCapitalLoanReport xhCapitalLoanReport = xhCapitalLoanReportManager
		.getXhCapitalLoanReport(Id);
		//根据资金出借报告取出借编号
		String lendNumber = xhCapitalLoanReport.getXhCapitalLoanReportInfos().get(0).getLendNo();
		System.out.println("出借编号=======>"+lendNumber);
		XhcfCjrxx lender = xhCapitalLoanReport.getLender();
		
		lendNumber = lendNumber.substring(lendNumber.lastIndexOf('-') + 1);
		String reportDate = CreditHarmonyComputeUtilties
				.dateToString(xhCapitalLoanReport.getReportDate());
		String fileName = "资金出借报告-" + lender.getCjrxm() + "-" + lendNumber+"("+id+")"
						   + ".swf";
		String filePath = reportDate+File.separator+fileName;
		//${fp}/customerReport/${reportDate}/${fileName}
		String swffilePath = InitSetupListener.filePath + "customerReport" + File.separator + reportDate + File.separator + fileName;
        File swfFile = new File(swffilePath);
        if(!swfFile.exists()){
            model.addAttribute("swfServer",InitSetupListener.swfBackServer);
        }else{
            model.addAttribute("swfServer",InitSetupListener.swfServer);
        }
		model.addAttribute("fileName",fileName);
		model.addAttribute("reportDate",reportDate);
		return "jsgl/lookXhCapitalLoanReport";
	}
}

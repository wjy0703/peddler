package cn.com.cucsi.app.web.excel;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.util.WebUtils;

import cn.com.cucsi.app.entity.baseinfo.Employee;
import cn.com.cucsi.app.excel.bean.ExcelConfiguration;
import cn.com.cucsi.app.excel.bean.ExcelExportEntity;
import cn.com.cucsi.app.excel.bean.ExcelModelConfiguration;
import cn.com.cucsi.app.excel.bean.ExcelModelTaskParam;
import cn.com.cucsi.app.excel.bean.ExcelParamOutsideBean;
import cn.com.cucsi.app.excel.task.ExcelExportUtils2003;
import cn.com.cucsi.app.service.baseinfo.BaseInfoManager;
import cn.com.cucsi.app.service.excel.IExcelServiceBase;
import cn.com.cucsi.app.service.security.OperatorDetails;
import cn.com.cucsi.app.web.InitSetupListener;
import cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils;

/**
 * 
 * @author xjs
 *
 */
@Controller
public class ExcelTaskController {

	@Autowired
	@Qualifier("excelConfiguration")
	ExcelConfiguration excelConfiguration;
    
	@Autowired
	BaseInfoManager baseInfoManager;
	
	Logger log = Logger.getLogger(ExcelTaskController.class);

	@RequestMapping("/exportExcel")
	public ResponseEntity<String> exportExcel(ExcelParamOutsideBean excelParamOutsideBean,HttpServletRequest request,HttpServletResponse response) {
		//得到当前登录用户
		OperatorDetails operator = SpringSecurityUtils.getCurrentUser();
		int success = 1;
		try {
			// excelService,取表中数据时用到的service
			IExcelServiceBase excelService = (IExcelServiceBase) RequestContextUtils.getWebApplicationContext(request).getBean(excelParamOutsideBean.getServiceName());
			//取得相应的配置文件，在xml中定义
			ExcelModelConfiguration excelModelConfiguration = excelConfiguration.getSingleConfigMap().get(excelParamOutsideBean.getServiceName());
			//相应的配置信息，在导出excel时用到
			ExcelModelTaskParam excelModelTaskParam = new ExcelModelTaskParam();
			excelModelTaskParam.setRowsPerSheet(excelConfiguration.getRowsPerSheet());
			String dir = InitSetupListener.filePath + "excel"  + File.separator;
			excelModelTaskParam.setDir(dir);
			Map<String, Object> map = WebUtils.getParametersStartingWith(request, "filter_");
			//过滤查询内容，所需条件   ----开始
			Employee emp = baseInfoManager.getUserEmployee();
			String loginName = operator.getCtiCode();
			map.put("loginName", loginName);
			map.put("emp", emp);
			//过滤查询内容，所需条件   ----结束
	
			ExcelExportEntity result = excelService.queryExcelRows(map);
			new ExcelExportUtils2003(excelModelTaskParam, excelModelConfiguration,excelParamOutsideBean).exportExcelToFile(result,response);
		} catch (Exception e) {
			success = 0;
			e.printStackTrace();
			log.debug("发生异常返回-1");
		}
		//ServletUtils.renderJson(response, success);
		return null;
	}
}

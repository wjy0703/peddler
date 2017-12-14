package cn.com.cucsi.app.service.excel;

import java.util.List;
import java.util.Map;

import cn.com.cucsi.app.excel.bean.ExcelExportEntity;

/**
 * 
 * @author xjs
 * 所有需要导出excel service的接口
 */
public interface IExcelServiceBase {

    ExcelExportEntity queryExcelRows(Map<String, Object> params);
    
    List<Map<String,Object>> queryRows(Map<String, Object> params);
}

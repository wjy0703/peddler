package cn.com.cucsi.app.service.excel;

import java.util.List;
import java.util.Map;

import cn.com.cucsi.app.excel.bean.ExcelExportEntity;

/**
 * 
 * @author xjs
 * 所有需要导出excel service的父类
 */
public abstract class  AbstractExcelService implements IExcelServiceBase {

	@Override
	public ExcelExportEntity queryExcelRows(Map<String, Object> params) {
		ExcelExportEntity excelExportEntity = new ExcelExportEntity();
		List<Map<String, Object>> rows = queryRows(params);
		if( rows != null){
			excelExportEntity.setRows(rows);
			excelExportEntity.setTotal(rows.size());
		}
		return excelExportEntity;
	}

	@Override
	public abstract List<Map<String, Object>> queryRows(Map<String, Object> params);
}

package cn.com.cucsi.app.service.excel;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.app.service.xhcf.XhTzsqManager;

@Service
public class TzsqRefusedExcelService extends AbstractExcelService {
	@Autowired
	JdbcDao jdbcDao;
	
	@Override
	public List<Map<String, Object>> queryRows(Map<String,Object> conditions) {
		return jdbcDao.searchByMergeSqlTemplate("queryTzsqRefusedList", new XhTzsqManager().conditions(conditions));
	}
}

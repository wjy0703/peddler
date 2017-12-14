package cn.com.cucsi.app.service.excel;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.cucsi.app.dao.JdbcDao;

@Service
public class TestExcelService extends AbstractExcelService {

	@Autowired
	JdbcDao jdbcDao;
	
	@Override
	public List<Map<String, Object>> queryRows(Map<String, Object> params) {
		String sql = "select ID as num,name as name,SEX as sex from BASE_EMPLOYEE where STS = 1 and sts = ?"; 
		List<Map<String, Object>> values = jdbcDao.selectBySql(sql, new Object[]{1});
		return values;
	}

}

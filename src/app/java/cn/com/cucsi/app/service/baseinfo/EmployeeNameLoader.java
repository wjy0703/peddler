package cn.com.cucsi.app.service.baseinfo;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.com.cucsi.app.dao.baseinfo.AttrJdbcDao;

import com.google.common.cache.CacheLoader;


@Component("employeeNameLoader")
public class EmployeeNameLoader extends CacheLoader<Long,String> {

	@Autowired
	EmployeeNameManager employeeNameManager; 
	
	@Override
	public String load(Long id) throws Exception {
		return employeeNameManager.getName(id);
	}

}

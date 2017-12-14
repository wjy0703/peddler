package cn.com.cucsi.app.service.baseinfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.app.dao.baseinfo.EmployeeDao;
import cn.com.cucsi.app.dao.xhcf.CjrxxDao;
import cn.com.cucsi.app.dao.xhcf.UpdateCjrxxHistoryDao;
import cn.com.cucsi.app.entity.baseinfo.Employee;
import cn.com.cucsi.app.entity.xhcf.UpdateCjrxxHistory;
import cn.com.cucsi.app.entity.xhcf.XhcfCjrxx;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.utils.PropertiesUtils;

//Spring Bean的标识.
@Component
//默认将类中的所有函数纳入事务管理.
@Transactional
public class EmployeeManager {

	@Autowired
	private EmployeeDao employeeDao;
	
	public List<Employee> queryByName(String name){
	    return employeeDao.queryByName(name);
	}	
}

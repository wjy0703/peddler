package cn.com.cucsi.vechicle.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.entity.xhcf.XhJksqState;
import cn.com.cucsi.app.service.PublicService;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.vechicle.dao.XhCarStateDao;
import cn.com.cucsi.vechicle.entity.xhcf.XhCarState;
//Spring Bean的标识.
@Component
//默认将类中的所有函数纳入事务管理.
@Transactional
public class CarStateManager extends PublicService{
	
	private final static String GET_TASK_BY_EMPLOYEE_SQL = "getTaskByStateAndEmployee";
	
	
	@Autowired
	private XhCarStateDao xhCarStateDao;
	
	
	@Transactional(readOnly = true)
	public void CarStateHistoryList(Long Id, Page<XhCarState> page) {
		xhCarStateDao.query(page,Id);
		
	}

}

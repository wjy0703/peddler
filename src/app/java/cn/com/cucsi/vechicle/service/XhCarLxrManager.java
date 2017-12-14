package cn.com.cucsi.vechicle.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.utils.PropertiesUtils;
import cn.com.cucsi.vechicle.dao.XhCarLoanUserDao;
import cn.com.cucsi.vechicle.dao.XhCarLxrDao;
import cn.com.cucsi.vechicle.entity.xhcf.XhCarLoanUser;
import cn.com.cucsi.vechicle.entity.xhcf.XhCarLxr;



//Spring Bean的标识.
@Component
//默认将类中的所有函数纳入事务管理.
@Transactional
public class XhCarLxrManager {

	private XhCarLxrDao xhCarLxrDao;
	@Autowired
	public void setXhCarLxrDao(XhCarLxrDao xhCarLxrDao) {
		this.xhCarLxrDao = xhCarLxrDao;
	}
	private JdbcDao jdbcDao;
	
	@Autowired
	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}
	@Transactional(readOnly = true)
	public XhCarLxr getXhCarLxr(Long id) {
		return xhCarLxrDao.get(id);
	}


	/**
	 * 删除用户,如果尝试删除超级管理员将抛出异常.
	 */
	public void deleteXhCarLxr(Long id) {
		xhCarLxrDao.delete(id);
	}
	
	public boolean batchDelXhCarLxr(String[] ids){
		
		try {
			for(String id: ids){
				deleteXhCarLxr(Long.valueOf(id));
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
}

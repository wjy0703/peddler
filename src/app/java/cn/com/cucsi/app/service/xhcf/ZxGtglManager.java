package cn.com.cucsi.app.service.xhcf;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.app.dao.xhcf.ZxGtglDao;
import cn.com.cucsi.app.entity.xhcf.ZxGtjl;
import cn.com.cucsi.core.orm.Page;

//Spring Bean的标识.
@Component
//默认将类中的所有函数纳入事务管理.
@Transactional
public class ZxGtglManager {
	private ZxGtglDao zxGtglDao;
	@Autowired
	public void setZxGtglDao(ZxGtglDao zxGtglDao) {
		this.zxGtglDao = zxGtglDao;
	}
	private JdbcDao jdbcDao;
	
	@Autowired
	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}
	@Transactional(readOnly = true)
	public Page<ZxGtjl> searchZxGtjl(final Page<ZxGtjl> page, final Map<String,Object> filters) {
		return zxGtglDao.queryZxGtjl(page, filters);
	}
	@Transactional(readOnly = true)
	public ZxGtjl getZxGtjl(Long id) {
		return zxGtglDao.get(id);
	}

	public void saveZxGtjl(ZxGtjl entity) {
		zxGtglDao.save(entity);
	}
	/**
	 * 删除用户,如果尝试删除超级管理员将抛出异常.
	 */
	public void deleteZxGtjl(Long id) {
		zxGtglDao.delete(id);
	}
	
	public boolean batchDelZxGtjl(String[] ids){
		
		try {
			for(String id: ids){
				deleteZxGtjl(Long.valueOf(id));
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
}

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
import cn.com.cucsi.vechicle.entity.xhcf.XhCarLoanUser;



//Spring Bean的标识.
@Component
//默认将类中的所有函数纳入事务管理.
@Transactional
public class XhCarLoanUserManager {

	private XhCarLoanUserDao xhCarLoanUserDao;
	@Autowired
	public void setXhCarLoanUserDao(XhCarLoanUserDao xhCarLoanUserDao) {
		this.xhCarLoanUserDao = xhCarLoanUserDao;
	}
	private JdbcDao jdbcDao;
	
	@Autowired
	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}
	@Transactional(readOnly = true)
	public Page<XhCarLoanUser> searchXhCarLoanUser(final Page<XhCarLoanUser> page, final Map<String,Object> filters) {
		return xhCarLoanUserDao.queryXhCarLoanUser(page, filters);
	}
	@Transactional(readOnly = true)
	public XhCarLoanUser getXhCarLoanUser(Long id) {
		return xhCarLoanUserDao.get(id);
	}

	public void saveXhCarLoanUser(XhCarLoanUser entity) {
		xhCarLoanUserDao.save(entity);
	}

	/**
	 * 删除用户,如果尝试删除超级管理员将抛出异常.
	 */
	public void deleteXhCarLoanUser(Long id) {
		xhCarLoanUserDao.delete(id);
	}
	
	public boolean batchDelXhCarLoanUser(String[] ids){
		
		try {
			for(String id: ids){
				deleteXhCarLoanUser(Long.valueOf(id));
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Transactional(readOnly = true)
	public List<Map<String,Object>> searchXhCarLoanUser(String queryName,Map<String,Object> filter,JdbcPage page)
	{
		String sql="";
		String value = "";
		//借款人姓名
		if(filter.containsKey("userName")){
			value = String.valueOf(filter.get("userName"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.USER_NAME like '%" +  value + "%'";
			}
		}
		
		if(filter.containsKey("crmprovince")){
			value = String.valueOf(filter.get("crmprovince"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CRMPROVINCE =" +  value + "";
			}
		}
		
		if(filter.containsKey("crmcity")){
			value = String.valueOf(filter.get("crmcity"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CRMCITY = " +  value + "";
			}
		}
		
		if(filter.containsKey("crmarea")){
			value = String.valueOf(filter.get("crmarea"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.crmarea = " +  value + "";
			}
		}
		//权限过滤查询sql
		sql = sql + PropertiesUtils.getCarSql();
		
		if (page.getOrderBy()!=null){
			sql = sql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		
		System.out.println("sql=======>" + sql);
		Map<String, Object> conditions = new HashMap<String,Object>();
		conditions.put("sql", sql);
		
		return jdbcDao.searchPagesByMergeSqlTemplate(queryName, conditions, page);
	}
	
	/**
	 * 检查是否唯一.
	 *propertyName 所要验证的字段名
	 * @return newValue在数据库中唯一或等于oldValue时返回true.
	 */
	@Transactional(readOnly = true)
	public boolean isCheckUnique(String propertyName,String newValue, String oldValue) {
		return xhCarLoanUserDao.isPropertyUnique(propertyName, newValue, oldValue);
	}
	
}

package cn.com.cucsi.app.service.baseinfo;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.app.dao.baseinfo.PositionDao;
import cn.com.cucsi.app.entity.baseinfo.BasePosition;

import cn.com.cucsi.app.service.ServiceException;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils;
import cn.com.cucsi.core.utils.EncodeUtils;
import cn.com.cucsi.core.utils.PropertiesUtils;



//Spring Bean的标识.
@Component
//默认将类中的所有函数纳入事务管理.
@Transactional
public class BasePositionManager {

	private PositionDao basePositionDao;
	@Autowired
	public void setBasePositionDao(PositionDao basePositionDao) {
		this.basePositionDao = basePositionDao;
	}
	private JdbcDao jdbcDao;
	
	@Autowired
	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}
	@Transactional(readOnly = true)
	public Page<BasePosition> searchBasePosition(final Page<BasePosition> page, final Map<String,Object> filters) {
		return basePositionDao.queryBasePosition(page, filters);
	}
	@Transactional(readOnly = true)
	public BasePosition getBasePosition(Long id) {
		return basePositionDao.get(id);
	}

	public void saveBasePosition(BasePosition entity) {
		basePositionDao.save(entity);
	}

	/**
	 * 删除用户,如果尝试删除超级管理员将抛出异常.
	 */
	public void deleteBasePosition(Long id) {
		basePositionDao.delete(id);
	}
	
	public boolean batchDelBasePosition(String[] ids){
		
		try {
			for(String id: ids){
				deleteBasePosition(Long.valueOf(id));
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Transactional(readOnly = true)
	public List<Map<String,Object>> searchBasePosition(String queryName,Map<String,Object> filter,JdbcPage page)
	{
		String sql="";
		String value = "";
		//职务ID
		if(filter.containsKey("id")){
			value = String.valueOf(filter.get("id"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ID = '" +  value + "'";
			}
		}
		//职务ID
		if(filter.containsKey("id")){
			value = String.valueOf(filter.get("id"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ID = '" +  value + "'";
			}
		}
		//职务名称
		if(filter.containsKey("positionName")){
			value = String.valueOf(filter.get("positionName"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.POSITION_NAME = '" +  value + "'";
			}
		}
		//职务名称
		if(filter.containsKey("positionName")){
			value = String.valueOf(filter.get("positionName"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.POSITION_NAME = '" +  value + "'";
			}
		}
		//职务权限级别
		if(filter.containsKey("positionCode")){
			value = String.valueOf(filter.get("positionCode"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.POSITION_CODE = '" +  value + "'";
			}
		}
		//职务权限级别
		if(filter.containsKey("positionCode")){
			value = String.valueOf(filter.get("positionCode"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.POSITION_CODE = '" +  value + "'";
			}
		}
		//职级标准
		if(filter.containsKey("positionLevel")){
			value = String.valueOf(filter.get("positionLevel"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.POSITION_LEVEL = '" +  value + "'";
			}
		}
		//职级标准
		if(filter.containsKey("positionLevel")){
			value = String.valueOf(filter.get("positionLevel"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.POSITION_LEVEL = '" +  value + "'";
			}
		}
		//职级英文编码
		if(filter.containsKey("positionLevelCode")){
			value = String.valueOf(filter.get("positionLevelCode"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.POSITION_LEVEL_CODE = '" +  value + "'";
			}
		}
		//职级VALUE
		if(filter.containsKey("positionLevelValue")){
			value = String.valueOf(filter.get("positionLevelValue"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.POSITION_LEVEL_VALUE = '" +  value + "'";
			}
		}
		
		if (page.getOrderBy()!=null){
			sql = sql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		
		System.out.println("sql=======>" + sql);
		Map<String, Object> conditions = new HashMap<String,Object>();
		conditions.put("sql", sql);
		
		return jdbcDao.searchPagesByMergeSqlTemplate(queryName, conditions, page);
	}
}

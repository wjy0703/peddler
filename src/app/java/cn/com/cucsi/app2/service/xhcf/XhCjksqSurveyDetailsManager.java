package cn.com.cucsi.app2.service.xhcf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.app2.dao.xhcf.XhCjksqSurveyDetailsDao;
import cn.com.cucsi.app2.entity.xhcf.XhCjksqSurveyDetail;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.orm.Page;



//Spring Bean的标识.
@Component
//默认将类中的所有函数纳入事务管理.
@Transactional
public class XhCjksqSurveyDetailsManager {

	private XhCjksqSurveyDetailsDao xhCjksqSurveyDetailsDao;
	@Autowired
	public void setXhCjksqSurveyDetailsDao(XhCjksqSurveyDetailsDao xhCjksqSurveyDetailsDao) {
		this.xhCjksqSurveyDetailsDao = xhCjksqSurveyDetailsDao;
	}
	private JdbcDao jdbcDao;
	
	@Autowired
	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}
	@Transactional(readOnly = true)
	public Page<XhCjksqSurveyDetail> searchXhCjksqSurveyDetails(final Page<XhCjksqSurveyDetail> page, final Map<String,Object> filters) {
		return xhCjksqSurveyDetailsDao.queryXhCjksqSurveyDetails(page, filters);
	}
	@Transactional(readOnly = true)
	public XhCjksqSurveyDetail getXhCjksqSurveyDetails(Long id) {
		return xhCjksqSurveyDetailsDao.get(id);
	}

	public void saveXhCjksqSurveyDetails(XhCjksqSurveyDetail entity) {
		xhCjksqSurveyDetailsDao.save(entity);
	}

	/**
	 * 删除用户,如果尝试删除超级管理员将抛出异常.
	 */
	public void deleteXhCjksqSurveyDetails(Long id) {
		xhCjksqSurveyDetailsDao.delete(id);
	}
	
	public boolean batchDelXhCjksqSurveyDetails(String[] ids){
		
		try {
			for(String id: ids){
				deleteXhCjksqSurveyDetails(Long.valueOf(id));
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Transactional(readOnly = true)
	public List<Map<String,Object>> searchXhCjksqSurveyDetails(String queryName,Map<String,Object> filter,JdbcPage page)
	{
		String sql="";
		String value = "";
		//外访具体条目(对应枚举类中的内容)
		if(filter.containsKey("itemsName")){
			value = String.valueOf(filter.get("itemsName"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ITEMS_NAME = '" +  value + "'";
			}
		}
		//外访具体条目结果0:否  1：通过
		if(filter.containsKey("itemsResult")){
			value = String.valueOf(filter.get("itemsResult"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ITEMS_RESULT = '" +  value + "'";
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

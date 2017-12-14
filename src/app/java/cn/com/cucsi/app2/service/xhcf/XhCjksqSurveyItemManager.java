package cn.com.cucsi.app2.service.xhcf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.app2.dao.xhcf.XhCjksqSurveyItemDao;
import cn.com.cucsi.app2.entity.xhcf.XhCjksqSurveyItem;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.orm.Page;



//Spring Bean的标识.
@Component
//默认将类中的所有函数纳入事务管理.
@Transactional
public class XhCjksqSurveyItemManager {

	private XhCjksqSurveyItemDao xhCjksqSurveyItemDao;
	@Autowired
	public void setXhCjksqSurveyItemDao(XhCjksqSurveyItemDao xhCjksqSurveyItemDao) {
		this.xhCjksqSurveyItemDao = xhCjksqSurveyItemDao;
	}
	private JdbcDao jdbcDao;
	
	@Autowired
	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}
	@Transactional(readOnly = true)
	public Page<XhCjksqSurveyItem> searchXhCjksqSurveyItem(final Page<XhCjksqSurveyItem> page, final Map<String,Object> filters) {
		return xhCjksqSurveyItemDao.queryXhCjksqSurveyItem(page, filters);
	}
	@Transactional(readOnly = true)
	public XhCjksqSurveyItem getXhCjksqSurveyItem(Long id) {
		return xhCjksqSurveyItemDao.get(id);
	}

	public void saveXhCjksqSurveyItem(XhCjksqSurveyItem entity) {
		xhCjksqSurveyItemDao.save(entity);
	}

	/**
	 * 删除用户,如果尝试删除超级管理员将抛出异常.
	 */
	public void deleteXhCjksqSurveyItem(Long id) {
		xhCjksqSurveyItemDao.delete(id);
	}
	
	public boolean batchDelXhCjksqSurveyItem(String[] ids){
		
		try {
			for(String id: ids){
				deleteXhCjksqSurveyItem(Long.valueOf(id));
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Transactional(readOnly = true)
	public List<Map<String,Object>> searchXhCjksqSurveyItem(String queryName,Map<String,Object> filter,JdbcPage page)
	{
		String sql="";
		String value = "";
		//外访要求
		if(filter.containsKey("demandWords")){
			value = String.valueOf(filter.get("demandWords"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.DEMAND_WORDS = '" +  value + "'";
			}
		}
		//外访回复
		if(filter.containsKey("demandrReply")){
			value = String.valueOf(filter.get("demandrReply"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.DEMANDR_REPLY = '" +  value + "'";
			}
		}
		//外访类型   0:外访家庭，1:外访单位
		if(filter.containsKey("surveyType")){
			value = String.valueOf(filter.get("surveyType"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.SURVEY_TYPE = '" +  value + "'";
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

package cn.com.cucsi.app2.service.xhcf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.app2.dao.xhcf.XhCjksqVechicleDao;
import cn.com.cucsi.app2.entity.xhcf.XhCjksqVechicle;
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
public class XhCjksqVechicleManager {

	private XhCjksqVechicleDao xhCjksqVechicleDao;
	@Autowired
	public void setXhCjksqVechicleDao(XhCjksqVechicleDao xhCjksqVechicleDao) {
		this.xhCjksqVechicleDao = xhCjksqVechicleDao;
	}
	private JdbcDao jdbcDao;
	
	@Autowired
	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}
	@Transactional(readOnly = true)
	public Page<XhCjksqVechicle> searchXhCjksqVechicle(final Page<XhCjksqVechicle> page, final Map<String,Object> filters) {
		return xhCjksqVechicleDao.queryXhCjksqVechicle(page, filters);
	}
	@Transactional(readOnly = true)
	public XhCjksqVechicle getXhCjksqVechicle(Long id) {
		return xhCjksqVechicleDao.get(id);
	}

	public void saveXhCjksqVechicle(XhCjksqVechicle entity) {
		xhCjksqVechicleDao.save(entity);
	}

	/**
	 * 删除用户,如果尝试删除超级管理员将抛出异常.
	 */
	public void deleteXhCjksqVechicle(Long id) {
		xhCjksqVechicleDao.delete(id);
	}
	
	public boolean batchDelXhCjksqVechicle(String[] ids){
		
		try {
			for(String id: ids){
				deleteXhCjksqVechicle(Long.valueOf(id));
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Transactional(readOnly = true)
	public List<Map<String,Object>> searchXhCjksqVechicle(String queryName,Map<String,Object> filter,JdbcPage page)
	{
		String sql="";
		String value = "";
		//注册日期
		if(filter.containsKey("registerDate")){
			value = String.valueOf(filter.get("registerDate"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.REGISTER_DATE = '" +  value + "'";
			}
		}
		//登记日期
		if(filter.containsKey("dengjiDate")){
			value = String.valueOf(filter.get("dengjiDate"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.DENGJIDATE = '" +  value + "'";
			}
		}
		//产权归属
		if(filter.containsKey("owener")){
			value = String.valueOf(filter.get("owener"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.OWENER = '" +  value + "'";
			}
		}
		//有无抵押 0:无 1:有
		if(filter.containsKey("endorse")){
			value = String.valueOf(filter.get("endorse"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ENDORSE = '" +  value + "'";
			}
		}
		//借款余额
		if(filter.containsKey("borrowmoney")){
			value = String.valueOf(filter.get("borrowmoney"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.BORROWMONEY = '" +  value + "'";
			}
		}
		//估值
		if(filter.containsKey("estimateValue")){
			value = String.valueOf(filter.get("estimateValue"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ESTIMATE_VALUE = '" +  value + "'";
			}
		}
		//估值/确认途径
		if(filter.containsKey("valueWay")){
			value = String.valueOf(filter.get("valueWay"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.VALUE_WAY = '" +  value + "'";
			}
		}
		//市场报价(类似备注)
		if(filter.containsKey("marchetValueComment")){
			value = String.valueOf(filter.get("marchetValueComment"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.MARCHET_VALUE_COMMENT = '" +  value + "'";
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

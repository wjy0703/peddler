package cn.com.cucsi.app2.service.xhcf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.app2.dao.xhcf.XhCjksqBankRecordsDao;
import cn.com.cucsi.app2.entity.xhcf.XhCjksqBankRecords;
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
public class XhCjksqBankRecordsManager {

	private XhCjksqBankRecordsDao xhCjksqBankRecordsDao;
	@Autowired
	public void setXhCjksqBankRecordsDao(XhCjksqBankRecordsDao xhCjksqBankRecordsDao) {
		this.xhCjksqBankRecordsDao = xhCjksqBankRecordsDao;
	}
	private JdbcDao jdbcDao;
	
	@Autowired
	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}
	@Transactional(readOnly = true)
	public Page<XhCjksqBankRecords> searchXhCjksqBankRecords(final Page<XhCjksqBankRecords> page, final Map<String,Object> filters) {
		return xhCjksqBankRecordsDao.queryXhCjksqBankRecords(page, filters);
	}
	@Transactional(readOnly = true)
	public XhCjksqBankRecords getXhCjksqBankRecords(Long id) {
		return xhCjksqBankRecordsDao.get(id);
	}

	public void saveXhCjksqBankRecords(XhCjksqBankRecords entity) {
		xhCjksqBankRecordsDao.save(entity);
	}

	/**
	 * 删除用户,如果尝试删除超级管理员将抛出异常.
	 */
	public void deleteXhCjksqBankRecords(Long id) {
		xhCjksqBankRecordsDao.delete(id);
	}
	
	public boolean batchDelXhCjksqBankRecords(String[] ids){
		
		try {
			for(String id: ids){
				deleteXhCjksqBankRecords(Long.valueOf(id));
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Transactional(readOnly = true)
	public List<Map<String,Object>> searchXhCjksqBankRecords(String queryName,Map<String,Object> filter,JdbcPage page)
	{
		String sql="";
		String value = "";
		//余额数对应的截至日期
		if(filter.containsKey("currentDate")){
			value = String.valueOf(filter.get("currentDate"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CURRENT_DATE = '" +  value + "'";
			}
		}
		//余额
		if(filter.containsKey("remainAmount")){
			value = String.valueOf(filter.get("remainAmount"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.REMAIN_AMOUNT = '" +  value + "'";
			}
		}
		//银行(下拉)
		if(filter.containsKey("bank")){
			value = String.valueOf(filter.get("bank"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.BANK = '" +  value + "'";
			}
		}
		//月份
		if(filter.containsKey("one")){
			value = String.valueOf(filter.get("one"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ONE = '" +  value + "'";
			}
		}
		//月收入情况
		if(filter.containsKey("onecount")){
			value = String.valueOf(filter.get("onecount"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ONECOUNT = '" +  value + "'";
			}
		}
		//月份
		if(filter.containsKey("two")){
			value = String.valueOf(filter.get("two"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.TWO = '" +  value + "'";
			}
		}
		//月收入情况
		if(filter.containsKey("twocount")){
			value = String.valueOf(filter.get("twocount"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.TWOCOUNT = '" +  value + "'";
			}
		}
		//月份
		if(filter.containsKey("three")){
			value = String.valueOf(filter.get("three"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.THREE = '" +  value + "'";
			}
		}
		//月收入情况
		if(filter.containsKey("threecount")){
			value = String.valueOf(filter.get("threecount"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.THREECOUNT = '" +  value + "'";
			}
		}
		//月份
		if(filter.containsKey("four")){
			value = String.valueOf(filter.get("four"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.FOUR = '" +  value + "'";
			}
		}
		//月收入情况
		if(filter.containsKey("fourcount")){
			value = String.valueOf(filter.get("fourcount"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.FOURCOUNT = '" +  value + "'";
			}
		}
		//月份
		if(filter.containsKey("five")){
			value = String.valueOf(filter.get("five"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.FIVE = '" +  value + "'";
			}
		}
		//月收入情况
		if(filter.containsKey("fivecount")){
			value = String.valueOf(filter.get("fivecount"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.FIVECOUNT = '" +  value + "'";
			}
		}
		//月份
		if(filter.containsKey("six")){
			value = String.valueOf(filter.get("six"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.SIX = '" +  value + "'";
			}
		}
		//月收入情况
		if(filter.containsKey("sixcount3")){
			value = String.valueOf(filter.get("sixcount3"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.SIXCOUNT3 = '" +  value + "'";
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

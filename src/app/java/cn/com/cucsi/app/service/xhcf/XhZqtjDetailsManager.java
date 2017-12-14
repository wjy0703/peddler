package cn.com.cucsi.app.service.xhcf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.app.dao.xhcf.XhZqtjDetailsDao;
import cn.com.cucsi.app.entity.xhcf.XhZqtjDetails;
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
public class XhZqtjDetailsManager {

	private XhZqtjDetailsDao xhZqtjDetailsDao;
	@Autowired
	public void setXhZqtjDetailsDao(XhZqtjDetailsDao xhZqtjDetailsDao) {
		this.xhZqtjDetailsDao = xhZqtjDetailsDao;
	}
	private JdbcDao jdbcDao;
	
	@Autowired
	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}
	@Transactional(readOnly = true)
	public Page<XhZqtjDetails> searchXhZqtjDetails(final Page<XhZqtjDetails> page, final Map<String,Object> filters) {
		return xhZqtjDetailsDao.queryXhZqtjDetails(page, filters);
	}

	
	@Transactional(readOnly = true)
	public XhZqtjDetails getXhZqtjDetails(Long id) {
		return xhZqtjDetailsDao.get(id);
	}

	public void saveXhZqtjDetails(XhZqtjDetails entity) {
		xhZqtjDetailsDao.save(entity);
	}

	/**
	 * 删除用户,如果尝试删除超级管理员将抛出异常.
	 */
	public void deleteXhZqtjDetails(Long id) {
		xhZqtjDetailsDao.delete(id);
	}
	
	public boolean batchDelXhZqtjDetails(String[] ids){
		
		try {
			for(String id: ids){
				deleteXhZqtjDetails(Long.valueOf(id));
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Transactional(readOnly = true)
	public List<Map<String,Object>> searchXhZqtjDetails(String queryName,Map<String,Object> filter,JdbcPage page)
	{
		String sql="";
		String value = "";
		//债权推荐ID
		if(filter.containsKey("zqtjId")){
			value = String.valueOf(filter.get("zqtjId"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ZQTJ_ID = '" +  value + "'";
			}
		}
		//债权推荐ID
		if(filter.containsKey("zqtjId")){
			value = String.valueOf(filter.get("zqtjId"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ZQTJ_ID = '" +  value + "'";
			}
		}
		//资金
		if(filter.containsKey("money")){
			value = String.valueOf(filter.get("money"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.MONEY = '" +  value + "'";
			}
		}
		//资金
		if(filter.containsKey("money")){
			value = String.valueOf(filter.get("money"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.MONEY = '" +  value + "'";
			}
		}
		//还款周期
		if(filter.containsKey("hkzq")){
			value = String.valueOf(filter.get("hkzq"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.HKZQ = '" +  value + "'";
			}
		}
		//还款周期
		if(filter.containsKey("hkzq")){
			value = String.valueOf(filter.get("hkzq"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.HKZQ = '" +  value + "'";
			}
		}
		//可用债权价值ID
		if(filter.containsKey("kyzqjzId")){
			value = String.valueOf(filter.get("kyzqjzId"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.KYZQJZ_ID = '" +  value + "'";
			}
		}
		//可用债权价值ID
		if(filter.containsKey("kyzqjzId")){
			value = String.valueOf(filter.get("kyzqjzId"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.KYZQJZ_ID = '" +  value + "'";
			}
		}
		//债权持有比例
		if(filter.containsKey("zqcybi")){
			value = String.valueOf(filter.get("zqcybi"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ZQCYBI = '" +  value + "'";
			}
		}
		//债权持有比例
		if(filter.containsKey("zqcybi")){
			value = String.valueOf(filter.get("zqcybi"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ZQCYBI = '" +  value + "'";
			}
		}
		//剩余期数
		if(filter.containsKey("hkzqSy")){
			value = String.valueOf(filter.get("hkzqSy"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.HKZQ_SY = '" +  value + "'";
			}
		}
		//剩余期数
		if(filter.containsKey("hkzqSy")){
			value = String.valueOf(filter.get("hkzqSy"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.HKZQ_SY = '" +  value + "'";
			}
		}
		//剩余资金
		if(filter.containsKey("moneySy")){
			value = String.valueOf(filter.get("moneySy"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.MONEY_SY = '" +  value + "'";
			}
		}
		//剩余资金
		if(filter.containsKey("moneySy")){
			value = String.valueOf(filter.get("moneySy"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.MONEY_SY = '" +  value + "'";
			}
		}
		//每月还本金
		if(filter.containsKey("moneyMonth")){
			value = String.valueOf(filter.get("moneyMonth"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.MONEY_MONTH = '" +  value + "'";
			}
		}
		//每月还本金
		if(filter.containsKey("moneyMonth")){
			value = String.valueOf(filter.get("moneyMonth"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.MONEY_MONTH = '" +  value + "'";
			}
		}
		//每月还利息
		if(filter.containsKey("zqlixiMonth")){
			value = String.valueOf(filter.get("zqlixiMonth"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ZQLIXI_MONTH = '" +  value + "'";
			}
		}
		//每月还利息
		if(filter.containsKey("zqlixiMonth")){
			value = String.valueOf(filter.get("zqlixiMonth"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ZQLIXI_MONTH = '" +  value + "'";
			}
		}
		//每月还利息(首个)
		if(filter.containsKey("zqlixiMonthSg")){
			value = String.valueOf(filter.get("zqlixiMonthSg"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ZQLIXI_MONTH_SG = '" +  value + "'";
			}
		}
		//每月还利息(首个)
		if(filter.containsKey("zqlixiMonthSg")){
			value = String.valueOf(filter.get("zqlixiMonthSg"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ZQLIXI_MONTH_SG = '" +  value + "'";
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

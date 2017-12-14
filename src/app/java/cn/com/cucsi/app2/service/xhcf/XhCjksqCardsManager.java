package cn.com.cucsi.app2.service.xhcf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.app2.dao.xhcf.XhCjksqCardsDao;
import cn.com.cucsi.app2.entity.xhcf.XhCjksqCards;
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
public class XhCjksqCardsManager {

	private XhCjksqCardsDao xhCjksqCardsDao;
	@Autowired
	public void setXhCjksqCardsDao(XhCjksqCardsDao xhCjksqCardsDao) {
		this.xhCjksqCardsDao = xhCjksqCardsDao;
	}
	private JdbcDao jdbcDao;
	
	@Autowired
	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}
	@Transactional(readOnly = true)
	public Page<XhCjksqCards> searchXhCjksqCards(final Page<XhCjksqCards> page, final Map<String,Object> filters) {
		return xhCjksqCardsDao.queryXhCjksqCards(page, filters);
	}
	@Transactional(readOnly = true)
	public XhCjksqCards getXhCjksqCards(Long id) {
		return xhCjksqCardsDao.get(id);
	}

	public void saveXhCjksqCards(XhCjksqCards entity) {
		xhCjksqCardsDao.save(entity);
	}

	/**
	 * 删除用户,如果尝试删除超级管理员将抛出异常.
	 */
	public void deleteXhCjksqCards(Long id) {
		xhCjksqCardsDao.delete(id);
	}
	
	public boolean batchDelXhCjksqCards(String[] ids){
		
		try {
			for(String id: ids){
				deleteXhCjksqCards(Long.valueOf(id));
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Transactional(readOnly = true)
	public List<Map<String,Object>> searchXhCjksqCards(String queryName,Map<String,Object> filter,JdbcPage page)
	{
		String sql="";
		String value = "";
		//激活账户总数
		if(filter.containsKey("activeCount")){
			value = String.valueOf(filter.get("activeCount"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ACTIVE_COUNT = '" +  value + "'";
			}
		}
		//授信总额
		if(filter.containsKey("moneySum")){
			value = String.valueOf(filter.get("moneySum"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.MONEY_SUM = '" +  value + "'";
			}
		}
		//在用账户数
		if(filter.containsKey("cardInuse")){
			value = String.valueOf(filter.get("cardInuse"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CARD_INUSE = '" +  value + "'";
			}
		}
		//单张最高授信
		if(filter.containsKey("singleCardUpper")){
			value = String.valueOf(filter.get("singleCardUpper"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.SINGLE_CARD_UPPER = '" +  value + "'";
			}
		}
		//单张最低授信
		if(filter.containsKey("singleCardLower")){
			value = String.valueOf(filter.get("singleCardLower"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.SINGLE_CARD_LOWER = '" +  value + "'";
			}
		}
		//已使用额度
		if(filter.containsKey("amountUsed")){
			value = String.valueOf(filter.get("amountUsed"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.AMOUNT_USED = '" +  value + "'";
			}
		}
		//估值
		if(filter.containsKey("estimateValue")){
			value = String.valueOf(filter.get("estimateValue"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ESTIMATE_VALUE = '" +  value + "'";
			}
		}
		//信用卡使用率
		if(filter.containsKey("useFrequency")){
			value = String.valueOf(filter.get("useFrequency"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.USE_FREQUENCY = '" +  value + "'";
			}
		}
		//逾期情况说明
		if(filter.containsKey("exceedComment")){
			value = String.valueOf(filter.get("exceedComment"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.EXCEED_COMMENT = '" +  value + "'";
			}
		}
		//最近6个月查询记录
		if(filter.containsKey("recentRecord")){
			value = String.valueOf(filter.get("recentRecord"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.RECENT_RECORD = '" +  value + "'";
			}
		}
		
		if (page.getOrderBy()!=null){
			sql = sql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		
		Map<String, Object> conditions = new HashMap<String,Object>();
		conditions.put("sql", sql);
		
		return jdbcDao.searchPagesByMergeSqlTemplate(queryName, conditions, page);
	}
}

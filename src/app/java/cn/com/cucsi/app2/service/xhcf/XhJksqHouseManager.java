package cn.com.cucsi.app2.service.xhcf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.app2.dao.xhcf.XhJksqHouseDao;
import cn.com.cucsi.app2.entity.xhcf.XhJksqHouse;
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
public class XhJksqHouseManager {

	private XhJksqHouseDao xhJksqHouseDao;
	@Autowired
	public void setXhJksqHouseDao(XhJksqHouseDao xhJksqHouseDao) {
		this.xhJksqHouseDao = xhJksqHouseDao;
	}
	private JdbcDao jdbcDao;
	
	@Autowired
	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}
	@Transactional(readOnly = true)
	public Page<XhJksqHouse> searchXhJksqHouse(final Page<XhJksqHouse> page, final Map<String,Object> filters) {
		return xhJksqHouseDao.queryXhJksqHouse(page, filters);
	}
	@Transactional(readOnly = true)
	public XhJksqHouse getXhJksqHouse(Long id) {
		return xhJksqHouseDao.get(id);
	}

	public void saveXhJksqHouse(XhJksqHouse entity) {
		xhJksqHouseDao.save(entity);
	}

	/**
	 * 删除用户,如果尝试删除超级管理员将抛出异常.
	 */
	public void deleteXhJksqHouse(Long id) {
		xhJksqHouseDao.delete(id);
	}
	
	public void delete(Long id) {
        xhJksqHouseDao.delete(id);
    }
	
	@Transactional(readOnly = true)
	public List<Map<String,Object>> searchXhJksqHouse(String queryName,Map<String,Object> filter,JdbcPage page)
	{
		String sql="";
		String value = "";
		//住宅地址
		if(filter.containsKey("address")){
			value = String.valueOf(filter.get("address"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ADDRESS = '" +  value + "'";
			}
		}
		//住宅类型( 全款，按揭 -- 下拉或单选)
		if(filter.containsKey("typeh")){
			value = String.valueOf(filter.get("typeh"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.TYPEH = '" +  value + "'";
			}
		}
		//建筑年份
		if(filter.containsKey("buildYear")){
			value = String.valueOf(filter.get("buildYear"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.BUILD_YEAR = '" +  value + "'";
			}
		}
		//销售面积
		if(filter.containsKey("area")){
			value = String.valueOf(filter.get("area"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.AREA = '" +  value + "'";
			}
		}
		//按揭银行
		if(filter.containsKey("bank")){
			value = String.valueOf(filter.get("bank"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.BANK = '" +  value + "'";
			}
		}
		//借款总额
		if(filter.containsKey("loanMoney")){
			value = String.valueOf(filter.get("loanMoney"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.LOAN_MONEY = '" +  value + "'";
			}
		}
		//贷款年限
		if(filter.containsKey("loanMonth")){
			value = String.valueOf(filter.get("loanMonth"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.LOAN_MONTH = '" +  value + "'";
			}
		}
		//够买价格
		if(filter.containsKey("buyMoney")){
			value = String.valueOf(filter.get("buyMoney"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.BUY_MONEY = '" +  value + "'";
			}
		}
		//借款余额
		if(filter.containsKey("remainmoney")){
			value = String.valueOf(filter.get("remainmoney"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.REMAINMONEY = '" +  value + "'";
			}
		}
		//月还款
		if(filter.containsKey("monthReturn")){
			value = String.valueOf(filter.get("monthReturn"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.MONTH_RETURN = '" +  value + "'";
			}
		}
		//产权归属
		if(filter.containsKey("chouseOwner")){
			value = String.valueOf(filter.get("chouseOwner"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CHOUSE_OWNER = '" +  value + "'";
			}
		}
		//有无抵押 0:无 1:有
		if(filter.containsKey("chouseEndorse")){
			value = String.valueOf(filter.get("chouseEndorse"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CHOUSE_ENDORSE = '" +  value + "'";
			}
		}
		//估值
		if(filter.containsKey("chouseValue")){
			value = String.valueOf(filter.get("chouseValue"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CHOUSE_VALUE = '" +  value + "'";
			}
		}
		//市场报价(一般是写信息例如 100米 8000元一米)
		if(filter.containsKey("chouseMarchetValue")){
			value = String.valueOf(filter.get("chouseMarchetValue"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CHOUSE_MARCHET_VALUE = '" +  value + "'";
			}
		}
		//估值/确认途径
		if(filter.containsKey("chouseValueWay")){
			value = String.valueOf(filter.get("chouseValueWay"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CHOUSE_VALUE_WAY = '" +  value + "'";
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

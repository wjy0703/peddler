package cn.com.cucsi.app.service.xhcf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.app.dao.xhcf.XhMonthlyArDao;
import cn.com.cucsi.app.entity.xhcf.XhMonthlyAr;
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
public class XhMonthlyArManager {

	private XhMonthlyArDao xhMonthlyArDao;
	@Autowired
	public void setXhMonthlyArDao(XhMonthlyArDao xhMonthlyArDao) {
		this.xhMonthlyArDao = xhMonthlyArDao;
	}
	private JdbcDao jdbcDao;
	
	@Autowired
	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}
	@Transactional(readOnly = true)
	public Page<XhMonthlyAr> searchXhMonthlyAr(final Page<XhMonthlyAr> page, final Map<String,Object> filters) {
		return xhMonthlyArDao.queryXhMonthlyAr(page, filters);
	}
	@Transactional(readOnly = true)
	public XhMonthlyAr getXhMonthlyAr(Long id) {
		return xhMonthlyArDao.get(id);
	}

	public void saveXhMonthlyAr(XhMonthlyAr entity) {
		xhMonthlyArDao.save(entity);
	}

	/**
	 * 删除用户,如果尝试删除超级管理员将抛出异常.
	 */
	public void deleteXhMonthlyAr(Long id) {
		xhMonthlyArDao.delete(id);
	}
	
	public boolean batchDelXhMonthlyAr(String[] ids){
		
		try {
			for(String id: ids){
				deleteXhMonthlyAr(Long.valueOf(id));
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Transactional(readOnly = true)
	public List<Map<String,Object>> searchXhMonthlyAr(String queryName,Map<String,Object> filter,JdbcPage page)
	{
		String sql="";
		String value = "";
		//帐外
		if(filter.containsKey("additional")){
			value = String.valueOf(filter.get("additional"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ADDITIONAL = '" +  value + "'";
			}
		}
		//省份
		if(filter.containsKey("area")){
			value = String.valueOf(filter.get("area"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.AREA = '" +  value + "'";
			}
		}
		//银行名称
		if(filter.containsKey("bankName")){
			value = String.valueOf(filter.get("bankName"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.BANK_NAME = '" +  value + "'";
			}
		}
		//银行账户
		if(filter.containsKey("bankNumber")){
			value = String.valueOf(filter.get("bankNumber"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.BANK_NUMBER = '" +  value + "'";
			}
		}
		//银行开户行
		if(filter.containsKey("bankOpen")){
			value = String.valueOf(filter.get("bankOpen"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.BANK_OPEN = '" +  value + "'";
			}
		}
		//账单日
		if(filter.containsKey("billDay")){
			value = String.valueOf(filter.get("billDay"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.BILL_DAY = '" +  value + "'";
			}
		}
		//地市
		if(filter.containsKey("city")){
			value = String.valueOf(filter.get("city"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CITY = '" +  value + "'";
			}
		}
		//利息
		if(filter.containsKey("interest")){
			value = String.valueOf(filter.get("interest"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.INTEREST = '" +  value + "'";
			}
		}
		//借款人ID
		if(filter.containsKey("loanId")){
			value = String.valueOf(filter.get("loanId"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.LOAN_ID = '" +  value + "'";
			}
		}
		//借款人身份证号
		if(filter.containsKey("loanIdCard")){
			value = String.valueOf(filter.get("loanIdCard"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.LOAN_ID_CARD = '" +  value + "'";
			}
		}
		//借款人名称
		if(filter.containsKey("loanName")){
			value = String.valueOf(filter.get("loanName"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.LOAN_NAME = '" +  value + "'";
			}
		}
		//借款人编号
		if(filter.containsKey("loanNumber")){
			value = String.valueOf(filter.get("loanNumber"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.LOAN_NUMBER = '" +  value + "'";
			}
		}
		//借款产品
		if(filter.containsKey("loanPro")){
			value = String.valueOf(filter.get("loanPro"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.LOAN_PRO = '" +  value + "'";
			}
		}
		//借款状态
		if(filter.containsKey("loanState")){
			value = String.valueOf(filter.get("loanState"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.LOAN_STATE = '" +  value + "'";
			}
		}
		//金额
		if(filter.containsKey("money")){
			value = String.valueOf(filter.get("money"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.MONEY = '" +  value + "'";
			}
		}
		//结算日期
		if(filter.containsKey("settlementDate")){
			value = String.valueOf(filter.get("settlementDate"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.SETTLEMENT_DATE = '" +  value + "'";
			}
		}
		//备用字段1
		if(filter.containsKey("spareField01")){
			value = String.valueOf(filter.get("spareField01"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.SPARE_FIELD01 = '" +  value + "'";
			}
		}
		//备用字段2
		if(filter.containsKey("spareField02")){
			value = String.valueOf(filter.get("spareField02"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.SPARE_FIELD02 = '" +  value + "'";
			}
		}
		//备用字段3
		if(filter.containsKey("spareField03")){
			value = String.valueOf(filter.get("spareField03"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.SPARE_FIELD03 = '" +  value + "'";
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

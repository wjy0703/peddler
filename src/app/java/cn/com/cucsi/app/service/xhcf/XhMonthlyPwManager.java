package cn.com.cucsi.app.service.xhcf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.app.dao.xhcf.XhMonthlyPwDao;
import cn.com.cucsi.app.entity.xhcf.XhMonthlyPw;
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
public class XhMonthlyPwManager {

	private XhMonthlyPwDao xhMonthlyPwDao;
	@Autowired
	public void setXhMonthlyPwDao(XhMonthlyPwDao xhMonthlyPwDao) {
		this.xhMonthlyPwDao = xhMonthlyPwDao;
	}
	private JdbcDao jdbcDao;
	
	@Autowired
	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}
	@Transactional(readOnly = true)
	public Page<XhMonthlyPw> searchXhMonthlyPw(final Page<XhMonthlyPw> page, final Map<String,Object> filters) {
		return xhMonthlyPwDao.queryXhMonthlyPw(page, filters);
	}
	@Transactional(readOnly = true)
	public XhMonthlyPw getXhMonthlyPw(Long id) {
		return xhMonthlyPwDao.get(id);
	}

	public void saveXhMonthlyPw(XhMonthlyPw entity) {
		xhMonthlyPwDao.save(entity);
	}

	/**
	 * 删除用户,如果尝试删除超级管理员将抛出异常.
	 */
	public void deleteXhMonthlyPw(Long id) {
		xhMonthlyPwDao.delete(id);
	}
	
	public boolean batchDelXhMonthlyPw(String[] ids){
		
		try {
			for(String id: ids){
				deleteXhMonthlyPw(Long.valueOf(id));
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Transactional(readOnly = true)
	public List<Map<String,Object>> searchXhMonthlyPw(String queryName,Map<String,Object> filter,JdbcPage page)
	{
		String sql="";
		String value = "";
		//主键
		if(filter.containsKey("id")){
			value = String.valueOf(filter.get("id"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ID = '" +  value + "'";
			}
		}
		//帐外
		if(filter.containsKey("additional")){
			value = String.valueOf(filter.get("additional"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ADDITIONAL = '" +  value + "'";
			}
		}
		//银行名称
		if(filter.containsKey("bankName")){
			value = String.valueOf(filter.get("bankName"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.BANK_NAME = '" +  value + "'";
			}
		}
		//银行账号
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
		//利息
		if(filter.containsKey("interest")){
			value = String.valueOf(filter.get("interest"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.INTEREST = '" +  value + "'";
			}
		}
		//出借人ID
		if(filter.containsKey("lenderId")){
			value = String.valueOf(filter.get("lenderId"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.LENDER_ID = '" +  value + "'";
			}
		}
		//出借人身份证号
		if(filter.containsKey("lenderIdCard")){
			value = String.valueOf(filter.get("lenderIdCard"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.LENDER_ID_CARD = '" +  value + "'";
			}
		}
		//出借人名称
		if(filter.containsKey("lenderName")){
			value = String.valueOf(filter.get("lenderName"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.LENDER_NAME = '" +  value + "'";
			}
		}
		//出借编号
		if(filter.containsKey("lenderNumber")){
			value = String.valueOf(filter.get("lenderNumber"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.LENDER_NUMBER = '" +  value + "'";
			}
		}
		//出借状态
		if(filter.containsKey("lenderState")){
			value = String.valueOf(filter.get("lenderState"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.LENDER_STATE = '" +  value + "'";
			}
		}
		//金额
		if(filter.containsKey("money")){
			value = String.valueOf(filter.get("money"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.MONEY = '" +  value + "'";
			}
		}
		//付款日期
		if(filter.containsKey("payDate")){
			value = String.valueOf(filter.get("payDate"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.PAY_DATE = '" +  value + "'";
			}
		}
		//付款类型
		if(filter.containsKey("payType")){
			value = String.valueOf(filter.get("payType"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.PAY_TYPE = '" +  value + "'";
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

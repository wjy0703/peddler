package cn.com.cucsi.app.service.xhcf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.app.dao.xhcf.XhLoanReturnRecordsDao;
import cn.com.cucsi.app.entity.xhcf.XhLoanReturnRecords;
import cn.com.cucsi.app.service.ServiceException;
import cn.com.cucsi.app2.entity.xhcf.XhJksqRelations;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils;
import cn.com.cucsi.core.utils.EncodeUtils;
import cn.com.cucsi.core.utils.PropertiesUtils;



//Spring Bean的标识.
@Component
//默认将类中的所有函数纳入事务管理.
@Transactional
public class XhLoanReturnRecordsManager {

	private XhLoanReturnRecordsDao xhLoanReturnRecordsDao;
	@Autowired
	public void setXhLoanReturnRecordsDao(XhLoanReturnRecordsDao xhLoanReturnRecordsDao) {
		this.xhLoanReturnRecordsDao = xhLoanReturnRecordsDao;
	}
	private JdbcDao jdbcDao;
	
	@Autowired
	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}
	@Transactional(readOnly = true)
	public Page<XhLoanReturnRecords> searchXhLoanReturnRecords(final Page<XhLoanReturnRecords> page, final Map<String,Object> filters) {
		return xhLoanReturnRecordsDao.queryXhLoanReturnRecords(page, filters);
	}
	@Transactional(readOnly = true)
	public XhLoanReturnRecords getXhLoanReturnRecords(Long id) {
		return xhLoanReturnRecordsDao.get(id);
	}

	public void saveXhLoanReturnRecords(XhLoanReturnRecords entity) {
		xhLoanReturnRecordsDao.save(entity);
	}

	/**
	 * 删除用户,如果尝试删除超级管理员将抛出异常.
	 */
	public void deleteXhLoanReturnRecords(Long id) {
		xhLoanReturnRecordsDao.delete(id);
	}
	
	public boolean batchDelXhLoanReturnRecords(String[] ids){
		
		try {
			for(String id: ids){
				deleteXhLoanReturnRecords(Long.valueOf(id));
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Transactional(readOnly = true)
	public List<Map<String,Object>> searchXhLoanReturnRecords(String queryName,Map<String,Object> filter,JdbcPage page)
	{
		String sql="";
		String value = "";
		//交易金额
		if(filter.containsKey("deailingMoney")){
			value = String.valueOf(filter.get("deailingMoney"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.DEAILING_MONEY = '" +  value + "'";
			}
		}
		//交易时间
		if(filter.containsKey("dealingTime")){
			value = String.valueOf(filter.get("dealingTime"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.DEALING_TIME = '" +  value + "'";
			}
		}
		//交易类型
		if(filter.containsKey("dealingType")){
			value = String.valueOf(filter.get("dealingType"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.DEALING_TYPE = '" +  value + "'";
			}
		}
		//主账户ID
		if(filter.containsKey("loanerMainAccountId")){
			value = String.valueOf(filter.get("loanerMainAccountId"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.LOANER_MAIN_ACCOUNT_ID = '" +  value + "'";
			}
		}
		//合同编码
		if(filter.containsKey("htbm")){
			value = String.valueOf(filter.get("htbm"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.HTBM = '" +  value + "'";
			}
		}
		//错误原因
		if(filter.containsKey("errorState")){
			value = String.valueOf(filter.get("errorState"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ERROR_STATE = '" +  value + "'";
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
	public Page<XhLoanReturnRecords> searchxhLoanReturnFailRecords(final Page<XhLoanReturnRecords> page, final Map<String, Object> filters) {
		// TODO Auto-generated method stub
		return xhLoanReturnRecordsDao.queryXhLoanReturnRecords(page,filters);
		
	}
}

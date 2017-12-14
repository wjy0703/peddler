package cn.com.cucsi.app2.service.xhcf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.app2.dao.xhcf.XhJksqCreditsDao;
import cn.com.cucsi.app2.entity.xhcf.XhJksqCredits;
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
public class XhJksqCreditsManager {

	private XhJksqCreditsDao xhJksqCreditsDao;
	@Autowired
	public void setXhJksqCreditsDao(XhJksqCreditsDao xhJksqCreditsDao) {
		this.xhJksqCreditsDao = xhJksqCreditsDao;
	}
	private JdbcDao jdbcDao;
	
	@Autowired
	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}
	@Transactional(readOnly = true)
	public Page<XhJksqCredits> searchXhJksqCredits(final Page<XhJksqCredits> page, final Map<String,Object> filters) {
		return xhJksqCreditsDao.queryXhJksqCredits(page, filters);
	}
	@Transactional(readOnly = true)
	public XhJksqCredits getXhJksqCredits(Long id) {
		return xhJksqCreditsDao.get(id);
	}

	public void saveXhJksqCredits(XhJksqCredits entity) {
		xhJksqCreditsDao.save(entity);
	}
	
	public void deleteCredit(Long Id){
	    xhJksqCreditsDao.delete(Id);
	}

	/**
	 * 删除用户,如果尝试删除超级管理员将抛出异常.
	 */
	public void deleteXhJksqCredits(Long id) {
		xhJksqCreditsDao.delete(id);
	}
	
	public boolean batchDelXhJksqCredits(String[] ids){
		
		try {
			for(String id: ids){
				deleteXhJksqCredits(Long.valueOf(id));
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Transactional(readOnly = true)
	public List<Map<String,Object>> searchXhJksqCredits(String queryName,Map<String,Object> filter,JdbcPage page)
	{
		String sql="";
		String value = "";
		//信用资料类别 0:无抵押 1：有抵押
		if(filter.containsKey("typeh")){
			value = String.valueOf(filter.get("typeh"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.TYPEH = '" +  value + "'";
			}
		}
		//抵押物品(自有汽车等)
		if(filter.containsKey("mortage")){
			value = String.valueOf(filter.get("mortage"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.MORTAGE = '" +  value + "'";
			}
		}
		//机构名称
		if(filter.containsKey("compBankName")){
			value = String.valueOf(filter.get("compBankName"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.COMP_BANK_NAME = '" +  value + "'";
			}
		}
		//贷款额度
		if(filter.containsKey("loanAmount")){
			value = String.valueOf(filter.get("loanAmount"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.LOAN_AMOUNT = '" +  value + "'";
			}
		}
		//月还款额
		if(filter.containsKey("monthReturn")){
			value = String.valueOf(filter.get("monthReturn"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.MONTH_RETURN = '" +  value + "'";
			}
		}
		//借款余额
		if(filter.containsKey("remain")){
			value = String.valueOf(filter.get("remain"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.REMAIN = '" +  value + "'";
			}
		}
		//借款期限
		if(filter.containsKey("cloanCount")){
			value = String.valueOf(filter.get("cloanCount"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CLOAN_COUNT = '" +  value + "'";
			}
		}
		//有无还款证明
		if(filter.containsKey("cloanReturnFile")){
			value = String.valueOf(filter.get("cloanReturnFile"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CLOAN_RETURN_FILE = '" +  value + "'";
			}
		}
		//还款情况说明
		if(filter.containsKey("cloanComment")){
			value = String.valueOf(filter.get("cloanComment"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CLOAN_COMMENT = '" +  value + "'";
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

package cn.com.cucsi.app.service.xhcf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.app.dao.xhcf.XhLoanerAccountDao;
import cn.com.cucsi.app.entity.xhcf.XhLoanerAccount;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.orm.Page;



//Spring Bean的标识.
@Component
//默认将类中的所有函数纳入事务管理.
@Transactional
public class XhLoanerAccountManager {

	private XhLoanerAccountDao xhLoanerAccountDao;
	@Autowired
	public void setXhLoanerAccountDao(XhLoanerAccountDao xhLoanerAccountDao) {
		this.xhLoanerAccountDao = xhLoanerAccountDao;
	}
	private JdbcDao jdbcDao;
	
	@Autowired
	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}
	@Transactional(readOnly = true)
	public Page<XhLoanerAccount> searchXhLoanerAccount(final Page<XhLoanerAccount> page, final Map<String,Object> filters) {
		return xhLoanerAccountDao.queryXhLoanerAccount(page, filters);
	}
	@Transactional(readOnly = true)
	public XhLoanerAccount getXhLoanerAccount(Long id) {
		return xhLoanerAccountDao.get(id);
	}

	public void saveXhLoanerAccount(XhLoanerAccount entity) {
		xhLoanerAccountDao.save(entity);
	}
	
	public XhLoanerAccount getAccountByJksqId(Long jksqId) {
		return xhLoanerAccountDao.getAccountByJksqId(jksqId);
	}

	/**
	 * 删除用户,如果尝试删除超级管理员将抛出异常.
	 */
	public void deleteXhLoanerAccount(Long id) {
		xhLoanerAccountDao.delete(id);
	}
	
	public boolean batchDelXhLoanerAccount(String[] ids){
		
		try {
			for(String id: ids){
				deleteXhLoanerAccount(Long.valueOf(id));
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Transactional(readOnly = true)
	public List<Map<String,Object>> searchXhLoanerAccount(String queryName,Map<String,Object> filter,JdbcPage page)
	{
		String sql="";
		String value = "";
		//借款合同ID
		if(filter.containsKey("loanContractId")){
			value = String.valueOf(filter.get("loanContractId"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.LOAN_CONTRACT_ID = '" +  value + "'";
			}
		}
		//借款申请ID账户
		if(filter.containsKey("loanApplyId")){
			value = String.valueOf(filter.get("loanApplyId"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.LOAN_APPLY_ID = '" +  value + "'";
			}
		}
		//账户余额
		if(filter.containsKey("accountBalance")){
			value = String.valueOf(filter.get("accountBalance"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ACCOUNT_BALANCE = '" +  value + "'";
			}
		}
		//账户状态: 0 正常账户  1历史账户
		if(filter.containsKey("accountState")){
			value = String.valueOf(filter.get("accountState"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ACCOUNT_STATE = '" +  value + "'";
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
	
	/**
	 * 
	 * 根据条件获取XhLoanerAccount
	 * 
	 * @param name
	 * @param bankNum
	 * @param bank
	 * @author xjs
	 * @return 
	 * @date 2013-8-14 下午2:26:15
	 */
	@SuppressWarnings("unchecked")
    public List<XhLoanerAccount> getXhLoanerAccountByCondition(String name,String bankNum,String bank,String jkhtbm){
	    
	    String hql = " from XhLoanerAccount xhLoanerAccount where 1 = 1 ";
	    Map params = new HashMap();
	    if(org.springframework.util.StringUtils.hasText(name)){
	        hql += " and xhLoanerAccount.loanApply.jkrxm = :name";
	        params.put("name", name);
	    }
	    if(org.springframework.util.StringUtils.hasText(bankNum)){
	        hql += " and xhLoanerAccount.loanApply.bankNum = :bankNum";
	        params.put("bankNum", bankNum);
	    }
	    if(org.springframework.util.StringUtils.hasText(bank)){
	        hql += " and xhLoanerAccount.loanApply.bankOpen = :bank";
	        params.put("bank", bank);
	    }
	    if(org.springframework.util.StringUtils.hasText(jkhtbm)){
	        hql += " and xhLoanerAccount.loanContract.jkhtbm = :jkhtbm";
            params.put("jkhtbm", jkhtbm);
	    }
        return xhLoanerAccountDao.find(hql, params);
	}
}

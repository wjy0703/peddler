package cn.com.cucsi.app.service.xhcf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.app.dao.loan.XhJksqDao;
import cn.com.cucsi.app.dao.xhcf.XhIpcApplyDao;
import cn.com.cucsi.app.dao.xhcf.XhIpcConstractDao;
import cn.com.cucsi.app.entity.xhcf.XhIpcApply;
import cn.com.cucsi.app.entity.xhcf.XhIpcConstract;
import cn.com.cucsi.app.entity.xhcf.XhJksq;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.orm.Page;



//Spring Bean的标识.
@Component
//默认将类中的所有函数纳入事务管理.
@Transactional
public class XhIpcApplyManager {
	private XhIpcConstractDao xhIpcConstractDao;
	
	private XhJksqDao xhJksqDao;

	@Autowired
	public void setXhIpcConstractDao(XhIpcConstractDao xhIpcConstractDao) {
		this.xhIpcConstractDao = xhIpcConstractDao;
	}
	@Autowired
	public void setXhJksqDao(XhJksqDao xhJksqDao) {
		this.xhJksqDao = xhJksqDao;
	}
	private XhIpcApplyDao xhIpcApplyDao;
	@Autowired
	public void setXhIpcApplyDao(XhIpcApplyDao xhIpcApplyDao) {
		this.xhIpcApplyDao = xhIpcApplyDao;
	}
	private JdbcDao jdbcDao;
	
	@Autowired
	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}
	@Transactional(readOnly = true)
	public Page<XhIpcApply> searchXhIpcApply(final Page<XhIpcApply> page, final Map<String,Object> filters) {
		return xhIpcApplyDao.queryXhIpcApply(page, filters);
	}
	@Transactional(readOnly = true)
	public XhIpcApply getXhIpcApply(Long id) {
		return xhIpcApplyDao.get(id);
	}
	
	public XhIpcConstract getApplyConst(Long applyId){
		return xhIpcConstractDao.findXhIpcConstractByApplyID(applyId);
	}

	public void saveXhIpcApply(XhIpcApply entity) {
		
		
		XhJksq jksq = null;
		
		if (entity.getLoanApply() != null) {
			jksq = entity.getLoanApply();
		} else {
			jksq = new XhJksq();
			jksq.setState("50");
			entity.setLoanApply(jksq);
			
		}

		jksq.setJkrxm(entity.getCustomerName());
		jksq.setZjhm(entity.getCustomerCardId());
//		jksq.setJkLoanQuota(entity.get + "");
//		jksq.setJkCycle(entity.getLoanMonth() + "");
		jksq.setBackup01("IPC");
		jksq.setBankNum(entity.getBankCardNum());
		jksq.setBankOpen(entity.getBankName());
		jksq.setBankUsername(entity.getCustomerName());
//		if (null != entity.getBackMoneyType()
//				&& !"".equals(entity.getBackMoneyType())) {
//			if ("1".equals(entity.getBackMoneyType()))
//				jksq.setHkWay("独立还款");
//			else if ("2".equals(entity.getBackMoneyType()))
//				jksq.setHkWay("家人协助共同还款");
//			else if ("3".equals(entity.getBackMoneyType()))
//				jksq.setHkWay("其他还款方式");
//		}
		jksq.setJkType(entity.getLoanType());
//		jksq.setJkType("G");
	/*	if (null != entity.getLoanUse()
				&& !"".equals(entity.getLoanUse())) {
		if("1".equals(entity.getLoanUse())){
			entity.setLoanUse("经营");
		}
		else if("2".equals(entity.getLoanUse())){
			entity.setLoanUse("消费");
		}
		else if("3".equals(entity.getLoanUse())){
			entity.setLoanUse("周转");
		}
		}改成标签形式的
	*/	
		jksq.setJkUse(entity.getLoanUse());
		
		
		// jksq.setJkLoanQuota(entity.getLoanLoanAmount());
		// jksq.setJkCycle(entity.getLoanMonth());
		xhJksqDao.save(jksq);
		
		
		
		
		
		
		
		
		xhIpcApplyDao.save(entity);
	}

	/**
	 * 删除用户,如果尝试删除超级管理员将抛出异常.
	 */
	public void deleteXhIpcApply(Long id) {
		xhIpcApplyDao.delete(id);
	}
	
	public boolean batchDelXhIpcApply(String[] ids){
		
		try {
			for(String id: ids){
				deleteXhIpcApply(Long.valueOf(id));
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Transactional(readOnly = true)
	public List<Map<String,Object>> searchXhIpcApply(String queryName,Map<String,Object> filter,JdbcPage page)
	{
		String sql="";
		String value = "";
		//客户姓名
		if(filter.containsKey("customerName")){
			value = String.valueOf(filter.get("customerName"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CUSTOMER_NAME = '" +  value + "'";
			}
		}
		//客户编号
		if(filter.containsKey("customerNum")){
			value = String.valueOf(filter.get("customerNum"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CUSTOMER_NUM = '" +  value + "'";
			}
		}
		//客户电话
		if(filter.containsKey("customerPhone")){
			value = String.valueOf(filter.get("customerPhone"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CUSTOMER_PHONE = '" +  value + "'";
			}
		}
		//开户行
		if(filter.containsKey("customerCardId")){
			value = String.valueOf(filter.get("customerCardId"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CUSTOMER_CARD_ID = '" +  value + "'";
			}
		}
		//账户名
		if(filter.containsKey("bankName")){
			value = String.valueOf(filter.get("bankName"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.BANK_NAME = '" +  value + "'";
			}
		}
		//账（卡）号
		if(filter.containsKey("bankCardNum")){
			value = String.valueOf(filter.get("bankCardNum"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.BANK_CARD_NUM = '" +  value + "'";
			}
		}
		//共借人姓名
		if(filter.containsKey("togetherName")){
			value = String.valueOf(filter.get("togetherName"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.TOGETHER_NAME = '" +  value + "'";
			}
		}
		//共借人关系
		if(filter.containsKey("togetherRelation")){
			value = String.valueOf(filter.get("togetherRelation"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.TOGETHER_RELATION = '" +  value + "'";
			}
		}
		//共借人电话
		if(filter.containsKey("togetherPhone")){
			value = String.valueOf(filter.get("togetherPhone"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.TOGETHER_PHONE = '" +  value + "'";
			}
		}
		//商铺（单位）地址
		if(filter.containsKey("customerCompAddress")){
			value = String.valueOf(filter.get("customerCompAddress"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CUSTOMER_COMP_ADDRESS = '" +  value + "'";
			}
		}
		//家庭住址
		if(filter.containsKey("customerHomeAddress")){
			value = String.valueOf(filter.get("customerHomeAddress"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CUSTOMER_HOME_ADDRESS = '" +  value + "'";
			}
		}
		//共借人住址
		if(filter.containsKey("togetherHomeAddress")){
			value = String.valueOf(filter.get("togetherHomeAddress"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.TOGETHER_HOME_ADDRESS = '" +  value + "'";
			}
		}
		//通讯录 
		if(filter.containsKey("txl")){
			value = String.valueOf(filter.get("txl"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.TXL = '" +  value + "'";
			}
		}
		//开发信贷员

		if(filter.containsKey("kfEmp")){
			value = String.valueOf(filter.get("kfEmp"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.KF_EMP = '" +  value + "'";
			}
		}
		//负责信贷员
		if(filter.containsKey("fzEmp")){
			value = String.valueOf(filter.get("fzEmp"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.FZ_EMP = '" +  value + "'";
			}
		}
		//维护人员

		if(filter.containsKey("whEmp")){
			value = String.valueOf(filter.get("whEmp"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.WH_EMP = '" +  value + "'";
			}
		}
		//借款类型
		if(filter.containsKey("loanType")){
			value = String.valueOf(filter.get("loanType"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.LOAN_TYPE = '" +  value + "'";
			}
		}
		//借款用途
		if(filter.containsKey("loanUse")){
			value = String.valueOf(filter.get("loanUse"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.LOAN_USE = '" +  value + "'";
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

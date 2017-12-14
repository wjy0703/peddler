package cn.com.cucsi.app.service.xhcf;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.app.dao.xhcf.XhMonthlyDwDao;
import cn.com.cucsi.app.entity.xhcf.XhMonthlyDw;
import cn.com.cucsi.app.service.ServiceException;
import cn.com.cucsi.app.web.util.CreditHarmonyComputeUtilties;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils;
import cn.com.cucsi.core.utils.EncodeUtils;
import cn.com.cucsi.core.utils.PropertiesUtils;



//Spring Bean的标识.
@Component
//默认将类中的所有函数纳入事务管理.
@Transactional
public class XhMonthlyDwManager {

	private XhMonthlyDwDao xhMonthlyDwDao;
	@Autowired
	public void setXhMonthlyDwDao(XhMonthlyDwDao xhMonthlyDwDao) {
		this.xhMonthlyDwDao = xhMonthlyDwDao;
	}
	private JdbcDao jdbcDao;
	
	@Autowired
	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}
	@Transactional(readOnly = true)
	public Page<XhMonthlyDw> searchXhMonthlyDw(final Page<XhMonthlyDw> page, final Map<String,Object> filters) {
		return xhMonthlyDwDao.queryXhMonthlyDw(page, filters);
	}
	@Transactional(readOnly = true)
	public XhMonthlyDw getXhMonthlyDw(Long id) {
		return xhMonthlyDwDao.get(id);
	}

	public void saveXhMonthlyDw(XhMonthlyDw entity) {
		xhMonthlyDwDao.save(entity);
	}

	/**
	 * 删除用户,如果尝试删除超级管理员将抛出异常.
	 */
	public void deleteXhMonthlyDw(Long id) {
		xhMonthlyDwDao.delete(id);
	}
	
	public boolean batchDelXhMonthlyDw(String[] ids){
		
		try {
			for(String id: ids){
				deleteXhMonthlyDw(Long.valueOf(id));
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Transactional(readOnly = true)
	public List<Map<String,Object>> searchXhMonthlyDw(String queryName,Map<String,Object> filter,JdbcPage page)
	{
		String sql="";
		String value = "";
		
		//出借人名称
		if(filter.containsKey("lenderName")){
			value = String.valueOf(filter.get("lenderName"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.LENDER_NAME like '%" +  value + "%'";
			}
		}
		//出借编号
		if(filter.containsKey("lenderNumber")){
			value = String.valueOf(filter.get("lenderNumber"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.LENDER_NUMBER like '%" +  value + "%'";
			}
		}
		//出借状态
		if(filter.containsKey("lenderState")){
			value = String.valueOf(filter.get("lenderState"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.LENDER_STATE = '" +  value + "'";
			}
		}
		
		//付款日期
		if(filter.containsKey("payDate")){
			
			value =CreditHarmonyComputeUtilties.dateToString((Date)filter.get("payDate"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.PAY_DATE =  to_date('" +  value + "','yyyy-MM-dd')";
			}
		}
		
		
		sql=sql+ " group by a.LENDER_ID_CARD,a.BANK_NAME,a.BANK_NUMBER,a.BANK_OPEN,a.LENDER_ID,a.LENDER_NAME,a.LENDER_NUMBER,a.LENDER_STATE,a.PAY_DATE";
		
//		if (page.getOrderBy()!=null){
//			sql = sql + " order by " + page.getOrderBy() + " " + page.getOrder();
//		}
		
		System.out.println("sql=======>" + sql);
		Map<String, Object> conditions = new HashMap<String,Object>();
		conditions.put("sql", sql);
		
		return jdbcDao.searchPagesByMergeSqlTemplate(queryName, conditions, page);
	}
	
	@Transactional(readOnly = true)
	public List<Map<String,Object>> listAssetsAccounting(String queryName,Map<String,Object> filter,JdbcPage page)
	{
		String sql="";
		String value = "";
		
		//出借人名称
		if(filter.containsKey("lenderName")){
			value = String.valueOf(filter.get("lenderName"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and t.lender_name like '%" +  value + "%'";
			}
		}
		//出借人身份证号
		if(filter.containsKey("idCard")){
			value = String.valueOf(filter.get("idCard"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and t.lender_id_card like '%" +  value + "%'";
			}
		}
		//出借编号
		if(filter.containsKey("lenderNumber")){
			value = String.valueOf(filter.get("lenderNumber"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and t.lender_number like '%" +  value + "%'";
			}
		}
		//出借状态暂留
		if(filter.containsKey("lenderState")){
			value = String.valueOf(filter.get("lenderState"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and t.lender_state = '" +  value + "'";
			}
		}
		
		//过滤数据 投资产品
		if(filter.containsKey("tzcp")){
			value = String.valueOf(filter.get("tzcp"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and g.tzcp_id in (" +  value + ")";
			}
		}
		
		sql=sql+ " group by c1.zjhm, t.lender_name, t.lender_id, g.id, g.tzsqbh, g.jhtzje, g.tzcp_id, g.jhtzrq, g.cjzq";
		
		sql=sql+ " order by g.jhtzje, t.lender_name asc";
		
		Map<String, Object> conditions = new HashMap<String,Object>();
		conditions.put("sql", sql);
		
		return jdbcDao.searchPagesByMergeSqlTemplate(queryName, conditions, page);
	}
	
	@Transactional(readOnly = true)
	public List<Map<String,Object>> listAssetsAccountingInfo(String queryName,Map<String,Object> filter,JdbcPage page)
	{
		String sql="";
		String value = "";
		
		//出借人ID
		if(filter.containsKey("tzsq_id")){
			
			value = String.valueOf(filter.get("tzsq_id"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and g.id = "+value;
			}
		}
		
		//付款日期
		if(filter.containsKey("payDate")){
			
			value =CreditHarmonyComputeUtilties.dateToString((Date)filter.get("payDate"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and t.PAY_DATE =  to_date('" +  value + "','yyyy-MM-dd')";
			}
		}
		
		sql=sql+ " group by t.lender_id_card, t.lender_name, t.lender_id ,t.pay_date";
		
		sql=sql+ " order by t.pay_date asc";
		
		Map<String, Object> conditions = new HashMap<String,Object>();
		conditions.put("sql", sql);
		
		return jdbcDao.searchPagesByMergeSqlTemplate(queryName, conditions, page);
	}
	
	public List<Date> getAllPayDate(){
		
		return xhMonthlyDwDao.getAllPayDate();
	}
	/**
	 * @param cardID
	 * @param payDate
	 * @return
	 */
	public Long[] getXhMonthlyDws(String cardID, Date payDate) {
		
		return xhMonthlyDwDao.getXhMonthlyDws(cardID,payDate);
	}
	
}

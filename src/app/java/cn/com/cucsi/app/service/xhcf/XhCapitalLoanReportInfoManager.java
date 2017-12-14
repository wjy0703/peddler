package cn.com.cucsi.app.service.xhcf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.app.dao.xhcf.XhCapitalLoanReportInfoDao;
import cn.com.cucsi.app.entity.xhcf.XhCapitalLoanReportInfo;
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
public class XhCapitalLoanReportInfoManager {

	private XhCapitalLoanReportInfoDao xhCapitalLoanReportInfoDao;
	@Autowired
	public void setXhCapitalLoanReportInfoDao(XhCapitalLoanReportInfoDao xhCapitalLoanReportInfoDao) {
		this.xhCapitalLoanReportInfoDao = xhCapitalLoanReportInfoDao;
	}
	private JdbcDao jdbcDao;
	
	@Autowired
	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}
	@Transactional(readOnly = true)
	public Page<XhCapitalLoanReportInfo> searchXhCapitalLoanReportInfo(final Page<XhCapitalLoanReportInfo> page, final Map<String,Object> filters) {
		return xhCapitalLoanReportInfoDao.queryXhCapitalLoanReportInfo(page, filters);
	}
	@Transactional(readOnly = true)
	public XhCapitalLoanReportInfo getXhCapitalLoanReportInfo(Long id) {
		return xhCapitalLoanReportInfoDao.get(id);
	}

	public void saveXhCapitalLoanReportInfo(XhCapitalLoanReportInfo entity) {
		xhCapitalLoanReportInfoDao.save(entity);
	}

	/**
	 * 删除用户,如果尝试删除超级管理员将抛出异常.
	 */
	public void deleteXhCapitalLoanReportInfo(Long id) {
		xhCapitalLoanReportInfoDao.delete(id);
	}
	
	public boolean batchDelXhCapitalLoanReportInfo(String[] ids){
		
		try {
			for(String id: ids){
				deleteXhCapitalLoanReportInfo(Long.valueOf(id));
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Transactional(readOnly = true)
	public List<Map<String,Object>> searchXhCapitalLoanReportInfo(String queryName,Map<String,Object> filter,JdbcPage page)
	{
		String sql="";
		String value = "";
		//报告主表
		if(filter.containsKey("reportId")){
			value = String.valueOf(filter.get("reportId"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.REPORT_ID = '" +  value + "'";
			}
		}
		//出借编号
		if(filter.containsKey("lendNo")){
			value = String.valueOf(filter.get("lendNo"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.LEND_NO = '" +  value + "'";
			}
		}
		//初始出借日期
		if(filter.containsKey("firstLendDate")){
			value = String.valueOf(filter.get("firstLendDate"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.FIRST_LEND_DATE = '" +  value + "'";
			}
		}
		//出借及回收方式
		if(filter.containsKey("lendType")){
			value = String.valueOf(filter.get("lendType"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.LEND_TYPE = '" +  value + "'";
			}
		}
		//初始出借金额
		if(filter.containsKey("firstLendMoney")){
			value = String.valueOf(filter.get("firstLendMoney"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.FIRST_LEND_MONEY = '" +  value + "'";
			}
		}
		//本期应还金额
		if(filter.containsKey("shoudBack")){
			value = String.valueOf(filter.get("shoudBack"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.SHOUD_BACK = '" +  value + "'";
			}
		}
		//本期实际还款金额
		if(filter.containsKey("realBack")){
			value = String.valueOf(filter.get("realBack"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.REAL_BACK = '" +  value + "'";
			}
		}
		//延迟支付金额
		if(filter.containsKey("latePayMoney")){
			value = String.valueOf(filter.get("latePayMoney"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.LATE_PAY_MONEY = '" +  value + "'";
			}
		}
		//账户管理费率
		if(filter.containsKey("mngFeeRate")){
			value = String.valueOf(filter.get("mngFeeRate"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.MNG_FEE_RATE = '" +  value + "'";
			}
		}
		//账户管理费
		if(filter.containsKey("mngFee")){
			value = String.valueOf(filter.get("mngFee"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.MNG_FEE = '" +  value + "'";
			}
		}
		//当期受让金额
		if(filter.containsKey("reLend")){
			value = String.valueOf(filter.get("reLend"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.RE_LEND = '" +  value + "'";
			}
		}
		//当期回收金额
		if(filter.containsKey("drawing")){
			value = String.valueOf(filter.get("drawing"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.DRAWING = '" +  value + "'";
			}
		}
		//当前全部账户资产
		if(filter.containsKey("allMoney")){
			value = String.valueOf(filter.get("allMoney"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ALL_MONEY = '" +  value + "'";
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

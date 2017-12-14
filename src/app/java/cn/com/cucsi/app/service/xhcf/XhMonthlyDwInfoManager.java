package cn.com.cucsi.app.service.xhcf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.app.dao.xhcf.XhMonthlyDwInfoDao;
import cn.com.cucsi.app.entity.xhcf.XhMonthlyDwInfo;
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
public class XhMonthlyDwInfoManager {

	private XhMonthlyDwInfoDao xhMonthlyDwInfoDao;
	@Autowired
	public void setXhMonthlyDwInfoDao(XhMonthlyDwInfoDao xhMonthlyDwInfoDao) {
		this.xhMonthlyDwInfoDao = xhMonthlyDwInfoDao;
	}
	private JdbcDao jdbcDao;
	
	@Autowired
	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}
	@Transactional(readOnly = true)
	public Page<XhMonthlyDwInfo> searchXhMonthlyDwInfo(final Page<XhMonthlyDwInfo> page, final Map<String,Object> filters) {
		return xhMonthlyDwInfoDao.queryXhMonthlyDwInfo(page, filters);
	}
	@Transactional(readOnly = true)
	public XhMonthlyDwInfo getXhMonthlyDwInfo(Long id) {
		return xhMonthlyDwInfoDao.get(id);
	}

	public void saveXhMonthlyDwInfo(XhMonthlyDwInfo entity) {
		xhMonthlyDwInfoDao.save(entity);
	}

	/**
	 * 删除用户,如果尝试删除超级管理员将抛出异常.
	 */
	public void deleteXhMonthlyDwInfo(Long id) {
		xhMonthlyDwInfoDao.delete(id);
	}
	
	public boolean batchDelXhMonthlyDwInfo(String[] ids){
		
		try {
			for(String id: ids){
				deleteXhMonthlyDwInfo(Long.valueOf(id));
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Transactional(readOnly = true)
	public List<Map<String,Object>> searchXhMonthlyDwInfo(String queryName,Map<String,Object> filter,JdbcPage page)
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
		//利息
		if(filter.containsKey("interest")){
			value = String.valueOf(filter.get("interest"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.INTEREST = '" +  value + "'";
			}
		}
		//借款人名称
		if(filter.containsKey("loanName")){
			value = String.valueOf(filter.get("loanName"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.LOAN_NAME = '" +  value + "'";
			}
		}
		//金额
		if(filter.containsKey("money")){
			value = String.valueOf(filter.get("money"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.MONEY = '" +  value + "'";
			}
		}
		//月应付主表ID
		if(filter.containsKey("zqtjId")){
			value = String.valueOf(filter.get("zqtjId"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ZQTJ_ID = '" +  value + "'";
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

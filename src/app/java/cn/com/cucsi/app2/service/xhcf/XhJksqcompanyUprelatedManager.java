package cn.com.cucsi.app2.service.xhcf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.app2.dao.xhcf.XhJksqcompanyUprelatedDao;
import cn.com.cucsi.app2.entity.xhcf.XhJksqcompanyUprelated;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.orm.Page;



//Spring Bean的标识.
@Component
//默认将类中的所有函数纳入事务管理.
@Transactional
public class XhJksqcompanyUprelatedManager {

	private XhJksqcompanyUprelatedDao xhJksqcompanyUprelatedDao;
	@Autowired
	public void setXhJksqcompanyUprelatedDao(XhJksqcompanyUprelatedDao xhJksqcompanyUprelatedDao) {
		this.xhJksqcompanyUprelatedDao = xhJksqcompanyUprelatedDao;
	}
	private JdbcDao jdbcDao;
	
	@Autowired
	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}
	@Transactional(readOnly = true)
	public Page<XhJksqcompanyUprelated> searchXhJksqcompanyUprelated(final Page<XhJksqcompanyUprelated> page, final Map<String,Object> filters) {
		return xhJksqcompanyUprelatedDao.queryXhJksqcompanyUprelated(page, filters);
	}
	@Transactional(readOnly = true)
	public XhJksqcompanyUprelated getXhJksqcompanyUprelated(Long id) {
		return xhJksqcompanyUprelatedDao.get(id);
	}

	public void saveXhJksqcompanyUprelated(XhJksqcompanyUprelated entity) {
		xhJksqcompanyUprelatedDao.save(entity);
	}

	/**
	 * 删除用户,如果尝试删除超级管理员将抛出异常.
	 */
	public void deleteXhJksqcompanyUprelated(Long id) {
		xhJksqcompanyUprelatedDao.delete(id);
	}
	
	public boolean batchDelXhJksqcompanyUprelated(String[] ids){
		
		try {
			for(String id: ids){
				deleteXhJksqcompanyUprelated(Long.valueOf(id));
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Transactional(readOnly = true)
	public List<Map<String,Object>> searchXhJksqcompanyUprelated(String queryName,Map<String,Object> filter,JdbcPage page)
	{
		String sql="";
		String value = "";
		//公司名称
		if(filter.containsKey("companyName")){
			value = String.valueOf(filter.get("companyName"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.COMPANY_NAME = '" +  value + "'";
			}
		}
		//合同类型
		if(filter.containsKey("contactType")){
			value = String.valueOf(filter.get("contactType"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CONTACT_TYPE = '" +  value + "'";
			}
		}
		//合同金额
		if(filter.containsKey("contactMoney")){
			value = String.valueOf(filter.get("contactMoney"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CONTACT_MONEY = '" +  value + "'";
			}
		}
		//合同期限
		if(filter.containsKey("contactDue")){
			value = String.valueOf(filter.get("contactDue"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CONTACT_DUE = '" +  value + "'";
			}
		}
		//结算方式
		if(filter.containsKey("contactHandleType")){
			value = String.valueOf(filter.get("contactHandleType"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CONTACT_HANDLE_TYPE = '" +  value + "'";
			}
		}
		//电话核实情况
		if(filter.containsKey("phoneBackinfo")){
			value = String.valueOf(filter.get("phoneBackinfo"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.PHONE_BACKINFO = '" +  value + "'";
			}
		}
		//电话及来源
		if(filter.containsKey("phoneOther")){
			value = String.valueOf(filter.get("phoneOther"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.PHONE_OTHER = '" +  value + "'";
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

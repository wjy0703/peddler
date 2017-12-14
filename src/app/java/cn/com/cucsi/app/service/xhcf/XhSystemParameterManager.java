package cn.com.cucsi.app.service.xhcf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.app.dao.xhcf.XhSystemParameterDao;
import cn.com.cucsi.app.entity.xhcf.XhSystemParameter;
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
public class XhSystemParameterManager {

	private XhSystemParameterDao xhSystemParameterDao;
	@Autowired
	public void setXhSystemParameterDao(XhSystemParameterDao xhSystemParameterDao) {
		this.xhSystemParameterDao = xhSystemParameterDao;
	}
	private JdbcDao jdbcDao;
	
	@Autowired
	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}
	@Transactional(readOnly = true)
	public Page<XhSystemParameter> searchXhSystemParameter(final Page<XhSystemParameter> page, final Map<String,Object> filters) {
		return xhSystemParameterDao.queryXhSystemParameter(page, filters);
	}
	@Transactional(readOnly = true)
	public XhSystemParameter getXhSystemParameter(Long id) {
		return xhSystemParameterDao.get(id);
	}

	public void saveXhSystemParameter(XhSystemParameter entity) {
		xhSystemParameterDao.save(entity);
	}

	/**
	 * 删除用户,如果尝试删除超级管理员将抛出异常.
	 */
	public void deleteXhSystemParameter(Long id) {
		xhSystemParameterDao.delete(id);
	}
	
	public boolean batchDelXhSystemParameter(String[] ids){
		
		try {
			for(String id: ids){
				deleteXhSystemParameter(Long.valueOf(id));
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Transactional(readOnly = true)
	public List<Map<String,Object>> searchXhSystemParameter(String queryName,Map<String,Object> filter,JdbcPage page)
	{
		String sql="";
		String value = "";
		//备注
		if(filter.containsKey("reamrk")){
			value = String.valueOf(filter.get("reamrk"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.REAMRK = '" +  value + "'";
			}
		}
		//变量值
		if(filter.containsKey("sysValue")){
			value = String.valueOf(filter.get("sysValue"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.SYS_VALUE = '" +  value + "'";
			}
		}
		//变量说明
		if(filter.containsKey("sysCname")){
			value = String.valueOf(filter.get("sysCname"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.SYS_CNAME = '" +  value + "'";
			}
		}
		//变量名
		if(filter.containsKey("sysName")){
			value = String.valueOf(filter.get("sysName"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.SYS_NAME = '" +  value + "'";
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

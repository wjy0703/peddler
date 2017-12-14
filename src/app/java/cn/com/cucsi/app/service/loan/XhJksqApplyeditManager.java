package cn.com.cucsi.app.service.loan;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.app.dao.xhcf.XhJksqApplyeditDao;
import cn.com.cucsi.app.entity.xhcf.XhJksqApplyedit;
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
public class XhJksqApplyeditManager {

	private XhJksqApplyeditDao xhJksqApplyeditDao;
	@Autowired
	public void setXhJksqApplyeditDao(XhJksqApplyeditDao xhJksqApplyeditDao) {
		this.xhJksqApplyeditDao = xhJksqApplyeditDao;
	}
	private JdbcDao jdbcDao;
	
	@Autowired
	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}
	@Transactional(readOnly = true)
	public Page<XhJksqApplyedit> searchXhJksqApplyedit(final Page<XhJksqApplyedit> page, final Map<String,Object> filters) {
		return xhJksqApplyeditDao.queryXhJksqApplyedit(page, filters);
	}
	@Transactional(readOnly = true)
	public XhJksqApplyedit getXhJksqApplyedit(Long id) {
		return xhJksqApplyeditDao.get(id);
	}

	public void saveXhJksqApplyedit(XhJksqApplyedit entity) {
		xhJksqApplyeditDao.save(entity);
	}

	@Transactional(readOnly = true)
	public XhJksqApplyedit getXhJksqApplyeditByJksqId(Long id) {
		String sql = "select id from xh_jksq_applyedit where STATE='0' and JKSQ_ID=" + id;
		List<Map<String,Object>> list = jdbcDao.searchByMergeSql(sql);
		if(list.size() == 1){
			return xhJksqApplyeditDao.get(Long.parseLong(list.get(0).get("ID")+""));
		}else{
			return null;
		}
	}
	/**
	 * 删除用户,如果尝试删除超级管理员将抛出异常.
	 */
	public void deleteXhJksqApplyedit(Long id) {
		xhJksqApplyeditDao.delete(id);
	}
	
	public boolean batchDelXhJksqApplyedit(String[] ids){
		
		try {
			for(String id: ids){
				deleteXhJksqApplyedit(Long.valueOf(id));
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Transactional(readOnly = true)
	public List<Map<String,Object>> searchXhJksqApplyedit(String queryName,Map<String,Object> filter,JdbcPage page)
	{
		String sql="";
		String value = "";
		//申请修改备注信息
		if(filter.containsKey("commentInfo")){
			value = String.valueOf(filter.get("commentInfo"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.COMMENT_INFO = '" +  value + "'";
			}
		}
		//申请修改人ID
		if(filter.containsKey("operatorId")){
			value = String.valueOf(filter.get("operatorId"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.OPERATOR_ID = '" +  value + "'";
			}
		}
		//状态 0：未处理，1已处理
		if(filter.containsKey("state")){
			value = String.valueOf(filter.get("state"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.STATE = '" +  value + "'";
			}
		}
		//借款申请ID
		if(filter.containsKey("jksqId")){
			value = String.valueOf(filter.get("jksqId"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.JKSQ_ID = '" +  value + "'";
			}
		}
		//备用字段
		if(filter.containsKey("bankup01")){
			value = String.valueOf(filter.get("bankup01"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.BANKUP01 = '" +  value + "'";
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

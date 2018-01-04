package cn.com.peddler.app.service.baseinfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.peddler.app.dao.JdbcDao;
import cn.com.peddler.app.dao.baseinfo.BusinessinfoDao;
import cn.com.peddler.app.entity.login.Businessinfo;
import cn.com.peddler.core.orm.JdbcPage;
import cn.com.peddler.core.orm.Page;



//Spring Bean的标识.
@Component
//默认将类中的所有函数纳入事务管理.
@Transactional
public class BusinessinfoManager {

	private BusinessinfoDao businessinfoDao;
	@Autowired
	public void setBusinessinfoDao(BusinessinfoDao businessinfoDao) {
		this.businessinfoDao = businessinfoDao;
	}
	private JdbcDao jdbcDao;
	
	@Autowired
	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}
	@Transactional(readOnly = true)
	public Page<Businessinfo> searchBusinessinfo(final Page<Businessinfo> page, final Map<String,Object> filters) {
		return businessinfoDao.queryBusinessinfo(page, filters);
	}
	@Transactional(readOnly = true)
	public Businessinfo getBusinessinfo(Long id) {
		return businessinfoDao.get(id);
	}

	public void saveBusinessinfo(Businessinfo entity) {
		businessinfoDao.save(entity);
	}

	/**
	 * 删除用户,如果尝试删除超级管理员将抛出异常.
	 */
	public void deleteBusinessinfo(Long id) {
		businessinfoDao.delete(id);
	}
	
	public boolean batchDelBusinessinfo(String[] ids){
		
		try {
			for(String id: ids){
				deleteBusinessinfo(Long.valueOf(id));
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	public void insertBusinessinfo(String insertName,Map<String,Object> conditions){
		jdbcDao.insertBySqlTemplate(insertName, conditions);
	}
	
	public void updateBusinessinfo(String updateName,Map<String,Object> conditions){
		jdbcDao.updateBySqlTemplate(updateName, conditions);
	}
	private Map<String, Object> fromBusinessinfoEntity(Businessinfo businessinfo){
    	Map<String, Object> conditions = new HashMap<String, Object>();
    	conditions.put("id", businessinfo.getId());
    	conditions.put("busiaccount", businessinfo.getBusiaccount());
    	conditions.put("businame", businessinfo.getBusiname());
    	conditions.put("corporation", businessinfo.getCorporation());
    	conditions.put("card", businessinfo.getCard());
    	conditions.put("phone", businessinfo.getPhone());
    	conditions.put("createtime", businessinfo.getCreatetime());
    	conditions.put("modifytime", businessinfo.getModifytime());
    	conditions.put("createuser", businessinfo.getCreateuser());
    	conditions.put("modifyuser", businessinfo.getModifyuser());
    	conditions.put("vtypes", businessinfo.getVtypes());
    	conditions.put("tctypes", businessinfo.getTctypes());
    	conditions.put("starttime", businessinfo.getStarttime());
    	conditions.put("overtime", businessinfo.getOvertime());
    	return conditions;
    }
	@Transactional(readOnly = true)
	public List<Map<String,Object>> searchBusinessinfo(String queryName,Map<String,Object> filter,JdbcPage page)
	{
		StringBuffer sql=new StringBuffer();
		String value = "";
		//公司账户
		if(filter.containsKey("busiaccount")){
			value = String.valueOf(filter.get("busiaccount"));
			if(StringUtils.isNotEmpty(value)) {
				sql.append(" and a.busiaccount = '").append(value).append("'");
				//sql = sql + " and a.busiaccount = '" +  value + "'";
			}
		}
		//公司名
		if(filter.containsKey("businame")){
			value = String.valueOf(filter.get("businame"));
			if(StringUtils.isNotEmpty(value)) {
				sql.append(" and a.businame = '").append(value).append("'");
				//sql = sql + " and a.businame = '" +  value + "'";
			}
		}
		//法人
		if(filter.containsKey("corporation")){
			value = String.valueOf(filter.get("corporation"));
			if(StringUtils.isNotEmpty(value)) {
				sql.append(" and a.corporation = '").append(value).append("'");
				//sql = sql + " and a.corporation = '" +  value + "'";
			}
		}
		//证件号码
		if(filter.containsKey("card")){
			value = String.valueOf(filter.get("card"));
			if(StringUtils.isNotEmpty(value)) {
				sql.append(" and a.card = '").append(value).append("'");
				//sql = sql + " and a.card = '" +  value + "'";
			}
		}
		//联系方式
		if(filter.containsKey("phone")){
			value = String.valueOf(filter.get("phone"));
			if(StringUtils.isNotEmpty(value)) {
				sql.append(" and a.phone = '").append(value).append("'");
				//sql = sql + " and a.phone = '" +  value + "'";
			}
		}
		//创建时间
		if(filter.containsKey("createtime")){
			value = String.valueOf(filter.get("createtime"));
			if(StringUtils.isNotEmpty(value)) {
				sql.append(" and a.createtime = '").append(value).append("'");
				//sql = sql + " and a.createtime = '" +  value + "'";
			}
		}
		//修改时间
		if(filter.containsKey("modifytime")){
			value = String.valueOf(filter.get("modifytime"));
			if(StringUtils.isNotEmpty(value)) {
				sql.append(" and a.modifytime = '").append(value).append("'");
				//sql = sql + " and a.modifytime = '" +  value + "'";
			}
		}
		//创建人
		if(filter.containsKey("createuser")){
			value = String.valueOf(filter.get("createuser"));
			if(StringUtils.isNotEmpty(value)) {
				sql.append(" and a.createuser = '").append(value).append("'");
				//sql = sql + " and a.createuser = '" +  value + "'";
			}
		}
		//修改人
		if(filter.containsKey("modifyuser")){
			value = String.valueOf(filter.get("modifyuser"));
			if(StringUtils.isNotEmpty(value)) {
				sql.append(" and a.modifyuser = '").append(value).append("'");
				//sql = sql + " and a.modifyuser = '" +  value + "'";
			}
		}
		//属性（在用、欠费、停用）
		if(filter.containsKey("vtypes")){
			value = String.valueOf(filter.get("vtypes"));
			if(StringUtils.isNotEmpty(value)) {
				sql.append(" and a.vtypes = '").append(value).append("'");
				//sql = sql + " and a.vtypes = '" +  value + "'";
			}
		}
		//套餐类型
		if(filter.containsKey("tctypes")){
			value = String.valueOf(filter.get("tctypes"));
			if(StringUtils.isNotEmpty(value)) {
				sql.append(" and a.tctypes = '").append(value).append("'");
				//sql = sql + " and a.tctypes = '" +  value + "'";
			}
		}
		//生效时间
		if(filter.containsKey("starttime")){
			value = String.valueOf(filter.get("starttime"));
			if(StringUtils.isNotEmpty(value)) {
				sql.append(" and a.starttime = '").append(value).append("'");
				//sql = sql + " and a.starttime = '" +  value + "'";
			}
		}
		//到期时间
		if(filter.containsKey("overtime")){
			value = String.valueOf(filter.get("overtime"));
			if(StringUtils.isNotEmpty(value)) {
				sql.append(" and a.overtime = '").append(value).append("'");
				//sql = sql + " and a.overtime = '" +  value + "'";
			}
		}
		
		if (page.getOrderBy()!=null){
//			sql = sql + " order by " + page.getOrderBy() + " " + page.getOrder();
			sql.append(" order by ").append(page.getOrderBy()).append(" ").append(page.getOrder());
		}
		
		System.out.println("sql=======>" + sql.toString());
		Map<String, Object> conditions = new HashMap<String,Object>();
		conditions.put("sql", sql.toString());
		
		return jdbcDao.searchPagesByMergeSqlTemplate(queryName, conditions, page);
	}
}

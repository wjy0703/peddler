package cn.com.cucsi.app2.service.xhcf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.app.dao.loan.XhJksqDao;
import cn.com.cucsi.app.entity.xhcf.XhJksq;
import cn.com.cucsi.app2.dao.xhcf.XhJksqRelationsDao;
import cn.com.cucsi.app2.entity.xhcf.XhJksqRelations;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.orm.Page;



//Spring Bean的标识.
@Component
//默认将类中的所有函数纳入事务管理.
@Transactional
public class XhJksqRelationsManager {

	private XhJksqRelationsDao xhJksqRelationsDao;
	@Autowired
	private XhJksqDao xhJksqDao;
	
	
	@Autowired
	public void setXhJksqRelationsDao(XhJksqRelationsDao xhJksqRelationsDao) {
		this.xhJksqRelationsDao = xhJksqRelationsDao;
	}
	private JdbcDao jdbcDao;
	
	@Autowired
	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}
	@Transactional(readOnly = true)
	public Page<XhJksqRelations> searchXhJksqRelations(final Page<XhJksqRelations> page, final Map<String,Object> filters) {
		return xhJksqRelationsDao.queryXhJksqRelations(page, filters);
	}
	@Transactional(readOnly = true)
	public XhJksqRelations getXhJksqRelations(Long id) {
		return xhJksqRelationsDao.get(id);
	}

	public void saveXhJksqRelations(XhJksqRelations entity) {
		xhJksqRelationsDao.save(entity);
	}

	/**
	 * 删除用户,如果尝试删除超级管理员将抛出异常.
	 */
	public void deleteXhJksqRelations(Long id) {
		xhJksqRelationsDao.delete(id);
	}
	
	public boolean batchDelXhJksqRelations(String[] ids){
		
		try {
			for(String id: ids){
				deleteXhJksqRelations(Long.valueOf(id));
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Transactional(readOnly = true)
	public List<Map<String,Object>> searchXhJksqRelations(String queryName,Map<String,Object> filter,JdbcPage page)
	{
		String sql="";
		String value = "";
		//直系亲属姓名
		if(filter.containsKey("name")){
			value = String.valueOf(filter.get("name"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.NAME = '" +  value + "'";
			}
		}
		//直系亲属身份证号
		if(filter.containsKey("zjhm")){
			value = String.valueOf(filter.get("zjhm"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ID_CARD = '" +  value + "'";
			}
		}
		//直系亲属年龄
		if(filter.containsKey("age")){
			value = String.valueOf(filter.get("age"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.AGE = '" +  value + "'";
			}
		}
		//直系亲属电话
		if(filter.containsKey("phone")){
			value = String.valueOf(filter.get("phone"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.PHONE = '" +  value + "'";
			}
		}
		//单位名称
		if(filter.containsKey("office")){
			value = String.valueOf(filter.get("office"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.OFFICE = '" +  value + "'";
			}
		}
		//职务
		if(filter.containsKey("officePosition")){
			value = String.valueOf(filter.get("officePosition"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.OFFICE_POSITION = '" +  value + "'";
			}
		}
		//单位地址
		if(filter.containsKey("officeAdress")){
			value = String.valueOf(filter.get("officeAdress"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.OFFICE_ADRESS = '" +  value + "'";
			}
		}
		//单位电话
		if(filter.containsKey("officePhone")){
			value = String.valueOf(filter.get("officePhone"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.OFFICE_PHONE = '" +  value + "'";
			}
		}
		//月收入
		if(filter.containsKey("monthIncome")){
			value = String.valueOf(filter.get("monthIncome"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.MONTH_INCOME = '" +  value + "'";
			}
		}
		//直系亲属类别（0：父母，1：配偶，2：子女）
		if(filter.containsKey("relClass")){
			value = String.valueOf(filter.get("relClass"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.REL_CLASS = '" +  value + "'";
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
	
//	public List<XhJksq> findJksqByZjhm(String idCard) {
//		return xhJksqDao.listJksqByIdCardFromRelation(idCard);
//	}
}

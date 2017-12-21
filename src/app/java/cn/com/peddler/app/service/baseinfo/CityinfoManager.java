package cn.com.peddler.app.service.baseinfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.peddler.app.dao.JdbcDao;
import cn.com.peddler.app.entity.login.Operatelog;
import cn.com.peddler.app.entity.login.Cityinfo;
import cn.com.peddler.app.service.ServiceException;
import cn.com.peddler.core.orm.JdbcPage;
import cn.com.peddler.core.orm.Page;
import cn.com.peddler.core.security.springsecurity.SpringSecurityUtils;
import cn.com.peddler.core.utils.EncodeUtils;
import cn.com.peddler.core.utils.PropertiesUtils;



//Spring Bean的标识.
@Component
//默认将类中的所有函数纳入事务管理.
@Transactional
public class CityinfoManager {

	
	private JdbcDao jdbcDao;
	
	@Autowired
	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}
	
	
	public void insertCityinfo(String insertName,Map<String,Object> conditions){
		jdbcDao.insertBySqlTemplate(insertName, conditions);
	}
	
	public void updateCityinfo(String updateName,Map<String,Object> conditions){
		jdbcDao.updateBySqlTemplate(updateName, conditions);
	}
	private Map<String, Object> fromCityinfoEntity(Cityinfo cityinfo){
    	Map<String, Object> conditions = new HashMap<String, Object>();
    	conditions.put("id", cityinfo.getId());
    	conditions.put("cname", cityinfo.getCname());
    	conditions.put("coding", cityinfo.getCoding());
    	conditions.put("deptlevel", cityinfo.getDeptlevel());
    	conditions.put("vname", cityinfo.getVname());
    	conditions.put("pinyin", cityinfo.getPinyin());
    	conditions.put("sortno", cityinfo.getSortno());
    	conditions.put("vtypes", cityinfo.getVtypes());
    	conditions.put("parentid", cityinfo.getParentid());
    	conditions.put("createtime", cityinfo.getCreatetime());
    	conditions.put("modifytime", cityinfo.getModifytime());
    	conditions.put("createuser", cityinfo.getCreateuser());
    	conditions.put("modifyuser", cityinfo.getModifyuser());
    	return conditions;
    }
	
	private Cityinfo getCityinfoFromMap(Map<String, Object> map){
		Cityinfo cityinfo=new Cityinfo();
    	cityinfo.setId(Long.parseLong(map.get("id")+""));
		cityinfo.setCname(map.get("cname")+"");
		cityinfo.setCoding(map.get("coding")+"");
		cityinfo.setDeptlevel(Long.parseLong(map.get("deptlevel")+""));
		cityinfo.setVname(map.get("vname")+"");
		cityinfo.setPinyin(map.get("pinyin")+"");
		cityinfo.setSortno(Long.parseLong(map.get("sortno")+""));
		cityinfo.setVtypes(map.get("vtypes")+"");
		cityinfo.setParentid(Long.parseLong(map.get("parentid")+""));
		cityinfo.setCreateuser(map.get("createuser")+"");
		cityinfo.setModifyuser(map.get("modifyuser")+"");
    	return cityinfo;
    }
	@Transactional(readOnly = true)
	public List<Map<String,Object>> searchCityinfo(String queryName,Map<String,Object> filter,JdbcPage page)
	{
		StringBuffer sql=new StringBuffer();
		String value = "";
		//别名
		if(filter.containsKey("cname")){
			value = String.valueOf(filter.get("cname"));
			if(StringUtils.isNotEmpty(value)) {
				sql.append(" and a.cname = '").append(value).append("'");
				//sql = sql + " and a.cname = '" +  value + "'";
			}
		}
		//编码
		if(filter.containsKey("coding")){
			value = String.valueOf(filter.get("coding"));
			if(StringUtils.isNotEmpty(value)) {
				sql.append(" and a.coding = '").append(value).append("'");
				//sql = sql + " and a.coding = '" +  value + "'";
			}
		}
		//等级
		if(filter.containsKey("deptlevel")){
			value = String.valueOf(filter.get("deptlevel"));
			if(StringUtils.isNotEmpty(value)) {
				sql.append(" and a.deptlevel = '").append(value).append("'");
				//sql = sql + " and a.deptlevel = '" +  value + "'";
			}
		}
		//地区名称
		if(filter.containsKey("vname")){
			value = String.valueOf(filter.get("vname"));
			if(StringUtils.isNotEmpty(value)) {
				sql.append(" and a.vname = '").append(value).append("'");
				//sql = sql + " and a.vname = '" +  value + "'";
			}
		}
		//拼音
		if(filter.containsKey("pinyin")){
			value = String.valueOf(filter.get("pinyin"));
			if(StringUtils.isNotEmpty(value)) {
				sql.append(" and a.pinyin = '").append(value).append("'");
				//sql = sql + " and a.pinyin = '" +  value + "'";
			}
		}
		//排序
		if(filter.containsKey("sortno")){
			value = String.valueOf(filter.get("sortno"));
			if(StringUtils.isNotEmpty(value)) {
				sql.append(" and a.sortno = '").append(value).append("'");
				//sql = sql + " and a.sortno = '" +  value + "'";
			}
		}
		//状态
		if(filter.containsKey("vtypes")){
			value = String.valueOf(filter.get("vtypes"));
			if(StringUtils.isNotEmpty(value)) {
				sql.append(" and a.vtypes = '").append(value).append("'");
				//sql = sql + " and a.vtypes = '" +  value + "'";
			}
		}
		//上级地区
		if(filter.containsKey("parentid")){
			value = String.valueOf(filter.get("parentid"));
			if(StringUtils.isNotEmpty(value)) {
				sql.append(" and a.parentid = '").append(value).append("'");
				//sql = sql + " and a.parentid = '" +  value + "'";
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

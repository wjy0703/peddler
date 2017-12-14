package cn.com.cucsi.app.service.xhcf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.NamedJdbcDao;
import cn.com.cucsi.app.dao.security.CldkzxDao;
import cn.com.cucsi.app.dao.security.FcdkzxDao;
import cn.com.cucsi.app.dao.security.XydkzxDao;
import cn.com.cucsi.app.entity.baseinfo.Employee;
import cn.com.cucsi.app.entity.baseinfo.Xydkzx;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.utils.PropertiesUtils;

@Component
//默认将类中的所有函数纳入事务管理.
@Transactional
public class DksqManager {
    
    private static Logger logger = LoggerFactory.getLogger(DksqManager.class);
    
    @Autowired
    private XydkzxDao xydkzxDao;
	@Autowired
	private FcdkzxDao fcdkzxDao;
	@Autowired
	private CldkzxDao cldkzxDao;
	@Autowired
	private NamedJdbcDao jdbcDao;
	
	public Page<Xydkzx> searchXydkzx(final Page<Xydkzx> page, final Map<String,Object> filters) {
		return xydkzxDao.queryXydkzx(page, filters);
	}
	@Transactional(readOnly = true)
	public Page<Xydkzx> searchFcdkzx(final Page<Xydkzx> page, final Map<String,Object> filters) {
		return fcdkzxDao.queryXydkzx(page, filters);
	}
	public Page<Xydkzx> searchCldkzx(final Page<Xydkzx> page, final Map<String,Object> filters) {
		return cldkzxDao.queryXydkzx(page, filters);
	}
	
	public void saveXydkzx(Xydkzx xydkzx) {
		xydkzxDao.save(xydkzx);
	}
	
	public void deleteXydkzx(Long id) {
		xydkzxDao.delete(id);
	}
	public Xydkzx getXydkzx(Long id){
		return xydkzxDao.get(id);
	}
	
	public List<Map<String,Object>> searchKhzx(String queryName,Map<String,Object> filter,JdbcPage page)
	{
		String sql="";
		String value = "";
		if(filter.containsKey("zt")){
			value = String.valueOf(filter.get("zt"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ZHUANG_TAI = :zt";
		   }
		}
		if(filter.containsKey("dh")){
			value = String.valueOf(filter.get("dh"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.phone = :dh";
		   }
		}
		if(filter.containsKey("type")){
			value = String.valueOf(filter.get("type"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.type = :type";
		   }
		}
		if(filter.containsKey("name")){
			value = String.valueOf(filter.get("name"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.khmc = :name";
		   }
		}

		if(filter.containsKey("crmprovince")){
			value = String.valueOf(filter.get("crmprovince"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.crmprovince = :crmprovince";
			}
		}
		if(filter.containsKey("crty")){
			value = String.valueOf(filter.get("crty"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.crty = :crty";
			}
		}
		
		sql = sql + PropertiesUtils.getLoanSql();//MDY 后加2013-11-12
		
		String sql2 = "";
		if (filter.containsKey("organi.id")) {// 门店查询
			value = String.valueOf(filter.get("organi.id"));
			if (StringUtils.isNotEmpty(value)) {
				if (!"".equals(value)) {
					sql2 = sql2 + " and yybid = " + value + "";
				}
			}
		}

		//sql = sql + getSql(filter);删除
		if (page.getOrderBy()!=null){
			sql = sql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}else {
			sql = sql + " order by a.CREATE_TIME desc ";

		}		
		logger.debug("sql=======>" + sql);
		filter.put("sql", sql);
		filter.put("sql2", sql2);
		return jdbcDao.searchPagesByMergeSqlTemplate(queryName, filter, page);
	}
	
	public static String getSql(Map<String,Object> filter){
		String sql = "";
		String value = "";
		String value2 = "";
		Employee emp;
		if(filter.containsKey("emp")){
			//员工
			emp = (Employee)filter.get("emp");
			if(emp != null && emp.getPosition() != null){
				//职务编号
				String positionCode = emp.getPosition().getPositionCode();
				if(StringUtils.isNotEmpty(positionCode)) {
					//团队经理
					if(positionCode.equals("0002")){
						if(filter.containsKey("loginName")){
							value = String.valueOf(filter.get("loginName"));
							value2 = String.valueOf(emp.getOrgani().getId());
							if(StringUtils.isNotEmpty(value) && StringUtils.isNotEmpty(value2)) {
								sql = sql + " and (a.create_by='" + value+"' or a.organi_id in " +
							"(select id from BASE_ZZJG t start with t.parent_id =" + value2 + 
							" connect by nocycle t.parent_id = prior id))";
							}
						}
					}
					//客户经理
					if(positionCode.equals("0001")){
						if(filter.containsKey("loginName")){
							value = String.valueOf(filter.get("loginName"));
							if(StringUtils.isNotEmpty(value)) {
								sql = sql + " and a.create_by='" + value+"'" ;
							}
						}
					}
//					if(positionCode.equals("0007")){
//						if(filter.containsKey("loginName")){
//							value = String.valueOf(filter.get("loginName"));
//							value2 = String.valueOf(emp.getOrgani().getId());
//							if(StringUtils.isNotEmpty(value)) {
////								sql = sql + " and a.create_by='" + value+"'" ;
//								sql = sql + " and a.organi_id='" + value2+"'" ;
//							}
//						}
//					}
				}
			}
		}
		
		return sql;
	}
}

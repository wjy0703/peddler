package cn.com.cucsi.app.dao.baseinfo;


import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.baseinfo.Tzcp;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

/**
 * 菜单对象的泛型DAO.
 * 
 * @author 马道永
 */
@Component
public class TzcpDao extends HibernateDao<Tzcp, Long> {
	
	public Page<Tzcp> queryCp(Page<Tzcp> page, Map<String, Object> filters) {
		String hql = "from Tzcp tzcp where 1=1";
		
		if(filters.containsKey("tzcpMc")){
			hql = hql + " and tzcp.tzcpMc like '%'||:tzcpMc||'%'";
		}
		if(filters.containsKey("sts")){
			hql = hql + " and tzcp.tzcpZt = :sts";
		}
		if(filters.containsKey("tzcpFl")){
			hql = hql + " and tzcp.tzcpFl = :tzcpFl";
		}
		
		if (page.getOrderBy()!=null){
			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		return this.findPage(page, hql, filters);
	}		
	
}

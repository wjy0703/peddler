package cn.com.cucsi.app.dao.baseinfo;


import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.baseinfo.MateData;
import cn.com.cucsi.app.entity.baseinfo.Tzcp;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

/**
 * 菜单对象的泛型DAO.
 * 
 * @author 马道永
 */
@Component
public class MateDataDao extends HibernateDao<MateData, Long> {
	
	public Page<MateData> queryMateData(Page<MateData> page, Map<String, Object> filters) {
		String hql = "from MateData mateData where 1=1";
		
		if(filters.containsKey("name")){
			hql = hql + " and name like '%'||:name||'%'";
		}
		if(filters.containsKey("state")){
			hql = hql + " and state = :state";
		}
		if(filters.containsKey("code")){
			String code = filters.get("code").toString();
			hql = hql + " and code.id = "+Long.valueOf(code);
			filters.remove("code");
		}
		
		if (page.getOrderBy()!=null){
			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		return this.findPage(page, hql, filters);
	}		
	
}

package cn.com.cucsi.app.dao.baseinfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.baseinfo.City;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;
@Component
public class CityDao extends HibernateDao<City, Long> {

	public List<City> getSuggestCity(String parentId) {
		Map<String, Object> filter = new HashMap<String, Object>();
		filter.put("parentId", parentId);
		String hql= "from City city where parent.id=''||:parentId||'' order by id "; 
		return this.find(hql,filter);
	}
	
}

package cn.com.cucsi.app.dao.baseinfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.baseinfo.Dept;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

/**
 * 部门对象的泛型DAO.
 * 
 * @author jiangxd
 */
@Component
public class DeptDao extends HibernateDao<Dept, Long> {

	public List<Dept> getSuggestDept(String pinyin) {
		Map<String, Object> filter = new HashMap<String, Object>();
		filter.put("pinyin", pinyin);
		String hql= "from Dept dept where name like '%'||:pinyin||'%' or pinyin like '%'||:pinyin||'%"; 
		return this.find(hql,filter);
	}

}

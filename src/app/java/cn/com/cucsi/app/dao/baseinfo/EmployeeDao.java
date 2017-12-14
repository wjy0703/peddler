package cn.com.cucsi.app.dao.baseinfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.baseinfo.Employee;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.CriteriaSetup;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

/**
 * 员工对象的泛型DAO.
 * 
 * @author jiangxd
 */
@Component
public class EmployeeDao extends HibernateDao<Employee, Long> {

	public Page<Employee> queryEmployee(final Page<Employee> page,
			Map<String, Object> filters) {
		return findPage(page, filters, empLookupCriteriaSetup());
	}

	/**
	 * 通过名字查找employee
	 * @param name
	 * @return
	 */
	public List<Employee> queryByName(String name){
		String hql = "from Employee  where  name = :name";
		Map map = new HashMap();
		map.put("name", name);
		return this.find(hql, map);		
	}
	
	protected CriteriaSetup empLookupCriteriaSetup() {
		return new CriteriaSetup() {
			public void setup(Criteria criteria, Map<String, Object> params) {
				if (params.containsKey("organi.rganiName")) {
					criteria.createAlias("organi", "organi");
					criteria.add(Restrictions.like("organi.rganiName",
							(String) params.get("organi.rganiName"),
							MatchMode.ANYWHERE));
				}

				if (params.containsKey("position.positionName")) {
					criteria.createAlias("position", "position");
					criteria.add(Restrictions.like("position.positionName",
							(String) params.get("position.positionName"),
							MatchMode.ANYWHERE));
				}
				if (params.containsKey("organi.id")) {
					criteria.createAlias("organi", "organi");
					String id = params.get("organi.id").toString();
					criteria.add(Restrictions.eq("organi.id", Long.valueOf(id)));
				}
				if (params.containsKey("position_id")) {
					criteria.createAlias("position", "position");
					String id = params.get("position_id").toString();
					criteria.add(Restrictions.eq("position.id", Long.valueOf(id)));
				}
				if (params.containsKey("name")) {
					criteria.add(Restrictions.like("name",
							(String) params.get("name"), MatchMode.ANYWHERE));
				}
				
				if (params.containsKey("empNo")) {
					criteria.add(Restrictions.like("empNo",
							(String) params.get("empNo"), MatchMode.ANYWHERE));
				}

				if (params.containsKey("sts")) {
					criteria.add(Restrictions.eq("sts",
							 params.get("sts")));
				}
				if (params.containsKey("parentFlag")) {
					Object[] objs = {Long.parseLong(params.get("lookId")+""),Long.parseLong(params.get("parentId")+"")};
					criteria.add(Restrictions.in("organi.id", objs));
				}else{
					if (params.containsKey("lookId")) {
						String data = (String) params.get("lookId");
						// Long[] lookIds = new Long[data.split(",").length];
						// if(params.get("lookId") != null &&
						// !params.get("lookId").equals("")){
						// String[] newData = data.split(",");
						// for(int i = 0 ; i < newData.length ; i++){
						// lookIds[i] = Long.valueOf(newData[i]);
						// }
						// }
						// criteria.add(Restrictions.in("organi.id", lookIds));
						criteria.add(Restrictions.eq("organi.id",
								Long.valueOf(data)));
					}
				}
				if (params.containsKey("code2")) {
					String code = (String) params.get("code");
					String code2 = (String) params.get("code2");
					Object[] codes = {code,code2};
					criteria.createAlias("position", "position");
					criteria.add(Restrictions.in("position.positionCode", codes));
				}else{
					if (params.containsKey("code")) {
						String code = (String) params.get("code");
						criteria.createAlias("position", "position");
						criteria.add(Restrictions.eq("position.positionCode", code));
					}
				}
			}
		};
	}

}

package cn.com.cucsi.app.dao.security;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.security.User;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

/**
 * 用户对象的泛型DAO类.
 * 
 * @author jiangxd
 */
@Component
public class UserDao extends HibernateDao<User, Long> {
	
	//查询员工是否被账户引用的hql语句
	private static String hql_isReferenceToEmployee = "from User user where user.employee.id=?";
	
	
	public boolean isReferenceToEmployee(Long employee_id){
		List<User> list = find(hql_isReferenceToEmployee, employee_id);
		return (list!=null)&&(list.size()>0);		
			
	}
	
	public Page<User> queryUser(Page<User> page, Map<String, Object> params){
		String hql = "from User user where 1=1";
		
		if(params.containsKey("loginName")){
			hql = hql + " and loginName like '%'||:loginName||'%'";
		}
		if(params.containsKey("sts")){
			hql = hql + " and sts = :sts";
		}
		if(params.containsKey("organi.id")){
			String id = params.get("organi.id").toString();
			hql = hql + " and employee.organi.id = "+Long.valueOf(id);
		}
		
		if (page.getOrderBy()!=null){
			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		return this.findPage(page, hql, params);
	}
}

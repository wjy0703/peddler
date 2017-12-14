package cn.com.cucsi.app.dao.security;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.security.Role;
import cn.com.cucsi.app.entity.security.User;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

/**
 * 角色对象的泛型DAO.
 * 
 * @author calvin
 */
@Component
public class RoleDao extends HibernateDao<Role, Long> {

	private static final String QUERY_USER_BY_ROLEID = "select u from User u left join u.roleList r where r.id=?";

	/**
	 * 重载函数,因为Role中没有建立与User的关联,因此需要以较低效率的方式进行删除User与Role的多对多中间表.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void delete(Long id) {
		Role role = get(id);
		//查询出拥有该角色的用户,并删除该用户的角色.
		List<User> users = createQuery(QUERY_USER_BY_ROLEID, role.getId()).list();
		for (User u : users) {
			u.getRoleList().remove(role);
		}
		super.delete(role);
	}
	
	@SuppressWarnings("unchecked")
	public List<Role> getRolesBySts(){
		Criteria criteria = createCriteria();
		Criterion criterion = Restrictions.eq("sts", "0");
		criteria.add(criterion);
		criteria.addOrder(Order.asc("id"));
		return criteria.list();
	}

	public Page<Role> queryRole(Page<Role> page, Map<String, Object> params) {
		String hql = "from Role role where 1=1";
		
		if(params.containsKey("name")){
			hql = hql + " and name like '%'||:name||'%'";
		}
		if(params.containsKey("sts")){
			hql = hql + " and sts = :sts";
		}
		
		if (page.getOrderBy()!=null){
			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		return this.findPage(page, hql, params);
	}
}

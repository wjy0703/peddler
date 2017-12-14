package cn.com.cucsi.app.dao.security;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.security.Authority;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

/**
 * 授权对象的泛型DAO.
 * 
 * @author calvin
 */
@Component
public class AuthorityDao extends HibernateDao<Authority, Long> {
	
	public List<Authority> queryAuthorities(){
		String hql = "from Authority authority where authority.sts=? order by authority.id asc";
		return this.find(hql, "0");
	}

	public Page<Authority> queryAuth(Page<Authority> page, Map<String, Object> params) {
		String hql = "from Authority authority where 1=1";
		
		if(params.containsKey("name")){
			hql = hql + " and name like '%'||:name||'%'";
		}

		if(params.containsKey("cname")){
			hql = hql + " and cname like '%'||:cname||'%'";
		}
		
		if(params.containsKey("path")){
			hql = hql + " and path like '%'||:path||'%'";
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

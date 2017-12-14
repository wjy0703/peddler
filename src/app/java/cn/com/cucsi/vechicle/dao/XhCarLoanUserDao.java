package cn.com.cucsi.vechicle.dao;

import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;
import cn.com.cucsi.vechicle.entity.xhcf.XhCarLoanUser;

@Component
public class XhCarLoanUserDao extends HibernateDao<XhCarLoanUser, Long>{

	public Page<XhCarLoanUser> queryXhCarLoanUser(Page<XhCarLoanUser> page, Map<String, Object> params){
		String hql = " from XhCarLoanUser xhCarLoanUser where 1=1 ";
		
		if(params.containsKey("userName")){
			hql = hql + " and userName like '%'||:userName||'%'";
		}
		
		if (page.getOrderBy()!=null){
			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		return this.findPage(page, hql, params);
	}
}

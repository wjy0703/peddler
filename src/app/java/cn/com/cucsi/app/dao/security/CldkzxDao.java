package cn.com.cucsi.app.dao.security;

import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.baseinfo.Tzcp;
import cn.com.cucsi.app.entity.baseinfo.Xydkzx;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

@Component
public class CldkzxDao extends HibernateDao<Xydkzx, Long> {
	public Page<Xydkzx> queryXydkzx(Page<Xydkzx> page, Map<String, Object> filters) {
		String hql = "from Xydkzx xydkzx where 1=1 and xydkzx.zhongLi='车贷'";
		
		if(filters.containsKey("name")){
			hql = hql + " and xydkzx.khmc like '%'||:name||'%'";
		}
		if(filters.containsKey("crty")){
			hql = hql + " and xydkzx.crty = :crty";
		}
		if(filters.containsKey("teamName")){
			hql = hql + " and xydkzx.teamName = :teamName";
		}
		if(filters.containsKey("saleName")){
			hql = hql + " and xydkzx.saleName = :saleName";
		}
		if(filters.containsKey("carNumber")){
			hql = hql + " and xydkzx.carNumber = :carNumber";
		}
		
		if (page.getOrderBy()!=null){
			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		return this.findPage(page, hql, filters);
	}		
}

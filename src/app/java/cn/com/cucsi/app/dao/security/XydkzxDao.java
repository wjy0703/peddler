package cn.com.cucsi.app.dao.security;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.baseinfo.Tzcp;
import cn.com.cucsi.app.entity.baseinfo.Xydkzx;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

@Component
public class XydkzxDao extends HibernateDao<Xydkzx, Long> {
	public Page<Xydkzx> queryXydkzx(Page<Xydkzx> page, Map<String, Object> filters) {
		String hql = "from Xydkzx xydkzx where 1=1 ";
		String value = "";
		if(filters.containsKey("name")){
			hql = hql + " and xydkzx.khmc like '%'||:name||'%'";
		}
		if(filters.containsKey("zt")){
			hql = hql + " and xydkzx.zhuangTai = :zt";
		}
		if(filters.containsKey("dh")){
			hql = hql + " and xydkzx.phone = :dh";
		}
		if(filters.containsKey("crmcity")){
			hql = hql + " and xydkzx.crty = :crmcity";
		}
		if(filters.containsKey("employeeCrm.id")){
			hql = hql + " and xydkzx.employeeCrm = :employeeCrm.id";
		}
		if(filters.containsKey("employeeCca.id")){
			hql = hql + " and xydkzx.saleName = :employeeCca.id";
		}
		if(filters.containsKey("crmprovince")){
			value = String.valueOf(filters.get("crmprovince"));
			if(StringUtils.isNotEmpty(value)) {
				hql = hql + " and xydkzx.crmprovince = '" +  value + "'";
			}
		}
		if(filters.containsKey("crty")){
			value = String.valueOf(filters.get("crty"));
			if(StringUtils.isNotEmpty(value)) {
				hql = hql + " and xydkzx.crty = '" +  value + "'";
			}
		}
		if (page.getOrderBy()!=null){
			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		return this.findPage(page, hql, filters);
	}		
}

package cn.com.cucsi.app.dao.xhcf;

import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.xhcf.XhTzsq;
import cn.com.cucsi.app.entity.xhcf.ZxGtjl;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

@Component
public class ZxGtglDao extends HibernateDao<ZxGtjl, Long>{
	public Page<ZxGtjl> queryZxGtjl(Page<ZxGtjl> page, Map<String, Object> params){
		String hql = "from ZxGtjl zxGtjl where 1=1";
		
		if(params.containsKey("xydkzx_id")){
			params.put("xydkzx_id", Long.parseLong(params.get("xydkzx_id")+""));
			hql = hql + " and xydkzx_id = :xydkzx_id";
		}
		if (page.getOrderBy()!=null){
			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		System.out.println("hql===>" +hql);
		return this.findPage(page, hql, params);
	}

}

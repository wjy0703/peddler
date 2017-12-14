package cn.com.cucsi.app.dao.xhcf;

import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.xhcf.XhHouseloans;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

@Component
public class xhHouseLoansDao extends HibernateDao<XhHouseloans, Long>{
	public Page<XhHouseloans> queryHouseLoans(Page<XhHouseloans> page, Map<String, Object> params){
		String hql = "from XhHouseloans houseloans where 1=1";
		
//		if(params.containsKey("cjrbh")){
//		hql = hql + " and cjrbh like '%'||:cjrbh||'%'";
//	    }
//		if(params.containsKey("cjrState")){
//		hql = hql + " and cjrState = :cjrState";
//	    }
	    if (page.getOrderBy()!=null){
		hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
	    }
		return this.findPage(page, hql, params);
		}
}

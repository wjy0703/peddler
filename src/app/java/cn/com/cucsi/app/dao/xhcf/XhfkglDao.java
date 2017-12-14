package cn.com.cucsi.app.dao.xhcf;

import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.xhcf.FangKuangGuanLi;
import cn.com.cucsi.app.entity.xhcf.XhFkgl;
import cn.com.cucsi.app.entity.xhcf.XhcfCjrxx;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

@Component
public class XhfkglDao extends HibernateDao<XhFkgl, Long>{

	public Page<XhFkgl> queryFkgl(Page<XhFkgl> page, Map<String, Object> params){
		String hql = "from XhFkgl fkgl where 1=1";
		
		if(params.containsKey("cjrbh")){
			hql = hql + " and cjrbh like '%'||:cjrbh||'%'";
		}
		if(params.containsKey("ztFlag")){
			hql = hql + " and ztFlag = :ztFlag";
		}
		if(params.containsKey("cjrState")){
			hql = hql + " and cjrState = :cjrState";
		}
		if (page.getOrderBy()!=null){
			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		if(params.containsKey("fkrq")){
			hql = hql + " and sjfkrq >=:fkrq";
		}
		if(params.containsKey("fkrq1")){
			hql = hql + " and sjfkrq <=:fkrq1";
		}
		return this.findPage(page, hql, params);
	}

}

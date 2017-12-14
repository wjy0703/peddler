package cn.com.cucsi.app.dao.xhcf;

import java.util.Map;

import org.springframework.stereotype.Component;


import cn.com.cucsi.app.entity.xhcf.FangKuangGuanLi;
import cn.com.cucsi.app.entity.xhcf.XhcfCjrxx;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;
@Component
public class FangKuanGuanLiDao extends HibernateDao<FangKuangGuanLi, Long>{
	public Page<FangKuangGuanLi> queryFangKuangGuanLi(Page<FangKuangGuanLi> page, Map<String, Object> params){
		String hql = "from FangKuangGuanLi fkgl where 1=1";
		
		if(params.containsKey("jkrbh")){
			hql = hql + " and jkrbh like '%'||:jkrbh||'%'";
		}
		if(params.containsKey("jkrxm")){
			hql = hql + " and jkrxm = :jkrxm";
		}
		if(params.containsKey("fksj")){
			hql = hql + " and fksj >=:fksj";
//			String startDate = params.get("fksj").toString();
//			params.remove("startDate");
//			hql = hql + " and fksj > TO_DATE('"+startDate+"','yyyy-MM-dd')";
		}
		if(params.containsKey("fksj1")){
			hql = hql + " and fksj <=:fksj1";
//			String startDate = params.get("fksj1").toString();
//			params.remove("startDate");
//			hql = hql + " and fksj > TO_DATE('"+startDate+"','yyyy-MM-dd')";
		}
		
		if (page.getOrderBy()!=null){
			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		return this.findPage(page, hql, params);
	}

}

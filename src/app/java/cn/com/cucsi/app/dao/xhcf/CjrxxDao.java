package cn.com.cucsi.app.dao.xhcf;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.tool.hbm2x.StringUtils;
import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.xhcf.XhcfCjrxx;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

@Component
public class CjrxxDao extends HibernateDao<XhcfCjrxx, Long>{

	public Page<XhcfCjrxx> queryCjrxx(Page<XhcfCjrxx> page, Map<String, Object> params){
		String hql = "from XhcfCjrxx cjr where 1=1";
		
		if(params.containsKey("cjrxm")){
			hql = hql + " and cjrxm like '%'||:cjrxm||'%'";
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
		return this.findPage(page, hql, params);
	}
	
	public List<XhcfCjrxx> queryCjrxxByNameAndLeader(String name,String createBy){
		String hql = "from XhcfCjrxx cjr where 1=1 and cjrxm = :cjrxm";
		Map map = new HashMap();
		map.put("cjrxm", name); 
		if(StringUtils.isNotEmpty(createBy)){
			hql += " and createBy = :createBy";
			map.put("createBy", createBy);
		}
		return this.find(hql, map);		
	}
	
	public List<XhcfCjrxx> queryCjrxxByName(String name){
		String hql = "from XhcfCjrxx cjr where 1=1 and cjrxm = :cjrxm";
		Map map = new HashMap();
		map.put("cjrxm", name);
		return this.find(hql, map);		
	}
}

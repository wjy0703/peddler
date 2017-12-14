package cn.com.cucsi.app.dao.xhcf;

import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.xhcf.XhMadeword;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

@Component
public class XhMadewordDao extends HibernateDao<XhMadeword, Long>{

	public Page<XhMadeword> queryXhMadeword(Page<XhMadeword> page, Map<String, Object> params){
		String hql = "from XhMadeword xhMadeword where 1=1";
		//任务表ID
		if(params.containsKey("tableId")){
			hql = hql + " and tableId = :tableId";
		}
		//任务表名称
		if(params.containsKey("tableName")){
			hql = hql + " and tableName = :tableName";
		}
		//状态0待制作1已制作
		if(params.containsKey("state")){
			hql = hql + " and state = :state";
		}
		if (page.getOrderBy()!=null){
			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		return this.findPage(page, hql, params);
	}
}

package cn.com.cucsi.app.dao.xhcf;

import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.xhcf.XhZqtj;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

@Component
public class XhZqtjDao extends HibernateDao<XhZqtj, Long>{

	public Page<XhZqtj> queryXhZqtj(Page<XhZqtj> page, Map<String, Object> params){
		String hql = "from XhZqtj xhZqtj where 1=1";
		//投资申请ID
		if(params.containsKey("tzsqId")){
			hql = hql + " and tzsqId = :tzsqId";
		}
		//资金
		if(params.containsKey("money")){
			hql = hql + " and money = :money";
		}
		//状态0暂存,1待审批，2审批通过，3审批不通过，9删除，
		if(params.containsKey("state")){
			hql = hql + " and state = :state";
		}
		if (page.getOrderBy()!=null){
			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		return this.findPage(page, hql, params);
	}
}

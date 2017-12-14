package cn.com.cucsi.app.dao.xhcf;

import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.xhcf.XhJksqApplyedit;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

@Component
public class XhJksqApplyeditDao extends HibernateDao<XhJksqApplyedit, Long>{

	public Page<XhJksqApplyedit> queryXhJksqApplyedit(Page<XhJksqApplyedit> page, Map<String, Object> params){
		String hql = "from XhJksqApplyedit xhJksqApplyedit where 1=1";
		//申请修改备注信息
		if(params.containsKey("commentInfo")){
			hql = hql + " and commentInfo = :commentInfo";
		}
		//申请修改人ID
		if(params.containsKey("operatorId")){
			hql = hql + " and operatorId = :operatorId";
		}
		//状态 0：未处理，1已处理
		if(params.containsKey("state")){
			hql = hql + " and state = :state";
		}
		//借款申请ID
		if(params.containsKey("jksqId")){
			hql = hql + " and jksqId = :jksqId";
		}
		//备用字段
		if(params.containsKey("bankup01")){
			hql = hql + " and bankup01 = :bankup01";
		}
		if (page.getOrderBy()!=null){
			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		return this.findPage(page, hql, params);
	}
}

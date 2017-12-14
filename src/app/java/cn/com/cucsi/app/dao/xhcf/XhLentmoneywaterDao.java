package cn.com.cucsi.app.dao.xhcf;

import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.xhcf.XhLentmoneywater;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

@Component
public class XhLentmoneywaterDao extends HibernateDao<XhLentmoneywater, Long>{

	public Page<XhLentmoneywater> queryXhLentmoneywater(Page<XhLentmoneywater> page, Map<String, Object> params){
		String hql = "from XhLentmoneywater xhLentmoneywater where 1=1";
		//状态0申请单,1推荐，2回款
		if(params.containsKey("state")){
			hql = hql + " and state = :state";
		}
		//计划投资日期
		if(params.containsKey("jhtzrq")){
			hql = hql + " and jhtzrq = :jhtzrq";
		}
		//资金
		if(params.containsKey("money")){
			hql = hql + " and money = :money";
		}
		//债权推荐ID
		if(params.containsKey("zqtjId")){
			hql = hql + " and zqtjId = :zqtjId";
		}
		//投资申请ID
		if(params.containsKey("tzsqId")){
			hql = hql + " and tzsqId = :tzsqId";
		}
		if (page.getOrderBy()!=null){
			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		return this.findPage(page, hql, params);
	}
}

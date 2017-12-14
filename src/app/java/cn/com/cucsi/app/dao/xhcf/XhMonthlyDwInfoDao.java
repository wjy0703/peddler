package cn.com.cucsi.app.dao.xhcf;

import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.xhcf.XhMonthlyDwInfo;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

@Component
public class XhMonthlyDwInfoDao extends HibernateDao<XhMonthlyDwInfo, Long>{

	public Page<XhMonthlyDwInfo> queryXhMonthlyDwInfo(Page<XhMonthlyDwInfo> page, Map<String, Object> params){
		String hql = "from XhMonthlyDwInfo xhMonthlyDwInfo where 1=1";
		//主键
		if(params.containsKey("id")){
			hql = hql + " and id = :id";
		}
		//利息
		if(params.containsKey("interest")){
			hql = hql + " and interest = :interest";
		}
		//借款人名称
		if(params.containsKey("loanName")){
			hql = hql + " and loanName = :loanName";
		}
		//金额
		if(params.containsKey("money")){
			hql = hql + " and money = :money";
		}
		//月应付主表ID
		if(params.containsKey("ids")){
			hql = hql + " and zqtjId in (:ids)";
		}
		if (page.getOrderBy()!=null){
			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		return this.findPage(page, hql, params);
	}
}

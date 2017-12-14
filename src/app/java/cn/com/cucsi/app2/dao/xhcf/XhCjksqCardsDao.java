package cn.com.cucsi.app2.dao.xhcf;

import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.app2.entity.xhcf.XhCjksqCards;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

@Component
public class XhCjksqCardsDao extends HibernateDao<XhCjksqCards, Long>{

	public Page<XhCjksqCards> queryXhCjksqCards(Page<XhCjksqCards> page, Map<String, Object> params){
		String hql = "from XhCjksqCards xhCjksqCards where 1=1";
		//激活账户总数
		if(params.containsKey("activeCount")){
			hql = hql + " and activeCount = :activeCount";
		}
		//授信总额
		if(params.containsKey("moneySum")){
			hql = hql + " and moneySum = :moneySum";
		}
		//在用账户数
		if(params.containsKey("cardInuse")){
			hql = hql + " and cardInuse = :cardInuse";
		}
		//单张最高授信
		if(params.containsKey("singleCardUpper")){
			hql = hql + " and singleCardUpper = :singleCardUpper";
		}
		//单张最低授信
		if(params.containsKey("singleCardLower")){
			hql = hql + " and singleCardLower = :singleCardLower";
		}
		//已使用额度
		if(params.containsKey("amountUsed")){
			hql = hql + " and amountUsed = :amountUsed";
		}
		//估值
		if(params.containsKey("estimateValue")){
			hql = hql + " and estimateValue = :estimateValue";
		}
		//信用卡使用率
		if(params.containsKey("useFrequency")){
			hql = hql + " and useFrequency = :useFrequency";
		}
		//逾期情况说明
		if(params.containsKey("exceedComment")){
			hql = hql + " and exceedComment = :exceedComment";
		}
		//最近6个月查询记录
		if(params.containsKey("recentRecord")){
			hql = hql + " and recentRecord = :recentRecord";
		}
		if (page.getOrderBy()!=null){
			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		return this.findPage(page, hql, params);
	}
}

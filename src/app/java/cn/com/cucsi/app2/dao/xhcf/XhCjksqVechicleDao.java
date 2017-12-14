package cn.com.cucsi.app2.dao.xhcf;

import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.app2.entity.xhcf.XhCjksqVechicle;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

@Component
public class XhCjksqVechicleDao extends HibernateDao<XhCjksqVechicle, Long>{

	public Page<XhCjksqVechicle> queryXhCjksqVechicle(Page<XhCjksqVechicle> page, Map<String, Object> params){
		String hql = "from XhCjksqVechicle xhCjksqVechicle where 1=1";
		//注册日期
		if(params.containsKey("registerDate")){
			hql = hql + " and registerDate = :registerDate";
		}
		//登记日期
		if(params.containsKey("dengjiDate")){
			hql = hql + " and dengjiDate = :dengjiDate";
		}
		//产权归属
		if(params.containsKey("owener")){
			hql = hql + " and owener = :owener";
		}
		//有无抵押 0:无 1:有
		if(params.containsKey("endorse")){
			hql = hql + " and endorse = :endorse";
		}
		//借款余额
		if(params.containsKey("borrowmoney")){
			hql = hql + " and borrowmoney = :borrowmoney";
		}
		//估值
		if(params.containsKey("estimateValue")){
			hql = hql + " and estimateValue = :estimateValue";
		}
		//估值/确认途径
		if(params.containsKey("valueWay")){
			hql = hql + " and valueWay = :valueWay";
		}
		//市场报价(类似备注)
		if(params.containsKey("marchetValueComment")){
			hql = hql + " and marchetValueComment = :marchetValueComment";
		}
		if (page.getOrderBy()!=null){
			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		return this.findPage(page, hql, params);
	}
}

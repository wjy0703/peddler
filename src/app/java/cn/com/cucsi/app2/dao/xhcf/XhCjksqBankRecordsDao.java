package cn.com.cucsi.app2.dao.xhcf;

import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.app2.entity.xhcf.XhCjksqBankRecords;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

@Component
public class XhCjksqBankRecordsDao extends HibernateDao<XhCjksqBankRecords, Long>{

	public Page<XhCjksqBankRecords> queryXhCjksqBankRecords(Page<XhCjksqBankRecords> page, Map<String, Object> params){
		String hql = "from XhCjksqBankRecords xhCjksqBankRecords where 1=1";
		//余额数对应的截至日期
		if(params.containsKey("currentDate")){
			hql = hql + " and currentDate = :currentDate";
		}
		//余额
		if(params.containsKey("remainAmount")){
			hql = hql + " and remainAmount = :remainAmount";
		}
		//银行(下拉)
		if(params.containsKey("bank")){
			hql = hql + " and bank = :bank";
		}
		//月份
		if(params.containsKey("one")){
			hql = hql + " and one = :one";
		}
		//月收入情况
		if(params.containsKey("onecount")){
			hql = hql + " and onecount = :onecount";
		}
		//月份
		if(params.containsKey("two")){
			hql = hql + " and two = :two";
		}
		//月收入情况
		if(params.containsKey("twocount")){
			hql = hql + " and twocount = :twocount";
		}
		//月份
		if(params.containsKey("three")){
			hql = hql + " and three = :three";
		}
		//月收入情况
		if(params.containsKey("threecount")){
			hql = hql + " and threecount = :threecount";
		}
		//月份
		if(params.containsKey("four")){
			hql = hql + " and four = :four";
		}
		//月收入情况
		if(params.containsKey("fourcount")){
			hql = hql + " and fourcount = :fourcount";
		}
		//月份
		if(params.containsKey("five")){
			hql = hql + " and five = :five";
		}
		//月收入情况
		if(params.containsKey("fivecount")){
			hql = hql + " and fivecount = :fivecount";
		}
		//月份
		if(params.containsKey("six")){
			hql = hql + " and six = :six";
		}
		//月收入情况
		if(params.containsKey("sixcount3")){
			hql = hql + " and sixcount3 = :sixcount3";
		}
		if (page.getOrderBy()!=null){
			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		return this.findPage(page, hql, params);
	}
}

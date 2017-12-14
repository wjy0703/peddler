package cn.com.cucsi.app2.dao.xhcf;

import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.app2.entity.xhcf.XhJksqCredits;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

@Component
public class XhJksqCreditsDao extends HibernateDao<XhJksqCredits, Long>{

	public Page<XhJksqCredits> queryXhJksqCredits(Page<XhJksqCredits> page, Map<String, Object> params){
		String hql = "from XhJksqCredits xhJksqCredits where 1=1";
		//信用资料类别 0:无抵押 1：有抵押
		if(params.containsKey("typeh")){
			hql = hql + " and typeh = :typeh";
		}
		//抵押物品(自有汽车等)
		if(params.containsKey("mortage")){
			hql = hql + " and mortage = :mortage";
		}
		//机构名称
		if(params.containsKey("compBankName")){
			hql = hql + " and compBankName = :compBankName";
		}
		//贷款额度
		if(params.containsKey("loanAmount")){
			hql = hql + " and loanAmount = :loanAmount";
		}
		//月还款额
		if(params.containsKey("monthReturn")){
			hql = hql + " and monthReturn = :monthReturn";
		}
		//借款余额
		if(params.containsKey("remain")){
			hql = hql + " and remain = :remain";
		}
		//借款期限
		if(params.containsKey("cloanCount")){
			hql = hql + " and cloanCount = :cloanCount";
		}
		//有无还款证明
		if(params.containsKey("cloanReturnFile")){
			hql = hql + " and cloanReturnFile = :cloanReturnFile";
		}
		//还款情况说明
		if(params.containsKey("cloanComment")){
			hql = hql + " and cloanComment = :cloanComment";
		}
		if (page.getOrderBy()!=null){
			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		return this.findPage(page, hql, params);
	}
}

package cn.com.cucsi.app2.dao.xhcf;

import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.app2.entity.xhcf.XhJksqcompanyUprelated;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

@Component
public class XhJksqcompanyUprelatedDao extends HibernateDao<XhJksqcompanyUprelated, Long>{

	public Page<XhJksqcompanyUprelated> queryXhJksqcompanyUprelated(Page<XhJksqcompanyUprelated> page, Map<String, Object> params){
		String hql = "from XhJksqcompanyUprelated xhJksqcompanyUprelated where 1=1";
		//公司名称
		if(params.containsKey("companyName")){
			hql = hql + " and companyName = :companyName";
		}
		//合同类型
		if(params.containsKey("contactType")){
			hql = hql + " and contactType = :contactType";
		}
		//合同金额
		if(params.containsKey("contactMoney")){
			hql = hql + " and contactMoney = :contactMoney";
		}
		//合同期限
		if(params.containsKey("contactDue")){
			hql = hql + " and contactDue = :contactDue";
		}
		//结算方式
		if(params.containsKey("contactHandleType")){
			hql = hql + " and contactHandleType = :contactHandleType";
		}
		//电话核实情况
		if(params.containsKey("phoneBackinfo")){
			hql = hql + " and phoneBackinfo = :phoneBackinfo";
		}
		//电话及来源
		if(params.containsKey("phoneOther")){
			hql = hql + " and phoneOther = :phoneOther";
		}
		if (page.getOrderBy()!=null){
			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		return this.findPage(page, hql, params);
	}
}

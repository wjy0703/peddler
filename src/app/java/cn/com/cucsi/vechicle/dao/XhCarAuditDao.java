package cn.com.cucsi.vechicle.dao;
import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;
import cn.com.cucsi.vechicle.entity.xhcf.XhCarAudit;

@Component
public class XhCarAuditDao extends HibernateDao<XhCarAudit, Long>{

	public Page<XhCarAudit> queryXhCarAudit(Page<XhCarAudit> page, Map<String, Object> params){
		String hql = "from XhCarAudit xhCarAudit where 1=1";
		//信审类型：初审(1)、复审(2)、终审(100)
		if(params.containsKey("creditType")){
			hql = hql + " and creditType = :creditType";
		}
		//信审状态：0信审未结束 1信审结束
		if(params.containsKey("creditState")){
			hql = hql + " and creditState = :creditState";
		}
		//审批金额
		if(params.containsKey("creditAmount")){
			hql = hql + " and creditAmount = :creditAmount";
		}
		//借款期限（天）
		if(params.containsKey("creditMonth")){
			hql = hql + " and creditMonth = :creditMonth";
		}
		//综合费率
		if(params.containsKey("creditAllRate")){
			hql = hql + " and creditAllRate = :creditAllRate";
		}
		//业务收费
		if(params.containsKey("operationFee")){
			hql = hql + " and operationFee = :operationFee";
		}
		//外访费(需求说明文档没有)
		if(params.containsKey("outVisitFee")){
			hql = hql + " and outVisitFee = :outVisitFee";
		}
		//加急费需求说明文档没有)
		if(params.containsKey("urgentCreditFee")){
			hql = hql + " and urgentCreditFee = :urgentCreditFee";
		}
		//拒贷原因
		if(params.containsKey("creditRefuseReason")){
			hql = hql + " and creditRefuseReason = :creditRefuseReason";
		}
		//信审意见
		if(params.containsKey("creditAuditReport")){
			hql = hql + " and creditAuditReport = :creditAuditReport";
		}
		//信审结果：1通过、0拒绝
		if(params.containsKey("creditResult")){
			hql = hql + " and creditResult = :creditResult";
		}
		//信审通过后形成的客户编号
		if(params.containsKey("passedCustomerNo")){
			hql = hql + " and passedCustomerNo = :passedCustomerNo";
		}
		if (page.getOrderBy()!=null){
			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		return this.findPage(page, hql, params);
	}
}

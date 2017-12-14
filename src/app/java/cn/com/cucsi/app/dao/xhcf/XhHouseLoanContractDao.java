package cn.com.cucsi.app.dao.xhcf;

import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.xhcf.XhHouseLoanContract;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

@Component
public class XhHouseLoanContractDao extends HibernateDao<XhHouseLoanContract, Long>{

	public Page<XhHouseLoanContract> queryXhHouseLoanContract(Page<XhHouseLoanContract> page, Map<String, Object> params){
		String hql = "from XhHouseLoanContract xhHouseLoanContract where 1=1";
		//合同编号
		if(params.containsKey("contractNum")){
			hql = hql + " and contractNum = :contractNum";
		}
		//合同金额
		if(params.containsKey("contractMoney")){
			hql = hql + " and contractMoney = :contractMoney";
		}
		//合同日期
		if(params.containsKey("contractDate")){
			hql = hql + " and contractDate = :contractDate";
		}
		//0：待签约 1：已签约上传   -1：取消
		if(params.containsKey("state")){
			params.put("state", Long.parseLong((String)params.get("state")));
			hql = hql + " and state = :state";
		}
		//信审ID
		if(params.containsKey("auditInfoId")){
			hql = hql + " and auditInfoId = :auditInfoId";
		}
		//申请表ID
		if(params.containsKey("loanApplyId")){
			hql = hql + " and loanApplyId = :loanApplyId";
		}
		//备注
		if(params.containsKey("remark")){
			hql = hql + " and remark = :remark";
		}
		//出借人ID
		if(params.containsKey("middleManId")){
			hql = hql + " and middleManId = :middleManId";
		}
		if (page.getOrderBy()!=null){
			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		return this.findPage(page, hql, params);
	}
}

package cn.com.cucsi.app.dao.xhcf;

import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.xhcf.XhLoanerAccountInfo;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

@Component
public class XhLoanerAccountInfoDao extends HibernateDao<XhLoanerAccountInfo, Long>{

	public Page<XhLoanerAccountInfo> queryXhLoanerAccountInfo(Page<XhLoanerAccountInfo> page, Map<String, Object> params){
		String hql = "from XhLoanerAccountInfo xhLoanerAccountInfo where 1=1";
		//借款人主账户ID
		if(params.containsKey("loanerMainAccountId")){
			hql = hql + " and xhLoanerAccountInfo.loanerMainAccount.id = :loanerMainAccountId";
		}
		//交易类型 0:还款存入   1：结算划扣
		if(params.containsKey("dealingType")){
			hql = hql + " and dealingType = :dealingType";
		}
		//交易金额
		if(params.containsKey("deailingMoney")){
			hql = hql + " and deailingMoney = :deailingMoney";
		}
		//交易时间
		if(params.containsKey("dealingTime")){
			hql = hql + " and dealingTime = :dealingTime";
		}
		if (page.getOrderBy()!=null){
			hql = hql + " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		return this.findPage(page, hql, params);
	}
}

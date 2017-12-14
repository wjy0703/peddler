package cn.com.cucsi.app.dao.xhcf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.xhcf.XhLoanerAccount;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

@Component
public class XhLoanerAccountDao extends HibernateDao<XhLoanerAccount, Long> {

	public Page<XhLoanerAccount> queryXhLoanerAccount(
			Page<XhLoanerAccount> page, Map<String, Object> params) {
		String hql = "from XhLoanerAccount xhLoanerAccount where 1=1";
		// 借款合同编码
		if (params.containsKey("loanContractId")) {
			hql = hql
					+ " and xhLoanerAccount.loanContract.jkhtbm = :loanContractId";
		}
		// 借款申请ID账户
		if (params.containsKey("loanApplyName")) {
			hql = hql + " and xhLoanerAccount.loanApply.jkrxm = :loanApplyName";
		}
		// 起始还款日期
		if (params.containsKey("qshkrq")) {
			hql = hql + " and xhLoanerAccount.loanContract.qshkrq = :qshkrq";
		}
		// 账户状态: 0 正常账户 1历史账户
		if (params.containsKey("accountState")) {
			String accountState = (String) params.get("accountState");
			params.put("accountState", Integer.parseInt(accountState));
			hql = hql + " and accountState = :accountState";
		}
		// 账户余额
		if (params.containsKey("accountBalance")) {
			String tt = "!=";
			String accountBalance = (String) params.get("accountBalance");
			params.put("accountBalance", Double.valueOf(accountBalance));
			if (params.containsKey("tt")) {
				tt = (String) params.get("tt");
			}
			hql = hql + " and xhLoanerAccount.accountBalance "+tt+" :accountBalance";
		}
		if (page.getOrderBy() != null) {
			hql = hql + " order by " + page.getOrderBy() + " "
					+ page.getOrder();
		}
		return this.findPage(page, hql, params);
	}
	
	public XhLoanerAccount getAccountByJksqId(Long jksqId){
		XhLoanerAccount xhLoanerAccount = null;
		String hql = "from XhLoanerAccount x where x.loanApply.id = :jksqId";
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("jksqId", jksqId);
		List<XhLoanerAccount> list = this.find(hql, values);
		if(list.size() > 0){
			xhLoanerAccount = list.get(0);
		}
		return xhLoanerAccount;
	}
}

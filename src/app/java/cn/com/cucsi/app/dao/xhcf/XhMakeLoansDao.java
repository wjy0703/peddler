package cn.com.cucsi.app.dao.xhcf;

import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.xhcf.XhMakeLoans;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

@Component
public class XhMakeLoansDao extends HibernateDao<XhMakeLoans, Long> {

	public Page<XhMakeLoans> queryXhMakeLoans(Page<XhMakeLoans> page,
			Map<String, Object> params) {
		String hql = "from XhMakeLoans xhMakeLoans where 1=1";
		// 中间人（账户）ID
		if (params.containsKey("middleMan.id")) {
			hql = hql + " and middleMan.id = :middleManId";
		}
		// 借款合同ID
		if (params.containsKey("jkrxm")) {
			hql = hql + " and loanContract.xhJksq.jkrxm like '%'||:jkrxm||'%'";
		}
		// 借款类型
		if (params.containsKey("backup01")) {
			hql = hql + " and loanContract.xhJksq.backup01 like '%'||:backup01||'%'";
		}
		// 放款时间
		if (params.containsKey("makeLoanDate")) {
			hql = hql + " and TO_CHAR(makeLoanDate,'yyyy-MM-dd') = :makeLoanDate";
		}
		if (page.getOrderBy() != null) {
			hql = hql + " order by " + page.getOrderBy() + " "
					+ page.getOrder();
		}
		else{
			hql = hql + " order by xhMakeLoans.createTime desc";
		}
		return this.findPage(page, hql, params);
	}
}

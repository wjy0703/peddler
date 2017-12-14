package cn.com.cucsi.app.dao.xhcf;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;


import cn.com.cucsi.app.entity.xhcf.XhMatchingInvestment;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

@Component
public class XhMatchingInvestmentDao extends
		HibernateDao<XhMatchingInvestment, Long> {

	public Page<XhMatchingInvestment> queryXhMatchingInvestment(
			Page<XhMatchingInvestment> page, Map<String, Object> params) {
		String hql = "from XhMatchingInvestment xhMatchingInvestment where 1=1";
		// 投资申请
		if (params.containsKey("investApplyId")) {
			hql = hql
					+ " and xhMatchingInvestment.investApply.id = :investApplyId";
		}
		// 出借日期
		if (params.containsKey("firstInvestDate")) {
			hql = hql + " and firstInvestDate = :firstInvestDate";
		}
		// 账单日
		if (params.containsKey("billDay")) {
			hql = hql + " and billDay = :billDay";
		}
		// 出借方式（ 产品类型）
		if (params.containsKey("investType")) {
			hql = hql + " and investType = :investType";
		}
		// 已匹配金额
		if (params.containsKey("matchedAmount")) {
			hql = hql + " and matchedAmount = :matchedAmount";
		}
		// 待匹配金额
		if (params.containsKey("matchingAmount")) {
			hql = hql + " and matchingAmount = :matchingAmount";
		}
		// 划扣日期
		if (params.containsKey("deductDate")) {
			hql = hql + " and deductDate = :deductDate";
		}
		// 交割日期
		if (params.containsKey("deliveryDate")) {
			hql = hql + " and deliveryDate = :deliveryDate";
		}
		// 状态 0: 未完成匹配 1:完成匹配 2 待审批 3 审核拒绝 4 审核通过，待订购， 5取消订购 6 已订购，带交割 7已交割
		if (params.containsKey("investState")) {
			hql = hql + " and investState = :investState";
		}
		// 0首期 1非首期
		if (params.containsKey("investMode")) {
			hql = hql + " and investMode = :investMode";
		}
		if (page.getOrderBy() != null) {
			hql = hql + " order by " + page.getOrderBy() + " "
					+ page.getOrder();
		}
		return this.findPage(page, hql, params);
	}

//	/**
//	 * @param ids
//	 * @return
//	 */
//	public List<XhAvailableValueOfCreditorBAK> findAvailableValuesByIds(
//			String[] ids) {
//
//		String hql = "from  XhAvailableValueOfCreditor xhAvailableValueOfCreditor where xhAvailableValueOfCreditor.id in (:ids)";
//		Session session = this.getSession();
//		Query query = session.createQuery(hql);
//		query.setParameterList("ids", convIntArray(ids));
//		return query.list();
//	}

	public static Long[] convIntArray(String[] ids) {
		if (ids == null) {
			return new Long[0];
		}
		Long[] ia = new Long[ids.length];
		for (int i = 0; i < ids.length; i++) {
			ia[i] = Long.parseLong(ids[i]);
		}
		return ia;
	}

}

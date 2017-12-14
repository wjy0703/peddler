package cn.com.cucsi.app.dao.xhcf;

import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.xhcf.XhHouseLoanAudit;
import cn.com.cucsi.app.entity.xhcf.XhHouseLoanAuditInfo;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

@Component
public class XhHouseLoanAuditInfoDao extends
		HibernateDao<XhHouseLoanAuditInfo, Long> {

	public Page<XhHouseLoanAuditInfo> queryXhHouseLoanAuditInfo(
			Page<XhHouseLoanAuditInfo> page, Map<String, Object> params) {
		String hql = "from XhHouseLoanAuditInfo xhHouseLoanAuditInfo where 1=1";
		// 房屋性质
		if (params.containsKey("houseType")) {
			hql = hql + " and houseType = :houseType";
		}
		// 房屋评估价值
		if (params.containsKey("houseEvaluateValue")) {
			hql = hql + " and houseEvaluateValue = :houseEvaluateValue";
		}
		// 信访费
		if (params.containsKey("creditVisitFee")) {
			hql = hql + " and creditVisitFee = :creditVisitFee";
		}
		// 综合费率
		if (params.containsKey("allFeeRate")) {
			hql = hql + " and allFeeRate = :allFeeRate";
		}
		// 服务费率
		if (params.containsKey("serviceFeeRate")) {
			hql = hql + " and serviceFeeRate = :serviceFeeRate";
		}
		// 月利率
		if (params.containsKey("monthRate")) {
			hql = hql + " and monthRate = :monthRate";
		}
		// 贷款期数
		if (params.containsKey("loanMonth")) {
			hql = hql + " and loanMonth = :loanMonth";
		}
		// 签约日期
		if (params.containsKey("signContractDate")) {
			hql = hql + " and signContractDate = :signContractDate";
		}
		// 借款申请ID
		if (params.containsKey("houseLoanApplyId")) {
			hql = hql + " and houseLoanApplyId = :houseLoanApplyId";
		}
		// 借款审核ID
		if (params.containsKey("houseLoanAduitId")) {
			hql = hql + " and houseLoanAduitId = :houseLoanAduitId";
		}
		
		// cityparams.get("city")+")";
		if (params.containsKey("city")) {
			hql = hql + " and xhHouseLoanAuditInfo.xhHouseLoanApply.city in ( "
					+ params.get("city") + ")";
		}
		
		if (page.getOrderBy() != null) {
			hql = hql + " order by " + page.getOrderBy() + " "
					+ page.getOrder();
		}
		return this.findPage(page, hql, params);
	}

	/**
	 * @param id
	 */
	public XhHouseLoanAuditInfo getXhHouseLoanAuditInfoByApplyId(Long id) {
		String hql = "from XhHouseLoanAuditInfo xhHouseLoanAuditInfo where 1=1";
		hql = hql + " and xhHouseLoanAuditInfo.xhHouseLoanApply.id = " + id;

		return (XhHouseLoanAuditInfo) this.getSession().createQuery(hql)
				.uniqueResult();
	}
}

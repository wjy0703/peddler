package cn.com.cucsi.app.dao.xhcf;

import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.cucsi.app.entity.xhcf.XhHouseLoanAudit;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.orm.hibernate.HibernateDao;

@Component
public class XhHouseLoanAuditDao extends HibernateDao<XhHouseLoanAudit, Long> {

	public Page<XhHouseLoanAudit> queryXhHouseLoanAudit(
			Page<XhHouseLoanAudit> page, Map<String, Object> params) {
		String hql = "from XhHouseLoanAudit xhHouseLoanAudit where 1=1";
		// 房屋所在地
		if (params.containsKey("houseAddress")) {
			hql = hql + " and houseAddress = :houseAddress";
		}
		// 房屋周边设施
		if (params.containsKey("houseAroundObject")) {
			hql = hql + " and houseAroundObject = :houseAroundObject";
		}
		// 银行抵押金额
		if (params.containsKey("houseBankMortgageValue")) {
			hql = hql + " and houseBankMortgageValue = :houseBankMortgageValue";
		}
		// 用款目的
		if (params.containsKey("loanReason")) {
			hql = hql + " and loanReason = :loanReason";
		}
		// 还款来源
		if (params.containsKey("repaySource")) {
			hql = hql + " and repaySource = :repaySource";
		}
		// 可放款金额
		if (params.containsKey("makableValue")) {
			hql = hql + " and makableValue = :makableValue";
		}
		// 其它审核信息
		if (params.containsKey("otherAuditInfo")) {
			hql = hql + " and otherAuditInfo = :otherAuditInfo";
		}
		// 房屋建筑面积
		if (params.containsKey("buildingArea")) {
			hql = hql + " and buildingArea = :buildingArea";
		}
		// 房屋年限
		if (params.containsKey("buildingYears")) {
			hql = hql + " and buildingYears = :buildingYears";
		}
		// 综合评定单价
		if (params.containsKey("aduitUnitPrice")) {
			hql = hql + " and aduitUnitPrice = :aduitUnitPrice";
		}
		// 市场价值
		if (params.containsKey("marketUnitPrice")) {
			hql = hql + " and marketUnitPrice = :marketUnitPrice";
		}
		// 中介1名称
		if (params.containsKey("mediumOneName")) {
			hql = hql + " and mediumOneName = :mediumOneName";
		}
		// 中介1收房价
		if (params.containsKey("mediumOnePrice")) {
			hql = hql + " and mediumOnePrice = :mediumOnePrice";
		}
		// 中介2名称
		if (params.containsKey("mediumTwoName")) {
			hql = hql + " and mediumTwoName = :mediumTwoName";
		}
		// 中介2收房价
		if (params.containsKey("mediumTwoPrice")) {
			hql = hql + " and mediumTwoPrice = :mediumTwoPrice";
		}
		// 中介3名称
		if (params.containsKey("mediumThreeName")) {
			hql = hql + " and mediumThreeName = :mediumThreeName";
		}
		// 中介3收房价
		if (params.containsKey("mediumThreePrice")) {
			hql = hql + " and mediumThreePrice = :mediumThreePrice";
		}
		// 审核状态
		if (params.containsKey("aduitState")) {
			hql = hql + " and aduitState = :aduitState";
		}
		// 审核结果
		if (params.containsKey("aduitResult")) {
			hql = hql + " and aduitResult = :aduitResult";
		}
		// 审核意见
		if (params.containsKey("aduitSuggestion")) {
			hql = hql + " and aduitSuggestion = :aduitSuggestion";
		}
		// cityparams.get("city")+")";
		if (params.containsKey("city")) {
			hql = hql + " and xhHouseLoanAudit.xhHouseLoanApply.city in ( "
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
	public XhHouseLoanAudit getXhHouseLoanAuditByApplyId(Long id) {
		String hql = "from XhHouseLoanAudit xhHouseLoanAudit where 1=1";
		hql = hql + " and xhHouseLoanAudit.xhHouseLoanApply.id = " + id;

		return (XhHouseLoanAudit) this.getSession().createQuery(hql)
				.uniqueResult();
	}
}

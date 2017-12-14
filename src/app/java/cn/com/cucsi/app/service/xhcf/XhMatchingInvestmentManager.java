package cn.com.cucsi.app.service.xhcf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.app.dao.xhcf.XhMatchingInvestmentDao;

import cn.com.cucsi.app.entity.xhcf.XhMatchingInvestment;
import cn.com.cucsi.app.service.ServiceException;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils;
import cn.com.cucsi.core.utils.EncodeUtils;
import cn.com.cucsi.core.utils.PropertiesUtils;

//Spring Bean的标识.
@Component
// 默认将类中的所有函数纳入事务管理.
@Transactional
public class XhMatchingInvestmentManager {

	private XhMatchingInvestmentDao xhMatchingInvestmentDao;

	@Autowired
	public void setXhMatchingInvestmentDao(
			XhMatchingInvestmentDao xhMatchingInvestmentDao) {
		this.xhMatchingInvestmentDao = xhMatchingInvestmentDao;
	}

	private JdbcDao jdbcDao;

	@Autowired
	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}

	@Transactional(readOnly = true)
	public Page<XhMatchingInvestment> searchXhMatchingInvestment(
			final Page<XhMatchingInvestment> page,
			final Map<String, Object> filters) {
		return xhMatchingInvestmentDao.queryXhMatchingInvestment(page, filters);
	}

	@Transactional(readOnly = true)
	public XhMatchingInvestment getXhMatchingInvestment(Long id) {
		return xhMatchingInvestmentDao.get(id);
	}

	public void saveXhMatchingInvestment(XhMatchingInvestment entity) {
		xhMatchingInvestmentDao.save(entity);
	}

	/**
	 * 删除用户,如果尝试删除超级管理员将抛出异常.
	 */
	public void deleteXhMatchingInvestment(Long id) {
		xhMatchingInvestmentDao.delete(id);
	}

	public boolean batchDelXhMatchingInvestment(String[] ids) {

		try {
			for (String id : ids) {
				deleteXhMatchingInvestment(Long.valueOf(id));
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Transactional(readOnly = true)
	public List<Map<String, Object>> searchXhMatchingInvestment(
			String queryName, Map<String, Object> filter, JdbcPage page) {
		String sql = "";
		String value = "";
		// 投资申请
		if (filter.containsKey("investApplyId")) {
			value = String.valueOf(filter.get("investApplyId"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.INVEST_APPLY_ID = '" + value + "'";
			}
		}
		// 出借日期
		if (filter.containsKey("firstInvestDate")) {
			value = String.valueOf(filter.get("firstInvestDate"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.FIRST_INVEST_DATE = '" + value + "'";
			}
		}
		// 账单日
		if (filter.containsKey("billDay")) {
			value = String.valueOf(filter.get("billDay"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.BILL_DAY = '" + value + "'";
			}
		}
		// 出借方式（ 产品类型）
		if (filter.containsKey("investType")) {
			value = String.valueOf(filter.get("investType"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.INVEST_TYPE = '" + value + "'";
			}
		}
		// 已匹配金额
		if (filter.containsKey("matchedAmount")) {
			value = String.valueOf(filter.get("matchedAmount"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.MATCHED_AMOUNT = '" + value + "'";
			}
		}
		// 待匹配金额
		if (filter.containsKey("matchingAmount")) {
			value = String.valueOf(filter.get("matchingAmount"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.MATCHING_AMOUNT = '" + value + "'";
			}
		}
		// 划扣日期
		if (filter.containsKey("deductDate")) {
			value = String.valueOf(filter.get("deductDate"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.DEDUCT_DATE = '" + value + "'";
			}
		}
		// 交割日期
		if (filter.containsKey("deliveryDate")) {
			value = String.valueOf(filter.get("deliveryDate"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.DELIVERY_DATE = '" + value + "'";
			}
		}
		// 状态 0: 未完成匹配 1:完成匹配 2 待审批 3 审核拒绝 4 审核通过，待订购， 5取消订购 6 已订购，带交割 7已交割
		if (filter.containsKey("investState")) {
			value = String.valueOf(filter.get("investState"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.INVEST_STATE = '" + value + "'";
			}
		}
		// 0首期 1非首期
		if (filter.containsKey("investMode")) {
			value = String.valueOf(filter.get("investMode"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.INVEST_MODE = '" + value + "'";
			}
		}

		if (page.getOrderBy() != null) {
			sql = sql + " order by " + page.getOrderBy() + " "
					+ page.getOrder();
		}

		System.out.println("sql=======>" + sql);
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("sql", sql);

		return jdbcDao.searchPagesByMergeSqlTemplate(queryName, conditions,
				page);
	}

}

package cn.com.cucsi.app.service.xhcf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.app.dao.xhcf.XhHouseLoanAuditDao;
import cn.com.cucsi.app.entity.xhcf.XhHouseLoanAudit;
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
public class XhHouseLoanAuditManager {

	private XhHouseLoanAuditDao xhHouseLoanAuditDao;

	@Autowired
	public void setXhHouseLoanAuditDao(XhHouseLoanAuditDao xhHouseLoanAuditDao) {
		this.xhHouseLoanAuditDao = xhHouseLoanAuditDao;
	}

	private JdbcDao jdbcDao;

	@Autowired
	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}

	@Transactional(readOnly = true)
	public Page<XhHouseLoanAudit> searchXhHouseLoanAudit(
			final Page<XhHouseLoanAudit> page, final Map<String, Object> filters) {
		return xhHouseLoanAuditDao.queryXhHouseLoanAudit(page, filters);
	}

	@Transactional(readOnly = true)
	public XhHouseLoanAudit getXhHouseLoanAudit(Long id) {
		return xhHouseLoanAuditDao.get(id);
	}

	public void saveXhHouseLoanAudit(XhHouseLoanAudit entity) {
		entity.getXhHouseLoanApply().setLoanState("32");
		entity.setAduitState("1");
		xhHouseLoanAuditDao.save(entity);
	}
	
	
	public void saveXhHouseLoanSecondAudit(XhHouseLoanAudit entity) {
		entity.getXhHouseLoanApply().setLoanState("33");
		entity.setAduitState("2");
		xhHouseLoanAuditDao.save(entity);
	}

	@Transactional(readOnly = true)
	public List<Map<String, Object>> searchXhHouseLoanAudit(String queryName,
			Map<String, Object> filter, JdbcPage page) {
		String sql = "";
		String value = "";
		// 房屋所在地
		if (filter.containsKey("houseAddress")) {
			value = String.valueOf(filter.get("houseAddress"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.HOUSE_ADDRESS = '" + value + "'";
			}
		}
		// 房屋周边设施
		if (filter.containsKey("houseAroundObject")) {
			value = String.valueOf(filter.get("houseAroundObject"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.HOUSE_AROUND_OBJECT = '" + value + "'";
			}
		}
		// 银行抵押金额
		if (filter.containsKey("houseBankMortgageValue")) {
			value = String.valueOf(filter.get("houseBankMortgageValue"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.HOUSE_BANK_MORTGAGE_VALUE = '" + value
						+ "'";
			}
		}
		// 用款目的
		if (filter.containsKey("loanReason")) {
			value = String.valueOf(filter.get("loanReason"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.LOAN_REASON = '" + value + "'";
			}
		}
		// 还款来源
		if (filter.containsKey("repaySource")) {
			value = String.valueOf(filter.get("repaySource"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.REPAY_SOURCE = '" + value + "'";
			}
		}
		// 可放款金额
		if (filter.containsKey("makableValue")) {
			value = String.valueOf(filter.get("makableValue"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.MAKABLE_VALUE = '" + value + "'";
			}
		}
		// 其它审核信息
		if (filter.containsKey("otherAuditInfo")) {
			value = String.valueOf(filter.get("otherAuditInfo"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.OTHER_AUDIT_INFO = '" + value + "'";
			}
		}
		// 房屋建筑面积
		if (filter.containsKey("buildingArea")) {
			value = String.valueOf(filter.get("buildingArea"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.BUILDING_AREA = '" + value + "'";
			}
		}
		// 房屋年限
		if (filter.containsKey("buildingYears")) {
			value = String.valueOf(filter.get("buildingYears"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.BUILDING_YEARS = '" + value + "'";
			}
		}
		// 综合评定单价
		if (filter.containsKey("aduitUnitPrice")) {
			value = String.valueOf(filter.get("aduitUnitPrice"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ADUIT_UNIT_PRICE = '" + value + "'";
			}
		}
		// 市场价值
		if (filter.containsKey("marketUnitPrice")) {
			value = String.valueOf(filter.get("marketUnitPrice"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.MARKET_UNIT_PRICE = '" + value + "'";
			}
		}
		// 中介1名称
		if (filter.containsKey("mediumOneName")) {
			value = String.valueOf(filter.get("mediumOneName"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.MEDIUM_ONE_NAME = '" + value + "'";
			}
		}
		// 中介1收房价
		if (filter.containsKey("mediumOnePrice")) {
			value = String.valueOf(filter.get("mediumOnePrice"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.MEDIUM_ONE_PRICE = '" + value + "'";
			}
		}
		// 中介2名称
		if (filter.containsKey("mediumTwoName")) {
			value = String.valueOf(filter.get("mediumTwoName"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.MEDIUM_TWO_NAME = '" + value + "'";
			}
		}
		// 中介2收房价
		if (filter.containsKey("mediumTwoPrice")) {
			value = String.valueOf(filter.get("mediumTwoPrice"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.MEDIUM_TWO_PRICE = '" + value + "'";
			}
		}
		// 中介3名称
		if (filter.containsKey("mediumThreeName")) {
			value = String.valueOf(filter.get("mediumThreeName"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.MEDIUM_THREE_NAME = '" + value + "'";
			}
		}
		// 中介3收房价
		if (filter.containsKey("mediumThreePrice")) {
			value = String.valueOf(filter.get("mediumThreePrice"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.MEDIUM_THREE_PRICE = '" + value + "'";
			}
		}
		// 审核状态
		if (filter.containsKey("aduitState")) {
			value = String.valueOf(filter.get("aduitState"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ADUIT_STATE = '" + value + "'";
			}
		}
		// 审核结果
		if (filter.containsKey("aduitResult")) {
			value = String.valueOf(filter.get("aduitResult"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ADUIT_RESULT = '" + value + "'";
			}
		}
		// 审核意见
		if (filter.containsKey("aduitSuggestion")) {
			value = String.valueOf(filter.get("aduitSuggestion"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ADUIT_SUGGESTION = '" + value + "'";
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

	/**
	 * @param id
	 * @return
	 */
	public XhHouseLoanAudit getXhHouseLoanAuditByApplyId(Long id) {
		return xhHouseLoanAuditDao.getXhHouseLoanAuditByApplyId(id);
	}
}

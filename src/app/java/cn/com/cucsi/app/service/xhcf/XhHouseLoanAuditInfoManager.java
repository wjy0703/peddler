package cn.com.cucsi.app.service.xhcf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.app.dao.xhcf.XhHouseLoanAuditInfoDao;
import cn.com.cucsi.app.entity.xhcf.XhHouseLoanAudit;
import cn.com.cucsi.app.entity.xhcf.XhHouseLoanAuditInfo;
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
public class XhHouseLoanAuditInfoManager {

	private XhHouseLoanAuditInfoDao xhHouseLoanAuditInfoDao;

	@Autowired
	public void setXhHouseLoanAuditInfoDao(
			XhHouseLoanAuditInfoDao xhHouseLoanAuditInfoDao) {
		this.xhHouseLoanAuditInfoDao = xhHouseLoanAuditInfoDao;
	}

	private JdbcDao jdbcDao;

	@Autowired
	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}

	@Transactional(readOnly = true)
	public Page<XhHouseLoanAuditInfo> searchXhHouseLoanAuditInfo(
			final Page<XhHouseLoanAuditInfo> page,
			final Map<String, Object> filters) {
		return xhHouseLoanAuditInfoDao.queryXhHouseLoanAuditInfo(page, filters);
	}

	@Transactional(readOnly = true)
	public XhHouseLoanAuditInfo getXhHouseLoanAuditInfo(Long id) {
		return xhHouseLoanAuditInfoDao.get(id);
	}

	public void saveXhHouseLoanAuditInfo(XhHouseLoanAuditInfo entity) {
		xhHouseLoanAuditInfoDao.save(entity);
	}

	/**
	 * 删除用户,如果尝试删除超级管理员将抛出异常.
	 */
	public void deleteXhHouseLoanAuditInfo(Long id) {
		xhHouseLoanAuditInfoDao.delete(id);
	}

	public boolean batchDelXhHouseLoanAuditInfo(String[] ids) {

		try {
			for (String id : ids) {
				deleteXhHouseLoanAuditInfo(Long.valueOf(id));
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	@Transactional(readOnly = true)
	public List<Map<String, Object>> searchXhHouseLoanAuditInfo(
			String queryName, Map<String, Object> filter, JdbcPage page) {
		String sql = "";
		String value = "";
		// 房屋性质
		if (filter.containsKey("houseType")) {
			value = String.valueOf(filter.get("houseType"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.HOUSE_TYPE = '" + value + "'";
			}
		}
		// 房屋评估价值
		if (filter.containsKey("houseEvaluateValue")) {
			value = String.valueOf(filter.get("houseEvaluateValue"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.HOUSE_EVALUATE_VALUE = '" + value + "'";
			}
		}
		// 信访费
		if (filter.containsKey("creditVisitFee")) {
			value = String.valueOf(filter.get("creditVisitFee"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CREDIT_VISIT_FEE = '" + value + "'";
			}
		}
		// 综合费率
		if (filter.containsKey("allFeeRate")) {
			value = String.valueOf(filter.get("allFeeRate"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ALL_FEE_RATE = '" + value + "'";
			}
		}
		// 服务费率
		if (filter.containsKey("serviceFeeRate")) {
			value = String.valueOf(filter.get("serviceFeeRate"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.SERVICE_FEE_RATE = '" + value + "'";
			}
		}
		// 月利率
		if (filter.containsKey("monthRate")) {
			value = String.valueOf(filter.get("monthRate"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.MONTH_RATE = '" + value + "'";
			}
		}
		// 贷款期数
		if (filter.containsKey("loanMonth")) {
			value = String.valueOf(filter.get("loanMonth"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.LOAN_MONTH = '" + value + "'";
			}
		}
		// 签约日期
		if (filter.containsKey("signContractDate")) {
			value = String.valueOf(filter.get("signContractDate"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.SIGN_CONTRACT_DATE = '" + value + "'";
			}
		}
		// 借款申请ID
		if (filter.containsKey("houseLoanApplyId")) {
			value = String.valueOf(filter.get("houseLoanApplyId"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.HOUSE_LOAN_APPLY_ID = '" + value + "'";
			}
		}
		// 借款审核ID
		if (filter.containsKey("houseLoanAduitId")) {
			value = String.valueOf(filter.get("houseLoanAduitId"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.HOUSE_LOAN_ADUIT_ID = '" + value + "'";
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

	public XhHouseLoanAuditInfo getXhHouseLoanAuditInfoByApplyId(Long id) {
		return xhHouseLoanAuditInfoDao.getXhHouseLoanAuditInfoByApplyId(id);
	}
}

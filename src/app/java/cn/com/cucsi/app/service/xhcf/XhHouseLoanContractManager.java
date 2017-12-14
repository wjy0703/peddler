package cn.com.cucsi.app.service.xhcf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.app.dao.xhcf.XhHouseLoanContractDao;
import cn.com.cucsi.app.dao.xhcf.XhJkhtDao;
import cn.com.cucsi.app.entity.xhcf.XhHouseLoanContract;
import cn.com.cucsi.app.entity.xhcf.XhJkht;
import cn.com.cucsi.app.service.ServiceException;
import cn.com.cucsi.app.web.util.CreditHarmonyComputeUtilties;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.security.springsecurity.SpringSecurityUtils;
import cn.com.cucsi.core.utils.EncodeUtils;
import cn.com.cucsi.core.utils.PropertiesUtils;

//Spring Bean的标识.
@Component
// 默认将类中的所有函数纳入事务管理.
@Transactional
public class XhHouseLoanContractManager {

	private XhHouseLoanContractDao xhHouseLoanContractDao;
	private XhJkhtDao xhJkhtDao;

	@Autowired
	public void setXhJkhtDao(XhJkhtDao xhJkhtDao) {
		this.xhJkhtDao = xhJkhtDao;
	}

	@Autowired
	public void setXhHouseLoanContractDao(
			XhHouseLoanContractDao xhHouseLoanContractDao) {
		this.xhHouseLoanContractDao = xhHouseLoanContractDao;
	}

	private JdbcDao jdbcDao;

	@Autowired
	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}

	@Transactional(readOnly = true)
	public Page<XhHouseLoanContract> searchXhHouseLoanContract(
			final Page<XhHouseLoanContract> page,
			final Map<String, Object> filters) {
		return xhHouseLoanContractDao.queryXhHouseLoanContract(page, filters);
	}

	@Transactional(readOnly = true)
	public XhHouseLoanContract getXhHouseLoanContract(Long id) {
		return xhHouseLoanContractDao.get(id);
	}

	public void saveXhHouseLoanContract(XhHouseLoanContract entity) {
		
		XhJkht jkht=null;
		entity.setState(Long.parseLong("0"));
		if(entity.getJkht()==null){
			jkht = new XhJkht();
		}else{
			jkht=entity.getJkht();
		}
		
		
		//jkht.getXhJksq().setLoanCode(entity.getContractNum());
		jkht.setJkhtbm(entity.getContractNum());
		jkht.setHtje(entity.getContractMoney());
		jkht.setDkll(entity.getXhHouseLoanAuditInfo().getMonthRate());
		jkht.setXhJksq(entity.getXhHouseLoanApply().getLoanApply());
		jkht.setHkqs(entity.getXhHouseLoanAuditInfo().getLoanMonth());
		jkht.setYbjje(entity.getContractMoney()
				/ entity.getXhHouseLoanAuditInfo().getLoanMonth());
		jkht.setYlxje(jkht.getYbjje() * jkht.getDkll() / 100);
		jkht.setYhkje(jkht.getYbjje() + jkht.getYlxje());
		String sDate = CreditHarmonyComputeUtilties.dateToString(entity
				.getContractDate());
		jkht.setQshkrq(CreditHarmonyComputeUtilties
				.getFirstDateOfBackMoney(sDate));
		jkht.setHkr(CreditHarmonyComputeUtilties.getBackMoneyDateOfMonth(jkht
				.getQshkrq()));
		jkht.setMiddleMan(entity.getMiddleMan());
		jkht.setFkje(entity.getXhHouseLoanAuditInfo().getContractMoney());
		xhJkhtDao.save(jkht);

		entity.setJkht(jkht);
		entity.getXhHouseLoanApply().setLoanState("52");
		entity.getXhHouseLoanApply().getLoanApply().setState("52");
		entity.getXhHouseLoanApply().getLoanApply().setLoanCode(entity.getContractNum());
		xhHouseLoanContractDao.save(entity);

	}

	

	@Transactional(readOnly = true)
	public List<Map<String, Object>> searchXhHouseLoanContract(
			String queryName, Map<String, Object> filter, JdbcPage page) {
		String sql = "";
		String value = "";
		// 合同编号
		if (filter.containsKey("contractNum")) {
			value = String.valueOf(filter.get("contractNum"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CONTRACT_NUM = '" + value + "'";
			}
		}
		// 合同金额
		if (filter.containsKey("contractMoney")) {
			value = String.valueOf(filter.get("contractMoney"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CONTRACT_MONEY = '" + value + "'";
			}
		}
		// 合同日期
		if (filter.containsKey("contractDate")) {
			value = String.valueOf(filter.get("contractDate"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CONTRACT_DATE = '" + value + "'";
			}
		}
		// 0：待签约 1：已签约上传 -1：取消
		if (filter.containsKey("state")) {
			value = String.valueOf(filter.get("state"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.STATE = '" + value + "'";
			}
		}
		// 信审ID
		if (filter.containsKey("auditInfoId")) {
			value = String.valueOf(filter.get("auditInfoId"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.AUDIT_INFO_ID = '" + value + "'";
			}
		}
		// 申请表ID
		if (filter.containsKey("loanApplyId")) {
			value = String.valueOf(filter.get("loanApplyId"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.LOAN_APPLY_ID = '" + value + "'";
			}
		}
		// 备注
		if (filter.containsKey("remark")) {
			value = String.valueOf(filter.get("remark"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.REMARK = '" + value + "'";
			}
		}
		// 出借人ID
		if (filter.containsKey("middleManId")) {
			value = String.valueOf(filter.get("middleManId"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.MIDDLE_MAN_ID = '" + value + "'";
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
	 * 合同签约确认
	 * 
	 * @param xhHouseLoanContract
	 */
	public void confirmXhHouseLoanContract(
			XhHouseLoanContract xhHouseLoanContract) {
	
		xhHouseLoanContractDao.save(xhHouseLoanContract);

	}
}

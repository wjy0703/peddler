package cn.com.cucsi.app.service.xhcf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.app.dao.xhcf.XhIpcApplyDao;
import cn.com.cucsi.app.dao.xhcf.XhIpcConstractDao;
import cn.com.cucsi.app.dao.xhcf.XhJkhtDao;
import cn.com.cucsi.app.entity.xhcf.XhIpcApply;
import cn.com.cucsi.app.entity.xhcf.XhIpcConstract;
import cn.com.cucsi.app.entity.xhcf.XhJkht;
import cn.com.cucsi.app.entity.xhcf.XhJksq;
import cn.com.cucsi.app.service.baseinfo.BaseInfoManager;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.orm.Page;

//Spring Bean的标识.
@Component
// 默认将类中的所有函数纳入事务管理.
@Transactional
public class XhIpcConstractManager {
	private BaseInfoManager baseInfoManager;
	private XhJkhtDao jkhtDao;
	
	private XhIpcApplyDao xhIpcApplyDao;
	@Autowired
	public void setXhIpcApplyDao(XhIpcApplyDao xhIpcApplyDao) {
		this.xhIpcApplyDao = xhIpcApplyDao;
	}

	@Autowired
	public void setJkhtDao(XhJkhtDao jkhtDao) {
		this.jkhtDao = jkhtDao;
	}

	@Autowired
	public void setBaseInfoManager(BaseInfoManager baseInfoManager) {
		this.baseInfoManager = baseInfoManager;
	}

	private XhIpcConstractDao xhIpcConstractDao;

	@Autowired
	public void setXhIpcConstractDao(XhIpcConstractDao xhIpcConstractDao) {
		this.xhIpcConstractDao = xhIpcConstractDao;
	}

	private JdbcDao jdbcDao;

	@Autowired
	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}

	@Transactional(readOnly = true)
	public Page<XhIpcConstract> searchXhIpcConstract(
			final Page<XhIpcConstract> page, final Map<String, Object> filters) {
		return xhIpcConstractDao.queryXhIpcConstract(page, filters);
	}

	@Transactional(readOnly = true)
	public XhIpcConstract getXhIpcConstract(Long id) {
		return xhIpcConstractDao.get(id);
	}
	
	@Transactional(readOnly = true)
	public XhIpcConstract getXhIpcConstractByjksqId(Long jksqId) {
		List<XhIpcApply> list1 = xhIpcApplyDao.findBy("loanApply.id", jksqId);
		List<XhIpcConstract> list = xhIpcConstractDao.findBy("ipcApply.id", list1.get(0).getId());
		return list.get(0);
	}
	
	public void saveXhIpcConstractMdy(XhIpcConstract entity) {
		xhIpcConstractDao.save(entity);
	}
	
	public void saveXhIpcConstract(XhIpcConstract entity) {

		XhJkht jkht = null;
		// entity.setState(Long.parseLong("0"));
		if (entity.getJkht() == null) {
			jkht = new XhJkht();
		} else {
			jkht = entity.getJkht();
		}

		// jkht.getXhJksq().setLoanCode(entity.getContractNum());
		jkht.setJkhtbm(entity.getJkhtbm());
		jkht.setHtje(entity.getHtje());
		jkht.setDkll(entity.getDkll());
		XhJksq jksq = entity.getIpcApply().getLoanApply();
		jksq.setState("60");
		jksq.setJkLoanQuota(entity.getHtje() + "");
		jksq.setJkCycle(entity.getHkqs() + "");
		jksq.setLoanCode(entity.getJkhtbm());
		jkht.setXhJksq(jksq);
		jkht.setHkqs(Integer.parseInt(entity.getHkqs() + ""));
		jkht.setYbjje(entity.getYbjje());
		jkht.setYlxje(entity.getYlxje());
		jkht.setYhkje(entity.getYhkje());
		// String sDate =
		// CreditHarmonyComputeUtilties.dateToString(entity.getContractDate());//CreditHarmonyComputeUtilties
		// .getFirstDateOfBackMoney(sDate)
		jkht.setQshkrq(entity.getQshkrq());
		jkht.setHkr(entity.getHkr());

		jkht.setMiddleMan(baseInfoManager.getMiddleMan(entity.getMiddlemanId()));
		jkht.setFkje(entity.getFkje());
		jkhtDao.save(jkht);

		entity.setJkht(jkht);
		xhIpcConstractDao.save(entity);
		XhIpcApply xhIpcApply = entity.getIpcApply();
		xhIpcApply.setState("1");
		xhIpcApplyDao.save(xhIpcApply);
	}

	/**
	 * 删除用户,如果尝试删除超级管理员将抛出异常.
	 */
	public void deleteXhIpcConstract(Long id) {
		xhIpcConstractDao.delete(id);
	}

	public boolean batchDelXhIpcConstract(String[] ids) {

		try {
			for (String id : ids) {
				deleteXhIpcConstract(Long.valueOf(id));
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	@Transactional(readOnly = true)
	public List<Map<String, Object>> searchXhIpcConstract(String queryName,
			Map<String, Object> filter, JdbcPage page) {
		String sql = "";
		String value = "";
		// 审核时间
		if (filter.containsKey("auditDate")) {
			value = String.valueOf(filter.get("auditDate"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.AUDIT_DATE = '" + value + "'";
			}
		}
		// 审核意见
		if (filter.containsKey("auditIdea")) {
			value = String.valueOf(filter.get("auditIdea"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.AUDIT_IDEA = '" + value + "'";
			}
		}
		// 审核人
		if (filter.containsKey("auditPerson")) {
			value = String.valueOf(filter.get("auditPerson"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.AUDIT_PERSON = '" + value + "'";
			}
		}
		// 放款金额
		if (filter.containsKey("fkje")) {
			value = String.valueOf(filter.get("fkje"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.FKJE = '" + value + "'";
			}
		}
		// 服务费
		if (filter.containsKey("fwf")) {
			value = String.valueOf(filter.get("fwf"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.FWF = '" + value + "'";
			}
		}
		// 合同金额
		if (filter.containsKey("htje")) {
			value = String.valueOf(filter.get("htje"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.HTJE = '" + value + "'";
			}
		}
		// 借款合同编号
		if (filter.containsKey("jkhtbm")) {
			value = String.valueOf(filter.get("jkhtbm"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.JKHTBM = '" + value + "'";
			}
		}
		// 合同签订日期
		if (filter.containsKey("qdrq")) {
			value = String.valueOf(filter.get("qdrq"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.QDRQ = '" + value + "'";
			}
		}
		// 起始还款日期
		if (filter.containsKey("qshkrq")) {
			value = String.valueOf(filter.get("qshkrq"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.QSHKRQ = '" + value + "'";
			}
		}
		// 状态0暂存,1待审批，2审批通过，3审批不通过，9删除
		if (filter.containsKey("state")) {
			value = String.valueOf(filter.get("state"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.STATE = '" + value + "'";
			}
		}
		// 信用审核费
		if (filter.containsKey("xyshf")) {
			value = String.valueOf(filter.get("xyshf"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.XYSHF = '" + value + "'";
			}
		}
		// 月本金金额
		if (filter.containsKey("ybjje")) {
			value = String.valueOf(filter.get("ybjje"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.YBJJE = '" + value + "'";
			}
		}
		// 月还款金额
		if (filter.containsKey("yhkje")) {
			value = String.valueOf(filter.get("yhkje"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.YHKJE = '" + value + "'";
			}
		}
		// 月利息金额
		if (filter.containsKey("ylxje")) {
			value = String.valueOf(filter.get("ylxje"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.YLXJE = '" + value + "'";
			}
		}
		// 账户管理费
		if (filter.containsKey("zhglf")) {
			value = String.valueOf(filter.get("zhglf"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ZHGLF = '" + value + "'";
			}
		}
		// 咨询费
		if (filter.containsKey("zxf")) {
			value = String.valueOf(filter.get("zxf"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ZXF = '" + value + "'";
			}
		}
		// 综合费率
		if (filter.containsKey("yzhfl")) {
			value = String.valueOf(filter.get("yzhfl"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.YZHFL = '" + value + "'";
			}
		}
		// 还款期数
		if (filter.containsKey("hkqs")) {
			value = String.valueOf(filter.get("hkqs"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.HKQS = '" + value + "'";
			}
		}
		// 信访费
		if (filter.containsKey("xff")) {
			value = String.valueOf(filter.get("xff"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.XFF = '" + value + "'";
			}
		}
		// 签约确认审核时间
		if (filter.containsKey("auditQzqrDate")) {
			value = String.valueOf(filter.get("auditQzqrDate"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.AUDIT_QZQR_DATE = '" + value + "'";
			}
		}
		// 签约确认审核意见
		if (filter.containsKey("auditQzqrIdea")) {
			value = String.valueOf(filter.get("auditQzqrIdea"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.AUDIT_QZQR_IDEA = '" + value + "'";
			}
		}
		// 签约确认审核人
		if (filter.containsKey("auditQzqrPerson")) {
			value = String.valueOf(filter.get("auditQzqrPerson"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.AUDIT_QZQR_PERSON = '" + value + "'";
			}
		}
		// 贷款利率
		if (filter.containsKey("dkll")) {
			value = String.valueOf(filter.get("dkll"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.DKLL = '" + value + "'";
			}
		}
		// 批贷金额
		if (filter.containsKey("pdje")) {
			value = String.valueOf(filter.get("pdje"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.PDJE = '" + value + "'";
			}
		}
		// 剩余金额
		if (filter.containsKey("syje")) {
			value = String.valueOf(filter.get("syje"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.SYJE = '" + value + "'";
			}
		}
		// 剩余利率
		if (filter.containsKey("syll")) {
			value = String.valueOf(filter.get("syll"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.SYLL = '" + value + "'";
			}
		}
		// 居间人ID
		if (filter.containsKey("middlemanId")) {
			value = String.valueOf(filter.get("middlemanId"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.MIDDLEMAN_ID = '" + value + "'";
			}
		}
		// 还款日
		if (filter.containsKey("hkr")) {
			value = String.valueOf(filter.get("hkr"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.HKR = '" + value + "'";
			}
		}
		// 临时姓名
		if (filter.containsKey("tmpName")) {
			value = String.valueOf(filter.get("tmpName"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.TMP_NAME = '" + value + "'";
			}
		}
		// 申请ID
		if (filter.containsKey("ipcApplyId")) {
			value = String.valueOf(filter.get("ipcApplyId"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.IPC_APPLY_ID = '" + value + "'";
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

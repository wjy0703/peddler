package cn.com.cucsi.app.service.xhcf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.app.dao.xhcf.XhAvailableValueOfClaimsDao;
import cn.com.cucsi.app.dao.xhcf.XhLentmoneywaterDao;
import cn.com.cucsi.app.dao.xhcf.XhMadewordDao;
import cn.com.cucsi.app.dao.xhcf.XhMatchingInvestmentDao;
import cn.com.cucsi.app.dao.xhcf.XhSendemailDao;
import cn.com.cucsi.app.dao.xhcf.XhTzsqDao;
import cn.com.cucsi.app.dao.xhcf.XhTzsqHistoryDao;
import cn.com.cucsi.app.dao.xhcf.XhTzsqStateDao;
import cn.com.cucsi.app.dao.xhcf.XhUploadFilesDao;
import cn.com.cucsi.app.dao.xhcf.XhZqtjDao;
import cn.com.cucsi.app.entity.export.ExportTzsq;
import cn.com.cucsi.app.entity.xhcf.XhAvailableValueOfClaims;
import cn.com.cucsi.app.entity.xhcf.XhLentmoneywater;
import cn.com.cucsi.app.entity.xhcf.XhMadeword;
import cn.com.cucsi.app.entity.xhcf.XhMatchingInvestment;
import cn.com.cucsi.app.entity.xhcf.XhSendemail;
import cn.com.cucsi.app.entity.xhcf.XhTzsq;
import cn.com.cucsi.app.entity.xhcf.XhTzsqHistory;
import cn.com.cucsi.app.entity.xhcf.XhTzsqState;
import cn.com.cucsi.app.entity.xhcf.XhUploadFiles;
import cn.com.cucsi.app.entity.xhcf.XhZqtj;
import cn.com.cucsi.app.entity.xhcf.XhZqtjDetails;
import cn.com.cucsi.app.service.baseinfo.BaseInfoManager;
import cn.com.cucsi.app.web.InitSetupListener;
import cn.com.cucsi.app.web.util.CreditHarmonyComputeUtilties;
import cn.com.cucsi.app.web.util.ExportExcel;
import cn.com.cucsi.app.web.util.Java2Excel;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.core.utils.DateUtils;
import cn.com.cucsi.core.utils.PropertiesUtils;

//Spring Bean的标识.
@Component
// 默认将类中的所有函数纳入事务管理.
@Transactional
public class XhTzsqManager {
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private XhUploadFilesDao xhUploadFilesDao;

	 @Autowired
	 private BaseInfoManager baseInfoManager;
	 
	 @Autowired
	 private XhMadewordDao xhMadewordDao;
	
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private JyglManager jyglManager;

	@Autowired
	public void setJyglManager(JyglManager jyglManager) {
		this.jyglManager = jyglManager;
	}

	private XhAvailableValueOfClaimsDao xhAvailableValueOfClaimsDao;

	@Autowired
	public void setXhAvailableValueOfClaimsDao(
			XhAvailableValueOfClaimsDao xhAvailableValueOfClaimsDao) {
		this.xhAvailableValueOfClaimsDao = xhAvailableValueOfClaimsDao;
	}

	private XhTzsqDao xhTzsqDao;

	@Autowired
	public void setXhTzsqDao(XhTzsqDao xhTzsqDao) {
		this.xhTzsqDao = xhTzsqDao;
	}
	
	private XhZqtjDao xhZqtjDao;
	
	
	
	@Autowired
	public void setXhZqtjDao(XhZqtjDao xhZqtjDao) {
		this.xhZqtjDao = xhZqtjDao;
	}

	private XhTzsqHistoryDao xhTzsqHistoryDao;

	@Autowired
	public void setXhTzsqHistoryDao(XhTzsqHistoryDao xhTzsqHistoryDao) {
		this.xhTzsqHistoryDao = xhTzsqHistoryDao;
	}

	private XhSendemailDao xhSendemailDao;

	@Autowired
	public void setXhSendemailDao(XhSendemailDao xhSendemailDao) {
		this.xhSendemailDao = xhSendemailDao;
	}
	
	private XhTzsqStateDao xhTzsqStateDao;
	@Autowired
	public void setXhTzsqStateDao(XhTzsqStateDao xhTzsqStateDao) {
		this.xhTzsqStateDao = xhTzsqStateDao;
	}
	
	public String saveXhSendemail(String id) {
		String result = "0";
		String sql = "select id from xh_zqtj t where t.STATE='2' and t.LENT_STATE='0' and t.STATEDG !='0' and t.TZSQ_ID="
				+ id;
		List<Map<String, Object>> zqtjList = jdbcDao.searchByMergeSql(sql);
		
		if (zqtjList.size() == 1) {
			XhTzsq xhTzsq = getXhTzsq(Long.valueOf(id));

			String email = xhTzsq.getCjrxx().getDzyx();
			if (StringUtils.isNotEmpty(email)) {
				String zq_id = zqtjList.get(0).get("ID") + "";
				
				XhZqtj xhZqtj = xhZqtjDao.get(Long.parseLong(zq_id));
				xhZqtj.setBillSendState("1");
				xhZqtjDao.save(xhZqtj);
				
				XhSendemail xsd = new XhSendemail();
				xsd.setState("0");
				xsd.setTableId(Long.parseLong(zq_id));
				xsd.setTableName("xh_zqtj");
				xhSendemailDao.save(xsd);
			} else {
				result = "客户没有填写邮箱。";
			}
		} else {
			result = "客户无有效首期债权。";
		}
		return result;
	}

	public void saveXhTzsqHistory(XhTzsqHistory entity) {
		xhTzsqHistoryDao.save(entity);
	}

	@Transactional(readOnly = true)
	public XhTzsqHistory getXhTzsqHistory(Long id) {
		return xhTzsqHistoryDao.get(id);
	}

	private JdbcDao jdbcDao;

	@Autowired
	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}

	private XhLentmoneywaterDao xhLentmoneywaterDao;

	@Autowired
	public void setXhLentmoneywaterDao(XhLentmoneywaterDao xhLentmoneywaterDao) {
		this.xhLentmoneywaterDao = xhLentmoneywaterDao;
	}

	private XhMatchingInvestmentDao xhMatchingInvestmentDao;

	@Autowired
	public void setXhMatchingInvestmentDao(
			XhMatchingInvestmentDao xhMatchingInvestmentDao) {
		this.xhMatchingInvestmentDao = xhMatchingInvestmentDao;
	}

	public void saveXhMatchingInvestment(
			XhMatchingInvestment xhMatchingInvestment) {
		xhMatchingInvestmentDao.save(xhMatchingInvestment);
	}

	public void saveXhLentmoneywater(XhLentmoneywater xhLentmoneywater) {
		xhLentmoneywaterDao.save(xhLentmoneywater);
	}

	@Transactional(readOnly = true)
	public Page<XhTzsq> searchXhTzsq(final Page<XhTzsq> page,
			final Map<String, Object> filters) {
		return xhTzsqDao.queryXhTzsq(page, filters);
	}

	@Transactional(readOnly = true)
	public XhTzsq getXhTzsq(Long id) {
		return xhTzsqDao.get(id);
	}

	public void saveXhTzsq(XhTzsq entity) {
		xhTzsqDao.save(entity);
	}

	public synchronized void auditXhTzsq(XhTzsq xhTzsq) {
		xhTzsqDao.save(xhTzsq);
		// 如果投资申请审核通过则向资金流水表添加一条记录
		if (xhTzsq.getState().equals("2")) {
			String sql = "select ID from XH_LENTMONEYWATER a where 1=1 ";
			sql = sql + " and a.TZSQ_ID = " + xhTzsq.getId();
			List<Map<String, Object>> waterList = jdbcDao.searchByMergeSql(sql);
			if (waterList.size() == 0) {
				XhLentmoneywater xhLentmoneywater = new XhLentmoneywater();
				xhLentmoneywater.setJhtzrq(xhTzsq.getJhtzrq());
				xhLentmoneywater
						.setMoney(Double.parseDouble(xhTzsq.getJhtzje()));
				xhLentmoneywater.setState("0");
				xhLentmoneywater.setXhTzsq(xhTzsq);
				xhLentmoneywaterDao.save(xhLentmoneywater);
			}
			XhTzsqState xhTzsqState = new XhTzsqState();
			xhTzsqState.setDescribe("投资申请审核通过");
			xhTzsqState.setRemarks(xhTzsq.getAuditIdea());
			xhTzsqState.setXhTzsq(xhTzsq);
			xhTzsqState.setState("2");
			xhTzsqStateDao.save(xhTzsqState);
			/*
			 * XhMatchingInvestment xhMatchingInvestment = new
			 * XhMatchingInvestment();
			 * xhMatchingInvestment.setInvestApply(xhTzsq);
			 * xhMatchingInvestment.setInvestMode(0);
			 * xhMatchingInvestment.setDeductDate
			 * (DateUtils.parse(xhTzsq.getJhhkrq(), "yyyy-MM-dd"));
			 * xhMatchingInvestment.setMatchedAmount(0.0);
			 * xhMatchingInvestment.setMatchingAmount
			 * (Double.parseDouble(xhTzsq.getJhtzje()));
			 * xhMatchingInvestment.setInvestState(0);
			 * xhMatchingInvestment.setFirstInvestDate
			 * (DateUtils.parse(xhTzsq.getJhtzrq(), "yyyy-MM-dd"));
			 * xhMatchingInvestmentDao.save(xhMatchingInvestment);
			 */
		}else{
			XhTzsqState xhTzsqState = new XhTzsqState();
			xhTzsqState.setDescribe("投资申请审核不通过");
			xhTzsqState.setRemarks(xhTzsq.getAuditIdea());
			xhTzsqState.setXhTzsq(xhTzsq);
			xhTzsqState.setState("21");
			xhTzsqStateDao.save(xhTzsqState);
		}
	}

	public void auditXhTzsqChange(XhTzsq xhTzsq) {
		xhTzsqDao.save(xhTzsq);
		// 如果投资申请变更审核通过则修改资金流水表记录
		if (xhTzsq.getUpstate().equals("-1")) {
			String sql = "select ID from XH_LENTMONEYWATER a where 1=1 ";
			sql = sql + " and a.TZSQ_ID = " + xhTzsq.getId();
			List<Map<String, Object>> waterList = jdbcDao.searchByMergeSql(sql);
			if (waterList.size() == 1) {
				XhLentmoneywater xhLentmoneywater = xhLentmoneywaterDao
						.get(Long.parseLong(waterList.get(0).get("ID") + ""));
				xhLentmoneywater.setJhtzrq(xhTzsq.getJhtzrq());
				xhLentmoneywater
						.setMoney(Double.parseDouble(xhTzsq.getJhtzje()));
				xhLentmoneywaterDao.save(xhLentmoneywater);
			}
			/*
			 * XhMatchingInvestment xhMatchingInvestment = new
			 * XhMatchingInvestment();
			 * xhMatchingInvestment.setInvestApply(xhTzsq);
			 * xhMatchingInvestment.setInvestMode(0);
			 * xhMatchingInvestment.setDeductDate
			 * (DateUtils.parse(xhTzsq.getJhhkrq(), "yyyy-MM-dd"));
			 * xhMatchingInvestment.setMatchedAmount(0.0);
			 * xhMatchingInvestment.setMatchingAmount
			 * (Double.parseDouble(xhTzsq.getJhtzje()));
			 * xhMatchingInvestment.setInvestState(0);
			 * xhMatchingInvestment.setFirstInvestDate
			 * (DateUtils.parse(xhTzsq.getJhtzrq(), "yyyy-MM-dd"));
			 * xhMatchingInvestmentDao.save(xhMatchingInvestment);
			 */
		}
	}

	public synchronized void overXhTzsq(Long id, String shrq) {
		XhTzsq xhTzsq = this.getXhTzsq(id);
		xhTzsq.setOverstate("2");
		xhTzsq.setShrq(shrq);
		xhTzsq.setRemark("系统赎回！");
		String sql = "select t.ID from xh_zqtj t where  t.tzsq_id = " + id;
		List<Map<String, Object>> zqtjList = jdbcDao.searchByMergeSql(sql);
		for (Map<String, Object> m : zqtjList) {
			Long zqtjid = Long.parseLong(m.get("ID") + "");
			XhZqtj xhZqtj = jyglManager.getXhZqtj(zqtjid);
			// 删除推荐明细
			Set<XhZqtjDetails> xhZqtjDetails = xhZqtj.getXhZqtjDetails();
			for (XhZqtjDetails x : xhZqtjDetails) {
				// 还原金额
				XhAvailableValueOfClaims xhAvailableValueOfClaims = jyglManager
						.getXhAvailableValueOfClaims(x.getKyzqjzId());
				xhAvailableValueOfClaims.setKyzqjz(xhAvailableValueOfClaims
						.getKyzqjz() + x.getMoney());
				xhAvailableValueOfClaims
						.setZjrcybl(xhAvailableValueOfClaims.getKyzqjz()
								/ xhAvailableValueOfClaims.getJkje() * 100);
				xhAvailableValueOfClaimsDao.save(xhAvailableValueOfClaims);
				// 删除明细
				// xhZqtjDetailsDao.delete(x);
			}
			// 删除债权推荐表
			// jyglManager.saveXhZqtj(xhZqtj);
		}

		this.saveXhTzsq(xhTzsq);
		// 调用存储过程，回收资金
		jdbcTemplate.execute("call redeem_balance(" + id + ",'" + shrq + "')");
	}

	/**
	 * 债款回款明细复查
	 */
	public void checkXhTzsqMoney(Long id) {
		// 调用存储过程，债款回款明细复查
		jdbcTemplate
				.execute("{call CHECKMONEY_UTIL.checkByTzsqId(" + id + ")}");
	}

	/**
	 * 删除用户,如果尝试删除超级管理员将抛出异常.
	 */
	public void deleteXhTzsq(Long id) {
		xhTzsqDao.delete(id);
	}

	public boolean batchDelXhTzsq(String[] ids) {

		try {
			for (String id : ids) {
				deleteXhTzsq(Long.valueOf(id));
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	// --------------------------------------

	@Transactional(readOnly = true)
	public List<Map<String, Object>> searchXhTzsq(String queryName,
			Map<String, Object> filter, JdbcPage page) {
		return jdbcDao.searchPagesByMergeSqlTemplate(queryName,
				conditions(filter), page);
	}

	@Transactional(readOnly = true)
	public List<Map<String, Object>> selectXhTzsqOne(String queryName,
			Map<String, Object> filter) {
		String sql = "";
		String value = "";
		// 编号
		if (filter.containsKey("id")) {
			value = String.valueOf(filter.get("id"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.tzsq_id = " + value + "";
			}
		}

		System.out.println("sql=======>" + sql);
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("sql", sql);
		return jdbcDao.searchByMergeSqlTemplate(queryName, conditions);
	}

	@Transactional(readOnly = true)
	public List<Map<String, Object>> findXhTzsq(String queryName,
			Map<String, Object> filter) {
		String sql = "";
		String value = "";
		// 编号
		if (filter.containsKey("id")) {
			value = String.valueOf(filter.get("id"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ID = " + value + "";
				System.out.println(sql);
			}
		}
		if (filter.containsKey("tzsqbh")) {
			value = String.valueOf(filter.get("tzsqbh"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.tzsqbh = '" + value + "'";
			}
		}

		String sql2 = "";
		System.out.println("sql=======>" + sql);
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("sql", sql);
		conditions.put("sql2", sql2);
		return jdbcDao.searchByMergeSqlTemplate(queryName, conditions);
	}

	@Transactional(readOnly = true)
	public List<Map<String, Object>> searchXhTzsq(String queryName,
			Map<String, Object> filter) {
		return jdbcDao.searchByMergeSqlTemplate(queryName, conditions(filter));
	}

	public Map<String, Object> conditions(Map<String, Object> filter) {
		String sql = "";
		String sql2 = "";
		String value = "";
		
		if (filter.containsKey("id")) {
            value = String.valueOf(filter.get("id"));
            if (StringUtils.isNotEmpty(value)) {
                sql = sql + " and a.ID = " + value + "";
            }
        }
		// 协议编号
		if (filter.containsKey("tzsqbh")) {
			value = String.valueOf(filter.get("tzsqbh"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.TZSQBH like '%" + value + "%'";
			}
		}
		// ------------------------

		if (filter.containsKey("date")) {
			value = String.valueOf(filter.get("date"));
			if (StringUtils.isNotEmpty(value)) {
				String sqlDate = " to_date(a." + value + ",'yyyy-MM-dd') ";
				if (value.equals("MOQI")) {
					//sqlDate = "z." + value;
					String sqlDate2 = " to_date(z." + value + ",'yyyy-MM-dd') ";
					if (filter.containsKey("startdate")) {
						value = String.valueOf(filter.get("startdate"));
						if (StringUtils.isNotEmpty(value)) {
							sql2 += " and " + sqlDate2 + " >= to_date('" + value
									+ "','yyyy-MM-dd')";
						}
					}
					if (filter.containsKey("enddate")) {
						value = String.valueOf(filter.get("enddate"));
						if (StringUtils.isNotEmpty(value)) {
							sql2 += " and " + sqlDate2 + " <= to_date('" + value
									+ "','yyyy-MM-dd')";
						}
					}
				}else{
					if (filter.containsKey("startdate")) {
						value = String.valueOf(filter.get("startdate"));
						if (StringUtils.isNotEmpty(value)) {
							sql += " and " + sqlDate + " >= to_date('" + value
									+ "','yyyy-MM-dd')";
						}
					}
					if (filter.containsKey("enddate")) {
						value = String.valueOf(filter.get("enddate"));
						if (StringUtils.isNotEmpty(value)) {
							sql += " and " + sqlDate + " <= to_date('" + value
									+ "','yyyy-MM-dd')";
						}
					}
				}

			}
		}

		// 计划投资日期
		if (filter.containsKey("jhtzrq")) {
			value = String.valueOf(filter.get("jhtzrq"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.JHTZRQ = '" + value + "'";
			}
		}
		// 计划投资金额Min
		if (filter.containsKey("jhtzjeMin")) {
			value = String.valueOf(filter.get("jhtzjeMin"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and to_number(a.JHTZJE)>= " + value;
			}
		}

		// 计划投资金额Max
		if (filter.containsKey("jhtzjeMax")) {
			value = String.valueOf(filter.get("jhtzjeMax"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and to_number(a.JHTZJE) <= " + value;
			}
		}
		// 计划投资金额
		if (filter.containsKey("jhtzje")) {
			value = String.valueOf(filter.get("jhtzje"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.JHTZJE = '" + value + "'";
			}
		}
		// 投资方式
		if (filter.containsKey("tzfs")) {
			value = String.valueOf(filter.get("tzfs"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.TZFS = '" + value + "'";
			}
		}
		// 回收方式
		if (filter.containsKey("hsfs")) {
			value = String.valueOf(filter.get("hsfs"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.HSFS = '" + value + "'";
			}
		}
		// 付款方式
		if (filter.containsKey("fkfs")) {
			value = String.valueOf(filter.get("fkfs"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.FKFS = '" + value + "'";
			}
		}
		// 是否风险金补偿
		if (filter.containsKey("sffxjbc")) {
			value = String.valueOf(filter.get("sffxjbc"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.SFFXJBC = '" + value + "'";
			}
		}
		// 计划划扣日期
		if (filter.containsKey("jhhkrq")) {
			value = String.valueOf(filter.get("jhhkrq"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.JHHKRQ = '" + value + "'";
			}
		}

		// 出借日期
		if (filter.containsKey("jhtzrqMin")) {
			value = String.valueOf(filter.get("jhtzrqMin"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and to_date(a.JHTZRQ,'yyyy-MM-dd') >= to_date('"
						+ value + "','yyyy-MM-dd')";
			}
		}

		// 出借日期
		if (filter.containsKey("jhtzrqMax")) {
			value = String.valueOf(filter.get("jhtzrqMax"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and to_date(a.JHTZRQ,'yyyy-MM-dd') <= to_date('"
						+ value + "','yyyy-MM-dd')";
			}
		}
		// 计划划扣日期
		if (filter.containsKey("jhhkrqMin")) {
			value = String.valueOf(filter.get("jhhkrqMin"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and to_date(a.JHHKRQ,'yyyy-MM-dd') >= to_date('"
						+ value + "','yyyy-MM-dd')";
			}
		}

		// 计划划扣日期
		if (filter.containsKey("jhhkrqMin")) {
			value = String.valueOf(filter.get("jhhkrqMin"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and to_date(a.JHHKRQ,'yyyy-MM-dd') <= to_date('"
						+ value + "','yyyy-MM-dd')";
			}
		}
		// 申请日期
		if (filter.containsKey("sqrq")) {
			value = String.valueOf(filter.get("sqrq"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.SQRQ = '" + value + "'";
			}
		}
		// 部门主管
		if (filter.containsKey("bmzg")) {
			value = String.valueOf(filter.get("bmzg"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.BMZG = '" + value + "'";
			}
		}
		// 协议版本
		if (filter.containsKey("xybb")) {
			value = String.valueOf(filter.get("xybb"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.XYBB = '" + value + "'";
			}
		}
		// 销售折扣率
		if (filter.containsKey("xszkly")) {
			value = String.valueOf(filter.get("xszkly"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.XSZKLY = '" + value + "'";
			}
		}
		// 销售折扣率有效期限
		if (filter.containsKey("xszklyxqx")) {
			value = String.valueOf(filter.get("xszklyxqx"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.XSZKLYXQX = '" + value + "'";
			}
		}
		// 备注
		if (filter.containsKey("remark")) {
			value = String.valueOf(filter.get("remark"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.REMARK = '" + value + "'";
			}
		}
		// 申请单原件
		if (filter.containsKey("sqdyj")) {
			value = String.valueOf(filter.get("sqdyj"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.SQDYJ = '" + value + "'";
			}
		}
		// 投资付款开户行
		if (filter.containsKey("tzfkkhh")) {
			value = String.valueOf(filter.get("tzfkkhh"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.TZFKKHH = '" + value + "'";
			}
		}
		// 投资付款卡或折
		if (filter.containsKey("tzfkkhz")) {
			value = String.valueOf(filter.get("tzfkkhz"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.TZFKKHZ = '" + value + "'";
			}
		}
		// 投资付款银行名称
		if (filter.containsKey("tzfkyhmc")) {
			value = String.valueOf(filter.get("tzfkyhmc"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.TZFKYHMC = '" + value + "'";
			}
		}
		// 投资付款开户名称
		if (filter.containsKey("tzfkkhmc")) {
			value = String.valueOf(filter.get("tzfkkhmc"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.TZFKKHMC = '" + value + "'";
			}
		}
		// 投资付款银行帐号
		if (filter.containsKey("tzfkyhzh")) {
			value = String.valueOf(filter.get("tzfkyhzh"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.TZFKYHZH = '" + value + "'";
			}
		}
		// 回收资金开户行
		if (filter.containsKey("hszjkhh")) {
			value = String.valueOf(filter.get("hszjkhh"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.HSZJKHH = '" + value + "'";
			}
		}
		// 回收资金卡或折
		if (filter.containsKey("hszjkhz")) {
			value = String.valueOf(filter.get("hszjkhz"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.HSZJKHZ = '" + value + "'";
			}
		}
		// 回收资金银行名称
		if (filter.containsKey("hszjyhmc")) {
			value = String.valueOf(filter.get("hszjyhmc"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.HSZJYHMC = '" + value + "'";
			}
		}
		// 回收资金开户名称
		if (filter.containsKey("hszjkhmc")) {
			value = String.valueOf(filter.get("hszjkhmc"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.HSZJKHMC = '" + value + "'";
			}
		}
		// 回收资金银行帐号
		if (filter.containsKey("hszjyhzh")) {
			value = String.valueOf(filter.get("hszjyhzh"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.HSZJYHZH = '" + value + "'";
			}
		}
		// 申请状态
		if (filter.containsKey("sqzt")) {
			value = String.valueOf(filter.get("sqzt"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.SQZT = '" + value + "'";
			}
		}
		// 出借周期
		if (filter.containsKey("cjzq")) {
			value = String.valueOf(filter.get("cjzq"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CJZQ != '" + value + "'";
			}
		}
		// 交割日
		if (filter.containsKey("tzjgr")) {
			value = String.valueOf(filter.get("tzjgr"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.TZJGR = '" + value + "'";
			}
		}
		// 首个还款日期
		if (filter.containsKey("sghkrq")) {
			value = String.valueOf(filter.get("sghkrq"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.SGHKRQ = '" + value + "'";
			}
		}
		// 账单日
		if (filter.containsKey("zdr")) {
			value = String.valueOf(filter.get("zdr"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.ZDR = '" + value + "'";
			}
		}
		// 投资资金状态
		if (filter.containsKey("tzzjzt")) {
			value = String.valueOf(filter.get("tzzjzt"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.TZZJZT = '" + value + "'";
			}
		}
		// 使用期数
		if (filter.containsKey("syqs")) {
			value = String.valueOf(filter.get("syqs"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.SYQS = '" + value + "'";
			}
		}
		// 匹配状态
		if (filter.containsKey("ppzt")) {
			value = String.valueOf(filter.get("ppzt"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.PPZT = '" + value + "'";
			}
		}
		// 剩余出借周期
		if (filter.containsKey("lastCjzq")) {
			value = String.valueOf(filter.get("lastCjzq"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.LAST_CJZQ != '" + value + "'";
			}
		}
		// 状态0暂存,1待审批，2审批通过，3审批不通过，9删除，
		if (filter.containsKey("state")) {
			value = String.valueOf(filter.get("state"));
			if (StringUtils.isNotEmpty(value)) {
				if ("100".equals(value)) {
					sql = sql + " and a.HKSTATE = '2'";
				} else if("200".equals(value)){
					sql = sql + " and a.OVERSTATE = '2'";
				}else {
					sql = sql + " and a.STATE = '" + value + "'";
				}
			}
		}
		// 状态0暂存,1待审批，2审批通过，3审批不通过，9删除，
		if (filter.containsKey("hkstate")) {
			value = String.valueOf(filter.get("hkstate"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.HKSTATE = '" + value + "'";
			}
		}
		// 审核人
		if (filter.containsKey("auditPerson")) {
			value = String.valueOf(filter.get("auditPerson"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.AUDIT_PERSON = '" + value + "'";
			}
		}
		// 审核意见
		if (filter.containsKey("auditIdea")) {
			value = String.valueOf(filter.get("auditIdea"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.AUDIT_IDEA = '" + value + "'";
			}
		}
		// 审核时间
		if (filter.containsKey("auditTime")) {
			value = String.valueOf(filter.get("auditTime"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.AUDIT_TIME = '" + value + "'";
			}
		}
		// 变更状态-1，无修改，0暂存, 1待审批，3审批不通过
		if (filter.containsKey("upstate")) {
			value = String.valueOf(filter.get("upstate"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.UPSTATE = '" + value + "'";
			}
		}

		// 投资产品
		if (filter.containsKey("tzcp")) {
			value = String.valueOf(filter.get("tzcp"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.TZCP_ID = '" + value + "'";
			}
		}

		// 剩余出借周期
		if (filter.containsKey("overstate")) {
			value = String.valueOf(filter.get("overstate"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.OVERSTATE = '" + value + "'";
			}
		}
		
		//债权转让申请
		if (filter.containsKey("creditstate")) {
			value = String.valueOf(filter.get("creditstate"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.CREDITSTATE != " + value + "";
			}
		}
		
		if (filter.containsKey("creditstatefind")) {
            value = String.valueOf(filter.get("creditstatefind"));
            if (StringUtils.isNotEmpty(value)) {
                sql = sql + " and a.CREDITSTATE = '" + value + "'";
            }
        }

		// 剩余出借周期
		if (filter.containsKey("overstatenot")) {
			value = String.valueOf(filter.get("overstatenot"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.OVERSTATE != '" + value + "'";
			}
		}
		// 首期天数
		if (filter.containsKey("firstdate")) {
			value = String.valueOf(filter.get("firstdate"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and (a.LAST_CJZQ != '" + value
						+ "' or a.firstdate != '" + value + "')";
			}
		}
		if (filter.containsKey("cjrxm")) {
			value = String.valueOf(filter.get("cjrxm"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and b.cjrxm like '%" + value + "%'";
			}
		}
		if (filter.containsKey("khbm")) {
			value = String.valueOf(filter.get("khbm"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and b.khbm like '%" + value + "%'";
			}
		}
		
		if(filter.containsKey("crmprovince")){
			value = String.valueOf(filter.get("crmprovince"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and b.crmprovince = '" +  value + "'";
			}
		}
		if(filter.containsKey("crmcity")){
			value = String.valueOf(filter.get("crmcity"));
			if(StringUtils.isNotEmpty(value)) {
				sql = sql + " and b.crmcity = '" +  value + "'";
			}
		}

		if (filter.containsKey("province")) {
			value = String.valueOf(filter.get("province"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and b.province = '" + value + "'";
			}
		}
		if (filter.containsKey("city")) {
			value = String.valueOf(filter.get("city"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and b.city = '" + value + "'";
			}
		}

		// 级联查询sql
		sql = sql + PropertiesUtils.getLendSql();

		// if (page.getOrderBy()!=null){
		// sql = sql + " order by " + page.getOrderBy() + " " + page.getOrder();
		// }
		sql = sql + " order by  a.create_time desc";
		
		if (filter.containsKey("money")) {
			value = String.valueOf(filter.get("money"));
			if (StringUtils.isNotEmpty(value)) {
				sql2 = sql2
						+ " and (to_number(MONEY)>0 or ZQTJ_ID is not null) ";
			}
		}
		if (filter.containsKey("lentcount")) {
			value = String.valueOf(filter.get("lentcount"));
			if (StringUtils.isNotEmpty(value)) {
				sql2 = sql2 + " and LENTCOUNT=1 ";
			}
		}
		if (filter.containsKey("lenjhtzrq")) {
			value = String.valueOf(filter.get("lenjhtzrq"));
			if (StringUtils.isNotEmpty(value)) {
				sql2 = sql2 + " and lenjhtzrq= '" + value + "'";
			}
		}
		if (filter.containsKey("backcount")) {
			value = String.valueOf(filter.get("backcount"));
			if (StringUtils.isNotEmpty(value)) {
				if (value.equals("0")) {
					sql2 = sql2 + " and backcount=0 ";
				} else {
					sql2 = sql2 + " and backcount>0 ";
				}
			}
		}

		if (filter.containsKey("days")) {
			value = String.valueOf(filter.get("days"));
			if (StringUtils.isNotEmpty(value)) {
				sql2 = sql2 + " and JHJSRQ >= sysdate-1";
				sql2 = sql2 + " and JHJSRQ <= sysdate +" + value + "";
			}
		}
		// 计划划扣日期
		if (filter.containsKey("jhjsrqMin")) {
			value = String.valueOf(filter.get("jhjsrqMin"));
			if (StringUtils.isNotEmpty(value)) {
				sql2 = sql2 + " and JHJSRQ >= to_date('" + value
						+ "','yyyy-MM-dd')";
			}
		}

		// 计划划扣日期
		if (filter.containsKey("jhjsrqMax")) {
			value = String.valueOf(filter.get("jhjsrqMax"));
			if (StringUtils.isNotEmpty(value)) {
				sql2 = sql2 + " and JHJSRQ <= to_date('" + value
						+ "','yyyy-MM-dd')";
			}
		}

		// 已到期日期
		if (filter.containsKey("yesterday")) {
			value = String.valueOf(filter.get("yesterday"));
			if (StringUtils.isNotEmpty(value)) {
				sql2 = sql2 + " and JHJSRQ = to_date('" + value
						+ "','yyyy-MM-dd')";
				sql2 = sql2 + " and JHJSRQ <= sysdate ";
			}

		}

		if (filter.containsKey("yyb")) {
			value = String.valueOf(filter.get("yyb"));
			if (StringUtils.isNotEmpty(value)) {
				sql2 = sql2 + " and yybid = '" + value + "'";
			}
		}
		if (filter.containsKey("crmName")) {
			value = String.valueOf(filter.get("crmName"));
			if (StringUtils.isNotEmpty(value)) {
				sql2 = sql2 + " and EMPLOYEE_CRM_name like '%" + value + "%'";
			}
		}
		System.out.println("sql=======>" + sql);
		System.out.println("sql2=======>" + sql2);
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("sql", sql);
		conditions.put("sql2", sql2);
		return conditions;
	}

	public String exportProref(String path, List<Map<String, Object>> list)
			throws IOException {
		ExportExcel<ExportTzsq> ex = new ExportExcel<ExportTzsq>();
		String[] headers = { "编号", "客户姓名", "团队经理", "理财经理" };
		List<ExportTzsq> dataset = new ArrayList<ExportTzsq>();
		int i = 1;
		for (int a = 0; a < list.size(); a++) {
			dataset.add(new ExportTzsq(i + "", list.get(a).get("CJRXM") + "",
					list.get(a).get("EMPLOYEE_CRM_NAME") + "", list.get(a).get(
							"EMPLOYEE_CCA_NAME")
							+ ""));
			i++;
		}
		File temp = new File(path);
		if (!temp.exists()) {
			temp.mkdirs();
		}
		temp = null;
		path += "//" + UUID.randomUUID().toString() + ".xls";
		OutputStream out = new FileOutputStream(path);
		ex.exportExcel("投资申请信息导出", "", headers, dataset, out,
				"yyyy-MM-dd HH:mm:ss");
		out.close();
		return path;
	}

	public void exportHkmx(String path, Map<String, Object> map)
			{
		String mouFilePath = InitSetupListener.rootPath + "agaeeFile"
				+ File.separator;
		String inputPath = "";
		Java2Excel je = new Java2Excel();
		HashMap data = new HashMap();
		List l;
		String[] a;
		List<Map<String, Object>> list = this.searchXhTzsq(
				"queryOverStateXhTzsqList", map);

		inputPath = mouFilePath + "kjhkmx.xls";
		data.put("table", "1");
		data.put("table1", "0@2");// 第一个seet页，第2行开始
		l = new ArrayList();
		a = new String[10];
		for (int i = 0; i < a.length - 1; i++) {
			a[i] = i + "@v";
		}
		a[9] = "9@N";
		l.add(a);
		Double cyzqjzhj = 0.0;// 持有债权价值-合计
		int xu = 0;
		for (Map<String, Object> m : list) {
			xu++;
			cyzqjzhj += Double.parseDouble(m.get("LENTMONEY") + "");
			a = new String[10];
			a[0] = m.get("CJRXM") + "";
			a[1] = m.get("TZSQBH") + "";
			a[2] = m.get("JHTZRQ") + "";
			a[3] = m.get("JHTZJE") + "";
			a[4] = m.get("TZCP_MC") + "";
			a[5] = m.get("MOQI") + "";
			a[6] = m.get("CITYNAME") + "";
			// a[7] = m.get("HSZJKHH") + "";
			a[7] = m.get("HSZJYHMC") + "";
			a[8] = m.get("HSZJYHZH") + "";
			a[9] = m.get("LENTMONEY") + "";
			l.add(a);
		}
		a = new String[10];
		// a[0] = "合计";
		for (int i = 0; i < a.length - 1; i++) {
			a[i] = "";
		}
		a[9] = cyzqjzhj + "";
		l.add(a);
		data.put("table10@2", l);// 第一个seet页，第2行开始
		// }
		je.toExcel(inputPath, path, data);
	}

	/**
	 * 保存投资申请附件同时更改状态
	 * 
	 * @param id
	 * @param flag
	 * @param fileName
	 * @author xjs
	 * @date 2013-8-15 下午3:55:26
	 */
	public void saveUploadedFiles(String id, String flag,
			List<Map<String, String>> fileName) {
		if (fileName.size() > 0) {
			for (Map<String, String> m : fileName) {
				XhUploadFiles xhUploadFiles = new XhUploadFiles();
				xhUploadFiles.setFilename(m.get("fileName"));
				xhUploadFiles.setFilepath("upload");
				xhUploadFiles.setNewfilename(m.get("newFileName"));
				xhUploadFiles.setFileOwner("XH_TZSQ");
				xhUploadFiles.setMainId(Long.parseLong(id));
				xhUploadFilesDao.save(xhUploadFiles);
			}
		}
		if ("0".equals(flag)) {
			XhTzsq xhTzsq = getXhTzsq(Long.parseLong(id));
			xhTzsq.setState("8");
			saveXhTzsq(xhTzsq);
		}
	}
	
	
	private Map<String, Object> conditionsForJjdj(Map<String, Object> filter) {
		String sql = "";
		String sql2 = "";
		String value = "";
	
		// ------------------------

		if (filter.containsKey("date")) {
			value = String.valueOf(filter.get("date"));
			if (StringUtils.isNotEmpty(value)) {
				String sqlDate = " to_date(a." + value + ",'yyyy-MM-dd') ";
				if (value.equals("MOQI")) {
					//sqlDate = "z." + value;
					String sqlDate2 = " to_date(z." + value + ",'yyyy-MM-dd') ";
					if (filter.containsKey("startdate")) {
						value = String.valueOf(filter.get("startdate"));
						if (StringUtils.isNotEmpty(value)) {
							sql2 += " and " + sqlDate2 + " >= to_date('" + value
									+ "','yyyy-MM-dd')";
						}
					}
					if (filter.containsKey("enddate")) {
						value = String.valueOf(filter.get("enddate"));
						if (StringUtils.isNotEmpty(value)) {
							sql2 += " and " + sqlDate2 + " <= to_date('" + value
									+ "','yyyy-MM-dd')";
						}
					}
				}else{
					if (filter.containsKey("startdate")) {
						value = String.valueOf(filter.get("startdate"));
						if (StringUtils.isNotEmpty(value)) {
							sql += " and " + sqlDate + " >= to_date('" + value
									+ "','yyyy-MM-dd')";
						}
					}
					if (filter.containsKey("enddate")) {
						value = String.valueOf(filter.get("enddate"));
						if (StringUtils.isNotEmpty(value)) {
							sql += " and " + sqlDate + " <= to_date('" + value
									+ "','yyyy-MM-dd')";
						}
					}
				}

			}
		}

		// 计划投资日期
		if (filter.containsKey("jhtzrq")) {
			value = String.valueOf(filter.get("jhtzrq"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.JHTZRQ = '" + value + "'";
			}
		}
		
		// 级联查询sql
		sql = sql + PropertiesUtils.getLendSql();

		// if (page.getOrderBy()!=null){
		// sql = sql + " order by " + page.getOrderBy() + " " + page.getOrder();
		// }
		
		
		

		if (filter.containsKey("yyb")) {
			value = String.valueOf(filter.get("yyb"));
			if (StringUtils.isNotEmpty(value)) {
				sql2 = sql2 + " and yybid = '" + value + "'";
			}
		}
		if (filter.containsKey("zqstartdate")) {
			value = String.valueOf(filter.get("zqstartdate"));
			if (StringUtils.isNotEmpty(value)) {
				sql2 = sql2 + " and  ppzqrq >= '" + value + "'";
			}
		}
		if (filter.containsKey("zqoverdate")) {
			value = String.valueOf(filter.get("zqoverdate"));
			if (StringUtils.isNotEmpty(value)) {
				sql2 = sql2 + " and  ppzqrq <= '" + value + "'";
			}
		}
		if (filter.containsKey("xystartdate")) {
			value = String.valueOf(filter.get("xystartdate"));
			if (StringUtils.isNotEmpty(value)) {
				sql= sql + " and a.sjhkrq >= '" + value + "'";
			}
		}
		if (filter.containsKey("xyoverdate")) {
			value = String.valueOf(filter.get("xyoverdate"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.sjhkrq <= '" + value + "'";
			}
		}
		sql = sql + " order by  a.JHTZRQ ";
		System.out.println("sql=======>" + sql);
		System.out.println("sql2=======>" + sql2);
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("sql", sql);
		conditions.put("sql2", sql2);
		return conditions;
	}
	/*
	 * 财富进件登记
	 */
	@Transactional(readOnly = true)
	public List<Map<String,Object>> searchCfjjdj(Map<String,Object> filter,JdbcPage page)
	{
		return jdbcDao.searchPagesByMergeSqlTemplate("queryCfjjdj",conditionsForJjdj(filter), page);
	}
	
	/*
	 * 财富进件登记
	 */
	@Transactional(readOnly = true)
	public List<Map<String,Object>> searchCfjjdjAll(Map<String,Object> filter)
	{
		return jdbcDao.searchByMergeSqlTemplate("queryCfjjdj",conditionsForJjdj(filter));
	}

	public void exportCfJjdj(String path,Map<String, Object> map){
		String mouFilePath = InitSetupListener.rootPath + "agaeeFile"
				+ File.separator;
		String inputPath = "";
		Java2Excel je = new Java2Excel();
		HashMap data = new HashMap();
		List l ;
		String oldYyb = "";
		String newYyb = "";
		String[] a;
		List<Map<String, Object>> list = this.searchCfjjdjAll(map);
		data.put("{1}", DateUtils.format(new Date(),"yyyy年MM月dd日"));
		//data.put("{2}", orgName);
		inputPath = mouFilePath + "cfjjdjb.xls";
		data.put("table", "1");
		data.put("table1", "0@3");// 第一个seet页，第2行开始
		l = new ArrayList();
		a = new String[23];
		a[0] = "0@v";
		a[1] = "1@v";
		a[2] = "2@v";
		a[3] = "3@v";
		a[4] = "4@v";
		a[5] = "5@v";
		a[6] = "6@v";
		a[7] = "7@v";
		a[8] = "8@n2";
		a[9] = "9@v";
		a[10] = "10@v";
		a[11] = "11@v";
		a[12] = "12@v";
		a[13] = "13@v";
		a[14] = "14@v";
		a[15] = "15@v";
		a[16] = "16@v";
		a[17] = "17@v";
		a[18] = "18@v";
		a[19] = "19@vN";
		a[20] = "20@v";
		a[21] = "21@v";
		a[22] = "22@v";
		l.add(a);
		int xu = 0;
		for(Map<String, Object> m:list){
			xu++;
			a = new String[23];
    		a[0] = xu+"";
    		newYyb = res(m.get("YYB")+"");
    		/*
    		if(StringUtils.isNotEmpty(oldYyb)){
    			if(oldYyb.equals(newYyb)){
    				newYyb = "同上@red";
    			}else{
    				oldYyb = newYyb;
    			}
    		}else{
    			oldYyb = newYyb;
    		}
    		*/
    		a[1] = newYyb;
    		a[2] = res(m.get("CONTRACT_NUMBER")+"");
    		a[3] = m.get("CJRXM")+"";
    		a[4] = m.get("TZCP_MC")+"";
    		a[5] = res(m.get("UPLOADDATE")+"");
    		a[6] = m.get("JHHKRQ")+"";
    		a[7] = m.get("JHTZRQ")+"";
    		a[8] = res(m.get("JHTZJE")+"");
    		a[9] = res(m.get("EMPLOYEE_CRM_NAME")+"");
    		a[10] = res(m.get("EMPLOYEE_CCA_NAME")+"");
    		a[11] = res(m.get("CREATE_BY")+"");
    		a[12] = res(m.get("CHUSHEN_NAME")+"");
    		a[13] = resColor((m.get("UPLOADDATE")+""),(m.get("CHUSHEN_DATE")+""),1);
    		a[14] = res(m.get("SFCQ")+"");
    		a[15] = res(m.get("CQCS")+"");
    		a[16] = res(m.get("SPTG_DATE")+"");
    		a[17] = res(m.get("PPZQRQ")+"");
    		a[18] = res(m.get("SJHKRQ")+"");
    		a[19] = resColor((m.get("JHHKRQ")+""),(m.get("HKRQ")+""),1);
    		a[20] = res(m.get("1")+"");//res(m.get("OVERCREDITTIME")+"");
    		a[21] = res(m.get("ALLTIMES")+"");//res(m.get("MAKEJKHTTIME")+"");
    		a[22] = res(m.get("1")+"");//res(m.get("QDRQ")+"");
    		l.add(a);
		}
		data.put("table10@3", l);// 第一个seet页，第3行开始
    	//}
    	je.toExcel(inputPath, path, data);
    }
	private String res(String value){
		if(StringUtils.isNotEmpty(value) && !"null".equals(value)) {
			return value;
		}else{
			return "";
		}
	}
	
	private synchronized String resColor(String firstday,String nextday,int days){
		String value = "";
		if(StringUtils.isNotEmpty(nextday) && nextday.indexOf("-")>0){
			value = nextday;
			if(StringUtils.isNotEmpty(firstday) && firstday.indexOf("-")>0 ){
				try {
					Date first = CreditHarmonyComputeUtilties.getDateAfter(DateUtils.parse(firstday, "yyyy-MM-dd"),days);
					Date next = DateUtils.parse(nextday, "yyyy-MM-dd");
					if(next.after(first)){
						value = nextday + "@red";
					}
				} catch (ParseException e) {
					System.out.println("firstday==" + firstday + ";;nextday==" + nextday + ";;;" + e);
				}
			}
		}
		return value;
	}

	public void saveCreditApply(XhTzsq xhTzsq, String fileName,String newFileName) {
		// TODO Auto-generated method stub
		XhTzsq xhTzsqFind = this.getXhTzsq(xhTzsq.getId());
		xhTzsqFind.setCreditdate(xhTzsq.getCreditdate());
		xhTzsqFind.setServicefee(xhTzsq.getServicefee());
		xhTzsqFind.setIsurgent(xhTzsq.getIsurgent());
		xhTzsqFind.setCreditstate("2");
		this.saveXhTzsq(xhTzsqFind);
		
		
		XhUploadFiles xhUploadFiles = new XhUploadFiles();
		if(StringUtils.isNotEmpty(fileName)){
				xhUploadFiles = new XhUploadFiles();
				xhUploadFiles.setFilename(fileName);
				xhUploadFiles.setFilepath("upload");
				xhUploadFiles.setNewfilename(newFileName);
				xhUploadFiles.setFileOwner("XH_TZSQ");
				xhUploadFiles.setMainId(xhTzsqFind.getId());
				xhUploadFiles.setFlag("债权转让");
				baseInfoManager.saveXhUploadFiles(xhUploadFiles);
		}
	}

	
	/**
	 * 债权转让申请审批 -保存
	 * @param xhTzsq
	 */
	public synchronized void saveCreditAudit(XhTzsq xhTzsq,String state,String idea) {
		// TODO Auto-generated method stub
		if(state.equals("3")){
			xhTzsq.setCreditstate(state);
			this.saveXhTzsq(xhTzsq);
			XhTzsqState xhTzsqState = new XhTzsqState();
			xhTzsqState.setDescribe("已申请债权转让");
			xhTzsqState.setRemarks(idea);
			xhTzsqState.setXhTzsq(xhTzsq);
			xhTzsqState.setState("31");
			xhTzsqStateDao.save(xhTzsqState);
		}else{
			xhTzsq.setCreditstate(state);
			this.saveXhTzsq(xhTzsq);
			XhTzsqState xhTzsqState = new XhTzsqState();
			xhTzsqState.setDescribe("债权转让审批未通过");
			xhTzsqState.setRemarks(idea);
			xhTzsqState.setXhTzsq(xhTzsq);
			xhTzsqState.setState("32");
			xhTzsqStateDao.save(xhTzsqState);
		}
		
	}

	
	/*
	 * 债权转让申请
	 */
	public void CreditApply(XhTzsq xhTzsq, String creTime) {
		// TODO Auto-generated method stub
		xhTzsq.setCreditstate("1");
		this.saveXhTzsq(xhTzsq);
		
		XhMadeword xhMadeword = new XhMadeword();
		xhMadeword.setState("0");
		xhMadeword.setStype("0");
		xhMadeword.setCretime(creTime);
		xhMadeword.setTableId(xhTzsq.getId());
		xhMadeword.setTableName("xh_apply_credit");
		xhMadewordDao.save(xhMadeword);
	}
}

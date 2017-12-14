package cn.com.cucsi.app.service.xhcf;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.app.dao.loan.XhJksqStateDao;
import cn.com.cucsi.app.dao.xhcf.XhAvailableValueOfClaimsDao;
import cn.com.cucsi.app.dao.xhcf.XhIpcApplyDao;
import cn.com.cucsi.app.dao.xhcf.XhIpcConstractDao;
import cn.com.cucsi.app.dao.xhcf.XhLoanerAccountDao;
import cn.com.cucsi.app.dao.xhcf.XhMakeLoansDao;
import cn.com.cucsi.app.entity.xhcf.XhAvailableValueOfClaims;
import cn.com.cucsi.app.entity.xhcf.XhIpcConstract;
import cn.com.cucsi.app.entity.xhcf.XhJkht;
import cn.com.cucsi.app.entity.xhcf.XhJksq;
import cn.com.cucsi.app.entity.xhcf.XhJksqState;
import cn.com.cucsi.app.entity.xhcf.XhLoanerAccount;
import cn.com.cucsi.app.entity.xhcf.XhMakeLoans;
import cn.com.cucsi.app.service.baseinfo.BaseInfoManager;
import cn.com.cucsi.app.web.util.CreditHarmonyComputeUtilties;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.orm.Page;
import cn.com.cucsi.vechicle.dao.XhCarLoanApplyDao;
import cn.com.cucsi.vechicle.entity.xhcf.XhCarLoanApply;
import cn.com.cucsi.vechicle.service.XhCarLoanApplyManager;
import cn.com.cucsi.vechicle.util.CarOperation;

//Spring Bean的标识.
@Component
// 默认将类中的所有函数纳入事务管理.
@Transactional
public class XhMakeLoansManager {

	private XhMakeLoansDao xhMakeLoansDao;
	private XhAvailableValueOfClaimsDao xhAvailableValueOfClaimsDao;
	private XhLoanerAccountDao xhLoanerAccountDao;
	private XhJksqStateDao xhJksqStateDao;
	private XhIpcConstractDao xhIpcConstractDao;
	@Autowired
	public void setXhIpcConstractDao(XhIpcConstractDao xhIpcConstractDao) {
		this.xhIpcConstractDao = xhIpcConstractDao;
	}
	
	private XhIpcApplyDao xhIpcApplyDao;

	@Autowired
	public void setXhIpcApplyDao(XhIpcApplyDao xhIpcApplyDao) {
		this.xhIpcApplyDao = xhIpcApplyDao;
	}
	
	@Autowired
	public void setXhLoanerAccountDao(XhLoanerAccountDao xhLoanerAccountDao) {
		this.xhLoanerAccountDao = xhLoanerAccountDao;
	}

	@Autowired
	public void setXhAvailableValueOfClaimsDao(
			XhAvailableValueOfClaimsDao xhAvailableValueOfClaimsDao) {
		this.xhAvailableValueOfClaimsDao = xhAvailableValueOfClaimsDao;
	}

	@Autowired
	public void setXhJksqStateDao(XhJksqStateDao xhJksqStateDao) {
		this.xhJksqStateDao = xhJksqStateDao;
	}

	@Autowired
	public void setXhMakeLoansDao(XhMakeLoansDao xhMakeLoansDao) {
		this.xhMakeLoansDao = xhMakeLoansDao;
	}

	private JdbcDao jdbcDao;

	@Autowired
	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}
	
	@Autowired
	private XhCarLoanApplyDao xhCarLoanApplyDao;
	
	@Autowired
	private XhCarLoanApplyManager xhCarLoanApplyManager;

	@Transactional(readOnly = true)
	public Page<XhMakeLoans> searchXhMakeLoans(final Page<XhMakeLoans> page,
			final Map<String, Object> filters) {
		return xhMakeLoansDao.queryXhMakeLoans(page, filters);
	}

	@Transactional(readOnly = true)
	public XhMakeLoans getXhMakeLoans(Long id) {
		return xhMakeLoansDao.get(id);
	}

	@Transactional(readOnly = false)
	public synchronized void saveXhMakeLoans(XhMakeLoans entity) {
		
		// 变更申请状态
		XhJkht contract = entity.getLoanContract();
		XhJksq loanApply = entity.getLoanContract().getXhJksq();
		
		String sql = "select t.id from xh_available_value_of_claims t where 1=1 ";
		sql = sql + " and t.loan_contract_id = "+contract.getId();
		List<Map<String, Object>> claimsList = jdbcDao.searchByMergeSql(sql);
		if(claimsList.size() == 0){
			// 保存放款信息
			xhMakeLoansDao.save(entity);
			
			loanApply.setState("61");
			loanApply.setFkje(String.valueOf(entity.getLoanContract().getFkje()));
			
			XhJksqState state = new XhJksqState();
			state.setXhjksq(loanApply);
			state.setDescribe("已放款");
			state.setState(loanApply.getState());
			xhJksqStateDao.save(state);
			// 初始化可用债权价值
			XhAvailableValueOfClaims availableValue = new XhAvailableValueOfClaims();
			availableValue.setXhJksq(loanApply);
			availableValue.setMakeLoan(entity);// 放款实体
			availableValue.setJkbh(contract.getJkhtbm());// 借款编号
			availableValue.setLoanContract(contract);// 合同实体
			availableValue.setJkje(contract.getHtje());// 借款金额
			availableValue.setKyzqjz(contract.getHtje());// 可用债权价值:初始值=借款金额
			availableValue.setSytjje(contract.getHtje());// 剩余推荐金额：初始值=借款金额
			availableValue.setSqhkrq(contract.getQshkrq());// 还款起始日期
	
			availableValue.setHkr(CreditHarmonyComputeUtilties
					.getBackMoneyDateOfMonth(contract.getQshkrq()));// 每月的还款日15或30
																	// ，还款日值为30的，2月份的还款日为2月份最后1天
			availableValue
					.setZqyjcyl(Math.pow((1 + contract.getDkll() / 100), 12) - 1);
			availableValue.setSyqs(Long.valueOf((contract.getHkqs())));// 借款期数 字段名命名问题
//			Long remainingMonths = CreditHarmonyComputeUtilties.getRemainingMonths(
//					contract.getQshkrq(), Long.valueOf((contract.getHkqs())));
			
			Long remainingMonths = reKyzqjzSyqs(availableValue.getSqhkrq(), availableValue.getHkr(), availableValue.getSyqs());
			availableValue.setSyhkys(remainingMonths);// 剩余月数
			String sDate = CreditHarmonyComputeUtilties.getLastDateOfBackMoney(
					contract.getQshkrq(), contract.getHkqs());
			availableValue.setMqhkrq(sDate);// //还款截至日期
			availableValue.setMiddleMan(contract.getMiddleMan());// 中間人
			availableValue.setZjrcybl(Double.valueOf(100));// 中间人持有比例，初始值100%
	
			xhAvailableValueOfClaimsDao.save(availableValue);
			// 初始化还款账户
			XhLoanerAccount xhLoanerAccount = new XhLoanerAccount();
			xhLoanerAccount.setXhAvailableValueOfClaims(availableValue);
			xhLoanerAccount.setLoanContract(contract);
			xhLoanerAccount.setLoanApply(loanApply);
			xhLoanerAccount.setAccountBalance(0.00);// 初始还款账户金额
			xhLoanerAccount.setAccountState(0);// 初始化账户状态
			xhLoanerAccountDao.save(xhLoanerAccount);
			
			//查询是否是IPC业务，并设置状态为已放款。
			if(loanApply.getBackup01().equals("IPC")){
				sql = "select t.ID,t.IPC_APPLY_ID from XH_IPC_CONSTRACT t where 1=1 ";
				sql = sql + " and t.JKHT_ID = "+contract.getId();
				List<Map<String, Object>> ipcList = jdbcDao.searchByMergeSql(sql);
				if(ipcList.size()==1){
					XhIpcConstract xhIpcConstract = xhIpcConstractDao.get(Long.parseLong(ipcList.get(0).get("ID")+""));
					xhIpcConstract.setState("61");
					xhIpcConstractDao.save(xhIpcConstract);
				}
			}else if(loanApply.getBackup01().equals("CAR")){
				sql = "select * from XH_CAR_LOAN_APPLY t where 1=1 ";
				sql = sql + " and t.XH_JKSQ_ID = "+loanApply.getId();
				List<Map<String, Object>> carList = jdbcDao.searchByMergeSql(sql);
				if(carList.size()==1){
					/*XhIpcConstract xhIpcConstract = xhIpcConstractDao.get(Long.parseLong(ipcList.get(0).get("ID")+""));
					xhIpcConstract.setState("61");
					xhIpcConstractDao.save(xhIpcConstract);*/
					XhCarLoanApply xhCarLoanApply = xhCarLoanApplyDao.get(Long.parseLong(carList.get(0).get("ID")+""));
					xhCarLoanApply.setState("61");
					xhCarLoanApplyDao.save(xhCarLoanApply);
					xhCarLoanApplyManager.saveHistory(xhCarLoanApply.getState(), xhCarLoanApply, CarOperation.YI_FANG_KUAN,CarOperation.YI_FANG_KUAN.getMessage());
				}
			}
		}
	}
	/**
	 * 可用债权剩余期数处理，根据首个还款日期和账单日，期数，计算剩余期数
	 * @param qshkrq
	 * @param zdr
	 * @return
	 */
	private Long reKyzqjzSyqs(String qshkrq,String zdr,Long loanMonths){
		Long value = loanMonths;
		//获取首个结算日期
		String sgsjjsrq = CreditHarmonyComputeUtilties.reKyzqjzSgjsrq(qshkrq, zdr);
		Date nowDate = new Date();
		
		Date newDate = CreditHarmonyComputeUtilties.StringToDate(sgsjjsrq, "yyyy-MM-dd");
		//判断当前日期是否大于首个结算日期
		if(nowDate.after(newDate)){
			//执行sql，计算剩余期数
			String sql = "select "+loanMonths+"-(trunc( months_between(sysdate,to_date('"+sgsjjsrq+"','YYYY-MM-dd')))+1) as rel from dual";
			List<Map<String, Object>> searchTime = jdbcDao.searchByMergeSql(sql);
			if(searchTime.size()==1){
				String rel = "";
				if (searchTime.get(0).containsKey("REL")) {
					rel = String.valueOf(searchTime.get(0).get("REL"));
					if (StringUtils.isNotEmpty(rel)) {
						value = Long.parseLong(rel);
					}
				}
			}
		}
		return value;
	}
	
	@Transactional(readOnly = true)
	public List<Map<String, Object>> searchXhMakeLoans(String queryName,
			Map<String, Object> filter, JdbcPage page) {
		String sql = "";
		String value = "";
		// 中间人（账户）ID
		if (filter.containsKey("middleMan.id")) {
			value = String.valueOf(filter.get("middleMan.id"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.MIDDLE_MAN_ID = '" + value + "'";
			}
		}
		// 借款合同ID
		if (filter.containsKey("loanContract.id")) {
			value = String.valueOf(filter.get("loanContract.id"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.LOAN_CONTRACT_ID = '" + value + "'";
			}
		}
		// 放款时间
		if (filter.containsKey("makeLoanDate")) {
			value = String.valueOf(filter.get("makeLoanDate"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.MAKE_LOAN_DATE = '" + value + "'";
			}
		}

		if (page.getOrderBy() != null) {
			sql = sql + " order by " + page.getOrderBy() + " "
					+ page.getOrder();
		}else{
			sql = sql + " order by a.create_time desc";
		}

		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("sql", sql);

		return jdbcDao.searchPagesByMergeSqlTemplate(queryName, conditions,
				page);
	}
}

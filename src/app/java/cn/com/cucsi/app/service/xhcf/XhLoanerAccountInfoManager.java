package cn.com.cucsi.app.service.xhcf;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cucsi.app.dao.JdbcDao;
import cn.com.cucsi.app.dao.xhcf.XhLoanerAccountInfoDao;
import cn.com.cucsi.app.entity.xhcf.XhIpcConstract;
import cn.com.cucsi.app.entity.xhcf.XhJksq;
import cn.com.cucsi.app.entity.xhcf.XhLoanerAccount;
import cn.com.cucsi.app.entity.xhcf.XhLoanerAccountInfo;
import cn.com.cucsi.app.service.PublicService;
import cn.com.cucsi.app.service.loan.XhJksqManager;
import cn.com.cucsi.core.orm.JdbcPage;
import cn.com.cucsi.core.orm.Page;

//Spring Bean的标识.
@Component
// 默认将类中的所有函数纳入事务管理.
@Transactional
public class XhLoanerAccountInfoManager extends PublicService{

	private XhLoanerAccountInfoDao xhLoanerAccountInfoDao;
	
	private XhJksqManager xhJksqManager;
	
	private XhIpcConstractManager xhIpcConstractManager;

	@Autowired
	public void setXhIpcConstractManager(XhIpcConstractManager xhIpcConstractManager) {
		this.xhIpcConstractManager = xhIpcConstractManager;
	}

	@Autowired
	public void setXhJksqManager(XhJksqManager xhJksqManager) {
		this.xhJksqManager = xhJksqManager;
	}

	@Autowired
	public void setXhLoanerAccountInfoDao(
			XhLoanerAccountInfoDao xhLoanerAccountInfoDao) {
		this.xhLoanerAccountInfoDao = xhLoanerAccountInfoDao;
	}

	private JdbcDao jdbcDao;

	@Autowired
	public void setJdbcDao(JdbcDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}

	@Transactional(readOnly = true)
	public Page<XhLoanerAccountInfo> searchXhLoanerAccountInfo(
			final Page<XhLoanerAccountInfo> page,
			final Map<String, Object> filters) {
		return xhLoanerAccountInfoDao.queryXhLoanerAccountInfo(page, filters);
	}

	@Transactional(readOnly = true)
	public XhLoanerAccountInfo getXhLoanerAccountInfo(Long id) {
		return xhLoanerAccountInfoDao.get(id);
	}

	/**
	 * 保存借款人还款明细,同时核减可用债权价值,修改账户结余
	 * 
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public Integer saveXhLoanerAccountInfo(XhLoanerAccountInfo entity) {
		Integer res = 0;
		//无论是否正常还款均按月核减可用债权
//		// 核减可用债权价值
//		XhAvailableValueOfClaims availableValue = entity.getLoanerMainAccount()
//				.getXhAvailableValueOfClaims();
//		Double HoldingRate = availableValue.getZjrcybl();
//		availableValue.setKyzqjz(availableValue.getKyzqjz()
//				- entity.getDeailingMoney() * HoldingRate / 100);//
		Integer accountState = entity.getLoanerMainAccount().getAccountState();
		if(accountState == 2){
			res = 1;
			return res;
		}
        System.out.println(entity.getLoanerMainAccount().getId());
        System.out.println(entity.getDealingTime());
        Double monMoney = entity.getLoanerMainAccount().getLoanContract().getYhkje();
		entity.setDealingType(entity.getDealingType());
		// 账户结余
		entity.getLoanerMainAccount().setAccountBalance(entity.getLoanerMainAccount().getAccountBalance() 	+ entity.getDeailingMoney());
		entity.setBalance(entity.getLoanerMainAccount().getAccountBalance());
		//获取JKSQ状态100：还款中101：提前结清102：正常结清
		String state = getAccountInfoState(entity.getLoanerMainAccount().getLoanContract().getId(), entity.getDeailingMoney());
		if(entity.getDealingType() == 3){
			entity.getLoanerMainAccount().setAccountState(3);
			state = "101";
			setACCInfo(entity.getLoanerMainAccount(), entity.getDeailingMoney(), 1);

		}
		if(StringUtils.isNotEmpty(state)){
			entity.getLoanerMainAccount().getLoanApply().setState(state);
			XhJksq jksq = entity.getLoanerMainAccount().getLoanApply();
			String type = jksq.getBackup01();
			if(type != null && "IPC".equals(type)){
				XhIpcConstract xhIpcConstract = xhIpcConstractManager.getXhIpcConstractByjksqId(jksq.getId());
				xhIpcConstract.setState(state);
				xhIpcConstractManager.saveXhIpcConstractMdy(xhIpcConstract);
			}
			if("102".equals(state)){
				entity.getLoanerMainAccount().setAccountState(1);
			}
		}
		xhLoanerAccountInfoDao.save(entity);
		//setACCInfo(entity.getLoanerMainAccount(), monMoney, 1);
		return res;
	}
	
	public void setACCInfo(XhLoanerAccount xhLoanerAccount, Double deailingMoney, Integer dealingType) {
		double balance = 0;
		double accBbalance = 0;
		XhLoanerAccountInfo accInfo = new XhLoanerAccountInfo();
		accInfo.setBalance(0.0);
		accInfo.setDealingTime(new Date());
		accInfo.setDealingType(dealingType);
		accInfo.setLoanerMainAccount(xhLoanerAccount);
		if(dealingType == 2){
			balance = xhLoanerAccount.getAccountBalance()+deailingMoney;
			accBbalance = deailingMoney;
			accInfo.setDeailingMoney(deailingMoney);
		}else if(dealingType == 1){
			balance = xhLoanerAccount.getAccountBalance()-deailingMoney;
			accBbalance = balance;
			accInfo.setDeailingMoney(-deailingMoney);
		}else if(dealingType == 0){
			balance = xhLoanerAccount.getAccountBalance()+deailingMoney;
			accBbalance = deailingMoney;
			accInfo.setDeailingMoney(deailingMoney);
		}
		accInfo.getLoanerMainAccount().setAccountBalance(balance);
		accInfo.setBalance(accBbalance);
		xhLoanerAccountInfoDao.save(accInfo);
	}
	
	public Date getDateTime(String time){
		Date date = new Date();
    	DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
    	        try {  
    	        	if(time != null && !"".equals(time)){
    	        		date = sdf.parse(time);  
    	        	}
    	       } catch (Exception e) {  
    	            e.printStackTrace();  
    	} 
    	return date;
	}
	
	public void setACCInfo1(XhLoanerAccount xhLoanerAccount, Double deailingMoney, Integer dealingType, String time) {
		double balance = 0;
		double accBbalance = 0;
		XhLoanerAccountInfo accInfo = new XhLoanerAccountInfo();
		accInfo.setBalance(0.0);
		accInfo.setDealingTime(getDateTime(time));
		accInfo.setDealingType(dealingType);
		accInfo.setLoanerMainAccount(xhLoanerAccount);
		if(dealingType == 2){
			balance = xhLoanerAccount.getAccountBalance()+deailingMoney;
			accBbalance = deailingMoney;
			accInfo.setDeailingMoney(deailingMoney);
		}else if(dealingType == 1){
			balance = xhLoanerAccount.getAccountBalance()-deailingMoney;
			accBbalance = balance;
			accInfo.setDeailingMoney(-deailingMoney);
		}else if(dealingType == 0){
			balance = xhLoanerAccount.getAccountBalance()+deailingMoney;
			accBbalance = deailingMoney;
			accInfo.setDeailingMoney(deailingMoney);
		}
		accInfo.getLoanerMainAccount().setAccountBalance(balance);
		accInfo.setBalance(accBbalance);
		xhLoanerAccountInfoDao.save(accInfo);
	}
	
	public String getAccountInfoState(Long contractId, double moneyNew){
		String state = "";
		List<Map<String, Object>> list = getJkhtByAccount(contractId);
		Map<String, Object> map = list.get(0);
		//String qshkrq = String.valueOf(map.get("QSHKRQ"));
		String hkqsOld = String.valueOf(map.get("HKQSOLD"));
		String hkqs = String.valueOf(map.get("HKQS"));
		String money = String.valueOf(map.get("MONEY"));
		double addUpMoney = Double.valueOf(money) * Integer.valueOf(hkqsOld);
		//验证是否第一个还款日
		if(new Integer(hkqs) >= 0 && new Integer(hkqs).intValue() < new Integer(hkqsOld).intValue()){
			state = "100";
		}else
		//验证是否正常结清
		if(new Integer(hkqs).intValue() >= new Integer(hkqsOld).intValue()){
			state = "102";
		}
//		else
//		//验证是否提前结清
//		if(moneyNew >= addUpMoney){
//			state = "101";
//		}
		return state;
	}

	public List<Map<String, Object>> getJkhtByAccount(Long contractId){
		Map<String, Object> conditions = formatSqlByJkhtId(contractId);
		return jdbcDao.searchByMergeSqlTemplate("queryJkhtByXhLoanerAccount", conditions);
	}
	
	private Map<String, Object> formatSqlByJkhtId(Long contractId){
		StringBuffer sql = new StringBuffer();
		sql.append(" and t.id = :id");
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("id", contractId);
		conditions.put("sql", sql.toString());
		return conditions;
	}
	
	
	

	@Transactional(readOnly = true)
	public List<Map<String, Object>> searchXhLoanerAccountInfo(
			String queryName, Map<String, Object> filter, JdbcPage page) {
		String sql = "";
		String value = "";
		// 借款人主账户ID
		if (filter.containsKey("loanerMainAccountId")) {
			value = String.valueOf(filter.get("loanerMainAccountId"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.LOANER_MAIN_ACCOUNT_ID = '" + value + "'";
			}
		}
		// 交易类型 0:还款存入 1：结算划扣
		if (filter.containsKey("dealingType")) {
			value = String.valueOf(filter.get("dealingType"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.DEALING_TYPE = '" + value + "'";
			}
		}
		// 交易金额
		if (filter.containsKey("deailingMoney")) {
			value = String.valueOf(filter.get("deailingMoney"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.DEAILING_MONEY = '" + value + "'";
			}
		}
		// 交易时间
		if (filter.containsKey("dealingTime")) {
			value = String.valueOf(filter.get("dealingTime"));
			if (StringUtils.isNotEmpty(value)) {
				sql = sql + " and a.DEALING_TIME = '" + value + "'";
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


    public boolean notHandle(Long id, Double returnMoney, Date returnDate) {

        String hql = " from XhLoanerAccountInfo xhLoanerAccountInfo where 1 = 1 and  xhLoanerAccountInfo.loanerMainAccount.id=:id ";
        hql += " and xhLoanerAccountInfo.deailingMoney  = :returnMoney and xhLoanerAccountInfo.dealingTime  = :returnDate";        
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("returnMoney", returnMoney);
        params.put("returnDate", returnDate);
        params.put("id", id);
        List infos = xhLoanerAccountInfoDao.find(hql, params);
        if(infos != null && infos.size()>0)
            return false;
        else
            return true;
    }
    
    public void deleteAccInfo(Long id){
    	xhLoanerAccountInfoDao.delete(id);
    }
    
    public void saveAccInfo(XhLoanerAccountInfo entity){
    	xhLoanerAccountInfoDao.save(entity);
    }
    
    public XhLoanerAccountInfo getXhLoanerAccountInfoById(Long id){
    	return xhLoanerAccountInfoDao.get(id);
    }
    
    public Long getAccountIdById(Long id){
    	Long accId = null;
    	XhLoanerAccountInfo xhLoanerAccountInfo = xhLoanerAccountInfoDao.get(id);
    	if(xhLoanerAccountInfo != null){
    		accId = xhLoanerAccountInfo.getLoanerMainAccount().getId();
    	}
    	return accId;
    }
}
